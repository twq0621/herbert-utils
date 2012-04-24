package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastMessageResponse52248 extends ServerResponse {
//	结果(byte  0提醒有婚宴,2领红包),举办日(int  1普通,2富贵,3豪华),婚宴规模号(byte)
	public WedFeastMessageResponse52248(int tag,int data,int type,int lineid){
		setMsgCode(52248);
			writeByte(tag);
			writeInt(data);
			writeByte(type);
			writeByte(lineid);
	}
}
