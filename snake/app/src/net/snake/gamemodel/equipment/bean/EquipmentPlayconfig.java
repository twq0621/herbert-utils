package net.snake.gamemodel.equipment.bean;

import net.snake.ibatis.IbatisEntity;

/**
 * 装备强化表
 * 
 * @author benchild
 */

public class EquipmentPlayconfig implements IbatisEntity {

	/**
	 * 装备id t_equipment_playconfig.f_goodmodel_id
	 * 
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * 装备name t_equipment_playconfig.f_goodmodel_name
	 * 
	 * 
	 */
	private String goodmodelName;
	/**
	 * 需要精炼石id (重置附加属性使用) t_equipment_playconfig.f_jinglian
	 * 
	 * 
	 */
	private Integer jinglian;
	/**
	 * 需要精炼石数量 (重置附加属性使用) t_equipment_playconfig.f_jinglian_num
	 * 
	 * 
	 */
	private Integer jinglianNum;
	
	/**
	 * 下一等级物品id (材料、宝石合成，装备升级使用) t_equipment_playconfig.f_next_goodmodel_id
	 * 
	 * 
	 */
	private Integer nextGoodmodelId;
	/**
	 * 上一等级物品id (宝石合成失败时使用) t_equipment_playconfig.f_befo_goodmodel_id
	 * 
	 * 
	 */
	private Integer befoGoodmodelId;

	/**
	 * 重置附加属性所需真气(重置附加属性使用) t_equipment_playconfig.f_reset_add_zhenqi
	 * 
	 * 
	 */
	private Integer resetAddZhenqi;
	/**
	 * 重置附加属性成功几率(重置附加属性使用) t_equipment_playconfig.f_reset_add_probability
	 * 
	 * 
	 */
	private Integer resetAddProbability;
	/**
	 * 宝石熔合成功几率(宝石熔合使用) t_equipment_playconfig.f_ronghe_probability
	 * 
	 * 
	 */
	private Integer rongheProbability;
	/**
	 * 宝石熔合所需真气(宝石熔合使用) t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 * 
	 */
	private Integer rongheZhenqi;
	/**
	 * 宝石熔合所需铜币(宝石熔合使用) t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 * 
	 */
	private Integer rongheCopper;
	/**
	 * 材料合成所需真气(材料合成使用) t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 * 
	 */
	private Integer cailiaoCombineZhenqi;
	/**
	 * 材料合成所需铜币(材料合成使用) t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 * 
	 */
	private Integer cailiaoCombineCopper;
	/**
	 * 材料合成成功几率(材料合成使用) t_equipment_playconfig.f_cailiao_combine_probability
	 * 
	 * 
	 */
	private Integer cailiaoCombineProbability;

	/**
	 * 拨除所需真气 t_equipment_playconfig.f_bachu_zhenqi
	 * 
	 * 
	 */
	private Integer bachuZhenqi;
	/**
	 * 拨除成功概率 t_equipment_playconfig.f_bachu_probablity
	 * 
	 * 
	 */
	private Integer bachuCopper;
	/**
	 * 拨除成功概率 t_equipment_playconfig.f_bachu_probablity
	 * 
	 * 
	 */
	private Integer bachuProbablity;
	/**
	 * 拨除时破碎几率 t_equipment_playconfig.f_break_prob
	 * 
	 * 
	 */
	private Integer breakProb;
	/**
	 * 升阶合并的数量 t_equipment_playconfig.f_merge_sum
	 * 
	 * 
	 */
	private Integer mergeSum;
	/**
	 * 最大可镶嵌个数(用于规定装备或护身符的最大镶嵌个数) t_equipment_playconfig.f_max_stone_num
	 * 
	 * 
	 */
	private Integer maxStoneNum;

	/**
	 * 重置强化等级消耗铜币 (强化重置使用) t_equipment_playconfig.f_reset_strengthen_copper
	 * 
	 * 
	 */
	private Integer resetStrengthenCopper;
	/**
	 * 重置附加属性消耗铜币(重置附加属性使用) t_equipment_playconfig.f_reset_add_copper
	 * 
	 * 
	 */
	private Integer resetAddCopper;
	
