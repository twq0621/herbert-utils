package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class CharDayInComeData implements IbatisEntity{

	/**
	 * 色角ID t_character_dayincome_count.f_characterid
	 *
	 */
	private Integer fCharacterid;
	/**
	 * 经验统计 t_character_dayincome_count.f_exp
	 *
	 */
	private Long fExp;
	/**
	 * 杀怪统计 t_character_dayincome_count.f_killmonster
	 *
	 */
	private Integer fKillmonster;
	/**
	 * 得获装备数*改为副本次数 t_character_dayincome_count.f_equip
	 *
	 */
	private Integer fEquip;
	/**
	 * 完成任务数 t_character_dayincome_count.f_finshtask
	 *
	 */
	private Integer fFinshtask;
	/**
	 * 获得真气数 t_character_dayincome_count.f_zhengqi
	 *
	 */
	private Integer fZhengqi;
	/**
	 * 击杀BOSS数 t_character_dayincome_count.f_killboss
	 *
	 */
	private Integer fKillboss;
	/**
	 * 取得座骑数 t_character_dayincome_count.f_gethorse
	 *
	 */
	private Integer fGethorse;
	/**
	 * 完成阵法数 t_character_dayincome_count.f_finshinstance
	 *
	 */
	private Integer fFinshinstance;
	/**
	 * 月 t_character_dayincome_count.f_month
	 *
	 */
	private Integer fMonth;
	/**
	 * 日 t_character_dayincome_count.f_day
	 *
	 */
	private Integer fDay;
	/**
	 * 年 t_character_dayincome_count.f_year
	 *
	 */
	private Integer fYear;
	/**
	 * 日当婚宴累计获取真气 t_character_dayincome_count.f_feast_zhengqi
	 *
	 */
	private Integer fFeastZhengqi;
	/**
	 * 当日累计婚宴获取经验 t_character_dayincome_count.f_feast_exp
	 *
	 */
	private Integer fFeastExp;
	/**
	 * t_character_dayincome_count.f_shengwang
	 *
	 */
	private Integer fShengwang;
	/**
	 * 今日送出红包数 t_character_dayincome_count.f_feast_gift
	 *
	 */
	private Integer fFeastGift;

	/**
	 * 色角ID t_character_dayincome_count.f_characterid
	 * @return  the value of t_character_dayincome_count.f_characterid
	 *
	 */
	public Integer getfCharacterid() {
		return fCharacterid;
	}

	/**
	 * 色角ID t_character_dayincome_count.f_characterid
	 * @param fCharacterid  the value for t_character_dayincome_count.f_characterid
	 *
	 */
	public void setfCharacterid(Integer fCharacterid) {
		this.fCharacterid = fCharacterid;
	}

	/**
	 * 经验统计 t_character_dayincome_count.f_exp
	 * @return  the value of t_character_dayincome_count.f_exp
	 *
	 */
	public Long getfExp() {
		return fExp;
	}

	/**
	 * 经验统计 t_character_dayincome_count.f_exp
	 * @param fExp  the value for t_character_dayincome_count.f_exp
	 *
	 */
	public void setfExp(Long fExp) {
		this.fExp = fExp;
	}

	/**
	 * 杀怪统计 t_character_dayincome_count.f_killmonster
	 * @return  the value of t_character_dayincome_count.f_killmonster
	 *
	 */
	public Integer getfKillmonster() {
		return fKillmonster;
	}

	/**
	 * 杀怪统计 t_character_dayincome_count.f_killmonster
	 * @param fKillmonster  the value for t_character_dayincome_count.f_killmonster
	 *
	 */
	public void setfKillmonster(Integer fKillmonster) {
		this.fKillmonster = fKillmonster;
	}

	/**
	 * 得获装备数*改为副本次数 t_character_dayincome_count.f_equip
	 * @return  the value of t_character_dayincome_count.f_equip
	 *
	 */
	public Integer getfEquip() {
		return fEquip;
	}

	/**
	 * 得获装备数*改为副本次数 t_character_dayincome_count.f_equip
	 * @param fEquip  the value for t_character_dayincome_count.f_equip
	 *
	 */
	public void setfEquip(Integer fEquip) {
		this.fEquip = fEquip;
	}

	/**
	 * 完成任务数 t_character_dayincome_count.f_finshtask
	 * @return  the value of t_character_dayincome_count.f_finshtask
	 *
	 */
	public Integer getfFinshtask() {
		return fFinshtask;
	}

	/**
	 * 完成任务数 t_character_dayincome_count.f_finshtask
	 * @param fFinshtask  the value for t_character_dayincome_count.f_finshtask
	 *
	 */
	public void setfFinshtask(Integer fFinshtask) {
		this.fFinshtask = fFinshtask;
	}

	/**
	 * 获得真气数 t_character_dayincome_count.f_zhengqi
	 * @return  the value of t_character_dayincome_count.f_zhengqi
	 *
	 */
	public Integer getfZhengqi() {
		return fZhengqi;
	}

	/**
	 * 获得真气数 t_character_dayincome_count.f_zhengqi
	 * @param fZhengqi  the value for t_character_dayincome_count.f_zhengqi
	 *
	 */
	public void setfZhengqi(Integer fZhengqi) {
		this.fZhengqi = fZhengqi;
	}

	/**
	 * 击杀BOSS数 t_character_dayincome_count.f_killboss
	 * @return  the value of t_character_dayincome_count.f_killboss
	 *
	 */
	public Integer getfKillboss() {
		return fKillboss;
	}

	/**
	 * 击杀BOSS数 t_character_dayincome_count.f_killboss
	 * @param fKillboss  the value for t_character_dayincome_count.f_killboss
	 *
	 */
	public void setfKillboss(Integer fKillboss) {
		this.fKillboss = fKillboss;
	}

	/**
	 * 取得座骑数 t_character_dayincome_count.f_gethorse
	 * @return  the value of t_character_dayincome_count.f_gethorse
	 *
	 */
	public Integer getfGethorse() {
		return fGethorse;
	}

	/**
	 * 取得座骑数 t_character_dayincome_count.f_gethorse
	 * @param fGethorse  the value for t_character_dayincome_count.f_gethorse
	 *
	 */
	public void setfGethorse(Integer fGethorse) {
		this.fGethorse = fGethorse;
	}

	/**
	 * 完成阵法数 t_character_dayincome_count.f_finshinstance
	 * @return  the value of t_character_dayincome_count.f_finshinstance
	 *
	 */
	public Integer getfFinshinstance() {
		return fFinshinstance;
	}

	/**
	 * 完成阵法数 t_character_dayincome_count.f_finshinstance
	 * @param fFinshinstance  the value for t_character_dayincome_count.f_finshinstance
	 *
	 */
	public void setfFinshinstance(Integer fFinshinstance) {
		this.fFinshinstance = fFinshinstance;
	}

	/**
	 * 月 t_character_dayincome_count.f_month
	 * @return  the value of t_character_dayincome_count.f_month
	 *
	 */
	public Integer getfMonth() {
		return fMonth;
	}

	/**
	 * 月 t_character_dayincome_count.f_month
	 * @param fMonth  the value for t_character_dayincome_count.f_month
	 *
	 */
	public void setfMonth(Integer fMonth) {
		this.fMonth = fMonth;
	}

	/**
	 * 日 t_character_dayincome_count.f_day
	 * @return  the value of t_character_dayincome_count.f_day
	 *
	 */
	public Integer getfDay() {
		return fDay;
	}

	/**
	 * 日 t_character_dayincome_count.f_day
	 * @param fDay  the value for t_character_dayincome_count.f_day
	 *
	 */
	public void setfDay(Integer fDay) {
		this.fDay = fDay;
	}

	/**
	 * 年 t_character_dayincome_count.f_year
	 * @return  the value of t_character_dayincome_count.f_year
	 *
	 */
	public Integer getfYear() {
		return fYear;
	}

	/**
	 * 年 t_character_dayincome_count.f_year
	 * @param fYear  the value for t_character_dayincome_count.f_year
	 *
	 */
	public void setfYear(Integer fYear) {
		this.fYear = fYear;
	}

	/**
	 * 日当婚宴累计获取真气 t_character_dayincome_count.f_feast_zhengqi
	 * @return  the value of t_character_dayincome_count.f_feast_zhengqi
	 *
	 */
	public Integer getfFeastZhengqi() {
		return fFeastZhengqi;
	}

	/**
	 * 日当婚宴累计获取真气 t_character_dayincome_count.f_feast_zhengqi
	 * @param fFeastZhengqi  the value for t_character_dayincome_count.f_feast_zhengqi
	 *
	 */
	public void setfFeastZhengqi(Integer fFeastZhengqi) {
		this.fFeastZhengqi = fFeastZhengqi;
	}

	/**
	 * 当日累计婚宴获取经验 t_character_dayincome_count.f_feast_exp
	 * @return  the value of t_character_dayincome_count.f_feast_exp
	 *
	 */
	public Integer getfFeastExp() {
		return fFeastExp;
	}

	/**
	 * 当日累计婚宴获取经验 t_character_dayincome_count.f_feast_exp
	 * @param fFeastExp  the value for t_character_dayincome_count.f_feast_exp
	 *
	 */
	public void setfFeastExp(Integer fFeastExp) {
		this.fFeastExp = fFeastExp;
	}

	/**
	 * t_character_dayincome_count.f_shengwang
	 * @return  the value of t_character_dayincome_count.f_shengwang
	 *
	 */
	public Integer getfShengwang() {
		return fShengwang;
	}

	/**
	 * t_character_dayincome_count.f_shengwang
	 * @param fShengwang  the value for t_character_dayincome_count.f_shengwang
	 *
	 */
	public void setfShengwang(Integer fShengwang) {
		this.fShengwang = fShengwang;
	}

	/**
	 * 今日送出红包数 t_character_dayincome_count.f_feast_gift
	 * @return  the value of t_character_dayincome_count.f_feast_gift
	 *
	 */
	public Integer getfFeastGift() {
		return fFeastGift;
	}

	/**
	 * 今日送出红包数 t_character_dayincome_count.f_feast_gift
	 * @param fFeastGift  the value for t_character_dayincome_count.f_feast_gift
	 *
	 */
	public void setfFeastGift(Integer fFeastGift) {
		this.fFeastGift = fFeastGift;
	}
}
