package net.snake.gamemodel.chat.logic;

import java.util.Collection;

import net.snake.GameServer;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatAllMsg30114;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 世界频道管理
 * 
 */
public class RoleAllChatManager {
	public static Byte CHANNEL = 5;
	public static int speakTimeMin = 7; // 发言间隔
	private long lastspeakTime;
	private Hero chatRole;
	private int timeOutCount;
	private String msg;
	private ChatManager manager;

	public RoleAllChatManager(ChatManager manager) {
		this.manager = manager;
		this.chatRole = manager.getCharacter();
	}

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	/**
	 * 发送世界聊天消息
	 * 
	 * @param msg
	 */
	public void sendWorldMsg(String content) {
		if (chatRole.getGrade() < 20) {
			chatRole.sendMsg(new ChatFailMsg30120(CHANNEL, 13014));
			return;
		}
		if (!manager.isSpeak()) {
			content = BadWordsFilter.getInstance().badWordstFilterStr(content);
			ServerResponse msg = new ChatAllMsg30114(chatRole, content, false);
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
		if (msg != null && msg.equals(content)) {
			manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13015));
			return;
		}
		msg = content;
		// 内容过滤
		content = BadWordsFilter.getInstance().badWordstFilterStr(content);
		timeOutCount = 0;
		lastspeakTime = System.currentTimeMillis();
		boolean isGm = false;
		if (manager.getCharacter().getAccount().getGm() == 1) {
			isGm = true;
		}
		sendWorldMsgExceptBlackFriend(new ChatAllMsg30114(chatRole, content, isGm));
	}

	private void sendWorldMsgExceptBlackFriend(ServerResponse msg) {
		if (Options.IsCrossServ) {
			VLineServer vLineServer = manager.getCharacter().getVlineserver();
			if (vLineServer != null) {
				Collection<Hero> characterList = vLineServer.getOnlineManager().getCharacterList();
				for (Hero role : characterList) {
					manager.sendMsgToOther(role, msg);
				}
			}
			return;
		}
		// 发送世界聊天消息
		Collection<VLineServer> lineServersList = GameServer.vlineServerManager.getLineServersList();
		for (VLineServer vLineServer : lineServersList) {
			Collection<Hero> characterList = vLineServer.getOnlineManager().getCharacterList();
			for (Hero role : characterList) {
				manager.sendMsgToOther(role, msg);
			}
		}
	}

	public static int getSpeakTimeMin() {
		return speakTimeMin;
	}

	public static void setSpeakTimeMin(int speakTimeMin) {
		RoleAllChatManager.speakTimeMin = speakTimeMin;
	}

	public long getLastspeakTime() {
		return lastspeakTime;
	}

	public void setLastspeakTime(long lastspeakTime) {
		this.lastspeakTime = lastspeakTime;
	}

}
