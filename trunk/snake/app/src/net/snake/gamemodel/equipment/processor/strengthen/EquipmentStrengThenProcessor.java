package net.snake.gamemodel.equipment.processor.strengthen;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.strengthen.StrengthenUpgradeMsg50104;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 装备强化
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 50103, accessLimit = 100)
public class EquipmentStrengThenProcessor extends CharacterMsgProcessor {
	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		// 位置(short),模型id(int)，幸运晶个数(byte)
		if (Options.IsCrossServ)
			return;
		short position = request.getShort();
		int modelId = request.getInt();
		final byte xinyunNum = request.getByte();

		CharacterGoods equipment = character.getCombineController().equipmentCondition(modelId, position);
		if (equipment == null) {
			character.sendMsg(new StrengthenUpgradeMsg50104(0));
			return;
		}
		character.getCombineController().strengthenUpgrade(equipment, xinyunNum);
	}
}
