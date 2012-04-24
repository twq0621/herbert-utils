package net.snake.gamemodel.dujie.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.InstancePlugin;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 选择渡劫阵法。
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60327)
public class SlctHufazhenPro extends MsgProcessor implements IThreadProcessor{
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		Scene scene = hero.getSceneRef();
		
		int id = request.getByte();
		InstanceController controller=scene.getInstanceController();
		if (controller == null) {
			return ;
		}
		InstancePlugin plugin=controller.getPlugin();
		if (plugin == null) {
			return ;
		}
		
		GuardImg[] guards=plugin.getAllGuardImgs();
		int cnt=0;
		for(int i=0;i< guards.length;i++){
			if(guards[i].id!=-1){
				cnt++;
			}
		}
		if(cnt!=4){
			return ;
		}
		plugin.setHufazhen(id);
	}
}
