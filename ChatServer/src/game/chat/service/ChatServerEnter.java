package game.chat.service;

import game.chat.dto.Chat_C2S;
import game.chat.dto.ConnectChat_C2S;
import lion.core.ChannelClose_C2S;
import lion.core.IGameService;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 服务端所有服务的入口
 * @author hexuhui
 *
 */
@Component
public class ChatServerEnter implements IGameService {

	@Autowired
	private ChatManager chatManager;

	public void connectChat(Channel channel, ConnectChat_C2S reqMsg) throws InvalidProtocolBufferException {
		chatManager.connectChat(channel, reqMsg);
	}

	public void chat(Channel channel, Chat_C2S reqMsg) {
		chatManager.chat(channel, reqMsg);
	}

	public void channelClose(Channel channel, ChannelClose_C2S dto) {
		chatManager.removeRole(channel);
	}

}
