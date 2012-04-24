package net.snake.commons.script;


/**
 * 技能公式
 * @author serv_dev
 *
 */
public interface EvtSkillFormula {

	/**
	 * 帮派技能触发几率
	 * (自己心法等级-对方抗性心法等级+5)*1.5/100，最小为0，最大为100%
	 * @return
	 */
	public int bangpaiSkillTribLv(int skillGrade, int enemySkillGrade);
	
	/**
	 * 帮派技能触发时间
	 * INT((自己心法等级-对方抗性心法等级)*3/10)+8
	 * @return
	 */
	public int bangpaiSkillDuration(int skillGrade, int enemySkillGrade);
	
	/**
	 * 千幻掌
	 * @return
	 */
	public int getReduceDodgePrecent_QianHuanZhang();
	
	/**
	 * 七煞掌
	 * @return
	 */
	public int getReduceCrtPrecent_QiShaZhang();
	/**
	 * 狮吼功
	 * @return
	 */
	public int getReduceMoveSpeed_SiHouGong();
	/**
	 * 错骨分筋
	 * @return
	 */
	public int getReduceAttackSpeed_CuoGuFenJin();
	
	/**
	 * (old) 武功升级消耗真气储量=（100+武功等级*武功等级-武功等级）*武功修炼难度系数
	 * 心法升级消耗真气储量=（100+心法等级*心法等级-心法等级）*心法修炼难度系数 (new)
	 * 武功升级消耗真气存贮量=对应技能等级^2*武功修炼难度系数字段数值
	 * new 取表的数据
	 * @return
	 */
	

	public int getSkillUpgradeNeedZhenqi(SCharacterSkill skill);
	
	/**
	 * 吸星触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * (自己心法等级-对方抗性心法等级)*1.5/100 2010.12.8
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int xixueTriggerlv(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 吸星回血百分比 (技能等级+10)/200
	 * (技能等级+10)/200  最小为0，最大为50%
	 * @param skillGrade
	 * @return 百分比 例如：50% 返回的是50
	 */
	public int xixuePercent(int skillGrade);

	/**
	 * 化功触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * (自己心法等级-对方抗性心法等级)*1.5/100，最小为0，最大为100%
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int fengMpTriggerlv(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 化功持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+5
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 化功持续时间(单位毫秒)
	 */
	public int fengMpTime(int skillGrade, SVO attacker,
			SVO affecter);
	
	/**
	 * 化攻去内力比例：（施法方技能等级-被攻击方角色等级+25）/100 最小为0，最大为60%
	 * @param skillGrade
	 *            施法方技能等级
	 * @param attacker 攻击角色
	 * @param affecter 被攻击方角色
	 * @return 化攻去内力值
	 */
	public int fengMpValue(int skillGrade, SVO attacker,
			SVO affecter);
	
