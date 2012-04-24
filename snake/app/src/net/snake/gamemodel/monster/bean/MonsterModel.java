package net.snake.gamemodel.monster.bean;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.TimeExpression;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.logic.lostgoods.Lostgoods;
import net.snake.gamemodel.monster.logic.lostgoods.MonsterLostgoods;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.ibatis.IbatisEntity;

/**
 * 怪物模板表
 * 
 * @author serv_dev
 */

public class MonsterModel implements IbatisEntity {

	/** 暴击色怪 **/
	public static final int attackCol = 1;
	/** 防御色怪 **/
	public static final int defenceCol = 2;
	/** 经验色怪 **/
	public static final int exposeCol = 3;
	/** 闪避色怪 **/
	public static final int dodgeCol = 4;
	/** 血厚色怪 **/
	public static final int hpCol = 5;
	/** 金钱色怪 *****/
	public static final int moneyCol = 6;
	/** 怪物id *****/
	private int id;
	/** 怪物名 ***/
	private String name;
	private String nameI18n;
	/** 怪物类型(普通1，精英2，boss3) *****/
	private int type;
	private int subtype;
	/** 怪物头像 **/
	private int headIconId;
	/** 怪物图片模型id ***/
	private int avatarId;
	/*** 攻击时的声音id ***/
	private int attackAudio;
	/*** 被攻击时的声音id ***/
	private int hurtAudio;
	/*** 死亡时的声音id ***/
	private int dieAudio;
	/*** 等级 ***/
	private int grade;
	/*** 生命值 ***/
	private int hp;
	/*** 命中 **/
	private int hit;
	/** 攻击 ***/
	private int attack;
	/** 防御 **/
	private int defence;
	/** 暴击率 1/10000 */
	private int crt;
	/** 闪避率1/10000 */
	private int dodge;
	/** 攻击速度 由攻击冷却时间表示 ****/
	private int atkColdtime;
	/*** 移动速度 像素值/秒 ***/
	private int movespeed;
	/** 变身变态倍数 ***/
	private int abnormal;
	/** 变身攻击色怪几率 1/10000 */
	private int attackcolor;
	/*** 变身防御色怪几率 1/10000 ***/
	private int defencecolor;
	/*** 变身暴击色怪几率 1/10000 ****/
	private int exposecolor;
	/** 变身防御色怪几率 1/10000 ***/
	private int dodgecolor;
	/** 变身血量色怪几率 1/10000 ***/
	private int hpcolor;
	/** 变身金钱色怪几率 1/10000 **/
	private int moneycolor;//
	/** 攻击设定，主动攻击1/被动攻击2 **/
	private short is;
	/** 视野范围(原警戒范围) **/
	private short alert;
	/**** 巡逻范围(以逻辑格计) **/
	private int patrol;
	/** 追击范围(以逻辑格为单位) **/
	private int pursuit;
	/** 怪物的平砍技能id **/
	private int skillPingkan;
	/*** 怪物平砍技能等级 **/
	private int skillPingkangrade;
	/*** 技能相关（技能id,等级;） **/
	private String skillRelevance;
	/** 技能描述 **/
	private String skilldesc;
	private String skilldescI18n;

