package net.snake.gamemodel.skill.logic.buff.dantian;

import net.snake.ai.fight.controller.EffectController;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.buff.Buff;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;


/**
 * 
 * 弹指神通
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class TanZhiShenTong extends Buff {

	public TanZhiShenTong(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		//弹指神通可以被跳闪
		if (controller.getVo().isJumping()) {
			return false;
		}
		
		VisibleObject vo = controller.getVo();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			Hero character = (Hero)vo;
			String failChannelIds = character.getMyChannelManager().getFailChannelId();
			if (failChannelIds != null && !"".equals(failChannelIds)) {
				String[] failChannelIdsArr = failChannelIds.split(",");
				if (failChannelIdsArr.length  >= 4) {//一个玩家最多4个经脉重伤，达到上限时，将对弹指神通免疫
					return false;
				} 
			}
			String nofailChannelIds = character.getMyChannelManager().getNoFailChannelId();
			if (nofailChannelIds != null && !"".equals(nofailChannelIds)) {
				String[] nofailChannelIdsArr = nofailChannelIds.split(",");
				String channelId = nofailChannelIdsArr[GenerateProbability.randomIntValue(0, nofailChannelIdsArr.length - 1)];
				//让一条经脉失效(return 经脉)  随机一个
				int debuffId = character.getMyChannelManager().getChannelBebuffId(channelId);
				SkillEffect skillEffect = SkillEffectManager.getInstance().getSkillEffectById(debuffId);
				EffectInfo effectInfo = new EffectInfo(skillEffect);
				effectInfo.setAttacker(this.effect.getAttacker());
				effectInfo.setAffecter(this.effect.getAffecter());
				effectInfo.setJingmaiId(channelId);
				controller.addEffect(effectInfo);
			}
			return false;
		} else {
			return false;
		}
		
	}
	
	
	@Override
	public boolean leave(EffectController controller) {
		//经脉失效还原
		return false;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new TanZhiShenTong(effect);
	}
}
