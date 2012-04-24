package net.snake.gamemodel.gm.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.upgrade.response.UpgradeBordsResponse;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.commons.StringUtil;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SPropertyEntity;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.EffectType;
import net.snake.consts.GameConstant;
import net.snake.consts.HorseStateConsts;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.chest.persistence.ChestCountManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.DujieAdd;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.persistence.DujieAddDataTbl;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.response.DujieAllStateResp;
import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.GameMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseCreator;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.logic.SystemPublishTask;
import net.snake.gamemodel.task.logic.action.BaseLoopTaskAction;
import net.snake.gamemodel.task.logic.action.TaskActionFactory;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.gamemodel.task.response.GetLoopTaskMsg20000;
import net.snake.gamemodel.task.response.GetTaskResponse;
import net.snake.gamemodel.task.response.TaskRunResponse;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.script.ScriptApiImpl;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * GM命令处理器
 * 
 * @author serv_dev
 * 
 */
public class GMCommand extends MsgProcessor {

	private static Logger logger = Logger.getLogger(GMCommand.class);

	private Pattern number = Pattern.compile("-?\\d{1,10}", 0);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {

		if (!Options.EnableGMCmd) {
			return;
		}

		String src = request.getString();
		if (src == null) {
			return;
		}
		src = src.toUpperCase();

		if (src.startsWith("#ESCAPE")) {// 逃离
		}
		if (!authentication(session)) {
			return;
		}
		// 全角空格变半角空格
		String content = src.trim().replace("　", " ");

		// 满血满蓝 MONEY
		// HP 0-100 把自己的血设置成多少百分比
		String[] array = content.split(" ");
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		String command = array[0];

		Hero charactor = session.getCurrentCharacter(Hero.class);
		if (command.equals("#HP")) {
			cmd4HP(charactor, array);
		} else if (command.equals("#BOSS")) {
			cmd4BOSS(charactor, array);
		} else if (command.equals("#FUBEN")) {
			cinstance(charactor, array);
		} else if (command.equals("#MAXHP")) {
			cmd4MaxHp(charactor, array);
		} else if (command.equals("#MAXMP")) {
			cmd4MaxMp(charactor, array);
		} else if (command.equals("#DEFENCE")) {
			cmd4Defence(charactor, array);
		} else if (command.equals("#ATTACK")) {
			cmd4Attack(charactor, array);
		} else if (command.equals("#MOVESPEED")) {
			cmd4MoveSpeed(charactor, array);
		} else if (command.equals("#ATTACKSPEED")) {
			cmd4AttackSpeed(charactor, array);
		} else if (command.equals("#MP")) {
			cmd4MP(charactor, array);
		} else if (command.equals("#SW")) {
			cmd4SW(charactor, array);
		} else if (command.equals("#SP")) {
			cmd4SP(charactor, array);
		} else if (command.equals("#ZHENQI")) {
			cmd4ZHENQI(charactor, array);
		} else if (command.equals("#YUANBAO")) {
			cmd4Gold(charactor, array);
		} else if (command.equals("#GETBOW")) {
			cmd4GetBow(charactor);
		} else if (command.equals("#GETDANTIAN")) {
			cmd4GetDantian(charactor);
		} else if (command.equals("#SILVER")) {
			cmd4Silver(charactor, array);
		} else if (command.equals("#COPPER")) {// 铜币
			cmd4Copper(charactor, array);
		} else if (command.equals("#LIJIN")) {// 铜币
			cmd4Jiaozi(charactor, array);
		} else if (command.equals("#GETITEM")) {
			cmd4CreateItem(charactor, array);
		} else if (command.equals("#GETITEMLIKE")) {
			cmd4CreateItemLike(charactor, array);
		} else if (command.equals("#EXP")) {
			cmd4Exp(charactor, array);
		} else if (command.equals("#HEXP")) {
			cmd4HExp(charactor, array);
		} else if (command.equals("#TP")) {
			cmd4Tp(charactor, array);
		} else if (command.equals("#KM")) {//
			killMonster(charactor, array);
		} else if (command.equals("#TASK")) {
			cmd4Task(charactor, array);
		} else if (command.equals("#FINTASK")) {
			cmd4FinishTask(charactor, array);
		} else if (command.equals("#KILL")) {
			cmd4Kill(charactor, array);
		} else if (command.equals("#KILLALL")) {
			cmd4KillALL(charactor, array);
		} else if (command.equals("#KILLMID")) {
			cmd4KillMID(charactor, array);
		} else if (command.equals("#KILLVIEW")) {
			cmd4KillView(charactor, array);
		} else if (command.equals("#SUPERMAN")) {
			cmd4Superman(charactor, array);
		} else if (command.equals("#AFK")) {// 挂机
			cmd4AFK(charactor, array);
		} else if (command.equals("#SYSBC")) {
			this.cmd4SYSBC(charactor, src);
		} else if (command.equals("#BC")) {
			this.cmd4BC(charactor, array);
		} else if (command.equals("#FCM")) {
			this.cmd4FCM(charactor, array);
		} else if (command.equals("#SKILL")) {// 获得技能
			changeSkillId(charactor, array[1]);
		} else if (command.equals("#SKILLUPGRADE")) {// 技能升级
			changeSkillUpgrade(charactor, array);
		} else if (command.equals("#ADDHORSE")) {// 加坐骑
			ADDHORSE(charactor, array);
		} else if (command.equals("#HORSEHL")) {// 加坐骑
			HORSEHL(charactor, array);
		} else if (command.equals("#LIANZHAN")) {// 连斩
			lianzhan(charactor, array);
		} else if (command.equals("#JINGMAI")) {// 经脉
			jingmai(charactor, array);
		} else if (command.equals("#ZHENLONG")) {// 经脉
			zhenlong(charactor, array);
		} else if (command.equals("#PAIHANG")) {// 排行
			paihang(charactor, array);
		} else if (command.equals("#JINYAN")) {// 禁言
			jinyan(charactor, array);
		} else if (command.equals("#ALLPROP")) {// 获得所有类别属性的加成值
			allprop(charactor, array);
		} else if (command.equals("#HORSESKILL")) {// 设置坐骑默认技能 添加坐骑技能
			sethorseskill(charactor, array);
		} else if (command.equals("#HORSESKILLREMOVE")) {// 设置坐骑默认技能 添加坐骑技能
			sethorseskillRemove(charactor, array);
		} else if (command.equals("#DROPGOODS")) {// 某种怪物掉落物品 金钱除外
			DropGoods(charactor, array);
		} else if (command.equals("#TONGGUAN")) {// 创建物品
			cG(charactor, array);
		} else if (command.equals("#BUFF")) {// buff
			buff(charactor, array);
		} else if (command.equals("#LAS")) {// 学习所有的技能
			learnAllSkill(charactor, array);
		} else if (command.equals("#LASS")) {// 学习所有的技能
			upgradeAllSkill(charactor, array);
		} else if (command.equals("#MX")) {// 满星 参数100
			manxing(charactor, array);
		} else if (command.equals("#CAB")) {// 清除身上的buff
			clearAllBuff(charactor, array);
		} else if (command.equals("#AQ")) {// 身上的暗器
			aq(charactor, array);
		} else if (command.equals("#CB")) {// 清空背包里所有的物品
			cl(charactor, array);
		} else if (command.equals("#TAKE")) {//
			TAKE(charactor, array);
		} else if (command.equals("#RESETJINSONGGUO")) {// 重置金松果祝福
			cmdReSetChesCount(charactor, array);
		} else if (command.equals("#CDCLEAR")) {// 清除技能cd时间
			cdclear(charactor, array);
		} else if (command.equals("#BESTEQUIP")) {// 最好的装备
			bestEquipment(charactor, array);
		} else if (command.equals("#MXZIZHUANG")) {// 满星紫装
			cmdMxZiZhuang(charactor, array);
		} else if (command.equals("#CZSW")) {
			cmd4ZCSW(charactor, array);
		} else if (command.equals("#LJSW")) {
			cmd4LJSW(charactor, array);
		} else if (command.equals("#TESTPRO")) {
			cmdTestProcessor(session, array);
		} else if (command.equals("#GRADE")) {
			cmd4upgrade(charactor, array);
		} else if (command.equals("#DUJIE")) {
			dujie(charactor, array);
		} else if (command.equals("#ROUSHEN")) {
			roushen(charactor, array);
		} else if (command.equals("#KMT")) {
			cmd4Km(charactor, array);
		} else if (command.equals("#PET")) {
			cmd4Pet(charactor, array);
		} else if (command.equals("#GETBOSS")) {
			SApi api = new ScriptApiImpl();
			api.createTimerMonster(Integer.parseInt(array[1]), charactor.getX(), charactor.getY(), 4, 1, 1, false, charactor.getSceneRef(), 0);
			// logger.warn("unknown GM cmd " + array[0]);
		}
	}

