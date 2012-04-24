package net.snake.gamemodel.chest.processor;

/**
 * 
 * 宝箱抽奖
 */
import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.response.ChestResponse60104;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 60101)
public class ChestLotteryProcess60101 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 查看配包里边有没有宝箱
		int typeid = request.getInt();// 宝箱类别
		CharacterGoods cGoods = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(typeid);
		if (null != cGoods && cGoods.getGoodModel().getKind() != 15) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, typeid));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 834));
			return;
		}
		List<Chest> chestList = character.getMyChestManager().getChestlist();
		List<Chest> chestList_New = new ArrayList<Chest>(chestList.size());
		for (Chest chest : chestList) {
			if (chest.getGoodmodelType() == typeid && chest.getTurn() == 0) {
				chest.setTurn(CommonUseNumber.byte1);
				if (chest.getId() != null) {
					character.getMyChestManager().updateChest(chest);
				}
			}
			chestList_New.add(chest);
		}
		character.sendMsg(new ChestResponse60104(CommonUseNumber.byte1, typeid));

	}

}
