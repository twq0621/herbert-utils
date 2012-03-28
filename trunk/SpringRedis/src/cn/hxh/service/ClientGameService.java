package cn.hxh.service;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.hxh.MainServer;
import cn.hxh.core.IGameService;
import cn.hxh.dto.GetNewRole_S2C;
import cn.hxh.dto.GetOnlineNames_S2C;
import cn.hxh.dto.TestPushMsg_S2C;

@Component
public class ClientGameService implements IGameService {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	public void getNewRole(Channel channel, GetNewRole_S2C msg) {
		logger.info("receive getNewRoleMsg,count={}", msg.getRoleCount());
	}

	public void getOnlineNames(Channel channel, GetOnlineNames_S2C msg) {
		logger.info("getOnlineNames,ret={}", msg.getRoleNames());
	}
	
	public void testPushMsg(Channel channel,TestPushMsg_S2C msg)
	{
		logger.info("testPushMsg ret={}",msg.getNameDTO());
	}

}
