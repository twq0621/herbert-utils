package net.snake.gamemodel.gm.processor;

import java.io.IOException;

import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

public class TestProClientRequest implements RequestMsg {
	private static Logger logger = Logger.getLogger(TestProClientRequest.class);

	DataIOUtil util = null;
	byte[] buffer = null;
	int msgcode;
	long receiveTime = System.currentTimeMillis();

	@Override
	public int getTotalBytes() {
		return buffer.length + 4;
	}

	@Override
	public long getReceiveTime() {
		return receiveTime;
	}

	public TestProClientRequest(String[] array) throws IllegalArgumentException, IOException {
		if (array == null) {
			throw new IllegalArgumentException("消息缓冲区对象为null");
		}
		if (array.length == 0) {
			throw new IllegalArgumentException("消息缓冲区对象大小为0");
		}
		DataIOUtil out = DataIOUtil.newInstance4Wr();
		out.writeInt(Integer.parseInt(array[1]));
		for (int i = 2; i < array.length; i++) {
			String str = array[i];
			// I:int,B:byte,S:short,
			String type = str.substring(0, 1);
			String val = str.substring(1);
			if ("I".equalsIgnoreCase(type)) {
				int ival = Integer.parseInt(val);
				out.writeInt(ival);
			} else if ("B".equalsIgnoreCase(type)) {
				byte bval = Byte.parseByte(val);
				out.writeByte(bval);
			} else if ("S".equalsIgnoreCase(type)) {
				short sval = Short.parseShort(val);
				out.writeShort(sval);
			} else if ("U".equalsIgnoreCase(type)) {
				out.writeUTF(val);
			} else {
				logger.error("\"" + type + "\"this data type is not be supported，u can use I:int,B:byte,S:short");
			}
		}
		buffer = out.toByteArray();
		util = DataIOUtil.newInstance4Rd(buffer);
		try {
			msgcode = util.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getMsgCode() {
		return msgcode;
	}

	@Override
	public byte getByte() throws IOException {
		return util.readByte();
	}

	@Override
	public short getShort() throws IOException {
		return util.readShort();
	}

	@Override
	public int getInt() throws IOException {
		return util.readInt();
	}

	@Override
	public long getLong() throws IOException {
		return util.readLong();
	}

	@Override
	public float getFloat() throws IOException {
		return util.readFloat();
	}

	@Override
	public double getDouble() throws IOException {
		return util.readDouble();
	}

	@Override
	public String getString() throws IOException {
		return util.readUTF();
	}

	// @Override
	// public Object getObj() throws IOException, ClassNotFoundException {
	// return util.readObj();
	// }

	@Override
	public void BitReversion() {
	}

}
