package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.response.PersonalsResponse52206;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.netio.message.RequestMsg;

/**
 * 交友显示
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52205)
public class PersonalsProcessor52205 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// character 人物角色
		int characterid = request.getInt();
		Personals personals = null;
		Hero character2 = character.getVlineserver().getOnlineManager().getByID(characterid);
		if (null == character2) {// 930
			PersonalsResponse52206 response52206 = new PersonalsResponse52206(personals, 0, CommonUseNumber.byte0);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 930));
			character.sendMsg(response52206);
			return;
			// 完全保密 60011
		} else if (character2.getMyPersonalsManager().getPersonals().getBaomichengdu() == 2) {
			PersonalsResponse52206 response52206 = new PersonalsResponse52206(personals, 0, CommonUseNumber.byte0);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60011));
			character.sendMsg(response52206);
			return;
		}
		personals = character2.getMyPersonalsManager().getPersonals();
		PersonalsResponse52206 response52206 = new PersonalsResponse52206(personals, characterid,CommonUseNumber.byte1);
		character.sendMsg(response52206);

	}

}
