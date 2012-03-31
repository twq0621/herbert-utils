package lion.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.hxh.common.ErrorCode;
import cn.hxh.dao.UserDao;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.CreateRole_S2C;
import cn.hxh.dto.EnterGame_C2S;
import cn.hxh.dto.EnterGame_S2C;
import cn.hxh.dto.Login_C2S;
import cn.hxh.dto.Login_S2C;
import cn.hxh.dto.RoleDTO;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
@Transactional
public class UserManager {

	private static Logger logger = LoggerFactory.getLogger(UserManager.class);

	private Map<Integer, UserInfo> usersMap = new ConcurrentHashMap<Integer, UserInfo>();

	private UserDao userDao;
	
	@Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
	 * 登陆游戏
	 * @param channel
	 * @param msg
	 */
	@Transactional(readOnly = false)
	public void login(Channel channel, Login_C2S msg) {
		logger.info("user login!");
		Login_S2C retMsg = new Login_S2C();
		retMsg.setCode(ErrorCode.SUCCESS);
		boolean userExist = userDao.checkUserExist(msg.getName());
		if (!userExist) {
			userDao.createUser(msg.getName(), msg.getPwd());
			
			System.out.println("+++++++++++++++++++++++");
            Map map = new HashMap();
           // System.out.println("========="+Integer.parseInt((String)map.get("key")));
		} else {
			boolean pwdValid = userDao.checkPwdValid(msg.getName(), msg.getPwd());
			if (!pwdValid) {
				retMsg.setCode(ErrorCode.PWD_INVALID);
				channel.write(retMsg);
				return;
			}
		}
		try {
			Set<RoleDTO> dtoSet = userDao.getRoles(msg.getName());
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
	@Transactional(readOnly = false)
	public void createRole(UserInfo userInfo, CreateRole_C2S msg) {
		logger.info("create role!");
		CreateRole_S2C retMsg = new CreateRole_S2C();
		boolean roleNameExist = userDao.checkRoleNameExist(msg.getRoleName());
		if (roleNameExist) {
			retMsg.setCode(ErrorCode.ROLE_NAME_ALREADY_EXIST);
			userInfo.send(retMsg);
			return;
		}
		try {
			userDao.createRole(userInfo.getUserName(), msg);
			
			
		} catch (InvalidProtocolBufferException e) {
			logger.error("", e);
		}
		userInfo.send(retMsg);
	}

	/**
	 * 登陆游戏
	 * @param userInfo
	 * @param reqMsg
	 * @throws InvalidProtocolBufferException 
	 */
	public void enterGame(UserInfo userInfo, EnterGame_C2S reqMsg) throws InvalidProtocolBufferException {
		logger.info("enterGame");
		EnterGame_S2C retMsg = new EnterGame_S2C();
		String selectedRoleName = reqMsg.getSelectedRole();
		//检查选择的角色是否有效
		boolean nameValid = false;
		Set<RoleDTO> roleSet = userDao.getRoles(userInfo.getUserName());
		for (RoleDTO roleDTO : roleSet) {
			if (roleDTO.getName().equals(selectedRoleName)) {
				nameValid = true;
				break;
			}
		}
		if (!nameValid) {
			retMsg.setCode(ErrorCode.SELECT_ROLE_NAME_INVALID);
			userInfo.send(retMsg);
			return;
		}
		//发送成功的消息
		userInfo.setCurrentRoleName(selectedRoleName);
		retMsg.setCode(ErrorCode.SUCCESS);
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
