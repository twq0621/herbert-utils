package net.snake.gamemodel.tongji.logic;

import java.util.List;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 角色击杀怪物行为统计
 * @author serv_dev
 *
 */
public class KillMonsterCount {
	private MyCharacterAchieveCountManger myAchieveManger;
	public final  static int shuoshuIdMAX = 5013;
	public final  static int shuoshuIdMIN = 5001;
	public final  static int oneMonsterType = 5;// 第一次杀怪
	public final  static int shuoshuType = 11;// 杀死硕鼠
	public final  static int colorType = 13;// 变异怪
	public final  static int bossType = 14;// 变异怪
	boolean isFirst = false; // 第一次击杀怪物成就是否完成
	boolean shuoshu = false;// 击杀硕鼠成就是否全部完成

	public KillMonsterCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 击杀怪物行为统计
	 * 
	 * @param monster
	 */
	public void killMonsterCount(SceneMonster monster) {
		 killoneMonster();
		 killShuoshuMonster(monster);
		 killBianyiMonster(monster);
		 killBossMonster(monster);
	}
	/**
	 * 第一次击杀怪物 
	 */
	private void killoneMonster() {
		if (isFirst) {
			return;
		}
		isFirst = myAchieveManger.isOverCountDbByType(oneMonsterType);
		if (isFirst) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(oneMonsterType, 1);
	}

	private void killShuoshuMonster(SceneMonster monster) {
		int modelId = monster.getModel();
		if (modelId < shuoshuIdMIN || modelId > shuoshuIdMAX) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(shuoshuType,1);
	}
	private void killBianyiMonster(SceneMonster monster) {
		if (monster.getColorTypeCount() < 1&&!monster.getMonsterModel().isJY()) {
			return;
		}
		Hero character = myAchieveManger.getCharacter();
		if (character.getGrade() > monster.getGrade()&&isFirst(colorType)) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(colorType, 1);
	}
	/**
	 * 击杀boss
	 * @param monster
	 */
	private void killBossMonster(SceneMonster monster) {
		if (!monster.getMonsterModel().isBoss()) {
			return;
		}
		Hero character = myAchieveManger.getCharacter();
		if (character.getGrade() > monster.getGrade()&&isFirst(bossType)) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(bossType, 1);
	}
/**
 * 返回true 表示第一次成就已经获得  返回false 没有获得 是第一次击杀
 * @param type
 * @return
 */
	private boolean isFirst(int type){
		List<Achieve> list = AchieveManager.getInstance()
		.getAchieveListByChildKind(type);
		Achieve achieve=list.get(list.size()-1);
		return myAchieveManger.getCharacter().getMyCharacterAchieveManger().isHaveDbAchieve(achieve);
	}

}
