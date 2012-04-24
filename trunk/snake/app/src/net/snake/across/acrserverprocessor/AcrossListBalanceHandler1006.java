package net.snake.across.acrserverprocessor;

import java.io.IOException;
import java.util.Collection;

import net.snake.GameServer;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;

/**
 * 请求当前分线的人数
 * 
 * @author serv_dev
 */
public class AcrossListBalanceHandler1006 extends AuthSTSHandler {

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (!Options.IsCrossServ) {
			return;
		}
		Collection<VLineServer> lineServersList = GameServer.vlineServerManager.getLineServersList();
		session.write(new BalanceListResult1007(lineServersList));
	}

}

class BalanceListResult1007 extends Msg {
	public BalanceListResult1007(Collection<VLineServer> list) throws IOException {
		setFunction(1007);
		ByteArrayWriter out = getContentWriter();
		if (list != null) {
			out.writeByte(list.size());
			for (VLineServer vLineServer : list) {
				int lineid = vLineServer.getLineid();
				int getplayercount = vLineServer.getOnlineManager().getplayercount();
				out.writeInt(lineid);
				out.writeInt(getplayercount);
			}
		} else {
			out.writeByte(0);
		}
	}
}