	/**
	 * 天生属性强化所需材料ID t_equipment_playconfig.f_born_strengthen_cailiao_modelid
	 * 
	 * 
	 */
	private Integer bornStrengthenCailiaoModelid;
	/**
	 * 天生属性强化所需材料数量 t_equipment_playconfig.f_born_strengthen_cailiao_num
	 * 
	 * 
	 */
	private Integer bornStrengthenCailiaoNum;
	/**
	 * 天生属性强化所需消耗铜币 t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * 
	 */
	private Integer bornStrengthenCopper;
	/**
	 * 天生属性强化所需消耗真气 t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * 
	 */
	private Integer bornStrengthenZhenqi;
	
	/**
	 * 装备名称国际化 t_equipment_playconfig.f_goodmodel_name_i18n
	 * 
	 * 
	 */
	private String goodmodelNameI18n;

	/**
	 * 装备id t_equipment_playconfig.f_goodmodel_id
	 * 
	 * @return the value of t_equipment_playconfig.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 装备id t_equipment_playconfig.f_goodmodel_id
	 * 
	 * @param goodmodelId
	 *            the value for t_equipment_playconfig.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 装备name t_equipment_playconfig.f_goodmodel_name
	 * 
	 * @return the value of t_equipment_playconfig.f_goodmodel_name
	 * 
	 */
	public String getGoodmodelName() {
		return goodmodelName;
	}

	/**
	 * 装备name t_equipment_playconfig.f_goodmodel_name
	 * 
	 * @param goodmodelName
	 *            the value for t_equipment_playconfig.f_goodmodel_name
	 * 
	 */
	public void setGoodmodelName(String goodmodelName) {
		this.goodmodelName = goodmodelName;
	}

	
	/**
	 * 需要精炼石id (重置附加属性使用) t_equipment_playconfig.f_jinglian
	 * 
	 * @return the value of t_equipment_playconfig.f_jinglian
	 * 
	 */
	public Integer getJinglian() {
		return jinglian;
	}

	/**
	 * 需要精炼石id (重置附加属性使用) t_equipment_playconfig.f_jinglian
	 * 
	 * @param jinglian
	 *            the value for t_equipment_playconfig.f_jinglian
	 * 
	 */
	public void setJinglian(Integer jinglian) {
		this.jinglian = jinglian;
	}

	/**
	 * 需要精炼石数量 (重置附加属性使用) t_equipment_playconfig.f_jinglian_num
	 * 
	 * @return the value of t_equipment_playconfig.f_jinglian_num
	 * 
	 */
	public Integer getJinglianNum() {
		return jinglianNum;
	}

	/**
	 * 需要精炼石数量 (重置附加属性使用) t_equipment_playconfig.f_jinglian_num
	 * 
	 * @param jinglianNum
	 *            the value for t_equipment_playconfig.f_jinglian_num
	 * 
	 */
	public void setJinglianNum(Integer jinglianNum) {
		this.jinglianNum = jinglianNum;
	}

	
	/**
	 * 下一等级物品id (材料、宝石合成，装备升级使用) t_equipment_playconfig.f_next_goodmodel_id
	 * 
	 * @return the value of t_equipment_playconfig.f_next_goodmodel_id
	 * 
	 */
	public Integer getNextGoodmodelId() {
		return nextGoodmodelId;
	}

	/**
	 * 下一等级物品id (材料、宝石合成，装备升级使用) t_equipment_playconfig.f_next_goodmodel_id
	 * 
	 * @param nextGoodmodelId
	 *            the value for t_equipment_playconfig.f_next_goodmodel_id
	 * 
	 */
	public void setNextGoodmodelId(Integer nextGoodmodelId) {
		this.nextGoodmodelId = nextGoodmodelId;
	}

	/**
	 * 上一等级物品id (宝石合成失败时使用) t_equipment_playconfig.f_befo_goodmodel_id
	 * 
	 * @return the value of t_equipment_playconfig.f_befo_goodmodel_id
	 * 
	 */
	public Integer getBefoGoodmodelId() {
		return befoGoodmodelId;
	}

	/**
	 * 上一等级物品id (宝石合成失败时使用) t_equipment_playconfig.f_befo_goodmodel_id
	 * 
	 * @param befoGoodmodelId
	 *            the value for t_equipment_playconfig.f_befo_goodmodel_id
	 * 
	 */
	public void setBefoGoodmodelId(Integer befoGoodmodelId) {
		this.befoGoodmodelId = befoGoodmodelId;
	}

