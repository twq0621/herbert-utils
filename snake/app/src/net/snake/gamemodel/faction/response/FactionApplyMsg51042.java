package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


public class FactionApplyMsg51042 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionApplyMsg51042.class);
	public FactionApplyMsg51042(int msgKey,String... vars) {
		this.setMsgCode(51042);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public FactionApplyMsg51042(FactionController factionC) {
		this.setMsgCode(51042);
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
