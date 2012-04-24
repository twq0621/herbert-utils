package net.snake.gamemodel.heroext.lianti.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterLiantiDataEntry  implements IbatisEntity{

	/**
	 * t_character_lianti.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * 
	 */
	private Integer liantiJingjieId;
	/**
	 * t_character_lianti.f_hp
	 * 
	 */
	private Integer hp;
	/**
	 * t_character_lianti.f_atk
	 * 
	 */
	private Integer atk;
	/**
	 * t_character_lianti.f_mp
	 * 
	 */
	private Integer mp;
	/**
	 * t_character_lianti.f_sp
	 * 
	 */
	private Integer sp;
	/**
	 * t_character_lianti.f_def
	 * 
	 */
	private Integer def;
	/**
	 * t_character_lianti.f_atk_speed
	 * 
	 */
	private Integer atkSpeed;
	/**
	 * t_character_lianti.f_move_speed
	 * 
	 */
	private Integer moveSpeed;
	/**
	 * t_character_lianti.f_aq_jv
	 * 
	 */
	private Integer aqJv;
	/**
	 * t_character_lianti.f_fjsh
	 * 
	 */
	private Integer fjsh;
	/**
	 * t_character_lianti.f_shjm
	 * 
	 */
	private Integer shjm;
	/**
	 * t_character_lianti.f_tupo_count
	 * 
	 */
	private Integer tupoCount;
	/**
	 * t_character_lianti.f_zhufu
	 * 
	 */
	private Integer zhufu;
	/**
	 * t_character_lianti.f_zhufu_starttime
	 * 
	 */
	private Date zhufuStarttime;
	/**
	 * t_character_lianti.f_usegood_count
	 * 
	 */
	private Integer usegoodCount;
	/**
	 * 超值梦幻菩提卡　剩余可使用次数 t_character_lianti.f_puti_card_usecount
	 * 
	 */
	private Integer putiCardUsecount;

	/**
	 * t_character_lianti.f_character_id
	 * @return  the value of t_character_lianti.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * t_character_lianti.f_character_id
	 * @param characterId  the value for t_character_lianti.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * @return  the value of t_character_lianti.f_lianti_jingjie_id
	 * 
	 */
	public Integer getLiantiJingjieId() {
		return liantiJingjieId;
	}

	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * @param liantiJingjieId  the value for t_character_lianti.f_lianti_jingjie_id
	 * 
	 */
	public void setLiantiJingjieId(Integer liantiJingjieId) {
		this.liantiJingjieId = liantiJingjieId;
	}

	/**
	 * t_character_lianti.f_hp
	 * @return  the value of t_character_lianti.f_hp
	 * 
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * t_character_lianti.f_hp
	 * @param hp  the value for t_character_lianti.f_hp
	 * 
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * t_character_lianti.f_atk
	 * @return  the value of t_character_lianti.f_atk
	 * 
	 */
	public Integer getAtk() {
		return atk;
	}

	/**
	 * t_character_lianti.f_atk
	 * @param atk  the value for t_character_lianti.f_atk
	 * 
	 */
	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	/**
	 * t_character_lianti.f_mp
	 * @return  the value of t_character_lianti.f_mp
	 * 
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * t_character_lianti.f_mp
	 * @param mp  the value for t_character_lianti.f_mp
	 * 
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * t_character_lianti.f_sp
	 * @return  the value of t_character_lianti.f_sp
	 * 
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * t_character_lianti.f_sp
	 * @param sp  the value for t_character_lianti.f_sp
	 * 
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * t_character_lianti.f_def
	 * @return  the value of t_character_lianti.f_def
	 * 
	 */
	public Integer getDef() {
		return def;
	}

	/**
	 * t_character_lianti.f_def
	 * @param def  the value for t_character_lianti.f_def
	 * 
	 */
	public void setDef(Integer def) {
		this.def = def;
	}

	/**
	 * t_character_lianti.f_atk_speed
	 * @return  the value of t_character_lianti.f_atk_speed
	 * 
	 */
	public Integer getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * t_character_lianti.f_atk_speed
	 * @param atkSpeed  the value for t_character_lianti.f_atk_speed
	 * 
	 */
	public void setAtkSpeed(Integer atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * t_character_lianti.f_move_speed
	 * @return  the value of t_character_lianti.f_move_speed
	 * 
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * t_character_lianti.f_move_speed
	 * @param moveSpeed  the value for t_character_lianti.f_move_speed
	 * 
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * t_character_lianti.f_aq_jv
	 * @return  the value of t_character_lianti.f_aq_jv
	 * 
	 */
	public Integer getAqJv() {
		return aqJv;
	}

	/**
	 * t_character_lianti.f_aq_jv
	 * @param aqJv  the value for t_character_lianti.f_aq_jv
	 * 
	 */
	public void setAqJv(Integer aqJv) {
		this.aqJv = aqJv;
	}

	/**
	 * t_character_lianti.f_fjsh
	 * @return  the value of t_character_lianti.f_fjsh
	 * 
	 */
	public Integer getFjsh() {
		return fjsh;
	}

	/**
	 * t_character_lianti.f_fjsh
	 * @param fjsh  the value for t_character_lianti.f_fjsh
	 * 
	 */
	public void setFjsh(Integer fjsh) {
		this.fjsh = fjsh;
	}

	/**
	 * t_character_lianti.f_shjm
	 * @return  the value of t_character_lianti.f_shjm
	 * 
	 */
	public Integer getShjm() {
		return shjm;
	}

	/**
	 * t_character_lianti.f_shjm
	 * @param shjm  the value for t_character_lianti.f_shjm
	 * 
	 */
	public void setShjm(Integer shjm) {
		this.shjm = shjm;
	}

	/**
	 * t_character_lianti.f_tupo_count
	 * @return  the value of t_character_lianti.f_tupo_count
	 * 
	 */
	public Integer getTupoCount() {
		return tupoCount;
	}

	/**
	 * t_character_lianti.f_tupo_count
	 * @param tupoCount  the value for t_character_lianti.f_tupo_count
	 * 
	 */
	public void setTupoCount(Integer tupoCount) {
		this.tupoCount = tupoCount;
	}

	/**
	 * t_character_lianti.f_zhufu
	 * @return  the value of t_character_lianti.f_zhufu
	 * 
	 */
	public Integer getZhufu() {
		return zhufu;
	}

	/**
	 * t_character_lianti.f_zhufu
	 * @param zhufu  the value for t_character_lianti.f_zhufu
	 * 
	 */
	public void setZhufu(Integer zhufu) {
		this.zhufu = zhufu;
	}

	/**
	 * t_character_lianti.f_zhufu_starttime
	 * @return  the value of t_character_lianti.f_zhufu_starttime
	 * 
	 */
	public Date getZhufuStarttime() {
		return zhufuStarttime;
	}

	/**
	 * t_character_lianti.f_zhufu_starttime
	 * @param zhufuStarttime  the value for t_character_lianti.f_zhufu_starttime
	 * 
	 */
	public void setZhufuStarttime(Date zhufuStarttime) {
		this.zhufuStarttime = zhufuStarttime;
	}

	/**
	 * t_character_lianti.f_usegood_count
	 * @return  the value of t_character_lianti.f_usegood_count
	 * 
	 */
	public Integer getUsegoodCount() {
		return usegoodCount;
	}

	/**
	 * t_character_lianti.f_usegood_count
	 * @param usegoodCount  the value for t_character_lianti.f_usegood_count
	 * 
	 */
	public void setUsegoodCount(Integer usegoodCount) {
		this.usegoodCount = usegoodCount;
	}

	/**
	 * 超值梦幻菩提卡　剩余可使用次数 t_character_lianti.f_puti_card_usecount
	 * @return  the value of t_character_lianti.f_puti_card_usecount
	 * 
	 */
	public Integer getPutiCardUsecount() {
		return putiCardUsecount;
	}

	/**
	 * 超值梦幻菩提卡　剩余可使用次数 t_character_lianti.f_puti_card_usecount
	 * @param putiCardUsecount  the value for t_character_lianti.f_puti_card_usecount
	 * 
	 */
	public void setPutiCardUsecount(Integer putiCardUsecount) {
		this.putiCardUsecount = putiCardUsecount;
	}
}
