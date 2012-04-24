package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class Breakthrough implements IbatisEntity {

	/**
	 * t_breakthrough.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 名字 t_breakthrough.f_name
	 * 
	 */
	private String name;
	/**
	 * 类别那个模块1真气模块 t_breakthrough.f_type
	 * 
	 */
	private Integer type;
	/**
	 * 物品id加需要数量（3100*10,3200*5） t_breakthrough.f_goomode_id_count
	 * 
	 */
	private String goomodeIdCount;
	/**
	 * 需要真气 t_breakthrough.f_need_zhenqi
	 * 
	 */
	private Integer needZhenqi;
	/**
	 * 几率 t_breakthrough.f_odds
	 * 
	 */
	private Integer odds;
	/**
	 * 需要铜钱 t_breakthrough.f_copper
	 * 
	 */
	private Integer copper;

	/**
	 * t_breakthrough.f_id
	 * @return  the value of t_breakthrough.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_breakthrough.f_id
	 * @param id  the value for t_breakthrough.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 名字 t_breakthrough.f_name
	 * @return  the value of t_breakthrough.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名字 t_breakthrough.f_name
	 * @param name  the value for t_breakthrough.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 类别那个模块1真气模块 t_breakthrough.f_type
	 * @return  the value of t_breakthrough.f_type
	 * 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类别那个模块1真气模块 t_breakthrough.f_type
	 * @param type  the value for t_breakthrough.f_type
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 物品id加需要数量（3100*10,3200*5） t_breakthrough.f_goomode_id_count
	 * @return  the value of t_breakthrough.f_goomode_id_count
	 * 
	 */
	public String getGoomodeIdCount() {
		return goomodeIdCount;
	}

	/**
	 * 物品id加需要数量（3100*10,3200*5） t_breakthrough.f_goomode_id_count
	 * @param goomodeIdCount  the value for t_breakthrough.f_goomode_id_count
	 * 
	 */
	public void setGoomodeIdCount(String goomodeIdCount) {
		this.goomodeIdCount = goomodeIdCount;
	}

	/**
	 * 需要真气 t_breakthrough.f_need_zhenqi
	 * @return  the value of t_breakthrough.f_need_zhenqi
	 * 
	 */
	public Integer getNeedZhenqi() {
		return needZhenqi;
	}

	/**
	 * 需要真气 t_breakthrough.f_need_zhenqi
	 * @param needZhenqi  the value for t_breakthrough.f_need_zhenqi
	 * 
	 */
	public void setNeedZhenqi(Integer needZhenqi) {
		this.needZhenqi = needZhenqi;
	}

	/**
	 * 几率 t_breakthrough.f_odds
	 * @return  the value of t_breakthrough.f_odds
	 * 
	 */
	public Integer getOdds() {
		return odds;
	}

	/**
	 * 几率 t_breakthrough.f_odds
	 * @param odds  the value for t_breakthrough.f_odds
	 * 
	 */
	public void setOdds(Integer odds) {
		this.odds = odds;
	}

	/**
	 * 需要铜钱 t_breakthrough.f_copper
	 * @return  the value of t_breakthrough.f_copper
	 * 
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 需要铜钱 t_breakthrough.f_copper
	 * @param copper  the value for t_breakthrough.f_copper
	 * 
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}
}
