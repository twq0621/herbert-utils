package net.snake.serverenv;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.player.GamePlayer;

public class CharacterGCManagerImp implements Runnable, CharacterGCManager {
	private static Logger logger = org.apache.log4j.Logger.getLogger(CharacterGCManagerImp.class);

	private ConcurrentLinkedQueue<GCInfo> latertasklist = new ConcurrentLinkedQueue<GCInfo>();

	private static class GCInfo {
		public Hero character;
		public long starttime;
	}

	@Override
	public void start() {
		GameServer.executorService.scheduleWithFixedDelay(this, 10, 10, TimeUnit.SECONDS);
	}

	@Override
	public void addCharacterToGC(Hero character) {
		GCInfo gcinfo = new GCInfo();
		gcinfo.character = character;
		gcinfo.starttime = System.currentTimeMillis();
		latertasklist.add(gcinfo);
	}

	public CharacterGCManagerImp() {

	}

	@Override
	public void run() {
		for (GCInfo gcinfo = latertasklist.peek(); gcinfo != null; gcinfo = latertasklist.peek()) {
			if (System.currentTimeMillis() - gcinfo.starttime > 30 * 1000) {
				try {
					GamePlayer gp = gcinfo.character.getPlayer();
					if (gp != null && gp.getIoSession() != null && gp.getIoSession().isConnected()) {
						// 怎么还有会话连接，那就不destory了
					} else {
						if (logger.isDebugEnabled()) {
							logger.debug("GC character " + gcinfo.character.getViewName());
						}
						gcinfo.character.destory();
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				latertasklist.poll();
			} else {
				// 第一个都不满足,后面的一定都不满足
				break;
			}
		}

	}
}
