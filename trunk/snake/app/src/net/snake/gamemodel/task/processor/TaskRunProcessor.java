package net.snake.gamemodel.task.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.shell.Options;

/**
 * 任务追踪处理 10267
 * 
 * @author serv_dev
 * @see MsgProcessor
 */
@MsgCodeAnn(msgcode = 10267, accessLimit = 0)
public class TaskRunProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int taskId = request.getInt();// 任务id
		byte triggerType = request.getByte();// 触发类型(功能类型:1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作 8复合任务)
		int data = request.getInt();// 1刺杀怪物id 2寻找NPCid 3收集 4到达指定区域 5传送 6采集 7触发动作id
		CharacterTaskController taskController = character.getTaskController();
		taskController.taskRun(taskId, triggerType, data);
	}
}
