package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class NiMoXing_Yewai_Die implements IEventListener {
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger.getLogger(HuoDu_Zhuxian.class);
	private final int MonsterID = 1561;// 尼摩星野外的怪物模型ID
	


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {
		}
	}

}//end of class
