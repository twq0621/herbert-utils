package cn.hxh.core;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.google.protobuf.MessageLite;

public class ProtobufRedisSerializer<T> implements RedisSerializer<T> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Override
	public byte[] serialize(T t) throws SerializationException {
		return ((MessageLite) t).toByteArray();
	}

	/**
	 * 由于protobuf的本身机制原因，反序列化操作在程序中执行
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		return (T) bytes;
	}

}
