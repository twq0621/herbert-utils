package datatransport.bean.characterbow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BowTransportData implements Serializable{
	private static final long serialVersionUID = 1L;
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterid == null ? Integer.MIN_VALUE : this.characterid);
		out.writeInt(this.bowmodelid == null ? Integer.MIN_VALUE : this.bowmodelid);
		out.writeInt(this.bag1type == null ? Integer.MIN_VALUE : this.bag1type);
		out.writeInt(this.bag1count == null ? Integer.MIN_VALUE : this.bag1count);
		out.writeInt(this.bag2type == null ? Integer.MIN_VALUE : this.bag2type);
		out.writeInt(this.bag2count == null ? Integer.MIN_VALUE : this.bag2count);
		out.writeUTF(this.skillitems == null ? "NaN" : this.skillitems);
		out.writeInt(this.faildcount == null ? Integer.MIN_VALUE : this.faildcount);
		out.writeInt(this.bag1bind == null ? Integer.MIN_VALUE : this.bag1bind);
		out.writeInt(this.bag2bind == null ? Integer.MIN_VALUE : this.bag2bind);
		out.writeByte(this.enable == null ? 2 : this.enable== true ? 1 : 0);
		out.writeInt(this.luck == null ? Integer.MIN_VALUE : this.luck);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.characterid = in.readInt();
		this.characterid = this.characterid == Integer.MIN_VALUE ? null : this.characterid;
		this.bowmodelid = in.readInt();
		this.bowmodelid = this.bowmodelid == Integer.MIN_VALUE ? null : this.bowmodelid;
		this.bag1type = in.readInt();
		this.bag1type = this.bag1type == Integer.MIN_VALUE ? null : this.bag1type;
		this.bag1count = in.readInt();
		this.bag1count = this.bag1count == Integer.MIN_VALUE ? null : this.bag1count;
		this.bag2type = in.readInt();
		this.bag2type = this.bag2type == Integer.MIN_VALUE ? null : this.bag2type;
		this.bag2count = in.readInt();
		this.bag2count = this.bag2count == Integer.MIN_VALUE ? null : this.bag2count;
		this.skillitems = in.readUTF();
		this.skillitems = this.skillitems.equals("NaN") ? null : this.skillitems;
		this.faildcount = in.readInt();
		this.faildcount = this.faildcount == Integer.MIN_VALUE ? null : this.faildcount;
		this.bag1bind = in.readInt();
		this.bag1bind = this.bag1bind == Integer.MIN_VALUE ? null : this.bag1bind;
		this.bag2bind = in.readInt();
		this.bag2bind = this.bag2bind == Integer.MIN_VALUE ? null : this.bag2bind;
		this.enable = cv(in.readByte());
		this.luck = in.readInt();
		this.luck = this.luck == Integer.MIN_VALUE ? null : this.luck;
	}
	private Boolean cv(byte b) {
		if(b == 0){
			return false;
		}else
		if(b == 1){
			return true;
		}
		return null;
	}
	/**
	 * t_character_bow.f_characterid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer characterid;
	/**
	 * t_character_bow.f_bowmodelid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bowmodelid;
	/**
	 * t_character_bow.f_bag1type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag1type;
	/**
	 * t_character_bow.f_bag1count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag1count;
	/**
	 * t_character_bow.f_bag2type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag2type;
	/**
	 * t_character_bow.f_bag2count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag2count;
	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private String skillitems;
	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer faildcount;
	/**
	 * t_character_bow.f_bag1bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag1bind;
	/**
	 * t_character_bow.f_bag2bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer bag2bind;
	/**
	 * t_character_bow.f_enable
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Boolean enable;
	/**
	 * 运幸值 t_character_bow.f_luck
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	private Integer luck;

	/**
	 * t_character_bow.f_characterid
	 * @return  the value of t_character_bow.f_characterid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * t_character_bow.f_characterid
	 * @param characterid  the value for t_character_bow.f_characterid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * t_character_bow.f_bowmodelid
	 * @return  the value of t_character_bow.f_bowmodelid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBowmodelid() {
		return bowmodelid;
	}

	/**
	 * t_character_bow.f_bowmodelid
	 * @param bowmodelid  the value for t_character_bow.f_bowmodelid
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBowmodelid(Integer bowmodelid) {
		this.bowmodelid = bowmodelid;
	}

	/**
	 * t_character_bow.f_bag1type
	 * @return  the value of t_character_bow.f_bag1type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag1type() {
		return bag1type;
	}

	/**
	 * t_character_bow.f_bag1type
	 * @param bag1type  the value for t_character_bow.f_bag1type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag1type(Integer bag1type) {
		this.bag1type = bag1type;
	}

	/**
	 * t_character_bow.f_bag1count
	 * @return  the value of t_character_bow.f_bag1count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag1count() {
		return bag1count;
	}

	/**
	 * t_character_bow.f_bag1count
	 * @param bag1count  the value for t_character_bow.f_bag1count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag1count(Integer bag1count) {
		this.bag1count = bag1count;
	}

	/**
	 * t_character_bow.f_bag2type
	 * @return  the value of t_character_bow.f_bag2type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag2type() {
		return bag2type;
	}

	/**
	 * t_character_bow.f_bag2type
	 * @param bag2type  the value for t_character_bow.f_bag2type
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag2type(Integer bag2type) {
		this.bag2type = bag2type;
	}

	/**
	 * t_character_bow.f_bag2count
	 * @return  the value of t_character_bow.f_bag2count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag2count() {
		return bag2count;
	}

	/**
	 * t_character_bow.f_bag2count
	 * @param bag2count  the value for t_character_bow.f_bag2count
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag2count(Integer bag2count) {
		this.bag2count = bag2count;
	}

	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * @return  the value of t_character_bow.f_skillitems
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public String getSkillitems() {
		return skillitems;
	}

	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * @param skillitems  the value for t_character_bow.f_skillitems
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setSkillitems(String skillitems) {
		this.skillitems = skillitems;
	}

	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * @return  the value of t_character_bow.f_faildcount
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getFaildcount() {
		return faildcount;
	}

	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * @param faildcount  the value for t_character_bow.f_faildcount
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setFaildcount(Integer faildcount) {
		this.faildcount = faildcount;
	}

	/**
	 * t_character_bow.f_bag1bind
	 * @return  the value of t_character_bow.f_bag1bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag1bind() {
		return bag1bind;
	}

	/**
	 * t_character_bow.f_bag1bind
	 * @param bag1bind  the value for t_character_bow.f_bag1bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag1bind(Integer bag1bind) {
		this.bag1bind = bag1bind;
	}

	/**
	 * t_character_bow.f_bag2bind
	 * @return  the value of t_character_bow.f_bag2bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getBag2bind() {
		return bag2bind;
	}

	/**
	 * t_character_bow.f_bag2bind
	 * @param bag2bind  the value for t_character_bow.f_bag2bind
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setBag2bind(Integer bag2bind) {
		this.bag2bind = bag2bind;
	}

	/**
	 * t_character_bow.f_enable
	 * @return  the value of t_character_bow.f_enable
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * t_character_bow.f_enable
	 * @param enable  the value for t_character_bow.f_enable
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * 运幸值 t_character_bow.f_luck
	 * @return  the value of t_character_bow.f_luck
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * 运幸值 t_character_bow.f_luck
	 * @param luck  the value for t_character_bow.f_luck
	 * @ibatorgenerated  Fri Jun 24 09:14:23 CST 2011
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}
}