	/**
	 * 重置附加属性所需真气(重置附加属性使用) t_equipment_playconfig.f_reset_add_zhenqi
	 * 
	 * @return the value of t_equipment_playconfig.f_reset_add_zhenqi
	 * 
	 */
	public Integer getResetAddZhenqi() {
		return resetAddZhenqi;
	}

	/**
	 * 重置附加属性所需真气(重置附加属性使用) t_equipment_playconfig.f_reset_add_zhenqi
	 * 
	 * @param resetAddZhenqi
	 *            the value for t_equipment_playconfig.f_reset_add_zhenqi
	 * 
	 */
	public void setResetAddZhenqi(Integer resetAddZhenqi) {
		this.resetAddZhenqi = resetAddZhenqi;
	}

	/**
	 * 重置附加属性成功几率(重置附加属性使用) t_equipment_playconfig.f_reset_add_probability
	 * 
	 * @return the value of t_equipment_playconfig.f_reset_add_probability
	 * 
	 */
	public Integer getResetAddProbability() {
		return resetAddProbability;
	}

	/**
	 * 重置附加属性成功几率(重置附加属性使用) t_equipment_playconfig.f_reset_add_probability
	 * 
	 * @param resetAddProbability
	 *            the value for t_equipment_playconfig.f_reset_add_probability
	 * 
	 */
	public void setResetAddProbability(Integer resetAddProbability) {
		this.resetAddProbability = resetAddProbability;
	}

	/**
	 * 宝石熔合成功几率(宝石熔合使用) t_equipment_playconfig.f_ronghe_probability
	 * 
	 * @return the value of t_equipment_playconfig.f_ronghe_probability
	 * 
	 */
	public Integer getRongheProbability() {
		return rongheProbability;
	}

	/**
	 * 宝石熔合成功几率(宝石熔合使用) t_equipment_playconfig.f_ronghe_probability
	 * 
	 * @param rongheProbability
	 *            the value for t_equipment_playconfig.f_ronghe_probability
	 * 
	 */
	public void setRongheProbability(Integer rongheProbability) {
		this.rongheProbability = rongheProbability;
	}

	/**
	 * 宝石熔合所需真气(宝石熔合使用) t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 * @return the value of t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 */
	public Integer getRongheZhenqi() {
		return rongheZhenqi;
	}

	/**
	 * 宝石熔合所需真气(宝石熔合使用) t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 * @param rongheZhenqi
	 *            the value for t_equipment_playconfig.f_ronghe_zhenqi
	 * 
	 */
	public void setRongheZhenqi(Integer rongheZhenqi) {
		this.rongheZhenqi = rongheZhenqi;
	}

	/**
	 * 材料合成所需真气(材料合成使用) t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 * @return the value of t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 */
	public Integer getCailiaoCombineZhenqi() {
		return cailiaoCombineZhenqi;
	}

	/**
	 * 材料合成所需真气(材料合成使用) t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 * @param cailiaoCombineZhenqi
	 *            the value for t_equipment_playconfig.f_cailiao_combine_zhenqi
	 * 
	 */
	public void setCailiaoCombineZhenqi(Integer cailiaoCombineZhenqi) {
		this.cailiaoCombineZhenqi = cailiaoCombineZhenqi;
	}

	/**
	 * 材料合成成功几率(材料合成使用) t_equipment_playconfig.f_cailiao_combine_probability
	 * 
	 * @return the value of t_equipment_playconfig.f_cailiao_combine_probability
	 * 
	 */
	public Integer getCailiaoCombineProbability() {
		return cailiaoCombineProbability;
	}

	/**
	 * 材料合成成功几率(材料合成使用) t_equipment_playconfig.f_cailiao_combine_probability
	 * 
	 * @param cailiaoCombineProbability
	 *            the value for
	 *            t_equipment_playconfig.f_cailiao_combine_probability
	 * 
	 */
	public void setCailiaoCombineProbability(Integer cailiaoCombineProbability) {
		this.cailiaoCombineProbability = cailiaoCombineProbability;
	}

	/**
	 * 拨除所需真气 t_equipment_playconfig.f_bachu_zhenqi
	 * 
	 * @return the value of t_equipment_playconfig.f_bachu_zhenqi
	 * 
	 */
	public Integer getBachuZhenqi() {
		return bachuZhenqi;
	}

