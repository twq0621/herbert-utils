package net.snake.gamemodel.equipment.processor.shelizi;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 */
@MsgCodeAnn(msgcode = 52105, accessLimit = 500)
public class SLZTakeOutProcess52105 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// if (Options.IsCrossServ)
		// return;
		// // 护身符ID(int),护身符位置(short),拔除id（byte）
		// // 成功失败标示(byte 0失败1成功)
		// int amuletid = request.getInt();
		// short amuletPosition = request.getShort();
		// byte index = request.getByte();
		// CharacterGoods amulet = character.getCharacterGoodController().getGoodsByPositon(amuletPosition);
		// if (amuletPosition < Position.BagGoodsBeginPostion || amuletPosition > Position.BagGoodsEndPostion) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
		// return;
		// }
		// boolean slzTakeOut = character.getCombineController().slzTakeOut(amulet, index);
		// character.sendMsg(new SLZTakeOutMsg52106(slzTakeOut));
	}

}
