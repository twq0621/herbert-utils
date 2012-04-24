package net.snake.gamemodel.common.processor;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.shell.Options;

public abstract class CharacterMsgProcessor extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero character = session.getCurrentCharacter(Hero.class);
		if (character == null) {
			return;
		}
		int factionNum = request.getMsgCode();
		if (character.getAcrossLock().isAcrossLock() && factionNum != Options.Msg_Heart) {
			return;
		}
		process(character, request);
	}

	public abstract void process(Hero character, RequestMsg request) throws Exception;

}
