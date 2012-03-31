package game.chat.dao;

import game.chat.common.RedisFieldConstants;
import game.chat.common.RedisKeyConstants;
import lion.common.BaseDaoImpl;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class UserDao extends BaseDaoImpl {

	public String getUserNameKey(String userName) {
		return String.format(RedisKeyConstants.USER_ENTITY_KEY, userName);
	}

	public boolean checkLoginValid(String userName, String sid) {
		boolean ret = false;
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hashOperations = getStringRedisTemplate().opsForHash();
		Object sidObj = hashOperations.get(uNameKey, RedisFieldConstants.SESSION_ID);
		if (sidObj != null && sidObj instanceof String) {
			String dbSid = (String) sidObj;
			if (sid.equals(dbSid)) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean checkRoleValid(String userName, String roleName) throws InvalidProtocolBufferException {
		boolean ret = false;
		String uNameKey = getUserNameKey(userName);
		HashOperations<String, Object, Object> hashOperations = getStringRedisTemplate().opsForHash();
		Object currentRoleNameObj = hashOperations.get(uNameKey, RedisFieldConstants.CURRENT_ROLE);
		if (currentRoleNameObj != null && currentRoleNameObj instanceof String) {
			String dbRoleName = (String) currentRoleNameObj;
			if (dbRoleName.equals(roleName)) {
				ret = true;
			}
		}
		return ret;
	}

}
