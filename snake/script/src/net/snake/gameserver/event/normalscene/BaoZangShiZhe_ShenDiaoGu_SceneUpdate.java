package net.snake.gameserver.event.normalscene;

import java.util.Calendar;
import java.util.Date;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SScene;
import net.snake.script.util.DateUtile;

/**
 * 宝藏使者
 * 
 * @author serv_dev
 */
public class BaoZangShiZhe_ShenDiaoGu_SceneUpdate implements IEventListener {

//	private final int MonsterID = 21103;// 宝藏使者的怪物模型ID TODO
	public static int sceneId = 20011;
	public static int[][] refreshPoint = new int[][] { { 110, 33 } };
	public static boolean sendMsgTime = true;

	/**
	 * 刷新怪物
	 * 
	 * @param api
	 * @param scene
	 */
	public void flushMonster(SApi api, SScene scene) {

		// api.createMonsterAroundPoint(scene, refreshPoint[0][0],
		// refreshPoint[0][1], 20, MonsterID, false, 3600000 * 23);

	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_SceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SScene scene = (SScene) args[0];

		if (sceneId != scene.getId()) {
			return;
		}
		Calendar calendar = DateUtile.dateToCalendar(new Date());
		int hourse = calendar.get(Calendar.HOUR_OF_DAY);
		if (hourse != 18) {
			return;
		}
		int mm = calendar.get(Calendar.MINUTE);
		if (mm > 42) {
			return;
		}
		int lineId = scene.getLineId();
		if (mm >= 40) {
			Boolean flushMusterTime = (Boolean) scene.getAttribute("baozang");
			if (flushMusterTime == null) {
				flushMusterTime = true;
			}
			if (flushMusterTime) {
				flushMusterTime = false;
				flushMonster(api, scene);
				scene.setAttribute("baozang", flushMusterTime);
			}// TODO 现在 flush参数是false到明天这个时间，还刷不刷？
			if (lineId == 1) {
				sendMsgTime = true;
			}
			return;
		} else if (mm >= 30) {
			scene.setAttribute("baozang", true);
			if (lineId == 1) {
				if (sendMsgTime) {
					sendMsgTime = false;
					api.sendMsgToAll((byte) 4, 20124);
				}
			}
		}
	}
}
