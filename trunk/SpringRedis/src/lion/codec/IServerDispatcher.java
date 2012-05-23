package lion.codec;

import lion.core.GamePlayer;
import lion.message.MsgDispatcher;
import lion.processor.MsgProcessor;

/**
 * @author serv_devr
 */
public interface IServerDispatcher {
	/**
	 * 消息分发器
	 * 
	 * @return
	 */
	public MsgDispatcher getMsgDispatcher();

	/**
	 * 工作路径
	 * 
	 * @return
	 */
	public String getWorkPath();

	/**
	 * 消息处理器配置文件地址
	 * 
	 * @return
	 */
	public String getProccessProp();

	/**
	 * 检查IP是否是管理IP
	 * 
	 * @param ip
	 * @return true是
	 */
	public boolean checkIP(String ip);

	/**
	 * 返回消息号处理类
	 * 
	 * @param msgCode
	 * @return
	 */
	public MsgProcessor getMsgProcessor(int msgCode);

	/**
	 * session关闭处理
	 * 
	 * @param player
	 */
	public void sessionClosed(GamePlayer player);
}
