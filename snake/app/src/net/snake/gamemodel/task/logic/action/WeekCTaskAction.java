package net.snake.gamemodel.task.logic.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.GenerateProbability;
import net.snake.consts.LoopTaskType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.response.GetTaskResponse;


/**
 * 每周环任务
 * 
 * @author serv_dev
 * 
 */
public class WeekCTaskAction extends BaseLoopTaskAction {
	static final Logger logger = Logger.getLogger(WeekCTaskAction.class);

	@Override
	public boolean acceptCondition(CharacterTask characterTask) {
		if (super.acceptCondition()) {

			if (characterTask.getAcceptNum() >= Task.Week_Task_Limit) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20016, 0, Task.Week_Task_Limit + ""));
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected final LoopTaskType getLoopTaskType() {
		return LoopTaskType.week;
	}

	@Override
	protected final void extraReward(CharacterTask finishCharacterTask) throws Exception {
		if (finishCharacterTask.getCompleteNum() == 100) {
			CharacterFormula.experienceProcess(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskWeekExp100(getCharacter()));
		} else if (finishCharacterTask.getCompleteNum() == 200) {
			CharacterFormula.experienceProcess(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskWeekExp200(getCharacter()));
			CharacterPropertyManager.changeZhenqi(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskWeekZhenqi200(getCharacter()));
		}
	}

	@Override
	public final boolean isOutTime(CharacterTask characterTask) {
		Calendar recordCalendar = new GregorianCalendar();// 当前记录的日期
		Calendar completeCalendar = new GregorianCalendar();// 比较的日期
		// recordCalendar.setTime(characterTask.getGettime());
		recordCalendar.setTimeInMillis(characterTask.getGettime().getTime() - 24 * 60 * 60 * 1000);
		completeCalendar.setTime(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
		if (recordCalendar.get(Calendar.YEAR) != completeCalendar.get(Calendar.YEAR) || recordCalendar.get(Calendar.WEEK_OF_YEAR) != completeCalendar.get(Calendar.WEEK_OF_YEAR)) {

			// if (completeCalendar.getTime().getTime() -
			// recordCalendar.getTime().getTime() >= 7 * 24 * 60 * 60 * 1000) {
			// recordCalendar.setTime(new Date());
			// return true;
			// }

			// if (completeCalendar.get(Calendar.DAY_OF_WEEK) == 2) {//
			// 一周的第二天，星期一
			// recordCalendar.setTime(new Date());
			// return true;
			// }

			return true;
		} else {
			return false;
		}
	}

	@Override
	protected final int getLoopNum(CharacterTask characterTask) {
		return characterTask.getAcceptNum();
	}

	@Override
	public final void randomNpc(CharacterTask characterTask) {
		List<Npc> npcs = NpcManager.getInstance().getWeekTaskNpc(getCharacter().getGrade());
		int _num = GenerateProbability.randomIntValue(0, npcs.size() - 1);
		Npc npc = npcs.get(_num);
		characterTask.setEndnpc(npc.getId());// 动态endNpc
	}
}
