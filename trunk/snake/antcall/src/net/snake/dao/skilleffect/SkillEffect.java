package net.snake.dao.skilleffect;

public class SkillEffect {

	/**
	 * t_skill_effect.f_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer id;
	/**
	 * 鏁堟灉鍚嶇О t_skill_effect.f_name
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String name;
	/**
	 * 浣滅敤鑼冨洿锛�鍗曚綋銆�缇や綋锛�t_skill_effect.f_user_scope
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte userScope;
	/**
	 * 鑼冨洿鍗婂緞璁惧畾 t_skill_effect.f_scope_radius
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer scopeRadius;
	/**
	 * 浣滅敤浜烘暟涓婇檺 t_skill_effect.f_use_limit
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer useLimit;
	/**
	 * AOE绉嶇被(鑷韩涓烘牳蹇�銆佺洰鏍囦负鏍稿績2锛�t_skill_effect.f_aoe_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte aoeType;
	/**
	 * 浼ゅ鍊艰绠楁柟寮忥紙鍗曚竴鐩爣1锛屽叏閮ㄧ洰鏍�(鎵�湁鐨勪激瀹冲钩鍒�锛�t_skill_effect.f_hurt_consideration_way
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte hurtConsiderationWay;
	/**
	 * t_skill_effect.f_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte type;
	/**
	 * 浼ゅ鏁板� t_skill_effect.f_hurt_value
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer hurtValue;
	/**
	 * 鎬荤殑鎸佺画鏃堕棿锛坢s锛�t_skill_effect.f_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer duration;
	/**
	 * 鍗曟浣滅敤鏃堕棿 t_skill_effect.f_pre_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer preDuration;
	/**
	 * 鏁堟灉閲嶅閫夐」锛氭晥鏋滃彔鍔�銆佹晥鏋滄浛鎹�銆侀噸澶嶆棤鏁� t_skill_effect.f_effect_repeat_option
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte effectRepeatOption;
	/**
	 * 鍙犲姞涓婇檺娆℃暟锛屽彧鏈夐�鎷╂晥鏋滃彔鍔犳椂鏈夌敤 t_skill_effect.f_effect_repeat_count
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer effectRepeatCount;
	/**
	 * 鏁堟灉瀵圭珛绫诲瀷锛堟柦鏀�銆佸厤鐤�锛�t_skill_effect.f_special_effect_change
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte specialEffectChange;
	/**
	 * 鍙犲姞绫诲埆(涓�噸?浜岄噸?) t_skill_effect.f_effect_repeat_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte effectRepeatType;
	/**
	 * 鎻忚堪 t_skill_effect.f_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String desc;
	/**
	 * 浼ゅ闄勫姞 t_skill_effect.f_hurt_adds
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer hurtAdds;
	/**
	 * 鐧惧垎姣�t_skill_effect.f_percent
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer percent;
	/**
	 * 鏄惁瑙﹀彂浼ゅ(0瑙﹀彂,1涓嶈Е鍙� t_skill_effect.f_type_pn
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte typePn;
	/**
	 * 鍑婚�璺濈(榛樿) t_skill_effect.f_jitui_distance
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer jituiDistance;
	/**
	 * t_skill_effect.f_icon_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer iconId;
	/**
	 * 鎹㈣id t_skill_effect.f_effect_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer effectId;
	/**
	 * 鐢熸垚鐗规畩閬撳叿(椹ザ) t_skill_effect.f_goodmodel_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer goodmodelId;
	/**
	 * 0涓轰笉骞挎挱姝ｉ潰buff,1涓哄箍鎾礋闈uff2骞挎挱姝ｉ潰buff,3vip鏈堝崱锛堥潪鍦ㄧ嚎璁℃椂绫籦uffer锛�t_skill_effect.f_buff_flag
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte buffFlag;
	/**
	 * 0姝讳骸鏃朵笉娓呴櫎buff锛�姝讳骸鏃舵竻闄uff t_skill_effect.f_is_die_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte isDieClean;
	/**
	 * 鏁板�闅忔満鏈�皬鍊�t_skill_effect.f_hurt_value_min
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer hurtValueMin;
	/**
	 * 鏁板�闅忔満鏈�ぇ鍊�t_skill_effect.f_hurt_value_max
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer hurtValueMax;
	/**
	 * 0鏂藉姞鑰呮浜′笅绾挎椂涓嶆竻闄uff锛�鏂藉姞鑰呮浜′笅绾挎椂娓呴櫎buff t_skill_effect.f_attacker_is_ide_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Byte attackerIsIdeClean;
	/**
	 * 涓瘨涓婇檺琛�噺 t_skill_effect.f_du_max_hurt
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private Integer duMaxHurt;
	/**
	 * 鎻愮ず鎻忚堪 t_skill_effect.f_clips_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String clipsDesc;
	/**
	 * 鏁堟灉鍚嶇О鍥介檯鍖�t_skill_effect.f_name_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String nameI18n;
	/**
	 * 鏁堟灉鎻忚堪鍥介檯鍖�t_skill_effect.f_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String descI18n;
	/**
	 * 鎻愮ず鎻忚堪鍥介檯鍖�t_skill_effect.f_clips_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	private String clipsDescI18n;

	/**
	 * t_skill_effect.f_id
	 * @return  the value of t_skill_effect.f_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_skill_effect.f_id
	 * @param id  the value for t_skill_effect.f_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 鏁堟灉鍚嶇О t_skill_effect.f_name
	 * @return  the value of t_skill_effect.f_name
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * 鏁堟灉鍚嶇О t_skill_effect.f_name
	 * @param name  the value for t_skill_effect.f_name
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 浣滅敤鑼冨洿锛�鍗曚綋銆�缇や綋锛�t_skill_effect.f_user_scope
	 * @return  the value of t_skill_effect.f_user_scope
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getUserScope() {
		return userScope;
	}

	/**
	 * 浣滅敤鑼冨洿锛�鍗曚綋銆�缇や綋锛�t_skill_effect.f_user_scope
	 * @param userScope  the value for t_skill_effect.f_user_scope
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setUserScope(Byte userScope) {
		this.userScope = userScope;
	}

	/**
	 * 鑼冨洿鍗婂緞璁惧畾 t_skill_effect.f_scope_radius
	 * @return  the value of t_skill_effect.f_scope_radius
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getScopeRadius() {
		return scopeRadius;
	}

	/**
	 * 鑼冨洿鍗婂緞璁惧畾 t_skill_effect.f_scope_radius
	 * @param scopeRadius  the value for t_skill_effect.f_scope_radius
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setScopeRadius(Integer scopeRadius) {
		this.scopeRadius = scopeRadius;
	}

	/**
	 * 浣滅敤浜烘暟涓婇檺 t_skill_effect.f_use_limit
	 * @return  the value of t_skill_effect.f_use_limit
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getUseLimit() {
		return useLimit;
	}

	/**
	 * 浣滅敤浜烘暟涓婇檺 t_skill_effect.f_use_limit
	 * @param useLimit  the value for t_skill_effect.f_use_limit
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setUseLimit(Integer useLimit) {
		this.useLimit = useLimit;
	}

	/**
	 * AOE绉嶇被(鑷韩涓烘牳蹇�銆佺洰鏍囦负鏍稿績2锛�t_skill_effect.f_aoe_type
	 * @return  the value of t_skill_effect.f_aoe_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getAoeType() {
		return aoeType;
	}

	/**
	 * AOE绉嶇被(鑷韩涓烘牳蹇�銆佺洰鏍囦负鏍稿績2锛�t_skill_effect.f_aoe_type
	 * @param aoeType  the value for t_skill_effect.f_aoe_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setAoeType(Byte aoeType) {
		this.aoeType = aoeType;
	}

	/**
	 * 浼ゅ鍊艰绠楁柟寮忥紙鍗曚竴鐩爣1锛屽叏閮ㄧ洰鏍�(鎵�湁鐨勪激瀹冲钩鍒�锛�t_skill_effect.f_hurt_consideration_way
	 * @return  the value of t_skill_effect.f_hurt_consideration_way
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getHurtConsiderationWay() {
		return hurtConsiderationWay;
	}

	/**
	 * 浼ゅ鍊艰绠楁柟寮忥紙鍗曚竴鐩爣1锛屽叏閮ㄧ洰鏍�(鎵�湁鐨勪激瀹冲钩鍒�锛�t_skill_effect.f_hurt_consideration_way
	 * @param hurtConsiderationWay  the value for t_skill_effect.f_hurt_consideration_way
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setHurtConsiderationWay(Byte hurtConsiderationWay) {
		this.hurtConsiderationWay = hurtConsiderationWay;
	}

	/**
	 * t_skill_effect.f_type
	 * @return  the value of t_skill_effect.f_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * t_skill_effect.f_type
	 * @param type  the value for t_skill_effect.f_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 浼ゅ鏁板� t_skill_effect.f_hurt_value
	 * @return  the value of t_skill_effect.f_hurt_value
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getHurtValue() {
		return hurtValue;
	}

	/**
	 * 浼ゅ鏁板� t_skill_effect.f_hurt_value
	 * @param hurtValue  the value for t_skill_effect.f_hurt_value
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setHurtValue(Integer hurtValue) {
		this.hurtValue = hurtValue;
	}

	/**
	 * 鎬荤殑鎸佺画鏃堕棿锛坢s锛�t_skill_effect.f_duration
	 * @return  the value of t_skill_effect.f_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * 鎬荤殑鎸佺画鏃堕棿锛坢s锛�t_skill_effect.f_duration
	 * @param duration  the value for t_skill_effect.f_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * 鍗曟浣滅敤鏃堕棿 t_skill_effect.f_pre_duration
	 * @return  the value of t_skill_effect.f_pre_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getPreDuration() {
		return preDuration;
	}

	/**
	 * 鍗曟浣滅敤鏃堕棿 t_skill_effect.f_pre_duration
	 * @param preDuration  the value for t_skill_effect.f_pre_duration
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setPreDuration(Integer preDuration) {
		this.preDuration = preDuration;
	}

	/**
	 * 鏁堟灉閲嶅閫夐」锛氭晥鏋滃彔鍔�銆佹晥鏋滄浛鎹�銆侀噸澶嶆棤鏁� t_skill_effect.f_effect_repeat_option
	 * @return  the value of t_skill_effect.f_effect_repeat_option
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getEffectRepeatOption() {
		return effectRepeatOption;
	}

	/**
	 * 鏁堟灉閲嶅閫夐」锛氭晥鏋滃彔鍔�銆佹晥鏋滄浛鎹�銆侀噸澶嶆棤鏁� t_skill_effect.f_effect_repeat_option
	 * @param effectRepeatOption  the value for t_skill_effect.f_effect_repeat_option
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setEffectRepeatOption(Byte effectRepeatOption) {
		this.effectRepeatOption = effectRepeatOption;
	}

	/**
	 * 鍙犲姞涓婇檺娆℃暟锛屽彧鏈夐�鎷╂晥鏋滃彔鍔犳椂鏈夌敤 t_skill_effect.f_effect_repeat_count
	 * @return  the value of t_skill_effect.f_effect_repeat_count
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getEffectRepeatCount() {
		return effectRepeatCount;
	}

	/**
	 * 鍙犲姞涓婇檺娆℃暟锛屽彧鏈夐�鎷╂晥鏋滃彔鍔犳椂鏈夌敤 t_skill_effect.f_effect_repeat_count
	 * @param effectRepeatCount  the value for t_skill_effect.f_effect_repeat_count
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setEffectRepeatCount(Integer effectRepeatCount) {
		this.effectRepeatCount = effectRepeatCount;
	}

	/**
	 * 鏁堟灉瀵圭珛绫诲瀷锛堟柦鏀�銆佸厤鐤�锛�t_skill_effect.f_special_effect_change
	 * @return  the value of t_skill_effect.f_special_effect_change
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getSpecialEffectChange() {
		return specialEffectChange;
	}

	/**
	 * 鏁堟灉瀵圭珛绫诲瀷锛堟柦鏀�銆佸厤鐤�锛�t_skill_effect.f_special_effect_change
	 * @param specialEffectChange  the value for t_skill_effect.f_special_effect_change
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setSpecialEffectChange(Byte specialEffectChange) {
		this.specialEffectChange = specialEffectChange;
	}

	/**
	 * 鍙犲姞绫诲埆(涓�噸?浜岄噸?) t_skill_effect.f_effect_repeat_type
	 * @return  the value of t_skill_effect.f_effect_repeat_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getEffectRepeatType() {
		return effectRepeatType;
	}

	/**
	 * 鍙犲姞绫诲埆(涓�噸?浜岄噸?) t_skill_effect.f_effect_repeat_type
	 * @param effectRepeatType  the value for t_skill_effect.f_effect_repeat_type
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setEffectRepeatType(Byte effectRepeatType) {
		this.effectRepeatType = effectRepeatType;
	}

	/**
	 * 鎻忚堪 t_skill_effect.f_desc
	 * @return  the value of t_skill_effect.f_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 鎻忚堪 t_skill_effect.f_desc
	 * @param desc  the value for t_skill_effect.f_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 浼ゅ闄勫姞 t_skill_effect.f_hurt_adds
	 * @return  the value of t_skill_effect.f_hurt_adds
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getHurtAdds() {
		return hurtAdds;
	}

	/**
	 * 浼ゅ闄勫姞 t_skill_effect.f_hurt_adds
	 * @param hurtAdds  the value for t_skill_effect.f_hurt_adds
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setHurtAdds(Integer hurtAdds) {
		this.hurtAdds = hurtAdds;
	}

	/**
	 * 鐧惧垎姣�t_skill_effect.f_percent
	 * @return  the value of t_skill_effect.f_percent
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getPercent() {
		return percent;
	}

	/**
	 * 鐧惧垎姣�t_skill_effect.f_percent
	 * @param percent  the value for t_skill_effect.f_percent
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	/**
	 * 鏄惁瑙﹀彂浼ゅ(0瑙﹀彂,1涓嶈Е鍙� t_skill_effect.f_type_pn
	 * @return  the value of t_skill_effect.f_type_pn
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getTypePn() {
		return typePn;
	}

	/**
	 * 鏄惁瑙﹀彂浼ゅ(0瑙﹀彂,1涓嶈Е鍙� t_skill_effect.f_type_pn
	 * @param typePn  the value for t_skill_effect.f_type_pn
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setTypePn(Byte typePn) {
		this.typePn = typePn;
	}

	/**
	 * 鍑婚�璺濈(榛樿) t_skill_effect.f_jitui_distance
	 * @return  the value of t_skill_effect.f_jitui_distance
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getJituiDistance() {
		return jituiDistance;
	}

	/**
	 * 鍑婚�璺濈(榛樿) t_skill_effect.f_jitui_distance
	 * @param jituiDistance  the value for t_skill_effect.f_jitui_distance
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setJituiDistance(Integer jituiDistance) {
		this.jituiDistance = jituiDistance;
	}

	/**
	 * t_skill_effect.f_icon_id
	 * @return  the value of t_skill_effect.f_icon_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getIconId() {
		return iconId;
	}

	/**
	 * t_skill_effect.f_icon_id
	 * @param iconId  the value for t_skill_effect.f_icon_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	/**
	 * 鎹㈣id t_skill_effect.f_effect_id
	 * @return  the value of t_skill_effect.f_effect_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getEffectId() {
		return effectId;
	}

	/**
	 * 鎹㈣id t_skill_effect.f_effect_id
	 * @param effectId  the value for t_skill_effect.f_effect_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setEffectId(Integer effectId) {
		this.effectId = effectId;
	}

	/**
	 * 鐢熸垚鐗规畩閬撳叿(椹ザ) t_skill_effect.f_goodmodel_id
	 * @return  the value of t_skill_effect.f_goodmodel_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 鐢熸垚鐗规畩閬撳叿(椹ザ) t_skill_effect.f_goodmodel_id
	 * @param goodmodelId  the value for t_skill_effect.f_goodmodel_id
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 0涓轰笉骞挎挱姝ｉ潰buff,1涓哄箍鎾礋闈uff2骞挎挱姝ｉ潰buff,3vip鏈堝崱锛堥潪鍦ㄧ嚎璁℃椂绫籦uffer锛�t_skill_effect.f_buff_flag
	 * @return  the value of t_skill_effect.f_buff_flag
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getBuffFlag() {
		return buffFlag;
	}

	/**
	 * 0涓轰笉骞挎挱姝ｉ潰buff,1涓哄箍鎾礋闈uff2骞挎挱姝ｉ潰buff,3vip鏈堝崱锛堥潪鍦ㄧ嚎璁℃椂绫籦uffer锛�t_skill_effect.f_buff_flag
	 * @param buffFlag  the value for t_skill_effect.f_buff_flag
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setBuffFlag(Byte buffFlag) {
		this.buffFlag = buffFlag;
	}

	/**
	 * 0姝讳骸鏃朵笉娓呴櫎buff锛�姝讳骸鏃舵竻闄uff t_skill_effect.f_is_die_clean
	 * @return  the value of t_skill_effect.f_is_die_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getIsDieClean() {
		return isDieClean;
	}

	/**
	 * 0姝讳骸鏃朵笉娓呴櫎buff锛�姝讳骸鏃舵竻闄uff t_skill_effect.f_is_die_clean
	 * @param isDieClean  the value for t_skill_effect.f_is_die_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setIsDieClean(Byte isDieClean) {
		this.isDieClean = isDieClean;
	}

	/**
	 * 鏁板�闅忔満鏈�皬鍊�t_skill_effect.f_hurt_value_min
	 * @return  the value of t_skill_effect.f_hurt_value_min
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getHurtValueMin() {
		return hurtValueMin;
	}

	/**
	 * 鏁板�闅忔満鏈�皬鍊�t_skill_effect.f_hurt_value_min
	 * @param hurtValueMin  the value for t_skill_effect.f_hurt_value_min
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setHurtValueMin(Integer hurtValueMin) {
		this.hurtValueMin = hurtValueMin;
	}

	/**
	 * 鏁板�闅忔満鏈�ぇ鍊�t_skill_effect.f_hurt_value_max
	 * @return  the value of t_skill_effect.f_hurt_value_max
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getHurtValueMax() {
		return hurtValueMax;
	}

	/**
	 * 鏁板�闅忔満鏈�ぇ鍊�t_skill_effect.f_hurt_value_max
	 * @param hurtValueMax  the value for t_skill_effect.f_hurt_value_max
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setHurtValueMax(Integer hurtValueMax) {
		this.hurtValueMax = hurtValueMax;
	}

	/**
	 * 0鏂藉姞鑰呮浜′笅绾挎椂涓嶆竻闄uff锛�鏂藉姞鑰呮浜′笅绾挎椂娓呴櫎buff t_skill_effect.f_attacker_is_ide_clean
	 * @return  the value of t_skill_effect.f_attacker_is_ide_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Byte getAttackerIsIdeClean() {
		return attackerIsIdeClean;
	}

	/**
	 * 0鏂藉姞鑰呮浜′笅绾挎椂涓嶆竻闄uff锛�鏂藉姞鑰呮浜′笅绾挎椂娓呴櫎buff t_skill_effect.f_attacker_is_ide_clean
	 * @param attackerIsIdeClean  the value for t_skill_effect.f_attacker_is_ide_clean
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setAttackerIsIdeClean(Byte attackerIsIdeClean) {
		this.attackerIsIdeClean = attackerIsIdeClean;
	}

	/**
	 * 涓瘨涓婇檺琛�噺 t_skill_effect.f_du_max_hurt
	 * @return  the value of t_skill_effect.f_du_max_hurt
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public Integer getDuMaxHurt() {
		return duMaxHurt;
	}

	/**
	 * 涓瘨涓婇檺琛�噺 t_skill_effect.f_du_max_hurt
	 * @param duMaxHurt  the value for t_skill_effect.f_du_max_hurt
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setDuMaxHurt(Integer duMaxHurt) {
		this.duMaxHurt = duMaxHurt;
	}

	/**
	 * 鎻愮ず鎻忚堪 t_skill_effect.f_clips_desc
	 * @return  the value of t_skill_effect.f_clips_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getClipsDesc() {
		return clipsDesc;
	}

	/**
	 * 鎻愮ず鎻忚堪 t_skill_effect.f_clips_desc
	 * @param clipsDesc  the value for t_skill_effect.f_clips_desc
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setClipsDesc(String clipsDesc) {
		this.clipsDesc = clipsDesc;
	}

	/**
	 * 鏁堟灉鍚嶇О鍥介檯鍖�t_skill_effect.f_name_i18n
	 * @return  the value of t_skill_effect.f_name_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 鏁堟灉鍚嶇О鍥介檯鍖�t_skill_effect.f_name_i18n
	 * @param nameI18n  the value for t_skill_effect.f_name_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 鏁堟灉鎻忚堪鍥介檯鍖�t_skill_effect.f_desc_i18n
	 * @return  the value of t_skill_effect.f_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 鏁堟灉鎻忚堪鍥介檯鍖�t_skill_effect.f_desc_i18n
	 * @param descI18n  the value for t_skill_effect.f_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 鎻愮ず鎻忚堪鍥介檯鍖�t_skill_effect.f_clips_desc_i18n
	 * @return  the value of t_skill_effect.f_clips_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public String getClipsDescI18n() {
		return clipsDescI18n;
	}

	/**
	 * 鎻愮ず鎻忚堪鍥介檯鍖�t_skill_effect.f_clips_desc_i18n
	 * @param clipsDescI18n  the value for t_skill_effect.f_clips_desc_i18n
	 * @ibatorgenerated  Wed Jul 27 15:40:01 CST 2011
	 */
	public void setClipsDescI18n(String clipsDescI18n) {
		this.clipsDescI18n = clipsDescI18n;
	}
}
