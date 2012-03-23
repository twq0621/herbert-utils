package cn.hxh.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import cn.hxh.stat.StatConstants;

@Component
public class CommonStatDao {

	private static Logger logger = LoggerFactory.getLogger(CommonStatDao.class);

	// inject the actual template
	// @Autowired
	// private RedisTemplate<String, String> template;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public Long getDailyNewRole(String queryDate) {
		SetOperations<String, String> opsForSet = stringRedisTemplate
				.opsForSet();
		String queryKey = String
				.format(StatConstants.DAILY_NEW_ROLE, queryDate);
		return opsForSet.size(queryKey);
	}

	public void addLink(String roleName, String url) {
		logger.info("save to redis! a={},b={}", roleName, url);
		ValueOperations<String, String> opForVal = stringRedisTemplate
				.opsForValue();
		opForVal.set(roleName, url);
	}

	public void getMembers() {
		logger.info("get members!");
		stringRedisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long size = connection.dbSize();
				logger.info("size={}", size);
				return size;
			}
		});
		ValueOperations<String, String> valOp = stringRedisTemplate
				.opsForValue();
		String monsterKey = valOp.get("key_monster");
		logger.info("monster key:{}", monsterKey);
	}

}
