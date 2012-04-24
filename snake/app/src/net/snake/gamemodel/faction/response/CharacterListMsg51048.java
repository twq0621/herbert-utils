package net.snake.gamemodel.faction.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class CharacterListMsg51048 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(CharacterListMsg51048.class);
	public CharacterListMsg51048(String name,int nowPage,List<Hero> list) {
		this.setMsgCode(51048);
		try {
			this.writeUTF(name);
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
