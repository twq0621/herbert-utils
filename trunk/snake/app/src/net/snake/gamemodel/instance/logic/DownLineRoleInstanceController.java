/**
 * 
 */
package net.snake.gamemodel.instance.logic;

import net.snake.GameServer;
import net.snake.gamemodel.faction.response.factioncity.FactionCityChangeLineMsg51134;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.serverenv.vline.VLineServer;


/**
 * 角色下线副本临时管理器
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class DownLineRoleInstanceController {
	public static int timeOut=6*60*1000+500;
	private InstanceController instancController;
	private VLineServer vlineServer;// 所在分线
	private long lastTime; // 超时时间
	private int leaveRoleId;// 离线角色id

	/**
	 * @param instanceController
	 * @param character
	 */
	public DownLineRoleInstanceController(
			InstanceController instanceController, Hero character,VLineServer vline) {
	     this.instancController=instanceController;
	     this.vlineServer=vline;
	     this.leaveRoleId=character.getId();
	     this.lastTime=System.currentTimeMillis()+timeOut;
	     if(this.lastTime>=instanceController.getEndTime()-2000){
	    	 this.lastTime=instanceController.getEndTime();
	     }
	}

	public InstanceController getInstancController() {
		return instancController;
	}

	public void setInstancController(InstanceController instancController) {
		this.instancController = instancController;
	}

	

	public int getLeaveRoleId() {
		return leaveRoleId;
	}

	public void setLeaveRoleId(int leaveRoleId) {
		this.leaveRoleId = leaveRoleId;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
    public boolean isExChangeToEnterInstance(){
    	long time=this.lastTime -System.currentTimeMillis();
    	if(time<1500){
    		return false;
    	}
    	return true;
    }
	public void update() {
		if (lastTime > System.currentTimeMillis()) {
			return;
		}
		vlineServer.getRuningInstanceManager()
				.removeDownLineRoleInstanceController(this.leaveRoleId);
	}

	/**
	 * 角色上线 重新进入之前因掉线而离开的副本
	 * 
	 * @param character
	 */
	public void roleOnlineEnterInstance(Hero character) {
		int lineId = character.getVlineserver().getLineid();
		Scene scene = instancController.getSceneBySceneId(character.getScene());
		if (lineId == vlineServer.getLineid()) {
			ExchangeMapTrans.trans(scene, character.getX(), character.getY(),
					character);
			character.setVlineserver(vlineServer);
		} else {
			character.sendMsg(new FactionCityChangeLineMsg51134(vlineServer.getLineid(),vlineServer.getLinename()));
			ExchangeMapTrans.trans(scene, character.getX(), character.getY(),
					character);
			GameServer.vlineServerManager
					.removeCharacterById(character.getId());
			character.setVlineserver(vlineServer);
			vlineServer.getOnlineManager().addCharacter(character);
		}

	}

	/**
	 * 将副本切到副本轮训中
	 * @param character
	 */
	public void changeToInstnaceLoop(Hero character) {
		int lineId=character.getVlineserver().getLineid();
        if(lineId!=vlineServer.getLineid()){
        	return;
        }
        vlineServer.getRuningInstanceManager().removeDownLineRoleInstanceController(character.getId());
        vlineServer.getRuningInstanceManager().addInstanceController(instancController);
	}

}
