package net.snake.dao.skill;

public class Skill {

	/**
	 * 鎶�兘id t_skill.f_id
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer id;
	/**
	 * 鎶�兘鍚嶇О t_skill.f_name
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String name;
	/**
	 * 鎶�兘鍥炬爣 t_skill.f_image
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer image;
	/**
	 * 鍐峰嵈鏃堕棿(姣ms) t_skill.f_coolingtime
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer coolingtime;
	/**
	 * 鎶�兘鏄惁瑙﹀彂鍏叡鍐峰嵈鏃堕棿,鏄�,鍚� t_skill.f_iscommon_cd
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Boolean iscommonCd;
	/**
	 * 鎶�兘娑堣�鍙傛暟锛圚P-1銆丮P-2銆丼P-3  娲诲姏4銆佸府璐�锛�t_skill.f_depletion_parameter
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte depletionParameter;
	/**
	 * 鎶�兘娑堣�鍙傛暟鍊�t_skill.f_depletion_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer depletionValue;
	/**
	 * 閲婃斁璺濈 t_skill.f_distance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer distance;
	/**
	 * 鎶�兘浣跨敤鏂瑰紡(涓诲姩浣跨敤1銆佽鍔ㄥ姞鎴�锛�t_skill.f_useway
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte useway;
	/**
	 * 鎶�兘鐩爣锛堣嚜宸�銆佽嚜宸辨墍鍦ㄧ殑灏忛槦2銆佸弸濂界洰鏍�銆佽寖鍥村唴鏁屽鐩爣4,褰撳墠鐩爣6,鍦板浘鐐瑰仛鐩爣7,8鐐瑰嚮蹇嵎閿洿鎺ラ�杩囬紶鏍囦綅缃噴鏀�.瀵硅嚜宸辩殑閰嶅伓閲婃斁10瀵规鍦ㄤ笌鎮ㄧ殑閰嶅伓鍙戠敓鎴樻枟鐨勭洰鏍囦娇鐢�1浼犻� t_skill.f_target
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte target;
	/**
	 * 鍏宠仈鐨勬晥鏋渋ds t_skill.f_effect_ids
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String effectIds;
	/**
	 * 鏄惁涓烘櫘閫氭敾鍑伙紝0涓嶆槸锛�鏄�t_skill.f_isgeneral
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte isgeneral;
	/**
	 * 浜虹墿瀛︿範绛夌骇闄愬埗 t_skill.f_char_level
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte charLevel;
	/**
	 * 闂ㄦ淳(0-涓嶆樉绀洪棬娲�1 - 灏戞灄,2 - 姝﹀綋,3 - 鍙ゅ,4 - 宄ㄧ湁) t_skill.f_popsinger
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte popsinger;
	/**
	 * 鎵�渶鎶�兘涔︼紙瀵瑰簲goomodel id瀛楁锛�t_skill.f_learning_book
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer learningBook;
	/**
	 * 鎻忚堪 t_skill.f_desc
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String desc;
	/**
	 * 姝﹀姛淇偧闅惧害绯绘暟 t_skill.f_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer revise;
	/**
	 * 鎶�兘浼ゅ淇绯绘暟 t_skill.f_hurt_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Float hurtRevise;
	/**
	 * 鎶�兘浼ゅ闄勫姞 t_skill.f_hurt_append
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer hurtAppend;
	/**
	 * 灞傛暟涓婇檺 t_skill.f_grade_limit
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer gradeLimit;
	/**
	 * 1椹殑鎶�兘 2瑙掕壊鐨勬妧鑳�3鎬墿鎶�兘 t_skill.f_skilltype
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer skilltype;
	/**
	 * 鎶�兘瑙﹀彂鍑犵巼 t_skill.f_trigger_probability
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer triggerProbability;
	/**
	 * 鎶�兘灞曠ず鏁堟灉0 t_skill.f_effect_res_id0
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer effectResId0;
	/**
	 * 鎶�兘灞曠ず鏁堟灉1 t_skill.f_effect_res_id1
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer effectResId1;
	/**
	 * 鎶�兘灞曠ず鏁堟灉2 t_skill.f_effect_res_id2
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer effectResId2;
	/**
	 * 鏄剧ず椤哄簭 t_skill.f_show_order
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer showOrder;
	/**
	 * 鍏宠仈鎶�兘 t_skill.f_skill_relevance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer skillRelevance;
	/**
	 * 鍔ㄤ綔寤舵椂鏃堕棿(姣) t_skill.f_play_delay
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer playDelay;
	/**
	 * 鏁堟灉椋炶閫熷害(鍍忕礌/姣) t_skill.f_fly_speed
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer flySpeed;
	/**
	 * 0闈炲垱寤鸿鑹叉椂瀛︿範1鍒涘缓瑙掕壊鏃跺涔�t_skill.f_create_learn
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte createLearn;
	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿绫诲瀷 t_skill.f_common_cool_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte commonCoolType;
	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿(姣) t_skill.f_common_cool_time
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer commonCoolTime;
	/**
	 * 鎶�兘甯︽潵鐨勪粐鎭ㄥ� t_skill.f_enmity_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer enmityValue;
	/**
	 * 澧炵泭鎬ф妧鑳�涓嶅彲璁句负榛樿)1/浼ゅ鎬ф妧鑳�(鍙涓洪粯璁� t_skill.f_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte type;
	/**
	 * 鎶�兘澶у浘鏍�t_skill.f_big_ico
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer bigIco;
	/**
	 * 闊虫晥 t_skill.f_sound
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer sound;
	/**
	 * 0鏀诲嚮鐩爣鏃惰Е鍙�琚敾鍑绘椂瑙﹀彂 t_skill.f_hurted_trib
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Boolean hurtedTrib;
	/**
	 * 1闂ㄦ淳缁濆2姹熸箹缁濆3鍏朵粬鎿嶄綔4.甯淳鎶�兘5.澶鎶�兘6.鐜変僵鎶�兘 t_skill.f_wugong_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte wugongType;
	/**
	 * boss鑾峰緱|npc璐拱|浜虹墿鍗囩骇鑷姩瀛︿範 t_skill.f_source
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String source;
	/**
	 * 瀵瑰簲鐨勬姉鎬ф妧鑳絠d t_skill.f_kanxing_skill
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer kanxingSkill;
	/**
	 * 琚姩鎶�兘甯︽潵鐨勫熀纭��(鐩墠浣跨敤鍦ㄥ潗楠戞妧鑳戒笂) t_skill.f_beidong_base_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer beidongBaseValue;
	/**
	 * (0涓嶆槸1鏄�鍧愰獞缁勫悎鎶�兘 t_skill.f_is_zuhe
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Byte isZuhe;
	/**
	 * 甯淳鎶�兘娑堣�甯础(瀛︿範鏃跺�鐢� t_skill.f_bang_gong
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer bangGong;
	/**
	 * 鎺ㄨ崘瀛︿範鐨勭被鍨�0:涓嶆帹鑽� 1锛氭帹鑽愰獞涔樺潗楠戝涔�2锛氭帹鑽愬嚭鎴樺潗楠戝涔�t_skill.f_learn_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private Integer learnType;
	/**
	 * 鎶�兘鍚嶇О鍥介檯鍖�t_skill.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String nameI18n;
	/**
	 * 鎶�兘鎻忚堪鍥介檯鍖�t_skill.f_desc_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String descI18n;
	/**
	 * 鎶�兘鑾峰緱鏂瑰紡鍥介檯鍖�t_skill.f_source_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	private String sourceI18n;
	
	private String showAction;

	private Integer arrowWay;
	
	private String upgradedes;
	private String upgradedesci18n;
	
	public Integer getArrowWay() {
		return arrowWay;
	}

	public void setArrowWay(Integer arrowWay) {
		this.arrowWay = arrowWay;
	}

	public String getShowAction() {
		return showAction;
	}

	public void setShowAction(String showAction) {
		this.showAction = showAction;
	}

	/**
	 * 鎶�兘id t_skill.f_id
	 * @return  the value of t_skill.f_id
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 鎶�兘id t_skill.f_id
	 * @param id  the value for t_skill.f_id
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 鎶�兘鍚嶇О t_skill.f_name
	 * @return  the value of t_skill.f_name
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * 鎶�兘鍚嶇О t_skill.f_name
	 * @param name  the value for t_skill.f_name
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 鎶�兘鍥炬爣 t_skill.f_image
	 * @return  the value of t_skill.f_image
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getImage() {
		return image;
	}

	/**
	 * 鎶�兘鍥炬爣 t_skill.f_image
	 * @param image  the value for t_skill.f_image
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setImage(Integer image) {
		this.image = image;
	}

	/**
	 * 鍐峰嵈鏃堕棿(姣ms) t_skill.f_coolingtime
	 * @return  the value of t_skill.f_coolingtime
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getCoolingtime() {
		return coolingtime;
	}

	/**
	 * 鍐峰嵈鏃堕棿(姣ms) t_skill.f_coolingtime
	 * @param coolingtime  the value for t_skill.f_coolingtime
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setCoolingtime(Integer coolingtime) {
		this.coolingtime = coolingtime;
	}

	/**
	 * 鎶�兘鏄惁瑙﹀彂鍏叡鍐峰嵈鏃堕棿,鏄�,鍚� t_skill.f_iscommon_cd
	 * @return  the value of t_skill.f_iscommon_cd
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Boolean getIscommonCd() {
		return iscommonCd;
	}

	/**
	 * 鎶�兘鏄惁瑙﹀彂鍏叡鍐峰嵈鏃堕棿,鏄�,鍚� t_skill.f_iscommon_cd
	 * @param iscommonCd  the value for t_skill.f_iscommon_cd
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setIscommonCd(Boolean iscommonCd) {
		this.iscommonCd = iscommonCd;
	}

	/**
	 * 鎶�兘娑堣�鍙傛暟锛圚P-1銆丮P-2銆丼P-3  娲诲姏4銆佸府璐�锛�t_skill.f_depletion_parameter
	 * @return  the value of t_skill.f_depletion_parameter
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getDepletionParameter() {
		return depletionParameter;
	}

	/**
	 * 鎶�兘娑堣�鍙傛暟锛圚P-1銆丮P-2銆丼P-3  娲诲姏4銆佸府璐�锛�t_skill.f_depletion_parameter
	 * @param depletionParameter  the value for t_skill.f_depletion_parameter
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setDepletionParameter(Byte depletionParameter) {
		this.depletionParameter = depletionParameter;
	}

	/**
	 * 鎶�兘娑堣�鍙傛暟鍊�t_skill.f_depletion_value
	 * @return  the value of t_skill.f_depletion_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getDepletionValue() {
		return depletionValue;
	}

	/**
	 * 鎶�兘娑堣�鍙傛暟鍊�t_skill.f_depletion_value
	 * @param depletionValue  the value for t_skill.f_depletion_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setDepletionValue(Integer depletionValue) {
		this.depletionValue = depletionValue;
	}

	/**
	 * 閲婃斁璺濈 t_skill.f_distance
	 * @return  the value of t_skill.f_distance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * 閲婃斁璺濈 t_skill.f_distance
	 * @param distance  the value for t_skill.f_distance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * 鎶�兘浣跨敤鏂瑰紡(涓诲姩浣跨敤1銆佽鍔ㄥ姞鎴�锛�t_skill.f_useway
	 * @return  the value of t_skill.f_useway
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getUseway() {
		return useway;
	}

	/**
	 * 鎶�兘浣跨敤鏂瑰紡(涓诲姩浣跨敤1銆佽鍔ㄥ姞鎴�锛�t_skill.f_useway
	 * @param useway  the value for t_skill.f_useway
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setUseway(Byte useway) {
		this.useway = useway;
	}

	/**
	 * 鎶�兘鐩爣锛堣嚜宸�銆佽嚜宸辨墍鍦ㄧ殑灏忛槦2銆佸弸濂界洰鏍�銆佽寖鍥村唴鏁屽鐩爣4,褰撳墠鐩爣6,鍦板浘鐐瑰仛鐩爣7,8鐐瑰嚮蹇嵎閿洿鎺ラ�杩囬紶鏍囦綅缃噴鏀�.瀵硅嚜宸辩殑閰嶅伓閲婃斁10瀵规鍦ㄤ笌鎮ㄧ殑閰嶅伓鍙戠敓鎴樻枟鐨勭洰鏍囦娇鐢�1浼犻� t_skill.f_target
	 * @return  the value of t_skill.f_target
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getTarget() {
		return target;
	}

	/**
	 * 鎶�兘鐩爣锛堣嚜宸�銆佽嚜宸辨墍鍦ㄧ殑灏忛槦2銆佸弸濂界洰鏍�銆佽寖鍥村唴鏁屽鐩爣4,褰撳墠鐩爣6,鍦板浘鐐瑰仛鐩爣7,8鐐瑰嚮蹇嵎閿洿鎺ラ�杩囬紶鏍囦綅缃噴鏀�.瀵硅嚜宸辩殑閰嶅伓閲婃斁10瀵规鍦ㄤ笌鎮ㄧ殑閰嶅伓鍙戠敓鎴樻枟鐨勭洰鏍囦娇鐢�1浼犻� t_skill.f_target
	 * @param target  the value for t_skill.f_target
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setTarget(Byte target) {
		this.target = target;
	}

	/**
	 * 鍏宠仈鐨勬晥鏋渋ds t_skill.f_effect_ids
	 * @return  the value of t_skill.f_effect_ids
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getEffectIds() {
		return effectIds;
	}

	/**
	 * 鍏宠仈鐨勬晥鏋渋ds t_skill.f_effect_ids
	 * @param effectIds  the value for t_skill.f_effect_ids
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setEffectIds(String effectIds) {
		this.effectIds = effectIds;
	}

	/**
	 * 鏄惁涓烘櫘閫氭敾鍑伙紝0涓嶆槸锛�鏄�t_skill.f_isgeneral
	 * @return  the value of t_skill.f_isgeneral
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getIsgeneral() {
		return isgeneral;
	}

	/**
	 * 鏄惁涓烘櫘閫氭敾鍑伙紝0涓嶆槸锛�鏄�t_skill.f_isgeneral
	 * @param isgeneral  the value for t_skill.f_isgeneral
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setIsgeneral(Byte isgeneral) {
		this.isgeneral = isgeneral;
	}

	/**
	 * 浜虹墿瀛︿範绛夌骇闄愬埗 t_skill.f_char_level
	 * @return  the value of t_skill.f_char_level
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getCharLevel() {
		return charLevel;
	}

	/**
	 * 浜虹墿瀛︿範绛夌骇闄愬埗 t_skill.f_char_level
	 * @param charLevel  the value for t_skill.f_char_level
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setCharLevel(Byte charLevel) {
		this.charLevel = charLevel;
	}

	/**
	 * 闂ㄦ淳(0-涓嶆樉绀洪棬娲�1 - 灏戞灄,2 - 姝﹀綋,3 - 鍙ゅ,4 - 宄ㄧ湁) t_skill.f_popsinger
	 * @return  the value of t_skill.f_popsinger
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getPopsinger() {
		return popsinger;
	}

	/**
	 * 闂ㄦ淳(0-涓嶆樉绀洪棬娲�1 - 灏戞灄,2 - 姝﹀綋,3 - 鍙ゅ,4 - 宄ㄧ湁) t_skill.f_popsinger
	 * @param popsinger  the value for t_skill.f_popsinger
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setPopsinger(Byte popsinger) {
		this.popsinger = popsinger;
	}

	/**
	 * 鎵�渶鎶�兘涔︼紙瀵瑰簲goomodel id瀛楁锛�t_skill.f_learning_book
	 * @return  the value of t_skill.f_learning_book
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getLearningBook() {
		return learningBook;
	}

	/**
	 * 鎵�渶鎶�兘涔︼紙瀵瑰簲goomodel id瀛楁锛�t_skill.f_learning_book
	 * @param learningBook  the value for t_skill.f_learning_book
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setLearningBook(Integer learningBook) {
		this.learningBook = learningBook;
	}

	/**
	 * 鎻忚堪 t_skill.f_desc
	 * @return  the value of t_skill.f_desc
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 鎻忚堪 t_skill.f_desc
	 * @param desc  the value for t_skill.f_desc
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 姝﹀姛淇偧闅惧害绯绘暟 t_skill.f_revise
	 * @return  the value of t_skill.f_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getRevise() {
		return revise;
	}

	/**
	 * 姝﹀姛淇偧闅惧害绯绘暟 t_skill.f_revise
	 * @param revise  the value for t_skill.f_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setRevise(Integer revise) {
		this.revise = revise;
	}

	/**
	 * 鎶�兘浼ゅ淇绯绘暟 t_skill.f_hurt_revise
	 * @return  the value of t_skill.f_hurt_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Float getHurtRevise() {
		return hurtRevise;
	}

	/**
	 * 鎶�兘浼ゅ淇绯绘暟 t_skill.f_hurt_revise
	 * @param hurtRevise  the value for t_skill.f_hurt_revise
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setHurtRevise(Float hurtRevise) {
		this.hurtRevise = hurtRevise;
	}

	/**
	 * 鎶�兘浼ゅ闄勫姞 t_skill.f_hurt_append
	 * @return  the value of t_skill.f_hurt_append
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getHurtAppend() {
		return hurtAppend;
	}

	/**
	 * 鎶�兘浼ゅ闄勫姞 t_skill.f_hurt_append
	 * @param hurtAppend  the value for t_skill.f_hurt_append
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setHurtAppend(Integer hurtAppend) {
		this.hurtAppend = hurtAppend;
	}

	/**
	 * 灞傛暟涓婇檺 t_skill.f_grade_limit
	 * @return  the value of t_skill.f_grade_limit
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getGradeLimit() {
		return gradeLimit;
	}

	/**
	 * 灞傛暟涓婇檺 t_skill.f_grade_limit
	 * @param gradeLimit  the value for t_skill.f_grade_limit
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setGradeLimit(Integer gradeLimit) {
		this.gradeLimit = gradeLimit;
	}

	/**
	 * 1椹殑鎶�兘 2瑙掕壊鐨勬妧鑳�3鎬墿鎶�兘 t_skill.f_skilltype
	 * @return  the value of t_skill.f_skilltype
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getSkilltype() {
		return skilltype;
	}

	/**
	 * 1椹殑鎶�兘 2瑙掕壊鐨勬妧鑳�3鎬墿鎶�兘 t_skill.f_skilltype
	 * @param skilltype  the value for t_skill.f_skilltype
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setSkilltype(Integer skilltype) {
		this.skilltype = skilltype;
	}

	/**
	 * 鎶�兘瑙﹀彂鍑犵巼 t_skill.f_trigger_probability
	 * @return  the value of t_skill.f_trigger_probability
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getTriggerProbability() {
		return triggerProbability;
	}

	/**
	 * 鎶�兘瑙﹀彂鍑犵巼 t_skill.f_trigger_probability
	 * @param triggerProbability  the value for t_skill.f_trigger_probability
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setTriggerProbability(Integer triggerProbability) {
		this.triggerProbability = triggerProbability;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉0 t_skill.f_effect_res_id0
	 * @return  the value of t_skill.f_effect_res_id0
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getEffectResId0() {
		return effectResId0;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉0 t_skill.f_effect_res_id0
	 * @param effectResId0  the value for t_skill.f_effect_res_id0
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setEffectResId0(Integer effectResId0) {
		this.effectResId0 = effectResId0;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉1 t_skill.f_effect_res_id1
	 * @return  the value of t_skill.f_effect_res_id1
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getEffectResId1() {
		return effectResId1;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉1 t_skill.f_effect_res_id1
	 * @param effectResId1  the value for t_skill.f_effect_res_id1
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setEffectResId1(Integer effectResId1) {
		this.effectResId1 = effectResId1;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉2 t_skill.f_effect_res_id2
	 * @return  the value of t_skill.f_effect_res_id2
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getEffectResId2() {
		return effectResId2;
	}

	/**
	 * 鎶�兘灞曠ず鏁堟灉2 t_skill.f_effect_res_id2
	 * @param effectResId2  the value for t_skill.f_effect_res_id2
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setEffectResId2(Integer effectResId2) {
		this.effectResId2 = effectResId2;
	}

	/**
	 * 鏄剧ず椤哄簭 t_skill.f_show_order
	 * @return  the value of t_skill.f_show_order
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getShowOrder() {
		return showOrder;
	}

	/**
	 * 鏄剧ず椤哄簭 t_skill.f_show_order
	 * @param showOrder  the value for t_skill.f_show_order
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	/**
	 * 鍏宠仈鎶�兘 t_skill.f_skill_relevance
	 * @return  the value of t_skill.f_skill_relevance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getSkillRelevance() {
		return skillRelevance;
	}

	/**
	 * 鍏宠仈鎶�兘 t_skill.f_skill_relevance
	 * @param skillRelevance  the value for t_skill.f_skill_relevance
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setSkillRelevance(Integer skillRelevance) {
		this.skillRelevance = skillRelevance;
	}

	/**
	 * 鍔ㄤ綔寤舵椂鏃堕棿(姣) t_skill.f_play_delay
	 * @return  the value of t_skill.f_play_delay
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getPlayDelay() {
		return playDelay;
	}

	/**
	 * 鍔ㄤ綔寤舵椂鏃堕棿(姣) t_skill.f_play_delay
	 * @param playDelay  the value for t_skill.f_play_delay
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setPlayDelay(Integer playDelay) {
		this.playDelay = playDelay;
	}

	/**
	 * 鏁堟灉椋炶閫熷害(鍍忕礌/姣) t_skill.f_fly_speed
	 * @return  the value of t_skill.f_fly_speed
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getFlySpeed() {
		return flySpeed;
	}

	/**
	 * 鏁堟灉椋炶閫熷害(鍍忕礌/姣) t_skill.f_fly_speed
	 * @param flySpeed  the value for t_skill.f_fly_speed
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setFlySpeed(Integer flySpeed) {
		this.flySpeed = flySpeed;
	}

	/**
	 * 0闈炲垱寤鸿鑹叉椂瀛︿範1鍒涘缓瑙掕壊鏃跺涔�t_skill.f_create_learn
	 * @return  the value of t_skill.f_create_learn
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getCreateLearn() {
		return createLearn;
	}

	/**
	 * 0闈炲垱寤鸿鑹叉椂瀛︿範1鍒涘缓瑙掕壊鏃跺涔�t_skill.f_create_learn
	 * @param createLearn  the value for t_skill.f_create_learn
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setCreateLearn(Byte createLearn) {
		this.createLearn = createLearn;
	}

	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿绫诲瀷 t_skill.f_common_cool_type
	 * @return  the value of t_skill.f_common_cool_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getCommonCoolType() {
		return commonCoolType;
	}

	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿绫诲瀷 t_skill.f_common_cool_type
	 * @param commonCoolType  the value for t_skill.f_common_cool_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setCommonCoolType(Byte commonCoolType) {
		this.commonCoolType = commonCoolType;
	}

	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿(姣) t_skill.f_common_cool_time
	 * @return  the value of t_skill.f_common_cool_time
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getCommonCoolTime() {
		return commonCoolTime;
	}

	/**
	 * 鍏叡鎶�兘鍐峰嵈鏃堕棿(姣) t_skill.f_common_cool_time
	 * @param commonCoolTime  the value for t_skill.f_common_cool_time
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setCommonCoolTime(Integer commonCoolTime) {
		this.commonCoolTime = commonCoolTime;
	}

	/**
	 * 鎶�兘甯︽潵鐨勪粐鎭ㄥ� t_skill.f_enmity_value
	 * @return  the value of t_skill.f_enmity_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getEnmityValue() {
		return enmityValue;
	}

	/**
	 * 鎶�兘甯︽潵鐨勪粐鎭ㄥ� t_skill.f_enmity_value
	 * @param enmityValue  the value for t_skill.f_enmity_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setEnmityValue(Integer enmityValue) {
		this.enmityValue = enmityValue;
	}

	/**
	 * 澧炵泭鎬ф妧鑳�涓嶅彲璁句负榛樿)1/浼ゅ鎬ф妧鑳�(鍙涓洪粯璁� t_skill.f_type
	 * @return  the value of t_skill.f_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 澧炵泭鎬ф妧鑳�涓嶅彲璁句负榛樿)1/浼ゅ鎬ф妧鑳�(鍙涓洪粯璁� t_skill.f_type
	 * @param type  the value for t_skill.f_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 鎶�兘澶у浘鏍�t_skill.f_big_ico
	 * @return  the value of t_skill.f_big_ico
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getBigIco() {
		return bigIco;
	}

	/**
	 * 鎶�兘澶у浘鏍�t_skill.f_big_ico
	 * @param bigIco  the value for t_skill.f_big_ico
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setBigIco(Integer bigIco) {
		this.bigIco = bigIco;
	}

	/**
	 * 闊虫晥 t_skill.f_sound
	 * @return  the value of t_skill.f_sound
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getSound() {
		return sound;
	}

	/**
	 * 闊虫晥 t_skill.f_sound
	 * @param sound  the value for t_skill.f_sound
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setSound(Integer sound) {
		this.sound = sound;
	}

	/**
	 * 0鏀诲嚮鐩爣鏃惰Е鍙�琚敾鍑绘椂瑙﹀彂 t_skill.f_hurted_trib
	 * @return  the value of t_skill.f_hurted_trib
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Boolean getHurtedTrib() {
		return hurtedTrib;
	}

	/**
	 * 0鏀诲嚮鐩爣鏃惰Е鍙�琚敾鍑绘椂瑙﹀彂 t_skill.f_hurted_trib
	 * @param hurtedTrib  the value for t_skill.f_hurted_trib
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setHurtedTrib(Boolean hurtedTrib) {
		this.hurtedTrib = hurtedTrib;
	}

	/**
	 * 1闂ㄦ淳缁濆2姹熸箹缁濆3鍏朵粬鎿嶄綔4.甯淳鎶�兘5.澶鎶�兘6.鐜変僵鎶�兘 t_skill.f_wugong_type
	 * @return  the value of t_skill.f_wugong_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getWugongType() {
		return wugongType;
	}

	/**
	 * 1闂ㄦ淳缁濆2姹熸箹缁濆3鍏朵粬鎿嶄綔4.甯淳鎶�兘5.澶鎶�兘6.鐜変僵鎶�兘 t_skill.f_wugong_type
	 * @param wugongType  the value for t_skill.f_wugong_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setWugongType(Byte wugongType) {
		this.wugongType = wugongType;
	}

	/**
	 * boss鑾峰緱|npc璐拱|浜虹墿鍗囩骇鑷姩瀛︿範 t_skill.f_source
	 * @return  the value of t_skill.f_source
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getSource() {
		return source;
	}

	/**
	 * boss鑾峰緱|npc璐拱|浜虹墿鍗囩骇鑷姩瀛︿範 t_skill.f_source
	 * @param source  the value for t_skill.f_source
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 瀵瑰簲鐨勬姉鎬ф妧鑳絠d t_skill.f_kanxing_skill
	 * @return  the value of t_skill.f_kanxing_skill
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getKanxingSkill() {
		return kanxingSkill;
	}

	/**
	 * 瀵瑰簲鐨勬姉鎬ф妧鑳絠d t_skill.f_kanxing_skill
	 * @param kanxingSkill  the value for t_skill.f_kanxing_skill
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setKanxingSkill(Integer kanxingSkill) {
		this.kanxingSkill = kanxingSkill;
	}

	/**
	 * 琚姩鎶�兘甯︽潵鐨勫熀纭��(鐩墠浣跨敤鍦ㄥ潗楠戞妧鑳戒笂) t_skill.f_beidong_base_value
	 * @return  the value of t_skill.f_beidong_base_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getBeidongBaseValue() {
		return beidongBaseValue;
	}

	/**
	 * 琚姩鎶�兘甯︽潵鐨勫熀纭��(鐩墠浣跨敤鍦ㄥ潗楠戞妧鑳戒笂) t_skill.f_beidong_base_value
	 * @param beidongBaseValue  the value for t_skill.f_beidong_base_value
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setBeidongBaseValue(Integer beidongBaseValue) {
		this.beidongBaseValue = beidongBaseValue;
	}

	/**
	 * (0涓嶆槸1鏄�鍧愰獞缁勫悎鎶�兘 t_skill.f_is_zuhe
	 * @return  the value of t_skill.f_is_zuhe
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Byte getIsZuhe() {
		return isZuhe;
	}

	/**
	 * (0涓嶆槸1鏄�鍧愰獞缁勫悎鎶�兘 t_skill.f_is_zuhe
	 * @param isZuhe  the value for t_skill.f_is_zuhe
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setIsZuhe(Byte isZuhe) {
		this.isZuhe = isZuhe;
	}

	/**
	 * 甯淳鎶�兘娑堣�甯础(瀛︿範鏃跺�鐢� t_skill.f_bang_gong
	 * @return  the value of t_skill.f_bang_gong
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getBangGong() {
		return bangGong;
	}

	/**
	 * 甯淳鎶�兘娑堣�甯础(瀛︿範鏃跺�鐢� t_skill.f_bang_gong
	 * @param bangGong  the value for t_skill.f_bang_gong
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setBangGong(Integer bangGong) {
		this.bangGong = bangGong;
	}

	/**
	 * 鎺ㄨ崘瀛︿範鐨勭被鍨�0:涓嶆帹鑽� 1锛氭帹鑽愰獞涔樺潗楠戝涔�2锛氭帹鑽愬嚭鎴樺潗楠戝涔�t_skill.f_learn_type
	 * @return  the value of t_skill.f_learn_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public Integer getLearnType() {
		return learnType;
	}

	/**
	 * 鎺ㄨ崘瀛︿範鐨勭被鍨�0:涓嶆帹鑽� 1锛氭帹鑽愰獞涔樺潗楠戝涔�2锛氭帹鑽愬嚭鎴樺潗楠戝涔�t_skill.f_learn_type
	 * @param learnType  the value for t_skill.f_learn_type
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setLearnType(Integer learnType) {
		this.learnType = learnType;
	}

	/**
	 * 鎶�兘鍚嶇О鍥介檯鍖�t_skill.f_name_i18n
	 * @return  the value of t_skill.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 鎶�兘鍚嶇О鍥介檯鍖�t_skill.f_name_i18n
	 * @param nameI18n  the value for t_skill.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 鎶�兘鎻忚堪鍥介檯鍖�t_skill.f_desc_i18n
	 * @return  the value of t_skill.f_desc_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 鎶�兘鎻忚堪鍥介檯鍖�t_skill.f_desc_i18n
	 * @param descI18n  the value for t_skill.f_desc_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 鎶�兘鑾峰緱鏂瑰紡鍥介檯鍖�t_skill.f_source_i18n
	 * @return  the value of t_skill.f_source_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public String getSourceI18n() {
		return sourceI18n;
	}

	/**
	 * 鎶�兘鑾峰緱鏂瑰紡鍥介檯鍖�t_skill.f_source_i18n
	 * @param sourceI18n  the value for t_skill.f_source_i18n
	 * @ibatorgenerated  Fri May 06 17:22:31 CST 2011
	 */
	public void setSourceI18n(String sourceI18n) {
		this.sourceI18n = sourceI18n;
	}

	public String getUpgradedes() {
		return upgradedes;
	}

	public void setUpgradedes(String upgradedes) {
		this.upgradedes = upgradedes;
	}

	public String getUpgradedesci18n() {
		System.err.println(upgradedesci18n);
		return upgradedesci18n;
	}

	public void setUpgradedesci18n(String upgradedesci18n) {
		this.upgradedesci18n = upgradedesci18n;
	}
}
