package net.snake.consts;

import net.snake.commons.Language;

/**
 * 门派 人男（道士1） 近战 单攻 妖男(战士2) 近战 群攻 人女（乐师3） 远程 单攻 妖女（法师4） 近程 单攻
 * 
 * @author serv_dev
 * 
 */
public enum Popsinger {
	//少林派 - 古冥
	guming(1, Language.SECT_SHAOLIN, 1, Language.XIASHI),
	//全真教 - 浮屠
	futu(2, Language.SECT_QUANZHEN, 1, Language.XIASHI),
	//古墓派 - 云月
	yunyue(3, Language.SECT_GUMU, 0, Language.NVXIA),
	//桃花岛 - 缈玉
	miaoyu(4, Language.SECT_TAOHUA, 0, Language.NVXIA);
	private int pops;
	private String name;
	private int sex;
	private String appellation;

	private Popsinger(int pops, String name, int sex, String appellation) {
		this.pops = pops;
		this.name = name;
		this.sex = sex;
		this.appellation = appellation;
	}

	public String getAppellation() {
		return appellation;
	}

	public String getName() {
		return name;
	}

	public int getSex() {
		return sex;
	}

	public int getPopsinger() {
		return this.pops;
	}

	public static Popsinger getPopsinger(int id) {
		switch (id) {
		case 1:
			return guming;
		case 2:
			return futu;
		case 3:
			return yunyue;
		case 4:
			return miaoyu;
		}
		return null;
	}

	public static String getPopsingerName(int id) {
		switch (id) {
		case 1:
			return guming.getName();
		case 2:
			return futu.getName();
		case 3:
			return yunyue.getName();
		case 4:
			return miaoyu.getName();
		default:
			return "";
		}
	}
}
