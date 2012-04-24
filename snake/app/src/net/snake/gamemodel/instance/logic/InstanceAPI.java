package net.snake.gamemodel.instance.logic;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
public interface InstanceAPI {

	/**
	 * 往场景中添加怪物，怪物需要配置在t_scene_monster中配置 t_scene_monster中需要增加怪物路线,以支持塔防类副本
	 * 怪物路线表达式为 [x,y][x,y][x,y][x,y] 该设置为了支持分波打怪用
	 * @param sceneid
	 * @param monsterid
	 */
	public void addMonster(int sceneid,int scenemonsterid);	
	/**
	 * 往场景中根据怪物模版创建怪物,并添加到场景中,即提供一种方式,不用在数据库中配置,也能往场景中添加怪物
	 * @param sceneid
	 * @param modelid
	 * @param x
	 * @param y
	 * @param isrelive
	 * @return
	 */
	public SceneMonster createMonsterToScene(int sceneid, int modelid,
			 int x, int y, boolean isrelive);
	/**
	 * 获得已经放到场景中的怪物实例
	 * @param sceneid
	 * @param scenemonsterid
	 * @return
	 */
	public SceneMonster getMonster(int sceneid,int scenemonsterid);
	/**
	 * 从场景中移除怪物
	 * @param sceneid
	 * @param scenemonsterid
	 */
	public void removeMonster(int sceneid,int scenemonsterid);
	/**
	 * 根据批号增加怪物
	 * @param sceneid
	 * @param batchNum
	 */
	public void addMonsterByBatchNum(int sceneid,int batchNum);
	

	/**
	 * 让怪物说话,可用于当玩家进入到区域
	 * 
	 * @param scenemonsterid
	 * @param message
	 */
	public void speek(int sceneid,int scenemonsterid, String message);

	/**
	 * 打开地图传送点
	 * 
	 * @param sceneid
	 * @param pointid
	 */
	public void openTransPoint(int sceneid, int pointid);

	/**
	 * 判断是否第一次到达区域
	 * 
	 * @param character
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isFirstEnterArea(Hero character, int sceneid, int x,
			int y, int width, int height);

	public boolean isFirstEnterArea(SceneMonster character, int sceneid, int x,
			int y, int width, int height);
	/**
	 * 是不是副本中所有的怪物都死亡了
	 * 
	 * @return
	 */
	public boolean allMonsterInInstanceIsDie();
	/**
	 * 是不是某副本场景中所有的怪物都死亡了
	 * @param sceneid
	 * @return
	 */
	public boolean allMonsterInSceneIsDie(int sceneid);
	/**
	 * 向所有人发送副本提示消息
	 * @param msg
	 */
	public void sendMsgDialogToAll(String msg);
	public void sendMsgDialog(Hero character, String msg);
	/**
	 * 关闭副本，如果有人在副本中的话，将其传送到副本外
	 */
	public void shutdown();
	/**
	 * 关闭副本，如果有人在副本中的话，将其传送到副本外
	 * 延迟关闭
	 */
	public void shutdown(int delay);	
	

}
