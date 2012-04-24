package net.snake.gamemodel.map.response.hero;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class WuxueJingJieChangeMsg10504 extends ServerResponse {
	public WuxueJingJieChangeMsg10504(Hero character) {
		setMsgCode(10504);
			writeInt(character.getId());
			writeShort(character.getWuxueJingjie());
	}
}
