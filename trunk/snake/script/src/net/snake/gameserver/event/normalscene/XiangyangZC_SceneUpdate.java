/**
 * 
 */
package net.snake.gameserver.event.normalscene;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import java.util.Date;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SScene;
import net.snake.script.util.DateUtile;

/**
 * 襄阳战场金兵入侵
 */

public class XiangyangZC_SceneUpdate implements IEventListener {
	public static int xiangyangZhanchangId = 20010;
	public static int stateHours = 20;
	public static int lifetime = 598 * 1000;
	public static int tieshiCount = 0;
	public static int monsterMaxCount = 300;

	// /*
	// *
	// * @see
	// * net.snake.script.events.EvtSceneLoop#onEvtSceneLoop(net.snake.script
	// * .api.SApi, net.snake.script.api.SScene)
	// */
	// @Override
	// public void onEvtSceneLoop(SApi api, SScene scene) {
	// if (xiangyangZhanchangId != scene.getId()) {
	// return;
	// }
	// Calendar calendar = DateUtile.dateToCalendar(new Date());
	// if (!isRuqinTime(calendar)) {
	// return;
	// }
	// int time = getZouguoMinite(calendar);
	// if(time>=13*10){
	// if(tieshiCount==1){
	// tieshiCount=0;
	// api.sendMsgToAll((byte) 4, 18002);
	// }
	// scene.setAttribute("jinbing", 0);
	// return;
	// }
	// if(scene.getLineId() == 1){
	// if (tieshiCount == 0) {
	// tieshiCount++;
	// api.sendMsgToAll((byte) 4, 18000, 3000 + "", 10 + "");
	// }
	// }
	// Integer boshu = (Integer) scene.getAttribute("jinbing");
	//
	// if (boshu == null) {
	// boshu = 0;
	// }
	// if(boshu>=12){
	// return;
	// }
	// if (time >= (boshu + 1) * 10) {
	// boshu=time/10;
	// fushMonster(boshu, api, scene);
	// scene.setAttribute("jinbing", boshu);
	// }
	//
	// }

	private void fushMonster(int boshu, SApi api, SScene scene) {
		int mousterId = 14000 + boshu;
		String name = api.getMonsterNameById(mousterId);
		if (scene.getLineId() == 1) {
			api.sendMsgToAll((byte) 4, 18001, boshu + "", name);
		}
		Collection<SMonster> collection = scene.getSMonsterCollection();
		if (collection == null) {
			return;
		}
		List<SMonster> list = new ArrayList<SMonster>();
		list.addAll(collection);
		int count = 0;
		for (int h = 0; h < 3; h++) {
			for (int i = 0; i < list.size(); i++) {
				SMonster monster = list.get(i);
				count++;
				if (count >= monsterMaxCount) {
					return;
				}
				api.createMonsterAroundPoint(scene, monster.getOriginalX(), monster.getOriginalY(), 8, mousterId, false, lifetime);
			}
		}
	}

	private boolean isRuqinTime(Calendar calendar) {
		boolean tf = true;
		if (tf) {
			return false;
		}
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == Calendar.MONDAY || week == Calendar.TUESDAY || week == Calendar.THURSDAY || week == Calendar.FRIDAY || week == Calendar.SUNDAY) {
			return false;
		}// 每周3和6

		int hourse = calendar.get(Calendar.HOUR_OF_DAY);
		if (hourse < 20) {
			return false;
		} else if (hourse > 23) {
			return false;
		} else if (hourse == 22) {
			int m = calendar.get(Calendar.MINUTE);
			if (m > 13) {
				return false;
			} else {
				return true;
			}
		}
		// 20：00--22：12
		return true;
	}

	public int getZouguoMinite(Calendar calendar) {
		int hourse = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		m = m + (hourse - 20) * 60;
		return m;
	}

	// 18001
	// 金军向襄阳战场增兵3000员，襄阳城防告急，请各位江湖侠义之士伸出援助之手（极其强烈推荐组队前往）。第一波增兵将在10分钟后抵达襄阳战场。“

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_SceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SScene scene = (SScene) args[0];

		if (xiangyangZhanchangId != scene.getId()) {
			return;
		}
		Calendar calendar = DateUtile.dateToCalendar(new Date());
		if (!isRuqinTime(calendar)) {
			return;
		}

		int time = getZouguoMinite(calendar);
		if (time >= 13 * 10) {
			if (tieshiCount == 1) {
				tieshiCount = 0;
				api.sendMsgToAll((byte) 4, 18002);
			}
			scene.setAttribute("jinbing", 0);
			return;
		}
		if (scene.getLineId() == 1) {
			if (tieshiCount == 0) {
				tieshiCount++;
				api.sendMsgToAll((byte) 4, 18000, 3000 + "", 10 + "");
			}
		}
		Integer boshu = (Integer) scene.getAttribute("jinbing");

		if (boshu == null) {
			boshu = 0;
		}
		if (boshu >= 12) {
			return;
		}
		if (time >= (boshu + 1) * 10) {
			boshu = time / 10;
			scene.setAttribute("jinbing", boshu);
			fushMonster(boshu, api, scene);

		}
	}
}
