package net.snake.gamemodel.monster.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.ai.IEyeShotManager;
import net.snake.ai.eyeshot.BaseEyeShotManager;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.BaseFightController;
import net.snake.ai.fight.controller.MonsterFightController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.MonsterBeatBackDie20040;
import net.snake.ai.fight.response.MonsterDie20048;
import net.snake.ai.fight.response.MonsterDieMsg11168;
import net.snake.ai.fight.response.MonsterShockMsg;
import net.snake.ai.fight.response.MonsterSpeak10298;
import net.snake.ai.formula.AttackFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.ai.move.IMoveController;
import net.snake.ai.move.MonsterMoveController;
import net.snake.api.IHurtListener;
import net.snake.api.IStateListener;
import net.snake.commons.GenerateProbability;
import net.snake.commons.TimeExpression;
import net.snake.commons.program.IntId;
import net.snake.commons.program.Updatable;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SVO;
import net.snake.consts.ArrowWay;
import net.snake.consts.BuffId;
import net.snake.consts.EffectType;
import net.snake.consts.GameConstant;
import net.snake.consts.SkillId;
import net.snake.consts.Symbol;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Enmity;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.monster.MonsterEnterEyeShot10032;
import net.snake.gamemodel.map.response.monster.MonsterInstantMove10122;
import net.snake.gamemodel.map.response.monster.MonsterLeaveEyeShot10096;
import net.snake.gamemodel.monster.logic.MonsterSkillManager;
import net.snake.gamemodel.monster.logic.SceneMonsterDropGoodManager;
import net.snake.gamemodel.monster.logic.SceneMonsterLineManager;
import net.snake.gamemodel.monster.logic.WhoCatchMeManager;
import net.snake.gamemodel.monster.persistence.BossLastKillManager;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.ibatis.IbatisEntity;
import net.snake.netio.message.ResponseMsg;
import net.snake.serverenv.vline.VLineServer;

/**
 * 场景中的怪物 策划文档中的NPC好友也使用该类
 * 
 * @author serv_dev
 * 
 */
public class SceneMonster extends VisibleObject implements IbatisEntity, SMonster, Cloneable, Updatable {
	private static Logger logger = Logger.getLogger(SceneMonster.class);
	/*** 是否进入场景后 才初始化怪物基本属性 ****/
	private boolean enterSceneInitBaseProperty = true;
	/**** 怪物模板 ******/
	private MonsterModel monsterModel;
	/**** 怪物模型id ************/
	private int model;
	/**** 出生点坐标x ***********/
	private short originalX;
	/**** 出生点坐标y *********/
	private short originalY;
	/**** 1,可以复活 0 不可以复活 ***/
	private int relive;
	/*** 攻击设定,主动攻击1/被动攻击2 */
	private Short is;//
	/*** 动态设置的怪物名字 ***/
	private String replaceName;
	/*** 死亡时间 ***/
	protected long dieTime;
	/*** 预约候补怪物时间 ****/
	private long reservationTime = -1;
	/*** 怪物的寿命，－1表示，除非怪物死亡，才会消失，否则只存活，指定时间 ***/
	private int lifetime = -1;
	/*** 怪物加载到场景中的时间 ****/
	private long brontime = 0;
	/*** 变身攻击色怪几率 1/10000 *******/
	private boolean attackcolor = false;
	/*** 变身防御色怪几率 1/10000 *******/
	private boolean defencecolor = false;
	/*** 变身暴击色怪几率 1/10000 *******/
	private boolean exposecolor = false;
	/*** 变身防御色怪几率 1/10000 *******/
	private boolean dodgecolor = false;
	/*** 变身血量色怪几率 1/10000 *******/
	private boolean hpcolor = false;
	/*** 变身金钱色怪几率 1/10000 *******/
	private boolean moneycolor = false;
	/*** 缓存数,色怪种类数 *******/
	private int colorTypeCount;
	/*** 获得金钱数 *******/
	private int money;
	/*** 获得经验数 *******/
	private int exp;
	/*** 掉落加成 单位 是10000 *******/
	private int dropGoodJiacheng = 10000;
	// 怪物掉落的物品 **/
	// private Map<Short, CharacterGoods> dropGoods;
	// /*** 拥有优先拾取物品玩家 组队玩家 包含他的队友 */
	// private Character goodscharacter;
	/*** 怪物的状态机 */
	private MonsterFSM monsterfsm;
	/*** 怪物移动控制器 ***/
	private MonsterMoveController moveController;
	/*** 怪物路径管理器 ***/
	private SceneMonsterLineManager sceneMonsterLineManager;
	/*** 战斗控制器 ****/
	private BaseFightController fightController;
	/*** 怪物技能管理器 **/
	private MonsterSkillManager monsterSkillManager;
	/*** 捕获马管理器 ***/
	private WhoCatchMeManager whoCatchMeManager;
	/*** 受到攻击时呼叫其他怪物的id ****/
	private int[] helperIds;
	/*** 是否 ***/
	private boolean hasSendHelp = false;
	/*** 记录第一个攻击的玩家 ****/
	// private Character firstAttackPlayer;
	/*** 技能距离 **/
	private int skillDistance;
	/*** 怪物状态 **/
	private int status = VisibleObjectState.Idle;
	/*** 物品掉落管理器 **/
	private final SceneMonsterDropGoodManager dropGoodManager = new SceneMonsterDropGoodManager(this);
	/** 追击距离 */
	private int pursuitScope;
	/*** 是否主动攻击 **/
	private boolean initiativeAttack;
	/*** 当前连斩副本中的级别 ***/
	private int instanceLianzhanGrade = 0;
	/*** 连斩副本加成防御 *****/
	private int instanceDefence = 0;
	/*** 连斩副本加成经验 ***/
	private int instanceExp = 0;
	/*** 怪物的视野管理器 */
	private IEyeShotManager eyeshot = new BaseEyeShotManager(this);
	/*** 是否已经倒下,boss死后10秒不倒 **/
	private boolean fallDown = false;
	/*** 怪物ID生成器 分配动态的怪物资源ID,数据库配置的值都小于9000000 */
	private final static IntId monsterid = new IntId(9000000);

	public void setDefence(int defence) {
		super.setDefence(defence);
	}

