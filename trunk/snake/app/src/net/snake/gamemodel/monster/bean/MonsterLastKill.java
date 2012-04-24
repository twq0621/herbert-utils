package net.snake.gamemodel.monster.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class MonsterLastKill extends MonsterLastKillKey implements IbatisEntity {
	/**
	 * 最后击杀者 t_monster_lastkill.f_killerid
	 * 
	 */
	private Integer killerid;

	/**
	 * 最后更新 t_monster_lastkill.f_updatetime
	 * 
	 */
	private Date updatetime;

	/**
	 * 最后击杀者 t_monster_lastkill.f_killerid
	 * 
	 * @return the value of t_monster_lastkill.f_killerid
	 * 
	 */
	public Integer getKillerid() {
		return killerid;
	}

	/**
	 * 最后击杀者 t_monster_lastkill.f_killerid
	 * 
	 * @param killerid
	 *            the value for t_monster_lastkill.f_killerid
	 * 
	 */
	public void setKillerid(Integer killerid) {
		this.killerid = killerid;
	}

	/**
	 * 最后更新 t_monster_lastkill.f_updatetime
	 * 
	 * @return the value of t_monster_lastkill.f_updatetime
	 * 
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * 最后更新 t_monster_lastkill.f_updatetime
	 * 
	 * @param updatetime
	 *            the value for t_monster_lastkill.f_updatetime
	 * 
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
