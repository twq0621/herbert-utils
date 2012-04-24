package net.snake.gamemodel.dujie.response;

import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.netio.ServerResponse;

/**
 * 护法的货币体系
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class HufaCurrencyResp extends ServerResponse {
	public HufaCurrencyResp() {
		super();
		setMsgCode(60322);
		writeByte(3);
		
		writeByte(GuardImg.Guards_Normal);//护法类型:byte
		writeByte(1);//刷新货币类型:byte
		writeInt(99);//刷新消费额度:int
		writeByte(1);//雇佣货币类型:byte
		
		writeByte(GuardImg.Guards_Advanced);
		writeByte(2);
		writeInt(0);
		writeByte(1);
		
		writeByte(GuardImg.Guards_Super);
		writeByte(2);
		writeInt(0);
		writeByte(2);
	}
	
}
