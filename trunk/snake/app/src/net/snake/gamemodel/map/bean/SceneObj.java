package net.snake.gamemodel.map.bean;

import net.snake.ai.IEyeShotManager;
import net.snake.ai.formula.DistanceFormula;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.ResponseMsg;


/**
 * 该类表示一切可以放到场景中的对像
 * 
 * @author serv_dev
 * 
 */
public abstract class SceneObj {
	
	public static final int SceneObjType_Hero=1;
	public static final int SceneObjType_MonsterScene=2;
	public static final int SceneObjType_Horse=3;

	public static final int SceneObjType_DropedGood=5;
	public static final int SceneObjType_MonsterBangqi=6;
	public static final int SceneObjType_MonsterFactionCt=7;
	public static final int SceneObjType_MonsterFeast=8;
	public static final int SceneObjType_MonsterXuanyuan=9;
		
	protected Integer id; // id会经常做key值,所以不作基本类型
	protected volatile short x;// x坐标
	protected volatile short y;// y坐标
	protected volatile Scene sceneRef;//场景引用
	protected volatile int scene;// 场景

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getScene() {
		return this.scene;
	}

	/**
	 * 场景
	 * 
	 * @param Integer
	 *            scene
	 */
	public void setScene(int scene) {
		this.scene = scene;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getX() {
		return this.x;
	}

	public void setY(short y) {
		this.y = y;
	}

	public short getY() {
		return this.y;
	}

	public void setSceneRef(Scene sceneRef) {
		this.sceneRef = sceneRef;
		if (sceneRef != null) {
			//注意,这里不能清空场景ID,scene 将用于数据库保存用
			this.scene = sceneRef.getId();
		}
	}

	public Scene getSceneRef() {
		return sceneRef;
	}

	public float getDistance(SceneObj obj) {
		return DistanceFormula.getDistance2(x, y, obj.getX(), obj.getY());
	}

	public int getGridMaxDistance(SceneObj obj) {
		return getGridMaxDistance(obj.getX(), obj.y);
	}

	/**纵向或者横向的格子的差距**/
	public int getGridMaxDistance(short targetx, short targety) {
		int xdis = Math.abs(targetx - x);
		int ydis = Math.abs(targety - y);
		return xdis > ydis ? xdis : ydis;
	}

	/**
	 * 把我设置成其他人的位置
	 * @param other
	 */
	public void setToOthersPostion(SceneObj other) {
		x = other.x;
		y = other.y;
	}

	/**
	 * 获得该对像进入视野时的消息
	 * 
	 * @return
	 */
	public abstract ResponseMsg getEnterEyeshotMsg();

	/**
	 * 获得该对像离开视野时的消息
	 * 
	 * @return
	 */
	public abstract ResponseMsg getLeaveEyeshotMsg();

	/**
	 * 获得视野管理器
	 * 
	 * @return
	 */
	abstract public IEyeShotManager getEyeShotManager();

	/**
	 * 当进入场景时触发,由e对像回调
	 * @param scene
	 */
	abstract public void onEnterScene(Scene scene);
	/**
	 * 当离开场景时触发,由场景对像回调
	 * @param scene
	 * @param newscene
	 */
	abstract public void onLeaveScene(Scene scene, Scene newscene);
	
	/***获得自己所关心的 场景对象类型******/
	public abstract int[] getHeedSceneObject();
	
	public abstract int getSceneObjType();
}
