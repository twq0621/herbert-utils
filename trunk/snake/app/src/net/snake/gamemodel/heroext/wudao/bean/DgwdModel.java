package net.snake.gamemodel.heroext.wudao.bean;

import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.ibatis.IbatisEntity;


public class DgwdModel implements PropertyChangeListener ,IbatisEntity{
    /**
	 * 编号 t_dgwd.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 等级名称 t_dgwd.f_name
	 * 
	 */
	private String name;
	/**
	 * t_dgwd.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 打座特效ID t_dgwd.f_dzeffect_id
	 * 
	 */
	private Integer dzeffectId;
	/**
	 * 境界ICO t_dgwd.f_ico_id
	 * 
	 */
	private Integer icoId;
	/**
	 * 领悟难易度 t_dgwd.f_degree
	 * 
	 */
	private String degree;
	/**
	 * 领悟难易度星级 t_dgwd.f_degree_xj
	 * 
	 */
	private String degreeXj;
	/**
	 * 奖励丰厚度 t_dgwd.f_rich_level
	 * 
	 */
	private String richLevel;
	/**
	 * 主面板描述 t_dgwd.f_main_desc
	 * 
	 */
	private String mainDesc;
	/**
	 * t_dgwd.f_main_desc_i18n
	 * 
	 */
	private String mainDescI18n;
	/**
	 * 次级面板描述 t_dgwd.f_sub_desc
	 * 
	 */
	private String subDesc;
	/**
	 * t_dgwd.f_sub_desc_i18n
	 * 
	 */
	private String subDescI18n;
	/**
	 * 增加攻击 t_dgwd.f_addattack
	 * 
	 */
	private Integer addattack;
	/**
	 * 显示增加攻击 t_dgwd.f_addattack_show
	 * 
	 */
	private Integer addattackShow;
	/**
	 * 增加防御 t_dgwd.f_adddefence
	 * 
	 */
	private Integer adddefence;
	/**
	 * 显示增加防御 t_dgwd.f_adddefence_show
	 * 
	 */
	private Integer adddefenceShow;
	/**
	 * 增加HP上限 t_dgwd.f_addmaxhp
	 * 
	 */
	private Integer addmaxhp;
	/**
	 * 显示增加HP上限 t_dgwd.f_addmaxhp_show
	 * 
	 */
	private Integer addmaxhpShow;
	/**
	 * 增加内力 t_dgwd.f_addmp
	 * 
	 */
	private Integer addmp;
	/**
	 * 显示增加内力 t_dgwd.f_addmp_show
	 * 
	 */
	private Integer addmpShow;
	/**
	 * 增加体力 t_dgwd.f_addsp
	 * 
	 */
	private Integer addsp;
	/**
	 * 显示增加体力 t_dgwd.f_addsp_show
	 * 
	 */
	private Integer addspShow;
	/**
	 * 增加特殊BUFFID t_dgwd.f_specialeffect_id
	 * 
	 */
	private Integer specialeffectId;
	/**
	 * 祝福值上限 t_dgwd.f_zhufu_max
	 * 
	 */
	private Integer zhufuMax;
	/**
	 * 败失祝福最小 t_dgwd.f_faild_zhufu_min
	 * 
	 */
	private Integer faildZhufuMin;
	/**
	 * 失败祝福最大 t_dgwd.f_faild_zhufu_max
	 * 
	 */
	private Integer faildZhufuMax;
	/**
	 * 消耗物品ID t_dgwd.f_consume_goodid
	 * 
	 */
	private Integer consumeGoodid;
	/**
	 * 耗消物品数量 t_dgwd.f_consume_num
	 * 
	 */
	private Integer consumeNum;
	/**
	 * 耗消真气 t_dgwd.f_consume_zq
	 * 
	 */
	private Integer consumeZq;
	/**
	 * 升阶成功率 t_dgwd.f_upprob
	 * 
	 */
	private Integer upprob;
	/**
	 * 显示升阶成功率 t_dgwd.f_upprob_show
	 * 
	 */
	private Integer upprobShow;
	/**
	 * 最小失败次数 t_dgwd.f_need_minfaild
	 * 
	 */
	private Integer needMinfaild;
	/**
	 * 最大失败次数 t_dgwd.f_need_maxfaild
	 * 
	 */
	private Integer needMaxfaild;
	/**
	 * 降低其他各门派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_allcrt
	 * 
	 */
	private Integer reduceAllcrt;
	/**
	 * 降低少林派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_slcrt
	 * 
	 */
	private Integer reduceSlcrt;
	/**
	 * 降低武当派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_wdcrt
	 * 
	 */
	private Integer reduceWdcrt;
	/**
	 * 降低桃花岛玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_thdcrt
	 * 
	 */
	private Integer reduceThdcrt;
	/**
	 * 降低古墓派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_gmcrt
	 * 
	 */
	private Integer reduceGmcrt;
	/**
	 * 降低白驼山玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_btscrt
	 * 
	 */
	private Integer reduceBtscrt;
	/**
	 * 降低您受到其他玩家暗器技能攻击几率(1/1000) t_dgwd.f_reduce_aqJv
	 * 
	 */
	private Integer reduceAqjv;
	/**
	 * 降低您受到其他玩家暗器伤害几率(1/1000) t_dgwd.f_reduce_hwhurt
	 * 
	 */
	private Integer reduceHwhurt;
	/**
	 * 降低您受到其他玩家弓箭箭术技能攻击几率(1/1000) t_dgwd.f_reduce_bow
	 * 
	 */
	private Integer reduceBow;
	/**
	 * 降低您受到其他玩家丹田武学的攻击几率(1/1000) t_dgwd.f_reduce_dantian
	 * 
	 */
	private Integer reduceDantian;

