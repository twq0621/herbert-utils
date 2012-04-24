package net.snake.gamemodel.heroext.lianti.bean;

import net.snake.ibatis.IbatisEntity;

public class Lianti  implements IbatisEntity{

	/**
	 * 境界编号 t_lianti.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 境界名称 t_lianti.f_name
	 * 
	 */
	private String name;
	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc
	 * 
	 */
	private String desc;
	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo
	 * 
	 */
	private String descTupo;
	/**
	 * 生命上限最大加成 t_lianti.f_hp
	 * 
	 */
	private Integer hp;
	/**
	 * 攻击最大加成 t_lianti.f_atk
	 * 
	 */
	private Integer atk;
	/**
	 * 防御最大加成 t_lianti.f_def
	 * 
	 */
	private Integer def;
	/**
	 * 内力上限最大加成 t_lianti.f_mp
	 * 
	 */
	private Integer mp;
	/**
	 * 体力上限最大加成 t_lianti.f_sp
	 * 
	 */
	private Integer sp;
	/**
	 * 攻速最大加成（千分比） t_lianti.f_atk_speed
	 * 
	 */
	private Integer atkSpeed;
	/**
	 * 移速最大加成 t_lianti.f_move_speed
	 * 
	 */
	private Integer moveSpeed;
	/**
	 * 暗器发动几率最大加成（万分比） t_lianti.f_aq_jv
	 * 
	 */
	private Integer aqJv;
	/**
	 * 反弹伤害最大加成（百分比） t_lianti.f_fjsh
	 * 
	 */
	private Integer fjsh;
	/**
	 * 伤害减免最大加成（百分比） t_lianti.f_shjm
	 * 
	 */
	private Integer shjm;
	/**
	 * 突破所需物品ID t_lianti.f_tupo_need_goodsmodel
	 * 
	 */
	private Integer tupoNeedGoodsmodel;
	/**
	 * 突破所需物品数量 t_lianti.f_tupo_need_goodscount
	 * 
	 */
	private Integer tupoNeedGoodscount;
	/**
	 * 突破显示用成功几率 t_lianti.f_tupo_show_probability
	 * 
	 */
	private Integer tupoShowProbability;
	/**
	 * 突破真实计算用成功几率 t_lianti.f_tupo_real_probability
	 * 
	 */
	private Integer tupoRealProbability;
	/**
	 * t_lianti.f_tupo_need_min_count
	 * 
	 */
	private Integer tupoNeedMinCount;
	/**
	 * 突破最大所需次数 t_lianti.f_tupo_need_max_count
	 * 
	 */
	private Integer tupoNeedMaxCount;
	/**
	 * t_lianti.f_tupo_need_rmb
	 * 
	 */
	private Integer tupoNeedRmb;
	/**
	 * 祝福值上限 t_lianti.f_zhufu_max
	 * 
	 */
	private Integer zhufuMax;
	/**
	 * 每次突破失败增加祝福值 t_lianti.f_zhufu_onfail
	 * 
	 */
	private Integer zhufuOnfail;
	/**
	 * t_lianti.f_food_goodsid
	 * 
	 */
	private Integer foodGoodsid;
	/**
	 * t_lianti.f_food_goodsname
	 * 
	 */
	private String foodGoodsname;
	/**
	 * 食用后加成幅度万分比 t_lianti.f_food_addproperty
	 * 
	 */
	private Integer foodAddproperty;
	/**
	 * t_lianti.f_food_map
	 * 
	 */
	private String foodMap;
	/**
	 * t_lianti.f_food_monstername
	 * 
	 */
	private String foodMonstername;
	/**
	 * t_lianti.f_location
	 * 
	 */
	private String location;
	/**
	 * 境界名称国际化 t_lianti.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc_i18n
	 * 
	 */
	private String descI18n;
	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo_i18n
	 * 
	 */
	private String descTupoI18n;
	/**
	 * 物品名称国际化 t_lianti.f_food_goodsname_i18n
	 * 
	 */
	private String foodGoodsnameI18n;
	/**
	 * 物品掉落地图名称国际化 t_lianti.f_food_map_i18n
	 * 
	 */
	private String foodMapI18n;
	/**
	 * 物品掉过怪物名称国际化 t_lianti.f_food_monstername_i18n
	 * 
	 */
	private String foodMonsternameI18n;

