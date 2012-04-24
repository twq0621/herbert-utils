package net.snake.gamemodel.map.logic;

import java.util.Collection;
import java.util.Iterator;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodsContainerType;
import net.snake.consts.Popsinger;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
@MsgCodeAnn(msgcode=50695)
public class FresherResetPro extends MsgProcessor implements IThreadProcessor{

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		// clear buff
		hero.getEffectController().clearAllEffectListAndRemoveBuffOnBody();
		// clear quick bar
		Collection<CharacterSkill> skills=hero.getCharacterSkillManager().getAllCharacterSkill();
		int normalSkill = hero.getSkillid();
		for (Iterator<CharacterSkill> iterator = skills.iterator(); iterator.hasNext();) {
			CharacterSkill skill = (CharacterSkill) iterator.next();
			int skillId=skill.getSkill().getId();
			if (skillId == normalSkill) {
				continue;
			}
			int index = skill.getQuickbarindex();
			hero.getQuickbarController().removeQuickBar(index);
		}
		// clear bag
		Collection<CharacterGoods> goods=hero.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
		for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
			CharacterGoods good=iterator.next();
			hero.getCharacterGoodController().deleteCharacterGoods(good);
		}
		
		goods=hero.getCharacterGoodController().getBodyGoodsList();
		if (goods.size()>0) {
			// move it to bag
			for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
				CharacterGoods good=iterator.next();
				hero.getCharacterGoodController().movePosition(good.getPosition(),0,GoodsContainerType.onBag,(short)0,0);
			}
			// clear bag
			goods=hero.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
			for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
				CharacterGoods good=iterator.next();
				hero.getCharacterGoodController().deleteCharacterGoods(good);
			}
		}
		//add it to bag
			CharacterGoods cg = null;//CharacterGoods.createCharacterGoods(1, 89100001, 0);
			int pops=hero.getPopsinger();
			if (pops==Popsinger.futu.getPopsinger()) {
				cg =CharacterGoods.createCharacterGoods(1, 90120006, 0);
			}else if (pops==Popsinger.guming.getPopsinger()) {
				cg =CharacterGoods.createCharacterGoods(1, 90110001, 0);
			}else if (pops == Popsinger.miaoyu.getPopsinger()) {
				cg =CharacterGoods.createCharacterGoods(1, 90140016, 0);
			}else {
				cg =CharacterGoods.createCharacterGoods(1, 90130011, 0);
			}
			hero.getCharacterGoodController().addGoodsToBag(cg);
			
			hero.setJiaozi(0);
			CharacterPropertyManager.changeLijin(hero, 100);
	}

}
