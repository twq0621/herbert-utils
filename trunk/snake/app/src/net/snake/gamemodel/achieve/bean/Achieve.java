package net.snake.gamemodel.achieve.bean;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.ibatis.IbatisEntity;

public class Achieve implements IbatisEntity {

	/**
	 * t_achieve.f_id
	 */
	private Integer id;
	/**
	 * 称号name t_achieve.f_title
	 */
	private String title;
	/**
	 * 类别（0天道酬勤，1江湖历练，2强者之路,3神兵神骑,4浴血沙场） t_achieve.f_kind
	 */
	private Integer kind;
	/**
	 * 子分类 t_achieve.f_child_kind
	 */
	private Integer childKind;
	/**
	 * 成就描述 t_achieve.f_desc
	 */
	private String desc;
	/**
	 * 完成进度计数（满足条件） t_achieve.f_achieve_count
	 */
	private Integer achieveCount;
	/**
	 * 增加点数 t_achieve.f_point
	 */
	private Integer point;
	/**
	 * 填写子分类备注 t_achieve.f_beizhu
	 */
	private String beizhu;
	/**
	 * 图标编号 t_achieve.f_ico
	 */
	private Integer ico;
	/**
	 * 奖励礼包 t_achieve.f_good_id
	 */
	private Integer goodId;
	/**
	 * 是否公告 0不公告/1公告 t_achieve.f_isnotice
	 */
	private Integer isnotice;
	/**
	 * 成就名称 t_achieve.f_name
	 */
	private String name;
	/**
	 * 0击杀怪物 ，2任务 ，3装备 ，4坐骑， 5活动，6副本，7社交（好友，帮会, 组队）,8玩家死亡计数，9其他（连斩，武功，声望，静脉，等级，跳跃） 程序模块分类 t_achieve.f_model_type
	 */
	private Integer modelType;
	/**
	 * 称号name国际化 t_achieve.f_title_i18n
	 */
	private String titleI18n;
	/**
	 * 成就描述国际化 t_achieve.f_desc_i18n
	 */
	private String descI18n;
	/**
	 * 填写子分类备注国际化 t_achieve.f_beizhu_i18n
	 */
	private String beizhuI18n;
	/**
	 * 成就名称国际化 t_achieve.f_name_i18n
	 */
	private String nameI18n;

	/**
	 * t_achieve.f_id
	 * 
	 * @return the value of t_achieve.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_achieve.f_id
	 * 
	 * @param id
	 *            the value for t_achieve.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 称号name t_achieve.f_title
	 * 
	 * @return the value of t_achieve.f_title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 称号name t_achieve.f_title
	 * 
	 * @param title
	 *            the value for t_achieve.f_title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 类别（0天道酬勤，1江湖历练，2强者之路,3神兵神骑,4浴血沙场） t_achieve.f_kind
	 * 
	 * @return the value of t_achieve.f_kind
	 */
	public Integer getKind() {
		return kind;
	}

	/**
	 * 类别（0天道酬勤，1江湖历练，2强者之路,3神兵神骑,4浴血沙场） t_achieve.f_kind
	 * 
	 * @param kind
	 *            the value for t_achieve.f_kind
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}

	/**
	 * 子分类 t_achieve.f_child_kind
	 * 
	 * @return the value of t_achieve.f_child_kind
	 */
	public Integer getChildKind() {
		return childKind;
	}

	/**
	 * 子分类 t_achieve.f_child_kind
	 * 
	 * @param childKind
	 *            the value for t_achieve.f_child_kind
	 */
	public void setChildKind(Integer childKind) {
		this.childKind = childKind;
	}

	/**
	 * 成就描述 t_achieve.f_desc
	 * 
	 * @return the value of t_achieve.f_desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 成就描述 t_achieve.f_desc
	 * 
	 * @param desc
	 *            the value for t_achieve.f_desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 完成进度计数（满足条件） t_achieve.f_achieve_count
	 * 
	 * @return the value of t_achieve.f_achieve_count
	 */
	public Integer getAchieveCount() {
		return achieveCount;
	}

	/**
	 * 完成进度计数（满足条件） t_achieve.f_achieve_count
	 * 
	 * @param achieveCount
	 *            the value for t_achieve.f_achieve_count
	 */
	public void setAchieveCount(Integer achieveCount) {
		this.achieveCount = achieveCount;
	}

	/**
	 * 增加点数 t_achieve.f_point
	 * 
	 * @return the value of t_achieve.f_point
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * 增加点数 t_achieve.f_point
	 * 
	 * @param point
	 *            the value for t_achieve.f_point
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * 填写子分类备注 t_achieve.f_beizhu
	 * 
	 * @return the value of t_achieve.f_beizhu
	 */
	public String getBeizhu() {
		return beizhu;
	}

