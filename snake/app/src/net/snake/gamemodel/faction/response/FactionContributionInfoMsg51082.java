package net.snake.gamemodel.faction.response;

import java.util.List;

import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionContributionInfoMsg51082 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionContributionInfoMsg51082.class);

	public FactionContributionInfoMsg51082(int pagNum, int nowPage, int allpage, List<Integer> maxList, List<FactionCharacter> fcList, Faction faction, Hero character) {
		this.setMsgCode(51082);
		try {
			this.writeShort(pagNum);
			this.writeShort(nowPage);
			this.writeShort(allpage);
			this.writeDouble(faction.getCopper());
			this.writeInt(faction.getBangzhulingCount());
			this.writeInt(faction.getQinglongCount());
			this.writeInt(faction.getBaihuCount());
			this.writeInt(faction.getZhuquCount());
			this.writeInt(faction.getXuanwuCount());
			this.writeInt(maxList.get(0));
			this.writeInt(maxList.get(1));
			this.writeInt(maxList.get(2));
			this.writeInt(maxList.get(3));
			this.writeInt(maxList.get(4));
			this.writeInt(maxList.get(5));
			this.writeInt(fcList.size());
			for (FactionCharacter fc : fcList) {
				this.writeInt(fc.getCharacterId());
				this.writeUTF(fc.getCce().getViewName());
				this.writeShort(fc.getCce().getGrade());
				this.writeByte(fc.getPosition());
				this.writeByte(fc.getCce().getIsOnline());
				this.writeInt(fc.getCopper());
				this.writeInt(fc.getBangzhulingCount());
				this.writeInt(fc.getQinglongCount());
				this.writeInt(fc.getBaihuCount());
				this.writeInt(fc.getZhuquCount());
				this.writeInt(fc.getXuanwuCount());
				this.writeInt(fc.getContribution());
			}
			this.writeInt(character.getContribution());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
