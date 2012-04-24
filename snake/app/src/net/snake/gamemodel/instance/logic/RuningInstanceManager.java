package net.snake.gamemodel.instance.logic;

//import net.snake.api.context.ISubline;
import net.snake.serverenv.vline.VLineServer;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


public class RuningInstanceManager {
private static Logger logger = Logger.getLogger(RuningInstanceManager.class);
	private VLineServer vlineserver;
	//private ISubline subline;
	private Map<Integer, InstanceController> map = new ConcurrentHashMap<Integer, InstanceController>();
	private Map<Integer, DownLineRoleInstanceController> downLineMap = new ConcurrentHashMap<Integer, DownLineRoleInstanceController>();

	public void addInstanceController(InstanceController instanceController) {
		instanceController.setIsloop(true);
		map.put(instanceController.getNum(), instanceController);
	}

	public InstanceController getInstanceController(int num) {
		return map.get(num);
	}

	public void removeInstanceController(int num) {
		InstanceController instanceController=map.remove(num);
		if(instanceController!=null){
			instanceController.setIsloop(false);
		}
	}

	public void addDownLineRoleInstanceController(
			DownLineRoleInstanceController instanceDownLine) {
		downLineMap.put(instanceDownLine.getLeaveRoleId(), instanceDownLine);
	}

	public void removeDownLineRoleInstanceController(int roleId) {
		downLineMap.remove(roleId);
	}

	public VLineServer getVlineserver() {
		return vlineserver;
	}
	//public ISubline getSubline(){
	//	return subline;
	//}

	public void setVlineserver(VLineServer vlineserver) {
		this.vlineserver = vlineserver;
	}
	//public void setSubline(ISubline line){
	//	subline=line;
	//}

	public void update(long now) {
		if (map.size() != 0) {
			for (InstanceController instanceController : map.values()) {
				try {
					instanceController.update(now);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		if (downLineMap.size() == 0) {
			return;
		}
		for (DownLineRoleInstanceController downLine : downLineMap.values()) {
			downLine.update();
		}
	}

	public Collection<InstanceController> getInstanceCollection() {
		return map.values();
	}

	public int getOnlineCount() {
		if (map.size() == 0) {
			return 0;
		}
		int i = 0;
		for (InstanceController instanceController : map.values()) {
			i = i + instanceController.getInstanceAllCharacters().size();
		}
		return i;
	}

	public DownLineRoleInstanceController getDownLineRoleInstanceController(
			int roleId) {
		return downLineMap.get(roleId);
	}

	public int getInstanceDownLineRoleCount() {
		return downLineMap.size();
	}
}
