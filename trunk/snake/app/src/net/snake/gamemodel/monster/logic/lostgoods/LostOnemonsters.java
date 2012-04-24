package net.snake.gamemodel.monster.logic.lostgoods;

import java.util.List;

import net.snake.GameServer;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 怪物死亡掉落怪物
 * @author serv_dev
 */
public class LostOnemonsters extends Lostmonsters {
	private List<MonsterModel> list;
	public LostOnemonsters(List<MonsterModel> list){
		this.list=list;
	}
	@Override
	public SceneMonster dropLostMonsters(Hero character, MonsterModel monster) {
//		int monsterType=monster.getType();
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
		
		jiacheng = GameServer.doubActivityManager.getCacheDoubActivity().getBaolv();
		if(jiacheng<1){
			jiacheng=1;
		}
		boolean isDrop = GenerateProbability.isGenerateToBoolean(
				getProbability()*jiacheng, GenerateProbability.gailv);
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
			if (list.size() ==0) {
				return null;
			}
			int rand=GenerateProbability.randomIntValue(0, list.size()-1);
			MonsterModel mm=list.get(rand);
			SceneMonster sm=new SceneMonster();
			sm.setModel(mm.getId());
			sm.setMonsterModel(mm);
	        return sm;
		}
		return null;

	}
}
