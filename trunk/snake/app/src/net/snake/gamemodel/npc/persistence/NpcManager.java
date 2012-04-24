package net.snake.gamemodel.npc.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.ibatis.SystemFactory;

/**
 * npc管理
 * 
 */

public class NpcManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(NpcManager.class);
	public static final String TABLE = "npc";

	private static NpcDAO dao = new NpcDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static NpcManager instance;

	public static NpcManager getInstance() {
		if (instance == null) {
			instance = new NpcManager();
		}
		return instance;
	}

	private NpcManager() {
		reload();
	}

	// 单例实现========================================
	private Map<Integer, Npc> npcMap = new ConcurrentHashMap<Integer, Npc>();// npc加载列表
	private Map<Integer, ArrayList<Npc>> npcSceneMap = new HashMap<Integer, ArrayList<Npc>>();

	public void reload() {
		try {
			BeanTool.addOrUpdate(npcMap, getNpcMap(), "id");
			Map<Integer, ArrayList<Npc>> tempNpcSceneMap = new HashMap<Integer, ArrayList<Npc>>();
			for (Npc npc : npcMap.values()) {
				ArrayList<Npc> npclist = tempNpcSceneMap.get(npc.getSceneid());
				if (npclist == null) {
					npclist = new ArrayList<Npc>();
					tempNpcSceneMap.put(npc.getSceneid(), npclist);
				}
				npclist.add(npc);
			}
			npcSceneMap = tempNpcSceneMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 是否在某个功能NPC附近
	 * 
	 * @param sceneobj
	 * @param npcfunction
	 * @param radius
	 * @return
	 */
	public boolean isInNpcAround(SceneObj sceneobj, String npcfunction) {
		ArrayList<Npc> npclist = npcSceneMap.get(sceneobj.getSceneRef().getId());
		if (npclist == null) {
			return false;
		}
		for (Npc npc : npclist) {
			if (npc.iscontainsFunction(npcfunction) && Npc.validateNpc(npc, sceneobj)) {
				return true;
			}
		}
		return false;
	}

	public Map<Integer, Npc> getCacheNpcMap() {
		return npcMap;
	}

	public Npc get(int id) {
		return npcMap.get(id);
	}
	@SuppressWarnings("unchecked")
	private Map<Integer, Npc> getNpcMap() {
		try {
			List<Npc> npclist = dao.select();
			Map<Integer, Npc> map = BeanTool.listToMap(npclist, "id");
			return map;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 寻找环任务动态npc
	 * 
	 * @param characterGrade
	 * @return
	 */
	public List<Npc> getWeekTaskNpc(short characterGrade) {
		List<Npc> npcs = new ArrayList<Npc>();
		for (Iterator<Npc> iterator = npcMap.values().iterator(); iterator.hasNext();) {
			Npc npc = iterator.next();
			int mn = npc.getMinGrade();
			int mx = npc.getMaxGrade();
			if ( mn<= characterGrade && characterGrade <= mx) {
				npcs.add(npc);
			}
		}
		return npcs;
	}

	@Override
	public String getAppname() {
		return "npc";
	}

	@Override
	public String getCachename() {
		return "npc";
	}
}
