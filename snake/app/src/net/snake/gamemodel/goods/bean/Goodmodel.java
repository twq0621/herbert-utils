package net.snake.gamemodel.goods.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.consts.Position;
import net.snake.consts.Property;
import net.snake.gamemodel.goods.logic.action.UseGoodAction;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.ibatis.IbatisEntity;

public class Goodmodel implements IbatisEntity {

	/**
	 * 装备 t_goodmodel.f_id
	 * 
	 * 
	 */
	private Integer id;

	/**
	 * 名称 t_goodmodel.f_name
	 * 
	 * 
	 */
	private String name;

	/**
	 * 描述 t_goodmodel.f_desc
	 * 
	 * 
	 */
	private String desc;

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc
	 * 
	 * 
	 */
	private String useDesc;

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc_i18n
	 * 
	 * 
	 */
	private String useDescI18n;

	/**
	 * 门派需求,0无,1少林,2武当,3古墓,4峨眉 t_goodmodel.f_popsinger
	 * 
	 * 
	 */
	private Integer popsinger;

	/**
	 * t_goodmodel.f_kind
	 * 
	 * 
	 */
	private Short kind;

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_goodmodel.f_binding
	 * 
	 * 
	 */
	private Byte binding;

	/**
	 * 是否可丢弃，0不可以，1可以 t_goodmodel.f_discard
	 * 
	 * 
	 */
	private Boolean discard;

	/**
	 * 物品叠放数量上限 1为不可叠加 t_goodmodel.f_repeat
	 * 
	 * 
	 */
	private Integer repeat;

	/**
	 * 背包中的ICO图标编号 t_goodmodel.f_ico
	 * 
	 * 
	 */
	private Integer ico;

	/**
	 * 大图标 t_goodmodel.f_big_ico
	 * 
	 * 
	 */
	private Integer bigIco;

	/**
	 * 换装ID,如果是装备的话 t_goodmodel.f_avatar_id
	 * 
	 * 
	 */
	private Integer avatarId;

	/**
	 * 是否记录日志,0不记录,1记录掉出,使用,交易,出售,摧毁日志 t_goodmodel.f_log
	 * 
	 * 
	 */
	private Boolean log;

	/**
	 * 掉出时是否发送公告,0不发送,1仅在聊天框个人公告,2在屏幕中央和聊天框均个人公告,3仅在聊天框全服公告,4在屏幕中央和聊天框均全服公告 t_goodmodel.f_send_announcement
	 * 
	 * 
	 */
	private Boolean sendAnnouncement;

	/**
	 * 捡入或掉落时播放声音编号 t_goodmodel.f_sound
	 * 
	 * 
	 */
	private Integer sound;

	/**
	 * 使用时播放声音编号 t_goodmodel.f_use_sound
	 * 
	 * 
	 */
	private Integer useSound;

	/**
	 * 使用时播放效果图像编号 t_goodmodel.f_use_image
	 * 
	 * 
	 */
	private String useImage;

	/**
	 * 物品的购买价格 t_goodmodel.f_buy_price
	 * 
	 * 
	 */
	private Integer buyPrice;

	/**
	 * 物品的售价价格 t_goodmodel.f_sale_price
	 * 
	 * 
	 */
	private Integer salePrice;

	/**
	 * 增加攻击力-装备基础属性 t_goodmodel.f_attack
	 * 
	 * 
	 */
	private Integer attack;

	/**
	 * 增加防御力-装备基础属性 t_goodmodel.f_defence
	 * 
	 * 
	 */
	private Integer defence;

	/**
	 * 增加暴击 1/10000-装备基础属性 t_goodmodel.f_crt
	 * 
	 * 
	 */
	private Integer crt;

	/**
	 * 增加闪避 单位1/10000-装备基础属性 t_goodmodel.f_dodge
	 * 
	 * 
	 */
	private Integer dodge;

	/**
	 * 增加命中单位1/10000-装备基础属性 t_goodmodel.f_hit
	 * 
	 * 
	 */
	private Integer hit;

	/**
	 * 增加攻击速度 用攻击冷却时间计(ms)-装备基础属性 t_goodmodel.f_atk_coldtime
	 * 
	 * 
	 */
	private Integer atkColdtime;

	/**
	 * 增加移动速度 以像素计-装备基础属性 t_goodmodel.f_move_speed
	 * 
	 * 
	 */
	private Integer moveSpeed;

	/**
	 * 增加生命上限值-装备基础属性 t_goodmodel.f_hp
	 * 
	 * 
	 */
	private Integer hp;

	/**
	 * 增加内力上限值-装备基础属性 t_goodmodel.f_mp
	 * 
	 * 
	 */
	private Integer mp;

	/**
	 * 增加体力上限值-装备基础属性 t_goodmodel.f_sp
	 * 
	 * 
	 */
	private Integer sp;

	/**
	 * 装备品阶-属于装备的属性 t_goodmodel.f_grade
	 * 
	 * 
	 */
	private Integer grade;

	/**
	 * 强化等级附加几率 t_goodmodel.f_grade_probability
	 * 
	 * 
	 */
	private Integer gradeProbability;

	/**
	 * 强化等级附加最小等级 t_goodmodel.f_grade_min
	 * 
	 * 
	 */
	private Integer gradeMin;

	/**
	 * 强化等级附加最大等级 t_goodmodel.f_grade_max
	 * 
	 * 
	 */
	private Integer gradeMax;

	/**
	 * 掉落附加产生几率 t_goodmodel.f_flop_probability
	 * 
	 * 
	 */
	private Integer flopProbability;

	/**
	 * 天生附加几率 t_goodmodel.f_born_probability
	 * 
	 * 
	 */
	private Integer bornProbability;

	/**
	 * 天生附加最大几率 t_goodmodel.f_bron_max
	 * 
	 * 
	 */
	private Integer bronMax;

	/**
	 * 天生附加最小几率 t_goodmodel.f_bron_min
	 * 
	 * 
	 */
	private Integer bronMin;

	/**
	 * 增加命中出现几率 t_goodmodel.f_attack_probability
	 * 
	 * 
	 */
	private Integer hitProbability;

	/**
	 * 增加命中最小值 t_goodmodel.f_attack_min
	 * 
	 * 
	 */
	private Integer hitMin;

	/**
	 * 增加命中最大值 t_goodmodel.f_attack_max
	 * 
	 * 
	 */
	private Integer hitMax;

	/**
	 * 增加攻击力出现几率 t_goodmodel.f_attack_probability
	 * 
	 * 
	 */
	private Integer attackProbability;

	/**
	 * 增加攻击力最小值 t_goodmodel.f_attack_min
	 * 
	 * 
	 */
	private Integer attackMin;

	/**
	 * 增加攻击力最大值 t_goodmodel.f_attack_max
	 * 
	 * 
	 */
	private Integer attackMax;

	/**
	 * 增加防御力出现几率 t_goodmodel.f_defence_probability
	 * 
	 * 
	 */
	private Integer defenceProbability;

	/**
	 * 增加防御力最小值 t_goodmodel.f_defence_min
	 * 
	 * 
	 */
	private Integer defenceMin;

	/**
	 * 增加防御力最大值 t_goodmodel.f_defence_max
	 * 
	 * 
	 */
	private Integer defenceMax;

	/**
	 * 增加暴击出现几率 t_goodmodel.f_crt_probability
	 * 
	 * 
	 */
	private Integer crtProbability;

	/**
	 * 增加暴击最小值 t_goodmodel.f_crt_min
	 * 
	 * 
	 */
	private Integer crtMin;

	/**
	 * 增加暴击最大值 t_goodmodel.f_crt_max
	 * 
	 * 
	 */
	private Integer crtMax;

	/**
	 * 增加闪避出现几率 t_goodmodel.f_dodge_probability
	 * 
	 * 
	 */
	private Integer dodgeProbability;

	/**
	 * 增加闪避最小值 t_goodmodel.f_dodge_min
	 * 
	 * 
	 */
	private Integer dodgeMin;

	/**
	 * 增加闪避最大值 t_goodmodel.f_dodge_max
	 * 
	 * 
	 */
	private Integer dodgeMax;

	/**
	 * 增加攻击速度出现几率 t_goodmodel.f_atk_coldtime_probability
	 * 
	 * 
	 */
	private Integer atkColdtimeProbability;

	/**
	 * 增加攻击速度最小值 t_goodmodel.f_atk_coldtime_min
	 * 
	 * 
	 */
	private Integer atkColdtimeMin;

	/**
	 * 增加攻击速度最大值 t_goodmodel.f_atk_coldtime_max
	 * 
	 * 
	 */
	private Integer atkColdtimeMax;

	/**
	 * 增加移动速度出现几率 t_goodmodel.f_move_speed_probability
	 * 
	 * 
	 */
	private Integer moveSpeedProbability;

	/**
	 * 增加移动速度最小值 t_goodmodel.f_move_speed_min
	 * 
	 * 
	 */
	private Integer moveSpeedMin;

	/**
	 * 增加移动速度最大值 t_goodmodel.f_move_speed_max
	 * 
	 * 
	 */
	private Integer moveSpeedMax;

	/**
	 * 增加生命上限值出现几率 t_goodmodel.f_hp_probability
	 * 
	 * 
	 */
	private Integer hpProbability;

	/**
	 * 增加生命上限值最小值 t_goodmodel.f_hp_min
	 * 
	 * 
	 */
	private Integer hpMin;

	/**
	 * 增加生命上限值最大值 t_goodmodel.f_hp_max
	 * 
	 * 
	 */
	private Integer hpMax;

	/**
	 * 增加内力上限值出现几率 t_goodmodel.f_mp_probability
	 * 
	 * 
	 */
	private Integer mpProbability;

	/**
	 * 增加内力上限值最小值 t_goodmodel.f_mp_min
	 * 
	 * 
	 */
	private Integer mpMin;

	/**
	 * 增加内力上限值最大值 t_goodmodel.f_mp_max
	 * 
	 * 
	 */
	private Integer mpMax;

	/**
	 * 增加体力上限值出现几率 t_goodmodel.f_sp_probability
	 * 
	 * 
	 */
	private Integer spProbability;

	/**
	 * 增加体力上限值最小值 t_goodmodel.f_sp_min
	 * 
	 * 
	 */
	private Integer spMin;

