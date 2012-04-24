package net.snake.gamemodel.hero.logic;

import net.snake.gamemodel.hero.bean.CharDayInComeData;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.DayIncomeManager;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
/**
 * 
 * 每日统计
 * 
 * @author serv_dev
 */
public class DayInComeController extends CharacterController {
	private static Logger logger = Logger.getLogger(DayInComeController.class);
	private int year;
	private int dayofyear;
	private CharDayInComeData data;
	private boolean isinit = false;
	DayIncomeManager manager = DayIncomeManager.getInstance();

	public DayInComeController(Hero character) {
		super(character);
	}

	public CharDayInComeData getCountData() {
		if (CheckTime()) {
			dealUpdate();
		}
		return data;
	}

	/**
	 * 装备数
	 * 
	 * @param value
	 */
	public void dealEquip(int value) {
		deal(value, "equip");
	}

	/**
	 * 经验
	 * 
	 * @param value
	 */
	public void dealExp(int value) {
		deal(value, "exp");
	}

	/**
	 * 
	 * @param value
	 */
	public void dealFinshInstance(int value) {
		deal(value, "instance");
	}

	public void dealFinshTask(int value) {
		deal(value, "task");
	}

	public void dealGethorse(int value) {
		deal(value, "horse");
	}

	public void dealKillBoss(int value) {
		deal(value, "boss");
	}

	public void dealKillMonster(int value) {
		deal(value, "monster");
	}

	public void dealZhenqi(int value) {
		deal(value, "zq");
	}

	public void dealFeastZhenqi(int value) {
		deal(value, "feastZhenqi");
	}

	public void dealFeastExp(int value) {
		deal(value, "feastExp");
	}

	public void dealShengWang(int value) {
		deal(value, "shengwang");
	}

	public void dealFeastGift(int value) {
		deal(value, "feastGift");
	}

	private void deal(int value, String tag) {
		if (!isinit || data == null) {
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("当日收益:" + tag + "增加:" + value);
		}

		if (tag.equals("equip")) {
			data.setfEquip(data.getfEquip() + value);
		}else
		if (tag.equals("exp")) {
			data.setfExp(data.getfExp() + value);
		}else
		if (tag.equals("instance")) {// 完成阵法
			data.setfFinshinstance(data.getfFinshinstance() + value);
		}else
		if (tag.equals("task")) {
			data.setfFinshtask(data.getfFinshtask() + value);
		}else
		if (tag.equals("horse")) {
			data.setfGethorse(data.getfGethorse() + value);
		}else
		if (tag.equals("boss")) {
			data.setfKillboss(data.getfKillboss() + value);
		}else
		if (tag.equals("monster")) {
			data.setfKillmonster(data.getfKillmonster() + value);
		}else
		if (tag.equals("zq")) {
			data.setfZhengqi(data.getfZhengqi() + value);
		}else
		if (tag.equals("feastZhenqi")) {
			data.setfFeastZhengqi(data.getfFeastZhengqi() + value);
		}else
		if (tag.equals("feastExp")) {
			data.setfFeastExp(data.getfFeastExp() + value);
		}else
		if (tag.equals("shengwang")) {
			data.setfShengwang(data.getfShengwang() + value);
		}else
		if (tag.equals("feastGift")) {
			data.setfFeastGift(data.getfFeastGift() + value);
		}
		dealUpdate();
	}

	public void init() {
		try {
			CharDayInComeData inCome = manager.getInComeByCharID(character.getId());
			if (inCome == null) {
				data = new CharDayInComeData();
				data.setfCharacterid(character.getId());
				refreshData();
				isinit = true;
				manager.syncInsert(data);
			} else {
				data = inCome;
				year = data.getfYear();
				dayofyear = data.getfDay();
				isinit = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void refreshData() {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		data.setfEquip(0);
		data.setfExp(0l);
		data.setfFinshinstance(0);
		data.setfFinshtask(0);
		data.setfGethorse(0);
		data.setfKillboss(0);
		data.setfKillmonster(0);
		data.setfZhengqi(0);
		data.setfYear(instance.get(Calendar.YEAR));
		data.setfMonth(0);
		data.setfDay(instance.get(Calendar.DAY_OF_YEAR));
		data.setfFeastExp(0);
		data.setfFeastZhengqi(0);
		data.setfShengwang(0);
		data.setfFeastGift(0);
		year = data.getfYear();
		dayofyear = data.getfDay();
	}

	public void toPersistence() throws SQLException {
		manager.update(data);
	}

	private boolean CheckTime() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int year = c.get(Calendar.YEAR);
		int day = c.get(Calendar.DAY_OF_YEAR);
		return year != this.year || day != this.dayofyear;
	}

	private void dealUpdate() {
		if (!isinit) {
			return;
		}

		if (CheckTime()) {
			refreshData();
		}
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

}