	private void cmdTestProcessor(GamePlayer player, String[] array) {
		if (array.length < 1) {
			return;
		}
		try {
			RequestMsg request = new TestProClientRequest(array);
			player.addRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cmd4LJSW(Hero charactor, String[] array) {
		int value = Integer.parseInt(array[1]);
		CharacterPropertyManager.changeLunJianShengWang(charactor, value);
	}

	private void cmd4ZCSW(Hero charactor, String[] array) {
		int value = Integer.parseInt(array[1]);
		CharacterPropertyManager.changeGongChengShengWang(charactor, value, true);
	}

	private void cmd4AttackSpeed(Hero charactor, String[] array) {
		int attackSpeed = Integer.parseInt(array[1]);
		charactor.setAtkColdtime(charactor.getAtkColdtime() + attackSpeed);
		if (attackSpeed != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.attackspeed, charactor.getPropertyAdditionController().getExtraAttackSpeed()));
		}
	}

	private void cmd4GetDantian(Hero charactor) {
		charactor.getDanTianController().acquisitionDanTian();

	}

	private void cmd4GetBow(Hero charactor) {
		charactor.getBowController().acquisitionBow();
	}

	private void bestEquipment(Hero charactor, String[] array) {
		// CharacterGoodController characterGoodController = charactor.getCharacterGoodController();
		// GoodmodelManager goodmodelManager = GoodmodelManager.getInstance();
		// for (int i = 1; i <= 12; i++) {
		// CharacterGoods charactergoods = characterGoodController.getGoodsByPositon((short) i);
		// if (charactergoods != null) {
		// continue;
		// }
		// for (Iterator<Goodmodel> iterator = goodmodelManager.getAlLGoodCollection().iterator(); iterator.hasNext();) {
		// Goodmodel goodmodel = iterator.next();
		// if (!goodmodel.isEquipment()) {
		// continue;
		// }
		// int modelpop = goodmodel.getPopsinger();
		// if (modelpop == 0 || charactor.getPopsinger() == modelpop) {
		// if (goodmodel.getLimitGrade() <= charactor.getGrade()) {
		// if (goodmodel.getPosition() == i) {
		// CharacterGoods cg = CharacterGoods.createMaxCharacterGoods(goodmodel.getId());
		// cg.setPosition((short) i);
		// characterGoodController.addGoodsToBag(cg);
		// break;
		// }
		// }
		// }
		// }
		// }
	}

