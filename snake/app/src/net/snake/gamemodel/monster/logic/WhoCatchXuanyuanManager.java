package net.snake.gamemodel.monster.logic;
/**
 * 
 * 怪物帮会怪物管理
 * @author serv_dev
 */
import java.util.Set;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.map.logic.Scene;

import org.apache.mina.util.ConcurrentHashSet;


public class WhoCatchXuanyuanManager {
	private final SceneXuanyuanMonster scenemonster;
	private Set<Hero> charactersList=new ConcurrentHashSet<Hero>();
	public WhoCatchXuanyuanManager(SceneXuanyuanMonster scenemonster) {
		this.scenemonster=scenemonster;
	}
	/***增加捕获者**/
	public void addCatcher(Hero character){
		charactersList.add(character);
	}
	/**移除捕获者**/
	public void removeCatcher(Hero character){
		charactersList.remove(character);
	}
	/**移除所有的捕获者**/
	public void removeAllCatcher(){
		charactersList.clear();
	}
	
	//TODO 我的捕获时间到了，激发最后的捕获判断 如果成功则为角色创建坐骑，并从场景中移除此怪物,
	//注，但不从
	public void catchTimeOK(Hero character){
		if(charactersList.contains(character)){
			//捕获成功添加数据库，还差一个角色马的更新
			Scene scene=scenemonster.getSceneRef();	
			if(scene instanceof KuafuZhanTsMap){
				KuafuZhanTsMap sc=(KuafuZhanTsMap)scene;
				sc.catchXuanyuanSucess(scenemonster,character);
			}
		}
		//一个人成功之后通知别人失败
		for (Hero c : charactersList) {
			removeCatcher(c);
			if(c!=character){
				character.getCatchXuanyuanJianActionController().breakCatch();
			}
		}
	}
}
