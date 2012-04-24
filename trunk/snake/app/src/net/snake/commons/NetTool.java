package net.snake.commons;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.ResponseMsg;
import net.snake.netio.player.GamePlayer;



public class NetTool {

	private static Logger logger = Logger.getLogger(NetTool.class);

	/**
	 * 发送消息，并在其后关闭连接。
	 * 
	 * @param gameplayer
	 * @param responsemsg
	 * @param aftertime
	 */
	public static void sendAndClose(final GamePlayer gameplayer, ResponseMsg responsemsg, long aftertime) {
		gameplayer.sendMsg(responsemsg);
		GameServer.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					gameplayer.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}, aftertime, TimeUnit.MILLISECONDS);
	}

	/**
	 * 向gameplayer发消息
	 * 
	 * @param gameplayer
	 * @param msg
	 */
	public static void send(GamePlayer gameplayer, ResponseMsg msg) {
		if (gameplayer == null) {
			return;
		}
		gameplayer.sendMsg(msg);

	}

	/**
	 * 向gameplayer发一系列消息
	 */
	public static void send(GamePlayer gameplayer, List<ResponseMsg> messagelist) {
		if (messagelist != null) {
			for (ResponseMsg message : messagelist) {
				send(gameplayer, message);
			}
		}
	}

	/**
	 * 向一组玩家广播消息
	 * 
	 * @param gameplayers
	 * @param message
	 */
	public static void sendToGamePlayers(Collection<GamePlayer> gameplayers, ResponseMsg message) {
		for (GamePlayer userSession : gameplayers) {
			send(userSession, message);
		}
	}

	/**
	 * 向一组玩家广播消息 但排除
	 * 
	 * @param gameplayers
	 * @param excludeGameplayer
	 * @param message
	 */
	public static void sendToGamePlayers(Collection<GamePlayer> gameplayers, GamePlayer excludeGameplayer, ResponseMsg message) {
		for (GamePlayer userSession : gameplayers) {
			if (userSession == excludeGameplayer) {
				continue;
			}
			send(userSession, message);
		}
	}

	public static void send(Hero gameplayer, ResponseMsg msg) {
		send(gameplayer.getPlayer(), msg);
	}

	public static void send(Hero gameplayer, List<ResponseMsg> messagelist) {
		if (messagelist != null) {
			for (ResponseMsg message : messagelist) {
				send(gameplayer, message);
			}
		}
	}

	/**
	 * 向一组玩家广播消息
	 * 
	 * @param characters
	 * @param message
	 */
	public static void sendToCharacters(Collection<Hero> characters, ResponseMsg message) {
		for (Hero userSession : characters) {
			send(userSession, message);
		}
	}

	/**
	 * 向一组玩家广播消息 但排除
	 * 
	 * @param sessions
	 * @param excludeCharacter
	 * @param message
	 */
	public static void sendToCharacters(Collection<Hero> characters, Hero excludeCharacter, ResponseMsg message) {
		for (Hero userSession : characters) {
			if (userSession == excludeCharacter) {
				continue;
			}
			send(userSession, message);
		}
	}

}
