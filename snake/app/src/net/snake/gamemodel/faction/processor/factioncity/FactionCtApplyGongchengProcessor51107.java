package net.snake.gamemodel.faction.processor.factioncity;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 攻城申请提交
 *
 */
@MsgCodeAnn(msgcode = 51107)
public class FactionCtApplyGongchengProcessor51107 extends
		CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
             character.getMyFactionCityZhengDuoManager().tijiaoGongchengInfo();
	}

}
