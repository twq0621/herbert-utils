package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.vline.CharacterRun;

import org.apache.log4j.Logger;


/**
 * 宝箱统计
 * 
 * @author serv_dev
 * 
 */

public class ChestCountManager {
	private static final Logger logger = Logger.getLogger(ChestCountManager.class);
		
	private static ChestCountDAO  dao = new ChestCountDAO(SystemFactory.getCharacterSqlMapClient());

	//单例实现=====================================
	private static ChestCountManager instance;
	private ChestCountManager() {		
		
	}
	
	public static ChestCountManager getInstance() {
		if (instance == null) {
			instance=new ChestCountManager();
		}
		return instance;
	}
	//单例实现========================================
	
	public ChestCount selectChsetCount(int characterid){
		try {
			ChestCount chestCount= dao.selectByPrimaryKey(characterid);
			return chestCount;
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	public void addChsetCount(ChestCount chestCount){
		try {
			dao.insertSelective(chestCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}
	
	public void UpdateChsetCount(ChestCount chestCount){
		try {
			dao.updateByPrimaryKeySelective(chestCount);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void reSetChestCountExchangeCount(){
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getMyChestManager().getChestCount().setChesttypeExchangeCount(-1);
			}
		});
		
	}
	public void reSetChestUseCount(){
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getMyChestManager().getChestCount().setChestUseCount(0);	
			}
		});
		
	}
	public void reSetChestCountExchangeCountSql(){
		try {
			dao.reSetChestCountExchangeCount();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
