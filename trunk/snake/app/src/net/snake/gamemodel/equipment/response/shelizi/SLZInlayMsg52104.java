package net.snake.gamemodel.equipment.response.shelizi;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 * 舍利子嵌入结果
 *@author serv_dev
 */
public class SLZInlayMsg52104 extends ServerResponse {

	public SLZInlayMsg52104(boolean issuccess) throws IOException{
		setMsgCode(52104);
		if(issuccess){
			writeByte(1);
		}else{
			writeByte(0);
		}
	}
}
