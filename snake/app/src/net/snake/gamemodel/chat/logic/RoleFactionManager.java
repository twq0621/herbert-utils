package net.snake.gamemodel.chat.logic;

import java.util.Collection;

import net.snake.GameServer;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.chat.response.ChatFactionMsg30108;
import net.snake.gamemodel.chat.response.ChatFailMsg30120;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

/**
 * 帮会聊天管理器
 * 
 */
public class RoleFactionManager {
	public static Byte CHANNEL = 2;
	public static int speakTimeMin = 3; // 发言间隔
	// private ChatFaction chatFaction;
	// private Faction faction;
	private long lastspeakTime;
	private Hero chatRole;
	private ChatManager manager;

	public RoleFactionManager(ChatManager manager) {
		this.manager = manager;
		this.chatRole = manager.getCharacter();
	}

	private int timeOutCount;

	public int getTimeOutCount() {
		return timeOutCount;
	}

	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}

	/**
	 * 帮会发送消息
	 * 
	 * @param content
	 */
	public void sendFactionMsg(String content) {
		if (!chatRole.getMyFactionManager().isFaction() && !Options.IsCrossServ) {
			manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13008));
			return;
		}
		// 1私聊/2帮会/3组队/4普通/5世界
		if (!manager.isSpeak()) {
			content = BadWordsFilter.getInstance().badWordstFilterStr(content);
			ServerResponse msg = new ChatFactionMsg30108(chatRole, content, false);
			manager.sendMsg(msg);
			return;
		}
		if (timeOutCount < 3 && System.currentTimeMillis() - lastspeakTime < speakTimeMin * 1000) {
			timeOutCount++;
			if (timeOutCount >= 3) {
				lastspeakTime = System.currentTimeMillis();
				manager.setSpeakTime(System.currentTimeMillis() + 60 * 1000);
				manager.sendMsg(new ChatFailMsg30120(CHANNEL, 13009));
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
		if (manager.getCharacter().getAccount().getGm() == 1) {
			isGm = true;
		}
		sendFactionMsg(new ChatFactionMsg30108(chatRole, content, isGm));
	}

	/**
	 * 发送帮会消息
	 * 
	 * @param msg
	 */
	private void sendFactionMsg(ServerResponse msg) {
		if (Options.IsCrossServ) {
			Collection<Hero> acRoles = chatRole.getMyFactionManager().getAcrossFactionController().getAllFactionCharacter();
			for (Hero c : acRoles) {
				chatRole.getChatManager().sendMsgToOther(c, msg);
			}
			return;
		}
		Collection<FactionCharacter> allFactionCharacter = chatRole.getMyFactionManager().getFactionController().getAllFactionCharacter();
		if (allFactionCharacter == null || allFactionCharacter.size() <= 0) {
			return;
		}
		for (FactionCharacter factionCharacter : allFactionCharacter) {
			// 发送帮会消息
			Integer characterId = factionCharacter.getCharacterId();
			Hero role = GameServer.vlineServerManager.getCharacterById(characterId);
			// 只给在线玩家发
			if (role != null) {
				chatRole.getChatManager().sendMsgToOther(role, msg);
				// role.getChatManager().sendMsgToOther(target,
				// msg).sendMsg(msg);
				// manager.sendMsg(msg);
			}
		}
	}

	public long getLastspeakTime() {
		return lastspeakTime;
	}

	public void setLastspeakTime(long lastspeakTime) {
		this.lastspeakTime = lastspeakTime;
	}

}
