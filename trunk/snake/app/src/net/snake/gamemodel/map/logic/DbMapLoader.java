package net.snake.gamemodel.map.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.instance.bean.InstanceDataRef;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.map.bean.MapDate;
import net.snake.gamemodel.map.bean.TransportDate;
import net.snake.gamemodel.map.persistence.MapDateManager;
import net.snake.gamemodel.map.persistence.SceneMonsterManager;
import net.snake.gamemodel.map.persistence.TransportDateManager;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.shell.Options;
import org.apache.log4j.Logger;

/**
 * 加载地图资源文件并初始化
 * 
 * @author serv_dev
 * 
 */
public class DbMapLoader {
	private static Logger logger = Logger.getLogger(DbMapLoader.class);

	private DbMapLoader() {
	}

	public static SceneManager loadSceneManager() {
		List<MapDate> mapList = null;
		try {
			mapList = MapDateManager.getInstance().selectAllMapDateList();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		if (mapList == null || mapList.size() == 0) {
			return null;
		}
		logger.info("start reload map data");
		MapSceneManager sceneManager = new MapSceneManager();
		for (MapDate mapDate : mapList) {
			GameMap map = null;
			try {
				if (sceneManager.getSceneByID(mapDate.getMapId()) != null) {
					continue;
				}
				if (Options.IsCrossServ) {
					if (mapDate.getGongchengType() < 3) {
						continue;
					}
				}
				map = DbMapParse.initTSMapInfo(mapDate);
				if (map == null) {
					continue;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				// logger.info("加载地图出错：地图id：{}", mapDate.getMapId());
				continue;
			}
			map.setSceneManager(sceneManager);
			map.setClearPkProtect(mapDate.getClearPkProtect() == 1 ? true : false);
			sceneManager.addScene(map);
			// logger.info("正在加载基本地图数据正式结束，地图id：{}", map.getId());
		}
		logger.info("map data is finish");
		// 初始化副本相关数据
		initAllInstanceMapInfo(sceneManager);
		return sceneManager;
	}

	public static void initAllMapInfo(SceneManager sceneManager) {
		Collection<Scene> worlds = sceneManager.getWorldSceneList();
		for (Scene scene : worlds) {
			initSceneInfo(scene, sceneManager);
		}
	}

	/**
	 * 初始化副本怪物 传送点信息
	 * 
	 * @param sceneManager
	 */
	private static void initAllInstanceMapInfo(SceneManager sceneManager) {

		Collection<Scene> instances = sceneManager.getInstanceAllSceneList();
		for (Scene scene : instances) {
			initSceneInfo(scene, sceneManager);
		}

		// instancesNum:[scene0,scene1,.....]
		Map<Integer, ArrayList<Scene>> instanceMap = sceneManager.getInstanceSceneListMap();
		Collection<Entry<Integer, ArrayList<Scene>>> collection = instanceMap.entrySet();

		InstanceDataProvider instanceManager = InstanceDataProvider.getInstance();

		for (Entry<Integer, ArrayList<Scene>> ent : collection) {
			InstanceDataRef date = instanceManager.getInstanceDataRef(ent.getKey());
			if (date != null) {
				InstanceController intanceController = new InstanceController(date, ent.getValue());
				sceneManager.addInstanceController(intanceController);
			}
		}
	}

	/**
	 * 初始化地图跳转点 怪物等地图信息
	 * 
	 * @param scene
	 * @param sceneManager
	 */
	private static void initSceneInfo(Scene scene, SceneManager sceneManager) {
		// 初始化地图跳转点信息
		GameMap map = (GameMap) scene;
		initTeleports(map);
		if (map.getId() == Options.Fresher_Familytown) {
			sceneManager.setScene4Novice(Options.Fresher_Familytown, map);
		}
		// 装载地图怪物
		List<SceneMonster> coppylist = new ArrayList<SceneMonster>();
		try {
			SceneMonsterManager scenemonsterManager = SceneMonsterManager.getInstance();
			List<SceneMonster> list = scenemonsterManager.getSceneMonstersByMapid(map.getId());

			for (SceneMonster sceneMonster : list) {
				if (!map.isPermitted((short) sceneMonster.getOriginalX(), (short) sceneMonster.getOriginalY())) {
					logger.error("scene " + map.getId() + " scenemonster id＝" + sceneMonster.getId() + " birth point (" + sceneMonster.getOriginalX() + ","
							+ sceneMonster.getOriginalY() + ") has err");
					continue;
				}
				MonsterModel model = MonsterModelManager.getInstance().getFromCache(sceneMonster.getModel());
				if (model == null) {
					logger.error("scene " + map.getId() + " scenemonster id＝" + sceneMonster.getId() + ".there is no model for " + sceneMonster.getModel());
					continue;
				}
				sceneMonster.setMonsterModel(model);
				sceneMonster.setHit(model.getHit());
				sceneMonster.setDropGoodJiacheng(10000);
				sceneMonster.init();
				coppylist.add(sceneMonster);
				map.setAllMonsterWillAddToScene(coppylist);
				map.initSceneMonster();
			}

		} catch (DataException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 初始化跳转坐标点
	 * 
	 * @param parser
	 * @param tsMap
	 * @param nodeTeleList
	 * @throws Exception
	 */
	private static void initTeleports(GameMap map) {
		TransportDateManager tdm = TransportDateManager.getInstance();
		Set<TransportDate> tdSet = tdm.getTransportDateSet(map.getId());
		if (tdSet == null) {
			return;
		}
		// logger.info("开始加载地图id:{}的传出点 ", new Object[] { map.getId() });
		for (TransportDate td : tdSet) {
			byte isHide = 0;
			Scene scene = map.getSceneManager().getSceneByID(td.getTargetSceneId());
			if (scene == null) {
				if (Options.IsCrossServ) {
					MapTeleport teleport = new MapTeleport(td, isHide);
					map.addTeleport(teleport);
					continue;
				}
				logger.error("there is no map ，map is " + map.getId() + " out point is " + td.getX() + "," + td.getY() + " in point is {" + td.getTargetX() + "," + td.getTargetY()
						+ " transport id " + td.getTransportId());
				return;
			}
			if (!scene.isPermitted(td.getTargetX().shortValue(), td.getTargetY().shortValue())) {
				logger.error("can not pass with transport， map is :" + map.getId() + " out point is " + td.getX() + "," + td.getY() + "===>target map is :" + scene.getId()
						+ " in point is :" + td.getTargetX() + "," + td.getTargetY() + "  transport id " + td.getTransportId());

			}
			if (scene.getInstanceModelId() != 0 && map.getInstanceModelId() != 0) {
				isHide = 1;
			}
			MapTeleport teleport = new MapTeleport(td, isHide);
			map.addTeleport(teleport);
		}
	}
}
