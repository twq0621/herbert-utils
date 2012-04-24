package net.snake.gamemodel.chest.response;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev 宝藏
 */
public class ChestTreasureMap_SquirrelResponse60112 extends ServerResponse {

	public ChestTreasureMap_SquirrelResponse60112(Hero character, Goodmodel goodmodel) {
		setMsgCode(60112);
		// 普通藏宝图 9001
		// 珍惜藏宝图 9002
		// 普通铲子 9011
		// 锋利的铁铲 9012
		// 松子3100 3200 3300
		writeByte(CommonUseNumber.byte1);// 使用的铲子数量
		writeInt(goodmodel.getId());// 宝藏类别id
	}
}