	/**
	 * 填写子分类备注 t_achieve.f_beizhu
	 * 
	 * @param beizhu
	 *            the value for t_achieve.f_beizhu
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	/**
	 * 图标编号 t_achieve.f_ico
	 * 
	 * @return the value of t_achieve.f_ico
	 */
	public Integer getIco() {
		return ico;
	}

	/**
	 * 图标编号 t_achieve.f_ico
	 * 
	 * @param ico
	 *            the value for t_achieve.f_ico
	 */
	public void setIco(Integer ico) {
		this.ico = ico;
	}

	/**
	 * 奖励礼包 t_achieve.f_good_id
	 * 
	 * @return the value of t_achieve.f_good_id
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 奖励礼包 t_achieve.f_good_id
	 * 
	 * @param goodId
	 *            the value for t_achieve.f_good_id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 是否公告 0不公告/1公告 t_achieve.f_isnotice
	 * 
	 * @return the value of t_achieve.f_isnotice
	 */
	public Integer getIsnotice() {
		return isnotice;
	}

	/**
	 * 是否公告 0不公告/1公告 t_achieve.f_isnotice
	 * 
	 * @param isnotice
	 *            the value for t_achieve.f_isnotice
	 */
	public void setIsnotice(Integer isnotice) {
		this.isnotice = isnotice;
	}

	/**
	 * 成就名称 t_achieve.f_name
	 * 
	 * @return the value of t_achieve.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 成就名称 t_achieve.f_name
	 * 
	 * @param name
	 *            the value for t_achieve.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 0击杀怪物 ，2任务 ，3装备 ，4坐骑， 5活动，6副本，7社交（好友，帮会, 组队）,8玩家死亡计数，9其他（连斩，武功，声望，静脉，等级，跳跃） 程序模块分类 t_achieve.f_model_type
	 * 
	 * @return the value of t_achieve.f_model_type
	 */
	public Integer getModelType() {
		return modelType;
	}

	/**
	 * 0击杀怪物 ，2任务 ，3装备 ，4坐骑， 5活动，6副本，7社交（好友，帮会, 组队）,8玩家死亡计数，9其他（连斩，武功，声望，静脉，等级，跳跃） 程序模块分类 t_achieve.f_model_type
	 * 
	 * @param modelType
	 *            the value for t_achieve.f_model_type
	 */
	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	/**
	 * 称号name国际化 t_achieve.f_title_i18n
	 * 
	 * @return the value of t_achieve.f_title_i18n
	 */
	public String getTitleI18n() {
		return titleI18n;
	}

	/**
	 * 称号name国际化 t_achieve.f_title_i18n
	 * 
	 * @param titleI18n
	 *            the value for t_achieve.f_title_i18n
	 */
	public void setTitleI18n(String titleI18n) {
		this.titleI18n = titleI18n;
	}

	/**
	 * 成就描述国际化 t_achieve.f_desc_i18n
	 * 
	 * @return the value of t_achieve.f_desc_i18n
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 成就描述国际化 t_achieve.f_desc_i18n
	 * 
	 * @param descI18n
	 *            the value for t_achieve.f_desc_i18n
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 填写子分类备注国际化 t_achieve.f_beizhu_i18n
	 * 
	 * @return the value of t_achieve.f_beizhu_i18n
	 */
	public String getBeizhuI18n() {
		return beizhuI18n;
	}

	/**
	 * 填写子分类备注国际化 t_achieve.f_beizhu_i18n
	 * 
	 * @param beizhuI18n
	 *            the value for t_achieve.f_beizhu_i18n
	 */
	public void setBeizhuI18n(String beizhuI18n) {
		this.beizhuI18n = beizhuI18n;
	}

	/**
	 * 成就名称国际化 t_achieve.f_name_i18n
	 * 
	 * @return the value of t_achieve.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 成就名称国际化 t_achieve.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_achieve.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	private CharacterGoods cg;

	public void initGoods() {
		if (goodId == null) {
			return;
		}
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm == null) {
			this.cg = null;
		} else {
			this.cg = CharacterGoods.createCharacterGoods(1, gm, 0, 0);
			cg.setBind(CommonUseNumber.byte1);
		}
	}

	public void setJiangLiGoods(CharacterGoods goods) {
		cg = goods;
		if (cg == null) {
			return;
		}
		cg.setBind(CommonUseNumber.byte1);
	}

	public CharacterGoods getJianglIGoods() {
		return cg;
	}
}
