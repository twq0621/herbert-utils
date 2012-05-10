package net.snake.dao.monstermodel;

import java.util.List;

public interface MonsterModelDataProvider {
	MonsterModel getMonsterModelByID(int id);
	List<MonsterModel> getMonsterModelList();
}