	/**
	 * 境界编号 t_lianti.f_id
	 * @return  the value of t_lianti.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 境界编号 t_lianti.f_id
	 * @param id  the value for t_lianti.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 境界名称 t_lianti.f_name
	 * @return  the value of t_lianti.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 境界名称 t_lianti.f_name
	 * @param name  the value for t_lianti.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc
	 * @return  the value of t_lianti.f_desc
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc
	 * @param desc  the value for t_lianti.f_desc
	 * 
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo
	 * @return  the value of t_lianti.f_desc_tupo
	 * 
	 */
	public String getDescTupo() {
		return descTupo;
	}

	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo
	 * @param descTupo  the value for t_lianti.f_desc_tupo
	 * 
	 */
	public void setDescTupo(String descTupo) {
		this.descTupo = descTupo;
	}

	/**
	 * 生命上限最大加成 t_lianti.f_hp
	 * @return  the value of t_lianti.f_hp
	 * 
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * 生命上限最大加成 t_lianti.f_hp
	 * @param hp  the value for t_lianti.f_hp
	 * 
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 攻击最大加成 t_lianti.f_atk
	 * @return  the value of t_lianti.f_atk
	 * 
	 */
	public Integer getAtk() {
		return atk;
	}

	/**
	 * 攻击最大加成 t_lianti.f_atk
	 * @param atk  the value for t_lianti.f_atk
	 * 
	 */
	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	/**
	 * 防御最大加成 t_lianti.f_def
	 * @return  the value of t_lianti.f_def
	 * 
	 */
	public Integer getDef() {
		return def;
	}

	/**
	 * 防御最大加成 t_lianti.f_def
	 * @param def  the value for t_lianti.f_def
	 * 
	 */
	public void setDef(Integer def) {
		this.def = def;
	}

