package net.snake.gamemodel.tongji.logic;

import java.util.Collection;

import net.snake.consts.Position;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;

/**
 * 某块行为统计
 * @author serv_dev
 *
 */
public class EquipmentCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public static final  int wuqiQianghuaType = 33;// 任务计数
	public static final  int yifuQianghuaType = 34;// 第一次杀怪
	public static final  int takeOnManxingType = 35;// 12套装慢行
	public static final  int wuqiChongzhiType = 36;//
	public static final  int yifuChongzhiType = 37;//
	public static final  int takeOnZiseType = 38;// 12套装慢行
	public static final  int wuqiHechengType = 39;//
	public static final  int yifuHechengType = 40;//
	public static final  int takeOnDengjieType = 41;// 12套装慢行
	public static final  int baoshiHechengType = 42;//
	public static final  int baoshiRongheType = 43;//

	public EquipmentCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 装备强化调用此接口
	 * 
	 * @param cg
	 */
	public void qianghuaEquipment(CharacterGoods cg) {
		if (cg.getJinjie() < 1) {
			return;
		}
		Goodmodel gm = cg.getGoodModel();
		if (gm.getPosition() == 1) {
			qianghuaWuqiEquipment(cg);
		} else if (gm.getPosition() == 3) {
			qianghuaYifuEquipment(cg);
		}
	}

	private void qianghuaWuqiEquipment(CharacterGoods cg) {
		myAchieveManger.catchAchieveToDBCount(wuqiQianghuaType, cg.getJinjie());
	}

	private void qianghuaYifuEquipment(CharacterGoods cg) {
		myAchieveManger.catchAchieveToDBCount(yifuQianghuaType, cg.getJinjie());
	}

	/**
	 * 套装接口
	 */
	public void takeOnOfferEquipment() {
		Collection<CharacterGoods> collection = myAchieveManger.getCharacter()
				.getCharacterGoodController().getBodyGoodsContiner()
				.getGoodsList();
		takeOnManxingEquipment(collection);
		takeOnZiseEquipment(collection);
		takeOnDengjieEquipment(collection);
	}

	private void takeOnManxingEquipment(Collection<CharacterGoods> collection) {
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.getJinjie() >= 10&&cg.getPosition()!=Position.POSTION_TESHU) {
				count++;
			}
		}
		if (count == 0) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(takeOnManxingType, count);
	}
	private void takeOnZiseEquipment(Collection<CharacterGoods> collection) {
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.getPingzhiColor() >=4&&cg.getPosition()!=Position.POSTION_TESHU) {
				count++;
			}
		}
		if (count == 0) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(takeOnZiseType, count);
	}
	private void takeOnDengjieEquipment(Collection<CharacterGoods> collection) {
		if (collection.size() < 12) {
			return;
		}
		int count = 0;
		int grade = -1;
		for (CharacterGoods cg : collection) {
			if (grade == -1) {
				grade = cg.getGoodModel().getGrade();
			}
			if (cg.getGoodModel().getGrade() == grade&&cg.getPosition()!=Position.POSTION_TESHU) {
				count++;
			}
		}
		if (count < 12) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(takeOnDengjieType, 1);
	}

	/**
	 * 重置装备属性
	 */
	public void chongzhiEquipment(CharacterGoods cg) {
		if (cg.getPingzhiColor() < 4) {
			return;
		}
		Goodmodel gm = cg.getGoodModel();
		if (gm.getPosition() == 1||gm.getPosition() == 2) {
			chongzhiWuqiEquipment(cg);
		} else if (gm.getPosition() == 3) {
			chongzhiYifuEquipment(cg);
		}
	}

	private void chongzhiWuqiEquipment(CharacterGoods cg) {
		myAchieveManger.characterAchieveToDBCount(wuqiChongzhiType, 1);
	}

	private void chongzhiYifuEquipment(CharacterGoods cg) {
		myAchieveManger.characterAchieveToDBCount(yifuChongzhiType, 1);
	}

	/**
	 * 装备合成升级
	 */
	public void hechengEquipment(CharacterGoods cg) {
		Goodmodel gm = cg.getGoodModel();
		if (gm.getGrade() < 1) {
			return;
		}
		if (gm.getPosition() == 1) {
			hechengWuqiEquipment(cg);
		} else if (gm.getPosition() == 3) {
			hechengYifuEquipment(cg);
		}
	}

	private void hechengWuqiEquipment(CharacterGoods cg) {
		int grade = cg.getGoodModel().getGrade();
		myAchieveManger.catchAchieveToDBCount(wuqiHechengType, grade);
	}

	private void hechengYifuEquipment(CharacterGoods cg) {
		int grade = cg.getGoodModel().getGrade();
		myAchieveManger.catchAchieveToDBCount(yifuHechengType, grade);
	}
	public void baoshiHechengEquipment(CharacterGoods cg) {
		int grade = cg.getGoodModel().getGrade();
		myAchieveManger.catchAchieveToDBCount(baoshiHechengType, grade);
	}
	public void baoshiRonghe(CharacterGoods cg) {
		int grade = cg.getBaoshiNum();
		myAchieveManger.catchAchieveToDBCount(baoshiRongheType, grade);
	}
}
