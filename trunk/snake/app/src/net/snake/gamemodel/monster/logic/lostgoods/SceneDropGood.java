package net.snake.gamemodel.monster.logic.lostgoods;

import java.util.Collection;

import org.apache.log4j.Logger;

import net.snake.ai.IEyeShotManager;
import net.snake.ai.eyeshot.BaseEyeShotManager;
import net.snake.commons.program.IntId;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.goods.GoodsEnterEyeshot11160;
import net.snake.gamemodel.map.response.goods.GoodsLeaveEyeshot11164;
import net.snake.gamemodel.map.response.goods.SceneDropGoodMsg11162;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.message.ResponseMsg;



/**
 * 掉落物品（掉落到场景的物品封装）
 * 
 * @author serv_dev
 * 
 */
public class SceneDropGood extends SceneObj implements Updatable {
	private static Logger logger = Logger.getLogger(SceneDropGood.class);

	private static IntId intId = new IntId();
	private CharacterGoods cg; // 金钱的话物品某型id==-1
	Hero character;
	private long dropTime = System.currentTimeMillis();
	private IEyeShotManager eyeshot = new BaseEyeShotManager(this);
	private int monsterId;

	public int getAllObjInHeap() throws Exception {
		int size = (cg == null ? 0 : 1);
		size = size + (character == null ? 0 : 1);
		int[] c = getHeedSceneObject();
		for (int i = 0; i < c.length; i++) {
			Collection<SceneObj> col = eyeshot.getEyeShortObjs(c[i]);
			if (col != null) {
				size = size + col.size();
			}
		}
		return size;
	}

	public SceneDropGood(CharacterGoods cg, Hero character, SceneMonster monster) {
		this.cg = cg;
		this.dropTime = System.currentTimeMillis();
		this.setId(intId.getNextId());
		if (cg.isOwner) {
			this.character = character;
		}
		setX(monster.getX());
		setY(monster.getY());
		this.monsterId = monster.getId();
	}

	public SceneDropGood(CharacterGoods cg, Hero character) {
		this.cg = cg;
		this.dropTime = System.currentTimeMillis();
		this.setId(intId.getNextId());
		this.character = null;
		setX(character.getX());
		setY(character.getY());
		this.monsterId = character.getId();
	}

	public boolean isMoney() {
		return cg.getGoodmodelId() == -1;
	}

	/**
	 * 进入场景初始化信息
	 */
	public void initScenePositon(Scene scene) {
		setSceneRef(scene);
		short[] point = scene.getRandomPoint(getX(), getY(), 7);
		setX(point[0]);
		setY(point[1]);
	}

	public boolean isPickUp(Hero picker) {
		if (character == null) {
			return true;
		}
		int mid = character.getId();
		if (mid == picker.getId()) {
			return true;
		}
		long time = System.currentTimeMillis() - dropTime;
		if (picker.getMyTeamManager().isTeam() && character.getMyTeamManager().isTeam()) {
			int teamId = character.getMyTeamManager().getMyTeam().getTeamId();
			if (picker.getMyTeamManager().getMyTeam().getTeamId() == teamId) {
				if (time > 5 * 1000) {
					return true;
				}
				// int t = (int) (5 * 1000 - time - 1) / 1000 + 1;
				return false;
			}
		}
		if (time > 15 * 1000) {
			return true;
		}
		// int t = (int) (15 * 1000 - time - 1) / 1000 + 1;
		return false;
	}

	public boolean isPickUpAndSendMsg(Hero picker) {
		if (character == null) {
			return true;
		}
		int mid = character.getId();
		if (mid == picker.getId()) {
			return true;
		}
		long time = System.currentTimeMillis() - dropTime;
		if (picker.getMyTeamManager().isTeam() && character.getMyTeamManager().isTeam()) {
			int teamId = character.getMyTeamManager().getMyTeam().getTeamId();
			if (picker.getMyTeamManager().getMyTeam().getTeamId() == teamId) {
				if (time > 5 * 1000) {
					return true;
				}
				int t = (int) (5 * 1000 - time - 1) / 1000 + 1;
				//
				picker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1013, t + ""));
				return false;
			}
		}
		if (time > 15 * 1000) {
			return true;
		}
		int t = (int) (15 * 1000 - time - 1) / 1000 + 1;
		picker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1013, t + ""));
		return false;
	}

	public CharacterGoods getCg() {
		return cg;
	}

	@Override
	public void update(long now) {
		try {
			now = System.currentTimeMillis();
			if (now - dropTime > 60 * 1000) {
				getSceneRef().leaveScene(this);
				return;
			}
			if (now - dropTime > 15 * 1000) {
				this.character = null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public ResponseMsg getEnterEyeshotMsg() {
		return new GoodsEnterEyeshot11160(this);
	}

	@Override
	public ResponseMsg getLeaveEyeshotMsg() {
		return new GoodsLeaveEyeshot11164(this);
	}

	@Override
	public IEyeShotManager getEyeShotManager() {
		return eyeshot;
	}

	public int getMonsterId() {
		return monsterId;
	}

	public long getDropTime() {
		return dropTime;
	}

	public Hero getOwn() {
		return this.character;
	}

	@Override
	public void onEnterScene(Scene scene) {
		initScenePositon(scene);
		getEyeShotManager().onEnterScene(scene, false);
		getEyeShotManager().sendMsg(new SceneDropGoodMsg11162(this));
	}

	@Override
	public void onLeaveScene(Scene scene, Scene newscene) {
		getEyeShotManager().onLeaveScene(scene);
		setSceneRef(null);
	}

	private static final int[] heedSceneObjects = { SceneObj.SceneObjType_Hero };

	@Override
	public int[] getHeedSceneObject() {
		return heedSceneObjects;
	}

	@Override
	public int getSceneObjType() {
		return SceneObjType_DropedGood;
	}
}
