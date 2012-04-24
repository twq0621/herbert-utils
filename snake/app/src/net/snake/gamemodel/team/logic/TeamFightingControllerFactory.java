package net.snake.gamemodel.team.logic;


import net.snake.gamemodel.fight.logic.special.FengshiFightingController;
import net.snake.gamemodel.fight.logic.special.FuxibaguaFightingController;
import net.snake.gamemodel.fight.logic.special.HuyiFightingController;
import net.snake.gamemodel.fight.logic.special.LiangyiFightingController;
import net.snake.gamemodel.fight.logic.special.LiuyunFightingController;
import net.snake.gamemodel.fight.logic.special.SancaiFightingController;
import net.snake.gamemodel.fight.logic.special.TaiyiFightingController;
import net.snake.gamemodel.fight.logic.special.TianggangbeidouFightingController;
import net.snake.gamemodel.team.bean.TeamFighting;
/**
 *  阵法 产生阵法控制器
 * @author serv_dev
 *
 */
public class TeamFightingControllerFactory {
	/**
	 *
	 * @return
	 */
	public static TeamFightingController getTfc(TeamFighting tf){
		int type = tf.getId();
		switch (type) {
		case 1:
			return new LiangyiFightingController(tf);
		case 2:
			return new SancaiFightingController(tf);
	
		case 3:
			return new HuyiFightingController(tf);
	
		case 4:
			return new TaiyiFightingController(tf);
	
		case 5:
			return new FengshiFightingController(tf);
	
		case 6:
			return new LiuyunFightingController(tf);
		case 7:
			return new TianggangbeidouFightingController(tf);
		case 8:
			return new FuxibaguaFightingController(tf);
			
		default:
			return new NormalFightingControl(tf);
		}
	
	}
	
}
