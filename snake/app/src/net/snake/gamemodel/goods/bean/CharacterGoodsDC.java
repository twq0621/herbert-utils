package net.snake.gamemodel.goods.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterGoodsDC  implements IbatisEntity{

	/**
	 * 角色id t_character_goods_dc.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 已完成收集数据索引id(格式{id;}*) t_character_goods_dc.f_goods_dc
	 * 
	 */
	private String goodsDc;

	/**
	 * 角色id t_character_goods_dc.f_character_id
	 * @return  the value of t_character_goods_dc.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_goods_dc.f_character_id
	 * @param characterId  the value for t_character_goods_dc.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 已完成收集数据索引id(格式{id;}*) t_character_goods_dc.f_goods_dc
	 * @return  the value of t_character_goods_dc.f_goods_dc
	 * 
	 */
	public String getGoodsDc() {
		return goodsDc;
	}

	/**
	 * 已完成收集数据索引id(格式{id;}*) t_character_goods_dc.f_goods_dc
	 * @param goodsDc  the value for t_character_goods_dc.f_goods_dc
	 * 
	 */
	public void setGoodsDc(String goodsDc) {
		this.goodsDc = goodsDc;
	}
    
}
