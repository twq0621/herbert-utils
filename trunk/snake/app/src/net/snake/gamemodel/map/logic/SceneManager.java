package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.serverenv.vline.VLineServer;

/**
 * 
 * @author wutao
 * 
 */

public interface SceneManager extends Cloneable {

	public void setVlineServer(VLineServer vlineServer);

	public VLineServer getVlineServer();

	// 获得副本场景的模型，获得的场景不能直接在副本中使用，需要clone一个
	public Scene getInstanceScene(Integer sceneId);

	// 根据id获得场景可能是副本场景，也可能是世界场景
	public Scene getSceneByID(Integer targetMapId);

	/**
	 * 获得初始场景
	 * 
	 * @return
	 */
	public Scene getScene4Novice();

	public void setScene4Novice(Integer sceneId, Scene scene);

	/**
	 * 获得世界场景
	 * 
	 * @param sceneId
	 * @return
	 */

	public Scene getScene(Integer sceneId);

	/**
	 * 获得世界场景
	 * 
	 * @return
	 */
	public Collection<Scene> getWorldSceneList();

	/**
	 * 获得某副本的场景列表
	 * 
	 * @param instancemodelid
	 * @return
	 */
	public ArrayList<Scene> getInstanceSceneList(int instancemodelid);

	/**
	 * 获得副本场景
	 * 
	 * @return
	 */
	public Collection<Scene> getInstanceAllSceneList();

	public Object clone();

	/**
	 * 获得世界场景的总角色数
	 * 
	 * @return
	 */
	public int getCharacterCount();

	/**
	 * 获得世界场景的总怪物数
	 * 
	 * @return
	 */
	public int getMonsterCount();

	/**
	 * 获得世界场景的总出战坐骑数
	 * 
	 * @return
	 */
	public int getShowHorseCount();

	public Map<Integer, ArrayList<Scene>> getInstanceSceneListMap();

	public void addInstanceController(InstanceController instanceController);

	public InstanceController getInstanceControllerById(int id);
}
