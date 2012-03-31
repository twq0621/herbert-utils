package game.service;

import game.dto.CreateRole_C2S;
import game.dto.EnterGame_C2S;
import game.dto.Login_C2S;
import lion.core.ChannelClose_C2S;
import lion.core.IGameService;
import lion.core.UserInfo;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 服务端服务入口
 * @author hexuhui
 *
 */
@Component
public class ServerServiceEnter implements IGameService {

	@Autowired
	private UserManager userManager;

	public void login(Channel channel, Login_C2S msg) {
		userManager.login(channel, msg);
	}

	public void createRole(Channel channel, CreateRole_C2S msg) {
		UserInfo userInfo = userManager.getUserInfo(channel.getId());
		userManager.createRole(userInfo, msg);
	}

	public void enterGame(Channel channel, EnterGame_C2S reqMsg) throws InvalidProtocolBufferException {
		UserInfo userInfo = userManager.getUserInfo(channel.getId());
		userManager.enterGame(userInfo, reqMsg);
	}

	public void channelClose(Channel channel, ChannelClose_C2S dto) {
	}

}
