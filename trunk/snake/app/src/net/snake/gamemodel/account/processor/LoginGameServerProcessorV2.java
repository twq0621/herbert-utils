package net.snake.gamemodel.account.processor;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.NetTool;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.account.response.LoginGameServerSuccess10106;
import net.snake.gamemodel.account.response.RepeatLoginMsg10030;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.activities.persistence.ActivitiesManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gm.persistence.GmInfoManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 请求进入分线服务器
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 10105, accessLimit = 2000)
public class LoginGameServerProcessorV2 extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {
	private static Logger logger = Logger.getLogger(LoginGameServerProcessorV2.class);

	@Override
	public void process(final GamePlayer session, RequestMsg request) throws Exception {
		int gameaccount = request.getInt();// 游戏帐号
		final int characterid = request.getInt();
		logger.info("request enter game ,account id=" + gameaccount + " ,hero id=" + characterid);
		int lineid = request.getByte();
		// 初始化会话所属的分线服务器
		VLineServer vlineserver = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
		if (vlineserver != null) {
			session.setAttribute("VLineServer", vlineserver);
		}
		if (Options.IsCrossServ) {
			AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(lineid);
			if (!asd.isTimeExpression()) {
				session.sendMsg(SimpleResponse.failReasonMsg(10106, 1359));
				return;
			}
		}
		if (!Options.IsCrossServ) {
			// 添加运营GM标识
			Account account = AccountManager.getInstance().selectByAccountid(gameaccount);
			if (account != null) {
				GmInfoManager.getInstance().validateAndOptionGM(account);
			}
		}
		// 登陆的过程
		processLogin(session, gameaccount, characterid, vlineserver);
	}