	private void cmd4CreateItemLike(Hero charactor, String[] array) {
		int len = array.length;

		// 没有物品模型ID参数，视为无效命令
		if (len == 1) {
			return;
		}

		String goodModelName = array[1];
		// if (!goodModelId.matches("-?\\d{1,10}")) {
		// return;
		// }
		int amount = 1;
		int loopcount = 1;

		Goodmodel model = null;
		Collection<Goodmodel> col = GoodmodelManager.getInstance().getAlLGoodCollection();
		for (Iterator<Goodmodel> iterator = col.iterator(); iterator.hasNext();) {
			Goodmodel goodmodel = iterator.next();
			if (goodmodel.getName().contains(goodModelName)) {
				model = goodmodel;
				break;
			}
		}

		if (model == null) {
			return;
		}

		try {
			amount = Integer.parseInt(array[2]);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// try {
		// loopcount = Integer.parseInt(array[3]);
		// } catch (Exception e) {
		// logger.error(e.getMessage(),e);
		// }
		int bind = 0;
		if (array.length >= 4) {
			try {
				bind = Integer.parseInt(array[3]);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		CharacterGoods goods = CharacterGoods.createCharacterGoods(amount, model, loopcount, 0);
		if (bind > 0) {
			goods.setBind(CommonUseNumber.byte1);
		}

		int pinzhi = 0;
		if (array.length >= 5) {
			try {
				pinzhi = Integer.parseInt(array[4]);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		goods.randomGenerateExtraProp(0, 0, pinzhi);
		charactor.getCharacterGoodController().addGoodsToBag(goods);

	}

	/**
	 * 移动速度
	 * 
	 * @param charactor
	 * @param array
	 */
	private void cmd4MoveSpeed(Hero charactor, String[] array) {
		int moveSpeed = Integer.parseInt(array[1]);
		charactor.setMoveSpeed(charactor.getMoveSpeed() + moveSpeed);
		if (moveSpeed != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.movespeed, charactor.getPropertyAdditionController().getExtraMoveSpeed()));
		}
	}

	/**
	 * 清除技能cd时间
	 * 
	 * @param charactor
	 * @param array
	 */
	private void cdclear(Hero charactor, String[] array) {
		Collection<CharacterSkill> allSkill = charactor.getSkillManager().getAllCharacterSkill();
		for (Iterator<CharacterSkill> iterator = allSkill.iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = iterator.next();
			characterSkill.setStartcd(0l);
			charactor.getCharacterSkillManager().updateCharacterSkill(characterSkill);
		}
	}

	/**
	 * @param charactor
	 * @param array
	 */
	private void TAKE(Hero charactor, String[] array) {
		charactor.getPlayer().close();
	}

	private void cl(Hero charactor, String[] array) {
		Collection<CharacterGoods> col = charactor.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
		for (Iterator<CharacterGoods> iterator = col.iterator(); iterator.hasNext();) {
			CharacterGoods characterGoods2 = iterator.next();
			charactor.getCharacterGoodController().deleteCharacterGoods(characterGoods2);
		}
	}

	private void cmdReSetChesCount(Hero charactor, String[] array) {
		ChestCountManager.getInstance().reSetChestCountExchangeCount();
		ChestCountManager.getInstance().reSetChestCountExchangeCountSql();
		ChestCountManager.getInstance().reSetChestUseCount();
	}

	private void aq(Hero charactor, String[] array) {
		CharacterHiddenWeapon.create(1, 1, charactor);
	}

	private void cmd4SW(Hero charactor, String[] array) {
		Integer mpEdited = Integer.valueOf(1);

		// 没有参数，视为满内功命令
		if (array.length == 1) {
			mpEdited = MaxLimit.PRESTIGE_MAX;
		} else {
			// 有参数x，视为${当前内功}=${满内功}*(x%);

			// 正则检查
			if (!array[1].matches("-?\\d{1,3}")) {
				return;
			}

			int param = Integer.parseInt(array[1]);
			if (param > 100) {
				return;
			}

			float percent = (float) param / 100;
			int mp = (int) (percent * charactor.getMaxMp());
			if (mp <= 0) {
				mpEdited = 0;
			} else {
				mpEdited = mp;
			}
		}

		CharacterPropertyManager.changePrestige(charactor, mpEdited);
		// charactor.setNowMp(0);
		// CharacterPropertyManager.changeNowMp(charactor, mpEdited);
	}

	private void clearAllBuff(Hero charactor, String[] array) {
		charactor.getEffectController().clearAllEffectListAndRemoveBuffOnBody();
	}

	private void HORSEHL(Hero charactor, String[] array) {
		CharacterHorseController characterHorseController = charactor.getCharacterHorseController();
		Horse horse = characterHorseController.getShowHorse();
		if (horse != null) {
			// CharacterHorse characterHorse = horse.getCharacterHorse();
			// characterHorse.setLivingness(characterHorse.getLivingnessMax());
			int addlivingness = Integer.parseInt(array[1]);
			horse.addLivingness(addlivingness);
		} else {
			charactor.sendMsg(new PrompMsg(762));
		}
	}

	private void sethorseskillRemove(Hero charactor, String[] array) {// 骑乘的坐骑
		Integer skillId = Integer.valueOf(array[1]);
		Skill skill = SkillManager.getInstance().getCacheSkillMap().get(skillId.intValue());
		Horse horse = charactor.getCharacterHorseController().getShowHorse();
		if (horse != null && skill != null) {
			// ((HorseSkillManager)horse.getSkillManager())._removeSkill(skill);
		}

	}

	private void manxing(Hero charactor, String[] array) {
		Short position = Short.parseShort(array[1]);
		CharacterGoods characterGood = charactor.getCharacterGoodController().getGoodsByPositon(position);
		if (characterGood == null)
			return;
		if (characterGood.getGoodModel().isEquipment()) {
			characterGood.setStrengthenCount(GameConstant.StrengthenCountLimit);
			charactor.getCharacterGoodController().updateCharacterGoods(characterGood);
		}
	}

	private void upgradeAllSkill(Hero charactor, String[] array) {
		int grade = Integer.parseInt(array[1]);
		Collection<CharacterSkill> characterSkills = charactor.getSkillManager().getAllCharacterSkill();
		for (Iterator<CharacterSkill> iterator = characterSkills.iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = (CharacterSkill) iterator.next();
			if (characterSkill.getSkillId() == 50000)
				continue;
			((CharacterSkillManager) charactor.getCharacterSkillManager()).characterSkillUpgrade(characterSkill.getSkillId(), grade);
		}
	}

	private void learnAllSkill(Hero charactor, String[] array) {
		CharacterSkillManager characterSkillManager = ((CharacterSkillManager) charactor.getSkillManager());
		Collection<Skill> skills = SkillManager.getInstance().getCacheSkillMap().values();
		for (Iterator<Skill> iterator = skills.iterator(); iterator.hasNext();) {
			Skill skill2 = iterator.next();
			int msg = characterSkillManager.isLearnSkill(skill2);
			if (msg != 0) {
				continue;
			}
			if (skill2.getSkilltype() == 2) {
				characterSkillManager.create(skill2);
			}
		}
	}

	private void buff(Hero charactor, String[] array) {
		int buffId = Integer.parseInt(array[1]);
		SkillEffect skillEffect = SkillEffectManager.getInstance().getSkillEffectById(buffId);
		if (skillEffect == null)
			return;

		EffectInfo effectInfo = new EffectInfo(skillEffect);
		effectInfo.setAffecter(charactor);
		effectInfo.setAttacker(charactor);
		effectInfo.setRelevanceSkillId(buffId);
		try {
			charactor.getEffectController().addEffect(effectInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void cG(Hero charactor, String[] array) {
		charactor.getMyCharacterInstanceManager().getInstanceController().finishiInstance();
	}

	private void DropGoods(Hero charactor, String[] array) {
		int id = amountParamter(array);
		int jiacheng = Integer.parseInt(array[2]);
		if (jiacheng <= 1) {
			jiacheng = 50;
		}
		if (jiacheng > 1000) {
			jiacheng = 100;
		}
		int amount = Integer.parseInt(array[2]);
		if (amount < 1 || amount > 150) {
			amount = 50;
		}
		MonsterModel mm = MonsterModelManager.getInstance().getFromCache(id);
		for (int i = 0; i < amount; i++) {
			List<CharacterGoods> list = mm.dropGoods(charactor, 10000 * jiacheng);
			if (list.size() != 0) {
				for (CharacterGoods cg : list) {
					SceneDropGood sceneGoods = new SceneDropGood(cg, charactor);
					charactor.getSceneRef().enterScene(sceneGoods);
				}
			}
		}
	}

	private void sethorseskill(Hero charactor, String[] array) {
		Horse horse = charactor.getCharacterHorseController().getShowHorse();
		if (horse != null) {
			Integer skillId = Integer.valueOf(array[1]);
			CharacterSkill characterSkill = horse.getSkillManager().getCharacterSkillById(skillId);
			if (characterSkill != null && characterSkill.getSkillId() != 50000) {
				horse.getAotoAttackController().setDefaultSkill(characterSkill);
				horse.getCharacterHorse().setDefaultSkillId(characterSkill.getSkillId());
			} else {
				characterSkill = horse.getSkillManager().addSkill(skillId, horse.getSkillManager().getAllSkillCount());
				// CharacterHorse characterHorse = horse.getCharacterHorse();
				// characterHorse.setSavvySkills("" + skillId);
				if (characterSkill != null) {
					horse.getCharacterHorse().setDefaultSkillId(characterSkill.getSkillId());
					horse.getAotoAttackController().setDefaultSkill(characterSkill);
				}
			}
		}
	}

	private void cmd4BOSS(Hero charactor, String[] array) {
		Scene[] scenes = charactor.getVlineserver().getSceneManager().getWorldSceneList().toArray(new Scene[0]);
		for (Scene scene : scenes) {
			GameMap map = (GameMap) scene;
			map.getRefreshMonsterController().showBoss();
		}
	}

	private void allprop(Hero charactor, String[] array) {
		CharacterPropertyAdditionControllerImp propertyController = (CharacterPropertyAdditionControllerImp) charactor.getPropertyAdditionController();
		SPropertyEntity buff = propertyController.getBuff();
		SPropertyEntity chenghao = propertyController.getChenghao();
		SPropertyEntity jingmai = propertyController.getJingmai();
		SPropertyEntity xinfa = propertyController.getXinfa();
		SPropertyEntity zhengfa = propertyController.getZhengfa();
		SPropertyEntity zhuangbei = propertyController.getZhuangbei();
		SPropertyEntity zuoqi = propertyController.getZuoqi();
		SPropertyEntity zuoqiJineng = propertyController.getZuoqiJineng();
		SPropertyEntity bangpai = propertyController.getBangpai();
		SPropertyEntity shouhuyoulong = propertyController.getShouhuyoulong();
		SPropertyEntity taozhuang = propertyController.getTaoZhuang();
		SPropertyEntity chuzhanzuoqijineng = propertyController.getChuZhanZuoqiJineng();
		SPropertyEntity specialdrug = propertyController.getSpecialDrug();
		SPropertyEntity youlong = propertyController.getYoulong();
		SPropertyEntity anqi = propertyController.getAnqi();
		SPropertyEntity bow = propertyController.getBow();
		SPropertyEntity dantian = propertyController.getDantian();
		SPropertyEntity dugujiujian = propertyController.getDugujiujian();
		logger.info("buff: " + buff);
		logger.info("chenghao: " + chenghao);
		logger.info("jingmai: " + jingmai);
		logger.info("xinfa: " + xinfa);
		logger.info("zhengfa: " + zhengfa);
		logger.info("zhuangbei: " + zhuangbei);
		logger.info("zuoqi: " + zuoqi);
		logger.info("zuoqiJineng: " + zuoqiJineng);
		logger.info("bangpai: " + bangpai);
		logger.info("shouhuyoulong: " + shouhuyoulong);
		logger.info("taozhuang: " + taozhuang);

		logger.info("chuzhanzuoqijineng: " + chuzhanzuoqijineng);
		logger.info("specialdrug: " + specialdrug);
		logger.info("youlong: " + youlong);
		logger.info("anqi: " + anqi);
		logger.info("bow: " + bow);
		logger.info("dantian: " + dantian);
		logger.info("dugujiujian: " + dugujiujian);
	}

	private void cmd4Jiaozi(Hero charactor, String[] array) {
		Integer lijin = amountParamter(array);
		if (lijin == null) {
			return;
		}
		charactor.setJiaozi(0);
		CharacterPropertyManager.changeLijin(charactor, lijin);

	}

	private void cmd4HExp(Hero charactor, String[] array) {
		Integer exp = amountParamter(array);
		if (exp == null) {
			return;
		}
		try {
			Horse horse = charactor.getCharacterHorseController().getShowHorse();
			if (horse != null) {
				horse.horseGainedExp(exp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void jinyan(Hero charactor, String[] array) {
		if (array.length != 2) {
			return;
		}
		long time = Integer.parseInt(array[2]);
		time = System.currentTimeMillis() + time * 1000 * 60;
		charactor.setAllowchatStarttime(new Date(time));
		// charactor.getVlineserver().getChatSessionManager()
		// .sendGameToChatServerMsg(new ChatToGsMsg626(charactor));
	}

	private void paihang(Hero charactor, String[] array) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				RankingManager.getInstance().init();
			}
		});
	}

	private void lianzhan(Hero charactor, String[] array) {
		charactor.getContinuumKillSys().gmOperater();
	}

	private void killMonster(Hero charactor, String[] array) {
		Collection<SceneMonster> c = charactor.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		int x = charactor.getX();
		int y = charactor.getY();
		int juli = 100000;
		SceneMonster tsm = null;
		for (SceneMonster sm : c) {
			int tx = sm.getX();
			int ty = sm.getY();
			int temp = DistanceFormula.getDistanceRound(x, y, tx, ty);
			if (!sm.isDie() && temp < juli) {
				juli = temp;
				tsm = sm;
			}
		}
		if (tsm != null) {
			tsm.getEnmityManager().addEnmityValue(charactor, 10000);
			tsm.getEnmityManager().addHurtValue(charactor, tsm.getNowHp());
			tsm.setNowHp(0);
			FightMsgSender.directHurtBroadCase(null, tsm, 0, CommonUseNumber.byte0);
			tsm.die(charactor);
		}
	}

	private void cmd4FinishTask(Hero charactor, String[] array) {
		if (!"".equals(array[1])) {
			Integer taskId = Integer.parseInt(array[1]);
			short choosegoodsPos = 0;
			if (array.length > 2 && !"".equals(array[2])) {
				choosegoodsPos = Short.parseShort(array[2]);
			}
			int horseId = 0;
			if (array.length > 3 && !"".equals(array[3])) {
				horseId = Integer.parseInt(array[3]);
			}
			try {
				SystemPublishTask.finishTask(taskId, choosegoodsPos, horseId, charactor);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void cmd4SP(Hero charactor, String[] array) {
		Integer mpEdited = Integer.valueOf(1);

		// 没有参数，视为满内功命令
		if (array.length == 1) {
			mpEdited = charactor.getPropertyAdditionController().getExtraMaxSp();
		} else {
			// 有参数x，视为${当前内功}=${满内功}*(x%);

			// 正则检查
			if (!array[1].matches("-?\\d{1,3}")) {
				return;
			}

			int param = Integer.parseInt(array[1]);
			if (param > 100) {
				return;
			}

			float percent = (float) param / 100;
			int mp = (int) (percent * charactor.getPropertyAdditionController().getExtraMaxSp());
			if (mp <= 0) {
				mpEdited = 0;
			} else {
				mpEdited = mp;
			}
		}
		// charactor.setNowSp(mpEdited);
		CharacterPropertyManager.changeNowSp(charactor, mpEdited);

	}

	private void cmd4MaxMp(Hero charactor, String[] array) {
		Integer mpEdited = Integer.valueOf(array[1]);
		charactor.setMaxMp(charactor.getMaxMp() + mpEdited);

		if (mpEdited != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.maxMp, charactor.getPropertyAdditionController().getExtraMaxMp()));
		}
	}

	private void cmd4Attack(Hero charactor, String[] array) {
		int attack = Integer.parseInt(array[1]);
		charactor.setAttack(charactor.getAttack() + attack);
		if (attack != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.attack, charactor.getPropertyAdditionController().getExtraAttack()));
		}
	}

	private void cmd4Defence(Hero charactor, String[] array) {
		int defence = Integer.parseInt(array[1]);
		charactor.setDefence(charactor.getDefence() + defence);
		if (defence != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.defence, charactor.getPropertyAdditionController().getExtraDefend()));
		}
	}

	private void cmd4ZHENQI(Hero charactor, String[] array) {
		int zhenqi = 10000;
		if (array.length >= 2) {
			zhenqi = Integer.parseInt(array[1]);
		}
		CharacterPropertyManager.changeZhenqi(charactor, zhenqi);

	}

	private void ADDHORSE(Hero charactor, String[] array) {
		int horsemodelid = Integer.parseInt(array[1]);
		HorseCreator.createCharacterHorse(charactor, horsemodelid);
	}

	private void changeSkillUpgrade(Hero charactor, String[] array) {

		int skillId = Integer.parseInt(array[1]);
		if (skillId == 50000)
			return;
		int grade = Integer.parseInt(array[2]);
		((CharacterSkillManager) charactor.getCharacterSkillManager()).characterSkillUpgrade(skillId, grade);
	}

	private void changeSkillId(Hero character, String skillId) {
		if (skillId == null || "".equals(skillId)) {
			return;
		}
		CharacterSkill characterSkill = character.getCharacterSkillManager().getCharacterSkillMap().get(Integer.parseInt(skillId));
		if (characterSkill == null) {
			character.getCharacterSkillManager().create(SkillManager.getInstance().getSkillById(Integer.parseInt(skillId)));
		}
	}

	private void cmd4KillMID(Hero charactor, String[] array) {
		int mid = 0;
		if (array.length == 0) {
			VisibleObject vo = charactor.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
				mid = ((SceneMonster) vo).getModel();
			}
		} else {
			mid = Integer.parseInt(array[1]);
		}
		if (mid == 0) {
			return;
		}
		//
		for (SceneMonster vo : charactor.getSceneRef().getAllSceneMonster()) {
			if (vo.getModel() == mid) {
				characterKillMonster(charactor, vo);
			}
		}
	}

	private void characterKillMonster(Hero character, SceneMonster monster) {
		// monster.setFirstAttackPlayer(character);
		monster.getEnmityManager().addEnmityValue(character, 10);
		monster.getEnmityManager().addHurtValue(character, monster.getMaxHp());
		monster.setNowHp(0);

		try {
			FightMsgSender.directHurtBroadCase(character, monster, 0, CommonUseNumber.byte0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		monster.shock(character, null);
		// monster.die(character);
	}

	private void cmd4KillALL(Hero character, String[] array) {
		for (SceneMonster vo : character.getSceneRef().getAllSceneMonster()) {
			if (vo instanceof SceneBangqiMonster) {
				continue;
			}
			characterKillMonster(character, vo);
		}
	}

	private void cmd4KillView(Hero character, String[] array) {
		for (Object vo : character.getEyeShotManager().getEyeShortObjs(VisibleObject.SceneObjType_MonsterScene)) {
			if (vo instanceof SceneMonster) {
				characterKillMonster(character, (SceneMonster) vo);
			}
		}
	}

	private void cmd4SYSBC(Hero charactor, String array) {
		String content = array.substring(6);
		final PrompMsg msg = new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 1025, content);

		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {

			@Override
			public void run(Hero character) {
				character.sendMsg(msg);
			}
		});
	}

	private void cmd4BC(Hero character, String[] array) {
		if (array.length != 3) {
			return;
		}
		int type = Integer.parseInt(array[2]);
		if (type > 0 && type < 8) {
			String content = array[1];

			// String content = array.substring(6);
			final PrompMsg msg = new PrompMsg(type, 1025, content);

			GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {

				@Override
				public void run(Hero character) {
					character.sendMsg(msg);
				}
			});
		}

	}

	private void cmd4FCM(Hero charactor, String[] array) {
		long time = 3L * 60 * 60 * 1000;
		if (array != null && array.length > 1) {
			time = Long.parseLong(array[1]) * 60 * 60 * 1000;
		}
		charactor.setOnlinedate(time);
	}

	private void cinstance(Hero charactor, String[] array) {
		charactor.getMyInstanceDayStatManager().clearCount();
	}

	private void cmd4MaxHp(Hero charactor, String[] array) {
		Integer hpEdited = Integer.valueOf(array[1]);
		charactor.setMaxHp(charactor.getMaxHp() + hpEdited);

		if (hpEdited != 0) {
			charactor.getPropertyAdditionController().recompute();
			charactor.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(charactor, EffectType.maxHp, charactor.getPropertyAdditionController().getExtraMaxHp()));
		}
	}

