package net.snake.netio;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;

import net.snake.netio.message.ResponseMsg;
import net.snake.netio.util.DataEncryption;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 服务端发给客户端的消息。 所有返回给客户端的消息都最好继承于它.<br>
 * 这里封装了基本的输出字节操作。
 * 
 * @author serv_dev
 * 
 */
public class ServerResponse implements ResponseMsg {
	protected static final Logger logger = Logger.getLogger(ServerResponse.class);

	private ByteBuffer output = null;
	private int capacity = 0;

	private int batchIndex = -1;

	private IoBuffer outMsg = null;

	private int msgCode = 0;
	private int length = 0;

	public int formatLen;
	public static final int Format_Byte = 1;
	public static final int Format_Short = 2;
	public static final int Format_Int = 4;
	public static final int Format_Long = 8;

	public ServerResponse() {
		output = ByteBuffer.allocate(64);
		capacity = 64;

		output.position(8);
		//output.position(6);
	}

	public ServerResponse(int initialSize) {
		output = ByteBuffer.allocate(initialSize);
		capacity = initialSize;

		output.position(8);
		//output.position(6);
	}

	public final boolean beginBatchAdd(int formatLen) {
		batchIndex = output.position();

		boolean flag = false;
		switch (formatLen) {
		case Format_Byte:
			this.writeByte(0);
			flag = true;
			break;
		case Format_Short:
			this.writeShort(0);
			flag = true;
			break;
		case Format_Int:
			this.writeInt(0);
			flag = true;
			break;
		case Format_Long:
			this.writeLong(0);
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		this.formatLen = formatLen;
		return flag;
	}

	/**
	 * 
	 * @param counter
	 * @return
	 */
	public final void endBatchAdd(long counter) {

		switch (this.formatLen) {
		case Format_Byte:
			output.put(batchIndex, (byte) counter);
			break;
		case Format_Short:
			output.putShort(batchIndex, (short) counter);
			break;
		case Format_Int:
			output.putInt(batchIndex, (int) counter);
			break;
		case Format_Long:
			output.putLong(batchIndex, counter);
			break;
		default:
			break;
		}
		batchIndex = -1;
	}

	public final void writeByte(int value) {
		needMoreBuffer(this, 1);
		this.output.put((byte) value);
	}

	public final void writeShort(int value) {
		needMoreBuffer(this, 2);
		this.output.putShort((short) value);
	}

	public final void writeInt(int value) {
		needMoreBuffer(this, 4);
		this.output.putInt(value);
	}

	public final void writeLong(long value) {
		needMoreBuffer(this, 8);
		this.output.putLong(value);
	}

	public final void writeDouble(double value) {
		needMoreBuffer(this, 8);
		this.output.putLong(Double.doubleToLongBits(value));
	}

	public final void writeBoolean(Boolean value) {
		this.writeByte(value ? 1 : 0);
	}

	public final void writeInterUTF(int msgkey) throws IOException {
		needMoreBuffer(this, 5);
		this.output.putInt(msgkey);
		this.output.put((byte) 0);
	}

	public final void writeInterUTF(int msgkey, String... vars) throws Exception {
		if (vars == null) {
			writeInterUTF(msgkey);
		} else {
			this.writeInt(msgkey);
			this.writeByte(vars.length);
			for (String var : vars) {
				this.writeUTF(var);
			}
		}
	}

	public final void writeUTF(String value) throws Exception {
		int i = value.length();
		int j = 0;
		int l = 0;
		int k;
		for (int i1 = 0; i1 < i; ++i1) {
			k = value.charAt(i1);
			if ((k >= 1) && (k <= 127))
				++j;
			else if (k > 2047)
				j += 3;
			else
				j += 2;
		}
		if (j > 65535)
			throw new UTFDataFormatException("encoded string too long: " + j + " bytes");
		byte[] arrayOfByte = null;
		arrayOfByte = new byte[j + 2];
		arrayOfByte[(l++)] = (byte) (j >>> 8 & 0xFF);
		arrayOfByte[(l++)] = (byte) (j >>> 0 & 0xFF);
		int i2 = 0;
		for (i2 = 0; i2 < i; ++i2) {
			k = value.charAt(i2);
			if (k < 1)
				break;
			if (k > 127)
				break;
			arrayOfByte[(l++)] = (byte) k;
		}
		while (i2 < i) {
			k = value.charAt(i2);
			if ((k >= 1) && (k <= 127)) {
				arrayOfByte[(l++)] = (byte) k;
			} else if (k > 2047) {
				arrayOfByte[(l++)] = (byte) (0xE0 | k >> 12 & 0xF);
				arrayOfByte[(l++)] = (byte) (0x80 | k >> 6 & 0x3F);
				arrayOfByte[(l++)] = (byte) (0x80 | k >> 0 & 0x3F);
			} else {
				arrayOfByte[(l++)] = (byte) (0xC0 | k >> 6 & 0x1F);
				arrayOfByte[(l++)] = (byte) (0x80 | k >> 0 & 0x3F);
			}
			++i2;
		}
		needMoreBuffer(this, j + 2);

		this.output.put(arrayOfByte, 0, j + 2);
	}

	private static void needMoreBuffer(ServerResponse msg, int bytes) {
		if (msg.output.position() + bytes <= msg.output.limit()) {
			return;
		}
		int pos = msg.output.position();

		int times = (msg.output.position() + bytes) / msg.output.limit();

		msg.capacity = msg.output.limit() * (times + 1);
		byte[] newBlock = new byte[msg.capacity];
		System.arraycopy(msg.output.array(), 0, newBlock, 0, pos);
		msg.output = ByteBuffer.wrap(newBlock);
		msg.output.position(pos);
	}

	@Override
	public final int getMsgCode() {
		return msgCode;
	}

	@Override
	public final int getTotalBytes() {
		return length;
	}

	@Override
	public final void setMsgCode(int code) {
		msgCode = code;
	}

	@Override
	public final IoBuffer entireMsg() {
		if (outMsg == null) {
			byte[] body = null;
			body = output.array();

			if (msgCode > 10000) {
				// 前两位协议标志（short 2）协议长度+(int 4) 消息号=6
				DataEncryption.BitReversion(body, 0);
			}
			/* 标识位short 长度short 逻辑标识 int */
			length = output.position();
			output.putShort(0, (short) 127);
			output.putShort(2, (short) length);
			output.putInt(4, (int) msgCode);
//			output.putShort(0, (short) length);
//			output.putInt(2, (int) msgCode);
			outMsg = IoBuffer.wrap(body, 0, length);

			return outMsg;
		}
		IoBuffer newbuf = outMsg.duplicate();
		newbuf.position(0);
		newbuf.limit(length);
		return newbuf;
	}
}
