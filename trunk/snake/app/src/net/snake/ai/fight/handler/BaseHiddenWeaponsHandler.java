package net.snake.ai.fight.handler;
import java.util.Collection;
import java.util.List;

import net.snake.ai.formula.AttackFormula;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.HiddenWeapons;


public abstract class BaseHiddenWeaponsHandler {

	private VisibleObject vo;
	protected BaseHiddenWeaponsHandler(VisibleObject vo){
		this.vo = vo;
	}
	

	
	/**
	 * 
	 * 找到范围内的目标
	 * @param centerObject  以自身为中心或者以目标为中心
	 * @param distance   施法距离
	 * @param voInRegion  临时存放目标对象列表
	 * 
	 */

	public void _findCharacterInScene(short centerX,short centerY,
			HiddenWeapons hiddenWeapons, List<VisibleObject> voInRegion) {
		int i = 0;
		Collection<Hero> cs = vo.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		for(Hero character : cs){
			if (!character.isZeroHp() && AttackFormula.atkIsEnable2(centerX, centerY, character.getX(), character.getY(),hiddenWeapons.getAttackDis())) {// 在效果的范围内
				if (i >= hiddenWeapons.getAttackTargets()) break;
				voInRegion.add(character);
				i++;
			}
		}
	}
	
	
	/**
	 * 找到范围内的目标
	 * @param centerObject  以自身为中心或者以目标为中心
	 * @param distance   施法距离
	 * @param voInRegion  临时存放目标对象列表
	 */

	public void _findSceneMonsterInScene(VisibleObject centerObject,HiddenWeapons hiddenWeapons, List<VisibleObject> voInRegion){
		int i = 0;
		Collection<SceneMonster> ms=centerObject.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		for(SceneMonster vo : ms){
			if (vo.isHorse()) continue;
			if (!vo.isDie() && AttackFormula.atkIsEnable2(centerObject.getX(), centerObject.getY(), vo.getX(), vo.getY(),hiddenWeapons.getAttackDis())) {// 在效果的范围内
				if (i >= hiddenWeapons.getAttackTargets()) break;
				voInRegion.add(vo);
				i++;
			}
		}
	}
	
}
