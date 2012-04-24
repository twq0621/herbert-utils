package net.snake.ai.fight.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.ai.EnmityManager;
import net.snake.ai.fight.bean.CharacterDayPkRecord;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.CharacterEffectHandler;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CharacterFaceMsg10088;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.ClientConfig;
import net.snake.consts.EffectType;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.DaypkRecord;
import net.snake.gamemodel.fight.persistence.DaypkRecordManager;
import net.snake.gamemodel.hero.bean.Enmity;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.onhoor.response.ChangeTargetMsg50594;
import net.snake.gamemodel.skill.bean.CommonTime;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.gamemodel.util.GenerateSQLUtil;
import net.snake.gamemodel.util.GenerateSQLUtil.CRUD;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 
 * 人物战斗管理者
 * 
 */
public class CharacterFightController extends BaseFightController {

	public CharacterFightController(VisibleObject vo) {
		super(vo);
		character = (Hero) vo;
		setEffectHandler(new CharacterEffectHandler(vo));
	}

	private static final Logger logger = Logger.getLogger(CharacterFightController.class);

	private Hero character;
	private FighterVO fighterVO = null;

	/** pk模式 0.和平 1.组队 2帮派3.全体 */
	private Integer pkModel;//
	/***/
	private long attackModelBeginTime;
	/** 平砍时间定格 */
	private long timelag;//
	/** 自身关闭时间 */
	private long closeTime;//
	/** 人物普通攻击的技能id */
	private CharacterSkill commonskill;//
	/** 人物默认的使用技能 */
	private CharacterSkill defaultSkill;//
	/***/
	private int msg10906Cnt = 0;
	/** 可以攻击的自卫目标 开始时间 */
	private Map<Integer, Long> ziweiTargetSet = new ConcurrentHashMap<Integer, Long>();//
	/** 是否在pk中 */
	private volatile boolean isPk = false;//
	private long beginPkTime = 0l;
	private Map<Integer, CommonTime> commonCoolTime = new HashMap<Integer, CommonTime>();
	// 若PK双方今日PK胜负场次超过5次，则该双方今日互相PK不再增减战场声望

	private final CharacterDayPkRecord gongchengDayPkRecord = new CharacterDayPkRecord();
	private final CharacterDayPkRecord kuafuDayPkRecord = new CharacterDayPkRecord();

	@Override
	public void destroy() {
		super.destroy();
		if (commonCoolTime != null) {
			commonCoolTime.clear();
			commonCoolTime = null;
		}
		if (ziweiTargetSet != null) {
			ziweiTargetSet.clear();
			ziweiTargetSet = null;
		}

		if (commonskill != null) {
			commonskill.destroy();
			commonskill = null;
		}
		if (defaultSkill != null) {
			defaultSkill.destroy();
			defaultSkill = null;
		}
	}

	/**
	 * 对affecter胜利的次数
	 * 
	 * @param affecterId
	 * @return
	 */
	public int getPKWinRecord(int affecterId) {
		if (Options.IsCrossServ) {
			return kuafuDayPkRecord.getPKWinRecord(affecterId);
		}
		return gongchengDayPkRecord.getPKWinRecord(affecterId);
	}

	/**
	 * pk获胜
	 * 
	 * @param character
	 *            被攻击者
	 * @return 战场声望
	 */
	public int win(Hero affecter) {
		if (affecter == null) {
			return 0;
		}
		boolean gongcheng = character.getMyFactionCityZhengDuoManager().isFactionCityZhengduoState();
		boolean kuafu = Options.IsCrossServ;
		if (!gongcheng && !kuafu) {// 如果不在攻城并且不在跨服战
			return 0;
		}

		Date date = new Date();
		if (gongcheng) {
			if (!gongchengDayPkRecord.isToday(date)) {
				gongchengDayPkRecord.setDate(date);
				gongchengDayPkRecord.clearRecord();
			}

			if (!gongchengDayPkRecord.noNeedRecord(character, affecter)) {
				gongchengDayPkRecord.win(affecter.getId());
				return pkIncomePros(this.character, affecter);
			}
		} else if (kuafu) {
			if (!kuafuDayPkRecord.isToday(date)) {
				kuafuDayPkRecord.setDate(date);
				kuafuDayPkRecord.clearRecord();
			}

			if (!kuafuDayPkRecord.noNeedRecord(character, affecter)) {
				kuafuDayPkRecord.win(affecter.getId());
				return pkIncomePros(this.character, affecter);
			}
		}

		return 0;
	}

