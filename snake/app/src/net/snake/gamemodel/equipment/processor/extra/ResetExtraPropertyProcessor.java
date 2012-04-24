package net.snake.gamemodel.equipment.processor.extra;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.extra.ResetExtraPropertyMsg50110;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 装备附加属性重置
 * 
 */
@MsgCodeAnn(msgcode = 50109, accessLimit = 100)
public class ResetExtraPropertyProcessor extends CharacterMsgProcessor {
	// private static Logger logger = Logger.getLogger(ResetExtraPropertyProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		// 位置(short),模型id(int),附加属性数量(byte){编号(byte)}*N
		if (Options.IsCrossServ) {
			return;
		}
		short position = request.getShort();
		int modelId = request.getInt();
		byte attrSize = request.getByte();
		byte attrs[] = new byte[attrSize];
		for (byte i = 0; i < attrSize; i++) {
			attrs[i] = request.getByte();
		}

		final CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);

		if (characterGoods == null) {
			character.sendMsg(new CombineFailMsg50150(0));
			return;
		}

		if (modelId != characterGoods.getGoodmodelId()) {
			// logger.warn("数据错误goodmodelid:{} position:{}", new Object[] { modelId, position });
			return;
		}

		if (position < Position.BagGoodsBeginPostion || position > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			character.sendMsg(new ResetExtraPropertyMsg50110(0));
			return;
		}

		character.getCombineController().resetExtraProperty(characterGoods, attrs);
	}
}
