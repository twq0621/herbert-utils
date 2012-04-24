package net.snake.commons;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 国际化
 * 
 * @author serv_dev
 * 
 */
public class Language {
	private static Logger logger = Logger.getLogger(Language.class);

	private static Map<Integer, String> map = new HashMap<Integer, String>();
	public static String AND;
	public static String OF;
	public static String WHITE_EQUIP;
	public static String BLUE_EQUIP;
	public static String GREEN_EQUIP;
	public static String PUTPLE_EQUIP;
	public static String SECT_GUMU;
	public static String SECT_TAOHUA;
	public static String SECT_QUANZHEN;
	public static String SECT_SHAOLIN;
	public static String XIASHI;
	public static String NVXIA;
	public static String GOODS;
	public static String ONE;
	public static String GEM;
	public static String LUCKY_CRYSTAL;
	public static String ZHENQI;
	public static String STUFF;
	public static String MAIN_STUFF;
	public static String AID_STUFF;
	public static String NEW_STUFF;
	public static String EXP;
	public static String COPPER;
	public static String GOLD;
	public static String LIJIN;
	public static String WAR_PRESTIGE;
	public static String HID_WEAPON;
	public static String FACTION_BANGZHU;
	public static String FACTION_FUBANGZHU;
	public static String FACTION_DAZHANGLAO;
	public static String FACTION_DASHIXIONG;
	public static String FACTION_DASHIJIE;
	public static String FACTION_BANGZHONG;
	public static String FU_ZHENXING;
	public static String FU_ZHENHUN;
	public static String GONGJI;
	public static String FONGYU;
	public static String MAX_HP;
	public static String MAX_MP;
	public static String MAX_TILI;
	public static String MAX_GONGJI;
	public static String MAX_SHANBI;
	public static String GONGJISUDU;
	public static String YIDONGSUDU;
	public static String CHENGHAO;
	public static String TANWEI;
	public static String WUXU;
	public static String WEIZHI;
	public static String WEISIWANG;
	public static String YUZAO;
	public static String ZANWUMIAOSHU;
	public static String YEAR;
	public static String MONTH;
	public static String DAY;
	public static String HOUR;
	public static String MINUTE;
	public static String SECOND;
	public static String PUTONG;
	public static String YOUXIOU;
	public static String XIYOU;
	public static String SHENJI;
	public static String TEAM;
	public static String BODY;
	public static String BAG;
	public static String STORE;
	public static String STALL;
	public static String HORSE;
	public static String NOWAY;
	public static String ZAI;
	public static String NOFILED;
	public static String NOPLAYER;
	public static String ATTACK; // 攻击
	public static String DEFENCE; // 防御
	public static String CRT; // 暴击
	public static String DODGE; // 闪避
	public static String MAXHP; // 生命值上限
	public static String MAXSP;// 体力值上限
	public static String MAXMP;// 内力上限
	public static String ATTACKSPEED;// 攻击速度
	public static String MOVESPEED;// 移动速度

	// 1攻击力增强2反弹伤害3忽视防御4伤害减免5暗器免伤
	public static String GJL;// 攻击力增强
	public static String FTSH;// 反弹伤害
	public static String HSFY;// 忽视防御
	public static String SHJM;// 伤害减免
	public static String AQMS;// 暗器免伤
	public static String AQJV;// 暗器几率

	public static String RESETTIANSHEN1;// ,且意外的获得了天生属性+
	public static String RESETTIANSHEN2;// %的惊喜

	public static String QU;// 移动速度
	public static String BORN_ADD;// 天生重置增加提示语
	public static String BORN_REDUCE;// 天生重置减少提示语
	public static String BORN_UNCHANGING;// 天生重置不变提示语
	/**
	 * 系统赠送
	 */
	public static String SYSTEMGIFT;

	/**
	 * @description 初始化静态字段
	 * @param pMap
	 *            静态资源集合
	 */
	public static void init(Map<Integer, String> pMap) {
		if (pMap == null || pMap.isEmpty()) {
			logger.error("init languageServer error ： pMap is null!");
			return;
		}
		map = pMap;
		setVar();// 赋值

	}

