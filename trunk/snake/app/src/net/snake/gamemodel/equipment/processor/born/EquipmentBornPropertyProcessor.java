package net.snake.gamemodel.equipment.processor.born;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.born.EquipmentBornPropertyResponse;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

/**
 * 51303 得到当前装备的天生属性 位置(short),模型id(int)
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 51303, accessLimit = 100)
public class EquipmentBornPropertyProcessor extends CharacterMsgProcessor {

	private static Logger logger = Logger.getLogger(EquipmentBornPropertyProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 位置(short),模型id(int)
		short position = request.getShort();
		int modelId = request.getInt();

		final CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);

		if (characterGoods == null) {
			character.sendMsg(new CombineFailMsg50150(0));
			return;
		}

		if (modelId != characterGoods.getGoodmodelId()) {
			logger.warn("data err .goodmodelid:"+modelId+" position:"+position);
			return;
		}
		EquipmentBornPropertyResponse response = new EquipmentBornPropertyResponse();
		response.setMsg(characterGoods);
		character.sendMsg(response);
	}

}
