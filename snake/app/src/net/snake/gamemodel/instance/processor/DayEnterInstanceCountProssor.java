package net.snake.gamemodel.instance.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.response.TodayEnterInstanceCount10712;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 今日进入副本信息 10711
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10711)
public class DayEnterInstanceCountProssor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int npcId = request.getInt();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		String[] instanceStr = npc.getFunctionTransfer().split(",");
		List<Scene> list = new ArrayList<Scene>();
		for (int i = 0; i < instanceStr.length; i++) {
			Scene scene = character.getVlineserver().getSceneManager().getInstanceScene(Integer.parseInt(instanceStr[i]));
			if (scene != null) {
				list.add(scene);
			}
		}
		character.sendMsg(new TodayEnterInstanceCount10712(character, list));
	}

}
