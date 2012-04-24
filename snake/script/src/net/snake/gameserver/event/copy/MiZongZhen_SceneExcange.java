package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SHorse;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.STeleport;

import org.apache.log4j.Logger;

/**
 * 迷踪阵副本
 */
public class MiZongZhen_SceneExcange extends SuperInstance implements IEventListener {
	private static Logger logger = Logger.getLogger(MiZongZhen_SceneExcange.class);

	public final int instanceId = 6;

	private int[] sceneIds = { 20033, 20177, 20178, 20179, 20180, 20181, 20182, 20183, 20184, 20185, 20186, 20187, 20188, 20189, 20190, 20191 };

	private static int mysteriousSceneId1 = 20192;// 迷踪阵神秘1层
	private static int mysteriousSceneId2 = 20193;// 迷踪阵神秘2层

	/**
	 * 返回所要进入的层
	 * 
	 * @param role
	 * @param teleport
	 * @param layer
	 * @param outDoors
	 * @param instance
	 * @return
	 */
	private int getNextLayer(SRole role, STeleport teleport, int layer, int[] outDoors, SInstance instance) {
		if (role.getSceneRef().getId() == mysteriousSceneId1 || role.getSceneRef().getId() == mysteriousSceneId2) {
			return Integer.parseInt(instance.getAttribute("layerOriginal" + role.getId()).toString());
		}
		if (layer > 16) {
			return 1;
		}
		int outDoor = outDoors[layer - 1];
		if (outDoor == teleport.getId()) { // 正确出口
			layer++;
			return layer;
		} else {
			boolean[] isTriggerErrors = (boolean[]) instance.getAttribute("isTriggerErrors");
			// 检测该玩家是否曾经触发过该层的错误入口
			SHorse horse = role.getCurrRidingHorse();
			int r = (int) (Math.random() * 100) + 1;
			if (isTriggerErrors[layer - 1]) { // 若已被触发过则继续判断玩家当前所骑乘的坐骑
				if (horse != null && horse.getKind() >= 9) { // 如果坐骑在豹子以上，则读取以下几率
					if (r > 0 && r <= 50) {// 50% 倒退一层
						layer -= 1;
					} else if (r > 50 && r <= 60) {// 10% 连续倒退三层
						layer -= 3;
					} else if (r > 60 && r <= 70) {// 10% 倒退至第一层
						layer = 1;
					} else if (r > 70 && r <= 80) {// 10% 传送至第10层
						layer = 10;
					} else if (r > 80 && r <= 90) {// 10% 传送至第5层
						layer = 5;
					} else if (r > 90 && r <= 100) {// 10% 倒退至第一层
						layer = 1;
					}
				} else {// 如果坐骑在豹子以下，则读取以下几率
					if (r > 0 && r <= 55) {// 55% 倒退一层
						layer -= 1;
					} else if (r > 55 && r <= 60) {// 5% 倒退至第一层
						layer = 1;
					} else if (r > 60 && r <= 80) {// 20% 连续倒退三层
						layer -= 3;
					} else if (r > 80 && r <= 90) {// 10% 传送至第10层
						layer = 10;
					} else if (r > 90 && r <= 97) {// 7% 传送至第5层
						layer = 5;
					} else if (r > 97 && r <= 100) {// 3% 倒退至第一层
						layer = 1;
					}
				}
			} else {// 若未曾触发过则继续判断玩家当前所骑乘的坐骑
				if (horse != null && horse.getKind() >= 9) { // 如果坐骑在豹子以上，则读取以下几率
					if (r > 0 && r <= 50) {// 50% 倒退一层
						layer -= 1;
					} else if (r > 50 && r <= 55) {// 5% 连续倒退三层
						layer -= 3;
					} else if (r > 55 && r <= 65) {// 10% 倒退至第一层
						layer = 1;
					} else if (r > 65 && r <= 75) {// 10% 传送至第10层
						layer = 10;
					} else if (r > 75 && r <= 90) {// 15% 传送至第5层
						layer = 5;
					} else if (r > 90 && r <= 95) {// 5% 传送至神秘层1
						if (instance.getAttribute("isInto" + mysteriousSceneId1) == null) {
							layer = 101;
							instance.setAttribute("isInto" + mysteriousSceneId1, true);
						}
					} else if (r > 95 && r <= 100) {// 5% 传送至神秘层2
						if (instance.getAttribute("isInto" + mysteriousSceneId2) == null) {
							layer = 102;
							instance.setAttribute("isInto" + mysteriousSceneId2, true);
						}
					} else {
						layer -= 1;
					}
				} else {// 如果坐骑在豹子以下，则读取以下几率
					if (r > 0 && r <= 55) {// 55% 倒退一层
						layer -= 1;
					} else if (r > 55 && r <= 60) {// 5% 连续倒退三层
						layer -= 3;
					} else if (r > 60 && r <= 65) {// 5% 倒退至第一层
						layer = 1;
					} else if (r > 65 && r <= 75) {// 10% 传送至第10层
						layer = 10;
					} else if (r > 75 && r <= 80) {// 5% 传送至第5层
						layer = 5;
					} else if (r > 80 && r <= 90) {// 10% 传送至神秘层1
						if (instance.getAttribute("isInto" + mysteriousSceneId1) == null) {
							layer = 101;
							instance.setAttribute("isInto" + mysteriousSceneId1, true);
						}
					} else if (r > 90 && r <= 100) {// 10% 传送至神秘层2
						if (instance.getAttribute("isInto" + mysteriousSceneId2) == null) {
							layer = 102;
							instance.setAttribute("isInto" + mysteriousSceneId2, true);
						}
					} else {
						layer -= 1;
					}
				}
				if (layer <= 16) {
					if (layer < 1) {
						layer = 1;
					}
					isTriggerErrors[layer - 1] = true;
				}
			}
		}
		if (layer < 1) {
			layer = 1;
		}

		return layer;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneExChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene enterScene, SRole role, boolean isSure, STeleport teleport
		SInstance instance = (SInstance) args[0];
		// SScene scene =(SScene)args[1];
		SRole role = (SRole) args[2];
		// boolean isSure =(Boolean)args[3];
		STeleport teleport = (STeleport) args[4];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		int[] outDoors = (int[]) instance.getAttribute("outDoors");
		int layerOriginal = Integer.parseInt(instance.getAttribute("layerMiZong" + role.getId()).toString());

		logger.info("现在层数:" + layerOriginal);
		int layer = getNextLayer(role, teleport, layerOriginal, outDoors, instance);
		logger.info("进入层数:" + layer);
		instance.setAttribute("layerOriginal" + role.getId(), layerOriginal);
		instance.setAttribute("layerMiZong" + role.getId(), layer);

		int sceneId;
		if (layer == 101) {
			sceneId = mysteriousSceneId1;
		} else if (layer == 102) {
			sceneId = mysteriousSceneId2;
		} else {
			sceneId = sceneIds[layer - 1];
		}

		api.setInstanceEnterMsg(role, null, false);
		api.transToInstanceScene(role, instance, sceneId, 88, 74);
	}
}
