package net.snake.gamemodel.team.logic;


import java.util.HashMap;
import java.util.Map;


/**
 * 组队存储类
 * @author serv_dev
 *
 */

public class TeamManager{
	
	//单例实现=====================================
//	private static TeamManager instance;
	public TeamManager() {		
	}
//	public static TeamManager getInstance() {
//		if (instance == null) {
//			instance=new TeamManager();
//		}
//		return instance;
//	}
	
	//单例实现========================================
	
	private Map<Integer,Team> downLineMap=new HashMap<Integer, Team>();//组队状态下线玩家退出 临时保存其组队信息，执行定时更新时清除
	
	private Map <Integer,Team> teamMap = new HashMap<Integer, Team>();

	public Map<Integer, Team> getTeamMap() {
		return teamMap;
	}
	public void addTeam(Team team){
		this.teamMap.put(team.getTeamId(), team);
	}
	public Team getTeam(Integer key){
		return this.teamMap.get(key);
	}
	public void removeTeam(Integer key){
		this.teamMap.remove(key);
	}
	
	
	public Map<Integer, Team> getDownLineMap() {
		return downLineMap;
	}
	public void setDownLineMap(Map<Integer, Team> downLineMap) {
		this.downLineMap = downLineMap;
	}
	public Team setDownLineCharacter(Integer key, Team team){
		return this.downLineMap.put(key, team);
	}
	public Team getDownLineCharacter(Integer key){
		return this.downLineMap.get(key);
	}
	public Team removeDownLineCharacter(Integer key){
		return this.downLineMap.remove(key);
	}

}
