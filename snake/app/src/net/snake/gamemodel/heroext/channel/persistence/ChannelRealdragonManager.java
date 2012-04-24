package net.snake.gamemodel.heroext.channel.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.heroext.channel.bean.ChannelRealdragon;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 经脉真龙管理
 * 
 * @author serv_dev
 * 
 */

public class ChannelRealdragonManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(ChannelRealdragonManager.class);
	private Map<Integer, ChannelRealdragon> channelMap = new HashMap<Integer, ChannelRealdragon>();
	private Map<Integer, List<ChannelRealdragon>> channelIdQuFenMap = new HashMap<Integer, List<ChannelRealdragon>>();
	private int totalchannelcount = 0;
	private List<ChannelRealdragon> channelList = null;
	private static ChannelRealdragonDAO dao = new ChannelRealdragonDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static ChannelRealdragonManager instance;
	// 攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)
	private int attackZhenLong, defenceZhenLong, crtZhenLong, dodgeZhenLong, hpZhenlong, mpZhenlong, spZhenLong;
	private ChannelRealdragonManager() {
	}

	public static ChannelRealdragonManager getInstance() {
		if (instance == null) {
			instance = new ChannelRealdragonManager();
		}
		return instance;
	}
	// private int attackZhenLong, defenceZhenLong, crtZhenLong, dodgeZhenLong,
	// hpZhenlong, mpZhenlong, spZhenLong;
	public int getAttackZhenLong() {
		return attackZhenLong;
	}

	public int getDefenceZhenLong() {
		return defenceZhenLong;
	}

	public int getDodgeZhenLong() {
		return dodgeZhenLong;
	}

	public int getCrtZhenLong() {
		return crtZhenLong;
	}

	public int getHpZhenlong() {
		return hpZhenlong;
	}

	public int getMpZhenlong() {
		return mpZhenlong;
	}

	public int getSpZhenLong() {
		return spZhenLong;
	}

	public Map<Integer, List<ChannelRealdragon>> getChannelIdQuFenMap() {
		return channelIdQuFenMap;
	}

	public List<ChannelRealdragon> getChannelList() {
		return channelList;
	}

	public Map<Integer, ChannelRealdragon> getCharactergradeMap() {
		return channelMap;
	}

	public int getTotalchannelcount() {
		return totalchannelcount;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, ChannelRealdragon> getChannel() {
		try {
			channelList = dao.select();
			for (ChannelRealdragon c : channelList) {
				attackZhenLong = attackZhenLong + c.getAttackAdd();
				defenceZhenLong = defenceZhenLong + c.getDefenceAdd();
				crtZhenLong = crtZhenLong + c.getCrtAdd();
				dodgeZhenLong = dodgeZhenLong + c.getDodgeAdd();
				hpZhenlong = hpZhenlong + c.getHpAdd();
				spZhenLong = spZhenLong + c.getSpAdd();
				mpZhenlong = mpZhenlong + c.getMpAdd();
			}
			Map<Integer, ChannelRealdragon> chMap = BeanTool.listToMap(channelList, "id");
			totalchannelcount = channelList.size() - 8;// 8数据不算在内，那8条是奖励数据
			return chMap;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void initaddJiaCheng() {
		attackZhenLong = 0;
		defenceZhenLong = 0;
		crtZhenLong = 0;
		dodgeZhenLong = 0;
		hpZhenlong = 0;
		mpZhenlong = 0;
		spZhenLong = 0;
	}

	public void reload() {
		try {
			initaddJiaCheng();
			BeanTool.addOrUpdate(channelMap, getChannel(), "id");
			channelIdToMap();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void channelIdToMap() {
		List<ChannelRealdragon> list = new ArrayList<ChannelRealdragon>();
		// 因为八条斤脉所以循环八次
		for (int i = 1; i < 9; i++) {
			for (ChannelRealdragon channelRealdragon : channelList) {
				String id = String.valueOf(channelRealdragon.getId()).substring(0, 1);
				if (id.equals(String.valueOf(i))) {
					list.add(channelRealdragon);
				}
			}
			channelIdQuFenMap.put(i, list);
			list = new ArrayList<ChannelRealdragon>();
		}

	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "channelrealdragon";
	}

}
