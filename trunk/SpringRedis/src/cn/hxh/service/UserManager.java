package cn.hxh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.common.ErrorCode;
import cn.hxh.dao.UserDao;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.CreateRole_S2C;
import cn.hxh.dto.GetOnlineNames_C2S;
import cn.hxh.dto.GetOnlineNames_S2C;
import cn.hxh.dto.Login_C2S;
import cn.hxh.dto.Login_S2C;
import cn.hxh.dto.NameDTO;
import cn.hxh.dto.RoleDto;
import cn.hxh.dto.TestPushMsg_S2C;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class UserManager {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);

	private Map<Integer, UserInfo> usersMap = new ConcurrentHashMap<Integer, UserInfo>();

	@Autowired
	private UserDao userDao;

	/**
	 * just for test
	 * @param channel
	 * @param queryMsg
	 */
	public void getOnlineNames(Channel channel, GetOnlineNames_C2S queryMsg) {
		List<String> roleNames = new ArrayList<String>();
		roleNames.add("he");
		roleNames.add("xu");
		GetOnlineNames_S2C retMsg = new GetOnlineNames_S2C();
		retMsg.setRoleNames(roleNames);
		channel.write(retMsg);
		TestPushMsg_S2C retMsg2 = new TestPushMsg_S2C();
		NameDTO dto = new NameDTO();
		dto.setAge(18);
		dto.setMoney(250.4f);
		dto.setName("huijige");
		dto.setPasswprd("pwd");
		retMsg2.setNameDTO(dto);
		channel.write(retMsg2);
	}

	/**
	 * 登陆游戏
	 * @param channel
	 * @param msg
	 */
	public void login(Channel channel, Login_C2S msg) {
		logger.info("user login!");
		Login_S2C retMsg = new Login_S2C();
		retMsg.setCode(ErrorCode.SUCCESS);
		boolean userExist = userDao.checkUserExist(msg.getName());
		if (!userExist) {
			userDao.createUser(msg.getName(), msg.getPwd());
		} else {
			boolean pwdValid = userDao.checkPwdValid(msg.getName(), msg.getPwd());
			if (!pwdValid) {
				retMsg.setCode(ErrorCode.PWD_INVALID);
				channel.write(retMsg);
				return;
			}
		}
		try {
			Set<RoleDto> dtoSet = userDao.getRoles(msg.getName());
			retMsg.setRoleList(dtoSet);
		} catch (InvalidProtocolBufferException e) {
			logger.error("", e);
		}
		//初始化userInfo
		UserInfo uInfo = new UserInfo(channel, msg.getName());
		usersMap.put(channel.getId(), uInfo);
		channel.write(retMsg);
	}

	/**
	 * 创建角色
	 * @param userInfo
	 * @param msg
	 */
	public void createRole(UserInfo userInfo, CreateRole_C2S msg) {
		logger.info("create role!");
		CreateRole_S2C retMsg = new CreateRole_S2C();
		boolean roleNameExist = userDao.checkRoleNameExist(msg.getRoleName());
		if (roleNameExist) {
			retMsg.setCode(ErrorCode.ROLE_NAME_ALREADY_EXIST);
			userInfo.send(retMsg);
			return;
		}
		userDao.createRole(userInfo.getUserName(), msg);
		userInfo.send(retMsg);
	}

	public UserInfo getUserInfo(int channelId) {
		return usersMap.get(channelId);
	}

	/**
	 * 发送给所有玩家
	 * @param obj
	 */
	public void sendAll(Object obj) {
		for (UserInfo userInfo : usersMap.values()) {
			userInfo.send(obj);
		}
	}

}
