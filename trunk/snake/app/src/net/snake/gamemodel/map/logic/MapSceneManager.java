package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.serverenv.vline.VLineServer;


public class MapSceneManager implements SceneManager, Cloneable {
	private static final Logger logger = Logger.getLogger(MapSceneManager.class);
	VLineServer vlineServer;
	// 世界场景
	private Map<Integer, Scene> worldSceneMap = new HashMap<Integer, Scene>();

	// 副本场景 key:场景id
	private Map<Integer, Scene> instanceSceneMap = new HashMap<Integer, Scene>();
	/**
	 * key 副本模型id value:该副本的所有场景
	 */
	private Map<Integer, InstanceController> instanceControllerMap = new HashMap<Integer, InstanceController>();
	private Map<Integer, ArrayList<Scene>> instanceSceneListMap = new HashMap<Integer, ArrayList<Scene>>();//instancemodelid,instacesceces
	private Scene noviceScene;

	public Map<Integer, InstanceController> getInstanceControllerMap() {
		return instanceControllerMap;
	}

	public void setInstanceControllerMap(
			Map<Integer, InstanceController> instanceControllerMap) {
		this.instanceControllerMap = instanceControllerMap;
	}

	// 单例实现=====================================
	public MapSceneManager() {
	}

	public void setVlineServer(VLineServer vlineServer) {
		this.vlineServer = vlineServer;
	}

	public VLineServer getVlineServer() {
		return vlineServer;
	}

	@Override
	public Object clone() {
		try {
			MapSceneManager t = new MapSceneManager();
			for (Scene scene : worldSceneMap.values()) {
				Scene clone = ((Scene) scene.clone());
				clone.setSceneManager(t);
				t.addScene(clone);
			}
			t.instanceSceneMap=instanceSceneMap;
			t.setInstanceControllerMap(instanceControllerMap);
			return t;
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	// 单例实现========================================
	public void addScene(Scene scene) {
		scene.setSceneManager(this);
		//SceneListenerFactory.setSceneUseItemListener(scene);
		if (scene.isInstanceScene()) {
			instanceSceneMap.put(scene.getId(), scene);
			ArrayList<Scene> scenelist = instanceSceneListMap.get(scene
					.getInstanceModelId());
			if (scenelist == null) {
				scenelist = new ArrayList<Scene>();
				instanceSceneListMap.put(scene.getInstanceModelId(), scenelist);
			}
			scenelist.add(scene);
		} else {
			worldSceneMap.put(scene.getId(), scene);
		}
	}

	@Override
	public Scene getScene(Integer sceneId) {
		return worldSceneMap.get(sceneId);
	}

	// 获得副本场景
	public Scene getInstanceScene(Integer sceneId) {
		return instanceSceneMap.get(sceneId);
	}

	@Override
	public Scene getScene4Novice() {
		return noviceScene;
	}

	@Override
	public void setScene4Novice(Integer sceneId, Scene scene) {
		worldSceneMap.put(sceneId, scene);
		noviceScene = scene;
	}

	public Collection<Scene> getWorldSceneList() {
		return worldSceneMap.values();
	}

	public Collection<Scene> getInstanceAllSceneList() {
		return instanceSceneMap.values();
	}

	public Scene getSceneByID(Integer targetMapId) {
		Scene targetScene = getScene(targetMapId);
		if (targetScene == null) {
			// 检查目标场景是否是副本场景
			targetScene = getInstanceScene(targetMapId);
		}
		return targetScene;

	}

	@Override
	public ArrayList<Scene> getInstanceSceneList(int instancemodelid) {
		return instanceSceneListMap.get(instancemodelid);
	}

	@Override
	public int getCharacterCount() {
		int count = 0;
		for (Scene scene : worldSceneMap.values()) {
			count = count + scene.getCharacterCount();
		}
		return count;
	}

	@Override
	public int getMonsterCount() {
		int count = 0;
		for (Scene scene : worldSceneMap.values()) {
			count = count + scene.getMonsterCount();
		}
		return count;
	}

	@Override
	public int getShowHorseCount() {
		int count = 0;
		for (Scene scene : worldSceneMap.values()) {
			count = count + scene.getHorseCount();
		}
		return count;
	}

	public Map<Integer, ArrayList<Scene>> getInstanceSceneListMap() {
		return instanceSceneListMap;
	}

	public void addInstanceController(InstanceController instanceController) {
		this.instanceControllerMap.put(instanceController.getInstanceData()
				.getInstanceModelId(), instanceController);
	}

	public InstanceController getInstanceControllerById(int id) {
		return this.instanceControllerMap.get(id);
	}
}
