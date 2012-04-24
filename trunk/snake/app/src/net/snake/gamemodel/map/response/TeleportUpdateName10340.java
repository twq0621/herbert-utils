package net.snake.gamemodel.map.response;


import net.snake.netio.ServerResponse;


/**
 * 传送点改名
 * 
 * @@author serv_dev.
 * @version: 1.0
 * @Create at: 2011-4-16 上午09:51:13
 */
public class TeleportUpdateName10340 extends ServerResponse{
	public TeleportUpdateName10340(int scenId, int teleportId, String name) {
		setMsgCode(10340);
		try {
			writeInt(scenId);
			writeInt(teleportId);
			writeUTF(name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
