package cn.hxh.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.hxh.MainServer;
import cn.hxh.dto.GetNewRole_S2C;

@Component
public class ClientGameService implements IGameService {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	public void getNewRole(GetNewRole_S2C msg) {
		logger.info("receive getNewRoleMsg,count={}", msg.getRoleCount());
	}

}
