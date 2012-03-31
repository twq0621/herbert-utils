package game.chat.service;

import game.chat.common.ChatConstants;
import game.chat.common.ErrorCode;
import game.chat.dto.Chat_C2S;
import game.chat.dto.Chat_S2C;
import game.chat.dto.ConnectChat_S2C;
import lion.core.ChannelClose_C2S;
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
		if (reqMsg.getCode() == ErrorCode.SUCCESS) {
			Chat_C2S chatMsg = new Chat_C2S();
			chatMsg.setContent("聊天消息1");
			chatMsg.setType(ChatConstants.CHAT_TYPE_ALL);
			channel.write(chatMsg);
		}
	}

	public void chat(Channel channel, Chat_S2C retMsg) {
		logger.info("receive chat msg,content={}", retMsg.getContent());
		channel.close();
	}

	public void channelClose(Channel channel, ChannelClose_C2S dto) {
		//have no use
	}
}
