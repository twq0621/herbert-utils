package net.snake.ai.fight.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.consts.SkillTarget;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.SkillEffect;


/**
 * 怪物的效果处理
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class MonsterEffectHandler extends BaseEffectHandler {

	public MonsterEffectHandler(VisibleObject vo) {
		super(vo);
		monster = (SceneMonster) vo;
	}

	private static final Logger logger = Logger.getLogger(MonsterEffectHandler.class);

	private SceneMonster monster;// 施法者

	@Override
	protected List<VisibleObject> monomerEffectTarget(FighterVO fighterVO) throws Exception {
		List<VisibleObject> visibleObjects = new ArrayList<VisibleObject>();
		int effectScope = fighterVO.getCharacterSkill().getSkill().getTarget();
		SkillTarget skillTarget = SkillTarget.getTarget(effectScope);
		switch (skillTarget) {
		case Me:// 自己
			visibleObjects.add(monster);
			break;
		case MyTeam:// 自己所在小队
			break;
		case FriendTarget:// 范围内友好目标
			break;
		case ScopeHostilityTarge:// 范围内敌对目标(玩家或者怪物)
			SkillEffect effect = fighterVO.getEffect();
			if (effect.getAoeType() == 1) {
				findCharacterInScene(monster.getX(), monster.getY(), effect, visibleObjects, effect.getScopeRadius());
			} else {// effect.getAoeType()=2
				findCharacterInScene(fighterVO.getRecipient().getX(), fighterVO.getRecipient().getY(), effect, visibleObjects, effect.getScopeRadius());
			}

			if (visibleObjects.size() > 0) {
				fighterVO.setMaxHurtNum(1);
				VisibleObject vo = visibleObjects.get(0);
				visibleObjects.clear();
				visibleObjects.add(vo);
				fighterVO.setRecipient(vo);
			}
			break;
		// case :// TODO 其他技能
		// break;
		case Target:// 当前目标
			// TODO 考虑：判断是否给目标加防
			visibleObjects.add(fighterVO.getRecipient());
			break;
		case PointTarget:
			findSceneMonsterInScene(monster, fighterVO.getEffect(), visibleObjects);
			break;
		default:
//			logger.warn("检查单体效果范围不存在：", effectScope);
			break;
		}

		return visibleObjects;
	}

	@Override
	protected List<VisibleObject> populationEffectTarget(FighterVO fighterVO) throws Exception {
		List<VisibleObject> visibleObjects = new ArrayList<VisibleObject>();
		int effectScope = fighterVO.getCharacterSkill().getSkill().getTarget();
		SkillTarget skillTarget = SkillTarget.getTarget(effectScope);

		switch (skillTarget) {
		case Me:// 自己
			visibleObjects.add(monster);
			break;
		case MyTeam:// 自己所在小队
			break;
		case FriendTarget:// 范围内友好目标
			findSceneMonsterInScene(monster, fighterVO.getEffect(), visibleObjects);
			break;
		case ScopeHostilityTarge:// 范围内敌对目标(玩家或者怪物)
			SkillEffect effect = fighterVO.getEffect();
			if (effect.getAoeType() == 1) {
				findCharacterInScene(monster.getX(), monster.getY(), effect, visibleObjects, effect.getScopeRadius());
			} else {// effect.getAoeType()=2
				findCharacterInScene(fighterVO.getRecipient().getX(), fighterVO.getRecipient().getY(), effect, visibleObjects, effect.getScopeRadius());
			}
			break;
		// case 5:// TODO 其他技能
		// break;
		case Target:// 当前目标
			logger.warn("target：current target");
			break;
		case PointTarget:
			findSceneMonsterInScene(monster, fighterVO.getEffect(), visibleObjects);
			break;
		default:
//			logger.warn("检查群体效果范围不存在：", effectScope);
			break;
		}
		return visibleObjects;
	}
}
