package net.snake.gamemodel.task.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Language;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharDayInComeData;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.InstanceDataRef;
import net.snake.gamemodel.instance.bean.InstanceDayStat;
import net.snake.gamemodel.instance.logic.MyInstanceDayStatManager;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.response.BossRefreshTimeResponse40022;
import net.snake.gamemodel.task.bean.Backlog;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.response.BackLogResponse40032;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40031, accessLimit = 100)
public class BacklogProccess40031 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		Collection<CharacterTask> allCurrentTasks = character.getTaskController().getAllCurrentTasks();
		List<Backlog> list = new ArrayList<Backlog>();
		Map<Integer, Task> cacheTaskMap = TaskManager.getInstance().getCacheTaskMap();
		Set<Entry<Integer, Task>> entrySet = cacheTaskMap.entrySet();
		// 处理环任务
		for (Entry<Integer, Task> entry : entrySet) {
			Task value = entry.getValue();
			if (value.isWeekOrDayLoopTask()) {
				CharacterTask task = character.getTaskController().getTerminativeTaskById(value.getTaskId());
				if (task != null) {
					list.add(transToBacklog(task));
				} else {
					list.add(transToBacklog(value));
				}
			}
		}
		// 环任务结束

		// 处理普通任务
		for (CharacterTask characterTask : allCurrentTasks) {
			if (!characterTask.getTaskVO().getTask().isWeekOrDayLoopTask()) {
				list.add(transToBacklog(characterTask));
			}
		}

		List<Backlog> instance = new ArrayList<Backlog>();
		MyInstanceDayStatManager manager = character.getMyInstanceDayStatManager();
		List<InstanceDataRef> instanceDataRefList = InstanceDataProvider.getInstance().getInstanceDataRefList();
		for (InstanceDataRef instanceDataRef : instanceDataRefList) {
			String title = "";
			if (instanceDataRef.getEnable() == 1) {
				InstanceDayStat stat = manager.getStat(instanceDataRef.getInstanceModelId());
				String name = instanceDataRef.getInstanceNameI18n();
				int count = stat == null ? 0 : stat.getResetinstancecount();
				int max = instanceDataRef.getEnterCountLimite();
				if (character.getMyCharacterVipManger().isVipYueka()) {
					max++;
				}
				if (count < max) {
					title += name + "（" + count + "/" + max + "）\n";
				}
				instance.add(new Backlog(instanceDataRef.getTransnpcid(), title));
			}

		}

		List<SceneMonster> bosslist = new ArrayList<SceneMonster>();
		Collection<Scene> worldSceneList = character.getVlineserver().getSceneManager().getWorldSceneList();
		for (Scene scene : worldSceneList) {
			List<SceneMonster> bossList2 = scene.getRefreshMonsterController().getBossList();
			if (bossList2 != null && bossList2.size() > 0) {
				for (SceneMonster sceneMonster : bossList2) {
					if (sceneMonster.getMonsterModel().isBoss()) {
						bosslist.add(sceneMonster);
					}
				}
			}
			Collection<SceneMonster> allSceneMonster = scene.getAllSceneMonster();
			for (SceneMonster sceneMonster : allSceneMonster) {
				if (sceneMonster.getMonsterModel().isBoss()) {
					bosslist.add(sceneMonster);
				}
			}
		}
		Collections.sort(bosslist, BossRefreshTimeResponse40022.bossComparator);
		String bossdesc = "";
		if (bosslist != null && bosslist.size() > 0) {

			for (SceneMonster boss : bosslist) {
				String start = "";
				if (!boss.isDie()) {
					start = Language.WEISIWANG;
				} else {
					start = boss.getMonsterModel().getTimeExpression().getStart(System.currentTimeMillis());
				}

				String name = boss.getMonsterModel().getNameI18n();
				if (name.contains("_")) {
					name = name.substring(0, name.indexOf("_"));
				}
				if (name.length() == 2) {
					name += "  ";
				}
				bossdesc += name + "  	  	" + start + ",";
			}

			if (worldSceneList.size() > 0) {
				bossdesc.substring(0, bossdesc.length() - 1);
			}
		}
		CharDayInComeData countData = character.getDayInCome().getCountData();
		long getfExp = countData == null ? 0 : countData.getfExp();
		Integer zq = countData == null ? 0 : countData.getfZhengqi();
		Integer getfEquip = countData == null ? 0 : countData.getfEquip();
		Integer getfFinshinstance = countData == null ? 0 : countData.getfFinshinstance();
		Integer getfFinshtask = countData == null ? 0 : countData.getfFinshtask();
		Integer getfShengwang = countData == null ? 0 : countData.getfShengwang();
		Integer getfKillboss = countData == null ? 0 : countData.getfKillboss();
		Integer getfKillmonster = countData == null ? 0 : countData.getfKillmonster();
		int suggest = character.getSuggestManager().getSuggest();
		BackLogResponse40032 f = new BackLogResponse40032(list, instance, bossdesc, getfExp, getfKillmonster, getfEquip, getfFinshtask, zq, getfKillboss, getfShengwang,
				getfFinshinstance, suggest);
		character.sendMsg(f);
	}

	private Backlog transToBacklog(CharacterTask task) {
		return new Backlog(task.getTask(), task.getTaskVO().getTask().getNameI18n(), task.getCompleteNum(), task.getTaskVO().getTask().getLoopmaxcount() == null ? 1 : task
				.getTaskVO().getTask().getLoopmaxcount());
	}

	private Backlog transToBacklog(Task task) {
		return new Backlog(task.getTaskId(), task.getNameI18n(), 0, task.getLoopmaxcount());
	}

}
