package net.snake.across.gameprocessor;

import net.snake.GameServer;
import net.snake.commons.CertificationUtil;
import net.snake.commons.NetTool;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.persistence.CharacterAcrossDateCenterManager;
import net.snake.gamemodel.across.response.AcrossToken10116;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;
import org.apache.log4j.Logger;
/**
 * 跨服服务器返回角色传输是否成功 成功发送传送消息
 */

public class CharacterTrantsResultHandler1003 extends AuthSTSHandler {

	private static Logger logger = Logger.getLogger(CharacterTrantsResultHandler1003.class);

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		logger.info("开始执行消息号：" + message.getFunction());
		ByteArrayReader read = message.getContentReader();
		int roleId = read.readInt();
		int acrossId = read.readInt();
		int type = read.readByte();
		logger.info("收到消息1003 type:" + type);
		if (type > 0) {
			@SuppressWarnings("static-access")
			Hero character = GameServer.getInstance().vlineServerManager.getCharacterById(roleId);
			if (character == null) {
				return;
			}
			if (CharacterAcrossDateCenterManager.getInstance().isInitAcrossShouyiToAcross(character.getId())) {
				return;
			}
			if (!character.getAcrossLock().isAcrossLock()) {
				return;
			}
			AcrossServerDate as = AcrossServerDateManager.getInstance().getAcrossServerDateById(acrossId);
			if (as == null) {
				character.getAcrossLock().clearCharacterLockAction(character);
				return;
			}
			character.getMyCharacterAcrossIncomeManager().addAcrossFlagClearBendiShouyi(as);

			int newcharacterId = read.readInt();
			int newaccountId = read.readInt();
			final String param = newaccountId + "_" + character.getAntiAddictedSystem().getIsLimitToByte() + "_" + System.currentTimeMillis();
			final String auth = CertificationUtil.encodeBase64(param);
			final String sign = CertificationUtil.md5(auth + as.getAcrossMd5key());
			character.getMyFactionManager().bangzhuAcrossNoticOther(as);
			NetTool.sendAndClose(character.getPlayer(), new AcrossToken10116(auth, sign, as, newcharacterId, newaccountId), 6000);
		} else {
			Hero character = GameServer.vlineServerManager.getCharacterById(roleId);
			if (character == null) {
				return;
			}
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1327));
			character.getAcrossLock().clearCharacterLockAction(character);
		}
		session.close(true);
	}

}
