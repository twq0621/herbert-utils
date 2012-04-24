package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class HereTeamList10216 extends ServerResponse{

	public HereTeamList10216(int nowPage,int maxPage,List<Hero> list) {
		this.setMsgCode(10216);
		try {
			this.writeShort(nowPage);
			this.writeShort(maxPage);
			this.writeByte(list.size());
			for(Hero c:list){
				this.writeInt(c.getMyTeamManager().getMyTeam().getTeamId());
				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getMyTeamManager().getMyTeam().getTeamLevel());
				this.writeByte(c.getMyTeamManager().getMyTeam().getTeamPopulation());
				this.writeByte(c.getMyTeamManager().getZhenFaId());
				this.writeUTF(c.getMyTeamManager().getTeamDeclare());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		
	}

}
