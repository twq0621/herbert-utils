package net.snake.dao.taskdialog;

import java.util.Map;

import java.util.List;

public interface TaskdialogDataProvider {
	List<Taskdialog> getTaskdialogByString(String strid);
	Map<String, List<Taskdialog>> getMap();
	List<Taskdialog> getTaskdialogList();
}
