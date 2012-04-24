package net.snake.gamemodel.instance.logic;

import org.apache.log4j.Logger;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 组队进入副本倒计时
 * @author serv_dev
 *
 */
public class TeamEnterInstanceTimer implements Updatable {
	private static final Logger logger = Logger.getLogger(TeamEnterInstanceTimer.class);
	SafeTimer st;
	Hero character;
	public TeamEnterInstanceTimer(Hero character) {
		this.character = character;
	}

	public void start() {
		st = new SafeTimer(6 * 1000);
		character.getUpdateObjManager().addFrameUpdateObject(this);
	}

	public void shutdown() {
		character.getUpdateObjManager().removeFrameUpdateObject(this);
	}

	@Override
	public void update(long now) {
		if (st.isFirstOK(now)) {
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			try {
				if (character.getMyTeamManager().isTeamLeader()) {
					character.getMyCharacterInstanceManager().teamInterInstance();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	}

}