	private void processLogin(final GamePlayer session, final int gameaccount, final int characterid, VLineServer vlineserver) throws SQLException {
		if (!GameServer.loginstatsuManager.addAccountID(gameaccount)) {
			session.close();
			return;
		}
		/*
		 * / 先查是否处于切线状态 // Character oldcharacter = // GameServer.changeLineManager.removeChangeLineCharacter(characterid); // if (oldcharacter != null) { //
		 * vlineserver.addTaskInFrameUpdateThread(new // UseoldCharacterTask(oldcharacter, session, false)); // return; // }
		 */
		// 再查是否存在于其他分线
		Hero oldcharacter = GameServer.vlineServerManager.getCharacterByAccountId(gameaccount);
		// 有角色正在被使用
		if (oldcharacter != null) {
			// 不再维护这个角色的缓存
			GameServer.vlineServerManager.removeCharacterById(oldcharacter.getId());
			// 如果正在使用的角色和登录的角色相同，将旧角色挂到新的应用会话上
			if (oldcharacter.getId() == characterid) {
				vlineserver.addTaskInFrameUpdateThread(new UseoldCharacterTask(oldcharacter, session, true));
				return;
			}
			// 如果正在使用的角色和登录的角色不同相同，通知之前的用户
			closeOldPlayerAndSendRepeatLoginMsg(oldcharacter);
			oldcharacter.getDownLineManager().downLine(new Runnable() {
				@Override
				public void run() {
					try {
						initNewCharacter(gameaccount, session, characterid);
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
		} else {
			// 服务器上不存在该在线角色,重新建立角色登陆
			initNewCharacter(gameaccount, session, characterid);
		}
	}

	private static void closeOldPlayerAndSendRepeatLoginMsg(Hero character) {
		final GamePlayer oldgameplayer = character.getPlayer();
		if (oldgameplayer != null) {
			oldgameplayer.removeIOSessionMap();
			oldgameplayer.sendMsg(new RepeatLoginMsg10030());
			oldgameplayer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ALERT, 904));
			GameServer.executorService.schedule(new Runnable() {
				@Override
				public void run() {
					oldgameplayer.close();
				}
			}, 5, TimeUnit.SECONDS);
		}
	}

	private void initNewCharacter(int gameaccount, final GamePlayer session, final int charaId) throws SQLException {
		GameServer.loginstatsuManager.removeAccountID(gameaccount);
		Account account = AccountManager.getInstance().selectByAccountid(gameaccount);
		if (!account.isAbleAccount()) {
			NetTool.sendAndClose(session, SimpleResponse.failReasonMsg(10106, 40015), 5000);
			return;
		}
		if (Options.IsCrossServ) {
			AcrossEtc ace = AcrossEtcManager.getInstance().getAcorssByCharacterId(charaId);
			if (ace == null || ace.isGongshiFlag()) {
				NetTool.sendAndClose(session, SimpleResponse.failReasonMsg(10106, 1300), 5000);
				return;
			}
		}
		Hero ncharacter = CharacterManager.getInstance().getCharacterById(charaId);
		if (ncharacter.getAccountId() != gameaccount) {
			logger.warn("there is no hero for this id " + charaId);
			return;
		}
		if (ncharacter.getIscloseCharacter() == 1) {
			NetTool.sendAndClose(session, SimpleResponse.failReasonMsg(10106, 40014), 5000);
			return;
		}
		VLineServer vlineserver = (VLineServer) session.getAttribute("VLineServer");
		ncharacter.setVlineserver(vlineserver);
		ncharacter.setAccount(account);
		ncharacter.initCharacterData();
		logger.info("init hero data by db is finish ,hero name is " + ncharacter.getViewName());
		ncharacter.getAccount().setOnline(1);
		AccountManager.getInstance().markOnline(ncharacter.getAccount());
		session.setAttribute("ACCOUNT", account);
		ncharacter.setPlayer(session);
		session.setCurrentCharacter(ncharacter);
		vlineserver.getOnlineManager().addCharacter(ncharacter);
		session.setValidate(true);
		ncharacter.setWedfeast(false);
		if (WedFeastManager.getInstance().isFeastNotEnd(charaId)) {
			ncharacter.setWedfeast(true);
		}
		// 角色上线初始化跨服收益
		ncharacter.getMyCharacterAcrossIncomeManager().initOnline();
		session.sendMsg(new LoginGameServerSuccess10106());
		if (Options.FresherCard_Check) {
			ActivitiesManager.getInstance().addActivitiesGoods(ncharacter);
		}
		// 发送日志
		GameServer.dataLogManager.logLogin(ncharacter.getLogUid(), ncharacter.getHeroInfo());
	}

	public static class UseoldCharacterTask implements Runnable {
		private Hero character;
		private GamePlayer session;
		boolean sendclose;

		public UseoldCharacterTask(Hero character, GamePlayer session, boolean sendclose) {
			this.character = character;
			this.session = session;
			this.sendclose = sendclose;
		}

		@Override
		public void run() {
			if (character.getSceneRef() != null) {
				character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
				// 使用旧的角色的话，先让它离开场景，以免发特殊的消息给它
				character.getSceneRef().leaveScene(character, null);
				character.setSceneRef(null);
				character.getMyTeamManager().leaveTeam();
				character.getMyTeamManager().setMyTeam(null);
			}
			final GamePlayer oldgameplayer = character.getPlayer();
			if (oldgameplayer != null) {
				if (sendclose) {
					closeOldPlayerAndSendRepeatLoginMsg(character);
					character.getChatManager().downLine();
				} else {
					oldgameplayer.removeIOSessionMap();
					oldgameplayer.close();
				}
			}
			// end清除可能存在的旧会话
			character.setPlayer(session);
			VLineServer vlineserver = (VLineServer) session.getAttribute("VLineServer");
			character.setVlineserver(vlineserver);
			session.setCurrentCharacter(character);
			vlineserver.getOnlineManager().addCharacter(character);
			logger.info("use memery data login " + character.getId() + " name:" + character.getViewName());
			session.setValidate(true);
			if (sendclose) {
				character.setFristEnterMap(true);
			}
			session.sendMsg(new LoginGameServerSuccess10106());
			GameServer.loginstatsuManager.removeAccountID(character.getAccountId());
		}

	}
}
