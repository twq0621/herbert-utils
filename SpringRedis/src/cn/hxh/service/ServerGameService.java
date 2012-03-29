package cn.hxh.service;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.core.IGameService;
import cn.hxh.dto.GetNewRole_C2S;
import cn.hxh.dto.GetOnlineNames_C2S;
import cn.hxh.dto.Login_C2S;

@Component
public class ServerGameService implements IGameService {

	@Autowired
	private CommonStatManager commonStatManager;
	@Autowired
	private UserManager userManager;

	public void getNewRole(Channel channel, GetNewRole_C2S c2sEntity) {
		commonStatManager.getNewRole(channel, c2sEntity.getQueryDay());
	}

	public void getOnlineNames(Channel channel, GetOnlineNames_C2S queryMsg) {
		userManager.getOnlineNames(channel, queryMsg);
	}

	public void login(Channel channel, Login_C2S msg) {
		userManager.login(channel, msg);
	}

}
