package net.snake.across.msg;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;


import datatransport.bean.acrossincome.AcrossIncomeTransportData;
import datatransport.bean.charactergoods.GoodsTransportData;

import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;

/**
 * 返回角色收益数据
 */

public class CharacteLingquShouyiResult1005 extends Msg {
	private static final Logger logger = Logger.getLogger(CharacteLingquShouyiResult1005.class);
	public CharacteLingquShouyiResult1005(int serverId,int oldInitiallycharacterId,int characterId,AcrossIncomeTransportData shouyi,List<GoodsTransportData> list){
		this.setFunction(1005);
		try {
			ByteArrayWriter out=this.getContentWriter();
			out.writeInt(serverId);
			out.writeInt(oldInitiallycharacterId);
			out.writeInt(characterId);
			if(shouyi!=null){
				out.writeByte(1);
				out.writeIObject(shouyi);
				out.writeIObject(list);
			}else{
				out.writeByte(0);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
