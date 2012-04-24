package net.snake.gamemodel.map.logic;

import net.snake.commons.script.STeleport;

/**
 * 传送点抽象
 * 
 * @author serv_dev
 * 
 */
public interface Teleport extends STeleport{

	/**
	 * 目标地图的名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 目标地图id
	 * 
	 * @return
	 */
	public int getTargetSceneID();

	public short getTargetX();

	public short getTargetY();

	/**
	 * 
	 * @param scene
	 *            目标地图
	 * @return
	 * 
	 *         public short[] getEnterPoint();
	 */
	/**
	 * 返回true 表示是隐藏传送点
	 * 
	 * @return
	 */
	public boolean isHideTelePort();
	public short getX();
	public short getY();
}
