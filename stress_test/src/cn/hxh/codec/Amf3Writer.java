package cn.hxh.codec;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Amf3 writer
 * 
 * @author bright
 * @version 20111106
 */
public final class Amf3Writer implements Amf3Types {

	private static ThreadLocal<Amf3Writer> _writer = new ThreadLocal<Amf3Writer>();

	private IdentityHashMap<Object, Integer> _objectTable;

	private HashMap<TraitsInfo, Integer> _traitsTable;

	private HashMap<String, Integer> _stringTable;

	private ByteArrayOutputStream _stream;

	private DataOutputStream _out;

	private void writeAmf3Boolean(boolean b) throws IOException {
		if (b)
			_out.write(kTrueType);
		else
			_out.write(kFalseType);
	}

	private void writeAmf3Null() throws IOException {
		_out.write(kNullType);
	}

	private void writeAmf3String(String s) throws IOException {
		_out.write(kStringType);
		writeStringWithoutType(s);
	}

	private void writeAmf3UTF(String s) throws IOException {
		int strlen = s.length();
		int utflen = 0;
		int c, count = 0;
		char[] charr = new char[strlen];
		s.getChars(0, strlen, charr, 0);
		for (int i = 0; i < strlen; i++) {
			c = charr[i];
			if (c <= 0x007F) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}
		writeUInt29((utflen << 1) | 1);
		byte[] bytearr = new byte[utflen];
		for (int i = 0; i < strlen; i++) {
			c = charr[i];
			if (c <= 0x007F) {
				bytearr[count++] = (byte) c;
			} else if (c > 0x07FF) {
				bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			} else {
				bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			}
		}
		_out.write(bytearr, 0, utflen);
	}

	private void writeStringWithoutType(String s) throws IOException {
		if (s.length() == 0) {
			writeUInt29(1);
			return;
		}
		if (!byReference(s)) {
			writeAmf3UTF(s);
			return;
		}
	}

	private void writeUInt29(int ref) throws IOException {
		if (ref < 0x80) {
			_out.writeByte(ref);
		} else if (ref < 0x4000) {
			_out.writeByte(((ref >> 7) & 0x7F) | 0x80);
			_out.writeByte(ref & 0x7F);

		} else if (ref < 0x200000) {
			_out.writeByte(((ref >> 14) & 0x7F) | 0x80);
			_out.writeByte(((ref >> 7) & 0x7F) | 0x80);
			_out.writeByte(ref & 0x7F);
		} else if (ref < 0x40000000) {
			_out.writeByte(((ref >> 22) & 0x7F) | 0x80);
			_out.writeByte(((ref >> 15) & 0x7F) | 0x80);
			_out.writeByte(((ref >> 8) & 0x7F) | 0x80);
			_out.writeByte(ref & 0xFF);
		} else {
			throw new IOException();
		}
	}

	private void writeAmf3Date(Date d) throws IOException {
		_out.write(kDateType);
		if (!byReference(d)) {
			writeUInt29(1);
			_out.writeDouble((double) d.getTime());
		}
	}

	private void writeAmf3Double(double d) throws IOException {
		_out.write(kDoubleType);
		_out.writeDouble(d);
	}

	private void writeAmf3Int(int i) throws IOException {
		if (i >= INT28_MIN_VALUE && i <= INT28_MAX_VALUE) {
			i = i & UINT29_MASK; // Mask is 2^29 - 1
			_out.write(kIntegerType);
			writeUInt29(i);
		} else {
			writeAmf3Double(i);
		}
	}

	private void writeObjectTraits(TraitsInfo ti) throws IOException {
		String className = ti.getClassName();
		if (!byReference(ti)) {
			int count = 0;
			List<String> propertyNames = null;
			boolean externalizable = ti.isExternalizable();
			if (!externalizable) {
				propertyNames = ti.getProperties();
				if (propertyNames != null)
					count = propertyNames.size();
			}
			boolean dynamic = ti.isDynamic();
			writeUInt29(3 | (externalizable ? 4 : 0) | (dynamic ? 8 : 0)
					| (count << 4));
			writeStringWithoutType(className);
			if (!externalizable && propertyNames != null) {
				for (int i = 0; i < count; i++) {
					String propName = (String) ti.getProperty(i);
					writeStringWithoutType(propName);
				}
			}
		}
	}

