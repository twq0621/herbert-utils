package net.snake.gamemodel.fight.logic.special;

import java.util.List;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.netio.ServerResponse;


/**
 * 三才阵 队伍中只有3人，且3人为同门派关系，才能开启
 * 
 * @author serv_dev
 * 
 */
public class SancaiFightingController extends TeamFightingController {

	public SancaiFightingController(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		if (t.getTeamPopulation() != 3) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1069,"3");
		}
		List<Hero> cs = t.getCharacterPlayers();
		int p = cs.get(0).getPopsinger();
		if (cs.get(1).getPopsinger() != p || cs.get(2).getPopsinger() != p) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1070);
		}
		return null;
	}

	@Override
	public boolean teamNumEfectProperty() {
		return false;
	}
}
