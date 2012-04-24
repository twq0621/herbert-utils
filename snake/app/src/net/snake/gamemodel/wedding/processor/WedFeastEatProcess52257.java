package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.logic.SceneFeastMonster;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastEatResponse52258;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 *@author serv_dev
 */
@MsgCodeAnn(msgcode = 52257,accessLimit=100)
public class WedFeastEatProcess52257 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int monsterid = request.getInt();
		SceneFeastMonster feastMonster = character.getVlineserver().getWedFeastManagerVline().getFeastMonsterById(monsterid);
		if(feastMonster==null){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50038));
			character.sendMsg(new WedFeastEatResponse52258(1));
			return ;
		}
		if(feastMonster.eatFeast(character)){
			character.sendMsg(new WedFeastEatResponse52258(0));
		}else{
			character.sendMsg(new WedFeastEatResponse52258(1));
		}
	}
}

