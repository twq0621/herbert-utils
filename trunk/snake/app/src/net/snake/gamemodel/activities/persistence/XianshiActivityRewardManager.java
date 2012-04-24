package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.XianshiActivityReward;
import net.snake.gamemodel.activities.bean.XianshiActivtyRewardByOrderComparator;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;
public class XianshiActivityRewardManager implements CacheUpdateListener {
	private static Logger logger = Logger
			.getLogger(XianshiActivityRewardManager.class);
	
	private XianshiActivityRewardDAO dao = new XianshiActivityRewardDAO(
			SystemFactory.getGamedataSqlMapClient());
	Map<Integer, List<XianshiActivityReward>> map = new HashMap<Integer, List<XianshiActivityReward>>();
	Map<Integer, XianshiActivityReward> idMap = new HashMap<Integer, XianshiActivityReward>();
	
	// 单例实现=====================================
	private static XianshiActivityRewardManager instance;

	private XianshiActivityRewardManager() {
	}

	public static XianshiActivityRewardManager getInstance() {
		if (instance == null) {
			instance = new XianshiActivityRewardManager();
		}
		return instance;
	}

	/**初始化数据*/
	@SuppressWarnings("unchecked")
	private void initDate() {
		List<XianshiActivityReward> list2 = new ArrayList<XianshiActivityReward>();
		try {
			list2 = dao.selectWithBLOBs();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		Collections.sort(list2, new XianshiActivtyRewardByOrderComparator());
		Map<Integer, List<XianshiActivityReward>> map1 = new HashMap<Integer, List<XianshiActivityReward>>();
		Map<Integer, XianshiActivityReward> idMap1 = new HashMap<Integer, XianshiActivityReward>();
		
		for (XianshiActivityReward tf : list2) {
			addActivityReward(map1, tf);
			idMap1.put(tf.getId(), tf);
			if(tf.getDescI18n()==null){
//				logger.debug("{},{},{}",new Object[]{tf.getId(),tf.getDesc(),tf.getDescI18n()});
			}
		}
		map = map1;
		idMap = idMap1;
	}

	/**
	 * 
	 */
	public void addActivityReward(
			Map<Integer, List<XianshiActivityReward>> maps,
			XianshiActivityReward xar) {
		Integer activityId=xar.getXianshiActivityId();
		List<XianshiActivityReward> temp = maps.get(activityId);
		if (temp == null) {
			temp = new ArrayList<XianshiActivityReward>();
			maps.put(activityId, temp);
		}
		try {
			xar.init();
			temp.add(xar);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public XianshiActivityReward getXianshiActivityRewardById(int id) {
		return idMap.get(id);
	}

	public List<XianshiActivityReward> getXianshiActivityReward(int activiteId) {
		return map.get(activiteId);
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "xianshiactivityreward";
	}

	/**
	 *  
	 * @see net.snake.commons.dbversioncache.CacheUpdateListener#reload()
	 */
	@Override
	public void reload() {
		initDate();
	}

}
