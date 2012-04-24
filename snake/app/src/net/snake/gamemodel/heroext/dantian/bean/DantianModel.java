package net.snake.gamemodel.heroext.dantian.bean;

import net.snake.ibatis.IbatisEntity;

public class DantianModel  implements IbatisEntity{

	/**
	 * 编号 t_dantian.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 修练等级 t_dantian.f_name
	 * 
	 */
	private String name;
	/**
	 * t_dantian.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 少林造型ID t_dantian.f_shaolin_resid
	 * 
	 */
	private Integer shaolinResid;
	/**
	 * 全真造型ID t_dantian.f_quanzhen_resid
	 * 
	 */
	private Integer quanzhenResid;
	/**
	 * 古墓造型ID t_dantian.f_gumu_resid
	 * 
	 */
	private Integer gumuResid;
	/**
	 * 嵋峨造型ID t_dantian.f_emei_resid
	 * 
	 */
	private Integer emeiResid;
	/**
	 * 增加攻击 t_dantian.f_add_attack
	 * 
	 */
	private Integer addAttack;
	/**
	 * 加增防御 t_dantian.f_add_defence
	 * 
	 */
	private Integer addDefence;
	/**
	 * 加增生命上限 t_dantian.f_add_maxhp
	 * 
	 */
	private Integer addMaxhp;
	/**
	 * 增加爆击 t_dantian.f_add_crt
	 * 
	 */
	private Integer addCrt;
	/**
	 * 增加闪避 t_dantian.f_add_dodge
	 * 
	 */
	private Integer addDodge;
	/**
	 * 增加门派武学层数 t_dantian.f_add_mengpai_wuxue
	 * 
	 */
	private Integer addMengpaiWuxue;
	/**
	 * 门派武学技能ID序列 t_dantian.f_mengpai_skillitem
	 * 
	 */
	private String mengpaiSkillitem;
	/**
	 * 增加江湖绝学层数 t_dantian.f_add_jianghu_wuxue
	 * 
	 */
	private Integer addJianghuWuxue;
	/**
	 * 江湖绝学技能ID序列 t_dantian.f_jianhu_skillitem
	 * 
	 */
	private String jianhuSkillitem;
	/**
	 * 增加其他绝学层数 t_dantian.f_add_qita_juexue
	 * 
	 */
	private Integer addQitaJuexue;
	/**
	 * 其他绝学技能ID序列 t_dantian.f_qita_skillitem
	 * 
	 */
	private String qitaSkillitem;
	/**
	 * 可激活的技能ID() t_dantian.f_canlean_skillitem
	 * 
	 */
	private String canleanSkillitem;
	/**
	 * 本阶祝福值上限 t_dantian.f_max_zhufu
	 * 
	 */
	private Integer maxZhufu;
	/**
	 * 突破失败所加成的祝福值min t_dantian.f_upfaild_zhufu_min
	 * 
	 */
	private Integer upfaildZhufuMin;
	/**
	 * 突破失败所加成的祝福值max t_dantian.f_upfaild_zhufu_max
	 * 
	 */
	private Integer upfaildZhufuMax;
	/**
	 * 升阶合成消耗物品ID t_dantian.f_up_needgoodid
	 * 
	 */
	private Integer upNeedgoodid;
	/**
	 * 升阶合成消耗物品数量 t_dantian.f_up_needgoodnum
	 * 
	 */
	private Integer upNeedgoodnum;
	/**
	 * 升阶合成计算用成功几率 t_dantian.f_up_probability
	 * 
	 */
	private Integer upProbability;
	/**
	 * 升阶合成显示用成功几率 t_dantian.f_up_probability_show
	 * 
	 */
	private Integer upProbabilityShow;
	/**
	 * 升阶合成所需最小成功次数 t_dantian.f_up_need_min_faild
	 * 
	 */
	private Integer upNeedMinFaild;
	/**
	 * 升阶合成所需最大成功次数 t_dantian.f_up_need_max_faild
	 * 
	 */
	private Integer upNeedMaxFaild;

	/**
	 * 编号 t_dantian.f_id
	 * @return  the value of t_dantian.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 编号 t_dantian.f_id
	 * @param id  the value for t_dantian.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 修练等级 t_dantian.f_name
	 * @return  the value of t_dantian.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 修练等级 t_dantian.f_name
	 * @param name  the value for t_dantian.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * t_dantian.f_name_i18n
	 * @return  the value of t_dantian.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * t_dantian.f_name_i18n
	 * @param nameI18n  the value for t_dantian.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 少林造型ID t_dantian.f_shaolin_resid
	 * @return  the value of t_dantian.f_shaolin_resid
	 * 
	 */
	public Integer getShaolinResid() {
		return shaolinResid;
	}

