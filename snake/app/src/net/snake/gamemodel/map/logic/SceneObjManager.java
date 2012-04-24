package net.snake.gamemodel.map.logic;

import java.util.Collection;
import java.util.Map;

import net.snake.gamemodel.map.bean.SceneObj;


/**
 * 场景可见对象管理器 由每个场景管理自己的
 * 
 * @author serv_dev
 */
public class SceneObjManager {
	/**
	 * 保存所有场景可见对象
	 */
//	private final Map<Class, Map<Integer, SceneObj>> sceneObjMap = new HashMap<Class, Map<Integer, SceneObj>>();
	/** 关注的对象类型 **/
	//public Class[] heedClasses;
	private  ArrayMap arraymap;
	/**
	 * @param classes
	 */
	public SceneObjManager(int[] classes) {
		arraymap=new ArrayMap(classes);
	}


	/**
	 * 添加进入场景的可见对象,如果该类不关注此类型的对像，则无法加入
	 * 
	 * @param SceneObj
	 */
	public void addVisibleObj(SceneObj SceneObj) {
		Map<Integer, SceneObj> map = arraymap.get(SceneObj.getSceneObjType());
		if(map==null){
			//必须指明自己可以存储什么类型的对像，不在自己的类别内，不支持存储
			return;
		}
		map.put(SceneObj.getId(), SceneObj);
	}

	/**
	 * 移除一个场景中的可见对象
	 * 
	 * @param SceneObj
	 *            要移除的对象
	 */
	public SceneObj removeVisibleObj(SceneObj SceneObj) {
		Map<Integer, SceneObj> map = arraymap.get(SceneObj.getSceneObjType());
		if (map == null) {
			return null;
		}
		return map.remove(SceneObj.getId());
	}

	/**
	 * 获得场景中的 某类型 的可视对象
	 * 
	 * @param type
	 *            类型
	 * @param id
	 *            对象id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getVisibleObjsByClazzAndId(int clazz, int id) {

		Map<Integer, SceneObj> sceneObjs = arraymap.get(clazz);
		if (sceneObjs == null) {
			return null;
		}
		return (T) sceneObjs.get(id);
	}

	/**
	 * 获得一个场景中所有 某种类型可见对象
	 * 
	 * @param type
	 *            可见对象类型
	 * @return map
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<Integer, T> getVisibleObjsMap(int clazz) {
		return (Map<Integer, T>) arraymap.get(clazz);
	}

	/**
	 * 获得一个场景中所有 某种类型可见对象
	 * 
	 * @param type
	 *            可见对象类型
	 * @return Collection
	 */
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getVisibleObjsCollection(int clazz) {

		Map<Integer, SceneObj> sceneObjs = arraymap.get(clazz);
		if (sceneObjs == null) {
			return null;
		}
		return (Collection<T>) sceneObjs.values();
	}

	public boolean containsVisibleObj(SceneObj obj) {
		if (obj == null) {
			return false;
		}
		Map<Integer, SceneObj> objsMap = arraymap.get(obj.getSceneObjType());
		if (objsMap == null) {
			return false;
		}
		return objsMap.containsKey(obj.getId());
	}

	/**
	 * 根据对象类型获得 该类型对象在场景中的总数量
	 * 
	 * @param type
	 * @return
	 */
	public int getVisibleObjsCount(int clazz) {
		Map<Integer, SceneObj> sceneObjs = arraymap.get(clazz);
		if (sceneObjs == null) {
			return 0;
		}
		return sceneObjs.size();
	}

	public Map<Integer, SceneObj>[] getAllSceneObject() {
		return arraymap.values();
	}

	/**
	 * 清除这个场景中所有可见对象的引用
	 */
	public void clear() {
		for (Map<Integer, SceneObj> obj : arraymap.values()) {
			obj.clear();
		}
	}

}