	/*** 是否自动回血,0不回血,1回血 **/
	private int hpAutoadd;
	/*** 所剩生命值在多少百分比时开始补血 1/10000 **/
	private int addhpPercentage;
	/*** 每次补血血量 **/
	private int addhpNum;
	/*** 补血时间间隔 ms **/
	private int addhpTime;
	/** 掉落经验 ***/
	private int exper;
	/** 掉落金钱 **/
	private int lm;
	/*** 携带战场声望 ***/
	private int prestige;
	/*** 说话时间间隔以秒为单位 **/
	private int speakTime;
	/*** 怪物说话内容 如果多句话 中间以;分割 ***/
	private String speaks;
	private String speaksI18n;
	/** 怪物尸体消失时间以秒为单位 ***/
	private int revivificationTime;
	/** 重生时间(候补队列怪物从预约到上场的时间间隔 以秒为单位) **/
	private int waittime;
	/** 重生时间(候补队列怪物从预约到上场的时间间隔 以秒为单位) ***/
	private String waittimeExpression;
	/** 物怪的职责 0随机巡逻 1 站立不动,2 向目标点移动 **/
	private int responsibility;
	/** 血量小于多少时要寻求帮助0-10000 1/10000 **/
	private int helpHp;
	/** 喊话的概率(0-10000) 1/10000 **/
	private int helpProbability;
	/** 喊话时的广度[0-32] ****/
	private int helpExtent;
	/** 可接收喊话的怪物模型ID,以逗号分隔 ***/
	private String helpmodelids;
	/** 追击的概率(0-10000) ****/
	private int pursuitProbability;
	/** 怪物所属的阵营 0 和玩家敌对,1 和玩家同一战线 ***/
	private short camp;
	/** 怪物描述 ***/
	private String desc;
	private String descI18n;
	/** 说话内容 ***/
	private String speakBody[];
	/** 如果不为0，则标识被捕获后的模型ID是什么; **/
	private Integer horseModelId;
	/** 轮询次数 **/
	private int loopCount;
	/** 贴身 **/
	private int skin;
	/** boss 物品掉落 *****/
	private Lostgoods bossDropGoods;
	private MonsterLostgoods monsterLostgoods;
	private TimeExpression timeExpression;
	private String scriptClass;
	private String scriptClassparam;
	/**** 爆率星级 **/
	private int probabilitylevel;
	private int instanceId;
	/**** 被攻击按固定值少血，（无视玩家攻击力）(固定值)（0不生效） **/
	private int beattackHp;
	/**** 攻击玩家按比例少血，（无视玩家防御力）（单位1/10000）（0不生效）(按最大的血量比例少血) **/
	private int attackHp;
	/**** 经验对于任何等级的玩家均不衰减。(1生效0不生效) **/
	private int expUnlimit;
	/******** 是否反击 1不反击 *********/
	private int beatBack;
	/******** 类别id *********/
	private int categoryId;

	public int getBeatBack() {
		return beatBack;
	}

	public void setBeatBack(int beatBack) {
		this.beatBack = beatBack;
	}

	public int getBeattackHp() {
		return beattackHp;
	}

	public void setBeattackHp(int beattackHp) {
		this.beattackHp = beattackHp;
	}

	public int getAttackHp() {
		return attackHp;
	}

	public void setAttackHp(int attackHp) {
		this.attackHp = attackHp;
	}

	public int getExpUnlimit() {
		return expUnlimit;
	}

	public void setExpUnlimit(int expUnlimit) {
		this.expUnlimit = expUnlimit;
	}

	public int getProbabilitylevel() {
		return probabilitylevel;
	}

	public void setProbabilitylevel(int probabilitylevel) {
		this.probabilitylevel = probabilitylevel;
	}

	private int isnpcfriend;

	// `f_script_class` varchar(255) DEFAULT NULL COMMENT '脚本类名 java全路径',
	// `f_script_classparam` varchar(255) DEFAULT NULL COMMENT '脚本类初始化参数',

	public TimeExpression getTimeExpression() {
		return timeExpression;
	}

	public void setScriptClass(String scriptClass) {
		this.scriptClass = scriptClass;
	}

	public void setScriptClassparam(String scriptClassparam) {
		this.scriptClassparam = scriptClassparam;
	}

	public String getScriptClass() {
		return scriptClass;
	}

	public String getScriptClassparam() {
		return scriptClassparam;
	}

	public void setWaittimeExpression(String waittimeExpression) {
		this.waittimeExpression = waittimeExpression;
		try {
			// 此表达式如果解析失败,则不使用时间表达式
			if (waittimeExpression != null && waittimeExpression.length() > 3) {
				timeExpression = new TimeExpression(waittimeExpression);
			}
		} catch (Exception e) {
			// logger.error(e.getMessage(),e);
		}
	}