	private void cmd4HP(Hero charactor, String[] array) throws Exception {
		Integer hpEdited = Integer.valueOf(1);

		// 没有参数，视为满血命令
		if (array.length == 1) {

			hpEdited = charactor.getPropertyAdditionController().getExtraMaxHp();
		} else {
			// 有参数x，视为${当前血量}=${满血量}*(x%);

			// 正则检查
			if (!array[1].matches("-?\\d{1,3}")) {
				return;
			}

			int param = Integer.parseInt(array[1]);
			if (param > 100) {
				return;
			}

			float percent = (float) param / 100;
			int hp = (int) (percent * charactor.getPropertyAdditionController().getExtraMaxHp());
			if (hp <= 0) {
				// 自取灭亡
				charactor.setNowHp(Integer.valueOf(0));
				charactor.setTarget(charactor);
				charactor.die(charactor);
				return;
			} else {

				hpEdited = hp;
			}
		}
		boolean isneedfuhuo = charactor.getNowHp() == 0;
		charactor.setNowHp(0);
		CharacterPropertyManager.changeNowHp(charactor, hpEdited);

		FightMsgSender.directHurtBroadCase(null, charactor, 0, CommonUseNumber.byte0);
		if (isneedfuhuo) {
			CharacterResurrect.fuhuo(charactor);
		}
	}

