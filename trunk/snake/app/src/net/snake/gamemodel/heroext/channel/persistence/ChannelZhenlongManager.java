package net.snake.gamemodel.heroext.channel.persistence;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.heroext.channel.bean.ChannelZhenlong;
import net.snake.ibatis.SystemFactory;


/**
 * 真龙经脉
 * 
 * 
 */

public class ChannelZhenlongManager {

	private static final Logger logger = Logger.getLogger(ChannelZhenlongManager.class);
	private static ChannelZhenlongDAO dao = new ChannelZhenlongDAO(SystemFactory.getCharacterSqlMapClient());

	//单例实现=====================================
	private static ChannelZhenlongManager instance;
	private ChannelZhenlongManager() {		
		
	}
	
	public static ChannelZhenlongManager getInstance() {
		if (instance == null) {
			instance=new ChannelZhenlongManager();
		}
		return instance;
	}
	//单例实现========================================
	
	public ChannelZhenlong selecteChannelZhenlongByCharacterId(int characterId){
		try {
			return dao.selectByPrimaryKey(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public void addChannelZhenlong(final ChannelZhenlong channelZhenlong){
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insertSelective(channelZhenlong);
				} catch (SQLException e) {
					logger.error(e.getMessage(),e);
				}
				
			}
		});
	}
	
	public void updateChannelZhenlong(final ChannelZhenlong channelZhenlong){
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKeySelective(channelZhenlong);
				} catch (SQLException e) {
					logger.error(e.getMessage(),e);
				}
				
			}
		});
	}
	
	
	
}
