package net.snake.gamemodel.team.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.snake.commons.Language;
import net.snake.commons.program.IntId;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.ServerResponse;
import net.snake.netio.player.GamePlayer;

/**
 * 队伍实体类(队伍成员集中管理 队伍阵法加成监听器)
 * 
 * @author serv_dev
 */

public class GameTeam implements Team {
	/** 0创建状态 */
	public final static byte CREATING = 0;//
	/** 1创建成功 */
	public final static byte CREATED = 1;//
	/** 队长位置 */
	public final static int TEAMLEADER_INDEX = 0;//
	public final static int MAX_COUNT = 7;
	private static IntId intId = new IntId();
	TeamManager teamManager;
	/** 状态 0创建状态 1创建成功 */
	private byte Statu;//
	private Integer teamId = intId.getNextId();
	private int teamLevel;
	/** 队伍总人数 */
	private int teamPopulation;//
	private String teamDeclare;
	private List<Hero> characterPlayes = new ArrayList<Hero>();
	/** 当前使用 */
	private TeamFightingController nowTfc; //
	private PropertyEntity pe = null;//

	public GameTeam(TeamManager teammanager) {
		this.teamManager = teammanager;
	}

	public String getTeamDeclare() {
		if (teamDeclare == null) {
			Hero c = getTeamLeader();
			return c.getViewName() + Language.TEAM;
		}
		return teamDeclare;
	}

	public TeamFightingController getNowTfc() {
		return nowTfc;
	}

	public void setNowTfc(TeamFightingController nowTfc) {
		this.nowTfc = nowTfc;
	}

	public void setTeamDeclare(String teamDeclare) {
		this.teamDeclare = teamDeclare;
	}

	public byte getStatu() {
		return Statu;
	}

	public void setStatu(byte statu) {
		Statu = statu;
	}

	public List<Hero> getCharacterPlayes() {
		return characterPlayes;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public int getTeamLevel() {
		if (this.teamLevel == 0) {
			checkteamstatus();
		}
		return this.teamLevel;
	}

	public void checkteamstatus() {
		int leve = 0;
		int count = 0;
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero character = characterPlayes.get(i);
			count++;
			leve = leve + character.getGrade();
		}
		if (count == 0) {
			return;
		}
		this.teamLevel = leve / count;
		this.teamPopulation = count;
	}

	public void setTeamLevel(int teamLevel) {
		this.teamLevel = teamLevel;
	}

	public int getTeamPopulation() {
		return teamPopulation;
	}

	public int getTeamLeaderIndex() {
		return TEAMLEADER_INDEX;
	}

	@Override
	public List<Hero> getCharacterPlayers() {
		return this.characterPlayes;
	}

	@Override
	public int addCharacter(Hero character) {
		if (characterPlayes.size() >= GameTeam.MAX_COUNT) {
			return 1001;
		}
		MyTeamManager myTeamManager = character.getMyTeamManager();
		myTeamManager.setMyTeam(this);
		myTeamManager.setTeamLeader(CommonUseNumber.byte0);
		if (characterPlayes.size() == 0) {
			myTeamManager.setTeamLeader(CommonUseNumber.byte1);
		}
		characterPlayes.add(character);
		checkteamstatus();
		// Instance instance = character.getCurrentInstance();
		// if (instance != null) {
		// instance.onTeamMemberAdd(character, this);
		// }
		// 通知聊天服务器队伍新成员
		// character.getVlineserver().getChatSessionManager().sendGameToChatServerMsg(new ChatToGsMsg608(
		// character.getId(), teamId));
		updateAllPlayersTeamFighting();
		character.getEffectController().getSpouseEffectManager().teamReload(this);// 添加风雨同舟buff
		this.getTeamLeader().getMyCharacterInstanceManager().updateEnterTeamInstance(12502, character.getViewName());
		return 1001;
	}

