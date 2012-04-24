package net.snake.gamemodel.hero.persistence;



import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.snake.gamemodel.hero.bean.Worshipcontempt;
import net.snake.ibatis.SystemFactory;


/**
 * 人物在线统计
 * 
 * @author serv_dev
 */

public class WorshipcontemptManager{

	private static final Logger logger = Logger.getLogger(WorshipcontemptManager.class);

	private static WorshipcontemptDAO dao = new WorshipcontemptDAO(SystemFactory.getCharacterSqlMapClient());

	private Worshipcontempt worshipcontempt;
	//单例实现=====================================
	private static WorshipcontemptManager instance;
	private WorshipcontemptManager() {	
	
		
	}
	public static WorshipcontemptManager getInstance() {
		if (instance == null) {
			instance=new WorshipcontemptManager();
		}
		return instance;
	}
	//单例实现========================================
	
	
	public Worshipcontempt getWorshipcontemptById(int id){
		try {
			worshipcontempt = dao.selectByPrimaryKey(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return worshipcontempt;
	}
	public void deleteWorshipcontempt(int id){
		try {
			dao.deleteByPrimaryKey(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void updateWorshipcontempt(Worshipcontempt worshipcontempt){
		try {
			dao.updateByPrimaryKeySelective(worshipcontempt);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void addWorshipcontempt(Worshipcontempt worshipcontempt){
		try {
			dao.insert(worshipcontempt);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	
}