	private void writeAmf3Map(Map<?, ?> map) throws IOException {
		_out.write(kObjectType);
		if (!byReference(map)) {
			TraitsInfo ti = new TraitsInfo(null, true, false, null);
			writeObjectTraits(ti);
			Iterator<?> it = map.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				if (key != null) {
					String propName = key.toString();
					writeStringWithoutType(propName);
					writeObject(map.get(key));
				}
			}
			writeStringWithoutType(EMPTY_STRING);
		} else {
		}
	}

	private void writeCollection(Collection<?> c) throws IOException {
		_out.write(kArrayType);
		if (!byReference(c)) {
			writeUInt29((c.size() << 1) | 1);
			writeStringWithoutType(EMPTY_STRING);
			for (Object item : c) {
				writeObject(item);
			}
		}
	}

	private void writeAmf3Array(Object o, Class<?> componentType)
			throws IOException {
		if (componentType.isPrimitive()) {
			writePrimitiveArray(o);
		} else if (componentType.equals(Byte.class)) {
			writeAmf3ByteArray((Byte[]) o);
		} else if (componentType.equals(Character.class)) {
			writeCharArrayAsString((Character[]) o);
		} else {
			writeObjectArray((Object[]) o);
		}
	}

	private void writePrimitiveArray(Object obj) throws IOException {
		Class<?> aType = obj.getClass().getComponentType();
		if (aType.equals(Character.TYPE)) {
			char[] c = (char[]) obj;
			writeCharArrayAsString(c);
		} else if (aType.equals(Byte.TYPE)) {
			writeAmf3ByteArray((byte[]) obj);
		} else {
			_out.write(kArrayType);
			if (!byReference(obj)) {
				if (aType.equals(Boolean.TYPE)) {
					boolean[] b = (boolean[]) obj;
					writeUInt29((b.length << 1) | 1);
					writeStringWithoutType(EMPTY_STRING);
					for (int i = 0; i < b.length; i++) {
						writeAmf3Boolean(b[i]);
					}
				} else if (aType.equals(Integer.TYPE)
						|| aType.equals(Short.TYPE)) {
					int length = Array.getLength(obj);
					writeUInt29((length << 1) | 1);
					writeStringWithoutType(EMPTY_STRING);
					for (int i = 0; i < length; i++) {
						int v = Array.getInt(obj, i);
						writeAmf3Int(v);
					}
				} else {
					int length = Array.getLength(obj);
					writeUInt29((length << 1) | 1);
					writeStringWithoutType(EMPTY_STRING);
					for (int i = 0; i < length; i++) {
						double v = Array.getDouble(obj, i);
						writeAmf3Double(v);
					}
				}
			}
		}
	}

	private void writeAmf3ByteArray(byte[] ba) throws IOException {
		_out.write(kByteArrayType);
		if (!byReference(ba)) {
			int length = ba.length;
			writeUInt29((length << 1) | 1);
			_out.write(ba, 0, length);
		}
	}

	private void writeAmf3ByteArray(Byte[] ba) throws IOException {
		_out.write(kByteArrayType);
		if (!byReference(ba)) {
			int length = ba.length;
			writeUInt29((length << 1) | 1);
			for (Byte b : ba) {
				if (b == null)
					_out.write(0);
				else
					_out.write(b.byteValue());
			}
		}
	}

	private void writeCharArrayAsString(Character[] ca) throws IOException {
		int length = ca.length;
		char[] chars = new char[length];
		for (int i = 0; i < length; i++) {
			Character c = ca[i];
			if (c == null)
				chars[i] = 0;
			else
				chars[i] = ca[i].charValue();
		}
		writeCharArrayAsString(chars);
	}

	private void writeCharArrayAsString(char[] ca) throws IOException {
		String str = new String(ca);
		writeAmf3String(str);
	}

	private void writeObjectArray(Object[] values) throws IOException {
		_out.write(kArrayType);
		if (!byReference(values)) {
			writeUInt29((values.length << 1) | 1);
			writeStringWithoutType(EMPTY_STRING);
			for (int i = 0; i < values.length; ++i) {
				Object item = values[i];
				writeObject(item);
			}
		}
	}

	private boolean byReference(String s) throws IOException {
		Object ref = _stringTable.get(s);
		if (ref != null) {
			try {
				int refNum = ((Integer) ref).intValue();
				writeUInt29(refNum << 1);
			} catch (ClassCastException e) {
				throw new IOException("String reference is not an Integer");
			}
		} else {
			_stringTable.put(s, new Integer(_stringTable.size()));
		}
		return (ref != null);
	}

	private boolean byReference(TraitsInfo ti) throws IOException {
		Object ref = _traitsTable.get(ti);
		if (ref != null) {
			try {
				int refNum = ((Integer) ref).intValue();
				writeUInt29((refNum << 2) | 1);
			} catch (ClassCastException e) {
				throw new IOException("TraitsInfo reference is not an Integer");
			}
		} else {
			_traitsTable.put(ti, new Integer(_traitsTable.size()));
		}
		return (ref != null);
	}

	private boolean byReference(Object o) throws IOException {
		Object ref = _objectTable.get(o);
		if (ref != null) {
			try {
				int refNum = ((Integer) ref).intValue();
				writeUInt29(refNum << 1);
			} catch (ClassCastException e) {
				throw new IOException("Object reference is not an Integer");
			}
		} else {
			_objectTable.put(o, new Integer(_objectTable.size()));
		}
		return (ref != null);
	}

	private void writeObject(Object o) throws IOException {
		if (o == null) {
			writeAmf3Null();
			return;
		}
		if (o instanceof String || o instanceof Character) {
			String s = o.toString();
			writeAmf3String(s);
		} else if (o instanceof Number) {
			if (o instanceof Integer || o instanceof Short || o instanceof Byte) {
				int i = ((Number) o).intValue();
				writeAmf3Int(i);
			} else if (o instanceof BigInteger || o instanceof BigDecimal) {
				writeAmf3String(((Number) o).toString());
			} else {
				double d = ((Number) o).doubleValue();
				writeAmf3Double(d);
			}
		} else if (o instanceof Boolean) {
			writeAmf3Boolean(((Boolean) o).booleanValue());
		} else if (o instanceof Date) {
			writeAmf3Date((Date) o);
		} else if (o instanceof Calendar) {
			writeAmf3Date(((Calendar) o).getTime());
		} else {
			Class<?> cls = o.getClass();
			if (o instanceof Map<?, ?>) {
				writeAmf3Map((Map<?, ?>) o);
			} else if (o instanceof Collection<?>) {
				writeCollection((Collection<?>) o);
			} else if (o.getClass().isArray()) {
				writeAmf3Array(o, cls.getComponentType());
			} else {
				writeAmf3Null();
			}
		}
	}

	private void flush() throws IOException {
		_out.flush();
	}

	private void reset() {
		_stream.reset();
		_objectTable.clear();
		_traitsTable.clear();
		_stringTable.clear();
	}

	public Amf3Writer() {
		_stream = new ByteArrayOutputStream();
		_out = new DataOutputStream(_stream);
		_objectTable = new IdentityHashMap<Object, Integer>(64);
		_traitsTable = new HashMap<TraitsInfo, Integer>(10);
		_stringTable = new HashMap<String, Integer>(64);
	}

	public byte[] toBytes() {
		return _stream.toByteArray();
	}

	public static byte[] write(Object obj) {
		try {
			Amf3Writer writer = _writer.get();
			if (writer == null) {
				writer = new Amf3Writer();
				_writer.set(writer);
			}
			writer.reset();
			writer.writeObject(obj);
			writer.flush();
			byte[] result = writer.toBytes();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
