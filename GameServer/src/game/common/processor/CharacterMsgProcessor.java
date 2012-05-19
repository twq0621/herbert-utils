package game.common.processor;

import game.entity.Hero;
import lion.core.GamePlayer;
import lion.message.MyRequestMsg;
import lion.processor.MsgProcessor;
import lion.shell.Options;

public abstract class CharacterMsgProcessor extends MsgProcessor {
	@Override
	public void process(GamePlayer session, MyRequestMsg request) throws Exception {
		Hero character = session.getCurrentCharacter(Hero.class);
		if (character == null) {
			return;
		}
		int factionNum = request.getMsgCode();
		if (factionNum != Options.Msg_Heart) {
			return;
		}
		process(character, request);
	}

	public abstract void process(Hero hero, MyRequestMsg request) throws Exception;

}
