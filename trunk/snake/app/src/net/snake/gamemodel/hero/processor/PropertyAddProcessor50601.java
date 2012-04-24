package net.snake.gamemodel.hero.processor;

import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.PropertyAddMsg50602;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 50601)
public class PropertyAddProcessor50601 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int attackAdd = request.getInt();
		int defenceAdd = request.getInt();
		int qingshenAdd = request.getInt();
		int jiantiAdd = request.getInt();

		if (attackAdd < 0 || defenceAdd < 0 || qingshenAdd < 0 || jiantiAdd < 0) {
			return;
		}

		int potential = character.getPotential();
		if (attackAdd > potential || defenceAdd > potential || qingshenAdd > potential || jiantiAdd > potential) {
			return;
		}

		if (attackAdd + defenceAdd + qingshenAdd + jiantiAdd == 0) {
			character.sendMsg(new PropertyAddMsg50602(0, 20060));
			return;
		}
		if (CharacterFormula.extraPropertyAdd(attackAdd, defenceAdd, qingshenAdd, jiantiAdd, character)) {
			character.sendMsg(new PropertyAddMsg50602(1, 0));
		} else {
			character.sendMsg(new PropertyAddMsg50602(0, 20061));
		}
	}
}
