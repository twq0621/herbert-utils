package net.snake.gameserver.event.copy;

import net.snake.commons.GenerateProbability;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

import org.apache.log4j.Logger;

public class NianLun_SceneExcange extends SuperInstance implements IEventListener {
	private static Logger logger = Logger.getLogger(NianLun_SceneExcange.class);

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneExChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		if (instance.getInstanceId() != NianLun_SceneInit.instanceId) {
			return;
		}
		SScene scene = (SScene) args[1];
		SRole role = (SRole) args[2];
		api.clearSceneMonster(scene);
		int sceneId = this.getNextSceneId(instance);
		api.setInstanceEnterMsg(role, null, false);
		api.transToInstanceScene(role, instance, sceneId, 0, 0);
	}

	private int getNextSceneId(SInstance instance) {
		int sceneId = 0;
		int layer = 1;
		if (instance.getAttribute("layer") != null) {
			layer = Integer.parseInt(instance.getAttribute("layer").toString());
		}
		// logger.info("layer==" + layer);
		if (layer >= 11) {
			sceneId = NianLun_SceneInit.endSceneId;
			instance.setAttribute("xingqiVal", false);
			instance.setAttribute("cxqd", 15);
			instance.setAttribute("nowSceneId", sceneId);
			return sceneId;
		}
		int addLayer = 1;
		// 0是死门，1是生门
		boolean rnd = GenerateProbability.isGenerate(50, 100);
		// logger.info("rnd=" + rnd);
		if (rnd) {
			// 减少副本ID数（1~3）随机（2的机率最大，3的机率非常小。）
			if (GenerateProbability.isGenerate(80, 100)) {
				addLayer = 2;
			} else if (GenerateProbability.isGenerate(60, 100)) {
				addLayer = 1;
			} else {
				addLayer = 3;
			}
			addLayer = -1 * addLayer;
		} else {
			// 增加副本ID数（1~3）随机（1的机率最大，3的机率非常小）
			if (GenerateProbability.isGenerate(80, 100)) {
				addLayer = 1;
			} else if (GenerateProbability.isGenerate(60, 100)) {
				addLayer = 2;
			} else {
				addLayer = 3;
			}
		}
		// logger.info("addLayer=" + addLayer);
		sceneId = Integer.parseInt(instance.getAttribute("nowSceneId").toString());
		sceneId = sceneId + addLayer;
		// logger.info("sceneId=" + sceneId);
		if (sceneId < NianLun_SceneInit.startSceneId) {
			sceneId = NianLun_SceneInit.startSceneId;
		} else if (sceneId > NianLun_SceneInit.endSceneId) {
			sceneId = NianLun_SceneInit.endSceneId;
		}
		if (sceneId == NianLun_SceneInit.endSceneId) {
			instance.setAttribute("xingqiVal", true);
		}
		int cxqd = this.getCXQD(sceneId);
		instance.setAttribute("cxqd", cxqd);
		instance.setAttribute("nowSceneId", sceneId);
		return sceneId;
	}

	private int getCXQD(int sceneId) {
		int cxqd = 12;
		int yf = sceneId - NianLun_SceneInit.startSceneId + 1;
		// logger.info("yf=" + yf);
		// 春12 夏13 秋14 冬15
		switch (yf) {
		case 1:
			cxqd = 15;
			break;
		case 2:
			cxqd = 15;
			break;
		case 3:
			cxqd = 12;
			break;
		case 4:
			cxqd = 12;
			break;
		case 5:
			cxqd = 12;
			break;
		case 6:
			cxqd = 13;
			break;
		case 7:
			cxqd = 13;
			break;
		case 8:
			cxqd = 13;
			break;
		case 9:
			cxqd = 14;
			break;
		case 10:
			cxqd = 14;
			break;
		case 11:
			cxqd = 14;
			break;
		case 12:
			cxqd = 15;
			break;
		}
		return cxqd;
	}
}
