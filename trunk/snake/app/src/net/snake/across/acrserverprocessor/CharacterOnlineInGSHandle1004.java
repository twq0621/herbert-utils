/**
 * 
 */
package net.snake.across.acrserverprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.GameServer;
import net.snake.across.msg.CharacteLingquShouyiResult1005;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Position;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;

import datatransport.bean.acrossincome.AcrossIncomeTransportData;
import datatransport.bean.acrossincome.AcrossIncomeTransportDataDAO;
import datatransport.bean.charactergoods.GoodsTransportData;
import datatransport.bean.charactergoods.GoodsTransportDataDAO;
import org.apache.log4j.Logger;
/**
 * 角色上线在游戏服务器中通知跨服返回角色收益
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class CharacterOnlineInGSHandle1004 extends AuthSTSHandler {
	private static Logger logger = Logger.getLogger(CharacterOnlineInGSHandle1004.class);

	@SuppressWarnings("unchecked")
	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (!Options.IsCrossServ) {
			return;
		}
		logger.info("开始执行消息号：" + message.getFunction());
		ByteArrayReader out = message.getContentReader();
		int serverId = out.readInt();
		int oldInitiallyAccountId = out.readInt(); // 原始服帐号id
		int oldInitiallycharacterId = out.readInt();// 原始 角色id
		int newcharacterId = out.readInt();
		AcrossEtc ae = AcrossEtcManager.getInstance().getAcrossEtcByOldAccountInitiallyId(serverId, oldInitiallyAccountId);
		if (ae == null) {
			ae = AcrossEtcManager.getInstance().getAcrossEtcByOldCharacterInitiallyId(serverId, oldInitiallycharacterId);
		}
		if (ae == null) {
			session.write(new CharacteLingquShouyiResult1005(serverId, oldInitiallycharacterId, newcharacterId, null, null));
		} else {
			int roleId = ae.getCharacterId();
			ae.setGuoshiFlag(CommonUseNumber.byte0);
			AcrossEtcManager.getInstance().updateToDb(ae);
			Hero character = GameServer.vlineServerManager.getCharacterById(roleId);
			if (character != null) {
				character.getDownLineManager().saveToDb();
				character.getPlayer().logout();
			}
			GoodsTransportDataDAO goodDao = new GoodsTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			Map<String, Integer> queryMap = new HashMap<String, Integer>();
			queryMap.put("characterId", roleId);
			queryMap.put("beginPostion", Position.BagGoodsBeginPostion - 0);
			queryMap.put("endPostion", Position.BagGoodsBeginPostion + 30);
			List<GoodsTransportData> goodList = goodDao.selectByPostion(queryMap);
			AcrossIncomeTransportDataDAO incomeDao = new AcrossIncomeTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			AcrossIncomeTransportData shouyi = incomeDao.selectByPrimaryKey(roleId);
			session.write(new CharacteLingquShouyiResult1005(serverId, oldInitiallycharacterId, newcharacterId, shouyi, goodList));
		}
	}

}