	/**
	 * 更新队友阵法加成
	 */
	public void updateAllPlayersTeamFighting() {
		if (nowTfc == null) {
			return;
		}
		if (nowTfc.checkOpenCondition(this) != null) {
			nowTfc.loseTeamFighting(this);
			return;
		}
		nowTfc.createTeamPropertyChangerListener(this);
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero teamer = characterPlayes.get(i);
			teamer.getMyZhenfaManager().updateTeamFighting();
		}
	}

	/**
	 * 根据角色id 从队伍中移出玩家角色（玩家退出队伍）返回离开队伍的玩家角色，返回null 标识该玩家没有在队伍中，或已经退出队伍
	 * 
	 * @param characterId
	 * @return
	 * @throws IOException
	 */
	public Hero removeCharacter(int characterId) {
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero character = characterPlayes.get(i);
			if (character.getId() == characterId) {
				Hero leaver = character;
				characterPlayes.remove(i);
				// 玩家在副本场景中被删除
				// EventHelper.onTeamMemberRemoved(leaver, this);
				clearTeamInfo(leaver);
				checkteamstatus();
				// 通知聊天服务器离队玩家
				// character.getVlineserver().getChatSessionManager().sendGameToChatServerMsg(new ChatToGsMsg610(
				// characterId));
				character.getEffectController().getSpouseEffectManager().teamReload(this);
				return leaver;
			}
		}
		return null;
	}

	private void clearTeamInfo(Hero character) {
		MyTeamManager myTeamManager = character.getMyTeamManager();
		myTeamManager.setTeamLeader(CommonUseNumber.byte0);
		myTeamManager.setMyTeam(null);
	}

	public Hero getTeamLeader() {
		return this.characterPlayes.get(GameTeam.TEAMLEADER_INDEX);
	}

	@Override
	public void sendTeamMsg(ServerResponse response, GamePlayer exclude) {
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero character = characterPlayes.get(i);
			if (character.getPlayer() != null && character.getPlayer() != exclude) {
				character.sendMsg(response);
			}
		}
	}

	@Override
	public Hero getCharacter(int id) {
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero character = characterPlayes.get(i);
			if (character.getId() == id) {
				return character;
			}
		}
		return null;
	}

	public Hero changeTeamLeader(int id) {
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero character = characterPlayes.get(i);
			if (character.getPlayer() != null) {
				if (character.getId() == id) {
					Hero newTeamleader = character;
					MyTeamManager myTeamManager = characterPlayes.get(GameTeam.TEAMLEADER_INDEX).getMyTeamManager();
					myTeamManager.setTeamLeader(CommonUseNumber.byte0);
					characterPlayes.remove(newTeamleader);
					characterPlayes.add(0, newTeamleader);
					MyTeamManager myLeaderTeamManager = newTeamleader.getMyTeamManager();
					myLeaderTeamManager.setTeamLeader(CommonUseNumber.byte1);
					return newTeamleader;
				}
			}
		}
		return null;
	}

	public Hero randomTeamLeader() {
		if (characterPlayes == null || characterPlayes.size() == 0) {
			// 解散队伍，队员全部离线
			takeOffTeam();
			return null;
		}
		Hero character = characterPlayes.get(GameTeam.TEAMLEADER_INDEX);
		Hero newTeamleader = character;
		MyTeamManager myLeaderTeamManager = newTeamleader.getMyTeamManager();
		myLeaderTeamManager.setTeamLeader(CommonUseNumber.byte1);
		return newTeamleader;
	}

	public void takeOffTeam() {
		teamManager.removeTeam(this.teamId);
	}

	/**
	 * 获取同地图其他玩家信息
	 * 
	 * @return
	 */
	public List<Hero> getOtherCharactersInSameScent(Hero character) {
		int id = character.getId();
		int scnenId = character.getScene();
		List<Hero> list = new ArrayList<Hero>();
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero teamer = characterPlayes.get(i);
			if (id != teamer.getId() && teamer.getScene() == scnenId) {
				list.add(teamer);
			}
		}
		return list;
	}

	@Override
	public int getTeamPopulationInScene(Scene scene) {
		int count = 0;
		for (int i = 0; i < characterPlayes.size(); i++) {
			Hero teamer = characterPlayes.get(i);
			if (teamer.getSceneRef() == scene) {
				count++;
			}
		}
		return count;
	}

	public PropertyEntity getPropertyEntity() {
		return pe;
	}

	public void setPropertyEntity(PropertyEntity pe) {
		this.pe = pe;
	}

	// @Override
	// public void change(VisibleObject vo) {
	// }
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.zhengfa;
	}

	@Override
	public TeamManager getTeamManager() {
		return teamManager;
	}

}
