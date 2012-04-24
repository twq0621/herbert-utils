package net.snake.gamemodel.chat.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.bean.AuthData;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.chat.response.LoginMsg30102;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

import org.apache.log4j.Logger;

import com.qq.open.CheckUtils;

/**
 * 登入验证操作
 * 
 * 
 */
@MsgCodeAnn(msgcode = 30101, accessLimit = 200)
public class ChatLoginVProcessor extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {
	private static Logger logger = Logger.getLogger(ChatLoginVProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		// 分线标识(string),角色ID（int）,角色名（String）,auth(String),sign(String)
		byte subserver = request.getByte();
		int characterId = request.getInt();
		String characterName = request.getString();// 得到角色的名称
		int gameaccount = request.getInt();// 游戏帐号
		String openid = request.getString();
		String sid = request.getString();
		long uid = (long)request.getDouble();
		String hash = request.getString();
		// 验证用户的有效性
		Hero role = GameServer.vlineServerManager.getCharacterById(characterId);
		if (role == null) {
			logger.info("验证失败,无角色数据,subserver=" + subserver + ",characterName=" + characterName);
			session.sendMsg(new LoginMsg30102(LoginMsg30102.ERR));
			return;
		}
		AuthData authData = new AuthData(sid, openid, hash, uid);
		session.setAttribute("auth", authData);
		if (!"baishe".equals(sid)) {
			boolean islogin = CheckUtils.checkHash(uid, hash);
			if (islogin == false) {
				logger.warn("auth chat session fail,because yanzhengma");
				session.sendMsg(new LoginMsg30102(LoginMsg30102.ERR));
				return;
			}
		} else {
			AccountManager am = AccountManager.getInstance();
			Account account = am.getByLoginname(openid);
			if (account == null) {
				logger.warn("no account");
				session.sendMsg(new LoginMsg30102(LoginMsg30102.ERR));
				return;
			}
			// 比较密码
			String pwd = account.getPassword();
			if (!pwd.equals(hash)) {
				session.sendMsg(new LoginMsg30102(LoginMsg30102.ERR));
				return;
			}
		}
		// 己通过游戏端口登录
		if (role.getAccountId() != gameaccount) {
			logger.warn("auth chat session fail because outtime");
			session.sendMsg(new LoginMsg30102(LoginMsg30102.ERR));
			return;
		}
		session.setValidate(true);
		session.setSessionType(GamePlayer.Session_Type_Chat);
		// if (logger.isDebugEnabled()) {
		// logger.debug("聊天会话验证通过！");
		// }

		session.setAccountid(gameaccount);
		role.getChatManager().Initail(session);
		session.setCurrentCharacter(role);
		session.sendMsg(new LoginMsg30102(LoginMsg30102.OK));
	}
}
