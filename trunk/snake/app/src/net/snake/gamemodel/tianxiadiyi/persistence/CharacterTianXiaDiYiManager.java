package net.snake.gamemodel.tianxiadiyi.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.AcrossServer;
import net.snake.GameServer;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;


/**
 * 天下第一管理类
 * 
 */
public class CharacterTianXiaDiYiManager {

	private static Logger logger = Logger.getLogger(CharacterTianXiaDiYiManager.class);
	private Map<String, CharacterTianXiaDiYi> haracterTianXiaDiYiMap = new ConcurrentHashMap<String, CharacterTianXiaDiYi>();
	private List<CharacterTianXiaDiYi> lisTianXiaDiYi;
	private CharacterTianXiaDiYiDAO characterTianXiaDiYiDAO = new CharacterTianXiaDiYiDAO(SystemFactory.getCharacterSqlMapClient());

	private static CharacterTianXiaDiYiManager instance;

	public static CharacterTianXiaDiYiManager getInstance() {
		if (instance == null) {
			instance = new CharacterTianXiaDiYiManager();
		}
		return instance;
	}

	public List<CharacterTianXiaDiYi> getLisTianXiaDiYi() {
		return lisTianXiaDiYi;
	}

	public CharacterTianXiaDiYiManager() {
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		try {
			lisTianXiaDiYi = characterTianXiaDiYiDAO.select();
			if (lisTianXiaDiYi == null || lisTianXiaDiYi.isEmpty()) {
				return;
			}
			this.initCharacterTianXiaDiYi(lisTianXiaDiYi);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	private void initCharacterTianXiaDiYi(List<CharacterTianXiaDiYi> list) {
		haracterTianXiaDiYiMap.clear();
		for (CharacterTianXiaDiYi o : list) {
			haracterTianXiaDiYiMap.put(o.getCharacterId().toString() + o.getServerId().toString(), o);
		}
	}

	public Map<String, CharacterTianXiaDiYi> getHaracterTianXiaDiYiMap() {
		return haracterTianXiaDiYiMap;
	}

	/**
	 * 清空本地数据 <br/>
	 * 循环物理机<br/>
	 * 添加天下第一数据
	 * 
	 * @throws SQLException
	 */
	public void updateData() {
		try {
			haracterTianXiaDiYiMap.clear();
			lisTianXiaDiYi.clear();
			characterTianXiaDiYiDAO.deleteAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			CharacterTianXiaDiYiGoodsManager.getInstance().updateData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (Options.IsCrossServ) {
			return;
		}
		for (List<AcrossServerDate> list : AcrossServerDateManager.getInstance().getMap().values()) {
			AcrossServerDate acrossServerDate = list.get(0);
			if (acrossServerDate == null) {
				return;
			}

			logger.info("向服务器:" + acrossServerDate.getLoginServerIp() + " 获取天下第一数据.");
			CtsConnectSessionMananger.getInstance().sendMsgToServer(acrossServerDate.getLoginServerIp(), AcrossServer.acrossPort, new Msg(1011));
		}

	}

	public void updateCharacterTianXiaDiYiTopOneSave(Hero character, int zhanchangId) {
		AcrossIncome ai = character.getMyCharacterAcrossIncomeManager().getAi();
		AcrossEtc etc = character.getCharacterAcrossServerManager().getAce();
		Horse horse = character.getCharacterHorseController().getCurrentRideHorse();
		CharacterTianXiaDiYi characterTianXiaDiYi = new CharacterTianXiaDiYi();
		characterTianXiaDiYi.setCharacterId(etc.getOldCharacterInitiallyId());
		characterTianXiaDiYi.setServerId(etc.getOldAreaId());
		characterTianXiaDiYi.setPopsinger(character.getPopsinger());
		characterTianXiaDiYi.setCharacterName(character.getName());
		characterTianXiaDiYi.setChestCount(0);
		characterTianXiaDiYi.setGrade((int) character.getGrade());
		characterTianXiaDiYi.setZhanchangId(zhanchangId);
		characterTianXiaDiYi.setHorseModelId(0);
		characterTianXiaDiYi.setLunjianShengwang(ai.getShengwang());
		characterTianXiaDiYi.setShenglv((ai.getWinCnt() + ai.getFailCnt()) == 0 ? 0 : (int) (ai.getWinCnt() * 1f / (ai.getWinCnt() + ai.getFailCnt()) * 100));
		if (null != horse) {
			characterTianXiaDiYi.setHorseModelId(horse.getCharacterHorse().getHorseModelId());
		}
		try {
			characterTianXiaDiYiDAO.insertSelective(characterTianXiaDiYi);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateCharacterTianXiaDiYi(final CharacterTianXiaDiYi characterTianXiaDiYi) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					characterTianXiaDiYiDAO.updateByPrimaryKeySelective(characterTianXiaDiYi);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});
	}

	/**
	 * 更新缓存并且更新对象
	 * 
	 * @param obj
	 */
	public void insert(CharacterTianXiaDiYi obj) {
		if (haracterTianXiaDiYiMap.containsKey(obj.getCharacterId().toString() + obj.getServerId())) {
			return;
		}
		obj.setId(null);
		haracterTianXiaDiYiMap.put(obj.getCharacterId().toString() + obj.getServerId(), obj);
		lisTianXiaDiYi.add(obj);
		try {
			characterTianXiaDiYiDAO.insertSelective(obj);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 跨服使用删除跨服数据
	 * 
	 * @param obj
	 */
	public void deleteCharacterTianXiaDiYiAll() {
		try {
			characterTianXiaDiYiDAO.deleteAll();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void resetCharacterTianXiaDiYiChest() {
		for (CharacterTianXiaDiYi characterTianXiaDiYi : lisTianXiaDiYi) {
			characterTianXiaDiYi.setChestCount(0);
		}
		try {
			characterTianXiaDiYiDAO.resetCharacterTianXiaDiYiChestCount();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
