package net.snake.gamemodel.equipment.processor.strengthen;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.extra.AddOneExtraResultMsg50188;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 装备重置附加属性时替换新生成的属性处理
 * 
 */
@MsgCodeAnn(msgcode = 50169, accessLimit = 100)
public class AddOneExtraChoiceProcessor50169 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short pos = request.getShort();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (characterGoods == null) {
			return;
		}

		if ("".equals(characterGoods.getTmpAddOneExtraStr())) {
			return;
		}
		byte method = request.getByte();// 0保留1替换
		if (method == 0) {
			characterGoods.setTmpAddOneExtraStr("");
			character.sendMsg(new AddOneExtraResultMsg50188(1));
			return;
		} else {
			characterGoods.setAdditionDesc(characterGoods.getTmpAddOneExtraStr());
			characterGoods.setTmpAddOneExtraStr("");
			character.getCharacterGoodController().updateCharacterGoods(characterGoods);
			character.sendMsg(new AddOneExtraResultMsg50188(1));
		}
	}

}
