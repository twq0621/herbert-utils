package net.snake.ai.formula;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.upgrade.response.UpgradeBordsResponse;
import net.snake.commons.script.IEventListener;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.hero.bean.Charactergrade;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.persistence.CharactergradeManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 人物属性处理
 * 
 * @author serv_dev
 * 
 */
public class CharacterFormula {

	private static Logger logger = Logger.getLogger(CharacterFormula.class);

	public static boolean xiAllDian(Hero character) {
		if (character.getAttackAddpoint() + character.getDefenceAddpoint() + character.getStrongAddpoint() + character.getLightAddpoint() <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 505));
			return false;
		}
		character.setPotential(character.getPotential() + character.getAttackAddpoint() + character.getDefenceAddpoint() + character.getStrongAddpoint()
				+ character.getLightAddpoint());
		character.setAttackAddpoint(0);
		character.setDefenceAddpoint(0);
		character.setLightAddpoint(0);
		character.setStrongAddpoint(0);
		sendCharacterProperties(character);
		character.getPropertyAdditionController().replaceListener(character.getAddPointManager(), character.getAddPointManager());
		return true;
	}

	public static boolean extraPropertyAdd(int attackAdd, int defenceAdd, int qingshenAdd, int jiantiAdd, Hero character) {

		int potential = character.getPotential();
		int _usePotential = attackAdd + defenceAdd + qingshenAdd + jiantiAdd;

		if (potential < _usePotential) {
			return false;
		}
		// character.setAttack(character.getAttack() + Math.round(attackAdd *
		// 1.6f));
		// character.setDefence(character.getDefence() + defenceAdd * 2);
		// character.setCrt(character.getCrt() + Math.round(0.8f *
		// qingshenAdd));
		// character.setDodge(character.getDodge() + 2 * qingshenAdd);
		// character.setMaxHp(character.getMaxHp() + 8 * jiantiAdd);
		// character.setMaxMp(character.getMaxMp() + 4 * jiantiAdd);
		// character.setMaxSp(character.getMaxSp() + jiantiAdd);
		character.setAttackAddpoint(character.getAttackAddpoint() + attackAdd);
		character.setDefenceAddpoint(character.getDefenceAddpoint() + defenceAdd);
		character.setLightAddpoint(character.getLightAddpoint() + qingshenAdd);
		character.setStrongAddpoint(character.getStrongAddpoint() + jiantiAdd);
		character.setPotential(character.getPotential() - _usePotential);
		// character.getPropertyController().recompute();
		sendCharacterProperties(character);
		character.getPropertyAdditionController().replaceListener(character.getAddPointManager(), character.getAddPointManager());
		return true;
	}

	/** 加点公式 ***/
	public static float getAddAttack(int attackAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddAttack(attackAdd);
	}

	public static float getAddDefence(int defenceAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddDefence(defenceAdd);
	}

	public static float getAddCrt(int qingshenAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddCrt(qingshenAdd);
	}

	public static float getAddDodge(int qingshenAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddDodge(qingshenAdd);
	}

	public static float getAddMaxHp(int jiantiAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddMaxHp(jiantiAdd);
	}

	public static float getAddMaxMp(int jiantiAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddMaxMp(jiantiAdd);
	}

	public static float getAddMaxSp(int jiantiAdd) {
		return AppEventCtlor.getInstance().getEvtFightFormula().getAddMaxSp(jiantiAdd);
	}

	/** 加点公式 ***/

	/**
	 * 递归方法
	 * 
	 * @param character
	 * @param _experience
	 *            经验
	 * @throws Exception
	 * 
	 * 
	 * 
	 */
	public static void experienceProcess(Hero character, long _experience) {
		if (Options.IsCrossServ) {
			character.getMyCharacterAcrossIncomeManager().addExp(_experience);
			return;
		}
		long _maxExpe = character.getNextExperience();
		long _nowExpe = character.getNowExperience();
		short grade = character.getGrade();
		long _exp = _nowExpe + _experience;
		if (_exp <= 0) {
			return;
		}
		if (_maxExpe > _exp) {
			character.setNowExperience(_exp);
			FightMsgSender.sendGainExperienceMsg(character);
			// RoleUpgradeDescManager.getInstance().upGradeMSG(character, _nowExpe);
			return;
		}
		character.setGrade((short) (grade + 1));
		if (!characterGrowup(character, grade + 1)) {
			character.setGrade(grade);
			return;
		}
		character.getMyCharacterGiftpacksManger().getRoleEachLevelGiftPacksManager().addGradeGiftPacks();
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_HeroUpGrade, new Object[] { character });
		character.setNowExperience(_exp - _maxExpe);
		character.setNowHp(character.getPropertyAdditionController().getExtraMaxHp());
		character.setNowMp(character.getPropertyAdditionController().getExtraMaxMp());
		character.setNowSp(character.getPropertyAdditionController().getExtraMaxSp());
		// //广播
		character.getEyeShotManager().sendMsg(new UpgradeBordsResponse(character.getId()));
		// 发送给自己属性增加
		sendCharacterProperties(character);
		// 添加升级日志输出
		// 考虑到可能有一次升多级的情况，所以加在递归前调用
		_maxExpe = character.getNextExperience();
		if (_maxExpe > 0 && character.getNowExperience() >= _maxExpe) {// 可能会连续升级几级的情况
			experienceProcess(character, 0);
		}
		character.getMyTeamManager().updateGradeUpMsgToTeamer();
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		character.getMyCharacterAchieveCountManger().getCharacterOtherCount().gradeCount(character.getGrade());
		character.getCharacterSkillManager().fuqiSkillReload();// 人物升级时检测夫妻的技能
		// RoleUpgradeDescManager.getInstance().upGradeMSG(character, 0l);
	}

	/**

	 */
	public static boolean characterGrowup(final Hero character, int nextGrade) {
		String key = character.getPopsinger() + "_" + nextGrade;
		Charactergrade charactergrade = CharactergradeManager.getInstance().getCharactergradeByKey(key);
		if (charactergrade == null) {
			return false;
		}
		List<Skill> skills = SkillManager.getInstance().getCanLearnSkills(character);
		for (int i = 0; i < skills.size(); i++) {
			character.getCharacterSkillManager().learnSkill(skills.get(i));
		}
		// 升级灵宠的等级
		Collection<Horse> list = character.getCharacterHorseController().getAllHorse();
		for (Horse horse : list) {
			horse.horseGainedExp(0);
		}
		// 命中1、暴击值2、气血上限3、法力上限4、外功攻击5、内功攻击6、外功防御7、内功防御8、闪避9、体质10、强壮 11、内功12、
		// 灵敏13、 定力14
		character.setMaxHp(character.getMaxHp() + charactergrade.getHpAdd());
		character.setMaxMp(character.getMaxMp() + charactergrade.getMpAdd());
		character.setMaxSp(character.getMaxSp() + charactergrade.getSpAdd());
		// TODO 攻击计算
		character.setAttack(character.getAttack() + charactergrade.getAttackAdd());
		character.setDefence(character.getDefence() + charactergrade.getDefAdd());
		character.setCrt(character.getCrt() + charactergrade.getExposeAdd());
		character.setDodge(character.getDodge() + charactergrade.getDodgeAdd());
		character.setNextExperience(charactergrade.getExpAdd());
		character.setPotential(character.getPotential() + charactergrade.getPotentialAdd());
		character.setMoveSpeed(character.getMoveSpeed() + charactergrade.getMoveSpeed());
		character.setAtkColdtime(character.getAtkColdtime() + charactergrade.getAtkSpeed());
		character.setHit(character.getHit() + charactergrade.getHitAdd());
		character.getPropertyAdditionController().recompute();
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterManager.getInstance().update(character);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		return true;

	}

	/**
	 * 创建角色调用此方法
	 */
	public static boolean characterCreateGrowup(Hero character, int nextGrade) throws IOException {
		String key = character.getPopsinger() + "_" + nextGrade;
		Charactergrade charactergrade = CharactergradeManager.getInstance().getCharactergradeByKey(key);
		if (charactergrade == null) {
			return false;
		}
		// 命中1、暴击值2、气血上限3、法力上限4、外功攻击5、内功攻击6、外功防御7、内功防御8、闪避9、体质10、强壮 11、内功12、
		// 灵敏13、 定力14
		character.setMaxHp(character.getMaxHp() + charactergrade.getHpAdd());
		character.setMaxMp(character.getMaxMp() + charactergrade.getMpAdd());
		character.setMaxSp(character.getMaxSp() + charactergrade.getSpAdd());
		// TODO 攻击计算
		character.setAttack(character.getAttack() + charactergrade.getAttackAdd());
		character.setDefence(character.getDefence() + charactergrade.getDefAdd());
		character.setCrt(character.getCrt() + charactergrade.getExposeAdd());
		character.setDodge(character.getDodge() + charactergrade.getDodgeAdd());
		character.setNextExperience(charactergrade.getExpAdd());
		character.setPotential(character.getPotential() + charactergrade.getPotentialAdd());
		character.setMoveSpeed(character.getMoveSpeed() + charactergrade.getMoveSpeed());
		character.setAtkColdtime(character.getAtkColdtime() + charactergrade.getAtkSpeed());
		character.setHit(character.getHit() + charactergrade.getHitAdd());
		// character.getPropertyController().recompute();
		return true;

	}

	/**
	 * 更换装备后修改人物属性并发送给客户端
	 * 
	 * @param session
	 * @param character
	 * @throws IOException
	 */
	public static void sendCharacterProperties(Hero character) {
		CharacterPropertyResponse10108 characterPropertyResponse = new CharacterPropertyResponse10108(character);
		character.sendMsg(characterPropertyResponse);
	}

	/**
	 * 婚宴主人所获经验
	 * 
	 * @return
	 */
	public static int getFeastOwnerExp(int grade) {
		return (grade * grade * grade) / 10;
	}

	/**
	 * 婚宴主人所获真气
	 * 
	 * @return
	 */
	public static int getFeastOwnerZhenQi(int grade) {
		return grade / 2;
	}

	/**
	 * 参加宴席获得真气
	 * 
	 * @param character
	 * @return
	 */
	public static int getFeastExp(Hero character) {
		return (int) Math.pow(character.getGrade(), 3);
	}

	/**
	 * 参加宴席获得真气
	 * 
	 * @param character
	 * @return
	 */
	public static int getFeastZhenQi(Hero character) {
		return character.getGrade() * 4;
	}

	/**
	 * 每日从婚宴中可获取的经验数上限
	 * 
	 * @param character
	 * @return
	 */
	public static int getDayMaxFeastExp(Hero character) {
		short grade = character.getGrade();
		return (int) (Math.pow(grade, 3) * 50);
	}

	/**
	 * 第日从婚宴中可获取的真气数上限
	 * 
	 * @param character
	 * @return
	 */
	public static int getDayMaxFeastZhenQi(Hero character) {
		return 5000;
	}

	public static int getKuafuOneMinute(Hero character) {
		return character.getGrade() * character.getGrade() * character.getMycharacterAcrossZhengzuoManager().getRegionContinuumExp()
				* character.getMyFactionManager().getXuanyuanjianJiacheng();
	}
}
