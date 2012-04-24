package net.snake.gamemodel.fight.logic;

import net.snake.gamemodel.fight.bean.CharacterVehicle;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.gamemodel.fight.persistence.CharacterVehicleManager;
import net.snake.gamemodel.fight.persistence.GongchengVehicleManager;
import net.snake.gamemodel.fight.response.VehicleMsg51116;
import net.snake.gamemodel.fight.response.VehicleMsg51122;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.shell.Options;


import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


/**
 * 攻城车管理器
 * @author serv_dev
 */
public class MyCharacterVehicleManager {
private static Logger logger = Logger
		.getLogger(MyCharacterVehicleManager.class);
	public Map<Integer, CharacterVehicle> map = new ConcurrentHashMap<Integer, CharacterVehicle>();
	private boolean isSending = false;
	private Hero character;
	private SendVehiecleTimer sendVehicleTimer;
	private int sendVechieveId;
	private short x;
	private short y;
	private Scene sendtoScene;

	public void destory(){
		map.clear();
		map=null;
		sendVehicleTimer=null;
	}
	public MyCharacterVehicleManager(Hero character) {
		this.character = character;
		sendVehicleTimer = new SendVehiecleTimer(character);
	}

	public void init() {
		try {
			List<CharacterVehicle> list = null;
			if (Options.IsCrossServ) {
				list=  character.getVlineserver().getAcrossVehicleManager().getCharacterVehicle(character);
			} else {
				list = CharacterVehicleManager.getInstance()
						.selectVechicleListByCharacterId(character.getId());
			}
			if (list == null || list.size() == 0) {
				return;
			}
			for (CharacterVehicle cv : list) {
				map.put(cv.getVehicleId(), cv);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 炮弹发射 进入倒计时
	 * 
	 * @param scene
	 * 
	 * @param vechieveId
	 * @param x
	 * @param y
	 */
	public void startSendShells(Scene scene, int vechieveId, short x, short y) {
		if (isSending) {
			return;
		}
		CharacterVehicle cv = getCharacterVehicleByVehicleId(vechieveId);
		if (cv == null || cv.getVehicleCount() < 1) {
			character.sendMsg(new VehicleMsg51122(14519));
			return;
		}
		isSending = true;
		this.sendVechieveId = vechieveId;
		this.x = x;
		this.y = y;
		this.sendtoScene = scene;
		sendVehicleTimer.start();

	}

	/**
	 * 打断发炮过程
	 */
	public void breakSenderShells() {
		this.isSending = false;
		sendVehicleTimer.shutdown();

	}

	/**
	 * 请求攻城车面板信息
	 * 
	 * @param vechicleId
	 * @param npcId 
	 */
	public void requestGongVechicleInfo(int vechicleId, int npcId) {
		CharacterVehicle cv = getCharacterVehicleByVehicleId(vechicleId);
		GongchengVehicle gongchengVehicle = GongchengVehicleManager
				.getInstance().getGongchengVehicleByVehicleId(vechicleId);
		character.sendMsg(new VehicleMsg51116(cv, gongchengVehicle,npcId));
	}

	public CharacterVehicle getCharacterVehicleByVehicleId(int vechicleId) {
		return map.get(vechicleId);
	}

	public boolean isSending() {
		return isSending;
	}

	public void setSending(boolean isSending) {
		this.isSending = isSending;
	}

	/**
	 * 炮弹向帮战地图发射（爆炸阶段）
	 */
	public void sendShellsToFactionCtScene() {
		Scene scene = this.sendtoScene;
		this.isSending = false;
		this.sendtoScene = null;
		if (scene instanceof GongchengTsMap) {
			GongchengTsMap sceneImp = (GongchengTsMap) scene;
			if (!sceneImp.isGongchengState) {
				character.sendMsg(new VehicleMsg51122(14520));
				return;
			}
			if (removeVehicle(this.sendVechieveId, 1)) {
				sceneImp.sendVechieveToHurtRole(this.sendVechieveId, x, y,
						character);
			} else {
				character.sendMsg(new VehicleMsg51122(14519));
			}
		} else if (scene instanceof KuafuZhanTsMap) {
			KuafuZhanTsMap sceneImp = (KuafuZhanTsMap) scene;
			if (!sceneImp.isKuafuZhanState) {
				character.sendMsg(new VehicleMsg51122(14520));
				return;
			}
			if (removeVehicle(this.sendVechieveId, 1)) {
				sceneImp.sendVechieveToHurtRole(this.sendVechieveId, x, y,
						character);
			} else {
				character.sendMsg(new VehicleMsg51122(14519));
			}
		}

	}

	public boolean removeVehicle(int vehiecleId, int count) {
		CharacterVehicle cv = getCharacterVehicleByVehicleId(vehiecleId);
		if (cv.getVehicleCount() < count) {
			return false;
		}
		cv.setVehicleCount(cv.getVehicleCount() - count);
		if (Options.IsCrossServ) {
			return true;
		}
		if (cv.getVehicleCount() == 0) {
			CharacterVehicleManager.getInstance().deleteCharacterVehicle(cv);
		} else {
			CharacterVehicleManager.getInstance().updateCharacterVehicle(cv);
		}
		return true;
	}

	public void addVehicle(int vehicleId, int count) {
		CharacterVehicle cv = getCharacterVehicleByVehicleId(vehicleId);
		if (cv == null) {
			cv = new CharacterVehicle();
			cv.setCharacterId(character.getId());
			cv.setVehicleId(vehicleId);
			cv.setVehicleCount(count);
			CharacterVehicleManager.getInstance().insertCharacterVehicle(cv);
			map.put(cv.getVehicleId(), cv);
		} else {
			cv.setVehicleCount(cv.getVehicleCount() + count);
			CharacterVehicleManager.getInstance().updateCharacterVehicle(cv);
		}
	}

}
