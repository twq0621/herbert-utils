package net.snake.gamemodel.team.logic;

import java.io.IOException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.ServerResponse;
import net.snake.netio.player.GamePlayer;


/**
 * 组队接口
 * 
 * @author serv_dev
 * 
 */
public interface Team extends PropertyChangeListener{
	public TeamManager getTeamManager();
	/**
	 * 获取队伍中所有玩家信息 Character[i]==null表示没有该玩家， Character[i].getPlayer()==null
	 * 表示玩家离线
	 * @return
	 */
	public  List<Hero> getCharacterPlayers();

	/**
	 * 队伍内部广播
	 * 
	 * @param response
	 * @param player
	 */
	public void sendTeamMsg(ServerResponse response, GamePlayer exclude);

	/**
	 * 队伍更换队长，根据新队长id 更新新队长属性 以及老队长属性值，返回null 说明不存在该玩家角色
	 * 
	 * @param id
	 * @return
	 */
	public Hero changeTeamLeader(int id);

	/**
	 * 队长解散队伍
	 */
	public void takeOffTeam();

	/**
	 * 老队长无法履行队长职责，随机选择队长 ，返回队长角色
	 * 
	 * @return
	 */
	public Hero randomTeamLeader();

	/**
	 * 队员加入队伍，返回组内编号，返回1001 标识假如失败
	 * 
	 * @param character
	 * @return
	 */
	public int addCharacter(Hero character);

	/**
	 * 获取队伍状态（0创建 中 1创建成功）
	 * 
	 * @return
	 */
	public byte getStatu();

	public void setStatu(byte statu);

	/**
	 * 更具玩家id 获取玩家角色对象 ，返回null 标识该玩家没有加入本队伍
	 * 
	 * @param id
	 * @return
	 */
	public Hero getCharacter(int id);


	/**
	 * 获取该队伍的队长角色
	 * 
	 * @return
	 */
	public Hero getTeamLeader();
    /**
     * 获取队伍id
     * @return
     */
	public Integer getTeamId();
    /**
     * 获取队伍等级
     * @return
     */
	public int getTeamLevel();

	public void setTeamLevel(int teamLevel);
   /**
    * 获取队伍人数
    * @return
    */
	public int getTeamPopulation();
	public int getTeamPopulationInScene(Scene scene);

	/**
	 * 把characterId从队伍中移除
	 * 
	 * @param characterId
	 * @param type
	 *            什么类型的移除
	 * @return
	 * @throws IOException
	 */
	public Hero removeCharacter(int characterId);

	/**
	 * 获取队伍宣言
	 * 
	 * @return
	 */
	public String getTeamDeclare();

	/**
	 * 设置队伍宣言
	 * 
	 * @param teamDeclare
	 */
	public void setTeamDeclare(String teamDeclare);

	/**
	 * 检查队伍等级数量
	 */
	public void checkteamstatus();

	/**
	 * 获取同地图其他队友信息
	 * 
	 * @return
	 */
	public List<Hero> getOtherCharactersInSameScent( Hero character);

	/**
	 * 获取当前阵法
	 * 
	 * @return
	 */
	public TeamFightingController getNowTfc();

	/**
	 * 添加当前阵法
	 * 
	 * @param nowTfc
	 */
	public void setNowTfc(TeamFightingController nowTfc);

	/**
	 * 更新队友阵法加成
	 */
	public void updateAllPlayersTeamFighting();
	/**
	 * 设置队伍阵法属性加成
	 * @param pe
	 */
	public void setPropertyEntity(PropertyEntity pe);
	/**
	 * 获取阵法队伍成员属性加成
	 */
	public PropertyEntity getPropertyEntity();
}
