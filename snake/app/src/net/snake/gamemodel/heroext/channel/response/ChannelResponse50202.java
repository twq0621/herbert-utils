package net.snake.gamemodel.heroext.channel.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * 返回角色经脉总体信息
 * 
 * @author serv_dev
 */
public class ChannelResponse50202 extends ServerResponse {

//	public ChannelResponse50202(Hero character) {
//		setMsgCode(50202);
//		ChannelJiSuanTools channelJiSuanTools = character.getMyChannelManager().getJiSuanTools();
//		ChannelJiSuanTools channeljiSuanZheLongTools = character.getMyChannelManager().getJiSuanZhenLongTools();
//		String weizhijinmaiString;
//
//		String zhenlongString = character.getChannelRealdragon();
//		if (zhenlongString.length() < 3 || StringUtil.isEmpty(zhenlongString)) {
//			// 普通
//			writeByte(0);
//			weizhijinmaiString = channelJiSuanTools.getJinmaiLieBiao();
//		} else {
//			// 真龙
//			writeByte(1);
//			weizhijinmaiString = channeljiSuanZheLongTools.getJinmaiLieBiao();
//		}
//
//		if ("".equals(weizhijinmaiString)) {
//			writeByte(0);
//		} else {
//
//			String weizhi[] = weizhijinmaiString.split(";");
//			writeByte(weizhi.length);
//			for (int i = 0; i < weizhi.length; i++) {
//				String[] string = weizhi[i].split(",");
//				writeByte(Byte.parseByte(string[0]));
//				writeShort(Short.valueOf(string[1]));
//
//			}
//		}
//	}

}
