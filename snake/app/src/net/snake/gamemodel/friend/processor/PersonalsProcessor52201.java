package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.friend.response.PersonalsResponse52202;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.netio.message.RequestMsg;

/**
 * 婚姻 查看自己资料
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52201)
public class PersonalsProcessor52201 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		Personals personals = character.getMyPersonalsManager().getPersonals();
		PersonalsResponse52202 response52202 = new PersonalsResponse52202(personals);
		character.sendMsg(response52202);
	}
}
