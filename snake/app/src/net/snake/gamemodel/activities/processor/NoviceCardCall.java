package net.snake.gamemodel.activities.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.activities.persistence.ActivitiesManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.shell.Options;


/**
 * 添加新手卡道具.
 */
@MsgCodeAnn(msgcode = 113)
public class NoviceCardCall extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		if(Options.IsCrossServ){
			session.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		/**
		 * 充值服务器和游戏服务器在同一台物理机上，只允许通过127.0.0.1访问充值接口
		 */
		if (session.getIoSession().getRemoteAddress().toString().indexOf("127.0.0.1") != -1) {
			int characterid = request.getInt();
			int type = request.getInt();
			Hero character = GameServer.vlineServerManager.getCharacterById(characterid);
			if (character != null) {
				ActivitiesManager.getInstance().addActivitieGoods(character,type);
			}
		}
		session.sendMsg(SimpleResponse.onlyMsgCodeMsg(0));
	}

}
