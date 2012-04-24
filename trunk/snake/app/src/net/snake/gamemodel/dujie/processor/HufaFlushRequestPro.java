package net.snake.gamemodel.dujie.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.dujie.logic.DujiePlugin;
import net.snake.gamemodel.dujie.persistence.TianshenDataTbl;
import net.snake.gamemodel.dujie.response.HufaPageListResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.InstancePlugin;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 请求刷新护法列表
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60323)
public class HufaFlushRequestPro extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = (Hero)session.getCurrentCharacter(Hero.class);
		InstanceController controller = hero.getMyCharacterInstanceManager().getInstanceController();
		if (controller == null) {
			return ;
		}
		if (controller.getAttribute("flushed")!=null) {
			return ;
		}
		
		
		
		
		InstancePlugin plugin = controller.getPlugin();
		
		int type = request.getByte();
		switch (type) {
		case GuardImg.Guards_Super:
			superGuard(session);
			break;
		case GuardImg.Guards_Normal:
			int copper = hero.getCopper();
			if (copper < 99) {
				return ;
			}
			CharacterPropertyManager.changeCopper(hero, -99, CopperAction.CONSUME);
			normalGuard(session,(DujiePlugin)plugin);
			break;
		case GuardImg.Guards_Advanced:
			advanceGuard(session,(DujiePlugin)plugin);
			break;

		}
		
		
	}

	private void superGuard(GamePlayer session) {
		List<Tianshen> ts = TianshenDataTbl.getInstance().getPagedTianshens(1);
		HufaPageListResp resp = new HufaPageListResp(1);
		resp.setTianshens(GuardImg.Guards_Super, ts);

		session.sendMsg(resp);
	}

	private void advanceGuard(GamePlayer session,DujiePlugin plugin) {
		Hero hero = session.getCurrentCharacter(Hero.class);
		List<GuardImg> guards=Guards.advancGuards();
		plugin.advanceGuards(guards);
		
		HufaPageListResp resp = new HufaPageListResp(1);
		resp.setHeros(GuardImg.Guards_Advanced, guards,hero.getDujieCtrlor().currentTianjieNum());
		session.sendMsg(resp);
	}

	private void normalGuard(GamePlayer session,DujiePlugin plugin) {
		Hero hero = session.getCurrentCharacter(Hero.class);
		int heroId = hero.getId();
		List<GuardImg> guards=Guards.rdmGuardImgs(10,hero.getDujieCtrlor().currentTianjieNum(),heroId);
		plugin.normalGuards(guards);
		
		HufaPageListResp resp = new HufaPageListResp(1);
		resp.setHeros(GuardImg.Guards_Normal, guards,hero.getDujieCtrlor().currentTianjieNum());
		session.sendMsg(resp);
	}
}
