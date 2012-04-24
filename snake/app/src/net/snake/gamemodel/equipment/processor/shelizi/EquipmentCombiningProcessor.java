package net.snake.gamemodel.equipment.processor.shelizi;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 装备合成 50117
 * 
 */
@MsgCodeAnn(msgcode = 50117, accessLimit = 100)
public class EquipmentCombiningProcessor extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		// if (Options.IsCrossServ) return;
		// short equipPosition = request.getShort();
		// int equipModel = request.getInt();
		// final byte zhenxingfu = request.getByte();
		// final byte zhenghunfu = request.getByte();
		// final byte xinyunNum = request.getByte();
		// final CharacterGoods equipment =
		// character.getCharacterGoodController().getGoodsByPositon(equipPosition);
		//
		// if (equipment == null) {
		// character.sendMsg(new CombineFailMsg50150(0));
		// return;
		// }
		//
		// if (equipModel != equipment.getGoodmodelId()) {
		// logger.warn("数据错误goodmodelid:{} position:{}",new
		// Object[]{equipModel,equipPosition});
		// return;
		// }
		//
		// if (!equipment.getGoodModel().isEquipment()) {
		// logger.warn("参数错误，第一个物品不是装备");
		// character.sendMsg(new CombineFailMsg50150(0));
		// return;
		// }
		//
		// if(equipPosition < Position.BagGoodsBeginPostion || equipPosition >
		// Position.BagGoodsEndPostion) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20076));
		// character.sendMsg(new CreateNextEquipmentMsg50118(0));
		// return;
		// }
		//
		// character.getCombineController().createNextCharacterGoods(equipment,
		// zhenxingfu, zhenghunfu, xinyunNum);
		//
	}
}
