package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.XianshiActivity;
import net.snake.gamemodel.activities.bean.XianshiActivityReward;
import net.snake.gamemodel.activities.bean.XianshiActivtyControllerComparator;
import net.snake.ibatis.SystemFactory;

/**
 * 限时活动管理。
 * 
 * @author serv_dev
 * 
 */
public class XianshiActivityManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(XianshiActivityManager.class);
	private XianshiActivityDAO dao = new XianshiActivityDAO(
			SystemFactory.getGamedataSqlMapClient());
	List<XianshiActivityController> list = new ArrayList<XianshiActivityController>();
	
	// 单例实现=====================================
	private static XianshiActivityManager instance;

	private XianshiActivityManager() {
	}

	public static XianshiActivityManager getInstance() {
		if (instance == null) {
			instance = new XianshiActivityManager();
		}
		return instance;
	}

	/**
	 * 初始化数据
	 */

	@SuppressWarnings("unchecked")
	private void initData() {
		XianshiActivityRewardManager.getInstance().reload();
		List<XianshiActivity> list2 = new ArrayList<XianshiActivity>();
		try {
			list2 = dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		List<XianshiActivityController> list1 = new ArrayList<XianshiActivityController>();
		Collections.sort(list2, new XianshiActivtyControllerComparator());
		for (XianshiActivity tf : list2) {
			addXianshiActivity(list1, tf);
		}
		list = list1;
	}

	private void addXianshiActivity(List<XianshiActivityController> list,
			XianshiActivity xa) {
		XianshiActivityController xac = new XianshiActivityController(xa);
		list.add(xac);
		List<XianshiActivityReward> listreward = XianshiActivityRewardManager
				.getInstance().getXianshiActivityReward(xa.getId());
		xac.setList(listreward);
	}

	/**
	 * 
	 * @return
	 */
	public List<XianshiActivityController> getXianshiActivityTypeControllerCollection() {
		return list;
	}

	public XianshiActivityController getActivtyListByType(int activetyId) {
		for (XianshiActivityController xac : list) {
			if (xac.getXianshiActivity().getId() == activetyId) {
				return xac;
			}
		}
		return null;
	}

	public XianshiActivityReward getXianshiActivityRewardByTypeAndItemId(
			int activetyId, int itemId) {
		XianshiActivityController xtc = getActivtyListByType(activetyId);
		if (xtc == null) {
			return null;
		}
		return xtc.getXianshiActivityRewardByItemId(itemId);
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "xianshiactivity";
	}

	/**
	 * 
	 * @see net.snake.commons.dbversioncache.CacheUpdateListener#reload()
	 */
	@Override
	public void reload() {
		initData();
	}

}
