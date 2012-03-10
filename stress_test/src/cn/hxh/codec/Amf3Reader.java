package cn.hxh.codec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UTFDataFormatException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Amf3 Reader
 * 
 * @author BrightLi
 * @version 20100505
 */
public final class Amf3Reader implements Amf3Types {

	private static ThreadLocal<Amf3Reader> _reader = new ThreadLocal<Amf3Reader>();

	private ByteArrayInputStream _stream;

	private ArrayList<Object> _objectTable;

	private ArrayList<String> _stringTable;

	private ArrayList<TraitsInfo> _traitsTable;

	private long readLong() throws IOException {
		byte[] buffer = new byte[8];
		_stream.read(buffer, 0, 8);
		return (((long) buffer[0] << 56) + ((long) (buffer[1] & 255) << 48)
				+ ((long) (buffer[2] & 255) << 40)
				+ ((long) (buffer[3] & 255) << 32)
				+ ((long) (buffer[4] & 255) << 24) + ((buffer[5] & 255) << 16)
				+ ((buffer[6] & 255) << 8) + ((buffer[7] & 255) << 0));
	}

	private Double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}

	private int readUInt29() throws IOException {
		int value;
		int b = _stream.read() & 0xFF;
		if (b < 128) {
			return b;
		}
		value = (b & 0x7F) << 7;
		b = _stream.read() & 0xFF;
		if (b < 128) {
			return (value | b);
		}
		value = (value | (b & 0x7F)) << 7;
		b = _stream.read() & 0xFF;

		if (b < 128) {
			return (value | b);
		}
		value = (value | (b & 0x7F)) << 8;
		b = _stream.read() & 0xFF;
		return (value | b);
	}

	private Object getObjectReference(int ref) {
		return _objectTable.get(ref);
	}

	private String getStringReference(int ref) {
		return _stringTable.get(ref);
	}

	private TraitsInfo getTraitReference(int ref) {
		return _traitsTable.get(ref);
	}

	private Object readXml() throws IOException {
		String xml = null;
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			xml = (String) getObjectReference(ref >> 1);
		} else {
			int len = (ref >> 1);
			if (len == 0)
				xml = EMPTY_STRING;
			else
				xml = readUTF(len);
			_objectTable.add(xml);
		}
		if (xml != null && xml.indexOf('<') == -1)
			return xml;
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// factory.setNamespaceAware(true);
			// factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(
					"<a>abc</a>")));
		} catch (Exception ex) {
		}
		return document;
	}

	private String readString() throws IOException {
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			return getStringReference(ref >> 1);
		} else {
			int len = (ref >> 1);
			if (len == 0) {
				return EMPTY_STRING;
			}
			String str = readUTF(len);
			_stringTable.add(str);
			return str;
		}
	}

	private String readUTF(int utflen) throws IOException {
		char[] charr = new char[utflen];
		byte[] bytearr = new byte[utflen];
		int c, char2, char3;
		int count = 0;
		int chCount = 0;
		_stream.read(bytearr, 0, utflen);
		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			switch (c >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				count++;
				charr[chCount] = (char) c;
				break;
			case 12:
			case 13:
				count += 2;
				if (count > utflen)
					throw new UTFDataFormatException();
				char2 = (int) bytearr[count - 1];
				if ((char2 & 0xC0) != 0x80)
					throw new UTFDataFormatException();
				charr[chCount] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
				break;
			case 14:
				count += 3;
				if (count > utflen)
					throw new UTFDataFormatException();
				char2 = (int) bytearr[count - 2];
				char3 = (int) bytearr[count - 1];
				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
					throw new UTFDataFormatException();
				charr[chCount] = (char) (((c & 0x0F) << 12)
						| ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
				break;
			default:
				throw new UTFDataFormatException();
			}
			chCount++;
		}
		return new String(charr, 0, chCount);
	}

	private ASObject readScriptObject() throws IOException {
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			return (ASObject) getObjectReference(ref >> 1);
		} else {
			TraitsInfo ti = readTraits(ref);
			String className = ti.getClassName();
			boolean externalizable = ti.isExternalizable();
			ASObject object = new ASObject();
			if (className == null || className.length() == 0) {
			} else {
				object.setType(className);
			}
			_objectTable.add(object);
			if (externalizable) {
			} else {
				if (ti.isDynamic()) {
					for (;;) {
						String name = readString();
						if (name == null || name.length() == 0)
							break;
						Object value = readObject();
						object.put(name, value);
					}
				}
			}
			return object;
		}
	}

	private TraitsInfo readTraits(int ref) throws IOException {
		if ((ref & 3) == 1) {
			return getTraitReference(ref >> 2);
		} else {
			boolean externalizable = ((ref & 4) == 4);
			boolean dynamic = ((ref & 8) == 8);
			int count = (ref >> 4);
			String className = readString();
			TraitsInfo ti = new TraitsInfo(className, dynamic, externalizable,
					count);
			_traitsTable.add(ti);
			for (int i = 0; i < count; i++) {
				String propName = readString();
				ti.addProperty(propName);
			}
			return ti;
		}
	}

	private Object readObjectValue(int type) throws IOException {
		Object value = null;
		switch (type) {
		case kStringType:
			value = readString();
			break;
		case kObjectType:
			value = readScriptObject();
			break;
		case kArrayType:
			value = readArray();
			break;
		case kFalseType:
			value = Boolean.FALSE;
			break;
		case kTrueType:
			value = Boolean.TRUE;
			break;
		case kIntegerType:
			int i = readUInt29();
			i = (i << 3) >> 3;
			value = new Integer(i);
			break;
		case kDoubleType:
			value = readDouble();
			break;
		case kUndefinedType:
			break;
		case kNullType:
			break;
		case kXMLType:
		case kAvmPlusXmlType:
			value = readXml();
			break;
		case kDateType:
			value = readDate();
			break;
		case kByteArrayType:
			value = readByteArray();
			break;
		default:
			throw new IOException();
		}
		return value;
	}

	private Object readArray() throws IOException {
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			return getObjectReference(ref >> 1);
		} else {
			int len = (ref >> 1);
			Object array = null;
			Map<String, Object> map = null;
			for (;;) {
				String name = readString();
				if (name == null || name.length() == 0)
					break;
				if (map == null) {
					map = new HashMap<String, Object>();
					array = map;
					_objectTable.add(array);
				}
				Object value = readObject();
				map.put(name, value);
			}
			if (map == null) {
				array = new Object[len];
				_objectTable.add(array);
				for (int i = 0; i < len; i++) {
					Object item = readObject();
					Array.set(array, i, item);
				}
			} else {
				for (int i = 0; i < len; i++) {
					Object item = readObject();
					map.put(Integer.toString(i), item);
				}
			}
			return array;
		}
	}

	private Date readDate() throws IOException {
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			return (Date) getObjectReference(ref >> 1);
		} else {
			long time = readLong();
			Date d = new Date(time);
			_objectTable.add(d);
			return d;
		}
	}

	private byte[] readByteArray() throws IOException {
		int ref = readUInt29();
		if ((ref & 1) == 0) {
			return (byte[]) getObjectReference(ref >> 1);
		} else {
			int len = (ref >> 1);
			byte[] ba = new byte[len];
			_objectTable.add(ba);
			_stream.read(ba, 0, len);
			return ba;
		}
	}

	private void setInputStream(ByteArrayInputStream stream) {
		_stream = stream;
		_stream.reset();
		_stringTable.clear();
		_objectTable.clear();
		_traitsTable.clear();
	}

	private Object readObject() throws IOException {
		int type = _stream.read();
		Object value = readObjectValue(type);
		return value;
	}

	public Amf3Reader() {
		_stringTable = new ArrayList<String>(64);
		_objectTable = new ArrayList<Object>(64);
		_traitsTable = new ArrayList<TraitsInfo>(10);
	}

	public static Object read(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			Amf3Reader reader = _reader.get();
			if (reader == null) {
				reader = new Amf3Reader();
				_reader.set(reader);
			}
			reader.setInputStream(new ByteArrayInputStream(bytes));
			Object obj = reader.readObject();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
