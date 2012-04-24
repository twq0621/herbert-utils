package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

public class DujieAllStateResp extends ServerResponse {
	public DujieAllStateResp() {
		super();
		setMsgCode(60300);
	}
	public void writeAllState(int dujieLvl,int dujieStat,int roushen,int dujieProcess,int isguard){
		writeByte(dujieLvl);
		writeByte(dujieStat);
		writeInt(roushen);
		writeInt(dujieProcess);
		writeByte(isguard);
	}
}
