package net.snake.api.log;

import org.xml.sax.Attributes;

public class StatConstants {

	public static final String DAILY_NEW_USER = "dailyNewUser_%s";

	public static final String DAILY_NEW_ROLE = "dailyNewRole_%s";

	public static final String DAILY_FINISH_ROOKIE = "dailyFinishRookie_%s";

	public static final String dateFormat = "yyyy-MM-dd";

	public static String MAINTAIN_LOG_IP = "127.0.0.1";

	public static int MAINTAIN_LOG_PORT = 1122;

	public static boolean MAINTAIN_LOG_ENABLE = false;

	public static short TABLE_ID_LOGIN = 8;

	public static short TABLE_ID_PAY = 9;

	public static short TABLE_ID_PROPS = 10;

	public static short TABLE_ID_ACT = 11;

	public static short TABLE_ID_REFER = 12;

	public static short TABLE_ID_GUIDE = 31;

	public static short TABLE_ID_INVENTORY = 32;

	public static enum RMB_ITEM_OPERATIONS {
		ADD("add"), SUB("sub");
		private final String text;

		private RMB_ITEM_OPERATIONS(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	};

	public static void parseXML(Attributes attributes) {
		String qName;
		String value;
		for (int i = 0; i < attributes.getLength(); i++) {
			qName = attributes.getQName(i);
			value = attributes.getValue(i);
			if (qName.equals("enable")) {
				MAINTAIN_LOG_ENABLE = Boolean.valueOf(value);
			} else if (qName.equals("ip")) {
				MAINTAIN_LOG_IP = value;
			} else if (qName.equals("port")) {
				MAINTAIN_LOG_PORT = Integer.valueOf(value);
			}
		}
	}
}
