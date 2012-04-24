package net.snake.gamemodel.chest.processor;

/**
 * 
 * 

 * 请求宝箱友好度
 */
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.gamemodel.chest.response.ChestResponse60118;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 60117)
public class ChestExchangeProcess60117 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		ChestCount chestCount = character.getMyChestManager().getChestCount();
		character.sendMsg(new ChestResponse60118(chestCount));

	}

}
