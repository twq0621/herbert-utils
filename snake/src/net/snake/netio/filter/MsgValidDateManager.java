package net.snake.netio.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


public class MsgValidDateManager {
	private static Logger logger = Logger.getLogger(MsgValidDateManager.class);

	private Map<Integer, Integer> msgTimeMap = new ConcurrentHashMap<Integer, Integer>();
	public static MsgValidDateManager instance;

	private MsgValidDateManager() {
	}

	public static MsgValidDateManager getInstance() {
		if (instance == null) {
			instance = new MsgValidDateManager();
		}
		return instance;
	}

	public Integer getMsgIntervalTime(int msgCode) {
		return msgTimeMap.get(msgCode);
	}

	public void initDate(Properties properties) {
		Set<Entry<Object, Object>> es = properties.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String requestcode = (String) entry.getKey();
			String requestProcessor = (String) entry.getValue();
			Integer msgCode = Integer.valueOf(requestcode);
			Exception ex = null;
			try {
				String[] str = requestProcessor.trim().split(",");
				if (str.length == 2) {
					Integer msgTime = Integer.valueOf(str[1]);
					msgTimeMap.put(msgCode, msgTime);
				} else {
					msgTimeMap.put(msgCode, 0);
				}
			} catch (SecurityException e) {
				ex = e;
			} catch (IllegalArgumentException e) {
				ex = e;
			}
			if (ex != null) {
				logger.warn("construct " + requestProcessor + "fail", ex);
			}
		}
		return;
	}
}
