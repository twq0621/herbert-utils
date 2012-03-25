package cn.hxh.core;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.google.protobuf.MessageLite;

public class ProtobufRedisSerializer implements RedisSerializer<Object> {

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		return ((MessageLite) t).toByteArray();
	}

	/**
	 * 由于protobuf的本身机制原因，反序列化操作在程序中执行
	 */
	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return bytes;
	}

}
