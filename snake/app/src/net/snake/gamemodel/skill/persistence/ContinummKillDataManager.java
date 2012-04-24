package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.skill.bean.ContinuumKillDataEntry;
import net.snake.ibatis.SystemFactory;

public class ContinummKillDataManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(ContinummKillDataManager.class);
	private static ContinuumKillDataEntryDAO dao;
	private volatile ArrayList<ContinuumKillDataEntry> continummkillDataEntryList = new ArrayList<ContinuumKillDataEntry>();
	// 单例实现=====================================
	private static ContinummKillDataManager instance;

	public static ContinummKillDataManager getInstance() {
		if (instance == null) {
			instance = new ContinummKillDataManager();
		}
		return instance;
	}

	private ContinummKillDataManager() {
		dao = new ContinuumKillDataEntryDAO(SystemFactory.getGamedataSqlMapClient());
		reload();
	}

	public ContinuumKillDataEntry getByKillCount(int count) {
		for (int i = 0; i < continummkillDataEntryList.size(); i++) {
			ContinuumKillDataEntry t = continummkillDataEntryList.get(i);
			if (t.getKillCountFrom() >= count) {
				return t;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			List<ContinuumKillDataEntry> entrylist = dao.select();
			ArrayList<ContinuumKillDataEntry> al = new ArrayList<ContinuumKillDataEntry>(entrylist);
			Collections.sort(al, new Comparator<ContinuumKillDataEntry>() {
				@Override
				public int compare(ContinuumKillDataEntry o1, ContinuumKillDataEntry o2) {
					return o1.getKillCountFrom().compareTo(o2.getKillCountFrom());
				}
			});
			continummkillDataEntryList = al;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "continummkill";
	}
}
