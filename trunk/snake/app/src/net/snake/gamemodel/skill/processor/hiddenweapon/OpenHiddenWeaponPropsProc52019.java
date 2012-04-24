package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 * 打开暗器隐藏属性
 */
@MsgCodeAnn(msgcode = 52019,accessLimit=100)
public class OpenHiddenWeaponPropsProc52019 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		byte open = request.getByte();
		character.getCharacterHiddenWeaponController().openHiddenProps(open);
	}
}
