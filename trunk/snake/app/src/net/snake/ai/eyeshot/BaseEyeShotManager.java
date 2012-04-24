package net.snake.ai.eyeshot;

import java.util.Collection;
import java.util.Map;

import net.snake.ai.IEyeShotManager;
import net.snake.commons.NetTool;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.SceneObjManager;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneFeastMonster;
import net.snake.netio.message.ResponseMsg;

/**
 * 基础视野管理器 每一个可视对像有一个视野
 * 
 * @author serv_dev
 */
public class BaseEyeShotManager implements IEyeShotManager {
	/*** 我的视野，注意,包括我自己 ****/
	protected SceneObj myself;
	/*** 属于哪个场景.为了保证一定有值 **/
	protected Scene scene;
	/*** 视野比屏幕大了一圈，该圈的大小用此变量表明 *****/
	protected static final int EXTEND_EYESHOT = 4;
	/*** 32+8+8 *****/
	protected static final int EyeShotWidth = ClientConfig.ScreenTileWidth + EXTEND_EYESHOT * 2;
	protected static final int HalfEyeShotWidth = EyeShotWidth / 2;
	/*** 22+8+8 **/
	protected static final int EyeShotHeight = ClientConfig.ScreenTileHeight + EXTEND_EYESHOT * 2;
	protected static final int HalfEyeShotHeight = EyeShotHeight / 2;
	/**
	 * 移动检查的敏感度,越大性能越好 :坐标Ｘ或Y被改变多少时开始检查 由于视野比屏幕大一圈 EXTEND_EYESHOT,最大为敏感度为不得超过
	 * EXTEND_EYESHOT
	 **/
	protected static final int MoveCheckSensitivity = 4;
	/**** 视野最左 ****/
	protected int eyeShotX = 0;
	/**** 视野最上 ******/
	protected int eyeShotY = 0;
	/** 上一次检查时的X坐标 **/
	protected short previousCheckX = 0;
	/** 上一次检查时的坐标 *****/
	protected short previousCheckY = 0;
	/*** 视野范围内的可见对象存储容器 ********/
	protected SceneObjManager eyeShortObjManager;

	public BaseEyeShotManager(SceneObj sceneObj) {
		this.myself = sceneObj;
		eyeShortObjManager = new SceneObjManager(myself.getHeedSceneObject());
	}

	public SceneObj getMyself() {
		return myself;
	}

	/**
	 * 测试这个对象是否在我的视野范围内
	 * 
	 * @param obj
	 * @return
	 */
	protected boolean testIsInEyeShot(SceneObj obj) {
		short x = obj.getX();
		short y = obj.getY();
		return x >= eyeShotX && x < (eyeShotX + EyeShotWidth) && y >= eyeShotY && y < (eyeShotY + EyeShotHeight);
	}

	/***
	 * 测试这个坐标点 是否在我的视野范围内
	 */
	public boolean testIsInMyEyeShot(short[] point) {
		short x = point[0];
		short y = point[1];
		return x >= eyeShotX && x < (eyeShotX + EyeShotWidth) && y >= eyeShotY && y < (eyeShotY + EyeShotHeight);
	}

	/**
	 * 以我当前坐标重新订正视野矩形的位置 <br>
	 * 
	 * @param other
	 */
	private void checkviewbox(Scene scene, SceneObj other) {
		short sceneWidth = scene.getWidth();
		short sceneHeight = scene.getHeight();

		short x = other.getX();
		if (x - HalfEyeShotWidth < 0) {
			eyeShotX = 0;// x在地图的最左端
		} else if (x + HalfEyeShotWidth > sceneWidth) {
			eyeShotX = sceneWidth - EyeShotWidth;// x在地图的最右端
		} else {
			eyeShotX = other.getX() - HalfEyeShotWidth;
		}

		short y = other.getY();
		if (y - HalfEyeShotHeight < 0) {
			eyeShotY = 0;// y在地图的最上端
		} else if (y + HalfEyeShotHeight > sceneHeight) {
			eyeShotY = sceneHeight - EyeShotHeight;// y在地图的最下端
		} else {
			eyeShotY = other.getY() - HalfEyeShotHeight;
		}
	}

	@Override
	public void onEnterScene(Scene scene, boolean broadcastToOther) {
		this.scene = scene;
		// 创建视野区域
		previousCheckX = myself.getX();
		previousCheckY = myself.getY();
		checkviewbox(scene, myself);
		clearEyeShot();
		internalSelfEnterSceneCheck(broadcastToOther);

		myself.setSceneRef(scene);
	}

	public void addInMyEyeShot(SceneObj obj, boolean msg) {
		eyeShortObjManager.addVisibleObj(obj);
	}

