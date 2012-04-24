package net.snake.ai.fight.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.program.Updatable;
import net.snake.consts.BuffId;
import net.snake.consts.EffectType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.skill.bean.BuffPersisData;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.CharacterBuffManager;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.util.GenerateSQLUtil;
import net.snake.gamemodel.util.GenerateSQLUtil.CRUD;
import net.snake.shell.Options;
import org.apache.log4j.Logger;

/**
 * 效果控制器 (人物处理buff效果与本类绑定处理) TODO 未实现跳跃
 * 
 * @author serv_dev
 * 
 */
public class EffectController implements Updatable {

	private static final Logger logger = Logger.getLogger(EffectController.class);

	private final SpouseEffectManager spouseEffectManager = new SpouseEffectManager();
	/** 主要的显示效果 */
	private int mainShowEffect;//
	/** 主效果显示技能id */
	private int mainShowSkillId;//
	/***/
	private VisibleObject vo;
	/** 人物buff的效果 */
	private final List<EffectInfo> effectlList = new CopyOnWriteArrayList<EffectInfo>();//
	/** 人物buff的效果id */
	private final Set<Integer> effectKey = new CopyOnWriteArraySet<Integer>();// //
	// private int immb = 0;//若为1则不能定身，为0为正常状态。定身状态为-1 无法进行移动/跳跃/攻击
	// private int mpOver = 0;//若为1则不能把mp设置为空，为0为正常状态。mp为空的时候设置为-1
	// private int spOver = 0;//若为1则不能把sp设置为空，为0为正常状态。sp为空的时候设置为-1
	// private int noWeapon = 0;////若为1则防止打落武器，为0为正常状态。打落武器的时候设置为-1
	// private int noDefend = 0;//若为1则防止打落防具，为0为正常状态。打落防具的时候设置为-1
	/** 是否有连斩系统buffer效果 */
	private boolean continuumKill = false; //
	/** 连斩系统buffer效果 对精英怪物 boss 伤害倍数 */
	private int ContinuumKillHate = 1;//
	/** 连斩系统的经验加成 加成倍数 */
	private float continuumExp = 0f;//
	/** 双倍经验(药品buff) */
	private volatile boolean doubleExp = false;//
	/** 五倍经验丹(药品buff) */
	private volatile boolean double5Exp = false;//
	/** 十倍经验丹(药品buff) */
	private volatile boolean double10Exp = false;//
	/** 双倍坐骑经验(药品buff) */
	private volatile boolean doubleHorseExp = false;//
	/** 双倍真气(药品buff) */
	private volatile boolean doubleZhenqi = false;//
	/** 初始化临时存储 */
	private List<BuffPersisData> buffPersisDataList;//
	/** 被击退延时时间 */
	private int jiTuiTime;//
	/** 被击退开始时间 */
	private long jiTuiBeginTime;//
	/** 是否被击退(用于暴击数值计算，不做其他使用) */
	private boolean isJiTuiBaoJi;//
	/** pk保护 */
	private volatile boolean ispkProtect = false;//
	/** pk保护buff */
	private volatile EffectInfo pkProtectEffect;//
	/** 武神临体buff */
	private volatile EffectInfo wushenBuff;//
	/** 战意激昂buff */
	private volatile EffectInfo zhanyiBuff;//

	/** 连斩buff时间 */
	private volatile EffectInfo continuumKillBuff;//

	/** 狂攻药剂 */
	private volatile EffectInfo kgYaoBuff;//
	/** 健体药剂 */
	private volatile EffectInfo jtYaoBuff;//
	/** 防御药剂 */
	private volatile EffectInfo fyYaoBuff;//
	/** 轻身药剂 */
	private volatile EffectInfo qsYaoBuff;//

	/** 错骨分筋 (攻击速度降低比率：减至500) */
	private volatile boolean reduceAttackSpeedBuff = false;//
	/** 狮吼功(移动速度降低比率：减至80) */
	private volatile boolean reduceMoveSpeedBuff = false;//
	/** 千幻掌 */
	private volatile boolean reduceDodgeBuff = false;//
	/** 七煞掌 */
	private volatile boolean reduceCrtBuff = false;//

