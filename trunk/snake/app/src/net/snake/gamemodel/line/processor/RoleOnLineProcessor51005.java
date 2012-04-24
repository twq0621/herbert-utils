package net.snake.gamemodel.line.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.line.response.CharacterListMsg51006;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 请求本线在线玩家列表 消息 
 * 
 * 
 */
@MsgCodeAnn(msgcode = 51005, accessLimit = 300)
public class RoleOnLineProcessor51005 extends CharacterMsgProcessor implements IThreadProcessor {
	private static final int pageMax = 5;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		short page = request.getShort();
		String name = request.getString();
		Collection<Hero> collection = null;
		if (page < 1) {
			page = 1;
		}
		int myId = character.getId();
		int startNum = (page - 1) * pageMax;
		int counte = 0;
		List<Hero> list = new ArrayList<Hero>();
		if (name.equals("")) {
			collection = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
			for (Hero value : collection) {
				if (value.getName() != null && value.getId() != myId) {
					counte++;
					if (counte > startNum) {
						list.add(value);
					}
					if (list.size() >= pageMax) {
						break;
					}
				}
			}
		} else {
			Collection<VLineServer> lines = GameServer.vlineServerManager.getLineServersList();
			for (VLineServer line : lines) {
				collection = line.getOnlineManager().getCharacterList();
				name = name.toLowerCase();
				for (Hero value : collection) {
					if (value.getViewName() != null && value.getId() != myId && value.getViewName().toLowerCase().contains(name)) {
						counte++;
						if (counte > startNum) {
							list.add(value);
						}
						if (list.size() >= pageMax) {
							break;
						}
					}
				}
				if (list.size() >= pageMax) {
					break;
				}
			}
		}
		if (page == 1 && list.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 832));
			return;
		}
		character.sendMsg(new CharacterListMsg51006(page, list));
	}
}
