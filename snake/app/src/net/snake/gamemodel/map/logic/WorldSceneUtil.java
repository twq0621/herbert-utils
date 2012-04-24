package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 世界地图传送计算工具
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class WorldSceneUtil {
	/**
	 * 跨地图数量
	 * 
	 * @param fromMapId
	 * @param targetMapID
	 * @return
	 */
	public static int getToTargetSceneLength(Scene fromMapId, int targetMapID,SceneManager sm) {
		List<MapTeleport> list = searchTargetScenePath(fromMapId, targetMapID,sm);
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	/**
	 * 获取世界地图传送点
	 * 
	 * @param fromMapId
	 * @param targetMapID
	 * @return
	 */
	public static MapTeleport getWorldExchangeTeleport(Scene fromMapId,
			int targetMapID,SceneManager sm) {
		List<MapTeleport> list = searchTargetScenePath(fromMapId, targetMapID,sm);
		if (list == null) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	/**
	 * 跨地图寻路获取地图切换点
	 * 
	 * @param fromMapId
	 * @param targetMapID
	 * @return
	 */
	public static List<MapTeleport> searchTargetScenePath(Scene nowScene,
			int targetMapID,SceneManager sm) {
		Scene targetScene = nowScene.getSceneManager()
				.getSceneByID(targetMapID);
		if (nowScene == null || targetScene == null || nowScene == targetScene) {
			return null;
		}
		return doSearch(nowScene.getId(), targetMapID, null,
				sm);
	}

	/**
	 * 跨地图寻路
	 * 
	 * @return
	 */
	private static List<MapTeleport> doSearch(int fromMapId, int targetMapID,
			List<Integer> path, SceneManager sm) {
		Scene nowScene = sm.getSceneByID(fromMapId);
		Scene targetScene = sm.getSceneByID(targetMapID);
		if (path == null) {
			path = new ArrayList<Integer>();
		}
		if (nowScene == null || targetScene == null) {
			return null;
		}
		if (targetScene.getTeleports4set().size() < 1) {
			return null;
		}
		path.add(fromMapId);

		List<MapTeleport> followpath;
		Set<MapTeleport> outs = nowScene.getTeleports4set();
		for (MapTeleport out : outs) {
			if (out.getTargetSceneID() == targetMapID) {
				List<MapTeleport> stepPath = new ArrayList<MapTeleport>();
				stepPath.add(out);
				return stepPath;
			}
		}
		List<List<MapTeleport>> allPath = new ArrayList<List<MapTeleport>>();
		for (MapTeleport out : outs) {
			if (ishaveScene(path, out)) {
				continue;
			}
			followpath = doSearch(out.getTargetSceneID(), targetMapID, path, sm);
			if (followpath != null && followpath.size() > 0) {
				List<MapTeleport> nowPath = new ArrayList<MapTeleport>();
				nowPath.add(out);
				nowPath.addAll(followpath);
				allPath.add(nowPath);
			}
		}

		if (allPath.size() < 1) {
			return null;
		}
		List<MapTeleport> goodPath = allPath.get(0);
		int goodsize = goodPath.size();
		for (List<MapTeleport> list : allPath) {
			if (goodsize > list.size()) {
				goodPath = list;
				goodsize = list.size();
			}
		}
		return goodPath;
	}

	private static boolean ishaveScene(List<Integer> path, MapTeleport out) {
		int targetId = out.getTargetSceneID();
		for (Integer i : path) {
			if (targetId == i) {
				return true;
			}
		}
		return false;
	}
}
