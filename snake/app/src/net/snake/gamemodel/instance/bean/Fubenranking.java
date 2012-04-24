package net.snake.gamemodel.instance.bean;

import java.util.Date;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;

public class Fubenranking  implements IbatisEntity{
	private int metop = 0;// 临时统计出我排名第几

	public int getMetop() {
		return metop;
	}

	public void setMetop(int metop) {
		this.metop = metop;
	}

	/**
	 * t_fubenranking.f_id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 人物id t_fubenranking.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 人物等级 t_fubenranking.f_character_grade
	 * 
	 * 
	 */
	private Integer characterGrade;
	/**
	 * 副本id t_fubenranking.f_fuben_id
	 * 
	 * 
	 */
	private Integer fubenId;
	/**
	 * 副本人物所用时间 t_fubenranking.f_fuben_time
	 * 
	 * 
	 */
	private Integer fubenTime;
	/**
	 * 创建时期 t_fubenranking.f_fuben_date
	 * 
	 * 
	 */
	private Date fubenDate;
	/**
	 * 副本更新时间 t_fubenranking.f_previous_time
	 * 
	 * 
	 */
	private Integer previousTime;

	/**
	 * t_fubenranking.f_id
	 * 
	 * @return the value of t_fubenranking.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_fubenranking.f_id
	 * 
	 * @param id
	 *            the value for t_fubenranking.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 人物id t_fubenranking.f_character_id
	 * 
	 * @return the value of t_fubenranking.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物id t_fubenranking.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_fubenranking.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 人物等级 t_fubenranking.f_character_grade
	 * 
	 * @return the value of t_fubenranking.f_character_grade
	 * 
	 */
	public Integer getCharacterGrade() {
		return characterGrade;
	}

	/**
	 * 人物等级 t_fubenranking.f_character_grade
	 * 
	 * @param characterGrade
	 *            the value for t_fubenranking.f_character_grade
	 * 
	 */
	public void setCharacterGrade(Integer characterGrade) {
		this.characterGrade = characterGrade;
	}

	/**
	 * 副本id t_fubenranking.f_fuben_id
	 * 
	 * @return the value of t_fubenranking.f_fuben_id
	 * 
	 */
	public Integer getFubenId() {
		return fubenId;
	}

	/**
	 * 副本id t_fubenranking.f_fuben_id
	 * 
	 * @param fubenId
	 *            the value for t_fubenranking.f_fuben_id
	 * 
	 */
	public void setFubenId(Integer fubenId) {
		this.fubenId = fubenId;
	}

	/**
	 * 副本人物所用时间 t_fubenranking.f_fuben_time
	 * 
	 * @return the value of t_fubenranking.f_fuben_time
	 * 
	 */
	public Integer getFubenTime() {
		return fubenTime;
	}

	/**
	 * 副本人物所用时间 t_fubenranking.f_fuben_time
	 * 
	 * @param fubenTime
	 *            the value for t_fubenranking.f_fuben_time
	 * 
	 */
	public void setFubenTime(Integer fubenTime) {
		this.fubenTime = fubenTime;
	}

	/**
	 * 创建时期 t_fubenranking.f_fuben_date
	 * 
	 * @return the value of t_fubenranking.f_fuben_date
	 * 
	 */
	public Date getFubenDate() {
		return fubenDate;
	}

	/**
	 * 创建时期 t_fubenranking.f_fuben_date
	 * 
	 * @param fubenDate
	 *            the value for t_fubenranking.f_fuben_date
	 * 
	 */
	public void setFubenDate(Date fubenDate) {
		this.fubenDate = fubenDate;
	}

	/**
	 * 副本更新时间 t_fubenranking.f_previous_time
	 * 
	 * @return the value of t_fubenranking.f_previous_time
	 * 
	 */
	public Integer getPreviousTime() {
		return previousTime;
	}

	/**
	 * 副本更新时间 t_fubenranking.f_previous_time
	 * 
	 * @param previousTime
	 *            the value for t_fubenranking.f_previous_time
	 * 
	 */
	public void setPreviousTime(Integer previousTime) {
		this.previousTime = previousTime;
	}

	private CharacterCacheEntry cce;

	public CharacterCacheEntry getCce() {
		if (this.cce == null) {
			this.cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(characterId);
			if (this.cce == null) {
				// return new CharacterCacheEntry();
			}
		}
		return cce;
	}

	public void setCce(CharacterCacheEntry cce) {
		this.cce = cce;
	}
}
