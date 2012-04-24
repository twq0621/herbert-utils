package net.snake.gamemodel.shop.bean;

import net.snake.ibatis.IbatisEntity;

public class ShopShengwang implements IbatisEntity{

	/**
	 * t_shop_shengwang.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 所属类别（1装备类，2商城道具类，3其他类） t_shop_shengwang.f_goods_type
	 * 
	 */
	private Integer goodsType;
	/**
	 * 物品ID t_shop_shengwang.f_goodmodel_id
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * 购买所需声望类型（1城战声望，2论剑声望，3两者均可购买） t_shop_shengwang.f_sale_type
	 * 
	 */
	private Integer saleType;
	/**
	 * 所需声望值 t_shop_shengwang.f_goods_price
	 * 
	 */
	private Integer goodsPrice;
	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop_shengwang.f_last_date
	 * 
	 */
	private Integer lastDate;
	/**
	 * 购买后是否绑定 0不绑定 ，1绑定 t_shop_shengwang.f_is_bind
	 * 
	 */
	private Integer isBind;
	/**
	 * 1开启 0关闭 t_shop_shengwang.f_is_enable
	 * 
	 */
	private Integer isEnable;
	/**
	 * 附加属性描述 t_shop_shengwang.f_addition_desc
	 * 
	 */
	private String additionDesc;
	/**
	 * 强化等级描述 t_shop_shengwang.f_strengthen_desc
	 * 
	 */
	private String strengthenDesc;
	/**
	 * t_shop_shengwang.f_desc
	 * 
	 */
	private String desc;
	/**
	 * t_shop_shengwang.f_desc_i18n
	 * 
	 */
	private String descI18n;
	/**
	 * t_shop_shengwang.f_use_desc
	 * 
	 */
	private String useDesc;
	/**
	 * t_shop_shengwang.f_use_desc_i18n
	 * 
	 */
	private String useDescI18n;

	/**
	 * t_shop_shengwang.f_id
	 * @return  the value of t_shop_shengwang.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_shop_shengwang.f_id
	 * @param id  the value for t_shop_shengwang.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 所属类别（1装备类，2商城道具类，3其他类） t_shop_shengwang.f_goods_type
	 * @return  the value of t_shop_shengwang.f_goods_type
	 * 
	 */
	public Integer getGoodsType() {
		return goodsType;
	}

	/**
	 * 所属类别（1装备类，2商城道具类，3其他类） t_shop_shengwang.f_goods_type
	 * @param goodsType  the value for t_shop_shengwang.f_goods_type
	 * 
	 */
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 * 物品ID t_shop_shengwang.f_goodmodel_id
	 * @return  the value of t_shop_shengwang.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 物品ID t_shop_shengwang.f_goodmodel_id
	 * @param goodmodelId  the value for t_shop_shengwang.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 购买所需声望类型（1城战声望，2论剑声望，3两者均可购买） t_shop_shengwang.f_sale_type
	 * @return  the value of t_shop_shengwang.f_sale_type
	 * 
	 */
	public Integer getSaleType() {
		return saleType;
	}

	/**
	 * 购买所需声望类型（1城战声望，2论剑声望，3两者均可购买） t_shop_shengwang.f_sale_type
	 * @param saleType  the value for t_shop_shengwang.f_sale_type
	 * 
	 */
	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	/**
	 * 所需声望值 t_shop_shengwang.f_goods_price
	 * @return  the value of t_shop_shengwang.f_goods_price
	 * 
	 */
	public Integer getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * 所需声望值 t_shop_shengwang.f_goods_price
	 * @param goodsPrice  the value for t_shop_shengwang.f_goods_price
	 * 
	 */
	public void setGoodsPrice(Integer goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop_shengwang.f_last_date
	 * @return  the value of t_shop_shengwang.f_last_date
	 * 
	 */
	public Integer getLastDate() {
		return lastDate;
	}

	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop_shengwang.f_last_date
	 * @param lastDate  the value for t_shop_shengwang.f_last_date
	 * 
	 */
	public void setLastDate(Integer lastDate) {
		this.lastDate = lastDate;
	}

	/**
	 * 购买后是否绑定 0不绑定 ，1绑定 t_shop_shengwang.f_is_bind
	 * @return  the value of t_shop_shengwang.f_is_bind
	 * 
	 */
	public Integer getIsBind() {
		return isBind;
	}

	/**
	 * 购买后是否绑定 0不绑定 ，1绑定 t_shop_shengwang.f_is_bind
	 * @param isBind  the value for t_shop_shengwang.f_is_bind
	 * 
	 */
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	/**
	 * 1开启 0关闭 t_shop_shengwang.f_is_enable
	 * @return  the value of t_shop_shengwang.f_is_enable
	 * 
	 */
	public Integer getIsEnable() {
		return isEnable;
	}

	/**
	 * 1开启 0关闭 t_shop_shengwang.f_is_enable
	 * @param isEnable  the value for t_shop_shengwang.f_is_enable
	 * 
	 */
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * 附加属性描述 t_shop_shengwang.f_addition_desc
	 * @return  the value of t_shop_shengwang.f_addition_desc
	 * 
	 */
	public String getAdditionDesc() {
		return additionDesc;
	}

	/**
	 * 附加属性描述 t_shop_shengwang.f_addition_desc
	 * @param additionDesc  the value for t_shop_shengwang.f_addition_desc
	 * 
	 */
	public void setAdditionDesc(String additionDesc) {
		this.additionDesc = additionDesc;
	}

	/**
	 * 强化等级描述 t_shop_shengwang.f_strengthen_desc
	 * @return  the value of t_shop_shengwang.f_strengthen_desc
	 * 
	 */
	public String getStrengthenDesc() {
		return strengthenDesc;
	}

	/**
	 * 强化等级描述 t_shop_shengwang.f_strengthen_desc
	 * @param strengthenDesc  the value for t_shop_shengwang.f_strengthen_desc
	 * 
	 */
	public void setStrengthenDesc(String strengthenDesc) {
		this.strengthenDesc = strengthenDesc;
	}

	/**
	 * t_shop_shengwang.f_desc
	 * @return  the value of t_shop_shengwang.f_desc
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * t_shop_shengwang.f_desc
	 * @param desc  the value for t_shop_shengwang.f_desc
	 * 
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * t_shop_shengwang.f_desc_i18n
	 * @return  the value of t_shop_shengwang.f_desc_i18n
	 * 
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * t_shop_shengwang.f_desc_i18n
	 * @param descI18n  the value for t_shop_shengwang.f_desc_i18n
	 * 
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * t_shop_shengwang.f_use_desc
	 * @return  the value of t_shop_shengwang.f_use_desc
	 * 
	 */
	public String getUseDesc() {
		return useDesc;
	}

	/**
	 * t_shop_shengwang.f_use_desc
	 * @param useDesc  the value for t_shop_shengwang.f_use_desc
	 * 
	 */
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	/**
	 * t_shop_shengwang.f_use_desc_i18n
	 * @return  the value of t_shop_shengwang.f_use_desc_i18n
	 * 
	 */
	public String getUseDescI18n() {
		return useDescI18n;
	}

	/**
	 * t_shop_shengwang.f_use_desc_i18n
	 * @param useDescI18n  the value for t_shop_shengwang.f_use_desc_i18n
	 * 
	 */
	public void setUseDescI18n(String useDescI18n) {
		this.useDescI18n = useDescI18n;
	}
}
