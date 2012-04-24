package net.snake.gamemodel.fight.logic.special;

import java.util.List;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.netio.ServerResponse;


/**
 * 两依阵
 * 
 * @author serv_dev
 * 
 */
public class LiangyiFightingController extends TeamFightingController {

	public LiangyiFightingController(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		List<Hero> list = t.getCharacterPlayers();
		if (list.size() != 2) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1068);
		}
		Hero cs = list.get(0);
		CouplesController cf = cs.getMyFriendManager().getRoleWedingManager().getFuqi();
		if (cf == null) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1068);
		}
		Hero cs1 = list.get(1);
		int frendId = cs1.getId();
		if (!cs.getMyFriendManager().getRoleWedingManager().isWeddingWith(frendId)) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1068);
		}
		return null;
	}

	@Override
	public boolean teamNumEfectProperty() {
		return false;
	}
}
