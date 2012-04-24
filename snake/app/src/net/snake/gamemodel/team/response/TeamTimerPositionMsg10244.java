package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 定时发送同地图队友玩家位置
 *
 */
public class TeamTimerPositionMsg10244 extends ServerResponse {

	public TeamTimerPositionMsg10244(List<Hero> list) {
		this.setMsgCode(10244);
		try {
			this.writeByte(list.size());
			for(Hero c:list){
				this.writeInt(c.getId());
				this.writeShort(c.getX());
				this.writeShort(c.getY());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
