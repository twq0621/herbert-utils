package net.snake.gamemodel.across.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.AcrossServer;
import net.snake.GameServer;
import net.snake.commons.UUIDGenerater;
import net.snake.consts.Position;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.across.logic.AccountCharacterAcrossController;
import net.snake.gamemodel.across.response.LingquShouyiMsg1004;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

import datatransport.bean.acrossincome.AcrossIncomeTransportData;
import datatransport.bean.acrossincome.AcrossIncomeTransportDataDAO;
import datatransport.bean.charactergoods.GoodsTransportData;
import datatransport.bean.charactergoods.GoodsTransportDataDAO;

public class CharacterAcrossDateCenterManager {
	private static final Logger logger = Logger.getLogger(CharacterAcrossDateCenterManager.class);
	public Object lock = new Object();
	public Map<Integer, Long> acrossmap = new ConcurrentHashMap<Integer, Long>(); // 请求跨服收益
	private static CharacterAcrossDateCenterManager instance;
	public Map<Integer, CharacterAcross> map = new ConcurrentHashMap<Integer, CharacterAcross>(); // 角色map
	public Map<Integer, AccountCharacterAcrossController> accountMap = new ConcurrentHashMap<Integer, AccountCharacterAcrossController>();
	private CharacterAcrossDAO dao = new CharacterAcrossDAO(SystemFactory.getCharacterSqlMapClient());

	private CharacterAcrossDateCenterManager() {

	}

