package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseGrade;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseGradeDataProvider;
import net.snake.gamemodel.pet.response.GetNeidanInfoResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 获得灵宠生产内丹的信息 60025
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60025)
public class GetNeidanInfoProcessor extends CharacterMsgProcessor {
	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		int horseid = request.getInt();
		// 灵宠ID(int)，状态（1展示,2收起）,生产内丹(id),剩余时间秒为单位(int)
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseid);
		if (horse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		sendNeidanInfo(character, horse);
	}
	
	public static void sendNeidanInfo(Hero character,Horse horse){
		CharacterHorse characterHorse = horse.getCharacterHorse();
		long time = 0;
		long nowtime = System.currentTimeMillis();
		if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
			time = characterHorse.getNeidanCdtime() - characterHorse.getNeidanUsetime();
		} else {
			time = characterHorse.getNeidanCdtime() - (nowtime - characterHorse.getNeidanStarttime());
		}
		if (time < 0) {
			time = 0;
		}
		int exp = characterHorse.getExperience();
		HorseGrade grade = HorseGradeDataProvider.getInstance().getHorseGradeById(characterHorse.getGrade());
		int nextexp = grade.getLevelExperience();
		int yutime = (int) (time / 1000);
		GetNeidanInfoResponse response = new GetNeidanInfoResponse(characterHorse.getId(), characterHorse.getStatus(), horse.getHorseModel().getChangeModelId(), yutime, exp, nextexp);
		character.sendMsg(response);
	}

}
