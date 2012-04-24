package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.commons.script.IEventListener;
import net.snake.commons.GenerateProbability;
import net.snake.events.AppEventCtlor;
//import net.snake.script.ScriptEventCenter;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * 怪物死亡触发脚本掉落
 * @author serv_dev
 */
public class LostScripGoods extends Lostgoods {
	private int dropScripNum;
	public LostScripGoods(int dropScripNum){
		this.dropScripNum=dropScripNum;
	}
	@Override
	public CharacterGoods dropLostGoods(Hero character, MonsterModel monster,float dropJiacheng) {
		//int monsterType=monster.getType();
//		int type=1;
//		if(monsterType==2){
//			type=2;
//		}
		if(character==null){
			return null;
		}
		int gradeCha=0;
			gradeCha=Math.abs(character.getGrade() - monster.getGrade());
		if (isGradeLimit()) {
			if (gradeCha >= gradeChaValue) {
				return null;
			}
		}
		if(jiacheng<1){
			jiacheng=1;
		}
		if(dropJiacheng<100){
			dropJiacheng=10000;
		}
		boolean isDrop = GenerateProbability.isGenerateToBoolean(getProbability()*jiacheng*dropJiacheng/10000,probabilityMax);
		if (getMaxNum() > 1) {
			int nowNum = getNowNum();
			nowNum++;
			if (getMaxNum() == nowNum) {
				isDrop = true;
				nowNum = 0;
			}
			setNowNum(nowNum);
		}
		if (isDrop) {
//			ScriptEventCenter.getInstance().onMonsterDropGoods(null, character, dropScripNum,monster.getGrade());
			AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterGoodDrop, new Object[]{character, dropScripNum,monster.getGrade()});
		}
		return null;

	}
}
