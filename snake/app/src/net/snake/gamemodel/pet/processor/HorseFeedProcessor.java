package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodModelKind;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseGrade;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseGradeDataProvider;
import net.snake.gamemodel.pet.response.HorseFeedResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 灵宠喂养 c-->s 60031 请求 灵宠ID(int),食用内丹位置（short），食用内丹modelid（int），食用数量（short）
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60031)
public class HorseFeedProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		int horseId = request.getInt();
		short pos = request.getShort();
		int neidanId = request.getInt();
		short neidanNum = request.getShort();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (null == characterGoods || characterGoods.getGoodmodelId() != neidanId || neidanNum == 0) {
			return;
		}
		Goodmodel goodmodel = characterGoods.getGoodModel();
		if (goodmodel.getKind() != GoodModelKind.neidan.getKind()) {
			return;
		}
		if (characterGoods.getCount() < neidanNum) {
			return;
		}
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseId);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		CharacterHorse characterHorse = horse.getCharacterHorse();
		if (characterHorse.getGrade() >= character.getGrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60056));
			return;
		}
		int relNum = 0;
		for (int i = 0; i < neidanNum; i++) {
			if (characterHorse.getGrade() >= character.getGrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60056));
				break ;
			}
			horse.horseGainedExp(goodmodel.getDrugValue());
			relNum++;
		}
		character.getCharacterGoodController().deleteCailiaoByPos(characterGoods, relNum);

		HorseGrade grade = HorseGradeDataProvider.getInstance().getHorseGradeById(characterHorse.getGrade());
		character.sendMsg(new HorseFeedResponse(horseId, characterHorse.getExperience(), grade.getLevelExperience()));

	}
}
