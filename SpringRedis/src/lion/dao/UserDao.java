package lion.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import lion.common.BaseDaoImpl;
import lion.common.RedisFieldConstants;
import lion.common.RedisKeyConstants;
import lion.dto.CreateRole_C2S;
import lion.dto.RoleDTO;
import lion.entity.RoleInfoProtos.RoleInfo;
import lion.entity.User;
import lion.entity.UserRolesProtos.UserRoles;

import com.google.protobuf.InvalidProtocolBufferException;

@Repository
public class UserDao extends BaseDaoImpl {

	public boolean checkUserExist(String userName) {
		logger.info("check user exist,uName={}", userName);
		boolean ret = false;
//		String uNameKey = getUserNameKey(userName);
//		ret = getStringRedisTemplate().hasKey(uNameKey);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name",userName);
		User u = (User)getSqlQuerySessionTemplate().selectOne("user.getUser", param);
		if(u != null){
		    ret = true;
		}
		return ret;
	}

	public void createUser(String name, String pwd) {
		logger.info("createUser,name={},pwd={}", name, pwd);
//		HashOperations<String, Object, Object> hashOperations = getStringRedisTemplate().opsForHash();
//		String uNameKey = getUserNameKey(name);
//		hashOperations.put(uNameKey, RedisFieldConstants.PWD, pwd);
//		hashOperations.put(uNameKey, RedisFieldConstants.ADD_TIME, String.valueOf(new Date().getTime()));
		User user = new User();
		user.setName(name);
		user.setSource(pwd);
		Object o = getSqlSession().insert("user.insertUser", user);
		System.out.println("======="+o);
	}

	public boolean checkPwdValid(String userName, String pwd) {
		boolean ret = false;
		if (!checkUserExist(userName)) {
			ret = false;
		}
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hashOperations = getStringRedisTemplate().opsForHash();
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
		SetOperations<String, String> setOp = getStringRedisTemplate().opsForSet();
		ret = setOp.isMember(RedisKeyConstants.ROLE_NAMES, roleName);
		return ret;
	}

	public void createRole(String userName, CreateRole_C2S msg) throws InvalidProtocolBufferException {
		// 保存角色名
		SetOperations<String, String> setOp = getStringRedisTemplate().opsForSet();
		setOp.add(RedisKeyConstants.ROLE_NAMES, msg.getRoleName());
		// 保存玩家的角色
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hoProto = getProtobufRedisTemplate().opsForHash();
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

	public Set<RoleDTO> getRoles(String userName) throws InvalidProtocolBufferException {
		Set<RoleDTO> retList = new HashSet<RoleDTO>();
		HashOperations<String, Object, Object> hashProto = getProtobufRedisTemplate().opsForHash();
		String uNameKey = getUserNameKey(userName);
		Object rolesList = hashProto.get(uNameKey, RedisFieldConstants.ROLES);
		if (rolesList != null) {
			UserRoles userRoles = UserRoles.parseFrom((byte[]) rolesList);
			List<RoleInfo> roleInfoList = userRoles.getRoleInfoList();
			for (RoleInfo roleInfo : roleInfoList) {
				RoleDTO dto = new RoleDTO();
				dto.setCharacterId(roleInfo.getCharacterId());
				dto.setGender(roleInfo.getGender());
				dto.setName(roleInfo.getName());
				retList.add(dto);
			}
		}
		return retList;
	}

}
