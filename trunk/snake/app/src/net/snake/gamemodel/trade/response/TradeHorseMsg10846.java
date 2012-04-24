package net.snake.gamemodel.trade.response;


import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.netio.ServerResponse;


/**
 *马在交易栏中取出成功(回复坐骑自由身)

 * 
 * 
 */
public class TradeHorseMsg10846 extends ServerResponse {
	public TradeHorseMsg10846( IMyTradeController mtc) {
		this.setMsgCode(10846);
			this.writeInt(mtc.getCharacter().getId());
			Horse horse=mtc.getHorse();
			if(horse==null){
				this.writeInt(-1);
				this.writeInt(-1);
			}else{
				this.writeInt(horse.getHorseModel().getId());
				this.writeInt(horse.getId());
			}
	}
}
