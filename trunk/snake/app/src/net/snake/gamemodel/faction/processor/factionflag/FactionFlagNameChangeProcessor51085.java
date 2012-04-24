package net.snake.gamemodel.faction.processor.factionflag;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 帮旗改名
 *
 */
@MsgCodeAnn(msgcode = 51085)
public class FactionFlagNameChangeProcessor51085 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
            String bangqiName=request.getString();
            character.getMyFactionManager().changBangqiName(bangqiName);
	}

}