	/**
	 * 清除怪物,先设置不复活,再设置成消失状态.
	 */
	public void destoryDispose() {
		setRelive(0);
		setObjectState(VisibleObjectState.Dispose);
	}

	/**
	 * 上轮击杀的角色
	 */
	// private static int beforkillerid=0;
	// 对于定时清除的怪，特殊处理一下
	public void destory() {
		super.destroy();
		if (moveController != null) {
			moveController.destory();
		}
		if (sceneMonsterLineManager != null) {
			sceneMonsterLineManager.destory();
		}
		if (whoCatchMeManager != null) {
			whoCatchMeManager.destory();
		}
		scriptPropertiesMap.clear();

	}

	/***
	 * 返回一个唯一的怪物id
	 * 
	 * @return
	 */
	public static int getNewID() {
		return monsterid.getNextId();
	}

	public void setInitiativeAttack(boolean initiativeAttack) {
		this.initiativeAttack = initiativeAttack;
	}

	public void setFallDown(boolean fallDown) {
		this.fallDown = fallDown;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public int getLifetime() {
		return lifetime;
	}

	public long getBrontime() {
		return brontime;
	}

	public boolean isFallDown() {
		return fallDown;
	}

	public SceneMonster() {
		this.enterSceneInitBaseProperty = true;
	}

	public SceneMonster(boolean initBaseProperty) {
		this.enterSceneInitBaseProperty = initBaseProperty;
	}

	public boolean isHorse() {
		return monsterModel.getHorseModelId() > 0;
	}

	@Override
	public boolean isJiTui() {
		return VisibleObjectState.Jitui == getObjectState();
	}

	public MonsterSkillManager getMonsterSkillManager() {
		return monsterSkillManager;
	}

	public int getSkillDistance() {
		return skillDistance;
	}

	public void setSkillDistance(int skillDistance) {
		this.skillDistance = skillDistance;
	}

	public WhoCatchMeManager getWhoCatchMeManager() {
		return whoCatchMeManager;
	}

	public int getRelive() {
		return relive;
	}

	public void setRelive(int relive) {
		this.relive = relive;
	}

	public int getType() {
		return getMonsterModel().getType();
	}

	/**
	 * 对于vo对像,是否已经到了我的警戒范围
	 * 
	 * @param vo
	 *            攻击目标
	 * @return
	 */
	public int getAlert(VisibleObject vo) {
		return monsterModel.getAlert();
	}

	/****
	 * 克隆一个怪物
	 */
	public Object clone() throws CloneNotSupportedException {
		SceneMonster t = new SceneMonster();
		// 只拷贝数据库传来的数据
		t.setId(this.getId());
		t.setModel(this.getModel());
		t.setX(this.getX());
		t.setY(this.getY());
		t.setScene(this.getScene());
		t.setOriginalX(this.getOriginalX());
		t.setOriginalY(this.getOriginalY());
		t.setRelive(this.relive);
		t.helperIds = this.helperIds;
		t.setIs(this.is);
		// t.dropGoodJiacheng=10000;
		// 共享=====================================
		t.setMonsterModel(this.monsterModel);
		return t;
	}

	/****
	 * 添加到场景
	 */
	public void onAddToScene() {
		brontime = System.currentTimeMillis();
		if (enterSceneInitBaseProperty) {
			basePropertyInit();
		}
		setObjectState(VisibleObjectState.Idle);
	}

	/****
	 * 设置怪物的基础属性 动态生成色怪的属性
	 */
	private void basePropertyInit() {
		fallDown = false;
		if (getMonsterModel().getHorseModelId() == 0 && !isBoss()) {
			attackcolor = GenerateProbability.isGenerate(monsterModel.getAttackcolor(), GenerateProbability.gailv);
			defencecolor = GenerateProbability.isGenerate(monsterModel.getDefencecolor(), GenerateProbability.gailv);
			exposecolor = GenerateProbability.isGenerate(monsterModel.getExposecolor(), GenerateProbability.gailv);
			dodgecolor = GenerateProbability.isGenerate(monsterModel.getDodgecolor(), GenerateProbability.gailv);
			hpcolor = GenerateProbability.isGenerate(monsterModel.getHpcolor(), GenerateProbability.gailv);
			moneycolor = GenerateProbability.isGenerate(monsterModel.getMoneycolor(), GenerateProbability.gailv);
		}
		colorTypeCount = (attackcolor ? 1 : 0) + (defencecolor ? 1 : 0) + (exposecolor ? 1 : 0) + (dodgecolor ? 1 : 0) + (hpcolor ? 1 : 0) + (moneycolor ? 1 : 0);

		if (attackcolor) {
			setAttack((int) (monsterModel.getAbnormal() * .5) * monsterModel.getAttack());
		} else {
			setAttack(monsterModel.getAttack());

		}

		if (defencecolor) {
			setDefence(monsterModel.getAbnormal() * monsterModel.getDefence());
		} else {
			setDefence(monsterModel.getDefence());

		}

		if (exposecolor) {
			setCrt(monsterModel.getAbnormal() * monsterModel.getCrt());
		} else {
			setCrt(monsterModel.getCrt());
		}

		if (dodgecolor) {
			setDodge(monsterModel.getAbnormal() * monsterModel.getDodge());
		} else {
			setDodge(monsterModel.getDodge());
		}

		if (moneycolor) {
			setMoney(monsterModel.getAbnormal() * monsterModel.getLm());
		} else {
			setMoney(monsterModel.getLm());
		}

		/**
		 * 其他变异怪物的血量 = 正常血量 * 2 血量变异怪的血量 = 正常血量 * （2+变态倍数）
		 */
		if (hpcolor) {
			setMaxHp((2 + monsterModel.getAbnormal()) * monsterModel.getHp());
		} else {
			if (attackcolor || defencecolor || exposecolor || dodgecolor || moneycolor) {
				setMaxHp(monsterModel.getHp() * 2);
			} else {
				setMaxHp(monsterModel.getHp());
			}
		}
		setNowHp(getMaxHp());
		setAtkColdtime(monsterModel.getAtkColdtime());
		setMoveSpeed(monsterModel.getMovespeed());

		/**
		 * 杀死色怪后的收益加成： 杀怪经验等于：怪物经验 * 变态倍数 * 变身种类个数
		 */
		if (getColorTypeCount() > 0) {
			if (!isBoss()) {
				setExp(monsterModel.getExper() * monsterModel.getAbnormal() * getColorTypeCount());
			} else {
				setExp(monsterModel.getExper());
			}
		} else {
			setExp(monsterModel.getExper());
		}
		getPropertyAdditionController().recompute();
		if (instanceLianzhanGrade > 1) {
			if (instanceDefence > 0) {
				this.setDefence(instanceDefence);
			}
			if (instanceExp > 0) {
				this.setExp(instanceExp);
			}
		}
	}

	public int getPatrolHeight() {
		return monsterModel.getPatrol();
	}

	public int getPatrolWidth() {
		return monsterModel.getPatrol();
	}

	public SceneMonsterLineManager getSceneMonsterLineManager() {
		return sceneMonsterLineManager;
	}

	/**
	 * 引发对c_attacter的攻击
	 * 
	 * @param c_attacter
	 */
	public void fireAttacking(VisibleObject _attacter) {
		if (isDie())
			return;
		setObjectState(VisibleObjectState.Attack);
		// setFirstAttackPlayer(_attacter);
		if (getTarget() != null)
			return;
		getEnmityManager().addEnmityValue(_attacter, 0);

	}

	public void setMonsterModel(MonsterModel model) {
		this.monsterModel = model;
		// 模型重置后将实例的基础属性设置一下
		if (model != null) {
			setModel(model.getId());
			setGrade((short) model.getGrade());
			setPursuitScope(model.getPursuit());
			if (this.getIs() == null || this.getIs() == 0) {
				initiativeAttack = model.getIs() == 1;
			} else {
				initiativeAttack = this.getIs() == 1;
			}
			if (!enterSceneInitBaseProperty) {
				basePropertyInit();
			}
		} else {
			logger.warn("monster model is null");
		}
	}

	public void setPursuitScope(int pursuitScope) {
		this.pursuitScope = pursuitScope;
	}

	public int getPursuitScope() {
		return pursuitScope;
	}

	public boolean isInitiativeAttack() {
		return initiativeAttack;
	}

	public MonsterModel getMonsterModel() {
		return monsterModel;
	}

	public void setOriginalX(short originalX) {
		this.originalX = originalX;
	}

	public void setOriginalY(short originalY) {
		this.originalY = originalY;
	}

	public short getOriginalX() {
		return originalX;
	}

	public short getOriginalY() {
		return originalY;
	}

	/*****
	 * 怪物尸体是否消失
	 * 
	 * @return
	 */
	public boolean isDieTimeOut(long nowtime) {
		return nowtime - dieTime >= getMonsterModel().getRevivificationTime() * 1000;
	}

	public void setDieTimestamp(long timestamp) {
		dieTime = timestamp;
	}

	public long getDieTime() {
		return dieTime;
	}

	/**
	 * 判断是否在巡逻的区域内
	 * 
	 * @param x
	 *            当前的坐标
	 * @param y
	 *            当前的坐标
	 * @return true 在区域内 false 不在区域内了
	 */
	private boolean isInRegion() {//
		return AttackFormula.atkIsEnable2(originalX, originalY, getX(), getY(), (short) getMonsterModel().getPatrol());
	}

	/**
	 * 死亡 1.死亡掉落物品 2.获得经验 3.清空之前身上所有的列表数据
	 */
	@Override
	public void die(VisibleObject visibleObject) {
		if (visibleObject instanceof Hero && !isBoss()) {
			Hero hero = (Hero) visibleObject;
			hero.setKillNum(hero.getKillNum() + GameConstant.KILL_MONSTER_NUM);
			hero.sendMsg(new CharacterOneAttributeMsg49990(hero, EffectType.killNum, hero.getKillNum()));
		}
		super.die(visibleObject);
		dieTime = System.currentTimeMillis();
		setObjectState(VisibleObjectState.Die);

		// if (getMonsterModel().isBoss()) {
		// dieTime = dieTime + 10000;
		// updateObjManager.addFrameUpdateLaterTask(new Runnable() {
		// @Override
		// public void run() {
		// // 只有发了这个消息才会倒下
		// fallDown = true;
		// getEyeShotManager().sendMsg(getDieMsg());
		// }
		// }, 10000);
		// } else {
		// // 只有发了这个消息才会倒下
		// fallDown = true;
		// getEyeShotManager().sendMsg(getDieMsg());
		// }
		// if (whoCatchMeManager != null) {
		// whoCatchMeManager.removeAllCatcher();
		// }
	}

	private void bossDie(VisibleObject killer, FighterVO fightVO) {
		super.die(killer);
		if (killer instanceof Hero) {
			Hero hero = (Hero) killer;
			VLineServer vLineServer = hero.getVlineserver();
			Collection<Hero> characterList = vLineServer.getOnlineManager().getCharacterList();
			PrompMsg msg = new PrompMsg(TipMsg.MSGPOSITION_JAVASCRIP, 60067, hero.getViewName(), this.getMonsterModel().getNameI18n().split("_")[0]);
			for (Hero role : characterList) {
				role.sendMsg(msg);
			}
		}
		fallDown = false;

		// 找到我最仇恨的人
		Enmity hatesetTarget = getEnmityManager().getEnmityBySortPosition(0);
		if (hatesetTarget != null) {
			if (hatesetTarget.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero enmitytarget = (Hero) hatesetTarget.getTarget();
				hateTargetPros(enmitytarget);
			}
		} else {
			// 最仇恨的人也死亡的时候
			this.getDropGoodManager().dropGoodsToScen(null);
		}
		getDropGoodManager().resetItemIndex();
		// 经验分配
		for (Enmity enmity : getEnmityManager().getEnmityList()) {
			if (enmity.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero target = (Hero) enmity.getTarget();
				target.onKillMonster(this);
			}
		}

		if (whoCatchMeManager != null) {
			whoCatchMeManager.removeAllCatcher();
		}
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterDie, new Object[] { this });
		// 副本怪物死亡
		InstanceController instance = this.getSceneRef().getInstanceController();
		if (instance != null) {
			instance.monsterDie(this);
		}
		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();

		// 因为坐骑也会打怪,打怪也会
		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		// 移除所有脚本定义属性
		removeAllAttribute();
		// 临时性首选技能[怪物死亡将重置]
		((MonsterSkillManager) getSkillManager()).clearTopChoiceSkill();
		if (killer.getSceneObjType() == SceneObjType_Hero) {
			((Hero) killer).getContinuumKillSys().tryKillTen();
		}
		die(fightVO.getFighter());
	}

