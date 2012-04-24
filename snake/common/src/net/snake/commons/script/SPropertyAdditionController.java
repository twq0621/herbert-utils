package net.snake.commons.script;

public interface SPropertyAdditionController {
	/**
	 * 
	 * 移动速度计算公式=((人物初始移动速度+人物升级成长带来的移动速度+人物冲穴带来的移动速度+装备带来的移动速度
	 * +BUFF类药品带来的移动速度+技能BUFF带来的移动速度
	 * +人物坐骑带来的移动速度)*(1+阵法带来的攻击速度百分比+宝石带来的攻击速度百分比)) / 1000
	 * 
	 * @return
	 */
	public int getExtraMoveSpeed();

	/**
	 * 攻击速度计算公式=((人物初始攻击速度+人物升级成长带来的攻击速度+人物冲穴带来的攻击速度+装备带来的攻击速度
	 * +BUFF类药品带来的攻击速度+技能BUFF带来的攻击速度+人物坐骑带来的攻击速度)
	 * *(1+阵法带来的攻击速度百分比+宝石带来的攻击速度百分比)) / 1000
	 * 
	 * @return
	 */
	public int getExtraAttackSpeed();

	public int getExtraMaxHp();

	public int getExtraMaxMp();

	public int getExtraMaxSp();

	public int getExtraAttack();

	public int getExtraDefend();

	public int getExtraCrt();

	public int getExtraDodge();

	public int getExtraHit();

	public int getExtraGjl();

	public int getExtraFtsh();

	public int getExtraHsfy();

	public int getExtraShjm();

	/**
	 * 战斗带来的攻击力
	 * 
	 * @return
	 */
	public float getFightAttack();

	/**
	 * 战斗带来的防御力
	 * 
	 * @return
	 */
	public float getFightDefence();
	// public SPropertyEntity getLianTi();
	// public SPropertyEntity getAddPoint();
	//
	// public SPropertyEntity getJingmai();
	//
	// public SPropertyEntity getZhuangbei();
	//
	// public SPropertyEntity getXinfa();
	//
	// public SPropertyEntity getChenghao();
	//
	// public SPropertyEntity getZhengfa();
	//
	// public SPropertyEntity getZuoqi();
	// public SPropertyEntity getBuff();
	//
	// public SPropertyEntity getZuoqiJineng();
	// public SPropertyEntity getTaoZhuang() ;
	//
	// public SPropertyEntity getChuZhanZuoqiJineng();
	// public SPropertyEntity getSpecialDrug();
	// public SPropertyEntity getBangpai();
	// public SPropertyEntity getYoulong();
	// public SPropertyEntity getShouhuyoulong();
	// public SPropertyEntity getAnqi();
	// public SPropertyEntity getBow();
	// public SPropertyEntity getDantian();

}
