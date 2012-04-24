package net.snake.gamemodel.equipment.processor.born;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.born.BornExtraStrengthenMsg50172;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 天生属性强化
 */
@MsgCodeAnn(msgcode = 50171, accessLimit = 200)
public class BornExtraStrengthenProcessor50171 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		short pos = request.getShort();
		int model = request.getInt();

		CharacterGoods equipment = character.getCombineController().equipmentCondition(model, pos);
		if (equipment == null) {
			BornExtraStrengthenMsg50172 msg = new BornExtraStrengthenMsg50172();
			character.sendMsg(msg);
			return;
		}
		if (!character.getCombineController().bornExtraStrengthen(equipment)) {
			character.sendMsg(new BornExtraStrengthenMsg50172());
		}
	}
}
