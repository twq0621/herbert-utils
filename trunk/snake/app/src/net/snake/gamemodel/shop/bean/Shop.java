package net.snake.gamemodel.shop.bean;

import net.snake.commons.TimeExpression;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.ibatis.IbatisEntity;

public class Shop implements IbatisEntity {
	/**
	 * t_shop.f_id
	 */
	private Integer id;
	/**
	 * 商品分类:8限购类型 t_shop.f_goods_type
	 */
	private Integer goodsType;
	/**
	 * 商品名称 t_shop.f_name
	 */
	private String name;
	/**
	 * 映射t_goodmodel表id t_shop.f_goodmodel_id
	 */
	private Integer goodmodelId;
	/**
	 * 购买条件:元宝1，礼金2，积分3，奖励4 t_shop.f_sale_type
	 */
	private Integer saleType;
	/**
	 * 商品价格 t_shop.f_goods_price
	 */
	private Integer goodsPrice;
	/**
	 * 热销标志 1热销 t_shop.f_hot_mark
	 */
	private Integer hotMark;
	/**
	 * 折扣标志 t_shop.f_abate_mark
	 */
	private Integer abateMark;
	/**
	 * 排序 t_shop.f_order
	 */
	private Integer order;
	/**
	 * 原价 t_shop.f_goods_old_price
	 */
	private Integer goodsOldPrice;
	/**
	 * 是否删除 0否 /1已经删除 t_shop.f_isdel
	 */
	private Integer isdel;
	/**
	 * 显示时间段控制 t_shop.f_view_time
	 */
	private String viewTime;
	/**
	 * 限制购买数量 只有限购类型的才会生效 t_shop.f_limite_count
	 */
	private Integer limiteCount;
	/**
	 * 购买后是否绑定 t_shop.f_is_bind
	 */
	private Integer isBind;
	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop.f_last_date
	 */
	private String lastDate;

	/**
	 * t_shop.f_id
	 * 
	 * @return the value of t_shop.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_shop.f_id
	 * 
	 * @param id
	 *            the value for t_shop.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 商品分类:8限购类型 t_shop.f_goods_type
	 * 
	 * @return the value of t_shop.f_goods_type
	 */
	public Integer getGoodsType() {
		return goodsType;
	}

	/**
	 * 商品分类:8限购类型 t_shop.f_goods_type
	 * 
	 * @param goodsType
	 *            the value for t_shop.f_goods_type
	 */
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 * 商品名称 t_shop.f_name
	 * 
	 * @return the value of t_shop.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名称 t_shop.f_name
	 * 
	 * @param name
	 *            the value for t_shop.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 映射t_goodmodel表id t_shop.f_goodmodel_id
	 * 
	 * @return the value of t_shop.f_goodmodel_id
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 映射t_goodmodel表id t_shop.f_goodmodel_id
	 * 
	 * @param goodmodelId
	 *            the value for t_shop.f_goodmodel_id
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 购买条件:元宝1，礼金2，积分3，奖励4 t_shop.f_sale_type
	 * 
	 * @return the value of t_shop.f_sale_type
	 */
	public Integer getSaleType() {
		return saleType;
	}

