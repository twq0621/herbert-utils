package lion.codec;




/**
 * 处理请求消息的类，代表了一个业务逻辑的运算。<br>
 * 子类请重写process函数，实现业务逻辑。
 * 
 * @author wutao
 * 
 */
public abstract class MsgProcessor {
	
	/**处理器是否可用  用于临时封消息*/
	private boolean enable=true;
	
	/**
	 * 处理请求消息，逻辑运算。
	 * 
	 * @param session
	 *            请求逻辑运算的会话，映射一个客户端
	 * @param request
	 *            请求消息
	 * @return 计算完毕后生成的响应消息
	 */
	public abstract void process(GamePlayer session, MyRequestMsg request)throws Exception;
	
	public boolean isEnable() {
		return enable;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}	
}