	public void shock(VisibleObject killer, FighterVO fightVO) {
		super.die(killer);
		setObjectState(VisibleObjectState.Shock);
		fallDown = false;
		long timestamp = System.currentTimeMillis();
		this.someoneShocksMe(killer.getId(), Hero.class, this.getId(), SceneMonster.class, timestamp);
		killer.iShockSomeone(killer.getId(), Hero.class, this.getId(), SceneMonster.class, timestamp);

		getEyeShotManager().sendMsg(getShockMsg());

		// 找到我最仇恨的人
		Enmity hatesetTarget = getEnmityManager().getEnmityBySortPosition(0);
		if (hatesetTarget != null) {
			if (hatesetTarget.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero enmitytarget = (Hero) hatesetTarget.getTarget();
				hateTargetPros(enmitytarget);
			}
		} else {
			// 最仇恨的人也死亡的时候
			this.getDropGoodManager().dropGoodsToScen(null);
		}
		try {
			// 经验分配
			for (Enmity enmity : getEnmityManager().getEnmityList()) {
				if (enmity.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero) {
					Hero target = (Hero) enmity.getTarget();
					target.onKillMonster(this);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (whoCatchMeManager != null) {
			whoCatchMeManager.removeAllCatcher();
		}
		// ScriptEventCenter.getInstance().onMonsterDie(null, this);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterDie, new Object[] { this });
		// 副本怪物死亡
		InstanceController instance = this.getSceneRef().getInstanceController();
		if (instance != null) {
			instance.monsterDie(this);
		}
		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();

		// 因为坐骑也会打怪,打怪也会
		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		// 移除所有脚本定义属性
		removeAllAttribute();
		// 临时性首选技能[怪物死亡将重置]
		((MonsterSkillManager) getSkillManager()).clearTopChoiceSkill();
		if (killer.getSceneObjType() == SceneObjType_Hero) {
			((Hero) killer).getContinuumKillSys().tryKillTen();
		}
	}

	/**
	 * 怪物计数统计
	 * 
	 * @param enmitytarget
	 */
	public void bosskillCount(Hero enmitytarget) {
		if (this.getSceneRef().isInstanceScene()) {
			enmitytarget.getDayInCome().dealKillMonster(1);
			return;
		}
		if (getMonsterModel().isBoss()) {
			// BOSS包含在怪物总数中
			enmitytarget.setBossKill(enmitytarget.getBossKill() + 1);
			enmitytarget.getDayInCome().dealKillBoss(1);
			enmitytarget.getMyFactionManager().updatebossKillCount(1);
			enmitytarget.getMyFactionManager().killBossMsg(this);
			// 更新最后击杀Boss的人
			BossLastKillManager.getInstance().update(this, enmitytarget);
		}
		// 精英不再统计 boss改成加1
		// else if (getMonsterModel().isJY()) {
		// enmitytarget.setBossKill(enmitytarget.getBossKill() + 1);
		// enmitytarget.getMyFactionManager().updatebossKillCount(1);
		// }
		enmitytarget.getDayInCome().dealKillMonster(1);
	}

	private void hateTargetPros(Hero enmitytarget) {
		if (enmitytarget == null) {
			return;
		}
		this.getDropGoodManager().dropGoodsToScen(enmitytarget);
		enmitytarget.getContinuumKillSys().onMonsterDie(this);
		bosskillCount(enmitytarget);
		sendToXiongshouOrTeamAboutTask(enmitytarget);
		enmitytarget.getMyCharacterAchieveCountManger().getKillMonster().killMonsterCount(this);

	}

	/**
	 * 发送杀怪的凶手和与其组队队友 (任务检测消息)
	 * 
	 * @param character
	 */
	private void sendToXiongshouOrTeamAboutTask(Hero character) {
		if (character != null) {
			if (character.getMyTeamManager().isTeam()) {
				character.getMyTeamManager().getMyTeam().sendTeamMsg(new MonsterDieMsg11168(this, character), null);
				return;
			}
			character.sendMsg(new MonsterDieMsg11168(this, character));
		}
	}

	/****
	 * 清空怪物的伤害列表
	 */
	public void clearHatesetHurtList() {
		for (Enmity enmity : getEnmityManager().getEnmityList()) {
			enmity.getTarget().getPursuitPointManager().removeArroundWithMeInFightMonsterPositions(this);
		}
		getEnmityManager().clearEnmityList();
	}

	public void haveArest() {
		if (isInRegion()) {
			forgetAnthing();
			setObjectState(VisibleObjectState.Idle);
		} else {
			setObjectState(VisibleObjectState.IsReset);
		}
	}

	/***
	 * 忘记所有攻击过的玩家
	 */
	public void forgetAnthing() {
		hasSendHelp = false;

		// changeNowHp(getPropertyController().getExtraMaxHp() - getNowHp());
		// FightMsgSender.directHurtBroadCase(null, this, (byte) 0);

		// effectController.clearEffectListAndRemoveBuffOnBody();
		for (Enmity enmity : getEnmityManager().getEnmityList()) {
			enmity.getTarget().getPursuitPointManager().removeArroundWithMeInFightMonsterPositions(this);
		}
		getEnmityManager().clearEnmityList();
		// setFirstAttackPlayer(null);
		super.setTarget(null);
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getModel() {
		return model;
	}

	public long getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(long reservationTime) {
		this.reservationTime = reservationTime;
	}

	/**
	 * 验证候补怪物是否可以上场
	 * 
	 * @return
	 */
	public boolean isReadyShow(long now) {
		TimeExpression te = monsterModel.getTimeExpression();
		if (te == null) {
			if (reservationTime == -1) {
				return true;
			} else {
				return now > reservationTime;
			}
			// 这是容错的返回,正常情况下reservationTime=0就应该有值
		} else {
			boolean t = (now > reservationTime && te.isExpressionTime(now));
			return t;
		}
	}

	@Override
	public void update(long now) {
		try {
			if (lifetime != -1) {// 有固定生存时间的
				if (getNowHp() > 0 && now - brontime > lifetime) {
					forgetAnthing();// 先忘掉，
					// 广播
					this.setObjectState(VisibleObjectState.Dispose);
					// FightMsgSender.directHurtBroadCase(null, this, 0,
					// (byte) 0);
					// die(null);
					return;
				}
			}

			super.update(now);
			try {
				monsterfsm.update(now);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public MonsterFSM getMonsterfsm() {
		return monsterfsm;
	}

	/***
	 * 怪物的初始化方法
	 */
	public void init() {
		// 马的初始化
		if (monsterModel == null) {
			return;
		}
		if (monsterModel.getHorseModelId() != 0) {
			whoCatchMeManager = new WhoCatchMeManager(this);
		}
		// 都需要用的
		moveController = new MonsterMoveController(this);
		monsterSkillManager = new MonsterSkillManager(this);
		skillInit();
		fightController = new MonsterFightController(this);
		// 用于巡逻,NPC好友不需要初始化
		sceneMonsterLineManager = new SceneMonsterLineManager(this);

		String helpmodelids = monsterModel.getHelpmodelids();
		if (helpmodelids != null && helpmodelids.length() > 5) {
			String[] _helperIds = helpmodelids.split(",");
			int[] helperIds = new int[_helperIds.length];
			for (int i = 0; i < helperIds.length; i++) {
				helperIds[i] = Integer.parseInt(_helperIds[i]);
			}
			this.helperIds = helperIds;
		}
		// ========================================================

		monsterfsm = new MonsterFSM(this);
		monsterfsm.initFSM();

	}

	public void setModelBasePropertyInit() {
		if (this.monsterModel != null) {
			basePropertyInit();
		}
	}

	/**
	 * 是否被定身
	 * 
	 * @return true 被定身
	 */
	public boolean isImmb() {
		return effectController.isImmb() || effectController.isSleep();
	}

	/****
	 * 设置怪物的攻击目标
	 */
	@Override
	public void setTarget(VisibleObject target) {
		// LOGGER.debug("---------------------------------------------------------------------------怪物设置目标:"
		// + target) ;
		if (target != getTarget()) {
			super.setTarget(target);
			monsterfsm.onChangeTarget(target);
		} else {// 目标没改变?
			super.setTarget(target);
		}

	}

	/****
	 * 怪物 说话
	 * 
	 * @param content
	 *            说话内容
	 */
	public void speek(String content) {
		if (getNowHp() > 0) {
			getEyeShotManager().sendMsg(new MonsterSpeak10298(getId(), content));
		}
	}

	/****
	 * 目标是否在我的追击范围
	 * 
	 * @param target
	 *            目标
	 * @return
	 */
	public boolean isInMyPursuitScope(VisibleObject target) {
		// float distance = DistanceFormula.getDistance2(originalX, originalY,
		// target.getX(), target.getY());
		// getX();
		// getY();
		return AttackFormula.atkIsEnable2(originalX, originalY, target.getX(), target.getY(), (short) pursuitScope);
		// return distance < monsterModel.getPursuit();
	}

	/****
	 * 设置血量
	 */
	@Override
	public void setNowHp(int nowHp) {
		super.setNowHp(nowHp);

		if (getSceneRef() != null) {
			int _maxHp = this.getMaxHp();
			int _helpHpPre = monsterModel.getHelpHp();
			if (!hasSendHelp && getNowHp() < _maxHp * _helpHpPre * 0.01 && AttackFormula.probabilityEnable(monsterModel.getHelpProbability())) {
				hasSendHelp = true;
				int helpercount = 0;// 可以喊过来的人
				// 触发怪物的逃跑模式
				// LOGGER.debug("触发了怪物的喊话呼救，当前血量：{},最大血量:{},喊话呼救血量百分比:{},喊话呼救几率：{}",new
				// Object[]{getNowHp(),_maxHp,_helpHpPre,monsterModel.getHelpProbability()});
				int helpScope = monsterModel.getHelpExtent();
				Collection<SceneMonster> ms = getEyeShotManager().getEyeShortObjs(SceneObjType_MonsterScene);
				for (SceneMonster vo : ms) {
					if (DistanceFormula.getDistanceRound(getX(), getY(), vo.getX(), vo.getY()) <= helpScope && !vo.isDie() && helperIds != null) {// 在呼救的范围内

						for (int i = 0; i < helperIds.length; i++) {
							if (helperIds[i] == vo.getModel()) {// 模型一样的话，过来参战
								helpercount++;
								vo.fireAttacking((Hero) this.getTarget());
							}
						}
					}
				}
				if (helpercount > 0) {
					getEyeShotManager().sendMsg(new MonsterSpeak10298(this.getId(), "HELP ME"));
				}
			}
		}
	}

	@Override
	public IMoveController getMoveController() {
		return moveController;
	}

	@Override
	public BaseFightController getFightController() {
		return fightController;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseSkillManager getSkillManager() {
		return monsterSkillManager;
	}

	/**
	 * 是否变异
	 * 
	 * @return true 变异
	 */
	public boolean variation() {
		return isAttackcolor() || isDefencecolor() || isDodgecolor() || isExposecolor() || isHpcolor() || isMoneycolor();
	}

	// 获得是否是色怪的信息
	public boolean isAttackcolor() {
		return attackcolor;
	}

	public boolean isDefencecolor() {
		return defencecolor;
	}

	public boolean isDodgecolor() {
		return dodgecolor;
	}

	public boolean isExposecolor() {
		return exposecolor;
	}

	public boolean isHpcolor() {
		return hpcolor;
	}

	public boolean isMoneycolor() {
		return moneycolor;
	}

	public int getColorTypeCount() {
		return colorTypeCount;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	/****
	 * 技能初始化
	 */
	public void skillInit() {
		// 先不初始化怪物的高级技能 jack 2011 12 02
		// String[] skillRelStr = skillRel.split(Symbol.FENGHAO);
		String[] skillRelStr = new String[0];

		/** 初始化平砍技能 **/
		if (getMonsterModel().getSkillPingkan() != 0) {
			CharacterSkill pingkanSkill = new CharacterSkill(this, this);
			pingkanSkill.setSkillId(getMonsterModel().getSkillPingkan());
			pingkanSkill.setGrade(getMonsterModel().getSkillPingkangrade());
			pingkanSkill.setTriggerProbability(GenerateProbability.gailv);
			getSkillManager().setPingkanSkill(pingkanSkill);
		} else {
			CharacterSkill pingkanSkill = new CharacterSkill(this, this);
			pingkanSkill.setSkillId(SkillId.MONSTER_PINGKAN_SKILL_ID);
			pingkanSkill.setGrade(1);
			pingkanSkill.setTriggerProbability(GenerateProbability.gailv);
			getSkillManager().setPingkanSkill(pingkanSkill);
		}

		/** 初始化其他技能 **/

		for (int i = 0; i < skillRelStr.length; i++) {
			if ("".equals(skillRelStr[i])) {
				continue;
			}

			String[] skillRelStr2 = skillRelStr[i].split(Symbol.DOUHAO);
			if (skillRelStr2.length < 3) {
				continue;
			}

			int skillId = Integer.parseInt(skillRelStr2[0]);
			int skillGrade = Integer.parseInt(skillRelStr2[1]);
			int skillJilv = Integer.parseInt(skillRelStr2[2]);
			// 定身怪”“化功怪”“绵骨怪”“五毒怪”“吸血怪”“击退怪”
			// （生命值1、内力值2、怒气值3,、吸血5、移动速度6、攻击速度7、内力值上限8、生命值上限9、
			// 会心11、闪避12、命中13、攻击力14、防御力15、封内力16、封体* (门派绝学2)点穴18、(门派绝学3)中毒19、
			// 打落武器20、打落防具21、(门派绝学1)击退22,马奶23）
			CharacterSkill characterSkill = this.monsterSkillManager.addSkill(skillId, skillGrade);
			characterSkill.setTriggerProbability(skillJilv);
			getSkillManager().initPassiveSkillsAddition();
		}
	}

	@Override
	public ResponseMsg getInstanceMoveMsg() {
		return new MonsterInstantMove10122(getId(), getX(), getY());
	}

	private Map<String, Object> scriptPropertiesMap = new HashMap<String, Object>();

	@Override
	public void setAttribute(String key, Object value) {
		scriptPropertiesMap.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return scriptPropertiesMap.get(key);
	}

	@Override
	public Object removeAttribute(String key) {
		return scriptPropertiesMap.remove(key);
	}

	@Override
	public void removeAllAttribute() {
		scriptPropertiesMap.clear();
	}

	@Override
	public ResponseMsg getEnterEyeshotMsg() {
		return new MonsterEnterEyeShot10032(this);
	}

	@Override
	public ResponseMsg getLeaveEyeshotMsg() {
		return new MonsterLeaveEyeShot10096(this.getId());
	}

	@Override
	public IEyeShotManager getEyeShotManager() {
		return eyeshot;
	}

	public SceneMonsterDropGoodManager getDropGoodManager() {
		return dropGoodManager;
	}

	@Override
	public ResponseMsg getDieMsg() {
		long now = System.currentTimeMillis();
		if (lastAttackInfo != null && (now - lastAttackInfo.getAttackTime()) < 50) {
			if (!monsterModel.isBangqiMonster() && (monsterModel.isJY() || monsterModel.isBoss() || GenerateProbability.isGenerate(25, 100))) {
				return new MonsterBeatBackDie20040(this);
			}
		}
		return new MonsterDie20048(this);
	}

	@Override
	public byte getSpriteType() {
		return 2;
	}

	@Override
	public int changeNowHp(int changeV) {
		changeV = onlychangeHpValue(changeV);
		// ScriptEventCenter.getInstance().onMonsterHPChange(null, this,null);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterHPChange, new Object[] { this, null });
		return changeV;
	}

	public int onlychangeHpValue(int changeV) {
		if (changeV == 0)
			return 0;
		int maxHp = this.getPropertyAdditionController().getExtraMaxHp();
		if (this.getNowHp() + changeV > maxHp) {
			changeV = maxHp - this.getNowHp();
		}
		if (this.getNowHp() + changeV < 0) {
			changeV = -this.getNowHp();
		}
		int now = this.getNowHp();
		this.setNowHp(now + changeV);
		return changeV;
	}

	@Override
	public int changeNowMp(int changeV) {
		return 0;
	}

	@Override
	public int changeNowSp(int changeV) {
		return 0;
	}

	@Override
	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
		if (isDie()) {
			return;
		}

		if (getObjectState() == VisibleObjectState.Patrol || getObjectState() == VisibleObjectState.Idle) {
			fireAttacking(fighterVO.getSponsor());
		}

		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) whoAttackMe;
			character.getEffectController().breakCatch();
			character.getFightController().updateAttackMondel(System.currentTimeMillis());
		}

		int reduceHp = fighterVO.getHurtValue();
		int returnHurt = (int) (reduceHp * getPropertyAdditionController().getExtraFtsh() / 100f);
		if (returnHurt < 0)
			returnHurt = 0;
		if (returnHurt > 0) {
			FighterVO _fighterVO = new FighterVO(this, this, fighterVO.getFighter());
			_fighterVO.setHurtValue(returnHurt);
			// logger.info("onBeAttack-------------sceneMonster --2");
			fighterVO.getFighter().onBeAttack(this, _fighterVO);
		}

		// 伤害减免：在受到伤害后，所受伤害*（1-本值）后再作用到人物身上
		reduceHp = (int) (reduceHp * (1 - getPropertyAdditionController().getExtraShjm() / 100f));
		// logger.info(fighterVO.getSkill().getId()+" ---- System.currentTimeMillis()-effectInfo.getBufBeginTime()=="+System.currentTimeMillis());
		List<IHurtListener> listeners = this.getSceneRef().getHurtListeners();
		int size = listeners.size();
		for (int i = 0; i < size; i++) {
			boolean goon = listeners.get(i).beforeHurt(whoAttackMe, this, fighterVO, reduceHp, false);
			if (!goon) {
				return;
			}
		}

		if (reduceHp > 0) {
			hurt(reduceHp, fighterVO, whoAttackMe);
		} else {
			if (fighterVO.isForceAttack()) {
				hurt(reduceHp, fighterVO, whoAttackMe);
				return;
			}
			FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), whoAttackMe, this, 1, fighterVO.getBaoji());
		}

		for (int i = 0; i < size; i++) {
			listeners.get(i).onBehurted(whoAttackMe, this, fighterVO, reduceHp);
		}

	}

	private void hurt(int reduceHp, FighterVO fighterVO, SVO whoAttackMe) {
		if (getMonsterModel().getBeattackHp() > 0) {
			reduceHp = getMonsterModel().getBeattackHp();// 寻宝鼠特殊AI：每次被攻击固定少血2点，无视玩家的攻击力。
		}
		Skill skill = fighterVO.getSkill();
		boolean isSleep = getEffectController().isSleep();
		boolean nohurt = false;
		try {
			if (isSleep && skill != null && skill.getArrowWay() == ArrowWay.NULL) {
				EffectInfo effectInfo = getEffectController().getBufferInBufferListByBufferId(BuffId.sleepId);
				if (effectInfo != null && System.currentTimeMillis() - effectInfo.getBufBeginTime() > 2000) {
					nohurt = true;
					getEffectController().removeEffect(effectInfo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (!nohurt) {
			int changeHp = changeNowHp(-reduceHp);
			AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterHPChange, new Object[] { this, whoAttackMe });
			getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
			getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
			FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), fighterVO.getSponsor(), this, 0, fighterVO.getBaoji());

			if (whoAttackMe instanceof Hero) {
				Hero tmpch = (Hero) whoAttackMe;
				if ((tmpch.getGrade() - this.getGrade() <= 10) && tmpch.getNowSp() < tmpch.getPropertyAdditionController().getExtraMaxSp()) {
					int changeSp = tmpch.getHurtSp(-changeHp);
					CharacterPropertyManager.changeNowSp(tmpch, changeSp);
				}
			}
			if (status == VisibleObjectState.Shock) {
				if (fighterVO.isForceAttack()) {
					die(fighterVO.getFighter());
				}
			} else if (isZeroHp()) {
				if (getType() == 3 || getType() == 10) {
					bossDie(fighterVO.getFighter(), fighterVO);
					return;
				}
				shock(fighterVO.getFighter(), fighterVO);
			}
		}
	}

	@Override
	public void onBeHiddenAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
		if (isDie())
			return;

		if (getObjectState() == VisibleObjectState.Patrol || getObjectState() == VisibleObjectState.Idle) {
			fireAttacking(fighterVO.getSponsor());
		}

		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) whoAttackMe;
			character.getEffectController().breakCatch();
			character.getFightController().updateAttackMondel(System.currentTimeMillis());
			// if (character.getCharacterContinuumKillSys().isTenKill(this)) {
			// if (getMonsterModel().isPT()) {
			// fighterVO.setHurtValue(getNowHp());
			// }
			// }

		}

		if (getMonsterModel().getBeattackHp() > 0) {
			fighterVO.setHiddenValue(getMonsterModel().getBeattackHp());// 寻宝鼠特殊AI：每次被攻击固定少血2点，无视玩家的攻击力。
		}

		if (fighterVO.getHiddenValue() > 0) {
			int changeHp = changeNowHp(-fighterVO.getHiddenValue());
			getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
			getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
			FightMsgSender.hiddeweaponDirectHurtBroadCase(whoAttackMe, this, 0, fighterVO.getBaoji());
			if (status == VisibleObjectState.Shock) {
				die(fighterVO.getFighter());
			} else if (isZeroHp()) {
				if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					Hero character = (Hero) whoAttackMe;
					character.getMyCharacterAchieveCountManger().getCharacterOtherCount().anqiFeiBiaoKillMonsterCount(1);
				}
				shock(fighterVO.getFighter(), fighterVO);

			}
		} else {
			FightMsgSender.hiddeweaponDirectHurtBroadCase(whoAttackMe, this, 1, fighterVO.getBaoji());
		}

	}

	@Override
	public void onEnterScene(Scene scene) {
		setSceneRef(scene);
		onAddToScene();
		getEyeShotManager().onEnterScene(scene, true);
	}

	@Override
	public void onLeaveScene(Scene scene, Scene newscene) {
		getEyeShotManager().onLeaveScene(scene);
	}

	@Override
	public void onBeJiTui(int jituiDistance) {
		super.onBeJiTui(jituiDistance);
		setObjectState(VisibleObjectState.Jitui);
	}

	public float getHpPercent() {
		return getNowHp() / (float) getMaxHp();
	}

	public boolean isBoss() {
		MonsterModel mm = this.getMonsterModel();
		if (mm == null) {
			return false;
		} else {
			return mm.isBoss();
		}
	}

	public boolean isPT() {
		MonsterModel mm = this.getMonsterModel();
		if (mm == null) {
			return false;
		} else {
			return mm.isPT();
		}
	}

	public boolean isDie() {
		if (getObjectState() == VisibleObjectState.Die) {
			return true;
		}
		return false;
	}

	public int getInstanceLianzhanGrade() {
		return instanceLianzhanGrade;
	}

	public void setInstanceLianzhanGrade(int instanceLianzhanGrade) {
		this.instanceLianzhanGrade = instanceLianzhanGrade;
	}

	public int getInstanceDefence() {
		return instanceDefence;
	}

	public void setInstanceDefence(int instanceDefence) {
		this.instanceDefence = instanceDefence;
	}

	public int getInstanceExp() {
		return instanceExp;
	}

	public void setInstanceExp(int instanceExp) {
		this.instanceExp = instanceExp;
	}

	public int getDropGoodJiacheng() {
		return dropGoodJiacheng;
	}

	public void setDropGoodJiacheng(int dropGoodJiacheng) {
		this.dropGoodJiacheng = dropGoodJiacheng;
	}

	public Short getIs() {
		return is;
	}

	public void setIs(Short is) {
		this.is = is;
	}

	public void setAttackcolor(boolean attackcolor) {
		this.attackcolor = attackcolor;
	}

	public void setDefencecolor(boolean defencecolor) {
		this.defencecolor = defencecolor;
	}

	public void setExposecolor(boolean exposecolor) {
		this.exposecolor = exposecolor;
	}

	public void setDodgecolor(boolean dodgecolor) {
		this.dodgecolor = dodgecolor;
	}

	public void setHpcolor(boolean hpcolor) {
		this.hpcolor = hpcolor;
	}

	public void setMoneycolor(boolean moneycolor) {
		this.moneycolor = moneycolor;
	}

	/****
	 * 重加载怪物
	 */
	public void reloadMonster() {
		this.setMonsterModel(getMonsterModel());
		if (this.isDie()) {
			return;
		}
		if (attackcolor) {
			setAttack((int) (monsterModel.getAbnormal() * 0.5) * monsterModel.getAttack());
		} else {
			setAttack(monsterModel.getAttack());

		}
		if (defencecolor) {
			setDefence(monsterModel.getAbnormal() * monsterModel.getDefence());
		} else {
			setDefence(monsterModel.getDefence());

		}
		if (exposecolor) {
			setCrt(monsterModel.getAbnormal() * monsterModel.getCrt());
		} else {
			setCrt(monsterModel.getCrt());
		}
		if (dodgecolor) {
			setDodge(monsterModel.getAbnormal() * monsterModel.getDodge());
		} else {
			setDodge(monsterModel.getDodge());
		}

		if (moneycolor) {
			setMoney(monsterModel.getAbnormal() * monsterModel.getLm());
		} else {
			setMoney(monsterModel.getLm());
		}

		// /**
		// 其他变异怪物的血量 = 正常血量 * 2
		// 血量变异怪的血量 = 正常血量 * （2+变态倍数）
		// */
		// if (hpcolor) {
		// setMaxHp((2 + monsterModel.getAbnormal()) * monsterModel.getHp());
		//
		// } else {
		// if (attackcolor || defencecolor || exposecolor || dodgecolor ||
		// moneycolor)
		// {
		// setMaxHp(monsterModel.getHp() * 2);
		// } else
		// {
		// setMaxHp(monsterModel.getHp());
		// }
		// }
		// //setNowHp(getMaxHp());
		setAtkColdtime(monsterModel.getAtkColdtime());
		setMoveSpeed(monsterModel.getMovespeed());

		/*
		 * 杀死色怪后的收益加成： 杀怪经验等于：怪物经验 * 变态倍数 * 变身种类个数
		 */
		if (getColorTypeCount() > 0) {
			if (!isBoss()) {
				setExp(monsterModel.getExper() * monsterModel.getAbnormal() * getColorTypeCount());
			} else {
				setExp(monsterModel.getExper());
			}
		} else {
			setExp(monsterModel.getExper());
		}
		getPropertyAdditionController().recompute();
		if (instanceLianzhanGrade > 1) {
			if (instanceDefence > 0) {
				this.setDefence(instanceDefence);
			}
			if (instanceExp > 0) {
				this.setExp(instanceExp);
			}
		}
	}

	public boolean isBangqiMonster() {
		return this.getMonsterModel().isBangqiMonster();
	}

	public void setReplaceName(String name) {
		this.replaceName = name;
	}

	@Override
	public String geReplacetName() {
		return this.replaceName;
	}

	@Override
	public void changeAttack(int value) {
		this.setAttack(value);
	}

	@Override
	public void changeDefence(int value) {
		this.setDefence(value);
	}

	@Override
	public void changeDodge(int value) {
		this.setDodge(value);
	}

	@Override
	public void changeCrt(int value) {
		this.setCrt(value);
	}

	@Override
	public void changeMoveSpeed(int value) {
		this.setMoveSpeed(value);
	}

	@Override
	public void changeAttackSpeed(int value) {
		this.setAtkColdtime(value);
	}

	public void changeAttackModel(short is) {
		this.is = is;
		if (is == 1) {
			initiativeAttack = true;
		} else {
			initiativeAttack = false;
		}
	}

	@Override
	public int getMonsterSkillGrade(int skillid) {
		CharacterSkill skill = this.getSkillManager().getCharacterSkillById(skillid);
		if (skill == null) {
			return this.getSkillManager().getPingkanSkill().getGrade();
		}
		return skill.getGrade();
	}

	@Override
	public void changeGrade(short grade) {
		this.setGrade(grade);
	}

	private static final int[] heedSceneObjs = { SceneObjType_Hero, SceneObjType_Horse, SceneObjType_MonsterScene };

	@Override
	public int[] getHeedSceneObject() {
		return heedSceneObjs;
	}

	@Override
	public void changeHp(int nowHp) {
		this.setNowHp(nowHp);
	}

	@Override
	public int getExpUnlimit() {
		return getMonsterModel().getExpUnlimit();
	}

	@Override
	public void setObjectState(int state) {
		List<IStateListener> list = sceneRef.getStateListeners();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (!list.get(i).beforeNewState(this, state)) {
				return;
			}
		}
		if (this.status == VisibleObjectState.Shock) {
			if (state != VisibleObjectState.Die) {
				return;
			}
		}

		monsterfsm.switchState(state);
		this.status = state;

	}

