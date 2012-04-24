package datatransport.bean.acrossincome;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class AcrossIncomeTransportData implements Serializable {
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeLong(this.exp);
		out.writeInt(this.shengwang == null ? Integer.MIN_VALUE : this.shengwang);
		out.writeInt(this.copper == null ? Integer.MIN_VALUE : this.copper);
		out.writeInt(this.xuanyuanjian == null ? Integer.MIN_VALUE : this.xuanyuanjian);
		out.writeObject(this.xuanyuanjianTime);
		out.writeInt(this.winCnt == null ? Integer.MIN_VALUE : this.winCnt);
		out.writeInt(this.failCnt == null ? Integer.MIN_VALUE : this.failCnt);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.exp = in.readLong();
		this.shengwang = in.readInt();
		this.shengwang = this.shengwang == Integer.MIN_VALUE ? null : this.shengwang;
		this.copper = in.readInt();
		this.copper = this.copper == Integer.MIN_VALUE ? null : this.copper;
		this.xuanyuanjian = in.readInt();
		this.xuanyuanjian = this.xuanyuanjian == Integer.MIN_VALUE ? null : this.xuanyuanjian;
		this.xuanyuanjianTime = (Date) in.readObject();
		this.winCnt = in.readInt();
		this.winCnt = this.winCnt == Integer.MIN_VALUE ? null : this.winCnt;
		this.failCnt = in.readInt();
		this.failCnt = this.failCnt == Integer.MIN_VALUE ? null : this.failCnt;
	}

	/**
	 * 角色id t_across_income.f_character_id
	 */
	private Integer characterId;
	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 */
	private Long exp;
	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 */
	private Integer shengwang;
	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 */
	private Integer copper;
	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 */
	private Integer xuanyuanjian;
	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 */
	private Date xuanyuanjianTime;
	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 */
	private Integer winCnt;
	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 */
	private Integer failCnt;

	/**
	 * 角色id t_across_income.f_character_id
	 * 
	 * @return the value of t_across_income.f_character_id
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_across_income.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_across_income.f_character_id
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 * 
	 * @return the value of t_across_income.f_exp
	 */
	public Long getExp() {
		return exp;
	}

	/**
	 * 跨服战获得经验 t_across_income.f_exp
	 * 
	 * @param exp
	 *            the value for t_across_income.f_exp
	 */
	public void setExp(Long exp) {
		this.exp = exp;
	}

	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 * 
	 * @return the value of t_across_income.f_shengwang
	 */
	public Integer getShengwang() {
		return shengwang;
	}

	/**
	 * 跨服战获得声望 t_across_income.f_shengwang
	 * 
	 * @param shengwang
	 *            the value for t_across_income.f_shengwang
	 */
	public void setShengwang(Integer shengwang) {
		this.shengwang = shengwang;
	}

	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 * 
	 * @return the value of t_across_income.f_copper
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 跨服战获得铜币 t_across_income.f_copper
	 * 
	 * @param copper
	 *            the value for t_across_income.f_copper
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 * 
	 * @return the value of t_across_income.f_xuanyuanjian
	 */
	public Integer getXuanyuanjian() {
		return xuanyuanjian;
	}

	/**
	 * 0表示没有持有 1表示持有 t_across_income.f_xuanyuanjian
	 * 
	 * @param xuanyuanjian
	 *            the value for t_across_income.f_xuanyuanjian
	 */
	public void setXuanyuanjian(Integer xuanyuanjian) {
		this.xuanyuanjian = xuanyuanjian;
	}

	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 * 
	 * @return the value of t_across_income.f_xuanyuanjian_time
	 */
	public Date getXuanyuanjianTime() {
		return xuanyuanjianTime;
	}

	/**
	 * 轩辕剑失效时间 t_across_income.f_xuanyuanjian_time
	 * 
	 * @param xuanyuanjianTime
	 *            the value for t_across_income.f_xuanyuanjian_time
	 */
	public void setXuanyuanjianTime(Date xuanyuanjianTime) {
		this.xuanyuanjianTime = xuanyuanjianTime;
	}

	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 * 
	 * @return the value of t_across_income.f_win_cnt
	 */
	public Integer getWinCnt() {
		return winCnt;
	}

	/**
	 * 胜利次数 t_across_income.f_win_cnt
	 * 
	 * @param winCnt
	 *            the value for t_across_income.f_win_cnt
	 */
	public void setWinCnt(Integer winCnt) {
		this.winCnt = winCnt;
	}

	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 * 
	 * @return the value of t_across_income.f_fail_cnt
	 */
	public Integer getFailCnt() {
		return failCnt;
	}

	/**
	 * 失败次数 t_across_income.f_fail_cnt
	 * 
	 * @param failCnt
	 *            the value for t_across_income.f_fail_cnt
	 */
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}
}
