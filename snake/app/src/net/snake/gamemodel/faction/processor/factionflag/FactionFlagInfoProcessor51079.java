package net.snake.gamemodel.faction.processor.factionflag;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.response.FactionFlagInfoMsg51080;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 51079)
public class FactionFlagInfoProcessor51079 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
        if(!character.getMyFactionManager().isFaction()){
        character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,859)); 
              return;
        }
        character.sendMsg(new FactionFlagInfoMsg51080(character.getMyFactionManager().getFactionController().getFaction(), character));
	}

}
