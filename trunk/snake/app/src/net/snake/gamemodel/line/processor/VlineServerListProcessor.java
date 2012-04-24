/**
 * 
 */
package net.snake.gamemodel.line.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.line.response.VlineServerList40300;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.vline.VLineServer;

/**
 * 返回服务器状态 ip 在线人数，服务器号，相应的消息号13。
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 40209, accessLimit = 10)
public class VlineServerListProcessor extends MsgProcessor implements IThreadProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		// 集合倒装
		Collection<VLineServer> collection = GameServer.vlineServerManager.getLineServersList();
		ArrayList<VLineServer> serverlist = new ArrayList<VLineServer>();
		for (VLineServer vline : collection) {
			/* if (vline.isChatSession()) { */
			serverlist.add(vline);
			/* } */
		}

		// 分线排序
		Collections.sort(serverlist, new Comparator<VLineServer>() {
			@Override
			public int compare(VLineServer o1, VLineServer o2) {
				int p1 = o1.getOnlineManager().getplayercount() * 100 + o1.getLineid();
				int p2 = o2.getOnlineManager().getplayercount() * 100 + o2.getLineid();
				return p1 < p2 ? -1 : (p1 == p2 ? 0 : 1);
			}
		});
		session.sendMsg(new VlineServerList40300(serverlist));
	}
}
