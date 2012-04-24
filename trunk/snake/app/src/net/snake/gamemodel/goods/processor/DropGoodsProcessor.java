package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 11181, accessLimit = 500)
public class DropGoodsProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short postion = request.getShort();
		int horseid = request.getInt();// 实际处理中,不存在直接销毁身上物品的情况
		if (Options.IsCrossServ) {
			if (postion >= Position.BagGoodsBeginPostion && postion < Position.BagGoodsBeginPostion + Position.BAG_PAGE_CAPACITY) {
				// 允许包裹内删除发生
			} else {
				// 不允许非包裹物品的删除
				return;
			}
		}
		CharacterGoods goods = character.getCharacterGoodController().getGoodsByPositon(postion);
		if (goods == null) {
			return;
		}
		if (goods != null && goods.getGoodModel().getDiscard() == false) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 874));
			return;
		}
		if (goods.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 875));
			return;
		}
		// 装备不能销毁
		if (goods.getGoodModel().getKind() == 2) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60065));
			return;
		}
		character.getCharacterGoodController().deleteCharacterGoods(postion, horseid);
	}

}
