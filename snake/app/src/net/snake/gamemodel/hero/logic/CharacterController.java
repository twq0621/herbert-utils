package net.snake.gamemodel.hero.logic;

import net.snake.gamemodel.hero.bean.Hero;

public abstract class CharacterController {
	
	protected final Hero character;
	
	public CharacterController(Hero character) {
		this.character = character;
	}
	
	public Hero getCharacter(){
		return this.character;
	}
	
	/**
	 * 敢声明这个函数，我们的设计师已经疯了。
	 * @return
	 * @throws Exception
	 */
	public abstract int getAllObjInHeap() throws Exception;
}
