package game.chat.service;

import game.chat.dto.ConnectChat_C2S;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.core.IGameService;

@Component
public class ChatServerEnter implements IGameService {

	@Autowired
	private ChatManager chatManager;

	public void connectChat(Channel channel, ConnectChat_C2S reqMsg) {
		chatManager.connectChat(channel, reqMsg);
	}

}
