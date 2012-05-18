package lion.innernet;

import java.io.*;

public class ByteArrayWriter extends ByteArrayOutputStream {
	DataOutputStream dos;

	public ByteArrayWriter() {
		dos = new DataOutputStream(this);
	}

	public ByteArrayWriter(int size) {
		super(size);
		dos = new DataOutputStream(this);
	}

	public final void writeBoolean(boolean v) throws IOException {
		dos.writeBoolean(v);
	}

	public final void writeByte(int v) throws IOException {
		dos.writeByte(v);
	}

	public final void writeShort(int v) throws IOException {
		dos.writeShort(v);
	}

	public final void writeChar(int v) throws IOException {
		dos.writeChar(v);
	}

	public final void writeInt(int v) throws IOException {
		dos.writeInt(v);
	}

	public final void writeLong(long v) throws IOException {
		dos.writeLong(v);
	}

	public final void writeFloat(float v) throws IOException {
		dos.writeFloat(v);
	}

	public final void writeDouble(double v) throws IOException {
		dos.writeDouble(v);
	}

	public final void writeBytes(String s) throws IOException {
		dos.writeBytes(s);
	}

	public final void writeChars(String s) throws IOException {
		dos.writeChars(s);
	}

	public final void writeShortVar(byte[] s) throws IOException {
		if (s != null) {
			writeShort(s.length);
			write(s);
		} else {
			writeShort(0);
		}
	}

	public final void writeIntVar(byte[] s) throws IOException {
		if (s != null) {
			writeInt(s.length);
			write(s);
		} else {
			writeInt(0);
		}
	}

	// ===============
	public ByteArrayWriter writeIObject(Object obj) throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bao);
		oos.writeObject(obj);
		oos.close();
		writeIntBytes(bao.toByteArray());
		return this;

	}

	public ByteArrayWriter writeIntBytes(byte[] s) throws IOException {
		if (s != null) {
			writeInt(s.length);
			dos.write(s);
		} else {
			writeShort(0);
		}
		return this;
	}

	public void writeUTF(String utf) throws IOException {
		writeShortUTF(utf);
	}

	public void writeShortUTF(String utf) throws IOException {
		if (utf == null) {
			writeShort(0);
		} else {
			dos.writeUTF(utf);
		}
	}

	public void writeShortUTF(long utf) throws IOException {
		String t = String.valueOf(utf);
		writeShortUTF(t);
	}

	public void writeIntUTF(String utf) throws IOException {
		if (utf == null) {
			writeInt(0);
			return;
		}
		writeIntVar(utf.getBytes("utf-8"));
	}

}
