package net.snake.gamemodel.monster.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class MonsterFriendData implements IbatisEntity {

	/**
	 * t_monster_friend.f_id
	 */
	private Integer id;
	/**
	 * t_monster_friend.f_character_id
	 */
	private Integer characterId;
	/**
	 * t_monster_friend.f_monster_id
	 */
	private Integer monsterId;
	/**
	 * t_monster_friend.f_favar
	 */
	private Integer favar;
	/**
	 * t_monster_friend.f_add_date
	 */
	private Date addDate;
	/**
	 * t_monster_friend.f_state
	 */
	private Integer state;
	/**
	 * t_monster_friend.f_now_hp
	 */
	private Integer nowHp;

	/**
	 * t_monster_friend.f_id
	 * 
	 * @return the value of t_monster_friend.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_monster_friend.f_id
	 * 
	 * @param id
	 *            the value for t_monster_friend.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_monster_friend.f_character_id
	 * 
	 * @return the value of t_monster_friend.f_character_id
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * t_monster_friend.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_monster_friend.f_character_id
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * t_monster_friend.f_monster_id
	 * 
	 * @return the value of t_monster_friend.f_monster_id
	 */
	public Integer getMonsterId() {
		return monsterId;
	}

	/**
	 * t_monster_friend.f_monster_id
	 * 
	 * @param monsterId
	 *            the value for t_monster_friend.f_monster_id
	 */
	public void setMonsterId(Integer monsterId) {
		this.monsterId = monsterId;
	}

	/**
	 * t_monster_friend.f_favar
	 * 
	 * @return the value of t_monster_friend.f_favar
	 */
	public Integer getFavar() {
		return favar;
	}

	/**
	 * t_monster_friend.f_favar
	 * 
	 * @param favar
	 *            the value for t_monster_friend.f_favar
	 */
	public void setFavar(Integer favar) {
		this.favar = favar;
	}

	/**
	 * t_monster_friend.f_add_date
	 * 
	 * @return the value of t_monster_friend.f_add_date
	 */
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * t_monster_friend.f_add_date
	 * 
	 * @param addDate
	 *            the value for t_monster_friend.f_add_date
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	/**
	 * t_monster_friend.f_state
	 * 
	 * @return the value of t_monster_friend.f_state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * t_monster_friend.f_state
	 * 
	 * @param state
	 *            the value for t_monster_friend.f_state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * t_monster_friend.f_now_hp
	 * 
	 * @return the value of t_monster_friend.f_now_hp
	 */
	public Integer getNowHp() {
		return nowHp;
	}

	/**
	 * t_monster_friend.f_now_hp
	 * 
	 * @param nowHp
	 *            the value for t_monster_friend.f_now_hp
	 */
	public void setNowHp(Integer nowHp) {
		this.nowHp = nowHp;
	}
}
