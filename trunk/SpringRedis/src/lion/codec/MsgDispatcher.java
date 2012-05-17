package lion.codec;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 此类是一个消息分发器，为不同的请求消息分配合适的处理器。<br/>
 * 内部使用一个{@link HashMap}来缓存消息处理器，试图对一个KEY绑定处理器进行覆盖操作是不会成功的。<br/>
 * 
 * @author serv_dev
 * 
 */
public class MsgDispatcher {
	private static Logger logger = Logger.getLogger(MsgDispatcher.class);

	private Map<Integer, MsgProcessor> processors = null;

	public MsgDispatcher() {
		processors = new HashMap<Integer, MsgProcessor>();
	}

	/**
	 * 添加一个MsgProcessor实例，并和一个Integer绑定。<br/>
	 * 试图对一个KEY绑定处理器进行覆盖操作是不会成功的
	 * 
	 * @param name
	 *            key
	 * @param processor
	 *            value
	 */
	public void addMsgProcessor(Integer name, MsgProcessor processor) {
		processors.put(name, processor);
	}

	/**
	 * 根据给定的键取得绑定的处理器。
	 * 
	 * @param name
	 *            key
	 * @return 指定的键绑定的处理器
	 */
	public MsgProcessor getMsgProcessor(Integer name) {
		MsgProcessor msgProc = processors.get(name);
		if (msgProc == null) {
			logger.warn("can not find processor for:" + name);
		}
		return msgProc;
	}

	public Map<Integer, MsgProcessor> getMsgProccessor() {
		return processors;
	}
}
