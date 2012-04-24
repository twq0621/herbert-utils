package net.snake.gamemodel.map.logic;


import net.snake.api.ISceneListener;
import net.snake.api.IStateListener;
import net.snake.gamemodel.dujie.logic.DujieBuffListener;
import net.snake.gamemodel.dujie.logic.DujieHurtListener;
import net.snake.gamemodel.dujie.logic.DujieNeidanListener;
import net.snake.gamemodel.dujie.logic.DujieSceneListener;
import net.snake.gamemodel.dujie.logic.DujieSpdrugListener;
import net.snake.gamemodel.dujie.logic.DujieZeningListener;
import net.snake.gamemodel.instance.listener.DazhaishineiAttackListener;
import net.snake.gamemodel.instance.listener.DazhaishineiHurtListener;
import net.snake.gamemodel.instance.listener.DazhaishineiStatListener;
import net.snake.gamemodel.instance.listener.JianlaoAttackListener;
import net.snake.gamemodel.instance.listener.JianlaoHurtListener;
import net.snake.gamemodel.map.listener.FresherZeningListener;

public class SceneListenerFactory {
	private static ISceneListener recordListener = new RecordPrepositionListener();
	private static IStateListener fresherZeningListener = new FresherZeningListener();
	
	public static void setSceneUseItemListener(Scene scene) {
		
		
		if (scene.isInstanceScene()) {
			int instanceId = scene.getInstanceModelId();
			if (instanceId == 9) {
				scene.addUseItemListener(new DujieNeidanListener());
				scene.addUseItemListener(new DujieSpdrugListener());

				scene.addSceneListener(new DujieSceneListener());
				scene.addStateListener(new DujieZeningListener());
				scene.addHurtListener(new DujieHurtListener());
				scene.addBuffListener(new DujieBuffListener());
			}else if (instanceId == 11||instanceId == 18) {
				scene.addAttackListener(new JianlaoAttackListener());
				scene.addHurtListener(new JianlaoHurtListener());
				scene.addStateListener(new DazhaishineiStatListener());
			}else if (instanceId == 14||instanceId == 20) {
				scene.addAttackListener(new DazhaishineiAttackListener());
				scene.addHurtListener(new DazhaishineiHurtListener());
				scene.addStateListener(new DazhaishineiStatListener());
			}
//			else if(instanceId == 1){
//				scene.addShockListener(new WushuangShockListener());
//			}
			else if (instanceId == 16) {
				scene.addStateListener(fresherZeningListener);
			}
				
		}else {
			scene.addSceneListener(recordListener);
		}

	}
}
