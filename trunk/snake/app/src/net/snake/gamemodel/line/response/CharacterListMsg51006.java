package net.snake.gamemodel.line.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class CharacterListMsg51006 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(CharacterListMsg51006.class);

	public CharacterListMsg51006(int nowPage, List<Hero> list) {
		this.setMsgCode(51006);
		try {
			this.writeShort(nowPage);
			this.writeByte(list.size());
			for (Hero c : list) {
				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
