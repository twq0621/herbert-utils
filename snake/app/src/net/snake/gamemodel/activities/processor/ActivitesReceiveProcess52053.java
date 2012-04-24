package net.snake.gamemodel.activities.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Position;
import net.snake.gamemodel.activities.bean.Activities;
import net.snake.gamemodel.activities.bean.XianshiActivityReward;
import net.snake.gamemodel.activities.persistence.ActivitiesManager;
import net.snake.gamemodel.activities.persistence.XianshiActivityRewardManager;
import net.snake.gamemodel.activities.response.ActivitesResponse52054;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

@MsgCodeAnn(msgcode = 52053, accessLimit = 2000)
public class ActivitesReceiveProcess52053 extends CharacterMsgProcessor implements IThreadProcessor {
	private static final Logger logger = Logger.getLogger(ActivitesReceiveProcess52053.class);
	// 7020 成长大礼包
	// 7021 豪华大礼包
	private int type2 = 102; // 角色道具兑换礼金统计
	private int type3 = 103; // 角色道具兑换道具统计

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int activiteId = request.getInt();
		logger.info("activiteId=" + activiteId);
		int item = request.getInt();
		int goodId = request.getInt();
		if (item <= 0) {
			return;
		}
		XianshiActivityReward xar = XianshiActivityRewardManager.getInstance().getXianshiActivityRewardById(item);
		if (xar == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19000));
			return;
		}
		if (!xar.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19000));
			return;
		}
		int type = xar.getType();
		if (type == 1) {
			lingquType1Reward(character, xar, goodId);
		} else if (type == 2) {
			lingquType2Reward(character, xar, goodId);
		} else if (type == 3) {
			lingquType3Reward(character, xar, goodId);
		} else if (type == 4) {
			lingquType4Reward(character, xar, goodId);
		} else if (type == 5) {
			lingquType5Reward(character, xar, goodId);
		} else if (type == 6) {
			lingquType6Reward(character, xar, goodId);
		} else if (type == 7) {
			lingquType7Reward(character, xar, goodId);
		} else if (type == 8) {
			lingquType8Reward(character, xar, goodId);
		} else if (type == 9) {
			lingquType9Reward(character, xar, goodId);
		} else if (type == 11) {
			lingquType11Reward(character, xar, goodId);
		} else if (type == 12) {
			lingquType12Reward(character, xar, goodId);
		} else if (type == 13) {
			lingquType13Reward(character, xar, goodId);
		} else if (type == 14) {
			lingquType14Reward(character, xar, goodId);
		} else if (type == 15) {
			lingquType15Reward(character, xar, goodId);
		}
	}

	/**
	 * 丹田条件限制类型15 道具兑换
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType15Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		int zise = 0;
		DanTian dantian = character.getDanTianController().getDanTian();
		if (dantian != null) {
			zise = dantian.getModel().getId();
		}
		if (zise >= 8) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1366));
			return;
		}
		if (zise <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1365));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		Activities activits = null;
		if (type_List.size() > 0) {
			activits = type_List.get(0);
		}
		int lingquCount = 0;
		if (activits != null) {
			lingquCount = activits.getCount();
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lingquCount < lqcInM) {
			return;
		}
		if (lingquCount >= xar.getRewardGoodMaxCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19025, xar.getRewardGoodMaxCount() + "", lingquCount + "", 0 + ""));
			return;
		}
		final int lingquTemp = lingquCount;
		final Activities activitsLog = activits;
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int countH = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (countH > lingquTemp) {
					return;
				}
				List<CharacterGoods> deleteList = xar.getGoodLimiteList();
				for (CharacterGoods cgDelete : deleteList) {
					if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(cgDelete.getGoodmodelId()) < cgDelete.getCount()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1381));
						return;
					}
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods add = null;
				try {
					add = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (add == null) {
					return;
				}
				add.setBind(CommonUseNumber.byte1);
				List<CharacterGoods> addList = new ArrayList<CharacterGoods>();
				addList.add(add);
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(deleteList, addList);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19006));
					return;
				}
				try {
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, add));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				int lingquCount = 0;
				if (activitsLog == null) {
					final Activities activities = new Activities();
					activities.setAccountId(character.getAccountId());
					activities.setType(xar.getId());
					activities.setCount(1);
					activities.setChongzhiqian(-1f);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().addActivities(activities);
						}
					});
					lingquCount = 1;
					character.getCharacterActivitesTempInfoManager().addActivities(activities);
				} else {
					activitsLog.setCount(activitsLog.getCount() + 1);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().updateCount(activitsLog);
						}
					});
					lingquCount = activitsLog.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(activitsLog);
				}
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19025, xar.getRewardGoodMaxCount() + "", lingquCount + "", (xar.getRewardGoodMaxCount() - lingquCount)
						+ ""));
				character.getCharacterCountController().count(type3, 1);
			}
		});
	}

	/**
	 * 充值活动 组合活动
	 */
	public void lingquType9Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		Activities activits = null;
		if (type_List.size() > 0) {
			activits = type_List.get(0);
		}
		int lingquCount = 0;
		if (activits != null) {
			lingquCount = activits.getCount();
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lingquCount < lqcInM) {
			return;
		}
		int lingqu_max_count = xar.getRewardGoodMaxCount();
		if (lingquCount >= lingqu_max_count) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19001));
			return;
		}
		float huodongchongzhi = 0;//TODO RechangeLogManager.getInstance().sumRechargeMonneyByTimeAndAccountId(character.getAccountId(), xar.getStartDate(), xar.getEndDate());
		int allCount = (int) (huodongchongzhi / xar.getMonneyLimit());
		if (allCount > lingqu_max_count) {
			allCount = lingqu_max_count;
		}
		int mayCountLinshi = allCount - lingquCount;
		if (mayCountLinshi < 1) {
			CharacterGoods linshi = xar.getMyRewardCg(goodId);
			if (linshi == null) {
				return;
			}
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19002, lingqu_max_count * linshi.getCount(), allCount * linshi.getCount()));
			return;
		}
		final int mayCount = 1;
		final int lingquTemp = lingquCount;
		final Activities activitsLog = activits;
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > lingquTemp) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				reward.setCount(reward.getCount() * mayCount);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "背包没有足够的空间存放奖励物品,请先清理背包"));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "背包没有足够的空间存放奖励物品,请先清理背包"));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				int lingquCount = 0;
				if (activitsLog == null) {
					final Activities activities = new Activities();
					activities.setAccountId(character.getAccountId());
					activities.setType(xar.getId());
					activities.setCount(mayCount);
					activities.setChongzhiqian(-1f);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().addActivities(activities);
						}
					});
					lingquCount = mayCount;
					character.getCharacterActivitesTempInfoManager().addActivities(activities);
				} else {
					activitsLog.setCount(activitsLog.getCount() + mayCount);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().updateCount(activitsLog);
						}
					});
					lingquCount = activitsLog.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(activitsLog);
				}
				int manCountNum = xar.getRewardGoodMaxCount() * temp.getCount() - lingquCount * temp.getCount();
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19013, xar.getRewardGoodMaxCount() * temp.getCount(), lingquCount * temp.getCount(), manCountNum));
				character.sendMsg(new ActivitesResponse52054(xar, goodId));

			}
		});
	}

	/**
	 * 充值活动
	 */
	public void lingquType1Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		Activities activits = null;
		if (type_List.size() > 0) {
			activits = type_List.get(0);
		}
		int lingquCount = 0;
		if (activits != null) {
			lingquCount = activits.getCount();
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lingquCount < lqcInM) {
			return;
		}
		int lingqu_max_count = xar.getRewardGoodMaxCount();
		if (lingquCount >= lingqu_max_count) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19001));
			return;
		}
		float huodongchongzhi = 0; //TODO //RechangeLogManager.getInstance().sumRechargeMonneyByTimeAndAccountId(character.getAccountId(), xar.getStartDate(), xar.getEndDate());
		int allCount = (int) (huodongchongzhi / xar.getMonneyLimit());
		if (allCount > lingqu_max_count) {
			allCount = lingqu_max_count;
		}
		final int mayCount = allCount - lingquCount;
		if (mayCount < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19015, lingqu_max_count, allCount));
			return;
		}
		final int lingquTemp = lingquCount;
		final Activities activitsLog = activits;
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > lingquTemp) {
					return;
				}
				List<CharacterGoods> rewardList = xar.getMyAllListReward(mayCount);

				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(rewardList)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(rewardList);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, rewardList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				int lingquCount = 0;
				if (activitsLog == null) {
					final Activities activities = new Activities();
					activities.setAccountId(character.getAccountId());
					activities.setType(xar.getId());
					activities.setCount(mayCount);
					activities.setChongzhiqian(-1f);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().addActivities(activities);
						}
					});
					lingquCount = mayCount;
					character.getCharacterActivitesTempInfoManager().addActivities(activities);
				} else {
					activitsLog.setCount(activitsLog.getCount() + mayCount);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().updateCount(activitsLog);
						}
					});
					lingquCount = activitsLog.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(activitsLog);
				}
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19015, xar.getRewardGoodMaxCount(), lingquCount));
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
			}
		});
	}

	/**
	 * 活动类型2 礼金兑换
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType2Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		Activities temp = null;
		if (type_List.size() > 0) {
			temp = type_List.get(0);
		}
		if (temp != null && temp.getCount() >= xar.getRewardGoodMaxCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19031, xar.getRewardGoodMaxCount() + "", xar.getRewardGoodMaxCount() + ""));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM >= xar.getRewardGoodMaxCount()) {
			return;
		}
		final Activities tempLog = temp;
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount >= xar.getRewardGoodMaxCount()) {
					return;
				}
				List<CharacterGoods> deleteList = xar.getGoodLimiteList();
				for (CharacterGoods cgDelete : deleteList) {
					if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(cgDelete.getGoodmodelId()) < cgDelete.getCount()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1381));
						return;
					}
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(deleteList, new ArrayList<CharacterGoods>());
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1381));
					return;
				}
				CharacterPropertyManager.changeLijin(character, xar.getRewardLijin());
				int lingquCount = 0;
				if (tempLog == null) {
					final Activities activities = new Activities();
					activities.setAccountId(character.getAccountId());
					activities.setType(xar.getId());
					activities.setCount(1);
					activities.setChongzhiqian(-1f);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().addActivities(activities);
						}
					});
					lingquCount = activities.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(activities);
				} else {
					tempLog.setCount(tempLog.getCount() + 1);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().updateCount(tempLog);
						}
					});
					lingquCount = tempLog.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(tempLog);
				}
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19031, xar.getRewardGoodMaxCount() + "", lingquCount + ""));
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.getCharacterCountController().count(type2, 1);
			}
		});

	}

	/**
	 * 活动类型3 道具兑换
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType3Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {

		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		Activities activits = null;
		if (type_List.size() > 0) {
			activits = type_List.get(0);
		}
		int lingquCount = 0;
		if (activits != null) {
			lingquCount = activits.getCount();
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lingquCount < lqcInM) {
			return;
		}
		if (lingquCount >= xar.getRewardGoodMaxCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19025, xar.getRewardGoodMaxCount() + "", lingquCount + "", 0 + ""));
			return;
		}
		final int lingquTemp = lingquCount;
		final Activities activitsLog = activits;
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int countH = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (countH > lingquTemp) {
					return;
				}
				List<CharacterGoods> deleteList = xar.getGoodLimiteList();
				for (CharacterGoods cgDelete : deleteList) {
					if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(cgDelete.getGoodmodelId()) < cgDelete.getCount()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1381));
						return;
					}
				}
				List<CharacterGoods> addList = xar.getMyAllListReward(1);
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(deleteList, addList);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19006));
					return;
				}
				try {
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, addList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				int lingquCount = 0;
				if (activitsLog == null) {
					final Activities activities = new Activities();
					activities.setAccountId(character.getAccountId());
					activities.setType(xar.getId());
					activities.setCount(1);
					activities.setChongzhiqian(-1f);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().addActivities(activities);
						}
					});
					lingquCount = 1;
					character.getCharacterActivitesTempInfoManager().addActivities(activities);
				} else {
					activitsLog.setCount(activitsLog.getCount() + 1);
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							ActivitiesManager.getInstance().updateCount(activitsLog);
						}
					});
					lingquCount = activitsLog.getCount();
					character.getCharacterActivitesTempInfoManager().addActivities(activitsLog);
				}
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				if (xar.getRewardGoodMaxCount() < 10000) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19025, xar.getRewardGoodMaxCount() + "", lingquCount + "",
							(xar.getRewardGoodMaxCount() - lingquCount) + ""));
				} else {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19090));
				}
				character.getCharacterCountController().count(type3, 1);
			}
		});
	}

	/**
	 * 活动类型4 静脉贯通
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType4Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		if (character.getChannelXuewei() < 344) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19007));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * 活动类型5静脉贯通
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType5Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		int zise = getZiseEquipment(character);
		if (zise < 12) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19010));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * 活动类型6 装备满星
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType6Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		int zise = getManxingEquipment(character);
		if (zise < 12) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19010));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * 活动类型7 装备满融
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType7Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		int zise = getManLongEquipment(character);
		if (zise < 12) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19010));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * @param character
	 * @return
	 */
	private int getManLongEquipment(Hero character) {
		Collection<CharacterGoods> collection = character.getCharacterGoodController().getBodyGoodsList();
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.isManxingGems() && cg.getPosition() != Position.POSTION_TESHU) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @param character
	 * @return
	 */
	private int getManxingEquipment(Hero character) {
		Collection<CharacterGoods> collection = character.getCharacterGoodController().getBodyGoodsList();
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.isAllStar() && cg.getPosition() != Position.POSTION_TESHU) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 活动类型5vip月卡
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType8Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		boolean isVip = character.getMyCharacterVipManger().isVipYueka();
		if (!isVip) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19011));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					List<CharacterGoods> reList = new ArrayList<CharacterGoods>();
					reList.add(reward);
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});

	}

	/**
	 * 活动类型12 丹田
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType12Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		DanTian dantian = character.getDanTianController().getDanTian();
		int zise = 0;
		if (dantian != null) {
			zise = dantian.getModel().getId();
		}
		if (zise < 8) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1360));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reward));
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * 活动类型11 弓箭
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType11Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		if (!character.getBowController().isMaxBow()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19014));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				CharacterGoods temp = xar.getMyRewardCg(goodId);
				CharacterGoods reward = null;
				try {
					reward = (CharacterGoods) temp.clone();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
				if (reward == null) {
					return;
				}
				reward.setBind(CommonUseNumber.byte1);
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(reward)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(reward);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, reward));
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});
	}

	/**
	 * 活动类型5vip月卡 随机领取奖励
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType13Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		boolean isVip = character.getMyCharacterVipManger().isVipYueka();
		if (!isVip) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19011));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				List<CharacterGoods> rewardList = xar.getRandomRewardList();
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(rewardList)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(rewardList);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, rewardList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});

	}

	/**
	 * 活动类型14坐骑领取奖励
	 * 
	 * @param character
	 * @param type
	 */
	private void lingquType14Reward(final Hero character, final XianshiActivityReward xar, final int goodId) {
		Horse horse = character.getMaxBagPinjieHorse();
		int maxKind = 0;
		if (horse != null) {
			if (maxKind < horse.getKind()) {
				maxKind = horse.getKind();
			}
		}
		Horse storagehorse = character.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxKind < storagehorse.getKind()) {
				maxKind = storagehorse.getKind();
				horse = storagehorse;
			}
		}
		if (xar.getHorseLimit() == null) {
			return;
		}
		int lingquMaxKind = xar.getHorseLimit();
		if (maxKind != lingquMaxKind) {
			List<HorseModel> hmList = HorseModelDataProvider.getInstance().getHorseModelListByTypeName(lingquMaxKind);
			String hName = hmList.get(0).getNameI18n();
			int last = hName.indexOf("(");
			if (last == -1) {
				last = hName.length();
			}
			hName = hName.substring(0, last);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19016, hName));
			return;
		}
		List<Activities> type_List = ActivitiesManager.getInstance().getActivities(character.getAccountId(), xar.getId());
		if (type_List.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19005));
			return;
		}
		int lqcInM = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
		if (lqcInM > 0) {
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				int cCount = character.getCharacterActivitesTempInfoManager().getCountByType(xar.getId());
				if (cCount > 0) {
					return;
				}
				List<CharacterGoods> rewardList = xar.getMyAllListReward(1);
				for (CharacterGoods cg : rewardList) {
					cg.setBind(CommonUseNumber.byte1);
				}
				if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(rewardList)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(rewardList);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19008));
					return;
				}
				try {
					character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, rewardList));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				final Activities activities = new Activities();
				activities.setAccountId(character.getAccountId());
				activities.setType(xar.getId());
				activities.setCount(1);
				activities.setChongzhiqian(-1f);
				character.sendMsg(new ActivitesResponse52054(xar, goodId));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						ActivitiesManager.getInstance().addActivities(activities);
					}
				});
				character.getCharacterActivitesTempInfoManager().addActivities(activities);
			}
		});

	}

	public List<Activities> getActivitiesListByType(List<Activities> list, int type) {
		List<Activities> typeList = new ArrayList<Activities>();
		for (Activities activitie : list) {
			if (activitie.getType() == type) {
				typeList.add(activitie);
			}
		}
		return typeList;
	}

	public int getZiseEquipment(Hero character) {
		Collection<CharacterGoods> collection = character.getCharacterGoodController().getBodyGoodsList();
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.getPingzhiColor() >= 4 && cg.getPosition() != Position.POSTION_TESHU) {
				count++;
			}
		}
		return count;
	}
}
