package net.snake.gamemodel.team.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.script.IEventListener;
import net.snake.consts.CommonUseNumber;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.response.TakeOffMsg10220;
import net.snake.gamemodel.team.response.TeamApplyToLeaderMsg10190;
import net.snake.gamemodel.team.response.TeamBroadMsg10222;
import net.snake.gamemodel.team.response.TeamBroadMsg10224;
import net.snake.gamemodel.team.response.TeamBroadMsg10228;
import net.snake.gamemodel.team.response.TeamBroadMsg10230;
import net.snake.gamemodel.team.response.TeamBroadMsg10234;
import net.snake.gamemodel.team.response.TeamBroadMsg10236;
import net.snake.gamemodel.team.response.TeamBroadMsg10238;
import net.snake.gamemodel.team.response.TeamInfoMsg10208;
import net.snake.gamemodel.team.response.TeamLeaderChangeMsg10204;
import net.snake.gamemodel.team.response.TeamLeaveMsg10200;
import net.snake.gamemodel.team.response.TeamSendInviteMsg10184;
import net.snake.gamemodel.team.response.TeamTimerPositionMsg10244;
import net.snake.gamemodel.team.response.TeamdeclareMsg10196;


/**
 * 组队逻辑管理器
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class MyTeamManager {
	Hero character = null;
	private Team myTeam = null;
	private byte teamLeader = 0;// teamLeader==1 为队长
	private Map<Integer, Long> teamInviteMap = new HashMap<Integer, Long>();// 组队发起列表
	private byte accessTeam = 1; // 自动同意任何人的组队邀请
	private byte accessApplyTeam = 1;// 自动同意任何人加入队伍
	private byte teamUI = 1; // 本设置用于开启和关闭主界面中的“队伍成员信息追踪面板”：
	private long ptime = System.currentTimeMillis();// 上一次发送时间
	private TeamFightingController nowTfc;// 该玩家当前使用阵法

	public MyTeamManager(Hero character) {
		this.character = character;
	}
	
	public  int getAllObjInHeap() throws Exception {
		if (myTeam == null) return 0;
		return myTeam.getCharacterPlayers().size();
	}

	/**
	 * 判断其他角色是否与我同队
	 * 
	 * @param otherId
	 * @return
	 */
	public boolean isInSameTeamWith(int otherId) {
		if (!isTeam()) {
			return false;
		}
		Hero other = myTeam.getCharacter(otherId);
		if (other == null) {
			return false;
		}
		return true;
	}

	public void sendInitScentTeamMsg() {
		if (!isTeam()) {
			return;
		}
		// 队伍信息消息
		character.sendMsg(new TeamInfoMsg10208(character.getMyTeamManager()
				.getMyTeam()));
		myTeam.sendTeamMsg(new TeamBroadMsg10230(character.getId()),
				character.getPlayer());
	}

	/**
	 * 定时向客户端发送队友玩家位置
	 */
	public void timerSendTeamPlayerPosition() {
		if (!isTeam()) {
			return;
		}
		if (System.currentTimeMillis() - ptime < 7000) {
			return;
		}
		ptime = System.currentTimeMillis();
		List<Hero> list = myTeam.getOtherCharactersInSameScent(character);
		character.sendMsg(new TeamTimerPositionMsg10244(list));
	}

	/**
	 * 返回阵法名字
	 * 
	 * @return
	 */
	public int getZhenFaId() {
		if (nowTfc == null) {
			return 0;
		}
		return nowTfc.getTf().getId();
	}

	/**
	 * 返回是否是队长 true 是队长
	 * 
	 * @return
	 */
	public boolean isTeamLeader() {
		if (isTeam() && teamLeader == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否组队 true 组队
	 * 
	 * @return
	 */
	public boolean isTeam() {
		if (myTeam != null && myTeam.getStatu() == GameTeam.CREATED) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 发送队长转让消息给转让者 询问是否同意被任命为队长
	 * 
	 * @param somebody
	 * @throws IOException
	 */
	public void sendChangeLeaderMsg(int somebody) throws IOException {
		if (somebody == character.getId()) {
			// character.sendMsg(new TeamLeaderChangeMsg10202(""));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 765));
			return;
		}
		if (!isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 766));
			return;
		}
		Hero hisCharacter = myTeam.getCharacter(somebody);
		if (hisCharacter == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 767));
			return;
		}
		if (hisCharacter.getPlayer() == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 768));
			return;
		}
		hisCharacter.sendMsg(new TeamLeaderChangeMsg10204(character.getId(),
				character.getViewName()));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 769));

	}

	/**
	 * 被任命玩家同意被任命为队长操作
	 * 
	 * @param oldLeaderId
	 */
	public void changeNewLeader(int oldLeaderId, byte type) {
		if (!isTeam() && type == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 770));
			return;
		}
		Hero olderLeader = myTeam.getTeamLeader();
		if (type == 0) {
			olderLeader.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 771));
			return;
		}
		if (olderLeader.getId() != oldLeaderId) {
			// haracter.sendMsg(new TeamLeaderUpMsg10206("对不起，您的操作已经过期了"));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 772));
			return;
		}
		if (isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 773));
			return;
		}
		myTeam.getTeamLeader().getMyCharacterInstanceManager()
				.updateEnterTeamInstance(12503);
		myTeam.changeTeamLeader(character.getId());
		// 关闭阵法
		character.getMyZhenfaManager().closeTeamfighting();
		olderLeader.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 774));
		myTeam.sendTeamMsg(new TeamBroadMsg10224(character.getId()), null);

	}

	/**
	 * 队长提掉somebody
	 * 
	 * @param takeId
	 * @throws IOException
	 */
	public void kick(int takeId) throws IOException {
		if (takeId == character.getId()) {
			// 10218
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 775));
			return;
		}
		if (!isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 776));
			return;
		}
		Hero hisCharacter = myTeam.getCharacter(takeId);
		if (hisCharacter != null) {
			hisCharacter.getMyZhenfaManager().cancelUseTeamfighting();
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 777));
			return;
		}
		hisCharacter = myTeam.removeCharacter(takeId);
		if (hisCharacter == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 777));
			return;
		}
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_OutOfTeam, new Object[]{hisCharacter});
		hisCharacter.sendMsg(new TakeOffMsg10220());
		myTeam.sendTeamMsg(new TeamBroadMsg10222(hisCharacter.getId()), null);
		myTeam.updateAllPlayersTeamFighting();
	}

	/**
	 * 队员离开队伍
	 * 
	 * @return
	 */
	public boolean leaveTeam() {
		if (!isTeam()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 778));
			return false;
		}
		Team team = myTeam;
		if (isTeamLeader()) {
			character.getMyZhenfaManager().closeTeamfighting();
			team.removeCharacter(character.getId());
			Hero nleader = team.randomTeamLeader();
			if (nleader == null) {
				character.sendMsg(new TeamLeaveMsg10200());
				return true;
			}
			character.sendMsg(new TeamLeaveMsg10200());
			team.sendTeamMsg(new TeamBroadMsg10224(nleader.getId()), null);
			team.sendTeamMsg(new TeamBroadMsg10222(character.getId()), null);
			return true;
		}
		character.getMyZhenfaManager().cancelUseTeamfighting();
		team.removeCharacter(character.getId());
