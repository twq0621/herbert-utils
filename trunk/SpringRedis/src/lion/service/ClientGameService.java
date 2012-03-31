package lion.service;

import java.util.Set;

import lion.MainServer;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.hxh.common.ErrorCode;
import cn.hxh.core.IGameService;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.CreateRole_S2C;
import cn.hxh.dto.EnterGame_C2S;
import cn.hxh.dto.EnterGame_S2C;
import cn.hxh.dto.GetNewRole_S2C;
import cn.hxh.dto.GetOnlineNames_S2C;
import cn.hxh.dto.Login_S2C;
import cn.hxh.dto.RoleDTO;
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

	public void testPushMsg(Channel channel, TestPushMsg_S2C msg) {
		logger.info("testPushMsg ret={}", msg.getNameDTO());
	}

	public void login(Channel channel, Login_S2C msg) {
		logger.info("login retCode={}", msg.getCode());
		if (msg.getCode() == ErrorCode.SUCCESS) {
			//获取角色信息
			Set<RoleDTO> dtoSet = msg.getRoleList();
			if (dtoSet.size() > 0) {
				EnterGame_C2S reqMsg = new EnterGame_C2S();
				reqMsg.setSelectedRole(dtoSet.iterator().next().getName());
				channel.write(reqMsg);
			} else {
				CreateRole_C2S reqMsg = new CreateRole_C2S();
				reqMsg.setRoleName("亲");
				reqMsg.setGender(2);
				reqMsg.setCharacterId(1);
				channel.write(reqMsg);
			}
		}
	}

	public void createRole(Channel channel, CreateRole_S2C msg) {
		logger.info("createRole ret={}", msg.getCode());
		if (msg.getCode() == ErrorCode.SUCCESS) {
			EnterGame_C2S reqMsg = new EnterGame_C2S();
			reqMsg.setSelectedRole("亲");
			channel.write(reqMsg);
		}
	}

	public void enterGame(Channel channel, EnterGame_S2C msg) {
		logger.info("enterGame ret={}", msg.getCode());
	}

}
