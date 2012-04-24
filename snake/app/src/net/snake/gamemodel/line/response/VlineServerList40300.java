package net.snake.gamemodel.line.response;

import java.util.Collection;

import net.snake.netio.ServerResponse;
import net.snake.serverenv.vline.VLineServer;

public class VlineServerList40300 extends ServerResponse {
	public VlineServerList40300(Collection<VLineServer> list) {
		setMsgCode(40300);
		ServerResponse out = this;
		try {
			out.writeByte(list.size());
			for (VLineServer bean : list) {
				out.writeByte(bean.getLineid());
				//String ipStr = GameServer.serverentry.getLoginserverip();
				//String loginserverport = GameServer.serverentry.getLoginserverport();
				//out.writeUTF(ipStr);
				//out.writeUTF(loginserverport);
				out.writeUTF(bean.getLinename());
				out.writeInt(bean.getOnlineManager().getplayercount());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
