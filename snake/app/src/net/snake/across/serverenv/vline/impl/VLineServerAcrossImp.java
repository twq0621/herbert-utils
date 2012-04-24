package net.snake.across.serverenv.vline.impl;

import net.snake.across.faction.AcrossFactionManager;
import net.snake.across.vehicle.AcrossVehicleManager;
import net.snake.serverenv.vline.imp.VLineServerImp;


/**
 * 跨服战场分线实现
 * 
 * @author serv_dev
 */
public class VLineServerAcrossImp extends VLineServerImp {
	private AcrossFactionManager acrossFactionManager;
	private AcrossVehicleManager acrossVehicleManager;

	@Override
	public void startUp() {
		super.startUp();
		acrossFactionManager = new AcrossFactionManager();
		acrossVehicleManager = new AcrossVehicleManager();
	}
	@Override
	public AcrossFactionManager getAcrossFactionManager() {
		return acrossFactionManager;
	}
	@Override
	public AcrossVehicleManager getAcrossVehicleManager() {
		return acrossVehicleManager;
	}

}
