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
import net.snake.gamemodel.heroext.zhenqi.response.CharToMsgChongqiwawa50710;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 角色灌注真气到真气娃娃里
 * 
 * 
 */
@MsgCodeAnn(msgcode = 50701, accessLimit = 500)
public class CharToChongqiwawaPros50701 extends CharacterMsgProcessor {

	private static final Logger logger = Logger.getLogger(CharToChongqiwawaPros50701.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ){
			return;
		}
		short position = request.getShort();
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		CharacterGoods characterGoods = characterGoodController.getGoodsByPositon(position);
		if (characterGoods == null) {
			logger.warn("process(Character, RequestMsg) - no this good"+ character.getViewName()); //$NON-NLS-1$
			return;
		}

		if (!characterGoods.getGoodModel().isChongqiwawa()) {
			logger.warn("process(Character, RequestMsg) - not wawa"); //$NON-NLS-1$
			return;
		}

		if (characterGoods.getGoodmodelId() != GoodItemId.chongqiwawa_kong) {
//			logger.warn("process(Character, RequestMsg) - 物品{}不是充气娃娃(空)", characterGoods.getGoodmodelId()); //$NON-NLS-1$
			return;
		}

		if (characterGoods.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 839));
			return;
		}
		if (characterGoods.getChongqiOwnerId() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 840));
			return;
		}
		if (character.getZhenqi() != MaxLimit.ZHENQI_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 841));
			return;
		}
		CharacterPropertyManager.changeZhenqi(character, -character.getZhenqi());
		characterGoods.setGoodmodelId(GoodItemId.chongqiwawa_full);
		characterGoods.setGoodModel(GoodmodelManager.getInstance().get(GoodItemId.chongqiwawa_full));
		characterGoods.setChongqiOwnerId(character.getId());
		characterGoods.setChongqiOwnerName(character.getViewName());
		characterGoodController.updateCharacterGoods(characterGoods);
		character.sendMsg(new CharToMsgChongqiwawa50710());

	}

}
