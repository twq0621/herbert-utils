package net.snake.gamemodel.fight.bean;

import net.snake.ibatis.IbatisEntity;

public class GongchengVehicle  implements IbatisEntity{

	/**
	 * 攻城车id 即为攻城车类别 与npc 功能类别等同，id 500起 t_gongcheng_vehicle.f_id
	 * 
	 */
	private Integer id;
	/**
	 * t_gongcheng_vehicle.f_hurt_value
	 * 
	 */
	private Integer hurtValue;
	/**
	 * 误差格数 t_gongcheng_vehicle.f_wucha_scope
	 * 
	 */
	private Integer wuchaScope;
	/**
	 * 购买价格 t_gongcheng_vehicle.f_copper
	 * 
	 */
	private Integer copper;
	/**
	 * 伤害范围 t_gongcheng_vehicle.f_hurt_scope
	 * 
	 */
	private Integer hurtScope;

	/**
	 * 攻城车id 即为攻城车类别 与npc 功能类别等同，id 500起 t_gongcheng_vehicle.f_id
	 * @return  the value of t_gongcheng_vehicle.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 攻城车id 即为攻城车类别 与npc 功能类别等同，id 500起 t_gongcheng_vehicle.f_id
	 * @param id  the value for t_gongcheng_vehicle.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_gongcheng_vehicle.f_hurt_value
	 * @return  the value of t_gongcheng_vehicle.f_hurt_value
	 * 
	 */
	public Integer getHurtValue() {
		return hurtValue;
	}

	/**
	 * t_gongcheng_vehicle.f_hurt_value
	 * @param hurtValue  the value for t_gongcheng_vehicle.f_hurt_value
	 * 
	 */
	public void setHurtValue(Integer hurtValue) {
		this.hurtValue = hurtValue;
	}

	/**
	 * 误差格数 t_gongcheng_vehicle.f_wucha_scope
	 * @return  the value of t_gongcheng_vehicle.f_wucha_scope
	 * 
	 */
	public Integer getWuchaScope() {
		return wuchaScope;
	}

	/**
	 * 误差格数 t_gongcheng_vehicle.f_wucha_scope
	 * @param wuchaScope  the value for t_gongcheng_vehicle.f_wucha_scope
	 * 
	 */
	public void setWuchaScope(Integer wuchaScope) {
		this.wuchaScope = wuchaScope;
	}

	/**
	 * 购买价格 t_gongcheng_vehicle.f_copper
	 * @return  the value of t_gongcheng_vehicle.f_copper
	 * 
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 购买价格 t_gongcheng_vehicle.f_copper
	 * @param copper  the value for t_gongcheng_vehicle.f_copper
	 * 
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 伤害范围 t_gongcheng_vehicle.f_hurt_scope
	 * @return  the value of t_gongcheng_vehicle.f_hurt_scope
	 * 
	 */
	public Integer getHurtScope() {
		return hurtScope;
	}

	/**
	 * 伤害范围 t_gongcheng_vehicle.f_hurt_scope
	 * @param hurtScope  the value for t_gongcheng_vehicle.f_hurt_scope
	 * 
	 */
	public void setHurtScope(Integer hurtScope) {
		this.hurtScope = hurtScope;
	}
}
