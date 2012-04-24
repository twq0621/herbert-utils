package net.snake.across.msg;

import java.io.IOException;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;

import org.apache.log4j.Logger;

/**
 * 角色数据初始化跨服是否成功
 */

public class CharacteInitResult1003 extends Msg {
	private static final Logger logger = Logger.getLogger(CharacteInitResult1003.class);
	public CharacteInitResult1003(AcrossEtc ae){
		this.setFunction(1003);
		try {
			ByteArrayWriter out=this.getContentWriter();
			out.writeInt(ae.getOldCharacterId());
			out.writeInt(ae.getAscossServerId());
			if(ae.getCharacterId()!=null){
				out.writeByte(1);
				out.writeInt(ae.getCharacterId());
				out.writeInt(ae.getAccountId());
			}else{
				out.writeByte(0);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