	/** 无敌的buff */
	private volatile boolean isUnWithstand = false;//

	/** 风雨同济 */
	private volatile EffectInfo fengYuTongJiBuff;//

	/** 守护的buff */
	private volatile boolean isGuard = false;//

	/** 失去防御 */
	private volatile boolean nodefence = false;//
	/** 沉睡 */
	private volatile boolean sleep = false;//

	public boolean isSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}

	public boolean isNodefence() {
		return nodefence;
	}

	public void setNodefence(boolean nodefence) {
		this.nodefence = nodefence;
	}

	public void destory() {
		if (effectlList != null) {
			effectlList.clear();
		}
		if (effectKey != null) {
			effectKey.clear();
		}
		if (buffPersisDataList != null) {
			buffPersisDataList.clear();
		}

	}

	public final SpouseEffectManager getSpouseEffectManager() {
		return spouseEffectManager;
	}

	/**
	 * 守护的buff
	 * 
	 * @return
	 */
	public boolean isGuard() {
		return isGuard;
	}

	public void setGuard(boolean isGuard) {
		this.isGuard = isGuard;
	}

	public boolean isFengYuTongJi() {
		return fengYuTongJiBuff != null;
	}

	public void setFengYuTongJi(EffectInfo fengYuTongJiBuff) {
		this.fengYuTongJiBuff = fengYuTongJiBuff;
	}

	public EffectInfo getFengYuTongJiBuff() {
		return fengYuTongJiBuff;
	}

	/**
	 * 给自己添加无敌的buff
	 * 
	 * @param durion
	 *            默认传0
	 */
	public void addUnWithstandBuff(int durion) {
		SkillEffect skillEffect = SkillEffectManager.getInstance().getSkillEffectById(BuffId.WuDiBuff);
		EffectInfo effectInfo = new EffectInfo(skillEffect);
		if (durion > 0) {
			effectInfo.setDuration(durion);
		}
		addEffect(effectInfo);

	}

	// /**
	// * 清空所有身上的经脉debuff
	// */
	// public void clearAllDebuffJingmai(){
	//
	// List<EffectInfo> tmpRmvList = new ArrayList<EffectInfo>();
	// for (Iterator<EffectInfo> iterator = getBuffList().iterator();
	// iterator.hasNext();) {
	// EffectInfo effectInfo = iterator.next();
	// if (effectInfo.getJingmaiId() != null &&
	// !"".equals(effectInfo.getJingmaiId())) {
	// tmpRmvList.add(effectInfo);
	// }
	// }
	//
	// if (!tmpRmvList.isEmpty()) {
	// for (Iterator<EffectInfo> iterator = tmpRmvList.iterator();
	// iterator.hasNext();) {
	// EffectInfo effectInfo = iterator.next();
	// try {
	// removeEffect(effectInfo);
	// } catch (Exception e) {
	// logger.error(e.getMessage(),e);
	// }
	// }
	// }
	// }

	/**
	 * 是否是10倍的经验生效
	 * 
	 * @return true生效
	 */
	public boolean isDouble10Exp() {
		return double10Exp;
	}

	public void setDouble10Exp(boolean double10Exp) {
		this.double10Exp = double10Exp;
	}

	/**
	 * 是否是5倍的经验生效
	 * 
	 * @return true生效
	 */
	public boolean isDouble5Exp() {
		return double5Exp;
	}

	public void setDouble5Exp(boolean double5Exp) {
		this.double5Exp = double5Exp;
	}

	/**
	 * 打断效果中的无敌状态
	 */
	public void breakCatch() {
		if (Options.IsCrossServ) {
			if (isUnWithstand) {
				this.removeBuffById(BuffId.lunjiantaifuhuobaohu_Buff);
			}
		}
	}

	/**
	 * 是否有无敌的buff
	 * 
	 * @return ture 有
	 */
	public boolean isUnWithstand() {
		return isUnWithstand;
	}

	public void setUnWithstand(boolean isUnWithstand) {
		this.isUnWithstand = isUnWithstand;
	}

	/**
	 * 是否中了千幻掌
	 * 
	 * @return ture 是
	 */
	public boolean isReduceDodgeBuff() {
		return reduceDodgeBuff;
	}

	public void setReduceDodgeBuff(boolean reduceDodgeBuff) {
		this.reduceDodgeBuff = reduceDodgeBuff;
	}

	/**
	 * 七煞掌
	 * 
	 * @return ture 是
	 */
	public boolean isReduceCrtBuff() {
		return reduceCrtBuff;
	}

	public void setReduceCrtBuff(boolean reduceCrtBuff) {
		this.reduceCrtBuff = reduceCrtBuff;
	}

	/**
	 * 狮吼功
	 * 
	 * @return ture 是
	 */
	public boolean isReduceMoveSpeedBuff() {
		return reduceMoveSpeedBuff;
	}

	public void setReduceMoveSpeedBuff(boolean reduceMoveSpeedBuff) {
		this.reduceMoveSpeedBuff = reduceMoveSpeedBuff;
	}

	/**
	 * 错骨分筋
	 * 
	 * @return ture 是
	 */
	public boolean isReduceAttackSpeedBuff() {
		return reduceAttackSpeedBuff;
	}

	public void setReduceAttackSpeedBuff(boolean reduceAttackSpeedBuff) {
		this.reduceAttackSpeedBuff = reduceAttackSpeedBuff;
	}

	/**
	 * 狂攻药剂
	 * 
	 * @return
	 */
	public EffectInfo getKgYaoBuff() {
		return kgYaoBuff;
	}

	public void setKgYaoBuff(EffectInfo kgYaoBuff) {
		this.kgYaoBuff = kgYaoBuff;
	}

	/**
	 * 健体药剂
	 * 
	 * @return
	 */
	public EffectInfo getJtYaoBuff() {
		return jtYaoBuff;
	}

	public void setJtYaoBuff(EffectInfo jtYaoBuff) {
		this.jtYaoBuff = jtYaoBuff;
	}

	/**
	 * 防御药剂
	 * 
	 * @return
	 */
	public EffectInfo getFyYaoBuff() {
		return fyYaoBuff;
	}

	public void setFyYaoBuff(EffectInfo fyYaoBuff) {
		this.fyYaoBuff = fyYaoBuff;
	}

	/**
	 * 轻身药剂
	 * 
	 * @return
	 */
	public EffectInfo getQsYaoBuff() {
		return qsYaoBuff;
	}

	public void setQsYaoBuff(EffectInfo qsYaoBuff) {
		this.qsYaoBuff = qsYaoBuff;
	}

	/**
	 * 武神临体buff
	 * 
	 * @return
	 */
	public boolean isWushen() {
		return wushenBuff != null;
	}

	/**
	 * 连斩buff
	 * 
	 * @return
	 */
	public EffectInfo getContinuumKillBuff() {
		return continuumKillBuff;
	}

	public void setContinuumKillBuff(EffectInfo continuumKillBuff) {
		this.continuumKillBuff = continuumKillBuff;
	}

	/**
	 * 战意激昂
	 * 
	 * @return
	 */
	public boolean isZhanyi() {
		return zhanyiBuff != null;
	}

	/**
	 * 武神临体
	 * 
	 * @return
	 */
	public EffectInfo getWushenBuff() {
		return wushenBuff;
	}

	public void setWushenBuff(EffectInfo wushenBuff) {
		this.wushenBuff = wushenBuff;
	}

	/**
	 * 战意激昂
	 * 
	 * @return
	 */
	public EffectInfo getZhanyiBuff() {
		return zhanyiBuff;
	}

	public void setZhanyiBuff(EffectInfo zhanyiBuff) {
		this.zhanyiBuff = zhanyiBuff;
	}

	/**
	 * 人物主动发起对其他玩家的PK，则和平保护BUFF立即消失，返回系统消息提示： “您对其他玩家发起了PK，和平保护状态消失了“
	 */
	public void clearPkProtectEffect(boolean pk) {

		if (pkProtectEffect != null) {
			try {
				this.removeEffect(pkProtectEffect);
				if (pk) {
					vo.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 503));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			pkProtectEffect = null;
		}
	}

	public void setPkProtectEffect(EffectInfo pkProtectEffect) {
		this.pkProtectEffect = pkProtectEffect;
	}

	/**
	 * pk和平保护
	 * 
	 * @return
	 */
	public boolean isIspkProtect() {
		return ispkProtect && vo.getSceneRef().isPkProtect();
	}

	public void setIspkProtect(boolean ispkProtect) {
		this.ispkProtect = ispkProtect;
	}

	/**
	 * 是否被击退
	 * 
	 * @return
	 */
	public boolean isJiTuiBaoJi() {
		return isJiTuiBaoJi;
	}

	public void setJiTuiBaoJi(boolean isJiTui) {
		this.isJiTuiBaoJi = isJiTui;
	}

	/**
	 * 是否被击退开始时间
	 * 
	 * @return
	 */
	public long getJiTuiBeginTime() {
		return jiTuiBeginTime;
	}

	public void setJiTuiBeginTime(long jiTuiBeginTime) {
		this.jiTuiBeginTime = jiTuiBeginTime;
	}

	/**
	 * 被击退延时时间
	 * 
	 * @return
	 */
	public int getJiTuiTime() {
		return jiTuiTime;
	}

	public void setJiTuiTime(int jiTuiTime) {
		this.jiTuiTime = jiTuiTime;
	}

	/**
	 * 连斩系统的经验加成
	 * 
	 * @return
	 */
	public float getContinuumExp() {
		return continuumExp;
	}

	public void setContinuumExp(float continuumExp) {
		this.continuumExp = continuumExp;
	}

	/**
	 * 是否双倍经验
	 * 
	 * @return
	 */
	public boolean isDoubleExp() {
		return doubleExp;
	}

	public void setDoubleExp(boolean doubleExp) {
		this.doubleExp = doubleExp;
	}

	/**
	 * 是否坐骑双倍经验
	 * 
	 * @return
	 */
	public boolean isDoubleHorseExp() {
		return doubleHorseExp;
	}

	public void setDoubleHorseExp(boolean doubleHorseExp) {
		this.doubleHorseExp = doubleHorseExp;
	}

	/**
	 * 是否双倍真气
	 * 
	 * @return
	 */
	public boolean isDoubleZhenqi() {
		return doubleZhenqi;
	}

	public void setDoubleZhenqi(boolean doubleZhenqi) {
		this.doubleZhenqi = doubleZhenqi;
	}

	/**
	 * 是否有连斩系统buffer效果
	 * 
	 * @return
	 */
	public boolean isContinuumKill() {
		return continuumKill;
	}

	/**
	 * 连斩系统buffer效果 对精英怪物 boss 伤害倍数
	 * 
	 * @return
	 */
	public int getContinuumKillHate() {
		return ContinuumKillHate;
	}

	public void setContinuumKill(boolean continuumKill) {
		this.continuumKill = continuumKill;
	}

	public void setContinuumKillHate(int continuumKillHate) {
		ContinuumKillHate = continuumKillHate;
	}

	public boolean isMpOver() {
		return hasBuff(EffectType.fengMp);
	}

	public boolean isSpOver() {
		return hasBuff(EffectType.fengSp);
	}

	public boolean isNoWeapon() {
		return hasBuff(EffectType.fengwuqi);
	}

	public boolean isNoDefend() {
		return hasBuff(EffectType.fengfanju);
	}

	public EffectController(VisibleObject vo) {
		this.vo = vo;
	}

	public VisibleObject getVo() {
		return vo;
	}

	public int getMainShowSkillId() {
		return mainShowSkillId;
	}

	public void setMainShowSkillId(int mainShowSkillId) {
		this.mainShowSkillId = mainShowSkillId;
	}

	public int getMainShowEffect() {
		return mainShowEffect;
	}

	public void setMainShowEffect(int mainShowEffect) {
		this.mainShowEffect = mainShowEffect;
	}

	/**
	 * 是否被定身
	 * 
	 * @return true 被定身
	 */
	public boolean isImmb() {
		return hasBuff(EffectType.dianxue) || hasBuff(EffectType.jueji);
	}

	/**
	 * 是否有这个buff
	 * 
	 * @param buffType
	 * @return true 有
	 */
	private boolean hasBuff(int buffType) {
		List<EffectInfo> buffList = getBuffList();
		for (Iterator<EffectInfo> iterator = buffList.iterator(); iterator.hasNext();) {
			EffectInfo effectInfo = iterator.next();
			if (effectInfo.getType() == buffType) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 清除身上所有的buff 比如对象死亡的时候
	 */
	public void clearEffectListAndRemoveBuffOnBody() {
		try {
			List<EffectInfo> list = new ArrayList<EffectInfo>(effectlList);
			int _size = list.size();
			if (_size > 0) {
				Iterator<EffectInfo> iterator = list.iterator();
				for (int i = 0; i < _size; i++) {
					EffectInfo _effect = iterator.next();
					if (_effect.getIsDieClean() == 1) {
						removeEffect(_effect);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// logger.error(e.getMessage(),e);
		}
	}

	/**
	 * gm命令
	 */
	public void clearAllEffectListAndRemoveBuffOnBody() {
		try {

			List<EffectInfo> list = new ArrayList<EffectInfo>(effectlList);
			int _size = list.size();
			if (_size > 0) {
				Iterator<EffectInfo> iterator = list.iterator();
				for (int i = 0; i < _size; i++) {
					EffectInfo _effect = iterator.next();
					removeEffect(_effect);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 身上是否有buff
	 * 
	 * @param effectId
	 * @return
	 */
	public boolean isContains(int effectId) {
		return effectKey.contains(effectId);
	}

	/**
	 * 添加一个效果
	 * 
	 * @param effect
	 *            效果
	 * @throws Exception
	 */
	public boolean addEffect(EffectInfo addEffectInfo) {
		if (vo instanceof SceneBangqiMonster) {
			return false;
		}
		try {
			if (add(addEffectInfo)) {
				FightMsgSender.broastSustainEffect(addEffectInfo, vo);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 根据id给自身加一个buff
	 * 
	 * @param buffId
	 * @return true 成功添加
	 */
	public boolean addEffect(int buffId) {
		if (vo instanceof SceneBangqiMonster) {
			return false;
		}

		SkillEffect skillEffect = SkillEffectManager.getInstance().getSkillEffectById(buffId);
		if (skillEffect == null)
			return false;

		EffectInfo effectInfo = new EffectInfo(skillEffect);
		effectInfo.setAffecter(vo);
		effectInfo.setAttacker(vo);
		return addEffect(effectInfo);
	}

	/**
	 * 添加一个效果 没有发送消息
	 * 
	 * @param effect
	 *            效果
	 * @throws Exception
	 */
	private boolean add(EffectInfo addEffectInfo) throws Exception {
		if (vo.getObjectState() == VisibleObjectState.Die) {
			return false;
		}
		int _size = effectlList.size();
		boolean _hasfind = false;
		EffectInfo _tmpEffect = null;
		// 寻找同类的效果 有的话就替换之
		for (int i = 0; i < _size; i++) {
			if (_hasfind)
				break;
			_tmpEffect = effectlList.get(i);
			EffectInfo _onBodyEffectInfo = (EffectInfo) _tmpEffect;
			if ((_onBodyEffectInfo.getType() == addEffectInfo.getType() && addEffectInfo.getType() == 32) || _onBodyEffectInfo.getId() == addEffectInfo.getId()) {// 是同一类型的效果
				// 效果叠加1、效果替换2、重复无效3
				Short ero = _onBodyEffectInfo.getEffectRepeatOption();
				switch (ero) {
				case 1:
					_hasfind = true;
					int goTime = (int) (System.currentTimeMillis() - _onBodyEffectInfo.getBufBeginTime());
					int remainDuration = (_onBodyEffectInfo.getDuration() + addEffectInfo.getDuration() - goTime);
					addEffectInfo.setDuration(remainDuration < 0 ? 0 : remainDuration);
					addEffectInfo.setDuration2(_onBodyEffectInfo.getDuration2());

					addEffectInfo.setRemainPoint(_onBodyEffectInfo.getRemainPoint() + addEffectInfo.getHurtValue());// 这里注意。只有血池类的使用
																													// 以后起冲突，在skillEffect中另起一个字段标示是血池字段
					addEffectInfo.setDelayRecoverTime(_onBodyEffectInfo.getDelayRecoverTime());
					if (addEffectInfo.init(this)) {
						removeEffect(_onBodyEffectInfo);
						_addEffect(null, addEffectInfo);
						return true;
					} else {
						return false;
					}
				case 2:
					return _addEffect(_onBodyEffectInfo, addEffectInfo);
				case 3:
					_hasfind = true;
					break;
				}
				break;
			}
		}

		if (!_hasfind) {
			return _addEffect(null, addEffectInfo);
		} else {
			return false;
		}
	}

	/**
	 * 删除一个效果
	 * 
	 * @param $effect
	 *            效果
	 * @throws Exception
	 */
	public boolean removeEffect(EffectInfo $effect) throws Exception {

		if (effectlList.remove($effect)) {
			effectKey.remove($effect.getId());
			FightMsgSender.sendCancelSustainEffectMsg(vo, $effect);
			$effect.leave(this);
			$effect.destroy();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据buff条件来移除效果
	 * 
	 * @param $effect
	 *            效果
	 * @throws Exception
	 */
	private void removeEffectByCondition(EffectInfo $effect) throws Exception {

		if ($effect.leave(this)) {
			if (effectlList.remove($effect)) {
				effectKey.remove($effect.getId());
				FightMsgSender.sendCancelSustainEffectMsg(vo, $effect);
			}
			$effect.destroy();
		}
	}

	/**
	 * buff施加在身上的处理 步骤：1.如果身上的buff有直接影响血量的话，直接处理掉
	 * 
	 * @return 有特效让人物无法做任何动作时，返回false.（轮询不处理了）
	 * @throws Exception
	 */
	public void update(long now) {
		if (effectlList.isEmpty()) {
			return;
		}
		EffectInfo effect = null;
		List<EffectInfo> tmpRemoveList = new ArrayList<EffectInfo>();
		for (Iterator<EffectInfo> iterator = effectlList.iterator(); iterator.hasNext();) {
			effect = iterator.next();
			if (effect == null) {
				continue;
			}

			if (effect.leaveCondition(now)) {
				tmpRemoveList.add(effect);
			}

			if (effect.isAttackerDieClean()) {
				VisibleObject vo = effect.getAttacker();
				if (vo == null || vo.isZeroHp() || vo.getIsonline() == 0) {
					tmpRemoveList.add(effect);
				}

			}
		}

		// buff时间到了，移除到buff的处理
		Iterator<EffectInfo> tmpIterator = tmpRemoveList.iterator();
		while (tmpIterator.hasNext()) {
			// LOGGER.debug("移除 {} 身上的buff",vo.getId());
			EffectInfo skillEffect = tmpIterator.next();
			try {
				removeEffectByCondition(skillEffect);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		tmpRemoveList = null;
	}

	/**
	 * 添加效果
	 * 
	 * @param onBodyEffect
	 *            在身上同类的效果
	 * @param addEffect
	 *            要添加的效果
	 * @return 身上移除同类的效果
	 * @throws Exception
	 */
	private boolean _addEffect(EffectInfo onBodyEffect, EffectInfo addEffect) throws Exception {

		if (!addEffect.init(this)) {
			return false;
		}

		// EffectInfo _removeEffect = null;
		if (onBodyEffect == null) {
			return _$addEffect(addEffect);
		} else {
			int _nowBuffValue = Math.abs(addEffect.getBufValue());// TODO
																	// 要加入的buff
																	// 给其设置值
			int _oldBuffValue = Math.abs(onBodyEffect.getBufValue());
			int _diffBuffValue = _nowBuffValue - _oldBuffValue;
			if (_diffBuffValue >= 0) {// 如果产生的效果值大于已产生的效果值才替换
				// _removeEffect = onBodyEffect;

				int goTime = (int) (System.currentTimeMillis() - onBodyEffect.getBufBeginTime());
				int _goTime = goTime - (onBodyEffect.getDuration() - onBodyEffect.getDuration2());
				if (_goTime > 0) {
					addEffect.setDuration2(onBodyEffect.getDuration2() - _goTime <= 0 ? 0 : onBodyEffect.getDuration2() - _goTime);
					addEffect.setDuration(addEffect.getDuration() + addEffect.getDuration2());
				} else {
					addEffect.setDuration2(onBodyEffect.getDuration2());
					addEffect.setDuration(addEffect.getDuration() + addEffect.getDuration2());
				}

				removeEffect(onBodyEffect);
				return _$addEffect(addEffect);
			}

			return false;
		}

	}

	/**
	 * 根据buffid移除buff
	 * 
	 * @param bufferId
	 */
	public boolean removeBuffById(int bufferId) {
		EffectInfo effect = getBufferInBufferListByBufferId(bufferId);
		try {
			return removeEffect(effect);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 添加buff
	 * 
	 * @param $effect
	 * @return
	 * @throws Exception
	 */
	private boolean _$addEffect(EffectInfo $effect) throws Exception {

		if (!$effect.enter(this)) {
			return false;
		}
		$effect.setBufBeginTime(System.currentTimeMillis());
		effectlList.add($effect);
		effectKey.add($effect.getId());
		if (vo.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			// 激活怪物
			SceneMonster sm = (SceneMonster) vo;
			VisibleObject vo = $effect.getAttacker();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero c_attacter = (Hero) vo;
				if (sm.getObjectState() == VisibleObjectState.Patrol || sm.getObjectState() == VisibleObjectState.Idle) {
					sm.fireAttacking(c_attacter);
				}
			}
		}

		if ($effect.isImmb()) {// 定身
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero character = (Hero) vo;
				character.getMoveController().stopMove();
				character.setObjectState(VisibleObjectState.Idle);
			} else {
				SceneMonster sm = (SceneMonster) vo;
				sm.getMoveController().stopMove();
			}
		}
		return true;
	}

	/**
	 * 一个buff剩余得时间
	 * 
	 * @param bufferId
	 * @return 如果buff不存在或者buff失效都将返回0
	 */
	public long getBuffRemainTime(int bufferId) {
		EffectInfo effectInfo = getBufferInBufferListByBufferId(bufferId);
		if (effectInfo != null) {
			long gotime = System.currentTimeMillis() - effectInfo.getBufBeginTime();
			long remainTime = effectInfo.getDuration() - gotime;
			if (remainTime < 0)
				remainTime = 0;
			return remainTime;
		}
		return 0;
	}

	public List<EffectInfo> getBuffList() {
		return this.effectlList;
	}

	/**
	 * 下线时保存
	 */
	public void save() {

		List<EffectInfo> effects = getBuffList();
		List<String> saveSQLs = new LinkedList<String>();
		if (buffPersisDataList == null) {
			saveSQLs.add(GenerateSQLUtil.CharacterBuffSQL(null, getVo().getId(), CRUD.delete));
		}
		if (!effects.isEmpty()) {
			long nowtime = System.currentTimeMillis();
			for (Iterator<EffectInfo> iterator = effects.iterator(); iterator.hasNext();) {
				EffectInfo baseSkillEffect = iterator.next();
				try {
					if (baseSkillEffect == null)
						continue;
					if (baseSkillEffect.getBuff().isSave()) {
						BuffPersisData buffPersisData = new BuffPersisData();
						buffPersisData.setBuffvalue(baseSkillEffect.getBufValue());
						buffPersisData.setCharacterid(getVo().getId());
						buffPersisData.setDuration(baseSkillEffect.getDuration());
						buffPersisData.setEffectId(baseSkillEffect.getId());
						buffPersisData.setGotime((int) (nowtime - baseSkillEffect.getBufBeginTime()));
						buffPersisData.setRelevanceSkillid(baseSkillEffect.getRelevanceSkillId());
						buffPersisData.setDuration2(baseSkillEffect.getDuration2());
						buffPersisData.setRemainpoint(baseSkillEffect.getRemainPoint());
						buffPersisData.setDelayrecoverTime(baseSkillEffect.getDelayRecoverTime());
						buffPersisData.setEffectInfoType(baseSkillEffect.getEffectInfoType());
						buffPersisData.setJingmaiId(baseSkillEffect.getJingmaiId());
						saveSQLs.add(GenerateSQLUtil.CharacterBuffSQL(buffPersisData, getVo().getId(), CRUD.create));
					}
				} catch (Exception e) {
					logger.error("find err when saving effect ,effect id:"+baseSkillEffect.getName());
					continue;
				}
			}
		}

		CharacterManager.getInstance().excuteSql(saveSQLs);
		effects = null;
	}

	/**
	 * 上线初始化
	 * 
	 * @throws Exception
	 */
	public void init() {
		try {
			if (getVo().getSceneObjType() == SceneObj.SceneObjType_Hero) {
				List<BuffPersisData> buffPersisDataList = CharacterBuffManager.getInstance().getListByCharacterId(getVo().getId());
				this.buffPersisDataList = buffPersisDataList;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 进入场景初始化 包括 1.添加各个buff到身上 2.发送消息
	 */
	public void initStatus() {

		if (buffPersisDataList != null && !buffPersisDataList.isEmpty()) {
			for (Iterator<BuffPersisData> iterator = buffPersisDataList.iterator(); iterator.hasNext();) {
				try {
					BuffPersisData buffPersisData = iterator.next();
					if (buffPersisData == null)
						continue;
					SkillEffect skillEffect = SkillEffectManager.getInstance().getCacheSkillEffect().get(buffPersisData.getEffectId());
					EffectInfo effectInfo = new EffectInfo(skillEffect);
					effectInfo.setDuration((buffPersisData.getDuration() - buffPersisData.getGotime()));
					effectInfo.setBufValue(buffPersisData.getBuffvalue());
					effectInfo.setRelevanceSkillId(buffPersisData.getRelevanceSkillid());
					effectInfo.setAffecter(getVo());
					effectInfo.setDuration2(buffPersisData.getDuration2());
					effectInfo.setRemainPoint(buffPersisData.getRemainpoint());
					effectInfo.setDelayRecoverTime(buffPersisData.getDelayrecoverTime());
					effectInfo.setEffectInfoType(buffPersisData.getEffectInfoType());
					effectInfo.setJingmaiId(buffPersisData.getJingmaiId());
					int effectInfoType = effectInfo.getEffectInfoType();
					if (effectInfoType == EffectType.wslt) {
						setWushenBuff(effectInfo);
					} else if (effectInfoType == EffectType.zyjy) {
						setZhanyiBuff(effectInfo);
					}
					addEffect(effectInfo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					// logger.error(e.getMessage(),e);
					continue;
				}
			}
		}

		buffPersisDataList = null;
	}

	/**
	 * 通过id获得某个buff 在轮询的时候，如果检查一个buff，不推荐使用该方法
	 * 
	 * @param bufferId
	 * @return true 有这个buff
	 */
	public EffectInfo getBufferInBufferListByBufferId(int bufferId) {
		List<EffectInfo> list = getBuffList();
		for (EffectInfo effect : list) {
			if (effect.getEffect().getId() == bufferId) {
				return effect;
			}
		}
		return null;
	}

}