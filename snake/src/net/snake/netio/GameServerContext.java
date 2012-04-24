package net.snake.netio;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.netio.player.GamePlayer;

public class GameServerContext {
	private static final Map<Integer, GamePlayer> ALLPLAYER_ONLINE = new ConcurrentHashMap<Integer, GamePlayer>();// <帐号id 玩家>存储所有在线的玩家

	/**
	 * 获取登陆服务器保存的在线玩家
	 * 
	 * @param accountId
	 * @return
	 */
	public static GamePlayer getPlayer(Integer accountId) {
		return ALLPLAYER_ONLINE.get(accountId);
	}

	/**
	 * 只在 玩家第一次进入场景的时候添加。<br>
	 * 将帐号id和应用会话映射,覆盖之前的此帐号连接。
	 * 
	 * @param playerId
	 * @param gamePlayer
	 */
	public static void putPlayer(Integer accountId, GamePlayer gamePlayer) {
		ALLPLAYER_ONLINE.put(accountId, gamePlayer);
	}

	public static Collection<GamePlayer> getAllGamePlayer() {
		return ALLPLAYER_ONLINE.values();
	}

	/**
	 * 玩家离开游戏时移除。 <br>
	 * 有时关闭连接的同时并不意味着离开游戏。
	 * 
	 * @param accountId
	 */
	public static void removePlayer(Integer accountId) {
		ALLPLAYER_ONLINE.remove(accountId);
	}

	public static Collection<GamePlayer> getAllPlayer() {
		return ALLPLAYER_ONLINE.values();
	}
}
