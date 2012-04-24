package net.snake.gamemodel.account.processor;

import java.sql.SQLException;
import java.util.Date;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.NetTool;
import net.snake.consts.SessionKey;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.bean.AuthData;
import net.snake.gamemodel.account.logic.AccountFactory;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.account.response.LoginFail10012;
import net.snake.gamemodel.account.response.LoginSuccess10012;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.line.bean.ServerEntry;
import net.snake.netio.GameServerContext;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

import com.qq.open.CheckUtils;

/**
 * 登录服务器登录过程
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10011, accessLimit = 30000)
public class LoginProcessorV2 extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {

	private static Logger logger = Logger.getLogger(LoginProcessorV2.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		// 这是第三方跨服服务器，不能这样登录
		if (Options.IsCrossServ) {
			session.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 服务器人数，超过了额定上限
		ServerEntry serverentry = GameServer.serverlistmanager.getServerEntryByID(Options.ServerId);
		if (GameServer.vlineServerManager.getOnlineCount() > serverentry.getMaxpermitplayercount()) {
			session.sendMsg(new LoginFail10012(40016));
			return;
		}
		String openid = request.getString();
		String sid = request.getString();
		long uid = (long) request.getDouble();
		String hash = request.getString();
		logger.info("openid=" + openid + ",uid=" + uid + ",sid=" + sid + ",hash=" + hash);
		if ("baishe".equals(sid)) {
			// 如果不满足运营授权公式，则使用内部帐号登陆
			login4Dev(session, openid, hash);
		} else {
			checkYYAccount(session, sid, openid, hash, uid);
		}
	}

	private void checkYYAccount(GamePlayer session, String sid, String openid, String hash, long uid) throws Exception {
		final AuthData authData = new AuthData(sid, openid, hash, uid);
		authData.setIp(session.getAddress());
		session.setAttribute("auth", authData);
		// 从登录平台获取授权到现在登录游戏，中间的时间差是否达到阀值。
		boolean islogin = CheckUtils.checkHash(uid, hash);
		logger.info("islogin=" + islogin);
		if (islogin == false) {
			session.sendMsg(new LoginFail10012(40017));
			return;
		}
		AccountManager accountManager = AccountManager.getInstance();
		// 合服可能会产生多个帐号,所以通过会话中要求的服务器ID取帐号
		Account account = accountManager.selectByYunyingId(openid, sid);
		logger.info("account" + account);
		if (account == null) {// 新帐号
			account = AccountFactory.createAccount(authData);
			accountManager.insert(account);
		} else {
			//account.setIsLimit(authData.isIndulge() ? CommonUseNumber.byte1 : 0);
		}
		session.setAttribute(SessionKey.sid, authData.getSid());
		processAccount(session, account);
	}

	private void login4Dev(GamePlayer session, String openid, String openkey) throws Exception {
		AccountManager am = AccountManager.getInstance();
		Account account = am.getByLoginname(openid);
		if (account == null) {
			logger.warn("no account");
			session.sendMsg(new LoginFail10012(40018));
			return;
		}
		// 比较密码
		String pwd = account.getPassword();
		if (!pwd.equals(openkey)) {
			session.sendMsg(new LoginFail10012(40019));
			return;
		}
		// 内部测试帐号登陆，服务器ID为0（应该是代理商站点上的第几组服务器）
		session.setAttribute(SessionKey.sid, "0");
		processAccount(session, account);

	}

	private void processAccount(GamePlayer session, Account account) throws SQLException {
		GamePlayer oldgamePlayer = GameServerContext.getPlayer(account.getId());// 登陆服务器保存在线玩家
		// 如果重复登录
		if (oldgamePlayer != null) {
			logger.info("not, chong fu login");
			NetTool.sendAndClose(oldgamePlayer, new LoginFail10012(40020), 5000);
		}
		// 将此账户的本次的登录信息更新到数据库
		Date date = new Date();
		account.setLastloginDate(date);
		account.setLastloginIp(session.getAddress());
		account.setLoginTime(account.getLoginTime() + 1);
		AccountManager.getInstance().updateAccount(account);

		// 应用会话和帐号通过验证，进入登录服务器成功
		session.setLastTime(System.currentTimeMillis());
		GameServerContext.putPlayer(account.getId(), session);
		session.setValidate(true);
		session.setAccountid(account.getId());
		// 登录成功的响应告诉客户端
		// String param = account.getId() + "_" + account.getIsLimit() + "_" + System.currentTimeMillis();
		// String auth = CertificationUtil.encodeBase64(param);
		// String sign = CertificationUtil.md5(auth + AuthConfigManager.getInstance().getMd5Key());
		LoginSuccess10012 loginsuccess = new LoginSuccess10012();
		loginsuccess.setData(account);
		session.sendMsg(loginsuccess);
	}
}
