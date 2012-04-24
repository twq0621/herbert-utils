package net.snake.gamemodel.equipment.processor.shelizi;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 舍利子镶嵌
 */
@MsgCodeAnn(msgcode = 52103, accessLimit = 500)
public class SLZInlayProcess52103 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// if (Options.IsCrossServ)
		// return;
		// // 护身符ID(int),护身符位置(short),舍利子ID(int),舍子利位置(short)
		// int amuletId = request.getInt();
		// short amuletPosition = request.getShort();
		// int slzId = request.getInt();
		// short slzPosition = request.getShort();
		// CharacterGoods amulet = character.getCharacterGoodController().getGoodsByPositon(amuletPosition);
		// if (amuletPosition < Position.BagGoodsBeginPostion || amuletPosition > Position.BagGoodsEndPostion) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
		// return;
		// }
		// CharacterGoods slz = character.getCharacterGoodController().getGoodsByPositon(slzPosition);
		// boolean result = character.getCombineController().slzCombiningAmulet(amulet, slz);
		// character.sendMsg(new SLZInlayMsg52104(result));
	}

}
