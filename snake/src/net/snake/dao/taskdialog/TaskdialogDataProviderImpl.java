package net.snake.dao.taskdialog;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class TaskdialogDataProviderImpl implements TaskdialogDataProvider ,InitializingBean {

	private List<Taskdialog> taskdialogslist;
	private TaskdialogDAO taskdialogDAO;
	private Map<String, List<Taskdialog>> map = new HashMap<String, List<Taskdialog>>();
	
	public TaskdialogDAO getTaskdialogDAO() {
		return taskdialogDAO;
	}

	public void setTaskdialogDAO(TaskdialogDAO taskdialogDAO) {
		this.taskdialogDAO = taskdialogDAO;
	}


	@Override
	public List<Taskdialog> getTaskdialogList() {
		return taskdialogslist;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
//		TaskdialogExample example = new TaskdialogExample();
//		example.setOrderByClause("f_task_id,f_dialog_type,f_talk_content_id");
//		taskdialogslist = taskdialogDAO.selectByExample(example);
//		List< Taskdialog> list = new ArrayList<Taskdialog>();
//		System.out.println("taskdialogslist=="+taskdialogslist.size());
//		for (Taskdialog taskdialog : taskdialogslist) {
//			String strid= taskdialog.getTaskId().toString()+"_"+taskdialog.getDialogType().toString()+"_"+taskdialog.getNpcId();
//			//String strid= taskdialog.getTaskId().toString();
//			List<Taskdialog> dialogList=map.get(strid);
//			if(dialogList==null){
//				dialogList=new ArrayList<Taskdialog>();
//				map.put(strid, dialogList);
//			}
//			dialogList.add(taskdialog);
////
////			for (Taskdialog taskdialog2 : taskdialogslist) {
////				String strid2= taskdialog2.getTaskId().toString()+"_"+taskdialog.getDialogType().toString()+"_"+taskdialog.getNpcId();
////				//String strid2= taskdialog.getTaskId().toString();
////				if(strid.equals(strid2)){
////				
////		
////					list.add(taskdialog2);
////				
////				}
////			}
//			//map.put(strid, list);
//			list = new ArrayList<Taskdialog>();
			
//		}
//System.out.println("maptaskdialogslist=="+map.keySet());
	}

	@Override
	public List<Taskdialog> getTaskdialogByString(String strid) {
		return null;
	}

	@Override
	public Map<String, List<Taskdialog>> getMap() {
		TaskdialogExample example = new TaskdialogExample();
		example.setOrderByClause("f_task_id,f_dialog_type,f_talk_content_id");
		taskdialogslist = taskdialogDAO.selectByExample(example);
		List< Taskdialog> list = new ArrayList<Taskdialog>();
		System.out.println("taskdialogslist=="+taskdialogslist.size());
		for (Taskdialog taskdialog : taskdialogslist) {
			String strid= taskdialog.getTaskId().toString()+"_"+taskdialog.getDialogType().toString()+"_"+taskdialog.getNpcId();
			//String strid= taskdialog.getTaskId().toString();
			List<Taskdialog> dialogList=map.get(strid);
			if(dialogList==null){
				dialogList=new ArrayList<Taskdialog>();
				map.put(strid, dialogList);
			}
			dialogList.add(taskdialog);
		}
		
		return map;
	}

}
