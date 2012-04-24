package net.snake.gamemodel.instance.logic;

import org.apache.log4j.Logger;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 邀请队友组队进入副本响应倒计时
 * @author serv_dev
 *
 */
public class InviteEnterInstanceTimer implements Updatable {
	private static final Logger logger = Logger.getLogger(InviteEnterInstanceTimer.class);
	SafeTimer st;
	Hero character;

	public InviteEnterInstanceTimer(Hero character) {
		this.character = character;
	}

	public void start() {
		st = new SafeTimer(16 * 1000);
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
				if (character.getMyTeamManager().isTeam()) {
					Hero leader = character.getMyTeamManager().getMyTeam()
							.getTeamLeader();
					if (leader != null) {
						leader.getMyCharacterInstanceManager()
								.otherIsAccessEnterInstance(character, (byte) 3);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	}

}
