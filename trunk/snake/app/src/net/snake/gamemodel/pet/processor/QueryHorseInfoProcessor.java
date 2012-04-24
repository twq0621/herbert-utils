package net.snake.gamemodel.pet.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.HorseInfoResponse60006;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 查询灵宠的详细信息 60005
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 60005, accessLimit = 100)
public class QueryHorseInfoProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int characterid = request.getInt();
		int horseId = request.getInt();
		Hero other = GameServer.vlineServerManager.getCharacterById(characterid);
		if (other != null) {
			Horse horse = other.getCharacterHorseController().getHorseById(horseId);
			if (horse == null) {
				return;
			}
			character.sendMsg(new HorseInfoResponse60006(other, horse));
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 890));
		}

	}
}