	/**
	 * 增加体力上限值最大值 t_goodmodel.f_sp_max
	 * 
	 * 
	 */
	private Integer spMax;

	/**
	 * 装备佩戴位置1武器,2头饰,3衣服,4腰带,5鞋子,6护手,7项链,8戒指,9手镯,10披风,11坐骑,12时装,13法宝 1武器-》,2骑站武器- 》无,3衣服-》胸甲,4护手-》护腕,5护腰-》腰带,6护腰-》鞋,7头饰-》头盔,8项链-》腿甲,9戒指-》戒指,10手镯 -》戒指,11腰坠-》饰品,12披风,13特殊-》法宝
	 * t_goodmodel.f_position
	 * 
	 * 
	 */
	private Byte position;

	/**
	 * 装备最大耐久度 t_goodmodel.f_durability
	 * 
	 * 
	 */
	private Integer durability;

	/**
	 * 修理单价，该项表示恢复一点耐久需要多少铜币 t_goodmodel.f_repair_money
	 * 
	 * 
	 */
	private Integer repairMoney;

	/**
	 * 等级限制 t_goodmodel.f_limit_grade
	 * 
	 * 
	 */
	private Integer limitGrade;

	/**
	 * 穿此装备-需要的进攻加点 t_goodmodel.f_attack_addpoint
	 * 
	 * 
	 */
	private Short attackAddpoint;

	/**
	 * 穿此装备-需要的防御加点 t_goodmodel.f_defence_addpoint
	 * 
	 * 
	 */
	private Short defenceAddpoint;

	/**
	 * 穿此装备-需要的轻身加点 t_goodmodel.f_light_addpoint
	 * 
	 * 
	 */
	private Short lightAddpoint;

	/**
	 * 穿此装备-需要的健体加点需求 t_goodmodel.f_strong_addpoint
	 * 
	 * 
	 */
	private Short strongAddpoint;

	/**
	 * 宝石种类,1增加攻击力,2增加防御力,3增加暴击,4增加闪避,5增加生命上限值,6增加体力上限值,7增加内力上限值,8增加攻击速度,9增加移动速度 ,10命中 t_goodmodel.f_gems_type
	 * 
	 * 
	 */
	private Integer gemsType;

	/**
	 * 宝石加成数值 t_goodmodel.f_gems_value
	 * 
	 * 
	 */
	private Integer gemsValue;

	/**
	 * 使用后习得武功或心法编号 t_goodmodel.f_skill
	 * 
	 * 
	 */
	private Integer skill;

	/**
	 * 使用后触发的任务 t_goodmodel.f_task
	 * 
	 * 
	 */
	private Integer task;

	/**
	 * 使用后触发的脚本类 t_goodmodel.f_scriptclass
	 * 
	 * 
	 */
	private String scriptclass;

	/**
	 * 脚本类的初始化参数 t_goodmodel.f_scriptclassparam
	 * 
	 * 
	 */
	private String scriptclassparam;

	/**
	 * 药品类型 t_goodmodel.f_drug_type
	 * 
	 * 
	 */
	private Integer drugType;

	/**
	 * 药品映射的SKILL_EFFECT ID 若为0表示永久增加的药品 目前各种BUFF均是只增加固定值,不存在百分比加成BUF t_goodmodel.f_drug_buff_id
	 * 
	 * 
	 */
	private Integer drugBuffId;

	/**
	 * 药品的值 t_goodmodel.f_drug_value
	 * 
	 * 
	 */
	private Integer drugValue;

	/**
	 * 物品冷却时间-属性药品的属性 t_goodmodel.f_coolingtime
	 * 
	 * 
	 */
	private Integer coolingtime;

	/**
	 * 坐骑精魂 t_goodmodel.f_horse_spirit
	 * 
	 * 
	 */
	private Integer horseSpirit;

	/**
	 * 最高的强化等级 t_goodmodel.f_strengthen_grade
	 * 
	 * 
	 */
	private Integer strengthenGrade;

	/**
	 * 为空表示没有特定的该属性 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) t_goodmodel.f_strengthen_desc
	 * 
	 * 
	 */
	private String strengthenDesc;

	/**
	 * 附加属性的说明 -- 装备使用(附加属性类型，属性值；) t_goodmodel.f_addition_desc
	 * 
	 * 
	 */
	private String additionDesc;

	/**
	 * 镶嵌的宝石模型ID （宝石模型id;宝石模型id;） t_goodmodel.f_inlay_gems_desc
	 * 
	 * 
	 */
	private String inlayGemsDesc;

	/**
	 * npc处购买物品时要求的最低声望值 t_goodmodel.f_prestige_limit
	 * 
	 * 
	 */
	private Integer prestigeLimit;

	/**
	 * 关联升级礼包 t_goodmodel.gift_packs_id
	 * 
	 * 
	 */
	private Integer giftPacksId;

	/**
	 * 物品落掉的特效ID t_goodmodel.f_drop_effect_id
	 * 
	 * 
	 */
	private Integer dropEffectId;

	/**
	 * 公共冷却时间影响的类型 t_goodmodel.f_common_cool_type
	 * 
	 * 
	 */
	private Integer commonCoolType;

	/**
	 * 公共冷却时间 单位毫秒 t_goodmodel.f_common_cool_time
	 * 
	 * 
	 */
	private Integer commonCoolTime;

	/**
	 * 物品消失时间 yyyy-mm-dd hh:ss:mm t_goodmodel.f_end_date
	 * 
	 * 
	 */
	private String endDate;

	/**
	 * 存在时间 单位： 秒 t_goodmodel.f_live_time
	 * 
	 * 
	 */
	private Integer liveTime;

	/**
	 * 增加武功层数值 t_goodmodel.f_wugong_grade
	 * 
	 * 
	 */
	private Integer wugongGrade;

	/**
	 * 附加武功类型取值区间格式：(武功ID,适用门派;武功ID,适用门派;） t_goodmodel.f_wugong_desc
	 * 
	 * 
	 */
	private String wugongDesc;

	/**
	 * 充气娃娃每天提取的最大次数 t_goodmodel.f_chongqi_max
	 * 
	 * 
	 */
	private Integer chongqiMax;

	/**
	 * 名称国际化 t_goodmodel.f_name_i18n
	 * 
	 * 
	 */
	private String nameI18n;

	/**
	 * 描述际化国 t_goodmodel.f_desc_i18n
	 * 
	 * 
	 */
	private String descI18n;

	/**
	 * 弓箭触发几率 t_goodmodel.f_arrowskill_prob
	 * 
	 * 
	 */
	private Integer arrowskillProb;

	/**
	 * 物品天生ICO光效 t_goodmodel.f_born_ico
	 * 
	 * 
	 */
	private Integer bornIco;

	/**
	 * 物品天生颜色品级(0 白色 1 蓝色 2 绿色 3 紫色 4 橙色) t_goodmodel.f_born_color_grade
	 * 
	 * 
	 */
	private Integer bornColorGrade;

	/**
	 * 物品是否允许出售 0不允许/1允许 t_goodmodel.f_is_sale
	 * 
	 * 
	 */
	private Byte isSale;

	/**
	 * 出售是否弹出确认面板0不是/1是 t_goodmodel.f_sale_is_ui
	 * 
	 * 
	 */
	private Byte saleIsUi;

	/**
	 * 物品销毁或丢弃时是否弹出二次确认面板，0不弹出，1弹出 t_goodmodel.f_discard_is_ui
	 * 
	 * 
	 */
	private Boolean discardIsUi;

	/**
	 * 可否被放入跨服包裹 0不能 ，1能被放入跨服包裹 t_goodmodel.f_is_across
	 * 
	 * 
	 */
	private Byte isAcross;

	private int changeModelId;

	private int minCount; // 概率成功最低次数 0 0
	private int maxCount; // 概率成功上限 0 0
	private int probability; // 概率，万分比 0 0
	private int copper; // 消耗铜币
	private int zhenqi; // 消耗真气
	
	/**
	 * 装备 t_goodmodel.f_id
	 * 
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 装备 t_goodmodel.f_id
	 * 
	 * @param id
	 *            the value for t_goodmodel.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 名称 t_goodmodel.f_name
	 * 
	 * @return the value of t_goodmodel.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称 t_goodmodel.f_name
	 * 
	 * @param name
	 *            the value for t_goodmodel.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述 t_goodmodel.f_desc
	 * 
	 * @return the value of t_goodmodel.f_desc
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 描述 t_goodmodel.f_desc
	 * 
	 * @param desc
	 *            the value for t_goodmodel.f_desc
	 * 
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc
	 * 
	 * @return the value of t_goodmodel.f_use_desc
	 * 
	 */
	public String getUseDesc() {
		return useDesc;
	}

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc
	 * 
	 * @param useDesc
	 *            the value for t_goodmodel.f_use_desc
	 * 
	 */
	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc_i18n
	 * 
	 * @return the value of t_goodmodel.f_use_desc_i18n
	 * 
	 */
	public String getUseDescI18n() {
		return useDescI18n;
	}

	/**
	 * 使用效果描述 t_goodmodel.f_use_desc_i18n
	 * 
	 * @param useDescI18n
	 *            the value for t_goodmodel.f_use_desc_i18n
	 * 
	 */
	public void setUseDescI18n(String useDescI18n) {
		this.useDescI18n = useDescI18n;
	}

	/**
	 * 门派需求,0无,1少林,2武当,3古墓,4峨眉 t_goodmodel.f_popsinger
	 * 
	 * @return the value of t_goodmodel.f_popsinger
	 * 
	 */
	public Integer getPopsinger() {
		return popsinger;
	}

	/**
	 * 门派需求,0无,1少林,2武当,3古墓,4峨眉 t_goodmodel.f_popsinger
	 * 
	 * @param popsinger
	 *            the value for t_goodmodel.f_popsinger
	 * 
	 */
	public void setPopsinger(Integer popsinger) {
		this.popsinger = popsinger;
	}

	/**
	 *  //1普通物品 2装备 3药品 4任务卷轴(可以给玩家一个任务) 5秘籍书 6宝石 7喇叭 8烟花 9可用于合成的材料 
	 *  //10时间过期类道具 11礼包道具 12坐骑精魂 15宝箱和秘籍摇奖 17宝藏 
	 *  //18打磨石 19回城卷轴 21洗点 22脚本物品 23宝石 24技能丹 25商城购物卡 26金松果相关 27炼体的食物 28箭支 29黑玉断续膏
	 *  //30获取马,31马 ,32加速器，33肉身,34内丹
	 * 
	 * @return the value of t_goodmodel.f_kind
	 * 
	 */
	public Short getKind() {
		return kind;
	}

