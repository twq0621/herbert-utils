package net.snake.gamemodel.instance.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class InstanceDayStatKey  implements IbatisEntity{

	/**
	 * 角色id t_instance_daystat.characterId
	 * 
	 */
	private Integer characterid;
	/**
	 * 副本模型id t_instance_daystat.instanceModelId
	 * 
	 */
	private Integer instancemodelid;
	/**
	 * 日期（仅年月日) t_instance_daystat.statdate
	 * 
	 */
	private Date statdate;

	/**
	 * 角色id t_instance_daystat.characterId
	 * @return  the value of t_instance_daystat.characterId
	 * 
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * 角色id t_instance_daystat.characterId
	 * @param characterid  the value for t_instance_daystat.characterId
	 * 
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * 副本模型id t_instance_daystat.instanceModelId
	 * @return  the value of t_instance_daystat.instanceModelId
	 * 
	 */
	public Integer getInstancemodelid() {
		return instancemodelid;
	}

	/**
	 * 副本模型id t_instance_daystat.instanceModelId
	 * @param instancemodelid  the value for t_instance_daystat.instanceModelId
	 * 
	 */
	public void setInstancemodelid(Integer instancemodelid) {
		this.instancemodelid = instancemodelid;
	}

	/**
	 * 日期（仅年月日) t_instance_daystat.statdate
	 * @return  the value of t_instance_daystat.statdate
	 * 
	 */
	public Date getStatdate() {
		return statdate;
	}

	/**
	 * 日期（仅年月日) t_instance_daystat.statdate
	 * @param statdate  the value for t_instance_daystat.statdate
	 * 
	 */
	public void setStatdate(Date statdate) {
		this.statdate = statdate;
	}
}
