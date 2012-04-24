package net.snake.gamemodel.monster.bean;

import net.snake.ibatis.IbatisEntity;

public class MonsterLastKillKey implements IbatisEntity {
	/**
	 * 分线ID t_monster_lastkill.f_lineid
	 * 
	 */
	private Integer lineid;

	/**
	 * 物怪模型ID t_monster_lastkill.f_modelid
	 * 
	 */
	private Integer modelid;

	/**
	 * 分线ID t_monster_lastkill.f_lineid
	 * 
	 * @return the value of t_monster_lastkill.f_lineid
	 * 
	 */
	public Integer getLineid() {
		return lineid;
	}

	/**
	 * 分线ID t_monster_lastkill.f_lineid
	 * 
	 * @param lineid
	 *            the value for t_monster_lastkill.f_lineid
	 * 
	 */
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}

	/**
	 * 物怪模型ID t_monster_lastkill.f_modelid
	 * 
	 * @return the value of t_monster_lastkill.f_modelid
	 * 
	 */
	public Integer getModelid() {
		return modelid;
	}

	/**
	 * 物怪模型ID t_monster_lastkill.f_modelid
	 * 
	 * @param modelid
	 *            the value for t_monster_lastkill.f_modelid
	 * 
	 */
	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}
}
