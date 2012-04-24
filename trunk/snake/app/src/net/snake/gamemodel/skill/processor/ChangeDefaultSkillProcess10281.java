package net.snake.gamemodel.skill.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 改变坐骑默认技能
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10281, accessLimit = 200)
public class ChangeDefaultSkillProcess10281 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		
	}
}
