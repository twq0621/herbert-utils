package net.snake.gamemodel.trade.response;

import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.trade.logic.MyTradeController;
import net.snake.netio.ServerResponse;


/**
 * 交易进行后 最终结果 是成功还是失败
 * 
 * 
 */
public class TradeMsg10840 extends ServerResponse {
	public TradeMsg10840(MyTradeController mtc,int copper,int ingot) {
		this.setMsgCode(10840);
		try {
			this.writeByte(1);
			this.writeInt(copper);
			this.writeInt(ingot);
			Horse horse = mtc.getHorse();
			if (horse == null) {
				this.writeInt(-1);
			} else {
				this.writeInt(horse.getHorseModel().getId());
			}
			Collection<CharacterGoods> collection = mtc.getGoodsCollection();
			this.writeByte(collection.size());
			for (CharacterGoods cg : collection) {
				this.writeInt(cg.getGoodmodelId());
				this.writeInt(cg.getCount());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public TradeMsg10840(int msgKey ,String... vars) {
		this.setMsgCode(10840);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
