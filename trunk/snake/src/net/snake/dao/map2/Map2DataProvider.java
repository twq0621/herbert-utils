package net.snake.dao.map2;

import java.util.List;

public interface Map2DataProvider {
	List<Map2> getMapList();
	List<Map2> getMap2byId(int id);
}
