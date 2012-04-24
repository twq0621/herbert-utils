package net.snake.gamemodel.instance.logic;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanUtils;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.InstanceDayStat;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;



/**
 * 副本每日进入次数计数器
 * 
 * @author serv_dev
 * 
 */
public class MyInstanceDayStatManager {
	private static Logger logger = Logger.getLogger(MyInstanceDayStatManager.class);
	/**
	 * 该类用于记录玩家对副本的重置记录的统计
	 */
	private Hero character;
	// TODAY日统计的日期// 副本日统计的次数 副本模型id(key) 副本日统计(value)
	private Date today;
	private Map<Integer, InstanceDayStat> dayStatMap;

	public MyInstanceDayStatManager(Hero character) {
		this.character = character;
	}

	public void destroy() {
		if (dayStatMap != null) {
			dayStatMap.clear();
			dayStatMap = null;
		}

	}

	/**
	 * 初始化玩家今日副本进入次数记录
	 */
	public void init() {
		try {
			today = new Date(onlyYMD());
			dayStatMap = InstanceDataProvider.getInstance().getCharacterInstanceDayStat(character.getId(), today);
			deletePriCount();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void deletePriCount() {
		InstanceDataProvider.getInstance().deletePriCount(character.getId(), today);
	}

	/**
	 * 检查当前记录是否是今日记录
	 */
	private void checktoday() {
		if (onlyYMD() != today.getTime()) {// 不是今天的数据 则要把旧的统计统计到数据库中
			// TODO 刷新instanceMap注意同步与异步
			dayStatMap = new HashMap<Integer, InstanceDayStat>();
			today = new Date(onlyYMD());
		}
	}

	/**
	 * 获取今日
	 * 
	 * @return
	 */
	private static long onlyYMD() {
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(System.currentTimeMillis());
		t.set(Calendar.HOUR_OF_DAY, 0);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
		return t.getTimeInMillis();
	}

	/**
	 * 获得某玩家今日免费进入副本的进入次数
	 * 
	 * @param instancemodelid
	 * @return
	 */
	public int getTodayResetCount(int instancemodelid) {
		checktoday();
		InstanceDayStat instancedaystat = dayStatMap.get(instancemodelid);
		if (instancedaystat == null) {
			return 0;
		}
		return instancedaystat.getResetinstancecount();
//		return 0;
	}

	public InstanceDayStat getStat(int instancemodelid) {
		checktoday();
		InstanceDayStat instancedaystat = dayStatMap.get(instancemodelid);
		return instancedaystat;
	}

	/**
	 * 获得某玩家今日某副本的进入次数
	 * 
	 * @param instancemodelid
	 * @return
	 */
	public int getTodayFubenLingCount(int instancemodelid) {
		checktoday();
		InstanceDayStat instancedaystat = dayStatMap.get(instancemodelid);
		if (instancedaystat == null) {
			return 0;
		}
		return instancedaystat.getFubenlingCount();
	}

	/**
	 * 获得某玩家今日进入某副本的进入次数
	 * 
	 * @param instancemodelid
	 * @return
	 */
	public int getTodayEnterCount(int instancemodelid) {
		checktoday();
		InstanceDayStat instancedaystat = dayStatMap.get(instancemodelid);
		if (instancedaystat == null) {
			return 0;
		}
		return instancedaystat.getFubenlingCount() + instancedaystat.getResetinstancecount();
	}

	/**
	 * 玩家进入副本时调用本方法 更新玩家进入副本次数记录
	 * 
	 * @param modelid
	 * @param isUseGood
	 */
	public void setInstanceID(int modelid, boolean isUseGood) {
		checktoday();
		InstanceDayStat currentCount = dayStatMap.get(modelid);
		if (currentCount == null) {
			currentCount = new InstanceDayStat();
			currentCount.setCharacterid(character.getId());
			currentCount.setInstancemodelid(modelid);
			currentCount.setStatdate(today);
			currentCount.setResetinstancecount((short) 0);
			currentCount.setFubenlingCount(0);
			dayStatMap.put(modelid, currentCount);
		}
		if (!isUseGood) {
			currentCount.setResetinstancecount((short) (currentCount.getResetinstancecount() + 1));
		} else {
			currentCount.setFubenlingCount(currentCount.getFubenlingCount() + 1);
			character.getMyCharacterInstanceManager().setUseFubenLing(false);
		}

		final InstanceDayStat copyInstanceDaystat = new InstanceDayStat();
		BeanUtils.copyProperties(currentCount, copyInstanceDaystat);
		// 异步更新数据库
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					InstanceDataProvider.getInstance().updateInstanceDayStat(copyInstanceDaystat);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public Collection<InstanceDayStat> getCollection() {
		if (dayStatMap == null || dayStatMap.size() == 0) {
			return null;
		}
		return dayStatMap.values();
	}

	public void clearCount() {
		dayStatMap = new HashMap<Integer, InstanceDayStat>();
	}

}
