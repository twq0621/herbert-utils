package net.snake.gamemodel.monster.logic;
//package net.snake.bean.monster;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import net.snake.ai.fight.bean.FighterVO;
//import net.snake.bean.VisibleObject;
//import net.snake.bean.character.Character;
//import net.snake.bean.scenes.Scene;
//
///**
// *@author serv_dev
// */
//public class SceneLivelyMonster extends SceneMonster {
//	Logger log=LoggerFactory.getLogger(getClass());
//
//	@Override
//	public void onEnterScene(Scene scene) {
//		super.onEnterScene(scene);
//		log.debug("助兴怪"+getMonsterModel().getName()+"进入场景x="+getX()+"y="+getY());
//	}
//	
//	
////
////	@Override
////	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
////		if (isDie())
////			return;
////		Character character = (Character) whoAttackMe;
////		int lind = character.getVlineserver().getLineid();
////		if (!(whoAttackMe instanceof Character)) {
////			return;
////		}
////		fighterVO.setHurtValue(2);
////		changeNowHp(-fighterVO.getHurtValue());
////		getEnmityManager().addEnmityValue(fighterVO.getFighter(),fighterVO.getEnmityValue());
////		getEnmityManager().addHurtValue(fighterVO.getFighter(),fighterVO.getHurtValue());
////		if (isDie()) {
////			die(fighterVO.getFighter());
////		}
////	}
//	
//}
