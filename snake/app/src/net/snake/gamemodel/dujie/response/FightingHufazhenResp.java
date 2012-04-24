package net.snake.gamemodel.dujie.response;

import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.netio.ServerResponse;

/**
 * 当前都是哪些护法在和我一起战斗。
 * [guard,....],阵法,阵法状态
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class FightingHufazhenResp extends ServerResponse {
	public FightingHufazhenResp() {
		super();
		setMsgCode(60328);
	}
	
	public void setData(GuardImg[] guardImgs,int zhenfa) throws Exception{
		beginBatchAdd(Format_Byte);
		int counter=0;
		for (int i = 0; i < guardImgs.length; i++) {
			if (guardImgs[i].id!=-1) {
				writeInt(guardImgs[i].id);
				writeByte(guardImgs[i].type);
				writeUTF(guardImgs[i].name);
				writeInt(guardImgs[i].headImg);
				writeInt(guardImgs[i].currZhenyuan);
				writeInt(guardImgs[i].mxZhenyuan);
				counter++;
			}
		}
		endBatchAdd(counter);
		
		writeByte(zhenfa);
		writeByte(counter==4?1:0);
	}
	public void only4Zhenfa(int zhenfa,int zhenStat){
		writeByte(0);
		writeByte(zhenfa);
		writeByte(zhenStat);
	}
}