	public String getWaittimeExpression() {
		return waittimeExpression;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	public void setHorseModelId(Integer horseModelId) {
		this.horseModelId = horseModelId;
	}

	public int getHorseModelId() {
		return horseModelId == null ? 0 : horseModelId;
	}

	public String[] getSpeakBody() {
		return speakBody;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getHit() {
		return hit;
	}

	/**
	 * id
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * id
	 * 
	 * @return int
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 怪物名称
	 * 
	 * @param String
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 怪物名称
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 怪物类型,普通1，精英2，boss3
	 * 
	 * @param int type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 怪物类型,普通1，精英2，boss3,副本小怪9，副本boss10
	 * 
	 * @return int
	 */
	public int getType() {
		return this.type;
	}

	public boolean isBoss() {
		return getType() == 3;
	}

	public boolean isJY() {
		return getType() == 2;
	}

	public boolean isPT() {
		return getType() == 1;
	}

	public void setHeadIconId(int headIconId) {
		this.headIconId = headIconId;
	}

	public int getHeadIconId() {
		return headIconId;
	}

	/**
	 * 攻击时声音编号
	 * 
	 * @param int attackAudio
	 */
	public void setAttackAudio(int attackAudio) {
		this.attackAudio = attackAudio;
	}

	/**
	 * 攻击时声音编号
	 * 
	 * @return int
	 */
	public int getAttackAudio() {
		return this.attackAudio;
	}

	/**
	 * 被攻击的声音
	 * 
	 * @param int hurtAudio
	 */
	public void setHurtAudio(int hurtAudio) {
		this.hurtAudio = hurtAudio;
	}

	/**
	 * 被攻击的声音
	 * 
	 * @return int
	 */
	public int getHurtAudio() {
		return this.hurtAudio;
	}

	/**
	 * 死亡时的声音编号
	 * 
	 * @param int dieAudio
	 */
	public void setDieAudio(int dieAudio) {
		this.dieAudio = dieAudio;
	}

	/**
	 * 死亡时的声音编号
	 * 
	 * @return int
	 */
	public int getDieAudio() {
		return this.dieAudio;
	}

	/**
	 * 等级
	 * 
	 * @param int grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * 等级
	 * 
	 * @return int
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * 生命值
	 * 
	 * @param int hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * 生命值
	 * 
	 * @return int
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * 攻击
	 * 
	 * @param int attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * 攻击
	 * 
	 * @return int
	 */
	public int getAttack() {
		return this.attack;
	}

	/**
	 * 防御
	 * 
	 * @param int defence
	 */
	public void setDefence(int defence) {
		this.defence = defence;
	}

	/**
	 * 防御
	 * 
	 * @return int
	 */
	public int getDefence() {
		return this.defence;
	}

	/**
	 * 暴击 1/10000
	 * 
	 * @param int crt
	 */
	public void setCrt(int crt) {
		this.crt = crt;
	}

	/**
	 * 暴击 1/10000
	 * 
	 * @return int
	 */
	public int getCrt() {
		return this.crt;
	}

	/**
	 * 闪避值 1/10000
	 * 
	 * @param int dodge
	 */
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	/**
	 * 闪避值 1/10000
	 * 
	 * @return int
	 */
	public int getDodge() {
		return this.dodge;
	}

	/**
	 * 攻击速度 由攻击冷却时间表示
	 * 
	 * @param int atkColdtime
	 */
	public void setAtkColdtime(int atkColdtime) {
		this.atkColdtime = atkColdtime;
	}

	/**
	 * 攻击速度 由攻击冷却时间表示
	 * 
	 * @return int
	 */
	public int getAtkColdtime() {
		return this.atkColdtime;
	}

	/**
	 * 移动速度 像素值/秒
	 * 
	 * @param int movespeed
	 */
	public void setMovespeed(int movespeed) {
		this.movespeed = movespeed;
	}

	/**
	 * 移动速度 像素值/秒
	 * 
	 * @return int
	 */
	public int getMovespeed() {
		return this.movespeed;
	}

	/**
	 * 变身变态倍数
	 * 
	 * @param int abnormal
	 */
	public void setAbnormal(int abnormal) {
		this.abnormal = abnormal;
	}

	/**
	 * 变身变态倍数
	 * 
	 * @return int
	 */
	public int getAbnormal() {
		return this.abnormal;
	}

	/**
	 * 变身攻击色怪几率 1/10000
	 * 
	 * @param int attackcolor
	 */
	public void setAttackcolor(int attackcolor) {
		this.attackcolor = attackcolor;
	}

	/**
	 * 变身攻击色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getAttackcolor() {
		return this.attackcolor;
	}

	/**
	 * 变身防御色怪几率 1/10000
	 * 
	 * @param int defencecolor
	 */
	public void setDefencecolor(int defencecolor) {
		this.defencecolor = defencecolor;
	}

	/**
	 * 变身防御色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getDefencecolor() {
		return this.defencecolor;
	}

	/**
	 * 变身暴击色怪几率 1/10000
	 * 
	 * @param int exposecolor
	 */
	public void setExposecolor(int exposecolor) {
		this.exposecolor = exposecolor;
	}

	/**
	 * 变身暴击色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getExposecolor() {
		return this.exposecolor;
	}

	/**
	 * 变身防御色怪几率 1/10000
	 * 
	 * @param int dodgecolor
	 */
	public void setDodgecolor(int dodgecolor) {
		this.dodgecolor = dodgecolor;
	}

	/**
	 * 变身防御色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getDodgecolor() {
		return this.dodgecolor;
	}

	/**
	 * 变身血量色怪几率 1/10000
	 * 
	 * @param int hpcolor
	 */
	public void setHpcolor(int hpcolor) {
		this.hpcolor = hpcolor;
	}

	/**
	 * 变身血量色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getHpcolor() {
		return this.hpcolor;
	}

	/**
	 * 变身金钱色怪几率 1/10000
	 * 
	 * @param int moneycolor
	 */
	public void setMoneycolor(int moneycolor) {
		this.moneycolor = moneycolor;
	}

	/**
	 * 变身金钱色怪几率 1/10000
	 * 
	 * @return int
	 */
	public int getMoneycolor() {
		return this.moneycolor;
	}

	/**
	 * 攻击设定，主动攻击1/被动攻击2
	 * 
	 * @param short is
	 */
	public void setIs(short is) {
		this.is = is;
	}

	/**
	 * 攻击设定，主动攻击1/被动攻击2
	 * 
	 * @return short
	 */
	public short getIs() {
		return this.is;
	}

	/**
	 * 视野范围(原警戒范围)
	 * 
	 * @param short alert
	 */
	public void setAlert(short alert) {
		this.alert = alert;
	}

	/**
	 * 视野范围(原警戒范围)
	 * 
	 * @return short
	 */
	public short getAlert() {
		return this.alert;
	}

	/**
	 * 巡逻范围(以逻辑格计)
	 * 
	 * @param int patrol
	 */
	public void setPatrol(int patrol) {
		this.patrol = patrol;
	}

	/**
	 * 巡逻范围(以逻辑格计)
	 * 
	 * @return int
	 */
	public int getPatrol() {
		return this.patrol;
	}

	/**
	 * 追击范围(以逻辑格为单位)
	 * 
	 * @param int pursuit
	 */
	public void setPursuit(int pursuit) {
		this.pursuit = pursuit;
	}

	/**
	 * 追击范围(以逻辑格为单位)
	 * 
	 * @return int
	 */
	public int getPursuit() {
		return this.pursuit;
	}

	/**
	 * 技能相关（技能id,等级,触发条件,触发值,触发几率;）
	 * 
	 * @param String
	 *            skillRelevance
	 */
	public void setSkillRelevance(String skillRelevance) {
		this.skillRelevance = skillRelevance;
	}

	/**
	 * 技能相关（技能id,等级,触发条件,触发值,触发几率;）
	 * 
	 * @return String
	 */
	public String getSkillRelevance() {
		return this.skillRelevance;
	}

	public String getSkilldesc() {
		return skilldesc;
	}

	public void setSkilldesc(String skilldesc) {
		this.skilldesc = skilldesc;
	}

	/**
	 * 是否自动回血,0不回血,1回血
	 * 
	 * @param int hpAutoadd
	 */
	public void setHpAutoadd(int hpAutoadd) {
		this.hpAutoadd = hpAutoadd;
	}

	/**
	 * 是否自动回血,0不回血,1回血
	 * 
	 * @return int
	 */
	public int getHpAutoadd() {
		return this.hpAutoadd;
	}

	/**
	 * 所剩生命值在多少百分比时开始补血 1/10000
	 * 
	 * @param int addhpPercentage
	 */
	public void setAddhpPercentage(int addhpPercentage) {
		this.addhpPercentage = addhpPercentage;
	}

	/**
	 * 所剩生命值在多少百分比时开始补血 1/10000
	 * 
	 * @return int
	 */
	public int getAddhpPercentage() {
		return this.addhpPercentage;
	}

	/**
	 * 每次补血血量
	 * 
	 * @param int addhpNum
	 */
	public void setAddhpNum(int addhpNum) {
		this.addhpNum = addhpNum;
	}

	/**
	 * 每次补血血量
	 * 
	 * @return int
	 */
	public int getAddhpNum() {
		return this.addhpNum;
	}

	/**
	 * 补血时间间隔 ms
	 * 
	 * @param int addhpTime
	 */
	public void setAddhpTime(int addhpTime) {
		this.addhpTime = addhpTime;
	}

	/**
	 * 补血时间间隔 ms
	 * 
	 * @return int
	 */
	public int getAddhpTime() {
		return this.addhpTime;
	}

	/**
	 * 掉落经验
	 * 
	 * @param int exper
	 */
	public void setExper(int exper) {
		this.exper = exper;
	}

	/**
	 * 掉落经验
	 * 
	 * @return int
	 */
	public int getExper() {
		return this.exper;
	}

	/**
	 * 掉落金钱
	 * 
	 * @param int lm
	 */
	public void setLm(int lm) {
		this.lm = lm;
	}

	/**
	 * 掉落金钱
	 * 
	 * @return int
	 */
	public int getLm() {
		return this.lm;
	}

	/**
	 * 携带战场声望
	 * 
	 * @param int prestige
	 */
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	/**
	 * 携带战场声望
	 * 
	 * @return int
	 */
	public int getPrestige() {
		return this.prestige;
	}

	/**
	 * 说话时间间隔以秒为单位
	 * 
	 * @param int speakTime
	 */
	public void setSpeakTime(int speakTime) {
		this.speakTime = speakTime;
	}

	/**
	 * 说话时间间隔以秒为单位
	 * 
	 * @return int
	 */
	public int getSpeakTime() {
		return this.speakTime;
	}

	/**
	 * 怪物说话内容 如果多句话 中间以;分割
	 * 
	 * @param String
	 *            speaks
	 */
	public void setSpeaks(String speaks) {
		if (speaks == null)
			speaks = "";
		this.speaks = speaks;
		this.speakBody = speaks.split(";");
	}

	/**
	 * 怪物说话内容 如果多句话 中间以;分割
	 * 
	 * @return String
	 */
	public String getSpeaks() {
		return this.speaks;
	}

	/**
	 * 怪物尸体消失时间以秒为单位
	 * 
	 * @param int revivificationTime
	 */
	public void setRevivificationTime(int revivificationTime) {
		this.revivificationTime = revivificationTime;
	}

	/**
	 * 怪物尸体消失时间以秒为单位
	 * 
	 * @return int
	 */
	public int getRevivificationTime() {
		return this.revivificationTime;
	}

	/**
	 * 重生时间(候补队列怪物从预约到上场的时间间隔 以秒为单位)
	 * 
	 * @param int waittime
	 */
	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	/**
	 * 重生时间(候补队列怪物从预约到上场的时间间隔 以秒为单位)
	 * 
	 * @return int
	 */
	public int getWaittime() {
		return this.waittime;
	}

	/**
	 * 物怪的职责 0随机巡逻 1 站立不动,2 向目标点移动
	 * 
	 * @param int responsibility
	 */
	public void setResponsibility(int responsibility) {
		this.responsibility = responsibility;
	}

	/**
	 * 物怪的职责 0随机巡逻 1 站立不动,2 向目标点移动
	 * 
	 * @return int
	 */
	public int getResponsibility() {
		return this.responsibility;
	}

	/**
	 * 血量小于多少时要寻求帮助0-10000 1/10000
	 * 
	 * @param int helpHp
	 */
	public void setHelpHp(int helpHp) {
		this.helpHp = helpHp;
	}

	/**
	 * 血量小于多少时要寻求帮助0-10000 1/10000
	 * 
	 * @return int
	 */
	public int getHelpHp() {
		return this.helpHp;
	}

	/**
	 * 喊话的概率(0-10000) 1/10000
	 * 
	 * @param int helpProbability
	 */
	public void setHelpProbability(int helpProbability) {
		this.helpProbability = helpProbability;
	}

	/**
	 * 喊话的概率(0-10000) 1/10000
	 * 
	 * @return int
	 */
	public int getHelpProbability() {
		return this.helpProbability;
	}

	/**
	 * 喊话时的广度[0-32]
	 * 
	 * @param int helpExtent
	 */
	public void setHelpExtent(int helpExtent) {
		this.helpExtent = helpExtent;
	}

	/**
	 * 喊话时的广度[0-32]
	 * 
	 * @return int
	 */
	public int getHelpExtent() {
		return this.helpExtent;
	}

	/**
	 * 可接收喊话的怪物模型ID,以逗号分隔
	 * 
	 * @param String
	 *            helpmodelids
	 */
	public void setHelpmodelids(String helpmodelids) {
		this.helpmodelids = helpmodelids;
	}

	/**
	 * 可接收喊话的怪物模型ID,以逗号分隔
	 * 
	 * @return String
	 */
	public String getHelpmodelids() {
		return this.helpmodelids;
	}

	/**
	 * 追击的概率(0-10000)
	 * 
	 * @param int pursuitProbability
	 */
	public void setPursuitProbability(int pursuitProbability) {
		this.pursuitProbability = pursuitProbability;
	}

	/**
	 * 追击的概率(0-10000)
	 * 
	 * @return int
	 */
	public int getPursuitProbability() {
		return this.pursuitProbability;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public int getAvatarId() {
		return avatarId;
	}

	/**
	 * 怪物所属的阵营 0 和玩家敌对,1 和玩家同一战线
	 * 
	 * @param short camp
	 */
	public void setCamp(short camp) {
		this.camp = camp;
	}

	/**
	 * 怪物所属的阵营 0 和玩家敌对,1 和玩家同一战线
	 * 
	 * @return short
	 */
	public short getCamp() {
		return this.camp;
	}

	/**
	 * 怪物描述
	 * 
	 * @param String
	 *            desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 怪物描述
	 * 
	 * @return String
	 */
	public String getDesc() {
		return this.desc;
	}

	public void setSpeakBody(String[] speakBody) {
		this.speakBody = speakBody;
	}

	public int getSkillPingkan() {
		return skillPingkan;
	}

	public void setSkillPingkan(int skillPingkan) {
		this.skillPingkan = skillPingkan;
	}

	public int getSkillPingkangrade() {
		return skillPingkangrade;
	}

	public void setSkillPingkangrade(int skillPingkangrade) {
		this.skillPingkangrade = skillPingkangrade;
	}

	public Skill getPingkanSkill() {
		return null;
	}

	public MonsterLostgoods getMonsterLostgoods() {
		return monsterLostgoods;
	}

	public void setMonsterLostgoods(MonsterLostgoods monsterLostgoods) {
		this.monsterLostgoods = monsterLostgoods;
	}

	public Lostgoods getBossDropGoods() {
		return bossDropGoods;
	}

	public void setBossDropGoods(Lostgoods bossDropGoods) {
		this.bossDropGoods = bossDropGoods;
	}

	public int getIsnpcfriend() {
		return isnpcfriend;
	}

	public void setIsnpcfriend(int isnpcfriend) {
		this.isnpcfriend = isnpcfriend;
	}

	/**
	 * 生成 怪物掉落的物品30*20=600*15
	 */
	public List<CharacterGoods> dropGoods(Hero character, int jiacheng) {
		if (this.monsterLostgoods == null) {
			return new ArrayList<CharacterGoods>();
		}
		return this.monsterLostgoods.dropMonsterLostGoodsMap(character, this, jiacheng);
	}

	/**
	 * 生成 怪物掉落的怪物30*20=600*15
	 */
	public SceneMonster dropMonsters(Hero character) {
		if (this.monsterLostgoods == null) {
			return null;
		}
		return this.monsterLostgoods.dropMonsterLostMonsterList(character, this);
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public boolean isBangqiMonster() {
		if (this.getType() == 5) {
			return true;
		} else {
			return false;
		}
	}

	public String getNameI18n() {
		return nameI18n;
	}

	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	public String getSkilldescI18n() {
		return skilldescI18n;
	}

	public void setSkilldescI18n(String skilldescI18n) {
		this.skilldescI18n = skilldescI18n;
	}

	public String getSpeaksI18n() {
		return speaksI18n;
	}

	public void setSpeaksI18n(String speaksI18n) {
		this.speaksI18n = speaksI18n;
	}

	public String getDescI18n() {
		return descI18n;
	}

	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	public int getSubtype() {
		return subtype;
	}

	public void setSubtype(int subtype) {
		this.subtype = subtype;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