	private void cmd4MP(Hero charactor, String[] array) throws Exception {
		Integer mpEdited = Integer.valueOf(1);

		// 没有参数，视为满内功命令
		if (array.length == 1) {
			mpEdited = charactor.getPropertyAdditionController().getExtraMaxMp();
		} else {
			// 有参数x，视为${当前内功}=${满内功}*(x%);

			// 正则检查
			if (!array[1].matches("-?\\d{1,3}")) {
				return;
			}

			int param = Integer.parseInt(array[1]);
			if (param > 100) {
				return;
			}

			float percent = (float) param / 100;
			int mp = (int) (percent * charactor.getMaxMp());
			if (mp <= 0) {
				mpEdited = 0;
			} else {
				mpEdited = mp;
			}
		}
		charactor.setNowMp(0);
		CharacterPropertyManager.changeNowMp(charactor, mpEdited);
	}

	private void cmd4Gold(Hero charactor, String[] array) {
		Integer gold = amountParamter(array);
		if (gold == null) {
			return;
		}
		charactor.getAccount().getAccountMonneyManager().changeTradeAddYuanbao(charactor, gold);

	}

	@Deprecated
	private void cmd4Silver(Hero charactor, String[] array) {
		Integer silver = amountParamter(array);
		if (silver == null) {
			return;
		}
		// charactor.setIntegral(silver);

		CharacterPropertyResponse10108 response = new CharacterPropertyResponse10108(charactor);
		charactor.getPlayer().sendMsg(response);
	}

