package net.snake.gamemodel.hero.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import net.snake.ai.formula.CharacterFormula;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Popsinger;
import net.snake.consts.Position;
import net.snake.consts.SessionKey;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.logic.GsCalculator;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.response.CreateCharacterFailResponse10016;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.action.BaseLoopTaskAction;
import net.snake.gamemodel.task.persistence.CharacterTaskManager;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.cache.CharacterCacheManager;

import org.apache.log4j.Logger;

public class CharacterFactory {
	private static Logger logger = Logger.getLogger(CharacterFactory.class);

	public static Hero createCharacter(Integer accountId, String nickName, int popsinger, byte ico, byte sex, GamePlayer session) {
		Date now = new Date();
		Hero character = new Hero();
		character.setGrade((short) 1);
		character.setX((short) 0);// 为了让该列不为空
		character.setY((short) 0);//
		character.setScene(0);
		// character.setEsotericaExp(4);
		character.setCopper(0);
		// character.setIngot(0);
		character.setName(nickName);
		character.setSex(sex);
		character.setAccountId(accountId);
		try {
			Account account = AccountManager.getInstance().selectByAccountid(accountId);
			character.setAccount(account);
			// 创建角色，设置角色账号原始ID
			character.setAccountInitiallyId(account.getAccountInitiallyId());
		} catch (SQLException e1) {
			Logger.getLogger(CharacterFactory.class).warn("角色" + character.getName() + "在创建的时候获取账户失败");
		}
		character.setPopsinger(popsinger);
		character.setHeadimg(ico);
		// character.setLastdate(now);
		character.setNowExperience(0);
		character.setLastLogindate(now);
		character.setIsallowChat(CommonUseNumber.byte0);
		character.setIscloseCharacter(CommonUseNumber.byte0);
		character.setIsonline((byte) 2);
		character.setAllowchatStarttime(now);
		// character.setNewcomeleaderStr("");
		character.setChannelJingmai("");
		character.setChannelRealdragon("");
		character.setChannelBeidongExp(24);
		character.setMaxHorseAmount(3);
		character.setMaxStorageHorseAmount(5);
		character.setMaxStorageAmount((short) 30);
		character.setMaxBagAmount((short) 30);
		character.setMoveSpeed(0);
		character.setNowXingqing("");
		character.setNowBiaoqing("0000");
		character.setBiguanDate(new Date());
		character.setState((short) 0);
		character.setFlowerCount(0);
		character.setPkModel(0);
		character.setWeekLoginCount(1);
		character.setDropGood(0);
		character.setDayOnline(0);
		character.setMaxJumpCount(0);
		character.setNowAppellationid(0);
		character.setChengjiuPoint(0);
		character.setLimitOnlineTime(0);
		character.setChengzhanShengWang(0);
		character.setLunjianShengWang(0);
		character.setTodayChengzhanShengwang(0);
		character.setMaxSp(0);
		character.setNowSp(0);
		try {
			CharacterFormula.characterCreateGrowup(character, character.getGrade());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		character.setNowHp(character.getMaxHp());
		character.setNowMp(character.getMaxMp());
		character.setNowSp(character.getMaxSp());
		character.setCreatetime(now);
		character.setLastdate(now);
		character.setSkillpoBeginTime(now);
		character.setSkillpoTime(0);
		character.setCreateip(session.getAddress());
		character.setLastip(session.getAddress());
		character.setContribution(0);
		int sid = Integer.parseInt((String) session.getAttribute(SessionKey.sid));
		character.setOriginalSid(sid);
		character.setTodayLiaoshangExp(0);
		int popsingera = character.getPopsinger();
		Skill skill = SkillManager.getInstance().getCommonSkill(popsingera);
		character.setSkillid(skill.getId());
		// 是否存在同名=================================
		try {
			// 数据库插入
			CharacterManager.getInstance().insert(character);

			initTaskInfo(character);
			initGiftPacksGoodsInfo(character);
			initDujie(character);
			updateCatch(character);
			return character;
		} catch (Exception e) {
			session.sendMsg(new CreateCharacterFailResponse10016(20057));
			logger.error(e.getMessage(), e);
			return null;
		}

	}

	public static void initDujie(Hero character) throws SQLException {
		HeroDujieData dto = new HeroDujieData();
		dto.setHeroId(character.getId());
		dto.setName(character.getViewName());
		dto.setNum(1);
		dto.setRoushen(0);
		dto.setProcess(0);
		dto.setIsguard(0);
		dto.setGs(GsCalculator.calcHeroGs(character));
		dto.setIncome(0);
		dto.setHead(character.getHeadimg());
		character.getDujieCtrlor().insert(dto);
		character.getDujieCtrlor().setHeroDujieData(dto);
	}

	/**
	 * 创建角更新角色缓存信息
	 * 
	 * @param character
	 */
	public static void updateCatch(Hero character) {
		CharacterCacheManager.getInstance().getCharacterMap().put(character.getId(), new CharacterCacheEntry(character));
	}

	private static void initTaskInfo(Hero character) {
		// **先得到character.getid 在初始化任务
		// 任务初始化
		Map<Integer, Task> cacheTaskMap = TaskManager.getInstance().getCreateCharacterCacheTaskmap();
		for (Iterator<Task> iterator = cacheTaskMap.values().iterator(); iterator.hasNext();) {
			Task task = iterator.next();
			CharacterTask characterTask = CharacterTask.createTask(character.getTaskController(), task);
			if (characterTask.isLoop()) {
				((BaseLoopTaskAction) characterTask.getTaskVO()).taskRandomConditionReward(characterTask);
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
			}
			CharacterTaskManager.getInstance().insertCharacterTask(character, characterTask);
			// character.getTaskController().addTask(task);
		}
	}

	private static void initGiftPacksGoodsInfo(Hero character) {
		CharacterGoods cg = null;// CharacterGoods.createCharacterGoods(1, 89100001, 0); //TODO 
		if (cg == null) {
			return;
		}
		int pops = character.getPopsinger();
		if (pops == Popsinger.futu.getPopsinger()) {
			cg = CharacterGoods.createCharacterGoods(1, 90120006, 0);
		} else if (pops == Popsinger.guming.getPopsinger()) {
			cg = CharacterGoods.createCharacterGoods(1, 90110001, 0);
		} else if (pops == Popsinger.miaoyu.getPopsinger()) {
			cg = CharacterGoods.createCharacterGoods(1, 90140016, 0);
		} else {
			cg = CharacterGoods.createCharacterGoods(1, 90130011, 0);
		}
		cg.setCharacterId(character.getId());
		cg.setPosition(Position.BagGoodsBeginPostion);
		cg.setBind(CommonUseNumber.byte1);
		GoodsDataEntryManager.getInstance().insert(cg);
	}

}
