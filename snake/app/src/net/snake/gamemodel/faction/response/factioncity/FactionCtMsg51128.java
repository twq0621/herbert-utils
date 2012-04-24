package net.snake.gamemodel.faction.response.factioncity;

import java.util.Calendar;

import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.netio.ServerResponse;

public class FactionCtMsg51128 extends ServerResponse {
	public FactionCtMsg51128(MyFactionCityZhengDuoManager myFactionCityZhengDuoManager) {
		this.setMsgCode(51128);
		try {
			SceneFactionCtMonster monster = MyFactionCityZhengDuoManager.monster;
			if (monster == null) {
				SceneFactionCtMonster youlong = MyFactionCityZhengDuoManager.youlongWeiBaMonster;
				this.writeByte(0);
				short[] point = GongchengTsMap.monsterPoint;
				this.writeShort(point[0]);
				this.writeShort(point[1]);
				long times = youlong.getWeiBuchuTime();
				int h = (int) (times / 60000);
				this.writeByte(h);
			} else {
				this.writeByte(1);
				Hero character = monster.getYoulongCharacter();
				this.writeShort(character.getX());
				this.writeShort(character.getY());
				this.writeUTF(character.getViewName());

				int time = (int) ((System.currentTimeMillis() - monster.getStartCatchYoulongTime()) / 60000);
				this.writeByte(time);
				this.writeUTF(character.getMyFactionManager().getFactionName());

			}
			int t = myFactionCityZhengDuoManager.getonlineTime();
			this.writeByte(t);
			long gongchengEnd = getGongchengEndTime();
			long gongcheng = gongchengEnd - System.currentTimeMillis();
			int g = (int) (gongcheng / 60000);
			this.writeByte(g);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取今日
	 * 
	 * @return
	 */
	private long getGongchengEndTime() {
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(System.currentTimeMillis());
		t.set(Calendar.HOUR_OF_DAY, FactionCityManager.endHorse);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
		return t.getTimeInMillis();
	}
}
