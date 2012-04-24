package net.snake.gamemodel.goods.logic.container;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.consts.CommonUseNumber;
import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.GoodsDelete10178;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;
import net.snake.gamemodel.hero.bean.Hero;

public abstract class AbstractContainer implements IContainer {
	protected volatile Map<Short, CharacterGoods> goodsMap = new ConcurrentHashMap<Short, CharacterGoods>();
	protected short beginPosition;
	protected int capacity;
	protected Hero owner;

	public void destory() {
		goodsMap.clear();
		goodsMap = null;
	}

	public void clearMemery() {
		goodsMap.clear();
	}

	public void initGoods(CharacterGoods goods) {
		goodsMap.put(goods.getPosition(), goods);
	}

	public void setOwner(Hero owner) {
		this.owner = owner;
	}

	@Override
	public int getTotalCapacity() {
		return capacity;
	}

	@Override
	public CharacterGoods getGoodsByPostion(short postion) {
		return goodsMap.get(postion);
	}

	@Override
	public Collection<CharacterGoods> getGoodsList() {
		return goodsMap.values();
	}

	@Override
	public short getBeginPosition() {
		return beginPosition;
	}

	public boolean isValidatePostion(short postion) {
		return postion >= beginPosition && postion < beginPosition + capacity;
	}

