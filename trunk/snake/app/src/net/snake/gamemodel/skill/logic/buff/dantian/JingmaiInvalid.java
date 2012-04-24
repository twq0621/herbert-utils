package net.snake.gamemodel.skill.logic.buff.dantian;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50218;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 
 * 经脉失效
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class JingmaiInvalid extends Buff {

	public JingmaiInvalid(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		//弹指神通可以被跳闪
		if (controller.getVo().isJumping()) {
			return false;
		}
		
		//让一条经脉失效(return 经脉)  随机一个
		String jingmai = effect.getJingmaiId();
		VisibleObject vo = controller.getVo();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			Hero character = (Hero)vo;
			return character.getMyChannelManager().failMyChnanel(jingmai);
		}
		return true;
	}
	
	
	@Override
	public boolean leave(EffectController controller) {
		
		String jingmai = effect.getJingmaiId();
		VisibleObject vo = controller.getVo();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			Hero character = (Hero)vo;
			character.getMyChannelManager().noFailMyChnanel(jingmai);
			String failChannelIds = character.getMyChannelManager().getFailChannelId();
			if (failChannelIds == null || "".equals(failChannelIds)) {
				character.getMyDazuoManager().endLiaoshang();
			}
			character.sendMsg(new ChannelResponse50218(character));
		}
		
		
		//经脉失效还原
		return true;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new JingmaiInvalid(effect);
	}
}
