package net.snake.gamemodel.monster.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;

/***
 * 怪物技能管理器
 * 
 * @author serv_dev
 * 
 */
public class MonsterSkillManager extends BaseSkillManager<SceneMonster> {

	public MonsterSkillManager(SceneMonster vo) {
		super(vo);
	}

	private CharacterSkill pingkanSkill;
	private final Map<Integer, CharacterSkill> characterSkillMap = new HashMap<Integer, CharacterSkill>();// 人物技能表的map，key为技能id
	private Map<Integer, CharacterSkill> topChoiceSkillMap = new HashMap<Integer, CharacterSkill>();// 首选的技能列表

	/**
	 * 添加首选技能触发
	 * 
	 * @param characterSkill
	 */
	public void addTopChoiceSkill(CharacterSkill characterSkill) {
		topChoiceSkillMap.put(characterSkill.getSkillId(), characterSkill);
	}

	public CharacterSkill removeFromTopChoiceSkillList(int characterId) {
		return topChoiceSkillMap.remove(characterId);
	}

	public void clearTopChoiceSkill() {
		if (topChoiceSkillMap.size() > 0) {
			topChoiceSkillMap.clear();
		}
	}

	@Override
	public boolean useSkill(CharacterSkill characterSkill) {

		SceneMonster monster = getVoObject();

		if (monster.getTarget() == null) {
			monster.setObjectState(VisibleObjectState.Idle);
			monster.getEnmityManager().clearEnmityList();
			return false;
		}
		VisibleObject vo = monster.getTarget();
		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero chara = (Hero) vo;
			if (chara.getObjectState() == VisibleObjectState.Die) {
				return false;
			}
		} else {
			if (vo.isZeroHp()) {
				return false;
			}
		}

		if (monster.getTarget().getScene() != monster.getScene()) {
			monster.getEnmityManager().removeFromMyEnmityList(monster.getTarget());
			return false;
		}

		Skill _skill = characterSkill.getSkill();
		if (characterSkill.cool()) {
			return false;
		} else {
			int triggerProbability = characterSkill.getTriggerProbability();
			if (!GenerateProbability.isGenerate2(GenerateProbability.gailv, triggerProbability)) {
				//				logger.info("useSkill(CharacterSkill) - 怪物{}不使用了技能{}",new Object[]{monster.getId(),_skill.getName()}); //$NON-NLS-1$
				return false;
			}

			monster.setSkillDistance(_skill.getDistance());

			FighterVO fighterVO = new FighterVO(monster, monster, monster.getTarget(), characterSkill);

			boolean tf = AttackFormula.atkIsEnable2(monster.getX(), monster.getY(), monster.getTarget().getX(), monster.getTarget().getY(), (short) (fighterVO.getCharacterSkill()
					.getSkill().getDistance()));
			MonsterModel monsterModel = monster.getMonsterModel();
			//tf表示能否攻击到对方
			if (!tf) {
				// logger.info("PK对象不存在或者已经死亡,当前血量：{}" +
				// "!------或者技能id:{}攻击无效,攻击范围：{}--攻击方id:{},x:{},y:{}"
				// + "----被攻击方id:{},x:{},y:{}------------", new Object[] {
				// monster.getNowHp(),
				// fighterVO.getCharacterSkill().getSkillId(),
				// fighterVO.getCharacterSkill().getSkill().getDistance(),
				// monster.getId(), monster.getX(),
				// monster.getY(), monster.getTarget().getId(),
				// monster.getTarget().getX(),
				// monster.getTarget().getY() });
				if (AttackFormula.probabilityEnable(monsterModel.getPursuitProbability())) {
					monster.setObjectState(VisibleObjectState.Ispursuit);
					return false;
				} else {
					monster.setObjectState(VisibleObjectState.Idle);
					return false;
				}
			}

			return monster.getFightController().fight(fighterVO);
			// monster.getFightController().getEffectHandler().effect(skillEffect,fighterVO);
			// long nowstart = System.currentTimeMillis();
			// _skill.getName() + "的时间间隔为:" +(nowstart -
			// characterSkill.getStartcd()));
			// characterSkill.setStartcd(nowstart);
		}
	}

	@Override
	public CharacterSkill getPingkanSkill() {
		return pingkanSkill;
	}

	@Override
	public void setPingkanSkill(CharacterSkill pingkanSkill) {
		this.pingkanSkill = pingkanSkill;
	}

	/**
	 * 自动攻击
	 * 
	 * @return
	 */
	public boolean autoFight() {

		boolean result = false;

		if (topChoiceSkillMap.size() > 0) {
			Collection<CharacterSkill> characterSkills = topChoiceSkillMap.values();
			CharacterSkill removeSkill = null;
			for (Iterator<CharacterSkill> iterator = characterSkills.iterator(); iterator.hasNext();) {
				CharacterSkill characterSkill = iterator.next();
				if (useSkill(characterSkill)) {
					characterSkill.setUsedCnt(characterSkill.getUsedCnt() + 1);
					if (characterSkill.getUsedCnt() >= characterSkill.getMaxUsedCnt()) {
						removeSkill = characterSkill;
					}
					result = true;
				}
			}

			if (removeSkill != null) {
				topChoiceSkillMap.remove(removeSkill.getSkillId());
			}

			return result;
		}

		Collection<CharacterSkill> characterSkills = getAllCharacterSkill();
		boolean fight = false;
		for (Iterator<CharacterSkill> iterator = characterSkills.iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = iterator.next();
			if (!characterSkill.getSkill().isZhudong()) {
				continue;
			}
			if (useSkill(characterSkill)) {
				return true;
			}
		}

		if (!fight) {
			CharacterSkill pingkanSkill = getPingkanSkill();
			if (pingkanSkill != null) {
				if (useSkill(pingkanSkill)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public CharacterSkill getCharacterSkillById(int skillId) {
		CharacterSkill characterSkill = characterSkillMap.get(skillId);
		if (characterSkill == null)
			characterSkill = topChoiceSkillMap.get(skillId);
		return characterSkill;
	}

	@Override
	public CharacterSkill addSkill(int skillId, int grade) {
		SceneMonster monster = getVoObject();
		CharacterSkill characterSkill = new CharacterSkill(monster, monster);
		characterSkill.setSkillId(skillId);
		characterSkill.setGrade(grade);
		characterSkillMap.put(skillId, characterSkill);
		return characterSkill;
	}

	@Override
	public Collection<CharacterSkill> getAllCharacterSkill() {
		return characterSkillMap.values();
	}

	@Override
	public void destroy() {
		// 对于npc来说就不用了
	}
}
