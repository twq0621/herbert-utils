package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.response.AddHorsePostion60004;
import net.snake.gamemodel.pet.response.HorseListResponse60018;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 扩展灵宠槽位
 * @author jack
 *
 */
@MsgCodeAnn(msgcode = 60003, accessLimit = 2000)
public class AddHorseContainerCapacityProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (character.getMaxHorseAmount() < 10) {
			CharacterGoodController goodscontroller = character.getCharacterGoodController();
			int count = character.getMaxHorseAmount() + 1;
			if (goodscontroller.getBagGoodsCountByModelID(GoodItemId.GOODS_KUOZHANFU) < count) {
				// 很抱歉，您背包中的扩展符道具数量不足，坐骑槽位新增失败
				character.sendMsg(new AddHorsePostion60004(0));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40002));
				return;
			}
			boolean b = goodscontroller.deleteGoodsFromBag(GoodItemId.GOODS_KUOZHANFU, count);
			if (!b) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 888));
				return;
			}
			character.setMaxHorseAmount(character.getMaxHorseAmount() + 1);
			character.getCharacterHorseController().getBagHorseContainer().setCapacity(character.getMaxHorseAmount());
			character.sendMsg(new AddHorsePostion60004(1));
			// 客户端坐骑列表更新
			character.sendMsg(new HorseListResponse60018(character.getCharacterHorseController().getBagHorseContainer()));
		} else {
			character.sendMsg(new AddHorsePostion60004(0));
			// 很抱歉，已经达到坐骑槽位上限,新增槽位失败
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40003));
		}

	}

}
