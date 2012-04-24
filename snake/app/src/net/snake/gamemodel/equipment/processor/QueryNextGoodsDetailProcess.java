package net.snake.gamemodel.equipment.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.EquipmentDetailMsg50102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 50133
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50133, accessLimit = 500)
public class QueryNextGoodsDetailProcess extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short position = request.getShort();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);
		if (characterGoods == null) {
			return;
		}
		if (!characterGoods.getGoodModel().isEquipment())
			return;

		EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());

		if (equipmentPlayconfig == null) {
			return;
		}
		CharacterGoods nextGoods = (CharacterGoods) characterGoods.clone();
		nextGoods.setGoodmodelId(equipmentPlayconfig.getNextGoodmodelId());
		character.sendMsg(new EquipmentDetailMsg50102(nextGoods));
	}
}
