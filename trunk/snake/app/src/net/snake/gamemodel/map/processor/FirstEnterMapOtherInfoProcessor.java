package net.snake.gamemodel.map.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.faction.response.factioncity.FactionCityChangeLineMsg51134;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.InitScentFinish;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;

/**
 * 进入场景发其他消息10021
 * 该类不能不使用线程池,因为玩家还没有进入到场景循环里，直接往player里加这个消息处理器无法获得机会
 * 由于刷桢是要用户进入场景后才会进行的，所以此类必须实现IThreadProcessor
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10021,accessLimit=1000)
public class FirstEnterMapOtherInfoProcessor extends CharacterMsgProcessor implements
		IThreadProcessor {
	private static Logger logger = Logger
			.getLogger(FirstEnterMapOtherInfoProcessor.class);
	
	InitScentFinish isf=new InitScentFinish();
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("进入场景");
		}
		
		//SceneManager mapSceneManager = MapSceneManager.getInstance();
		Scene currentMap =character.getSceneRef();// mapSceneManager.getScene(character.getScene());
		if (currentMap == null) {
			logger.warn("enter scene ,get scene is null whil joining group进入场景最后加入组中时，得到的场景为空");
			return;
		}
		
		character.getVlineserver().addTaskInFrameUpdateThread(
				new EnterSceneRunable(currentMap, character));
		//发送其他初始化信息
		
	}
	class EnterSceneRunable implements Runnable {
		Scene scene;
		Hero character;
		//List<EffectInfo> buffList;
		
		public EnterSceneRunable(Scene scene, Hero character) {
			this.scene = scene;
			this.character = character;
		}
		@Override
		public void run() {
			scene.enterScene(character);
			VLineServer line=character.getVlineserver();
			character.sendMsg(new FactionCityChangeLineMsg51134(line.getLineid(),line.getLinename()));
			isf.sendInitScentChangeFinishOtherInfo(character);
			
		}

	}

}