	protected void moveToIdelPostion(CharacterGoods fromGoods, AbstractContainer toGoodsContainer, short topostion) {
		short fromposition = fromGoods.getPosition();
		goodsMap.remove(fromGoods.getPosition());
		owner.sendMsg(new GoodsDelete10178(fromposition, fromGoods.getInHorseId()));

		fromGoods.setPosition(topostion);
		toGoodsContainer.beforeGetGoods(fromGoods);
		toGoodsContainer.goodsMap.put(topostion, fromGoods);// addMemoryGoods(fromGoods);
		owner.sendMsg(fromGoods.getGoodsUpdateMsg());
		// 数据库
		GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, fromGoods);
		// 事件处理
		onChangePostionGoodsRemove(fromGoods);
		toGoodsContainer.onChangePositionGoodsAdd(fromGoods);
	}

	protected void changeGoodsPostion(CharacterGoods fromGoods, AbstractContainer toGoodsContainer, CharacterGoods toGoods) {
		changePosition(fromGoods, toGoods);

		beforeGetGoods(toGoods);
		goodsMap.put(toGoods.getPosition(), toGoods);

		toGoodsContainer.beforeGetGoods(fromGoods);
		toGoodsContainer.goodsMap.put(fromGoods.getPosition(), fromGoods);

		owner.sendMsg(fromGoods.getGoodsUpdateMsg());
		owner.sendMsg(toGoods.getGoodsUpdateMsg());
		// 数据库
		GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, fromGoods);
		GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, toGoods);

		onChangePostionGoodsRemove(fromGoods);
		onChangePositionGoodsAdd(toGoods);
		toGoodsContainer.onChangePostionGoodsRemove(toGoods);
		toGoodsContainer.onChangePositionGoodsAdd(fromGoods);

	}

	protected void changePosition(CharacterGoods goods1, CharacterGoods goods2) {
		short goods1postion = goods1.getPosition();
		goods1.setPosition(goods2.getPosition());
		goods2.setPosition(goods1postion);
	}

	public void moveTo(short fromposition, IContainer target, short topostion) {

		CharacterGoods fromGoods = getGoodsByPostion(fromposition);
		if (fromGoods == null) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40000));
			return;
		}
		if (fromGoods.isInTrade()) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 745));
			return;
		}

		if (topostion == 0 || target instanceof BodyGoodsContiner || target instanceof HorseBodyGoodsContiner) {
			// 当往身上穿，或往坐骑身上穿的时候，强制计算位置
			topostion = target.getSuitPostionForGoods(fromGoods, this);
			if (topostion == 0) {
				if (this instanceof BodyGoodsContiner || this instanceof HorseBodyGoodsContiner) {
					owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 746));
				}
				return;
			}

		}

		if (!target.checksuit(fromGoods)) {
			return;
		}

		if (!target.isValidatePostion(topostion)) {
			owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40001));// "移动位置不合法"
			return;
		}

		CharacterGoods toGoods = target.getGoodsByPostion(topostion);
		if(fromposition==Position.POSTION_TESHU || topostion==Position.POSTION_TESHU){
			fromGoods.setIsUse(CommonUseNumber.byte0);
		}
		if (toGoods == null) {
			moveToIdelPostion(fromGoods, (AbstractContainer) target, topostion);
		} else {// 交换
			if (toGoods.isInTrade()) {//
				owner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 747));
				return;
			}
			if (isMergeAble(fromGoods, toGoods)) {
				merageGoods(fromGoods, toGoods);
			} else {
				if (!checksuit(toGoods)) {
					return;
				}
				changeGoodsPostion(fromGoods, (AbstractContainer) target, toGoods);
			}
		}
	}

	/**
	 * 获得物品前的一些前置工作
	 * 
	 * @param fromGoods
	 */
	abstract protected void beforeGetGoods(CharacterGoods fromGoods);

	/**
	 * 物品是否可以合并
	 * 
	 * @param goods1
	 * @param goods2
	 * @return
	 */
	public static boolean isMergeAble(CharacterGoods goods1, CharacterGoods goods2) {
		Goodmodel goodmodel1 = goods1.getGoodModel();
		Goodmodel goodmodel2 = goods2.getGoodModel();
		// 同种物品,同种叠加属性
		boolean issametime = false;
		if (goods1.getLastDate() == null) {
			if (goods2.getLastDate() == null) {
				issametime = true;
			}
		} else {
			if (goods2.getLastDate() != null && goods1.getLastDate().equals(goods2.getLastDate())) {
				issametime = true;
			}
		}
		if (!issametime) {
			return false;
		}
		String stone1 = goods1.getStroneAddproperty() == null ? "" : goods1.getStroneAddproperty();
		String stone2 = goods2.getStroneAddproperty() == null ? "" : goods2.getStroneAddproperty();
		boolean issamebaoshi = stone1.equals(stone2);
		if (goodmodel1 == goodmodel2 && goods1.getBind().byteValue() == goods2.getBind().byteValue() && goodmodel1.getRepeat() > 1 && issamebaoshi) {
			return true;
		}
		return false;
	}

	public static boolean isHasEnoughCapacityForAddGoods(CharacterGoods cgoods, Map<Short, CharacterGoods> goodsmap, int totalgirdcount) {
		if (cgoods.getGoodModel() == null || cgoods.getCount() == null || cgoods.getBind() == null) {
			throw new RuntimeException("参数不合法");
		}
		// 搜索包裹空余位置
		int space = 0;
		space += (totalgirdcount - goodsmap.size()) * cgoods.getGoodModel().getRepeat();
		if (space >= cgoods.getCount()) {
			return true;
		}

		for (CharacterGoods goods : goodsmap.values()) {
			if (isMergeAble(goods, cgoods)) {
				space += getLeavingsFreeSpace(goods);
			}
		}
		if (space >= cgoods.getCount()) {
			return true;
		}
		return false;
	}

	// 返回剩余的数量
	protected static int getLeavingsFreeSpace(CharacterGoods goods) {
		if (goods.isInTrade()) {
			return 0;
		}
		int t = goods.getGoodModel().getRepeat() - goods.getCount();
		return t < 0 ? 0 : t;
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
			goodsDataEntryManager.asynUpdataCharacterGoods(owner, toGoods);

		} else {
			fromGoods.setCount(fromGoods.getCount() - toGoodsLeavingFreeSpace);
			toGoods.setCount(toGoods.getGoodModel().getRepeat());
			// 消息
			owner.sendMsg(new GoodsUpdata10176(fromGoods));
			owner.sendMsg(new GoodsUpdata10176(toGoods));
			// 数据库
			goodsDataEntryManager.asynUpdataCharacterGoods(owner, fromGoods);
			goodsDataEntryManager.asynUpdataCharacterGoods(owner, toGoods);
		}
	}

	@Override
	public void updateGoods(CharacterGoods charactergoods) {
		GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(owner, charactergoods);
		owner.sendMsg(charactergoods.getGoodsUpdateMsg());
	}

	abstract protected void onChangePositionGoodsAdd(CharacterGoods goods);

	abstract protected void onChangePostionGoodsRemove(CharacterGoods goods);

	abstract protected void onGoodsAdd(CharacterGoods goods);

	abstract protected void onGoodsRemove(CharacterGoods goods);

}
