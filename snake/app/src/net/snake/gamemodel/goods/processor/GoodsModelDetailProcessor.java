package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.EquipmentDetailMsg50102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.response.GoodDetailFailMsg50132;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;


/**
 * 50131
 * @author serv_dev
 *
 */

@MsgCodeAnn(msgcode = 50131)
public class GoodsModelDetailProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		int goodmodelid=request.getInt();
		short postion=request.getShort();
		CharacterGoods characterGoods = CharacterGoods.createCharacterGoods(1, goodmodelid, 0);
		
		if (characterGoods!=null) {
			if (characterGoods.isDynamic()) {
				characterGoods.setPosition(postion);
				characterGoods.equipmentUpdate();
				character.sendMsg(new EquipmentDetailMsg50102(characterGoods));
			} else {
				character.sendMsg(new GoodDetailFailMsg50132(0,postion,goodmodelid,0));
			}
		} else {
			character.sendMsg(new GoodDetailFailMsg50132(0,postion,goodmodelid,0));
		}
	}
}
