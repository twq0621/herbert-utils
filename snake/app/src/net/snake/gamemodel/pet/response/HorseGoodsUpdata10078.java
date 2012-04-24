package net.snake.gamemodel.pet.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;
import org.apache.log4j.Logger;

public class HorseGoodsUpdata10078 extends ServerResponse {
	private static Logger logger = Logger.getLogger(HorseGoodsUpdata10078.class);

	public HorseGoodsUpdata10078(CharacterGoods fromGoods) {
		setMsgCode(10078);
		ServerResponse out = this;
		try {
			out.writeShort(fromGoods.getPosition());
			out.writeInt(fromGoods.getGoodmodelId());
			out.writeInt(fromGoods.getInHorseId());
			out.writeByte(fromGoods.getJinjie());// X
			out.writeByte(fromGoods.getPingzhiColor());// X
			out.writeByte(fromGoods.getBind());
			if (logger.isDebugEnabled()) {
				logger.debug("10078更新物品 位置:" + fromGoods.getPosition() + " modelid:" + fromGoods.getGoodmodelId() + " count:" + fromGoods.getCount());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
