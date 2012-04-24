package net.snake.gamemodel.goods.bean;

import net.snake.ibatis.IbatisEntity;

public class GoodsDC implements IbatisEntity {

	/**
	 * '主键' t_goods_dc.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 兑换收集等级 t_goods_dc.f_grade
	 * 
	 */
	private Integer grade;
	/**
	 * 兑换项名称 t_goods_dc.f_name
	 * 
	 */
	private String name;
	/**
	 * 收集物品 (物品id#物品数量#物品名称#地图ID_x_y_距离#怪物名称),* t_goods_dc.f_targetGoods
	 * 
	 */
	private String targetgoods;
	/**
	 * 奖励描述文字信息 t_goods_dc.f_rewardDesc
	 * 
	 */
	private String rewarddesc;
	/**
	 * 奖励潜能点个数 t_goods_dc.f_pointCnt
	 * 
	 */
	private Integer pointcnt;
	/**
	 * 兑换项名称国际化 t_goods_dc.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 奖励描述文字信息国际化 t_goods_dc.f_rewardDesc_i18n
	 * 
	 */
	private String rewarddescI18n;

	/**
	 * '主键' t_goods_dc.f_id
	 * @return  the value of t_goods_dc.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * '主键' t_goods_dc.f_id
	 * @param id  the value for t_goods_dc.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 兑换收集等级 t_goods_dc.f_grade
	 * @return  the value of t_goods_dc.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 兑换收集等级 t_goods_dc.f_grade
	 * @param grade  the value for t_goods_dc.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 兑换项名称 t_goods_dc.f_name
	 * @return  the value of t_goods_dc.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 兑换项名称 t_goods_dc.f_name
	 * @param name  the value for t_goods_dc.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 收集物品 (物品id#物品数量#物品名称#地图ID_x_y_距离#怪物名称),* t_goods_dc.f_targetGoods
	 * @return  the value of t_goods_dc.f_targetGoods
	 * 
	 */
	public String getTargetgoods() {
		return targetgoods;
	}

	/**
	 * 收集物品 (物品id#物品数量#物品名称#地图ID_x_y_距离#怪物名称),* t_goods_dc.f_targetGoods
	 * @param targetgoods  the value for t_goods_dc.f_targetGoods
	 * 
	 */
	public void setTargetgoods(String targetgoods) {
		this.targetgoods = targetgoods;
	}

	/**
	 * 奖励描述文字信息 t_goods_dc.f_rewardDesc
	 * @return  the value of t_goods_dc.f_rewardDesc
	 * 
	 */
	public String getRewarddesc() {
		return rewarddesc;
	}

	/**
	 * 奖励描述文字信息 t_goods_dc.f_rewardDesc
	 * @param rewarddesc  the value for t_goods_dc.f_rewardDesc
	 * 
	 */
	public void setRewarddesc(String rewarddesc) {
		this.rewarddesc = rewarddesc;
	}

	/**
	 * 奖励潜能点个数 t_goods_dc.f_pointCnt
	 * @return  the value of t_goods_dc.f_pointCnt
	 * 
	 */
	public Integer getPointcnt() {
		return pointcnt;
	}

	/**
	 * 奖励潜能点个数 t_goods_dc.f_pointCnt
	 * @param pointcnt  the value for t_goods_dc.f_pointCnt
	 * 
	 */
	public void setPointcnt(Integer pointcnt) {
		this.pointcnt = pointcnt;
	}

	/**
	 * 兑换项名称国际化 t_goods_dc.f_name_i18n
	 * @return  the value of t_goods_dc.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 兑换项名称国际化 t_goods_dc.f_name_i18n
	 * @param nameI18n  the value for t_goods_dc.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 奖励描述文字信息国际化 t_goods_dc.f_rewardDesc_i18n
	 * @return  the value of t_goods_dc.f_rewardDesc_i18n
	 * 
	 */
	public String getRewarddescI18n() {
		return rewarddescI18n;
	}

	/**
	 * 奖励描述文字信息国际化 t_goods_dc.f_rewardDesc_i18n
	 * @param rewarddescI18n  the value for t_goods_dc.f_rewardDesc_i18n
	 * 
	 */
	public void setRewarddescI18n(String rewarddescI18n) {
		this.rewarddescI18n = rewarddescI18n;
	}
}
