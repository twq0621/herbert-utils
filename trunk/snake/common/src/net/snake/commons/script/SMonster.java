package net.snake.commons.script;


/**
 * 场景中的怪物对像
 * 
 * @author serv_dev
 * 
 */
public interface SMonster extends SVO, PropertiesSupport {
	/**
	 * 获得怪物最大血量
	 * 
	 * @return
	 */
	public int getMaxHp();

	/**
	 * 获得怪物ID
	 * 
	 * @return
	 */
	public Integer getId();

	/**
	 * 获得怪物模型ID
	 * 
	 * @return
	 */
	public int getModel();

	/**
	 * 获得逻辑格坐标X
	 * 
	 * @return
	 */
	public short getX();

	/**
	 * 获得逻辑格坐标Y
	 * 
	 * @return
	 */
	public short getY();

	/**
	 * 获得怪物当前血量
	 * 
	 * @return
	 */
	public int getNowHp();

	/**
	 * 获得怪物初始化x点
	 * 
	 * @return
	 */
	public short getOriginalX();

	/**
	 * 获得怪物初始化y点
	 * 
	 * @return
	 */
	public short getOriginalY();

	/**
	 * 获得怪物当前场景
	 * 
	 * @return
	 */
	public SScene getSceneRef();

	/**
	 * 获得怪物仇恨列表
	 * 
	 * @return
	 */
	public SEnmityManager getEnmityManager();

	/**
	 * 获得当前血量百分比
	 * 
	 * @return
	 */
	public float getHpPercent();

	/**
	 * 是不是boss
	 * 
	 * @return
	 */
	public boolean isBoss();

	/**
	 * 是不是普通怪
	 * 
	 * @return
	 */
	public boolean isPT();

	/**
	 * 怪物是否死亡
	 * 
	 * @return
	 */
	public boolean isDie();

	/**
	 * 获得当前怪物连斩副本中的级别
	 * 
	 * @return
	 */
	public int getInstanceLianzhanGrade();

	/**
	 * 获得怪物的经验
	 * 
	 * @return
	 */
	public int getExp();

	public short getGrade();

	public SPropertyAdditionController getPropertyAdditionController();

	public int getType();

	/**
	 * 改变怪物攻击
	 * 
	 * @param value
	 * @return
	 */
	public void changeAttack(int value);

	/**
	 * 改变怪物 防御
	 * 
	 * @param value
	 * @return
	 */
	public void changeDefence(int value);

	/**
	 * 改变怪物 闪避
	 * 
	 * @param value
	 * @return
	 */
	public void changeDodge(int value);

	/****
	 * 改变怪物 暴击
	 * 
	 * @param value
	 * @return
	 */
	public void changeCrt(int value);

	/**
	 * 改变 移动速度
	 * 
	 * @param value
	 * @return
	 */
	public void changeMoveSpeed(int value);

	/**
	 * 改变 攻击速度
	 * 
	 * @param value
	 * @return
	 */
	public void changeAttackSpeed(int value);

	/**
	 * 获取怪物的技能等级 如果skillid 为0 则获取第一个 技能的等级
	 * 
	 * @return
	 */
	public int getMonsterSkillGrade(int skillid);

	/**
	 * 获得怪物的名字
	 * 
	 * @return
	 */
	public String geReplacetName();

	/**
	 * 改变攻击模式
	 * 
	 * @param is
	 */
	public void changeAttackModel(short is);

	/**
	 * 改变等级
	 * @param grade
	 */
	public void changeGrade(short grade);

	/**
	 * 改变血量
	 * @param hp
	 */
	public void changeHp(int hp);
	
	/**
	 *  是否经验对于任何等级的玩家均不衰减。
	 * @return (1生效0不生效)
	 */
	public int getExpUnlimit();
	
	/**
	 * 清除怪物,先设置不复活,再设置成消失状态.
	 */
	public void destoryDispose();
	
	public void nextTarget(SMonster monster);
	
	public void nextTarget(SRole hero);
	
	public short[] removeArroundWithMeInFightMonsterPositions(SMonster monster) ;
}
