package net.snake.gamemodel.dujie.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.response.DujieAllStateResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 请求当前的渡劫层级信息
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60301)
public class DujieLvlPro extends MsgProcessor implements IThreadProcessor{

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		
		int heroId = request.getInt();
		Hero hero=session.getCurrentCharacter(Hero.class);
		if (heroId == hero.getId()) {
			send(hero);
			return;
		}
		
		hero = hero.getVlineserver().getOnlineManager().getByID(heroId);
		if(hero == null){
			return ;
		}
		send(hero);
	}
	
	private void send(Hero hero){
		DujieCtrlor ctrlor=hero.getDujieCtrlor();
		int currentLvl=ctrlor.currentTianjieNum();
		int lvlStat = ctrlor.currentTianjieStat();
		int dujieProc = ctrlor.currentTianjieProc();
		
		
		DujieAllStateResp resp = new DujieAllStateResp();
		resp.writeAllState(currentLvl, lvlStat, ctrlor.getHeroDujieData().getRoushen(),dujieProc,ctrlor.getHeroDujieData().getIsguard());
		hero.sendMsg(resp);
	}

}