	/**
	 * 拨除所需真气 t_equipment_playconfig.f_bachu_zhenqi
	 * 
	 * @param bachuZhenqi
	 *            the value for t_equipment_playconfig.f_bachu_zhenqi
	 * 
	 */
	public void setBachuZhenqi(Integer bachuZhenqi) {
		this.bachuZhenqi = bachuZhenqi;
	}

	/**
	 * 拨除成功概率 t_equipment_playconfig.f_bachu_probablity
	 * 
	 * @return the value of t_equipment_playconfig.f_bachu_probablity
	 * 
	 */
	public Integer getBachuProbablity() {
		return bachuProbablity;
	}

	/**
	 * 拨除成功概率 t_equipment_playconfig.f_bachu_probablity
	 * 
	 * @param bachuProbablity
	 *            the value for t_equipment_playconfig.f_bachu_probablity
	 * 
	 */
	public void setBachuProbablity(Integer bachuProbablity) {
		this.bachuProbablity = bachuProbablity;
	}

	/**
	 * 拨除时破碎几率 t_equipment_playconfig.f_break_prob
	 * 
	 * @return the value of t_equipment_playconfig.f_break_prob
	 * 
	 */
	public Integer getBreakProb() {
		return breakProb;
	}

	/**
	 * 拨除时破碎几率 t_equipment_playconfig.f_break_prob
	 * 
	 * @param breakProb
	 *            the value for t_equipment_playconfig.f_break_prob
	 * 
	 */
	public void setBreakProb(Integer breakProb) {
		this.breakProb = breakProb;
	}

	/**
	 * 升阶合并的数量 t_equipment_playconfig.f_merge_sum
	 * 
	 * @return the value of t_equipment_playconfig.f_merge_sum
	 * 
	 */
	public Integer getMergeSum() {
		return mergeSum;
	}

	/**
	 * 升阶合并的数量 t_equipment_playconfig.f_merge_sum
	 * 
	 * @param mergeSum
	 *            the value for t_equipment_playconfig.f_merge_sum
	 * 
	 */
	public void setMergeSum(Integer mergeSum) {
		this.mergeSum = mergeSum;
	}

	/**
	 * 最大可镶嵌个数(用于规定装备或护身符的最大镶嵌个数) t_equipment_playconfig.f_max_stone_num
	 * 
	 * @return the value of t_equipment_playconfig.f_max_stone_num
	 * 
	 */
	public Integer getMaxStoneNum() {
		return maxStoneNum;
	}

	/**
	 * 最大可镶嵌个数(用于规定装备或护身符的最大镶嵌个数) t_equipment_playconfig.f_max_stone_num
	 * 
	 * @param maxStoneNum
	 *            the value for t_equipment_playconfig.f_max_stone_num
	 * 
	 */
	public void setMaxStoneNum(Integer maxStoneNum) {
		this.maxStoneNum = maxStoneNum;
	}

	
	
	/**
	 * 重置强化等级消耗铜币 (强化重置使用) t_equipment_playconfig.f_reset_strengthen_copper
	 * 
	 * @return the value of t_equipment_playconfig.f_reset_strengthen_copper
	 * 
	 */
	public Integer getResetStrengthenCopper() {
		return resetStrengthenCopper;
	}

	/**
	 * 重置强化等级消耗铜币 (强化重置使用) t_equipment_playconfig.f_reset_strengthen_copper
	 * 
	 * @param resetStrengthenCopper
	 *            the value for t_equipment_playconfig.f_reset_strengthen_copper
	 * 
	 */
	public void setResetStrengthenCopper(Integer resetStrengthenCopper) {
		this.resetStrengthenCopper = resetStrengthenCopper;
	}

	/**
	 * 重置附加属性消耗铜币(重置附加属性使用) t_equipment_playconfig.f_reset_add_copper
	 * 
	 * @return the value of t_equipment_playconfig.f_reset_add_copper
	 * 
	 */
	public Integer getResetAddCopper() {
		return resetAddCopper;
	}

	/**
	 * 重置附加属性消耗铜币(重置附加属性使用) t_equipment_playconfig.f_reset_add_copper
	 * 
	 * @param resetAddCopper
	 *            the value for t_equipment_playconfig.f_reset_add_copper
	 * 
	 */
	public void setResetAddCopper(Integer resetAddCopper) {
		this.resetAddCopper = resetAddCopper;
	}