	/**
	 * 绵骨触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int fengSpTriggerlv(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 绵骨持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+5
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 绵骨持续时间(单位毫秒)
	 */
	public int fengSpTime(int skillGrade, SVO attacker,
			SVO affecter);
	
	
	/**
	 * 绵骨去体力比例：（施法方技能等级-被攻击方角色等级+25）/50 最小为0，最大为100%
	 * @param skillGrade
	 *            施法方技能等级
	 * @param attacker 攻击角色
	 * @param affecter 被攻击方角色
	 * @return 绵骨去体力值
	 */
	public int fengSpValue(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 脱袍卸甲触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int fengHujuTriggerlv(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 脱袍卸甲持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+8
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 脱袍卸甲持续时间(单位毫秒)
	 */
	public int fengHujuTime(int skillGrade, SVO attacker,
			SVO affecter);
	/**
	 * 去装备防御比例：（施法方技能等级-被攻击方角色等级+25）/100最小为0，最大为60%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 脱袍卸甲持续时间(单位毫秒)
	 */
	public float fengHujuPercent(int skillGrade, SVO attacker,
			SVO affecter);
	
	/**
	 * 白手入刃触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int fengWuqiTriggerlv(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 白手入刃持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+8
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 白手入刃持续时间(单位毫秒)
	 */
	public int fengWuqiTime(int skillGrade, SVO attacker,
			SVO affecter);
	/**
	 * 去装备攻击比例：（施法方技能等级-被攻击方角色等级+25）/100最小为0，最大为60%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 脱袍卸甲持续时间(单位毫秒)
	 */
	public float fengWuqiPercent(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 击退触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int knockbackTriggerlv(int skillGrade,
			SVO attacker, SVO affecter);

	/**
	 * 击退距离 INT((自己心法等级-对方抗性心法等级)*2/10)+5
	 * INT((自己心法等级-对方抗性心法等级)*2/20)+5 2010.12.9
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 击退距离(单位1格子)
	 */
	public int knockbackDistance(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 点穴触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 点穴触发几率：(自己心法等级-对方抗性心法等级) /150，最小为0，最大为50%
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int hitvitalpointTriggerlv(int skillGrade,
			SVO attacker, SVO affecter);

	/**
	 * 点穴持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+3
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 点穴持续时间(单位毫秒)
	 */
	public int hitvitalpointTime(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 五毒触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public int poisoningTriggerlv(int skillGrade,
			SVO attacker, SVO affecter) ;

	/**
	 * 五毒持续时间 INT((自己心法等级-对方抗性心法等级)*2/10)+3
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 五毒持续时间(单位毫秒)
	 */
	public int poisoningTime(int skillGrade, SVO attacker,
			SVO affecter);

	/**
	 * 五毒掉血百分比 目标当前血量*5%
	 * 
	 * 
	 * 2011-01-04 04:54:02 Edited by 陈默 Changed AssignedTo from "chenmo" to "huangchunjian"
			五毒奇经命中后的每次掉血百分比计算公式：目标当前血量*5%
			
			若计算结果超过5000点，则以5000点作为掉血的上限值


	 * @param attacker
	 * @param affecter
	 * @return 五毒掉血量
	 */
	public int poisoningHp(SVO attacker, SVO affecter);

	/**
	 * 丹田吐纳功 (old)增加生命值上限 心法等级*2 (new) 具体效果值公式：心法等级*12
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getDantian_MaxHp(SVO vo, int skillgrade);
	/**
	 * 玄门吐纳功 (old)增加内力值上限 心法等级/心法等级-1+10 (new) 具体效果值公式：心法等级*6
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getXuanmen_MaxMp(SVO vo, int skillgrade);

	/**
	 * 金鲤行波 (old) 增加体力值上限 心法等级/心法等级 (new) 具体效果值公式：心法等级*1
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getJinli_MaxSp(SVO vo, int skillgrade);
	/**
	 * 玄元经 (old)增加攻击力上限 心法等级/心法等级-1+3 (new) 具体效果值公式：心法等级*4
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getXuanyuan_attack(SVO vo, int skillgrade);
	/**
	 * 小无相功 (old)增加防御力上限 心法等级/心法等级-1+3 (new) 心法等级*2.8
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getXiaowu_defence(SVO vo, int skillgrade);

	/**
	 * 地灵真经 增加闪避值上限 INT(心法等级/10)+1 具体效果值公式：心法等级*4
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getDiling_dodge(SVO vo, int skillgrade);
	/**
	 * 战气心诀 增加爆击值上限 INT(心法等级/10)+1 具体效果值公式：心法等级*1.2
	 * 
	 * @param vo
	 * @param skillId
	 * @return
	 */
	public int getZhanqi_crt(SVO vo, int skillgrade);
	/**
	 * 武功境界 达成条件 初 所有武学层数相加>100层 高 所有武学层数相加>250层 侠 所有武学层数相加>450层 武 所有武学层数相加>700层
	 * 义 所有武学层数相加>1000层 英 所有武学层数相加>1400层 尊 所有武学层数相加>1900层
	 * 
	 * @param totalGrade
	 * @return 初 1 高 2 侠 3 武 4 义 5 英 6 尊 7
	 */
	public int getSkillTotalGrade(int totalGrade);

	/**
	 * 隔空渡气回血量（单回） 自身最大血量*((技能等级+10)/1000)
	 * 
	 * @param skillGrade
	 * @param attacker
	 * @return
	 */
	public int getGeKongDuQiHp(int skillGrade, SVO attacker);

	/**
	 * 普渡慈航回血量（群回） (自身最大血量*((技能等级+10)/1000))/3
	 * 
	 * @param skillGrade
	 * @param attacker
	 * @return
	 */
	public int getPuDuCiHangHp(int skillGrade, SVO attacker);

	/**
	 * 战意临体提升百分比 (技能等级* 2+10)/1000
	 * 
	 * @param skillGrade
	 * @return
	 */
	public int getZhanYiLinTiValue(int skillGrade, int affecterAttack);

	/**
	 * 战意临体持续时间 (技能等级+10)*6
	 * 
	 * @param skillGrade
	 * @return 毫秒
	 */
	public int getZhanYiLinTiTime(int skillGrade) ;

	/**
	 * 武神临体提升百分比 (技能等级* 2+10)/1000
	 * 
	 * 
	 * @param skillGrade
	 * @return
	 */
	public int getWuShenLinTiValue(int skillGrade, int affecterDefence);

	/**
	 * 武神临体持续时间 (技能等级+10)*6
	 * 
	 * @param skillGrade
	 * @return 毫秒
	 */
	public int getWuShenLinTiTime(int skillGrade) ;
	
}
