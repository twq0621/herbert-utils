package game.chat.service;

import game.chat.dto.ConnectChat_C2S;
import game.chat.dto.ConnectChat_S2C;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatManager {

	private static Logger logger = LoggerFactory.getLogger(ChatManager.class);

	public void connectChat(Channel channel, ConnectChat_C2S reqMsg) {
		logger.info("connect to chat server!roleName={}", reqMsg.getRoleName());
		ConnectChat_S2C retMsg = new ConnectChat_S2C();
		channel.write(retMsg);
	}
	
	public void sendAll()
	{
		
	}
}
