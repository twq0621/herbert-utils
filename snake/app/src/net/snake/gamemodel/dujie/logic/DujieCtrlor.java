package net.snake.gamemodel.dujie.logic;

import java.sql.SQLException;
import java.util.Calendar;

import net.snake.commons.program.Updatable;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.dujie.bean.HeroDujieDao;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.response.DujieAllStateResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.ServerResponse;

/**
 * 渡劫控制器
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieCtrlor implements Updatable {
	/** 已达成 */
	public static final byte Lvl_Closed = 1;
	/** 开放的 */
	public static final byte Lvl_Unlock = 0;
	/** 未开放 */
	public static final byte Lvl_Lock = 2;

	private HeroDujieData dto = null;

	private Hero ownerHero;
	/** 当日凌晨时间戳 */
	private long timestamp00 = 0;
	// private boolean incomeNotify = false;

	private int week = 0;
	private boolean resetProc = false;

	public DujieCtrlor(Hero hero) {
		this.ownerHero = hero;
	}

	public HeroDujieData getHeroDujieData() {
		return dto;
	}

	public void setHeroDujieData(HeroDujieData dto) {
		this.dto = dto;
	}

	public int currentTianjieNum() {
		return dto.getNum();
	}

	public int currentTianjieStat() {
		int num = dto.getNum();
		Dujie jie = DujieDataTbl.getInstance().getDujie(num);
		if (jie.getZhenyuan() <= dto.getProcess()) {
			return Lvl_Closed;
		}
		if (jie.getMax_point() <= dto.getRoushen()) {
			return Lvl_Unlock;
		}
		return Lvl_Lock;
	}

	public int currentTianjieProc() {
		return dto.getProcess();
	}

	/**
	 * 完成最小的开放天劫层数
	 */
	public void finishDujie() {
		if (dto.getNum() == 13) {
			dto.setRoushen(0);
			return;
		}
		dto.setNum(dto.getNum() + 1);
		dto.setRoushen(0);
		dto.setProcess(0);
	}

	public void init() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		timestamp00 = calendar.getTimeInMillis();

		try {
			HeroDujieData dto = HeroDujieDao.getByHeroId(ownerHero.getId());
			if (dto == null) {
				generateDujieRecord(ownerHero);
			} else {
				this.dto = dto;
				if (currentTianjieStat() == Lvl_Closed) {
					finishDujie();
				}
			}

			if (this.dto.getIncome() > this.ownerHero.getGrade() * 5000) {
				dto.setIncome(this.ownerHero.getGrade() * 5000);
			}

			if (this.dto.getIsguard() == 0) {
				return;
			}
			Guards.updateElement(ownerHero);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(HeroDujieData dto) throws SQLException {
		HeroDujieDao.insert(dto);
	}

	public void save2DB() throws SQLException {
		dto.setGs(GsCalculator.calcHeroGs(ownerHero));
		HeroDujieDao.update(dto);
	}

	private void generateDujieRecord(net.snake.gamemodel.hero.bean.Hero hero) {
		HeroDujieData data = new HeroDujieData();
		data.setHeroId(hero.getId());
		data.setName(hero.getViewName());
		data.setNum(1);
		data.setRoushen(0);
		data.setProcess(0);
		data.setIsguard(0);
		data.setGs(GsCalculator.calcHeroGs(hero));
		data.setIncome(0);
		data.setHead(hero.getHeadimg());
		this.dto = data;
		try {
			HeroDujieDao.insert(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ServerResponse increaseRoushen(int inc) {
		dto.setRoushen(dto.getRoushen() + inc);
		int max = DujieDataTbl.getInstance().getDujie(this.currentTianjieNum()).getMax_point();
		if (dto.getRoushen() > max) {
			dto.setRoushen(max);
		}
		int stat = currentTianjieStat();
		DujieAllStateResp resp = new DujieAllStateResp();
		resp.writeAllState(currentTianjieNum(), stat, dto.getRoushen(), currentTianjieProc(), dto.getIsguard());
		return resp;
	}

	/**
	 * 领取收益
	 * 
	 * @param hero
	 */
	public void receiveGuardIncome() {
		int income = dto.getIncome();
		if (this.ownerHero.getGrade() * 5000 < income) {
			dto.setIncome(income - this.ownerHero.getGrade() * 5000);
			CharacterPropertyManager.changeCopper(ownerHero, this.ownerHero.getGrade() * 5000, CopperAction.ADD_GUARD);
		} else {
			dto.setIncome(0);
			CharacterPropertyManager.changeCopper(ownerHero, income, CopperAction.ADD_GUARD);
		}
	}

	@Override
	public void update(long now) {
		long diff = now - timestamp00;
		if (diff >= 86400000L) {
			timestamp00 = timestamp00 + 86400000L;// 过了零点
			// incomeNotify = false;
			getWeekday(now);
			resetProc = false;
			return;
		}
		if (week == Calendar.TUESDAY) {
			if (!resetProc) {
				if (diff >= 10800000l) {
					this.dto.setProcess(0);

					int currentLvl = this.currentTianjieNum();
					int lvlStat = this.currentTianjieStat();
					int dujieProc = this.currentTianjieProc();

					DujieAllStateResp resp = new DujieAllStateResp();
					resp.writeAllState(currentLvl, lvlStat, this.getHeroDujieData().getRoushen(), dujieProc, this.getHeroDujieData().getIsguard());
					ownerHero.sendMsg(resp);

					resetProc = true;
				}
			}

		}
		// if (incomeNotify) {
		// return;
		// }
		// if (diff >= 72000000L) {// 当日八点
		// int income = dto.getIncome();
		// if (ownerHero.getGrade() * 5000 < income) {
		// HufaIncomeResp incomeResp = new HufaIncomeResp(ownerHero.getGrade() * 5000,ownerHero.getGrade() * 5000);
		// ownerHero.sendMsg(incomeResp);
		// }else {
		// HufaIncomeResp incomeResp = new HufaIncomeResp(income,ownerHero.getGrade() * 5000);
		// ownerHero.sendMsg(incomeResp);
		// }
		// incomeNotify = true;
		// }

	}

	public boolean canReceive() {
		long now = System.currentTimeMillis();
		long diff = now - timestamp00;
		if (diff >= 72000000L && diff <= 75600000L) {
			return true;
		}

		return false;
	}

	private void getWeekday(long ms) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ms);
		week = calendar.get(Calendar.DAY_OF_WEEK);
	}
}