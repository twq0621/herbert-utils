package net.snake.gamemodel.hero.bean;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.goods.logic.CharacterResurrect;


import org.apache.log4j.Logger;
/**
 * 玩家死亡后，如果不做任何动作则会在16秒后自动复活
 * 
 * @author serv_dev
 * 
 */
public class FuhuoTimer implements Updatable {
	private static Logger logger = Logger.getLogger(FuhuoTimer.class);
	SafeTimer st;
	Hero character;

	public FuhuoTimer(Hero character) {
		this.character = character;
	}

	public void start() {
		st = new SafeTimer(16 * 1000);
		character.getUpdateObjManager().addFrameUpdateObject(this);
	}

	/**
	 * 当手动复活时，主动主闭自动复活计时
	 */
	public void shutdown() {
		character.getUpdateObjManager().removeFrameUpdateObject(this);
	}

	@Override
	public void update(long now) {
		if (st.isFirstOK(now)) {
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			try {
				CharacterResurrect.huichengFuhuo(character);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

}
