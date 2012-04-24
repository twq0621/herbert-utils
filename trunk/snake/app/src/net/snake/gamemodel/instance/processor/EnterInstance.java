package net.snake.gamemodel.instance.processor;



import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

public class EnterInstance extends CharacterMsgProcessor implements IThreadProcessor {
//	private static Logger logger = Logger.getLogger(EnterInstance.class);
	// 10701 首次请求进入副本
	@Override
	public void process(final Hero character, RequestMsg request)
			throws Exception {
//		String instanceid = request.getString();
//		if (logger.isDebugEnabled()) {
//			logger.debug("{},请求进入副本:{}", new Object[] { character, instanceid });
//		}
		
//		Instance instance =character.getVlineserver().getInstanceManager()
//				.getInstanceByID(instanceid);
//		// 初始化副本做一下地图初始化的工作
//		instance.initInstance();
//		final Scene entranceScene = instance.getEntranceScene();
//		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
//			@Override
//			public void run() {
//				ExchangeMapTrans.trans(entranceScene, entranceScene.getEnterX(),
//						entranceScene.getEnterY(), character);
//
//			}
//		});

	}
}
