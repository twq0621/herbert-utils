package cn.hxh.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hxh.Utils;

public class CallPool {

	private static Logger logger = LoggerFactory.getLogger(CallPool.class);

	private static Map<String, Method> methodMap = new HashMap<String, Method>();

	private static Class<? extends IGameService> gameService;

	@SuppressWarnings("rawtypes")
	public static void init(Class<? extends IGameService> gameService) {
		CallPool.gameService = gameService;
		try {
			Class gameServiceClass = Class.forName(CallPool.gameService
					.getName());
			Method[] methods = gameServiceClass.getDeclaredMethods();
			for (Method method : methods) {
				methodMap.put(method.getName().toLowerCase(), method);
			}
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		}
	}

	public static void execute(Channel channel, Object remoteObj) {
		String simpleClassName = remoteObj.getClass().getSimpleName();
		if (simpleClassName.endsWith(Utils.DTO_END_STR)) {
			String methodName = simpleClassName.substring(0,
					simpleClassName.indexOf((Utils.DTO_END_STR))).toLowerCase();
			Method callMethod = methodMap.get(methodName);
			if (callMethod == null) {
				logger.error("method not find!name={},class={}", methodName,
						CallPool.gameService.getName());
				return;
			}
			try {
				callMethod.invoke(
						SpringContextHolder.getBean(CallPool.gameService),
						channel, remoteObj);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
}
