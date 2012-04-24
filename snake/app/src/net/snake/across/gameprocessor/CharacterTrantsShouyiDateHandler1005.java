package net.snake.across.gameprocessor;

import net.snake.AcrossServer;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.persistence.CharacterAcrossDateCenterManager;
import net.snake.gamemodel.across.response.LingquShouyiSucessMsg1010;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;
import org.apache.log4j.Logger;
/**
 * 跨服服务器返回角色收益数据
 */

public class CharacterTrantsShouyiDateHandler1005 extends AuthSTSHandler {

	private static Logger logger = Logger.getLogger(CharacterTrantsShouyiDateHandler1005.class);

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		logger.info("开始执行消息号：" + message.getFunction());
		ByteArrayReader out = message.getContentReader();
		int serverId = out.readInt();
		int oldInitiallycharacterId = out.readInt();
		CharacterAcross characterAcross = CharacterAcrossDateCenterManager.getInstance().lingquShouyi(out);
		if (characterAcross != null) {
			AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(characterAcross.getAcrossServerId());
			CtsConnectSessionMananger.getInstance().sendMsgToServer(asd.getLoginServerIp(), AcrossServer.acrossPort,
					new LingquShouyiSucessMsg1010(serverId, oldInitiallycharacterId));
		} else {
			session.close(true);
		}
	}

}
