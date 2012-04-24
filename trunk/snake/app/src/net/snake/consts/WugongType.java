package net.snake.consts;
/**
 * 1门派绝学2江湖绝学3其他操作4.帮派技能5.夫妻技能6.玉佩技能7.弓箭技能8暗器技能9丹田技能
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public enum WugongType {
	
	NULL(0),
	MENG_PAI(1),
	JIANG_HU_JUE_XUE(2),
	OTHER(3),
	BANG_PAI(4),
	FU_QI(5),
	YU_PEI(6),
	GONG(7),
	Anqi(8),
	Dantian(9);
	
	private int num;
	private WugongType(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}

	public static WugongType getSkillType(int num) {
		switch (num) {
		case 1:
			return MENG_PAI;
		case 2:
			return JIANG_HU_JUE_XUE;
		case 3:
			return OTHER;
		case 4:
			return BANG_PAI;
		case 5:
			return FU_QI;
		case 6:
			return YU_PEI;
		case 7:
			return GONG;
		case 8:
			return Anqi;
		case 9:
			return Dantian;
		default:
			return NULL;
		}
	}
}
