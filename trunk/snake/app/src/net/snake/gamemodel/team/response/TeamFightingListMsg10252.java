package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.netio.ServerResponse;

/**
 * 发送阵法列表
 *
 */
public class TeamFightingListMsg10252 extends ServerResponse {
	public TeamFightingListMsg10252(List<TeamFighting> ownerlist,List<TeamFighting> learnlist){
		this.setMsgCode(10252);
			int size=ownerlist.size()+learnlist.size();
			this.writeByte((byte)size);
			for(TeamFighting tf:ownerlist){
				this.writeShort(tf.getId());
				this.writeByte(CommonUseNumber.byte1);
			}
			for(TeamFighting tf:learnlist){
				this.writeShort(tf.getId());
				this.writeByte(CommonUseNumber.byte0);
			}
	}
	
}
