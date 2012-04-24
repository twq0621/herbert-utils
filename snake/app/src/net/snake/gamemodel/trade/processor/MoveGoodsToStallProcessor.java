package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodsContainerType;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.IGirdContainer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13001)
public class MoveGoodsToStallProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short index = request.getShort();
		int copper = request.getInt();
		int ingot = request.getInt();
		short topostion = request.getShort();
		short num = request.getShort();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		if (copper < 0 || ingot < 0 || copper > MaxLimit.BAG_COPPER_MAX || ingot > MaxLimit.INGOT_MAX) {
			return;
		}

		CharacterGoodController goodcontroller = character.getCharacterGoodController();
		IGirdContainer container = goodcontroller.getStallGoodsContainer();
		CharacterGoods goods = goodcontroller.getGoodsByPositon(index);

		if (goods == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 754));
			return;
		}
		if (num > goods.getCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 921));
			return;
		}

		if (copper <= 0 && ingot <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 955));
			return;
		}
		if (copper < 0 || ingot < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 694));
			return;
		}

		if (topostion == 0) {
			// 自动查找位置
			topostion = container.findFirstIdleGirdPosition();
			if (topostion == 0) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 956));
				return;
			}
		}
		goods.setStallCopper(copper);
		goods.setStallIngot(ingot);
		int total = goods.getCount();
		goods.setCount((int) num);
		goodcontroller.movePosition(goods.getPosition(), 0, GoodsContainerType.onStall, topostion, 0);
		if (total != num) {
			CharacterGoods cg = (CharacterGoods) goods.clone();
			cg.setCount(total - num);
			character.getCharacterGoodController().getBagGoodsContiner().addGoods(cg);
		}
	}
}
