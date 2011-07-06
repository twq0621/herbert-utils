package cn.hxh.time;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UuidTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		System.out.println(System.currentTimeMillis());
		Date a = new Date(1308055999*1000);
		System.out.println(a);
		Calendar c = Calendar.getInstance();
		c.setTime(a);
		System.out.println(c);
	}

}
