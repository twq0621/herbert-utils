package net.snake.gamemodel.fight.logic;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.fight.response.VehicleMsg51120;
import net.snake.gamemodel.fight.response.VehicleMsg51122;
import net.snake.gamemodel.hero.bean.Hero;

import org.apache.log4j.Logger;

/**
 * 攻城车发射过程计时 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class SendVehiecleTimer implements Updatable {
	private static final Logger logger = Logger.getLogger(SendVehiecleTimer.class);
	SafeTimer st;
	Hero character;
	public boolean isStart = false;

	public SendVehiecleTimer(Hero character) {
		this.character = character;
	}

	public void start() {
		if (isStart) {
			return;
		}
		this.isStart = true;
		st = new SafeTimer(10 * 1000);
		character.getUpdateObjManager().addFrameUpdateObject(this);
		character.sendMsg(new VehicleMsg51120(10));
	}

	public void shutdown() {
		if (!this.isStart) {
			return;
		}
		this.isStart = false;
		character.sendMsg(new VehicleMsg51122(14560));
		character.getUpdateObjManager().removeFrameUpdateObject(this);
	}

	@Override
	public void update(long now) {
		if (st.isFirstOK(now)) {
			this.isStart = false;
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			try {
				character.getMyCharacterVehicleManager().sendShellsToFactionCtScene();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

}
