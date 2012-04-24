package net.snake.ai.fight.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import net.snake.gamemodel.hero.bean.Hero;

/**
 * 
 * 每日的pk记录
 */
public class CharacterDayPkRecord {
	/** 胜利的次数<失败者id,次数> */
	private Map<Integer, Integer> characterRecordMap = new HashMap<Integer, Integer>();//
	/** 当前记录的日期 */
	private final Calendar recordCalendar = new GregorianCalendar();//
	/** 比较的日期 */
	private final Calendar completeCalendar = new GregorianCalendar();//
	/** 每日最多可获得300点声望值(城战与论剑) */
	//private final int maxShengWang = 300;//

	public CharacterDayPkRecord() {
		recordCalendar.setTime(new Date());
	}

	public Calendar getRecordCalendar() {
		return recordCalendar;
	}

	/**
	 * 插入记录
	 * 
	 * @param failer
	 * @param cnt
	 */
	public void insterRecord(int failer, int cnt) {
		characterRecordMap.put(failer, cnt);
	}

	/**
	 * 胜利的次数
	 * 
	 * @param affecterId
	 * @return
	 */
	public int getPKWinRecord(int affecterId) {
		Integer recordCnt = characterRecordMap.get(affecterId);
		if (recordCnt == null) {
			return 0;
		} else {
			return recordCnt;
		}
	}

	/**
	 * 是否就是今天
	 * 
	 * @param date
	 * @return
	 */
	public boolean isToday(Date date) {
		if (date == null) {
			return false;
		}

		completeCalendar.setTime(date);
		if (recordCalendar.get(Calendar.YEAR) == completeCalendar.get(Calendar.YEAR) && recordCalendar.get(Calendar.MONTH) == completeCalendar.get(Calendar.MONTH)
				&& recordCalendar.get(Calendar.DATE) == completeCalendar.get(Calendar.DATE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置当日日期
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		recordCalendar.setTime(date);
	}

	/**
	 * 是否需要记录
	 * 
	 * @param characterId
	 * @return
	 */
	public boolean noNeedRecord(Hero winer, Hero affecter) {
		int recordCnt1 = getPKWinRecord(affecter.getId());// 获得胜利者战胜失败者的次数
		int recordCnt2 = affecter.getFightController().getPKWinRecord(winer.getId());// 获得失败者战胜胜利者次数
		int recordCnt = recordCnt1 + recordCnt2;
		if (recordCnt >= 5)
			return true;
		return false;
	}

	/**
	 * 胜利了记录一下
	 * 
	 * @param characterId
	 */
	public void win(int characterId) {
		Integer recordCnt = characterRecordMap.get(characterId);
		int addRecord = (recordCnt == null) ? 0 : recordCnt;
		characterRecordMap.put(characterId, ++addRecord);
	}

	/**
	 * 清空记录
	 */
	public void clearRecord() {
		characterRecordMap.clear();
	}

	public Map<Integer, Integer> getCharacterRecordMap() {
		return characterRecordMap;
	}

}
