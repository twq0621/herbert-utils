package cn.hxh.udplog;

import org.xml.sax.Attributes;

public class StatConstants {
	
	/** 商城整体的数量*/
	public static final String MARKET_TOTAL = "stat_market_total";
	
	public static final String MARKET_ITEM_TOTAL = "stat_market_item_total";

	public static final String DAILY_NEW_USER = "dailyNewUser_%s";

	public static final String DAILY_NEW_ROLE = "dailyNewRole_%s";

	/** 玩家登陆信息统计,%s为日期 */
	public static final String LOGIN_STAT_USER_SET = "loginStat_u_%s";

	/** 玩家登陆信息统计，%s为用户id，日期 */
	public static final String LOGIN_STAT_USER_LIST = "u_login_%s_%s";

	/** 玩家登陆信息统计实体，%s为uukey */
	public static final String LOGIN_STAT_USER_HASH = "u_login_%s";

	/** 角色进入游戏信息统计,%s为日期 */
	public static final String LOGIN_STAT_ROLE_SET = "loginStat_r_%s";

	/** 角色进入游戏信息统计,%s为角色名，日期，单数为登陆时间，双数为登出时间 */
	public static final String LOGIN_STAT_ROLE_LIST = "r_login_%s_%s";

	/** 角色进入游戏信息统计实体,%s为uukey */
	public static final String LOGIN_STAT_ROLE_HASH = "r_login_%s";

	public static final String LOGIN_STAT_USER_LOGIN_ID = "u_loginId";

	public static final String DAILY_FINISH_ROOKIE = "dailyFinishRookie_%s";

	public static final String DAILY_ONLINE_NUM = "onlineNum_%s";

	/** 用户pc环境信息记录set */
	public static final String USER_PC_INFO_SET = "u_pcinfo_%s";

	/** 用户pc环境信息记录 */
	public static final String USER_PC_INFO_ENTITY = "pcinfo_%s";

	/** 用户pc环境key */
	public static final String KEY_PC_INFO = "key_pcinfo";

	public static final String dateFormat = "yyyy-MM-dd";

	public static final String timeFormat = "yyyy-MM-dd HH:mm:ss";

	public static String MAINTAIN_LOG_IP = "192.168.1.133";

	public static int MAINTAIN_LOG_PORT = 8800;

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
