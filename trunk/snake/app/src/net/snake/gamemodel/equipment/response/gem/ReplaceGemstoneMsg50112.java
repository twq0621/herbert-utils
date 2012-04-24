package net.snake.gamemodel.equipment.response.gem;

import net.snake.netio.ServerResponse;


public class ReplaceGemstoneMsg50112 extends ServerResponse {
	public ReplaceGemstoneMsg50112(int flag,boolean isnew,byte type) {
		if(isnew){
			setMsgCode(50196);
		}else{
			setMsgCode(50112);
		}
			writeByte(flag);
			writeByte(type);
	}
	public ReplaceGemstoneMsg50112(int flag,boolean isnew){
		if(isnew){
			setMsgCode(50196);
		}else{
			setMsgCode(50112);
		}
			writeByte(flag);
	}
}
