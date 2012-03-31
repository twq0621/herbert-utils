package cn.hxh.service;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.core.IGameService;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.EnterGame_C2S;
import cn.hxh.dto.Login_C2S;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class ServerGameService implements IGameService {

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

}
