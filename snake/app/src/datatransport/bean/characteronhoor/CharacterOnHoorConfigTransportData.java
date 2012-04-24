package datatransport.bean.characteronhoor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CharacterOnHoorConfigTransportData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeByte(this.autoRevertHp == null ? 2 : this.autoRevertHp == true ? 1 : 0);
		out.writeInt(this.revertHp == null ? Integer.MIN_VALUE : this.revertHp);
		out.writeInt(this.revertMp == null ? Integer.MIN_VALUE : this.revertMp);
		out.writeInt(this.revertSp == null ? Integer.MIN_VALUE : this.revertSp);
		out.writeInt(this.revertHpMethod == null ? Integer.MIN_VALUE : this.revertHpMethod);
		out.writeInt(this.revertMpMethod == null ? Integer.MIN_VALUE : this.revertMpMethod);
		out.writeInt(this.revertSpMethod == null ? Integer.MIN_VALUE : this.revertSpMethod);
		out.writeByte(this.autoPickupEquip == null ? 2 : this.autoPickupEquip == true ? 1 : 0);
		out.writeByte(this.qualityEquip);
		out.writeByte(this.limitPopsinger);
		out.writeInt(this.gradeEquip == null ? Integer.MIN_VALUE : this.gradeEquip);
		out.writeByte(this.isTaskGoods == null ? 2 : this.isTaskGoods == true ? 1 : 0);
		out.writeByte(this.isYaopin == null ? 2 : this.isYaopin == true ? 1 : 0);
		out.writeByte(this.isCailiao == null ? 2 : this.isCailiao == true ? 1 : 0);
		out.writeByte(this.otherGoods == null ? 2 : this.otherGoods == true ? 1 : 0);
		out.writeInt(this.charSkillId == null ? Integer.MIN_VALUE : this.charSkillId);
		out.writeInt(this.horseSkillId == null ? Integer.MIN_VALUE : this.horseSkillId);
		out.writeInt(this.attackScope == null ? Integer.MIN_VALUE : this.attackScope);
		out.writeInt(this.attackTime == null ? Integer.MIN_VALUE : this.attackTime);
		out.writeByte(this.backSit == null ? 2 : this.backSit == true ? 1 : 0);
		out.writeByte(this.avoidMonster == null ? 2 : this.avoidMonster == true ? 1 : 0);
		out.writeByte(this.backUseRose == null ? 2 : this.backUseRose == true ? 1 : 0);
		out.writeByte(this.zhenqidan == null ? 2 : this.zhenqidan == true ? 1 : 0);
		out.writeByte(this.expdan == null ? 2 : this.expdan == true ? 1 : 0);
		out.writeByte(this.horseExpdan == null ? 2 : this.horseExpdan == true ? 1 : 0);
		out.writeByte(this.autoRevertMp == null ? 2 : this.autoRevertMp == true ? 1 : 0);
		out.writeByte(this.autoRevertSp == null ? 2 : this.autoRevertSp == true ? 1 : 0);
		out.writeByte(this.autoWushenBuff == null ? 2 : this.autoWushenBuff == true ? 1 : 0);
		out.writeByte(this.autoZhanyiBuff == null ? 2 : this.autoZhanyiBuff == true ? 1 : 0);
		out.writeByte(this.autoHorseCaoliao == null ? 2 : this.autoHorseCaoliao == true ? 1 : 0);
		out.writeByte(this.isMoney == null ? 2 : this.isMoney == true ? 1 : 0);
		out.writeByte(this.huolishendan == null ? 2 : this.huolishendan == true ? 1 : 0);
		out.writeByte(this.moshixiuli == null ? 2 : this.moshixiuli == true ? 1 : 0);
		out.writeByte(this.gekongduqi == null ? 2 : this.gekongduqi == true ? 1 : 0);
		out.writeByte(this.puducihang == null ? 2 : this.puducihang == true ? 1 : 0);
		out.writeByte(this.shouhu == null ? 2 : this.shouhu == true ? 1 : 0);
		out.writeByte(this.poxuekuangsha == null ? 2 : this.poxuekuangsha == true ? 1 : 0);
		out.writeByte(this.ningxuelihun == null ? 2 : this.ningxuelihun == true ? 1 : 0);
		out.writeByte(this.useallskill == null ? 2 : this.useallskill == true ? 1 : 0);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.autoRevertHp = cv(in.readByte());
		this.revertHp = in.readInt();
		this.revertHp = this.revertHp == Integer.MIN_VALUE ? null : this.revertHp;
		this.revertMp = in.readInt();
		this.revertMp = this.revertMp == Integer.MIN_VALUE ? null : this.revertMp;
		this.revertSp = in.readInt();
		this.revertSp = this.revertSp == Integer.MIN_VALUE ? null : this.revertSp;
		this.revertHpMethod = in.readInt();
		this.revertHpMethod = this.revertHpMethod == Integer.MIN_VALUE ? null : this.revertHpMethod;
		this.revertMpMethod = in.readInt();
		this.revertMpMethod = this.revertMpMethod == Integer.MIN_VALUE ? null : this.revertMpMethod;
		this.revertSpMethod = in.readInt();
		this.revertSpMethod = this.revertSpMethod == Integer.MIN_VALUE ? null : this.revertSpMethod;
		this.autoPickupEquip = cv(in.readByte());
		this.qualityEquip = in.readByte();
		this.limitPopsinger = in.readByte();
		this.gradeEquip = in.readInt();
		this.gradeEquip = this.gradeEquip == Integer.MIN_VALUE ? null : this.gradeEquip;
		this.isTaskGoods = cv(in.readByte());
		this.isYaopin = cv(in.readByte());
		this.isCailiao = cv(in.readByte());
		this.otherGoods = cv(in.readByte());
		this.charSkillId = in.readInt();
		this.charSkillId = this.charSkillId == Integer.MIN_VALUE ? null : this.charSkillId;
		this.horseSkillId = in.readInt();
		this.horseSkillId = this.horseSkillId == Integer.MIN_VALUE ? null : this.horseSkillId;
		this.attackScope = in.readInt();
		this.attackScope = this.attackScope == Integer.MIN_VALUE ? null : this.attackScope;
		this.attackTime = in.readInt();
		this.attackTime = this.attackTime == Integer.MIN_VALUE ? null : this.attackTime;
		this.backSit = cv(in.readByte());
		this.avoidMonster = cv(in.readByte());
		this.backUseRose = cv(in.readByte());
		this.zhenqidan = cv(in.readByte());
		this.expdan = cv(in.readByte());
		this.horseExpdan = cv(in.readByte());
		this.autoRevertMp = cv(in.readByte());
		this.autoRevertSp = cv(in.readByte());
		this.autoWushenBuff = cv(in.readByte());
		this.autoZhanyiBuff = cv(in.readByte());
		this.autoHorseCaoliao = cv(in.readByte());
		this.isMoney = cv(in.readByte());
		this.huolishendan = cv(in.readByte());
		this.moshixiuli = cv(in.readByte());
		this.gekongduqi = cv(in.readByte());
		this.puducihang = cv(in.readByte());
		this.shouhu = cv(in.readByte());
		this.poxuekuangsha = cv(in.readByte());
		this.ningxuelihun = cv(in.readByte());
		this.useallskill = cv(in.readByte());
	}

	private Boolean cv(byte b) {
		if (b == 0) {
			return false;
		} else if (b == 1) {
			return true;
		}
		return null;
	}

	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer characterId;
	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoRevertHp;
	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertHp;
	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertMp;
	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertSp;
	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertHpMethod;
	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertMpMethod;
	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer revertSpMethod;
	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoPickupEquip;
	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色）
	 * t_character_onhoor_config.f_quality_equip
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Byte qualityEquip;
	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花）
	 * t_character_onhoor_config.f_limit_popsinger
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Byte limitPopsinger;
	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer gradeEquip;
	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean isTaskGoods;
	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean isYaopin;
	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean isCailiao;
	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean otherGoods;
	/**
	 * 人物技能1id t_character_onhoor_config.f_char_skill_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer charSkillId;
	/**
	 * 坐骑使用技能1id t_character_onhoor_config.f_horse_skill_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer horseSkillId;
	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_scope
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer attackScope;
	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_time
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Integer attackTime;
	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean backSit;
	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean avoidMonster;
	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean backUseRose;
	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean zhenqidan;
	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean expdan;
	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean horseExpdan;
	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoRevertMp;
	/**
	 * 自动恢复体力(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_sp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoRevertSp;
	/**
	 * 自动使用武神buff（0不选中1选中） t_character_onhoor_config.f_auto_wushen_buff
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoWushenBuff;
	/**
	 * 自动使用战意buff（0不选中1选中） t_character_onhoor_config.f_auto_zhanyi_buff
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoZhanyiBuff;
	/**
	 * 骑乘/出战的坐骑自动吃草料（0不选中1选中） t_character_onhoor_config.f_auto_horse_caoliao
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean autoHorseCaoliao;
	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean isMoney;
	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean huolishendan;
	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean moshixiuli;
	/**
	 * 是否自动使用隔空渡气 t_character_onhoor_config.f_gekongduqi
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean gekongduqi;
	/**
	 * 是否自动使用普渡慈航 t_character_onhoor_config.f_puducihang
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean puducihang;
	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean shouhu;
	/**
	 * 是否自动使用破血狂杀 t_character_onhoor_config.f_poxuekuangsha
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean poxuekuangsha;
	/**
	 * 是否自动使用凝血离魂 t_character_onhoor_config.f_ningxuelihun
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean ningxuelihun;
	/**
	 * 是否自动续用以下辅助功能 t_character_onhoor_config.f_useallskill
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	private Boolean useallskill;

	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 * @return the value of t_character_onhoor_config.f_character_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_onhoor_config.f_character_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_revert_hp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoRevertHp() {
		return autoRevertHp;
	}

	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 * @param autoRevertHp
	 *            the value for t_character_onhoor_config.f_auto_revert_hp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoRevertHp(Boolean autoRevertHp) {
		this.autoRevertHp = autoRevertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_hp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertHp() {
		return revertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 * @param revertHp
	 *            the value for t_character_onhoor_config.f_revert_hp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertHp(Integer revertHp) {
		this.revertHp = revertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_mp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertMp() {
		return revertMp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 * @param revertMp
	 *            the value for t_character_onhoor_config.f_revert_mp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertMp(Integer revertMp) {
		this.revertMp = revertMp;
	}

	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_sp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertSp() {
		return revertSp;
	}

	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 * @param revertSp
	 *            the value for t_character_onhoor_config.f_revert_sp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertSp(Integer revertSp) {
		this.revertSp = revertSp;
	}

	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_hp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertHpMethod() {
		return revertHpMethod;
	}

	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 * @param revertHpMethod
	 *            the value for t_character_onhoor_config.f_revert_hp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertHpMethod(Integer revertHpMethod) {
		this.revertHpMethod = revertHpMethod;
	}

	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_mp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertMpMethod() {
		return revertMpMethod;
	}

	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 * @param revertMpMethod
	 *            the value for t_character_onhoor_config.f_revert_mp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertMpMethod(Integer revertMpMethod) {
		this.revertMpMethod = revertMpMethod;
	}

	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_sp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getRevertSpMethod() {
		return revertSpMethod;
	}

	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 * @param revertSpMethod
	 *            the value for t_character_onhoor_config.f_revert_sp_method
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setRevertSpMethod(Integer revertSpMethod) {
		this.revertSpMethod = revertSpMethod;
	}

	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_pickup_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoPickupEquip() {
		return autoPickupEquip;
	}

	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 * @param autoPickupEquip
	 *            the value for t_character_onhoor_config.f_auto_pickup_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoPickupEquip(Boolean autoPickupEquip) {
		this.autoPickupEquip = autoPickupEquip;
	}

	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色）
	 * t_character_onhoor_config.f_quality_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_quality_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Byte getQualityEquip() {
		return qualityEquip;
	}

	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色）
	 * t_character_onhoor_config.f_quality_equip
	 * 
	 * @param qualityEquip
	 *            the value for t_character_onhoor_config.f_quality_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setQualityEquip(Byte qualityEquip) {
		this.qualityEquip = qualityEquip;
	}

	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花）
	 * t_character_onhoor_config.f_limit_popsinger
	 * 
	 * @return the value of t_character_onhoor_config.f_limit_popsinger
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Byte getLimitPopsinger() {
		return limitPopsinger;
	}

	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花）
	 * t_character_onhoor_config.f_limit_popsinger
	 * 
	 * @param limitPopsinger
	 *            the value for t_character_onhoor_config.f_limit_popsinger
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setLimitPopsinger(Byte limitPopsinger) {
		this.limitPopsinger = limitPopsinger;
	}

	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_grade_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getGradeEquip() {
		return gradeEquip;
	}

	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 * @param gradeEquip
	 *            the value for t_character_onhoor_config.f_grade_equip
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setGradeEquip(Integer gradeEquip) {
		this.gradeEquip = gradeEquip;
	}

	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 * @return the value of t_character_onhoor_config.f_is_task_goods
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getIsTaskGoods() {
		return isTaskGoods;
	}

	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 * @param isTaskGoods
	 *            the value for t_character_onhoor_config.f_is_task_goods
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setIsTaskGoods(Boolean isTaskGoods) {
		this.isTaskGoods = isTaskGoods;
	}

	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 * @return the value of t_character_onhoor_config.f_is_yaopin
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getIsYaopin() {
		return isYaopin;
	}

	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 * @param isYaopin
	 *            the value for t_character_onhoor_config.f_is_yaopin
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setIsYaopin(Boolean isYaopin) {
		this.isYaopin = isYaopin;
	}

	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 * @return the value of t_character_onhoor_config.f_is_cailiao
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getIsCailiao() {
		return isCailiao;
	}

	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 * @param isCailiao
	 *            the value for t_character_onhoor_config.f_is_cailiao
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setIsCailiao(Boolean isCailiao) {
		this.isCailiao = isCailiao;
	}

	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 * @return the value of t_character_onhoor_config.f_other_goods
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getOtherGoods() {
		return otherGoods;
	}

	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 * @param otherGoods
	 *            the value for t_character_onhoor_config.f_other_goods
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setOtherGoods(Boolean otherGoods) {
		this.otherGoods = otherGoods;
	}

	/**
	 * 人物技能1id t_character_onhoor_config.f_char_skill_id
	 * 
	 * @return the value of t_character_onhoor_config.f_char_skill_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getCharSkillId() {
		return charSkillId;
	}

	/**
	 * 人物技能1id t_character_onhoor_config.f_char_skill_id
	 * 
	 * @param charSkillId
	 *            the value for t_character_onhoor_config.f_char_skill_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setCharSkillId(Integer charSkillId) {
		this.charSkillId = charSkillId;
	}

	/**
	 * 坐骑使用技能1id t_character_onhoor_config.f_horse_skill_id
	 * 
	 * @return the value of t_character_onhoor_config.f_horse_skill_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getHorseSkillId() {
		return horseSkillId;
	}

	/**
	 * 坐骑使用技能1id t_character_onhoor_config.f_horse_skill_id
	 * 
	 * @param horseSkillId
	 *            the value for t_character_onhoor_config.f_horse_skill_id
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setHorseSkillId(Integer horseSkillId) {
		this.horseSkillId = horseSkillId;
	}

	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_scope
	 * 
	 * @return the value of t_character_onhoor_config.f_attack_scope
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getAttackScope() {
		return attackScope;
	}

	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_scope
	 * 
	 * @param attackScope
	 *            the value for t_character_onhoor_config.f_attack_scope
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAttackScope(Integer attackScope) {
		this.attackScope = attackScope;
	}

	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_time
	 * 
	 * @return the value of t_character_onhoor_config.f_attack_time
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Integer getAttackTime() {
		return attackTime;
	}

	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位
	 * t_character_onhoor_config.f_attack_time
	 * 
	 * @param attackTime
	 *            the value for t_character_onhoor_config.f_attack_time
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAttackTime(Integer attackTime) {
		this.attackTime = attackTime;
	}

	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 * @return the value of t_character_onhoor_config.f_back_sit
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getBackSit() {
		return backSit;
	}

	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 * @param backSit
	 *            the value for t_character_onhoor_config.f_back_sit
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setBackSit(Boolean backSit) {
		this.backSit = backSit;
	}

	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 * @return the value of t_character_onhoor_config.f_avoid_monster
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAvoidMonster() {
		return avoidMonster;
	}

	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 * @param avoidMonster
	 *            the value for t_character_onhoor_config.f_avoid_monster
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAvoidMonster(Boolean avoidMonster) {
		this.avoidMonster = avoidMonster;
	}

	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 * @return the value of t_character_onhoor_config.f_back_use_rose
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getBackUseRose() {
		return backUseRose;
	}

	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 * @param backUseRose
	 *            the value for t_character_onhoor_config.f_back_use_rose
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setBackUseRose(Boolean backUseRose) {
		this.backUseRose = backUseRose;
	}

	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 * @return the value of t_character_onhoor_config.f_zhenqidan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getZhenqidan() {
		return zhenqidan;
	}

	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 * @param zhenqidan
	 *            the value for t_character_onhoor_config.f_zhenqidan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setZhenqidan(Boolean zhenqidan) {
		this.zhenqidan = zhenqidan;
	}

	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 * @return the value of t_character_onhoor_config.f_expdan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getExpdan() {
		return expdan;
	}

	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 * @param expdan
	 *            the value for t_character_onhoor_config.f_expdan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setExpdan(Boolean expdan) {
		this.expdan = expdan;
	}

	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 * @return the value of t_character_onhoor_config.f_horse_expdan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getHorseExpdan() {
		return horseExpdan;
	}

	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 * @param horseExpdan
	 *            the value for t_character_onhoor_config.f_horse_expdan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setHorseExpdan(Boolean horseExpdan) {
		this.horseExpdan = horseExpdan;
	}

	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_revert_mp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoRevertMp() {
		return autoRevertMp;
	}

	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 * @param autoRevertMp
	 *            the value for t_character_onhoor_config.f_auto_revert_mp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoRevertMp(Boolean autoRevertMp) {
		this.autoRevertMp = autoRevertMp;
	}

	/**
	 * 自动恢复体力(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_sp
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_revert_sp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoRevertSp() {
		return autoRevertSp;
	}

	/**
	 * 自动恢复体力(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_sp
	 * 
	 * @param autoRevertSp
	 *            the value for t_character_onhoor_config.f_auto_revert_sp
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoRevertSp(Boolean autoRevertSp) {
		this.autoRevertSp = autoRevertSp;
	}

	/**
	 * 自动使用武神buff（0不选中1选中） t_character_onhoor_config.f_auto_wushen_buff
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_wushen_buff
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoWushenBuff() {
		return autoWushenBuff;
	}

	/**
	 * 自动使用武神buff（0不选中1选中） t_character_onhoor_config.f_auto_wushen_buff
	 * 
	 * @param autoWushenBuff
	 *            the value for t_character_onhoor_config.f_auto_wushen_buff
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoWushenBuff(Boolean autoWushenBuff) {
		this.autoWushenBuff = autoWushenBuff;
	}

	/**
	 * 自动使用战意buff（0不选中1选中） t_character_onhoor_config.f_auto_zhanyi_buff
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_zhanyi_buff
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoZhanyiBuff() {
		return autoZhanyiBuff;
	}

	/**
	 * 自动使用战意buff（0不选中1选中） t_character_onhoor_config.f_auto_zhanyi_buff
	 * 
	 * @param autoZhanyiBuff
	 *            the value for t_character_onhoor_config.f_auto_zhanyi_buff
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoZhanyiBuff(Boolean autoZhanyiBuff) {
		this.autoZhanyiBuff = autoZhanyiBuff;
	}

	/**
	 * 骑乘/出战的坐骑自动吃草料（0不选中1选中） t_character_onhoor_config.f_auto_horse_caoliao
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_horse_caoliao
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getAutoHorseCaoliao() {
		return autoHorseCaoliao;
	}

	/**
	 * 骑乘/出战的坐骑自动吃草料（0不选中1选中） t_character_onhoor_config.f_auto_horse_caoliao
	 * 
	 * @param autoHorseCaoliao
	 *            the value for t_character_onhoor_config.f_auto_horse_caoliao
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setAutoHorseCaoliao(Boolean autoHorseCaoliao) {
		this.autoHorseCaoliao = autoHorseCaoliao;
	}

	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 * @return the value of t_character_onhoor_config.f_is_money
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getIsMoney() {
		return isMoney;
	}

	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 * @param isMoney
	 *            the value for t_character_onhoor_config.f_is_money
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setIsMoney(Boolean isMoney) {
		this.isMoney = isMoney;
	}

	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 * @return the value of t_character_onhoor_config.f_huolishendan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getHuolishendan() {
		return huolishendan;
	}

	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 * @param huolishendan
	 *            the value for t_character_onhoor_config.f_huolishendan
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setHuolishendan(Boolean huolishendan) {
		this.huolishendan = huolishendan;
	}

	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 * @return the value of t_character_onhoor_config.f_moshixiuli
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getMoshixiuli() {
		return moshixiuli;
	}

	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 * @param moshixiuli
	 *            the value for t_character_onhoor_config.f_moshixiuli
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setMoshixiuli(Boolean moshixiuli) {
		this.moshixiuli = moshixiuli;
	}

	/**
	 * 是否自动使用隔空渡气 t_character_onhoor_config.f_gekongduqi
	 * 
	 * @return the value of t_character_onhoor_config.f_gekongduqi
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getGekongduqi() {
		return gekongduqi;
	}

	/**
	 * 是否自动使用隔空渡气 t_character_onhoor_config.f_gekongduqi
	 * 
	 * @param gekongduqi
	 *            the value for t_character_onhoor_config.f_gekongduqi
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setGekongduqi(Boolean gekongduqi) {
		this.gekongduqi = gekongduqi;
	}

	/**
	 * 是否自动使用普渡慈航 t_character_onhoor_config.f_puducihang
	 * 
	 * @return the value of t_character_onhoor_config.f_puducihang
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getPuducihang() {
		return puducihang;
	}

	/**
	 * 是否自动使用普渡慈航 t_character_onhoor_config.f_puducihang
	 * 
	 * @param puducihang
	 *            the value for t_character_onhoor_config.f_puducihang
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setPuducihang(Boolean puducihang) {
		this.puducihang = puducihang;
	}

	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 * @return the value of t_character_onhoor_config.f_shouhu
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getShouhu() {
		return shouhu;
	}

	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 * @param shouhu
	 *            the value for t_character_onhoor_config.f_shouhu
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setShouhu(Boolean shouhu) {
		this.shouhu = shouhu;
	}

	/**
	 * 是否自动使用破血狂杀 t_character_onhoor_config.f_poxuekuangsha
	 * 
	 * @return the value of t_character_onhoor_config.f_poxuekuangsha
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getPoxuekuangsha() {
		return poxuekuangsha;
	}

	/**
	 * 是否自动使用破血狂杀 t_character_onhoor_config.f_poxuekuangsha
	 * 
	 * @param poxuekuangsha
	 *            the value for t_character_onhoor_config.f_poxuekuangsha
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setPoxuekuangsha(Boolean poxuekuangsha) {
		this.poxuekuangsha = poxuekuangsha;
	}

	/**
	 * 是否自动使用凝血离魂 t_character_onhoor_config.f_ningxuelihun
	 * 
	 * @return the value of t_character_onhoor_config.f_ningxuelihun
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getNingxuelihun() {
		return ningxuelihun;
	}

	/**
	 * 是否自动使用凝血离魂 t_character_onhoor_config.f_ningxuelihun
	 * 
	 * @param ningxuelihun
	 *            the value for t_character_onhoor_config.f_ningxuelihun
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setNingxuelihun(Boolean ningxuelihun) {
		this.ningxuelihun = ningxuelihun;
	}

	/**
	 * 是否自动续用以下辅助功能 t_character_onhoor_config.f_useallskill
	 * 
	 * @return the value of t_character_onhoor_config.f_useallskill
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public Boolean getUseallskill() {
		return useallskill;
	}

	/**
	 * 是否自动续用以下辅助功能 t_character_onhoor_config.f_useallskill
	 * 
	 * @param useallskill
	 *            the value for t_character_onhoor_config.f_useallskill
	 * @ibatorgenerated Fri Jul 01 21:57:02 CST 2011
	 */
	public void setUseallskill(Boolean useallskill) {
		this.useallskill = useallskill;
	}
}
