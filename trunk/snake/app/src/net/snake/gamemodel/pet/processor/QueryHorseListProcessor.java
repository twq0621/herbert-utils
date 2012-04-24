package net.snake.gamemodel.pet.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.HorseContainerType;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.gamemodel.pet.response.HorseListResponse60018;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 返回客户端请求灵宠列表信息 消息号是60001
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 60017, accessLimit = 500)
public class QueryHorseListProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int characterid = request.getInt();
		int container = 2;
		Hero other = GameServer.vlineServerManager.getCharacterById(characterid);

		if (other != null) {
			HorseContainerType horsecontainertype = HorseContainerType.getByValue(container);
			if (horsecontainertype == null) {
				return;
			}
			HorseContainer horsecontainer = other.getCharacterHorseController().getHorseContainer(horsecontainertype);
			if (horsecontainer != null) {
				character.sendMsg(new HorseListResponse60018(horsecontainer));
			}
		}

	}

}
