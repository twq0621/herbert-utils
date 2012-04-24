package net.snake.gamemodel.achieve.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.bean.AchieveComparator;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 成就管理器
 * 
 * @author serv_dev
 */

public class AchieveManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(AchieveManager.class);
	private int maxPoint = 0;
	private AchieveDAO dao = new AchieveDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, Achieve> achieveMap = new HashMap<Integer, Achieve>(); // 编号存储
	private Map<Integer, List<Achieve>> kindMap = new HashMap<Integer, List<Achieve>>(); // 大类存储
	private Map<Integer, List<Achieve>> childKindMap = new HashMap<Integer, List<Achieve>>(); // 子类存储
	// 单例实现=====================================
	private static AchieveManager instance;

	private AchieveManager() {
	}

	public int getMaxPoint() {
		return maxPoint;
	}

	/**
	 * 获取某大类所有成就
	 * 
	 * @param kind
	 * @return
	 */
	public List<Achieve> getAchieveListByKind(int kind) {
		return kindMap.get(kind);
	}

	/**
	 * 获取某子类类所有成就
	 * 
	 * @param kind
	 * @return
	 */
	public List<Achieve> getAchieveListByChildKind(int childKind) {
		return childKindMap.get(childKind);
	}

	public Achieve getAchieveById(int id) {
		return achieveMap.get(id);
	}

	public static AchieveManager getInstance() {
		if (instance == null) {
			instance = new AchieveManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void initAchieveMap() {
		// 跨服版本检查
		List<Achieve> list = selectAllAchieveList();

		this.achieveMap = BeanTool.listToMap(list, "id");
		kindMap = new HashMap<Integer, List<Achieve>>(); // 大类存储
		childKindMap = new HashMap<Integer, List<Achieve>>(); // 子类存储
		int point = 0;
		for (Achieve achieve : achieveMap.values()) {
			achieve.initGoods();
			addKindMap(achieve);
			addchildKindMap(achieve);
			point = point + achieve.getPoint();
		}
		this.maxPoint = point;
		for (List<Achieve> childList : childKindMap.values()) {
			Collections.sort(childList, new AchieveComparator());
		}
	}

	public void addKindMap(Achieve achieve) {
		Integer kind = achieve.getKind();
		List<Achieve> list = kindMap.get(kind);
		if (list == null) {
			list = new ArrayList<Achieve>();
			kindMap.put(kind, list);
		}
		list.add(achieve);
	}

	public void addchildKindMap(Achieve achieve) {
		Integer ckind = achieve.getChildKind();
		List<Achieve> list = childKindMap.get(ckind);
		if (list == null) {
			list = new ArrayList<Achieve>();
			childKindMap.put(ckind, list);
		}
		list.add(achieve);
	}

	@SuppressWarnings("unchecked")
	public List<Achieve> selectAllAchieveList() {
		try {
			return this.dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void reload() {
		initAchieveMap();
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "achieve";
	}

}
