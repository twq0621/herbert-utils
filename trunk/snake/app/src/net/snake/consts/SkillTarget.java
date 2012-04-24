package net.snake.consts;
/**
 * 技能目标
 * @author serv_dev
 */
public enum SkillTarget {
	/**自己*/
	Me,
	/**自己所在的小队*/
	MyTeam,
	/**友好目标*/
	FriendTarget,
	/**范围内敌对目标*/
	ScopeHostilityTarge,
	/**目标*/
	Target,
	/**点目标*/
	PointTarget,
	/** 对自己的配偶释放*/
	Spouse,
	/**对正在与您的配偶发生战斗的目标使用*/
	SpouseFighting,
	/**传送*/
	Transport,
	/**友好目标和自己*/
	FriendTargetAndMe,
	/**无*/
	Null;
	
	public static SkillTarget getTarget(int code){
		switch (code) {
		case 1:
			return Me;
		case 2:
			return MyTeam;
		case 3:
			return FriendTarget;
		case 4:
			return ScopeHostilityTarge;
		case 6:
			return Target;
		case 7:
		case 8:
			return PointTarget;
		case 9:
			return Spouse;
		case 10:
			return SpouseFighting;
		case 11:
			return Transport;
		case 12:
			return FriendTargetAndMe;
		default:
			return Null;
		}
	}
}
