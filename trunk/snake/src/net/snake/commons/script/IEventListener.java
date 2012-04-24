package net.snake.commons.script;


public interface IEventListener {
	public static final int Evt_SceneLoop = 1;
	public static final int Evt_InstanceLoop = 2;
	public static final int Evt_MonsterDie = 3;
	public static final int Evt_MonsterHPChange = 4;
	public static final int Evt_RoleDie = 5;
	public static final int Evt_RoleLoop = 6;
	public static final int Evt_UseGoods = 7;
	public static final int Evt_InstanceDestory = 8;
	public static final int Evt_InstanceInit = 9;
	public static final int Evt_InstanceSceneLoop = 10;
	public static final int Evt_InstanceSceneInit = 11;
	public static final int Evt_InstanceSceneEnter = 12;
	public static final int Evt_InstanceSceneExit = 13;
	public static final int Evt_InstanceMonsterDie = 14;
	public static final int Evt_InstanceSceneExChange = 15;
	public static final int Evt_MonsterGoodDrop = 16;
	public static final int Evt_WorldUpdate = 17;
	public static final int Evt_HeroUpGrade = 18;
	public static final int Evt_OutOfTeam=19;
	
	public int getInterestEvent();

	public void handleEvent(SApi api, Object[] args);

}
