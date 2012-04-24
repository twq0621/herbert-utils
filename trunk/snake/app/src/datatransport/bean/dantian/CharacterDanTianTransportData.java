package datatransport.bean.dantian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class CharacterDanTianTransportData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterid == null ? Integer.MIN_VALUE : this.characterid);
		out.writeInt(this.faildcount == null ? Integer.MIN_VALUE : this.faildcount);
		out.writeInt(this.luck == null ? Integer.MIN_VALUE : this.luck);
		out.writeInt(this.dantianid == null ? Integer.MIN_VALUE : this.dantianid);
		out.writeUTF(this.skillitem == null ? "NaN" : this.skillitem);
		out.writeObject(this.luckLastclear);
		out.writeInt(this.beforeLuck == null ? Integer.MIN_VALUE : this.beforeLuck);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.characterid = in.readInt();
		this.characterid = this.characterid == Integer.MIN_VALUE ? null : this.characterid;
		this.faildcount = in.readInt();
		this.faildcount = this.faildcount == Integer.MIN_VALUE ? null : this.faildcount;
		this.luck = in.readInt();
		this.luck = this.luck == Integer.MIN_VALUE ? null : this.luck;
		this.dantianid = in.readInt();
		this.dantianid = this.dantianid == Integer.MIN_VALUE ? null : this.dantianid;
		this.skillitem = in.readUTF();
		this.skillitem = this.skillitem.equals("NaN") ? null : this.skillitem;
		this.luckLastclear = (Date) in.readObject();
		this.beforeLuck = in.readInt();
		this.beforeLuck = this.beforeLuck == Integer.MIN_VALUE ? null : this.beforeLuck;
	}

	/**
	 * 角色id t_character_dantian.f_characterid
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Integer characterid;
	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Integer dantianid;
	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private String skillitem;
	/**
	 * 幸运值 t_character_dantian.f_luck
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Integer luck;
	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Integer faildcount;
	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Date luckLastclear;
	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * 
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	private Integer beforeLuck;

	/**
	 * 角色id t_character_dantian.f_characterid
	 * 
	 * @return the value of t_character_dantian.f_characterid
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * 角色id t_character_dantian.f_characterid
	 * 
	 * @param characterid
	 *            the value for t_character_dantian.f_characterid
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * 
	 * @return the value of t_character_dantian.f_dantianid
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Integer getDantianid() {
		return dantianid;
	}

	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * 
	 * @param dantianid
	 *            the value for t_character_dantian.f_dantianid
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setDantianid(Integer dantianid) {
		this.dantianid = dantianid;
	}

	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * 
	 * @return the value of t_character_dantian.f_skillitem
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public String getSkillitem() {
		return skillitem;
	}

	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * 
	 * @param skillitem
	 *            the value for t_character_dantian.f_skillitem
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setSkillitem(String skillitem) {
		this.skillitem = skillitem;
	}

	/**
	 * 幸运值 t_character_dantian.f_luck
	 * 
	 * @return the value of t_character_dantian.f_luck
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * 幸运值 t_character_dantian.f_luck
	 * 
	 * @param luck
	 *            the value for t_character_dantian.f_luck
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * 
	 * @return the value of t_character_dantian.f_faildcount
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Integer getFaildcount() {
		return faildcount;
	}

	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * 
	 * @param faildcount
	 *            the value for t_character_dantian.f_faildcount
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setFaildcount(Integer faildcount) {
		this.faildcount = faildcount;
	}

	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * 
	 * @return the value of t_character_dantian.f_luck_lastclear
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Date getLuckLastclear() {
		return luckLastclear;
	}

	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * 
	 * @param luckLastclear
	 *            the value for t_character_dantian.f_luck_lastclear
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setLuckLastclear(Date luckLastclear) {
		this.luckLastclear = luckLastclear;
	}

	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * 
	 * @return the value of t_character_dantian.f_before_luck
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public Integer getBeforeLuck() {
		return beforeLuck;
	}

	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * 
	 * @param beforeLuck
	 *            the value for t_character_dantian.f_before_luck
	 * @ibatorgenerated Fri Jul 01 21:56:20 CST 2011
	 */
	public void setBeforeLuck(Integer beforeLuck) {
		this.beforeLuck = beforeLuck;
	}
}
