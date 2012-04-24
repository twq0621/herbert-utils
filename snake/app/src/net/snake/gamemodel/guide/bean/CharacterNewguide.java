package net.snake.gamemodel.guide.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterNewguide  implements IbatisEntity{

	/**
	 * t_character_newguide.id
	 * 
	 */
	private Integer id;
	/**
	 * t_character_newguide.character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * t_character_newguide.guide_num
	 * 
	 */
	private Short guideNum;
	/**
	 * t_character_newguide.guide_count
	 * 
	 */
	private Short guideCount;
	/**
	 * t_character_newguide.guide_isfinish
	 * 
	 */
	private Boolean guideIsfinish;
	/**
	 * t_character_newguide.start_date
	 * 
	 */
	private Date startDate;
	/**
	 * t_character_newguide.end_date
	 * 
	 */
	private Date endDate;
	/**
	 * t_character_newguide.type
	 * 
	 */
	private Byte type;

	/**
	 *t_character_newguide.id
	 * @return  the value of t_character_newguide.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *  t_character_newguide.id
	 * @param id  the value for t_character_newguide.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *t_character_newguide.character_id
	 * @return  the value of t_character_newguide.character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 *  t_character_newguide.character_id
	 * @param characterId  the value for t_character_newguide.character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 *t_character_newguide.guide_num
	 * @return  the value of t_character_newguide.guide_num
	 * 
	 */
	public Short getGuideNum() {
		return guideNum;
	}

	/**
	 *  t_character_newguide.guide_num
	 * @param guideNum  the value for t_character_newguide.guide_num
	 * 
	 */
	public void setGuideNum(Short guideNum) {
		this.guideNum = guideNum;
	}

	/**
	 *t_character_newguide.guide_count
	 * @return  the value of t_character_newguide.guide_count
	 * 
	 */
	public Short getGuideCount() {
		return guideCount;
	}

	/**
	 *  t_character_newguide.guide_count
	 * @param guideCount  the value for t_character_newguide.guide_count
	 * 
	 */
	public void setGuideCount(Short guideCount) {
		this.guideCount = guideCount;
	}

	/**
	 *t_character_newguide.guide_isfinish
	 * @return  the value of t_character_newguide.guide_isfinish
	 * 
	 */
	public Boolean getGuideIsfinish() {
		return guideIsfinish;
	}

	/**
	 *  t_character_newguide.guide_isfinish
	 * @param guideIsfinish  the value for t_character_newguide.guide_isfinish
	 * 
	 */
	public void setGuideIsfinish(Boolean guideIsfinish) {
		this.guideIsfinish = guideIsfinish;
	}

	/**
	 *t_character_newguide.start_date
	 * @return  the value of t_character_newguide.start_date
	 * 
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 *  t_character_newguide.start_date
	 * @param startDate  the value for t_character_newguide.start_date
	 * 
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 *t_character_newguide.end_date
	 * @return  the value of t_character_newguide.end_date
	 * 
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 *  t_character_newguide.end_date
	 * @param endDate  the value for t_character_newguide.end_date
	 * 
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 *t_character_newguide.type
	 * @return  the value of t_character_newguide.type
	 * 
	 */
	public Byte getType() {
		return type;
	}

	/**
	 *  t_character_newguide.type
	 * @param type  the value for t_character_newguide.type
	 * 
	 */
	public void setType(Byte type) {
		this.type = type;
	}
}
