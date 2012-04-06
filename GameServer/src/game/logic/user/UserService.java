package game.logic.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import lion.core.UserInfo;
import game.common.ErrorCode;
import game.dto.CreateRole_C2S;
import game.dto.CreateRole_S2C;
import game.dto.EnterGame_C2S;
import game.dto.EnterGame_S2C;
import game.dto.Login_C2S;
import game.dto.Login_S2C;
import game.entity.Role;
import game.entity.User;
import game.logic.role.RoleDao;

/**
 *  用户信息
 * @Title  webGame	
 * @Description UserService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class UserService extends BaseServiceImpl {

	private Map<Integer, UserInfo> usersMap = new ConcurrentHashMap<Integer, UserInfo>();
	
	private UserDao userDao;
	
	private RoleDao roleDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	/**
	  * @Title: login
	  * @Description: 用户登录
	  * @param @param channel
	  * @param @param msg    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void login(Channel channel, Login_C2S msg){
		logger.info("user login!!");
		//返回给客户端的封装对象
		Login_S2C retMsg = new Login_S2C();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", msg.getName());
		User u = (User)userDao.get(param);
		if(u != null && u.getId() != null){//用户名已经存在
			//判断用户密码是否相同
			if(msg.getPwd().equals(u.getPassword())){
				param = new HashMap<String, Object>();
				param.put("userId", u.getId());
				List<Role> list = roleDao.list(param);
				Set<Role> dtoSet = new HashSet<Role>(list);
				retMsg.setRoleList(dtoSet);
			}else{//用户密码错误
				retMsg.setCode(ErrorCode.PWD_INVALID);
				channel.write(retMsg);
				return;
			}
		}else{//用户名不存在，创建用户和角色
			User user = new User();
			user.setUserName(msg.getName());
			user.setPassword(msg.getPwd());
			User retUser = userDao.insert(user);
			
			Role role = new Role();
			role.setUserId(retUser.getId());
			role.setRoleName(retUser.getUserName());
			Role retRole = roleDao.insert(role);
			
			Set<Role> dtoSet = new HashSet<Role>();
			dtoSet.add(retRole);
			retMsg.setRoleList(dtoSet);
		}
		retMsg.setCode(ErrorCode.SUCCESS);
		//初始化在线用户userInfo,存放在map中
		UserInfo uInfo = new UserInfo(channel, msg.getName());
		usersMap.put(channel.getId(), uInfo);
		
		channel.write(retMsg);
		
	}
	
	/**
	  * @Title: createRole
	  * @Description: 创建用户角色
	  * @param @param userInfo
	  * @param @param msg    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void createRole(UserInfo userInfo, CreateRole_C2S msg){
		logger.info("create role!");
		CreateRole_S2C retMsg = new CreateRole_S2C();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleName", msg.getRoleName());
		Role role = roleDao.get(param);
		if(role == null ){
			Role r = new Role();
			r.setUserId(msg.getUserId());
			r.setRoleName(msg.getRoleName());
			r.setSex(msg.getGender());
			r.setCareerId(msg.getCharacterId());
			roleDao.insert(r);
			userInfo.send(retMsg);
		}else{
			retMsg.setCode(ErrorCode.ROLE_NAME_ALREADY_EXIST);
			userInfo.send(retMsg);
			return;
		}
		
	}
	
	/**
	  * @Title: enterGame
	  * @Description: 角色登录游戏
	  * @param @param userInfo
	  * @param @param reqMsg    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void enterGame(UserInfo userInfo, EnterGame_C2S reqMsg){
		logger.info("enterGame");
		EnterGame_S2C retMsg = new EnterGame_S2C();
		String selectedRoleName = reqMsg.getSelectedRole();
		//TODO 角色登录游戏相关业务判断
		
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
