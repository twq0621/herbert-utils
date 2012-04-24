package net.snake.commons.script;

import java.util.Collection;

/**
 * 副本
 * 
 * @author serv_dev
 * 
 */
public interface SInstance extends PropertiesSupport {
	/**
	 * 获取副本id
	 * 
	 * @return
	 */
	public int getInstanceId();

	/**
	 * 获取副本中中玩家所在场景
	 * 
	 * @return
	 */
	public Collection<SScene> getSceneCollection();

	/**
	 * 获取当前副本中所有玩家 包括副本中切换场景状态的玩家
	 * 
	 * @return
	 */
	public Collection<SRole> getInstanceAllCharacters();

	/**
	 * 副本怪物善刷新次数
	 * 
	 * @return
	 */
	public int getMonsterflushCount();

	/**
	 * 副本boss怪物善刷新次数
	 * 
	 * @return
	 */
	public int getBossflushCount();

	/**
	 * 获取副本要刷新的怪物模型id
	 * 
	 * @param type
	 * @param grade
	 * @return
	 */
	public int getInstanceMonsterId(int type, int grade);

	/**
	 * 获取副本要刷新的怪物模型id,并且加入类别
	 * 
	 * @param type
	 * @param grade
	 * @param category
	 *            0为默认,之后递增
	 * @return
	 */
	public int getInstanceMonsterId(int type, int grade, int category);

	public void flushMonster(SScene sscene, int dropAdd, int propAdd);

	public void missionComplete();

	public void missionFailed();
}
