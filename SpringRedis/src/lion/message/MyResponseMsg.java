package lion.message;


/**
 * 此接口声明了响应消息编码函数，服务端生产的每个响应消息都必须实现此接口。<br>
 * 这里声明的函数在protocolEncoder中会被自动调用，编码成byte stream发送到客户端。
 * 
 * @author serv_dev
 * 
 */
public interface MyResponseMsg {
	/**
	 * 获得消息号
	 * 
	 * @return
	 */
	public int getMsgCode();

	public int getTotalBytes();

	/**
	 * 设置消息号
	 * 
	 * @param code
	 */
	public void setMsgCode(int code);

	/**
	 * 获取整条消息内容
	 * 
	 * @return
	 */
	public byte[] entireMsg();
}