	/**
	 * 少林造型ID t_dantian.f_shaolin_resid
	 * @param shaolinResid  the value for t_dantian.f_shaolin_resid
	 * 
	 */
	public void setShaolinResid(Integer shaolinResid) {
		this.shaolinResid = shaolinResid;
	}

	/**
	 * 全真造型ID t_dantian.f_quanzhen_resid
	 * @return  the value of t_dantian.f_quanzhen_resid
	 * 
	 */
	public Integer getQuanzhenResid() {
		return quanzhenResid;
	}

	/**
	 * 全真造型ID t_dantian.f_quanzhen_resid
	 * @param quanzhenResid  the value for t_dantian.f_quanzhen_resid
	 * 
	 */
	public void setQuanzhenResid(Integer quanzhenResid) {
		this.quanzhenResid = quanzhenResid;
	}

	/**
	 * 古墓造型ID t_dantian.f_gumu_resid
	 * @return  the value of t_dantian.f_gumu_resid
	 * 
	 */
	public Integer getGumuResid() {
		return gumuResid;
	}

	/**
	 * 古墓造型ID t_dantian.f_gumu_resid
	 * @param gumuResid  the value for t_dantian.f_gumu_resid
	 * 
	 */
	public void setGumuResid(Integer gumuResid) {
		this.gumuResid = gumuResid;
	}

	/**
	 * 嵋峨造型ID t_dantian.f_emei_resid
	 * @return  the value of t_dantian.f_emei_resid
	 * 
	 */
	public Integer getEmeiResid() {
		return emeiResid;
	}

	/**
	 * 嵋峨造型ID t_dantian.f_emei_resid
	 * @param emeiResid  the value for t_dantian.f_emei_resid
	 * 
	 */
	public void setEmeiResid(Integer emeiResid) {
		this.emeiResid = emeiResid;
	}

	/**
	 * 增加攻击 t_dantian.f_add_attack
	 * @return  the value of t_dantian.f_add_attack
	 * 
	 */
	public Integer getAddAttack() {
		return addAttack;
	}

	/**
	 * 增加攻击 t_dantian.f_add_attack
	 * @param addAttack  the value for t_dantian.f_add_attack
	 * 
	 */
	public void setAddAttack(Integer addAttack) {
		this.addAttack = addAttack;
	}

	/**
	 * 加增防御 t_dantian.f_add_defence
	 * @return  the value of t_dantian.f_add_defence
	 * 
	 */
	public Integer getAddDefence() {
		return addDefence;
	}

	/**
	 * 加增防御 t_dantian.f_add_defence
	 * @param addDefence  the value for t_dantian.f_add_defence
	 * 
	 */
	public void setAddDefence(Integer addDefence) {
		this.addDefence = addDefence;
	}

	/**
	 * 加增生命上限 t_dantian.f_add_maxhp
	 * @return  the value of t_dantian.f_add_maxhp
	 * 
	 */
	public Integer getAddMaxhp() {
		return addMaxhp;
	}

	/**
	 * 加增生命上限 t_dantian.f_add_maxhp
	 * @param addMaxhp  the value for t_dantian.f_add_maxhp
	 * 
	 */
	public void setAddMaxhp(Integer addMaxhp) {
		this.addMaxhp = addMaxhp;
	}

	/**
	 * 增加爆击 t_dantian.f_add_crt
	 * @return  the value of t_dantian.f_add_crt
	 * 
	 */
	public Integer getAddCrt() {
		return addCrt;
	}

	/**
	 * 增加爆击 t_dantian.f_add_crt
	 * @param addCrt  the value for t_dantian.f_add_crt
	 * 
	 */
	public void setAddCrt(Integer addCrt) {
		this.addCrt = addCrt;
	}

	/**
	 * 增加闪避 t_dantian.f_add_dodge
	 * @return  the value of t_dantian.f_add_dodge
	 * 
	 */
	public Integer getAddDodge() {
		return addDodge;
	}

	/**
	 * 增加闪避 t_dantian.f_add_dodge
	 * @param addDodge  the value for t_dantian.f_add_dodge
	 * 
	 */
	public void setAddDodge(Integer addDodge) {
		this.addDodge = addDodge;
	}

	/**
	 * 增加门派武学层数 t_dantian.f_add_mengpai_wuxue
	 * @return  the value of t_dantian.f_add_mengpai_wuxue
	 * 
	 */
	public Integer getAddMengpaiWuxue() {
		return addMengpaiWuxue;
	}

