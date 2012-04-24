package net.snake.gamemodel.recharge.processor;

import net.snake.GameServer;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

import org.apache.log4j.Logger;

/**
 * 充值
 * 
 */
// @MsgCodeAnn(msgcode = 111)
public class RechargeCall extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {
	private final static Logger logger = Logger.getLogger(RechargeCall.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		/**
		 * 充值服务器和游戏服务器在同一台物理机上，只允许通过127.0.0.1访问充值接口
		 */
		String ip = session.getIoSession().getRemoteAddress().toString();
		if (ip.indexOf("127.0.0.1") != -1) {
			int account = request.getInt();
			int nowyuanbao = request.getInt();
			Hero character = GameServer.vlineServerManager.getCharacterByAccountId(account);
			if (character != null) {
				Account dbAccount = AccountManager.getInstance().selectByAccountid(account);
				character.getAccount().getAccountMonneyManager().updateAccountChongZhiYuanbao(character, nowyuanbao, dbAccount);
				character.getTaskController().checkAllCurTaskYuanBao();
			}
		} else {
			logger.error("access interface of recharge with err----error ip=" + ip);
		}
		session.sendMsg(SimpleResponse.onlyMsgCodeMsg(0));
	}

}
