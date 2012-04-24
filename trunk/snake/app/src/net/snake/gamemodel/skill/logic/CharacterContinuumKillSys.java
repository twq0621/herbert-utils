package net.snake.gamemodel.skill.logic;

import java.util.Iterator;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.ContinuumKillDataEntry;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.ContinummKillDataManager;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.response.ContinummKillNum12001;
import net.snake.gamemodel.skill.response.ContinummKillTime12002;
import net.snake.shell.Options;

/**
 * 角色连斩系统管理器
 * 
 */
public class CharacterContinuumKillSys implements Updatable {
	private static Logger logger = Logger.getLogger(CharacterContinuumKillSys.class);
	//
	Hero character;
	/** 上次击杀怪物的时间 */
	long previouseKillTime;//
	int currentkillcount = 0;
	private int killCnt4Ten = 0;

	ContinuumKillDataEntry currentDataEntry;
	private CharacterSkill continuumKill_aoe = null;

	public CharacterContinuumKillSys(Hero character) {
		this.character = character;
		currentDataEntry = ContinummKillDataManager.getInstance().getByKillCount(1);

	}

	public void destroy() {
		currentDataEntry = null;
	}

	/**
	 * 进入场景发送此消息
	 */
	public void sendInitScentcurrentkillTime() {
		if (!character.isFristEnterMap()) {
			return;
		}
		character.sendMsg(new ContinummKillTime12002(currentDataEntry.getId()));
	}

	private boolean isTenKill(SceneMonster monster) {
		if (killCnt4Ten == 10) {
			killCnt4Ten = 0;
			return true;
		}
		// int ret=currentkillcount %10;
		// if (ret ==0) {
		// return true;
		// }
		return false;
	}

	/**
	 * 是否来个十人斩的aoe。
	 * 
	 */
	public boolean tryKillTen() {
		if (!isTenKill(null)) {
			return false;
		}

		if (continuumKill_aoe == null) {
			return false;

		}
		FighterVO fighterVO = new FighterVO(character, character, character.getX(), character.getY(), continuumKill_aoe);
		fighterVO.forceAttack(false);

		CharacterFightController fightController = character.getFightController();
		fightController.autoAttack(false);
		fightController.addSkillAttack(fighterVO);
		return true;
	}

	@Override
	public void update(long now) {
		if (currentDataEntry == null) {
			return;
		}
		if (now - previouseKillTime > currentDataEntry.getCountDown()) {
			if (currentkillcount != 0) {
				lianzhanCount(currentkillcount);
				currentkillcount = 0;
				killCnt4Ten = 0;
				currentDataEntry = ContinummKillDataManager.getInstance().getByKillCount(1);
				character.sendMsg(new ContinummKillTime12002(currentDataEntry.getId()));
				character.sendMsg(new ContinummKillNum12001(currentkillcount));
				return;
			}
		}
	}

	/**
	 * 连斩计数统计
	 * 
	 * @param currentkillcount
	 */
	public void lianzhanCount(int currentkillcount) {
		if (character.getMaxContinueKillcount() < currentkillcount) {
			character.setMaxContinueKillcount(currentkillcount);
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().lianzhanCount(currentkillcount);
		}
	}

	public void gmOperater() {
		currentkillcount = currentDataEntry.getKillCountTo() - 1;
		killCnt4Ten = 9;

		previouseKillTime = System.currentTimeMillis();
		character.sendMsg(new ContinummKillNum12001(currentkillcount));
		// onMonsterDie(null);
	}

	/**
	 * 当怪物被击杀时,调用此方法
	 * 
	 * @param scenemonster
	 */
	public void onMonsterDie(SceneMonster scenemonster) {
		if (character.getGrade() - scenemonster.getGrade() > Options.KillGradeLimit) {
			return;
		}

		int timecount = currentDataEntry.getCountDown();
		long now = System.currentTimeMillis();
		if (now - previouseKillTime > timecount) {
			if (currentkillcount != 0) {
				lianzhanCount(currentkillcount);
				currentkillcount = 0;
				killCnt4Ten = 0;
				currentDataEntry = ContinummKillDataManager.getInstance().getByKillCount(1);
				character.sendMsg(new ContinummKillTime12002(currentDataEntry.getId()));
				character.sendMsg(new ContinummKillNum12001(currentkillcount));
				previouseKillTime = now;
			}
			previouseKillTime = now;
			return;
		}
		currentkillcount++;
		killCnt4Ten++;
		character.sendMsg(new ContinummKillNum12001(currentkillcount));

		previouseKillTime = now;
		if (currentkillcount == currentDataEntry.getKillCountTo()) {// 当达到上限时
			// 调用检查获取称号 广播称号信息
			// int appId = currentDataEntry.getAppellationId();
			// character.getMyTitleManager().getTitleByTitleId(appId);
			int bufferId = currentDataEntry.getBufId();
			// 添加BUFfer
			addBuffer(bufferId);
			currentDataEntry = ContinummKillDataManager.getInstance().getByKillCount(currentkillcount);
			character.sendMsg(new ContinummKillTime12002(currentDataEntry.getId()));
			lianzhanCount(currentkillcount);

		}
	}

	/**
	 * 添加buffer
	 * 
	 * @param bufferId
	 */
	public void addBuffer(int bufferId) {
		SkillEffect effect = SkillEffectManager.getInstance().getSkillEffectById(bufferId);
		if (effect != null) {
			try {
				EffectInfo effectInfo = new EffectInfo(effect);
				effectInfo.setAffecter(character);
				effectInfo.setAttacker(character);
				character.getEffectController().addEffect(effectInfo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void initial() {
		CharacterSkillManager characterSkillManager = character.getCharacterSkillManager();
		for (Iterator<CharacterSkill> iterator = characterSkillManager.getAllCharacterSkill().iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = iterator.next();
			
			if (characterSkill.getSkill().getSkilltype() == 5) {
				continuumKill_aoe = characterSkill;
				break;
			}
		}
	}
}
