package net.snake.ai.move;

//import java.util.ArrayList;
//import java.util.List;

import net.snake.gamemodel.monster.bean.SceneMonster;

public class MonsterMoveController extends MoveController {

	public MonsterMoveController(SceneMonster monster) {
		super(monster);
		monster.setMoveSpeed(monster.getMonsterModel().getMovespeed());

	}

	
}