	/**
	 * 编号 t_dgwd.f_id
	 * @return  the value of t_dgwd.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 编号 t_dgwd.f_id
	 * @param id  the value for t_dgwd.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 等级名称 t_dgwd.f_name
	 * @return  the value of t_dgwd.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 等级名称 t_dgwd.f_name
	 * @param name  the value for t_dgwd.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * t_dgwd.f_name_i18n
	 * @return  the value of t_dgwd.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * t_dgwd.f_name_i18n
	 * @param nameI18n  the value for t_dgwd.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 打座特效ID t_dgwd.f_dzeffect_id
	 * @return  the value of t_dgwd.f_dzeffect_id
	 * 
	 */
	public Integer getDzeffectId() {
		return dzeffectId;
	}

	/**
	 * 打座特效ID t_dgwd.f_dzeffect_id
	 * @param dzeffectId  the value for t_dgwd.f_dzeffect_id
	 * 
	 */
	public void setDzeffectId(Integer dzeffectId) {
		this.dzeffectId = dzeffectId;
	}

	/**
	 * 境界ICO t_dgwd.f_ico_id
	 * @return  the value of t_dgwd.f_ico_id
	 * 
	 */
	public Integer getIcoId() {
		return icoId;
	}

	/**
	 * 境界ICO t_dgwd.f_ico_id
	 * @param icoId  the value for t_dgwd.f_ico_id
	 * 
	 */
	public void setIcoId(Integer icoId) {
		this.icoId = icoId;
	}

	/**
	 * 领悟难易度 t_dgwd.f_degree
	 * @return  the value of t_dgwd.f_degree
	 * 
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * 领悟难易度 t_dgwd.f_degree
	 * @param degree  the value for t_dgwd.f_degree
	 * 
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * 领悟难易度星级 t_dgwd.f_degree_xj
	 * @return  the value of t_dgwd.f_degree_xj
	 * 
	 */
	public String getDegreeXj() {
		return degreeXj;
	}

	/**
	 * 领悟难易度星级 t_dgwd.f_degree_xj
	 * @param degreeXj  the value for t_dgwd.f_degree_xj
	 * 
	 */
	public void setDegreeXj(String degreeXj) {
		this.degreeXj = degreeXj;
	}

