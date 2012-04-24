package net.snake.gamemodel.activities.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Language;
import net.snake.gamemodel.activities.bean.ActivityData;
import net.snake.gamemodel.activities.bean.DailyActivity;
import net.snake.gamemodel.activities.persistence.ActivityDataManager;
import net.snake.gamemodel.activities.response.DailyActivityResponse40042;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40041, accessLimit = 100)
public class DailyActivityProccess40041 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
//		else {
//			// no daily
//			int i=0;
//			if (i==0) {
//				return ;
//			}
//			
//		}
		List<ActivityData> allActivity = ActivityDataManager.getInstance().getAllActivity();
		byte byte1 = request.getByte();
		// byte byte2 = request.getByte();

		DailyActivity[] dia = null;
		switch (byte1) {
		case 1:// 符合条件的
			List<DailyActivity> meetConditionsDailyActivity = meetConditionsDailyActivity(allActivity, character);
			if (meetConditionsDailyActivity != null && meetConditionsDailyActivity.size() > 0) {
				dia = meetConditionsDailyActivity.toArray(new DailyActivity[] {});
			}
			break;
		case 0:// 全部
			List<DailyActivity> activityToDailyActivity = activityToDailyActivity(allActivity, character);
			if (activityToDailyActivity != null && activityToDailyActivity.size() > 0)
				dia = activityToDailyActivity.toArray(new DailyActivity[] {});
			break;
		}
		character.sendMsg(new DailyActivityResponse40042(dia));
	}

	/**
	 * 所有活动
	 */
	private List<DailyActivity> activityToDailyActivity(List<ActivityData> allActivity, Hero c) {
		if (allActivity == null) {
			return null;
		}
		List<DailyActivity> list = new ArrayList<DailyActivity>();
		for (ActivityData activity : allActivity) {
			short iscount = activity.getfIscount();
			int finshcount = 0;
			if (iscount == 1) {
				Integer getfTaskid = activity.getfTaskid();
				CharacterTask terminativeTaskById = c.getTaskController().getTerminativeTaskById(getfTaskid);
				if (terminativeTaskById != null) {
					finshcount = terminativeTaskById.getCompleteNum();
				}
			}
			DailyActivity dailyActivity = new DailyActivity(activity.getfActivityTimeI18n(), activity.getfNameI18n(), activity.getfGrade(), getNpcName(activity.getfFbnpcid()),
					activity.getfFbnpcid(), finshcount, activity.getfMaxcountI18n(), activity.getfRewardleave(), activity.getfDescI18n(), 1, 1, "", activity.getfIscount());
			list.add(dailyActivity);
		}
		return list;

	}

	/*** 可参加的活动 **/
	private List<DailyActivity> meetConditionsDailyActivity(List<ActivityData> allActivity, Hero c) {
		if (allActivity != null) {
			List<DailyActivity> list = new ArrayList<DailyActivity>();
			for (ActivityData activity : allActivity) {
				if (activity.getfDayshow().contains("" + getWeek())) {
					short iscount = activity.getfIscount();
					int finshcount = 0;
					if (iscount == 1) {
						Integer getfTaskid = activity.getfTaskid();
						CharacterTask terminativeTaskById = c.getTaskController().getTerminativeTaskById(getfTaskid);
						if (terminativeTaskById != null) {
							finshcount = terminativeTaskById.getCompleteNum();
						}
					}

					DailyActivity dailyActivity = new DailyActivity(activity.getfActivityTimeI18n(), activity.getfNameI18n(), activity.getfGrade(),
							getNpcName(activity.getfFbnpcid()), activity.getfFbnpcid(), finshcount, activity.getfMaxcountI18n(), activity.getfRewardleave(),
							activity.getfDescI18n(), 1, 1, "", activity.getfIscount());
					list.add(dailyActivity);
				}
			}
			return list;
		}
		return null;
	}

	public int getWeek() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		return dayOfWeek;
	}

	private String getNpcName(int id) {
		if (id == 0) {
			return Language.WUXU;
		}
		Npc npc = NpcManager.getInstance().getCacheNpcMap().get(id);
		npc.getNameI18n();
		return npc == null ? Language.WEIZHI + "NPC" : npc.getNameI18n();
	}

}
