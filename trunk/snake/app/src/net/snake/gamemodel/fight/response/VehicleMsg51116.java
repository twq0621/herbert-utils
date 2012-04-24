package net.snake.gamemodel.fight.response;

import net.snake.gamemodel.fight.bean.CharacterVehicle;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.netio.ServerResponse;

/**
 * 返回炮弹面板信息
 * 
 * @author serv_dev
 * 
 */
public class VehicleMsg51116 extends ServerResponse {

	public VehicleMsg51116(CharacterVehicle cv, GongchengVehicle gongchengVehicle, int npcId) {
		this.setMsgCode(51116);
		this.writeInt(npcId);
		this.writeInt(gongchengVehicle.getId());
		this.writeInt(gongchengVehicle.getHurtValue());
		this.writeShort(gongchengVehicle.getWuchaScope());
		this.writeShort(gongchengVehicle.getHurtScope());
		if (cv == null) {
			this.writeInt(0);
		} else {
			this.writeInt(cv.getVehicleCount());
		}
		this.writeInt(gongchengVehicle.getCopper());
	}

}
