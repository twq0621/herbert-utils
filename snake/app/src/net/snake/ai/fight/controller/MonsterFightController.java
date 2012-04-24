package net.snake.ai.fight.controller;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.MonsterEffectHandler;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.MonsterSkillManager;


public class MonsterFightController extends BaseFightController {

	private int time = 0;//怪物攻击间隔
	private long beginTime = 0l;
	public MonsterFightController(VisibleObject vo) {
		super(vo);
		setEffectHandler(new MonsterEffectHandler(vo));
		if (vo.getSkillManager().getPingkanSkill() == null) {
			time = 0;
		}else {
			time = vo.getSkillManager().getPingkanSkill().getCoolingtime();
		}
		
	}

	/**
	 * 自动战斗
	 */
	@Override
	public void autofight() {
		VisibleObject target=getVo().getTarget();
		
		if(target==null){
			return;
		}

		if (target.isJiTui()) {
			return;
		}
		if (target.getSceneRef() == null) {
			SceneMonster monster = (SceneMonster)getVo();
			monster.setTarget(null);
			return;
		}
		
		long now = System.currentTimeMillis();
		if (now - beginTime > time) {
			if (((MonsterSkillManager)getVo().getSkillManager()).autoFight()) {
				if(target.getObjectState()==VisibleObjectState.Idle){
					target.setObjectState(VisibleObjectState.BeAttacked);
				}
				beginTime = now;
			}
		}
	}
	
	@Override
	public boolean fight(FighterVO fighterVo) {
		if ( super.fight(fighterVo)) {
			long nowstart = System.currentTimeMillis();
			fighterVo.getCharacterSkill().setStartcd(nowstart);
			return true;
		}
		return false;
	}
	
}
