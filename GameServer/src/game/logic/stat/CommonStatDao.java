package game.logic.stat;

import lion.serialize.ProtobufRedisTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

//import com.example.tutorial.AddressBookProtos.Person;
import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class CommonStatDao {

	private static Logger logger = LoggerFactory.getLogger(CommonStatDao.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ProtobufRedisTemplate protobufRedisTemplate;

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

	public void savePerson() {
		logger.info("protobuf save person!");
		ValueOperations<String, Object> vo = protobufRedisTemplate
				.opsForValue();
//		Person john = Person
//				.newBuilder()
//				.setId(1234)
//				.setName("John Doe")
//				.setEmail("jdoe@example.com")
//				.addPhone(
//						Person.PhoneNumber.newBuilder().setNumber("555-4321")
//								.setType(Person.PhoneType.HOME)).build();
//		vo.set("john", john);
	}

	public void getPerson() throws InvalidProtocolBufferException {
		ValueOperations<String, Object> vo = protobufRedisTemplate
				.opsForValue();
		Object ret = vo.get("john");
//		Person p = Person.parseFrom((byte[]) ret);
//		logger.info("p={}", p);
		ValueOperations<String, String> voString = stringRedisTemplate
				.opsForValue();
		voString.increment("myAge", 10);
		long retAge = voString.increment("myAge", 18);
		logger.info("retAge={}", retAge);
	}

}
