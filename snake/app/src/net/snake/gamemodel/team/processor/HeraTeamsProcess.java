package net.snake.gamemodel.team.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.bean.TeamComparator;
import net.snake.gamemodel.team.response.HereTeamList10216;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 获取附近组队队伍列表 每次最多返回10个 消息号10215
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10215)
public class HeraTeamsProcess extends CharacterMsgProcessor implements IThreadProcessor {
	private static final int pageNum = 11;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int nowPage = request.getShort();
		Collection<Hero> list = character.getSceneRef().getAllCharacters();
		List<Hero> cl = new ArrayList<Hero>();
		int id = character.getId();
		for (Hero hisCharacter : list) {
			if (hisCharacter != null && hisCharacter.getMyTeamManager().isTeamLeader() && hisCharacter.getId() != id) {
				cl.add(hisCharacter);
			}
		}
		if (cl.size() == 0) {
			character.sendMsg(new HereTeamList10216(0, 0, new ArrayList<Hero>()));
			return;
		}
		Collections.sort(cl, new TeamComparator());
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
		character.sendMsg(new HereTeamList10216(nowPage, maxPage, tempList));
		// TeamSender.sendNearTeamsMsg10216(character, cl);
	}

}
