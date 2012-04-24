package net.snake.gamemodel.pet.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.commons.GenerateProbability;
import net.snake.consts.EffectType;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.skill.bean.CharacterSkillData;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.CharacterSkillDataProvider2;
import net.snake.gamemodel.skill.persistence.SkillManager;
import org.apache.log4j.Logger;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class HorseSkillManager extends BaseSkillManager<Horse> {
	private static final Logger logger = Logger.getLogger(HorseSkillManager.class);

	public HorseSkillManager(Horse vo) {
		super(vo);
	}

	List<CharacterSkill> zhudongSkills;// 主动使用技能 可以拖到快捷栏上的技能
	List<CharacterSkill> beidongSkills;// 给主人加属性的技能
	private CharacterSkill pingkanSkill;
	private List<CharacterSkill> allSkills;//

	private CharacterSkill neipanShenji;// 角色复活后，增加角色的伤害减免，持续一段时间
	private CharacterSkill tianyuanyangshengShenji;// 加速灵宠生产内丹的速度

	/**
	 * 角色复活后，增加角色的伤害减免，持续一段时间
	 * 
	 * @return
	 */
	public CharacterSkill getNeipanShenji() {
		return neipanShenji;
	}

	/**
	 * 加速灵宠生产内丹的速度
	 * 
	 * @return
	 */
	public CharacterSkill getTianyuanyangshengShenji() {
		return tianyuanyangshengShenji;
	}

	private List<Skill> randomSkillList;

	public int getAllSkillCount() {
		int allSkillSize = allSkills.size();
		return allSkillSize;
	}

	public CharacterSkill getPingkanSkill() {
		return pingkanSkill;
	}

	/**
	 * 骑乘被动技能添加
	 */
	public void onRide() {
		if (!beidongSkills.isEmpty()) {
			CharacterSkill[] characterSkills = new CharacterSkill[beidongSkills.size()];
			beidongSkills.toArray(characterSkills);
			getVoObject().getCharacter().getPropertyAdditionController().addChangeListener(characterSkills);
			characterSkills = null;
		}
	}

	/**
	 * 下马被动技能删除
	 */
	public void onUnRide() {
		if (!beidongSkills.isEmpty()) {
			CharacterSkill[] characterSkills = new CharacterSkill[beidongSkills.size()];
			beidongSkills.toArray(characterSkills);
			getVoObject().getCharacter().getPropertyAdditionController().removeChangeListener(characterSkills);
			characterSkills = null;
		}
	}

	public void init() {
		Horse horse = getVoObject();
		allSkills = new ArrayList<CharacterSkill>();// 所有技能
		zhudongSkills = new ArrayList<CharacterSkill>();// 主动使用技能
		beidongSkills = new ArrayList<CharacterSkill>();// 被动使用技能

		// 初始化平砍技能，同人物的平砍技能
		pingkanSkill = new CharacterSkill(horse, horse.getCharacter());
		CharacterSkill commonSkill = horse.getCharacter().getFightController().getCommonskill();
		pingkanSkill.setGrade(commonSkill.getGrade());
		pingkanSkill.setSkillId(commonSkill.getSkillId());

		try {
			List<CharacterSkillData> characterskilldatalist = CharacterSkillDataProvider2.getInstance().getHorseSkillDataList(horse.getId());

			for (CharacterSkillData data : characterskilldatalist) {
				CharacterSkill characterSkill = new CharacterSkill(data, horse, horse.getCharacter());
				Skill skill = characterSkill.getSkill();
				allSkills.add(characterSkill);
				if (skill.isZhudong()) {
					if (skill.getEffect().getType() == EffectType.speedUpNeiDan) {
						tianyuanyangshengShenji = characterSkill;
					} else if (skill.getEffect().getType() == EffectType.shjm) {
						neipanShenji = characterSkill;
					} else {
						zhudongSkills.add(characterSkill);
					}
				} else {
					beidongSkills.add(characterSkill);
					if (characterSkill != null && horse == horse.getCharacter().getCharacterHorseController().getShowHorse()) {
						horse.getCharacter().getPropertyAdditionController().addChangeListener(characterSkill);
					}
				}
			}
		} catch (SQLException e) {
			logger.error("initial skill of pet with err", e);
		}
	}

	/**
	 * 如果灵宠升级了，则随机一个技能给它，如果已经满5个技能了，则不再处理
	 * 
	 * @param characterHorse
	 * @return
	 */
	public Skill saveHorseSkill(CharacterHorse characterHorse, short pos) {
		SkillManager sm = SkillManager.getInstance();
		List<Skill> list = sm.getHorseSkills();
		return this.saveHorseSkill(characterHorse, pos, list);
	}

	private Skill saveHorseSkill(CharacterHorse characterHorse, short pos, List<Skill> skills) {
		logger.info("要保存的技能位置：=" + pos);
		if (isEnoughSkill()) {
			return null;
		}

		List<Skill> canLearnSkills = new ArrayList<Skill>();
		for (Skill skill : skills) {
			if (skill.isPassive()) {
				canLearnSkills.add(skill);
				continue;
			}
			if (null == getCharacterSkillById(skill.getId())) {
				canLearnSkills.add(skill);
			}
		}
		Skill skill = genSkill(canLearnSkills, pos);
		return skill;
	}

	/**
	 * 灵宠产生神技
	 * 
	 * @param characterHorse
	 */
	public void saveShenji(CharacterHorse characterHorse) {
		Horse horse = getVoObject();
		if (horse.getHorseModel().getIsUseShenji() == 0) {
			return;
		}
		if (characterHorse.getGrade() <= GameConstant.maxLearnSkillGrade) {
			return;
		}
		int skillcnt = this.getAllSkillCount();
		int cnt = characterHorse.getGrade() / GameConstant.preGradeSkill;
		int canlrnSkill = cnt - skillcnt;
		if (canlrnSkill < 0) {
			return;
		}
		SkillManager sm = SkillManager.getInstance();
		List<Skill> horseSkills = sm.getHorseShenjiSkills();
		List<Skill> canLearnSkills = new ArrayList<Skill>();
		for (Skill skill : horseSkills) {
			if (null == getCharacterSkillById(skill.getId())) {
				canLearnSkills.add(skill);
			}
		}
		short pos = (short) skillcnt;
		genSkill(canLearnSkills, pos);
	}

	public Skill genSkill(List<Skill> horseSkills, short pos) {
		logger.info("horseSkills.size()=" + horseSkills.size());
		if (horseSkills.size() <= 0) {
			return null;
		}
		int rnd = GenerateProbability.randomValue(horseSkills.size() - 1, 0);
		Skill skill = horseSkills.get(rnd);
		logger.info("保存之前技能总数=" + allSkills.size() + "zhudong=" + zhudongSkills.size() + ",beidong=" + beidongSkills.size());
		CharacterSkill characterSkill = addSkill(skill.getId(), pos);
		logger.info("pos=" + pos + ",保存的characterSkill=" + characterSkill.getSkillId() + "type=" + skill.getEffect().getType() + ",iszhu=" + skill.isZhudong());
		CharacterSkillDataProvider2.getInstance().insert(characterSkill);
		logger.info("保存以后技能总数=" + allSkills.size() + "zhudong=" + zhudongSkills.size() + ",beidong=" + beidongSkills.size());
		return skill;
	}

	public void randomHorseSkill(int skillPos[]) {
		if (randomSkillList == null || randomSkillList.size() != 16) {
			return;
		}
		CharacterSkill[] delcss = new CharacterSkill[skillPos.length];
		for (int i = 0; i < skillPos.length; i++) {
			CharacterSkill characterSkill = allSkills.get(skillPos[i]);
			delcss[i] = characterSkill;
		}
		Horse horse = getVoObject();
		CharacterHorse characterHorse = horse.getCharacterHorse();
		for (int i = 0; i < skillPos.length; i++) {
			delSkill(delcss[i]);
			saveHorseSkill(characterHorse, (short) skillPos[i], randomSkillList);
		}
	}

	private void delSkill(CharacterSkill characterSkill) {
		logger.info("删除的技能位置：=" + characterSkill.getPosition());
		Skill skill = characterSkill.getSkill();
		allSkills.remove(characterSkill);
		Horse horse = getVoObject();
		if (skill.isZhudong()) {
			zhudongSkills.remove(characterSkill);
		} else {
			beidongSkills.remove(characterSkill);
			if (characterSkill != null && horse == horse.getCharacter().getCharacterHorseController().getShowHorse()) {
				horse.getCharacter().getPropertyAdditionController().removeChangeListener(characterSkill);
			}
		}
		CharacterSkillDataProvider2.getInstance().asynDelCharacterSkill(horse.getCharacter(), characterSkill.getId());
	}

	@Override
	public CharacterSkill addSkill(int skillId, int pos) {
		Horse horse = getVoObject();
		CharacterSkill characterSkill = new CharacterSkill(horse, horse.getCharacter());
		characterSkill.setSkillId(skillId);
		characterSkill.setGrade(1);
		characterSkill.setCharacterId(horse.getId());
		characterSkill.setPosition((short) pos);
		characterSkill.setGrade(1);
		characterSkill.setQuickbarindex(0);
		characterSkill.setMastery(0);
		characterSkill.setPo(false);
		characterSkill.setMaxMastery(0);
		characterSkill.setSkilltype(1);
		Skill skill = characterSkill.getSkill();
		allSkills.add(pos, characterSkill);
		if (skill.isZhudong()) {
			if (skill.getEffect().getType() == EffectType.speedUpNeiDan) {
				tianyuanyangshengShenji = characterSkill;
			} else if (skill.getEffect().getType() == EffectType.shjm) {
				neipanShenji = characterSkill;
			} else {
				zhudongSkills.add(characterSkill);
			}
		} else {
			beidongSkills.add(characterSkill);
			if (characterSkill != null && horse == horse.getCharacter().getCharacterHorseController().getShowHorse()) {
				horse.getCharacter().getPropertyAdditionController().addChangeListener(characterSkill);
			}
		}
		return characterSkill;
	}

	@Override
	public Collection<CharacterSkill> getAllCharacterSkill() {
		return allSkills;
	}

	public List<CharacterSkill> getAllHorseSkillOrderByPos() {
		return allSkills;
	}

	public CharacterSkill getCharacterSkillById(int skillId) {
		CharacterSkill characterSkill = null;
		for (int i = 0; i < allSkills.size(); i++) {
			if (allSkills.get(i).getSkillId() == skillId) {
				characterSkill = allSkills.get(i);
				break;
			}
		}
		if (characterSkill == null) {
			characterSkill = getPingkanSkill();
		}
		return characterSkill;
	}

	public String getLearnSkills() {
		StringBuilder learnSkillsStr = new StringBuilder();
		for (CharacterSkill characterSkill : this.getAllCharacterSkill()) {
			learnSkillsStr.append(characterSkill.getSkillId()).append(",");
		}
		return learnSkillsStr.substring(0, learnSkillsStr.length() - 1);
	}

	public boolean useSkill(CharacterSkill characterSkill) {
		if (characterSkill == null) {
			return false;
		}
		Skill _skill = characterSkill.getSkill();
		long nowstart = System.currentTimeMillis();
		if (nowstart - characterSkill.getStartcd() < _skill.getCoolingtime()) {
			return false;
		} else {
			Horse horse = getVoObject();
			VisibleObject horseTarget = horse.getTarget();
			VisibleObject heroTarget =  horse.getCharacter().getTarget();
			if (horseTarget == null && heroTarget == null) {
				logger.warn("hero and his pet donot have attack target");
				return false;
			}
			FighterVO fighterVO = new FighterVO(horse, horse.getCharacter(), (horseTarget!= null ? horseTarget : heroTarget), characterSkill);
			if (horse.getCharacter().getFightController().fight(fighterVO)) {
				if (_skill.getDepletionParameter() == 4) {// 消耗坐骑活力
					horse.addLivingness(-_skill.getDepletionValue());
				}
				return true;
			}
			return false;
		}
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
	public boolean horseSkillUpgrade(CharacterSkill characterSkill, int grade) {
		SkillUpgradeExp exp = characterSkill.upgradeNeedZhenqi();// 当前的
		if (exp.getLimitGrade() > this.getVoObject().getCharacterHorse().getGrade()) {
			this.getVoObject().getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60061));
			return false;
		}
		boolean bf = super.skillUpgrade(characterSkill, grade);
		if (!bf) {
			return bf;
		}
		Skill skill = characterSkill.getSkill();
		if (skill.isPassive()) {
			Horse horse = getVoObject();
			if (characterSkill != null && horse == horse.getCharacter().getCharacterHorseController().getShowHorse()) {
				horse.getCharacter().getPropertyAdditionController().addChangeListener(characterSkill);
			}
		}
		return true;
	}

	/**
	 * 技能是否达到上限
	 * 
	 * @return
	 */
	public boolean isEnoughSkill() {
		int allSkillSize = zhudongSkills.size() + beidongSkills.size();
		logger.info("allSkillSize=" + allSkillSize);
		if (allSkillSize >= 5) {
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		if (pingkanSkill != null) {
			pingkanSkill.destroy();
			pingkanSkill = null;
		}
		if (zhudongSkills != null && !zhudongSkills.isEmpty()) {
			for (Iterator<CharacterSkill> iterator = zhudongSkills.iterator(); iterator.hasNext();) {
				CharacterSkill characterSkill = iterator.next();
				characterSkill.destroy();
			}
			if (zhudongSkills != null) {
				zhudongSkills = null;
			}
		}

		if (beidongSkills != null && !beidongSkills.isEmpty()) {
			for (Iterator<CharacterSkill> iterator = beidongSkills.iterator(); iterator.hasNext();) {
				CharacterSkill characterSkill = iterator.next();
				characterSkill.destroy();
			}
			if (beidongSkills != null) {
				beidongSkills = null;
			}
		}
		voObject = null;
	}

	public Collection<CharacterSkill> getZhudongSkills() {
		return zhudongSkills;
	}

	public Collection<CharacterSkill> getBeidongSkills() {
		return beidongSkills;
	}

	public List<Skill> getRandomSkillList() {
		return randomSkillList;
	}

	public void setRandomSkillList(List<Skill> randomSkillList) {
		this.randomSkillList = randomSkillList;
	}

}
