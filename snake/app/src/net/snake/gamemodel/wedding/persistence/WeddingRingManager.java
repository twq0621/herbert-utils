package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.bean.WeddingRingComparator;
import net.snake.ibatis.SystemFactory;

public class WeddingRingManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(WeddingRingManager.class);
	private WeddingRingDAO dao = new WeddingRingDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, WeddingRing> map = new HashMap<Integer, WeddingRing>();
	private Map<Integer, WeddingRing> halfMap = new HashMap<Integer, WeddingRing>();
	private List<WeddingRing> list = new ArrayList<WeddingRing>();
	// 单例实现=====================================
	private static WeddingRingManager instance;

	private WeddingRingManager() {
	}

	public static WeddingRingManager getInstance() {
		if (instance == null) {
			instance = new WeddingRingManager();
		}
		return instance;
	}

	/**
	 * 初始化hu数据
	 */
	@SuppressWarnings("unchecked")
	private void initDate() {
		List<WeddingRing> list2 = new ArrayList<WeddingRing>();
		try {
			list2 = dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		Map<Integer, WeddingRing> map1 = new HashMap<Integer, WeddingRing>();
		Map<Integer, WeddingRing> halfMap1 = new HashMap<Integer, WeddingRing>();
		List<WeddingRing> list1 = new ArrayList<WeddingRing>();
		for (WeddingRing tf : list2) {
			map1.put(tf.getRingId(), tf);
			list1.add(tf);
			halfMap1.put(tf.getMaleGood(), tf);
			halfMap1.put(tf.getFemaleGood(), tf);
		}
		map = map1;
		halfMap = halfMap1;
		Collections.sort(list1, new WeddingRingComparator());
		list = list1;

	}

	/**
	 * 返回婚戒列表
	 * 
	 * @return
	 */
	public List<WeddingRing> getWeddingRingList() {
		return list;
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "weddingring";
	}

	@Override
	public void reload() {
		initDate();
	}

	/**
	 * @param ringId
	 * @return
	 */
	public WeddingRing getWeddingRingById(Integer ringId) {
		return map.get(ringId);
	}

	/**
	 * 根据般配id 获取玉佩对应WeddingRing对象
	 * 
	 * @param id
	 * @return
	 */
	public WeddingRing getWeddingRingByHalfId(Integer id) {
		return halfMap.get(id);
	}
}
