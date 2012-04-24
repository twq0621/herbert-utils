package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.Iterator;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class Lianzhan_MonsterDie implements IEventListener {
//	public final int mianguSkillId = 52003;
//	public final int huagongSkillId = 52002;
//	public final int dianxueSkillId = 53010;
	
	private static final int Normal_MnGrade = 20;
	private static final int Normal_MxGrade = 65;
	
	private static final int[] Boss_Grade={25,30,35,40,45,50,55,60};
	private static final short Boss_x=123;
	private static short Boss_y = 100;
	public final int yichiTime = 1000*25;
	public final int bossYichiTime = 1000*5;


	private boolean isFinishiInstance(SScene scene){
		Collection<SMonster> collection=scene.getAllCurrentSceneMonster();
		if(scene.isHaveRefreshMonster()){
			return false;
		}
		if(collection.size()==0){
			return true;
		}
		for(SMonster monster:collection){
			if(!monster.isDie()){
				return false;
			}
		}
		return true;
	}
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}
	@Override
	public void handleEvent(SApi api, Object[] args) {
		//SInstance instance,		SMonster monster
		SInstance instance =(SInstance)args[0];
		SMonster monster = (SMonster)args[1];
		
		if (instance.getInstanceId() != 1) {
			return;
		}
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		Iterator<SRole> iterator = roleCollection.iterator();
		SRole role = iterator.next();
		if (role == null) {
			return;
		}
		// 普通怪物刷新
		if (monster.isPT()) {
			int monsterCount = monster.getInstanceLianzhanGrade();
			if (monsterCount >= 8) {
				if(isFinishiInstance(monster.getSceneRef())){
					instance.missionComplete();
				}
				return;
			}
			int grade =role.getMaxTeamerGrade() + (monsterCount-2);
			float expJiacheng = 1 + (monsterCount) * 0.1f;
			grade = getNomalMonsterGrade(grade);
			int ptId = instance.getInstanceMonsterId(1, grade);
			SMonster sceneMonster = api.createTimerMonster(ptId,
					monster.getOriginalX(), monster.getOriginalY(), 4,
					1, expJiacheng, false, monster.getSceneRef(),yichiTime);
//			if (monsterCount == 3) {
//				api.addMonsterSkill(sceneMonster, mianguSkillId, 1000);
//			} else if (monsterCount == 4) {
//				api.addMonsterSkill(sceneMonster, huagongSkillId, 1000);
//			} else if (monsterCount == 5) {
//				api.addMonsterSkill(sceneMonster, dianxueSkillId, 1000);
//			} else if (monsterCount > 5) {
//				api.addMonsterSkill(sceneMonster, mianguSkillId, 1000);
//				api.addMonsterSkill(sceneMonster, huagongSkillId, 1000);
//				api.addMonsterSkill(sceneMonster, dianxueSkillId, 1000);
//			}
			api.changeInstanceSceneMonsterflushCount(sceneMonster, monsterCount+1);
		}
		// boss怪物刷新
		if (monster.isBoss()) {
			int bossCount =  monster.getInstanceLianzhanGrade();
			if (bossCount >= 8) {
				if(isFinishiInstance(monster.getSceneRef())){
					String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
					api.sendMsg(role, (byte)7, tips);
					instance.setAttribute("endInstance", Long.valueOf(System.currentTimeMillis()+60000));
					
					instance.missionComplete();
					
					api.sendCountDownMsg(role, 60000, false);
				}
				return;
			}
			api.changeInstanceBossflushCount(instance, 1);
			int grade = role.getMaxTeamerGrade() + (bossCount) * 2+1;
			grade = getBossMonsterGrade(grade);
			
			int expJiacheng = bossCount+1;
			int defenceJiacheng = bossCount + 2;
			int bossId = instance.getInstanceMonsterId(3, grade);
			SMonster sceneMonster = api.createTimerMonster(bossId,
					Boss_x, Boss_y, 4,
					defenceJiacheng, expJiacheng, false, monster.getSceneRef(),bossYichiTime);
//			api.addMonsterSkill(sceneMonster, mianguSkillId, 1000);
//			api.addMonsterSkill(sceneMonster, huagongSkillId, 1000);
//			api.addMonsterSkill(sceneMonster, dianxueSkillId, 1000);
			api.changeInstanceSceneMonsterflushCount(sceneMonster, bossCount+1);
			for(SRole roles:roleCollection){
				api.sendMsg(roles, (byte)7, GlobalLanguage.exChangeParamToString(GlobalLanguage.instanceLianzhanKillBossStr,bossCount+"",(bossCount+1)+""));
			}
		}
	}
	
	private int getNomalMonsterGrade(int grade){
		if (grade < Normal_MnGrade) {
			return Normal_MnGrade;
		}
		if (grade > Normal_MxGrade) {
			return Normal_MxGrade;
		}
		return grade;
	}
	
	private int getBossMonsterGrade(int grade){
		for (int i = 0; i < Boss_Grade.length; i++) {
			if (grade < Boss_Grade[i]) {
				return Boss_Grade[i];
			}
		}
		
		return Boss_Grade[ Boss_Grade.length-1];
	}
}
