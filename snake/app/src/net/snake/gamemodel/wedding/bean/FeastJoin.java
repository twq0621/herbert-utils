package net.snake.gamemodel.wedding.bean;

import net.snake.ibatis.IbatisEntity;

public class FeastJoin implements IbatisEntity {

	/**
	 * t_feast_join.f_id
	 * 
	 */
	private Integer id;
	/**
	 * t_feast_join.f_applyer
	 * 
	 */
	private Integer applyer;
	/**
	 * 宴婚ID t_feast_join.f_characterid
	 * 
	 */
	private Integer characterid;
	/**
	 * 红包 t_feast_join.f_gift
	 * 
	 */
	private Integer gift;
	/**
	 * t_feast_join.f_mateid
	 * 
	 */
	private Integer mateid;

	/**
	 * t_feast_join.f_id
	 * @return  the value of t_feast_join.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_feast_join.f_id
	 * @param id  the value for t_feast_join.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_feast_join.f_applyer
	 * @return  the value of t_feast_join.f_applyer
	 * 
	 */
	public Integer getApplyer() {
		return applyer;
	}

	/**
	 * t_feast_join.f_applyer
	 * @param applyer  the value for t_feast_join.f_applyer
	 * 
	 */
	public void setApplyer(Integer applyer) {
		this.applyer = applyer;
	}

	/**
	 * 宴婚ID t_feast_join.f_characterid
	 * @return  the value of t_feast_join.f_characterid
	 * 
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * 宴婚ID t_feast_join.f_characterid
	 * @param characterid  the value for t_feast_join.f_characterid
	 * 
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * 红包 t_feast_join.f_gift
	 * @return  the value of t_feast_join.f_gift
	 * 
	 */
	public Integer getGift() {
		return gift;
	}

	/**
	 * 红包 t_feast_join.f_gift
	 * @param gift  the value for t_feast_join.f_gift
	 * 
	 */
	public void setGift(Integer gift) {
		this.gift = gift;
	}

	/**
	 * t_feast_join.f_mateid
	 * @return  the value of t_feast_join.f_mateid
	 * 
	 */
	public Integer getMateid() {
		return mateid;
	}

	/**
	 * t_feast_join.f_mateid
	 * @param mateid  the value for t_feast_join.f_mateid
	 * 
	 */
	public void setMateid(Integer mateid) {
		this.mateid = mateid;
	}
}
