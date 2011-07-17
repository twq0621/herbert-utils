package cn.hxh.string;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UuidTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		uuidStr = uuidStr.replaceAll("-", "");
		System.out.println(uuidStr);
		System.out.println(uuidStr.length());
		System.out.println(System.currentTimeMillis());
		Date a = new Date(1308055999*1000);
		System.out.println(a);
		Calendar c = Calendar.getInstance();
		c.setTime(a);
		System.out.println(c);
	}

}
