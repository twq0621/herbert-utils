package net.snake.gamemodel.hero.processor;

import java.io.IOException;
import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.FactionCharacterManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.response.DelCharacterResponse10018;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.gamemodel.wedding.persistence.CouplesManager;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.cache.CharacterCacheManager;
import org.apache.log4j.Logger;


/**
 * 删除角色处理
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10017, accessLimit = 10)
public class DelCharacterProcessor extends MsgProcessor implements IThreadProcessor {

	private static Logger logger = Logger.getLogger(DelCharacterProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int characterId = request.getInt();
		boolean isPlaying = this.isPlaying(session.getAccountid());
		if (isPlaying) {
			session.sendMsg(new DelCharacterResponse10018(40011));
			return;
		}
		if (WedFeastManager.getInstance().isFeast(characterId)) {
			session.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19510));
			return;
		}
		logger.info(characterId + "");
		// 删除人物角色
		CharacterManager characterManager = CharacterManager.getInstance();
		// Character character = characterManager.getCharacterById(characterId);
		try {
			characterManager.deleteCharacter(characterId);
			session.sendMsg(new DelCharacterResponse10018());
			// 删除角色更新游戏服务器各分线角色信息 角色相关帮会信息
			// delCharacterUpdateGameServer(character);
			// 删除角色好友信息
			deleteUpdateFriendInfo(characterId);
			deleteUpdateFactionInfo(characterId);
			delCharacterUpdateWeddingInfo(characterId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			session.sendMsg(new DelCharacterResponse10018(40012));
		}
	}

	/**
	 * 好友相关信息删除操作
	 * 
	 * @param character
	 */
	public void deleteUpdateFriendInfo(int characterId) {
		CharacterFriendManager.getInstance().deleteByFriendId(characterId);
		CharacterFriendManager.getInstance().delteByCharacterId(characterId);
	}

	/**
	 * 帮会相关信息删除操作
	 * 
	 * @param character
	 */
	public void deleteUpdateFactionInfo(int characterId) {
		FactionCharacter fc = FactionCharacterManager.getInstance().getFc(characterId);
		if (fc == null) {
			return;
		}
		FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionID(fc.getFactionId());
		if (factionC == null) {
			return;
		}
		if (fc.getPosition() == 1) {
			factionC.dismissFaction();
			FactionCharacterManager.getInstance().deleteFactionCharacter(fc);
			return;
		}
		factionC.leaveFaction(null, characterId, null);

	}

	/**
	 * 删号清楚夫妻关系
	 * 
	 * @param character
	 */
	public void delCharacterUpdateWeddingInfo(int characterId) {
		CouplesController cc = CouplesManager.getInstance().getCouplesController(characterId);
		if (cc != null) {
			cc.overWeddingWhenDeleteCharacter(characterId);
		}
	}

	/**
	 * 角色删除 (暂时不要调用)
	 * 
	 * @param id
	 * @throws DataException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void delCharacterUpdateGameServer(Hero character) throws DataException, IOException, SQLException {
		int id = character.getId();
		CharacterCacheManager.getInstance().removeById(id);
	}

	/**
	 * 验证玩家是否 正在游戏
	 * 
	 * @return
	 */
	private boolean isPlaying(int accountId) {
		return GameServer.vlineServerManager.getCharacterByAccountId(accountId) != null;
	}

}
