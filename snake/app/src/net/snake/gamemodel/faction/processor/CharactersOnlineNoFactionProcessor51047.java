package net.snake.gamemodel.faction.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.response.CharacterListMsg51048;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;
import org.apache.log4j.Logger;


/**
 * 请求在线玩家列表（没有加入帮会） 消息 51047
 * 
 * 
 */
@MsgCodeAnn(msgcode = 51047)
public class CharactersOnlineNoFactionProcessor51047 extends CharacterMsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(CharactersOnlineNoFactionProcessor51047.class);
	private static final int pageMax = 5;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("收到消息==========================================10301  ");
		}

		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		String name = request.getString();
		short page = request.getShort();
		if (name.equals("")) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 860));
			return;
		}

		if (page < 1) {
			page = 1;
		}
		int myId = character.getId();
		int startNum = (page - 1) * pageMax;
		int counte = 0;
		List<Hero> list = new ArrayList<Hero>();
		String oldeName = name;
		name = name.toLowerCase();
		Collection<VLineServer> lines = GameServer.vlineServerManager.getLineServersList();
		for (VLineServer line : lines) {
			Collection<Hero> collection = line.getOnlineManager().getCharacterList();
			for (Hero value : collection) {
				if (value.getName() != null && value.getId() != myId) {
					if (value.getName().toLowerCase().contains(name)) {
						counte++;
						if (counte > startNum) {
							list.add(value);
						}
						if (list.size() >= pageMax) {
							break;
						}
					}
				}
			}
			if (list.size() >= pageMax) {
				break;
			}
		}
		if (page == 1 && list.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 832));
			return;
		}
		character.sendMsg(new CharacterListMsg51048(oldeName, page, list));
	}
}
