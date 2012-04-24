package net.snake.gamemodel.tongji.logic;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.hero.bean.Hero;
/**
 * 角色死亡某块统计
 * 
 * @author serv_dev
 * 
 */
public class CharacterDieCount {
	MyCharacterAchieveCountManger myAchieveManger;

	public final  static int CharacterDieType = 56;// 角色死亡类别


	public CharacterDieCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 击杀怪物行为统计
	 * 
	 * @param monster
	 */
	public void characterDie(Hero attacker) {
		otherKillMe(attacker);
	}

	/**
	 * 角色死亡次数统计
	 */
	public void otherKillMe(Hero killer) {
		if (killer ==null) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(CharacterDieType, 1);
	}
}
