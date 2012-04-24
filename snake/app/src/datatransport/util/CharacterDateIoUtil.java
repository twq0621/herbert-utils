package datatransport.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.UUIDGenerater;
import net.snake.consts.Position;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.line.bean.ServerEntry;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.vline.VLineServer;


import datatransport.bean.account.AccountTransportData;
import datatransport.bean.account.AccountTransportDataDAO;
import datatransport.bean.acrossincome.AcrossIncomeTransportData;
import datatransport.bean.acrossincome.AcrossIncomeTransportDataDAO;
import datatransport.bean.channel.channelzhenlong.ChannelZhenlongTransportData;
import datatransport.bean.channel.channelzhenlong.ChannelZhenlongTransportDataDAO;
import datatransport.bean.character.CharacterTransportData;
import datatransport.bean.character.CharacterTransportDataDAO;
import datatransport.bean.characterDGWD.CharacterDGWDTransportData;
import datatransport.bean.characterDGWD.CharacterDGWDTransportDataDAO;
import datatransport.bean.characterbow.BowTransportData;
import datatransport.bean.characterbow.BowTransportDataDAO;
import datatransport.bean.charactergoods.GoodsTransportData;
import datatransport.bean.charactergoods.GoodsTransportDataDAO;
import datatransport.bean.characterhiddenweapon.HiddenWeaponTransportData;
import datatransport.bean.characterhiddenweapon.HiddenWeaponTransportDataDAO;
import datatransport.bean.characterhorse.CharacterHorseTransportData;
import datatransport.bean.characterhorse.CharacterHorseTransportDataDAO;
import datatransport.bean.characterlianti.CharacterLiantiTransportData;
import datatransport.bean.characterlianti.CharacterLiantiTransportDataDAO;
import datatransport.bean.characteronhoor.CharacterOnHoorConfigTransportData;
import datatransport.bean.characteronhoor.CharacterOnHoorConfigTransportDataDAO;
import datatransport.bean.characterskilldata.CharacterSkillTransportData;
import datatransport.bean.characterskilldata.CharacterSkillTransportDataDAO;
import datatransport.bean.characterteamfighting.CharacterTeamfightingTransportData;
import datatransport.bean.characterteamfighting.CharacterTeamfightingTransportDataDAO;
import datatransport.bean.dantian.CharacterDanTianTransportData;
import datatransport.bean.dantian.CharacterDanTianTransportDataDAO;

