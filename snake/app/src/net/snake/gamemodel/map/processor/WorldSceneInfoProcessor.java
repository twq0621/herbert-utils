package net.snake.gamemodel.map.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.WorldSceneUtil;
import net.snake.gamemodel.map.response.WorldSceneInfo10132;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 10131
 * 世界地图信息返回
 * @author serv_dev
 *
 */
@MsgCodeAnn(msgcode = 10131)
public class WorldSceneInfoProcessor extends CharacterMsgProcessor implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int sceneId=request.getInt();
		if(character.getSceneRef().getInstanceModelId()!=0){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,907)); 
			return;
		}
		Scene scene=character.getVlineserver().getSceneManager().getScene(sceneId);
		if(scene==null){
			return;
		}
		int yuanbao=WorldSceneUtil.getToTargetSceneLength(character.getSceneRef(), sceneId,character.getVlineserver().getSceneManager());
        character.sendMsg(new WorldSceneInfo10132(scene,yuanbao));
	}

}
