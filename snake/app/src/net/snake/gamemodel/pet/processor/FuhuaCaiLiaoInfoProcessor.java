package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.response.FuhuaCaiLiaoInfoResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 内丹孵化灵宠消耗 c-->s 60029 请求 内丹道具位置（short），内丹道具modelid（int）
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60029)
public class FuhuaCaiLiaoInfoProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		short pos = request.getShort();
		int modelid = request.getInt();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(pos);
		if(characterGoods==null || characterGoods.getGoodmodelId()!=modelid){
			return;
		}
		if (characterGoods.getIsHomemade() == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60047));
			character.sendMsg(new FuhuaCaiLiaoInfoResponse());
			return;
		}
		Goodmodel goodmodel = characterGoods.getGoodModel();
		int horseModelId=goodmodel.getChangeModelId();
		int copper = goodmodel.getCopper();
		int zhenqi = goodmodel.getZhenqi();
		int gailv = goodmodel.getProbability()/100;
		character.sendMsg(new FuhuaCaiLiaoInfoResponse(horseModelId, copper, zhenqi, gailv));
	}

}
