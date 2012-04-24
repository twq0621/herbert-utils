package net.snake.gamemodel.heroext.channel.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.GameServer;
import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.vline.CharacterRun;

import org.apache.log4j.Logger;

/**
 * 经脉管理
 * 
 * @author serv_dev
 * 
 */

public class ChannelManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(ChannelManager.class);
	private Map<Integer, Channel> channelMap = new HashMap<Integer, Channel>();
	private Map<Integer, List<Channel>> channelIdQuFenMap = new HashMap<Integer, List<Channel>>();

	// 攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)
	private int attack, defence, crt, dodge, hp, mp, sp;
	private int totalchannelcount = 0;
	private List<Channel> channelList = null;
	private static ChannelDAO dao = new ChannelDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static ChannelManager instance;

	private ChannelManager() {
	}

	public static ChannelManager getInstance() {
		if (instance == null) {
			instance = new ChannelManager();
		}
		return instance;
	}

	public Map<Integer, List<Channel>> getChannelIdQuFenMap() {
		return channelIdQuFenMap;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {

		return defence;
	}

	public int getCrt() {
		return crt;
	}

	public int getdodge() {
		return dodge;
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public int getSp() {
		return sp;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public Map<Integer, Channel> getCharactergradeMap() {
		return channelMap;
	}

	public int getTotalchannelcount() {
		return totalchannelcount;
	}

	// 单例实现========================================

	@SuppressWarnings("unchecked")
	private Map<Integer, Channel> getChannel() {
		try {
			channelList = dao.select();
			for (Channel c : channelList) {
				attack = attack + c.getAttackAdd();
				defence = defence + c.getDefenceAdd();
				crt = crt + c.getCrtAdd();
				dodge = dodge + c.getDodgeAdd();
				hp = hp + c.getHpAdd();
				sp = sp + c.getSpAdd();
				mp = mp + c.getMpAdd();
			}
			Map<Integer, Channel> chMap = BeanTool.listToMap(channelList, "id");
			totalchannelcount = channelList.size() - 8;// 8数据不算在内，那8条是奖励数据
			return chMap;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void addOnlineCharacter_ChannelBeiDongExp() {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.setChannelBeidongExp(character.getChannelBeidongExp().intValue() + 24);
			}
		});
	}

	public void initaddJiaCheng() {
		attack = 0;
		defence = 0;
		crt = 0;
		dodge = 0;
		hp = 0;
		mp = 0;
		sp = 0;
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
		List<Channel> list = new ArrayList<Channel>();
		// 因为八条斤脉所以循环八次
		for (int i = 1; i < 9; i++) {
			for (Channel channel : channelList) {
				String id = String.valueOf(channel.getId()).substring(0, 1);
				if (id.equals(String.valueOf(i))) {
					list.add(channel);
				}
			}
			channelIdQuFenMap.put(i, list);
			list = new ArrayList<Channel>();
		}

	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "channel";
	}

}
