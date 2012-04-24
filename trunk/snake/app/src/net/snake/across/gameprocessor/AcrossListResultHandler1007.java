package net.snake.across.gameprocessor;

import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;


/**
 *	返回的跨服务信息
 *@author serv_dev
 */
public class AcrossListResultHandler1007 extends AuthSTSHandler {

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if(Options.IsCrossServ){
			return;
		}
		ByteArrayReader read = message.getContentReader();
		byte readByte = read.readByte();
		for(int i=0;i<readByte;i++){
			int serverid = read.readInt();
			AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(serverid);
			int onlinenum = read.readInt();
			asd.setOnlineNum(onlinenum);
		}
		session.close(true);
	}

	
}
