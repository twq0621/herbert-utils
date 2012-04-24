package net.snake.gamemodel.map.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.response.BossRefreshTimeResponse40022;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * BOSS 刷新时间查询
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40021, accessLimit = 100)
public class BossRefreshTimeProccess40021 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		List<SceneMonster> bosslist = new ArrayList<SceneMonster>();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		Collection<Scene> worldSceneList = character.getVlineserver().getSceneManager().getWorldSceneList();
		for (Scene scene : worldSceneList) {
			List<SceneMonster> bossList2 = scene.getRefreshMonsterController().getBossList();
			if (bossList2 != null && bossList2.size() > 0) {
				for (SceneMonster sceneMonster : bossList2) {
					if (sceneMonster.getMonsterModel().isBoss()) {
						bosslist.add(sceneMonster);
					}
				}
			}
			Collection<SceneMonster> allSceneMonster = scene.getAllSceneMonster();
			for (SceneMonster sceneMonster : allSceneMonster) {
				if (sceneMonster.getMonsterModel().isBoss()) {
					bosslist.add(sceneMonster);
				}
			}
		}
		character.sendMsg(new BossRefreshTimeResponse40022(bosslist, character.getVlineserver()));
	}
}
