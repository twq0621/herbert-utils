package net.snake.gamemodel.gift.logic;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.snake.GameServer;
import net.snake.commons.DateUtil;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksDayQiandaoMsg50758;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

/**
 * 每日签到礼包 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class RoleDayQiandaoGiftPacksManager {

	private CharacterGiftpacks nowGift;
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private Date upDateTime;

	public RoleDayQiandaoGiftPacksManager(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public CharacterGiftpacks getNowGift() {
		return nowGift;
	}

	public void setNowGift(CharacterGiftpacks nowGift) {
		this.nowGift = nowGift;
	}

	public void init() {
		if (nowGift == null) {
			List<GiftPacks> gplist = GiftPacksManager.getInstance().getDayQiandaoGiftList();
			if (gplist.size() <= 0) {
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
			nowGift.setQiandaoDate(new Date());
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					CharacterGiftpacksManager.getInstance().insert(nowGift);
				}
			});
		}
		initUpdate(new Date());
	}

	public boolean isSameDate(Date date) {
		Date giftDate = nowGift.getQiandaoDate();
		if (giftDate == null) {
			return false;
		}
		return DateUtil.isSameDay(giftDate, date);
	}

	public void update() {
		long now = System.currentTimeMillis();
		if (upDateTime == null || now < upDateTime.getTime()) {
			return;
		}
		if (isSameDate(new Date())) {
			return;
		}
		initUpdate(new Date());
		nowGift.setIsOwner(false);
		nowGift.setQiandaoDate(new Date());
		sendQiandaoInfo();

	}

	private void initUpdate(Date date) {
		upDateTime = DateUtil.dateToNextDay(date);
	}

	/**
	 * 打开升级礼包
	 * 
	 * @param gm
	 */
	public void qiandao() {

		Hero character = myCharacterGiftpacksManger.getCharacter();
		if (!isSameDate(new Date())) {
			nowGift.setIsOwner(false);
		}
		if (nowGift.getIsOwner()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 656));
			return;
		}
		GiftPacks gp = nowGift.getGp();
		nowGift.setIsOwner(true);
		nowGift.setQiandaoDate(new Date());
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		Collection<CharacterGoods> add = gp.getGoodlist(character.getPopsinger());
		if (add != null && add.size() > 0) {
			character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, gp.getGoodlist(character.getPopsinger()));
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CharacterGiftpacksManager.getInstance().update(nowGift);
			}
		});
		sendQiandaoInfo();
	}

	public void sendQiandaoInfo() {
		if (!isSameDate(new Date())) {
			nowGift.setIsOwner(false);
		}
		Hero character = myCharacterGiftpacksManger.getCharacter();
		character.sendMsg(new GiftPacksDayQiandaoMsg50758(nowGift, character));
	}
}
