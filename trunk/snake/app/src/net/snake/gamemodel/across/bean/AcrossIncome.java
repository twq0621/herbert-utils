package net.snake.gamemodel.across.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class AcrossIncome implements IbatisEntity{

	/**
	 * 角色id t_across_income.f_character_id
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer characterId;
	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Long exp;
	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer shengwang;
	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer copper;
	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer xuanyuanjian;
	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Date xuanyuanjianTime;
	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer winCnt;
	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	private Integer failCnt;

	/**
	 * 角色id t_across_income.f_character_id
	 * @return  the value of t_across_income.f_character_id
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_across_income.f_character_id
	 * @param characterId  the value for t_across_income.f_character_id
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 * @return  the value of t_across_income.f_exp
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Long getExp() {
		return exp;
	}

	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 * @param exp  the value for t_across_income.f_exp
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setExp(Long exp) {
		this.exp = exp;
	}

	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 * @return  the value of t_across_income.f_shengwang
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getShengwang() {
		return shengwang;
	}

	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 * @param shengwang  the value for t_across_income.f_shengwang
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setShengwang(Integer shengwang) {
		this.shengwang = shengwang;
	}

	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 * @return  the value of t_across_income.f_copper
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 * @param copper  the value for t_across_income.f_copper
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 * @return  the value of t_across_income.f_xuanyuanjian
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getXuanyuanjian() {
		return xuanyuanjian;
	}

	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 * @param xuanyuanjian  the value for t_across_income.f_xuanyuanjian
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setXuanyuanjian(Integer xuanyuanjian) {
		this.xuanyuanjian = xuanyuanjian;
	}

	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 * @return  the value of t_across_income.f_xuanyuanjian_time
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Date getXuanyuanjianTime() {
		return xuanyuanjianTime;
	}

	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 * @param xuanyuanjianTime  the value for t_across_income.f_xuanyuanjian_time
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setXuanyuanjianTime(Date xuanyuanjianTime) {
		this.xuanyuanjianTime = xuanyuanjianTime;
	}

	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 * @return  the value of t_across_income.f_win_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getWinCnt() {
		return winCnt;
	}

	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 * @param winCnt  the value for t_across_income.f_win_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setWinCnt(Integer winCnt) {
		this.winCnt = winCnt;
	}

	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 * @return  the value of t_across_income.f_fail_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public Integer getFailCnt() {
		return failCnt;
	}

	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 * @param failCnt  the value for t_across_income.f_fail_cnt
	 * @ibatorgenerated  Mon Jul 04 15:05:50 CST 2011
	 */
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}
}