	private void cmd4Copper(Hero charactor, String[] array) {
		Integer copper = amountParamter(array);
		if (copper == null) {
			return;
		}
		charactor.setCopper(0);
		CharacterPropertyManager.changeCopper(charactor, copper, CopperAction.ADD_OTHER);
	}

	private Integer amountParamter(String[] array) {
		// 没有参数，视为无效命令
		if (array.length == 1) {
			return null;
		}

		// 正则检查
		// if (!array[1].matches("-?\\d{1,10}")) {
		// return null;
		// }

		if (!number.matcher(array[1]).matches()) {
			return null;
		}

		int param = Integer.parseInt(array[1]);
		Integer gold = param;

		if (gold.intValue() <= 0) {
			gold = 0;
		}
		return gold;
	}

	private void cmdMxZiZhuang(Hero charactor, String[] array) {
		int weizhi = 100;
		String[] aa = new String[5];
		aa[0] = array[0];
		aa[1] = array[1];
		aa[2] = "1";
		aa[3] = "0";
		aa[4] = "6";
		cmd4CreateItem(charactor, aa);
		String weizhi2[] = { "0", String.valueOf(weizhi) };
		manxing(charactor, weizhi2);
	}

	private void cmd4CreateItem(Hero charactor, String[] array) {
		int len = array.length;

		// 没有物品模型ID参数，视为无效命令
		if (len == 1) {
			return;
		}

		String goodModelId = array[1];
		if (!goodModelId.matches("-?\\d{1,10}")) {
			return;
		}
		int amount = 1;
		int loopcount = 1;
		Goodmodel model = GoodmodelManager.getInstance().get(Integer.valueOf(goodModelId));
		if (model == null) {
			return;
		}

		if (array.length > 2) {
			amount = Integer.parseInt(array[2]);
		}

		int bind = 0;
		if (array.length >= 4) {
			try {
				bind = Integer.parseInt(array[3]);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		CharacterGoods goods = CharacterGoods.createCharacterGoods(amount, model, loopcount, 0);
		if (bind > 0) {
			goods.setBind(CommonUseNumber.byte1);
		}

		int pinzhi = 0;
		if (array.length >= 5) {
			pinzhi = Integer.parseInt(array[4]);
		}
		goods.randomGenerateExtraProp(0, 0, pinzhi);
		charactor.getCharacterGoodController().addGoodsToBag(goods);
	}

	private void cmd4Exp(Hero character, String[] array) {
		Integer exp = amountParamter(array);
		if (exp == null) {
			return;
		}
		try {
			CharacterFormula.experienceProcess(character, exp.intValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void cmd4upgrade(Hero character, String[] array) {
		Integer grade = amountParamter(array);
		if (grade == null) {
			return;
		}
		character.setGrade(grade.shortValue());
		if (!CharacterFormula.characterGrowup(character, grade))
			return;
		character.setNowExperience(0);
		character.setNowHp(character.getPropertyAdditionController().getExtraMaxHp());
		character.setNowMp(character.getPropertyAdditionController().getExtraMaxMp());
		character.setNowSp(character.getPropertyAdditionController().getExtraMaxSp());
		// //广播
		character.getEyeShotManager().sendMsg(new UpgradeBordsResponse(character.getId()));
		// 发送给自己属性增加
		CharacterFormula.sendCharacterProperties(character);
		// 添加升级日志输出
		// 考虑到可能有一次升多级的情况，所以加在递归前调用

		character.getMyTeamManager().updateGradeUpMsgToTeamer();
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		character.getMyCharacterAchieveCountManger().getCharacterOtherCount().gradeCount(character.getGrade());
		// 发送日志
		character.getCharacterSkillManager().fuqiSkillReload();// 人物升级时检测夫妻的技能
		// RoleUpgradeDescManager.getInstance().upGradeMSG(character, 0l);
	}

	private void cmd4Tp(Hero character, String[] array) {
		if (array.length == 1) {
			return;
		}
		String param = array[1];
		if (!param.matches("\\d{1,10}\\-\\d{1,10}\\.\\d{1,10}")) {
			return;
		}

		String[] sc = param.split("-");
		int scene = Integer.parseInt(sc[0]);
		String[] xy = sc[1].split("\\.");
		short x = Short.parseShort(xy[0]);
		short y = Short.parseShort(xy[1]);
		try {
			ExchangeMapTrans.trans(scene, x, y, character);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}

	}

	private void cmd4Kill(Hero character, String[] array) {
		VisibleObject vo = character.getTarget();
		vo.setNowHp(0);
		try {
			FightMsgSender.directHurtBroadCase(character, vo, 0, CommonUseNumber.byte0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		vo.die(character);
	}

	public void cmd4Task(Hero character, String[] array) {
		CharacterTaskController taskController = character.getTaskController();
		int taskId = 0;
		if (array.length < 3) {
			return;
		}
		String taskidstr = array[2];
		if (taskidstr == null || taskidstr.length() == 0) {
			return;
		}
		GamePlayer session = character.getPlayer();
		taskId = Integer.parseInt(taskidstr);
		Task task = TaskManager.getInstance().getCacheTaskMap().get(taskId);
		if (array[1].matches("A")) {
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				// character.sendMsg(new
				// PrompMsg(TipMsg.MSGPOSITION_RIGHT,969,needJingangshi+"",xinyunNum+"",euipmentStrengthen.getZhenqi()+""));
				return;
			}
			taskController.addTask(task);
		} else if (array[1].matches("C")) {
			TaskRunResponse taskRunResponse = null;
			CharacterTask characterTask = taskController.getCurrTaskById(taskId);
			characterTask.fillFullTask(character);
			String action = characterTask.getTargetaction();
			String npc = characterTask.getTargetnpc();
			String area = characterTask.getTargetarea();
			String monster = characterTask.getTargetmonster();

			if (action != null && action.length() != 0) {
				taskRunResponse = new TaskRunResponse(characterTask.getTask(), (byte) 7, action);
				session.sendMsg(taskRunResponse);
			}
			if (npc != null && npc.length() != 0) {
				taskRunResponse = new TaskRunResponse(characterTask.getTask(), (byte) 2, npc);
				session.sendMsg(taskRunResponse);
			}
			if (area != null && area.length() != 0) {
				taskRunResponse = new TaskRunResponse(characterTask.getTask(), (byte) 4, area);
				session.sendMsg(taskRunResponse);
			}
			if (monster != null && monster.length() != 0) {
				taskRunResponse = new TaskRunResponse(characterTask.getTask(), CommonUseNumber.byte1, monster);
				session.sendMsg(taskRunResponse);
			}

			if (characterTask.getOrgTask().isLoopTask()) {
				TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(characterTask.getTaskConditionId());
				if (taskCondition != null) {
					int horseId = taskCondition.getHorseId();
					if (horseId > 0) {
						HorseCreator.createCharacterHorse(character, horseId);
					}
				}
			}

			taskController.updateTask_sql(characterTask);

		} else if (array[1].matches("Q")) {// 强制接受任务
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				return;
			}
			TaskActionFactory.getInstance().createAction(task, taskController).acceptForTest();
		} else if (array[1].matches("T")) {// 环任务条件
			int condition = Integer.parseInt(array[3]);
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				return;
			}

			if (!task.isLoopTask()) {
				character.sendMsg(new GetTaskResponse(0, 20003, 0));
				return;
			}

			TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(condition);

			if (taskCondition == null) {
				character.sendMsg(new GetTaskResponse(0, 20004, 0));
				return;
			}

			CharacterTask characterTask = taskController.getCurrTaskById(taskId);
			if (characterTask == null) {
				characterTask = CharacterTask.createTask(taskController, task);
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				taskController.insertTask_sql(characterTask);
				taskController.putCurrentTaskMap(characterTask);
			}

			taskController.removeTerminativeTaskFromMap(characterTask);
			characterTask.setTaskConditionId(taskCondition.getConditionId());
			((BaseLoopTaskAction) characterTask.getTaskVO()).randomNpc(characterTask);
			taskController.updateTask_sql(characterTask);
			character.sendMsg(new GetLoopTaskMsg20000(characterTask));
		} else if (array[1].matches("J")) {// 环任务奖励更换
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				return;
			}

			if (!task.isLoopTask()) {
				character.sendMsg(new GetTaskResponse(0, 20003, 0));
				return;
			}

			int reward = Integer.parseInt(array[3]);
			TaskReward taskReward = TaskRewardManager.getInstance().getTaskRewardById(reward);
			if (taskReward == null) {
				character.sendMsg(new GetTaskResponse(0, 20005, 0));
				return;
			}

			CharacterTask characterTask = taskController.getCurrTaskById(taskId);
			if (characterTask == null) {
				characterTask = CharacterTask.createTask(taskController, task);
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				taskController.insertTask_sql(characterTask);
				taskController.putCurrentTaskMap(characterTask);
			}

			taskController.removeTerminativeTaskFromMap(characterTask);

			characterTask.getTaskVO().taskRewardFillTask(characterTask, taskReward);

			taskController.updateTask_sql(characterTask);
			character.sendMsg(new GetLoopTaskMsg20000(characterTask));
		} else if (array[1].matches("TP")) {// 环任务奖励更换
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				return;
			}

			if (!task.isLoopTask()) {
				character.sendMsg(new GetTaskResponse(0, 20003, 0));
				return;
			}

			CharacterTask characterTask = taskController.getCurrTaskById(taskId);
			if (characterTask == null) {
				return;
			}

			int npcId = characterTask.getEndnpc();
			Npc npc = NpcManager.getInstance().get(npcId);
			try {
				ExchangeMapTrans.trans(npc.getSceneid(), npc.getX().shortValue(), npc.getY().shortValue(), character);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else if (array[1].matches("COM")) {// 环任务奖励更换
			if (task == null) {
				character.sendMsg(new GetTaskResponse(0, 20000, 0));
				return;
			}

			if (!task.isLoopTask()) {
				character.sendMsg(new GetTaskResponse(0, 20003, 0));
				return;
			}

			CharacterTask characterTask = taskController.getCurrTaskById(taskId);
			if (characterTask == null) {
				return;
			}

			characterTask.getTaskVO().compelteForTest();
		}
	}

	private boolean authentication(GamePlayer player) {
		Account acount = (Account) player.getAttribute("ACCOUNT");
		Hero character = player.getCurrentCharacter(Hero.class);
		if (character != null) {
			acount = character.getAccount();
		}
		if (acount.getGm() == 1) {
			return true;
		}
		return false;
	}

	private void cmd4Superman(Hero character, String[] array) {
		if (array.length == 1) {
			character.setAttack(3248);
			character.setDefence(1783);
			character.setDodge(421);
			character.setHit(1115);
			character.setCrt(222);
			character.setMaxHp(999999999);
			character.setNowHp(999999999);
			character.setNowMp(99999999);
			character.setMaxMp(99999999);
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.attack, character.getPropertyAdditionController().getExtraDefend()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.defence, character.getPropertyAdditionController().getExtraDefend()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.dodge, character.getPropertyAdditionController().getExtraDefend()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.mp, character.getPropertyAdditionController().getExtraMaxMp()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.hp, character.getPropertyAdditionController().getExtraMaxHp()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.crt, character.getPropertyAdditionController().getExtraCrt()));
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.hit, character.getPropertyAdditionController().getExtraHit()));
			character.getPropertyAdditionController().recompute();
		} else {
			if (!array[1].equals("C")) {
				return;
			}
		}
	}

