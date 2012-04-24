package net.snake.gamemodel.goods.logic.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.GoodsDelete10178;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;

import org.apache.log4j.Logger;

public abstract class AbstractGirdContainer extends AbstractContainer implements IGirdContainer {
	private static final Logger logger = Logger.getLogger(AbstractGirdContainer.class);
	long previousCleanTime;

	@Override
	public void setTotalCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void cleanGoods() {
		// 验证操作冷却时间
		if (System.currentTimeMillis() - previousCleanTime < 5 * 1000) {
			// 整理背包功能每30秒之内限制使用一次
			// （整理背包按钮在点击后变为不可点击状态，30秒后恢复）
			// 为了减少不同步服务器计为20秒
			return;
		}
		previousCleanTime = System.currentTimeMillis();

		ArrayList<CharacterGoods> goodslist = new ArrayList<CharacterGoods>(goodsMap.values());
		// 验证是否有物品处于交易中
		for (CharacterGoods goods : goodslist) {
			if (goods.isInTrade()) {
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 748));
				return;
			}
		}

		// 第一步合并,合并时不删除物品,只是把物品数量标计为0
		for (int i = 0; i < goodslist.size(); i++) {
			CharacterGoods goodsi = goodslist.get(i);
			S: for (int j = i + 1; j < goodslist.size(); j++) {
				CharacterGoods goodsj = goodslist.get(j);
				if (!isneedContinueMerge(goodsi, goodsj)) {
					break S;
				}
			}
		}
		// 第二步，删除数量为0的物品
		for (int i = 0; i < goodslist.size(); i++) {
			CharacterGoods goods = goodslist.get(i);
			if (goods.getCount() == 0) {
				goodslist.remove(i);
				GoodsDataEntryManager.getInstance().asynDeleteCharacterGoods(owner, goods.getId());
				i--;
			}
		}
		// 第三步,排序
		Collections.sort(goodslist);
		for (int i = 0; i < goodslist.size(); i++) {
			CharacterGoods goods = goodslist.get(i);
			goods.setPosition((short) (beginPosition + i));
		}
		// 第四步，更新内存和数据库
		Map<Short, CharacterGoods> goodsMaptemp = new ConcurrentHashMap<Short, CharacterGoods>();
		for (CharacterGoods goods : goodslist) {
			goodsMaptemp.put(goods.getPosition(), goods);
			GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, goods);
		}
		goodsMap = goodsMaptemp;

	}

	private boolean isneedContinueMerge(CharacterGoods goodsT, CharacterGoods goodsS) {

		// 已经达到最大合并数
		if (goodsT.getCount().intValue() == goodsT.getGoodModel().getRepeat().intValue()) {
			return false;
		}
		// 模型一样
		if (isMergeAble(goodsT, goodsS)) {
			int needcount = goodsT.getGoodModel().getRepeat() - goodsT.getCount();
			if (goodsS.getCount() >= needcount) {
				goodsT.setCount(goodsT.getGoodModel().getRepeat());
				goodsS.setCount(goodsS.getCount() - needcount);
				return false;
			} else {
				goodsT.setCount(goodsT.getCount() + goodsS.getCount());
				goodsS.setCount(0);
				return true;
			}
		}
		return true;
	}

	// TODO 说明交易情况
	@Override
	public int getGoodsCountByModelID(int goodModelid) {
		int space = 0;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() == goodModelid && !goods.isInTrade() && !goods.isExpired()) {
				space += goods.getCount();
			}
		}
		return space;
	}

	// TODO 说明交易情况
	@Override
	public int getGoodsCountByModelID(int goodModelid, boolean bind) {
		int space = 0;
		for (CharacterGoods goods : goodsMap.values()) {
			if (goods.getGoodmodelId() == goodModelid && !goods.isInTrade() && !goods.isExpired()) {
				if (bind) {
					if (goods.getBind() == 0) {
						continue;
					}
				} else {
					if (goods.getBind() == 1) {
						continue;
					}
				}
				space += goods.getCount();
			}
		}
		return space;
	}

	public short findFirstIdleGirdPosition() {
		// 搜索包裹空余位置
		int maxIndex = beginPosition + capacity;// 人物包裹的最大索引下标
		for (short i = beginPosition; i < maxIndex; i++) {
			if (goodsMap.get(i) == null) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public short findIdleGirdCount() {
		int girdcount = 0;
		// 搜索包裹空余位置
		for (short i = beginPosition; i < beginPosition + capacity; i++) {
			if (goodsMap.get(i) == null) {
				girdcount++;
			}
		}
		return (short) girdcount;
	}

	// 返回剩余的数量
	protected static int getLeavingsFreeSpace(CharacterGoods goods) {
		if (goods.isInTrade()) {
			return 0;
		}
		int t = goods.getGoodModel().getRepeat() - goods.getCount();
		return t < 0 ? 0 : t;
	}

	@Override
	public int getGoodsCount() {
		return goodsMap.size();
	}

	public void deleteGoodsByPostion(short postion) {
		// 内存
		CharacterGoods charactergoods = goodsMap.get(postion);
		if (charactergoods.isInTrade()) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 749));
			return;
		}
		charactergoods = goodsMap.remove(postion);
		// 数据库
		if (charactergoods != null) {
			GoodsDataEntryManager.getInstance().asynDeleteCharacterGoods(owner, charactergoods.getId());
			owner.sendMsg(new GoodsDelete10178(postion, charactergoods.getInHorseId()));
			onGoodsRemove(charactergoods);
		}
	}

	/**
	 * 拆分物品
	 */
	public void splitGoods(short oldpostion, int splitcount) {
		CharacterGoods goods = goodsMap.get(oldpostion);
		if (goods == null) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 750));
			return;
		}
		if (goods.isInTrade()) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 749));
			return;
		}
		int idelindex = findFirstIdleGirdPosition();
		if (idelindex == 0) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 751));
			return;
		}
		if (splitcount <= 0 || splitcount >= goods.getCount()) {
			return;
		}
		goods.setCount(goods.getCount() - splitcount);
		try {
			CharacterGoods goods2 = (CharacterGoods) goods.clone();
			goods2.setCount(splitcount);
			goods2.setPosition((short) idelindex);
			// 内存
			goodsMap.put(goods2.getPosition(), goods2);
			// 消息
			owner.sendMsg(new GoodsUpdata10176(goods));
			owner.sendMsg(new GoodsUpdata10176(goods2));
			// 数据库
			GoodsDataEntryManager.getInstance().asynUpdateGoodsPositonCount(owner, goods);
			GoodsDataEntryManager.getInstance().asynInsertCharacterGoods(owner, goods2);

		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void movePostion(short fromposition, short topostion) {
		CharacterGoods fromGoods = getGoodsByPostion(fromposition);
		if (fromGoods == null) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40000));
			return;
		}
		if (fromposition == topostion) {
			return;
		}
		if (fromGoods.isInTrade()) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 752));
			return;
		}
		CharacterGoods toGoods = getGoodsByPostion(topostion);
		Goodmodel toModel = toGoods == null ? null : toGoods.getGoodModel();

		GoodsDataEntryManager goodsDataEntryManager = GoodsDataEntryManager.getInstance();
		if (toGoods == null) {// 目标位置为空
			// 内存
			goodsMap.remove(fromGoods.getPosition());
			fromGoods.setPosition(topostion);
			goodsMap.put(topostion, fromGoods);
			// 消息
			owner.sendMsg(new GoodsDelete10178(fromposition, 0));
			owner.sendMsg(new GoodsUpdata10176(fromGoods));
			// 数据库
			goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, fromGoods);

		} else {// 目标位置不为空
			if (toGoods.isInTrade()) {
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 753));
				return;
			}

			if (isMergeAble(fromGoods, toGoods)) {// 只有包裹和仓库才会出用到下面的判断
				int toGoodsLeavingFreeSpace = getLeavingsFreeSpace(toGoods);
				if (toGoodsLeavingFreeSpace >= fromGoods.getCount()) {
					// 可以合并成一个
					// 内存=====================================
					goodsMap.remove(fromGoods.getPosition());
					toGoods.setCount(toGoods.getCount() + fromGoods.getCount());
					// 消息=====================================
					owner.sendMsg(new GoodsDelete10178(fromposition, 0));
					owner.sendMsg(new GoodsUpdata10176(toGoods));
					// 数据库====================================
					goodsDataEntryManager.asynDeleteCharacterGoods(owner, fromGoods.getId());
					goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, toGoods);

				} else {
					// 不可以合并成一个
					// 内存
					fromGoods.setCount(fromGoods.getCount() - toGoodsLeavingFreeSpace);
					toGoods.setCount(toModel.getRepeat());
					// 消息
					owner.sendMsg(new GoodsUpdata10176(fromGoods));
					owner.sendMsg(new GoodsUpdata10176(toGoods));
					// 数据库
					goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, fromGoods);
					goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, toGoods);
				}
			} else {// 不可合并,直接交换
				// 内存
				changePosition(fromGoods, toGoods);
				goodsMap.put(fromGoods.getPosition(), fromGoods);
				goodsMap.put(toGoods.getPosition(), toGoods);
				// 消息
				owner.sendMsg(new GoodsUpdata10176(fromGoods));
				owner.sendMsg(new GoodsUpdata10176(toGoods));
				// 数据库
				goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, fromGoods);
				goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, toGoods);
			}
		}
	}

	protected void merageGoods(CharacterGoods fromGoods, CharacterGoods toGoods) {
		int toGoodsLeavingFreeSpace = getLeavingsFreeSpace(toGoods);
		GoodsDataEntryManager goodsDataEntryManager = GoodsDataEntryManager.getInstance();
		if (toGoodsLeavingFreeSpace >= fromGoods.getCount()) {
			// 可以合并成一个
			// 内存
			goodsMap.remove(fromGoods.getPosition());
			toGoods.setCount(toGoods.getCount() + fromGoods.getCount());
			// 消息
			owner.sendMsg(new GoodsDelete10178(fromGoods.getPosition(), 0));
			owner.sendMsg(new GoodsUpdata10176(toGoods));
			// 数据库
			goodsDataEntryManager.asynDeleteCharacterGoods(owner, fromGoods.getId());
			goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, toGoods);

		} else {
			fromGoods.setCount(fromGoods.getCount() - toGoodsLeavingFreeSpace);
			toGoods.setCount(toGoods.getGoodModel().getRepeat());
			// 消息
			owner.sendMsg(new GoodsUpdata10176(fromGoods));
			owner.sendMsg(new GoodsUpdata10176(toGoods));
			// 数据库
			goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, fromGoods);
			goodsDataEntryManager.asynUpdateGoodsPositonCount(owner, toGoods);
		}
	}

}
