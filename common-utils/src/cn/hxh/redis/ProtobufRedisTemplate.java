package cn.hxh.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class ProtobufRedisTemplate extends RedisTemplate<String, Object> {

	public ProtobufRedisTemplate() {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Object> protobufSerializer = new ProtobufRedisSerializer();
		setKeySerializer(stringSerializer);
		setValueSerializer(protobufSerializer);
		setHashKeySerializer(stringSerializer);
		setHashValueSerializer(protobufSerializer);
	}
}
