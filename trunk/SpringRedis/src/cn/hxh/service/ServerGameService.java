package cn.hxh.service;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

import cn.hxh.core.IGameService;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.EnterGame_C2S;
import cn.hxh.dto.Login_C2S;

@Component
public class ServerGameService implements IGameService {

	@Autowired
	private UserManager userManager;

	public void login(Channel channel, Login_C2S msg) {
		userManager.login(channel, msg);
	}

	public void createRole(UserInfo userInfo, CreateRole_C2S msg) {
		userManager.createRole(userInfo, msg);
	}

	public void enterGame(UserInfo userInfo, EnterGame_C2S reqMsg) throws InvalidProtocolBufferException {
		userManager.enterGame(userInfo, reqMsg);
	}

}
