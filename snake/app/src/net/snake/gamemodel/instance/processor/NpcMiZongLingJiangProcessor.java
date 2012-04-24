package net.snake.gamemodel.instance.processor;

import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.commons.script.SInstance;

/**
 * 16层刷新领奖NPC 52059
 * 
 * @author serv_dev.
 * @version: 1.0
 * @Create at: 2011-4-16 上午07:15:46
 */
@MsgCodeAnn(msgcode = 52059)
public class NpcMiZongLingJiangProcessor extends CharacterMsgProcessor {

	/**
	 * 给予经验 =人物等级*人物等级 *人物等级 * 10. 新增NPC功能选项：“领取迷踪阵通关经验奖励”
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		SInstance sInstance = character.getSceneRef().getInstance();
		if (sInstance == null) {
			return;
		}
		if (sInstance.getInstanceId() != 6) { // 如果玩家在 迷踪阵副本
			return;
		}
		if (character.getSceneRef().getId() != 20191) { // 并且玩家在最后一层
			return;
		}
		if (sInstance.getAttribute("MiZongLingJiang_" + character.getName()) == null) {
			CharacterFormula.experienceProcess(character, character.getGrade() * character.getGrade() * character.getGrade() * 10);
			sInstance.setAttribute("MiZongLingJiang_" + character.getName(), true);
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60014));
		}
	}
}
