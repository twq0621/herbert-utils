package net.snake.gamemodel.chat.logic;

import java.util.List;

import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.chat.response.ChatTeamMsg30110;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;

/**
 * 队伍管理器
 * 
 * @author serv_dev
 * 
 */
public class RoleTeamManager {
	public static Byte CHANNEL = 3;
	public static int speakTimeMin = 3; // 发言间隔
	// private ChatTeam team;
	private long lastspeakTime;
	private ChatManager manager;
	private int timeOutCount;
	private Hero role;

	public RoleTeamManager(ChatManager manager) {
		this.manager = manager;
		role = manager.getCharacter();
	}

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	public void sendTeamMsg(String content) {
		Team team = role.getMyTeamManager().getMyTeam();
		if (team == null) {
			manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13017));
			return;
		}

		if (!manager.isSpeak()) {
			content = BadWordsFilter.getInstance().badWordstFilterStr(content);
			ServerResponse msg = new ChatTeamMsg30110(role, content, false);
			manager.sendMsg(msg);
			return;
		}
		if (timeOutCount < 3 && System.currentTimeMillis() - lastspeakTime < speakTimeMin * 1000) {
			timeOutCount++;
			if (timeOutCount == 3) {
				lastspeakTime = System.currentTimeMillis();
				manager.setSpeakTime(System.currentTimeMillis() + 60 * 1000);
				manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13012));
				return;
			}
			manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13001));
			return;
		}
		// 内容过滤
		content = BadWordsFilter.getInstance().badWordstFilterStr(content);
		timeOutCount = 0;
		lastspeakTime = System.currentTimeMillis();
		boolean isGm = false;
		if (role.getAccount().getGm() == 1) {
			isGm = true;
		}
		sendTeamMsg(new ChatTeamMsg30110(role, content, isGm), team);
	}

	/**
	 * 发送消息给小组成员
	 * 
	 * @param msg
	 */
	public void sendTeamMsg(ServerResponse msg, Team team) {
		List<Hero> characterPlayers = team.getCharacterPlayers();
		for (Hero role : characterPlayers) {
			this.role.getChatManager().sendMsgToOther(role, msg);
			// 发送组队消息
		}
	}

	public long getLastspeakTime() {
		return lastspeakTime;
	}

	public void setLastspeakTime(long lastspeakTime) {
		this.lastspeakTime = lastspeakTime;
	}

	public static int getSpeakTimeMin() {
		return speakTimeMin;
	}

	public static void setSpeakTimeMin(int speakTimeMin) {
		RoleTeamManager.speakTimeMin = speakTimeMin;
	}

}
