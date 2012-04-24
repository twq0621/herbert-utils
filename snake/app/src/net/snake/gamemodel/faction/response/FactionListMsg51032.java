package net.snake.gamemodel.faction.response;

import java.util.List;

import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class FactionListMsg51032 extends ServerResponse {

	public FactionListMsg51032(int type, int pageNum, int nowPage, int allPage, List<Integer> maxList, List<FactionController> factionList) {
		this.setMsgCode(51032);
		try {
			this.writeByte(type);
			this.writeShort(pageNum);
			this.writeShort(nowPage);
			this.writeShort(allPage);
			this.writeInt(maxList.get(0));
			this.writeInt(maxList.get(1));
			this.writeInt(maxList.get(2));
			this.writeInt(maxList.get(3));
			this.writeInt(maxList.get(4));
			this.writeInt(factionList.size());
			for (FactionController factionC : factionList) {
				// 帮会ID（int）,帮会名称(str),帮主名称(str),帮主等级(short),帮主门派(byte),
				// 帮主在线状态(byte,1在线，2离线),帮中人数(short),帮中最大人数(short),
				// 等级总和(int),帮旗等级(byte)，帮旗名称(str),本周BOSS（int）
				this.writeInt(factionC.getFaction().getId());
				this.writeUTF(factionC.getFaction().getViewName());
				this.writeInt(factionC.getBangzhu().getCce().getId());
				this.writeUTF(factionC.getBangzhu().getCce().getViewName());
				this.writeShort(factionC.getBangzhu().getCce().getGrade());
				this.writeByte(factionC.getBangzhu().getCce().getPopsinger());
				this.writeByte(factionC.getBangzhu().getCce().getIsOnline());
				this.writeShort(factionC.getFactionCharacterSize());
				this.writeShort(FactionController.FactionCountMax);
				this.writeInt(factionC.getFactionGrade());
				this.writeByte(factionC.getFaction().getFactionFlag().getfGrade());
				this.writeUTF(factionC.getFaction().getBangqiName());
				this.writeInt(factionC.getBosskill());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
