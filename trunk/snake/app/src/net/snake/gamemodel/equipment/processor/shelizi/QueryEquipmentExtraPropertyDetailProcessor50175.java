package net.snake.gamemodel.equipment.processor.shelizi;


import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.MengHuanEquipmentMsg50176;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 50175, accessLimit = 200)
public class QueryEquipmentExtraPropertyDetailProcessor50175 extends CharacterMsgProcessor {
//	private static Logger logger = Logger.getLogger(QueryEquipmentExtraPropertyDetailProcessor50175.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short equipPosition = request.getShort();
		int equipModel = request.getInt();
		final CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(equipPosition);
		if (equipment == null) {
			return;
		}
		if (equipModel != equipment.getGoodmodelId()) {
//			logger.warn("数据错误goodmodelid:{} position:{}", new Object[] { equipModel, equipPosition });
			return;
		}
		if (!equipment.getGoodModel().isEquipment()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 850));
			character.sendMsg(new MengHuanEquipmentMsg50176(0, equipPosition, ""));
			return;
		}

		if (equipPosition < Position.BagGoodsBeginPostion || equipPosition > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			character.sendMsg(new MengHuanEquipmentMsg50176(0, equipPosition, ""));
			return;
		}

		if (equipment.getPingzhi() != 6) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20148));
			character.sendMsg(new MengHuanEquipmentMsg50176(0, equipPosition, ""));
			return;
		}

		character.sendMsg(new MengHuanEquipmentMsg50176(1, equipPosition, equipment.getAdditionAttributeStr()));
	}

}
