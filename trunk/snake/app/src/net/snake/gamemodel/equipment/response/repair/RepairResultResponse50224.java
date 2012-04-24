package net.snake.gamemodel.equipment.response.repair;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 * 维修结果消息
 *@author serv_dev
 */
public class RepairResultResponse50224 extends ServerResponse {
	private static final int MSGCODE = 50224;
	

	/**
	 * 
	 * @param result 维修结果 1:成功, 0:失败
	 * @param str	提示信息
	 */
	public RepairResultResponse50224(int result,String str){
		setMsgCode(MSGCODE);
		try {
			writeByte(result);
			writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	public RepairResultResponse50224(int result,int msgKey){
		setMsgCode(MSGCODE);
		try {
			writeByte(result);
			writeInterUTF(msgKey);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
	}
}