	/**
	 * 购买条件:元宝1，礼金2，积分3，奖励4 t_shop.f_sale_type
	 * 
	 * @param saleType
	 *            the value for t_shop.f_sale_type
	 */
	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	/**
	 * 商品价格 t_shop.f_goods_price
	 * 
	 * @return the value of t_shop.f_goods_price
	 */
	public Integer getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * 商品价格 t_shop.f_goods_price
	 * 
	 * @param goodsPrice
	 *            the value for t_shop.f_goods_price
	 */
	public void setGoodsPrice(Integer goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * 热销标志  0无状态 1热销 ,2限购，3限购+热销 t_shop.f_hot_mark
	 * 
	 * @return the value of t_shop.f_hot_mark
	 */
	public Integer getHotMark() {
		return hotMark;
	}

	/**
	 * 热销标志 1热销 t_shop.f_hot_mark
	 * 
	 * @param hotMark
	 *            the value for t_shop.f_hot_mark
	 */
	public void setHotMark(Integer hotMark) {
		this.hotMark = hotMark;
	}

	/**
	 * 折扣标志 t_shop.f_abate_mark
	 * 
	 * @return the value of t_shop.f_abate_mark
	 */
	public Integer getAbateMark() {
		return abateMark;
	}

	/**
	 * 折扣标志 t_shop.f_abate_mark
	 * 
	 * @param abateMark
	 *            the value for t_shop.f_abate_mark
	 */
	public void setAbateMark(Integer abateMark) {
		this.abateMark = abateMark;
	}

	/**
	 * 排序 t_shop.f_order
	 * 
	 * @return the value of t_shop.f_order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * 排序 t_shop.f_order
	 * 
	 * @param order
	 *            the value for t_shop.f_order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 原价 t_shop.f_goods_old_price
	 * 
	 * @return the value of t_shop.f_goods_old_price
	 */
	public Integer getGoodsOldPrice() {
		if (this.goodsOldPrice == null) {
			this.goodsOldPrice = this.goodsPrice;
		}
		return goodsOldPrice;
	}

	/**
	 * 原价 t_shop.f_goods_old_price
	 * 
	 * @param goodsOldPrice
	 *            the value for t_shop.f_goods_old_price
	 */
	public void setGoodsOldPrice(Integer goodsOldPrice) {
		this.goodsOldPrice = goodsOldPrice;
	}

	/**
	 * 是否删除 0否 /1已经删除 t_shop.f_isdel
	 * 
	 * @return the value of t_shop.f_isdel
	 */
	public Integer getIsdel() {
		return isdel;
	}

	/**
	 * 是否删除 0否 /1已经删除 t_shop.f_isdel
	 * 
	 * @param isdel
	 *            the value for t_shop.f_isdel
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	/**
	 * 显示时间段控制 t_shop.f_view_time
	 * 
	 * @return the value of t_shop.f_view_time
	 */
	public String getViewTime() {
		return viewTime;
	}

	/**
	 * 显示时间段控制 t_shop.f_view_time
	 * 
	 * @param viewTime
	 *            the value for t_shop.f_view_time
	 */
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}

	/**
	 * 限制购买数量 只有限购类型的才会生效 t_shop.f_limite_count
	 * 
	 * @return the value of t_shop.f_limite_count
	 */
	public Integer getLimiteCount() {
		return limiteCount;
	}

	/**
	 * 限制购买数量 只有限购类型的才会生效 t_shop.f_limite_count
	 * 
	 * @param limiteCount
	 *            the value for t_shop.f_limite_count
	 */
	public void setLimiteCount(Integer limiteCount) {
		this.limiteCount = limiteCount;
	}

	/**
	 * 购买后是否绑定 t_shop.f_is_bind
	 * 
	 * @return the value of t_shop.f_is_bind
	 */
	public Integer getIsBind() {
		return isBind;
	}

	/**
	 * 购买后是否绑定 t_shop.f_is_bind
	 * 
	 * @param isBind
	 *            the value for t_shop.f_is_bind
	 */
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop.f_last_date
	 * 
	 * @return the value of t_shop.f_last_date
	 */
	public String getLastDate() {
		return lastDate;
	}

	/**
	 * 购买后失效时间 格式 yyyy-mm-dd hh:mm:ss t_shop.f_last_date
	 * 
	 * @param lastDate
	 *            the value for t_shop.f_last_date
	 */
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	private Goodmodel goodmodel;

	public Goodmodel getGoodmodel() {
		return goodmodel;
	}

	public void setGoodmodel(Goodmodel goodmodel) {
		this.goodmodel = goodmodel;
	}

	/**
	 * 符合时间条件，可以显示和购买
	 * @return
	 */
	public boolean isTimeExpression() {
		if (this.getViewTime() == null || this.getViewTime().length() == 0 || "0".equals(this.getViewTime() )) {
			return true;
		}
		TimeExpression te = new TimeExpression(this.getViewTime());
		return te.isExpressionTime(System.currentTimeMillis());
	}

}
