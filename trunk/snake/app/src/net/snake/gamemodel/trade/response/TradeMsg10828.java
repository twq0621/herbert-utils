package net.snake.gamemodel.trade.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * S-A|B: 交易失败 对方ID：playerId(int)，是否接受邀请成功（byte）0失败｛失败原因：reason(str) ｝/1成功
 * 进行交易操作
 * 
 * 
 */
public class TradeMsg10828 extends ServerResponse {
	public TradeMsg10828(int othercId, String str) {
		this.setMsgCode(10828);
		try {
			this.writeInt(othercId);
			this.writeByte(0);
			this.writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public TradeMsg10828(Hero character) {
		this.setMsgCode(10828);
		try {
			this.writeInt(character.getId());
			this.writeByte(1);
			this.writeUTF(character.getViewName());
			this.writeShort(character.getGrade());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
