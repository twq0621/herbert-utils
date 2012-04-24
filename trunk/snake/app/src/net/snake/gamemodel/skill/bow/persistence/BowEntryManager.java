package net.snake.gamemodel.skill.bow.persistence;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.commons.BeanUtils;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.bow.bean.BowDataEntry;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;


/**
 *@author serv_dev
 */
public class BowEntryManager {
	private static final Logger logger = Logger.getLogger(BowEntryManager.class);
	private final static BowEntryManager instance=new BowEntryManager();
	public static BowEntryManager getInstance(){
		return instance;
	}
	private BowEntryManager(){
	}
	private BowDataEntryDAO dao=new BowDataEntryDAO(SystemFactory.getCharacterSqlMapClient());
	public void insertBow(Bow bow){
		try {
			dao.insertSelective(bow);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void updataBow(Bow bow){
		try {
			dao.updateByPrimaryKeySelective(bow);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void syncInsertBow(final Bow bow){
		GameServer.executorServiceForDB.execute(new Runnable() {
			
			@Override
			public void run() {
				insertBow(bow);
			}
		});
	}
	
	public void syncUpdateBow(final Bow bow){
		GameServer.executorServiceForDB.execute(new Runnable() {
			
			@Override
			public void run() {
				updataBow(bow);
			}
		});
	}
	
	public Bow getBow(int characterid){
		try {
			BowDataEntry entry = dao.selectByPrimaryKey(characterid);
			if(entry!=null){
				Bow bow=new Bow();
				BeanUtils.copyProperties(entry,bow);
				bow.initGoods();
				return bow;
			}
			return null;
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	public Bow getBow(net.snake.gamemodel.hero.bean.Hero character){
		return getBow(character.getId());
	}
}
