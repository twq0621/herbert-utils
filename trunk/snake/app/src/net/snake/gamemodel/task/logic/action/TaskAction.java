package net.snake.gamemodel.task.logic.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.Language;
import net.snake.consts.ClientConfig;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.guide.response.NewGuideTaskGoodMsg50666;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.response.DemissionResponse;
import net.snake.gamemodel.task.response.FinishTaskResponse;
import net.snake.gamemodel.task.response.GetTaskResponse;

/**
 * @author serv_dev
 * 
 */
public abstract class TaskAction implements Cloneable {
	private static final Logger logger = Logger.getLogger(TaskAction.class);
	protected Task task;
	protected CharacterTaskController taskController;

	public TaskAction(Task task, CharacterTaskController taskController) {
		this.task = task;
		this.taskController = taskController;
	}

	public void destroy() {
		task = null;
		taskController = null;
	}

	public Hero getCharacter() {
		return taskController.getCharacter();
	}

	protected final boolean isEnoughSpace(Collection<CharacterGoods> goodsCool, CharacterGoodController characterGoodController) {
		if (!characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(goodsCool)) {
			characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20027, 0));
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 是否过了时间，系统自动地放弃
	 * 
	 * @return
	 */
	public boolean isOutTime(CharacterTask characterTask) {
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public CharacterTaskController getTaskController() {
		return taskController;
	}

	public void setTaskController(CharacterTaskController taskController) {
		this.taskController = taskController;
	}

	public TaskAction() {
	}

	public void acceptForTest() {
	}

	public void compelteForTest() {
	}

	public boolean downRandomCondition(CharacterTask characterTask) {
		return false;
	}

	public boolean upRandomReward(CharacterTask characterTask) {
		return false;
	}

	public void taskRewardFillTask(CharacterTask characterTask, TaskReward taskRward) {
	}

	/**
	 * 接受任务时添加到包裹的物品
	 * 
	 * @return
	 */
	public Collection<CharacterGoods> getAcceptGoodsAddToBag() {
		Collection<CharacterGoods> goodCol = new ArrayList<CharacterGoods>();
		// 获得任务物品
		if (task.getTaskgoods() != null && !"".equals(task.getTaskgoods())) {
			int taskgoods = Integer.parseInt(task.getTaskgoods().split(":")[0]);// 任务品id
			Goodmodel goodModel = GoodmodelManager.getInstance().get(taskgoods);
			if (goodModel != null) {
				CharacterGoods tempgoods = new CharacterGoods();
				tempgoods.setGoodmodelId(taskgoods);
				tempgoods.setCount(1);
				tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
				goodCol.add(tempgoods);
			}
		}
		return goodCol;
	}

	/**
	 * 完成任务时添加到包裹的物品
	 * 
	 * @return
	 */
	public Collection<CharacterGoods> getCompleteGoodsAddToBag(CharacterTask characterTask, Hero character) {
		String taskAwardGoods = characterTask.getOrgTask().getGoods();// 奖励物品id
		// 添加任务奖励的物品
		Collection<CharacterGoods> characterGoodsCol = new ArrayList<CharacterGoods>();
		if (taskAwardGoods != null && taskAwardGoods.length() != 0) {
			String goods[] = taskAwardGoods.split(",");
			for (int i = 0; i < goods.length; i++) {
				String goodModel[] = goods[i].split("#");
				int goodmodelid = Integer.parseInt(goodModel[0]);
				int goodmodelnum = Integer.parseInt(goodModel[1]);
				int popsigner = Integer.parseInt(goodModel[2]);
				if (popsigner > 0 && popsigner != character.getPopsinger()) {
					continue;
				}
				CharacterGoods tempgoods = new CharacterGoods();
				tempgoods.setGoodmodelId(goodmodelid);
				tempgoods.setCount(goodmodelnum);
				tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
				characterGoodsCol.add(tempgoods);
			}
		}

		String rewrdGoods = characterTask.getRewardGoods();

		if (rewrdGoods != null && !"".equals(rewrdGoods)) {
			String[] goodStr = rewrdGoods.split(";");
			if (goodStr.length > 0) {
				for (int i = 0; i < goodStr.length; i++) {
					String[] _goodStrs = goodStr[i].split(",");
					if (_goodStrs.length <= 1)
						break;
					int goodId = Integer.parseInt(_goodStrs[0]);
					int goodNum = Integer.parseInt(_goodStrs[1]);
					CharacterGoods tempgoods = new CharacterGoods();
					tempgoods.setGoodmodelId(goodId);
					tempgoods.setCount(goodNum);
					tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
					characterGoodsCol.add(tempgoods);
				}
			}
		}

		return characterGoodsCol;
	}

	public boolean acceptCondition() {
		CharacterTask currCharacterTask = taskController.getCurrTaskById(task.getTaskId());
		if (currCharacterTask != null) {
			getCharacter().sendMsg(new GetTaskResponse(0, 20008, 0));
			return false;
		}

		// if (!getCharacter().getAntiAddictedSystem().getLimitTask()) {
		// getCharacter().sendMsg(new GetTaskResponse(0, 20009, 0));
		// return false;
		// }

		Date date1 = task.getAcceptBeginTime();
		if (date1 != null) {
			if (!"".equals(date1) && new Date().before(date1)) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20010, 0));
				return false;
			}
		}

		Date date2 = task.getCompleteEndTime();
		if (date2 != null) {
			if (!"".equals(date2) && new Date().after(date2)) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20011, 0));
				return false;
			}
		}

		int receptionLevel = task.getReceptionlevel();// 接受任务等级下限
		int receptionUpLevel = task.getReceptionuplevel();// 接受任务等级上限
		// 是否符合任务要求 等级
		if (receptionLevel != 0 && getCharacter().getGrade() < receptionLevel) {
			getCharacter().sendMsg(new GetTaskResponse(0, 20012, 0));
			return false;
		}
		if (receptionUpLevel != 0 && getCharacter().getGrade() > receptionUpLevel) {
			getCharacter().sendMsg(new GetTaskResponse(0, 20013, 0));
			return false;
		}

		int currtaskNum = taskController.getAllCurrentTasks().size();
		if (currtaskNum >= Task.TASKLIMIT_UP) {
			getCharacter().sendMsg(new GetTaskResponse(0, 20014, 0));
			return false;
		}

		if (!isEnoughSpace(getAcceptGoodsAddToBag(), getCharacter().getCharacterGoodController())) {
			return false;
		}

		return true;
	}

	public boolean dropCondition() {
		// 主线
		if (!task.getIslost()) {
			getCharacter().sendMsg(new DemissionResponse(20051, ""));
			return false;
		}
		return true;
	};

	public boolean completeCondition() {

		Date date1 = task.getAcceptBeginTime();
		if (date1 != null && !"".equals(date1)) {
			if (new Date().before(date1)) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20010, 0));
				return false;
			}
		}

		Date date2 = task.getCompleteEndTime();
		if (date2 != null && !"".equals(date2)) {
			if (new Date().after(date2)) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20011, 0));
				return false;
			}
		}

		CharacterTask characterTask = taskController.getCurrTaskById(task.getTaskId());

		int finishTaskLevel = task.getEndtasklevel();// 任务完成需要的等级
		// int taskFunType = task.getFunctiontype();
		// 验证结束任务等级
		if (getCharacter().getGrade() < finishTaskLevel) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20028, 0));
			return false;
		}
		// 验证是否超过时间限制
		if (!characterTask.validateLimitTime()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20029, 0));
			return false;
		}
		// if (taskFunType == Task.SEARCH_NPC || taskFunType == Task.FUHE) {
		// 验证 目标Npc
		if (!characterTask.validateNpc()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20030, 0));
			return false;
		}
		// }
		// if (taskFunType == Task.QUYU || taskFunType == Task.FUHE) {
		// 验证 目标区域
		if (!characterTask.validateArea()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20031, 0));
			return false;
		}
		// }
		// if (taskFunType == Task.KILL_MONSTER || taskFunType == Task.FUHE) {
		// 验证 目标怪
		if (!characterTask.validateMonster()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20032, 0));
			return false;
		}
		// }
		// if (taskFunType == Task.DONGZUO || taskFunType == Task.FUHE) {
		if (!characterTask.validateAction()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20033, 0));
			return false;
		}

		String targetMountUpGrade = getTask().getTargetmountupgrade();
		if (targetMountUpGrade != null && !"".equals(targetMountUpGrade) && Integer.parseInt(targetMountUpGrade) == 1) {// 如果需要进阶的判断
			if (getCharacter().getMyCharacterAchieveManger().getFinishAchieve(ClientConfig.HorseDiLuToNiuAchieveId) == null) {
				getCharacter().sendMsg(new FinishTaskResponse(0, 20034, 0));
				return false;
			}
		}

		if (!characterTask.validateTargetShopping()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20035, 0));
			return false;
		}

		if (!characterTask.validateRecharge()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20036, 0));
			return false;
		}

		if (!characterTask.validateTargetAllSkillLv()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20037, 0));
			return false;
		}

		if (!characterTask.validateTargetBuff()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20038, 0));
			return false;
		}

		if (!characterTask.validateTargetChannel()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20039, 0));
			return false;
		}

		if (!characterTask.validateTargetPoint()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20040, 0));
			return false;
		}

		if (!characterTask.validateTargetGroup()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20041, 0));
			return false;
		}

		if (!characterTask.validateTargetFriend()) {
			getCharacter().sendMsg(new FinishTaskResponse(0, 20042, 0));
			return false;
		}

		int targetEquipCond = characterTask.validateTargetEquip();
		if (targetEquipCond > 0) {
			if (targetEquipCond == 1) {
				getCharacter().sendMsg(new FinishTaskResponse(0, 20043, 0));
				return false;
			} else if (targetEquipCond == 2) {
				getCharacter().sendMsg(new FinishTaskResponse(0, 20044, 0));
				return false;
			}
		}

		int targetSkillCond = characterTask.validateTargetSkillLv();
		if (targetSkillCond > 0) {
			if (targetSkillCond == 1) {
				getCharacter().sendMsg(new FinishTaskResponse(0, 20045, 0));
				return false;
			} else if (targetSkillCond == 2) {
				getCharacter().sendMsg(new FinishTaskResponse(0, 20046, 0));
				return false;
			}
		}

		// }

		// if (getTask().getNeedwuqi() > 0) {
		// Goodmodel wuqi =
		// GoodmodelManager.getInstance().get(getTask().getNeedwuqi());
		// if (wuqi == null)
		// {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"找不到配置的武器",0));
		// return false;
		// }
		//
		// CharacterGoods characterGoods =
		// getCharacter().getCharacterGoodController().getGoodsByPositon(ClientConfig.POSTION_WUQI);
		// if (characterGoods == null) {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"没有佩戴武器",0));
		// return false;
		// }
		//
		// if (characterGoods.getGoodmodelId().intValue() != wuqi.getId()) {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"没有佩戴符合的武器",0));
		// return false;
		// }
		// }
		//
		// if (getTask().getNeeddress() > 0) {
		// Goodmodel dress =
		// GoodmodelManager.getInstance().get(getTask().getNeedwuqi());
		// if (dress == null)
		// {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"找不到配置的衣服",0));
		// return false;
		// }
		// CharacterGoods characterGoods =
		// getCharacter().getCharacterGoodController().getGoodsByPositon(ClientConfig.POSTION_YIFU);
		// if (characterGoods == null) {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"没有佩戴衣服",0));
		// return false;
		// }
		//
		// if (characterGoods.getGoodmodelId().intValue() != dress.getId()) {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"没有佩戴符合的衣服",0));
		// return false;
		// }
		//
		// }
		//
		// if (getTask().getNeedskill() > 0) {
		// Skill skill =
		// SkillManager.getInstance().getSkillById(getTask().getNeedskill());
		// if (skill == null) {
		// getCharacter().sendMsg(new FinishTaskResponse(0,"找不到配置的技能",0));
		// return false;
		// }
		//
		// CharacterSkill characterSkill =
		// getCharacter().getSkillManager().getCharacterSkillById(skill.getId());
		// if (characterSkill == null) {
		// getCharacter().sendMsg(new
		// FinishTaskResponse(0,"未学习武功 "+skill.getName()+" 技能",0));
		// return false;
		// }
		//
		// if (characterSkill.getGrade() < getTask().getNeedwugonggrade()) {
		// getCharacter().sendMsg(new
		// FinishTaskResponse(0,"武功 "+skill.getName()+" 层数" +
		// getTask().getNeedwugonggrade() + "限定",0));
		// return false;
		// }
		// }

		if (!isEnoughSpace(getCompleteGoodsAddToBag(characterTask, getCharacter()), getCharacter().getCharacterGoodController())) {
			return false;
		}

		// 验证回收物品
		if (!characterTask.validateGoodsCondition(getCharacter().getCharacterGoodController())) {
			return false;
		}

		return true;
	}

	/**
	 * 完成任务时统一的赏罚
	 */
	public void completeRewardsPunishments(CharacterTask characterTask) {
		// 奖励 经验 金币 铜币 好感值 帮派贡献 善恶值 称号

		/**
		 * 
		 * 金币 铜币 真气 奖励一个BUFF 战场声望值
		 * 
		 * 
		 * 个人帮贡值 奖励一个称号 奖励一个系统全服公告
		 */
		StringBuilder msg = new StringBuilder();
		if (task.getExperience() > 0) {
			msg.append(Language.EXP + ":").append(task.getExperience() + ",");
			try {
				CharacterFormula.experienceProcess(getCharacter(), task.getExperience());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		// TODO
		if (task.getCopper() > 0) {
			CharacterPropertyManager.changeCopper(getCharacter(), task.getCopper(), CopperAction.ADD_TASKORINSTANCE);
			msg.append(Language.COPPER + ":").append(task.getCopper() + ",");
		}
		if (task.getGold() > 0) {
			CharacterPropertyManager.changeLijin(getCharacter(), task.getGold());
			msg.append(Language.LIJIN + ":").append(task.getGold() + ",");
		}
		if (task.getZhenqi() > 0) {
			CharacterPropertyManager.changeZhenqi(getCharacter(), task.getZhenqi());
			msg.append(Language.ZHENQI + ":").append(task.getZhenqi() + ",");
		}
		if (task.getWarrepute() > 0) {
			CharacterPropertyManager.changePrestige(getCharacter(), task.getWarrepute());
			msg.append(Language.WAR_PRESTIGE + ":").append(task.getWarrepute() + ",");
		}
		if (task.getBuffid() > 0) {
			SkillEffect skillEffect = SkillEffectManager.getInstance().getCacheSkillEffect().get(task.getBuffid());
			if (skillEffect != null) {
				EffectInfo effectInfo = new EffectInfo(skillEffect);
				effectInfo.setAttacker(getCharacter());
				effectInfo.setAffecter(getCharacter());
				try {
					getCharacter().getEffectController().addEffect(effectInfo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		if (task.getHiddenweapon() > 0) {
			CharacterHiddenWeapon chw = CharacterHiddenWeapon.create(task.getHiddenweapon(), getCharacter());
			if (chw != null) {
				msg.append(Language.HID_WEAPON + ":").append(chw.getHiddenWeapons().getNameI18n() + ",");
			}
		}
		if (task.getBow() != null && task.getBow() == 1) {
			// 获取弓
			getCharacter().getBowController().acquisitionBow();
		}
		if (task.getDantian() != null && task.getDantian() == 1) {
			// 获取丹田
			getCharacter().getDanTianController().acquisitionDanTian();
		}

		CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();

		String taskAwardGoods = characterTask.getOrgTask().getGoods();// 奖励物品id
		// 添加任务奖励的物品
		CharacterGoods equipGood = null;
		List<CharacterGoods> tmpCG = new ArrayList<CharacterGoods>();
		if (taskAwardGoods != null && taskAwardGoods.length() != 0) {
			String goods[] = taskAwardGoods.split(",");
			for (int i = 0; i < goods.length; i++) {
				String goodModelStr[] = goods[i].split("#");
				int goodmodelid = Integer.parseInt(goodModelStr[0]);
				int goodmodelnum = Integer.parseInt(goodModelStr[1]);
				int menpai = Integer.parseInt(goodModelStr[2]);
				if (menpai > 0 && menpai != getCharacter().getPopsinger()) {
					continue;
				}
				CharacterGoods tempgoods = new CharacterGoods();
				tempgoods.setGoodmodelId(goodmodelid);
				tempgoods.setCount(goodmodelnum);
				tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定

				if (characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(tempgoods)) {
					Goodmodel goodModel = GoodmodelManager.getInstance().get(goodmodelid);
					CharacterGoods characterGoods = CharacterGoods.createCharacterGoods(goodmodelnum, goodModel, 0, 0);

					characterGoods.setBind(CommonUseNumber.byte1);
					if (goodModel.isEquipment()) {
						CharacterGoods _characterGoods = characterGoodController.getBagGoodsContiner().addAndReturnLast(characterGoods);
						if (_characterGoods != null) {
							equipGood = _characterGoods;
						}
					} else {
						characterGoodController.getBagGoodsContiner().addGoods(characterGoods);
						msg.append(Language.GOODS + ":").append(goodModel.getNameI18n() + ",");
					}

					tmpCG.add(characterGoods);
				}
			}
			if (!tmpCG.isEmpty() && getTask().getEndnpc() != null) {
				getCharacter().sendMsg(new GoodToBadEffectMsg11170(tmpCG, getTask().getEndnpc()));
			}

		}
		if (equipGood != null) {
			getCharacter().sendMsg(new NewGuideTaskGoodMsg50666(equipGood));// 新手导航使用
		}

		/*
		 * 任务交付后，在系统消息聊天频道发送提示： “恭喜您完成任务：XXXX（任务名），获得：（任务收益显示，例如经验：+xxx，真气+xxx，铜钱+xxx）”
		 */

		if (!"".equals(msg.toString())) {
			getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1101, getTask().getNameI18n(), msg.deleteCharAt(msg.length() - 1)));
			// msg.insert(0, "恭喜您完成任务:" + getTask().getName() + ",获得");
		} else {
			getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1102, getTask().getNameI18n()));
			// msg.insert(0, "恭喜您完成任务:" + getTask().getName());
		}

		// getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,982,
		// msg.toString()));

		characterTask.completeRewardGood(getCharacter());
	}

	/**
	 * 接受任务
	 * 
	 * @param controller
	 */
	public abstract void accept();

	/**
	 * 完成任务
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public abstract void complete() throws Exception;

	/**
	 * 放弃任务
	 * 
	 * @param controller
	 */
	public abstract void drop();

	/**
	 * 自动回收任务 环任务重新计环
	 */
	public void autoRecover() {

	}
}
