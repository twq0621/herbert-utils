package net.snake.ai.move;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;


/**
 * 轮询方式实现人走路方法类
 * 
 * @author serv_dev
 */
public class CharacterMoveController extends MoveController {
	
	private Hero character;
	public CharacterMoveController(Hero character) {
		super(character);
		this.character = character;
	}

	@Override
	public boolean checkArrived() {
		if (super.checkArrived()) { // 更新每次轮询走到位置
			character.setObjectState(VisibleObjectState.Idle);
			return true;
		}else{
			return false;
		}
	}
}
