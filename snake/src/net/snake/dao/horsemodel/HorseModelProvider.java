package net.snake.dao.horsemodel;

import java.util.List;

public interface  HorseModelProvider {
	HorseModel getHorseModelById(int id);
	List<HorseModel> getHorseModels();

}
