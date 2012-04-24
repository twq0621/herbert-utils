package net.snake.gamemodel.skill.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.AutoFightTargetMsg10906;
import net.snake.ai.fight.response.DelCharacterSkillMsg10290;
import net.snake.ai.fight.response.SkillAddiGradeMsg10296;
import net.snake.ai.fight.upgrade.response.LearnSkillMsg10274;
import net.snake.ai.fight.upgrade.response.SkillPoMsg10288;
import net.snake.ai.fight.upgrade.response.WuGongSkillGradeMsg10294;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.GenerateProbability;
import net.snake.commons.Language;
import net.snake.consts.CopperAction;
import net.snake.consts.Position;
import net.snake.consts.SkillStatus;
import net.snake.consts.WugongType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.response.hero.WuxueJingJieChangeMsg10504;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.CharacterSkillData;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.bow.logic.BowController;
import net.snake.gamemodel.skill.persistence.CharacterSkillDataProvider2;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import org.apache.log4j.Logger;

/**
 * 人物技能管理器
 * 
 * @author serv_dev
 * 
 */
public class CharacterSkillManager extends BaseSkillManager<Hero> {

	private final Map<Integer, CharacterSkill> characterSkillMap = new HashMap<Integer, CharacterSkill>();// 人物技能表的map，key为技能id
	private final Map<Integer, CharacterSkill> tmpFuqiSkillMap = new HashMap<Integer, CharacterSkill>();// 夫妻\玉佩技能的临时存放的地方
	private static final Logger logger = Logger.getLogger(CharacterSkillManager.class);
	public CharacterSkillManager(Hero vo) {
		super(vo);
	}

	public void destroy() {
		characterSkillMap.clear();
		tmpFuqiSkillMap.clear();
	}

	@Override
	public void save() {
		long now = System.currentTimeMillis();
		for (Iterator<CharacterSkill> iterator = getAllCharacterSkill().iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = iterator.next();
			int time = characterSkill.getCoolingtime() - (int) (now - characterSkill.getStartcd());
			if (time > 0) {// 如果还有剩余时间，则记录到数据库
				updateCharacterSkill(characterSkill);
			}
		}
		;
	}

	public int getskilladdGrade(int skillId) {
		CharacterSkill characterSkill = getCharacterSkillById(skillId);
		if (characterSkill == null) {
			return 0;
		}

		return characterSkill.getRealGrade() - characterSkill.getGrade();
	}

	/**
	 * 心法熟练度提升每次提升1点
	 * 
	 * @param skillId
	 */
	public void xinfaSkillUpMastery(CharacterSkill characterSkill) {
		if (characterSkill != null) {
			// if (!characterSkill.getSkill().isZhudong()) {
			characterSkill.addMastery(getVoObject());
			// }
		}
	}

	@Override
	public void initPassiveSkillsAddition() {
		super.initPassiveSkillsAddition();
		voObject.initwuxueJingjie();
	}

	public void sendUpdateSkillShowMsg() {
		Collection<CharacterSkill> col = getAllCharacterSkill();
		int size = col.size() - 1;
		if (size > 0) {
			int[] list = new int[size * 2];
			int i = 0;
			for (Iterator<CharacterSkill> iterator = col.iterator(); iterator.hasNext();) {
				CharacterSkill skill = iterator.next();
				if (skill.isPinKan()) {
					continue;
				}
				if (skill.getSkill().getShowOrder() == 0) {
					continue;
				}
				int addgrade = getskilladdGrade(skill.getSkillId());
				list[i] = skill.getSkillId();
				list[i + 1] = addgrade;
				i = i + 2;
			}
			initPassiveSkillsAddition();
			getVoObject().sendMsg(new SkillAddiGradeMsg10296(list));
		}
	}

