package net.snake.gamemodel.faction.response;

import java.util.Collection;
import java.util.List;

import net.snake.gamemodel.faction.bean.BangqiAnchangFactionInfo;
import net.snake.gamemodel.faction.bean.BangqiPosition;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionFlagPositionListMsg51084 extends ServerResponse {

	private static final Logger logger = Logger.getLogger(FactionFlagPositionListMsg51084.class);

	public FactionFlagPositionListMsg51084(byte type, short pageNum, short nowPage, short allPage, Collection<BangqiPosition> collection, Hero character,
			List<BangqiAnchangFactionInfo> bangqiAnchaList) {
		this.setMsgCode(51084);
		try {
			this.writeByte(type);
			this.writeShort(pageNum);
			this.writeShort(nowPage);
			this.writeShort(allPage);
			this.writeInt(collection.size());
			SceneBangqiManager sbm = SceneBangqiManager.getInstance();
			for (BangqiPosition position : collection) {
				this.writeInt(position.getId());
				this.writeInt(position.getSceneId());
				this.writeUTF(position.getSceneNameI18n());
				this.writeShort(position.getX());
				this.writeShort(position.getY());
				SceneBangqi bangqi = sbm.getSceneBangqiByPositionId(position.getId());
				if (bangqi == null) {
					this.writeByte(0);
				} else {
					this.writeByte(1);
					Faction faction = bangqi.getFactionController().getFaction();
					this.writeByte(faction.getFactionFlag().getfGrade());
					this.writeUTF(faction.getBangqiName());
					this.writeByte(faction.getIcoId());
					this.writeUTF(faction.getIcoStr());
					this.writeUTF(faction.getViewName());
					this.writeInt(bangqi.getNowHp());
					this.writeInt(bangqi.getMaxHp());
				}
			}
			this.writeInt(character.getMyFactionManager().getSceneBangqiCount());
			String oneName = "";
			int oneCount = 0;
			String twoName = "";
			int twoCount = 0;
			int i = 0;
			for (BangqiAnchangFactionInfo ancha : bangqiAnchaList) {
				i++;
				if (i == 1) {
					if (ancha.getAnchaCount() > 1) {
						oneName = ancha.getFactionName();
						oneCount = ancha.getAnchaCount();
					}
				} else if (i == 2) {
					if (ancha.getAnchaCount() > 1) {
						twoName = ancha.getFactionName();
						twoCount = ancha.getAnchaCount();
					}
					break;
				} else if (i > 2) {
					break;
				}
			}
			this.writeUTF(oneName);
			this.writeInt(oneCount);
			this.writeUTF(twoName);
			this.writeInt(twoCount);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
