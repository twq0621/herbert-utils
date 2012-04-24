package net.snake.commons;

import java.util.UUID;

/**
 * UUID风格的 唯一ID
 * 
 * @author serv_dev
 */
public class UUIDGenerater {

	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
