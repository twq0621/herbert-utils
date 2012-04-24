package net.snake.gamemodel.chat.logic;

import java.util.Collection;

import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.chat.response.ChatSceneMsg30112;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 普通频道聊天管理
 * 
 * @author serv_dev
 * 
 */
public class RoleSceneManager {
	public static Byte CHANNEL = 4;
	public static int speakTimeMin = 5; // 发言间隔
	private long lastspeakTime = 0;
	private Hero role;
	private ChatManager manager;

	public RoleSceneManager(ChatManager manager) {
		this.manager = manager;
		this.role = manager.getCharacter();
	}

	private int timeOutCount;

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void sendScenemMsg(String content) {
		if (role.getSceneRef() == null) {// 是不是在场景中
			role.sendMsg(new ChatFailMsg30120(CHANNEL, 13016));
			return;
		}

		if (!manager.isSpeak()) {// 被禁言的时间只能说给自己听
			content = BadWordsFilter.getInstance().badWordstFilterStr(content);
			ServerResponse msg = new ChatSceneMsg30112(role, content, false);
			manager.sendMsg(msg);
			return;
		}
		// 如果您说话距离上次时间过短
		if (timeOutCount < 3 && System.currentTimeMillis() - lastspeakTime < speakTimeMin * 1000) {
			timeOutCount++;
			if (timeOutCount == 3) {// 并且不止一次
				lastspeakTime = System.currentTimeMillis();
				manager.setSpeakTime(System.currentTimeMillis() + 60 * 1000);// 60秒后才能说给别人听
				manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13012));
				return;
			}
			manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13001));// 这次还是别说了
			return;
		}

		// 内容过滤
		content = BadWordsFilter.getInstance().badWordstFilterStr(content);
		timeOutCount = 0;// 从禁言中恢复
		lastspeakTime = System.currentTimeMillis();
		boolean isGm = false;
		if (role.getAccount().getGm() == 1) {
			isGm = true;
		}
		sendScenemMsg(new ChatSceneMsg30112(role, content, isGm));
	}

	private void sendScenemMsg(ServerResponse msg) {
		Collection<Hero> allCharacters = this.role.getSceneRef().getAllCharacters();
		for (Hero role : allCharacters) {
			this.role.getChatManager().sendMsgToOther(role, msg);
		}
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	public long getLastspeakTime() {
		return lastspeakTime;
	}

	public void setLastspeakTime(long lastspeakTime) {
		this.lastspeakTime = lastspeakTime;
	}

	public Hero getChatRole() {
		return role;
	}
}
