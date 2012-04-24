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

/**
 * 将铜币放到仓库里
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 11193, accessLimit = 2000)
public class DepositCopperProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int deposit = request.getInt();
		if (deposit < 0 || deposit > character.getCopper()) {
			// 操作失败
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 872));
			return;
		}
		if (character.getStorageCopper() + deposit > MaxLimit.STORAGE_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 873));
			return;
		}
		//
		CharacterPropertyManager.changeCopper(character, -deposit, CopperAction.DEPOSIT);
		CharacterPropertyManager.changeStorageCopper(character, deposit);

	}

}
