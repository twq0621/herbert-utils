package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.logic.SceneFeastMonster;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastJoinQueryResult52254;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 * 
 *@author serv_dev
 */
@MsgCodeAnn(msgcode = 52253,accessLimit=100)
public class WedFeastJoinQueryProcess52253 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int feastid = request.getInt();
		int monsterid = request.getInt();
		
		WedFeast feast=WedFeastManager.getInstance().getFeastByID(feastid);
		if(feast==null){
			return ;
		}
		if(feast!=null){
			boolean isgift = feast.isGift(character);
			if(isgift&&monsterid<=0){
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,50031));
			}
			SceneFeastMonster monster= character.getVlineserver().getWedFeastManagerVline().getFeastMonsterById(monsterid);
//			int joinCount = WedFeastManager.getInstance().joinCount(character);
			int joinCount=character.getDayInCome().getCountData().getfFeastGift();
			Integer exp = character.getDayInCome().getCountData().getfFeastExp();
			Integer zhenqi = character.getDayInCome().getCountData().getfFeastZhengqi();
			String name=monster!=null?monster.geReplacetName():feast.getWedFeastName();
			byte gift=(byte) (isgift?1:0);
			int surplus=monster!=null?monster.getSurplus():0;
			int sceneid=monster!=null?monster.getScene():-1;
			int x=monster!=null?monster.getX():-1;
			int y=monster!=null?monster.getY():-1;
			int juli=monster!=null?ClientConfig.FEAST_EAT_MAX_LANG:-1;
			character.sendMsg(new WedFeastJoinQueryResult52254(feastid,feast.getFasttype().byteValue(),monsterid,name,gift,sceneid,x,y,juli,surplus,exp,zhenqi,joinCount,monster.getFeast().getConfig().getGiftcost()));
		}
		
	}
}


