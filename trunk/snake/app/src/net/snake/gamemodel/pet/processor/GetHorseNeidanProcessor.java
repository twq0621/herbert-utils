package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.GetHorseNeidanResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 收获灵宠产的内丹
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60027)
public class GetHorseNeidanProcessor extends CharacterMsgProcessor {
	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		int horseid = request.getInt();
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseid);
		if (horse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		CharacterHorse characterHorse = horse.getCharacterHorse();
		long nowtime = System.currentTimeMillis();
		long starttime = characterHorse.getNeidanStarttime();
		if (starttime == 0) {
			characterHorse.setNeidanStarttime(System.currentTimeMillis());
			character.sendMsg(new GetHorseNeidanResponse(horse, CommonUseNumber.byte0));
			return;
		}
		long usetime = 0;
		if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
			usetime = characterHorse.getNeidanUsetime();
		} else {
			usetime = nowtime - starttime;
		}
		if (usetime < characterHorse.getNeidanCdtime()) {
			character.sendMsg(new GetHorseNeidanResponse(horse, CommonUseNumber.byte0));
			return;
		}
		short pos = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		if (pos == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}
		CharacterGoods neidangoods = CharacterGoods.createCharacterGoods(1, horse.getHorseModel().getChangeModelId(), 0);
		neidangoods.setPosition(pos);
		neidangoods.setIsHomemade(CommonUseNumber.byte1);
		character.getCharacterGoodController().addGoodsToBag(neidangoods);
		characterHorse.setNeidanCdtime(horse.getHorseModel().getNeidanCdtime() * 1000);
		characterHorse.setNeidanUsetime(0);
		characterHorse.setNeidanStarttime(nowtime);
		horse.speedUpNeiDan();
		character.sendMsg(new GetHorseNeidanResponse(horse, CommonUseNumber.byte1));
	}
}
