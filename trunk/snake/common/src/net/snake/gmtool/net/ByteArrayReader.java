package net.snake.gmtool.net;

import java.io.*;

public class ByteArrayReader extends DataInputStream {
	public ByteArrayReader(InputStream in) {
		super(in);
	}

	public ByteArrayReader(byte[] bytes) {
		super(new ByteArrayInputStream(bytes));
	}

	public byte[] readShortVar() throws IOException {
		int length = readShort();
		if (length < 0 || length > 50000) {
			throw new IOException("数据长度" + length + "");
		}
		byte[] b = new byte[length];
		read(b);
		return b;
	}

	public Object readIObject() throws IOException, ClassNotFoundException {
		byte[] bytes = readIntBytes();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		return ois.readObject();
	}

	public byte[] readIntBytes() throws IOException {
		int length = readInt();
		byte[] b = new byte[length];
		read(b);
		return b;
	}

	public byte[] readIntVar() throws IOException {
		int length = readInt();
		if (length < 0 || length > 50000) {
			throw new IOException("数据长度" + length);
		}
		byte[] b = new byte[length];
		read(b);
		return b;
	}

	public String readIntUTF() throws IOException {
		try {
			return new String(readIntVar(), "utf-8");
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

}
