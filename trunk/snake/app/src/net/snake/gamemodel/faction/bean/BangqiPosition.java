package net.snake.gamemodel.faction.bean;

import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.ibatis.IbatisEntity;

public class BangqiPosition implements IbatisEntity{
    /**
	 * t_bangqi_position.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 地图id t_bangqi_position.f_scene_id
	 * 
	 */
	private Integer sceneId;
	/**
	 * 地图名字 t_bangqi_position.f_scene_name
	 * 
	 */
	private String sceneName;
	/**
	 * 地图安插点x t_bangqi_position.f_x
	 * 
	 */
	private Short x;
	/**
	 * 地图安插y坐标 t_bangqi_position.f_y
	 * 
	 */
	private Short y;
	/**
	 * 帮旗地图名称国际化 t_bangqi_position.f_scene_name_i18n
	 * 
	 */
	private String sceneNameI18n;

	/**
	 * t_bangqi_position.f_id
	 * @return  the value of t_bangqi_position.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_bangqi_position.f_id
	 * @param id  the value for t_bangqi_position.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 地图id t_bangqi_position.f_scene_id
	 * @return  the value of t_bangqi_position.f_scene_id
	 * 
	 */
	public Integer getSceneId() {
		return sceneId;
	}

	/**
	 * 地图id t_bangqi_position.f_scene_id
	 * @param sceneId  the value for t_bangqi_position.f_scene_id
	 * 
	 */
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	/**
	 * 地图名字 t_bangqi_position.f_scene_name
	 * @return  the value of t_bangqi_position.f_scene_name
	 * 
	 */
	public String getSceneName() {
		return sceneName;
	}

	/**
	 * 地图名字 t_bangqi_position.f_scene_name
	 * @param sceneName  the value for t_bangqi_position.f_scene_name
	 * 
	 */
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	/**
	 * 地图安插点x t_bangqi_position.f_x
	 * @return  the value of t_bangqi_position.f_x
	 * 
	 */
	public Short getX() {
		return x;
	}

	/**
	 * 地图安插点x t_bangqi_position.f_x
	 * @param x  the value for t_bangqi_position.f_x
	 * 
	 */
	public void setX(Short x) {
		this.x = x;
	}

	/**
	 * 地图安插y坐标 t_bangqi_position.f_y
	 * @return  the value of t_bangqi_position.f_y
	 * 
	 */
	public Short getY() {
		return y;
	}

	/**
	 * 地图安插y坐标 t_bangqi_position.f_y
	 * @param y  the value for t_bangqi_position.f_y
	 * 
	 */
	public void setY(Short y) {
		this.y = y;
	}

	/**
	 * 帮旗地图名称国际化 t_bangqi_position.f_scene_name_i18n
	 * @return  the value of t_bangqi_position.f_scene_name_i18n
	 * 
	 */
	public String getSceneNameI18n() {
		return sceneNameI18n;
	}

	/**
	 * 帮旗地图名称国际化 t_bangqi_position.f_scene_name_i18n
	 * @param sceneNameI18n  the value for t_bangqi_position.f_scene_name_i18n
	 * 
	 */
	public void setSceneNameI18n(String sceneNameI18n) {
		this.sceneNameI18n = sceneNameI18n;
	}

	private long dieTime=0;
    private int killerFactionId=0;

	public long getDieTime() {
		return dieTime;
	}

	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	public int getKillerFactionId() {
		return killerFactionId;
	}

	public void setKillerFactionId(int killerFactionId) {
		this.killerFactionId = killerFactionId;
	}

	/**
	 * @param id2
	 * @return
	 */
	public boolean isEnterSceneByFactionId(Integer factionId) {
		if(killerFactionId==factionId){
			return true;
		}
		if(System.currentTimeMillis()-this.dieTime>SceneBangqiManager.enterBangqiTimeJiange){
			return true;
		}
		return false;
	}
    
}
