package net.snake.gamemodel.heroext.wudao.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;

public class WDUpgradeResult53266 extends ServerResponse {
	public WDUpgradeResult53266(boolean tag) throws IOException {
		setMsgCode(53266);
		writeBoolean(tag);
	}
}
