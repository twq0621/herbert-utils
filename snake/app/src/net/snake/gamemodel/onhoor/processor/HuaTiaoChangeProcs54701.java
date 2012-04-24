package net.snake.gamemodel.onhoor.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.onhoor.response.AfkConfigMsg50592;
import net.snake.gamemodel.onhoor.response.SaveAfkConfigResponse50582;
import net.snake.netio.message.RequestMsg;

/**
 * 滑调改变54701
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 54701, accessLimit = 500)
public class HuaTiaoChangeProcs54701 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int hpP = request.getByte();
		int mpP = request.getByte();
		int spP = request.getByte();
		if (hpP < 0) {
			hpP = 0;
		} else if (hpP > 100) {
			hpP = 100;
		}
		if (mpP < 0) {
			mpP = 0;
		} else if (mpP > 100) {
			mpP = 100;
		}
		if (spP < 0) {
			spP = 0;
		} else if (spP > 100) {
			spP = 100;
		}
		CharacterOnHoorController characterOnHoorController = character.getCharacterOnHoorController();
		CharacterOnHoorConfig characterOnHoorConfig = characterOnHoorController.getCharacterOnHoorConfig();
		characterOnHoorConfig.setRevertHp(hpP);
		characterOnHoorConfig.setRevertMp(mpP);
		characterOnHoorConfig.setRevertSp(spP);
		characterOnHoorController.saveOnHoorConfig();
		character.sendMsg(new AfkConfigMsg50592(characterOnHoorConfig, character.getCharacterOnHoorController()));
		character.sendMsg(new SaveAfkConfigResponse50582(1));
	}
}
