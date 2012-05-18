package lion.message;

import java.io.IOException;

/**
 * 此接口声明了对客户端消息的读取函数，由消息的消费者{@link MsgProcesser}使用。
 * 
 * 游戏协议是变长的，基于byte stream的协议。使用mina的IoBuffer包装，进行读取最为理想。 <br/>
 * 但mina规定,解码后变成自定义的消息类型，IoHandler.messageReceived()才会被调用。
 * 
 * 
 * @author wutao
 */
public interface MyRequestMsg {
	// 获得消息号
	public int getMsgCode();

	public int getTotalBytes();

	public void BitReversion();

	// 获得得到消息时的时间
	public long getReceiveTime();

	public byte getByte() throws IOException;

	public short getShort() throws IOException;

	public int getInt() throws IOException;

	public long getLong() throws IOException;

	public float getFloat() throws IOException;

	public double getDouble() throws IOException;

	public String getString() throws IOException;
	// public DataIOUtil getUtil();
	// public Object getObj() throws IOException,ClassNotFoundException;
}
