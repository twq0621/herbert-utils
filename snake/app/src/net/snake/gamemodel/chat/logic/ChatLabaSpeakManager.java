package net.snake.gamemodel.chat.logic;

import java.util.Collection;
import java.util.Date;

import net.snake.GameServer;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.chat.response.ChatLabaMsg30116;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.serverenv.vline.VLineServer;

public class ChatLabaSpeakManager {
	private Hero character;
	public static Byte CHANNEL = 6;
	public static int speakTimeMin = 10; // 发言间隔
	private long speakTime = 0;

	// private long lastspeakTime;
	// private int timeOutCount;

	public ChatLabaSpeakManager(ChatManager manager) {
		this.character = manager.getCharacter();
	}

	public void destory() {

	}

	public void init() {
		Date d = character.getAllowchatStarttime();
		if (d == null) {
			this.speakTime = 0;
		} else {
			this.speakTime = d.getTime();
		}
	}

	public boolean isSpeak(byte channel) {
		long time = speakTime - System.currentTimeMillis();
		if (time > 0) {
			int times = (int) (time / 1000);
			if (times < 60) {
				character.sendMsg(new ChatFailMsg30120(channel, 13002, times + ""));
			} else if (times < 6000) {
				character.sendMsg(new ChatFailMsg30120(channel, 13003, (times / 60) + ""));
			} else {
				character.sendMsg(new ChatFailMsg30120(channel, 13004, (times / 3600) + ""));
			}
			return false;
		}
		return true;
	}

	/**
	 * 发送喇叭消息
	 * 
	 * @param msg
	 */
	public void sendLabaMsg(String content) {
		// if (!isSpeak(CHANNEL)) {
		// return;
		// }
		// if (timeOutCount < 3 && System.currentTimeMillis() - lastspeakTime < speakTimeMin * 1000) {
		// timeOutCount++;
		// if (timeOutCount == 3) {
		// lastspeakTime = System.currentTimeMillis();
		// this.speakTime = System.currentTimeMillis() + 60 * 1000;
		// this.timeOutCount = 0;
		// character.sendMsg(new ChatFailMsg30120(CHANNEL, 13005));
		// return;
		// }
		// character.sendMsg(new ChatFailMsg30120(CHANNEL, 13006));
		// return;
		// }
		if (!character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.LABA_ID, 1)) {
			character.sendMsg(new ChatFailMsg30120(CHANNEL, 13007));
			return;
		}

		// timeOutCount = 0;
		// lastspeakTime = System.currentTimeMillis();
		// 内容过滤
		content = BadWordsFilter.getInstance().badWordstFilterStr(content);
		sendLabaMsgToAllPlayers(content);
		// character
		// .getVlineserver()
		// .getChatSessionManager()
		// .sendGameToChatServerMsg(
		// new ChatToGsMsg622(character.getId(), content));
	}

	/**
	 * 使用喇叭发送聊天消息
	 * 
	 * @param content
	 */
	private void sendLabaMsgToAllPlayers(String content) {
		boolean isGm = false;
		if (character.getAccount().getGm() == 1) {
			isGm = true;
		}
		ChatLabaMsg30116 msg = new ChatLabaMsg30116(character, content, isGm);
		Collection<VLineServer> lineServersList = GameServer.vlineServerManager.getLineServersList();
		for (VLineServer vLineServer : lineServersList) {
			Collection<Hero> characterList = vLineServer.getOnlineManager().getCharacterList();
			for (Hero role : characterList) {
				role.getChatManager().sendMsg(msg);
			}
		}
	}

}
