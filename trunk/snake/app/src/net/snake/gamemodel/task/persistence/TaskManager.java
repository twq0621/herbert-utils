package net.snake.gamemodel.task.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.task.bean.Task;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class TaskManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(TaskManager.class);

	private TaskDAO dao = new TaskDAO(SystemFactory.getGamedataSqlMapClient());

	// 单例实现=====================================
	private static TaskManager instance;

	public static TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

	private TaskManager() {
	}

	// 单例实现========================================
	private Map<Integer, Task> cacheTaskmap = new HashMap<Integer, Task>();
	private Map<Integer, Task> createCharacterCacheTaskmap = new HashMap<Integer, Task>();// 创建角色时缓存的任务
	// private Map<Integer,Npc> createCharacterCacheTaskmap = new
	// HashMap<Integer, Task>();//任务对应的npc
	private final Set<Integer> needShopGoods = new HashSet<Integer>();// 需要角色商城购买的物品

	public void reload() {
		try {
			getTaskMap();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Set<Integer> getNeedShopGoods() {
		return needShopGoods;
	}

	public Map<Integer, Task> getCacheTaskMap() {
		return cacheTaskmap;
	}

	public Task get(int id) {
		return cacheTaskmap.get(id);
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, Task> getTaskMap() {
		try {
			List<Task> tasklist = dao.select();
			cacheTaskmap.clear();
			BeanTool.addOrUpdate(cacheTaskmap, tasklist, "taskId");

			String targetShoppingGoods = "";
			if (!needShopGoods.isEmpty()){
				needShopGoods.clear();
			}
			for (Iterator<Task> iterator = cacheTaskmap.values().iterator(); iterator.hasNext();) {
				Task task = iterator.next();
				targetShoppingGoods = task.getTargetshopping();
				if (targetShoppingGoods != null && !"".equals(targetShoppingGoods.trim())) {
					String[] targetShoppingGoodsStr = targetShoppingGoods.split(",");
					for (int i = 0; i < targetShoppingGoodsStr.length; i++) {
						String _targetShoppingGood = targetShoppingGoodsStr[i];
						if ("".equals(_targetShoppingGood)){
							continue;
						}
						String[] __targetShoppingGood = _targetShoppingGood.split("#");
						if (__targetShoppingGood.length == 2) {
							needShopGoods.add(Integer.parseInt(__targetShoppingGood[0]));
						}
					}
				}
			}
			List<Task> createCharacterTasks = getTaskListByPublishStyle(tasklist, 2);
			Map<Integer, Task> createCharacterCacheTaskmaptmp = new HashMap<Integer, Task>();
			BeanTool.addOrUpdate(createCharacterCacheTaskmaptmp, createCharacterTasks, "taskId");
			createCharacterCacheTaskmap = createCharacterCacheTaskmaptmp;
			return cacheTaskmap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public List<Task> getTaskListByPublishStyle(List<Task> tasklist, int publishStyle) {
		List<Task> createCharacterTasks = new ArrayList<Task>();
		for (Task task : tasklist) {
			if (task.getPublishstyle() == publishStyle) {
				createCharacterTasks.add(task);
			}
		}
		return createCharacterTasks;
	}

	public Map<Integer, Task> getCreateCharacterCacheTaskmap() {
		return createCharacterCacheTaskmap;
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "task";
	}
}
