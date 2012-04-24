package net.snake.gamemodel.goods.logic.container;

import net.snake.GameServer;
import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.CharacterGetGoods11212;
import net.snake.gamemodel.goods.response.CharacterLoseGoods11214;
import net.snake.gamemodel.goods.response.GetABetterEquip50668;
import net.snake.gamemodel.goods.response.GetALearnSkillbook50670;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;

import org.apache.log4j.Logger;
import java.util.Collection;
import java.util.UUID;

public class BagGoodsContainer extends AbstractGirdContainer implements IBag {
	private static Logger logger = Logger.getLogger(BagGoodsContainer.class);

	public BagGoodsContainer() {
		beginPosition = Position.BagGoodsBeginPostion;
	}

	@Override
	public boolean deleteGoodsByModel(int modelid, int deletecount) {

		if (deletecount <= 0)
			return false;

		int realcount = getGoodsCountByModelID(modelid);
		if (realcount < deletecount) {
			// TODO 包裹里没有足够的此类物品
			return false;
		}

		int leavecount = deletecount;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() != modelid || goods.isInTrade() || goods.isExpired()) {
				continue;
			}

			if (goods.getCount() > leavecount) {
				// 内存
				goods.setCount(goods.getCount() - leavecount);
				updateFromContainer(goods);
				owner.sendMsg(new CharacterLoseGoods11214(modelid, leavecount));
				if (goods.getBuyType() == 1) {
					// 发送日志
					long time = 0;
					if (goods.getLastDate() != null) {
						time = goods.getLastDate().getTime() / 1000;
					}
					GameServer.dataLogManager.logRMBUseItem("1", modelid, goods.getBuyPrice(), time, leavecount, owner.getLogUid(), owner.getHeroInfo());
				}
				break;
			} else {
				leavecount = leavecount - goods.getCount();
				deleteGoodsByPostion(goods.getPosition());
				if (goods.getBuyType() == 1) {
					// 发送日志
					long time = 0;
					if (goods.getLastDate() != null) {
						time = goods.getLastDate().getTime() / 1000;
					}
					GameServer.dataLogManager.logRMBUseItem("1", modelid, goods.getBuyPrice(), time, leavecount, owner.getLogUid(), owner.getHeroInfo());
				}
				if (leavecount == 0) {
					break;
				}
			}
		}

		return true;
	}

	@Override
	public boolean deleteGoodsByModel(int modelid, int deletecount, boolean bind) {
		if (deletecount <= 0) {
			return false;
		}
		int realcount = getGoodsCountByModelID(modelid, bind);
		if (realcount < deletecount) {
			// TODO 包裹里没有足够的此类物品
			return false;
		}

		int leavecount = deletecount;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() != modelid || goods.isInTrade() || goods.isExpired()) {
				continue;
			}

			if (!bind) {
				if (goods.getBind() == 1) {
					continue;
				}
			} else {
				if (goods.getBind() == 0) {
					continue;
				}
			}

			if (goods.getCount() > leavecount) {
				// 内存
				goods.setCount(goods.getCount() - leavecount);
				updateFromContainer(goods);
				owner.sendMsg(new CharacterLoseGoods11214(modelid, leavecount));
				if (goods.getBuyType() == 1) {
					// 发送日志
					long time = 0;
					if (goods.getLastDate() != null) {
						time = goods.getLastDate().getTime() / 1000;
					}
					GameServer.dataLogManager.logRMBUseItem("1", modelid, goods.getBuyPrice(), time, leavecount, owner.getLogUid(), owner.getHeroInfo());
				}
				break;
			} else {
				leavecount = leavecount - goods.getCount();
				deleteGoodsByPostion(goods.getPosition());
				if (goods.getBuyType() == 1) {
					// 发送日志
					long time = 0;
					if (goods.getLastDate() != null) {
						time = goods.getLastDate().getTime() / 1000;
					}
					GameServer.dataLogManager.logRMBUseItem("1", modelid, goods.getBuyPrice(), time, leavecount, owner.getLogUid(), owner.getHeroInfo());
				}
				if (leavecount == 0) {
					break;
				}
			}
		}

		return true;
	}

	private void updateFromContainer(CharacterGoods goods) {
		// 消息
		owner.sendMsg(new GoodsUpdata10176(goods));
		// 数据库
		GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, goods);
	}

	@Override
	public boolean checksuit(CharacterGoods goods) {
		return true;
	}

	// 每次获得新的装备道具时与老的装备进行属性比较，若属性更好则弹出面板提示 每次都触发
	// 属性比较规则：
	// 该道具玩家是否可穿戴，不可穿戴则不弹出提示面板
	// 1： 只与同部位的道具进行比较
	// 2： 原部位上无装备，则新获得的装备属性更好
	// 3： 佩戴等级限制越高，则装备属性越好
	// 4： 佩戴等级限制相同，则品质颜色越高装备属性越好
	// 5： 颜色品质相同，则强化等级越高，属性越好
	// 6： 强化等级相同，则两者属性的好坏判断为相同，不弹出换装提示面板
	@Override
	protected void onChangePositionGoodsAdd(CharacterGoods goods) {

	}

	@Override
	protected void onChangePostionGoodsRemove(CharacterGoods goods) {

		if (goods.getQuickbarindex() != 0) {
			CharacterGoods nextquickbargoods = getFirstGoodsByModelid(goods.getGoodmodelId());
			if (nextquickbargoods != null) {
				// 设置新的快捷栏
				int quickbarindex = goods.getQuickbarindex();
				// owner.getQuickbarController().removeQuickBar(goods.getQuickbarindex());
				owner.getQuickbarController().removeQuickBarGoods(goods.getQuickbarindex());
				owner.getQuickbarController().setQuickBarGoods(quickbarindex, nextquickbargoods);
			} else {
				owner.getQuickbarController().removeQuickBarGoods(goods.getQuickbarindex());
			}
		}

	}

	@Override
	protected void onGoodsRemove(CharacterGoods goods) {
		owner.sendMsg(new CharacterLoseGoods11214(goods.getGoodmodelId(), goods.getCount()));
	}

	@Override
	protected void onGoodsAdd(CharacterGoods goods) {
		owner.sendMsg(new CharacterGetGoods11212(goods.getGoodmodelId(), goods.getCount()));
		// 检测是否我获得了很好的装备
		IBody bodygoods = owner.getCharacterGoodController().getBodyGoodsContiner();
		if (goods.getGoodModel().isEquipment() && bodygoods.checksuit(goods, false)) {
			CharacterGoods oldgoods = bodygoods.getGoodsByPostion(goods.getGoodModel().getPosition().shortValue());
			if (oldgoods == null || (oldgoods != null && goods.isBetterEquip(oldgoods))) {
				owner.sendMsg(new GetABetterEquip50668(goods));
			}
		}
		// 检查是否我获得可学的新技能
		if (goods.getGoodModel().isSkillBook()) {
			if (owner.getCharacterSkillManager().isLearnSkill(goods.getGoodModel().getSkill()) == 0) {
				owner.sendMsg(new GetALearnSkillbook50670(goods));
			}
		}
	}

	@Override
	protected void beforeGetGoods(CharacterGoods fromGoods) {
		fromGoods.setInHorseId(0);
	}

	@Override
	public boolean isHasSpaceForAddGoods(Collection<CharacterGoods> goodslist) {
		TempContainer tc = new TempContainer(goodsMap, beginPosition, capacity);
		return tc.tryAddGoods(goodslist);
	}

	@Override
	public boolean isHasSpaceForAddGoods(CharacterGoods goods) {
		return isHasEnoughCapacityForAddGoods(goods, goodsMap, capacity);
	}

	public boolean addGoods(CharacterGoods goods) {
		CharacterGoods returngoods = addAndReturnLast(goods);
		if (returngoods == null) {
			return false;
		}
		return true;
	}

	public boolean addGoods(Collection<CharacterGoods> goodslist) {
		TempContainer tc = new TempContainer(goodsMap, beginPosition, capacity);
		if (!tc.tryAddGoods(goodslist)) {
			// 不能添加物品
			return false;
		}
		for (CharacterGoods goods : goodslist) {
			addGoods(goods);
		}
		return true;
	}

	public boolean isHasSpaceForRemoveAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist) {

		TempContainer tc = new TempContainer(goodsMap, beginPosition, capacity);
		if (removepostions != null) {
			for (CharacterGoods postion : removepostions) {
				if (postion.getPosition() != null && postion.getPosition() != 0) {// 特定位置的删除
					if (postion.getCount() == null || postion.getCount() == 0) {
						tc.deleteByPostion(postion.getPosition());
					} else {// 物品不能删，也要返回false
						boolean isok = tc.deleteSplitGoods(postion.getPosition(), postion.getCount());
						if (!isok) {
							return false;
						}
					}
				} else {
					boolean isok = true;
					if (postion.getGoodModel() == null) {// 找不到物品模型
						logger.error("can not find good type");
						return false;
					}
					if (postion.getBind() == null) {
						isok = tc.deleteGoodsByModel(postion.getGoodmodelId(), postion.getCount());

					} else {
						isok = tc.deleteGoodsByModel(postion.getGoodmodelId(), postion.getCount(), postion.isBinding());
					}
					if (!isok) {
						return false;
					}
				}
			}
		}

		for (CharacterGoods good : addlist) {
			boolean ok = tc.addGoods(good);
			if (!ok) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 绑定优先删除
	 * 
	 * @param removepostions
	 * @param addlist
	 * @return
	 */
	public boolean isHasSpaceForBindFirstRemoveAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist) {

		TempContainer tc = new TempContainer(goodsMap, beginPosition, capacity);
		if (removepostions != null) {
			for (CharacterGoods postion : removepostions) {
				if (postion.getPosition() != null && postion.getPosition() != 0) {// 特定位置的删除
					if (postion.getCount() == goodsMap.get(postion.getPosition().shortValue()).getCount()) {
						tc.deleteByPostion(postion.getPosition());
					}
				} else {
					// 按数量删除
					// tc.deleteGoodsByModel(postion.getGoodmodelId(),
					// postion.getCount());
					tc.deleteGoodsByModelBindFirst(postion.getGoodmodelId(), postion.getCount());
				}
			}
		}

		for (CharacterGoods good : addlist) {
			boolean ok = tc.addGoods(good);
			if (!ok) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean deleteSplitGoods(short oldpostion, int splitcount) {
		CharacterGoods goods = goodsMap.get(oldpostion);
		if (goods == null) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 754));
			return false;
		}
		if (goods.isInTrade()) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 749));
			return false;
		}
		if (goods.isExpired()) {
			// owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,749));
			return false;
		}

		if (splitcount <= 0 || splitcount > goods.getCount()) {
			return false;
		}

		if (splitcount == goods.getCount()) {
			deleteGoodsByPostion(oldpostion);
			return true;
		}

		goods.setCount(goods.getCount() - splitcount);
		owner.sendMsg(new GoodsUpdata10176(goods));
		// 数据库
		GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, goods);
		owner.sendMsg(new CharacterLoseGoods11214(goods.getGoodmodelId(), splitcount));
		return true;
	}

	@Override
	public short getSuitPostionForGoods(CharacterGoods fromGoods, IContainer fromContainer) {
		short postion = findFirstIdleGirdPosition();
		if (postion == 0 && fromContainer instanceof IStall) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 755));
		}
		return findFirstIdleGirdPosition();

	}

	@Override
	public boolean removeAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist) {
		if (!isHasSpaceForRemoveAndAddGoods(removepostions, addlist)) {
			return false;
		}
		if (removepostions != null) {
			for (CharacterGoods postion : removepostions) {
				if (postion.getPosition() != null && postion.getPosition() != 0) {
					if (postion.getCount() == null || postion.getCount() == 0) {// 特定位置的删除,不计数量
						deleteGoodsByPostion(postion.getPosition());
					} else {
						deleteSplitGoods(postion.getPosition(), postion.getCount());
					}
				} else {
					// 按数量删除
					if (postion.getBind() == null) {
						deleteGoodsByModel(postion.getGoodmodelId(), postion.getCount());
					} else {
						deleteGoodsByModel(postion.getGoodmodelId(), postion.getCount(), postion.isBinding());
					}
				}
			}
		}
		for (CharacterGoods good : addlist) {
			boolean ok = addGoods(good);
			if (!ok) {
				return false;
			}
		}
		return true;
	}

	@Override
	public CharacterGoods getFirstGoodsByModelid(int modelid) {
		for (CharacterGoods goodsInBag : goodsMap.values()) {
			if (goodsInBag.getGoodmodelId() == modelid) {
				return goodsInBag;
			}
		}
		return null;
	}

	@Override
	public CharacterGoods addAndReturnLast(CharacterGoods goods) {
		if (!isHasEnoughCapacityForAddGoods(goods, goodsMap, capacity)) {
			return null;
		}
		Goodmodel goodmodel = goods.getGoodModel();
		try {
			goods = (CharacterGoods) goods.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
		int needcount = goods.getCount();
		goods.setCharacterId(owner.getId());
		goods.setId(UUID.randomUUID().toString().replace("-", ""));
		// 先尝试在指定位置插入是否可又满足
		short requirePostion = goods.getPosition() == null ? 0 : goods.getPosition();
		if (requirePostion > beginPosition && requirePostion <= beginPosition + capacity) {
			CharacterGoods oldgoods = getGoodsByPostion(requirePostion);
			if (oldgoods != null) {
				if (getLeavingsFreeSpace(oldgoods) >= needcount && isMergeAble(oldgoods, goods)) {
					oldgoods.setCount(oldgoods.getCount() + needcount);
					// 消息
					owner.sendMsg(new GoodsUpdata10176(oldgoods));
					// 数据库
					GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, oldgoods);
					onGoodsAdd(goods);
					return oldgoods;
				}
			} else {
				if (goods.getGoodModel().getRepeat() >= goods.getCount()) {
					// 可以直接放到
					// 内存
					goodsMap.put(requirePostion, goods);
					// 消息
					owner.sendMsg(new GoodsUpdata10176(goods));
					// 数据库
					GoodsDataEntryManager.getInstance().asynInsertCharacterGoods(owner, goods);
					onGoodsAdd(goods);
					return goods;
				}

			}
		}

		// 先合并的方式插入
		for (CharacterGoods goodsInBag : goodsMap.values()) {
			if (isMergeAble(goodsInBag, goods)) {// 检查是否可又堆叠
				int leavingspace = getLeavingsFreeSpace(goodsInBag);
				if (leavingspace > 0) {
					if (leavingspace >= needcount) {
						// 内存
						goodsInBag.setCount(goodsInBag.getCount() + needcount);
						// 消息
						owner.sendMsg(new GoodsUpdata10176(goodsInBag));
						// 数据库
						GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, goodsInBag);
						needcount = 0;
					} else {
						// 内存
						goodsInBag.setCount(goodmodel.getRepeat());
						// 消息
						owner.sendMsg(new GoodsUpdata10176(goodsInBag));
						// 数据库
						GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, goodsInBag);
						needcount = needcount - leavingspace;
					}
					goods.setPosition(goodsInBag.getPosition());
				}

			}
			if (needcount == 0) {
				break;
			}
		}
		// 再用新空间查入
		while (needcount > 0) {
			short index = findFirstIdleGirdPosition();
			if (index == 0) {
				// logger.warn("物品空间计算可能存在错误,请检查");
				break;
			}
			try {
				goods = (CharacterGoods) goods.clone();
				goods.setCharacterId(owner.getId());
				goods.setId(UUID.randomUUID().toString().replace("-", ""));
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			goods.setPosition(index);
			if (goodmodel.getRepeat() >= needcount) {
				goods.setCount(needcount);
				needcount = 0;
			} else {
				goods.setCount(goodmodel.getRepeat());
				needcount = needcount - goodmodel.getRepeat();
			}
			// 内存
			goodsMap.put(index, goods);
			// 消息
			owner.sendMsg(new GoodsUpdata10176(goods));
			// 数据库
			GoodsDataEntryManager.getInstance().asynInsertCharacterGoods(owner, goods);
		}
		onGoodsAdd(goods);
		return goods;
	}

	/**
	 * 交易中验证是否有足够空间
	 * 
	 * @param values
	 * @param goodsCollection
	 * @return
	 */
	@Override
	public boolean isHasSpaceForRemoveAndAddGoodsInTrading(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist) {
		TempContainer tc = new TempContainer(goodsMap, beginPosition, capacity, true);
		if (removepostions != null) {
			for (CharacterGoods postion : removepostions) {
				if (postion.getPosition() != null && postion.getPosition() != 0) {// 特定位置的删除
					if (postion.getCount() == null || postion.getCount() == 0) {
						tc.deleteByPostion(postion.getPosition());
					} else {// 物品不能删，也要返回false
						boolean isok = tc.deleteSplitGoods(postion.getPosition(), postion.getCount());
						if (!isok) {
							return false;
						}
					}
				} else {
					// 按数量删除
					boolean isok = tc.deleteGoodsByModel(postion.getGoodmodelId(), postion.getCount());
					if (!isok) {
						return false;
					}
				}

			}
		}
		for (CharacterGoods good : addlist) {
			boolean ok = tc.addGoods(good);
			if (!ok) {
				return false;
			}
		}
		return true;
	}
}
