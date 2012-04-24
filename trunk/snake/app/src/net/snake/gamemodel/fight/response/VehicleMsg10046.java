package net.snake.gamemodel.fight.response;

import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.netio.ServerResponse;

public class VehicleMsg10046 extends ServerResponse {
	
	public VehicleMsg10046(GongchengVehicle vehicle, short x, short y) {
		this.setMsgCode(10046);
		this.writeInt(vehicle.getId());
		this.writeShort(x);
		this.writeShort(y);
		this.writeShort(vehicle.getHurtScope());

	}

}
