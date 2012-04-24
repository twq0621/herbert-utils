/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import java.util.Collection;
import java.util.Iterator;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.wedding.response.WeddingMsg52362;
import net.snake.gamemodel.wedding.response.WeddingMsg52374;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;


/**
 * 配偶的武功界面
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 52361)
public class WedderWugongInfoProcessor52361 extends CharacterMsgProcessor implements IThreadProcessor{

	/* (non-Javadoc)
	 * @see net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake.bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
       int wedderId=character.getMyFriendManager().getRoleWedingManager().getWedderId();
       if(wedderId<1){
    		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
       }
      Hero wedder= GameServer.vlineServerManager.getCharacterById(wedderId);
      if(wedder==null){
    		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17575));
			return;
      }
      character.sendMsg(new WeddingMsg52362(wedder));
      sendUpdateSkillShowMsg(wedder,character);
	}
	public void sendUpdateSkillShowMsg(Hero wedder,Hero my){
		Collection<CharacterSkill> col = wedder.getCharacterSkillManager().getAllCharacterSkill();
		int size = col.size() - 1;
		if (size > 0) {
			int[] list = new int[size * 2];
			int i = 0;
			for (Iterator<CharacterSkill> iterator = col.iterator(); iterator.hasNext();) {
				CharacterSkill skill = iterator.next();
				if (skill.isPinKan()) {
					continue;
				}
				int addgrade = wedder.getCharacterSkillManager().getskilladdGrade(skill.getSkillId());
				list[i] = skill.getSkillId();
				list[i + 1] = addgrade;
				i = i + 2;
			}
			my.sendMsg(new WeddingMsg52374(list));
		}
	}
}
