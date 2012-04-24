package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.response.FactionNameCheckMsg51070;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 51069)
public class CheckFactionNameProcess51069 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		String factionName=request.getString();
        int msg= character.getMyFactionManager().checkFactionName(factionName);
        if(msg!=0){
        	character.sendMsg(new FactionNameCheckMsg51070(factionName, msg));
        	return ;
        }
    	character.sendMsg(new FactionNameCheckMsg51070(factionName));
	}

}
