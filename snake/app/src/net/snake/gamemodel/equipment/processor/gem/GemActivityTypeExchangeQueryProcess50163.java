package net.snake.gamemodel.equipment.processor.gem;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.gem.GemActivityTypeExchangeQueryMsg50162;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 */
@MsgCodeAnn(msgcode = 50163)
public class GemActivityTypeExchangeQueryProcess50163 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		if(!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(53)){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,1085));
			return;
		}
		short pos = request.getShort();
		int moduleid = request.getInt();
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(moduleid);
		CharacterGoods goods= character.getCharacterGoodController().getGoodsByPositon(pos);
		if(checkGemExchange(character, goods, goodmodel)){
			//可以
			character.sendMsg(new GemActivityTypeExchangeQueryMsg50162(CommonUseNumber.byte1,goodmodel.getGrade().byteValue()));
		}else{
			//不可以
			character.sendMsg(new GemActivityTypeExchangeQueryMsg50162(CommonUseNumber.byte0,goodmodel.getGrade().byteValue()));
		}
	}
	
	public static boolean checkGemExchange(Hero character,CharacterGoods goods,Goodmodel goodmodel){
		if(goods!=null&&goods.getGoodModel().equals(goodmodel)&&goodmodel.isGemStone()){
			if(goodmodel.getGrade()==6){
				//可以兑换
				return true;
			}
			if(goodmodel.getGrade()==5){
				if(checkXinYunJinNum(30, character)){
					return true;
				}else{
					//幸运晶不足
					return false;
				}
			}
		}
		//物品法合
		return false;
	}

	private static boolean checkXinYunJinNum(int i, Hero character) {
		int bagsum = character.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.XINGYUNJING_ID);
		if(bagsum<i){
			return false;
		}
		return true;		
	}
}