	private static void setVar() {

		WHITE_EQUIP = map.get(1);
		BLUE_EQUIP = map.get(2);
		GREEN_EQUIP = map.get(3);
		PUTPLE_EQUIP = map.get(4);
		SECT_GUMU = map.get(5);
		SECT_TAOHUA = map.get(6);
		SECT_QUANZHEN = map.get(7);
		SECT_SHAOLIN = map.get(8);
		XIASHI = map.get(9);
		NVXIA = map.get(10);
		GOODS = map.get(11);
		ONE = map.get(12);
		GEM = map.get(13);
		LUCKY_CRYSTAL = map.get(14);
		ZHENQI = map.get(15);
		STUFF = map.get(16);
		MAIN_STUFF = map.get(17);
		AID_STUFF = map.get(18);
		NEW_STUFF = map.get(19);
		EXP = map.get(20);
		COPPER = map.get(21);
		GOLD = map.get(22);
		LIJIN = map.get(23);
		WAR_PRESTIGE = map.get(24);
		HID_WEAPON = map.get(25);
		FACTION_BANGZHU = map.get(26);
		FACTION_FUBANGZHU = map.get(27);
		FACTION_DAZHANGLAO = map.get(28);
		FACTION_DASHIXIONG = map.get(29);
		FACTION_DASHIJIE = map.get(30);
		FACTION_BANGZHONG = map.get(31);
		FU_ZHENXING = map.get(32);
		FU_ZHENHUN = map.get(33);
		GONGJI = map.get(34);
		FONGYU = map.get(35);
		MAX_HP = map.get(36);
		MAX_MP = map.get(37);
		MAX_TILI = map.get(38);
		MAX_GONGJI = map.get(39);
		MAX_SHANBI = map.get(40);
		GONGJISUDU = map.get(41);
		YIDONGSUDU = map.get(42);
		CHENGHAO = map.get(43);
		TANWEI = map.get(44);
		WUXU = map.get(45);
		WEIZHI = map.get(46);
		WEISIWANG = map.get(47);
		YUZAO = map.get(48);
		ZANWUMIAOSHU = map.get(49);
		YEAR = map.get(50);
		MONTH = map.get(51);
		DAY = map.get(52);
		HOUR = map.get(53);
		MINUTE = map.get(54);
		SECOND = map.get(55);
		PUTONG = map.get(56);
		YOUXIOU = map.get(57);
		XIYOU = map.get(58);
		SHENJI = map.get(59);
		TEAM = map.get(60);
		BODY = map.get(61);
		BAG = map.get(62);
		STORE = map.get(63);
		STALL = map.get(64);
		HORSE = map.get(65);
		NOWAY = map.get(66);
		ZAI = map.get(67);
		NOFILED = map.get(68);
		NOPLAYER = map.get(69);
		ATTACK = map.get(70);
		DEFENCE = map.get(71);
		CRT = map.get(72);
		DODGE = map.get(73);
		MAXHP = map.get(74);
		MAXSP = map.get(75);
		MAXMP = map.get(76);
		ATTACKSPEED = map.get(77);
		MOVESPEED = map.get(78);

		GJL = map.get(79);
		FTSH = map.get(80);
		HSFY = map.get(81);
		SHJM = map.get(82);
		AQMS = map.get(83);

		RESETTIANSHEN1 = map.get(84);
		RESETTIANSHEN2 = map.get(85);
		AND = map.get(86);
		OF = map.get(87);
		SYSTEMGIFT = map.get(88);
		AQJV = map.get(89);
		QU = map.get(90);
		BORN_ADD = map.get(93);
		BORN_REDUCE = map.get(94);
		BORN_UNCHANGING = map.get(95);
	}

	public static String getValue(int key) {
		String value = map.get(key);
		if (value == null) {
			return "";
		}
		return value;
	}

}
