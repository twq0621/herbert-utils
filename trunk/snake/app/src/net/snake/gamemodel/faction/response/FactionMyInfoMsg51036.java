package net.snake.gamemodel.faction.response;

import java.util.List;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.netio.ServerResponse;

/**
 * 自己帮会信息
 * 
 * @author serv_dev
 * 
 */
public class FactionMyInfoMsg51036 extends ServerResponse {

	public FactionMyInfoMsg51036(FactionController factionController, int type, int pageNum, int nowPage, int allPage, List<FactionCharacter> fcList) {
		this.setMsgCode(51036);
		try {
			this.writeByte(type);
			this.writeShort(pageNum);
			this.writeShort(nowPage);
			this.writeShort(allPage);
			this.writeInt(factionController.getFcGradeMax());
			this.writeInt(factionController.getFcWuxueJingjieMax());
			this.writeInt(factionController.getFcChannekXueweiMax());
			this.writeInt(factionController.getFcChengjiuPointMax());
			this.writeInt(factionController.getFcPrestigeMax());
			this.writeInt(factionController.getFcBossKillMax());
			this.writeInt(fcList.size());
			for (FactionCharacter fc : fcList) {
				// 帮会ID（int）,帮会名称(str),帮主名称(str),帮主等级(short),帮主门派(byte),
				// 帮主在线状态(byte,1在线，2离线),帮中人数(short),帮中最大人数(short),
				// 等级总和(int),帮旗等级(byte)，帮旗名称(str),本周BOSS（int）
				this.writeInt(fc.getCce().getId());
				this.writeUTF(fc.getCce().getViewName());
				this.writeShort(fc.getCce().getGrade());
				this.writeByte(fc.getCce().getHeadimg());
				this.writeByte(fc.getCce().getPopsinger());
				this.writeByte(fc.getCce().getIsOnline());
				this.writeByte(fc.getPosition());
				this.writeUTF(fc.getName());
				this.writeShort(fc.getCce().getWuxueJingjie());
				this.writeShort(fc.getCce().getChannelXuewei());
				this.writeShort(fc.getCce().getChengjiuPoint());
				this.writeShort(fc.getCce().getChenzhanshengwang());
				this.writeShort(fc.getCce().getBossKill());
			}
			this.writeByte(factionController.getFaction().getAccessInFaction());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
