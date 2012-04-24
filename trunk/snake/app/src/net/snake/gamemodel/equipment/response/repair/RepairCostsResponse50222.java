package net.snake.gamemodel.equipment.response.repair;


import net.snake.netio.ServerResponse;


/**
 * 装备维修报价
 *@author serv_dev
 */
public class RepairCostsResponse50222 extends ServerResponse{
	private static final int MSGCODE = 50222;
	
	/**
	 * 
	 * @param general 普修价
	 * @param special 特修价
	 */
	public RepairCostsResponse50222(int general,int special){
		setMsgCode(MSGCODE);
			writeInt(general);
			writeInt(special);
		
	}
}
