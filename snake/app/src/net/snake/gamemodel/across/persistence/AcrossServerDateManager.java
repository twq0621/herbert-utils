package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.AcrossServer;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品包裹表数据管理
 * 
 */

public class AcrossServerDateManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(AcrossServerDateManager.class);

	private AcrossServerDateDAO dao = new AcrossServerDateDAO(SystemFactory.getGamedataSqlMapClient());
	private List<AcrossServerDate> list = new ArrayList<AcrossServerDate>();
	private Map<Integer, List<AcrossServerDate>> map = new HashMap<Integer, List<AcrossServerDate>>();
	public static boolean isOpenAcross = false;
	// 单例实现=====================================
	private static AcrossServerDateManager instance;

	public boolean isAcrossTime() {
		if (list != null && list.size() > 0 && isOpenAcross) {
			AcrossServerDate acrossServerDate = list.get(0);
			return acrossServerDate.isTimeExpression();
		}
		return false;
	}

	private AcrossServerDateManager() {
	}

	public static AcrossServerDateManager getInstance() {
		if (instance == null) {
			instance = new AcrossServerDateManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		List<AcrossServerDate> list1 = null;
		try {
			list1 = dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		if (list1 == null || list1.size() == 0) {
			return;
		}
		Map<Integer, List<AcrossServerDate>> map1 = new HashMap<Integer, List<AcrossServerDate>>();
		for (AcrossServerDate acr : list1) {
			if (acr.getEnable() == 1) {
				isOpenAcross = true;
			}
			addAcrossServerDateToMap(acr, map1);
		}
		this.list = list1;
		this.map = map1;
	}

	private void addAcrossServerDateToMap(AcrossServerDate acr, Map<Integer, List<AcrossServerDate>> map1) {
		List<AcrossServerDate> temp = map1.get(acr.getServerProcess());
		if (temp == null) {
			temp = new ArrayList<AcrossServerDate>();
			map1.put(acr.getServerProcess(), temp);
		}
		temp.add(acr);
	}

	public AcrossServerDate getAcrossServerDateById(int serverId) {
		for (AcrossServerDate acr : list) {
			if (acr.getServerId() == serverId) {
				return acr;
			}
		}
		return null;
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "acrossServer";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.commons.dbversioncache.CacheUpdateListener#reload()
	 */
	@Override
	public void reload() {
		init();
	}

	public List<AcrossServerDate> getList() {
		return list;
	}

	public void setList(List<AcrossServerDate> list) {
		this.list = list;
	}

	public Map<Integer, List<AcrossServerDate>> getMap() {
		return map;
	}

	public void checkAndUpdateBalance() throws InterruptedException {
		Set<Entry<Integer, List<AcrossServerDate>>> entrySet = map.entrySet();
		for (Entry<Integer, List<AcrossServerDate>> entry : entrySet) {
			List<AcrossServerDate> value = entry.getValue();
			if (value != null && !value.isEmpty()) {
				AcrossServerDate asd = value.get(0);
				if (System.currentTimeMillis() - asd.getUpdatetime() > 30 * 1000) {
					CtsConnectSessionMananger.getInstance().sendMsgToServer(asd.getLoginServerIp(), AcrossServer.acrossPort, new Msg(1006));
				}
			}
		}
	}
}