	/**
	 * 获得pk开始时间
	 * 
	 * @return
	 */
	public long getBeginPkTime() {
		return beginPkTime;
	}

	/**
	 * 设置pk开始时间
	 * 
	 * @param beginPkTime
	 */
	public void setBeginPkTime(long beginPkTime) {
		this.beginPkTime = beginPkTime;
	}

	/**
	 * 
	 * @param whoAttackMe
	 *            谁攻击我
	 */
	public void onBeAttack(VisibleObject whoAttackMe) {
		long now = System.currentTimeMillis();
		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero attacker = (Hero) whoAttackMe;
			CharacterFightController fightController = attacker.getFightController();
			fightController.setBeginPkTime(now);
			setBeginPkTime(now);
			if (!fightController.isZiweiTarget(character.getId()) && !isZiweiTarget(whoAttackMe.getId())) {

				ziweiTargetSet.put(whoAttackMe.getId(), System.currentTimeMillis());
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 502));
				// 在发起强制攻击的玩家头顶增加一个“攻击他人表情标记”（3秒后消失），在系统聊天频道向该玩家发送“您对XXX发起了强制攻击”的消息提示。
				whoAttackMe.sendMsg(new CharacterFaceMsg10088(1, character.getId()));
				// “被攻击表情标记”（3秒后消失），在系统聊天频道向该玩家发送“您受到了来自XXX玩家的PK”
				character.sendMsg(new CharacterFaceMsg10088(2, whoAttackMe.getId()));
			}

			if (isZiweiTarget(whoAttackMe.getId())) {
				ziweiTargetSet.put(whoAttackMe.getId(), System.currentTimeMillis());
			}

			fightController.updateAttackMondel(now);

			if (!this.isPk()) {
				setPk(true);
			}
			if (!fightController.isPk()) {
				fightController.setPk(true);
			}

			// 如果被攻击为非保护状态下，则攻击者添加保护非保护状态
			if (character.getMyCharacterVipManger().isFeibaohuState()) {
				attacker.getMyCharacterVipManger().addHuQiFeibaohuBuffer();
			}
		}
		updateAttackMondel(now);
	}

	/**
	 * 是否处于pk状态中
	 * 
	 * @return
	 */
	public boolean isPk() {
		return isPk;
	}

	/**
	 * 设置pk状态
	 * 
	 * @param isPk
	 */
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

	public int getMsg10906Cnt() {
		return msg10906Cnt;
	}

	public void setMsg10906Cnt(int msg10906Cnt) {
		this.msg10906Cnt = msg10906Cnt;
	}

	/**
	 * 更新战斗状态 包含开始时间与状态的改变
	 * 
	 * @param now
	 */
	public void updateAttackMondel(long now) {
		if (character.getObjectState() == VisibleObjectState.Die || character.getObjectState() == VisibleObjectState.Shock) {
			return;
		}
		attackModelBeginTime = now;
		if (!inFight(now)) {
			// setAttackModel(VisibleObjectState.Attack);
			this.vo.setObjectState(VisibleObjectState.Attack);
		}
	}

	public long getAttackModelBeginTime() {
		return attackModelBeginTime;
	}

	/**
	 * 是否处于战斗状态
	 * 
	 * @return
	 */
	public boolean inFight(long time) {
		boolean timeTag = time - attackModelBeginTime < ClientConfig.FightingTime;
		boolean statuesTag = VisibleObjectState.Attack == this.vo.getObjectState();
		return timeTag && statuesTag;
	}

	/**
	 * 
	 * @param characterId
	 *            是否是自卫目标
	 * @return
	 */
	public boolean isZiweiTarget(Integer characterId) {
		return ziweiTargetSet.get(characterId) == null ? false : true;
	}

	/**
	 * 移动自卫目标
	 * 
	 * @param characterId
	 */
	public void removeZiweiTarget(int characterId) {
		if (!ziweiTargetSet.isEmpty() && isZiweiTarget(characterId)) {
			ziweiTargetSet.remove(characterId);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 501));
		}
	}

	/**
	 * 清空自卫目标列表
	 */
	public void clearZiweiTarget() {
		if (!ziweiTargetSet.isEmpty()) {
			ziweiTargetSet.clear();
			// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,
			// "您已退出自卫模式"));
		}
	}

	/**
	 * 判断是否处于公共时间冷却中
	 * 
	 * @param characterSkill
	 * @return
	 */
	public boolean isCommonCool(CharacterSkill characterSkill) {
		Skill skill = characterSkill.getSkill();
		int commonCoolType = skill.getCommonCoolType();
		CommonTime commonTime = commonCoolTime.get(commonCoolType);
		if (commonTime == null) {
			commonTime = new CommonTime();
			commonCoolTime.put(commonCoolType, commonTime);
		}
		return !commonTime.isArrivedTime();
	}

	/**
	 * 触发公共技能冷却
	 * 
	 * @param characterSkill
	 */
	public void triggerCommonCoolTime(CharacterSkill characterSkill) {
		Skill skill = characterSkill.getSkill();
		int commonCoolType = skill.getCommonCoolType();
		CommonTime commonTime = commonCoolTime.get(commonCoolType);
		if (commonTime == null) {
			commonTime = new CommonTime();
			commonCoolTime.put(commonCoolType, commonTime);
		}
		commonTime.setCommonStartCD(System.currentTimeMillis());
		commonTime.setIntervalTime(characterSkill.getCommonCoolTime());
	}

	/**
	 * 战斗管理器加载,在人物技能与坐骑都初始化之后加载
	 */
	public void initData() {
		try {
			setPkModel(character.getPkModel());
			CharacterSkillManager characterSkillManager = character.getCharacterSkillManager();
			for (Iterator<CharacterSkill> iterator = characterSkillManager.getAllCharacterSkill().iterator(); iterator.hasNext();) {
				CharacterSkill characterSkill = iterator.next();
				Skill skill = characterSkill.getSkill();
				if (skill.isGeneral() && skill.getPopsinger() == character.getPopsinger()) {
					character.setSkillid(characterSkill.getSkillId());
					setCommonskill(characterSkill);
				}
			}
			// 初始化pk记录。不是当天的不记录
			List<DaypkRecord> dayPkRecordList = DaypkRecordManager.getInstance().getDaypkRecordList(character.getId());
			if (dayPkRecordList != null && !dayPkRecordList.isEmpty()) {
				for (DaypkRecord pkRecord : dayPkRecordList) {
					if (gongchengDayPkRecord.isToday(pkRecord.getDaytime())) {
						gongchengDayPkRecord.insterRecord(pkRecord.getFailer(), pkRecord.getCount());
					}
				}
			}
			// TODO 论剑pk记录初始化
			character.getCharacterHiddenWeaponController().initData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 当下线时处理
	 */
	public void whenDownLine() {
		if (!Options.IsCrossServ) {
			List<String> saveSQLs = new LinkedList<String>();
			saveSQLs.add(GenerateSQLUtil.CharacterPkRecordSQL(getVo().getId(), 0, 0, null, CRUD.delete));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<Integer, Integer> characterRecordMap = gongchengDayPkRecord.getCharacterRecordMap();
			for (Iterator<Integer> iterator = characterRecordMap.keySet().iterator(); iterator.hasNext();) {
				Integer failer = iterator.next();
				saveSQLs.add(GenerateSQLUtil.CharacterPkRecordSQL(getVo().getId(), failer, characterRecordMap.get(failer),
						format.format(gongchengDayPkRecord.getRecordCalendar().getTime()), CRUD.create));
			}

			CharacterManager.getInstance().excuteSql(saveSQLs);
		}
	}

	/**
	 * 获得默认技能
	 * 
	 * @return
	 */
	@Deprecated
	public CharacterSkill getDefaultSkill() {
		return defaultSkill;
	}

	/**
	 * 设置默认技能
	 * 
	 * @param defaultSkill
	 */
	@Deprecated
	public void setDefaultSkill(CharacterSkill defaultSkill) {
		this.defaultSkill = defaultSkill;
	}

	public CharacterSkill getCommonskill() {
		return commonskill;
	}

	public void setCommonskill(CharacterSkill commonskill) {
		this.commonskill = commonskill;
	}

	public long getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(long closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * 时间差(平砍攻击的时间差)
	 * 
	 * @return
	 */
	public long getTimelag() {
		return timelag;
	}

	/**
	 * 时间差(平砍攻击的时间差)
	 * 
	 * @param timelag
	 */

	public void setTimelag(long timelag) {
		this.timelag = timelag;
	}

	/**
	 * 获得pk模式 pk模式 0.和平 1.组队 2帮派3.全体
	 * 
	 * @return
	 */
	public Integer getPkModel() {
		return pkModel == null ? 0 : pkModel;
	}

	/**
	 * 设置pk模式
	 * 
	 * @param pkModel
	 */
	public void setPkModel(Integer pkModel) {
		character.setPkModel(pkModel);
		this.pkModel = pkModel;
	}

	/**
	 * 解除战斗状态
	 */
	public void releaseFightStatus() {
		// setAttackModel(VisibleObjectState.Idle);
		clearZiweiTarget();
		setPk(false);
		character.setTarget(null);
		character.getMyDazuoManager().updateIdelTime();
		if (vo.getObjectState() == VisibleObjectState.Shock || vo.getObjectState() == VisibleObjectState.Die) {
			return;
		}
		this.vo.setObjectState(VisibleObjectState.Idle);
	}

	/**
	 * 战斗在轮询中的处理
	 * 
	 * @param nowTime
	 * @throws Exception
	 */
	public void fightInWheel(long nowTime) throws Exception {
		if (fighterVO != null) {
			fight(fighterVO);
			fighterVO = null;
			return;
		}
		if (!couldAutoAttack()) {
			return;
		}
		checkZiweiTarget(nowTime);
		if (vo.getObjectState() == VisibleObjectState.Attack) {
			if (nowTime - attackModelBeginTime >= ClientConfig.FightingTime) {
				releaseFightStatus();
			}
		}
		int charStatus = character.getObjectState();
		if (charStatus == VisibleObjectState.Attack) {
			if (character.getTarget() == null || character.getTarget().getObjectState() == VisibleObjectState.Shock||character.getTarget().getObjectState() == VisibleObjectState.Die) {
				EnmityManager em = character.getEnmityManager();
				Enmity enmity = em.getHatesetTarget();
				if (enmity != null) {
					VisibleObject nextTarget = enmity.getTarget();
					character.sendMsg(new ChangeTargetMsg50594(nextTarget.getId()));
					character.setTarget(nextTarget);
					return;
				}
				character.setTarget(null);
				character.setObjectState(VisibleObjectState.Idle);
				this.autoAttack(false);
				return;
			}
			if (character.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero target = (Hero) character.getTarget();
				if (character.getVlineserver().getLineid() != target.getVlineserver().getLineid()) {
					character.setTarget(null);
					return;
				}
			}
			if (character.getTarget().isJiTui()) {
				return;
			}
			if (!character.isAblePk(character.getTarget())) {
				character.setObjectState(VisibleObjectState.Idle);
				return;
			}
			if (character.getEffectController().isImmb()) {
				character.setObjectState(VisibleObjectState.Idle);
				return;
			}
			if (character.getEffectController().isSleep()) {
				character.setObjectState(VisibleObjectState.Idle);
				return;
			}
			if (nowTime - getTimelag() < 2000) {// 第一次打的时候延时2秒
				return;
			}

			// if (getDefaultSkill() != null) {
			// if (!checkPkModel(character.getTarget(), getDefaultSkill())) {
			// character.setObjectState(VisibleObjectState.Idle);
			// return;
			// }
			// CharacterSkillManager csm= character.getCharacterSkillManager();
			// CharacterSkill defaultSkill = getDefaultSkill();
			// boolean can = csm.useSkill(defaultSkill);
			// if (!can) {
			// if (!checkPkModel(character.getTarget(), getCommonskill())) {
			// character.setObjectState(VisibleObjectState.Idle);
			// return;
			// }
			// character.getCharacterSkillManager().useSkill(getCommonskill());
			// }
			// } else {
			if (!checkPkModel(character.getTarget(), getCommonskill())) {
				character.setObjectState(VisibleObjectState.Idle);
				return;
			}
			character.getCharacterSkillManager().useSkill(getCommonskill());
			// }
		} else if (VisibleObjectState.Jitui == charStatus) {
			if (nowTime - character.getEffectController().getJiTuiBeginTime() > character.getEffectController().getJiTuiTime()) {
				character.setObjectState(VisibleObjectState.Idle);
			}
		}
	}

	/**
	 * 检查自卫队列
	 * 
	 * @param nowTime
	 */
	public void checkZiweiTarget(long nowTime) {
		if (nowTime - beginPkTime >= ClientConfig.FightingTime) {
			setPk(false);
		}
		if (ziweiTargetSet == null || ziweiTargetSet.isEmpty()) {
			return;
		}
//		Set<Integer> characterids = ziweiTargetSet.keySet();
//
//		for (Iterator<Integer> iterator = characterids.iterator(); iterator.hasNext();) {
//			Integer cid = iterator.next();
//			Long beginTime = ziweiTargetSet.get(cid);
//			if (nowTime - beginTime >= ClientConfig.FightingTime) {
//				ziweiTargetSet.remove(cid);
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 501));
//			}
//		}
		for (Iterator<Entry<Integer, Long>> iterator = ziweiTargetSet.entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, Long> entry=iterator.next();
			Integer cid = entry.getKey();
			Long beginTime = entry.getValue();
			
			if (nowTime - beginTime >= ClientConfig.FightingTime) {
				ziweiTargetSet.remove(cid);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 501));
			}
		}

		if (nowTime - beginPkTime >= ClientConfig.FightingTime) {
			setPk(false);
		}

	}

	@Override
	public void autofight() {
	}

	/**
	 * pk受益的处理 取绝对值： | PK胜利方玩家等级 – PK失败方玩家等级 | < =8 胜利方战场声望+2 ， 失败方战场声望-2 取绝对值：8 < | PK胜利方玩家等级 – PK失败方玩家等级 | <=15 胜利方战场声望+1 ， 失败方战场声望-1 取绝对值：15 < | PK胜利方玩家等级 – PK失败方玩家等级 |
	 * 胜利方不加战场声望，失败方不减战场声望
	 * 
	 * 2011-6-16 在跨服论剑台内战斗不加减战场声望，改为加减论剑声望，加减规则如下： 取绝对值： | PK胜利方玩家等级 – PK失败方玩家等级 | < =8 胜利方论剑声望+2 ， 失败方论剑声望-2 取绝对值：8 < | PK胜利方玩家等级 – PK失败方玩家等级 | <=15 胜利方论剑声望+1 ， 失败方论剑声望-1 取绝对值：15
	 * < | PK胜利方玩家等级 – PK失败方玩家等级 | 胜利方不加论剑声望，失败方不减论剑声望
	 * 
	 * 
	 * @param attacker
	 * @param affecter
	 * @return 声望值
	 */
	private int pkIncomePros(Hero attacker, Hero affecter) {
		// 若PK双方的IP相同，则不增减战场声望
		if (attacker.getPlayer().getAddress().equals(affecter.getPlayer().getAddress())) {
			return 0;
		}

		int difGrade = Math.abs(attacker.getGrade() - affecter.getGrade());
		int addV = 0;
		if (difGrade <= 8) {
			int shenwang = 2;
			if (character.getMyFactionCityZhengDuoManager().isFactionCityZhengduoState()) {
				int remianSw = MaxLimit.GainTodayMaxShengWang - attacker.getTodayChengzhanShengwang();
				if (remianSw > 0) {
					if (remianSw >= shenwang) {
						addV = CharacterPropertyManager.changeGongChengShengWang(attacker, shenwang, true);
					} else {
						addV = CharacterPropertyManager.changeGongChengShengWang(attacker, remianSw, true);
					}
					if (addV > 0) {
						attacker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20144, affecter.getViewName(), addV));
					}
				}

				addV = CharacterPropertyManager.changeGongChengShengWang(affecter, -shenwang, true);
				if (addV > 0) {
					affecter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20145, attacker.getViewName(), addV));
				}
			} else if (Options.IsCrossServ) {

				int remianSw = MaxLimit.GainTodayMaxShengWang - attacker.getMyCharacterAcrossIncomeManager().getShengWang();
				if (remianSw > 0) {
					if (remianSw >= shenwang) {
						addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(attacker, shenwang);
					} else {
						addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(attacker, remianSw);
					}
					if (addV != 0) {
						attacker.getMyCharacterAcrossIncomeManager().addWinCnt();
						attacker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20142, affecter.getViewName(), addV));
					}

				}

				addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(affecter, -shenwang);
				if (addV != 0) {
					affecter.getMyCharacterAcrossIncomeManager().addFailCnt();
					affecter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20143, attacker.getViewName(), addV));
				}
			}

			return shenwang;
		} else if (difGrade > 8 && difGrade <= 15) {
			int shenwang = 1;
			if (character.getMyFactionCityZhengDuoManager().isFactionCityZhengduoState()) {
				int remianSw = MaxLimit.GainTodayMaxShengWang - attacker.getTodayChengzhanShengwang();
				if (remianSw > 0) {
					if (remianSw >= shenwang) {
						addV = CharacterPropertyManager.changeGongChengShengWang(attacker, shenwang, true);
					} else {
						addV = CharacterPropertyManager.changeGongChengShengWang(attacker, remianSw, true);
					}
					if (addV > 0) {
						attacker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20144, affecter.getViewName(), addV));
					}
				}

				addV = CharacterPropertyManager.changeGongChengShengWang(affecter, -shenwang, true);
				if (addV > 0) {
					affecter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20145, attacker.getViewName(), addV));
				}

			} else if (Options.IsCrossServ) {
				int remianSw = MaxLimit.GainTodayMaxShengWang - attacker.getMyCharacterAcrossIncomeManager().getShengWang();
				if (remianSw > 0) {
					if (remianSw >= shenwang) {
						addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(attacker, shenwang);
					} else {
						addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(attacker, remianSw);
					}
					if (addV != 0) {
						attacker.getMyCharacterAcrossIncomeManager().addWinCnt();
						attacker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20142, affecter.getViewName(), addV));
					}
				}

				addV = CharacterPropertyManager.changeLunJianShengWang_InKuaFu(affecter, -shenwang);
				if (addV != 0) {
					affecter.getMyCharacterAcrossIncomeManager().addFailCnt();
					affecter.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20143, attacker.getViewName(), addV));
				}
			}

			return shenwang;
		} else {
			return 0;
		}
	}

	@Override
	protected boolean basefight(FighterVO fighterVo) {
		List<VisibleObject> visibleObjects = getEffectHandler().getFighterVo(fighterVo);
		if (visibleObjects == null) {
			return false;
		}
		int size = visibleObjects.size();
		if (size == 0) {
			if (fighterVo.getSkill().isPointEffect()) {
				// 功方技能ID(int),功方roleType（byte），攻方ID(int),X(short),Y(short),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*}
				FightMsgSender.sendEffectResponse_only2(fighterVo);
				fighterVo.getCharacterSkill().xiaohaoValue(character);
				triggerCommonCoolTime(fighterVo.getCharacterSkill());
			}
			if (fighterVo.getSkill().getEffect().getType() == EffectType.hongyan) {// 红颜
				FightMsgSender.sendEffectResponse_only(fighterVo);
				fighterVo.getCharacterSkill().xiaohaoValue(character);
				triggerCommonCoolTime(fighterVo.getCharacterSkill());
			}
			return false;
		}
		for (Iterator<VisibleObject> iterator = visibleObjects.iterator(); iterator.hasNext();) {
			VisibleObject visibleObject = (VisibleObject) iterator.next();
			getEffectHandler().judgeHurtWay(fighterVo, visibleObject);
		}
		fighterVo.setVisibleObjects(visibleObjects);
		return true;
	}

	/**
	 * 触发战斗
	 */
	@Override
	public boolean fight(final FighterVO fighterVo) {
		if (!super.fight(fighterVo)) {
			return false;
		}
		if (fighterVo.getSponsor().getSceneObjType() == SceneObj.SceneObjType_Hero) {
			character.getCatchYoulongActionController().breakCatch();// 拔剑进度条被打断接口
			character.getMyCharacterVehicleManager().breakSenderShells();// 打断发炮
			character.getCatchXuanyuanJianActionController().breakCatch();
			triggerCommonCoolTime(fighterVo.getCharacterSkill());
		} else {
			fighterVo.getCharacterSkill().triCooltime();
		}
		fighterVo.getCharacterSkill().xiaohaoValue(character);
		return true;

	}

	private boolean checkPkModel(VisibleObject vo, CharacterSkill characterSkill) {
		if (vo.getSceneObjType() != SceneObj.SceneObjType_Hero) {
			return true;
		}

		if (character.getId() == vo.getId()) {
			return false;
		}

		SkillEffect effect = characterSkill.getSkill().getEffect();
		if (effect.getTypePn() == 1) { // 不是伤害类型
			return true;
		}
		switch (getPkModel()) {
		case 0:
			return false;
		case 1:
			if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
				return false;
			}
			break;
		case 2:
			if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
				return false;
			}
			break;
		}
		return true;
	}

	public void addSkillAttack(FighterVO fighterVO) {
		this.fighterVO = fighterVO;
	}
}
