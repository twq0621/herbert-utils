package net.snake.gamemodel.heroext.biguandazuo.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.biguandazuo.response.CharacterListMsg50508;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 主角查询双修玩家 消息 50507
 * 
 * 
 */
@MsgCodeAnn(msgcode = 50507)
public class RoleOnServerSearchProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	private static final int pageMax = 5;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
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
				if (value.getName() == null || value.getId() == myId) {
					continue;
				}
				if (type == 1) {
					if (value.getMyDazuoManager().isShuangXiu()) {
						continue;
					}
				} else if (type == 2) {
					if (value.getMyDazuoManager().isLiaoshangState()) {
						continue;
					}
					String shoushang = value.getMyChannelManager().getFailChannelId();
					if (shoushang == null || shoushang.length() == 0) {
						continue;
					}
				}
				counte++;
				if (counte > startNum) {
					list.add(value);
				}
				if (list.size() >= pageMax) {
					break;
				}
			}
		} else {
			collection = character.getVlineserver().getOnlineManager().getCharacterList();
			name = name.toLowerCase();
			for (Hero value : collection) {
				if (value.getName() == null || value.getId() == myId) {
					continue;
				}
				if (!value.getName().toLowerCase().contains(name)) {
					continue;
				}
				counte++;
				if (counte > startNum) {
					list.add(value);
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
		character.sendMsg(new CharacterListMsg50508(type, page, list));
	}
}