	private void cmd4AFK(Hero character, String[] array) {
		if (array.length < 2) {
			return;
		}
		if (array[1].matches("ON")) {
			character.getCharacterOnHoorController().setAfkState(OnHoorState.on);
		} else if (array[1].matches("OFF")) {
			character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
		}
	}

	private void zhenlong(Hero character, String[] array) {
		character.getMyChannelManager().addChongXue_Gm_ZhenLong();
	}

	private void jingmai(Hero character, String[] array) {
		String jinmaiid = array[1];
		if (jinmaiid.length() < 3) {
			return;
		}
		int datong = character.getMyChannelManager().getDatongjinmai();
		if (datong == 8) {
			return;
		}
		if ("1000".equals(jinmaiid)) {
			for (int i = 100; i < 900; i++) {
				String a = String.valueOf(i + 1);
				String aa[] = { "", a };
				jingmai(character, aa);
				i = i + 100;
			}
		}
		int channelid = 0;
		if (StringUtil.isNotEmpty(jinmaiid)) {
			channelid = Integer.valueOf(jinmaiid);
		}

		String id = jinmaiid.substring(0, 1);
		List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
		String weizhijinmaiString = character.getMyChannelManager().getJiSuanTools().getJinmaiLieBiao();
		String weizhi[] = weizhijinmaiString.split(";");
		int jinmaiid2 = 0;
		for (int i = 0; i < weizhi.length; i++) {
			String[] string = weizhi[i].split(",");
			if (string[0].indexOf(id) != -1) {
				jinmaiid2 = Integer.valueOf(string[1]);
				break;
			}

		}
		if (jinmaiid2 == 0) {
			List<Channel> listChannel2 = new ArrayList<Channel>(listChannel);
			listChannel2.remove(0);
			for (Channel channel : listChannel2) {
				channelid = channelid + 1;
				Channel c = ChannelManager.getInstance().getCharactergradeMap().get(channel.getId());
				character.getMyChannelManager().addChongXueGm((short) channelid, CommonUseNumber.byte0, c);
			}
		} else {
			int count = character.getMyChannelManager().getJiSuanTools().getCountDaTongJinmai2(jinmaiid);
			if (count > 0) {
				return;
			}
			List<Channel> listChannel2 = new ArrayList<Channel>(listChannel);
			listChannel2.remove(0);
			for (Channel channel : listChannel2) {
				if (channel.getId().intValue() > jinmaiid2) {
					Channel c = ChannelManager.getInstance().getCharactergradeMap().get(channel.getId());
					character.getMyChannelManager().addChongXueGm((short) channel.getId().intValue(), CommonUseNumber.byte0, c);
				}
			}
		}

	}

