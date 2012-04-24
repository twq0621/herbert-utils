package net.snake.gamemodel.recharge.processor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.CertificationUtil;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;
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
@MsgCodeAnn(msgcode = 111)
public class NewRechargeCall extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {
	private final static Logger logger = Logger.getLogger(NewRechargeCall.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		/**
		 * 充值服务器和游戏服务器在同一台物理机上，只允许通过127.0.0.1访问充值接口
		 */
		String ip = session.getIoSession().getRemoteAddress().toString();
		logger.info("ip=" + ip);
		String sid = request.getString();
		String userid = request.getString();
		long orderId = request.getLong();
		int yuanbao = request.getInt();
		String sig = request.getString();
		String nowSig = CertificationUtil.md5(sid + userid + orderId + yuanbao + GameConstant.RECHARGE_SECRET_KEY);
		if (!nowSig.equals(sig)) {
			logger.error("signature of adding currence has err,ip=" + ip + ",sid=" + sid + ",userid=" + userid + ",orderId=" + orderId + ",yuanbao=" + yuanbao + ",sig=" + sig + ",nowsig=" + nowSig);
			return;
		}
		Account account = AccountManager.getInstance().selectByYunyingId(userid, sid);
		Integer id = account.getId();
		try {
			this.updateAccountYuanBao(id, yuanbao);
		} catch (Exception e) {
			logger.error("save adding current with err .",e);
			session.sendMsg(SimpleResponse.byteStatusMsg(0, 0));
			return;
		}
		Hero character = GameServer.vlineServerManager.getCharacterByAccountId(id);
		if (character != null) {
			character.getAccount().getAccountMonneyManager().updateAccountChongZhiYuanbao(character, yuanbao, account);
			character.getTaskController().checkAllCurTaskYuanBao();
		}
		session.sendMsg(SimpleResponse.byteStatusMsg(0, 1));
	}

	private void updateAccountYuanBao(int account, int addyuanbao) throws SQLException {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("gameaccountid", account);
		param.put("add", addyuanbao);
		SystemFactory.getAccountSqlMapClient().update("t_account.updateAccountYuanBao", param);
	}
}
