package net.snake.gamemodel.fight.logic.special;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.netio.ServerResponse;

/**
 *锋矢阵	通过使用阵法秘籍书《锋矢阵》习得
 * @author serv_dev
 *
 */
public class FengshiFightingController extends TeamFightingController {

	public FengshiFightingController(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		return null;
	}

	public void createTeamPropertyChangerListener(Team t){
    	PropertyEntity pe=new PropertyEntity();
    	int teamNum=t.getTeamPopulation();
		pe.addExtraProperty(Property.crt, this.getTf().getCrt()*(teamNum-1));
		if(teamNum==7){
			pe.addExtraProperty(Property.movespeed, this.getTf().getMovespeed());
		}
        t.setPropertyEntity(pe);
    }

	@Override
	public boolean teamNumEfectProperty() {
		return false;
	}
}
