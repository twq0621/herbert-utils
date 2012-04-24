package net.snake.gamemodel.chat.logic;

import net.snake.GameServer;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.chat.response.ChatPrivateMsg30106;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

/**
 * 私聊管理器
 * 
 * 
 */
public class PrivateChatManager {
	public static Byte CHANNEL = 1;
	/** 发言间隔 */
	public static int speakTimeMin = 3; //
	private long lastspeakTime;
	private ChatManager manage;
	private int timeOutCount = 0;

	/**
	 * 发送私聊消息
	 * 
	 * @param name
	 * @param content
	 */
	public void sendPrivateMsgToOther(String name, String content) {
		Hero target = null;
		if (Options.IsCrossServ) {
			target = manage.getCharacter().getVlineserver().getOnlineManager().getByName(name);
		} else {
			target = GameServer.vlineServerManager.getCharacterByName(name);
		}
		if (target == null) {
			manage.sendMsg(new ChatFailMsg30120(CHANNEL, 13010));
			return;
		}
		if (!manage.isSpeak()) {
			content = BadWordsFilter.getInstance().badWordstFilterStr(content);
			ServerResponse msg = new ChatPrivateMsg30106(manage.getCharacter(), content, target, false);
			manage.sendMsg(msg);
			return;
		}

		ChatManager chatManager = target.getChatManager();
		if (!chatManager.getIsAccessPriMsg()) {
			manage.sendMsg(new ChatFailMsg30120(CHANNEL, 13011));
			return;
		}
		if (timeOutCount < 3 && System.currentTimeMillis() - lastspeakTime < speakTimeMin * 1000) {
			timeOutCount++;
			if (timeOutCount == 3) {
				lastspeakTime = System.currentTimeMillis();
				manage.setSpeakTime(System.currentTimeMillis() + 60 * 1000);
				manage.sendMsg(new ChatFailMsg30120(CHANNEL, 13012));
				return;
			}
			manage.sendMsg(new ChatFailMsg30120(CHANNEL, 13001));
			return;
		}
		// 内容过滤
		content = BadWordsFilter.getInstance().badWordstFilterStr(content);
		timeOutCount = 0;
		lastspeakTime = System.currentTimeMillis();
		boolean isGm = false;
		if (manage.getCharacter().getAccount().getGm() == 1) {
			isGm = true;
		}
		ServerResponse msg = new ChatPrivateMsg30106(manage.getCharacter(), content, target, isGm);
		sendPrivateMsgToOther(target, msg);
	}

	/**
	 * 发送私聊消息
	 * 
	 * @param target
	 * @param msg
	 */
	private void sendPrivateMsgToOther(Hero target, ServerResponse msg) {
		if (target == null) {
			return;
		}
		this.manage.sendMsgToOther(target, msg);
		manage.sendMsg(msg);
		this.manage.getCharacter().getMyFriendManager().getRoleLateLinkManager().addOrUpdateLateLink(target.getId());
		target.getMyFriendManager().getRoleLateLinkManager().addOrUpdateLateLink(this.manage.getCharacter().getId());
	}

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	public PrivateChatManager(ChatManager manage) {
		this.manage = manage;
	}

	public long getLastspeakTime() {
		return lastspeakTime;
	}

	public void setLastspeakTime(long lastspeakTime) {
		this.lastspeakTime = lastspeakTime;
	}

}
