package net.snake.gamemodel.heroext.zhenqi.processor;



import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;

/**
 * 角色提取真气娃娃的真气
 * 
 * 
 */
@MsgCodeAnn(msgcode = 51401,accessLimit=500)
public class GuShuJIngHunChongqiwawaToCharPros51401 extends
		CharacterMsgProcessor {
private static Logger logger = Logger
		.getLogger(GuShuJIngHunChongqiwawaToCharPros51401.class);
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ)
			return;
		short position = request.getShort();
		CharacterGoodController characterGoodController = character
				.getCharacterGoodController();
		CharacterGoods characterGoods = characterGoodController
				.getGoodsByPositon(position);
		if (characterGoods == null) {
			logger.warn(
					"process(Character, RequestMsg) - no this good "+ character.getId()); //$NON-NLS-1$
			return;
		}

		if (characterGoods.getGoodmodelId() != GoodItemId.chongqiwawa_full) {
//			logger.warn(
//					"process(Character, RequestMsg) - 物品{}不是充气娃娃(满)", characterGoods.getGoodmodelId()); //$NON-NLS-1$
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 842));
			return;
		}

		if (characterGoods.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 839));
			return;
		}

		if (characterGoods.getChongqiOwnerId().intValue() == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 843));
			return;
		}

		if (character.getZhenqi() == MaxLimit.ZHENQI_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 844));
			return;
		}

		if (!characterGoodController.isEnoughGoods(GoodItemId.GSJH_S, 1)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567,
					GoodmodelManager.getInstance().get(GoodItemId.GSJH_S)
							.getNameI18n()));
			return;
		}

		try {
			if (!characterGoodController.getBagGoodsContiner()
					.deleteSplitGoods(position, 1)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 847));
				return;
			}
			characterGoodController.deleteGoodsFromBag(GoodItemId.GSJH_S, 1);
			CharacterPropertyManager.changeZhenqi(character,
					MaxLimit.ZHENQI_MAX);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20167,
					1 + ""));
			// character.sendMsg(new ChongqiwawaToCharMsg50708());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
