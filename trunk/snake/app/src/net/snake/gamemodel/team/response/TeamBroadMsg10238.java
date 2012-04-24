package net.snake.gamemodel.team.response;








import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 队伍中玩家升级 广播玩家升级后属性
 *
 */
public class TeamBroadMsg10238 extends ServerResponse{
	public TeamBroadMsg10238(Hero character){
		this.setMsgCode(10238);
			this.writeInt(character.getId());
			this.writeShort(character.getGrade());
		    this.writeInt(character.getNowHp());
		    this.writeInt(character.getPropertyAdditionController().getExtraMaxHp());
		    this.writeInt(character.getNowMp());
		    this.writeInt(character.getPropertyAdditionController().getExtraMaxMp());
		
	    
	}
	
}
