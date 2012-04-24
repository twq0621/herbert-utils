package net.snake.gamemodel.task.logic;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.commons.program.Updatable;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.shop.bean.TaskShoppingGoods;
import net.snake.gamemodel.shop.persistence.TaskShoppingManager;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.action.TaskAction;
import net.snake.gamemodel.task.logic.action.TaskActionFactory;
import net.snake.gamemodel.task.persistence.CharacterTaskManager;
import net.snake.gamemodel.task.response.InitTaskResponse;
import net.snake.gamemodel.task.response.TaskRunResponse;
import org.apache.log4j.Logger;

/**
 * 任务管理器
 * 
 * @author serv_dev
 * 
 */
public class CharacterTaskController extends CharacterController implements Updatable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CharacterTaskController.class);

	private Map<Integer, CharacterTask> currentCharacterTaskMap = new ConcurrentHashMap<Integer, CharacterTask>();// 当前未完成的任务
	private Map<Integer, CharacterTask> terminativeCharacterTaskMap = new ConcurrentHashMap<Integer, CharacterTask>();// 已经完成的角色任务;
	private Map<Integer, TaskShoppingGoods> taskShoppingGoodsMap = new HashMap<Integer, TaskShoppingGoods>();// goodmodelid,TaskShoppingGoods
	private final Calendar recordCalendar = new GregorianCalendar(TimeZone.getDefault());// 当前记录的日期
	private final Calendar completeCalendar = new GregorianCalendar(TimeZone.getDefault());// 比较的日期
	private long checkBeginTime = 0l;

	public void destroy() {
		if (currentCharacterTaskMap != null) {
			currentCharacterTaskMap.clear();
			currentCharacterTaskMap = null;
		}
		if (terminativeCharacterTaskMap != null) {
			terminativeCharacterTaskMap.clear();
			terminativeCharacterTaskMap = null;
		}
		if (taskShoppingGoodsMap != null) {
			taskShoppingGoodsMap.clear();
			taskShoppingGoodsMap = null;
		}

	}

	public CharacterTaskController(Hero character) {
		super(character);
	}

	/**
	 * 当前任务是否包含该任务
	 * 
	 * @param taskId
	 * @return
	 */
	public boolean isCurrentTasksContain(int taskId) {
		return currentCharacterTaskMap.get(taskId) == null ? false : true;
	}

	/**
	 * 已完成任务是否包含该任务
	 * 
	 * @param taskId
	 * @return
	 */
	public boolean isTerminativeTasksContain(int taskId) {
		return terminativeCharacterTaskMap.get(taskId) == null ? false : true;
	}

	public void addTaskShoppingGoods(TaskShoppingGoods taskShoppingGoods) {
		if (taskShoppingGoods.getGood() == null || taskShoppingGoods.getGood() == 0) {
			return;
		}
		if (getTaskShoppingGoods(taskShoppingGoods.getGood()) != null) {
			updateTaskShoppingGoods(taskShoppingGoods);
		} else {
			insertTaskShoppingGoods(taskShoppingGoods);
			taskShoppingGoodsMap.put(taskShoppingGoods.getGood(), taskShoppingGoods);
		}

		checkAllCurTaskTargetShopping();
	}

	public TaskShoppingGoods getTaskShoppingGoods(int goodmodelId) {
		return taskShoppingGoodsMap.get(goodmodelId);
	}

	public void updateTaskShoppingGoods(TaskShoppingGoods taskShoppingGoods) {
		TaskShoppingManager.getInstance().asynUpdataCharacterTaskShoppingGoods(character, taskShoppingGoods);
	}

	public void insertTaskShoppingGoods(TaskShoppingGoods taskShoppingGoods) {
		TaskShoppingManager.getInstance().asynInsertCharacterTaskShoppingGoods(character, taskShoppingGoods);
	}

	public boolean isNeedGood(int goodmodelId) {
		for (Iterator<CharacterTask> iterator = currentCharacterTaskMap.values().iterator(); iterator.hasNext();) {
			CharacterTask characterTask = iterator.next();
			if (characterTask.isNeedGood(character.getCharacterGoodController(), goodmodelId)) {
				return true;
			}
		}

		return false;
	}

	/***
	 * 任务管理器初始化
	 */
	public void init() {
		try {
			CharacterTaskManager taskManager = CharacterTaskManager.getInstance();
			taskManager.initCurrentCharacterTaskMap(currentCharacterTaskMap, this);
			taskManager.initTerminativeCharacterTaskMap(terminativeCharacterTaskMap, this);

			checkAllCurrentTaskOutTime();
			List<TaskShoppingGoods> taskShoppingGoods = TaskShoppingManager.getInstance().getCharacterTaskShoppingGoodsList(getCharacter().getId());
			for (Iterator<TaskShoppingGoods> iterator = taskShoppingGoods.iterator(); iterator.hasNext();) {
				TaskShoppingGoods taskShoppingGoods2 = iterator.next();
				taskShoppingGoodsMap.put(taskShoppingGoods2.getGood(), taskShoppingGoods2);
			}
			recordCalendar.setTime(new Date());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 检查当前的任务是否需要升级到牛的任务
	 */
	public void checkAllCurTaskMountUpGrade() {
		if (!currentCharacterTaskMap.isEmpty()) {
			for (Iterator<CharacterTask> iterator = currentCharacterTaskMap.values().iterator(); iterator.hasNext();) {
				CharacterTask characterTask = iterator.next();
				if (characterTask.getOrgTask().isLoopTask())
					continue;
				checkMountUpGrade(characterTask);
			}
		}
	}

	/**
	 * 检查当前的任务是否需要升级到牛的任务
	 * 
	 * @param characterTask
	 */
	public void checkMountUpGrade(CharacterTask characterTask) {
		Task task = characterTask.getOrgTask();
		String targetMountUpGrade = task.getTargetmountupgrade();
		if (targetMountUpGrade != null && !"".equals(targetMountUpGrade) && Integer.parseInt(targetMountUpGrade) == 1) {
			String recordTargetmountupgrade = characterTask.getTargetmountupgrade();
			if (recordTargetmountupgrade == null || "".equals(recordTargetmountupgrade)) {
				if (getCharacter().getMyCharacterAchieveManger().getFinishAchieve(ClientConfig.HorseDiLuToNiuAchieveId) != null) {
					characterTask.setTargetmountupgrade("1");
					getCharacter().getTaskController().updateTask_sql(characterTask);
					getCharacter().sendMsg(new TaskRunResponse(task.getTaskId(), Task.MOUNTUPGRADE, characterTask.getTargetmountupgrade()));
				}
			} else {
				getCharacter().sendMsg(new TaskRunResponse(task.getTaskId(), Task.MOUNTUPGRADE, characterTask.getTargetmountupgrade()));
			}
		}
	}

	/**
	 * 检查是否购买过某个道具 购买过的时候调用
	 */
	public void checkAllCurTaskTargetShopping() {
		if (!currentCharacterTaskMap.isEmpty()) {
			for (Iterator<CharacterTask> iterator = currentCharacterTaskMap.values().iterator(); iterator.hasNext();) {
				CharacterTask characterTask = iterator.next();
				if (characterTask.getOrgTask().isLoopTask())
					continue;
				checkTargetShopping(characterTask);
			}
		}
	}

	/**
	 * 检查是否购买过某个道具 接受任务时调用
	 * 
	 * @param characterTask
	 */
	public void checkTargetShopping(CharacterTask characterTask) {
		Task task = characterTask.getOrgTask();
		String targetshopping = task.getTargetshopping();
		if (targetshopping != null && !"".equals(targetshopping)) {

			String[] _targetShoppingStr = targetshopping.split(",");
			int tmp = 0;

			characterTask.setTargetshopping("");
			boolean change = false;
			for (int i = 0; i < _targetShoppingStr.length; i++) {
				String _targetShopping = _targetShoppingStr[i];
				if ("".equals(_targetShopping)) {
					continue;
				}

				String[] __targetShopping = _targetShopping.split("#");
				if (__targetShopping.length != 2)
					continue;
				int goodId = Integer.parseInt(__targetShopping[0]);
				TaskShoppingGoods taskShoppingGoods = getTaskShoppingGoods(goodId);

				if (taskShoppingGoods != null) {
					if (tmp > 0) {
						characterTask.setTargetshopping(characterTask.getTargetshopping() + ",");
					}
					int goodNum = Integer.parseInt(__targetShopping[1]);
					if (taskShoppingGoods.getNum() >= goodNum) {
						characterTask.setTargetshopping(characterTask.getTargetshopping() + goodId + "#" + goodNum);
						change = true;
					} else {
						characterTask.setTargetshopping(characterTask.getTargetshopping() + goodId + "#" + taskShoppingGoods.getNum());
						change = true;
					}

					tmp++;
				}
			}

			if (change) {
				getCharacter().getTaskController().updateTask_sql(characterTask);
				getCharacter().sendMsg(new TaskRunResponse(task.getTaskId(), Task.SHOPPING, characterTask.getTargetshopping()));
			}

			// String recordTargetshopping = characterTask.getTargetshopping();

			// if (recordTargetshopping == null ||
			// "".equals(recordTargetshopping)) {
			// String[] _targetShoppingStr = targetshopping.split(",");
			// Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
			// for (int i = 0; i < _targetShoppingStr.length; i++) {
			// String _targetShopping = _targetShoppingStr[i];
			// if ("".equals(_targetShopping)) {
			// continue;
			// }
			// String[] __targetShopping = _targetShopping.split("#");
			// if (__targetShopping.length != 2) continue;
			// tmpMap.put(Integer.parseInt(__targetShopping[0]),
			// Integer.parseInt(__targetShopping[1]));
			// }
			//
			// if (!tmpMap.isEmpty()) {
			// //
			// boolean result = false;
			// if (result) {
			// characterTask.setTargetshopping(targetshopping);
			// getCharacter().getTaskController().updateTask_sql(characterTask);
			// getCharacter().sendMsg(new
			// TaskRunResponse(task.getTaskId(),Task.SHOPPING,characterTask.getTargetshopping()));
			// }
			// }
			// }
		}
	}

	/**
	 * 检查是否充值过元宝
	 */
	public void checkAllCurTaskYuanBao() {
		if (!currentCharacterTaskMap.isEmpty()) {
			for (Iterator<CharacterTask> iterator = currentCharacterTaskMap.values().iterator(); iterator.hasNext();) {
				CharacterTask characterTask = iterator.next();
				if (characterTask.getOrgTask().isLoopTask())
					continue;
				checkTargetYuanBao(characterTask);
			}
		}
	}

	// /**
	// * 检测一次充值
	// * @param yuanbao
	// */
	// public void checkAllCurTaskOnesYuanBao() {
	// if (!currentCharacterTaskMap.isEmpty()) {
	// for (Iterator<CharacterTask> iterator = currentCharacterTaskMap
	// .values().iterator(); iterator.hasNext();) {
	// CharacterTask characterTask = iterator.next();
	// checkTargetOnesYuanBao(characterTask);
	// }
	// }
	// }
	//
	// public void checkTargetOnesYuanBao(CharacterTask characterTask) {
	// Task task = characterTask.getOrgTask();
	// Date date1 = task.getCompleteBeginTime();
	// if (date1 != null) {
	// if (new Date().before(date1)){
	// return;
	// }
	// }
	//
	// Date date2 = task.getCompleteEndTime();
	// if (date2 != null) {
	// if (new Date().after(date2)){
	// return;
	// }
	// }
	//
	//
	// }

	/**
	 * 检查指定的任务是否满足充值过元宝
	 */
	public void checkTargetYuanBao(final CharacterTask characterTask) {
		final Task task = characterTask.getOrgTask();
		final String targetrecharge = task.getTargetrecharge();
		if (targetrecharge != null && !"".equals(targetrecharge)) {
			final int needYuanbao = Integer.parseInt(targetrecharge);
			final Account account = character.getAccount();
			if (account != null) {
				final Date beginTime = task.getAcceptBeginTime();
				final Date endTime = task.getCompleteEndTime();
				if (beginTime != null && endTime != null) {
					Date date = new Date();
					if (date.before(beginTime) || date.after(endTime)) {
						return;
					}

					// 查询充值表记录
					GameServer.executorServiceForDB.execute(new Runnable() {
						@Override
						public void run() {
							try {
								final int accounYuanbao = 0;//TODO RechangeLogManager.getInstance().sumRechargeYuanbaoByTimeAndAccountId(account.getId(), beginTime, endTime);
								character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
									@Override
									public void run() {
										_procYuanbaoTask(characterTask, task, targetrecharge, needYuanbao, accounYuanbao);
									}
								});
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
							}
						}
					});
				} else {
					int accounYuanbao = account.getRechargeYuanbao();
					_procYuanbaoTask(characterTask, task, targetrecharge, needYuanbao, accounYuanbao);
				}
			}
		}
	}

	private void _procYuanbaoTask(CharacterTask characterTask, Task task, String targetrecharge, int needYuanbao, int accounYuanbao) {
		if (accounYuanbao > 0) {
			String recordTargetrecharge = characterTask.getTargetrecharge();
			if (recordTargetrecharge == null || "".equals(recordTargetrecharge)) {
				int recordYuanbao = 0;
				int _addYuanbao = accounYuanbao - recordYuanbao;
				if (_addYuanbao > 0) {
					if (accounYuanbao >= needYuanbao) {
						characterTask.setTargetrecharge(targetrecharge);
					} else {
						characterTask.setTargetrecharge(recordYuanbao + _addYuanbao + "");
					}
					getCharacter().getTaskController().updateTask_sql(characterTask);
					getCharacter().sendMsg(new TaskRunResponse(task.getTaskId(), Task.YUANBAO, characterTask.getTargetrecharge()));
				}
			} else {
				int recordYuanbao = Integer.parseInt(recordTargetrecharge);
				int _addYuanbao = accounYuanbao - recordYuanbao;
				if (_addYuanbao > 0) {
					if (accounYuanbao >= needYuanbao) {
						characterTask.setTargetrecharge(targetrecharge);
					} else {
						characterTask.setTargetrecharge(recordYuanbao + _addYuanbao + "");
					}
					getCharacter().getTaskController().updateTask_sql(characterTask);
					getCharacter().sendMsg(new TaskRunResponse(task.getTaskId(), Task.YUANBAO, characterTask.getTargetrecharge()));
				}
			}
		}
	}

	/**
	 * 
	 * 检查任务是否超时，环任务过时将自动移除掉 两个时间点检测 1.任务被初始化的时候 2.人物在线的时候
	 * 
	 */
	public void checkAllCurrentTaskOutTime() {
		List<CharacterTask> removeTasks = new ArrayList<CharacterTask>();
		if (!currentCharacterTaskMap.isEmpty()) {
			for (Iterator<CharacterTask> iterator = currentCharacterTaskMap.values().iterator(); iterator.hasNext();) {
				CharacterTask characterTask = iterator.next();
				// 日、周环任务过期移除 3日环式任务4.日押镖任务5周环式任务
				if (characterTask.isOutTime()) {
					removeTasks.add(characterTask);
				}
			}
		}

		if (!terminativeCharacterTaskMap.isEmpty()) {
			for (Iterator<CharacterTask> iterator = terminativeCharacterTaskMap.values().iterator(); iterator.hasNext();) {
				CharacterTask characterTask = iterator.next();
				// 日、周环任务过期移除 3日环式任务4.日押镖任务5周环式任务
				if (characterTask.isOutTime()) {
					removeTasks.add(characterTask);
				}
			}
		}

		for (Iterator<CharacterTask> iterator = removeTasks.iterator(); iterator.hasNext();) {
			CharacterTask characterTask = iterator.next();
			characterTask.getTaskVO().autoRecover();
		}
	}

	/**
	 * 
	 * 验证 和 添加 任务
	 * 
	 * @param taskMap
	 * @param taskId
	 * @param staticDataDispatcher
	 * @param isValidatePremisetask
	 *            是否验证前置任务id
	 * @return
	 */
	public boolean addTask(Task task) {
		TaskActionFactory factory = TaskActionFactory.getInstance();
		TaskAction ta = factory.createAction(task, this);
		if (ta != null) {
			ta.accept();
		}
		return true;
	}

	/**
	 * 任务追踪
	 * 
	 * @param taskId
	 * @param triggerType
	 * @param data
	 * @return
	 */
	public void taskRun(int taskId, byte triggerType, int data) {
		CharacterTask currCharacterTask = getCurrTaskById(taskId);
		Task task = currCharacterTask.getOrgTask();
		String dataMsg = null;
		// 1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作
		if (task != null && currCharacterTask != null) {
			Scene sceneid = character.getSceneRef();// 场景id
			SceneMonster sceneMonster = sceneid.getSceneMonster(data);// 得到场景怪物
			if (triggerType == Task.KILL_MONSTER)// 刺杀怪物
			{

				if (currCharacterTask.monsterRecord(sceneMonster)) {
					updateTask_sql(currCharacterTask);
					dataMsg = currCharacterTask.getTargetmonster();
				}

			} else if (triggerType == Task.SEARCH_NPC)// 找到npc
			{
				Map<Integer, Npc> npcMap = NpcManager.getInstance().getCacheNpcMap();
				Npc npc = npcMap.get(data);
				if (currCharacterTask.npcRecord(npc)) {
					this.updateTask_sql(currCharacterTask);
					dataMsg = currCharacterTask.getTargetnpc();
				}
			} else if (triggerType == Task.QUYU)// 到达指定区域
			{
				if (currCharacterTask.areaRecord(sceneid.getId(), character.getX(), character.getY())) {
					this.updateTask_sql(currCharacterTask);
					dataMsg = currCharacterTask.getTargetarea();
				}
			} else if (triggerType == Task.DONGZUO) {// 动作
				// TODO 以后要验证
				if (currCharacterTask.actionRecord(data)) {
					this.updateTask_sql(currCharacterTask);
					dataMsg = currCharacterTask.getTargetaction();
				}
			}
		}

		if (dataMsg != null) {
			character.sendMsg(new TaskRunResponse(taskId, triggerType, dataMsg));
		}
	}

	/**
	 * 完成任务
	 * 
	 * @param finishTaskId
	 * @param chooseGoodsId
	 * @return
	 * @throws Exception
	 */
	public void finishTask(CharacterTask finishCharacterTask) throws Exception {
		finishCharacterTask.getTaskVO().complete();
		character.getDayInCome().dealFinshTask(1);
	}

	// public String validateFinishTask(CharacterTask finishCharacterTask) {
	//
	// Task task = finishCharacterTask.getOrgTask();
	//
	// if (task != null) {
	// int finishTaskLevel = task.getEndtasklevel();// 任务完成需要的等级
	// // int taskFunType = task.getFunctiontype();
	// // 验证结束任务等级
	// if (character.getGrade() < finishTaskLevel) {
	// return "等级不符合任务条件";
	// }
	// // 验证是否超过时间限制
	// if (!finishCharacterTask.validateLimitTime()) {
	// return "任务超时";
	// }
	// // if (taskFunType == Task.SEARCH_NPC || taskFunType == Task.FUHE) {
	// // 验证 目标Npc
	// if (!finishCharacterTask.validateNpc()) {
	// return "没有找到指定NPC";
	// }
	// // }
	// // if (taskFunType == Task.QUYU || taskFunType == Task.FUHE) {
	// // 验证 目标区域
	// if (!finishCharacterTask.validateArea()) {
	// return "没有到过指定区域";
	// }
	// // }
	// // if (taskFunType == Task.KILL_MONSTER || taskFunType == Task.FUHE)
	// // {
	// // 验证 目标怪
	// if (!finishCharacterTask.validateMonster()) {
	// return "没有杀死指定怪物";
	// }
	// // }
	// // if (taskFunType == Task.DONGZUO || taskFunType == Task.FUHE) {
	// if (!finishCharacterTask.validateAction()) {
	// return "指定动作 没有完成";
	// }
	// // }
	//
	// } else {
	// return "指定任务 不存在";
	// }
	// return null;
	// }

	/**
	 * 删除任务物品
	 * 
	 * @param task
	 */
	public void deleteTaskGoods(Task task) {
		String taskGoodStr = task.getTaskgoods();
		if (!"".equals(taskGoodStr)) {
			String[] taskGoods = taskGoodStr.split(":");
			if (!"".equals(taskGoods[0])) {
				int goodId = Integer.parseInt(taskGoods[0]);
				int retrieve = Integer.parseInt(taskGoods[1]);
				if (retrieve == 1) {// 回收
					character.getCharacterGoodController().deleteGoodsFromBag(goodId, 1);
				}
			}
		}
	}

	/**
	 * 角色进入地图时发送消息
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void sendInitTaskResponse() throws IOException, ParseException {

		character.sendMsg(new InitTaskResponse(getAllCurrentTasks(), getAllTerminativeTasks()));
		checkAllCurTaskYuanBao();
	}

	public void putCurrentTaskMap(CharacterTask characterTask) {
		currentCharacterTaskMap.put(characterTask.getTask(), characterTask);
	}

	public CharacterTask removeCurrentTaskFromMap(CharacterTask characterTask) {
		return currentCharacterTaskMap.remove(characterTask.getTask());
	}

	public CharacterTask putTerminativeTaskMap(CharacterTask characterTask) {
		return terminativeCharacterTaskMap.put(characterTask.getTask(), characterTask);
	}

	/**
	 * 从已完成的列表里一处一个指定的任务
	 * 
	 * @param characterTask
	 * @return
	 */
	public CharacterTask removeTerminativeTaskFromMap(CharacterTask characterTask) {
		return terminativeCharacterTaskMap.remove(characterTask.getTask());
	}

	/**
	 * 获得一个指定的未完成的任务
	 * 
	 * @param taskId
	 * @return
	 */
	public CharacterTask getCurrTaskById(Integer taskId) {
		return this.currentCharacterTaskMap.get(taskId);
	}

	/**
	 * 获得一个指定的已完成的任务
	 * 
	 * @param taskId
	 * @return
	 */
	public CharacterTask getTerminativeTaskById(Integer taskId) {
		return this.terminativeCharacterTaskMap.get(taskId);
	}

	/**
	 * 获得所有未完成的任务
	 * 
	 * @return
	 */
	public Collection<CharacterTask> getAllCurrentTasks() {
		return this.currentCharacterTaskMap.values();
	}

	/**
	 * 所有已经完成的任务
	 * 
	 * @return
	 */
	private Collection<CharacterTask> getAllTerminativeTasks() {
		return this.terminativeCharacterTaskMap.values();
	}

	/**
	 * 更新 人物任务
	 * 
	 * @param characterTask
	 */
	public void updateTask_sql(final CharacterTask characterTask) {

		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				CharacterTaskManager.getInstance().updataCharacterTask(character, characterTask);
			}
		});
	}

	/**
	 * 删除 人物任务
	 * 
	 * @param characterTask
	 */
	public void deleteTask_sql(final CharacterTask characterTask) {

		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				CharacterTaskManager.getInstance().deleteCharacterTask(character, characterTask.getId());
			}
		});
	}

	/**
	 * 插入 人物任务
	 * 
	 * @param characterTask
	 */
	public void insertTask_sql(final CharacterTask characterTask) {

		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				CharacterTaskManager.getInstance().insertCharacterTask(character, characterTask);
			}
		});
	}

	@Override
	public void update(long now) {
		/**
		 * 一分钟做一次检测
		 */
		if (now - checkBeginTime > 60000) {
			Date nowDate = new Date();
			completeCalendar.setTime(nowDate);
			if (recordCalendar.get(Calendar.YEAR) != completeCalendar.get(Calendar.YEAR) || recordCalendar.get(Calendar.MONTH) != completeCalendar.get(Calendar.MONTH)
					|| recordCalendar.get(Calendar.DATE) != completeCalendar.get(Calendar.DATE)) {
				recordCalendar.setTime(nowDate);// 过了一天
				checkAllCurrentTaskOutTime();
			}
			checkBeginTime = now;
		}
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		int s1 = currentCharacterTaskMap.size();
		int s2 = terminativeCharacterTaskMap.size();
		int s3 = taskShoppingGoodsMap.size();
		return s1 + s2 + s3;
	}
}
