package net.snake.gameserver.event.frame;

import java.util.Calendar;

import java.util.Date;
import java.util.Random;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SScene;
import net.snake.script.util.DateUtile;

/**
 * 寻宝鼠 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class XunBaoShu_World implements IEventListener {
	public static int nextTimeMsg = 2;
	public static int nextFlushMonsterTime = 0;
	private final int MonsterID = 21101;// 寻宝鼠的怪物模型ID
	private Random r = new Random();

	private int line = 2;// 可刷的线路

	private int[] randomMapId = new int[] { 30001 };


	private int mapLen = randomMapId.length;

	/**
	 * 进行刷怪操作
	 * 
	 * @param api
	 * @param scenes
	 */
	@SuppressWarnings("unused")
	private void flushMonster(SApi api, SScene[] scenes) {
		int ran = r.nextInt(mapLen);// 0-10
		int ssLen = scenes.length;
		for (int i = 0; i < 5; i++) {
			int sceneId = randomMapId[ran];
			for (int j = 0; j < ssLen; j++) {
				SScene scene2 = scenes[j];

				if (scene2.getId() == sceneId) {
					int[][] centerRandomPoints = scene2.getCenterRandomPoints();
					int rpoints = r.nextInt(centerRandomPoints.length);
					int m = 0;
					for (int k = 0; k < 3;) {// 每张地图刷新3只

						int x = centerRandomPoints[rpoints][0];
						int y = centerRandomPoints[rpoints][1];
						if (!(x == 0 && y == 0)) {// 障碍物
							api.createMonsterAroundPoint(scene2, centerRandomPoints[rpoints][0], centerRandomPoints[rpoints][1], 20, MonsterID, false, 3600000 * 2);
							k++;
						}
						rpoints = rpoints + 7;
						if (rpoints >= centerRandomPoints.length) {
							rpoints = rpoints - centerRandomPoints.length;
						}
						m++;
						if (m > 30) {
							break;
						}
					}
				}
			}
			ran = ran + 3;
			if (ran >= mapLen) {
				ran = ran - mapLen;
			}
		}
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_WorldUpdate;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SScene[] scenes = (SScene[]) args[0];
		int line = (Integer) args[1];
		if (line != this.line) {
			return;
		}
		Calendar calendar = DateUtile.dateToCalendar(new Date());
		int hourse = calendar.get(Calendar.HOUR_OF_DAY);
		if (hourse % 3 == 1) {
			return;
		}
		int mm = calendar.get(Calendar.MINUTE);
		if (hourse % 3 == 2) {
			if (mm < 50) {
				return;
			}
			if (nextTimeMsg <= hourse) {
				nextTimeMsg = hourse + 3;
				api.sendMsgToAll((byte) 4, 20123);
			}
			return;
		} else if (hourse % 3 == 0) {
			if (mm > 1) {
				return;
			}
			if (hourse == 0) {
				if (nextFlushMonsterTime > 20) {
					nextFlushMonsterTime = 0;
				}
				nextTimeMsg = 2;
			}
			if (nextFlushMonsterTime <= hourse) {
				nextFlushMonsterTime = hourse + 3;
				// flushMonster(api,scenes);
			}
			return;
		}
	}

}
