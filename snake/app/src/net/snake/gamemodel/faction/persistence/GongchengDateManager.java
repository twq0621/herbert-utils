package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.ai.util.ArithmeticUtils;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.fight.bean.DayGongchengDateList;
import net.snake.gamemodel.fight.bean.GongchengDate;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class GongchengDateManager {
	private static Logger logger = Logger.getLogger(GongchengDateManager.class);
	private GongchengDateDAO dao = new GongchengDateDAO(SystemFactory.getCharacterSqlMapClient());
	private Map<Integer, GongchengDate> map = new ConcurrentHashMap<Integer, GongchengDate>();
	private Map<String, DayGongchengDateList> listmap = new ConcurrentHashMap<String, DayGongchengDateList>();
	// 单例实现=====================================
	private static GongchengDateManager instance;

	private GongchengDateManager() {
		init();
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			List<GongchengDate> list = dao.select();
			if (list == null || list.size() == 0) {
				return;
			}
			for (GongchengDate gongcheng : list) {
				addGongcheng(gongcheng);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addGongcheng(GongchengDate gongcheng) {
		map.put(gongcheng.getFactionId(), gongcheng);
		String str = ArithmeticUtils.DateToString(gongcheng.getGongchengDate());
		DayGongchengDateList dayGcDateList = listmap.get(str);
		if (dayGcDateList == null) {
			dayGcDateList = new DayGongchengDateList(gongcheng.getGongchengDate());
			listmap.put(str, dayGcDateList);
		}
		dayGcDateList.addGongchengDate(gongcheng);
	}

	public void removeGongcheng(GongchengDate gongchengDate) {
		map.remove(gongchengDate.getFactionId());
		String str = ArithmeticUtils.DateToString(gongchengDate.getGongchengDate());
		DayGongchengDateList dayGcDateList = listmap.get(str);
		if (dayGcDateList != null) {
			dayGcDateList.removeGongchengDate(gongchengDate);
			if (dayGcDateList.getList().size() < 1) {
				listmap.remove(str);
			}
		}

	}

	long boardMsgTime = 0;
	boolean isTodayFirst = true;
	boolean firstMsg = true;
	boolean secondMsg = true;
	boolean isTodayGongchengInit = false;

	public void sendGongchengMsg(GongchengTsMap gongchengTsMap, int hours) {
		if (hours != FactionCityManager.startHorse - 1) {
			return;
		}
		if (isTodayFirst) {
			boardMsgTime = System.currentTimeMillis();
			isTodayFirst = false;
		}
		this.isTodayGongchengInit = false;
		long time = System.currentTimeMillis() - boardMsgTime;
		if (firstMsg && time > 30 * 60 * 1000) {
			firstMsg = false;
			DayGongchengDateList ddList = getTodayGongChengDateList();
			if (ddList == null || ddList.getList().size() == 0) {
				return;
			}
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14501, 30 + ""));
		} else if (secondMsg && time > 50 * 60 * 1000) {
			secondMsg = false;
			DayGongchengDateList ddList = getTodayGongChengDateList();
			if (ddList == null || ddList.getList().size() == 0) {
				return;
			}
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14501, 10 + ""));
		}
	}

	public void startGongchengInit(GongchengTsMap gongchengTsMap, int hours) {
		if (hours != FactionCityManager.startHorse) {
			return;
		}
		this.boardMsgTime = 0;
		this.isTodayFirst = true;
		this.firstMsg = true;
		this.secondMsg = true;
		if (this.isTodayGongchengInit) {
			return;
		}
		this.isTodayGongchengInit = true;
		DayGongchengDateList ddList = getTodayGongChengDateList();
		if (ddList == null || ddList.getList().size() == 0) {
			return;
		}
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14502));
		FactionCityManager.getInstance().startGongchengInit(gongchengTsMap);
	}

	public DayGongchengDateList getTodayGongChengDateList() {
		Date today = new Date(onlyYMD());
		String str = ArithmeticUtils.DateToString(today);
		DayGongchengDateList dayGcDateList = listmap.get(str);
		return dayGcDateList;
	}

	/**
	 * 获取今日
	 * 
	 * @return
	 */
	private static long onlyYMD() {
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(System.currentTimeMillis());
		t.set(Calendar.HOUR_OF_DAY, FactionCityManager.startHorse);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
		return t.getTimeInMillis();
	}

	public static GongchengDateManager getInstance() {
		if (instance == null) {
			instance = new GongchengDateManager();
		}
		return instance;
	}

	public void insert(GongchengDate gongchengDate) {
		try {
			dao.insert(gongchengDate);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Collection<GongchengDate> getAllGongchengCollection() {
		return map.values();
	}

	public GongchengDate getGongchengDateByFactionId(int factionId) {
		return map.get(factionId);
	}

	public void addGongchengDate(final GongchengDate gongchengDate) {
		addGongcheng(gongchengDate);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				insert(gongchengDate);
			}
		});
	}

	/**
	 * 清楚今日攻城信息 并分配攻城收益
	 */
	public void clearTodayGongchengDateAndSharaFactionCtShouyi() {
		DayGongchengDateList date = getTodayGongChengDateList();
		List<GongchengDate> list = date.getList();
		int zhanchengFid = FactionCityManager.getInstance().getFactionCity().getFactionId();
		for (GongchengDate gongcheng : list) {
			FactionController factionController = FactionManager.getInstance().getFactionControllerByFactionID(gongcheng.getFactionId());
			if (factionController != null) {
				if (factionController.getFaction().getId() != zhanchengFid) {
					factionController.sharafactionCtShouyi(GongchengTsMap.failPresite, GongchengTsMap.failExp);
				} else {
					factionController.sharafactionCtShouyi(GongchengTsMap.successPresite, GongchengTsMap.successExp);
				}
			}
			map.remove(gongcheng.getFactionId());
			// removeGongcheng(gongcheng);
			// deleteGongchengDate(gongcheng);
		}
		String key = ArithmeticUtils.DateToString(date.getGongchengDate());
		list.clear();
		listmap.remove(key);
		deleteGongchengListByDate(new Date());
	}

	public void deleteGongchengListByDate(Date date) {
		try {
			dao.deleteByDate(date);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteGongchengDate(final GongchengDate gongchengDate) {
		removeGongcheng(gongchengDate);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteById(gongchengDate.getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public Collection<DayGongchengDateList> getAllGongchengDateCollection() {
		return listmap.values();
	}

	public boolean isHaveApplyGongcheng(int factionId) {
		GongchengDate gongcheng = map.get(factionId);
		if (gongcheng != null) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteGongchengDateByFaction(Integer id) {
		GongchengDate gongcheng = map.get(id);
		if (gongcheng != null) {
			deleteGongchengDate(gongcheng);
		}
	}

	public DayGongchengDateList getFirsteGongchengDate() {
		if (listmap.size() < 1) {
			return null;
		}
		DayGongchengDateList date = null;
		for (DayGongchengDateList temp : listmap.values()) {
			if (date == null) {
				date = temp;
			} else {
				if (date.getGongchengDate().after(temp.getGongchengDate())) {
					date = temp;
				}
			}
		}
		return date;
	}
}
