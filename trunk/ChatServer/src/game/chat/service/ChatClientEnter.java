package game.chat.service;

import game.chat.dto.ConnectChat_S2C;
import lion.core.IGameService;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 客户端服务入口
 * @author hexuhui
 *
 */
@Component
public class ChatClientEnter implements IGameService {

	private static Logger logger = LoggerFactory.getLogger(ChatClientEnter.class);

	public void connectChat(Channel channel, ConnectChat_S2C reqMsg) {
		logger.info("connectChat response,code={}", reqMsg.getCode());
	}
}
