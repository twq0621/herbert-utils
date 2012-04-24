package net.snake.gamemodel.equipment.processor.extra;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.extra.EquipmentExtraPropertyResponse;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 得到当前装备的附加属性
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 51301, accessLimit = 100)
public class EquipmentExtraPropertyProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 位置(short),模型id(int)
		short position = request.getShort();
		int modelId = request.getInt();

		CharacterGoods equipment = character.getCombineController().equipmentCondition(modelId, position);
		if (equipment == null) {
			return;
		}
		EquipmentExtraPropertyResponse response = new EquipmentExtraPropertyResponse();
		response.setMsg(equipment);
		character.sendMsg(response);
	}

}
