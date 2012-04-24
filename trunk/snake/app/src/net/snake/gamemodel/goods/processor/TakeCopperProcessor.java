package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 从仓库里取出铜币
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 11195, accessLimit = 2000)
public class TakeCopperProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int deposit = request.getInt();
		if (deposit < 0 || deposit > character.getStorageCopper()) {
			// 操作失败
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 880));
			return;
		}
		if (character.getCopper() + deposit > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 881));
			return;
		}
		//
		CharacterPropertyManager.changeStorageCopper(character, -deposit);
		CharacterPropertyManager.changeCopper(character, deposit, CopperAction.TAKEFORSTORAGE);
	}

}
