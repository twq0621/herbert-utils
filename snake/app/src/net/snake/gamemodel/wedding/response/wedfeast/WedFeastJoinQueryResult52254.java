package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastJoinQueryResult52254 extends ServerResponse{
//	场景ID(int),怪物x(int), 怪物y(int), 距离(int)(
	public WedFeastJoinQueryResult52254(int feastid,byte feasttype,int monsterid,String feastname,byte gifttag,int sceneid,int monsterx,int monstery,int juli,int shenyu,int jinyan,int zhenqi,int leijihongbao,int giftmin){
		setMsgCode(52254);
		ServerResponse out = this;
		try {
//			out.writeByte(0);
			out.writeInt(feastid);
			out.writeByte(feasttype);
			out.writeInt(monsterid);
			out.writeUTF(feastname);
			out.writeByte(gifttag);
			out.writeInt(sceneid);
			out.writeInt(monsterx);
			out.writeInt(monstery);
			out.writeInt(juli);
			if (gifttag == 1) {
				out.writeInt(shenyu);
				out.writeInt(jinyan);
				out.writeInt(zhenqi);
				out.writeInt(leijihongbao);
			} else {
				out.writeInt(shenyu);
				out.writeInt(jinyan);
				out.writeInt(zhenqi);
				out.writeInt(leijihongbao);
			}
			out.writeInt(giftmin);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
//	public WedFeastJoinQueryResult52554(int tag){
//		setMsgCode(52254);
//		try {
//			output.writeByte(tag);
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
//	}
//	红包标记(byte)(1{剩余份数(int),请求人今日婚宴所获经验(int),…真气(int)},0{今日累计出红包数(int)})
}
