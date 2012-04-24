package net.snake.gamemodel.gift.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 领取每级升级礼包	礼包ID(int)
 * @author jack
 *
 */
@MsgCodeAnn(msgcode = 60201)
public class GiftEachLevelProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int giftId  = request.getInt();
		character.getMyCharacterGiftpacksManger().getRoleEachLevelGiftPacksManager().getGradeGiftPacks(giftId);
	}

}
