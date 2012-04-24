package net.snake.gamemodel.trade.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.snake.commons.program.SafeTimer;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.consts.TradeStatus;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.trade.response.TradeMsg10824;
import net.snake.gamemodel.trade.response.TradeMsg10828;
import net.snake.gamemodel.trade.response.TradeMsg10840;
import net.snake.gamemodel.trade.response.TradeSuodingMsg10838;

/**
 * 
 * 角色对角色交易逻辑处理管理器 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class MyTradeController extends CharacterController implements IMyTradeController {
	public MyTradeController(Hero character) {
		super(character);
	}

	public static int TRADE_INDEX_MIN = 1;
	public static int TRADE_INDEX_MAX = 5;
	private TradeStatus tradeStatus = TradeStatus.idle;
	// 我向那些角色发起了交易
	private Map<Integer, Long> trackRequestMap = new HashMap<Integer, Long>();// 交易发起列表
	// 已经确定好了交易对像是谁
	private Hero tradeCharacter;// 交易对象
	// 交易中的物品列表=======================================
	private HashMap<Integer, CharacterGoods> goodslistMap = new HashMap<Integer, CharacterGoods>();
	private Horse horse;// 交易的坐骑
	private int copper = 0;// 交易中的铜币
	private int yuanbao = 0;// 元宝
	private static SafeTimer st = new SafeTimer(2000);

	public void destroy() {
		resetItem();
		trackRequestMap = null;
		goodslistMap = null;

	}

	public void putGoodsToTradeMap(int tradeIndex, CharacterGoods cg) {
		cg.setTradeIndex(tradeIndex);
		this.goodslistMap.put(tradeIndex, cg);
	}

	public CharacterGoods getCharacterGoodsByTradeIndex(int tradeIndex) {
		return this.goodslistMap.get(tradeIndex);
	}

	public void removeGoodsInTradeMap(int tradeIndex) {
		CharacterGoods cg = this.goodslistMap.remove(tradeIndex);
		cg.setTradeIndex(-1);
	}

	/**
	 * 随机选择位置存放
	 * 
	 * @return
	 */
	public int findTradIndex() {
		if (goodslistMap.size() == 0) {
			return TRADE_INDEX_MIN;
		}
		for (int i = TRADE_INDEX_MIN; i <= TRADE_INDEX_MAX; i++) {
			if (goodslistMap.get(i) == null) {
				return i;
			}
		}
		return TRADE_INDEX_MAX;
	}

	public Hero getTradeCharacter() {
		return tradeCharacter;
	}

	/**
	 * 是不是处在交易中 返回true表示正在交易中
	 * 
	 * @return
	 */
	public boolean isTrading() {
		if (tradeStatus == TradeStatus.idle) {
			return false;
		}
		return true;

	}

	/**
	 * 验证请求是否超过上次请求时间 tRUE表示可以接受
	 * 
	 * @return
	 */
	public boolean isTime(int characterId) {
		Long time = trackRequestMap.get(characterId);
		// && System.currentTimeMillis() - time < 30 * 1000
		if (time != null) {
			return false;
		}
		trackRequestMap.put(characterId, System.currentTimeMillis());
		return true;
	}

	public void removeInviteTime(int otherId) {
		trackRequestMap.remove(otherId);
	}

	public void setTradeCharacter(Hero tradeCharacter) {
		this.tradeCharacter = tradeCharacter;
	}

	@Override
	public Hero getCharacter() {
		return character;
	}

	public Horse getHorse() {
		return horse;
	}

	public int getCopper() {
		return copper;
	}

	public int getYuanbao() {
		if (yuanbao < 1) {
			this.yuanbao = 0;
		}
		return yuanbao;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	// =================================================
	// 清空重置交易中的物品列表,以及交易状态
	/**
	 * 清空玩家与其他玩家交易信息
	 */
	@Override
	public void resetItem() {
		if (goodslistMap != null) {
			goodslistMap.clear();
		}

		this.horse = null;
		this.copper = 0;
		this.yuanbao = 0;
		this.tradeStatus = TradeStatus.idle;
		this.tradeCharacter = null;
		if (trackRequestMap != null) {
			this.trackRequestMap.clear();
		}

	}

	// 收到其他玩家请求交易的信息
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.character.controller.MyTradeController#onRequestTrade (net.snake.bean.character.Character)
	 */
	@Override
	public void onRequestTrade(Hero other) {
		if (isTrading()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 626));
			return;
		}
		if (!isTime(other.getId())) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 627));
			return;
		}
		other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 628));
		character.sendMsg(new TradeMsg10824(other));
	}

	// 请求与其他角色交易
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.character.controller.MyTradeController#requestTradeWith (net.snake.bean.character.Character)
	 */
	@Override
	public void requestTradeWith(Hero other) {
		if (other == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 629));
			return;
		}

		if (!other.getCharacterLimitConfig().isOtherAbleTradeMe()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 630));
			return;
		}

		if (!character.getEyeShotManager().isContains(other)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 631));
			return;
		}
		if (isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 632));
			return;
		}
		other.getMyTradeController().onRequestTrade(character);
	}

	// 锁定物品
	@Override
	public void lockTrade() {
		if (!isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 633));
			return;
		}
		if (!character.getEyeShotManager().isContains(tradeCharacter)) {
			cancelTrade();
			TradeMsg10840 tradeMsg10840 = new TradeMsg10840(13502);
			character.sendMsg(tradeMsg10840);
			tradeCharacter.sendMsg(tradeMsg10840);
			return;
		}
		if (this.tradeStatus == TradeStatus.checking) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 634));
			return;
		}
		if (this.tradeStatus == TradeStatus.trading) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 635));
			return;
		}
		this.tradeStatus = TradeStatus.checking;
		TradeSuodingMsg10838 changeTradeSta = new TradeSuodingMsg10838(character.getId(), CommonUseNumber.byte1);
		character.sendMsg(changeTradeSta);
		this.tradeCharacter.sendMsg(changeTradeSta);
	}

	// 不锁定物品

	@Override
	public void unLockTrade() {

	}

	// 同意交易
	@Override
	public void trade() {
		if (!isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 633));
			return;
		}
		if (!character.getEyeShotManager().isContains(tradeCharacter)) {
			cancelTrade();
			TradeMsg10840 tradeMsg10840 = new TradeMsg10840(13502);
			character.sendMsg(tradeMsg10840);
			tradeCharacter.sendMsg(tradeMsg10840);
			return;
		}
		if (this.tradeStatus == TradeStatus.trading) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 636));
			return;
		}
		if (this.tradeStatus != TradeStatus.checking) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 637));
			return;
		}
		if (this.tradeCharacter.getMyTradeController().getTradeStatus() == TradeStatus.perTrade) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 638));
			return;
		}
		if (!ckeckTrading()) {
			cancelTrade();
			return;
		}
		if (this.tradeCharacter.getMyTradeController().getTradeStatus() == TradeStatus.checking) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 639));
			this.tradeStatus = TradeStatus.trading;
			TradeSuodingMsg10838 changeTradeSta = new TradeSuodingMsg10838(character.getId(), (byte) 2);
			character.sendMsg(changeTradeSta);
			this.tradeCharacter.sendMsg(changeTradeSta);
			return;
		}
		if (this.tradeCharacter.getMyTradeController().getTradeStatus() == TradeStatus.trading) {
			this.tradeStatus = TradeStatus.trading;
			finishTrade();
			return;
		}
		character.sendMsg(new TradeMsg10840(13503));
	}

	public boolean ckeckTrading() {
		if (character.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() <= 0 && horse == null && character.getMyTradeController().getHorse() != null) {
			// 很抱歉，您的坐骑槽位数已满，本次交易失败了
			character.sendMsg(new TradeMsg10840(13504));
			// 很抱歉，对方坐骑槽位已满，交易失败
			tradeCharacter.sendMsg(new TradeMsg10840(13505));
			return false;
		}
		if (character.getCopper() + tradeCharacter.getMyTradeController().getCopper() > MaxLimit.BAG_COPPER_MAX) {
			// 很抱歉，您的背包携带的铜钱数过多，本次交易失败了
			character.sendMsg(new TradeMsg10840(13506));
			// 很抱歉，对方的背包携带的铜钱数过多，本次交易失败了
			tradeCharacter.sendMsg(new TradeMsg10840(13507));
			return false;
		}
		if (character.getAccount().getYuanbao() + tradeCharacter.getMyTradeController().getYuanbao() > MaxLimit.INGOT_MAX) {
			// 很抱歉，您的账户携带的元宝数过多，本次交易失败了
			character.sendMsg(new TradeMsg10840(13508));
			// 很抱歉，对方的账户携带的元宝数过多，本次交易失败了
			tradeCharacter.sendMsg(new TradeMsg10840(13509));
			return false;
		}
		if (horse != null && tradeCharacter.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() <= 0 && tradeCharacter.getMyTradeController().getHorse() == null) {
			// 很抱歉，对方的坐骑槽位数已满，本次交易失败了
			character.sendMsg(new TradeMsg10840(13505));
			tradeCharacter.sendMsg(new TradeMsg10840(13504));
			return false;
		}
		if (tradeCharacter.getCopper() + character.getMyTradeController().getCopper() > MaxLimit.BAG_COPPER_MAX) {
			// "很抱歉，交易的铜钱数超过了对方背包的负荷，本次交易失败了"
			character.sendMsg(new TradeMsg10840(13507));
			// 很抱歉，您的账户携带的铜钱数过多，本次交易失败了
			tradeCharacter.sendMsg(new TradeMsg10840(13506));
			return false;
		}
		if (character.getAccount().getYuanbao() + character.getMyTradeController().getYuanbao() > MaxLimit.INGOT_MAX) {
			// 很抱歉，交易的元宝数超过了对方账户的负荷，本次交易失败了
			character.sendMsg(new TradeMsg10840(13509));
			// "很抱歉，您的账户携带的元宝数过多，本次交易失败了"
			tradeCharacter.sendMsg(new TradeMsg10840(13508));
			return false;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner()
				.isHasSpaceForRemoveAndAddGoodsInTrading(goodslistMap.values(), tradeCharacter.getMyTradeController().getGoodsCollection())) {
			character.sendMsg(new TradeMsg10840(13510));
			tradeCharacter.sendMsg(new TradeMsg10840(13511));
			return false;
		}
		if (!tradeCharacter.getCharacterGoodController().getBagGoodsContiner()
				.isHasSpaceForRemoveAndAddGoodsInTrading(tradeCharacter.getMyTradeController().getGoodsCollection(), getGoodsCollection())) {
			character.sendMsg(new TradeMsg10840(13511));
			tradeCharacter.sendMsg(new TradeMsg10840(13510));
			return false;
		}
		return true;
	}

	/**
	 * 交易双方确认进行物品金钱等交换
	 */
	public void finishTrade() {
		deleteTradeGoods();
		tradeCharacter.getMyTradeController().deleteTradeGoods();
		// 添加
		changeGoodsToTradeCharacter();
		tradeCharacter.getMyTradeController().changeGoodsToTradeCharacter();
		// 初始化
		tradeCharacter.getMyTradeController().resetItem();
		resetItem();
	}

	public void changeGoodsToTradeCharacter() {
		if (getCopper() > 0) {
			CharacterPropertyManager.changeCopper(tradeCharacter, getCopper(), CopperAction.ADD_DEAL);
		}
		// 删除金钱
		int ingotBack = getYuanbao();
		this.yuanbao = 0;
		this.copper = 0;
		if (ingotBack > 0) {
			character.getAccount().getAccountMonneyManager().updateReduceTradeDbAccountYuanbao(ingotBack);
			tradeCharacter.getAccount().getAccountMonneyManager().changeTradeAddYuanbao(tradeCharacter, ingotBack);
			// character.getAccount().getAccountMonneyLogManager().logTradeYuanbaoLog(character, ingotBack, tradeCharacter);
			// GameServer.dataLogManager.logRMBBuyItem("1", goodsId, unitPrice, count, uid, userInfo)
		}
		if (horse != null) {
			horse.setTradeIndex(-1);
			tradeCharacter.getCharacterHorseController().getBagHorseContainer().receiveOthersHorse(horse);
		}
		Collection<CharacterGoods> collection = getGoodsCollection();
		tradeCharacter.getCharacterGoodController().addGoodsToBag(collection);
		tradeCharacter.sendMsg(new GoodToBadEffectMsg11170(collection, character));
		tradeCharacter.sendMsg(new TradeMsg10840(this, getCopper(), ingotBack));
	}

	/**
	 * 删除用于交易的物品
	 */
	public void deleteTradeGoods() {
		if (horse != null) {
			horse.setTradeIndex(-1);
			character.getCharacterHorseController().getBagHorseContainer().releaseHorse(horse);
		}
		// 物品删除
		Collection<CharacterGoods> collection = getGoodsCollection();
		CharacterGoodController cgc = character.getCharacterGoodController();
		if (collection.size() > 0) {
			for (CharacterGoods cg : collection) {
				cg.setTradeIndex(-1);
				cgc.deleteCharacterGoods(cg);
			}
		}
	}

	/*
	 * 取消交易,当一些事情发生时 (non-Javadoc)
	 * 
	 * @see net.snake.bean.character.controller.MyTradeController#cancelTrade()
	 */
	@Override
	public void cancelTrade() {
		if (!isTrading()) {
			return;
		}
		tradeGoodToBagPack();
		tradeCharacter.getMyTradeController().tradeGoodToBagPack();
		// 初始化
		tradeCharacter.getMyTradeController().resetItem();
		resetItem();
	}

	/**
	 * 交易取消 恢复用于交易的物品加钱等
	 */
	public void tradeGoodToBagPack() {
		int copperBack = getCopper();
		int yuanbaoBack = getYuanbao();
		this.copper = 0;
		this.yuanbao = 0;
		CharacterPropertyManager.changeCopper(character, copperBack, CopperAction.TAKEFORSTORAGE);
		character.getAccount().getAccountMonneyManager().changeCancelRoleTradeYuanbao(character, yuanbaoBack);
		if (horse != null) {
			horse.setTradeIndex(-1);
		}
		// 物品删除
		Collection<CharacterGoods> collection = getGoodsCollection();
		if (collection.size() > 0) {
			for (CharacterGoods cg : collection) {
				cg.setTradeIndex(-1);
			}
		}
	}

	@Override
	public void setHorse(Horse horse) {
		this.horse = horse;
	}

	@Override
	public void setCopper(int copper) {
		this.copper = copper;
	}

	@Override
	public void setYuanbao(int ingot) {
		this.yuanbao = ingot;
	}

	public Collection<CharacterGoods> getGoodsCollection() {
		return goodslistMap.values();
	}

	@Override
	public boolean createTrading(Hero other) {
		if (other == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 640));
			return false;
		}
		if (!character.getEyeShotManager().isContains(other)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 641));
			return false;
		}
		if (other.getMyTradeController().isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 642));
			return false;
		}
		if (character.getMyTradeController().isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 643));
			return false;
		}
		createTradeInit(other);
		other.getMyTradeController().createTradeInit(character);
		// other.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP,
		// "玩家接受了你的交易请求"));
		other.sendMsg(new TradeMsg10828(character));
		character.sendMsg(new TradeMsg10828(other));
		character.getMyFriendManager().getRoleLateLinkManager().addOrUpdateLateLink(other.getId());
		other.getMyFriendManager().getRoleLateLinkManager().addOrUpdateLateLink(character.getId());
		return true;
	}

	public void createTradeInit(Hero other) {
		setTradeCharacter(other);
		setTradeStatus(TradeStatus.perTrade);
		this.copper = 0;
		this.yuanbao = 0;
		goodslistMap.clear();
	}

	/*
	 * (non-Javadoc) 用于定时检查二者距离是否过大
	 * 
	 * @see net.snake.bean.character.controller.MyTradeController#tradeInWheel(long)
	 */
	@Override
	public void update(long now) {
		if (!isTrading()) {
			return;
		}
		if (st.isIntervalOK(now)) {
			Hero trader = getTradeCharacter();
			if (!character.getEyeShotManager().isContains(trader)) {
				cancelTrade();
				TradeMsg10840 tradeMsg10840 = new TradeMsg10840(13502);
				character.sendMsg(tradeMsg10840);
				trader.sendMsg(tradeMsg10840);
			}
		}
	}

	/**
	 * 返回true 允许操作
	 */
	public boolean isGoodOperate() {
		if (!isTrading()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 644));
			return false;
		}
		if (tradeStatus == TradeStatus.perTrade) {
			return true;
		}
		if (tradeStatus == TradeStatus.checking) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 645));
			return false;
		}
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 646));
		return false;
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		int size = 0;
		if (tradeCharacter != null) {
			size++;
		}
		if (horse != null) {
			size++;
		}
		return size;
	}
}