/**
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class CharacterDateIoUtil {
	private static Logger logger = Logger.getLogger(CharacterDateIoUtil.class);

	@SuppressWarnings("unchecked")
	public static Msg characterToIo(Hero character, int serverId) {
		// DataIOUtil out=DataIOUtil.newInstance4Out();
		Msg msg = new Msg(1002);
		ByteArrayWriter out = msg.getContentWriter();
		character.getDownLineManager().saveToDb();
		CharacterTransportDataDAO characterDao = new CharacterTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
		try {
			long start = System.currentTimeMillis();
			CharacterCacheEntry cce = character.getMyFriendManager().getRoleWedingManager().getWedder();
			out.writeInt(serverId);
			ServerEntry server = GameServer.serverentry;
			out.writeInt(character.getVlineserver().getLineid());
			out.writeUTF(character.getVlineserver().getLinename());
			out.writeUTF(server.getLoginserverip());
			out.writeUTF(server.getLoginserverport());
			out.writeUTF(server.getChatserverip());
			out.writeUTF(server.getChatserverport());

			if (cce == null) {
				out.writeInt(0);
				out.writeInt(0);
				out.writeUTF("");
				out.writeInt(0);
				out.writeLong(0);
				out.writeInt(0);
				out.writeInt(0);
			} else {
				out.writeInt(cce.getOriginalSid());
				out.writeInt(cce.getId());
				out.writeUTF(cce.getName());
				Couples c = character.getMyFriendManager().getRoleWedingManager().getFuqi().getCouples();
				out.writeInt(c.getRingId());
				out.writeLong(c.getWeddingDate().getTime());
				out.writeInt(cce.getDantiangrade());
				out.writeInt(cce.getPopsinger());
			}
			Faction faction = character.getMyFactionManager().getFactionController().getFaction();
			out.writeInt(faction.getServerId());
			out.writeInt(faction.getId());
			out.writeUTF(faction.getName());
			out.writeInt(faction.getFactionFlagId());
			out.writeByte(character.getMyFactionManager().getFactionPosition());
			out.writeByte(character.getMyFactionManager().isXiangyangchengZhu() ? 1 : 0);
			AccountTransportDataDAO accountDao = new AccountTransportDataDAO(SystemFactory.getAccountTrantsSqlMapClient());
			AccountTransportData account = accountDao.selectByPrimaryKey(character.getAccountId());
			if (account == null) {
				return null;
			}
			out.writeIObject(account);
			CharacterTransportData roleTrace = characterDao.selectByPrimaryKey(character.getId());
			if (roleTrace == null) {
				return null;
			}
			// 角色写入
			out.writeIObject(roleTrace);
			GoodsTransportDataDAO goodDao = new GoodsTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			Map<String, Integer> queryMap = new HashMap<String, Integer>();
			queryMap.put("characterId", character.getId());
			queryMap.put("beginPostion", Position.AcrossBagBeginPostion - 0);
			queryMap.put("endPostion", Position.AcrossBagEndPostion - 0);
			List<GoodsTransportData> goodList = null;
			try {
				goodList = goodDao.selectByPostion(queryMap); //
				queryMap.put("beginPostion", Position.BodyGoodsBeginPostion - 0);
				queryMap.put("endPostion", Position.BagGoodsBeginPostion - 1);
				List<GoodsTransportData> goodList1 = goodDao.selectByPostion(queryMap); //
				goodList.addAll(goodList1);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
//			logger.info("goodList SIZE:： " + goodList.size());
			// 物品写入
			out.writeIObject(goodList);
			CharacterLiantiTransportDataDAO liantiDao = new CharacterLiantiTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterLiantiTransportData lianti = null;
			try {
				lianti = liantiDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			// 练体数据写入
			if (lianti == null) {
				lianti = new CharacterLiantiTransportData();
			}
			out.writeIObject(lianti);
			ChannelZhenlongTransportDataDAO channelDao = new ChannelZhenlongTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			ChannelZhenlongTransportData channel = null;
			try {
				channel = channelDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			// 静脉数据
			if (channel == null) {
				channel = new ChannelZhenlongTransportData();
			}
			out.writeIObject(channel);
			CharacterSkillTransportDataDAO skillDao = new CharacterSkillTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<CharacterSkillTransportData> skillList = null;
			;
			try {
				skillList = skillDao.selectByCharacterId(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			out.writeIObject(skillList);
			CharacterHorseTransportDataDAO horseDao = new CharacterHorseTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<CharacterHorseTransportData> horseList = null;
			try {
				horseList = horseDao.selectByCharacterId(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			out.writeIObject(horseList);
			HiddenWeaponTransportDataDAO anqiDao = new HiddenWeaponTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<HiddenWeaponTransportData> anqiList = null;
			try {
				anqiList = anqiDao.selectByCharacterId(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			HiddenWeaponTransportData anqi;
			if (anqiList != null && anqiList.size() > 0) {
				anqi = anqiList.get(0);
			} else {
				anqi = new HiddenWeaponTransportData();
			}
			out.writeIObject(anqi);
			CharacterOnHoorConfigTransportDataDAO configDao = new CharacterOnHoorConfigTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterOnHoorConfigTransportData guanji = null;
			try {
				guanji = configDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (guanji == null) {
				guanji = new CharacterOnHoorConfigTransportData();
			}
			out.writeIObject(guanji);
			CharacterTeamfightingTransportDataDAO tfDao = new CharacterTeamfightingTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<CharacterTeamfightingTransportData> tfList = null;
			try {
				tfList = tfDao.selectByCharacterId(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			out.writeIObject(tfList);
			BowTransportDataDAO bowDao = new BowTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			BowTransportData bow = null;
			try {
				bow = bowDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (bow == null) {
				bow = new BowTransportData();
			}
			out.writeIObject(bow);
			AcrossIncomeTransportDataDAO incomeDao = new AcrossIncomeTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			AcrossIncomeTransportData shouyi = null;
			try {
				shouyi = incomeDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (shouyi == null) {
				shouyi = new AcrossIncomeTransportData();
			}
			out.writeIObject(shouyi);
			CharacterDanTianTransportDataDAO dantianDao = new CharacterDanTianTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterDanTianTransportData dangtian = null;
			try {
				dangtian = dantianDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (dangtian == null) {
				dangtian = new CharacterDanTianTransportData();
			}
			out.writeIObject(dangtian);

			// 独孤九剑
			CharacterDGWDTransportData dugu = null;
			try {
				CharacterDGWDTransportDataDAO duguDao = new CharacterDGWDTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
				dugu = duguDao.selectByPrimaryKey(character.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (dugu == null) {
				dugu = new CharacterDGWDTransportData();
			}
			out.writeIObject(dugu);
			logger.info("游戏服务器角色数据 读出时间" + (System.currentTimeMillis() - start));
			return msg;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.info(e.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			logger.info(e.toString());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static AcrossEtc ioCharacterToDb(Msg msg) {
		long start = System.currentTimeMillis();
		ByteArrayReader out = msg.getContentReader();
		CharacterTransportDataDAO characterDao = new CharacterTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
		AcrossEtc ae = new AcrossEtc();
		try {
			int serverId = out.readInt();
			int lineID = out.readInt();
			String lineName = out.readUTF();
			String loginIp = out.readUTF();
			String loginPort = out.readUTF();
			String chatIp = out.readUTF();
			String chatPort = out.readUTF();
			ae.setCharacterLineId(lineID);
			ae.setCharacterLineName(lineName);
			ae.setLoginServerIp(loginIp);
			ae.setLoginport(loginPort);
			ae.setChatServerIp(chatIp);
			ae.setChatport(chatPort);

			int peiouServerId = out.readInt();
			// AcrossEtc ae=new AcrossEtc();
			int peiouId = out.readInt();
			String peiouName = out.readUTF();
			int quanpeiId = out.readInt();
			long wedderDate = out.readLong();
			ae.setWedderQuanpeiId(quanpeiId);
			if (wedderDate > 1000) {
				ae.setWedderTime(new Date(wedderDate));
			}
			int dantian = out.readInt();
			int menpai = out.readInt();
			ae.setOldWedderDantian((byte) dantian);
			ae.setOldWedderMenpai((byte) menpai);
			int factionServerId = out.readInt();
			int factionId = out.readInt();
			String factionName = out.readUTF();
			int factionGrade = out.readInt();
			byte factionPosition = out.readByte();
			byte ischengzhu = out.readByte();

			ae.setIsBangzhu((int) factionPosition);
			ae.setIsChengzhu((int) ischengzhu);
			ae.setGangGrade(factionGrade);
			AccountTransportData account = (AccountTransportData) out.readIObject();
			int oldAccountId = account.getId();
			ae.setOldAccountId(oldAccountId);
			ae.setOldAccountInitiallyId(account.getAccountInitiallyId());
			// 角色
			CharacterTransportData role = (CharacterTransportData) out.readIObject();
			int oldRoleId = role.getId();
			ae.setOldCharacterId(oldRoleId);
			ae.setAscossServerId(serverId);
			ae.setOldAreaId(role.getOriginalSid());
			ae.setOldCharacterInitiallyId(role.getCharacterInitiallyId());
			try {
				VLineServer line = GameServer.vlineServerManager.getVLineServerByLineID(serverId);
				if (line != null) {
					AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(serverId);
					if (asd != null && line.getOnlineManager().getplayercount() > asd.getMaxpermitplayercount()) {
						return ae;
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			AccountTransportDataDAO accountDao = new AccountTransportDataDAO(SystemFactory.getAccountTrantsSqlMapClient());
			account.setId(null);
			account.setLoginname(account.getLoginname() + System.currentTimeMillis());
			accountDao.insert(account);
			int newAccountId = account.getId();
			ae.setAccountId(newAccountId);
			role.setId(null);
			role.setAccountId(newAccountId);
			role.setMaxBagAmount((short) 30);
			characterDao.insert(role);
			int newRoleId = role.getId();
			ae.setCharacterId(newRoleId);
			// Character
			// character=CharacterManager.getInstance().getCharacterById(newRoleId);
			GoodsTransportDataDAO goodDao = new GoodsTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			// 物品
			List<GoodsTransportData> goodList = (List<GoodsTransportData>) out.readIObject();
			CharacterLiantiTransportDataDAO liantiDao = new CharacterLiantiTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterLiantiTransportData lianti = (CharacterLiantiTransportData) out.readIObject();
			// 练体数据写入
			if (lianti != null && lianti.getCharacterId() != null && lianti.getCharacterId() > 0) {
				lianti.setCharacterId(newRoleId);
				liantiDao.insert(lianti);
			}
			ChannelZhenlongTransportDataDAO channelDao = new ChannelZhenlongTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			ChannelZhenlongTransportData channel = (ChannelZhenlongTransportData) out.readIObject();
			// 静脉数据
			if (channel != null && channel.getCharacterId() != null && channel.getCharacterId() > 0) {
				channel.setCharacterId(newRoleId);
				channelDao.insert(channel);
			}
			CharacterSkillTransportDataDAO skillDao = new CharacterSkillTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<CharacterSkillTransportData> skillList = (List<CharacterSkillTransportData>) out.readIObject();
			for (CharacterSkillTransportData skill : skillList) {
				skill.setId(null);
				skill.setCharacterId(newRoleId);
				skillDao.insert(skill);
			}
			CharacterHorseTransportDataDAO horseDao = new CharacterHorseTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<CharacterHorseTransportData> horseList = (List<CharacterHorseTransportData>) out.readIObject();
			for (CharacterHorseTransportData horse : horseList) {
				horse.setCharacterId(newRoleId);
				int oldHours = horse.getId();
				horse.setId(null);
				horseDao.insert(horse);
				updateHorseGood(oldHours, horse.getId(), goodList);
			}
			for (GoodsTransportData good : goodList) {
				good.setId(UUIDGenerater.generate());
				good.setCharacterId(newRoleId);
				if (good.getPosition() > Position.AcrossBagBeginPostion - 1 && good.getPosition() < Position.AcrossBagEndPostion + 1) {
					good.setPosition((short) (good.getPosition() - Position.AcrossBagBeginPostion + Position.BagGoodsBeginPostion));
				}
				goodDao.insert(good);
			}
			HiddenWeaponTransportDataDAO anqiDao = new HiddenWeaponTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			HiddenWeaponTransportData anqi = (HiddenWeaponTransportData) out.readIObject();
			if (anqi != null && anqi.getCharacterId() != null && anqi.getCharacterId() > 0) {
				anqi.setCharacterId(newRoleId);
				anqi.setId(UUIDGenerater.generate());
				anqiDao.insert(anqi);
			}
			CharacterOnHoorConfigTransportDataDAO configDao = new CharacterOnHoorConfigTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterOnHoorConfigTransportData guanji = (CharacterOnHoorConfigTransportData) out.readIObject();
			if (guanji != null && guanji.getCharacterId() != null && guanji.getCharacterId() > 0) {
				guanji.setCharacterId(newRoleId);
				configDao.insert(guanji);
			}
			CharacterTeamfightingTransportDataDAO tfDao = new CharacterTeamfightingTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());

			List<CharacterTeamfightingTransportData> tfList = (List<CharacterTeamfightingTransportData>) out.readIObject();
			for (CharacterTeamfightingTransportData tf : tfList) {
				tf.setId(null);
				tf.setCharacterId(newRoleId);
				tfDao.insert(tf);
			}

			BowTransportDataDAO bowDao = new BowTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			BowTransportData bow = (BowTransportData) out.readIObject();
			if (bow.getCharacterid() != null && bow.getCharacterid() > 0) {
				bow.setCharacterid(newRoleId);
				bow.setBag1count(0);
				bow.setBag1type(-1);
				bow.setBag2count(0);
				bow.setBag2type(-1);
				bowDao.insert(bow);
			}
			ae.setOldGangAreaId(factionServerId);
			ae.setOldGangId(factionId);
			ae.setOldGangName(factionName);
			ae.setOldWedderAreaId(peiouServerId);
			ae.setOldWedderId(peiouId);
			ae.setOldWedderName(peiouName);
			AcrossIncomeTransportDataDAO incomeDao = new AcrossIncomeTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			AcrossIncomeTransportData shouyi = (AcrossIncomeTransportData) out.readIObject();
			if (shouyi != null && shouyi.getCharacterId() != null && shouyi.getCharacterId() > 0) {
				shouyi.setCharacterId(newRoleId);
				shouyi.setXuanyuanjian(0);
				incomeDao.insert(shouyi);
			}
			CharacterDanTianTransportDataDAO dantianDao = new CharacterDanTianTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			CharacterDanTianTransportData dangtian = (CharacterDanTianTransportData) out.readIObject();
			if (dangtian != null && dangtian.getCharacterid() != null && dangtian.getCharacterid() > 0) {
				dangtian.setCharacterid(newRoleId);
				dantianDao.insert(dangtian);
			}

			// 独孤九剑
			CharacterDGWDTransportData dugu = (CharacterDGWDTransportData) out.readIObject();
			CharacterDGWDTransportDataDAO duguDao = new CharacterDGWDTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			if (dugu != null && dugu.getCharacterid() != null && dugu.getCharacterid() > 0) {
				dugu.setCharacterid(newRoleId);
				duguDao.insert(dugu);
			}
			AcrossEtcManager.getInstance().addAcrossEtc(ae);
			return ae;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.info(e.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			logger.info(e.toString());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			logger.info(e.toString());
		}
		ae.setCharacterId(null);
		logger.info("跨服服务器角色数据 保存时间" + (System.currentTimeMillis() - start));
		return ae;
	}

	private static void updateHorseGood(int oldhorse, int newHorse, List<GoodsTransportData> goodList) {
		for (GoodsTransportData good : goodList) {
			if (good.getInHorseId() == oldhorse) {
				good.setInHorseId(newHorse);
			}
		}
	}
}
