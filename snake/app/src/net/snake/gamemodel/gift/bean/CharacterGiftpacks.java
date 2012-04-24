package net.snake.gamemodel.gift.bean;

import java.util.Date;

import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.ibatis.IbatisEntity;

public class CharacterGiftpacks implements IbatisEntity {

	/**
	 * t_character_giftpacks.id
	 * 
	 */
	private Integer id;
	/**
	 * 关联角色id t_character_giftpacks.character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 领取礼包id t_character_giftpacks.gift_packs_id
	 * 
	 */
	private Integer giftPacksId;
	/**
	 * 见面有礼礼包计时 t_character_giftpacks.time
	 * 
	 */
	private Long time;
	/**
	 * 是否已经领取该礼包 1领取/0没有 t_character_giftpacks.is_owner
	 * 
	 */
	private Boolean isOwner;
	/**
	 * 是否可以领取 0不满足领取/1满足 t_character_giftpacks.is_get
	 * 
	 */
	private Boolean isGet;
	/**
	 * 年份 t_character_giftpacks.year
	 * 
	 */
	private Integer year;
	/**
	 * 每年的第几个星期 t_character_giftpacks.week
	 * 
	 */
	private Integer week;
	/**
	 * 一年的第几个月 t_character_giftpacks.month
	 * 
	 */
	private Integer month;
	/**
	 * 签到时间 t_character_giftpacks.qiandao_date
	 * 
	 */
	private Date qiandaoDate;

	/**
	 * t_character_giftpacks.id
	 * @return  the value of t_character_giftpacks.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_giftpacks.id
	 * @param id  the value for t_character_giftpacks.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 关联角色id t_character_giftpacks.character_id
	 * @return  the value of t_character_giftpacks.character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 关联角色id t_character_giftpacks.character_id
	 * @param characterId  the value for t_character_giftpacks.character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 领取礼包id t_character_giftpacks.gift_packs_id
	 * @return  the value of t_character_giftpacks.gift_packs_id
	 * 
	 */
	public Integer getGiftPacksId() {
		return giftPacksId;
	}

	/**
	 * 领取礼包id t_character_giftpacks.gift_packs_id
	 * @param giftPacksId  the value for t_character_giftpacks.gift_packs_id
	 * 
	 */
	public void setGiftPacksId(Integer giftPacksId) {
		this.giftPacksId = giftPacksId;
	}

	/**
	 * 见面有礼礼包计时 t_character_giftpacks.time
	 * @return  the value of t_character_giftpacks.time
	 * 
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * 见面有礼礼包计时 t_character_giftpacks.time
	 * @param time  the value for t_character_giftpacks.time
	 * 
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * 是否已经领取该礼包 1领取/0没有 t_character_giftpacks.is_owner
	 * @return  the value of t_character_giftpacks.is_owner
	 * 
	 */
	public Boolean getIsOwner() {
		if(this.isOwner==null){
			this.isOwner=false;
		}
		return isOwner;
	}

	/**
	 * 是否已经领取该礼包 1领取/0没有 t_character_giftpacks.is_owner
	 * @param isOwner  the value for t_character_giftpacks.is_owner
	 * 
	 */
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	 * 是否可以领取 0不满足领取/1满足 t_character_giftpacks.is_get
	 * @return  the value of t_character_giftpacks.is_get
	 * 
	 */
	public Boolean getIsGet() {
		if(this.isGet==null){
			this.isGet=false;
		}
		return isGet;
	}

	/**
	 * 是否可以领取 0不满足领取/1满足 t_character_giftpacks.is_get
	 * @param isGet  the value for t_character_giftpacks.is_get
	 * 
	 */
	public void setIsGet(Boolean isGet) {
		this.isGet = isGet;
	}

	/**
	 * 年份 t_character_giftpacks.year
	 * @return  the value of t_character_giftpacks.year
	 * 
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * 年份 t_character_giftpacks.year
	 * @param year  the value for t_character_giftpacks.year
	 * 
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * 每年的第几个星期 t_character_giftpacks.week
	 * @return  the value of t_character_giftpacks.week
	 * 
	 */
	public Integer getWeek() {
		return week;
	}

	/**
	 * 每年的第几个星期 t_character_giftpacks.week
	 * @param week  the value for t_character_giftpacks.week
	 * 
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}

	/**
	 * 一年的第几个月 t_character_giftpacks.month
	 * @return  the value of t_character_giftpacks.month
	 * 
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * 一年的第几个月 t_character_giftpacks.month
	 * @param month  the value for t_character_giftpacks.month
	 * 
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * 签到时间 t_character_giftpacks.qiandao_date
	 * @return  the value of t_character_giftpacks.qiandao_date
	 * 
	 */
	public Date getQiandaoDate() {
		return qiandaoDate;
	}

	/**
	 * 签到时间 t_character_giftpacks.qiandao_date
	 * @param qiandaoDate  the value for t_character_giftpacks.qiandao_date
	 * 
	 */
	public void setQiandaoDate(Date qiandaoDate) {
		this.qiandaoDate = qiandaoDate;
	}

	private GiftPacks gp;

	public GiftPacks getGp() {
		if(this.gp==null){
			this.gp=GiftPacksManager.getInstance().getGiftPacksById(this.giftPacksId);
		}
		return gp;
	}

	public void setGp(GiftPacks gp) {
		this.gp = gp;
	}
	
}
