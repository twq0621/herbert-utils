package net.snake.gamemodel.equipment.processor.gem;



import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.gem.GemActivityTypeExchangeMsg50164;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;
/**
 */
@MsgCodeAnn(msgcode = 50165)
public class GemActivityTypeExchangeProcess50165 extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(GemActivityTypeExchangeProcess50165.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(53)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1085));
			return;
		}

		// 包裹位置(short),物品modelid（int）,想要的宝石类型（byte） 类型：1红 2黄 3蓝 4绿 5黑 6白
		short position = request.getShort();
		int shourcemodel = request.getInt();
		int want = request.getByte();
		if (want < 1 || want > 6) {
			return;
		}
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(shourcemodel);
		CharacterGoods goods = character.getCharacterGoodController().getGoodsByPositon(position);
		short newposition = dealExchange(character, goods, goodmodel, want);
		if (newposition >= 0) {
			sendSuccess(character, newposition);
		} else {
			sendFaild(character);
		}
	}

	private short dealExchange(Hero character, CharacterGoods goods, Goodmodel goodmodel, int wantmodel) {
		CharacterGoodController cgc = character.getCharacterGoodController();
		if (goods != null && goods.getGoodModel().equals(goodmodel) && goodmodel.isGemStone()) {
			int want = getWantModel(goodmodel, wantmodel);
			if (goodmodel.getGrade() != 6 && goodmodel.getGrade() != 5) {
				// 级别非法
				character.sendMsg(new PrompMsg(50047));
				return -1;
			}
			// if (!cgc.isEnoughGoods(want, 1)) {
			// character.sendMsg(new PrompMsg(50046));
			// // 包裹空位不足
			// return -1;
			// }
			if (goodmodel.getGrade() == 5) {
				if (!cgc.deleteGoodsFromBag(GoodItemId.XINGYUNJING_ID, 30)) {
					character.sendMsg(new PrompMsg(50048));
					// 删除幸运晶失败
					return -1;
				}
			}
			String stroneAddproperty = goods.getStroneAddproperty();
			if (cgc.getBagGoodsContiner().deleteSplitGoods(goods.getPosition(), 1)) {
				CharacterGoods good = CharacterGoods.createCharacterGood(want, 1, goods.getBind());
				good.setStroneAddproperty(stroneAddproperty);
				if (cgc.addGoodsToBag(good)) {
					CharacterGoods newgood = cgc.getBagGoodsContiner().getFirstGoodsByModelid(good.getGoodmodelId());
					if (newgood != null) {
						return newgood.getPosition();
					}
				} else {
					character.sendMsg(new PrompMsg(50006));
					if (logger.isDebugEnabled()) {
						logger.debug("由于包裹满而消失了一个" + goodmodel.getNameI18n());
					}

					// 添加包裹失败
				}
			}
			return -1;
		}
		// 物品法合
		return -1;
	}

	private int getWantModel(Goodmodel model, int want) {
		return 6000 + model.getGrade() * 100 + want;
	}

	private void sendSuccess(Hero character, short position) {
		character.sendMsg(new GemActivityTypeExchangeMsg50164(CommonUseNumber.byte1, position));
	}

	private void sendFaild(Hero character) {
		character.sendMsg(new GemActivityTypeExchangeMsg50164(CommonUseNumber.byte0, (short) -1));
	}
}
