package net.snake.gamemodel.gift.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.GameServer;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksGradeListMsg50712;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

/**
 * 角色升级礼包管理器 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleGradeGiftPacksManger {
	private CharacterGiftpacks nowGift;
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private boolean isFirst = true; // a是否第一次发送列表

	public RoleGradeGiftPacksManger(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void init() {
		if (nowGift == null) {
			List<GiftPacks> gplist = GiftPacksManager.getInstance().getGradeGiftList();
			if(gplist.size()<=0){
				return;
			}
			GiftPacks gp = gplist.get(0);
			nowGift = new CharacterGiftpacks();
			nowGift.setCharacterId(myCharacterGiftpacksManger.getCharacter().getId());
			nowGift.setGp(gp);
			nowGift.setGiftPacksId(gp.getId());
			nowGift.setIsGet(false);
			nowGift.setIsOwner(false);
			nowGift.setTime(0l);
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					CharacterGiftpacksManager.getInstance().insert(nowGift);
				}
			});
		}
	}

	/**
	 * 添加打开升级礼包
	 */
	public void addGradeGiftPacks() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		GiftPacks nextGp = getNextGradeGift(this.nowGift.getGp());
		if (nextGp == null) {
			nowGift.setIsGet(true);
			nowGift.setIsOwner(true);
			CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
			return;
		}
		nowGift.setGiftPacksId(nextGp.getId());
		nowGift.setGp(nextGp);
		nowGift.setIsGet(false);
		nowGift.setIsOwner(false);
		nowGift.setTime(0l);
		CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
	}

	private GiftPacks getNextGradeGift(GiftPacks nowgp) {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getGradeGiftList();
		int grade = nowgp.getGradeLimit();
		for (GiftPacks gp : gplist) {
			if (gp.getGradeLimit() > grade) {
				return gp;
			}
		}
		return null;
	}

	/**
	 * 发送升级礼包列表
	 */
	public void sendGradeGiftList() {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getGradeGiftList();
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksGradeListMsg50712(nowGift, gplist, myCharacterGiftpacksManger.getCharacter()));
		this.isFirst = false;
	}

	/**
	 * 打开升级礼包
	 * 
	 * @param gm
	 */
	public void useGoodDoSomething(CharacterGoods cg) {
		int giftId = cg.getGoodModel().getGiftPacksId();
		Hero character = myCharacterGiftpacksManger.getCharacter();
		GiftPacks gp = nowGift.getGp();
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 653));
			return;
		}
		if (nowGift.getGiftPacksId() != giftId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 655));
			return;
		}
		if (nowGift.getIsOwner()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 656));
			return;
		}
		if (gp.getGradeLimit() <= character.getGrade()) {
			nowGift.setIsGet(true);
		} else {
			nowGift.setIsGet(false);
		}
		if (!nowGift.getIsGet()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 657));
			return;
		}
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 659));
			return;
		}
		List<CharacterGoods> remove = new ArrayList<CharacterGoods>();
		remove.add(cg);
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForRemoveAndAddGoods(remove, gp.getGoodlist(character.getPopsinger()))) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 660));
			return;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(remove, gp.getGoodlist(character.getPopsinger()));
		addGradeGiftPacks();
		if (!isFirst) {
			sendGradeGiftList();
		}
	}

	public CharacterGiftpacks getNowGift() {
		return nowGift;
	}

	public void setNowGift(CharacterGiftpacks nowGift) {
		this.nowGift = nowGift;
	}

}
