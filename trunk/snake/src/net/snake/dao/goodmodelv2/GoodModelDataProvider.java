package net.snake.dao.goodmodelv2;

import java.util.List;

public interface GoodModelDataProvider {
	GoodModelV2 getGoodModelByID(int id);
	List<GoodModelV2> getGoodModelList();
}
