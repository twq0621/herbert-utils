package net.snake.netio;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.snake.netio.message.RequestMsg;
import net.snake.netio.util.DataEncryption;

final public class ClientRequestII implements RequestMsg {
	private byte[] buffer = null;
	private ByteBuffer reader = null;
	private int msgcode;
	private long receiveTime = System.currentTimeMillis();

	public ClientRequestII(byte[] array) throws IllegalArgumentException {
		if (array == null) {
			return;
		}
		if (array.length == 0) {
			return;
		}
		buffer = array;

		reader = ByteBuffer.wrap(buffer);
		msgcode = reader.getInt();
	}

	public String getString() throws IOException {
		int utflen =reader.getShort();
		char[] chararr = null;
		chararr = new char[utflen];

		int c, char2, char3;
		int strBegin = reader.position();
		int count = 0;
		int chararr_count = 0;

		while (count < utflen) {
			c = (int) buffer[count + strBegin] & 0xff;
			if (c > 127)
				break;
			count++;
			chararr[chararr_count++] = (char) c;
		}

		while (count < utflen) {
			c = (int) buffer[count + strBegin] & 0xff;
			switch (c >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				/* 0xxxxxxx */
				count++;
				chararr[chararr_count++] = (char) c;
				break;
			case 12:
			case 13:
				/* 110x xxxx 10xx xxxx */
				count += 2;
				if (count > utflen)
					throw new IOException(
							"malformed input: partial character at end");
				char2 = (int) buffer[count - 1 + strBegin];
				if ((char2 & 0xC0) != 0x80)
					throw new IOException("malformed input around byte "
							+ count);
				chararr[chararr_count++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
				break;
			case 14:
				/* 1110 xxxx 10xx xxxx 10xx xxxx */
				count += 3;
				if (count > utflen)
					throw new IOException(
							"malformed input: partial character at end");
				char2 = (int) buffer[count - 2 + strBegin];
				char3 = (int) buffer[count - 1 + strBegin];
				if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
					throw new IOException("malformed input around byte "
							+ (count - 1));
				chararr[chararr_count++] = (char) (((c & 0x0F) << 12)
						| ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
				break;
			default:
				/* 10xx xxxx, 1111 xxxx */
				throw new IOException("malformed input around byte " + count);
			}
		}
		// The number of chars produced may be less than utflen
		reader.position(reader.position() + utflen);
		return new String(chararr, 0, chararr_count);
	}

	@Override
	public int getMsgCode() {
		return msgcode;
	}

	@Override
	public int getTotalBytes() {
		return buffer.length;
	}

	@Override
	public void BitReversion() {
		if (this.msgcode > 10000) {
			// 消息号(int)4个字节 所以从4以后开始位反 10000外的消息为客户端发来的消息
			DataEncryption.BitReversion(buffer, 4);
		}
	}

	@Override
	public long getReceiveTime() {
		return receiveTime;
	}

	@Override
	public byte getByte() throws IOException {
		return reader.get();
	}

	@Override
	public short getShort() throws IOException {
		return reader.getShort();
	}

	@Override
	public int getInt() throws IOException {
		return reader.getInt();
	}

	@Override
	public long getLong() throws IOException {
		return reader.getLong();
	}

	@Override
	public float getFloat() throws IOException {
		return reader.getFloat();
	}

	@Override
	public double getDouble() throws IOException {
		return reader.getDouble();
	}

}
