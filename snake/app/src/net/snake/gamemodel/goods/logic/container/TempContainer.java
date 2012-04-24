package net.snake.gamemodel.goods.logic.container;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;

public class TempContainer {
	private static final Logger logger = Logger.getLogger(TempContainer.class);
	private volatile Map<Short, CharacterGoods> goodsMap = new ConcurrentHashMap<Short, CharacterGoods>();
	private short POSTIONBEGIN;
	private int capacity;

	public TempContainer(Map<Short, CharacterGoods> goodsMap2, short begin, int capacity) {
		this.POSTIONBEGIN = begin;
		this.capacity = capacity;
		for (CharacterGoods goods : goodsMap2.values()) {
			try {
				CharacterGoods tgoods = (CharacterGoods) goods.clone();
				goodsMap.put(tgoods.getPosition(), tgoods);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 
	 * @param goodsMap2
	 * @param begin
	 * @param capacity
	 * @param isIgnoreTrading
	 *            true 是忽略交易
	 */
	public TempContainer(Map<Short, CharacterGoods> goodsMap2, short begin, int capacity, boolean isIgnoreTrading) {
		this.POSTIONBEGIN = begin;
		this.capacity = capacity;
		for (CharacterGoods goods : goodsMap2.values()) {
			try {
				CharacterGoods tgoods = (CharacterGoods) goods.clone();
				if (isIgnoreTrading) {
					tgoods.setTradeIndex(-1);
				}
				goodsMap.put(tgoods.getPosition(), tgoods);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void deleteByPostion(short postion) {
		goodsMap.remove(postion);

	}

	private int getGoodsCountByModelID(int goodModelid) {
		int space = 0;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() == goodModelid && !goods.isInTrade() && !goods.isExpired()) {
				space += goods.getCount();
			}
		}
		return space;
	}

	private int getGoodsCountByModelID(int goodModelid, boolean bind) {
		int space = 0;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() == goodModelid && !goods.isInTrade() && !goods.isExpired()) {
				if (!bind) {
					if (goods.getBind() == 1) {
						continue;
					}
				} else {
					if (goods.getBind() == 0) {
						continue;
					}
				}
				space += goods.getCount();
			}
		}
		return space;
	}

	public boolean deleteSplitGoods(short oldpostion, int splitcount) {
		CharacterGoods goods = goodsMap.get(oldpostion);
		if (goods == null) {
			// owner.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "找不到物品"));
			return false;
		}
		if (goods.isInTrade()) {
			// owner.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP,
			// "该物品正在交易中,无法删除"));
			return false;
		}
		if (goods.isExpired()) {
			return false;
		}

		if (splitcount <= 0 || splitcount > goods.getCount()) {
			return false;
		}

		if (splitcount == goods.getCount()) {
			goodsMap.remove(oldpostion);
			return true;
		}
		goods.setCount(goods.getCount() - splitcount);

		return true;
	}

	public boolean deleteGoodsByModel(int modelid, int deletecount) {

		if (deletecount <= 0) {
			return false;
		}
		int realcount = getGoodsCountByModelID(modelid);
		if (realcount < deletecount) {
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
				break;
			} else {
				leavecount = leavecount - goods.getCount();
				goodsMap.remove(goods.getPosition());
				if (leavecount == 0) {
					break;
				}
			}
		}

		return true;
	}

	public boolean deleteGoodsByModel(int modelid, int deletecount, boolean bind) {

		if (deletecount <= 0) {
			return false;
		}
		int realcount = getGoodsCountByModelID(modelid);
		if (realcount < deletecount) {
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
				break;
			} else {
				leavecount = leavecount - goods.getCount();
				goodsMap.remove(goods.getPosition());
				if (leavecount == 0) {
					break;
				}
			}
		}

		return true;
	}

	public boolean deleteGoodsByModelBindFirst(int modelid, int deletecount) {

		if (deletecount <= 0) {
			return false;
		}

		int bindCount = getGoodsCountByModelID(modelid, true);
		int count = getGoodsCountByModelID(modelid, false);
		int realcount = bindCount + count;
		if (realcount < deletecount) {
			return false;
		}

		int leavecount = deletecount;
		boolean delete = true;
		if (leavecount > bindCount) {
			delete = deleteGoodsByModel(modelid, bindCount, true);
			if (!delete) {
				return false;
			}
			leavecount = leavecount - bindCount;
			delete = deleteGoodsByModel(modelid, leavecount, false);
			if (!delete) {
				return false;
			}
			return true;
		} else {
			delete = deleteGoodsByModel(modelid, leavecount, true);
			if (!delete) {
				return false;
			}
			return true;
		}
	}

	public boolean tryAddGoods(Collection<CharacterGoods> goodslist) {
		for (CharacterGoods goods : goodslist) {
			if (goods.getGoodModel() == null || goods.getCount() == null || goods.getBind() == null) {
				throw new RuntimeException("参数不合法");
			}
		}
		for (CharacterGoods goods : goodslist) {
			boolean ok = addGoods(goods);
			if (ok == false) {
				return false;
			}
		}
		return true;
	}

	// 返回剩余的数量
	private int getLeavingsFreeSpace(CharacterGoods goods) {
		if (goods.isInTrade()) {
			return 0;
		}
		int t = goods.getGoodModel().getRepeat() - goods.getCount();
		return t < 0 ? 0 : t;
	}

	public boolean addGoods(CharacterGoods goods) {
		// 检查空间是否足够
		if (!AbstractContainer.isHasEnoughCapacityForAddGoods(goods, goodsMap, capacity)) {
			return false;
		}
		int needcount = goods.getCount();
		Goodmodel goodmodel = goods.getGoodModel();
		try {
			goods = (CharacterGoods) goods.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
		// 先合并的方式插入
		for (CharacterGoods goodsInBag : goodsMap.values()) {
			if (AbstractContainer.isMergeAble(goodsInBag, goods)) {// 检查是否可又堆叠
				int leavingspace = getLeavingsFreeSpace(goodsInBag);
				if (leavingspace > 0) {
					if (leavingspace >= needcount) {
						// 内存
						goodsInBag.setCount(goodsInBag.getCount() + needcount);

						needcount = 0;
					} else {
						// 内存
						goodsInBag.setCount(goodmodel.getRepeat());

						needcount = needcount - leavingspace;
					}
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
				break;
			}
			try {
				CharacterGoods newGoods = (CharacterGoods) goods.clone();
				newGoods.setId(UUID.randomUUID().toString().replace("-", ""));
				newGoods.setPosition(index);
				if (goodmodel.getRepeat() >= needcount) {
					newGoods.setCount(needcount);
					needcount = 0;
				} else {
					newGoods.setCount(goodmodel.getRepeat());
					needcount = needcount - goodmodel.getRepeat();
				}
				// 内存
				goodsMap.put(index, newGoods);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return true;
	}

	private short findFirstIdleGirdPosition() {
		// 搜索包裹空余位置
		int end = POSTIONBEGIN + capacity;
		for (short i = POSTIONBEGIN; i < end; i++) {
			if (goodsMap.get(i) == null) {
				return i;
			}
		}
		return 0;
	}
}
