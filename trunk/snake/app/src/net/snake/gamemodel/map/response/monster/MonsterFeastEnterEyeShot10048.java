package net.snake.gamemodel.map.response.monster;


import net.snake.netio.ServerResponse;


/**
 * 婚宴怪进入视野
 * 宴会ID(int),模型ID(int),名称(str),logicpoint
 *@author serv_dev
 */
public class MonsterFeastEnterEyeShot10048 extends ServerResponse {
	public MonsterFeastEnterEyeShot10048(int monsterid,int modelid,String showname,int x,int y,int feastid) {
		setMsgCode(10048);
		ServerResponse out = this;
		try {
			out.writeInt(monsterid);//宴会怪ID
			out.writeInt(modelid);//宴会模型ID
			out.writeShort(x);
			out.writeShort(y);
			out.writeUTF(showname);//显示名称
			out.writeInt(feastid);//婚宴ID
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	
}
