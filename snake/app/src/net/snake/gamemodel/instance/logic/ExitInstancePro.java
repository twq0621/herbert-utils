package net.snake.gamemodel.instance.logic;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.response.DujieSucResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

@MsgCodeAnn(msgcode=10731)
public class ExitInstancePro extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		int instanceId = hero.getSceneRef().getInstanceModelId();
		if(instanceId == 9){
			hero.getSceneRef().getInstanceController().setAttribute("sucComp",Long.valueOf(0));
			DujieCtrlor ctrlor = hero.getDujieCtrlor();
			int num = ctrlor.currentTianjieNum();
			Dujie dujie = DujieDataTbl.getInstance().getDujie(num);
			int need = dujie.getZhenyuan();
			int proc = ctrlor.currentTianjieProc();
			
			DujieSucResp sucResp = new DujieSucResp(proc>=need?1:0,proc);
			hero.sendMsg(sucResp);
			
			return ;
		}
		
		if (hero.worldPos[0]==0) {
			Scene newScene = hero.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
			short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
			ExchangeMapTrans.trans(newScene, point[0], point[1], hero);
			return ;
		}
		hero.getMyCharacterInstanceManager().exitInstance2World(hero.worldPos[0], (short)hero.worldPos[1], (short)hero.worldPos[2]);
	}
}
