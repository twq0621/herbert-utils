package net.snake.gamemodel.gift.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.gamemodel.gift.response.GiftPacksOnlineMsg50756;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 角色每天累计在线礼包管理器
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleDayLineGiftPacks {
	public static int dayOnlineType = 1;
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	// private CharacterGiftpacks nowGift;
	private Date upDateTime;
	private Date startDate;
	int count = 0;// 单位分钟

	public RoleDayLineGiftPacks(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void init() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		Date lastDate = character.getLastdate();
		if (lastDate == null) {
			lastDate = new Date();
		}
		Calendar oldCal = dateToCalendar(lastDate);
		Calendar nowCal = dateToCalendar(new Date());
		if (oldCal.get(Calendar.DAY_OF_YEAR) != nowCal.get(Calendar.DAY_OF_YEAR)) {
			character.setDayOnline(0);
		}
		initUpdate(new Date());
		startDate = new Date();
		int onlineTime = getOnlineTime();
		count = onlineTime / 60000;
		myCharacterGiftpacksManger.initAchieve(dayOnlineType, count);
	}

	private void initUpdate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Calendar calendarUpdate = new GregorianCalendar();
		calendarUpdate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
		upDateTime = calendarUpdate.getTime();
	}

	/**
	 * 时间转化
	 * 
	 * @return
	 */
	private Calendar dateToCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 单位毫秒
	 * 
	 * @return
	 */
	public int getOnlineTime() {
		int time = myCharacterGiftpacksManger.getCharacter().getDayOnline();
		long online = System.currentTimeMillis() - startDate.getTime();
		int onlineTime = (int) (online + time);
		return onlineTime;
	}

	public void sendOnlineTime() {
		int time = getOnlineTime() / 1000;// 客户端只需要到秒级
		if (time > 120 * 60) {// 最高计时两个小时
			time = 120 * 60;
		}
		Hero character = myCharacterGiftpacksManger.getCharacter();
		character.sendMsg(new GiftPacksOnlineMsg50756(1, time));
	}

	/**
	 * 更新调用此方法
	 */
	public void update() {
		long now = System.currentTimeMillis();
		if (now < upDateTime.getTime()) {
			int onlineTime = getOnlineTime();
			int fen = onlineTime / 60000;
			if (fen > count) {
				myCharacterGiftpacksManger.getCharacter().getMyCharacterAchieveCountManger().addOrUpdateMemoryMapCount(dayOnlineType, count);
				count = fen;
				myCharacterGiftpacksManger.updateAchieve(dayOnlineType, count);
			}
		} else {
			Date nowDate = new Date();
			initUpdate(nowDate);
			myCharacterGiftpacksManger.getCharacter().setDayOnline(0);
			startDate = nowDate;
			int onlineTime = getOnlineTime();
			count = onlineTime / 60000;
			myCharacterGiftpacksManger.clearAchieve(dayOnlineType, count);
		}
	}

	public void downLine() {
		update();
		myCharacterGiftpacksManger.getCharacter().setDayOnline(getOnlineTime());
	}
}