	/**
	 * 
	 * 由服务器驱动人物使用技能的时候调用
	 * 
	 */
	@Override
	public boolean useSkill(CharacterSkill characterSkill) {

		Hero character = getVoObject();

		if (characterSkill == null) {
			character.setObjectState(VisibleObjectState.Idle);
			return false;
		}

		if (characterSkill.ableUse() != SkillStatus.ok) {
			return false;
		}

		if (character.getTarget().getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			SceneMonster monster = (SceneMonster) character.getTarget();
			if (monster.getObjectState() == VisibleObjectState.Dispose || monster.getObjectState() == VisibleObjectState.Die
					|| monster.getObjectState() == VisibleObjectState.Reset || monster.getObjectState() == VisibleObjectState.IsReset
					|| monster.getObjectState() == VisibleObjectState.Jitui) {
				FightMsgSender.sendSkillReleaseFailMsg(character, SkillStatus.invalid);
				return false;
			}
		}

		if (!AttackFormula.atkIsEnable2(character.getX(), character.getY(), character.getTarget().getX(), character.getTarget().getY(), characterSkill.getSkill().getDistance())) {
			if (character.getFightController().getMsg10906Cnt() > 1) {
				character.getFightController().setMsg10906Cnt(0);
				character.setObjectState(VisibleObjectState.Idle);
				return false;
			}

			character.getFightController().setMsg10906Cnt(character.getFightController().getMsg10906Cnt() + 1);
			character.sendMsg(new AutoFightTargetMsg10906(character.getTarget().getSceneObjType() == SceneObj.SceneObjType_Hero ? 1 : 2, character.getTarget().getId()));
			return false;
		}
		
		FighterVO fighterVO = new FighterVO(characterSkill.getSponsor(), character, character.getTarget(), characterSkill);
		return character.getFightController().fight(fighterVO);
	}

	@Override
	public CharacterSkill getPingkanSkill() {
		return getVoObject().getFightController().getCommonskill();
	}

	/**
	 * 从缓存中删除技能
	 * 
	 * @param characterSkill
	 */
	public void delCharacterSkillFromCache(CharacterSkill characterSkill) {
		CharacterSkill removeSkill = characterSkillMap.remove(characterSkill.getSkillId().intValue());
		tmpFuqiSkillMap.remove(characterSkill.getSkillId().intValue());
		if (removeSkill != null) {
			removeSkill.setQuickbarindex(0);
			updateCharacterSkill(removeSkill);
			getVoObject().sendMsg(new DelCharacterSkillMsg10290(characterSkill.getSkillId().intValue()));
		}
	}

	/**
	 * 进入场景时调用此方法(避免婚姻同步产生的问题) 结婚与离婚的时候调用此方法 等级变更的时候调用此方法 更换玉佩的时候调用此方法
	 */
	public void fuqiSkillReload() {

		// 如果是夫妻 有玉佩
		// 查看是否已经有，有的话不添加
		RoleWedingManager rwMgr = getVoObject().getMyFriendManager().getRoleWedingManager();
		if (rwMgr.isWedding()) {
			Collection<Skill> skillCol = SkillManager.getInstance().getAllFuQiSkills();
			for (Iterator<Skill> iterator = skillCol.iterator(); iterator.hasNext();) {
				Skill skill = iterator.next();
				if (characterSkillMap.keySet().contains(skill.getId())) {
					continue;
				}

				if (tmpFuqiSkillMap.keySet().contains(skill.getId())) {// 第一次初始化角色的时候可能会走这里
					CharacterSkill _characterSkill = tmpFuqiSkillMap.get(skill.getId());
					characterSkillMap.put(skill.getId(), _characterSkill);
					getVoObject().sendMsg(new LearnSkillMsg10274(_characterSkill, getVoObject()));
					continue;
				}

				if (skill.getCharLevel() > getVoObject().getGrade()) {
					continue;
				}

				addSkill(skill.getId(), 1);
			}
			WeddingRing weddingRing = rwMgr.getFuqiWeddingRing();
			if (weddingRing == null) {
				logger.warn("no yupei after wedding");
			} else {
				List<CharacterSkill> removeList = new ArrayList<CharacterSkill>();
				// 玉佩的处理 (更换玉佩时，删除老的玉佩技能 通知客户端 添加新的玉佩技能 通知客户端)
				for (Iterator<CharacterSkill> iterator = characterSkillMap.values().iterator(); iterator.hasNext();) {
					CharacterSkill skill = iterator.next();
					int skilId = skill.getSkillId();
					if (skill.getSkill().getWugongTypeConsts() == WugongType.YU_PEI) {
						// 如果不是当前玉佩上的技能添加到删除列表里
						if (weddingRing.getSkillA() != skilId && weddingRing.getSkillB() != skilId) {
							removeList.add(skill);
						}
					}
				}

				if (!removeList.isEmpty()) {
					for (Iterator<CharacterSkill> iterator = removeList.iterator(); iterator.hasNext();) {
						CharacterSkill characterSkill = iterator.next();
						delCharacterSkillFromCache(characterSkill);
					}
					removeList.clear();
					removeList = null;
				}

				// 添加玉佩上的技能
				Integer skillA = weddingRing.getSkillA();
				if (skillA != null && skillA > 0) {
					if (!characterSkillMap.keySet().contains(skillA.intValue())) {
						if (!tmpFuqiSkillMap.keySet().contains(skillA.intValue())) {
							addSkill(skillA.intValue(), 1);
						} else {
							CharacterSkill _characterSkill = tmpFuqiSkillMap.get(skillA.intValue());
							characterSkillMap.put(skillA.intValue(), _characterSkill);
							getVoObject().sendMsg(new LearnSkillMsg10274(_characterSkill, getVoObject()));
						}
					}
				}

				Integer skillB = weddingRing.getSkillB();
				if (skillB != null && skillB > 0) {
					if (!characterSkillMap.keySet().contains(skillB.intValue())) {
						if (!tmpFuqiSkillMap.keySet().contains(skillB.intValue())) {
							addSkill(skillB.intValue(), 1);
						} else {
							CharacterSkill _characterSkill = tmpFuqiSkillMap.get(skillB.intValue());
							characterSkillMap.put(skillB.intValue(), _characterSkill);
							getVoObject().sendMsg(new LearnSkillMsg10274(_characterSkill, getVoObject()));
						}
					}
				}
			}
		} else {

			// 如果不是夫妻(离婚) (未婚也会进行到这里，不过没有技能可移除)
			// 从缓存中删除所有的skill 并通知客户端

			List<CharacterSkill> removeList = new ArrayList<CharacterSkill>();
			for (Iterator<CharacterSkill> iterator = characterSkillMap.values().iterator(); iterator.hasNext();) {
				CharacterSkill skill = iterator.next();
				if (skill.getSkill().getWugongTypeConsts() == WugongType.YU_PEI || skill.getSkill().getWugongTypeConsts() == WugongType.FU_QI) {
					removeList.add(skill);
				}
			}
			if (!removeList.isEmpty()) {
				for (Iterator<CharacterSkill> iterator = removeList.iterator(); iterator.hasNext();) {
					CharacterSkill characterSkill = iterator.next();
					delCharacterSkillFromCache(characterSkill);
				}
				removeList.clear();
				removeList = null;
			}
		}

	}

