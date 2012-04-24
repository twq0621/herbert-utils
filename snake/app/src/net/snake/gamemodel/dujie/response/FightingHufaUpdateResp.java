package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

/**
 * 战斗中的护法真元有变化
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class FightingHufaUpdateResp extends ServerResponse {
	public FightingHufaUpdateResp() {
		super();
		setMsgCode(60330);
	}
	
	public void setData(int hufaId,int type , int zhenyuan){
		writeInt(hufaId);
		writeByte(type);
		writeInt(zhenyuan);
	}
}
