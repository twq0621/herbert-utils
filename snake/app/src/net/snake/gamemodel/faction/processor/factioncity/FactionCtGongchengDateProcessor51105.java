package net.snake.gamemodel.faction.processor.factioncity;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 请求攻城日程查询
 *
 */
@MsgCodeAnn(msgcode = 51105)
public class FactionCtGongchengDateProcessor51105 extends CharacterMsgProcessor implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
           character.getMyFactionCityZhengDuoManager().sendGongchengInfo();
	}

}
