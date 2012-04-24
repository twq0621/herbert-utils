package net.snake.commons.script;


/**
 * 战斗公式
 * @author serv_dev
 *
 */
public interface EvtFightFormula {
	
	/**
	 * 最小伤害值
	 * @return
	 */
	public int getMinHurt();
	
	/**
	 * 最大伤害值
	 * @return
	 */
	public int getMaxHurt();
	
	/**
	 * 最小伤害值
	 * @return
	 */
	public int getMinHit();
	
	/**
	 * 最大伤害值
	 * @return
	 */
	public int getMaxHit();
	/**
	 * 最小伤害百分比
	 * @return
	 */
	public float getMinHurtPrecent();
	
	/**
	 * 最大伤害百分比
	 * @return
	 */
	public float getMaxHurtPrecent();
	
	
	/**
	 * 最小闪避百分比
	 * @return
	 */
	public float getMinDodgePrecent();
	
	/**
	 * 最大闪避百分比
	 * @return
	 */
	
	public float getMaxDodgePrecent();

	/**
	 * 最小暴击百分比
	 * @return
	 */
	public float getMinCrtPrecent();
	
	/**
	 * 最大暴击百分比
	 * @return
	 */
	public float getMaxCrtPrecent();
	
	/**
	 * ( 怪物经验 * （1+ (队员数量 – 1) * 5%）) * 杀怪血量百分比 * 等级衰减系数 * （多倍经验系数 + 连斩BUFF系数）
	 * 
	 * 经验获得等级差取绝对值 等级差衰减系数
	 * | 怪物等级 - 人物等级 |>10 0.01 
	 * 5<| 怪物等级 - 人物等级 |<=10 0.8
	 * |怪物等级 - 人物等级 |<=5 1
	 * 
	 * 
	 * 
	 * 经验衰减系数调整
	( 怪物经验 * （1+ (同地图内的队伍人数 – 1) * 5%）) * 杀怪血量百分比 * 等级衰减系数 * （多倍经验系数 + 连斩BUFF系数）
	经验获得等级差取绝对值	对应衰减系数
	  45< | 怪物等级 - 人物等级 |	0.01
	  35< | 怪物等级 - 人物等级 |<=45   	0.05
	  25< | 怪物等级 - 人物等级 |<=35   	0.1
	  15< | 怪物等级 - 人物等级 |<=25  	0.3
	  10< | 怪物等级 - 人物等级 |<=15   	0.5
	  5<| 怪物等级 - 人物等级 |<=10     	0.8
	    | 怪物等级 - 人物等级 |<=5	        1

	 * @return
	 */

	public int getExp(SRole role, SMonster monster);



	/**
	 * 100*(自身等级+自身爆击-对方等级-对方爆击)/(对方等级+100)
	 * 
	 * 
	 * 暴击率=(攻击方的暴击值-(攻击方的人物等级-攻击方目前所佩带的武器等级)*2)/2000
	 * 暴击率=(攻击方的暴击值-(攻击方的人物等级-攻击方目前所佩带的武器等级)*2-被攻击方的闪避值/9)/1000
	 * 暴击率取值范围： [0.01，0.5]
	 * 
	 * @param attacker
	 * @param affecter
	 * @return 1到50
	 */
	public int getCrt(SVO attacker, SVO affecter);

	/**
	 * 100*(自身等级+自身爆击-对方等级-对方爆击)/(对方等级+100)
	 * 
	 * 命中率= 1-a*(被攻击者的闪避值/(攻击者的攻击值+防御方ID显示的闪避值)) +b*(攻击方角色等级-防御方角色等级)/100
	 * 
	 * a=0.5  b=0.25
	 * 
	 * 命中率的取值区间为： [0.1，1]
	 * 
	 * @param attacker
	 * @param affecter
	 * @return 10 100之间的值
	 */
	public int getDodge(SVO attacker, SVO affecter);

	/**
	 * 命中率= a* A的命中/(A的命中+B的ID显示的闪避) +b*(攻击方角色等级-防御方角色等级)/100
	 *	其中：a=1  b=0.25
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public int getHit(SVO attacker, SVO affecter);
	/**
	 * 原伤害= （人物成长与加点带来的攻击力+被动人物心法带来的攻击力+经脉带来的攻击力+药品带来的攻击力+（装备基础属性带来的攻击力+
	 * 装备附加属性带来的攻击力
	 * +装备强化带来的攻击力）*（1+套装带来的攻击力加成百分比））*（1+称号带来的攻击力百分比+队伍阵法带来的攻击力百分比+出战坐骑技能带来的百分比BUFF）*
	 +坐骑带来的攻击力*(1+坐骑技能带来的攻击力百分比) 
	 * -
	 * （对方成长与加点带来的防御力+被动人物心法带来的防御力+经脉带来的防御力
	 * +药品带来的防御力+（装备基础属性带来的防御力+装备附加属性带来的防御力+装备强化带来的防御力
	 * ）*（1+套装带来的防御力加成百分比））*（1+称号带来的防御力百分比
	 * +队伍阵法带来的防御力百分比+出战坐骑技能带来的百分比BUFF）+坐骑带来的防御力*(1+坐骑技能带来的防御力百分比) 
	 * 
	 * 
	 * 最终伤害=（原伤害+攻击方人物等级）*伤害随机区间
	 * 
	 * 伤害值 = 最终伤害 * 技能带来的攻击比例 + 技能伤害附加值
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public int getHurtValue(SVO attacker,
			SVO affecter, SFighterVO fighterVO);

	/**
	 * 暗器伤害 = 人物攻击力 * 暗器该修炼等级的攻击能力万分比 – 对方防御值
	 * @param attacker
	 * @param affecter
	 * @param fighterVO
	 * @return
	 */
	public int getHiddenHurtValue(SVO attacker,
			SVO affecter, SFighterVO fighterVO);
	/**
	 * 加点公式 加攻击
	 * @param attackAdd
	 * @return
	 */
	public float getAddAttack(int attackAdd);
	
	/**
	 * 加点公式 加防御
	 * @param defenceAdd
	 * @return
	 */
	public float getAddDefence(int defenceAdd);

	/**
	 * 加点公式 加暴击
	 * @param qingshenAdd
	 * @return
	 */
	public float getAddCrt(int qingshenAdd);
	
	/**
	 * 加点公式 加闪避
	 * @param qingshenAdd
	 * @return
	 */
	public float getAddDodge(int qingshenAdd);	
	/**
	 * 加点公式 加生命
	 * @param jiantiAdd
	 * @return
	 */
	public float getAddMaxHp(int jiantiAdd);
	
	/**
	 * 加点公式 加魔力
	 * @param jiantiAdd
	 * @return
	 */
	public float getAddMaxMp(int jiantiAdd);
	
	/**
	 * 加点公式 加体力
	 * @param jiantiAdd
	 * @return
	 */
	public float getAddMaxSp(int jiantiAdd);
}

