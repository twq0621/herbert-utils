package net.snake.gamemodel.monster.logic;
/**
 * 
 * 怪物帮会怪物管理
 * @author serv_dev
 */
import java.util.Set;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.Scene;

import org.apache.mina.util.ConcurrentHashSet;


public class WhoCatchYoulongManager {
	private final SceneFactionCtMonster scenemonster;
	private Set<Hero> charactersList=new ConcurrentHashSet<Hero>();
	public WhoCatchYoulongManager(SceneFactionCtMonster scenemonster) {
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
	
	public Set<Hero> getCharactersList() {
		return charactersList;
	}
	//TODO 我的捕获时间到了，激发最后的捕获判断 如果成功则为角色创建坐骑，并从场景中移除此怪物,
	//注，但不从
	public void catchTimeOK(Hero character){
		if(charactersList.contains(character)){
			//捕获成功添加数据库，还差一个角色马的更新
			Scene scene=scenemonster.getSceneRef();	
			if(scene instanceof GongchengTsMap){
				GongchengTsMap sc=(GongchengTsMap)scene;
				sc.catchYoulongSucess(scenemonster,character);
			}
		}
		//一个人成功之后通知别人失败
		for (Hero c : charactersList) {
			removeCatcher(c);
			if(c!=character){
				c.getCatchYoulongActionController().breakCatch();
			}
		}
	}
}
