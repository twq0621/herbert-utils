package net.snake.gamemodel.gift.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 * 50743 请求打开有礼礼包
 * 
 * 
 */
@MsgCodeAnn(msgcode = 50743,accessLimit=1000)
public class GiftLoginOpenProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int giftId=request.getInt();
		character.getMyCharacterGiftpacksManger().getRoleLoginGiftPacksManger().openMeetGiftPacks(giftId);
	}

}
