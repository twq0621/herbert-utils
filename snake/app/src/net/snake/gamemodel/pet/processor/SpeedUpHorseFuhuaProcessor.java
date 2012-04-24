package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodModelKind;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 加速灵宠孵化的速度
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60013)
public class SpeedUpHorseFuhuaProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short pos = request.getShort();
		int modelid = request.getInt();
		if (character.getFuhuaNeidanId() == -1) {
			return;
		}
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (characterGoods == null || characterGoods.getGoodmodelId() != modelid) {
			return;
		}
		if (characterGoods.getGoodModel().getKind() != GoodModelKind.SpeedUp.getKind()) {
			return;
		}
		long fuhuaCdtime = character.getFuhuaCdtime();
		int speedupVal = characterGoods.getGoodModel().getDrugValue() * 1000;
		fuhuaCdtime = fuhuaCdtime - speedupVal;
		if (fuhuaCdtime < 0) {
			fuhuaCdtime = 0;
		}
		character.setFuhuaCdtime(fuhuaCdtime);
		if (character.getIsfuhua() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60052));
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60051));
		}
		character.getCharacterGoodController().deleteCailiaoByPos(characterGoods, 1);
		if (fuhuaCdtime == 0) {
			character.fuhua(System.currentTimeMillis());
		}
		GetFuhuaInfoProcessor.processFuhuaInfo(character);
	}
}
