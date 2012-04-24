package net.snake.gameserver.event.monster;



import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class GongSunZhi_Zhuxian_HpChange implements IEventListener{
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger.getLogger(HuoDu_Zhuxian.class);
	private final int MonsterID = 1310;// 公孙止的怪物模型ID
	private int hitCount = 0;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {//2.	每10秒钟(改成10次)一次释放大范围杀伤性技能（技能等级=40
			hitCount++;
			if(hitCount > 10) {
				//@todo取消
				//api.setMonsterSkill(monster, 70004, 40, 10000, 1);
				hitCount = 0;
			}
			//todo
			//	3.	不停换目标进行攻击
		}
		
	}

}//end of class
