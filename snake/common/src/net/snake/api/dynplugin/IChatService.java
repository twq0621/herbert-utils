package net.snake.api.dynplugin;

import net.snake.netio.message.RequestMsg;
import net.snake.netio.player.GamePlayer;

public interface IChatService {
	public void chat(GamePlayer session,RequestMsg msg,long now);
}
