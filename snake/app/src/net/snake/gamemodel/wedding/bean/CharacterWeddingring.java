package net.snake.gamemodel.wedding.bean;

import java.util.Date;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;


public class CharacterWeddingring  implements IbatisEntity{

	/**
	 * t_character_weddingring.f_id
	 *
	 */
	private Integer id;
	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 *
	 */
	private Integer characterId;
	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 *
	 */
	private Integer ringId;
	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 *
	 */
	private Date weddingDate;
	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 *
	 */
	private Integer partnerId;

	/**
	 * t_character_weddingring.f_id
	 * @return  the value of t_character_weddingring.f_id
	 *
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_weddingring.f_id
	 * @param id  the value for t_character_weddingring.f_id
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 * @return  the value of t_character_weddingring.f_character_id
	 *
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 * @param characterId  the value for t_character_weddingring.f_character_id
	 *
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 * @return  the value of t_character_weddingring.f_ring_id
	 *
	 */
	public Integer getRingId() {
		return ringId;
	}

	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 * @param ringId  the value for t_character_weddingring.f_ring_id
	 *
	 */
	public void setRingId(Integer ringId) {
		this.ringId = ringId;
	}

	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 * @return  the value of t_character_weddingring.f_wedding_date
	 *
	 */
	public Date getWeddingDate() {
		return weddingDate;
	}

	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 * @param weddingDate  the value for t_character_weddingring.f_wedding_date
	 *
	 */
	public void setWeddingDate(Date weddingDate) {
		this.weddingDate = weddingDate;
	}

	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 * @return  the value of t_character_weddingring.f_partner_id
	 *
	 */
	public Integer getPartnerId() {
		return partnerId;
	}

	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 * @param partnerId  the value for t_character_weddingring.f_partner_id
	 *
	 */
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getMyName() {
		CharacterCacheEntry my= CharacterCacheManager.getInstance()
			.getCharacterCacheEntryById(this.getCharacterId());
		if(my==null){
			return null;
		}else{
			return my.getName();
		}
	}

	public String getPartnerName() {
		CharacterCacheEntry partner= CharacterCacheManager.getInstance()
			.getCharacterCacheEntryById(this.getPartnerId());
		if(partner==null){
			return null;
		}else{
			return partner.getName();
		}
	}
}
