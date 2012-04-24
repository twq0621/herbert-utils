package datatransport.bean.characterDGWD;

import java.io.Serializable;
import java.util.Date;

public class CharacterDGWDTransportData implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * t_character_dgwd.f_characterid
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Integer characterid;

	/**
	 * 前当悟道等级 t_character_dgwd.f_nowwd
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Integer nowwd;

	/**
	 * 当前幸运值 t_character_dgwd.f_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Integer luck;

	/**
	 * 败失次数 t_character_dgwd.f_faildcount
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Integer faildcount;

	/**
	 * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Date luckLastclear;

	/**
	 * 上次幸运值 t_character_dgwd.f_before_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	private Integer beforeLuck;

	/**
	 * t_character_dgwd.f_characterid
	 * 
	 * @return the value of t_character_dgwd.f_characterid
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * t_character_dgwd.f_characterid
	 * 
	 * @param characterid
	 *            the value for t_character_dgwd.f_characterid
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * 前当悟道等级 t_character_dgwd.f_nowwd
	 * 
	 * @return the value of t_character_dgwd.f_nowwd
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Integer getNowwd() {
		return nowwd;
	}

	/**
	 * 前当悟道等级 t_character_dgwd.f_nowwd
	 * 
	 * @param nowwd
	 *            the value for t_character_dgwd.f_nowwd
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setNowwd(Integer nowwd) {
		this.nowwd = nowwd;
	}

	/**
	 * 当前幸运值 t_character_dgwd.f_luck
	 * 
	 * @return the value of t_character_dgwd.f_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * 当前幸运值 t_character_dgwd.f_luck
	 * 
	 * @param luck
	 *            the value for t_character_dgwd.f_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	/**
	 * 败失次数 t_character_dgwd.f_faildcount
	 * 
	 * @return the value of t_character_dgwd.f_faildcount
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Integer getFaildcount() {
		return faildcount;
	}

	/**
	 * 败失次数 t_character_dgwd.f_faildcount
	 * 
	 * @param faildcount
	 *            the value for t_character_dgwd.f_faildcount
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setFaildcount(Integer faildcount) {
		this.faildcount = faildcount;
	}

	/**
	 * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
	 * 
	 * @return the value of t_character_dgwd.f_luck_lastclear
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Date getLuckLastclear() {
		return luckLastclear;
	}

	/**
	 * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
	 * 
	 * @param luckLastclear
	 *            the value for t_character_dgwd.f_luck_lastclear
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setLuckLastclear(Date luckLastclear) {
		this.luckLastclear = luckLastclear;
	}

	/**
	 * 上次幸运值 t_character_dgwd.f_before_luck
	 * 
	 * @return the value of t_character_dgwd.f_before_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public Integer getBeforeLuck() {
		return beforeLuck;
	}

	/**
	 * 上次幸运值 t_character_dgwd.f_before_luck
	 * 
	 * @param beforeLuck
	 *            the value for t_character_dgwd.f_before_luck
	 * 
	 * @ibatorgenerated Sat Jul 16 17:35:25 CST 2011
	 */
	public void setBeforeLuck(Integer beforeLuck) {
		this.beforeLuck = beforeLuck;
	}
}