	/**
	 * 内力上限最大加成 t_lianti.f_mp
	 * @return  the value of t_lianti.f_mp
	 * 
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * 内力上限最大加成 t_lianti.f_mp
	 * @param mp  the value for t_lianti.f_mp
	 * 
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * 体力上限最大加成 t_lianti.f_sp
	 * @return  the value of t_lianti.f_sp
	 * 
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * 体力上限最大加成 t_lianti.f_sp
	 * @param sp  the value for t_lianti.f_sp
	 * 
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * 攻速最大加成（千分比） t_lianti.f_atk_speed
	 * @return  the value of t_lianti.f_atk_speed
	 * 
	 */
	public Integer getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * 攻速最大加成（千分比） t_lianti.f_atk_speed
	 * @param atkSpeed  the value for t_lianti.f_atk_speed
	 * 
	 */
	public void setAtkSpeed(Integer atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * 移速最大加成 t_lianti.f_move_speed
	 * @return  the value of t_lianti.f_move_speed
	 * 
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * 移速最大加成 t_lianti.f_move_speed
	 * @param moveSpeed  the value for t_lianti.f_move_speed
	 * 
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * 暗器发动几率最大加成（万分比） t_lianti.f_aq_jv
	 * @return  the value of t_lianti.f_aq_jv
	 * 
	 */
	public Integer getAqJv() {
		return aqJv;
	}

	/**
	 * 暗器发动几率最大加成（万分比） t_lianti.f_aq_jv
	 * @param aqJv  the value for t_lianti.f_aq_jv
	 * 
	 */
	public void setAqJv(Integer aqJv) {
		this.aqJv = aqJv;
	}

	/**
	 * 反弹伤害最大加成（百分比） t_lianti.f_fjsh
	 * @return  the value of t_lianti.f_fjsh
	 * 
	 */
	public Integer getFjsh() {
		return fjsh;
	}

	/**
	 * 反弹伤害最大加成（百分比） t_lianti.f_fjsh
	 * @param fjsh  the value for t_lianti.f_fjsh
	 * 
	 */
	public void setFjsh(Integer fjsh) {
		this.fjsh = fjsh;
	}

	/**
	 * 伤害减免最大加成（百分比） t_lianti.f_shjm
	 * @return  the value of t_lianti.f_shjm
	 * 
	 */
	public Integer getShjm() {
		return shjm;
	}

	/**
	 * 伤害减免最大加成（百分比） t_lianti.f_shjm
	 * @param shjm  the value for t_lianti.f_shjm
	 * 
	 */
	public void setShjm(Integer shjm) {
		this.shjm = shjm;
	}

	/**
	 * 突破所需物品ID t_lianti.f_tupo_need_goodsmodel
	 * @return  the value of t_lianti.f_tupo_need_goodsmodel
	 * 
	 */
	public Integer getTupoNeedGoodsmodel() {
		return tupoNeedGoodsmodel;
	}

	/**
	 * 突破所需物品ID t_lianti.f_tupo_need_goodsmodel
	 * @param tupoNeedGoodsmodel  the value for t_lianti.f_tupo_need_goodsmodel
	 * 
	 */
	public void setTupoNeedGoodsmodel(Integer tupoNeedGoodsmodel) {
		this.tupoNeedGoodsmodel = tupoNeedGoodsmodel;
	}

	/**
	 * 突破所需物品数量 t_lianti.f_tupo_need_goodscount
	 * @return  the value of t_lianti.f_tupo_need_goodscount
	 * 
	 */
	public Integer getTupoNeedGoodscount() {
		return tupoNeedGoodscount;
	}

	/**
	 * 突破所需物品数量 t_lianti.f_tupo_need_goodscount
	 * @param tupoNeedGoodscount  the value for t_lianti.f_tupo_need_goodscount
	 * 
	 */
	public void setTupoNeedGoodscount(Integer tupoNeedGoodscount) {
		this.tupoNeedGoodscount = tupoNeedGoodscount;
	}

	/**
	 * 突破显示用成功几率 t_lianti.f_tupo_show_probability
	 * @return  the value of t_lianti.f_tupo_show_probability
	 * 
	 */
	public Integer getTupoShowProbability() {
		return tupoShowProbability;
	}

	/**
	 * 突破显示用成功几率 t_lianti.f_tupo_show_probability
	 * @param tupoShowProbability  the value for t_lianti.f_tupo_show_probability
	 * 
	 */
	public void setTupoShowProbability(Integer tupoShowProbability) {
		this.tupoShowProbability = tupoShowProbability;
	}

	/**
	 * 突破真实计算用成功几率 t_lianti.f_tupo_real_probability
	 * @return  the value of t_lianti.f_tupo_real_probability
	 * 
	 */
	public Integer getTupoRealProbability() {
		return tupoRealProbability;
	}

	/**
	 * 突破真实计算用成功几率 t_lianti.f_tupo_real_probability
	 * @param tupoRealProbability  the value for t_lianti.f_tupo_real_probability
	 * 
	 */
	public void setTupoRealProbability(Integer tupoRealProbability) {
		this.tupoRealProbability = tupoRealProbability;
	}

	/**
	 * t_lianti.f_tupo_need_min_count
	 * @return  the value of t_lianti.f_tupo_need_min_count
	 * 
	 */
	public Integer getTupoNeedMinCount() {
		return tupoNeedMinCount;
	}

	/**
	 * t_lianti.f_tupo_need_min_count
	 * @param tupoNeedMinCount  the value for t_lianti.f_tupo_need_min_count
	 * 
	 */
	public void setTupoNeedMinCount(Integer tupoNeedMinCount) {
		this.tupoNeedMinCount = tupoNeedMinCount;
	}

	/**
	 * 突破最大所需次数 t_lianti.f_tupo_need_max_count
	 * @return  the value of t_lianti.f_tupo_need_max_count
	 * 
	 */
	public Integer getTupoNeedMaxCount() {
		return tupoNeedMaxCount;
	}

	/**
	 * 突破最大所需次数 t_lianti.f_tupo_need_max_count
	 * @param tupoNeedMaxCount  the value for t_lianti.f_tupo_need_max_count
	 * 
	 */
	public void setTupoNeedMaxCount(Integer tupoNeedMaxCount) {
		this.tupoNeedMaxCount = tupoNeedMaxCount;
	}

	/**
	 * t_lianti.f_tupo_need_rmb
	 * @return  the value of t_lianti.f_tupo_need_rmb
	 * 
	 */
	public Integer getTupoNeedRmb() {
		return tupoNeedRmb;
	}

	/**
	 * t_lianti.f_tupo_need_rmb
	 * @param tupoNeedRmb  the value for t_lianti.f_tupo_need_rmb
	 * 
	 */
	public void setTupoNeedRmb(Integer tupoNeedRmb) {
		this.tupoNeedRmb = tupoNeedRmb;
	}

	/**
	 * 祝福值上限 t_lianti.f_zhufu_max
	 * @return  the value of t_lianti.f_zhufu_max
	 * 
	 */
	public Integer getZhufuMax() {
		return zhufuMax;
	}

	/**
	 * 祝福值上限 t_lianti.f_zhufu_max
	 * @param zhufuMax  the value for t_lianti.f_zhufu_max
	 * 
	 */
	public void setZhufuMax(Integer zhufuMax) {
		this.zhufuMax = zhufuMax;
	}

	/**
	 * 每次突破失败增加祝福值 t_lianti.f_zhufu_onfail
	 * @return  the value of t_lianti.f_zhufu_onfail
	 * 
	 */
	public Integer getZhufuOnfail() {
		return zhufuOnfail;
	}

	/**
	 * 每次突破失败增加祝福值 t_lianti.f_zhufu_onfail
	 * @param zhufuOnfail  the value for t_lianti.f_zhufu_onfail
	 * 
	 */
	public void setZhufuOnfail(Integer zhufuOnfail) {
		this.zhufuOnfail = zhufuOnfail;
	}

	/**
	 * t_lianti.f_food_goodsid
	 * @return  the value of t_lianti.f_food_goodsid
	 * 
	 */
	public Integer getFoodGoodsid() {
		return foodGoodsid;
	}

	/**
	 * t_lianti.f_food_goodsid
	 * @param foodGoodsid  the value for t_lianti.f_food_goodsid
	 * 
	 */
	public void setFoodGoodsid(Integer foodGoodsid) {
		this.foodGoodsid = foodGoodsid;
	}

	/**
	 * t_lianti.f_food_goodsname
	 * @return  the value of t_lianti.f_food_goodsname
	 * 
	 */
	public String getFoodGoodsname() {
		return foodGoodsname;
	}

	/**
	 * t_lianti.f_food_goodsname
	 * @param foodGoodsname  the value for t_lianti.f_food_goodsname
	 * 
	 */
	public void setFoodGoodsname(String foodGoodsname) {
		this.foodGoodsname = foodGoodsname;
	}

	/**
	 * 食用后加成幅度万分比 t_lianti.f_food_addproperty
	 * @return  the value of t_lianti.f_food_addproperty
	 * 
	 */
	public Integer getFoodAddproperty() {
		return foodAddproperty;
	}

	/**
	 * 食用后加成幅度万分比 t_lianti.f_food_addproperty
	 * @param foodAddproperty  the value for t_lianti.f_food_addproperty
	 * 
	 */
	public void setFoodAddproperty(Integer foodAddproperty) {
		this.foodAddproperty = foodAddproperty;
	}

	/**
	 * t_lianti.f_food_map
	 * @return  the value of t_lianti.f_food_map
	 * 
	 */
	public String getFoodMap() {
		return foodMap;
	}

	/**
	 * t_lianti.f_food_map
	 * @param foodMap  the value for t_lianti.f_food_map
	 * 
	 */
	public void setFoodMap(String foodMap) {
		this.foodMap = foodMap;
	}

	/**
	 * t_lianti.f_food_monstername
	 * @return  the value of t_lianti.f_food_monstername
	 * 
	 */
	public String getFoodMonstername() {
		return foodMonstername;
	}

	/**
	 * t_lianti.f_food_monstername
	 * @param foodMonstername  the value for t_lianti.f_food_monstername
	 * 
	 */
	public void setFoodMonstername(String foodMonstername) {
		this.foodMonstername = foodMonstername;
	}

	/**
	 * t_lianti.f_location
	 * @return  the value of t_lianti.f_location
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * t_lianti.f_location
	 * @param location  the value for t_lianti.f_location
	 * 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 境界名称国际化 t_lianti.f_name_i18n
	 * @return  the value of t_lianti.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 境界名称国际化 t_lianti.f_name_i18n
	 * @param nameI18n  the value for t_lianti.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc_i18n
	 * @return  the value of t_lianti.f_desc_i18n
	 * 
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 境界描述信息，支持换行与空格 t_lianti.f_desc_i18n
	 * @param descI18n  the value for t_lianti.f_desc_i18n
	 * 
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo_i18n
	 * @return  the value of t_lianti.f_desc_tupo_i18n
	 * 
	 */
	public String getDescTupoI18n() {
		return descTupoI18n;
	}

	/**
	 * 境界突破信息，支持换行，支持空格 t_lianti.f_desc_tupo_i18n
	 * @param descTupoI18n  the value for t_lianti.f_desc_tupo_i18n
	 * 
	 */
	public void setDescTupoI18n(String descTupoI18n) {
		this.descTupoI18n = descTupoI18n;
	}

	/**
	 * 物品名称国际化 t_lianti.f_food_goodsname_i18n
	 * @return  the value of t_lianti.f_food_goodsname_i18n
	 * 
	 */
	public String getFoodGoodsnameI18n() {
		return foodGoodsnameI18n;
	}

	/**
	 * 物品名称国际化 t_lianti.f_food_goodsname_i18n
	 * @param foodGoodsnameI18n  the value for t_lianti.f_food_goodsname_i18n
	 * 
	 */
	public void setFoodGoodsnameI18n(String foodGoodsnameI18n) {
		this.foodGoodsnameI18n = foodGoodsnameI18n;
	}

	/**
	 * 物品掉落地图名称国际化 t_lianti.f_food_map_i18n
	 * @return  the value of t_lianti.f_food_map_i18n
	 * 
	 */
	public String getFoodMapI18n() {
		return foodMapI18n;
	}

	/**
	 * 物品掉落地图名称国际化 t_lianti.f_food_map_i18n
	 * @param foodMapI18n  the value for t_lianti.f_food_map_i18n
	 * 
	 */
	public void setFoodMapI18n(String foodMapI18n) {
		this.foodMapI18n = foodMapI18n;
	}

	/**
	 * 物品掉过怪物名称国际化 t_lianti.f_food_monstername_i18n
	 * @return  the value of t_lianti.f_food_monstername_i18n
	 * 
	 */
	public String getFoodMonsternameI18n() {
		return foodMonsternameI18n;
	}

	/**
	 * 物品掉过怪物名称国际化 t_lianti.f_food_monstername_i18n
	 * @param foodMonsternameI18n  the value for t_lianti.f_food_monstername_i18n
	 * 
	 */
	public void setFoodMonsternameI18n(String foodMonsternameI18n) {
		this.foodMonsternameI18n = foodMonsternameI18n;
	}
}