	/**
	 * 奖励丰厚度 t_dgwd.f_rich_level
	 * @return  the value of t_dgwd.f_rich_level
	 * 
	 */
	public String getRichLevel() {
		return richLevel;
	}

	/**
	 * 奖励丰厚度 t_dgwd.f_rich_level
	 * @param richLevel  the value for t_dgwd.f_rich_level
	 * 
	 */
	public void setRichLevel(String richLevel) {
		this.richLevel = richLevel;
	}

	/**
	 * 主面板描述 t_dgwd.f_main_desc
	 * @return  the value of t_dgwd.f_main_desc
	 * 
	 */
	public String getMainDesc() {
		return mainDesc;
	}

	/**
	 * 主面板描述 t_dgwd.f_main_desc
	 * @param mainDesc  the value for t_dgwd.f_main_desc
	 * 
	 */
	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
	}

	/**
	 * t_dgwd.f_main_desc_i18n
	 * @return  the value of t_dgwd.f_main_desc_i18n
	 * 
	 */
	public String getMainDescI18n() {
		return mainDescI18n;
	}

	/**
	 * t_dgwd.f_main_desc_i18n
	 * @param mainDescI18n  the value for t_dgwd.f_main_desc_i18n
	 * 
	 */
	public void setMainDescI18n(String mainDescI18n) {
		this.mainDescI18n = mainDescI18n;
	}

	/**
	 * 次级面板描述 t_dgwd.f_sub_desc
	 * @return  the value of t_dgwd.f_sub_desc
	 * 
	 */
	public String getSubDesc() {
		return subDesc;
	}

	/**
	 * 次级面板描述 t_dgwd.f_sub_desc
	 * @param subDesc  the value for t_dgwd.f_sub_desc
	 * 
	 */
	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}

	/**
	 * t_dgwd.f_sub_desc_i18n
	 * @return  the value of t_dgwd.f_sub_desc_i18n
	 * 
	 */
	public String getSubDescI18n() {
		return subDescI18n;
	}

	/**
	 * t_dgwd.f_sub_desc_i18n
	 * @param subDescI18n  the value for t_dgwd.f_sub_desc_i18n
	 * 
	 */
	public void setSubDescI18n(String subDescI18n) {
		this.subDescI18n = subDescI18n;
	}

	/**
	 * 增加攻击 t_dgwd.f_addattack
	 * @return  the value of t_dgwd.f_addattack
	 * 
	 */
	public Integer getAddattack() {
		return addattack;
	}

	/**
	 * 增加攻击 t_dgwd.f_addattack
	 * @param addattack  the value for t_dgwd.f_addattack
	 * 
	 */
	public void setAddattack(Integer addattack) {
		this.addattack = addattack;
	}

	/**
	 * 显示增加攻击 t_dgwd.f_addattack_show
	 * @return  the value of t_dgwd.f_addattack_show
	 * 
	 */
	public Integer getAddattackShow() {
		return addattackShow;
	}

	/**
	 * 显示增加攻击 t_dgwd.f_addattack_show
	 * @param addattackShow  the value for t_dgwd.f_addattack_show
	 * 
	 */
	public void setAddattackShow(Integer addattackShow) {
		this.addattackShow = addattackShow;
	}

	/**
	 * 增加防御 t_dgwd.f_adddefence
	 * @return  the value of t_dgwd.f_adddefence
	 * 
	 */
	public Integer getAdddefence() {
		return adddefence;
	}

	/**
	 * 增加防御 t_dgwd.f_adddefence
	 * @param adddefence  the value for t_dgwd.f_adddefence
	 * 
	 */
	public void setAdddefence(Integer adddefence) {
		this.adddefence = adddefence;
	}

	/**
	 * 显示增加防御 t_dgwd.f_adddefence_show
	 * @return  the value of t_dgwd.f_adddefence_show
	 * 
	 */
	public Integer getAdddefenceShow() {
		return adddefenceShow;
	}

	/**
	 * 显示增加防御 t_dgwd.f_adddefence_show
	 * @param adddefenceShow  the value for t_dgwd.f_adddefence_show
	 * 
	 */
	public void setAdddefenceShow(Integer adddefenceShow) {
		this.adddefenceShow = adddefenceShow;
	}

	/**
	 * 增加HP上限 t_dgwd.f_addmaxhp
	 * @return  the value of t_dgwd.f_addmaxhp
	 * 
	 */
	public Integer getAddmaxhp() {
		return addmaxhp;
	}

	/**
	 * 增加HP上限 t_dgwd.f_addmaxhp
	 * @param addmaxhp  the value for t_dgwd.f_addmaxhp
	 * 
	 */
	public void setAddmaxhp(Integer addmaxhp) {
		this.addmaxhp = addmaxhp;
	}

	/**
	 * 显示增加HP上限 t_dgwd.f_addmaxhp_show
	 * @return  the value of t_dgwd.f_addmaxhp_show
	 * 
	 */
	public Integer getAddmaxhpShow() {
		return addmaxhpShow;
	}

	/**
	 * 显示增加HP上限 t_dgwd.f_addmaxhp_show
	 * @param addmaxhpShow  the value for t_dgwd.f_addmaxhp_show
	 * 
	 */
	public void setAddmaxhpShow(Integer addmaxhpShow) {
		this.addmaxhpShow = addmaxhpShow;
	}

	/**
	 * 增加内力 t_dgwd.f_addmp
	 * @return  the value of t_dgwd.f_addmp
	 * 
	 */
	public Integer getAddmp() {
		return addmp;
	}

	/**
	 * 增加内力 t_dgwd.f_addmp
	 * @param addmp  the value for t_dgwd.f_addmp
	 * 
	 */
	public void setAddmp(Integer addmp) {
		this.addmp = addmp;
	}

	/**
	 * 显示增加内力 t_dgwd.f_addmp_show
	 * @return  the value of t_dgwd.f_addmp_show
	 * 
	 */
	public Integer getAddmpShow() {
		return addmpShow;
	}

	/**
	 * 显示增加内力 t_dgwd.f_addmp_show
	 * @param addmpShow  the value for t_dgwd.f_addmp_show
	 * 
	 */
	public void setAddmpShow(Integer addmpShow) {
		this.addmpShow = addmpShow;
	}

	/**
	 * 增加体力 t_dgwd.f_addsp
	 * @return  the value of t_dgwd.f_addsp
	 * 
	 */
	public Integer getAddsp() {
		return addsp;
	}

	/**
	 * 增加体力 t_dgwd.f_addsp
	 * @param addsp  the value for t_dgwd.f_addsp
	 * 
	 */
	public void setAddsp(Integer addsp) {
		this.addsp = addsp;
	}

	/**
	 * 显示增加体力 t_dgwd.f_addsp_show
	 * @return  the value of t_dgwd.f_addsp_show
	 * 
	 */
	public Integer getAddspShow() {
		return addspShow;
	}

	/**
	 * 显示增加体力 t_dgwd.f_addsp_show
	 * @param addspShow  the value for t_dgwd.f_addsp_show
	 * 
	 */
	public void setAddspShow(Integer addspShow) {
		this.addspShow = addspShow;
	}

	/**
	 * 增加特殊BUFFID t_dgwd.f_specialeffect_id
	 * @return  the value of t_dgwd.f_specialeffect_id
	 * 
	 */
	public Integer getSpecialeffectId() {
		return specialeffectId;
	}

	/**
	 * 增加特殊BUFFID t_dgwd.f_specialeffect_id
	 * @param specialeffectId  the value for t_dgwd.f_specialeffect_id
	 * 
	 */
	public void setSpecialeffectId(Integer specialeffectId) {
		this.specialeffectId = specialeffectId;
	}

	/**
	 * 祝福值上限 t_dgwd.f_zhufu_max
	 * @return  the value of t_dgwd.f_zhufu_max
	 * 
	 */
	public Integer getZhufuMax() {
		return zhufuMax;
	}

	/**
	 * 祝福值上限 t_dgwd.f_zhufu_max
	 * @param zhufuMax  the value for t_dgwd.f_zhufu_max
	 * 
	 */
	public void setZhufuMax(Integer zhufuMax) {
		this.zhufuMax = zhufuMax;
	}

	/**
	 * 败失祝福最小 t_dgwd.f_faild_zhufu_min
	 * @return  the value of t_dgwd.f_faild_zhufu_min
	 * 
	 */
	public Integer getFaildZhufuMin() {
		return faildZhufuMin;
	}

	/**
	 * 败失祝福最小 t_dgwd.f_faild_zhufu_min
	 * @param faildZhufuMin  the value for t_dgwd.f_faild_zhufu_min
	 * 
	 */
	public void setFaildZhufuMin(Integer faildZhufuMin) {
		this.faildZhufuMin = faildZhufuMin;
	}

	/**
	 * 失败祝福最大 t_dgwd.f_faild_zhufu_max
	 * @return  the value of t_dgwd.f_faild_zhufu_max
	 * 
	 */
	public Integer getFaildZhufuMax() {
		return faildZhufuMax;
	}

	/**
	 * 失败祝福最大 t_dgwd.f_faild_zhufu_max
	 * @param faildZhufuMax  the value for t_dgwd.f_faild_zhufu_max
	 * 
	 */
	public void setFaildZhufuMax(Integer faildZhufuMax) {
		this.faildZhufuMax = faildZhufuMax;
	}

	/**
	 * 消耗物品ID t_dgwd.f_consume_goodid
	 * @return  the value of t_dgwd.f_consume_goodid
	 * 
	 */
	public Integer getConsumeGoodid() {
		return consumeGoodid;
	}

	/**
	 * 消耗物品ID t_dgwd.f_consume_goodid
	 * @param consumeGoodid  the value for t_dgwd.f_consume_goodid
	 * 
	 */
	public void setConsumeGoodid(Integer consumeGoodid) {
		this.consumeGoodid = consumeGoodid;
	}

	/**
	 * 耗消物品数量 t_dgwd.f_consume_num
	 * @return  the value of t_dgwd.f_consume_num
	 * 
	 */
	public Integer getConsumeNum() {
		return consumeNum;
	}

	/**
	 * 耗消物品数量 t_dgwd.f_consume_num
	 * @param consumeNum  the value for t_dgwd.f_consume_num
	 * 
	 */
	public void setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
	}

	/**
	 * 耗消真气 t_dgwd.f_consume_zq
	 * @return  the value of t_dgwd.f_consume_zq
	 * 
	 */
	public Integer getConsumeZq() {
		return consumeZq;
	}

	/**
	 * 耗消真气 t_dgwd.f_consume_zq
	 * @param consumeZq  the value for t_dgwd.f_consume_zq
	 * 
	 */
	public void setConsumeZq(Integer consumeZq) {
		this.consumeZq = consumeZq;
	}

	/**
	 * 升阶成功率 t_dgwd.f_upprob
	 * @return  the value of t_dgwd.f_upprob
	 * 
	 */
	public Integer getUpprob() {
		return upprob;
	}

	/**
	 * 升阶成功率 t_dgwd.f_upprob
	 * @param upprob  the value for t_dgwd.f_upprob
	 * 
	 */
	public void setUpprob(Integer upprob) {
		this.upprob = upprob;
	}

	/**
	 * 显示升阶成功率 t_dgwd.f_upprob_show
	 * @return  the value of t_dgwd.f_upprob_show
	 * 
	 */
	public Integer getUpprobShow() {
		return upprobShow;
	}

	/**
	 * 显示升阶成功率 t_dgwd.f_upprob_show
	 * @param upprobShow  the value for t_dgwd.f_upprob_show
	 * 
	 */
	public void setUpprobShow(Integer upprobShow) {
		this.upprobShow = upprobShow;
	}

	/**
	 * 最小失败次数 t_dgwd.f_need_minfaild
	 * @return  the value of t_dgwd.f_need_minfaild
	 * 
	 */
	public Integer getNeedMinfaild() {
		return needMinfaild;
	}

	/**
	 * 最小失败次数 t_dgwd.f_need_minfaild
	 * @param needMinfaild  the value for t_dgwd.f_need_minfaild
	 * 
	 */
	public void setNeedMinfaild(Integer needMinfaild) {
		this.needMinfaild = needMinfaild;
	}

	/**
	 * 最大失败次数 t_dgwd.f_need_maxfaild
	 * @return  the value of t_dgwd.f_need_maxfaild
	 * 
	 */
	public Integer getNeedMaxfaild() {
		return needMaxfaild;
	}

	/**
	 * 最大失败次数 t_dgwd.f_need_maxfaild
	 * @param needMaxfaild  the value for t_dgwd.f_need_maxfaild
	 * 
	 */
	public void setNeedMaxfaild(Integer needMaxfaild) {
		this.needMaxfaild = needMaxfaild;
	}

	/**
	 * 降低其他各门派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_allcrt
	 * @return  the value of t_dgwd.f_reduce_allcrt
	 * 
	 */
	public Integer getReduceAllcrt() {
		return reduceAllcrt;
	}

	/**
	 * 降低其他各门派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_allcrt
	 * @param reduceAllcrt  the value for t_dgwd.f_reduce_allcrt
	 * 
	 */
	public void setReduceAllcrt(Integer reduceAllcrt) {
		this.reduceAllcrt = reduceAllcrt;
	}

	/**
	 * 降低少林派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_slcrt
	 * @return  the value of t_dgwd.f_reduce_slcrt
	 * 
	 */
	public Integer getReduceSlcrt() {
		return reduceSlcrt;
	}

	/**
	 * 降低少林派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_slcrt
	 * @param reduceSlcrt  the value for t_dgwd.f_reduce_slcrt
	 * 
	 */
	public void setReduceSlcrt(Integer reduceSlcrt) {
		this.reduceSlcrt = reduceSlcrt;
	}

	/**
	 * 降低武当派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_wdcrt
	 * @return  the value of t_dgwd.f_reduce_wdcrt
	 * 
	 */
	public Integer getReduceWdcrt() {
		return reduceWdcrt;
	}

	/**
	 * 降低武当派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_wdcrt
	 * @param reduceWdcrt  the value for t_dgwd.f_reduce_wdcrt
	 * 
	 */
	public void setReduceWdcrt(Integer reduceWdcrt) {
		this.reduceWdcrt = reduceWdcrt;
	}

	/**
	 * 降低桃花岛玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_thdcrt
	 * @return  the value of t_dgwd.f_reduce_thdcrt
	 * 
	 */
	public Integer getReduceThdcrt() {
		return reduceThdcrt;
	}

	/**
	 * 降低桃花岛玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_thdcrt
	 * @param reduceThdcrt  the value for t_dgwd.f_reduce_thdcrt
	 * 
	 */
	public void setReduceThdcrt(Integer reduceThdcrt) {
		this.reduceThdcrt = reduceThdcrt;
	}

	/**
	 * 降低古墓派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_gmcrt
	 * @return  the value of t_dgwd.f_reduce_gmcrt
	 * 
	 */
	public Integer getReduceGmcrt() {
		return reduceGmcrt;
	}

	/**
	 * 降低古墓派玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_gmcrt
	 * @param reduceGmcrt  the value for t_dgwd.f_reduce_gmcrt
	 * 
	 */
	public void setReduceGmcrt(Integer reduceGmcrt) {
		this.reduceGmcrt = reduceGmcrt;
	}

	/**
	 * 降低白驼山玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_btscrt
	 * @return  the value of t_dgwd.f_reduce_btscrt
	 * 
	 */
	public Integer getReduceBtscrt() {
		return reduceBtscrt;
	}

	/**
	 * 降低白驼山玩家对您产生爆击伤害(1/1000) t_dgwd.f_reduce_btscrt
	 * @param reduceBtscrt  the value for t_dgwd.f_reduce_btscrt
	 * 
	 */
	public void setReduceBtscrt(Integer reduceBtscrt) {
		this.reduceBtscrt = reduceBtscrt;
	}

	/**
	 * 降低您受到其他玩家暗器技能攻击几率(1/1000) t_dgwd.f_reduce_aqJv
	 * @return  the value of t_dgwd.f_reduce_aqJv
	 * 
	 */
	public Integer getReduceAqjv() {
		return reduceAqjv;
	}

	/**
	 * 降低您受到其他玩家暗器技能攻击几率(1/1000) t_dgwd.f_reduce_aqJv
	 * @param reduceAqjv  the value for t_dgwd.f_reduce_aqJv
	 * 
	 */
	public void setReduceAqjv(Integer reduceAqjv) {
		this.reduceAqjv = reduceAqjv;
	}

	/**
	 * 降低您受到其他玩家暗器伤害几率(1/1000) t_dgwd.f_reduce_hwhurt
	 * @return  the value of t_dgwd.f_reduce_hwhurt
	 * 
	 */
	public Integer getReduceHwhurt() {
		return reduceHwhurt;
	}

	/**
	 * 降低您受到其他玩家暗器伤害几率(1/1000) t_dgwd.f_reduce_hwhurt
	 * @param reduceHwhurt  the value for t_dgwd.f_reduce_hwhurt
	 * 
	 */
	public void setReduceHwhurt(Integer reduceHwhurt) {
		this.reduceHwhurt = reduceHwhurt;
	}

	/**
	 * 降低您受到其他玩家弓箭箭术技能攻击几率(1/1000) t_dgwd.f_reduce_bow
	 * @return  the value of t_dgwd.f_reduce_bow
	 * 
	 */
	public Integer getReduceBow() {
		return reduceBow;
	}

	/**
	 * 降低您受到其他玩家弓箭箭术技能攻击几率(1/1000) t_dgwd.f_reduce_bow
	 * @param reduceBow  the value for t_dgwd.f_reduce_bow
	 * 
	 */
	public void setReduceBow(Integer reduceBow) {
		this.reduceBow = reduceBow;
	}

	/**
	 * 降低您受到其他玩家丹田武学的攻击几率(1/1000) t_dgwd.f_reduce_dantian
	 * @return  the value of t_dgwd.f_reduce_dantian
	 * 
	 */
	public Integer getReduceDantian() {
		return reduceDantian;
	}

	/**
	 * 降低您受到其他玩家丹田武学的攻击几率(1/1000) t_dgwd.f_reduce_dantian
	 * @param reduceDantian  the value for t_dgwd.f_reduce_dantian
	 * 
	 */
	public void setReduceDantian(Integer reduceDantian) {
		this.reduceDantian = reduceDantian;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.dugujiujian;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe= new PropertyEntity();		
		pe.addExtraProperty(Property.attack,getAddattack());
		pe.addExtraProperty(Property.defence,getAdddefence());
	    pe.addExtraProperty(Property.maxHp,getAddmaxhp());
	    pe.addExtraProperty(Property.maxMp,getAddmp());
	    pe.addExtraProperty(Property.maxSp,getAddsp());
	    
	    pe.addExtraProperty(Property.all_crt,getReduceAllcrt());
	    pe.addExtraProperty(Property.shaolin_crt, getReduceSlcrt());
	    pe.addExtraProperty(Property.wudan_crt, getReduceWdcrt());
	    pe.addExtraProperty(Property.gumu_crt, getReduceGmcrt());
	    pe.addExtraProperty(Property.taohuadao_crt,getReduceThdcrt());
	    pe.addExtraProperty(Property.baituoshan_crt, getReduceBtscrt());
	    pe.addExtraProperty(Property.reduce_aqHurt,getReduceHwhurt());
	    pe.addExtraProperty(Property.reduce_aqJv,getReduceAqjv());
	    pe.addExtraProperty(Property.reduce_dantian,getReduceDantian());
	    pe.addExtraProperty(Property.reduce_gong,getReduceBow());
		return pe;
	}
}
