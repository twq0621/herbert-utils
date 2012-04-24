package net.snake.ai.fight.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.ArrowWay;
import net.snake.consts.EffectType;
import net.snake.consts.SkillTarget;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;

/**
 * 效果处理
 * 
 * @author serv_dev
 * 
 */
public class CharacterEffectHandler extends BaseEffectHandler {

	public CharacterEffectHandler(VisibleObject vo) {
		super(vo);
		character = (Hero) vo;
	}

	private static final Logger LOGGER = Logger.getLogger(CharacterEffectHandler.class);

	private Hero character;

	/**
	 * 需找一个扇形角度\直线内所命中的对象
	 * 
	 * @param centerObject
	 *            自己
	 * @param target
	 *            目标
	 * @param skillEffect
	 *            效果
	 * @param voInRegion
	 *            所得命中的对象
	 */
	public void findObjectInAngle(VisibleObject centerObject, VisibleObject target, Skill skill, List<VisibleObject> voInRegion) {
		int i = 0;
		SkillEffect skillEffect = skill.getEffect();
		CharacterFightController characterFightController = character.getFightController();
		Collection<Hero> cs = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		for (Hero vo : cs) {
			if (vo.getSceneRef().getPvpModel() == 0 && skillEffect.getTypePn() == 0) {
				break;
			}
			if (vo.isZeroHp()) {
				continue;
			}
			if (skillEffect.getTypePn() == 0 && character.getId() == vo.getId()) {
				continue;
			}
			if (skillEffect.getTypePn() == 0 && !character.isAblePk(vo)) {
				continue;
			}
			switch (characterFightController.getPkModel()) {
			case 0:
				if (skillEffect.getTypePn() == 0) { // 伤害类型
					if (characterFightController.isZiweiTarget(vo.getId())) {
						break;
					}
					continue;
				} else {
					break;
				}
			case 1:
				if (skillEffect.getTypePn() == 0) { // 伤害类型
					if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
						continue;
					}
					break;
				} else {
					if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
						break;
					}
					continue;
				}
			case 2:
				if (skillEffect.getTypePn() == 0) { // 伤害类型
					if (character.getMyFactionManager().isInSameFactionWith(vo)) {
						continue;
					}
					break;
				} else {
					if (character.getMyFactionManager().isInSameFactionWith(vo)) {
						break;
					}
					continue;
				}
			case 3:
				if (skillEffect.getTypePn() == 0) { // 伤害类型
					break;
				} else {
					if (character.getId() == vo.getId()) {
						break;
					}
					continue;
				}
			default:
				break;
			}
			if (!AttackFormula.atkIsEnable2(centerObject.getX(), centerObject.getY(), vo.getX(), vo.getY(), skillEffect.getScopeRadius())) {// 在效果的范围内
				continue;
			}
			if (skill.getArrowWay() == ArrowWay.face) {
				if (!AttackFormula.inAngle(centerObject.getX(), centerObject.getY(), target.getX(), target.getY(), vo.getX(), vo.getY(), skill.getAngle(),
						skillEffect.getScopeRadius())) {
					continue;
				}
			} else if (skill.getArrowWay() == ArrowWay.line) {
				if (!AttackFormula.isInLine(centerObject.getX(), centerObject.getY(), target.getX(), target.getY(), vo.getX(), vo.getY())) {
					continue;
				}
			} else if (skill.getArrowWay() == ArrowWay.point_mul) {
				if (target.getId() == vo.getId()) {
				} else {
					if (centerObject.getId() == vo.getId()) {
						continue;
					}
					if (!AttackFormula.atkIsEnable2(target.getX(), target.getY(), vo.getX(), vo.getY(), skill.getArrow_point_radius())) {
						continue;
					}
				}
			} else {
				continue;
			}
			if (i >= skillEffect.getUseLimit()) {
				break;
			}
			voInRegion.add(vo);
			i++;
		}
		Collection<SceneMonster> ms = centerObject.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		for (SceneMonster vo : ms) {
			if (vo.isHorse()) {
				continue;
			}
			if (vo instanceof SceneBangqiMonster || vo instanceof SceneFactionCtMonster) {
				continue;
			}
			if (vo.isDie()) {
				continue;
			}
			// 在效果的范围内
			if (!AttackFormula.atkIsEnable2(centerObject.getX(), centerObject.getY(), vo.getX(), vo.getY(), skillEffect.getScopeRadius())) {
				continue;
			}
			if (skill.getArrowWay() == ArrowWay.face) {
				if (!AttackFormula.inAngle(centerObject.getX(), centerObject.getY(), target.getX(), target.getY(), vo.getX(), vo.getY(), skill.getAngle(),
						skillEffect.getScopeRadius())) {
					continue;
				}
			} else if (skill.getArrowWay() == ArrowWay.line) {
				if (!AttackFormula.isInLine(centerObject.getX(), centerObject.getY(), target.getX(), target.getY(), vo.getX(), vo.getY())) {
					continue;
				}
			} else if (skill.getArrowWay() == ArrowWay.point_mul) {
				if (target.getId() != vo.getId()) {
					if (!AttackFormula.atkIsEnable2(target.getX(), target.getY(), vo.getX(), vo.getY(), skill.getArrow_point_radius())) {
						continue;
					}
				}
			} else {
				continue;
			}
			if (i >= skillEffect.getUseLimit()) {
				break;
			}
			voInRegion.add(vo);
			i++;
		}

	}

	@Override
	protected void findSceneMonsterInScene(VisibleObject centerObject, SkillEffect skillEffect, List<VisibleObject> voInRegion) {
		int i = 0;
		Collection<SceneMonster> ms = centerObject.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		for (SceneMonster vo : ms) {
			if (vo.isHorse())
				continue;
			if (vo instanceof SceneBangqiMonster || vo instanceof SceneFactionCtMonster) {
				continue;
			}
			if (vo.isDie()) {
				continue;
			}

			if (AttackFormula.atkIsEnable2(centerObject.getX(), centerObject.getY(), vo.getX(), vo.getY(), skillEffect.getScopeRadius())) {// 在效果的范围内
				if (i >= skillEffect.getUseLimit()) {
					break;
				}
				voInRegion.add(vo);
				i++;
			}
		}
	}

	protected void hongyan(VisibleObject vo, SkillEffect skillEffect) {
		if (skillEffect.getType() == EffectType.hongyan) {// 红颜
			RoleWedingManager roleWedingManager = character.getMyFriendManager().getRoleWedingManager();
			if (!roleWedingManager.isWedding()) {
				return;
			}
			Scene scene = character.getSceneRef();
			Hero wedder = scene.getSceneObj(SceneObj.SceneObjType_Hero, roleWedingManager.getWedderId());
			if (wedder == null) {
				return;
			}
			if (!wedder.inFighting(System.currentTimeMillis()) || (vo.getEnmityManager().getEnmityOfVo(wedder) == null && wedder.getEnmityManager().getEnmityOfVo(vo) == null)) {
				return;
			}
		}
	}

	/**
	 * 0 和平模式：无法对其他任何玩家发起强制攻击，范围性魔法不会对其他任何玩家造成伤害。 1 组队模式：无法对同队伍成员发起强制攻击，范围性魔法不会对同队伍成员造成伤害。 2 帮会模式：无法对同帮会成员发起强制攻击，范围性魔法不会对同帮会成员造成伤害。 3 全体模式：可以对任何其他玩家发起强制攻击，范围性魔法会对任何其他玩家造成伤害。
	 */
	@Override
	protected void findCharacterInScene(short centerX, short centerY, SkillEffect effect, List<VisibleObject> voInRegion, int r) {
		CharacterFightController characterFightController = character.getFightController();
		int i = 0;
		Collection<Hero> cs = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		for (Hero vo : cs) {
			if (vo.getObjectState() == VisibleObjectState.Die) {
				continue;
			}
			if (vo.getSceneRef().getPvpModel() == 0) {
				if (effect.getTypePn() == 0) {
					break;
				}
			}
			if (effect.getType() == EffectType.guard) {// 守护
				voInRegion.remove(character);
				if (character.getId() == vo.getId()) {
					continue;
				}
				if (character.getMyFriendManager().getRoleWedingManager().getWedderId() == vo.getId().intValue()) {
					if (AttackFormula.atkIsEnable2(centerX, centerY, vo.getX(), vo.getY(), r)) {// 在效果的范围内
						voInRegion.add(vo);
						break;
					}
				}
				continue;
			}
			if (effect.getTypePn() == 1 && characterFightController.isZiweiTarget(vo.getId())) { // 伤害类型
				continue;
			}
			switch (characterFightController.getPkModel()) {
			case 0:
				if (effect.getTypePn() == 0) { // 伤害类型
					if (character.getId() == vo.getId()) {
						continue;
					}
					if (characterFightController.isZiweiTarget(vo.getId())) {
						if (!character.isAblePk(vo)) {
							continue;
						}
						break;
					}
					continue;
				} else {
					break;
				}
			case 1:
				if (effect.getTypePn() == 0) { // 伤害类型
					if (character.getId() == vo.getId()) {
						continue;
					}
					if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
						continue;
					}
					if (!character.isAblePk(vo)) {
						continue;
					}
					break;
				} else {
					if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
						break;
					}
					continue;
				}
			case 2:
				if (effect.getTypePn() == 0) { // 伤害类型
					if (character.getId() == vo.getId()) {
						continue;
					}
					if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
						continue;
					}
					if (!character.isAblePk(vo)) {
						continue;
					}
					break;
				} else {
					if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
						break;
					}
					continue;
				}
			case 3:
				if (effect.getTypePn() == 0) { // 伤害类型
					if (character.getId() == vo.getId()) {
						continue;
					}
					if (!character.isAblePk(vo)) {
						continue;
					}
					break;
				} else {
					if (character.getId() == vo.getId()) {
						break;
					}
					continue;
				}
			default:
				break;
			}

			if (AttackFormula.atkIsEnable2(centerX, centerY, vo.getX(), vo.getY(), effect.getScopeRadius())) {// 在效果的范围内
				if (i >= effect.getUseLimit()) {
					break;
				}
				if (vo.getId() == character.getId()) {
					continue;
				}
				voInRegion.add(vo);
				i++;
			}
		}
	}

	protected void findMonsterInScene(short centerX, short centerY, SkillEffect effect, List<VisibleObject> voInRegion, int r) {
		int i = 0;
		Collection<SceneMonster> ms = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		for (SceneMonster vo : ms) {
			if (vo.isDie()) {
				continue;
			}
			if (!AttackFormula.atkIsEnable2(centerX, centerY, vo.getX(), vo.getY(), r)) {// 在效果的范围内
				continue;
			}
			if (i >= effect.getUseLimit()) {
				break;
			}
			voInRegion.add(vo);
			i++;
		}
	}

	/**
	 * 对单一目标pk模式的判定
	 * 
	 * @param vo
	 * @param effect
	 * @return
	 */
	protected VisibleObject findCharacterTarget(VisibleObject vo, SkillEffect effect) {
		if (vo == null) {
			return null;
		}
		CharacterFightController characterFightController = character.getFightController();
		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero chvo = ((Hero) vo);
			int status = chvo.getObjectState();
			if (status == VisibleObjectState.Die) {
				return null;
			}
			if (effect.getTypePn() == 0 && chvo.isWudiBuffer) { // 伤害类型
				return null;
			}
		}
		if (!(vo.getSceneObjType() == SceneObj.SceneObjType_Hero)) {
			return vo;
		}
		switch (characterFightController.getPkModel()) {
		case 0:
			if (effect.getTypePn() == 0) { // 伤害类型
				if (character.getId() == vo.getId()) {
					return null;
				}
				if (vo.getSceneRef().getPvpModel() == 0 && !((Hero) vo).getMyCharacterVipManger().isFeibaohuState()) {
					return null;
				}
				if (characterFightController.isZiweiTarget(vo.getId())) {
					if (!character.isAblePk(vo)) {
						return null;
					}
					break;
				}
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 504));
				return null;
			} else {
				break;
			}
		case 1:
			if (effect.getTypePn() == 0) { // 伤害类型
				if (character.getId() == vo.getId()) {
					return null;
				}
				if (vo.getSceneRef().getPvpModel() == 0 && !((Hero) vo).getMyCharacterVipManger().isFeibaohuState()) {
					return null;
				}
				if (characterFightController.isZiweiTarget(vo.getId())) {
					break;
				}
				if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 527));
					return null;
				}
				if (!character.isAblePk(vo)) {
					return null;
				}
				break;
			} else {
				if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
					break;
				}
				return null;
			}
		case 2:
			if (effect.getTypePn() == 0) { // 伤害类型
				if (character.getId() == vo.getId()) {
					return null;
				}
				if (vo.getSceneRef().getPvpModel() == 0 && !((Hero) vo).getMyCharacterVipManger().isFeibaohuState()) {
					return null;
				}
				if (characterFightController.isZiweiTarget(vo.getId())) {
					break;
				}
				if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20088));
					return null;
				}
				if (!character.isAblePk(vo)) {
					return null;
				}
				break;
			} else {
				if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
					break;
				}
				return null;
			}
		case 3:
			if (effect.getTypePn() == 0) { // 伤害类型
				if (character.getId() == vo.getId()) {
					return null;
				}
				if (!character.isAblePk(vo)) {
					return null;
				}
				break;
			}
		default:
			break;
		}
		return vo;
	}

	@Override
	protected List<VisibleObject> monomerEffectTarget(FighterVO fighterVO) throws Exception {
		List<VisibleObject> visibleObjects = new ArrayList<VisibleObject>();
		int effectScope = fighterVO.getCharacterSkill().getSkill().getTarget();
		SkillTarget skillTarget = SkillTarget.getTarget(effectScope);
		SkillEffect effect = fighterVO.getEffect();
		int r = effect.getScopeRadius();
		switch (skillTarget) {
		case Me:// 自己
			visibleObjects.add(character);
			break;
		case MyTeam:// 自己所在小队
			// 给队伍某个人施加效果
			List<Hero> teamers = character.getMyTeamManager().getMyTeam().getCharacterPlayers();
			if (teamers.size() > 0) {
				Hero friend = teamers.get(0);
				if (friend != null) {
					visibleObjects.add(friend);
				}
			}
			break;
		case FriendTarget:// 友好目标
			visibleObjects.add(fighterVO.getRecipient());
			break;
		case ScopeHostilityTarge:// 范围内敌对目标(玩家或者怪物)
			if (effect.getAoeType() == 1) {
				findCharacterInScene(character.getX(), character.getY(), effect, visibleObjects, r);
				findSceneMonsterInScene(character, effect, visibleObjects);
			} else {
				findCharacterInScene(fighterVO.getRecipient().getX(), fighterVO.getRecipient().getY(), effect, visibleObjects, r);
				findSceneMonsterInScene(fighterVO.getRecipient(), effect, visibleObjects);
			}
			break;
		case Target:// 当前目标
			// TODO 考虑：判断是否给目标加防
			VisibleObject visibleObject = findCharacterTarget(fighterVO.getRecipient(), effect);
			if (effect.getTypePn() == 1 && visibleObject.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
				break;
			}
			if (visibleObject != null) {
				if (visibleObject.getObjectState() == VisibleObjectState.Shock && !fighterVO.isForceAttack()) {
					break;
				}
				visibleObjects.add(visibleObject);
			}
			break;
		case PointTarget:// 以一个坐标点为中心
			short x = fighterVO.getX();
			short y = fighterVO.getY();
			if (x == 0 && y == 0) {
				x = fighterVO.getFighter().getX();
				y = fighterVO.getFighter().getY();
			}
			if (effect.getTypePn() == 1) {
				if (AttackFormula.atkIsEnable2(x, y, character.getX(), character.getY(), r)) {// 在效果的范围内
					visibleObjects.add(character);// 如果不是伤害型的buff,先加上自己
				}
			}
			findCharacterInScene(x, y, effect, visibleObjects, r);
			if (visibleObjects.size() > 1) {
				VisibleObject vo = visibleObjects.get(0);
				visibleObjects.clear();
				visibleObjects.add(vo);
			}
			break;
		case Spouse:
		case SpouseFighting:
			visibleObjects.add(fighterVO.getRecipient());
			break;
		case Transport:
			visibleObjects.add(fighterVO.getRecipient());
			break;
		default:
			LOGGER.warn("no signal effect scope:" + effectScope);
			break;
		}
		return visibleObjects;
	}

	@Override
	public void judgeHurtWay(FighterVO fighterVO, VisibleObject who) {
		super.judgeHurtWay(fighterVO, who);
		if (fighterVO.getEffect().getTypePn() == 1) {
			return;
		}
		if (character.getObjectState() != VisibleObjectState.Attack) {
			character.setObjectState(VisibleObjectState.Attack);
			character.getFightController().setTimelag(System.currentTimeMillis());
		}
		character.getCharacterHorseController().onOwnerAttack(who);
	}

	@Override
	public void attackHurtValue(FighterVO fighterVO, VisibleObject affecter) throws Exception {
		super.attackHurtValue(fighterVO, affecter);
		character.getEquipmentController().fightInfluenceEquitment();// 战斗对装备的影响
	}

	@Override
	protected List<VisibleObject> populationEffectTarget(FighterVO fighterVO) throws Exception {
		List<VisibleObject> visibleObjects = new ArrayList<VisibleObject>();
		int effectScope = fighterVO.getCharacterSkill().getSkill().getTarget();
		SkillTarget skillTarget = SkillTarget.getTarget(effectScope);
		SkillEffect effect = fighterVO.getEffect();
		Iterator<VisibleObject> iterator = null;
		int r = effect.getScopeRadius();
		if (effect.getType() == EffectType.unhpbuff || effect.getType() == EffectType.jueji) {
			visibleObjects.add(character);// 如果不是伤害型的buff,先加上自己
			return visibleObjects;
		}
		switch (skillTarget) {
		case Me:// 自己
			visibleObjects.add(character);
			break;
		case MyTeam:// 自己所在小队
			// 给队伍某个人施加效果
			List<Hero> teamers = character.getMyTeamManager().getMyTeam().getCharacterPlayers();
			for (Hero friend : teamers) {
				visibleObjects.add(friend);
			}
			break;
		case FriendTarget:// 范围内友好目标
			// 给队伍某个人施加效果
			findCharacterInScene(character.getX(), character.getY(), effect, visibleObjects, r);
			break;
		case FriendTargetAndMe:// 范围内友好目标和自己
			if (effect.getAoeType() == 1) {
				findCharacterInScene(character.getX(), character.getY(), effect, visibleObjects, r);
			} else {
				findCharacterInScene(fighterVO.getRecipient().getX(), fighterVO.getRecipient().getY(), effect, visibleObjects, r);
			}
			visibleObjects.add(character);
			break;
		case ScopeHostilityTarge:// 范围内敌对目标(玩家或者怪物)
			if (effect.getAoeType() == 1) {
				findCharacterInScene(character.getX(), character.getY(), effect, visibleObjects, r);
				findSceneMonsterInScene(character, effect, visibleObjects);
			} else {
				findCharacterInScene(fighterVO.getRecipient().getX(), fighterVO.getRecipient().getY(), effect, visibleObjects, r);
				findSceneMonsterInScene(fighterVO.getRecipient(), effect, visibleObjects);
			}
			if (fighterVO.isForceAttack()) {
				break;
			}
			iterator = visibleObjects.iterator();
			while (iterator.hasNext()) {
				VisibleObject target = iterator.next();
				if (target.getObjectState() == VisibleObjectState.Shock) {
					iterator.remove();
				}
			}
			break;
		case Target:// 当前目标
			if (effect.getAoeType() == 1) {
				findCharacterInScene(character.getX(), character.getY(), effect, visibleObjects, r);
			} else {// effect.getAoeType()=2
				VisibleObject recipient=fighterVO.getRecipient();
				findCharacterInScene(recipient.getX(), recipient.getY(), effect, visibleObjects, r);
			}
			if (effect.getAoeType() == 1) {
				findSceneMonsterInScene(character, effect, visibleObjects);
			} else {// effect.getAoeType()=2
				findSceneMonsterInScene(fighterVO.getRecipient(), effect, visibleObjects);
			}
			if (fighterVO.isForceAttack()) {
				break;
			}
			iterator = visibleObjects.iterator();
			while (iterator.hasNext()) {
				VisibleObject target = iterator.next();
				if (target.getObjectState() == VisibleObjectState.Shock) {
					iterator.remove();
				}
			}
			break;
		case PointTarget:// 以一个坐标点为中心
			short x = fighterVO.getX();
			short y = fighterVO.getY();
			if (x == 0 && y == 0) {
				x = fighterVO.getFighter().getX();
				y = fighterVO.getFighter().getY();
			}
			int angle = fighterVO.getSkill().getAngle();
			ArrowWay arrowway = fighterVO.getSkill().getArrowWay();
			if (effect.getAoeType() == 1) {
				x = character.getX();
				y = character.getY();
			}
			short[] atkxy = null;
			if (arrowway == ArrowWay.line || arrowway == ArrowWay.face) {
				r = effect.getScopeRadius() / 2;
				atkxy = AttackFormula.getStraightLinePoint(character.getX(), character.getY(), fighterVO.getX(), fighterVO.getY(), r);
				if (atkxy != null) {
					x = atkxy[0];
					y = atkxy[1];
				}
			}
			if (effect.getTypePn() == 1) {
				if (AttackFormula.atkIsEnable2(x, y, character.getX(), character.getY(), r)) {// 在效果的范围内
					visibleObjects.add(character);// 如果不是伤害型的buff,先加上自己
				}
			} else {
				findMonsterInScene(x, y, effect, visibleObjects, r);
			}
			findCharacterInScene(x, y, effect, visibleObjects, r);
			if (fighterVO.isForceAttack()) {
				break;
			}
			iterator = visibleObjects.iterator();
			while (iterator.hasNext()) {
				VisibleObject target = iterator.next();
				if (target.getObjectState() == VisibleObjectState.Shock) {
					iterator.remove();
				}
			}
			if (arrowway == ArrowWay.line || arrowway == ArrowWay.face) {
				atkxy = AttackFormula.getStraightLinePoint(character.getX(), character.getY(), fighterVO.getX(), fighterVO.getY(), effect.getScopeRadius());
				if (atkxy != null) {
					fighterVO.setX(atkxy[0]);
					fighterVO.setY(atkxy[1]);
				}
			}
			if (arrowway == ArrowWay.face) {
				List<VisibleObject> visibleObjects_tmp = new ArrayList<VisibleObject>();
				for (int i = 0; i < visibleObjects.size(); i++) {
					VisibleObject vo = visibleObjects.get(i);
					boolean tf = AttackFormula
							.inAngle(character.getX(), character.getY(), fighterVO.getX(), fighterVO.getY(), vo.getX(), vo.getY(), angle, effect.getScopeRadius());
					if (tf) {
						visibleObjects_tmp.add(vo);
					}
				}
				visibleObjects = visibleObjects_tmp;
			}
			if (arrowway == ArrowWay.line) {
				// angle = 3;
				float k = 1;
				float b = 0;
				if (character.getX() != fighterVO.getX()) {
					float kb[] = AttackFormula.getLineKB(character.getX(), character.getY(), fighterVO.getX(), fighterVO.getY());
					if (kb != null) {
						k = kb[0];
						b = kb[1];
					}
				}
				List<VisibleObject> visibleObjects_tmp = new ArrayList<VisibleObject>();
				for (int i = 0; i < visibleObjects.size(); i++) {
					VisibleObject vo = visibleObjects.get(i);
					boolean tf = false;
					if (character.getX() == fighterVO.getX()) {
						tf = Math.abs(character.getX() - vo.getX()) < angle;
					} else {
						tf = AttackFormula.atkIsEnableRectangle(angle, k, b, vo.getX(), vo.getY());
					}
					if (tf) {
						visibleObjects_tmp.add(vo);
					}
				}
				visibleObjects = visibleObjects_tmp;
			}

			break;
		case Spouse:
		case SpouseFighting:
			visibleObjects.add(fighterVO.getRecipient());
			break;
		case Transport:
			visibleObjects.add(fighterVO.getRecipient());
			break;
		default:
			LOGGER.warn("no Groups effect scope:" + effectScope);
			break;
		}
		return visibleObjects;
	}
}