	/***
	 * 当我移动时 移动产生的改变小于敏感度不做检查 重新根据坐标计算我视野内可见 的物体
	 */
	public void onMove() {
		if (Math.abs(previousCheckX - myself.getX()) < MoveCheckSensitivity && Math.abs(previousCheckY - myself.getY()) < MoveCheckSensitivity) {
			return;
		}
		previousCheckX = myself.getX();
		previousCheckY = myself.getY();
		Scene scene = myself.getSceneRef();
		if (scene == null) {
			return;
		}
		checkviewbox(scene, myself);
		internalSelfMoveCheck();
	}

	/***
	 * 视野中是否包含 这个可见对象
	 */
	public boolean isContains(SceneObj other) {
		return eyeShortObjManager.containsVisibleObj(other);
	}

	/***
	 * 清空我的视野管理器
	 */
	protected void clearEyeShot() {
		eyeShortObjManager.clear();
	}

	/****
	 * 从视野管理器中移除某个可见对象
	 */
	public void removeFromMyEyeShot(SceneObj other) {
		eyeShortObjManager.removeVisibleObj(other);
	}

	/****
	 * 当我离开场景时清空我的视野管理器 并告诉所有能看见我的可见对象 我离开了,并从他的视野管理器中移除
	 */
	public void onLeaveScene(Scene scene) {
		// Collection<Map<Integer, SceneObj>> eyeShortObj = eyeShortObjManager
		// .getAllSceneObject();
		for (Map<Integer, SceneObj> sceneObjMap : eyeShortObjManager.getAllSceneObject()) {
			Collection<SceneObj> objs = sceneObjMap.values();
			for (SceneObj other : objs) {
				if (other == myself) {
					continue;
				}
				other.getEyeShotManager().removeFromMyEyeShot(myself);
			}
		}
		clearEyeShot();
	}

	/**
	 * 当我进入场景时，检查哪些可见物体进入视野，并发送消息通知客户端
	 */
	protected void internalSelfEnterSceneCheck(boolean broadcastToOther) {
		// Class []class1=this.myself.getHeedSceneObject();
		// int length=class1.length;
		for (int clazz : myself.getHeedSceneObject()) {
			Collection<SceneObj> collSceneObjs = scene.getSceneObjByClass(clazz);
			if (collSceneObjs == null) {
				continue;
			}
			for (SceneObj other : collSceneObjs) {
				if (testIsInEyeShot(other)) {
					addInMyEyeShot(other, true);
					other.getEyeShotManager().addInMyEyeShot(myself, broadcastToOther);
				}
			}
		}
		addInMyEyeShot(myself, true);

	}

	/**
	 * 当我移动时，检查哪些可见物体进入视野，并发送消息通知客户端
	 */
	public void checkOthersMove(SceneObj other) {
		if (eyeShortObjManager.containsVisibleObj(other)) {// 原本就在我的视野中
			if (!testIsInEyeShot(other)) {// 离开我的视野了
				removeFromMyEyeShot(other);
				// 其他人也把我从视野中移除
				other.getEyeShotManager().removeFromMyEyeShot(myself);
			}
		} else {// 不在我的视野中
			if (testIsInEyeShot(other)) {// 满足进入我的视野的条件了
				addInMyEyeShot(other, true);
				// 其他人也把我添加到视野中
				if (other instanceof SceneFeastMonster) {
					// LoggerProvider.debug("看到了婚宴怪");
				}
				other.getEyeShotManager().addInMyEyeShot(myself, true);
			}
		}
	}

	/****
	 * 检查自己的视野范围 当我的视野范围内中有可见对象时则 添加到我的视野管理器中
	 * 
	 */
	public void internalSelfMoveCheck() {
		for (int clazz : myself.getHeedSceneObject()) {
			Collection<SceneObj> collSceneObjs = scene.getSceneObjByClass(clazz);
			if (collSceneObjs == null) {
				continue;
			}
			for (SceneObj other : collSceneObjs) {
				if (this.myself == other) {
					continue;
				}
				other.getEyeShotManager().checkOthersMove(myself);
			}
		}
	}

	/****
	 * 获得所有的怪物
	 */
	public Collection<SceneMonster> getMonsters() {
		return eyeShortObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_MonsterScene);
	}

	@Override
	public void sendMsg(ResponseMsg msg) {
		Collection<Hero> cs = getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(cs, msg);
	}

	@Override
	public void sendMsg(ResponseMsg msg, Hero exclude) {
		// Collection<Character> characters=getEyeShortObjs(Character.class);
		// if(characters==null)
		// {
		// int i=0;
		// }
		Collection<Hero> cs = getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(cs, exclude, msg);
	}

	/***
	 * 通过对象类型 过的视野内对应类型的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getEyeShortObjs(int sceneObjType) {
		return (Collection<T>) eyeShortObjManager.getVisibleObjsCollection(sceneObjType);
	}

	@Override
	public <T> T getEyeShortObjs(int clazz, int id) {
		return eyeShortObjManager.getVisibleObjsByClazzAndId(clazz, id);
	}

}
