package net.snake.gamemodel.hero.logic;

import net.snake.commons.script.SPropertyAdditionController;

/**
 * 人物属性容器
 * 
 * @author serv_dev
 * 
 */
public interface PropertyAdditionController extends SPropertyAdditionController {

	/**
	 * 替换监听器
	 * 
	 * @param oldlistener
	 *            在列表里原有的监听器
	 * @param newlistener
	 *            添加新的监听器
	 */
	public void replaceListener(PropertyChangeListener oldlistener, PropertyChangeListener newlistener);

	public void destory();

	public void addChangeListener(PropertyChangeListener... listener);

	public void addChangeListener(PropertyChangeListener listener);

	public void recompute();

	public void removeChangeListener(PropertyChangeListener... listener);

	public void removeChangeListener(PropertyChangeListener listener);

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

	public PropertyEntity getTotalValue();

	// public PropertyEntity getAddPoint();
	//
	// public PropertyEntity getJingmai();
	//
	// public PropertyEntity getZhuangbei();
	//
	// public PropertyEntity getXinfa();
	//
	// public PropertyEntity getChenghao();
	//
	// public PropertyEntity getZhengfa();
	//
	// public PropertyEntity getZuoqi();
	// public PropertyEntity getBuff();
	//
	// public PropertyEntity getYoulong();
	//
	// public PropertyEntity getZuoqiJineng();
	// public PropertyEntity getTaoZhuang() ;
	// public PropertyEntity getLianTi();
	// public PropertyEntity getChuZhanZuoqiJineng();
	// public PropertyEntity getSpecialDrug();
	// public PropertyEntity getBangpai();
	//
	// public PropertyEntity getTotalValue();
	//
	// public PropertyEntity getShouhuyoulong();
	//
	// public PropertyEntity getAnqi();
	//
	// public PropertyEntity getBow();
}
