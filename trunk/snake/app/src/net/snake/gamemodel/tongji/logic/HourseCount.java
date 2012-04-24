package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;

/**
 * 坐骑模块统计
 * 
 * @author serv_dev
 * 
 */
public class HourseCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public final static int catchHuangbiaoType = 7;
	public final static int catchHeifengType = 22;
	public final static int catchChituType = 23;
	public final static int catchDiruType = 24;
	public final static int qualityType = 52;
	public final static int savvyType = 53;
	public final static int potentialType = 54;
	public final static int learnSkillType = 55;
	public final static int jinjieJinjiaShenniuType = 45;
	public final static int jinjieTianshankuanglangType = 46;
	public final static int jinjieXuemingShenghuType = 47;
	public final static int jinjieChiyanMoshiType = 48;
	public final static int jinjieAoshiyanbaoType = 49;
	public final static int jinjieZijinqilinType = 50;
	public final static int jinjieLonglinshengshouType = 51;

	public HourseCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 22的儒马 23赤兔马
	 * 
	 * @param horse
	 * @param type
	 */
	public void catchHorse(HorseModel hm) {
		int kind = hm.getKind();

		if (kind == 1) {// name.contains("黄骠马")
			catchHorse(catchHuangbiaoType);
		} else if (kind == 2) {// name.contains("黑风马")
			catchHorse(catchHeifengType);
		} else if (kind == 3) {// name.contains("赤兔马")
			catchHorse(catchChituType);
		} else if (kind == 4) {// name.contains("的卢马")
			catchHorse(catchDiruType);
		}
	}

	private void catchHorse(int type) {
		myAchieveManger.characterAchieveToDBCount(type, 1);
	}

	public void learnSkillCount(Horse horse) {
		int count = horse.getHorseskillManager().getAllSkillCount();
		myAchieveManger.catchAchieveToDBCount(learnSkillType, count);
	}

	public void jinjieHorse(Horse horse) {
		HorseModel hm = horse.getHorseModel();
		int kind = hm.getKind();
		if (kind == 5) {// 金甲神牛
			jinjieHorse(jinjieJinjiaShenniuType);
			horse.getCharacter().getTaskController().checkAllCurTaskMountUpGrade();
		} else if (kind == 6) {// name.equals("天煞狂狼")
			jinjieHorse(jinjieTianshankuanglangType);
		} else if (kind == 7) {// name.equals("玄冥圣虎")
			jinjieHorse(jinjieXuemingShenghuType);
		} else if (kind == 8) {// name.equals("赤焰魔狮")
			jinjieHorse(jinjieChiyanMoshiType);
		} else if (kind == 9) {// name.equals("傲世炎豹")
			jinjieHorse(jinjieAoshiyanbaoType);
		} else if (kind == 10) {// name.equals("紫金麒麟")
			jinjieHorse(jinjieZijinqilinType);
		} else if (kind == 11) {// name.equals("龙鳞圣兽")
			jinjieHorse(jinjieLonglinshengshouType);
		}
	}

	private void jinjieHorse(int type) {
		myAchieveManger.characterAchieveToDBCount(type, 1);
	}

}