	/**
	 * 增加门派武学层数 t_dantian.f_add_mengpai_wuxue
	 * @param addMengpaiWuxue  the value for t_dantian.f_add_mengpai_wuxue
	 * 
	 */
	public void setAddMengpaiWuxue(Integer addMengpaiWuxue) {
		this.addMengpaiWuxue = addMengpaiWuxue;
	}

	/**
	 * 门派武学技能ID序列 t_dantian.f_mengpai_skillitem
	 * @return  the value of t_dantian.f_mengpai_skillitem
	 * 
	 */
	public String getMengpaiSkillitem() {
		return mengpaiSkillitem;
	}

	/**
	 * 门派武学技能ID序列 t_dantian.f_mengpai_skillitem
	 * @param mengpaiSkillitem  the value for t_dantian.f_mengpai_skillitem
	 * 
	 */
	public void setMengpaiSkillitem(String mengpaiSkillitem) {
		this.mengpaiSkillitem = mengpaiSkillitem;
	}

	/**
	 * 增加江湖绝学层数 t_dantian.f_add_jianghu_wuxue
	 * @return  the value of t_dantian.f_add_jianghu_wuxue
	 * 
	 */
	public Integer getAddJianghuWuxue() {
		return addJianghuWuxue;
	}

	/**
	 * 增加江湖绝学层数 t_dantian.f_add_jianghu_wuxue
	 * @param addJianghuWuxue  the value for t_dantian.f_add_jianghu_wuxue
	 * 
	 */
	public void setAddJianghuWuxue(Integer addJianghuWuxue) {
		this.addJianghuWuxue = addJianghuWuxue;
	}

	/**
	 * 江湖绝学技能ID序列 t_dantian.f_jianhu_skillitem
	 * @return  the value of t_dantian.f_jianhu_skillitem
	 * 
	 */
	public String getJianhuSkillitem() {
		return jianhuSkillitem;
	}

	/**
	 * 江湖绝学技能ID序列 t_dantian.f_jianhu_skillitem
	 * @param jianhuSkillitem  the value for t_dantian.f_jianhu_skillitem
	 * 
	 */
	public void setJianhuSkillitem(String jianhuSkillitem) {
		this.jianhuSkillitem = jianhuSkillitem;
	}

	/**
	 * 增加其他绝学层数 t_dantian.f_add_qita_juexue
	 * @return  the value of t_dantian.f_add_qita_juexue
	 * 
	 */
	public Integer getAddQitaJuexue() {
		return addQitaJuexue;
	}

	/**
	 * 增加其他绝学层数 t_dantian.f_add_qita_juexue
	 * @param addQitaJuexue  the value for t_dantian.f_add_qita_juexue
	 * 
	 */
	public void setAddQitaJuexue(Integer addQitaJuexue) {
		this.addQitaJuexue = addQitaJuexue;
	}

	/**
	 * 其他绝学技能ID序列 t_dantian.f_qita_skillitem
	 * @return  the value of t_dantian.f_qita_skillitem
	 * 
	 */
	public String getQitaSkillitem() {
		return qitaSkillitem;
	}

	/**
	 * 其他绝学技能ID序列 t_dantian.f_qita_skillitem
	 * @param qitaSkillitem  the value for t_dantian.f_qita_skillitem
	 * 
	 */
	public void setQitaSkillitem(String qitaSkillitem) {
		this.qitaSkillitem = qitaSkillitem;
	}

	/**
	 * 可激活的技能ID() t_dantian.f_canlean_skillitem
	 * @return  the value of t_dantian.f_canlean_skillitem
	 * 
	 */
	public String getCanleanSkillitem() {
		return canleanSkillitem;
	}

	/**
	 * 可激活的技能ID() t_dantian.f_canlean_skillitem
	 * @param canleanSkillitem  the value for t_dantian.f_canlean_skillitem
	 * 
	 */
	public void setCanleanSkillitem(String canleanSkillitem) {
		this.canleanSkillitem = canleanSkillitem;
	}

	/**
	 * 本阶祝福值上限 t_dantian.f_max_zhufu
	 * @return  the value of t_dantian.f_max_zhufu
	 * 
	 */
	public Integer getMaxZhufu() {
		return maxZhufu;
	}

	/**
	 * 本阶祝福值上限 t_dantian.f_max_zhufu
	 * @param maxZhufu  the value for t_dantian.f_max_zhufu
	 * 
	 */
	public void setMaxZhufu(Integer maxZhufu) {
		this.maxZhufu = maxZhufu;
	}

	/**
	 * 突破失败所加成的祝福值min t_dantian.f_upfaild_zhufu_min
	 * @return  the value of t_dantian.f_upfaild_zhufu_min
	 * 
	 */
	public Integer getUpfaildZhufuMin() {
		return upfaildZhufuMin;
	}

