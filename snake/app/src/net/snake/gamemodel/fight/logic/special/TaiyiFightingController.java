package net.snake.gamemodel.fight.logic.special;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.netio.ServerResponse;

/**
 * 太乙阵	通过使用阵法秘籍书《太乙阵》习得
 * @author serv_dev
 *
 */
public class TaiyiFightingController extends TeamFightingController {

	public TaiyiFightingController(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		return null;
	}

	public void createTeamPropertyChangerListener(Team t){
    	PropertyEntity pe=new PropertyEntity();
    	int teamNum=t.getTeamPopulation();
    	pe.addExtraProperty(Property.defence, this.getTf().getDefence()*(teamNum-1));
        t.setPropertyEntity(pe);
    }

	@Override
	public boolean teamNumEfectProperty() {
		return true;
	}
}
