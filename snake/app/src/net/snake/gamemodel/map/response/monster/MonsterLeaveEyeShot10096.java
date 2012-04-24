package net.snake.gamemodel.map.response.monster;


import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 *
 */
public class MonsterLeaveEyeShot10096 extends ServerResponse {
	public MonsterLeaveEyeShot10096(int id){
		setMsgCode(10096);
			writeInt(id);
	}

}
