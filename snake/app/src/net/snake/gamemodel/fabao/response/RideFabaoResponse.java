package net.snake.gamemodel.fabao.response;

import net.snake.netio.ServerResponse;

/**
 * 法宝(站上或者收起) 11102
 * 角色ID(int),标志byte(0-收起 1-站上),法宝物品ID(int)
 * @author jack
 *
 */
public class RideFabaoResponse extends ServerResponse {

	public RideFabaoResponse(int characterId,byte b,int modelId){
		this.setMsgCode(11102);
		writeInt(characterId);
		writeByte(b);
		writeInt(modelId);
	}
}
