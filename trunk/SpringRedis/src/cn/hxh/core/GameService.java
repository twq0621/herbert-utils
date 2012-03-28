package cn.hxh.core;

import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.dto.GetNewRole_C2S;
import cn.hxh.stat.CommonStatManager;

@Component
public class GameService implements IGameService {

	@Autowired
	private CommonStatManager commonStatManager;

	public void getNewRole(Channel channel, GetNewRole_C2S c2sEntity) {
		commonStatManager.getNewRole(channel, c2sEntity.getQueryDay());
	}

}
