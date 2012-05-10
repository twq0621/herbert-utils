package net.snake.dao.map;

import java.util.List;

public interface MapDataProvider {
	List<Map> getMapList();
	java.util.Map<Integer, Map> getMap();
}
