package net.snake.gamemodel.instance.processor;


import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneMonsterHelper;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

@MsgCodeAnn(msgcode=50691)
public class ShowMonsterPro extends MsgProcessor implements IThreadProcessor{
	
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		Scene scene= hero.getSceneRef();
		if(scene.getInstanceModelId()!=16){
			return ;
		}
		InstanceController controller=scene.getInstanceController();
		if (controller.getAttribute("showMonster")!=null) {
			return ;
		}
		short[] newpoint = scene.getRandomPoint(hero.getX()-4, hero.getY(), 4);
		SceneMonster monster=SceneMonsterHelper.createMonsterToScene(70900217, newpoint[0], newpoint[1], 1, 1, false, scene);
		scene.getInstanceController().setAttribute("showMonster", monster);
		
	}
}
