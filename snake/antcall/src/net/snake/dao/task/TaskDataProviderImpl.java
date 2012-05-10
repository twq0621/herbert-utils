package net.snake.dao.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;





public class TaskDataProviderImpl implements TaskDataProvider ,InitializingBean{

	private Map<Integer, Task> map =new HashMap<Integer, Task>();
	private List<Task> list =null;
	private TaskDAO taskDAO;
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	public Task getTaskByID(int task) {
		return map.get(task);
	}

	@Override
	public List<Task> getTaskList() {
		return list;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TaskExample example = new TaskExample();
		example.setOrderByClause("f_id");
		list=taskDAO.selectByExample(example);
		map=BeanTool.listToMap(list,"id");
		System.out.println("task--------------"+list.size());
	}

}
