package net.snake.gamemodel.heroext.lianti.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * "请求指定角色当前炼体级别 （不一定是主角的，防止以后策划加可以查看他人的）"
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 53001, accessLimit = 100)
public class QueryLiantiInfo53001 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int characterid = request.getInt();
		Hero othercharacter = GameServer.vlineServerManager.getCharacterById(characterid);
		if (othercharacter != null) {
			// character.sendMsg(new CharacterLianTiInfoResponse53002(othercharacter));
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60019));
		}
	}

}
