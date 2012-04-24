package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.guide.bean.CharacterMsg;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;



/**
 * 玩家上线时获得的提示消息管理
 * 
 * @author serv_dev
 */
 
public class CharacterMsgManager  {
	private static final Logger logger = Logger.getLogger(CharacterMsgManager.class);
	private static CharacterMsgDAO dao = new CharacterMsgDAO(SystemFactory.getCharacterSqlMapClient());
	//单例实现=====================================
	private static CharacterMsgManager instance;
	private CharacterMsgManager() {		
	}
	public static CharacterMsgManager getInstance() {
		if (instance == null) {
			instance=new CharacterMsgManager();
		}
		return instance;
	}
	//单例实现========================================
	/**
	 * 根据角色id 查询角色提示消息
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterMsg> getListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	/**
	 * 根据角色id 删除其相关消息
	 * @param characterId
	 * @return
	 */
	public void deleteCharacterMsgByCharacterId(final int characterId) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				deleteByCharacterId(characterId);
			}
		});
		
	}
	public void deleteByCharacterId(int characterId){
		try {
			 dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 新线程提交消息
	 * @param msg
	 */
	public void insertCharacterMsg(final CharacterMsg msg){
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(msg);
				} catch (SQLException e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	}
}
