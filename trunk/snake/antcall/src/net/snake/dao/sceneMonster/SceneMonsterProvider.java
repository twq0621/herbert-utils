package net.snake.dao.sceneMonster;

import java.util.List;

public interface SceneMonsterProvider {
	
	List<SceneMonster> getSceneMonster();
	List<SceneMonster> getSceneMonsters(int mapId);
}
