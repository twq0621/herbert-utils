package net.snake.gamemodel.dujie.response;

import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.netio.ServerResponse;

/**
 * 之前存在的护法和法阵
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class ExistingHufas extends ServerResponse {
	public ExistingHufas() {
		super();
		setMsgCode(60326);
	}
	
	public void setData(int hufazhenId,GuardImg[] guardImgs) throws Exception{
//		writeByte(hufazhenId);
		int cnt=0;
		beginBatchAdd(Format_Byte);
		for (int i = 0; i < guardImgs.length; i++) {
			if (guardImgs[i].id!=-1) {
				writeInt(guardImgs[i].id);
				writeByte(guardImgs[i].type);
				writeUTF(guardImgs[i].name);
				writeInt(guardImgs[i].gs);
				writeInt(guardImgs[i].fee);
				cnt++;
			}
		}
		endBatchAdd(cnt);
	}
}
