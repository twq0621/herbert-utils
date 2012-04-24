package net.snake.gamemodel.activities.response;

/**
 * 
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.consts.Position;
import net.snake.gamemodel.activities.bean.Activities;
import net.snake.gamemodel.activities.bean.XianshiActivityReward;
import net.snake.gamemodel.activities.persistence.XianshiActivityController;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.netio.ServerResponse;

public class ActivitesResponse52056 extends ServerResponse {

	/**
	 * @param listActivities
	 * @param ctc
	 */
	public ActivitesResponse52056(List<Activities> listActivities, XianshiActivityController ctc, Hero character) {
		this.setMsgCode(52056);
		try {
			this.writeInt(ctc.getXianshiActivity().getId());
			List<XianshiActivityReward> list = ctc.getNowActivityList();
			this.writeInt(list.size());
			for (XianshiActivityReward xa : list) {
				this.writeInt(xa.getId());
				this.writeByte(xa.getType());
				if (xa.getType() == 1) {
					// 1{描述(str,支持HTML), 充值钱数(int),
					// 兑换目标道具ID（int）,兑换目标道具个数（short）,
					// 已领目标道具个数（short)}
					this.writeUTF(xa.getDescI18n());
					this.writeInt(xa.getMonneyLimit());
					List<CharacterGoods> rewards = xa.getListReward();
					this.writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						this.writeInt(cg.getGoodmodelId());
						this.writeShort(cg.getCount());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						Activities activities = type_List.get(0);
						type_getcount = activities.getCount();
					}
					this.writeByte(type_getcount);
					this.writeByte(xa.getRewardGoodMaxCount());
				} else if (xa.getType() == 2) {
					// 2{描述(str,支持HTML), 源道具ID（int）,源道具个数（short）,
					// 按钮数量(byte){条目编号(int),兑换礼金数量(int),是否已兑换(byte),
					// 文字描述(str)}}(条目编号取后面的，领取编号恒发0)
					this.writeUTF(xa.getDescI18n());
					CharacterGoods cg = xa.getGoodLimiteList().get(0);
					this.writeInt(cg.getGoodmodelId());
					this.writeShort(cg.getCount());
					List<XianshiActivityReward> lijinList = xa.getLijinXaList();
					this.writeByte(lijinList.size());
					for (XianshiActivityReward temp : lijinList) {
						this.writeInt(temp.getId());
						this.writeInt(temp.getRewardLijin());
						List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
						int type_getcount = 0;
						if (type_List.size() > 0) {
							type_getcount = 1;
						}
						this.writeByte(type_getcount);
						this.writeUTF(temp.getKuozhanDescI18n());
					}
				} else if (xa.getType() == 3) {
					// 3{描述(str,支持HTML), 源道具ID（int）,源道具个数（short）,
					// * 兑换目标道具ID（int）,兑换目标道具个数（short）, 是否已兑换(byte)}
					// 4{描述(str,支持HTML),
					// * 已打通穴位数量
					this.writeUTF(xa.getDescI18n());
					// this.output.writeInt(0);
					// this.output.writeShort(0);
					List<CharacterGoods> rewards = xa.getListReward();
					this.writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						this.writeInt(cg.getGoodmodelId());
						this.writeShort(cg.getCount());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int count = 0;
					if (type_List.size() > 0) {
						Activities activities = type_List.get(0);
						count = activities.getCount();
					}
					int maxCount = xa.getRewardGoodMaxCount();
					this.writeShort(count);
					this.writeShort(maxCount);
				} else if (xa.getType() == 4) {
					// 4{描述(str,支持HTML),
					// * 已打通穴位数量
					// *
					// (short),全部穴位数量(short),兑换目标道具ID（int）,兑换目标道具个数（short）,是否已兑换
					// * (byte)}
					this.writeUTF(xa.getDescI18n());
					writeShort(character.getChannelXuewei());
					writeShort(344);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 5) {
					// 5{描述(str,支持HTML),
					// * 已经为紫色的装备数量(short),全紫要求总数量(short),兑换目标道具ID
					// * （int）,兑换目标道具个数（short）,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					int zise = getZiseEquipment(character);
					writeShort(zise);
					writeShort(12);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 6) {
					// 6{描述(str,支持HTML),
					// * 已经为满星的装备数量
					// * (short),满星要求总数量(short),兑换目标道具ID（int）,兑换目标道具个数（short）
					// * ,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					int manxing = getManxingEquipment(character);
					writeShort(manxing);
					writeShort(12);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 7) {
					// 7{描述(str,支持HTML),
					// * 已经为满融的装备数量(short),满融要求总数量(short
					// * ),兑换目标道具ID（int）,兑换目标道具个数（short）,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					int manlong = getManLongEquipment(character);
					writeShort(manlong);
					writeShort(12);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 8) {
					// 8{描述(str,支持HTML),
					// 兑换目标道具ID（int）,兑换目标道具个数（short）,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 9) {
					// 9{描述(str,支持HTML), 可选数量（byte）{兑换目标道具ID（int）},
					// }(领取编号发兑换目标道具ID)
					this.writeUTF(xa.getDescI18n());
					this.writeInt(xa.getMonneyLimit());
					List<CharacterGoods> rewards = xa.getListReward();
					this.writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						this.writeInt(cg.getGoodmodelId());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						Activities activities = type_List.get(0);
						type_getcount = activities.getCount();
					}
					this.writeShort(xa.getRewardGoodMaxCount());
					this.writeShort(type_getcount);
				} else if (xa.getType() == 51) {
					// 51{描述(str,支持HTML)}
					this.writeUTF(xa.getDescI18n());
				} else if (xa.getType() == 52 || xa.getType() == 53) {
					// 52{描述(str,支持HTML),道具ID（int）} 53{描述(str,支持HTML),道具ID（int）}
					this.writeUTF(xa.getDescI18n());
					this.writeInt(xa.getViewGood());
				} else if (xa.getType() == 10) {
					// 10{描述(str,支持HTML), 购买目标道具ID（int）,购买目标道具个数（short）,
					// 已购买目标道具个数（short)}
					this.writeUTF(xa.getDescI18n());
					writeInt(xa.getShoplimitGoodId());
					writeShort(xa.getShoplimitGoodCount());
					int count = character.getCount(xa.getShoplimitGoodId());
					writeShort(count);
				} else if (xa.getType() == 11) {
					// {描述(str,支持HTML),当前弓箭阶数(short),多少阶以上可兑换(short),兑换目标道具ID（int）
					// ,兑换目标道具个数（short）,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					int zise = character.getBowController().getLevel();
					writeShort(zise);
					writeShort(7);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 12) {
					// {描述(str,支持HTML),当前弓箭阶数(short),多少阶以上可兑换(short),兑换目标道具ID（int）
					// ,兑换目标道具个数（short）,是否已兑换(byte)}
					this.writeUTF(xa.getDescI18n());
					DanTian dantian = character.getDanTianController().getDanTian();
					int zise = 0;
					if (dantian != null) {
						zise = dantian.getModel().getId();
					}
					writeShort(zise);
					writeShort(8);
					List<CharacterGoods> rewards = xa.getListReward();
					CharacterGoods cg = rewards.get(0);
					writeInt(cg.getGoodmodelId());
					writeShort(cg.getCount());
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 13) { // vip道具随机兑换
					this.writeUTF(xa.getDescI18n());
					this.writeBoolean(character.getMyCharacterVipManger().isVipYueka());
					List<CharacterGoods> rewards = xa.getListReward();
					writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						writeInt(cg.getGoodmodelId());
						writeShort(cg.getCount());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 14) { // 坐骑领取道具
					this.writeUTF(xa.getDescI18n());
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
					if (horse == null) {
						this.writeInt(0);
					} else {
						this.writeInt(horse.getHorseModel().getId());
					}
					int maxHorseId = 0;
					int horseKind = xa.getHorseLimit();
					List<HorseModel> hmList = HorseModelDataProvider.getInstance().getHorseModelListByTypeName(horseKind);
					if (hmList != null && hmList.size() > 0) {
						maxHorseId = hmList.get(0).getKind();
					}
					this.writeInt(maxHorseId);
					List<CharacterGoods> rewards = xa.getListReward();
					writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						writeInt(cg.getGoodmodelId());
						writeShort(cg.getCount());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int type_getcount = 0;
					if (type_List.size() > 0) {
						type_getcount = 1;
					}
					writeByte(type_getcount);
				} else if (xa.getType() == 15) {// 丹田玩家兑换道具
					this.writeUTF(xa.getDescI18n());
					int size = 0;
					DanTian dantian = character.getDanTianController().getDanTian();
					if (dantian != null) {
						size = dantian.getModel().getId();
					}
					this.writeByte(size);
					List<CharacterGoods> rewards = xa.getListReward();
					this.writeByte(rewards.size());
					for (CharacterGoods cg : rewards) {
						this.writeInt(cg.getGoodmodelId());
						this.writeShort(cg.getCount());
					}
					List<Activities> type_List = getActivitiesListByType(listActivities, xa.getId());
					int count = 0;
					if (type_List.size() > 0) {
						Activities activities = type_List.get(0);
						count = activities.getCount();
					}
					int maxCount = xa.getRewardGoodMaxCount();
					this.writeShort(count);
					this.writeShort(maxCount);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
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

}
