package net.snake.gamemodel.heroext.zhenqi.processor;



import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.heroext.zhenqi.response.ChongqiwawaToCharMsg50708;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;

/**
 * 角色提取真气娃娃的真气
 *
 */
@MsgCodeAnn(msgcode = 50707,accessLimit=500)
public class ChongqiwawaToCharPros50707 extends CharacterMsgProcessor {
private static Logger logger = Logger
		.getLogger(ChongqiwawaToCharPros50707.class);
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		short position = request.getShort();
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		CharacterGoods characterGoods = characterGoodController.getGoodsByPositon(position);
		if (characterGoods == null) {
			logger.warn("process(Character, RequestMsg) - no this good "+character.getId()); //$NON-NLS-1$
			return;
		}
		
		if (characterGoods.getGoodmodelId() != GoodItemId.chongqiwawa_full) {
//			logger.warn("process(Character, RequestMsg) - 物品{}不是充气娃娃(满)",characterGoods.getGoodmodelId()); //$NON-NLS-1$
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,842));
			return;
		}
		
		if (characterGoods.isInTrade()) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,839));
			return;
		}
		
		
//		if (characterGoods.getChongqiOwnerId() == null || characterGoods.getChongqiOwnerId() == 0 || characterGoods.getChongqiOwnerName() == null  || "".equals(characterGoods.getChongqiOwnerName())) {
//			character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_RIGHT,
//			"您必须要有充气娃娃（满）才可以提取真气，您可通过向其他玩家购买或交换获得"));
//			return;
//		}
		
		if (LinshiActivityManager.getInstance().isTimeByLinshiActivityID(9)) {
			if (character.getChongqiRecord() >= 5) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20075,5));
				return;
			}
		} else {
			Goodmodel gm = GoodmodelManager.getInstance().get(GoodItemId.chongqiwawa_full);
			if (character.getChongqiRecord() >= gm.getChongqiMax()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20075,gm.getChongqiMax()));
				return;
			}
		}
		
		if (characterGoods.getChongqiOwnerId().intValue() == character.getId()) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,843));
			return;
		}
		
		if (character.getZhenqi() == MaxLimit.ZHENQI_MAX) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,844));
			return;
		}
		
		int needGSJH = 1;
		
		if (needGSJH > 1) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,845));
			return;
		}
		
		if (!characterGoodController.isEnoughGoods(GoodItemId.GSJH, needGSJH)) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,846));
			return;
		}
		
		
		try{
			if (!characterGoodController.getBagGoodsContiner().deleteSplitGoods(position, 1)){
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,847));
				return;
			}
			characterGoodController.deleteGoodsFromBag(GoodItemId.GSJH, needGSJH);
			character.setChongqiRecord(character.getChongqiRecord() + 1);
			CharacterPropertyManager.changeZhenqi(character, MaxLimit.ZHENQI_MAX);
			//
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,1023,needGSJH+""));
			character.sendMsg(new ChongqiwawaToCharMsg50708());
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
