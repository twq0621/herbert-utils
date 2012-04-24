package datatransport.bean.characterhiddenweapon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HiddenWeaponTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(this.id == null ? "NaN" : this.id);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.grade == null ? Integer.MIN_VALUE : this.grade);
		out.writeByte(this.isUse == null ? 2 : this.isUse== true ? 1 : 0);
		out.writeInt(this.tupoCnt == null ? Integer.MIN_VALUE : this.tupoCnt);
		out.writeInt(this.nowMastery == null ? Integer.MIN_VALUE : this.nowMastery);
		out.writeInt(this.xiuGrade == null ? Integer.MIN_VALUE : this.xiuGrade);
		out.writeInt(this.luckValue == null ? Integer.MIN_VALUE : this.luckValue);
		out.writeInt(this.freeCnt == null ? Integer.MIN_VALUE : this.freeCnt);
		out.writeInt(this.randomFreeGrade == null ? Integer.MIN_VALUE : this.randomFreeGrade);
		out.writeByte(this.isOpenHiddenProps == null ? 2 : this.isOpenHiddenProps== true ? 1 : 0);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.id = in.readUTF();
		this.id = this.id.equals("NaN") ? null : this.id;
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.grade = in.readInt();
		this.grade = this.grade == Integer.MIN_VALUE ? null : this.grade;
		this.isUse = cv(in.readByte());
		this.tupoCnt = in.readInt();
		this.tupoCnt = this.tupoCnt == Integer.MIN_VALUE ? null : this.tupoCnt;
		this.nowMastery = in.readInt();
		this.nowMastery = this.nowMastery == Integer.MIN_VALUE ? null : this.nowMastery;
		this.xiuGrade = in.readInt();
		this.xiuGrade = this.xiuGrade == Integer.MIN_VALUE ? null : this.xiuGrade;
		this.luckValue = in.readInt();
		this.luckValue = this.luckValue == Integer.MIN_VALUE ? null : this.luckValue;
		this.freeCnt = in.readInt();
		this.freeCnt = this.freeCnt == Integer.MIN_VALUE ? null : this.freeCnt;
		this.randomFreeGrade = in.readInt();
		this.randomFreeGrade = this.randomFreeGrade == Integer.MIN_VALUE ? null : this.randomFreeGrade;
		this.isOpenHiddenProps = cv(in.readByte());
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
	 * t_character_hidden_weapon.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private String id;
	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer characterId;
	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer grade;
	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer nowMastery;
	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Boolean isUse;
	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer xiuGrade;
	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer luckValue;
	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer tupoCnt;
	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer freeCnt;
	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Integer randomFreeGrade;
	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	private Boolean isOpenHiddenProps;

	/**
	 * t_character_hidden_weapon.f_id
	 * @return  the value of t_character_hidden_weapon.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public String getId() {
		return id;
	}

	/**
	 * t_character_hidden_weapon.f_id
	 * @param id  the value for t_character_hidden_weapon.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * @return  the value of t_character_hidden_weapon.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * @param characterId  the value for t_character_hidden_weapon.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * @return  the value of t_character_hidden_weapon.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * @param grade  the value for t_character_hidden_weapon.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * @return  the value of t_character_hidden_weapon.f_now_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getNowMastery() {
		return nowMastery;
	}

	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * @param nowMastery  the value for t_character_hidden_weapon.f_now_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setNowMastery(Integer nowMastery) {
		this.nowMastery = nowMastery;
	}

	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * @return  the value of t_character_hidden_weapon.f_is_use
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Boolean getIsUse() {
		return isUse;
	}

	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * @param isUse  the value for t_character_hidden_weapon.f_is_use
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * @return  the value of t_character_hidden_weapon.f_xiu_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getXiuGrade() {
		return xiuGrade;
	}

	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * @param xiuGrade  the value for t_character_hidden_weapon.f_xiu_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setXiuGrade(Integer xiuGrade) {
		this.xiuGrade = xiuGrade;
	}

	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * @return  the value of t_character_hidden_weapon.f_luck_value
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getLuckValue() {
		return luckValue;
	}

	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * @param luckValue  the value for t_character_hidden_weapon.f_luck_value
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setLuckValue(Integer luckValue) {
		this.luckValue = luckValue;
	}

	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * @return  the value of t_character_hidden_weapon.f_tupo_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getTupoCnt() {
		return tupoCnt;
	}

	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * @param tupoCnt  the value for t_character_hidden_weapon.f_tupo_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setTupoCnt(Integer tupoCnt) {
		this.tupoCnt = tupoCnt;
	}

	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * @return  the value of t_character_hidden_weapon.f_free_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getFreeCnt() {
		return freeCnt;
	}

	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * @param freeCnt  the value for t_character_hidden_weapon.f_free_cnt
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setFreeCnt(Integer freeCnt) {
		this.freeCnt = freeCnt;
	}

	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * @return  the value of t_character_hidden_weapon.f_random_free_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Integer getRandomFreeGrade() {
		return randomFreeGrade;
	}

	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * @param randomFreeGrade  the value for t_character_hidden_weapon.f_random_free_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setRandomFreeGrade(Integer randomFreeGrade) {
		this.randomFreeGrade = randomFreeGrade;
	}

	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * @return  the value of t_character_hidden_weapon.f_is_open_hidden_props
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public Boolean getIsOpenHiddenProps() {
		return isOpenHiddenProps;
	}

	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * @param isOpenHiddenProps  the value for t_character_hidden_weapon.f_is_open_hidden_props
	 * @ibatorgenerated  Fri Jul 01 21:57:35 CST 2011
	 */
	public void setIsOpenHiddenProps(Boolean isOpenHiddenProps) {
		this.isOpenHiddenProps = isOpenHiddenProps;
	}
}
