package net.snake.gamemodel.hero.bean;

import net.snake.ai.EnmityManager;
import net.snake.ai.EnmityManagerImp;
import net.snake.ai.IEyeShotManager;
import net.snake.ai.PursuitPointManager;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.BaseFightController;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.move.IMoveController;
import net.snake.ai.move.Location;
import net.snake.commons.program.Updatable;
import net.snake.commons.script.SVO;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.logic.DynamicUpdateObjManager;
import net.snake.gamemodel.hero.logic.LastAttackerInfo;
import net.snake.gamemodel.hero.logic.PropertyAdditionController;
import net.snake.gamemodel.hero.logic.PropertyAdditionControllerImp;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.netio.message.ResponseMsg;

import org.apache.log4j.Logger;

/**
 * 在场景中看得见的物体
 * 
 * @author serv_dev
 * 
 */
public abstract class VisibleObject extends SceneObj implements SVO, Updatable {
	private static Logger logger = Logger.getLogger(VisibleObject.class);
	// 数据库映射字段==================================
	private int nowHp;// 当前剩余HP生命值
	private int nowMp;// 当前剩余MP魔力值
	private int maxHp;// HP上限
	private int maxMp;// MP上限
	private int nowSp;// 当前体力--2.2 怒气值
	private int maxSp;// 体力上限
	private short grade;// 等级
	private int attack;// 攻击力
	private int defence;// 防御力
	private int atkColdtime;// 攻击速度
	private int moveSpeed;// 移动速度 以像素计
	private int hit;// 命中
	private int dodge;// 闪避
	private int crt;// 暴击
	// =============================================
	private volatile int location = Location.Location_DI;
	private volatile VisibleObject target;// 攻击目标

	private ShockImg shockOther = new ShockImg();// 我将谁杀到濒死状态
	private ShockImg shockMe = new ShockImg();// 谁将我杀死到濒死状态

	/******* 初始化被动技能时更新，被动技能提升时更新 *********/
	private int unKnockbackGrade;// 抵抗击退等级
	private int unhitvitalpointGrade;// 抵抗点穴等级
	private int unpoisoningGrade;// 抵抗中毒等级
	private int unFengMpGrade;// 抵抗化功大法等级
	private int unFengSpGrade;// 抵抗绵骨大法等级
	private int unXiXueGrade;// 抵抗吸星大法等级
	private int unHujuGrade;// 抵抗护具失效等级
	private int unWuqiGrade;// 抵抗武器失效等级

	private int unDeAttackSpeedGrade;// 抵抗降低攻击速度
	private int unDeMoveSpeedGrade;// 抵抗降低移动速度
	private int unDodgeGrade;// 抵抗降低闪避
	private int unCrtGrade;// 抵抗降低暴击

	/* 初始化被动技能时更新，被动技能提升时更新******** */

	// 各种控制器=========================================================
	protected PropertyAdditionController propertyController = new PropertyAdditionControllerImp(this);
	protected EffectController effectController = new EffectController(this);
	private final EnmityManager enmityManager = new EnmityManagerImp(this);
	private final PursuitPointManager pursuitPointManager = new PursuitPointManager(this);
	// 最后一次攻击者的信息
	protected final LastAttackerInfo lastAttackInfo = new LastAttackerInfo();
	protected final DynamicUpdateObjManager updateObjManager = new DynamicUpdateObjManager();

	public abstract int changeNowHp(int changeV);

	public abstract int changeNowMp(int changeV);

	public abstract int changeNowSp(int changeV);

	public DynamicUpdateObjManager getUpdateObjManager() {
		return updateObjManager;
	}

	public abstract BaseFightController getFightController();

	public abstract IMoveController getMoveController();

	@SuppressWarnings("rawtypes")
	public abstract BaseSkillManager getSkillManager();

	public void destroy() {
		target = null;
		// whoKillMe=null;
		lastAttackInfo.clear();
		effectController.destory();
		enmityManager.destory();
		pursuitPointManager.destory();
		updateObjManager.destory();
		propertyController.destory();

	}

