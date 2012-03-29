package cn.hxh.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import cn.hxh.common.RedisFieldConstants;
import cn.hxh.common.RedisKeyConstants;
import cn.hxh.core.ProtobufRedisTemplate;
import cn.hxh.dto.CreateRole_C2S;
import cn.hxh.dto.RoleDto;
import cn.hxh.entity.RoleInfoProtos.RoleInfo;
import cn.hxh.entity.UserRolesProtos.UserRoles;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class UserDao {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ProtobufRedisTemplate protobufRedisTemplate;

	private static Logger logger = LoggerFactory.getLogger(UserDao.class);

	public boolean checkUserExist(String userName) {
		logger.info("check user exist,uName={}", userName);
		boolean ret = false;
		String uNameKey = getUserNameKey(userName);
		ret = stringRedisTemplate.hasKey(uNameKey);
		return ret;
	}

	public void createUser(String name, String pwd) {
		logger.info("createUser,name={},pwd={}", name, pwd);
		HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
		String uNameKey = getUserNameKey(name);
		hashOperations.put(uNameKey, RedisFieldConstants.PWD, pwd);
		hashOperations.put(uNameKey, RedisFieldConstants.ADD_TIME, String.valueOf(new Date().getTime()));
	}

	public boolean checkPwdValid(String userName, String pwd) {
		boolean ret = false;
		if (!checkUserExist(userName)) {
			ret = false;
		}
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
		Object pwdObj = hashOperations.get(uNameKey, RedisFieldConstants.PWD);
		if (pwdObj instanceof String) {
			String dbPwdStr = (String) pwdObj;
			if (dbPwdStr.equals(pwd)) {
				ret = true;
			}
		}
		return ret;
	}

	public String getUserNameKey(String userName) {
		return String.format(RedisKeyConstants.USER_ENTITY_KEY, userName);
	}

	public String getUserRolesKey(String userName) {
		return String.format(RedisKeyConstants.USER_ROLES_KEY, userName);
	}

	public String getRoleNameKey(String roleName) {
		return String.format(RedisKeyConstants.ROLE_ENTITY_KEY, roleName);
	}

	public boolean checkRoleNameExist(String roleName) {
		logger.info("check role exist,roleName={}", roleName);
		boolean ret = false;
		SetOperations<String, String> setOp = stringRedisTemplate.opsForSet();
		ret = setOp.isMember(RedisKeyConstants.ROLE_NAMES, roleName);
		return ret;
	}

	public void createRole(String userName, CreateRole_C2S msg) throws InvalidProtocolBufferException {
		// 保存角色名
		SetOperations<String, String> setOp = stringRedisTemplate.opsForSet();
		setOp.add(RedisKeyConstants.ROLE_NAMES, msg.getRoleName());
		// 保存玩家的角色
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hoProto = protobufRedisTemplate.opsForHash();
		Object rolesSet = hoProto.get(uNameKey, RedisFieldConstants.ROLES);
		UserRoles userRoles;
		if (rolesSet == null) {
			userRoles = UserRoles.newBuilder().build();
		} else {
			userRoles = UserRoles.parseFrom((byte[]) rolesSet);
		}
		RoleInfo roleInfo = RoleInfo.newBuilder().setName(msg.getRoleName()).setCharacterId(msg.getCharacterId()).setGender(msg.getGender()).build();
		userRoles = userRoles.toBuilder().addRoleInfo(roleInfo).build();
		hoProto.put(uNameKey, RedisFieldConstants.ROLES, userRoles);
	}

	public Set<RoleDto> getRoles(String userName) throws InvalidProtocolBufferException {
		Set<RoleDto> retList = new HashSet<RoleDto>();
		HashOperations<String, Object, Object> hashProto = protobufRedisTemplate.opsForHash();
		String uNameKey = getUserNameKey(userName);
		Object rolesList = hashProto.get(uNameKey, RedisFieldConstants.ROLES);
		if (rolesList != null) {
			UserRoles userRoles = UserRoles.parseFrom((byte[]) rolesList);
			List<RoleInfo> roleInfoList = userRoles.getRoleInfoList();
			for (RoleInfo roleInfo : roleInfoList) {
				RoleDto dto = new RoleDto();
				dto.setCharacterId(roleInfo.getCharacterId());
				dto.setGender(roleInfo.getGender());
				dto.setName(roleInfo.getName());
				retList.add(dto);
			}
		}
		return retList;
	}

}
