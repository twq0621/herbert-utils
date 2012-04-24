package net.snake.gamemodel.team.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 申请入队消息发送给队长
 *
 */
public class TeamApplyToLeaderMsg10190 extends ServerResponse{
public TeamApplyToLeaderMsg10190(Hero character){
	this.setMsgCode(10190);
	try {
		this.writeInt(character.getId());
		this.writeUTF(character.getViewName());
		this.writeShort(character.getGrade());
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
	
}
}
