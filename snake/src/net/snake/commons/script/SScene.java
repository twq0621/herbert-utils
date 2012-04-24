package net.snake.commons.script;

import java.util.Collection;
import java.util.Set;



/**
 * 场景对像
 * @author serv_dev
 *
 */
public interface SScene extends PropertiesSupport {
	/**
	 * 获得场景ID
	 * @return
	 */
	public int getId();
	/**
	 * 获得场景名子
	 * @return
	 */
	public String getName();
	/**
	 * 获得场景所属的副本,返回NULL表示该场景不在副本控制器
	 * @return
	 */

	public SInstance getInstance();
	/**
	 * 获得场景中当前的角色数
	 * @return
	 */
	public int getCharacterCount();
	/**
	 * 获得场景中当前的怪物数
	 * @return
	 */
	public int getMonsterCount();
	/**
	 * 获取当前场景的布怪点
	 * @return
	 */
	public Collection<SMonster> getSMonsterCollection();
	/**
	 * 获取场景中所有的当前没有死亡的怪物
	 * @return
	 */
	public Collection<SMonster> getAllCurrentSceneMonster();
	/**
	 * 
	 * @return
	 */
	public void clearRefreshMonsterController();
	public boolean isHaveRefreshMonster();
	
	/**
	 * 将地图的中央部分切割出来，放进15个大小的数组里。
	 * 以方便取得随机地图点
	 * @return 15*2 15个逻辑坐标点
	 */
	public int[][] getCenterRandomPoints();
	/**
	 * 获取地图线路
	 * @return
	 */
	public int getLineId();
	
	/**
	 * 返回所有传送点
	 * @return
	 */
	public Set<STeleport> getAllTeleports();
}
