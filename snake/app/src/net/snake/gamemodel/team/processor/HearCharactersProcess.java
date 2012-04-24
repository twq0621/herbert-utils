package net.snake.gamemodel.team.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.MyTeamManager;
import net.snake.gamemodel.team.response.HereCharacterList10214;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 获取附近没有组队玩家列表 每次最多返回10个 消息号10213 (全地图搜索)
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10213)
public class HearCharactersProcess extends CharacterMsgProcessor implements IThreadProcessor {
	private static final int pageNum = 11;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		int nowPage = request.getShort();
		// 修改 获取全地图玩家信息

		Collection<Hero> list = character.getSceneRef().getAllCharacters();
		List<Hero> cl = new ArrayList<Hero>();
		int id = character.getId();
		String name = request.getString();
		if (name == null || name.equals("")) {// 没有名字查询
			for (Hero hisCharacter : list) {
				MyTeamManager hisTeamManager = hisCharacter.getMyTeamManager();
				if (hisCharacter != null && !hisTeamManager.isTeam() && id != hisCharacter.getId()) {
					cl.add(hisCharacter);
				}
			}
		} else { // 有名字查询
			name = name.toLowerCase();
			for (Hero hisCharacter : list) {
				MyTeamManager hisTeamManager = hisCharacter.getMyTeamManager();
				if (hisCharacter != null && !hisTeamManager.isTeam() && id != hisCharacter.getId()) {
					if (hisCharacter.getName().toLowerCase().contains(name)) {
						cl.add(hisCharacter);
					}
				}
			}
		}
		if (cl.size() == 0) {
			character.sendMsg(new HereCharacterList10214(0, 0, new ArrayList<Hero>()));
			return;
		}
		int maxPage = (cl.size() - 1) / pageNum + 1;
		if (nowPage > maxPage) {
			nowPage = maxPage;
		}
		if (nowPage < 1) {
			nowPage = 1;
		}
		int startIndex = (nowPage - 1) * pageNum;
		List<Hero> tempList = new ArrayList<Hero>();
		int count = 0;
		for (int i = startIndex; i < cl.size(); i++) {
			tempList.add(cl.get(i));
			count++;
			if (count >= pageNum) {
				break;
			}
		}
		character.sendMsg(new HereCharacterList10214(nowPage, maxPage, tempList));

	}
}
