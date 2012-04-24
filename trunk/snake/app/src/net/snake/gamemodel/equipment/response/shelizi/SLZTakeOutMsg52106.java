package net.snake.gamemodel.equipment.response.shelizi;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 * 舍利子嵌入结果
 *@author serv_dev
 */
public class SLZTakeOutMsg52106 extends ServerResponse {

	public SLZTakeOutMsg52106(boolean issuccess) throws IOException{
		setMsgCode(52106);
		if(issuccess){
			writeByte(1);
		}else{
			writeByte(0);
		}
	}
}
