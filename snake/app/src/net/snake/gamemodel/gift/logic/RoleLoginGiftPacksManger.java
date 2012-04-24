package net.snake.gamemodel.gift.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.DateUtil;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksLoginListMsg50742;
import net.snake.gamemodel.gift.response.GiftPacksLoginMsg50744;
import net.snake.gamemodel.gift.response.GiftPacksUpdate50798;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;



/**
 * 登入有礼 按周做统计 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleLoginGiftPacksManger {
	private static final Logger logger = Logger.getLogger(RoleLoginGiftPacksManger.class);
	public static int loginType = 2;
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	// private CharacterGiftpacks nowGift;
	private List<CharacterGiftpacks> nowList;
	private Calendar oldCal;
	private Calendar nowCal;
	private Date upDateTime;
	private Date startDate;
	private int count = 1;//

	public RoleLoginGiftPacksManger(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void init() {
		nowList = new ArrayList<CharacterGiftpacks>();
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getLoginGiftList();
		int priCount = 0;
		for (GiftPacks gp : gplist) {
			CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(gp.getId());
			if (cgp != null) {
				nowList.add(cgp);
				if (gp.getLoginLimit() > count) {
					priCount = gp.getLoginLimit();
				}
			}
		}
		this.myCharacterGiftpacksManger.initAchieve(loginType, priCount);
		initLoginDateCount();

	}

	private void initLoginDateCount() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		startDate = new Date();
		initUpdate(startDate);
		this.nowCal = dateToPriDayCalendar(startDate);
		Date downlineDate = character.getLastdate();
		this.oldCal = dateToPriDayCalendar(downlineDate);
		if ((nowCal.getTimeInMillis() - oldCal.getTimeInMillis()) > 1000 * 60 * 60 * 24 * 7) {
			resetLoginGiftPacks(character);
			return;
		}
		updateWeekLoginCount(character);

	}

	private void initUpdate(Date date) {
		upDateTime = DateUtil.dateToNextDay(date);
	}

	/**
	 * 时间转化 同时减少一天计算
	 * 
	 * @return
	 */
	private Calendar dateToPriDayCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		return calendar;
	}

	private void resetLoginGiftPacks(Hero character) {
		character.setWeekLoginCount(1);
		count = 1;
		if (nowList.size() > 0) {
			for (CharacterGiftpacks cgp : nowList) {
				myCharacterGiftpacksManger.removeCharacterGiftpacks(cgp);
				CharacterGiftpacksManager.getInstance().asynDeleteCharacterGiftpacks(character, cgp);
			}
		}
		myCharacterGiftpacksManger.clearAchieve(loginType, count);
		myCharacterGiftpacksManger.updateAchieve(loginType, count);
	}

	public void updateWeekLoginCount(Hero character) {
		if (nowCal.get(Calendar.WEEK_OF_YEAR) == oldCal.get(Calendar.WEEK_OF_YEAR)) {
			if (nowCal.get(Calendar.DAY_OF_WEEK) == oldCal.get(Calendar.DAY_OF_WEEK) + 1) {
				count = character.getWeekLoginCount() + 1;
				character.setWeekLoginCount(count);
				updateGiftPacks(character, nowCal);
			}
			myCharacterGiftpacksManger.updateAchieve(loginType, count);
		} else {
			resetLoginGiftPacks(character);
		}
	}

	private void updateGiftPacks(Hero character, Calendar nowCal) {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getLoginGiftList();
		int count = character.getWeekLoginCount();
		for (GiftPacks gp : gplist) {
			if (count >= gp.getLoginLimit()) {
				CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(gp.getId());
				if (cgp == null) {
					cgp = new CharacterGiftpacks();
					cgp.setCharacterId(character.getId());
					cgp.setGiftPacksId(gp.getId());
					cgp.setGp(gp);
					cgp.setWeek(nowCal.get(Calendar.WEEK_OF_YEAR));
					cgp.setIsGet(false);
					cgp.setIsOwner(false);
					nowList.add(cgp);
					myCharacterGiftpacksManger.addCharacterGiftpacks(cgp);
					final CharacterGiftpacks nowcgp = cgp;
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							CharacterGiftpacksManager.getInstance().insert(nowcgp);
						}
					});
				}
			}
		}
	}

	private CharacterGiftpacks getLoginGiftPacks(int giftId) {
		for (CharacterGiftpacks cgp : nowList) {
			if (cgp.getGiftPacksId() == giftId) {
				return cgp;
			}
		}
		return null;
	}

	/**
	 * 见面有礼领取时间
	 * 
	 * @return
	 */
	public boolean openMeetGiftPacks(int giftId) {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		CharacterGiftpacks nowGift = getLoginGiftPacks(giftId);
		if (nowGift == null) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 663));
			return false;
		}
		if (nowGift.getIsOwner()) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 664));
			return false;
		}
		GiftPacks gp = nowGift.getGp();
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return false;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {

			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 665));
			return false;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForRemoveAndAddGoods(null, gp.getGoodlist(character.getPopsinger()))) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 666));
			return false;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, gp.getGoodlist(character.getPopsinger()));
		try {
			character.sendMsg(new GoodToBadEffectMsg11170((byte) 2, gp.getGoodlist(character.getPopsinger())));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		addGiftPacksLogin(nowGift);
		character.sendMsg(new GiftPacksLoginMsg50744(nowGift));
		return true;
	}

	private void addGiftPacksLogin(CharacterGiftpacks nowGift) {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		nowGift.setIsGet(true);
		nowGift.setIsOwner(true);
		CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
	}

	public void sendGiftList() {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getLoginGiftList();
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksLoginListMsg50742(myCharacterGiftpacksManger, gplist, myCharacterGiftpacksManger.getCharacter()));
	}

	/**
	 * 更新调用此方法
	 */
	public void update() {
		long now = System.currentTimeMillis();
		if (upDateTime==null || now < upDateTime.getTime()) {
			return;
		}
		Date nowDate = new Date();
		initUpdate(nowDate);
		this.oldCal = this.nowCal;
		this.nowCal = dateToPriDayCalendar(nowDate);
		updateWeekLoginCount(myCharacterGiftpacksManger.getCharacter());
		sendGiftList();
		sendHaveGetGiftPacksMsg();
		myCharacterGiftpacksManger.updateAchieve(loginType, count);
	}

	public void downline() {
		update();
	}

	public void sendHaveGetGiftPacksMsg() {
		if (nowList==null || nowList.size() == 0) {
			return;
		}
		for (CharacterGiftpacks cgp : nowList) {
			if (!cgp.getIsOwner()) {
				myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksUpdate50798(GiftPacks.loginType));
			}
		}
	}

	public void online() {
		this.myCharacterGiftpacksManger.updateAchieve(loginType, count);
		sendHaveGetGiftPacksMsg();
	}
}