//		character.getEffectController().getSpouseEffectManager().teamReload(team);
		character.sendMsg(new TeamLeaveMsg10200());
		team.sendTeamMsg(new TeamBroadMsg10222(character.getId()), null);
		team.updateAllPlayersTeamFighting();
		team.getTeamLeader().getMyCharacterInstanceManager()
				.updateEnterTeamInstance(12504, character.getViewName());
		return true;
	}

	public String getTeamDeclare() {
		if (!isTeam()) {
			return "";
		}
		return myTeam.getTeamDeclare();
	}

	/**
	 * 队长修改宣言
	 * 
	 * @param str
	 */
	public void chageTeamDeclare(String str) {
		if (myTeam == null) {
			character.sendMsg(new TeamdeclareMsg10196(10003));
			return;
		}
		if (teamLeader != 1) {
			character.sendMsg(new TeamdeclareMsg10196(10003));
			return;
		}
		myTeam.setTeamDeclare(str);
		myTeam.sendTeamMsg(new TeamdeclareMsg10196(str), null);
	}

	/**
	 * 玩家下线组队处理
	 * 
	 * @throws IOException
	 */
	public void playerDownLineWhenTeam() throws IOException {
		if (!isTeam()) {
			return;
		}
		leaveTeam();
	}

	/**
	 * 
	 * @param character
	 */
	public void updateHpMsgToTeamer() {
		if (!isTeam()) {
			return;
		}
		myTeam.sendTeamMsg(new TeamBroadMsg10234(character),
				character.getPlayer());
	}

	/**
	 * 
	 * @param character
	 */
	public void updateMpMsgToTeamer() {
		if (!isTeam()) {
			return;
		}
		myTeam.sendTeamMsg(new TeamBroadMsg10236(character),
				character.getPlayer());
	}

	/**
	 * 自己升级更新
	 */
	public void updateGradeUpMsgToTeamer() {
		if (!isTeam()) {
			return;
		}
		myTeam.checkteamstatus();
		myTeam.sendTeamMsg(new TeamBroadMsg10238(character),
				character.getPlayer());
		return;
	}

	/**
	 * 接受邀请消息间隔 true表示可以接受
	 * 
	 * @return
	 */
	public boolean isInviteTeam(int inviteId) {
		long end = System.currentTimeMillis();
		Long intviteteamtim = teamInviteMap.get(inviteId);
		if (intviteteamtim == null) {
			teamInviteMap.put(inviteId, end);
			return true;
		}
		long time = end - intviteteamtim;
		if (time > 32000) {
			teamInviteMap.put(inviteId, end);
			return true;
		}
		return false;
	}

	public void removeIniviteTeamTime(int inviteId) {
		this.teamInviteMap.remove(inviteId);
	}

	public byte getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(byte teamLeader) {
		this.teamLeader = teamLeader;
	}

	/**
	 * 返回null标识没有组队状态，返回组队对象
	 * 
	 * @return
	 */
	public Team getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;
	}

	public void reset() {
		myTeam = null;
		teamLeader = 0;
	}

	public byte getAccessTeam() {
		return accessTeam;
	}

	public void setAccessTeam(byte accessTeam) {
		this.accessTeam = accessTeam;
	}

	public byte getAccessApplyTeam() {
		return accessApplyTeam;
	}

	public void setAccessApplyTeam(byte accessApplyTeam) {
		this.accessApplyTeam = accessApplyTeam;
	}

	public byte getTeamUI() {
		return teamUI;
	}

	public void setTeamUI(byte teamUI) {
		this.teamUI = teamUI;
	}

	public TeamFightingController getNowTfc() {
		return nowTfc;
	}

	public void setNowTfc(TeamFightingController nowTfc) {
		this.nowTfc = nowTfc;
	}

	public void inviteOrApplyTeam(Hero hisCharacter) {
		int id=hisCharacter.getId();
		if(id==character.getId()){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 779));
			return;
		}
		if (!isTeam() && !hisCharacter.getMyTeamManager().isTeam()) {
			createTeamInvite(hisCharacter);
			return;
		}
		if (hisCharacter.getMyTeamManager().isTeam() && !isTeam()) {
			applyTeam(hisCharacter.getMyTeamManager().getMyTeam());
			return;
		}
		inviteTeam(hisCharacter);
	}

	public void inviteTeam(Hero hisCharacter) {
		int hisId = hisCharacter.getId();
		// 10182 失败消息同意处理
		if (character.getId() == hisId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 779));
			return;
		}
		if (hisCharacter.getMyTeamManager().isTeam()) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1014,
					hisCharacter.getViewName() + ""));
			return;
		}
		if (!hisCharacter.getMyTeamManager().isInviteTeam(character.getId())) { // 防止多次发送
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1015,
					hisCharacter.getViewName() + ""));
			return;
		}
		if (!character.getMyTeamManager().isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 780));
			return;
		}
		if (myTeam.getTeamPopulation() >= 7) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 781));
			return;
		}
		if (hisCharacter.getMyTeamManager().getAccessTeam() == 1) {
			hisCharacter.getMyTeamManager().accessInviteTeam(character);
			return;
		}
		hisCharacter.sendMsg(new TeamSendInviteMsg10184(character.getId(),
				character.getViewName(), character.getGrade()));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 782));
	}

	private void createTeamInvite(Hero hisCharacter) {
		if (!hisCharacter.getMyTeamManager().isInviteTeam(character.getId())) { // 防止多次发送
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1015,
					hisCharacter.getViewName() + ""));
			return;
		}
		Team t = new GameTeam(character.getVlineserver().getTeamManager());
		setTeamLeader(CommonUseNumber.byte1);
		t.setStatu((byte) GameTeam.CREATING);
		setMyTeam(t);
		if (hisCharacter.getMyTeamManager().getAccessTeam() == 1) {
			hisCharacter.getMyTeamManager().accessInviteTeam(character);
			return;
		}
		hisCharacter.sendMsg(new TeamSendInviteMsg10184(character.getId(),
				character.getViewName(), character.getGrade()));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 782));
		return;
	}

	public void applyTeam(Team leaderTeam) {
		if (character.getMyTeamManager().isTeam()) {
			// character.sendMsg(new TeamApllyMsg10188("请求失败,你已经在队伍中"));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 783));
			return;
		}
		if (leaderTeam == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 784));
			return;
		}
		if (leaderTeam.getTeamPopulation() >= 7) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 785));
			return;
		}
		Hero leader = leaderTeam.getTeamLeader();
		if (leader == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 786));
			return;
		}
		if (!leader.getMyTeamManager().isInviteTeam(character.getId())) { // 防止多次发送
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1016,
					leader.getViewName() + ""));
			return;
		}
		if (leader.getMyTeamManager().getAccessApplyTeam() == 1) {
			leader.getMyTeamManager().accessApplyTeam(character);
			return;
		}
		leader.sendMsg(new TeamApplyToLeaderMsg10190(character));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 787));
	}

	/**
	 * 队长调用该方法 参数是申请加入者
	 * 
	 * @param teamer
	 */
	public void accessApplyTeam(Hero teamer) {
		if (!isTeamLeader()) {
			return;
		}
		myTeam.addCharacter(teamer);
		character.getMyFriendManager().getRoleLateLinkManager()
				.addOrUpdateLateLink(teamer.getId());
		teamer.getMyFriendManager().getRoleLateLinkManager()
				.addOrUpdateLateLink(character.getId());
		myTeam.sendTeamMsg(new TeamInfoMsg10208(myTeam), null);
		teamer.sendMsg(new TeamBroadMsg10228(10001));
		myTeam.sendTeamMsg(new TeamBroadMsg10228(10002, teamer.getViewName()),
				teamer.getPlayer());
		teamer.getMyCharacterAchieveCountManger().getTeamCount().teamCount();
		character.getMyTeamManager().removeIniviteTeamTime(teamer.getId());
	}

	
	public void accessInviteTeam(Hero teamLeader) {
		Team team = teamLeader.getMyTeamManager().getMyTeam();
		if (character.getMyTeamManager().isTeam()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 788));
			return;
		}
		if (team != null && team.getStatu() == GameTeam.CREATING) {
			team.setStatu(GameTeam.CREATED);
			team.addCharacter(teamLeader);
			teamLeader.getMyTeamManager().setTeamLeader(CommonUseNumber.byte1);
			team.addCharacter(character);
			TeamManager tm = character.getVlineserver().getTeamManager();
			tm.addTeam(team);
			character.getMyFriendManager().getRoleLateLinkManager()
					.addOrUpdateLateLink(teamLeader.getId());
			teamLeader.getMyFriendManager().getRoleLateLinkManager()
					.addOrUpdateLateLink(character.getId());
			team.sendTeamMsg(new TeamInfoMsg10208(team), null);
			character.sendMsg(new TeamBroadMsg10228(10001));
			myTeam.sendTeamMsg(
					new TeamBroadMsg10228(10002, character.getViewName()),
					character.getPlayer());
			teamLeader.getMyCharacterAchieveCountManger().getTeamCount()
					.teamCount();
			character.getMyTeamManager().removeIniviteTeamTime(
					teamLeader.getId());
			return;
		}
		if (teamLeader.getMyTeamManager().isTeamLeader()) {
			if (team.getTeamPopulation() >= 7) {
				// character.sendMsg(new
				// TeamInviteAcessorMsg10186("对不起，该队伍已经满员"));
				character
						.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 785));
				return;
			}
			team.addCharacter(character);
			character.getMyFriendManager().getRoleLateLinkManager()
					.addOrUpdateLateLink(teamLeader.getId());
			teamLeader.getMyFriendManager().getRoleLateLinkManager()
					.addOrUpdateLateLink(character.getId());
			character.sendMsg(new TeamBroadMsg10228(10001));
			team.sendTeamMsg(new TeamInfoMsg10208(myTeam), null);
			myTeam.sendTeamMsg(
					new TeamBroadMsg10228(10002, character.getViewName()),
					character.getPlayer());
			character.getMyCharacterAchieveCountManger().getTeamCount()
					.teamCount();
			character.getMyTeamManager().removeIniviteTeamTime(
					teamLeader.getId());
			return;
		}
		// character.sendMsg(new TeamInviteAcessorMsg10186("对不起，您的操作超时了"));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 789));
	}

	public void destroy() {
		if (myTeam!= null) {
			myTeam.removeCharacter(character.getId());
			myTeam=null;
		}
		if(teamInviteMap!=null){
			teamInviteMap.clear();
			teamInviteMap=null;	
		}
		
	}
}
