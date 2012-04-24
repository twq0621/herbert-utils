/**
 * 
 */
package net.snake.across.acrserverprocessor;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;

import datatransport.bean.account.AccountTransportDataDAO;
import datatransport.bean.acrossincome.AcrossIncomeTransportDataDAO;
import datatransport.bean.channel.channelzhenlong.ChannelZhenlongTransportDataDAO;
import datatransport.bean.character.CharacterTransportDataDAO;
import datatransport.bean.characterbow.BowTransportDataDAO;
import datatransport.bean.charactergoods.GoodsTransportDataDAO;
import datatransport.bean.characterhiddenweapon.HiddenWeaponTransportDataDAO;
import datatransport.bean.characterhorse.CharacterHorseTransportDataDAO;
import datatransport.bean.characterlianti.CharacterLiantiTransportDataDAO;
import datatransport.bean.characteronhoor.CharacterOnHoorConfigTransportDataDAO;
import datatransport.bean.characterskilldata.CharacterSkillTransportDataDAO;
import datatransport.bean.characterteamfighting.CharacterTeamfightingTransportDataDAO;
import datatransport.bean.dantian.CharacterDanTianTransportDataDAO;
import org.apache.log4j.Logger;
/**
 * 收到源服务器发来的收益同步成功消息 删除跨服服务器上的角色全部信息
 */

public class CharacterShouyiLingquSucessHandle1010 extends AuthSTSHandler {

	private static Logger logger = Logger.getLogger(CharacterShouyiLingquSucessHandle1010.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.snake.commons.msgprocess.AuthSTSHandler#handleAuthMessage(org.apache
	 * .mina.core.session.IoSession, net.snake.gmtool.net.Msg)
	 */
	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (!Options.IsCrossServ) {
			return;
		}
		logger.info("开始执行消息号：" + message.getFunction());
		ByteArrayReader out = message.getContentReader();
		int oldserverId = out.readInt();
		int oldeCharacterId = out.readInt();
		AcrossEtc ae = AcrossEtcManager.getInstance().removeAcrossEtcByOldCharacterId(oldeCharacterId, oldserverId);

		if (ae != null) {
			int roleId = ae.getCharacterId();
			Hero character = GameServer.vlineServerManager.getCharacterById(roleId);
			if (character != null) {
				character.getPlayer().close();
			}
			deleteRoleDate(ae);
		}
		session.close(true);
	}

	private void deleteRoleDate(AcrossEtc ae) {

		CharacterTransportDataDAO characterDao = new CharacterTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
		try {
			characterDao.deleteByPrimaryKey(ae.getCharacterId());

			AccountTransportDataDAO accountDao = new AccountTransportDataDAO(SystemFactory.getAccountTrantsSqlMapClient());

			accountDao.deleteByPrimaryKey(ae.getAccountId());

			// Character
			// character=CharacterManager.getInstance().getCharacterById(newRoleId);
			GoodsTransportDataDAO goodDao = new GoodsTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());

			goodDao.deleteByCharacterId(ae.getCharacterId());
			CharacterLiantiTransportDataDAO liantiDao = new CharacterLiantiTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			liantiDao.deleteByPrimaryKey(ae.getCharacterId());
			// 练体数据写入
			ChannelZhenlongTransportDataDAO channelDao = new ChannelZhenlongTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			channelDao.deleteByPrimaryKey(ae.getCharacterId());
			CharacterSkillTransportDataDAO skillDao = new CharacterSkillTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());

			skillDao.deleteByCharacterId(ae.getCharacterId());

			CharacterHorseTransportDataDAO horseDao = new CharacterHorseTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			horseDao.deleteByCharacterId(ae.getCharacterId());

			HiddenWeaponTransportDataDAO anqiDao = new HiddenWeaponTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			anqiDao.deleteByCharacterId(ae.getCharacterId());

			CharacterOnHoorConfigTransportDataDAO configDao = new CharacterOnHoorConfigTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			configDao.deleteByPrimaryKey(ae.getCharacterId());

			CharacterTeamfightingTransportDataDAO tfDao = new CharacterTeamfightingTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			tfDao.deleteByCharacterId(ae.getCharacterId());

			BowTransportDataDAO bowDao = new BowTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			bowDao.deleteByPrimaryKey(ae.getCharacterId());
			AcrossIncomeTransportDataDAO incomeDao = new AcrossIncomeTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			incomeDao.deleteByPrimaryKey(ae.getCharacterId());
			CharacterDanTianTransportDataDAO dantianDao = new CharacterDanTianTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			dantianDao.deleteByPrimaryKey(ae.getCharacterId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
