package net.snake.gamemodel.onhoor.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.netio.message.RequestMsg;

/**
 * 开始挂机
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50583, accessLimit = 500)
public class BeginAfkProcessor50583 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte begin = request.getByte();

		if (begin == 1) {
			//
			if (ClientConfig.notAfkMapId.contains(character.getScene())) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20155));
				return;
			}

//			if (character.getMyDazuoManager().isDazuo()) {
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20156));
//				return;
//			}

			if (character.isZeroHp()) {
				return;
			}
			if (character.getEffectController().isImmb() || character.getEffectController().isSleep()) {
				return;
			}
			character.setObjectState(VisibleObjectState.Idle);
			character.getCharacterOnHoorController().setAfkState(OnHoorState.on);
		} else if (begin == 0) {
			character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
		}
	}
}
