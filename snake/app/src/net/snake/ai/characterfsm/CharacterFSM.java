package net.snake.ai.characterfsm;

import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import org.apache.log4j.Logger;


/**
 * 角色状态机
 * @author serv_dev
 *
 */
public class CharacterFSM implements Updatable {

	protected static final Logger LOGGER = Logger.getLogger(CharacterFSM.class);

	private Hero character;

	protected AfkState idelState;// 空闲状态

	protected AfkState attackState;// 攻击状态

	// ===========================
	private AfkState currentState;// 当前状态
	
	public void destroy(){
		currentState = null;
		attackState = null;
		idelState = null;
		character = null;
	}

	public CharacterFSM(Hero character) {
		this.character = character;
		initFSM();
	}

	protected boolean init = false;

	protected Hero getCharacter() {
		return character;
	}
	
	/**
	 * 初始化
	 */
	public void initFSM() {
		if (init) {
			return;
		}
		init = true;
		idelState = new IdleState(this, character);
		attackState = new AttackState(this, character);
		switchState(VisibleObjectState.Idle);
	}

	/**
	 * 取得状态机状态
	 * 
	 * @param status
	 * @return 状态
	 */
	private AfkState getFSMState(int status) {
		if (status == VisibleObjectState.Idle) {
			return idelState;
		} else if (status == VisibleObjectState.Attack) {
			return attackState;
		}
		return null;
	}

	@Override
	public void update(long now) {
		currentState.onUpdate(now);
	}

	/**
	 * 
	 * 选择状态
	 * 
	 * @param status
	 * 
	 */
	public void switchState(int status) {
		AfkState state = getFSMState(status);
		if (state == null) {
			currentState = null;
			return;
		}
		if (currentState == state) {
			return;
		}
		if (currentState != null) {
			currentState.onExit();
		}
		currentState = state;
		state.onBegin();
	}

}
