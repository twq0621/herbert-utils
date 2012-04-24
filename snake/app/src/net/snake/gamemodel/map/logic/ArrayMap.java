package net.snake.gamemodel.map.logic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.map.bean.SceneObj;

/**
 * 
 * 根据class不同，取出不同的MAP
 * 
 * @author serv_dev
 */
public class ArrayMap {
	/**
	 * 保存所有场景可见对象
	 */
	private ConcurrentHashMap<Integer, SceneObj>[] value;

	private int[] key;

	@SuppressWarnings("unchecked")
	public ArrayMap(int[] classes) {
		if (classes == null) {
			throw new RuntimeException("参数不合法");
		}

		key = classes;
		value = new ConcurrentHashMap[classes.length];
		for (int i = 0; i < key.length; i++) {
			value[i] = new ConcurrentHashMap<Integer, SceneObj>();
		}
	}

	public Map<Integer, SceneObj> get(int clazz) {
		for (int i = 0; i < key.length; i++) {
			if (key[i] == clazz) {
				return value[i];
			}
		}
		return null;
	}

	public Map<Integer, SceneObj>[] values() {
		return value;
	}
}
