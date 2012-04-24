package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodModelKind;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.message.RequestMsg;

/**
 * 加速内丹产生的速度
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60009)
public class SpeedUpHorseNeidanProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int horseid = request.getInt();
		short pos = request.getShort();
		int modelid = request.getInt();

		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (characterGoods == null || characterGoods.getGoodmodelId() != modelid) {
			return;
		}
		if (characterGoods.getGoodModel().getKind() != GoodModelKind.SpeedUp.getKind()) {
			return;
		}
		Horse horse = character.getCharacterHorseController().getHorseById(horseid);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		CharacterHorse characterHorse = horse.getCharacterHorse();
		long neidanCdtime = characterHorse.getNeidanCdtime();
		int speedupVal = characterGoods.getGoodModel().getDrugValue() * 1000;
		neidanCdtime = neidanCdtime - speedupVal;
		if (neidanCdtime < 0) {
			neidanCdtime = 0;
		}
		character.getCharacterGoodController().deleteCailiaoByPos(characterGoods, 1);
		characterHorse.setNeidanCdtime(neidanCdtime);
		GetNeidanInfoProcessor.sendNeidanInfo(character, horse);
	}
}
