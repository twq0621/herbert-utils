package net.snake.gamemodel.friend.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class CharacterListMsg10302 extends ServerResponse {

	public CharacterListMsg10302(int nowPage,List<Hero> list) {
		this.setMsgCode(10302);
		try {
			this.writeShort(nowPage);
			this.writeByte(list.size());
			for(Hero c:list){
				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}

}
