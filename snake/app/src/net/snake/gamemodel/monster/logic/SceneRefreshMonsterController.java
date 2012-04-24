package net.snake.gamemodel.monster.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.program.FrameIgnoreCount;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;




/**
 * 场景候补 怪物
 * 
 * @author serv_dev
 * 
 */
public class SceneRefreshMonsterController implements Updatable{
	private Scene scene;
	private static final Logger logger = Logger.getLogger(SceneRefreshMonsterController.class);
	// map<怪物模型id，怪物s>
	//key 怪物ID与  scenemonster
	private LinkedList<SceneMonster> scenemonsterList=new LinkedList<SceneMonster>();
	//key候补ID
	private FrameIgnoreCount frameignorecount=new FrameIgnoreCount(90);//3秒检查一次,不用非常实时

	public SceneRefreshMonsterController(Scene scene) {
		this.scene=scene;
	}

	/**
	 *
	 * 将死亡的怪物放入候补列表
	 * 
	 * @param sceneMonster
	 * @param isReservation 是否预约候补怪物
	 */
	public void addSceneMonster(SceneMonster sceneMonster) {
		scenemonsterList.addLast(sceneMonster);
	}

	/**
	 * 监听候补怪物队列 如果符合候补条件的 则添加到当前场景中
	 * 
	 */
	public void update(long now) {
		try {
			// 得到所有可以 上场的怪物 上场条件
			// 当 当前时间-怪物的预约时间>waitingTime
			if(frameignorecount.isNeedReturn()){
				return;
			}
			long currenttime=System.currentTimeMillis();
			for(Iterator<SceneMonster> iterator=scenemonsterList.iterator();iterator.hasNext();){
				SceneMonster sm=iterator.next();
				if(sm.isReadyShow(currenttime)){
					iterator.remove();
					scene.enterScene(sm);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/***
	 * 获取场景中的boss列表
	 * @return
	 */
	public List<SceneMonster> getBossList(){
		List<SceneMonster> l=new ArrayList<SceneMonster>();
		for (SceneMonster monster: scenemonsterList) {
			if(monster.getMonsterModel().isBoss()){
				if(!l.contains(monster)){
					l.add(monster);
				}
			}
		}
		return l;
	}
	/**
	 * 是否存在准备进入场景的怪物
	 * @return
	 */
	public boolean isHaveRefreshMonster(){
		if(scenemonsterList.size()<1){
			return false;
		}
		return true;
	}
	/***
	 * GM命令,刷出所有BOSS
	 */
	public void showBoss() {
		for(Iterator<SceneMonster> iterator=scenemonsterList.iterator();iterator.hasNext();){
			SceneMonster sm=iterator.next();
			if(sm.getMonsterModel().isBoss()){
				iterator.remove();
				scene.enterScene(sm);
			}
		}
	}

	public LinkedList<SceneMonster> getScenemonsterList() {
		return scenemonsterList;
	}
}
