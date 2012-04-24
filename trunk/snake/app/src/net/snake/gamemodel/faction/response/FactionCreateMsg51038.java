package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.netio.ServerResponse;


public class FactionCreateMsg51038 extends ServerResponse {
	public FactionCreateMsg51038(int msgKey,String...vars) {
		this.setMsgCode(51038);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public FactionCreateMsg51038(FactionController factionC) {
		this.setMsgCode(51038);
		try {
			this.writeByte(1);
			this.writeInt(factionC.getFaction().getId());
			this.writeUTF(factionC.getFaction().getViewName());
			FactionFlag flag=factionC.getFaction().getFactionFlag();
			this.writeByte(flag.getfGrade());
			this.writeUTF(factionC.getFaction().getBangqiName());
			this.writeByte(factionC.getFaction().getIcoId());
			this.writeUTF(factionC.getFaction().getIcoStr());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
