package net.snake.gamemodel.skill.logic;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.persistence.SkillManager;

public class ShenTongSkillManager {

	private CharacterSkill wudiBuffST;

	private CharacterSkill speedUpSkillCoolST;

	private CharacterSkill freeFuhuoST;
	private Hero hero;

	public ShenTongSkillManager(Hero hero) {
		this.hero = hero;
		wudiBuffST = new CharacterSkill(hero, hero);
		wudiBuffST.setSkillId(SkillManager.getInstance().getWudiBuffST().getId());
		initCharacterSKill(wudiBuffST);
		
		speedUpSkillCoolST = new CharacterSkill(hero, hero);
		speedUpSkillCoolST.setSkillId(SkillManager.getInstance().getSpeedUpSkillCoolST().getId());
		initCharacterSKill(speedUpSkillCoolST);
		
		freeFuhuoST = new CharacterSkill(hero, hero);
		freeFuhuoST.setSkillId(SkillManager.getInstance().getFreeFuhuoST().getId());
		initCharacterSKill(freeFuhuoST);
	}
	
	private void initCharacterSKill(CharacterSkill characterSkill){
		characterSkill.setGrade(1);
		characterSkill.setCharacterId(hero.getId());
		characterSkill.setSkilltype(2);
	}

	/**
	 * 免费复活一次
	 */
	public void freeFuhuo() {
		if (freeFuhuoST == null) {
			return;
		}
		if (!freeFuhuoST.able2Use(hero)) {
			return;
		}
		CharacterResurrect.fuhuo(hero);
		freeFuhuoST.xiaohaoValue(hero);
	}

	/**
	 * 加入技能的冷却时间
	 */
	public void speedUpSkillCool() {
		if (speedUpSkillCoolST == null) {
			return;
		}
		if (!speedUpSkillCoolST.able2Use(hero)) {
			return;
		}
		EffectInfo effect = new EffectInfo(speedUpSkillCoolST.getSkill().getEffect());
		FighterVO fighterVO =  new FighterVO(hero, hero, hero,speedUpSkillCoolST);
		effect.setFighterVO(fighterVO);
		hero.getEffectController().addEffect(effect);
		speedUpSkillCoolST.xiaohaoValue(hero);
	}

	/**
	 * 产生一个无敌buffer
	 */
	public void wudiBuff() {
		if (wudiBuffST == null) {
			return;
		}
		if (!wudiBuffST.able2Use(hero)) {
			return;
		}
		if(!GenerateProbability.isGenerate(wudiBuffST.getSkill().getTriggerProbability(), GenerateProbability.gailv)){
			return ;
		}
		EffectInfo effect = new EffectInfo(wudiBuffST.getSkill().getEffect());
		FighterVO fighterVO =  new FighterVO(hero, hero, hero,wudiBuffST);
		effect.setFighterVO(fighterVO);
		hero.getEffectController().addEffect(effect);
		wudiBuffST.xiaohaoValue(hero);
	}

	/**
	 * 离开副本场景的时候，对技能进行清理
	 */
	public void leaveFuben(){
		speedUpSkillCoolST.setStartcd(0L);
		wudiBuffST.setStartcd(0L);
		freeFuhuoST.setStartcd(0L);
	}
}
