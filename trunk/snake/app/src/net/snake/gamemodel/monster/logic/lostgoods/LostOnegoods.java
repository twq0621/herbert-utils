package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.commons.GenerateProbability;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * 怪物掉落某物品操作体
 * 
 * @author serv_dev
 */
public class LostOnegoods extends Lostgoods {
	private Goodmodel gm;
	public LostOnegoods(Goodmodel gm){
		this.gm=gm;
	}
	@Override
	public CharacterGoods dropLostGoods(Hero character, MonsterModel monster,float dropJiacheng) {
		//int monsterType=monster.getType();
//		int type=1;
//		if(monsterType==2){
//			type=2;
//		}
		int gradeCha=0;
		if(character!=null){
			gradeCha=Math.abs(character.getGrade() - monster.getGrade());
		}
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
			if (gm == null) {
				return null;
			}
			CharacterGoods cg= CharacterGoods.createCharacterGoods(1, gm, monster.getLoopCount(), 0);
			if(gm.getBinding()==BindChangeType.BIND){
				cg.setBind(CommonUseNumber.byte1);
			}else{
				cg.setBind(CommonUseNumber.byte0);
			}
			if(this.getBind()==1){
				cg.setBind(CommonUseNumber.byte1);
			}
			cg.setOwner(this.isOwner());
			return cg;
		}
		return null;

	}
}
