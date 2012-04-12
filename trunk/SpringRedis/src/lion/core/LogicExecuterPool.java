package lion.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lion.common.Utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单线程执行游戏逻辑
 * @author hexuhui
 *
 */
public class LogicExecuterPool {

	private static ExecutorService threadPool;

	private static Logger logger = LoggerFactory.getLogger(LogicExecuterPool.class);

	private static Map<String, Method> methodMap = new HashMap<String, Method>();

	private static Class<? extends IGameService> gameService;

	static {
		BasicThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("logic-thread").daemon(true).priority(Thread.MAX_PRIORITY).build();
		threadPool = Executors.newFixedThreadPool(1, factory);
	}

	@SuppressWarnings("rawtypes")
	public static void init(Class<? extends IGameService> gameService) {
		LogicExecuterPool.gameService = gameService;
		try {
			Class gameServiceClass = Class.forName(LogicExecuterPool.gameService.getName());
			Method[] methods = gameServiceClass.getDeclaredMethods();
			for (Method method : methods) {
				methodMap.put(method.getName().toLowerCase(), method);
			}
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		}
	}

	public static void executeEvents() {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				//TODO handle 
			}
		});
	}

	public static void executeIoRequest(final Channel channel, final Object remoteObj) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				execute(channel, remoteObj);
			}
		});
	}

	public static void execute(Channel channel, Object remoteObj) {
		String simpleClassName = remoteObj.getClass().getSimpleName();
		if (simpleClassName.endsWith(Utils.DTO_END_STR)) {
			String methodName = simpleClassName.substring(0, simpleClassName.indexOf((Utils.DTO_END_STR))).toLowerCase();
			Method callMethod = methodMap.get(methodName);
			if (callMethod == null) {
				logger.error("method not find!name={},class={}", methodName, LogicExecuterPool.gameService.getName());
				return;
			}
			try {
				callMethod.invoke(SpringContextHolder.getBean(LogicExecuterPool.gameService), channel, remoteObj);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

}