	/**
	 * t_goodmodel.f_kind
	 * 
	 * @param kind
	 *            the value for t_goodmodel.f_kind
	 * 
	 */
	public void setKind(Short kind) {
		this.kind = kind;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_goodmodel.f_binding
	 * 
	 * @return the value of t_goodmodel.f_binding
	 * 
	 */
	public Byte getBinding() {
		return binding;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_goodmodel.f_binding
	 * 
	 * @param binding
	 *            the value for t_goodmodel.f_binding
	 * 
	 */
	public void setBinding(Byte binding) {
		this.binding = binding;
	}

	/**
	 * 是否可丢弃，0不可以，1可以 t_goodmodel.f_discard
	 * 
	 * @return the value of t_goodmodel.f_discard
	 * 
	 */
	public Boolean getDiscard() {
		return discard;
	}

	/**
	 * 是否可丢弃，0不可以，1可以 t_goodmodel.f_discard
	 * 
	 * @param discard
	 *            the value for t_goodmodel.f_discard
	 * 
	 */
	public void setDiscard(Boolean discard) {
		this.discard = discard;
	}

	/**
	 * 物品叠放数量上限 1为不可叠加 t_goodmodel.f_repeat
	 * 
	 * @return the value of t_goodmodel.f_repeat
	 * 
	 */
	public Integer getRepeat() {
		return repeat;
	}

	/**
	 * 物品叠放数量上限 1为不可叠加 t_goodmodel.f_repeat
	 * 
	 * @param repeat
	 *            the value for t_goodmodel.f_repeat
	 * 
	 */
	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	/**
	 * 背包中的ICO图标编号 t_goodmodel.f_ico
	 * 
	 * @return the value of t_goodmodel.f_ico
	 * 
	 */
	public Integer getIco() {
		return ico;
	}

	/**
	 * 背包中的ICO图标编号 t_goodmodel.f_ico
	 * 
	 * @param ico
	 *            the value for t_goodmodel.f_ico
	 * 
	 */
	public void setIco(Integer ico) {
		this.ico = ico;
	}

	/**
	 * 大图标 t_goodmodel.f_big_ico
	 * 
	 * @return the value of t_goodmodel.f_big_ico
	 * 
	 */
	public Integer getBigIco() {
		return bigIco;
	}

	/**
	 * 大图标 t_goodmodel.f_big_ico
	 * 
	 * @param bigIco
	 *            the value for t_goodmodel.f_big_ico
	 * 
	 */
	public void setBigIco(Integer bigIco) {
		this.bigIco = bigIco;
	}

	/**
	 * 换装ID,如果是装备的话 t_goodmodel.f_avatar_id
	 * 
	 * @return the value of t_goodmodel.f_avatar_id
	 * 
	 */
	public Integer getAvatarId() {
		return avatarId;
	}

	/**
	 * 换装ID,如果是装备的话 t_goodmodel.f_avatar_id
	 * 
	 * @param avatarId
	 *            the value for t_goodmodel.f_avatar_id
	 * 
	 */
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	/**
	 * 是否记录日志,0不记录,1记录掉出,使用,交易,出售,摧毁日志 t_goodmodel.f_log
	 * 
	 * @return the value of t_goodmodel.f_log
	 * 
	 */
	public Boolean getLog() {
		return log;
	}

	/**
	 * 是否记录日志,0不记录,1记录掉出,使用,交易,出售,摧毁日志 t_goodmodel.f_log
	 * 
	 * @param log
	 *            the value for t_goodmodel.f_log
	 * 
	 */
	public void setLog(Boolean log) {
		this.log = log;
	}

	/**
	 * 掉出时是否发送公告,0不发送,1仅在聊天框个人公告,2在屏幕中央和聊天框均个人公告,3仅在聊天框全服公告,4在屏幕中央和聊天框均全服公告 t_goodmodel.f_send_announcement
	 * 
	 * @return the value of t_goodmodel.f_send_announcement
	 * 
	 */
	public Boolean getSendAnnouncement() {
		return sendAnnouncement;
	}

	/**
	 * 掉出时是否发送公告,0不发送,1仅在聊天框个人公告,2在屏幕中央和聊天框均个人公告,3仅在聊天框全服公告,4在屏幕中央和聊天框均全服公告 t_goodmodel.f_send_announcement
	 * 
	 * @param sendAnnouncement
	 *            the value for t_goodmodel.f_send_announcement
	 * 
	 */
	public void setSendAnnouncement(Boolean sendAnnouncement) {
		this.sendAnnouncement = sendAnnouncement;
	}

	/**
	 * 捡入或掉落时播放声音编号 t_goodmodel.f_sound
	 * 
	 * @return the value of t_goodmodel.f_sound
	 * 
	 */
	public Integer getSound() {
		return sound;
	}

	/**
	 * 捡入或掉落时播放声音编号 t_goodmodel.f_sound
	 * 
	 * @param sound
	 *            the value for t_goodmodel.f_sound
	 * 
	 */
	public void setSound(Integer sound) {
		this.sound = sound;
	}

	/**
	 * 使用时播放声音编号 t_goodmodel.f_use_sound
	 * 
	 * @return the value of t_goodmodel.f_use_sound
	 * 
	 */
	public Integer getUseSound() {
		return useSound;
	}

	/**
	 * 使用时播放声音编号 t_goodmodel.f_use_sound
	 * 
	 * @param useSound
	 *            the value for t_goodmodel.f_use_sound
	 * 
	 */
	public void setUseSound(Integer useSound) {
		this.useSound = useSound;
	}

	/**
	 * 使用时播放效果图像编号 t_goodmodel.f_use_image
	 * 
	 * @return the value of t_goodmodel.f_use_image
	 * 
	 */
	public String getUseImage() {
		return useImage;
	}

	/**
	 * 使用时播放效果图像编号 t_goodmodel.f_use_image
	 * 
	 * @param useImage
	 *            the value for t_goodmodel.f_use_image
	 * 
	 */
	public void setUseImage(String useImage) {
		this.useImage = useImage;
	}

	/**
	 * 物品的购买价格 t_goodmodel.f_buy_price
	 * 
	 * @return the value of t_goodmodel.f_buy_price
	 * 
	 */
	public Integer getBuyPrice() {
		return buyPrice;
	}

	/**
	 * 物品的购买价格 t_goodmodel.f_buy_price
	 * 
	 * @param buyPrice
	 *            the value for t_goodmodel.f_buy_price
	 * 
	 */
	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * 物品的售价价格 t_goodmodel.f_sale_price
	 * 
	 * @return the value of t_goodmodel.f_sale_price
	 * 
	 */
	public Integer getSalePrice() {
		return salePrice;
	}

	/**
	 * 物品的售价价格 t_goodmodel.f_sale_price
	 * 
	 * @param salePrice
	 *            the value for t_goodmodel.f_sale_price
	 * 
	 */
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * 增加攻击力-装备基础属性 t_goodmodel.f_attack
	 * 
	 * @return the value of t_goodmodel.f_attack
	 * 
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 增加攻击力-装备基础属性 t_goodmodel.f_attack
	 * 
	 * @param attack
	 *            the value for t_goodmodel.f_attack
	 * 
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 增加防御力-装备基础属性 t_goodmodel.f_defence
	 * 
	 * @return the value of t_goodmodel.f_defence
	 * 
	 */
	public Integer getDefence() {
		return defence;
	}

	/**
	 * 增加防御力-装备基础属性 t_goodmodel.f_defence
	 * 
	 * @param defence
	 *            the value for t_goodmodel.f_defence
	 * 
	 */
	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	/**
	 * 增加暴击 1/10000-装备基础属性 t_goodmodel.f_crt
	 * 
	 * @return the value of t_goodmodel.f_crt
	 * 
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 增加暴击 1/10000-装备基础属性 t_goodmodel.f_crt
	 * 
	 * @param crt
	 *            the value for t_goodmodel.f_crt
	 * 
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 增加闪避 单位1/10000-装备基础属性 t_goodmodel.f_dodge
	 * 
	 * @return the value of t_goodmodel.f_dodge
	 * 
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 增加闪避 单位1/10000-装备基础属性 t_goodmodel.f_dodge
	 * 
	 * @param dodge
	 *            the value for t_goodmodel.f_dodge
	 * 
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	/**
	 * 增加攻击速度 用攻击冷却时间计(ms)-装备基础属性 t_goodmodel.f_atk_coldtime
	 * 
	 * @return the value of t_goodmodel.f_atk_coldtime
	 * 
	 */
	public Integer getAtkColdtime() {
		return atkColdtime;
	}

	/**
	 * 增加攻击速度 用攻击冷却时间计(ms)-装备基础属性 t_goodmodel.f_atk_coldtime
	 * 
	 * @param atkColdtime
	 *            the value for t_goodmodel.f_atk_coldtime
	 * 
	 */
	public void setAtkColdtime(Integer atkColdtime) {
		this.atkColdtime = atkColdtime;
	}

	/**
	 * 增加移动速度 以像素计-装备基础属性 t_goodmodel.f_move_speed
	 * 
	 * @return the value of t_goodmodel.f_move_speed
	 * 
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * 增加移动速度 以像素计-装备基础属性 t_goodmodel.f_move_speed
	 * 
	 * @param moveSpeed
	 *            the value for t_goodmodel.f_move_speed
	 * 
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * 增加生命上限值-装备基础属性 t_goodmodel.f_hp
	 * 
	 * @return the value of t_goodmodel.f_hp
	 * 
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * 增加生命上限值-装备基础属性 t_goodmodel.f_hp
	 * 
	 * @param hp
	 *            the value for t_goodmodel.f_hp
	 * 
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 增加内力上限值-装备基础属性 t_goodmodel.f_mp
	 * 
	 * @return the value of t_goodmodel.f_mp
	 * 
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * 增加内力上限值-装备基础属性 t_goodmodel.f_mp
	 * 
	 * @param mp
	 *            the value for t_goodmodel.f_mp
	 * 
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * 增加体力上限值-装备基础属性 t_goodmodel.f_sp
	 * 
	 * @return the value of t_goodmodel.f_sp
	 * 
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * 增加体力上限值-装备基础属性 t_goodmodel.f_sp
	 * 
	 * @param sp
	 *            the value for t_goodmodel.f_sp
	 * 
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * 装备品阶-属于装备的属性 t_goodmodel.f_grade
	 * 
	 * @return the value of t_goodmodel.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 装备品阶-属于装备的属性 t_goodmodel.f_grade
	 * 
	 * @param grade
	 *            the value for t_goodmodel.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 强化等级附加几率 t_goodmodel.f_grade_probability
	 * 
	 * @return the value of t_goodmodel.f_grade_probability
	 * 
	 */
	public Integer getGradeProbability() {
		return gradeProbability;
	}

	/**
	 * 强化等级附加几率 t_goodmodel.f_grade_probability
	 * 
	 * @param gradeProbability
	 *            the value for t_goodmodel.f_grade_probability
	 * 
	 */
	public void setGradeProbability(Integer gradeProbability) {
		this.gradeProbability = gradeProbability;
	}

	/**
	 * 强化等级附加最小等级 t_goodmodel.f_grade_min
	 * 
	 * @return the value of t_goodmodel.f_grade_min
	 * 
	 */
	public Integer getGradeMin() {
		return gradeMin;
	}

	/**
	 * 强化等级附加最小等级 t_goodmodel.f_grade_min
	 * 
	 * @param gradeMin
	 *            the value for t_goodmodel.f_grade_min
	 * 
	 */
	public void setGradeMin(Integer gradeMin) {
		this.gradeMin = gradeMin;
	}

	/**
	 * 强化等级附加最大等级 t_goodmodel.f_grade_max
	 * 
	 * @return the value of t_goodmodel.f_grade_max
	 * 
	 */
	public Integer getGradeMax() {
		return gradeMax;
	}

	/**
	 * 强化等级附加最大等级 t_goodmodel.f_grade_max
	 * 
	 * @param gradeMax
	 *            the value for t_goodmodel.f_grade_max
	 * 
	 */
	public void setGradeMax(Integer gradeMax) {
		this.gradeMax = gradeMax;
	}

	public Integer getHitProbability() {
		return hitProbability;
	}

	public void setHitProbability(Integer hitProbability) {
		this.hitProbability = hitProbability;
	}

	public Integer getHitMin() {
		return hitMin;
	}

	public void setHitMin(Integer hitMin) {
		this.hitMin = hitMin;
	}

	public Integer getHitMax() {
		return hitMax;
	}

	public void setHitMax(Integer hitMax) {
		this.hitMax = hitMax;
	}

	/**
	 * 掉落附加产生几率 t_goodmodel.f_flop_probability
	 * 
	 * @return the value of t_goodmodel.f_flop_probability
	 * 
	 */
	public Integer getFlopProbability() {
		return flopProbability;
	}

	/**
	 * 掉落附加产生几率 t_goodmodel.f_flop_probability
	 * 
	 * @param flopProbability
	 *            the value for t_goodmodel.f_flop_probability
	 * 
	 */
	public void setFlopProbability(Integer flopProbability) {
		this.flopProbability = flopProbability;
	}

	/**
	 * 天生附加几率 t_goodmodel.f_born_probability
	 * 
	 * @return the value of t_goodmodel.f_born_probability
	 * 
	 */
	public Integer getBornProbability() {
		return bornProbability;
	}

	/**
	 * 天生附加几率 t_goodmodel.f_born_probability
	 * 
	 * @param bornProbability
	 *            the value for t_goodmodel.f_born_probability
	 * 
	 */
	public void setBornProbability(Integer bornProbability) {
		this.bornProbability = bornProbability;
	}

	/**
	 * 天生附加最大几率 t_goodmodel.f_bron_max
	 * 
	 * @return the value of t_goodmodel.f_bron_max
	 * 
	 */
	public Integer getBronMax() {
		return bronMax;
	}

	/**
	 * 天生附加最大几率 t_goodmodel.f_bron_max
	 * 
	 * @param bronMax
	 *            the value for t_goodmodel.f_bron_max
	 * 
	 */
	public void setBronMax(Integer bronMax) {
		this.bronMax = bronMax;
	}

	/**
	 * 天生附加最小几率 t_goodmodel.f_bron_min
	 * 
	 * @return the value of t_goodmodel.f_bron_min
	 * 
	 */
	public Integer getBronMin() {
		return bronMin;
	}

	/**
	 * 天生附加最小几率 t_goodmodel.f_bron_min
	 * 
	 * @param bronMin
	 *            the value for t_goodmodel.f_bron_min
	 * 
	 */
	public void setBronMin(Integer bronMin) {
		this.bronMin = bronMin;
	}

	/**
	 * 增加攻击力出现几率 t_goodmodel.f_attack_probability
	 * 
	 * @return the value of t_goodmodel.f_attack_probability
	 * 
	 */
	public Integer getAttackProbability() {
		return attackProbability;
	}

	/**
	 * 增加攻击力出现几率 t_goodmodel.f_attack_probability
	 * 
	 * @param attackProbability
	 *            the value for t_goodmodel.f_attack_probability
	 * 
	 */
	public void setAttackProbability(Integer attackProbability) {
		this.attackProbability = attackProbability;
	}

	/**
	 * 增加攻击力最小值 t_goodmodel.f_attack_min
	 * 
	 * @return the value of t_goodmodel.f_attack_min
	 * 
	 */
	public Integer getAttackMin() {
		return attackMin;
	}

	/**
	 * 增加攻击力最小值 t_goodmodel.f_attack_min
	 * 
	 * @param attackMin
	 *            the value for t_goodmodel.f_attack_min
	 * 
	 */
	public void setAttackMin(Integer attackMin) {
		this.attackMin = attackMin;
	}

	/**
	 * 增加攻击力最大值 t_goodmodel.f_attack_max
	 * 
	 * @return the value of t_goodmodel.f_attack_max
	 * 
	 */
	public Integer getAttackMax() {
		return attackMax;
	}

	/**
	 * 增加攻击力最大值 t_goodmodel.f_attack_max
	 * 
	 * @param attackMax
	 *            the value for t_goodmodel.f_attack_max
	 * 
	 */
	public void setAttackMax(Integer attackMax) {
		this.attackMax = attackMax;
	}

	/**
	 * 增加防御力出现几率 t_goodmodel.f_defence_probability
	 * 
	 * @return the value of t_goodmodel.f_defence_probability
	 * 
	 */
	public Integer getDefenceProbability() {
		return defenceProbability;
	}

	/**
	 * 增加防御力出现几率 t_goodmodel.f_defence_probability
	 * 
	 * @param defenceProbability
	 *            the value for t_goodmodel.f_defence_probability
	 * 
	 */
	public void setDefenceProbability(Integer defenceProbability) {
		this.defenceProbability = defenceProbability;
	}

	/**
	 * 增加防御力最小值 t_goodmodel.f_defence_min
	 * 
	 * @return the value of t_goodmodel.f_defence_min
	 * 
	 */
	public Integer getDefenceMin() {
		return defenceMin;
	}

	/**
	 * 增加防御力最小值 t_goodmodel.f_defence_min
	 * 
	 * @param defenceMin
	 *            the value for t_goodmodel.f_defence_min
	 * 
	 */
	public void setDefenceMin(Integer defenceMin) {
		this.defenceMin = defenceMin;
	}

	/**
	 * 增加防御力最大值 t_goodmodel.f_defence_max
	 * 
	 * @return the value of t_goodmodel.f_defence_max
	 * 
	 */
	public Integer getDefenceMax() {
		return defenceMax;
	}

	/**
	 * 增加防御力最大值 t_goodmodel.f_defence_max
	 * 
	 * @param defenceMax
	 *            the value for t_goodmodel.f_defence_max
	 * 
	 */
	public void setDefenceMax(Integer defenceMax) {
		this.defenceMax = defenceMax;
	}

	/**
	 * 增加暴击出现几率 t_goodmodel.f_crt_probability
	 * 
	 * @return the value of t_goodmodel.f_crt_probability
	 * 
	 */
	public Integer getCrtProbability() {
		return crtProbability;
	}

	/**
	 * 增加暴击出现几率 t_goodmodel.f_crt_probability
	 * 
	 * @param crtProbability
	 *            the value for t_goodmodel.f_crt_probability
	 * 
	 */
	public void setCrtProbability(Integer crtProbability) {
		this.crtProbability = crtProbability;
	}

	/**
	 * 增加暴击最小值 t_goodmodel.f_crt_min
	 * 
	 * @return the value of t_goodmodel.f_crt_min
	 * 
	 */
	public Integer getCrtMin() {
		return crtMin;
	}

	/**
	 * 增加暴击最小值 t_goodmodel.f_crt_min
	 * 
	 * @param crtMin
	 *            the value for t_goodmodel.f_crt_min
	 * 
	 */
	public void setCrtMin(Integer crtMin) {
		this.crtMin = crtMin;
	}

	/**
	 * 增加暴击最大值 t_goodmodel.f_crt_max
	 * 
	 * @return the value of t_goodmodel.f_crt_max
	 * 
	 */
	public Integer getCrtMax() {
		return crtMax;
	}

	/**
	 * 增加暴击最大值 t_goodmodel.f_crt_max
	 * 
	 * @param crtMax
	 *            the value for t_goodmodel.f_crt_max
	 * 
	 */
	public void setCrtMax(Integer crtMax) {
		this.crtMax = crtMax;
	}

	/**
	 * 增加闪避出现几率 t_goodmodel.f_dodge_probability
	 * 
	 * @return the value of t_goodmodel.f_dodge_probability
	 * 
	 */
	public Integer getDodgeProbability() {
		return dodgeProbability;
	}

	/**
	 * 增加闪避出现几率 t_goodmodel.f_dodge_probability
	 * 
	 * @param dodgeProbability
	 *            the value for t_goodmodel.f_dodge_probability
	 * 
	 */
	public void setDodgeProbability(Integer dodgeProbability) {
		this.dodgeProbability = dodgeProbability;
	}

	/**
	 * 增加闪避最小值 t_goodmodel.f_dodge_min
	 * 
	 * @return the value of t_goodmodel.f_dodge_min
	 * 
	 */
	public Integer getDodgeMin() {
		return dodgeMin;
	}

	/**
	 * 增加闪避最小值 t_goodmodel.f_dodge_min
	 * 
	 * @param dodgeMin
	 *            the value for t_goodmodel.f_dodge_min
	 * 
	 */
	public void setDodgeMin(Integer dodgeMin) {
		this.dodgeMin = dodgeMin;
	}

	/**
	 * 增加闪避最大值 t_goodmodel.f_dodge_max
	 * 
	 * @return the value of t_goodmodel.f_dodge_max
	 * 
	 */
	public Integer getDodgeMax() {
		return dodgeMax;
	}

	/**
	 * 增加闪避最大值 t_goodmodel.f_dodge_max
	 * 
	 * @param dodgeMax
	 *            the value for t_goodmodel.f_dodge_max
	 * 
	 */
	public void setDodgeMax(Integer dodgeMax) {
		this.dodgeMax = dodgeMax;
	}

	/**
	 * 增加攻击速度出现几率 t_goodmodel.f_atk_coldtime_probability
	 * 
	 * @return the value of t_goodmodel.f_atk_coldtime_probability
	 * 
	 */
	public Integer getAtkColdtimeProbability() {
		return atkColdtimeProbability;
	}

	/**
	 * 增加攻击速度出现几率 t_goodmodel.f_atk_coldtime_probability
	 * 
	 * @param atkColdtimeProbability
	 *            the value for t_goodmodel.f_atk_coldtime_probability
	 * 
	 */
	public void setAtkColdtimeProbability(Integer atkColdtimeProbability) {
		this.atkColdtimeProbability = atkColdtimeProbability;
	}

	/**
	 * 增加攻击速度最小值 t_goodmodel.f_atk_coldtime_min
	 * 
	 * @return the value of t_goodmodel.f_atk_coldtime_min
	 * 
	 */
	public Integer getAtkColdtimeMin() {
		return atkColdtimeMin;
	}

	/**
	 * 增加攻击速度最小值 t_goodmodel.f_atk_coldtime_min
	 * 
	 * @param atkColdtimeMin
	 *            the value for t_goodmodel.f_atk_coldtime_min
	 * 
	 */
	public void setAtkColdtimeMin(Integer atkColdtimeMin) {
		this.atkColdtimeMin = atkColdtimeMin;
	}

	/**
	 * 增加攻击速度最大值 t_goodmodel.f_atk_coldtime_max
	 * 
	 * @return the value of t_goodmodel.f_atk_coldtime_max
	 * 
	 */
	public Integer getAtkColdtimeMax() {
		return atkColdtimeMax;
	}

	/**
	 * 增加攻击速度最大值 t_goodmodel.f_atk_coldtime_max
	 * 
	 * @param atkColdtimeMax
	 *            the value for t_goodmodel.f_atk_coldtime_max
	 * 
	 */
	public void setAtkColdtimeMax(Integer atkColdtimeMax) {
		this.atkColdtimeMax = atkColdtimeMax;
	}

	/**
	 * 增加移动速度出现几率 t_goodmodel.f_move_speed_probability
	 * 
	 * @return the value of t_goodmodel.f_move_speed_probability
	 * 
	 */
	public Integer getMoveSpeedProbability() {
		return moveSpeedProbability;
	}

	/**
	 * 增加移动速度出现几率 t_goodmodel.f_move_speed_probability
	 * 
	 * @param moveSpeedProbability
	 *            the value for t_goodmodel.f_move_speed_probability
	 * 
	 */
	public void setMoveSpeedProbability(Integer moveSpeedProbability) {
		this.moveSpeedProbability = moveSpeedProbability;
	}

	/**
	 * 增加移动速度最小值 t_goodmodel.f_move_speed_min
	 * 
	 * @return the value of t_goodmodel.f_move_speed_min
	 * 
	 */
	public Integer getMoveSpeedMin() {
		return moveSpeedMin;
	}

	/**
	 * 增加移动速度最小值 t_goodmodel.f_move_speed_min
	 * 
	 * @param moveSpeedMin
	 *            the value for t_goodmodel.f_move_speed_min
	 * 
	 */
	public void setMoveSpeedMin(Integer moveSpeedMin) {
		this.moveSpeedMin = moveSpeedMin;
	}

	/**
	 * 增加移动速度最大值 t_goodmodel.f_move_speed_max
	 * 
	 * @return the value of t_goodmodel.f_move_speed_max
	 * 
	 */
	public Integer getMoveSpeedMax() {
		return moveSpeedMax;
	}

	/**
	 * 增加移动速度最大值 t_goodmodel.f_move_speed_max
	 * 
	 * @param moveSpeedMax
	 *            the value for t_goodmodel.f_move_speed_max
	 * 
	 */
	public void setMoveSpeedMax(Integer moveSpeedMax) {
		this.moveSpeedMax = moveSpeedMax;
	}

	/**
	 * 增加生命上限值出现几率 t_goodmodel.f_hp_probability
	 * 
	 * @return the value of t_goodmodel.f_hp_probability
	 * 
	 */
	public Integer getHpProbability() {
		return hpProbability;
	}

	/**
	 * 增加生命上限值出现几率 t_goodmodel.f_hp_probability
	 * 
	 * @param hpProbability
	 *            the value for t_goodmodel.f_hp_probability
	 * 
	 */
	public void setHpProbability(Integer hpProbability) {
		this.hpProbability = hpProbability;
	}

	/**
	 * 增加生命上限值最小值 t_goodmodel.f_hp_min
	 * 
	 * @return the value of t_goodmodel.f_hp_min
	 * 
	 */
	public Integer getHpMin() {
		return hpMin;
	}

	/**
	 * 增加生命上限值最小值 t_goodmodel.f_hp_min
	 * 
	 * @param hpMin
	 *            the value for t_goodmodel.f_hp_min
	 * 
	 */
	public void setHpMin(Integer hpMin) {
		this.hpMin = hpMin;
	}

	/**
	 * 增加生命上限值最大值 t_goodmodel.f_hp_max
	 * 
	 * @return the value of t_goodmodel.f_hp_max
	 * 
	 */
	public Integer getHpMax() {
		return hpMax;
	}

	/**
	 * 增加生命上限值最大值 t_goodmodel.f_hp_max
	 * 
	 * @param hpMax
	 *            the value for t_goodmodel.f_hp_max
	 * 
	 */
	public void setHpMax(Integer hpMax) {
		this.hpMax = hpMax;
	}

	/**
	 * 增加内力上限值出现几率 t_goodmodel.f_mp_probability
	 * 
	 * @return the value of t_goodmodel.f_mp_probability
	 * 
	 */
	public Integer getMpProbability() {
		return mpProbability;
	}

	/**
	 * 增加内力上限值出现几率 t_goodmodel.f_mp_probability
	 * 
	 * @param mpProbability
	 *            the value for t_goodmodel.f_mp_probability
	 * 
	 */
	public void setMpProbability(Integer mpProbability) {
		this.mpProbability = mpProbability;
	}

	/**
	 * 增加内力上限值最小值 t_goodmodel.f_mp_min
	 * 
	 * @return the value of t_goodmodel.f_mp_min
	 * 
	 */
	public Integer getMpMin() {
		return mpMin;
	}

	/**
	 * 增加内力上限值最小值 t_goodmodel.f_mp_min
	 * 
	 * @param mpMin
	 *            the value for t_goodmodel.f_mp_min
	 * 
	 */
	public void setMpMin(Integer mpMin) {
		this.mpMin = mpMin;
	}

	/**
	 * 增加内力上限值最大值 t_goodmodel.f_mp_max
	 * 
	 * @return the value of t_goodmodel.f_mp_max
	 * 
	 */
	public Integer getMpMax() {
		return mpMax;
	}

	/**
	 * 增加内力上限值最大值 t_goodmodel.f_mp_max
	 * 
	 * @param mpMax
	 *            the value for t_goodmodel.f_mp_max
	 * 
	 */
	public void setMpMax(Integer mpMax) {
		this.mpMax = mpMax;
	}

	/**
	 * 增加体力上限值出现几率 t_goodmodel.f_sp_probability
	 * 
	 * @return the value of t_goodmodel.f_sp_probability
	 * 
	 */
	public Integer getSpProbability() {
		return spProbability;
	}

	/**
	 * 增加体力上限值出现几率 t_goodmodel.f_sp_probability
	 * 
	 * @param spProbability
	 *            the value for t_goodmodel.f_sp_probability
	 * 
	 */
	public void setSpProbability(Integer spProbability) {
		this.spProbability = spProbability;
	}

	/**
	 * 增加体力上限值最小值 t_goodmodel.f_sp_min
	 * 
	 * @return the value of t_goodmodel.f_sp_min
	 * 
	 */
	public Integer getSpMin() {
		return spMin;
	}

	/**
	 * 增加体力上限值最小值 t_goodmodel.f_sp_min
	 * 
	 * @param spMin
	 *            the value for t_goodmodel.f_sp_min
	 * 
	 */
	public void setSpMin(Integer spMin) {
		this.spMin = spMin;
	}

	/**
	 * 增加体力上限值最大值 t_goodmodel.f_sp_max
	 * 
	 * @return the value of t_goodmodel.f_sp_max
	 * 
	 */
	public Integer getSpMax() {
		return spMax;
	}

	/**
	 * 增加体力上限值最大值 t_goodmodel.f_sp_max
	 * 
	 * @param spMax
	 *            the value for t_goodmodel.f_sp_max
	 * 
	 */
	public void setSpMax(Integer spMax) {
		this.spMax = spMax;
	}

	/**
	 * 装备佩戴位置1武器,2头饰,3衣服,4腰带,5鞋子,6护手,7项链,8戒指,9手镯,10披风,11坐骑,12时装,13法宝 t_goodmodel.f_position 1武器-》,2骑站武器-》无,3衣服-》胸甲,4护手-》护腕,5护腰-》腰带,6护腰-》鞋,7头饰-》
	 * 头盔,8项链-》腿甲,9戒指-》戒指,10手镯-》戒指,11腰坠-》饰品,12披风,13特殊-》法宝
	 * 
	 * @return the value of t_goodmodel.f_position
	 * 
	 */
	public Byte getPosition() {
		return position;
	}

	/**
	 * 装备佩戴位置1武器,2头饰,3衣服,4腰带,5鞋子,6护手,7项链,8戒指,9手镯,10披风,11坐骑,12时装,13法宝 t_goodmodel.f_position 1武器-》,2骑站武器-》无,3衣服-》胸甲,4护手-》护腕,5护腰-》腰带,6护腰-》鞋,7头饰-》
	 * 头盔,8项链-》腿甲,9戒指-》戒指,10手镯-》戒指,11腰坠-》饰品,12披风,13特殊-》法宝
	 * 
	 * @param position
	 *            the value for t_goodmodel.f_position
	 * 
	 */
	public void setPosition(Byte position) {
		this.position = position;
	}

	/**
	 * 装备最大耐久度 t_goodmodel.f_durability
	 * 
	 * @return the value of t_goodmodel.f_durability
	 * 
	 */
	public Integer getDurability() {
		return durability;
	}

	/**
	 * 装备最大耐久度 t_goodmodel.f_durability
	 * 
	 * @param durability
	 *            the value for t_goodmodel.f_durability
	 * 
	 */
	public void setDurability(Integer durability) {
		this.durability = durability;
	}

	/**
	 * 修理单价，该项表示恢复一点耐久需要多少铜币 t_goodmodel.f_repair_money
	 * 
	 * @return the value of t_goodmodel.f_repair_money
	 * 
	 */
	public Integer getRepairMoney() {
		return repairMoney;
	}

	/**
	 * 修理单价，该项表示恢复一点耐久需要多少铜币 t_goodmodel.f_repair_money
	 * 
	 * @param repairMoney
	 *            the value for t_goodmodel.f_repair_money
	 * 
	 */
	public void setRepairMoney(Integer repairMoney) {
		this.repairMoney = repairMoney;
	}

	/**
	 * 等级限制 t_goodmodel.f_limit_grade
	 * 
	 * @return the value of t_goodmodel.f_limit_grade
	 * 
	 */
	public Integer getLimitGrade() {
		return limitGrade;
	}

	/**
	 * 等级限制 t_goodmodel.f_limit_grade
	 * 
	 * @param limitGrade
	 *            the value for t_goodmodel.f_limit_grade
	 * 
	 */
	public void setLimitGrade(Integer limitGrade) {
		this.limitGrade = limitGrade;
	}

	/**
	 * 穿此装备-需要的进攻加点 t_goodmodel.f_attack_addpoint
	 * 
	 * @return the value of t_goodmodel.f_attack_addpoint
	 * 
	 */
	public Short getAttackAddpoint() {
		return attackAddpoint;
	}

	/**
	 * 穿此装备-需要的进攻加点 t_goodmodel.f_attack_addpoint
	 * 
	 * @param attackAddpoint
	 *            the value for t_goodmodel.f_attack_addpoint
	 * 
	 */
	public void setAttackAddpoint(Short attackAddpoint) {
		this.attackAddpoint = attackAddpoint;
	}

	/**
	 * 穿此装备-需要的防御加点 t_goodmodel.f_defence_addpoint
	 * 
	 * @return the value of t_goodmodel.f_defence_addpoint
	 * 
	 */
	public Short getDefenceAddpoint() {
		return defenceAddpoint;
	}

	/**
	 * 穿此装备-需要的防御加点 t_goodmodel.f_defence_addpoint
	 * 
	 * @param defenceAddpoint
	 *            the value for t_goodmodel.f_defence_addpoint
	 * 
	 */
	public void setDefenceAddpoint(Short defenceAddpoint) {
		this.defenceAddpoint = defenceAddpoint;
	}

	/**
	 * 穿此装备-需要的轻身加点 t_goodmodel.f_light_addpoint
	 * 
	 * @return the value of t_goodmodel.f_light_addpoint
	 * 
	 */
	public Short getLightAddpoint() {
		return lightAddpoint;
	}

	/**
	 * 穿此装备-需要的轻身加点 t_goodmodel.f_light_addpoint
	 * 
	 * @param lightAddpoint
	 *            the value for t_goodmodel.f_light_addpoint
	 * 
	 */
	public void setLightAddpoint(Short lightAddpoint) {
		this.lightAddpoint = lightAddpoint;
	}

	/**
	 * 穿此装备-需要的健体加点需求 t_goodmodel.f_strong_addpoint
	 * 
	 * @return the value of t_goodmodel.f_strong_addpoint
	 * 
	 */
	public Short getStrongAddpoint() {
		return strongAddpoint;
	}

	/**
	 * 穿此装备-需要的健体加点需求 t_goodmodel.f_strong_addpoint
	 * 
	 * @param strongAddpoint
	 *            the value for t_goodmodel.f_strong_addpoint
	 * 
	 */
	public void setStrongAddpoint(Short strongAddpoint) {
		this.strongAddpoint = strongAddpoint;
	}

	/**
	 * 宝石种类,1增加攻击力,2增加防御力,3增加暴击,4增加闪避,5增加生命上限值,6增加体力上限值,7增加内力上限值,8增加攻击速度,9增加移动速度 ,10命中 t_goodmodel.f_gems_type
	 * 
	 * @return the value of t_goodmodel.f_gems_type
	 * 
	 */
	public Integer getGemsType() {
		return gemsType;
	}

	/**
	 * 宝石种类,1增加攻击力,2增加防御力,3增加暴击,4增加闪避,5增加生命上限值,6增加体力上限值,7增加内力上限值,8增加攻击速度,9增加移动速度 ,10命中 t_goodmodel.f_gems_type
	 * 
	 * @param gemsType
	 *            the value for t_goodmodel.f_gems_type
	 * 
	 */
	public void setGemsType(Integer gemsType) {
		this.gemsType = gemsType;
	}

	/**
	 * 宝石加成数值 t_goodmodel.f_gems_value
	 * 
	 * @return the value of t_goodmodel.f_gems_value
	 * 
	 */
	public Integer getGemsValue() {
		return gemsValue;
	}

	/**
	 * 宝石加成数值 t_goodmodel.f_gems_value
	 * 
	 * @param gemsValue
	 *            the value for t_goodmodel.f_gems_value
	 * 
	 */
	public void setGemsValue(Integer gemsValue) {
		this.gemsValue = gemsValue;
	}

	/**
	 * 使用后习得武功或心法编号 t_goodmodel.f_skill
	 * 
	 * @return the value of t_goodmodel.f_skill
	 * 
	 */
	public Integer getSkill() {
		return skill;
	}

	/**
	 * 使用后习得武功或心法编号 t_goodmodel.f_skill
	 * 
	 * @param skill
	 *            the value for t_goodmodel.f_skill
	 * 
	 */
	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	/**
	 * 使用后触发的任务 t_goodmodel.f_task
	 * 
	 * @return the value of t_goodmodel.f_task
	 * 
	 */
	public Integer getTask() {
		return task;
	}

	/**
	 * 使用后触发的任务 t_goodmodel.f_task
	 * 
	 * @param task
	 *            the value for t_goodmodel.f_task
	 * 
	 */
	public void setTask(Integer task) {
		this.task = task;
	}

	/**
	 * 使用后触发的脚本类 t_goodmodel.f_scriptclass
	 * 
	 * @return the value of t_goodmodel.f_scriptclass
	 * 
	 */
	public String getScriptclass() {
		return scriptclass;
	}

	/**
	 * 使用后触发的脚本类 t_goodmodel.f_scriptclass
	 * 
	 * @param scriptclass
	 *            the value for t_goodmodel.f_scriptclass
	 * 
	 */
	public void setScriptclass(String scriptclass) {
		this.scriptclass = scriptclass;
	}

	/**
	 * 脚本类的初始化参数 t_goodmodel.f_scriptclassparam
	 * 
	 * @return the value of t_goodmodel.f_scriptclassparam
	 * 
	 */
	public String getScriptclassparam() {
		return scriptclassparam;
	}

	/**
	 * 脚本类的初始化参数 t_goodmodel.f_scriptclassparam
	 * 
	 * @param scriptclassparam
	 *            the value for t_goodmodel.f_scriptclassparam
	 * 
	 */
	public void setScriptclassparam(String scriptclassparam) {
		this.scriptclassparam = scriptclassparam;
	}

	/**
	 * 药品类型 t_goodmodel.f_drug_type * 【1,增加攻击力2,增加防御力3,增加暴击4,增加闪避5,增加攻击速度6,增加移动速度7,增加生命上限值8,增加内力上限值9 ,增加体力上限值10,增加潜能点个数 11 ,增加等级
	 * 12,增加经验13,增加坐骑经验14,增加真气储量15,增加战场声望16,恢复生命值17,恢复内力值18, 恢复体力值19,恢复坐骑活力20,解除全部负面状态21,双倍经验获得22,双倍真气储量,64肉身
	 * 
	 * @return the value of t_goodmodel.f_drug_type
	 * 
	 */
	public Integer getDrugType() {
		return drugType;
	}

	/**
	 * 药品类型 t_goodmodel.f_drug_type
	 * 
	 * @param drugType
	 *            the value for t_goodmodel.f_drug_type
	 * 
	 */
	public void setDrugType(Integer drugType) {
		this.drugType = drugType;
	}

	/**
	 * 药品映射的SKILL_EFFECT ID 若为0表示永久增加的药品 目前各种BUFF均是只增加固定值,不存在百分比加成BUF t_goodmodel.f_drug_buff_id
	 * 
	 * @return the value of t_goodmodel.f_drug_buff_id
	 * 
	 */
	public Integer getDrugBuffId() {
		return drugBuffId;
	}

	/**
	 * 药品映射的SKILL_EFFECT ID 若为0表示永久增加的药品 目前各种BUFF均是只增加固定值,不存在百分比加成BUF t_goodmodel.f_drug_buff_id
	 * 
	 * @param drugBuffId
	 *            the value for t_goodmodel.f_drug_buff_id
	 * 
	 */
	public void setDrugBuffId(Integer drugBuffId) {
		this.drugBuffId = drugBuffId;
	}

	/**
	 * 药品的值 t_goodmodel.f_drug_value
	 * 
	 * @return the value of t_goodmodel.f_drug_value
	 * 
	 */
	public Integer getDrugValue() {
		return drugValue;
	}

	/**
	 * 药品的值 t_goodmodel.f_drug_value
	 * 
	 * @param drugValue
	 *            the value for t_goodmodel.f_drug_value
	 * 
	 */
	public void setDrugValue(Integer drugValue) {
		this.drugValue = drugValue;
	}

	/**
	 * 物品冷却时间-属性药品的属性 t_goodmodel.f_coolingtime
	 * 
	 * @return the value of t_goodmodel.f_coolingtime
	 * 
	 */
	public Integer getCoolingtime() {
		return coolingtime;
	}

	/**
	 * 物品冷却时间-属性药品的属性 t_goodmodel.f_coolingtime
	 * 
	 * @param coolingtime
	 *            the value for t_goodmodel.f_coolingtime
	 * 
	 */
	public void setCoolingtime(Integer coolingtime) {
		this.coolingtime = coolingtime;
	}

	/**
	 * 坐骑精魂 t_goodmodel.f_horse_spirit
	 * 
	 * @return the value of t_goodmodel.f_horse_spirit
	 * 
	 */
	public Integer getHorseSpirit() {
		return horseSpirit;
	}

	/**
	 * 坐骑精魂 t_goodmodel.f_horse_spirit
	 * 
	 * @param horseSpirit
	 *            the value for t_goodmodel.f_horse_spirit
	 * 
	 */
	public void setHorseSpirit(Integer horseSpirit) {
		this.horseSpirit = horseSpirit;
	}

	/**
	 * 最高的强化等级 t_goodmodel.f_strengthen_grade
	 * 
	 * @return the value of t_goodmodel.f_strengthen_grade
	 * 
	 */
	public Integer getStrengthenGrade() {
		return strengthenGrade;
	}

	/**
	 * 最高的强化等级 t_goodmodel.f_strengthen_grade
	 * 
	 * @param strengthenGrade
	 *            the value for t_goodmodel.f_strengthen_grade
	 * 
	 */
	public void setStrengthenGrade(Integer strengthenGrade) {
		this.strengthenGrade = strengthenGrade;
	}

	/**
	 * 为空表示没有特定的该属性 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) t_goodmodel.f_strengthen_desc
	 * 
	 * @return the value of t_goodmodel.f_strengthen_desc
	 * 
	 */
	public String getStrengthenDesc() {
		return strengthenDesc;
	}

	/**
	 * 为空表示没有特定的该属性 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) t_goodmodel.f_strengthen_desc
	 * 
	 * @param strengthenDesc
	 *            the value for t_goodmodel.f_strengthen_desc
	 * 
	 */
	public void setStrengthenDesc(String strengthenDesc) {
		this.strengthenDesc = strengthenDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(附加属性类型，属性值；) t_goodmodel.f_addition_desc
	 * 
	 * @return the value of t_goodmodel.f_addition_desc
	 * 
	 */
	public String getAdditionDesc() {
		return additionDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(附加属性类型，属性值；) t_goodmodel.f_addition_desc
	 * 
	 * @param additionDesc
	 *            the value for t_goodmodel.f_addition_desc
	 * 
	 */
	public void setAdditionDesc(String additionDesc) {
		this.additionDesc = additionDesc;
	}

	/**
	 * 镶嵌的宝石模型ID （宝石模型id;宝石模型id;） t_goodmodel.f_inlay_gems_desc
	 * 
	 * @return the value of t_goodmodel.f_inlay_gems_desc
	 */
	public String getInlayGemsDesc() {
		return inlayGemsDesc;
	}

	/**
	 * 镶嵌的宝石模型ID （宝石模型id;宝石模型id;） t_goodmodel.f_inlay_gems_desc
	 * 
	 * @param inlayGemsDesc
	 *            the value for t_goodmodel.f_inlay_gems_desc
	 * 
	 */
	public void setInlayGemsDesc(String inlayGemsDesc) {
		this.inlayGemsDesc = inlayGemsDesc;
	}

	/**
	 * npc处购买物品时要求的最低声望值 t_goodmodel.f_prestige_limit
	 * 
	 * @return the value of t_goodmodel.f_prestige_limit
	 * 
	 */
	public Integer getPrestigeLimit() {
		return prestigeLimit;
	}

	/**
	 * npc处购买物品时要求的最低声望值 t_goodmodel.f_prestige_limit
	 * 
	 * @param prestigeLimit
	 *            the value for t_goodmodel.f_prestige_limit
	 * 
	 */
	public void setPrestigeLimit(Integer prestigeLimit) {
		this.prestigeLimit = prestigeLimit;
	}

	/**
	 * 关联升级礼包 t_goodmodel.gift_packs_id
	 * 
	 * @return the value of t_goodmodel.gift_packs_id
	 * 
	 */
	public Integer getGiftPacksId() {
		return giftPacksId;
	}

	/**
	 * 关联升级礼包 t_goodmodel.gift_packs_id
	 * 
	 * @param giftPacksId
	 *            the value for t_goodmodel.gift_packs_id
	 * 
	 */
	public void setGiftPacksId(Integer giftPacksId) {
		this.giftPacksId = giftPacksId;
	}

	/**
	 * 物品落掉的特效ID t_goodmodel.f_drop_effect_id
	 * 
	 * @return the value of t_goodmodel.f_drop_effect_id
	 * 
	 */
	public Integer getDropEffectId() {
		return dropEffectId;
	}

	/**
	 * 物品落掉的特效ID t_goodmodel.f_drop_effect_id
	 * 
	 * @param dropEffectId
	 *            the value for t_goodmodel.f_drop_effect_id
	 * 
	 */
	public void setDropEffectId(Integer dropEffectId) {
		this.dropEffectId = dropEffectId;
	}

	/**
	 * 公共冷却时间影响的类型 t_goodmodel.f_common_cool_type
	 * 
	 * @return the value of t_goodmodel.f_common_cool_type
	 * 
	 */
	public Integer getCommonCoolType() {
		return commonCoolType;
	}

	/**
	 * 公共冷却时间影响的类型 t_goodmodel.f_common_cool_type
	 * 
	 * @param commonCoolType
	 *            the value for t_goodmodel.f_common_cool_type
	 * 
	 */
	public void setCommonCoolType(Integer commonCoolType) {
		this.commonCoolType = commonCoolType;
	}

	/**
	 * 公共冷却时间 单位毫秒 t_goodmodel.f_common_cool_time
	 * 
	 * @return the value of t_goodmodel.f_common_cool_time
	 * 
	 */
	public Integer getCommonCoolTime() {
		return commonCoolTime;
	}

	/**
	 * 公共冷却时间 单位毫秒 t_goodmodel.f_common_cool_time
	 * 
	 * @param commonCoolTime
	 *            the value for t_goodmodel.f_common_cool_time
	 * 
	 */
	public void setCommonCoolTime(Integer commonCoolTime) {
		this.commonCoolTime = commonCoolTime;
	}

	/**
	 * 物品消失时间 yyyy-mm-dd hh:ss:mm t_goodmodel.f_end_date
	 * 
	 * @return the value of t_goodmodel.f_end_date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 物品消失时间 yyyy-mm-dd hh:ss:mm t_goodmodel.f_end_date
	 * 
	 * @param endDate
	 *            the value for t_goodmodel.f_end_date
	 * 
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 存在时间 单位： 秒 t_goodmodel.f_live_time
	 * 
	 * @return the value of t_goodmodel.f_live_time
	 * 
	 */
	public Integer getLiveTime() {
		return liveTime;
	}

	/**
	 * 存在时间 单位： 秒 t_goodmodel.f_live_time
	 * 
	 * @param liveTime
	 *            the value for t_goodmodel.f_live_time
	 * 
	 */
	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}

	/**
	 * 增加武功层数值 t_goodmodel.f_wugong_grade
	 * 
	 * @return the value of t_goodmodel.f_wugong_grade
	 * 
	 */
	public Integer getWugongGrade() {
		return wugongGrade;
	}

	/**
	 * 增加武功层数值 t_goodmodel.f_wugong_grade
	 * 
	 * @param wugongGrade
	 *            the value for t_goodmodel.f_wugong_grade
	 * 
	 */
	public void setWugongGrade(Integer wugongGrade) {
		this.wugongGrade = wugongGrade;
	}

	/**
	 * 附加武功类型取值区间格式：(武功ID,适用门派;武功ID,适用门派;） t_goodmodel.f_wugong_desc
	 * 
	 * @return the value of t_goodmodel.f_wugong_desc
	 * 
	 */
	public String getWugongDesc() {
		return wugongDesc;
	}

	/**
	 * 附加武功类型取值区间格式：(武功ID,适用门派;武功ID,适用门派;） t_goodmodel.f_wugong_desc
	 * 
	 * @param wugongDesc
	 *            the value for t_goodmodel.f_wugong_desc
	 * 
	 */
	public void setWugongDesc(String wugongDesc) {
		this.wugongDesc = wugongDesc;
	}

	/**
	 * 充气娃娃每天提取的最大次数 t_goodmodel.f_chongqi_max
	 * 
	 * @return the value of t_goodmodel.f_chongqi_max
	 * 
	 */
	public Integer getChongqiMax() {
		return chongqiMax;
	}

	/**
	 * 充气娃娃每天提取的最大次数 t_goodmodel.f_chongqi_max
	 * 
	 * @param chongqiMax
	 *            the value for t_goodmodel.f_chongqi_max
	 * 
	 */
	public void setChongqiMax(Integer chongqiMax) {
		this.chongqiMax = chongqiMax;
	}

	/**
	 * 名称国际化 t_goodmodel.f_name_i18n
	 * 
	 * @return the value of t_goodmodel.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 名称国际化 t_goodmodel.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_goodmodel.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 描述际化国 t_goodmodel.f_desc_i18n
	 * 
	 * @return the value of t_goodmodel.f_desc_i18n
	 * 
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 描述际化国 t_goodmodel.f_desc_i18n
	 * 
	 * @param descI18n
	 *            the value for t_goodmodel.f_desc_i18n
	 * 
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 弓箭触发几率 t_goodmodel.f_arrowskill_prob
	 * 
	 * @return the value of t_goodmodel.f_arrowskill_prob
	 * 
	 */
	public Integer getArrowskillProb() {
		return arrowskillProb;
	}

	/**
	 * 弓箭触发几率 t_goodmodel.f_arrowskill_prob
	 * 
	 * @param arrowskillProb
	 *            the value for t_goodmodel.f_arrowskill_prob
	 * 
	 */
	public void setArrowskillProb(Integer arrowskillProb) {
		this.arrowskillProb = arrowskillProb;
	}

	/**
	 * 物品天生ICO光效 t_goodmodel.f_born_ico
	 * 
	 * @return the value of t_goodmodel.f_born_ico
	 * 
	 */
	public Integer getBornIco() {
		return bornIco;
	}

	/**
	 * 物品天生ICO光效 t_goodmodel.f_born_ico
	 * 
	 * @param bornIco
	 *            the value for t_goodmodel.f_born_ico
	 * 
	 */
	public void setBornIco(Integer bornIco) {
		this.bornIco = bornIco;
	}

	/**
	 * 物品天生颜色品级(0 白色 1 蓝色 2 绿色 3 紫色 4 橙色) t_goodmodel.f_born_color_grade
	 * 
	 * @return the value of t_goodmodel.f_born_color_grade
	 * 
	 */
	public Integer getBornColorGrade() {
		return bornColorGrade;
	}

	/**
	 * 物品天生颜色品级(0 白色 1 蓝色 2 绿色 3 紫色 4 橙色) t_goodmodel.f_born_color_grade
	 * 
	 * @param bornColorGrade
	 *            the value for t_goodmodel.f_born_color_grade
	 * 
	 */
	public void setBornColorGrade(Integer bornColorGrade) {
		this.bornColorGrade = bornColorGrade;
	}

	/**
	 * 物品是否允许出售 0不允许/1允许 t_goodmodel.f_is_sale
	 * 
	 * @return the value of t_goodmodel.f_is_sale
	 * 
	 */
	public Byte getIsSale() {
		return isSale;
	}

	/**
	 * 物品是否允许出售 0不允许/1允许 t_goodmodel.f_is_sale
	 * 
	 * @param isSale
	 *            the value for t_goodmodel.f_is_sale
	 * 
	 */
	public void setIsSale(Byte isSale) {
		this.isSale = isSale;
	}

	/**
	 * 出售是否弹出确认面板0不是/1是 t_goodmodel.f_sale_is_ui
	 * 
	 * @return the value of t_goodmodel.f_sale_is_ui
	 * 
	 */
	public Byte getSaleIsUi() {
		return saleIsUi;
	}

	/**
	 * 出售是否弹出确认面板0不是/1是 t_goodmodel.f_sale_is_ui
	 * 
	 * @param saleIsUi
	 *            the value for t_goodmodel.f_sale_is_ui
	 * 
	 */
	public void setSaleIsUi(Byte saleIsUi) {
		this.saleIsUi = saleIsUi;
	}

	/**
	 * 物品销毁或丢弃时是否弹出二次确认面板，0不弹出，1弹出 t_goodmodel.f_discard_is_ui
	 * 
	 * @return the value of t_goodmodel.f_discard_is_ui
	 * 
	 */
	public Boolean getDiscardIsUi() {
		return discardIsUi;
	}

	/**
	 * 物品销毁或丢弃时是否弹出二次确认面板，0不弹出，1弹出 t_goodmodel.f_discard_is_ui
	 * 
	 * @param discardIsUi
	 *            the value for t_goodmodel.f_discard_is_ui
	 * 
	 */
	public void setDiscardIsUi(Boolean discardIsUi) {
		this.discardIsUi = discardIsUi;
	}

	/**
	 * 可否被放入跨服包裹 0不能 ，1能被放入跨服包裹 t_goodmodel.f_is_across
	 * 
	 * @return the value of t_goodmodel.f_is_across
	 * 
	 */
	public Byte getIsAcross() {
		return isAcross;
	}

	/**
	 * 可否被放入跨服包裹 0不能 ，1能被放入跨服包裹 t_goodmodel.f_is_across
	 * 
	 * @param isAcross
	 *            the value for t_goodmodel.f_is_across
	 * 
	 */
	public void setIsAcross(Byte isAcross) {
		this.isAcross = isAcross;
	}

	public boolean isTaskgoods() {
		return this.getKind() == 4;
	}

	/**
	 * 是否是装备
	 * 
	 * @return
	 */
	public boolean isEquipment() {
		return this.getKind() == 2;
	}

	/**
	 * 是否是宝石
	 * 
	 * @return
	 */
	public boolean isGemStone() {
		return this.getKind() == 6;
	}

	/**
	 * 是否是附身符
	 * 
	 * @return
	 */
	public boolean isFuShenfu() {
		return false;// this.isEquipment() && getPosition() == 13;
	}

	/**
	 * 舍利子
	 * 
	 * @return
	 */
	public boolean isShelizi() {
		return this.getKind() == 23;
	}

	/**
	 * 是否箭支
	 * 
	 * @return
	 */
	public boolean isArrow() {
		return this.getKind() == 28;
	}

	public boolean isAcross() {
		return getIsAcross() == 1;
	}

	public boolean isSkillBook() {
		return this.getKind() == 5 && (this.getSkill() != null || this.getSkill() != 0);
	}

	public boolean isZhenFaBook() {
		return this.getKind() == 5 && (this.getSkill() == null || this.getSkill() == 0);
	}

	// 是否显示在换装上
	public boolean isShowInAvator() {
		short t = position;
		return t == Position.POSTION_WUQI || t == Position.POSTION_QIBING || t == Position.POSTION_YIFU;
	}

	// 是否是坐骑装备
	public boolean isHorseEquip() {
		short t = position;
		return t == Position.POSTION_HOURSE_ANJU || t == Position.POSTION_HOURSE_JIANGSHENG || t == Position.POSTION_HOURSE_TIJU || t == Position.POSTION_HOURSE_TITIE;
	}

	public boolean isRepeatGoods() {
		if (this.repeat == 1) {
			return true;
		}
		return false;
	}

	public boolean isChongqiwawa() {
		return this.getKind() == 16;
	}

	public boolean isDrug() {
		return this.getKind() == 3;
	}

	public boolean isGiftBag() {
		return this.getKind() == 11;
	}

	public boolean isHascoolingtime() {
		return coolingtime != 0 && coolingtime > 0;
	}

	/**
	 * 增加命中单位1/10000-装备基础属性 t_goodmodel.f_hit
	 * 
	 * @return
	 */
	public Integer getHit() {
		return hit;
	}

	/**
	 * 增加命中单位1/10000-装备基础属性 t_goodmodel.f_hit
	 * 
	 * @param hit
	 */
	public void setHit(Integer hit) {
		this.hit = hit;
	}

	/**
	 * 灵宠产生内丹道具的id
	 * 
	 * @return
	 */
	public int getChangeModelId() {
		return changeModelId;
	}

	public void setChangeModelId(int changeModelId) {
		this.changeModelId = changeModelId;
	}

	public int getMinCount() {
		return minCount;
	}

	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		this.copper = copper;
	}

	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * 
	 * 
	 * @param property
	 * @param goodmodel
	 * @return
	 */
	public int getBasePropertyValue(Property property) {

		switch (property) {
		case attack:
			return getAttack();
		case defence:
			return getDefence();
		case crt:
			return getCrt();
		case dodge:
			return getDodge();
		case maxHp:
			return getHp();
		case maxSp:
			return getSp();
		case movespeed:
			return getMoveSpeed();
		case attackspeed:
			return getAtkColdtime();
		case maxMp:
			return getMp();
		case hit:
			return getHit();
		default:
			return 0;
		}

	}

	/**
	 * 
	 * @param property
	 * @param percent
	 *            1/100单位
	 * @return
	 */
	public int getAddPropertyMaxValue(Property property) {
		switch (property) {
		case attack:
			return getAttackMax();
		case defence:
			return getDefenceMax();
		case crt:
			return getCrtMax();
		case dodge:
			return getDodgeMax();
		case maxHp:
			return getHpMax();
		case maxSp:
			return getSpMax();
		case movespeed:
			return getMoveSpeedMax();
		case attackspeed:
			return getAtkColdtimeMax();
		case maxMp:
			return getMpMax();
		case hit:
			return getHitMax();
		default:
			return 0;
		}
	}

	public int getAddPropertyMinValue(Property property) {
		switch (property) {
		case attack:
			return getAttackMin();
		case defence:
			return getDefenceMin();
		case crt:
			return getCrtMin();
		case dodge:
			return getDodgeMin();
		case maxHp:
			return getHpMin();
		case maxSp:
			return getSpMin();
		case movespeed:
			return getMoveSpeedMin();
		case attackspeed:
			return getAtkColdtimeMin();
		case maxMp:
			return getMpMin();
		case hit:
			return getHitMin();
		default:
			return 0;
		}
	}

	public void initPosignerskillSheliziMap() {
		if (isShelizi()) {
			if (posignerskillSheliziMap == null) {
				posignerskillSheliziMap = new HashMap<Integer, List<Integer>>();
			}
			posignerskillSheliziMap.clear();

		}

		String wugongdesc = getWugongDesc();
		if (wugongdesc == null) {
			return;
		}
		if (wugongdesc.equals("")) {
			return;
		}
		String[] wgdescs = wugongdesc.split(";");
		for (int i = 0; i < wgdescs.length; i++) {
			String desc = wgdescs[i];
			if (desc != null && !"".equals(desc)) {
				String[] wgd = desc.split(",");
				int skillid = Integer.parseInt(wgd[0]);
				int posigner = Integer.parseInt(wgd[1]);
				List<Integer> list = posignerskillSheliziMap.get(posigner);
				if (list == null) {
					list = new ArrayList<Integer>();
					posignerskillSheliziMap.put(posigner, list);
				}
				list.add(skillid);
			}
		}
	}

	public Map<Integer, List<Integer>> getPosignerskillSheliziMap() {
		return posignerskillSheliziMap;
	}

	private Map<Integer, List<Integer>> posignerskillSheliziMap;

	private UseGoodAction useGoodAction;

	/**
	 * 调用物品使用封装对象
	 * 
	 * @return
	 */

	public UseGoodAction getUseGoodAction() {
		return useGoodAction;
	}

	public void setUseGoodAction(UseGoodAction useGoodAction) {
		this.useGoodAction = useGoodAction;
	}

	public WeddingRing getWeddingRing() {
		WeddingRing wr = null;
		WeddingRingManager manager = WeddingRingManager.getInstance();
		if (this.getPosition() == 15) {
			wr = manager.getWeddingRingById(this.getId());
			return wr;
		}
		if (this.getPosition() == 16) {
			wr = manager.getWeddingRingByHalfId(this.getId());
			return wr;
		}
		return wr;
	}
}