	public void initData() {
		try {
			Hero character = getVoObject();
			List<CharacterSkillData> characterskilldatalist = CharacterSkillDataProvider2.getInstance().getCharacterSkillDataList(character.getId());

			for (CharacterSkillData data : characterskilldatalist) {
				CharacterSkill characterSkill = new CharacterSkill(data, character, character);
				WugongType wgt = characterSkill.getSkill().getWugongTypeConsts();
				if (wgt == WugongType.FU_QI || wgt == WugongType.YU_PEI) {
					tmpFuqiSkillMap.put(characterSkill.getSkillId(), characterSkill);
				} else {
					putIntoCache(character, characterSkill);
				}
				// character.setWuxueJingjie(character.getWuxueJingjie()
				// + characterSkill.getGrade());
			}
			initPassiveSkillsAddition();
			if (characterSkillMap.size() == 0) {// 刚创建角色,为其初始化技能
				int quickBarIndex = Position.QuickBarBeginPostion + 1;
				Collection<Skill> skills = SkillManager.getInstance().getCreateLearnSkills(character.getPopsinger());
				for (Iterator<Skill> iterator = skills.iterator(); iterator.hasNext();) {
					Skill skill = iterator.next();
					CharacterSkill characterSkill = this.addSkill(skill.getId(), 1);
					if (!skill.isGeneral()) {
						if (skill.isZhudong() && !(skill.getWugongTypeConsts() == WugongType.OTHER) && skill.getWugongTypeConsts() != WugongType.NULL) {
							characterSkill.setQuickbarindex(quickBarIndex);
							quickBarIndex++;// 被动技能跳过
						}
					} else {
						if (skill.getWugongTypeConsts() != WugongType.NULL) {
							characterSkill.setQuickbarindex(Position.QuickBarBeginPostion + 0);
						}
					}

					if (characterSkill.getQuickbarindex() != 0) {
						character.getQuickbarController().initQuickBarGoods(characterSkill);
					}
					updateCharacterSkill(characterSkill);
				}
				initPassiveSkillsAddition();
				character.setNowHp(character.getPropertyAdditionController().getExtraMaxHp());
				character.setNowMp(character.getPropertyAdditionController().getExtraMaxMp());
				character.setNowSp(character.getPropertyAdditionController().getExtraMaxSp());
			}
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().wugongCount(character.getWuxueJingjie());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 放入到内存中
	 * 
	 * @param character
	 * @param characterSkill
	 */
	private void putIntoCache(Hero character, CharacterSkill characterSkill) {
		characterSkillMap.put(characterSkill.getSkillId(), characterSkill);
		if (characterSkill.getQuickbarindex() != 0) {
			character.getQuickbarController().initQuickBarGoods(characterSkill);
		}

		if (characterSkill.isPinKan()) {
			character.getFightController().setCommonskill(characterSkill);
		}
	}

	/**
	 * 插入 人物技能关系
	 * 
	 * @param characterSkill
	 */
	public void insertCharacterSkill(CharacterSkill characterSkill) {
		CharacterSkillDataProvider2.getInstance().asynInsertCharacterSkill(getVoObject(), characterSkill);
	}

	/**
	 * 删除 数据库缓存中人物技能
	 * 
	 * @param characterSkill
	 */
	public void delCharacterSkill(int skillId) {
		CharacterSkill characterSkill = getCharacterSkillById(skillId);
		if (characterSkill == null) {
			return;
		}
		delCharacterSkillFromCache(characterSkill);
		CharacterSkillDataProvider2.getInstance().asynDelCharacterSkill(getVoObject(), characterSkill.getId());
	}

	/**
	 * 更新 人物技能关系
	 * 
	 * @param characterSkill
	 */
	public void updateCharacterSkill(CharacterSkill characterSkill) {
		CharacterSkillDataProvider2.getInstance().asynUpdataCharacterSkill(getVoObject(), characterSkill);
	}

	public CharacterSkill create(Skill skill) {
		Hero character = getVoObject();
		CharacterSkill characterSkill = new CharacterSkill(character, character);
		characterSkill.setCharacterId(character.getId());
		characterSkill.setSkillId(skill.getId());
		characterSkill.setPosition((short) skill.getShowOrder());
		characterSkill.setGrade(1);

		characterSkill.setQuickbarindex(0);
		characterSkill.setMastery(0);
		characterSkill.setPo(false);
		characterSkill.setMaxMastery(0);
		characterSkill.setSkilltype(2);
		putIntoCache(character, characterSkill);
		insertCharacterSkill(characterSkill);
		character.sendMsg(new LearnSkillMsg10274(characterSkill, character));
		_passiveSkillUpgrade(characterSkill.getGrade(), characterSkill);
		checksendwuxuejingjieiconchangemsg(character);

		return characterSkill;
	}

	public Map<Integer, CharacterSkill> getCharacterSkillMap() {
		return characterSkillMap;
	}

	@Override
	public CharacterSkill addSkill(int skillId, int grade) {
		Skill skill = SkillManager.getInstance().getSkillById(skillId);

		if (skill == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("addSkill(int) - 没有改技能id:"+skillId); //$NON-NLS-1$
				
			}
			return null;
		}
		CharacterSkill characterSkill = create(skill);
		if (characterSkill != null) {
			WugongType wType = characterSkill.getSkill().getWugongTypeConsts();
			if (wType == WugongType.FU_QI || wType == WugongType.YU_PEI) {
				tmpFuqiSkillMap.put(characterSkill.getSkillId(), characterSkill);
			}
		}
		return characterSkill;
	}

	@Override
	public CharacterSkill getCharacterSkillById(int skillId) {
		return characterSkillMap.get(skillId);
	}

	/**
	 * 是不是可以学习的技能 返回null 可以学习
	 * 
	 * @param skill
	 * @return
	 */
	public int isLearnSkill(int skillId) {
		Skill skill = SkillManager.getInstance().getSkillById(skillId);
		if (skill == null) {
			return 584;
		}
		return isLearnSkill(skill);
	}

	/**
	 * 是不是可以学习的技能 返回0 可以学习
	 * 
	 * @param skill
	 * @return
	 */
	public int isLearnSkill(Skill skill) {
		Hero character = getVoObject();
		if (skill.getWugongTypeConsts() == WugongType.GONG) {
			// 很抱歉，您还没有弓箭，无法使用箭术秘籍20137
			// 您当前的弓箭上不包含该箭术秘籍的箭术 20138
			// 该箭术已经激活，无需重复激活20139
			BowController bowController = getVoObject().getBowController();
			if (bowController.getBow() == null) {
				// getVoObject().sendMsg(
				// new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20137));
				return 20137;
			}

			if (bowController.hasActivate(skill.getId())) {
				// getVoObject().sendMsg(
				// new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20139));
				return 20139;
			}

			if (!bowController.isInUnActivate(skill.getId())) {
				// getVoObject().sendMsg(
				// new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20138));
				return 20138;
			}
			CharacterSkill characterSkill = getCharacterSkillById(skill.getId());
			if (characterSkill != null) {
				delCharacterSkill(skill.getId());
			}
		}
		if (skill.getPopsinger() != 0 && skill.getPopsinger() != character.getPopsinger()) {
			return 582;
		}
		CharacterSkill characterSkill = getCharacterSkillById(skill.getId());
		if (characterSkill != null) {
			return 581;
		}

		if (skill.getCharLevel() > character.getGrade()) {
			return 583;
		}

		if (skill.getDantian_grade() > 0) {
			int dantianGrade = character.getDanTianController().getDanTian() == null ? 0 : character.getDanTianController().getDanTian().getDantianid();
			if (dantianGrade < skill.getDantian_grade()) {
				return 20153;// 很抱歉，丹田等级过低，无法学习该武学，请先提升丹田等级
			}
		}

		return 0;
	}

	/**
	 * 学习技能 (任务脚本，技能书学习，帮派学习)
	 * 
	 * @param skillId
	 * @return
	 */
	public boolean learnSkill(Skill skill) {
		if (skill == null) {
			return false;
		}
		if (skill.getSkilltype() == 1) {
			return false;
		}
		int msg = isLearnSkill(skill);
		if (msg != 0) {
			getVoObject().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, msg));
			return false;
		}

