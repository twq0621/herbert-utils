package net.snake.gamemodel.chest.response;

import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.netio.ServerResponse;

/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-3-22 上午06:22:14
 */
public class ChestResponse60118 extends ServerResponse {

	public ChestResponse60118(ChestCount chestCount) {
		setMsgCode(60118);
		writeInt(chestCount.getChesttypeExchangeCount() == -1 ? 0 : chestCount.getChesttypeExchangeCount());
		writeShort(chestCount.getChesttypeReceive3011().intValue() - chestCount.getChesttypeReceiveTrue3011().intValue());
		writeShort(chestCount.getChesttypeReceive3012().intValue() - chestCount.getChesttypeReceiveTrue3012().intValue());
		writeShort(chestCount.getChesttypeReceive3013().intValue() - chestCount.getChesttypeReceiveTrue3013().intValue());
		writeShort(chestCount.getChesttypeReceive3014().intValue() - chestCount.getChesttypeReceiveTrue3014().intValue());
		writeShort(chestCount.getChesttypeReceive3015().intValue() - chestCount.getChesttypeReceiveTrue3015().intValue());

	}

}
