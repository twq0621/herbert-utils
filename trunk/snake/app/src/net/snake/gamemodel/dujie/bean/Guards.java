package net.snake.gamemodel.dujie.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.snake.GameServer;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.dujie.logic.GsCalculator;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

public class Guards {
	private static GuardImg guardCache = new GuardImg();
	private static List<GuardImg> top10 = new ArrayList<GuardImg>();

	private static int counter = 0;

	/**
	 * 初始化普通护法缓存
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public static void initNormalGuards() throws SQLException {
		List cache = SystemFactory.getCharacterSqlMapClient().queryForList("t_hero_dujie.getCache");
		for (Iterator iterator = cache.iterator(); iterator.hasNext();) {
			HeroDujieData data = (HeroDujieData) iterator.next();
			GuardImg img = new GuardImg();
			img.id = data.getHeroId();
			img.gs = data.getGs();
			img.name = data.getName();
			img.mxZhenyuan = GsCalculator.calcHeroZhenyuan(img.gs);
			img.headImg=data.getHead();

			GuardImg temp = guardCache.nextNode;

			guardCache.nextNode = img;
			img.preNode = guardCache;
			img.nextNode = temp;
			if (temp != null) {
				temp.preNode = img;
			}
			counter++;
		}
		buildTop10();

	}

	/**
	 * 不再成为护法候选
	 * 
	 * @param heroId
	 */
	public static void nolongerGuard(int heroId) {
		for (GuardImg cur = guardCache.nextNode; cur != null; cur = cur.nextNode) {
			if (cur.id == heroId) {
				cur.preNode.nextNode = cur.nextNode;
				if (cur.nextNode != null) {
					cur.nextNode.preNode = cur.preNode;
				}
				counter--;
				break;
			}
		}
		buildTop10();
	}

	/**
	 * 成为护法候选
	 * 
	 * @param hero
	 */
	public static void becomeGuard(Hero hero) {
		HeroDujieData data = hero.getDujieCtrlor().getHeroDujieData();
		GuardImg img = new GuardImg();
		img.id = data.getHeroId();
		img.gs = data.getGs();
		img.mxZhenyuan = GsCalculator.calcHeroZhenyuan(img.gs);
		img.name = data.getName();
		img.headImg = hero.getHeadimg();

		GuardImg temp = guardCache.nextNode;

		guardCache.nextNode = img;
		img.preNode = guardCache;
		img.nextNode = temp;
		if (temp != null) {
			temp.preNode = img;
		}
		counter++;
		buildTop10();
	}

	public static List<GuardImg> rdmGuardImgs(int cnt, int tianjieNum, int querierId) {
		List<GuardImg> res = new ArrayList<GuardImg>();
		if (counter <= cnt) {
			for (GuardImg cur = guardCache.nextNode; cur != null; cur = cur.nextNode) {
				if (cur.id == querierId) {
					continue;
				}
				res.add(cur);
			}
			return res;
		}

		int mxIdx = counter - cnt;
		int begin = GenerateProbability.randomIntValue(0, mxIdx);
		int index = 0;
		for (GuardImg cur = guardCache.nextNode; cur != null; cur = cur.nextNode) {
			if (index < begin) {
				index++;
				continue;
			}
			if (cur.id == querierId) {
				continue;
			}
			if (index == (begin + cnt)) {
				break;
			}
			res.add(cur);
			index++;
		}
		return res;
	}

	public static void updateElement(Hero hero) {
		int heroId = hero.getId();
		GuardImg target = null;
		for (GuardImg cur = guardCache.nextNode; cur != null; cur = cur.nextNode) {
			if (cur.id == heroId) {
				cur.gs = GsCalculator.calcHeroGs(hero);
				cur.mxZhenyuan = GsCalculator.calcHeroZhenyuan(cur.gs);
				target = cur;
				break;
			}
		}

		if (target == null) {
			becomeGuard(hero);
		}
		buildTop10();

	}

	public static List<GuardImg> advancGuards() {
		return top10;
	}

	private static void buildTop10() {
		top10.clear();
		for (GuardImg cur = guardCache.nextNode; cur != null; cur = cur.nextNode) {
			int size = top10.size();
			if (size == 0) {
				top10.add(cur);
				continue;
			}
			int i = 0;
			for (; i < size; i++) {
				GuardImg temp = top10.get(i);
				if (cur.gs > temp.gs) {
					top10.add(i, cur);
					break;
				}
			}
			if (i == size) {
				top10.add(cur);
			}

			if (top10.size() == 11) {
				top10.remove(10);
			}
		}

	}

	public static void increaseGuardIncome(GuardImg[] imgs) {
		for (int i = 0; i < imgs.length; i++) {
			if (imgs[i].id == -1) {
				continue;
			}
			Hero hero = GameServer.vlineServerManager.getCharacterById(imgs[i].id);
			if (hero != null) {
				HeroDujieData data = hero.getDujieCtrlor().getHeroDujieData();
				if (data.getIncome() == hero.getGrade() * 5000) {
					continue;
				}
				if ((data.getIncome() + imgs[i].fee) > hero.getGrade() * 5000) {
					data.setIncome(hero.getGrade() * 5000);
					continue;
				}
				data.setIncome(data.getIncome() + imgs[i].fee);
				continue;
			}

			final HeroDujieData data = new HeroDujieData();
			data.setHeroId(imgs[i].id);
			data.setIncome(imgs[i].fee);
			GameServer.executorServiceForDB.execute(new Runnable() {

				@Override
				public void run() {
					try {
						HeroDujieDao.updateIncome(data);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
		}

	}

}
