package game.service;

import game.MainServer;
import game.common.ErrorCode;
import game.dto.CreateRole_C2S;
import game.dto.CreateRole_S2C;
import game.dto.EnterGame_C2S;
import game.dto.EnterGame_S2C;
import game.dto.GetNewRole_S2C;
import game.dto.GetOnlineNames_S2C;
import game.dto.Login_S2C;
import game.dto.TestPushMsg_S2C;
import game.entity.Role;

import java.util.Set;

import lion.core.ChannelClose_C2S;
import lion.core.IGameService;
import lion.core.Security_C2S;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 客户端服务入口
 * @author hexuhui
 *
 */
@Component
public class ClientServiceEnter implements IGameService {

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
			Set<Role> dtoSet = msg.getRoleList();
			if (dtoSet.size() > 0) {
				//				EnterGame_C2S reqMsg = new EnterGame_C2S();
				//				reqMsg.setSelectedRole(dtoSet.iterator().next().getName());
				//				channel.write(reqMsg);
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

	public void channelClose(Channel channel, ChannelClose_C2S dto) {
		// have nothing to do
	}

	public void security(Channel channel, Security_C2S securityDto) {
		// have nothing to do
	}

}
