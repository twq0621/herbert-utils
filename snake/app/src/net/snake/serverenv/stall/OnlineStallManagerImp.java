package net.snake.serverenv.stall;

import net.snake.consts.HorseContainerType;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.IGirdContainer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 在线摆摊管理
 * 
 * @author serv_dev
 * 
 */
public class OnlineStallManagerImp implements OnlineStallManager {
	private static Logger logger = Logger.getLogger(OnlineStallManagerImp.class);

	private static OnlineStallManager instance = new OnlineStallManagerImp();

	public static OnlineStallManager getInstance() {
		return instance;
	}

	private OnlineStallManagerImp() {
	}

	private volatile HashSet<Hero> treeset = new HashSet<Hero>();

	/**
	 * 更新角色摆摊信息
	 * 
	 * @param character
	 */
	public void updateStallInfo(Hero character) {
		// 初始化是否正在摆摊
		if (character.isInStall()) {
			character.setStallStatus(1);
			this.addOrUpdateStallList(character);
		} else {
			character.setStallStatus(0);
		}
	}

	@Override
	public synchronized void addOrUpdateStallList(Hero character) {
		treeset.add(character);
	}

	@Override
	public synchronized void removeFromStallList(Hero character) {
		treeset.remove(character);
	}

	@Override
	public synchronized List<Hero> getStallList() {
		ArrayList<Hero> list = new ArrayList<Hero>(treeset);
		Collections.sort(list, new StallComparateor());
		return list;
	}

	private class StallComparateor implements Comparator<Hero> {
		@Override
		public int compare(Hero o1, Hero o2) {

			// 按照摆摊人的等级进行排序，等级越高则排序越靠前
			// 等级相同则按照在售数量进行排序，在售物品+坐骑数越高，排序越靠前
			// 在售数量相等则角色编号越小，排序越靠前

			if (o1.getGrade() > o2.getGrade()) {
				return -1;
			} else if (o1.getGrade() == o2.getGrade()) {
				IGirdContainer gstall_1 = o1.getCharacterGoodController().getStallGoodsContainer();
				IGirdContainer gstall_2 = o2.getCharacterGoodController().getStallGoodsContainer();
				HorseContainer hstall_1 = o1.getCharacterHorseController().getHorseContainer(HorseContainerType.onStall);
				HorseContainer hstall_2 = o2.getCharacterHorseController().getHorseContainer(HorseContainerType.onStall);
				int count1 = gstall_1.getGoodsCount() + hstall_1.getHorseCount();
				int count2 = gstall_2.getGoodsCount() + hstall_2.getHorseCount();
				if (count1 > count2) {
					return -1;
				} else if (count1 == count2) {
					return o1.getId().compareTo(o2.getId());
				} else {
					return 1;
				}
			} else {
				return 1;
			}

		}
	}

	@Override
	public synchronized void checkCharacterStall(Hero character) {
		IGirdContainer gstall_1 = character.getCharacterGoodController().getStallGoodsContainer();
		HorseContainer hstall_1 = character.getCharacterHorseController().getHorseContainer(HorseContainerType.onStall);

		if (gstall_1.getGoodsCount() > 0 || hstall_1.getHorseCount() > 0) {
			if (character.getStallStatus() == 0) {
				character.setStallStatus(1);
				character.getMyFriendManager().updateCharacterState();

			}
			addOrUpdateStallList(character);
		} else {
			if (character.getStallStatus() == 1) {
				character.setStallStatus(0);
				character.getMyFriendManager().updateCharacterState();
			}
			removeFromStallList(character);
		}
	}

	@Override
	public synchronized List<Hero> getStallListByCharacterName(String name) {
		ArrayList<Hero> temp = new ArrayList<Hero>();
		for (Hero character : treeset) {
			if (character.getViewName().indexOf(name) != -1) {
				temp.add(character);
			}
		}
		Collections.sort(temp, new StallComparateor());
		return temp;
	}

	@Override
	public synchronized List<Hero> getStallListByGoodsName(String name) {
		ArrayList<Hero> temp = new ArrayList<Hero>();
		for (Hero character : treeset) {
			// try {
			IGirdContainer gstall_1 = character.getCharacterGoodController().getStallGoodsContainer();
			Collection<CharacterGoods> goodslist = gstall_1.getGoodsList();
			for2: for (CharacterGoods goods : goodslist) {
				if (goods.getGoodModel().getNameI18n().indexOf(name) != -1) {
					temp.add(character);
					break for2;
				}
			}
			// } catch (Exception e) {
			// logger.error(e.getMessage(), e);
			// }
		}
		Collections.sort(temp, new StallComparateor());
		return temp;
	}

	@Override
	public synchronized List<Hero> getStallListByHorseName(String name) {
		ArrayList<Hero> temp = new ArrayList<Hero>();
		for (Hero character : treeset) {

			try {
				HorseContainer hstall_1 = character.getCharacterHorseController().getHorseContainer(HorseContainerType.onStall);

				Collection<Horse> horselist = hstall_1.getHorseCollection();
				for2: for (Horse horse : horselist) {
					if (horse.getHorseModel().getNameI18n().indexOf(name) != -1) {
						temp.add(character);
						break for2;
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		Collections.sort(temp, new StallComparateor());
		return temp;
	}
}
