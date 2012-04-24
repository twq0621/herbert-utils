package net.snake.ai;

import java.util.Collection;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.ResponseMsg;


public interface IEyeShotManager {	
	/**
	 * 是否在我的视野中
	 * 
	 * @param object
	 * @return
	 */
	public boolean isContains(SceneObj other);
	
	/**
	 * 向我的视野发送消息(包含视野主人)
	 * @param msg
	 */
	public void sendMsg(ResponseMsg msg);
	/**
	 * 向我的视野发送消息但排除某人
	 * @param msg
	 * @param exclude
	 */
	public void sendMsg(ResponseMsg msg,Hero exclude);
	

	/**
	 * 初始化我的视野，同时把我加入到其他人的视野
	 */
	public void onEnterScene(Scene scene,boolean broadcastToOther);
	/**
	 * 当我自己离开场景
	 * @param scene
	 */
	public void onLeaveScene(Scene scene);
	/**
	 * 当我移动时
	 */
	public void onMove();
	/**
	 * 根据 对应的class获得对象结合
	 */
	public <T> Collection<T> getEyeShortObjs(int sceneObjType);
	
	
	/**
	 * 根据 对应的class,id获得对象结合
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return 对象object
	 */
	public <T> T getEyeShortObjs(int clazz,int id);
	
	/***
	 * 添加到我的视野中
	 * @param obj
	 * @param msg
	 */
	public void addInMyEyeShot (SceneObj obj,boolean msg);
	/***
	 * 从我的视野中移除
	 * @param obj
	 */
	public void removeFromMyEyeShot(SceneObj obj);
	/***
	 * 当我移动时，检查哪些可见物体进入视野
	 * @param obj
	 */
	public void  checkOthersMove(SceneObj obj);
	/**
	 * 是否在我的视野中
	 * @param point
	 * @return
	 */
	public boolean testIsInMyEyeShot(short[] point);
}
