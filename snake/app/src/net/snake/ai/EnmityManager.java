package net.snake.ai;

import java.util.Collection;

import net.snake.commons.program.Updatable;
import net.snake.commons.script.SEnmityManager;
import net.snake.gamemodel.hero.bean.Enmity;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;


/**
 * 仇恨管理器
 * 
 * @author serv_dev
 * 
 */

public interface EnmityManager extends SEnmityManager, Updatable {
	public int getAllObjInHeap() throws Exception;
	public void destory();
	public void setTargetIgnoreEnmity(VisibleObject vo, int duration) ;
	/**
	 * 添加到我的仇恨列表 ,也可用于更新仇恨 目前只有攻击动作产生时才会产生仇恨值
	 * 
	 * void addToMyEnmityList(VisibleObject vo, int enmity);
	 */
	/**
	 * 添加到我的仇恨列表 ,也可用于更新仇恨
	 * 
	 * void addToMyEnmityList(VisibleObject vo, int enmity, int hurtvalue);
	 */
	/**
	 * 增加对血量的伤害值
	 * 
	 * @param vo
	 * @param hurtvalue
	 */
	void addHurtValue(VisibleObject vo, int hurtvalue);

	/**
	 * 增加对我的仇恨值
	 * 
	 * @param vo
	 * @param enmityvalue
	 *            *
	 */
	void addEnmityValue(VisibleObject vo, int enmityvalue);

	/**
	 * 从我的仇恨列表移除
	 * 
	 * @param vo
	 */
	void removeFromMyEnmityList(VisibleObject vo);

	/**
	 * 检查vo是否在我的仇恨列表中,没有的话返回空
	 */
	Enmity getEnmityOfVo(VisibleObject vo);

	/**
	 * 主动清除我的仇恨列表
	 */
	void clearEnmityList();

	/**
	 * 清除所有人对我的仇恨
	 */
	void clearWhosEnmityisMe();

	Collection<Enmity> getEnmityList();

	int getEnmityListSize();

	// 根据仇恨值排序,获得仇恨值位于第N位的玩家
	Enmity getEnmityBySortPosition(int position);

	public Enmity getHatesetTarget();

	public boolean isContainRole();

	public boolean isContainMaleRole();

	public boolean isContainFemaleRole();

	public boolean isContainPopsinger(int t);

	public Hero getAtkMaxRole();

	public Hero getHpMaxRole();

	public Hero getDefMaxRole();
}
