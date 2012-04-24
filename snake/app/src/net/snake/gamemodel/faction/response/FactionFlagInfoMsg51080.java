package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionFlagInfoMsg51080 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionFlagInfoMsg51080.class);

	public FactionFlagInfoMsg51080(Faction faction, Hero character) {
		this.setMsgCode(51080);
		try {
			FactionFlag flag = faction.getFactionFlag();
			this.writeByte(flag.getfGrade());
			this.writeUTF(faction.getBangqiName());
			this.writeByte(faction.getIcoId());
			this.writeUTF(faction.getIcoStr());
			this.writeInt(faction.getFactionFlag().getBufferId());
			// this.writeInt(faction.getCopper());
			// this.writeInt(faction.getQinglongCount());
			// this.writeInt(faction.getBaihuCount());
			// this.writeInt(faction.getZhuquCount());
			// this.writeInt(faction.getXuanwuCount());
			FactionFlag nextFlag = FactionFlagManager.getInstance().getFactionFlagByGrade(flag.getfGrade() + 1);
			if (nextFlag == null) {
				this.writeByte(0);
			} else {
				this.writeByte(1);
				this.writeInt(nextFlag.getBufferId());
				this.writeInt(flag.getfCopperLimite());
				this.writeInt(flag.getfQinglongCount());
				this.writeInt(flag.getfBaihuCount());
				this.writeInt(flag.getfZhuquCount());
				this.writeInt(flag.getfXuanwuCount());
			}

			// this.writeInt(character.getContribution());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
