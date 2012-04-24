package net.snake.gamemodel.hero.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.SessionKey;
import net.snake.gamemodel.hero.bean.CharacterForList;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.response.CharacterListReponse10014;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;



/**
 * 选择角色处理器。<br>
 * 
 * @author wutao
 * 
 */
@MsgCodeAnn(msgcode = 10013, accessLimit = 10)
public class QueryCharacterListProcessor extends MsgProcessor implements IThreadProcessor {
//	private static Logger logger = Logger.getLogger(QueryCharacterListProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {

		int accountId = session.getAccountid();
//		logger.info("accountId={},选择角色", accountId);
		int sid = Integer.parseInt((String) session.getAttribute(SessionKey.sid));
		CharacterManager cm = CharacterManager.getInstance();
		List<CharacterForList> characterlist = null;
		if (sid == 0) {// for dev
			characterlist = cm.getCharacterByAccountId(accountId);
		} else {// for commerce
			characterlist = cm.getCharacterByAccountIdAndSid(accountId, sid);
		}
		//byte count = (byte) characterlist.size();
//		logger.info("accountId={},有{}个角色", accountId, count);
		session.sendMsg(new CharacterListReponse10014(characterlist));
	}
}