		if (skill.getWugongTypeConsts() == WugongType.BANG_PAI) {
			if (skill.getBangGong() > 0) {
				if (getVoObject().getMyFactionManager().isEnoughFactionContribution(skill.getBangGong())) {
					getVoObject().getMyFactionManager().changeFactionContribution(skill.getBangGong());
				} else {
					getVoObject().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20082));
					return false;
				}
			}
		}

		this.create(skill);// 创建技能
		int addgrade = getVoObject().getCharacterSkillManager().getskilladdGrade(skill.getId());
		getVoObject().sendMsg(new SkillAddiGradeMsg10296(skill.getId(), addgrade));
		if (skill.getWugongTypeConsts() == WugongType.GONG) {
			BowController bowController = getVoObject().getBowController();
			bowController.addSkill(skill.getId());
			// 恭喜您，箭术激活成功，放入对应的箭支即可触发强大箭技20140
			getVoObject().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20140));
		}
		return true;
	}

	public boolean characterSkillPo(int skillId) {
		CharacterSkill characterSkill = getCharacterSkillById(skillId);

		if (characterSkill == null)
			return false;

		Hero character = getVoObject();

		SkillUpgradeExp exp = characterSkill.upgradeNeedZhenqi();// 当前的
		if (exp != null) {
			Integer expZhengqi = exp.getPoNeedZhenqi() == null ? 0 : exp.getPoNeedZhenqi();
			Integer expCash = exp.getPoNeedCopper() == null ? 0 : exp.getPoNeedCopper();
			String targetGoodsStr = exp.getTargetGoods();

			if (characterSkill.getPo()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 585));
				return false;
			}

			boolean pinjin = exp.getPinjin() == 1 ? true : false;
			if (!pinjin) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 586));
				return false;
			}

			Date poDate = new Date(character.getSkillpoBeginTime().getTime() + character.getSkillpoTime() * 3600 * 1000);
			if (!new Date().after(poDate)) {
				int hour = (int) ((poDate.getTime() - new Date().getTime()) / 1000 / 3600);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 587, (hour == 0 ? 1 : hour) + ""));
				return false;
			}

			if (character.getZhenqi() < expZhengqi) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 588));
				return false;
			}
			if (character.getCopper() < expCash) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 589));
				return false;
			}

			String goodMsg = "";
			Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
			if (targetGoodsStr != null && !"".equals(targetGoodsStr)) {
				String[] targetGoods = targetGoodsStr.split(";");
				for (int i = 0; i < targetGoods.length; i++) {
					String _targetGoods = targetGoods[i];
					if (_targetGoods != null && !"".equals(_targetGoods)) {
						String[] goods = _targetGoods.split(",");
						if (goods.length == 2) {
							int goodsId = Integer.parseInt(goods[0]);
							int goodsNum = Integer.parseInt(goods[1]);
							if (!character.getCharacterGoodController().isEnoughGoods(goodsId, goodsNum)) {
								//
								character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 590, GoodmodelManager.getInstance().get(goodsId).getNameI18n()));
								goodMsg = Language.GOODS + ":" + GoodmodelManager.getInstance().get(goodsId).getNameI18n() + ".";
								return false;
							}
							tmpMap.put(goodsId, goodsNum);
						}
					}
				}
			}

			for (Iterator<Integer> iterator = tmpMap.keySet().iterator(); iterator.hasNext();) {
				Integer goodid = iterator.next();
				Integer goodNum = tmpMap.get(goodid);
				character.getCharacterGoodController().deleteGoodsFromBag(goodid, goodNum);
			}
			CharacterPropertyManager.changeZhenqi(character, -expZhengqi);
			CharacterPropertyManager.changeCopper(character, -expCash, CopperAction.CONSUME);

			if (GenerateProbability.isGenerate2(GenerateProbability.gailv, exp.getPoNeedLv() == null ? 0 : exp.getPoNeedLv())) {
				characterSkill.setPo(true);
				updateCharacterSkill(characterSkill);
				character.setSkillpoBeginTime(new Date());
				character.setSkillpoTime(exp.getPoCooltime() == null ? 0 : exp.getPoCooltime());
				// character.sendMsg(new SkillUpgradeMsg10272(characterSkill,
				// character));
				character.sendMsg(new SkillPoMsg10288(characterSkill.getSkillId()));
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 591, expCash + "", expZhengqi + "," + goodMsg, characterSkill.getSkill().getNameI18n()));
			} else {
				// "
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 592, expCash + "", expZhengqi + "," + goodMsg, characterSkill.getSkill().getNameI18n()));
			}
			return true;
		}
		return false;

	}

	/**
	 * 技能升级
	 * 
	 * @param skillId
	 *            技能id
	 * @param grade
	 *            升级的等级
	 * @return
	 */
	public boolean characterSkillUpgrade(int skillId, int grade) {
		CharacterSkill characterSkill = this.getCharacterSkillById(skillId);
		boolean bf = super.skillUpgrade(characterSkill, grade);
		if (!bf) {
			return bf;
		}
		Hero character = this.getVoObject();
		int oldGrade = characterSkill.getGrade();
		_passiveSkillUpgrade((grade - oldGrade), characterSkill);
		checksendwuxuejingjieiconchangemsg(character);
		int addgrade = character.getCharacterSkillManager().getskilladdGrade(characterSkill.getSkillId());
		character.sendMsg(new SkillAddiGradeMsg10296(characterSkill.getSkillId(), addgrade));
		return true;
	}

	@Override
	public Collection<CharacterSkill> getAllCharacterSkill() {
		return getCharacterSkillMap().values();
	}

	@Override
	protected void _passiveSkillUpgrade(int grade, CharacterSkill characterSkill) {
		super._passiveSkillUpgrade(grade, characterSkill);
		getVoObject().initwuxueJingjie();
		// character.setWuxueJingjie(character.getWuxueJingjie() + grade);
	}

	@Override
	protected void _passiveSkillUpgrade(CharacterSkill characterSkill) {
		super._passiveSkillUpgrade(characterSkill);
	}

	private void checksendwuxuejingjieiconchangemsg(Hero character) {
		int wuxuejingjie = character.getWuxueJingjie();
		if (wuxuejingjie == 100 || wuxuejingjie == 250 || wuxuejingjie == 450 || wuxuejingjie == 700 || wuxuejingjie == 1000 || wuxuejingjie == 1400 || wuxuejingjie == 1900) {
			character.getEyeShotManager().sendMsg(new WuxueJingJieChangeMsg10504(character));
		}

		character.sendMsg(new WuGongSkillGradeMsg10294(wuxuejingjie));
		character.getMyCharacterAchieveCountManger().getCharacterOtherCount().wugongCount(wuxuejingjie);
	}

}
