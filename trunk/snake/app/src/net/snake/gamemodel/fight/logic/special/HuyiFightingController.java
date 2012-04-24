package net.snake.gamemodel.fight.logic.special;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.netio.ServerResponse;

/**
 * 虎翼阵  无开启限制
 * @author serv_dev
 *
 */
public class HuyiFightingController extends TeamFightingController {

	public HuyiFightingController(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		return null;
	}

	public void createTeamPropertyChangerListener(Team t){
    	PropertyEntity pe=new PropertyEntity();
    	int teamNum=t.getTeamPopulation();
	    pe.addExtraProperty(Property.attack, this.getTf().getAttack()*(teamNum-1));
        t.setPropertyEntity(pe);
    }

	@Override
	public boolean teamNumEfectProperty() {
		return true;
	}
}
