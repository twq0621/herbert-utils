package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 52011,accessLimit=500)
public class HiddenWeaponUpMastryProc52011 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		byte method = request.getByte();
		if (method == 1) {
			character.getCharacterHiddenWeaponController().useOneStoneUpMastery();
		} else if(method == 2) {
			character.getCharacterHiddenWeaponController().useAllStoneUpMastery();
		}
	}

}
