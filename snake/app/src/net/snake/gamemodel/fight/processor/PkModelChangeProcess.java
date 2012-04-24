package net.snake.gamemodel.fight.processor;

import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.response.PkModelChanageMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * pk模式的切换 10901
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10901, accessLimit = 500)
public class PkModelChangeProcess extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		byte pk = request.getByte();
		CharacterFightController characterFightController = character.getFightController();

		if (character.isZeroHp()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 924));
			character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
			return;
		}

		if (characterFightController.getPkModel() == pk) {// 相同的pk模式
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 925));
			character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
			return;
		}

		if (character.getGrade() < 20) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 926));
			character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
			return;
		}

		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 927));
			character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
			return;
		}

		if (pk == 1) {// 组队模式
			// 2. 在玩家没有队伍时，组队模式为不可用；

			if (!character.getMyTeamManager().isTeam()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 928));
				character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
				return;
			}
		}

		if (pk == 2) {// 帮派模式
			// 2. 在玩家没有队伍时，组队模式为不可用；
			if (!character.getMyFactionManager().isFaction()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 929));
				character.sendMsg(new PkModelChanageMsg(characterFightController.getPkModel()));
				return;
			}
		}

		characterFightController.setPkModel((int) pk);
		PkModelChanageMsg msg = new PkModelChanageMsg(pk);
		character.sendMsg(msg);
	}
}