	private void dujie(Hero hero, String[] array) {
		if (array.length != 3) {
			return;
		}
		if (!array[1].equals("E")) {
			return;
		}

		DujieCtrlor ctrlor = hero.getDujieCtrlor();
		ctrlor.getHeroDujieData().setNum(Integer.parseInt(array[2]));
		propAdd2Hero(hero, ctrlor.getHeroDujieData().getNum());
		ctrlor.finishDujie();

		int currentLvl = ctrlor.currentTianjieNum();
		int currentStat = ctrlor.currentTianjieStat();
		int currentProc = ctrlor.currentTianjieProc();

		DujieAllStateResp resp = new DujieAllStateResp();
		resp.writeAllState(currentLvl, currentStat, ctrlor.getHeroDujieData().getRoushen(), currentProc, ctrlor.getHeroDujieData().getIsguard());
		hero.sendMsg(resp);

	}

	private void roushen(Hero hero, String[] array) {
		int inc = Integer.parseInt(array[1]);
		ServerResponse response = hero.getDujieCtrlor().increaseRoushen(inc);
		if (response != null) {
			hero.sendMsg(response);
		}
	}

	private void cmd4Km(Hero hero, String[] array) {
		CharacterTaskController taskController = hero.getTaskController();
		taskController.taskRun(90003, (byte) 1, Integer.parseInt(array[1]));
	}

	private void cmd4Pet(Hero hero, String[] array) {
		int horseId = Integer.parseInt(array[1]);
		CharacterHorse characterHorse = HorseCreator.createCharacterHorse(hero, horseId, HorseStateConsts.REST);
		HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
		characterHorse.setNeidanStarttime(System.currentTimeMillis());
		characterHorse.setNeidanCdtime(horseModel.getNeidanCdtime() * 1000);
		characterHorse.setNeidanUsetime(0);
		// HorseContainer horsecontainer = hero.getCharacterHorseController().getBagHorseContainer();
		// horsecontainer.addHorse(characterHorse);
	}

	private void propAdd2Hero(Hero hero, int dujieLvl) {
		DujieAdd add = DujieAddDataTbl.getInstance().getDujieAdd();
		Dujie dujie = DujieDataTbl.getInstance().getDujie(dujieLvl);
		int popsigner = hero.getPopsinger();

		float hp = dujie.getAdd_hp() * add.getAddhpByPopsigner(popsigner);
		float mp = dujie.getAdd_mp() * add.getAddmpByPopsigner(popsigner);
		float atk = dujie.getAdd_attack() * add.getAddattackByPopsigner(popsigner);
		float dfn = dujie.getAdd_defence() * add.getAdddefenceByPopsigner(popsigner);
		float sp = dujie.getAdd_sp();
		float as = dujie.getAdd_as();
		float ms = dujie.getAdd_ms();
		float crt = dujie.getAdd_crt() * add.getAddcrtByPopsigner(popsigner);
		float dodge = dujie.getAdd_dodge() * add.getAdddefenceByPopsigner(popsigner);
		float hit = dujie.getAdd_hit() * add.getAddhitByPopsigner(popsigner);
		float exp = dujie.getAdd_exp();
		float copp = dujie.getAdd_copper();

		CharacterPropertyManager.changeMaxHp(hero, (int) hp);
		CharacterPropertyManager.changeMaxMp(hero, (int) mp);
		CharacterPropertyManager.changeMaxSp(hero, (int) sp);
		CharacterPropertyManager.changeAttack(hero, (int) atk);
		CharacterPropertyManager.changeDefence(hero, (int) dfn);
		CharacterPropertyManager.changeMoveSpeed(hero, (int) ms);
		CharacterPropertyManager.changeCrt(hero, (int) crt);
		CharacterPropertyManager.changeDodge(hero, (int) dodge);
		CharacterPropertyManager.changeAtkColdtime(hero, (int) as);
		CharacterPropertyManager.changeHit(hero, (int) hit);
		CharacterPropertyManager.changeCopper(hero, (int) copp, CopperAction.ADD_TASKORINSTANCE);
		CharacterFormula.experienceProcess(hero, (int) exp);
		hero.getPropertyAdditionController().recompute();

		int[] skills = dujie.getSkillUpdate(popsigner);
		if (skills == null) {
			return;
		}
		CharacterSkillManager csm = hero.getCharacterSkillManager();
		for (int i = 0; i < skills.length; i = i + 2) {
			CharacterSkill characterskill = csm.getCharacterSkillById(skills[i]);
			if (characterskill != null) {
				csm.characterSkillUpgrade(skills[i], characterskill.getGrade() + skills[i + 1]);
			}
		}

	}
}
