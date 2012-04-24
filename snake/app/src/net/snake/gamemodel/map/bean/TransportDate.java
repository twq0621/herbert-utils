package net.snake.gamemodel.map.bean;

import net.snake.ibatis.IbatisEntity;

public class TransportDate implements IbatisEntity {

	/**
	 * t_transport2.f_transport_id
	 */
	private Integer transportId;
	/**
	 * t_transport2.f_scene_id
	 */
	private Integer sceneId;
	/**
	 * t_transport2.f_x
	 */
	private Integer x;
	/**
	 * t_transport2.f_y
	 */
	private Integer y;
	/**
	 * t_transport2.f_target_scene_id
	 */
	private Integer targetSceneId;
	/**
	 * t_transport2.f_target_x
	 */
	private Integer targetX;
	/**
	 * t_transport2.f_target_y
	 */
	private Integer targetY;

	/**
	 * t_transport2.f_transport_id
	 * 
	 * @return the value of t_transport2.f_transport_id
	 */
	public Integer getTransportId() {
		return transportId;
	}

	/**
	 * t_transport2.f_transport_id
	 * 
	 * @param transportId
	 *            the value for t_transport2.f_transport_id
	 */
	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	/**
	 * t_transport2.f_scene_id
	 * 
	 * @return the value of t_transport2.f_scene_id
	 */
	public Integer getSceneId() {
		return sceneId;
	}

	/**
	 * t_transport2.f_scene_id
	 * 
	 * @param sceneId
	 *            the value for t_transport2.f_scene_id
	 */
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	/**
	 * t_transport2.f_x
	 * 
	 * @return the value of t_transport2.f_x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * t_transport2.f_x
	 * 
	 * @param x
	 *            the value for t_transport2.f_x
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * t_transport2.f_y
	 * 
	 * @return the value of t_transport2.f_y
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * t_transport2.f_y
	 * 
	 * @param y
	 *            the value for t_transport2.f_y
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	 * t_transport2.f_target_scene_id
	 * 
	 * @return the value of t_transport2.f_target_scene_id
	 */
	public Integer getTargetSceneId() {
		return targetSceneId;
	}

	/**
	 * t_transport2.f_target_scene_id
	 * 
	 * @param targetSceneId
	 *            the value for t_transport2.f_target_scene_id
	 */
	public void setTargetSceneId(Integer targetSceneId) {
		this.targetSceneId = targetSceneId;
	}

	/**
	 * t_transport2.f_target_x
	 * 
	 * @return the value of t_transport2.f_target_x
	 */
	public Integer getTargetX() {
		return targetX;
	}

	/**
	 * t_transport2.f_target_x
	 * 
	 * @param targetX
	 *            the value for t_transport2.f_target_x
	 */
	public void setTargetX(Integer targetX) {
		this.targetX = targetX;
	}

	/**
	 * t_transport2.f_target_y
	 * 
	 * @return the value of t_transport2.f_target_y
	 */
	public Integer getTargetY() {
		return targetY;
	}

	/**
	 * t_transport2.f_target_y
	 * 
	 * @param targetY
	 *            the value for t_transport2.f_target_y
	 */
	public void setTargetY(Integer targetY) {
		this.targetY = targetY;
	}
}
