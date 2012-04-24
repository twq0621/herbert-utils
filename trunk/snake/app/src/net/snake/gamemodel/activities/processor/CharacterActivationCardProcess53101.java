package net.snake.gamemodel.activities.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.activities.bean.ActivationCard;
import net.snake.gamemodel.activities.response.ActivationCardResponse53102;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 获得兑换码信息
 */
@MsgCodeAnn(msgcode = 53101)
public class CharacterActivationCardProcess53101 extends CharacterMsgProcessor
implements IThreadProcessor{
	
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		String cardNo = request.getString();
		//验证空字串
		if (cardNo == null || cardNo.length() <= 0) {
			// 提示卡号不合法
			character.sendMsg(new PrompMsg(1181));
			return;
		}
		
		ActivationCard activationCard = 
			GameServer.activationCardManager.findActivationCardByCardNo(cardNo);
		if (activationCard == null) {
			// 提示卡号不合法
			character.sendMsg(new PrompMsg(1181));
			return;
		}
		//验证是否被领取
		if (GameServer.characterActivationCardManager.isExchangeForCard(
				character.getOriginalSid(), activationCard)) {
			// 已经被领取
			character.sendMsg(new PrompMsg(1182));
			return;
		}
		
		//返回正确显示信息
		character.sendMsg(new ActivationCardResponse53102(activationCard, character));
	}

}
