package net.snake.gamemodel.fight.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class VehicleMsg51122 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(VehicleMsg51122.class);

	/**
	 * 发射失败
	 */
	public VehicleMsg51122(int msgKey, String... vars) {
		this.setMsgCode(51122);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 炮弹发射成功
	 * 
	 * @param vehieveId
	 * @param point
	 * @param hurtScope
	 * @param dieRoles
	 * @param hurtRoles
	 */
	public VehicleMsg51122(int vehieveId, short[] point, int hurtScope, List<Hero> hurtRoles, List<Hero> dieRoles) {
		this.setMsgCode(51122);
		try {
			this.writeByte(1);
			this.writeInt(vehieveId);
			this.writeShort(point[0]);
			this.writeShort(point[1]);
			this.writeShort(hurtScope);
			this.writeShort(hurtRoles.size());
			for (Hero role : hurtRoles) {
				this.writeUTF(role.getViewName());
			}
			this.writeShort(dieRoles.size());
			for (Hero role : dieRoles) {
				this.writeUTF(role.getViewName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
