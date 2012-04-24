package datatransport.bean.characterlianti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class CharacterLiantiTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.moveSpeed == null ? Integer.MIN_VALUE : this.moveSpeed);
		out.writeInt(this.liantiJingjieId == null ? Integer.MIN_VALUE : this.liantiJingjieId);
		out.writeInt(this.hp == null ? Integer.MIN_VALUE : this.hp);
		out.writeInt(this.atk == null ? Integer.MIN_VALUE : this.atk);
		out.writeInt(this.mp == null ? Integer.MIN_VALUE : this.mp);
		out.writeInt(this.sp == null ? Integer.MIN_VALUE : this.sp);
		out.writeInt(this.def == null ? Integer.MIN_VALUE : this.def);
		out.writeInt(this.atkSpeed == null ? Integer.MIN_VALUE : this.atkSpeed);
		out.writeInt(this.aqJv == null ? Integer.MIN_VALUE : this.aqJv);
		out.writeInt(this.fjsh == null ? Integer.MIN_VALUE : this.fjsh);
		out.writeInt(this.shjm == null ? Integer.MIN_VALUE : this.shjm);
		out.writeInt(this.tupoCount == null ? Integer.MIN_VALUE : this.tupoCount);
		out.writeInt(this.zhufu == null ? Integer.MIN_VALUE : this.zhufu);
		out.writeObject(this.zhufuStarttime);
		out.writeInt(this.usegoodCount == null ? Integer.MIN_VALUE : this.usegoodCount);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.moveSpeed = in.readInt();
		this.moveSpeed = this.moveSpeed == Integer.MIN_VALUE ? null : this.moveSpeed;
		this.liantiJingjieId = in.readInt();
		this.liantiJingjieId = this.liantiJingjieId == Integer.MIN_VALUE ? null : this.liantiJingjieId;
		this.hp = in.readInt();
		this.hp = this.hp == Integer.MIN_VALUE ? null : this.hp;
		this.atk = in.readInt();
		this.atk = this.atk == Integer.MIN_VALUE ? null : this.atk;
		this.mp = in.readInt();
		this.mp = this.mp == Integer.MIN_VALUE ? null : this.mp;
		this.sp = in.readInt();
		this.sp = this.sp == Integer.MIN_VALUE ? null : this.sp;
		this.def = in.readInt();
		this.def = this.def == Integer.MIN_VALUE ? null : this.def;
		this.atkSpeed = in.readInt();
		this.atkSpeed = this.atkSpeed == Integer.MIN_VALUE ? null : this.atkSpeed;
		this.aqJv = in.readInt();
		this.aqJv = this.aqJv == Integer.MIN_VALUE ? null : this.aqJv;
		this.fjsh = in.readInt();
		this.fjsh = this.fjsh == Integer.MIN_VALUE ? null : this.fjsh;
		this.shjm = in.readInt();
		this.shjm = this.shjm == Integer.MIN_VALUE ? null : this.shjm;
		this.tupoCount = in.readInt();
		this.tupoCount = this.tupoCount == Integer.MIN_VALUE ? null : this.tupoCount;
		this.zhufu = in.readInt();
		this.zhufu = this.zhufu == Integer.MIN_VALUE ? null : this.zhufu;
		this.zhufuStarttime = (Date)in.readObject();
		this.usegoodCount = in.readInt();
		this.usegoodCount = this.usegoodCount == Integer.MIN_VALUE ? null : this.usegoodCount;
	}
	/**
	 * t_character_lianti.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer characterId;
	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer liantiJingjieId;
	/**
	 * t_character_lianti.f_hp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer hp;
	/**
	 * t_character_lianti.f_atk
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer atk;
	/**
	 * t_character_lianti.f_mp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer mp;
	/**
	 * t_character_lianti.f_sp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer sp;
	/**
	 * t_character_lianti.f_def
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer def;
	/**
	 * t_character_lianti.f_atk_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer atkSpeed;
	/**
	 * t_character_lianti.f_move_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer moveSpeed;
	/**
	 * t_character_lianti.f_aq_jv
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer aqJv;
	/**
	 * t_character_lianti.f_fjsh
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer fjsh;
	/**
	 * t_character_lianti.f_shjm
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer shjm;
	/**
	 * t_character_lianti.f_tupo_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer tupoCount;
	/**
	 * t_character_lianti.f_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer zhufu;
	/**
	 * t_character_lianti.f_zhufu_starttime
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Date zhufuStarttime;
	/**
	 * t_character_lianti.f_usegood_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	private Integer usegoodCount;

	/**
	 * t_character_lianti.f_character_id
	 * @return  the value of t_character_lianti.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * t_character_lianti.f_character_id
	 * @param characterId  the value for t_character_lianti.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * @return  the value of t_character_lianti.f_lianti_jingjie_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getLiantiJingjieId() {
		return liantiJingjieId;
	}

	/**
	 * 当前炼体境界 t_character_lianti.f_lianti_jingjie_id
	 * @param liantiJingjieId  the value for t_character_lianti.f_lianti_jingjie_id
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setLiantiJingjieId(Integer liantiJingjieId) {
		this.liantiJingjieId = liantiJingjieId;
	}

	/**
	 * t_character_lianti.f_hp
	 * @return  the value of t_character_lianti.f_hp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * t_character_lianti.f_hp
	 * @param hp  the value for t_character_lianti.f_hp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * t_character_lianti.f_atk
	 * @return  the value of t_character_lianti.f_atk
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getAtk() {
		return atk;
	}

	/**
	 * t_character_lianti.f_atk
	 * @param atk  the value for t_character_lianti.f_atk
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	/**
	 * t_character_lianti.f_mp
	 * @return  the value of t_character_lianti.f_mp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * t_character_lianti.f_mp
	 * @param mp  the value for t_character_lianti.f_mp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * t_character_lianti.f_sp
	 * @return  the value of t_character_lianti.f_sp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * t_character_lianti.f_sp
	 * @param sp  the value for t_character_lianti.f_sp
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * t_character_lianti.f_def
	 * @return  the value of t_character_lianti.f_def
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getDef() {
		return def;
	}

	/**
	 * t_character_lianti.f_def
	 * @param def  the value for t_character_lianti.f_def
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setDef(Integer def) {
		this.def = def;
	}

	/**
	 * t_character_lianti.f_atk_speed
	 * @return  the value of t_character_lianti.f_atk_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * t_character_lianti.f_atk_speed
	 * @param atkSpeed  the value for t_character_lianti.f_atk_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setAtkSpeed(Integer atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * t_character_lianti.f_move_speed
	 * @return  the value of t_character_lianti.f_move_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * t_character_lianti.f_move_speed
	 * @param moveSpeed  the value for t_character_lianti.f_move_speed
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * t_character_lianti.f_aq_jv
	 * @return  the value of t_character_lianti.f_aq_jv
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getAqJv() {
		return aqJv;
	}

	/**
	 * t_character_lianti.f_aq_jv
	 * @param aqJv  the value for t_character_lianti.f_aq_jv
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setAqJv(Integer aqJv) {
		this.aqJv = aqJv;
	}

	/**
	 * t_character_lianti.f_fjsh
	 * @return  the value of t_character_lianti.f_fjsh
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getFjsh() {
		return fjsh;
	}

	/**
	 * t_character_lianti.f_fjsh
	 * @param fjsh  the value for t_character_lianti.f_fjsh
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setFjsh(Integer fjsh) {
		this.fjsh = fjsh;
	}

	/**
	 * t_character_lianti.f_shjm
	 * @return  the value of t_character_lianti.f_shjm
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getShjm() {
		return shjm;
	}

	/**
	 * t_character_lianti.f_shjm
	 * @param shjm  the value for t_character_lianti.f_shjm
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setShjm(Integer shjm) {
		this.shjm = shjm;
	}

	/**
	 * t_character_lianti.f_tupo_count
	 * @return  the value of t_character_lianti.f_tupo_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getTupoCount() {
		return tupoCount;
	}

	/**
	 * t_character_lianti.f_tupo_count
	 * @param tupoCount  the value for t_character_lianti.f_tupo_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setTupoCount(Integer tupoCount) {
		this.tupoCount = tupoCount;
	}

	/**
	 * t_character_lianti.f_zhufu
	 * @return  the value of t_character_lianti.f_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getZhufu() {
		return zhufu;
	}

	/**
	 * t_character_lianti.f_zhufu
	 * @param zhufu  the value for t_character_lianti.f_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setZhufu(Integer zhufu) {
		this.zhufu = zhufu;
	}

	/**
	 * t_character_lianti.f_zhufu_starttime
	 * @return  the value of t_character_lianti.f_zhufu_starttime
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Date getZhufuStarttime() {
		return zhufuStarttime;
	}

	/**
	 * t_character_lianti.f_zhufu_starttime
	 * @param zhufuStarttime  the value for t_character_lianti.f_zhufu_starttime
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setZhufuStarttime(Date zhufuStarttime) {
		this.zhufuStarttime = zhufuStarttime;
	}

	/**
	 * t_character_lianti.f_usegood_count
	 * @return  the value of t_character_lianti.f_usegood_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public Integer getUsegoodCount() {
		return usegoodCount;
	}

	/**
	 * t_character_lianti.f_usegood_count
	 * @param usegoodCount  the value for t_character_lianti.f_usegood_count
	 * @ibatorgenerated  Fri Jun 24 09:15:59 CST 2011
	 */
	public void setUsegoodCount(Integer usegoodCount) {
		this.usegoodCount = usegoodCount;
	}
}