	/**
	 * 天生属性强化所需材料ID t_equipment_playconfig.f_born_strengthen_cailiao_modelid
	 * 
	 * @return the value of
	 *         t_equipment_playconfig.f_born_strengthen_cailiao_modelid
	 * 
	 */
	public Integer getBornStrengthenCailiaoModelid() {
		return bornStrengthenCailiaoModelid;
	}

	/**
	 * 天生属性强化所需材料ID t_equipment_playconfig.f_born_strengthen_cailiao_modelid
	 * 
	 * @param bornStrengthenCailiaoModelid
	 *            the value for
	 *            t_equipment_playconfig.f_born_strengthen_cailiao_modelid
	 * 
	 */
	public void setBornStrengthenCailiaoModelid(Integer bornStrengthenCailiaoModelid) {
		this.bornStrengthenCailiaoModelid = bornStrengthenCailiaoModelid;
	}

	/**
	 * 天生属性强化所需材料数量 t_equipment_playconfig.f_born_strengthen_cailiao_num
	 * 
	 * @return the value of t_equipment_playconfig.f_born_strengthen_cailiao_num
	 * 
	 */
	public Integer getBornStrengthenCailiaoNum() {
		return bornStrengthenCailiaoNum;
	}

	/**
	 * 天生属性强化所需材料数量 t_equipment_playconfig.f_born_strengthen_cailiao_num
	 * 
	 * @param bornStrengthenCailiaoNum
	 *            the value for
	 *            t_equipment_playconfig.f_born_strengthen_cailiao_num
	 * 
	 */
	public void setBornStrengthenCailiaoNum(Integer bornStrengthenCailiaoNum) {
		this.bornStrengthenCailiaoNum = bornStrengthenCailiaoNum;
	}

	/**
	 * 天生属性强化所需消耗铜币 t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * @return the value of t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 */
	public Integer getBornStrengthenCopper() {
		return bornStrengthenCopper;
	}

	/**
	 * 天生属性强化所需消耗铜币 t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * @param bornStrengthenCopper
	 *            the value for t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 */
	public void setBornStrengthenCopper(Integer bornStrengthenCopper) {
		this.bornStrengthenCopper = bornStrengthenCopper;
	}

	/**
	 * 天生属性强化所需消耗真气 t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * @return the value of t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 */
	public Integer getBornStrengthenZhenqi() {
		return bornStrengthenZhenqi;
	}

	/**
	 * 天生属性强化所需消耗真气t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 * @param bornStrengthenCopper
	 *            the value for t_equipment_playconfig.f_born_strengthen_copper
	 * 
	 */
	public void setBornStrengthenZhenqi(Integer bornStrengthenZhenqi) {
		this.bornStrengthenZhenqi = bornStrengthenZhenqi;
	}

	/**
	 * 装备名称国际化 t_equipment_playconfig.f_goodmodel_name_i18n
	 * 
	 * @return the value of t_equipment_playconfig.f_goodmodel_name_i18n
	 * 
	 */
	public String getGoodmodelNameI18n() {
		return goodmodelNameI18n;
	}

	/**
	 * 装备名称国际化 t_equipment_playconfig.f_goodmodel_name_i18n
	 * 
	 * @param goodmodelNameI18n
	 *            the value for t_equipment_playconfig.f_goodmodel_name_i18n
	 * 
	 */
	public void setGoodmodelNameI18n(String goodmodelNameI18n) {
		this.goodmodelNameI18n = goodmodelNameI18n;
	}

	public Integer getCailiaoCombineCopper() {
		return cailiaoCombineCopper;
	}

	public void setCailiaoCombineCopper(Integer cailiaoCombineCopper) {
		this.cailiaoCombineCopper = cailiaoCombineCopper;
	}

	public Integer getBachuCopper() {
		return bachuCopper;
	}

	public void setBachuCopper(Integer bachuCopper) {
		this.bachuCopper = bachuCopper;
	}

	public Integer getRongheCopper() {
		return rongheCopper;
	}

	public void setRongheCopper(Integer rongheCopper) {
		this.rongheCopper = rongheCopper;
	}

}
