package net.snake.gamemodel.equipment.processor.gem;



import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;
@MsgCodeAnn(msgcode = 50173,accessLimit=100)
public class GemActivityExchangeProcess50173 extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(GemActivityExchangeProcess50173.class);
	private static int NEEDCOUNT = 10;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int modelid = request.getInt();
		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(36)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1085));
			return;
		}
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(modelid);
		CharacterGoodController goodmanage = character.getCharacterGoodController();
		EquipmentPlayconfig config = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(goodmodel.getId());

		if (goodmodel.getKind() == 6 && goodmodel.getGrade() == 5) {
			int count = goodmanage.getBagGoodsCountByModelID(modelid);
			if (count >= NEEDCOUNT) {
				if (goodmanage.deleteGoodsFromBag(modelid, NEEDCOUNT)) {
					// 增加新的宝石一个
					CharacterGoods createCharacterGoods = CharacterGoods.createCharacterGoods(1, config.getNextGoodmodelId(), 0);
					goodmanage.addGoodsToBag(createCharacterGoods);
				} else {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 684));// 删除物品失败
				}
			} else {
				// 提示数量不足
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567, goodmodel.getNameI18n()));// 物品数量不足
			}
		} else {
			logger.warn("good type is err");
		}
	}

}