	public static CharacterAcrossDateCenterManager getInstance() {
		if (instance == null) {
			instance = new CharacterAcrossDateCenterManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void initDate() {
		try {
			List<CharacterAcross> list = dao.select();
			for (CharacterAcross ca : list) {
				addCharacterAcrossOnInit(ca);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public long getSendAcrossTime(int characterId) {
		Long time = acrossmap.get(characterId);
		if (time == null) {
			time = System.currentTimeMillis();
		}
		return time;
	}

	private void addCharacterAcrossOnInit(CharacterAcross ca) {
		map.put(ca.getCharacterId(), ca);
		AccountCharacterAcrossController acac = accountMap.get(ca.getAccountId());
		if (acac == null) {
			acac = new AccountCharacterAcrossController();
			accountMap.put(ca.getAccountId(), acac);
		}
		acac.addCharacterAcross(ca);
	}

	/**
	 * @param characterId
	 * @return
	 */
	public CharacterAcross getCharacterAcrossByCharacterId(int characterId) {
		return map.get(characterId);
	}

	/**
	 * 向跨服服务器发送领取收益请求
	 * 
	 * @param character
	 */
	public void sendlingquShouyiMsg(Hero character) {
		CharacterAcross characterAcross = map.get(character.getId());
		if (characterAcross != null && (characterAcross.getShouyiState() == 1)) {
			AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(characterAcross.getAcrossServerId());
			CtsConnectSessionMananger.getInstance().sendMsgToServer(asd.getLoginServerIp(), AcrossServer.acrossPort, new LingquShouyiMsg1004(character));
			acrossmap.put(character.getId(), System.currentTimeMillis());
		}
	}

	@SuppressWarnings("unchecked")
	public CharacterAcross lingquShouyi(ByteArrayReader out) {
		try {
			int characterId = out.readInt();
			byte type = out.readByte();
			if (type == 0) {
				updateCharacterAcrossOnLingquShouyi(characterId, 0);
				return null;
			}
			CharacterAcross characterAcross = null;
			synchronized (lock) {
				characterAcross = map.get(characterId);
				if (characterAcross == null || characterAcross.getShouyiState() != 1) {
					return null;
				}
				characterAcross.setShouyiState(2);
			}
			AcrossIncomeTransportData shouyi = (AcrossIncomeTransportData) out.readIObject();
			shouyi.setCharacterId(characterId);
			AcrossIncomeTransportDataDAO aitDao = new AcrossIncomeTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			List<GoodsTransportData> list = (List<GoodsTransportData>) out.readIObject();

			aitDao.updateByPrimaryKey(shouyi);
			GoodsTransportDataDAO goodDao = new GoodsTransportDataDAO(SystemFactory.getCharacterTrantsSqlMapClient());
			int goodCout = Position.AcrossBagEndPostion - Position.AcrossBagBeginPostion;
			for (GoodsTransportData good : list) {
				good.setCharacterId(characterId);
				if (good.getPosition() > Position.BagGoodsBeginPostion - 1 && good.getPosition() < Position.BagGoodsEndPostion + 1) {
					good.setPosition((short) (good.getPosition() - Position.BagGoodsBeginPostion + Position.AcrossBagBeginPostion));
				}
				good.setId(UUIDGenerater.generate());
				goodDao.insert(good);
				if (goodCout == 0) {
					break;
				}
				goodCout--;
			}
			Hero character = GameServer.vlineServerManager.getCharacterById(characterId);
			if (character == null) {
				updateCharacterAcrossOnLingquShouyi(characterId, 0);
				return characterAcross;
			}
			character.getMyCharacterAcrossIncomeManager().initAcrossShouyi();
			return characterAcross;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 是否要想远程跨服服务器发送同步跨服包裹消息请求（true 表示 是 false 表示 不需要）
	 * 
	 * @param characterId
	 * @return
	 */
	public boolean isInitAcrossShouyiToAcross(int characterId) {
		CharacterAcross characterAcross = map.get(characterId);
		if (characterAcross == null || characterAcross.getShouyiState() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 跨服数据状态
	 * 
	 * @param characterId
	 * @param state
	 */
	public void updateCharacterAcrossOnLingquShouyi(int characterId, int state) {
		removeSendAcross(characterId);
		CharacterAcross characterAcross = null;
		synchronized (lock) {
			characterAcross = map.get(characterId);
			if (characterAcross == null || characterAcross.getShouyiState() == state) {
				return;
			}
			characterAcross.setShouyiState(state);
			characterAcross.setShouyiLingquTime(new Date());
		}
		try {
			dao.updateById(characterAcross);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void logCharacterKafuAction(Hero character, AcrossServerDate as) {
		CharacterAcross characterAcross;
		synchronized (lock) {
			characterAcross = map.get(character.getId());
			if (characterAcross == null) {
				characterAcross = new CharacterAcross();
				characterAcross.setCharacterId(character.getId());
				characterAcross.setKuafuDate(new Date());
				characterAcross.setShouyiState(1);
				characterAcross.setAcrossServerId(as.getServerId());
			} else {
				characterAcross.setKuafuDate(new Date());
				characterAcross.setShouyiState(1);
				characterAcross.setAcrossServerId(as.getServerId());
			}
			map.put(character.getId(), characterAcross);
		}
		final CharacterAcross temp = characterAcross;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (temp.getId() != null) {
						dao.updateByPrimaryKeySelective(temp);
					} else {
						dao.insert(temp);
					}
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * @param character
	 * @param as
	 */
	public void addOrUpdateCharacterAcrossFlag(Hero character, AcrossServerDate as) {
		CharacterAcross characterAcross = null;
		synchronized (lock) {
			characterAcross = map.get(character.getId());
			if (characterAcross == null) {
				characterAcross = new CharacterAcross();
				characterAcross.setAcrossServerId(as.getServerId());
				characterAcross.setCharacterId(character.getId());
				characterAcross.setCharacterInitiallyId(character.getCharacterInitiallyId());
				characterAcross.setAccountInitiallyId(character.getAccountInitiallyId());
				characterAcross.setServerId(character.getOriginalSid());
				characterAcross.setKuafuDate(new Date());
				characterAcross.setShouyiState(1);
				characterAcross.setAccountId(character.getAccountId());
				map.put(character.getId(), characterAcross);
			} else {
				characterAcross.setAcrossServerId(as.getServerId());
				characterAcross.setCharacterId(character.getId());
				characterAcross.setCharacterInitiallyId(character.getCharacterInitiallyId());
				characterAcross.setAccountInitiallyId(character.getAccountInitiallyId());
				characterAcross.setKuafuDate(new Date());
				characterAcross.setShouyiState(1);
				characterAcross.setServerId(character.getOriginalSid());
				characterAcross.setAccountId(character.getAccountId());
			}
			AccountCharacterAcrossController acac = accountMap.get(character.getAccountId());
			if (acac == null) {
				acac = new AccountCharacterAcrossController();
				accountMap.put(character.getAccountId(), acac);
			}
			acac.addCharacterAcross(characterAcross);
		}
		try {
			if (characterAcross.getId() != null) {
				dao.updateById(characterAcross);
			} else {
				dao.insert(characterAcross);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void sendlingquShouyiAboutAccountMsg(Hero character) {
		AccountCharacterAcrossController acac = accountMap.get(character.getAccountId());
		if (acac == null) {
			return;
		}
		acac.sendLingquShouyiAboutOhereRoles(character);

	}

	/**
	 * @param id
	 */
	public void removeSendAcross(Integer id) {
		acrossmap.remove(id);
	}

}
