package net.snake.gamemodel.goods.processor;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.response.BagCountMsg11190;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 增加包裹或仓库的空间
 * 
 * 
 */
@MsgCodeAnn(msgcode = 11189, accessLimit = 500)
public class AddBagCapacityProcessor extends CharacterMsgProcessor {
	private static final Logger logger = Logger.getLogger(AddBagCapacityProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int bagcount = character.getMaxBagAmount() / MaxLimit.BagCapacity;
		if (bagcount >= 5) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 866));
			return;
		}
		int needkuozhanfu = getNeedKuozhanfu(bagcount);
		if (needkuozhanfu == -1) {
			return;
		}
		CharacterGoodController goodscontroller = character.getCharacterGoodController();

		if (goodscontroller.getBagGoodsCountByModelID(GoodItemId.GOODS_KUOZHANFU) < needkuozhanfu) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 867));
			return;
		}
		// 删除
		boolean b = goodscontroller.deleteGoodsFromBag(GoodItemId.GOODS_KUOZHANFU, needkuozhanfu);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 868));
			return;
		}
		// 扩展
		character.setMaxBagAmount((short) (character.getMaxBagAmount() + MaxLimit.BagCapacity));
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					CharacterManager.getInstance().update(character);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		IBag container = goodscontroller.getBagGoodsContiner();
		container.setTotalCapacity(character.getMaxBagAmount());
		// 发消息
		character.sendMsg(new BagCountMsg11190(bagcount + 1));

	}

	private int getNeedKuozhanfu(int bagcount) {
		if (bagcount == 1) {
			return 2;
		} else if (bagcount == 2) {
			return 6;
		} else if (bagcount == 3) {
			return 18;
		} else if (bagcount == 4) {
			return 50;
		}
		return -1;
	}

}
