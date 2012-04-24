package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;


@MsgCodeAnn(msgcode = 52005,accessLimit=200)
public class OpenHiddenWeaponProc52005 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		boolean open = request.getByte() == 1 ? true : false;
		character.getCharacterHiddenWeaponController().openHidden(open);
	}

}
