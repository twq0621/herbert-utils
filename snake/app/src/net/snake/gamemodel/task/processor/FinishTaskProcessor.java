package net.snake.gamemodel.task.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.logic.SystemPublishTask;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 完成任务处理
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10263, accessLimit = 500)
public class FinishTaskProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int taskId = request.getInt();// 任务id
		short choosegoodsPos = request.getShort();// 用户选择的 任务提交物品位置下标
		int horseId = request.getInt();// 用户选择的 提交坐骑
		SystemPublishTask.finishTask(taskId, choosegoodsPos, horseId, character);
	}

}