	public LastAttackerInfo getLastAttackInfo() {
		return lastAttackInfo;
	}

	public byte getIsonline() {
		return 1;
	}

	public PropertyAdditionController getPropertyAdditionController() {
		return propertyController;
	}

	public PursuitPointManager getPursuitPointManager() {
		return pursuitPointManager;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int LOCATION) {
		this.location = LOCATION;
	}

	public boolean isJumping() {
		return location == Location.Location_KONG;
	}

	public void setAtkColdtime(int atkColdtime) {
		this.atkColdtime = atkColdtime;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getAtkColdtime() {
		return atkColdtime;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public EnmityManager getEnmityManager() {
		return enmityManager;
	}

	public EffectController getEffectController() {
		return effectController;
	}

	public void sendMsg(ResponseMsg msg) {
	}

	/**
	 * 
	 * @param attacker
	 *            凶手
	 * 
	 */
	public void die(VisibleObject attacker) {
		if (nowHp > 0) {
			nowHp = 0;
		}
		effectController.clearEffectListAndRemoveBuffOnBody();
//		updateObjManager.clearEffectFromOther();
	}

	public VisibleObject getTarget() {
		return target;
	}

	public void setTarget(VisibleObject target) {
		this.target = target;
	}

	public boolean isZeroHp() {
		if (nowHp <= 0) {
			return true;
		} else
			return false;
	}

	/**
	 * 当前剩余HP生命值
	 * 
	 * @param Integer
	 *            nowHp
	 */
	public void setNowHp(int nowHp) {

		if (nowHp < 0)
			nowHp = 0;

		this.nowHp = nowHp;

	}

	/**
	 * 当前剩余HP生命值
	 * 
	 * @return Integer
	 */
	public int getNowHp() {
		return nowHp;
	}

	/**
	 * 当前剩余MP魔力值
	 * 
	 * @param Integer
	 *            nowMp
	 */
	public void setNowMp(int nowMp) {

		if (nowMp < 0)
			nowMp = 0;

		this.nowMp = nowMp;
	}

	/**
	 * 当前剩余MP魔力值
	 * 
	 * @return Integer
	 */
	public int getNowMp() {
		return nowMp;
	}

	/**
	 * HP上限
	 * 
	 * @param Integer
	 *            maxHp
	 */
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * HP上限
	 * 
	 * @return Integer
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * MP上限
	 * 
	 * @param Integer
	 *            maxMp
	 */
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	/**
	 * MP上限 warn 获得最大血量的上限 (必须考虑到buff情况)
	 * 
	 * @return Integer
	 */
	public int getMaxMp() {
		return maxMp;
	}

	/**
	 * 当前怒气
	 * 
	 * @param Integer
	 *            nowSp
	 */
	public void setNowSp(int nowSp) {

		if (nowSp < 0)
			nowSp = 0;

		this.nowSp = nowSp;
	}

	/**
	 * 当前怒气
	 * 
	 * @return Integer
	 */
	public int getNowSp() {
		return nowSp;
	}

	/**
	 * 怒气上限
	 * 
	 * @param Integer
	 *            maxSp
	 */
	public void setMaxSp(int maxSp) {
		this.maxSp = maxSp;
	}

	/**
	 * 怒气上限
	 * 
	 * @return Integer
	 */
	public int getMaxSp() {
		return maxSp;
	}

	/**
	 * 等级
	 * 
	 * @param Integer
	 *            grade
	 */
	public void setGrade(short grade) {
		this.grade = grade;
	}

	/**
	 * 等级
	 * 
	 * @return Integer
	 */
	public short getGrade() {
		return grade;
	}

	/**
	 * 命中
	 * 
	 * @param Integer
	 *            hit
	 */
	public void setHit(int hit) {
		if (hit < 0)
			hit = 0;
		this.hit = hit;
	}

	/**
	 * 命中
	 * 
	 * @return Integer
	 */
	public int getHit() {
		return hit;
	}

	/**
	 * 闪避
	 * 
	 * @param Integer
	 *            dodge
	 */
	public void setDodge(int dodge) {
		if (dodge < 0)
			dodge = 0;
		this.dodge = dodge;
	}

	/**
	 * 闪避
	 * 
	 * @return Integer
	 */
	public int getDodge() {
		return dodge;
	}

	/**
	 * 会心率0~100
	 * 
	 * @param Short
	 *            crt
	 */
	public void setCrt(int crt) {
		if (crt < 0)
			crt = 0;
		this.crt = crt;
	}

	/**
	 * 会心率0~100
	 * 
	 * @return Short
	 */
	public int getCrt() {
		return crt;
	}

	public short[] findWay(short toX, short toY) {
		if (sceneRef == null) {
			return null;
		}
		return sceneRef.findWay(x, y, toX, toY);
	}

	public int getUnKnockbackGrade() {
		return unKnockbackGrade;
	}

	public void setUnKnockbackGrade(int unKnockbackGrade) {
		this.unKnockbackGrade = unKnockbackGrade;
	}

	public int getUnhitvitalpointGrade() {
		return unhitvitalpointGrade;
	}

	public void setUnhitvitalpointGrade(int unhitvitalpointGrade) {
		this.unhitvitalpointGrade = unhitvitalpointGrade;
	}

	public int getUnpoisoningGrade() {
		return unpoisoningGrade;
	}

	public void setUnpoisoningGrade(int unpoisoningGrade) {
		this.unpoisoningGrade = unpoisoningGrade;
	}

	public int getUnFengMpGrade() {
		return unFengMpGrade;
	}

	public void setUnFengMpGrade(int unFengMpGrade) {
		this.unFengMpGrade = unFengMpGrade;
	}

	public int getUnFengSpGrade() {
		return unFengSpGrade;
	}

	public void setUnFengSpGrade(int unFengSpGrade) {
		this.unFengSpGrade = unFengSpGrade;
	}

	public int getUnXiXueGrade() {
		return unXiXueGrade;
	}

	public void setUnXiXueGrade(int unXiXueGrade) {
		this.unXiXueGrade = unXiXueGrade;
	}

	public int getUnHujuGrade() {
		return unHujuGrade;
	}

	public void setUnHujuGrade(int unHujuGrade) {
		this.unHujuGrade = unHujuGrade;
	}

	public int getUnWuqiGrade() {
		return unWuqiGrade;
	}

	public void setUnWuqiGrade(int unWuqiGrade) {
		this.unWuqiGrade = unWuqiGrade;
	}

	public int getUnCrtGrade() {
		return unCrtGrade;
	}

	public void setUnCrtGrade(int unCrtGrade) {
		this.unCrtGrade = unCrtGrade;
	}

	public int getUnDeAttackSpeedGrade() {
		return unDeAttackSpeedGrade;
	}

	public void setUnDeAttackSpeedGrade(int unDeAttackSpeedGrade) {
		this.unDeAttackSpeedGrade = unDeAttackSpeedGrade;
	}

	public int getUnDeMoveSpeedGrade() {
		return unDeMoveSpeedGrade;
	}

	public void setUnDeMoveSpeedGrade(int unDeMoveSpeedGrade) {
		this.unDeMoveSpeedGrade = unDeMoveSpeedGrade;
	}

	public int getUnDodgeGrade() {
		return unDodgeGrade;
	}

	public void setUnDodgeGrade(int unDodgeGrade) {
		this.unDodgeGrade = unDodgeGrade;
	}

	public int getValueByProperty(Property property) {
		int peValue = 0;
		switch (property) {
		case attack:
			peValue = this.getAttack();
			break;
		case defence:
			peValue = this.getDefence();
			break;
		case attackspeed:
			break;
		case crt:
			peValue = this.getCrt();
			break;
		case dodge:
			peValue = this.getDodge();
			break;
		case hit:
			peValue = this.getHit();
			break;
		case maxHp:
			peValue = this.getMaxHp();
			break;
		case maxMp:
			peValue = this.getMaxMp();
			break;
		case maxSp:
			peValue = this.getMaxSp();
			break;
		case movespeed:
			peValue = this.getMoveSpeed();
			break;
		default:
			break;
		}
		return peValue;
	}

	@Override
	public String toString() {
		return "角色：" + getId() + "  攻击力：" + propertyController.getExtraAttack() + "防御力: " + propertyController.getExtraDefend() + "攻击速度："
				+ propertyController.getExtraAttackSpeed() + "移动速度: " + propertyController.getExtraMoveSpeed() + "当前生命值: " + getNowHp() + "最大生命值"
				+ propertyController.getExtraMaxHp() + "当前魔法值: " + getNowMp() + "最大魔法值" + propertyController.getExtraMaxMp() + "当前体力值: " + getNowSp() + "最大体力值"
				+ propertyController.getExtraMaxSp() + "暴击值：" + propertyController.getExtraCrt() + "闪避值：" + propertyController.getExtraDodge();
	}

	/**
	 * 获得顺移消息
	 * 
	 * @return
	 */
	public abstract ResponseMsg getInstanceMoveMsg();

	/**
	 * 获得死亡消息
	 * 
	 * @return
	 */
	public abstract ResponseMsg getDieMsg();

	/** 濒死消息 */
	public abstract ResponseMsg getShockMsg();

	/**
	 * 获得精灵类型
	 * 
	 * @return
	 */
	public abstract byte getSpriteType();

	/**
	 * 获得精灵视野
	 */
	abstract public IEyeShotManager getEyeShotManager();

	/**
	 * 当我被攻击时
	 */
	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {

	}

	/**
	 * 当我中了暗器
	 * 
	 * @param whoAttackMe
	 * @param fighterVO
	 */
	public void onBeHiddenAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {

	}

	/**
	 * 当我的位置变化时
	 */
	public void onMove() {
		getEyeShotManager().onMove();
	}

	/**
	 * 当我被击退时
	 * 
	 * @param jituiDistance
	 */
	public void onBeJiTui(int jituiDistance) {
		getEffectController().setJiTuiBaoJi(true);
		getEffectController().setJiTuiBeginTime(System.currentTimeMillis());
		getEffectController().setJiTuiTime(Math.round((jituiDistance * 25) / 500 * 1000));// 每秒500像素
	}

	public boolean isJiTui() {
		return false;
	}

	/**
	 * 是否可pk
	 * 
	 * @param vo
	 * @return
	 */
	public boolean isAblePk(VisibleObject vo) {
		return true;
	}

	@Override
	public void update(long now) {
		try {
			lastAttackInfo.update(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		try {
			updateObjManager.update(now);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void someoneShocksMe(int killer, Class<?> killerType, int shocked, Class<?> shockedType, long time) {
		shockMe.killerId = killer;
		shockMe.killerType = killerType;
		shockMe.shockedId = shocked;
		shockMe.shockedType = shockedType;
		shockMe.shockTimestamp = time;
	}

	/**
	 * 谁将我杀死到濒死状态
	 * 
	 * @return
	 */
	public ShockImg getShockMeImg() {
		return this.shockMe;
	}

	public void iShockSomeone(int killer, Class<?> killerType, int shocked, Class<?> shockedType, long time) {
		shockOther.killerId = killer;
		shockOther.killerType = killerType;
		shockOther.shockedId = shocked;
		shockOther.shockedType = shockedType;
		shockOther.shockTimestamp = time;
	}

	/**
	 * 我将谁杀到濒死状态
	 * 
	 * @return
	 */
	public ShockImg getShockSomeoneImg() {
		return shockOther;
	}

	public abstract void setStandState();
	public abstract void setObjectState(int state);

	public abstract int getObjectState();
}
