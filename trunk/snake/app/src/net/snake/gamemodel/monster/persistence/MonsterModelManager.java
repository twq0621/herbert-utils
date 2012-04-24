package net.snake.gamemodel.monster.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.map.persistence.SceneMonsterManager;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.logic.lostgoods.BossLostGood;
import net.snake.gamemodel.monster.logic.lostgoods.MonsterLostgoods;

import org.apache.log4j.Logger;

/**
 * 怪物模板表管理
 * 
 * @author benchild
 */

public class MonsterModelManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(MonsterModelManager.class);

	private static MonsterModelDao dao = new MonsterModelDao();
	// 单例实现=====================================
	private static MonsterModelManager instance;
	private Map<String, MonsterModel> lianzhanMap = new HashMap<String, MonsterModel>();
	private Map<String, TreeMap<Integer, MonsterModel>> newLianzhanmap = new HashMap<String, TreeMap<Integer, MonsterModel>>();// 怪物类型和副本id固定，怪物等级为一个范围区间

	private MonsterModelManager() {
	}

	public static MonsterModelManager getInstance() {
		if (instance == null) {
			instance = new MonsterModelManager();
		}
		return instance;
	}

	// 单例实现========================================
	private Map<Integer, MonsterModel> cacheMonstermodelMap = new HashMap<Integer, MonsterModel>();
	private Map<Integer, MonsterModel> cacheNameMonstermodelMap = new HashMap<Integer, MonsterModel>();
	private Map<Integer, MonsterModel> cacheNpcFriendmodelMap = new HashMap<Integer, MonsterModel>();

	protected MonsterModelDao getEntityDao() {
		return dao;
	}

	public Map<Integer, MonsterModel> getCacheMonstermodelMap() {
		return cacheMonstermodelMap;
	}

	public Map<Integer, MonsterModel> getCacheNpcfriendModelMap() {
		return cacheNpcFriendmodelMap;
	}

	/**
	 * 初始化怪物及其相关数据内存调用方法
	 */
	public void initMonsterData() {
		BossLostGoodDateManager.getInstance().initBossGoodMap();
		MonsterLostgoodsDateManager.getInstance().initMonsterLostgoods();
		// 初始化怪物掉落物品 到怪物模型上
		initMonsterDropGoods();
	}

	@Override
	public void reload() {
		long start = System.currentTimeMillis();
		logger.info("start reload monster model：" + start);
		initCacheMonstermodelMap();
		initMonsterData();
		long zhong = System.currentTimeMillis();
		logger.info("start reload scene monster：" + zhong);
		SceneMonsterManager.getInstance().reload();
		logger.info("monster data reload finish：" + System.currentTimeMillis());
	}

	public MonsterModel getFromCache(int modelid) {
		return cacheMonstermodelMap.get(modelid);
	}

	public MonsterModel getFromCacheByName(String modelName) {
		return cacheNameMonstermodelMap.get(modelName);
	}

	public void initCacheMonstermodelMap() {
		try {
			List<MonsterModel> list = dao.getAll();
			BeanTool.addOrUpdate(cacheMonstermodelMap, list, "id");
			BeanTool.addOrUpdate(cacheNameMonstermodelMap, list, "name");
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
	}

	/**
	 * 初始化怪物掉落物品 到怪物模型上
	 */
	private void initMonsterDropGoods() {
		cacheNpcFriendmodelMap.clear();
		Collection<MonsterModel> collection = MonsterModelManager.getInstance().getAllMonsterCollection();
		BossLostGoodDateManager blgdm = BossLostGoodDateManager.getInstance();
		MonsterLostgoodsDateManager mlgdm = MonsterLostgoodsDateManager.getInstance();
		Map<String, MonsterModel> _lianzhanMap = new HashMap<String, MonsterModel>();
		Map<String, TreeMap<Integer, MonsterModel>> _newLianzhanmap = new HashMap<String, TreeMap<Integer, MonsterModel>>();
		for (MonsterModel monsterModel : collection) {
			if (monsterModel.getIsnpcfriend() == 1) {
				cacheNpcFriendmodelMap.put(monsterModel.getId(), monsterModel);
			}
			MonsterLostgoods mlg = mlgdm.getMonsterLostgoodsByMonsterId(monsterModel.getId());
			monsterModel.setMonsterLostgoods(mlg);
			BossLostGood blg = blgdm.getBossLostGoodByNameInMap(monsterModel.getId());
			monsterModel.setBossDropGoods(blg);
			if (monsterModel.getInstanceId() > 0) {
				_lianzhanMap.put(monsterModel.getInstanceId() + "_" + monsterModel.getType() + "_" + monsterModel.getGrade() + "_" + monsterModel.getCategoryId(), monsterModel);
				String treeMapKey = monsterModel.getInstanceId() + "_" + monsterModel.getType() + "_" + monsterModel.getCategoryId();
				TreeMap<Integer, MonsterModel> retTreeMap = _newLianzhanmap.get(treeMapKey);
				if (retTreeMap == null) {
					retTreeMap = new TreeMap<Integer, MonsterModel>();
					_newLianzhanmap.put(treeMapKey, retTreeMap);
				}
				retTreeMap.put(monsterModel.getGrade(), monsterModel);
			}
		}
		lianzhanMap = _lianzhanMap;
		newLianzhanmap = _newLianzhanmap;
	}

	public MonsterModel getLianzhanMonster(int instanceId, int type, int grade, int category) {
		return lianzhanMap.get(instanceId + "_" + type + "_" + grade + "_" + category);
	}

	/**
	 * boss是取和玩家相同等级或者向上取最靠近玩家等级的
	 * 
	 * @param instanceId
	 * @param type
	 * @param grade
	 * @return
	 */
	public MonsterModel getNearestMoster(int instanceId, int type, int grade, int category) {
		MonsterModel mm = getLianzhanMonster(instanceId, type, grade, category);
		if (mm == null) {
			TreeMap<Integer, MonsterModel> modelTree = newLianzhanmap.get(instanceId + "_" + type + "_" + category);
			if (modelTree != null) {
				Set<Map.Entry<Integer, MonsterModel>> monsterEntry = modelTree.entrySet();
				boolean hasFind = false;
				MonsterModel lastEntry = null;
				for (Iterator<Entry<Integer, MonsterModel>> it = monsterEntry.iterator(); it.hasNext();) {
					Entry<Integer, MonsterModel> entry = it.next();
					lastEntry = entry.getValue();
					if (grade <= entry.getKey()) {
						mm = entry.getValue();
						hasFind = true;
						break;
					}
				}
				if (!hasFind) {
					mm = lastEntry;
				}
			}
		}
		return mm;
	}

	public Collection<MonsterModel> getAllMonsterCollection() {
		return cacheMonstermodelMap.values();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "monster";
	}

}
