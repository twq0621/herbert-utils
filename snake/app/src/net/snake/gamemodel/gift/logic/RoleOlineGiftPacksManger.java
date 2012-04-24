package net.snake.gamemodel.gift.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import net.snake.gamemodel.gift.response.GiftPacksMeetMsg50716;
import net.snake.gamemodel.gift.response.GiftPacksOnlineListMsg50752;
import net.snake.gamemodel.gift.response.GiftPacksOnlineMsg50756;
import net.snake.gamemodel.gift.response.GiftPacksUpdate50798;
import net.snake.gamemodel.guide.bean.CharacterWeekTime;
import net.snake.gamemodel.guide.persistence.CharacterWeekTimeManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

/**
 * 角色每月在线时间礼包 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleOlineGiftPacksManger {
	public static int monthOnlineType = 3;
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private CharacterGiftpacks nowGift;
	private CharacterGiftpacks priGift;
	private CharacterWeekTime priWeek;// 上月时间
	private CharacterWeekTime nowWeek;// 当月
	private Date updateTime;
	private Date startDate;
	private int count = 0;// 单位小时

	public RoleOlineGiftPacksManger(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private void addNowMonthDate(Hero character, Calendar calendar) {
		nowWeek = new CharacterWeekTime();
		nowWeek.setfCharacterId(character.getId());
		int nowY = calendar.get(Calendar.YEAR);
		int nowW = calendar.get(Calendar.WEEK_OF_YEAR);
		nowWeek.setfWeek(nowW);
		nowWeek.setfYear(nowY);
		nowWeek.setfOnlineTime(0);
		return;
	}

	public void init() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		startDate = new Date();
		initUpdate(startDate);
		Date olddate = character.getLastdate();
		Calendar nowCal = DateUtil.dateToPriDayCalendar(startDate);
		Calendar oldCal = DateUtil.dateToPriDayCalendar(olddate);
		int priWeek = oldCal.get(Calendar.WEEK_OF_YEAR);
		int nowWeek = nowCal.get(Calendar.WEEK_OF_YEAR);
		int chars = Math.abs(nowWeek - priWeek);
		if (chars > 1) {
			if (nowGift != null) {
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						CharacterGiftpacksManager.getInstance().delete(nowGift);
					}
				});
			}
			this.priWeek = null;
			this.nowWeek = null;
			addNowMonthDate(character, nowCal);
			count = getMonthOnlineTime() / 3600;
			myCharacterGiftpacksManger.initAchieve(monthOnlineType, count);
			return;
		}
		initOnlineTime();
		count = getMonthOnlineTime() / 3600;
		if (chars == 1) {
			updateGiftPicks(character, oldCal);
			myCharacterGiftpacksManger.initAchieve(monthOnlineType, count);
			return;
		}
		myCharacterGiftpacksManger.initAchieve(monthOnlineType, count);
	}

	/**
	 * 更新在线礼包
	 * 
	 * @param character
	 * @param oldCal
	 */
	public void updateGiftPicks(Hero character, Calendar oldCal) {
		priGift = this.nowGift;
		GiftPacks gp = gotGiftPacks();
		if (gp != null) {
			CharacterGiftpacks cgp = new CharacterGiftpacks();
			cgp.setCharacterId(character.getId());
			cgp.setGiftPacksId(gp.getId());
			cgp.setGp(gp);
			cgp.setIsGet(true);
			cgp.setIsOwner(false);
			cgp.setTime(0l);
			int priYear = oldCal.get(Calendar.YEAR);
			int priWeek = oldCal.get(Calendar.WEEK_OF_YEAR);
			cgp.setYear(priYear);
			cgp.setWeek(priWeek);
			this.nowGift = cgp;
		} else {
			this.nowGift = null;
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				if (priGift != null) {
					CharacterGiftpacksManager.getInstance().delete(priGift);
				}
				if (nowGift != null) {
					CharacterGiftpacksManager.getInstance().insert(nowGift);
				}
			}
		});
	}

	/**
	 * 获取当前更新的礼包
	 * 
	 * @return
	 */
	private GiftPacks gotGiftPacks() {
		if (this.priWeek == null) {
			return null;
		}
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getOnlineGiftList();
		for (int i = gplist.size() - 1; i >= 0; i--) {
			GiftPacks gp = gplist.get(i);
			int time = gp.getOnlineTimeLimit() * 3600;
			if (time <= this.priWeek.getfOnlineTime()) {
				return gp;
			}
		}
		return null;
	}

	public void initOnlineTime() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		Calendar calendar = DateUtil.dateToPriMonDay(new Date());
		int priW = calendar.get(Calendar.WEEK_OF_YEAR);
		this.priWeek = CharacterWeekTimeManager.getInstance().selecteListByCharacterId(character.getId(), priW);
		Calendar nowCal = DateUtil.dateToPriDayCalendar(new Date());
		int nowW = nowCal.get(Calendar.WEEK_OF_YEAR);
		this.nowWeek = CharacterWeekTimeManager.getInstance().selecteListByCharacterId(character.getId(), nowW);
		if (this.nowWeek == null) {
			addNowMonthDate(character, nowCal);
		}
	}

	/**
	 * 初始化更新时间
	 * 
	 * @param date
	 */
	public void initUpdate(Date date) {
		updateTime = DateUtil.dateToNextMonDay(date).getTime();
	}

	/**
	 * 见面有礼领取时间
	 * 
	 * @return
	 */
	public boolean openOnlineGiftPacks() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		if (this.nowGift == null) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 667));
			return false;
		}
		if (this.nowGift.getIsOwner()) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 668));
			return false;
		}
		GiftPacks gp = this.nowGift.getGp();
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return false;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 659));
			return false;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		addGiftPacksMeet();
		int t = (int) ((System.currentTimeMillis() - startDate.getTime()) / 1000);
		int time = nowWeek.getfOnlineTime() + t;
		sendOnlineGiftList();
		character.sendMsg(new GiftPacksOnlineMsg50756(0, time));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 988, gp.getLijin() + ""));
		return true;
	}

	public void sendOnlineTime() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		int time = getMonthOnlineTime();
		character.sendMsg(new GiftPacksOnlineMsg50756(0, time));
	}

	private void addGiftPacksMeet() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		nowGift.setIsGet(true);
		nowGift.setIsOwner(true);
		CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
	}

	/**
	 * 发送是否有可以领取奖品
	 */
	public void sendHaveGetGiftPacksMsg() {
		if (this.nowGift == null || this.nowGift.getIsOwner()) {
			return;
		}
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksUpdate50798(GiftPacks.onlineType));
	}

	public void sendOnlineGiftList() {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getOnlineGiftList();
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksOnlineListMsg50752(this, gplist, myCharacterGiftpacksManger.getCharacter()));
	}

	/**
	 * 更新调用此方法
	 */
	public void update() {
		if(this.nowWeek==null){
			return;
		}
		long now = System.currentTimeMillis();
		if (updateTime == null || now < updateTime.getTime()) {
			int h = getMonthOnlineTime() / 3600;
			if (h > count) {
				count = h;
				myCharacterGiftpacksManger.updateAchieve(monthOnlineType, count);
			}
			return;
		}
		Date nowDate = new Date();
		initUpdate(nowDate);
		updateOnlineTime();
		Hero character = myCharacterGiftpacksManger.getCharacter();
		updateGiftPicks(character, DateUtil.dateToPriMonDay(nowDate));
		sendOnlineGiftList();
		sendHaveGetGiftPacksMsg();
		count = getMonthOnlineTime() / 3600;
		myCharacterGiftpacksManger.clearAchieve(monthOnlineType, count);
	}

	public void clearAchieve(Hero character) {
		character.getMyCharacterAchieveCountManger().getGiftPacksCount().clearGiftPacksCount(monthOnlineType, count);
	}

	/**
	 * 更新成就状态
	 */
	public void updateAchieve() {
		myCharacterGiftpacksManger.getCharacter().getMyCharacterAchieveCountManger().getGiftPacksCount().giftPacksCount(monthOnlineType, count);
	}

	public void updateOnlineTime() {
		long now = System.currentTimeMillis();
		Hero character = myCharacterGiftpacksManger.getCharacter();
		int time = (int) ((now - startDate.getTime()) / 1000);
		this.nowWeek.setfOnlineTime(this.nowWeek.getfOnlineTime() + time);
		CharacterWeekTimeManager.getInstance().asynUpdateCharacterGiftpacks(character, this.nowWeek);
		this.priWeek = this.nowWeek;
		nowWeek = null;
		startDate = new Date();
		Calendar calendar = DateUtil.dateToPriDayCalendar(new Date());
		addNowMonthDate(character, calendar);

	}

	public void downline() {
		update();
		updateNowMonthTime();
	}

	private void updateNowMonthTime() {
		int time = getMonthOnlineTime();
		this.nowWeek.setfOnlineTime(time);
		if (this.nowWeek.getfId() == null) {
			CharacterWeekTimeManager.getInstance().asynInsertCharacterGiftpacks(myCharacterGiftpacksManger.getCharacter(), this.nowWeek);
			return;
		}
		CharacterWeekTimeManager.getInstance().asynUpdateCharacterGiftpacks(myCharacterGiftpacksManger.getCharacter(), this.nowWeek);
	}

	public CharacterGiftpacks getNowGift() {
		return nowGift;
	}

	public void setNowGift(CharacterGiftpacks nowGift) {
		this.nowGift = nowGift;
	}

	public CharacterGiftpacks getPriGift() {
		return priGift;
	}

	public void setPriGift(CharacterGiftpacks priGift) {
		this.priGift = priGift;
	}

	/**
	 * 单位秒
	 * 
	 * @return
	 */
	public int getMonthOnlineTime() {
		return (int) ((System.currentTimeMillis() - getStartDate().getTime()) / 1000) + this.nowWeek.getfOnlineTime();
	}

	public CharacterWeekTime getPriWeek() {
		return priWeek;
	}

	public void setPriWeek(CharacterWeekTime priWeek) {
		this.priWeek = priWeek;
	}

	public CharacterWeekTime getNowWeek() {
		return nowWeek;
	}

	public void setNowWeek(CharacterWeekTime nowWeek) {
		this.nowWeek = nowWeek;
	}

}
