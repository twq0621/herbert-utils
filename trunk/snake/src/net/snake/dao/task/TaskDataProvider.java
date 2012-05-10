package net.snake.dao.task;

import java.util.List;

public interface TaskDataProvider {
	Task getTaskByID(int task);
	List<Task> getTaskList();
}
