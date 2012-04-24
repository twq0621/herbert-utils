package net.snake.gamemodel.task.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.response.FinishTaskResponse;
import net.snake.serverenv.vline.CharacterRun;

/**
 * 系统发布任务 系统发布任务 不能带任务道具 否则 如果人物包裹是满的 则将任务道具 放入 人物包裹中
 * 
 * @author serv_dev
 * 
 */
public class SystemPublishTask {

	/**
	 * 使用物品发布任务给 某个玩家
	 */
	public static boolean publishTaskToPlayer(int taskid, Hero character) {
		CharacterTaskController taskController = character.getTaskController();
		taskController.addTask(TaskManager.getInstance().getCacheTaskMap().get(taskid));
		return true;
	}

	/**
	 * 发布任务给所有我玩家
	 */
	public static boolean publishTaskToAllPlayer(Task task) {
		final int taskid = task.getTaskId();
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				CharacterTaskController taskController = character.getTaskController();
				taskController.addTask(TaskManager.getInstance().getCacheTaskMap().get(taskid));
			}
		});
		return true;
	}

	public static void finishTask(int taskId, short choosegoodsPos, int horseId, Hero character) throws Exception {
		CharacterTaskController taskController = character.getTaskController();
		CharacterTask characterTask = taskController.getCurrTaskById(taskId);
		if (characterTask == null) {
			character.sendMsg(new FinishTaskResponse(0, 20000, 0));
			return;
		}

		if (choosegoodsPos > 0) {
			CharacterGoodController characterGoodController = character.getCharacterGoodController();
			CharacterGoods characterGoods = characterGoodController.getGoodsByPositon(choosegoodsPos);

			if (characterGoods == null) {
				character.sendMsg(new FinishTaskResponse(0, 20047, 0));
				return;
			}

			List<CharacterGoods> characterGoodsList = characterTask.getTakeCharacterGoods();
			if (characterGoodsList == null) {
				characterGoodsList = new ArrayList<CharacterGoods>();
				characterTask.setTakeCharacterGoods(characterGoodsList);
				characterGoodsList.add(characterGoods);
			} else {
				if (!characterGoodsList.isEmpty()) {
					characterGoodsList.clear();
				}
				characterGoodsList.add(characterGoods);
			}
		} else {
			characterTask.setTakeCharacterGoods(null);
		}

		if (horseId > 0) {
			CharacterHorseController characterHorseController = character.getCharacterHorseController();
			Horse horse = characterHorseController.getBagHorseContainer().getHorseByID(horseId);
			if (horse != null) {
				characterTask.setTakeHorse(horse);
			} else {
				characterTask.setTakeHorse(null);
			}
		} else {
			characterTask.setTakeHorse(null);
		}

		taskController.finishTask(characterTask);
	}
}