	/**
	 * 突破失败所加成的祝福值min t_dantian.f_upfaild_zhufu_min
	 * @param upfaildZhufuMin  the value for t_dantian.f_upfaild_zhufu_min
	 * 
	 */
	public void setUpfaildZhufuMin(Integer upfaildZhufuMin) {
		this.upfaildZhufuMin = upfaildZhufuMin;
	}

	/**
	 * 突破失败所加成的祝福值max t_dantian.f_upfaild_zhufu_max
	 * @return  the value of t_dantian.f_upfaild_zhufu_max
	 * 
	 */
	public Integer getUpfaildZhufuMax() {
		return upfaildZhufuMax;
	}

	/**
	 * 突破失败所加成的祝福值max t_dantian.f_upfaild_zhufu_max
	 * @param upfaildZhufuMax  the value for t_dantian.f_upfaild_zhufu_max
	 * 
	 */
	public void setUpfaildZhufuMax(Integer upfaildZhufuMax) {
		this.upfaildZhufuMax = upfaildZhufuMax;
	}

	/**
	 * 升阶合成消耗物品ID t_dantian.f_up_needgoodid
	 * @return  the value of t_dantian.f_up_needgoodid
	 * 
	 */
	public Integer getUpNeedgoodid() {
		return upNeedgoodid;
	}

	/**
	 * 升阶合成消耗物品ID t_dantian.f_up_needgoodid
	 * @param upNeedgoodid  the value for t_dantian.f_up_needgoodid
	 * 
	 */
	public void setUpNeedgoodid(Integer upNeedgoodid) {
		this.upNeedgoodid = upNeedgoodid;
	}

	/**
	 * 升阶合成消耗物品数量 t_dantian.f_up_needgoodnum
	 * @return  the value of t_dantian.f_up_needgoodnum
	 * 
	 */
	public Integer getUpNeedgoodnum() {
		return upNeedgoodnum;
	}

	/**
	 * 升阶合成消耗物品数量 t_dantian.f_up_needgoodnum
	 * @param upNeedgoodnum  the value for t_dantian.f_up_needgoodnum
	 * 
	 */
	public void setUpNeedgoodnum(Integer upNeedgoodnum) {
		this.upNeedgoodnum = upNeedgoodnum;
	}

	/**
	 * 升阶合成计算用成功几率 t_dantian.f_up_probability
	 * @return  the value of t_dantian.f_up_probability
	 * 
	 */
	public Integer getUpProbability() {
		return upProbability;
	}

	/**
	 * 升阶合成计算用成功几率 t_dantian.f_up_probability
	 * @param upProbability  the value for t_dantian.f_up_probability
	 * 
	 */
	public void setUpProbability(Integer upProbability) {
		this.upProbability = upProbability;
	}

	/**
	 * 升阶合成显示用成功几率 t_dantian.f_up_probability_show
	 * @return  the value of t_dantian.f_up_probability_show
	 * 
	 */
	public Integer getUpProbabilityShow() {
		return upProbabilityShow;
	}

	/**
	 * 升阶合成显示用成功几率 t_dantian.f_up_probability_show
	 * @param upProbabilityShow  the value for t_dantian.f_up_probability_show
	 * 
	 */
	public void setUpProbabilityShow(Integer upProbabilityShow) {
		this.upProbabilityShow = upProbabilityShow;
	}

	/**
	 * 升阶合成所需最小成功次数 t_dantian.f_up_need_min_faild
	 * @return  the value of t_dantian.f_up_need_min_faild
	 * 
	 */
	public Integer getUpNeedMinFaild() {
		return upNeedMinFaild;
	}

	/**
	 * 升阶合成所需最小成功次数 t_dantian.f_up_need_min_faild
	 * @param upNeedMinFaild  the value for t_dantian.f_up_need_min_faild
	 * 
	 */
	public void setUpNeedMinFaild(Integer upNeedMinFaild) {
		this.upNeedMinFaild = upNeedMinFaild;
	}

	/**
	 * 升阶合成所需最大成功次数 t_dantian.f_up_need_max_faild
	 * @return  the value of t_dantian.f_up_need_max_faild
	 * 
	 */
	public Integer getUpNeedMaxFaild() {
		return upNeedMaxFaild;
	}

	/**
	 * 升阶合成所需最大成功次数 t_dantian.f_up_need_max_faild
	 * @param upNeedMaxFaild  the value for t_dantian.f_up_need_max_faild
	 * 
	 */
	public void setUpNeedMaxFaild(Integer upNeedMaxFaild) {
		this.upNeedMaxFaild = upNeedMaxFaild;
	}
}