	@Override
	public int getObjectState() {
		return status;
	}

	@Override
	public ResponseMsg getShockMsg() {
		return new MonsterShockMsg(this);
	}

	@Override
	public int getSceneObjType() {
		return SceneObjType_MonsterScene;
	}

	@Override
	public void nextTarget(SMonster monster) {
		setTarget((VisibleObject) monster);
	}

	@Override
	public void nextTarget(SRole hero) {
		setTarget((VisibleObject) hero);
	}

	@Override
	public int getCurrentStat() {
		return this.getObjectState();
	}

	@Override
	public String toString() {
		return "怪物id：" + getId() + " 怪物模型id：" + this.getModel() + " 怪物名字：" + this.getMonsterModel().getName() + "  攻击力：" + propertyController.getExtraAttack() + "防御力: "
				+ propertyController.getExtraDefend() + "攻击速度：" + propertyController.getExtraAttackSpeed() + "移动速度: " + propertyController.getExtraMoveSpeed() + "当前生命值: "
				+ getNowHp() + "最大生命值" + propertyController.getExtraMaxHp() + "当前魔法值: " + getNowMp() + "最大魔法值" + propertyController.getExtraMaxMp() + "当前体力值: " + getNowSp()
				+ "最大体力值" + propertyController.getExtraMaxSp() + "暴击值：" + propertyController.getExtraCrt() + "闪避值：" + propertyController.getExtraDodge();
	}

	@Override
	public short[] removeArroundWithMeInFightMonsterPositions(SMonster monster) {
		SceneMonster sm = (SceneMonster) monster;
		this.getPursuitPointManager().removeArroundWithMeInFightMonsterPositions(sm);
		return null;
	}

	@Override
	public void setStandState() {
		// TODO Auto-generated method stub

	}
}
