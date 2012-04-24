package net.snake.gamemodel.equipment.response.shelizi;

import java.io.IOException;

import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.netio.ServerResponse;


/**
 * 舍利子
 *@author serv_dev
 */
public class QuerySheliInlayMsg52102 extends ServerResponse {

	/**
	 * 
	 * @param length 己嵌数量 
	 * @param split	舍利子MODEL,武功类型
	 * @param maxStoneNum 最大可嵌数量
	 * @throws IOException 
	 */
	public QuerySheliInlayMsg52102(int num, String[] split, int maxStoneNum) throws IOException {
		setMsgCode(52102);
//		己嵌数量(short){序号ID(byte),模型id(int),武功ID(int)},最大可嵌数量(short)
		writeShort(split.length);
		if(split!=null&&split.length>0){
			for(int i=0;i<split.length;i++){
				writeInt(i + Position.HUSHENFU_SHENLIZI_BEGIN);//从这个Position.FUSHENFU_SHENLIZI_BEGIN位置开始
				String[] split2 = split[i].split(Symbol.DOUHAO);
				writeInt(Integer.parseInt(split2[0]));
				writeInt(Integer.parseInt(split2[1]));
			}
		}
		writeShort(maxStoneNum);
	}
}
