package net.snake.gamemodel.bulletin.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.bulletin.response.NewBulletinResponse50796;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 新闻公告
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 50795)
public class NewsBulletinProcess50795 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		String bulletin = GameServer.configParamManger.getConfigParam().getBulletin();
		character.sendMsg(new NewBulletinResponse50796(bulletin));
	}
}
