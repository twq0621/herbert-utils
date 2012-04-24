package net.snake.gamemodel.goods.processor;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.IGirdContainer;
import net.snake.gamemodel.goods.response.StorageCountMsg11192;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 增加包裹或仓库的空间
 * 
 * 
 */
@MsgCodeAnn(msgcode = 11191,accessLimit=500)
public class AddStorageCapacityProcessor extends CharacterMsgProcessor {
	private static final Logger logger = Logger.getLogger(AddStorageCapacityProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int bagcount = character.getMaxStorageAmount() / MaxLimit.StorageCapacity;
		if (bagcount >= 5) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 869));
			return;
		}
		int needkuozhanfu = getNeedKuozhanfu(bagcount);
		if (needkuozhanfu == -1) {
			return;
		}
		CharacterGoodController goodscontroller = character.getCharacterGoodController();

		if (goodscontroller.getBagGoodsCountByModelID(GoodItemId.GOODS_KUOZHANFU) < needkuozhanfu) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 870));
			return;
		}
		// 删除
		boolean b = goodscontroller.deleteGoodsFromBag(GoodItemId.GOODS_KUOZHANFU, needkuozhanfu);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 871));
			return;
		}
		// 扩展
		character.setMaxStorageAmount((short) (character.getMaxStorageAmount() + MaxLimit.StorageCapacity));
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
		IGirdContainer container = goodscontroller.getStorageGoodsContainer();
		container.setTotalCapacity(character.getMaxStorageAmount());
		// 发消息
		character.sendMsg(new StorageCountMsg11192(bagcount + 1));
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
