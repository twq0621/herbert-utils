package net.snake.gamemodel.pet.persistence;

/**
 * 马模型表
 * 
 * 
 */
import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.bean.HorseModelComparator;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class HorseModelDataProvider implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(HorseModelDataProvider.class);
	private HorseModelDAO horseModelDAO = new HorseModelDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, HorseModel> map = new HashMap<Integer, HorseModel>();
	private List<HorseModel> listHorseModels;
	private Map<Integer, List<HorseModel>> qualityMap = new HashMap<Integer, List<HorseModel>>();

	// 单例实现=====================================
	private static HorseModelDataProvider instance;

	private HorseModelDataProvider() {
	}

	public static HorseModelDataProvider getInstance() {
		if (instance == null) {
			instance = new HorseModelDataProvider();
		}
		return instance;
	}

	// 单例实现========================================
	public HorseModel getHorseModelByID(int horseid) {
		return map.get(horseid);
	}

	/**
	 * 根据马的名字种类获取该类马(由于策划需求变化修改为根据类别)
	 * 
	 * @return
	 */
	public List<HorseModel> getHorseModelListByTypeName(Integer kind) {
		return qualityMap.get(kind);
	}

	@SuppressWarnings("unchecked")
	public List<HorseModel> getHorseModels() {
		try {
			listHorseModels = horseModelDAO.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return listHorseModels;
	}

	private void getQualityMap(Collection<HorseModel> collection) {
		Map<Integer, List<HorseModel>> quality = new HashMap<Integer, List<HorseModel>>();
		for (HorseModel hm : collection) {
			List<HorseModel> qualityList = quality.get(hm.getKind());
			if (qualityList == null) {
				qualityList = new ArrayList<HorseModel>();
				quality.put(hm.getKind(), qualityList);
			}
			qualityList.add(hm);
		}
		qualityMap = quality;
		sortQuality(qualityMap.values());
	}

	public void sortQuality(Collection<List<HorseModel>> collection) {
		for (List<HorseModel> list : collection) {
			if (list != null) {
				Collections.sort(list, new HorseModelComparator());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, HorseModel> getHorseModelMap() {
		List<HorseModel> list = getHorseModels();
		return BeanTool.listToMap(list, "id");
	}

	@Override
	public void reload() {
		try {
			BeanTool.addOrUpdate(map, getHorseModelMap(), "id");
			// 名字分类存储
			getQualityMap(map.values());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "horsemodel";
	}
}
