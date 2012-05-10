package net.snake.dao.monstermodel;

public class MonsterModel {

	/**
	 * id t_monster_model.f_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer id;
	/**
	 * 鎬墿鍚嶇О t_monster_model.f_name
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String name;
	/**
	 * 鎬墿绫诲瀷,鏅�1锛岀簿鑻�锛宐oss3 锛�5甯棗鎬墿  ,6娓搁緳涔嬪垉7濠氬椁愭8瀵诲疂榧�鍓湰灏忔�锛�0鍓湰BOSS t_monster_model.f_type
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short type;
	/**
	 * 澶村儚鍥炬爣ID t_monster_model.f_head_icon_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer headIconId;
	/**
	 * 鎬墿鎹㈣璧勬簮ID t_monster_model.f_avatar_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer avatarId;
	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_monster_model.f_attack_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer attackAudio;
	/**
	 * 琚敾鍑荤殑澹伴煶 t_monster_model.f_hurt_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer hurtAudio;
	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_monster_model.f_die_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer dieAudio;
	/**
	 * 绛夌骇 t_monster_model.f_grade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short grade;
	/**
	 * 鐢熷懡鍊�t_monster_model.f_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer hp;
	/**
	 * 鏀诲嚮 t_monster_model.f_attack
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer attack;
	/**
	 * 闃插尽 t_monster_model.f_defence
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer defence;
	/**
	 * 鍛戒腑 t_monster_model.f_hit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer hit;
	/**
	 * 鏆村嚮 1/10000 t_monster_model.f_crt
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer crt;
	/**
	 * 闂伩鍊�1/10000 t_monster_model.f_dodge
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer dodge;
	/**
	 * 鏀诲嚮閫熷害 鐢辨敾鍑诲喎鍗存椂闂磋〃绀�t_monster_model.f_atk_coldtime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short atkColdtime;
	/**
	 * 绉诲姩閫熷害 鍍忕礌鍊�绉�t_monster_model.f_movespeed
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer movespeed;
	/**
	 * 鍙樿韩鍙樻�鍊嶆暟 t_monster_model.f_abnormal
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short abnormal;
	/**
	 * 鍙樿韩鏀诲嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_attackcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer attackcolor;
	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_defencecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer defencecolor;
	/**
	 * 鍙樿韩鏆村嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_exposecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer exposecolor;
	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_dodgecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer dodgecolor;
	/**
	 * 鍙樿韩琛�噺鑹叉�鍑犵巼 1/10000 t_monster_model.f_hpcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer hpcolor;
	/**
	 * 鍙樿韩閲戦挶鑹叉�鍑犵巼 1/10000 t_monster_model.f_moneycolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer moneycolor;
	/**
	 * 鏀诲嚮璁惧畾锛屼富鍔ㄦ敾鍑�/琚姩鏀诲嚮2 t_monster_model.f_is
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Byte is;
	/**
	 * 璀︽垝鑼冨洿(浠ユ�鐗�褰撳墠鐐�涓轰腑蹇冪偣) 涓诲姩鏀诲嚮鎬拰瑙掕壊闂磋窛绂昏繘鍏ヨ鎴掕寖鍥村皢寮曞彂鎬墿鐨勪富鍔ㄦ敾鍑�t_monster_model.f_alert
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short alert;
	/**
	 * 宸￠�鑼冨洿(浠ユ�鐗�鍑虹敓鐐�涓轰腑蹇冪偣) ,鎬墿鍒濆鍖栨椂鍦ㄨ繖涓寖鍥村唴绉诲姩', t_monster_model.f_patrol
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer patrol;
	/**
	 * 杩藉嚮鑼冨洿(鎬墿鍑�鍑虹敓鐐�涓轰腑蹇冪偣) 瓒呰繃浜嗚繖涓寖鍥�鎬墿涓嶅啀杩藉嚮鐜╁,濡傛灉鎬墿澶勪簬鎴樻枟鐘舵�,鍒欏皢寮曞彂鍚戝嚭鐢熺偣璺戝幓,骞堕噸缃�, t_monster_model.f_pursuit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer pursuit;
	/**
	 * 鎬墿鐨勫钩鐮嶆妧鑳絠d t_monster_model.f_skill_pingkan
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer skillPingkan;
	/**
	 * 鎬墿骞崇爫鎶�兘绛夌骇 t_monster_model.f_skill_pingkangrade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer skillPingkangrade;
	/**
	 * 鎶�兘鐩稿叧锛堟妧鑳絠d,绛夌骇;锛�t_monster_model.f_skill_relevance
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String skillRelevance;
	/**
	 * 鏄惁鑷姩鍥炶,0涓嶅洖琛�1鍥炶 t_monster_model.f_hp_autoadd
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer hpAutoadd;
	/**
	 * 鎵�墿鐢熷懡鍊煎湪澶氬皯鐧惧垎姣旀椂寮�琛ヨ 1/10000 t_monster_model.f_addhp_percentage
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short addhpPercentage;
	/**
	 * 姣忔琛ヨ琛�噺  t_monster_model.f_addhp_num
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short addhpNum;
	/**
	 * 琛ヨ鏃堕棿闂撮殧 ms t_monster_model.f_addhp_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short addhpTime;
	/**
	 * 鎺夎惤缁忛獙 t_monster_model.f_exper
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer exper;
	/**
	 * 鎺夎惤閲戦挶 t_monster_model.f_lm
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer lm;
	/**
	 * 鎼哄甫鎴樺満澹版湜 t_monster_model.f_prestige
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer prestige;
	/**
	 * 璇磋瘽鏃堕棿闂撮殧浠ョ涓哄崟浣�t_monster_model.f_speak_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short speakTime;
	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String speaks;
	/**
	 * 鎬墿灏镐綋娑堝け鏃堕棿浠ョ涓哄崟浣�t_monster_model.f_revivification_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Short revivificationTime;
	/**
	 * 灏镐綋娑堝け鍚庡啀鍋渇_wait_Time寮�澶嶆椿 t_monster_model.f_waitTime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer waittime;
	/**
	 * 鐢ㄤ簬瀹氬埗鍒锋�鏃堕棿 鏃堕棿琛ㄨ揪寮忔牸寮�涓� [骞碷[鏈圿[鏃[鏃堕棿娈礭;[骞碷[鏈圿[鏃[鏃堕棿娈礭;.... t_monster_model.f_waitTime_expression
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String waittimeExpression;
	/**
	 * 鐗╂�鐨勮亴璐�0闅忔満宸￠� 1 绔欑珛涓嶅姩,2 鍚戠洰鏍囩偣绉诲姩 t_monster_model.f_responsibility
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer responsibility;
	/**
	 * 琛�噺灏忎簬澶氬皯鏃惰瀵绘眰甯姪0-10000 1/10000 t_monster_model.f_help_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer helpHp;
	/**
	 * 鍠婅瘽鐨勬鐜�0-10000) 1/10000 t_monster_model.f_help_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer helpProbability;
	/**
	 * 鍠婅瘽鏃剁殑骞垮害[0-32] t_monster_model.f_help_extent
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer helpExtent;
	/**
	 * 鍙帴鏀跺枈璇濈殑鎬墿妯″瀷ID,浠ラ�鍙峰垎闅�t_monster_model.f_helpmodelids
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String helpmodelids;
	/**
	 * 杩藉嚮鐨勬鐜�0-10000) t_monster_model.f_pursuit_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer pursuitProbability;
	/**
	 * 鎬墿鎵�睘鐨勯樀钀�0 鍜岀帺瀹舵晫瀵�1 鍜岀帺瀹跺悓涓�垬绾�t_monster_model.f_camp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Byte camp;
	/**
	 * 鎬墿鎻忚堪 t_monster_model.f_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String desc;
	/**
	 * 濡傛灉涓嶄负0 鍒欏�琛ㄧず涓烘崟鑾峰悗锛屽潗楠戞ā鍨婭D鏄粈涔�t_monster_model.f_horse_model_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer horseModelId;
	/**
	 * 鑴氭湰绫诲悕 java鍏ㄨ矾寰�t_monster_model.f_script_class
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String scriptClass;
	/**
	 * 鑴氭湰绫诲垵濮嬪寲鍙傛暟 t_monster_model.f_script_classparam
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String scriptClassparam;
	/**
	 * 0涓嶈创韬紝1璐磋韩鏀诲嚮 t_monster_model.f_skin
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Byte skin;
	/**
	 * 瑁呭杞娆℃暟 t_monster_model.f_loop_count
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer loopCount;
	/**
	 * 鏄惁浣滀负NPC濂藉弸妯″瀷 t_monster_model.f_isnpcfriend
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer isnpcfriend;
	/**
	 * 鐖嗙巼鏄熺骇 t_monster_model.f_probabilitylevel
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer probabilitylevel;
	/**
	 * t_monster_model.f_instance_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer instanceId;
	/**
	 * 姝﹀姛鎻忚堪 t_monster_model.f_skill_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String skillDesc;
	/**
	 * 琚敾鍑绘寜鍥哄畾鍊煎皯琛�紝锛堟棤瑙嗙帺瀹舵敾鍑诲姏锛�鍥哄畾鍊�锛�涓嶇敓鏁堬級 t_monster_model.f_beattack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer beattackHp;
	/**
	 * 鏀诲嚮鐜╁鎸夋瘮渚嬪皯琛�紝锛堟棤瑙嗙帺瀹堕槻寰″姏锛夛紙鍗曚綅1/10000锛夛紙0涓嶇敓鏁堬級(鎸夋渶澶х殑琛�噺姣斾緥灏戣) t_monster_model.f_attack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer attackHp;
	/**
	 * 缁忛獙瀵逛簬浠讳綍绛夌骇鐨勭帺瀹跺潎涓嶈“鍑忋�(1鐢熸晥0涓嶇敓鏁� t_monster_model.f_exp_unlimit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer expUnlimit;
	/**
	 * {0鍙嶅嚮锛堣蛋姝ｅ父鐨刟i锛�1涓嶅弽鍑�鍖呮嫭锛氱┖闂瞐i,宸￠�ai,姝讳骸ai,灏镐綋娑堝けai) t_monster_model.f_beat_back
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private Integer beatBack;
	/**
	 * 鎬墿鍚嶇О鍥介檯鍖�t_monster_model.f_name_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String nameI18n;
	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String speaksI18n;
	/**
	 * 鎬墿鎻忚堪鍥介檯鍖�t_monster_model.f_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String descI18n;
	/**
	 * 姝﹀姛鎻忚堪鍥介檯鍖�t_monster_model.f_skill_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	private String skillDescI18n;
	
	private Integer subtype;

	/**
	 * id t_monster_model.f_id
	 * @return  the value of t_monster_model.f_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * id t_monster_model.f_id
	 * @param id  the value for t_monster_model.f_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 鎬墿鍚嶇О t_monster_model.f_name
	 * @return  the value of t_monster_model.f_name
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * 鎬墿鍚嶇О t_monster_model.f_name
	 * @param name  the value for t_monster_model.f_name
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 鎬墿绫诲瀷,鏅�1锛岀簿鑻�锛宐oss3 锛�5甯棗鎬墿  ,6娓搁緳涔嬪垉7濠氬椁愭8瀵诲疂榧�鍓湰灏忔�锛�0鍓湰BOSS t_monster_model.f_type
	 * @return  the value of t_monster_model.f_type
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getType() {
		return type;
	}

	/**
	 * 鎬墿绫诲瀷,鏅�1锛岀簿鑻�锛宐oss3 锛�5甯棗鎬墿  ,6娓搁緳涔嬪垉7濠氬椁愭8瀵诲疂榧�鍓湰灏忔�锛�0鍓湰BOSS t_monster_model.f_type
	 * @param type  the value for t_monster_model.f_type
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setType(Short type) {
		this.type = type;
	}

	/**
	 * 澶村儚鍥炬爣ID t_monster_model.f_head_icon_id
	 * @return  the value of t_monster_model.f_head_icon_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHeadIconId() {
		return headIconId;
	}

	/**
	 * 澶村儚鍥炬爣ID t_monster_model.f_head_icon_id
	 * @param headIconId  the value for t_monster_model.f_head_icon_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHeadIconId(Integer headIconId) {
		this.headIconId = headIconId;
	}

	/**
	 * 鎬墿鎹㈣璧勬簮ID t_monster_model.f_avatar_id
	 * @return  the value of t_monster_model.f_avatar_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getAvatarId() {
		return avatarId;
	}

	/**
	 * 鎬墿鎹㈣璧勬簮ID t_monster_model.f_avatar_id
	 * @param avatarId  the value for t_monster_model.f_avatar_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_monster_model.f_attack_audio
	 * @return  the value of t_monster_model.f_attack_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getAttackAudio() {
		return attackAudio;
	}

	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_monster_model.f_attack_audio
	 * @param attackAudio  the value for t_monster_model.f_attack_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAttackAudio(Integer attackAudio) {
		this.attackAudio = attackAudio;
	}

	/**
	 * 琚敾鍑荤殑澹伴煶 t_monster_model.f_hurt_audio
	 * @return  the value of t_monster_model.f_hurt_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHurtAudio() {
		return hurtAudio;
	}

	/**
	 * 琚敾鍑荤殑澹伴煶 t_monster_model.f_hurt_audio
	 * @param hurtAudio  the value for t_monster_model.f_hurt_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHurtAudio(Integer hurtAudio) {
		this.hurtAudio = hurtAudio;
	}

	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_monster_model.f_die_audio
	 * @return  the value of t_monster_model.f_die_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getDieAudio() {
		return dieAudio;
	}

	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_monster_model.f_die_audio
	 * @param dieAudio  the value for t_monster_model.f_die_audio
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDieAudio(Integer dieAudio) {
		this.dieAudio = dieAudio;
	}

	/**
	 * 绛夌骇 t_monster_model.f_grade
	 * @return  the value of t_monster_model.f_grade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getGrade() {
		return grade;
	}

	/**
	 * 绛夌骇 t_monster_model.f_grade
	 * @param grade  the value for t_monster_model.f_grade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setGrade(Short grade) {
		this.grade = grade;
	}

	/**
	 * 鐢熷懡鍊�t_monster_model.f_hp
	 * @return  the value of t_monster_model.f_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * 鐢熷懡鍊�t_monster_model.f_hp
	 * @param hp  the value for t_monster_model.f_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 鏀诲嚮 t_monster_model.f_attack
	 * @return  the value of t_monster_model.f_attack
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 鏀诲嚮 t_monster_model.f_attack
	 * @param attack  the value for t_monster_model.f_attack
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 闃插尽 t_monster_model.f_defence
	 * @return  the value of t_monster_model.f_defence
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getDefence() {
		return defence;
	}

	/**
	 * 闃插尽 t_monster_model.f_defence
	 * @param defence  the value for t_monster_model.f_defence
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	/**
	 * 鍛戒腑 t_monster_model.f_hit
	 * @return  the value of t_monster_model.f_hit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHit() {
		return hit;
	}

	/**
	 * 鍛戒腑 t_monster_model.f_hit
	 * @param hit  the value for t_monster_model.f_hit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHit(Integer hit) {
		this.hit = hit;
	}

	/**
	 * 鏆村嚮 1/10000 t_monster_model.f_crt
	 * @return  the value of t_monster_model.f_crt
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 鏆村嚮 1/10000 t_monster_model.f_crt
	 * @param crt  the value for t_monster_model.f_crt
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 闂伩鍊�1/10000 t_monster_model.f_dodge
	 * @return  the value of t_monster_model.f_dodge
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 闂伩鍊�1/10000 t_monster_model.f_dodge
	 * @param dodge  the value for t_monster_model.f_dodge
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	/**
	 * 鏀诲嚮閫熷害 鐢辨敾鍑诲喎鍗存椂闂磋〃绀�t_monster_model.f_atk_coldtime
	 * @return  the value of t_monster_model.f_atk_coldtime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAtkColdtime() {
		return atkColdtime;
	}

	/**
	 * 鏀诲嚮閫熷害 鐢辨敾鍑诲喎鍗存椂闂磋〃绀�t_monster_model.f_atk_coldtime
	 * @param atkColdtime  the value for t_monster_model.f_atk_coldtime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAtkColdtime(Short atkColdtime) {
		this.atkColdtime = atkColdtime;
	}

	/**
	 * 绉诲姩閫熷害 鍍忕礌鍊�绉�t_monster_model.f_movespeed
	 * @return  the value of t_monster_model.f_movespeed
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getMovespeed() {
		return movespeed;
	}

	/**
	 * 绉诲姩閫熷害 鍍忕礌鍊�绉�t_monster_model.f_movespeed
	 * @param movespeed  the value for t_monster_model.f_movespeed
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setMovespeed(Integer movespeed) {
		this.movespeed = movespeed;
	}

	/**
	 * 鍙樿韩鍙樻�鍊嶆暟 t_monster_model.f_abnormal
	 * @return  the value of t_monster_model.f_abnormal
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAbnormal() {
		return abnormal;
	}

	/**
	 * 鍙樿韩鍙樻�鍊嶆暟 t_monster_model.f_abnormal
	 * @param abnormal  the value for t_monster_model.f_abnormal
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAbnormal(Short abnormal) {
		this.abnormal = abnormal;
	}

	/**
	 * 鍙樿韩鏀诲嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_attackcolor
	 * @return  the value of t_monster_model.f_attackcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getAttackcolor() {
		return attackcolor;
	}

	/**
	 * 鍙樿韩鏀诲嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_attackcolor
	 * @param attackcolor  the value for t_monster_model.f_attackcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAttackcolor(Integer attackcolor) {
		this.attackcolor = attackcolor;
	}

	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_defencecolor
	 * @return  the value of t_monster_model.f_defencecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getDefencecolor() {
		return defencecolor;
	}

	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_defencecolor
	 * @param defencecolor  the value for t_monster_model.f_defencecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDefencecolor(Integer defencecolor) {
		this.defencecolor = defencecolor;
	}

	/**
	 * 鍙樿韩鏆村嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_exposecolor
	 * @return  the value of t_monster_model.f_exposecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getExposecolor() {
		return exposecolor;
	}

	/**
	 * 鍙樿韩鏆村嚮鑹叉�鍑犵巼 1/10000 t_monster_model.f_exposecolor
	 * @param exposecolor  the value for t_monster_model.f_exposecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setExposecolor(Integer exposecolor) {
		this.exposecolor = exposecolor;
	}

	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_dodgecolor
	 * @return  the value of t_monster_model.f_dodgecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getDodgecolor() {
		return dodgecolor;
	}

	/**
	 * 鍙樿韩闃插尽鑹叉�鍑犵巼 1/10000 t_monster_model.f_dodgecolor
	 * @param dodgecolor  the value for t_monster_model.f_dodgecolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDodgecolor(Integer dodgecolor) {
		this.dodgecolor = dodgecolor;
	}

	/**
	 * 鍙樿韩琛�噺鑹叉�鍑犵巼 1/10000 t_monster_model.f_hpcolor
	 * @return  the value of t_monster_model.f_hpcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHpcolor() {
		return hpcolor;
	}

	/**
	 * 鍙樿韩琛�噺鑹叉�鍑犵巼 1/10000 t_monster_model.f_hpcolor
	 * @param hpcolor  the value for t_monster_model.f_hpcolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHpcolor(Integer hpcolor) {
		this.hpcolor = hpcolor;
	}

	/**
	 * 鍙樿韩閲戦挶鑹叉�鍑犵巼 1/10000 t_monster_model.f_moneycolor
	 * @return  the value of t_monster_model.f_moneycolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getMoneycolor() {
		return moneycolor;
	}

	/**
	 * 鍙樿韩閲戦挶鑹叉�鍑犵巼 1/10000 t_monster_model.f_moneycolor
	 * @param moneycolor  the value for t_monster_model.f_moneycolor
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setMoneycolor(Integer moneycolor) {
		this.moneycolor = moneycolor;
	}

	/**
	 * 鏀诲嚮璁惧畾锛屼富鍔ㄦ敾鍑�/琚姩鏀诲嚮2 t_monster_model.f_is
	 * @return  the value of t_monster_model.f_is
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Byte getIs() {
		return is;
	}

	/**
	 * 鏀诲嚮璁惧畾锛屼富鍔ㄦ敾鍑�/琚姩鏀诲嚮2 t_monster_model.f_is
	 * @param is  the value for t_monster_model.f_is
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setIs(Byte is) {
		this.is = is;
	}

	/**
	 * 璀︽垝鑼冨洿(浠ユ�鐗�褰撳墠鐐�涓轰腑蹇冪偣) 涓诲姩鏀诲嚮鎬拰瑙掕壊闂磋窛绂昏繘鍏ヨ鎴掕寖鍥村皢寮曞彂鎬墿鐨勪富鍔ㄦ敾鍑�t_monster_model.f_alert
	 * @return  the value of t_monster_model.f_alert
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAlert() {
		return alert;
	}

	/**
	 * 璀︽垝鑼冨洿(浠ユ�鐗�褰撳墠鐐�涓轰腑蹇冪偣) 涓诲姩鏀诲嚮鎬拰瑙掕壊闂磋窛绂昏繘鍏ヨ鎴掕寖鍥村皢寮曞彂鎬墿鐨勪富鍔ㄦ敾鍑�t_monster_model.f_alert
	 * @param alert  the value for t_monster_model.f_alert
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAlert(Short alert) {
		this.alert = alert;
	}

	/**
	 * 宸￠�鑼冨洿(浠ユ�鐗�鍑虹敓鐐�涓轰腑蹇冪偣) ,鎬墿鍒濆鍖栨椂鍦ㄨ繖涓寖鍥村唴绉诲姩', t_monster_model.f_patrol
	 * @return  the value of t_monster_model.f_patrol
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getPatrol() {
		return patrol;
	}

	/**
	 * 宸￠�鑼冨洿(浠ユ�鐗�鍑虹敓鐐�涓轰腑蹇冪偣) ,鎬墿鍒濆鍖栨椂鍦ㄨ繖涓寖鍥村唴绉诲姩', t_monster_model.f_patrol
	 * @param patrol  the value for t_monster_model.f_patrol
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setPatrol(Integer patrol) {
		this.patrol = patrol;
	}

	/**
	 * 杩藉嚮鑼冨洿(鎬墿鍑�鍑虹敓鐐�涓轰腑蹇冪偣) 瓒呰繃浜嗚繖涓寖鍥�鎬墿涓嶅啀杩藉嚮鐜╁,濡傛灉鎬墿澶勪簬鎴樻枟鐘舵�,鍒欏皢寮曞彂鍚戝嚭鐢熺偣璺戝幓,骞堕噸缃�, t_monster_model.f_pursuit
	 * @return  the value of t_monster_model.f_pursuit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getPursuit() {
		return pursuit;
	}

	/**
	 * 杩藉嚮鑼冨洿(鎬墿鍑�鍑虹敓鐐�涓轰腑蹇冪偣) 瓒呰繃浜嗚繖涓寖鍥�鎬墿涓嶅啀杩藉嚮鐜╁,濡傛灉鎬墿澶勪簬鎴樻枟鐘舵�,鍒欏皢寮曞彂鍚戝嚭鐢熺偣璺戝幓,骞堕噸缃�, t_monster_model.f_pursuit
	 * @param pursuit  the value for t_monster_model.f_pursuit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setPursuit(Integer pursuit) {
		this.pursuit = pursuit;
	}

	/**
	 * 鎬墿鐨勫钩鐮嶆妧鑳絠d t_monster_model.f_skill_pingkan
	 * @return  the value of t_monster_model.f_skill_pingkan
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getSkillPingkan() {
		return skillPingkan;
	}

	/**
	 * 鎬墿鐨勫钩鐮嶆妧鑳絠d t_monster_model.f_skill_pingkan
	 * @param skillPingkan  the value for t_monster_model.f_skill_pingkan
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkillPingkan(Integer skillPingkan) {
		this.skillPingkan = skillPingkan;
	}

	/**
	 * 鎬墿骞崇爫鎶�兘绛夌骇 t_monster_model.f_skill_pingkangrade
	 * @return  the value of t_monster_model.f_skill_pingkangrade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getSkillPingkangrade() {
		return skillPingkangrade;
	}

	/**
	 * 鎬墿骞崇爫鎶�兘绛夌骇 t_monster_model.f_skill_pingkangrade
	 * @param skillPingkangrade  the value for t_monster_model.f_skill_pingkangrade
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkillPingkangrade(Integer skillPingkangrade) {
		this.skillPingkangrade = skillPingkangrade;
	}

	/**
	 * 鎶�兘鐩稿叧锛堟妧鑳絠d,绛夌骇;锛�t_monster_model.f_skill_relevance
	 * @return  the value of t_monster_model.f_skill_relevance
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getSkillRelevance() {
		return skillRelevance;
	}

	/**
	 * 鎶�兘鐩稿叧锛堟妧鑳絠d,绛夌骇;锛�t_monster_model.f_skill_relevance
	 * @param skillRelevance  the value for t_monster_model.f_skill_relevance
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkillRelevance(String skillRelevance) {
		this.skillRelevance = skillRelevance;
	}

	/**
	 * 鏄惁鑷姩鍥炶,0涓嶅洖琛�1鍥炶 t_monster_model.f_hp_autoadd
	 * @return  the value of t_monster_model.f_hp_autoadd
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHpAutoadd() {
		return hpAutoadd;
	}

	/**
	 * 鏄惁鑷姩鍥炶,0涓嶅洖琛�1鍥炶 t_monster_model.f_hp_autoadd
	 * @param hpAutoadd  the value for t_monster_model.f_hp_autoadd
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHpAutoadd(Integer hpAutoadd) {
		this.hpAutoadd = hpAutoadd;
	}

	/**
	 * 鎵�墿鐢熷懡鍊煎湪澶氬皯鐧惧垎姣旀椂寮�琛ヨ 1/10000 t_monster_model.f_addhp_percentage
	 * @return  the value of t_monster_model.f_addhp_percentage
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAddhpPercentage() {
		return addhpPercentage;
	}

	/**
	 * 鎵�墿鐢熷懡鍊煎湪澶氬皯鐧惧垎姣旀椂寮�琛ヨ 1/10000 t_monster_model.f_addhp_percentage
	 * @param addhpPercentage  the value for t_monster_model.f_addhp_percentage
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAddhpPercentage(Short addhpPercentage) {
		this.addhpPercentage = addhpPercentage;
	}

	/**
	 * 姣忔琛ヨ琛�噺  t_monster_model.f_addhp_num
	 * @return  the value of t_monster_model.f_addhp_num
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAddhpNum() {
		return addhpNum;
	}

	/**
	 * 姣忔琛ヨ琛�噺  t_monster_model.f_addhp_num
	 * @param addhpNum  the value for t_monster_model.f_addhp_num
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAddhpNum(Short addhpNum) {
		this.addhpNum = addhpNum;
	}

	/**
	 * 琛ヨ鏃堕棿闂撮殧 ms t_monster_model.f_addhp_time
	 * @return  the value of t_monster_model.f_addhp_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getAddhpTime() {
		return addhpTime;
	}

	/**
	 * 琛ヨ鏃堕棿闂撮殧 ms t_monster_model.f_addhp_time
	 * @param addhpTime  the value for t_monster_model.f_addhp_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAddhpTime(Short addhpTime) {
		this.addhpTime = addhpTime;
	}

	/**
	 * 鎺夎惤缁忛獙 t_monster_model.f_exper
	 * @return  the value of t_monster_model.f_exper
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getExper() {
		return exper;
	}

	/**
	 * 鎺夎惤缁忛獙 t_monster_model.f_exper
	 * @param exper  the value for t_monster_model.f_exper
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setExper(Integer exper) {
		this.exper = exper;
	}

	/**
	 * 鎺夎惤閲戦挶 t_monster_model.f_lm
	 * @return  the value of t_monster_model.f_lm
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getLm() {
		return lm;
	}

	/**
	 * 鎺夎惤閲戦挶 t_monster_model.f_lm
	 * @param lm  the value for t_monster_model.f_lm
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setLm(Integer lm) {
		this.lm = lm;
	}

	/**
	 * 鎼哄甫鎴樺満澹版湜 t_monster_model.f_prestige
	 * @return  the value of t_monster_model.f_prestige
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getPrestige() {
		return prestige;
	}

	/**
	 * 鎼哄甫鎴樺満澹版湜 t_monster_model.f_prestige
	 * @param prestige  the value for t_monster_model.f_prestige
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setPrestige(Integer prestige) {
		this.prestige = prestige;
	}

	/**
	 * 璇磋瘽鏃堕棿闂撮殧浠ョ涓哄崟浣�t_monster_model.f_speak_time
	 * @return  the value of t_monster_model.f_speak_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getSpeakTime() {
		return speakTime;
	}

	/**
	 * 璇磋瘽鏃堕棿闂撮殧浠ョ涓哄崟浣�t_monster_model.f_speak_time
	 * @param speakTime  the value for t_monster_model.f_speak_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSpeakTime(Short speakTime) {
		this.speakTime = speakTime;
	}

	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks
	 * @return  the value of t_monster_model.f_speaks
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getSpeaks() {
		return speaks;
	}

	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks
	 * @param speaks  the value for t_monster_model.f_speaks
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSpeaks(String speaks) {
		this.speaks = speaks;
	}

	/**
	 * 鎬墿灏镐綋娑堝け鏃堕棿浠ョ涓哄崟浣�t_monster_model.f_revivification_time
	 * @return  the value of t_monster_model.f_revivification_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Short getRevivificationTime() {
		return revivificationTime;
	}

	/**
	 * 鎬墿灏镐綋娑堝け鏃堕棿浠ョ涓哄崟浣�t_monster_model.f_revivification_time
	 * @param revivificationTime  the value for t_monster_model.f_revivification_time
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setRevivificationTime(Short revivificationTime) {
		this.revivificationTime = revivificationTime;
	}

	/**
	 * 灏镐綋娑堝け鍚庡啀鍋渇_wait_Time寮�澶嶆椿 t_monster_model.f_waitTime
	 * @return  the value of t_monster_model.f_waitTime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getWaittime() {
		return waittime;
	}

	/**
	 * 灏镐綋娑堝け鍚庡啀鍋渇_wait_Time寮�澶嶆椿 t_monster_model.f_waitTime
	 * @param waittime  the value for t_monster_model.f_waitTime
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setWaittime(Integer waittime) {
		this.waittime = waittime;
	}

	/**
	 * 鐢ㄤ簬瀹氬埗鍒锋�鏃堕棿 鏃堕棿琛ㄨ揪寮忔牸寮�涓� [骞碷[鏈圿[鏃[鏃堕棿娈礭;[骞碷[鏈圿[鏃[鏃堕棿娈礭;.... t_monster_model.f_waitTime_expression
	 * @return  the value of t_monster_model.f_waitTime_expression
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getWaittimeExpression() {
		return waittimeExpression;
	}

	/**
	 * 鐢ㄤ簬瀹氬埗鍒锋�鏃堕棿 鏃堕棿琛ㄨ揪寮忔牸寮�涓� [骞碷[鏈圿[鏃[鏃堕棿娈礭;[骞碷[鏈圿[鏃[鏃堕棿娈礭;.... t_monster_model.f_waitTime_expression
	 * @param waittimeExpression  the value for t_monster_model.f_waitTime_expression
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setWaittimeExpression(String waittimeExpression) {
		this.waittimeExpression = waittimeExpression;
	}

	/**
	 * 鐗╂�鐨勮亴璐�0闅忔満宸￠� 1 绔欑珛涓嶅姩,2 鍚戠洰鏍囩偣绉诲姩 t_monster_model.f_responsibility
	 * @return  the value of t_monster_model.f_responsibility
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getResponsibility() {
		return responsibility;
	}

	/**
	 * 鐗╂�鐨勮亴璐�0闅忔満宸￠� 1 绔欑珛涓嶅姩,2 鍚戠洰鏍囩偣绉诲姩 t_monster_model.f_responsibility
	 * @param responsibility  the value for t_monster_model.f_responsibility
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setResponsibility(Integer responsibility) {
		this.responsibility = responsibility;
	}

	/**
	 * 琛�噺灏忎簬澶氬皯鏃惰瀵绘眰甯姪0-10000 1/10000 t_monster_model.f_help_hp
	 * @return  the value of t_monster_model.f_help_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHelpHp() {
		return helpHp;
	}

	/**
	 * 琛�噺灏忎簬澶氬皯鏃惰瀵绘眰甯姪0-10000 1/10000 t_monster_model.f_help_hp
	 * @param helpHp  the value for t_monster_model.f_help_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHelpHp(Integer helpHp) {
		this.helpHp = helpHp;
	}

	/**
	 * 鍠婅瘽鐨勬鐜�0-10000) 1/10000 t_monster_model.f_help_probability
	 * @return  the value of t_monster_model.f_help_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHelpProbability() {
		return helpProbability;
	}

	/**
	 * 鍠婅瘽鐨勬鐜�0-10000) 1/10000 t_monster_model.f_help_probability
	 * @param helpProbability  the value for t_monster_model.f_help_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHelpProbability(Integer helpProbability) {
		this.helpProbability = helpProbability;
	}

	/**
	 * 鍠婅瘽鏃剁殑骞垮害[0-32] t_monster_model.f_help_extent
	 * @return  the value of t_monster_model.f_help_extent
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHelpExtent() {
		return helpExtent;
	}

	/**
	 * 鍠婅瘽鏃剁殑骞垮害[0-32] t_monster_model.f_help_extent
	 * @param helpExtent  the value for t_monster_model.f_help_extent
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHelpExtent(Integer helpExtent) {
		this.helpExtent = helpExtent;
	}

	/**
	 * 鍙帴鏀跺枈璇濈殑鎬墿妯″瀷ID,浠ラ�鍙峰垎闅�t_monster_model.f_helpmodelids
	 * @return  the value of t_monster_model.f_helpmodelids
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getHelpmodelids() {
		return helpmodelids;
	}

	/**
	 * 鍙帴鏀跺枈璇濈殑鎬墿妯″瀷ID,浠ラ�鍙峰垎闅�t_monster_model.f_helpmodelids
	 * @param helpmodelids  the value for t_monster_model.f_helpmodelids
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHelpmodelids(String helpmodelids) {
		this.helpmodelids = helpmodelids;
	}

	/**
	 * 杩藉嚮鐨勬鐜�0-10000) t_monster_model.f_pursuit_probability
	 * @return  the value of t_monster_model.f_pursuit_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getPursuitProbability() {
		return pursuitProbability;
	}

	/**
	 * 杩藉嚮鐨勬鐜�0-10000) t_monster_model.f_pursuit_probability
	 * @param pursuitProbability  the value for t_monster_model.f_pursuit_probability
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setPursuitProbability(Integer pursuitProbability) {
		this.pursuitProbability = pursuitProbability;
	}

	/**
	 * 鎬墿鎵�睘鐨勯樀钀�0 鍜岀帺瀹舵晫瀵�1 鍜岀帺瀹跺悓涓�垬绾�t_monster_model.f_camp
	 * @return  the value of t_monster_model.f_camp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Byte getCamp() {
		return camp;
	}

	/**
	 * 鎬墿鎵�睘鐨勯樀钀�0 鍜岀帺瀹舵晫瀵�1 鍜岀帺瀹跺悓涓�垬绾�t_monster_model.f_camp
	 * @param camp  the value for t_monster_model.f_camp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setCamp(Byte camp) {
		this.camp = camp;
	}

	/**
	 * 鎬墿鎻忚堪 t_monster_model.f_desc
	 * @return  the value of t_monster_model.f_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 鎬墿鎻忚堪 t_monster_model.f_desc
	 * @param desc  the value for t_monster_model.f_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 濡傛灉涓嶄负0 鍒欏�琛ㄧず涓烘崟鑾峰悗锛屽潗楠戞ā鍨婭D鏄粈涔�t_monster_model.f_horse_model_id
	 * @return  the value of t_monster_model.f_horse_model_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getHorseModelId() {
		return horseModelId;
	}

	/**
	 * 濡傛灉涓嶄负0 鍒欏�琛ㄧず涓烘崟鑾峰悗锛屽潗楠戞ā鍨婭D鏄粈涔�t_monster_model.f_horse_model_id
	 * @param horseModelId  the value for t_monster_model.f_horse_model_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setHorseModelId(Integer horseModelId) {
		this.horseModelId = horseModelId;
	}

	/**
	 * 鑴氭湰绫诲悕 java鍏ㄨ矾寰�t_monster_model.f_script_class
	 * @return  the value of t_monster_model.f_script_class
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getScriptClass() {
		return scriptClass;
	}

	/**
	 * 鑴氭湰绫诲悕 java鍏ㄨ矾寰�t_monster_model.f_script_class
	 * @param scriptClass  the value for t_monster_model.f_script_class
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setScriptClass(String scriptClass) {
		this.scriptClass = scriptClass;
	}

	/**
	 * 鑴氭湰绫诲垵濮嬪寲鍙傛暟 t_monster_model.f_script_classparam
	 * @return  the value of t_monster_model.f_script_classparam
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getScriptClassparam() {
		return scriptClassparam;
	}

	/**
	 * 鑴氭湰绫诲垵濮嬪寲鍙傛暟 t_monster_model.f_script_classparam
	 * @param scriptClassparam  the value for t_monster_model.f_script_classparam
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setScriptClassparam(String scriptClassparam) {
		this.scriptClassparam = scriptClassparam;
	}

	/**
	 * 0涓嶈创韬紝1璐磋韩鏀诲嚮 t_monster_model.f_skin
	 * @return  the value of t_monster_model.f_skin
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Byte getSkin() {
		return skin;
	}

	/**
	 * 0涓嶈创韬紝1璐磋韩鏀诲嚮 t_monster_model.f_skin
	 * @param skin  the value for t_monster_model.f_skin
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkin(Byte skin) {
		this.skin = skin;
	}

	/**
	 * 瑁呭杞娆℃暟 t_monster_model.f_loop_count
	 * @return  the value of t_monster_model.f_loop_count
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getLoopCount() {
		return loopCount;
	}

	/**
	 * 瑁呭杞娆℃暟 t_monster_model.f_loop_count
	 * @param loopCount  the value for t_monster_model.f_loop_count
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setLoopCount(Integer loopCount) {
		this.loopCount = loopCount;
	}

	/**
	 * 鏄惁浣滀负NPC濂藉弸妯″瀷 t_monster_model.f_isnpcfriend
	 * @return  the value of t_monster_model.f_isnpcfriend
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getIsnpcfriend() {
		return isnpcfriend;
	}

	/**
	 * 鏄惁浣滀负NPC濂藉弸妯″瀷 t_monster_model.f_isnpcfriend
	 * @param isnpcfriend  the value for t_monster_model.f_isnpcfriend
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setIsnpcfriend(Integer isnpcfriend) {
		this.isnpcfriend = isnpcfriend;
	}

	/**
	 * 鐖嗙巼鏄熺骇 t_monster_model.f_probabilitylevel
	 * @return  the value of t_monster_model.f_probabilitylevel
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getProbabilitylevel() {
		return probabilitylevel;
	}

	/**
	 * 鐖嗙巼鏄熺骇 t_monster_model.f_probabilitylevel
	 * @param probabilitylevel  the value for t_monster_model.f_probabilitylevel
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setProbabilitylevel(Integer probabilitylevel) {
		this.probabilitylevel = probabilitylevel;
	}

	/**
	 * t_monster_model.f_instance_id
	 * @return  the value of t_monster_model.f_instance_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getInstanceId() {
		return instanceId;
	}

	/**
	 * t_monster_model.f_instance_id
	 * @param instanceId  the value for t_monster_model.f_instance_id
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 姝﹀姛鎻忚堪 t_monster_model.f_skill_desc
	 * @return  the value of t_monster_model.f_skill_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getSkillDesc() {
		return skillDesc;
	}

	/**
	 * 姝﹀姛鎻忚堪 t_monster_model.f_skill_desc
	 * @param skillDesc  the value for t_monster_model.f_skill_desc
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	/**
	 * 琚敾鍑绘寜鍥哄畾鍊煎皯琛�紝锛堟棤瑙嗙帺瀹舵敾鍑诲姏锛�鍥哄畾鍊�锛�涓嶇敓鏁堬級 t_monster_model.f_beattack_hp
	 * @return  the value of t_monster_model.f_beattack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getBeattackHp() {
		return beattackHp;
	}

	/**
	 * 琚敾鍑绘寜鍥哄畾鍊煎皯琛�紝锛堟棤瑙嗙帺瀹舵敾鍑诲姏锛�鍥哄畾鍊�锛�涓嶇敓鏁堬級 t_monster_model.f_beattack_hp
	 * @param beattackHp  the value for t_monster_model.f_beattack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setBeattackHp(Integer beattackHp) {
		this.beattackHp = beattackHp;
	}

	/**
	 * 鏀诲嚮鐜╁鎸夋瘮渚嬪皯琛�紝锛堟棤瑙嗙帺瀹堕槻寰″姏锛夛紙鍗曚綅1/10000锛夛紙0涓嶇敓鏁堬級(鎸夋渶澶х殑琛�噺姣斾緥灏戣) t_monster_model.f_attack_hp
	 * @return  the value of t_monster_model.f_attack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getAttackHp() {
		return attackHp;
	}

	/**
	 * 鏀诲嚮鐜╁鎸夋瘮渚嬪皯琛�紝锛堟棤瑙嗙帺瀹堕槻寰″姏锛夛紙鍗曚綅1/10000锛夛紙0涓嶇敓鏁堬級(鎸夋渶澶х殑琛�噺姣斾緥灏戣) t_monster_model.f_attack_hp
	 * @param attackHp  the value for t_monster_model.f_attack_hp
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setAttackHp(Integer attackHp) {
		this.attackHp = attackHp;
	}

	/**
	 * 缁忛獙瀵逛簬浠讳綍绛夌骇鐨勭帺瀹跺潎涓嶈“鍑忋�(1鐢熸晥0涓嶇敓鏁� t_monster_model.f_exp_unlimit
	 * @return  the value of t_monster_model.f_exp_unlimit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getExpUnlimit() {
		return expUnlimit;
	}

	/**
	 * 缁忛獙瀵逛簬浠讳綍绛夌骇鐨勭帺瀹跺潎涓嶈“鍑忋�(1鐢熸晥0涓嶇敓鏁� t_monster_model.f_exp_unlimit
	 * @param expUnlimit  the value for t_monster_model.f_exp_unlimit
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setExpUnlimit(Integer expUnlimit) {
		this.expUnlimit = expUnlimit;
	}

	/**
	 * {0鍙嶅嚮锛堣蛋姝ｅ父鐨刟i锛�1涓嶅弽鍑�鍖呮嫭锛氱┖闂瞐i,宸￠�ai,姝讳骸ai,灏镐綋娑堝けai) t_monster_model.f_beat_back
	 * @return  the value of t_monster_model.f_beat_back
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public Integer getBeatBack() {
		return beatBack;
	}

	/**
	 * {0鍙嶅嚮锛堣蛋姝ｅ父鐨刟i锛�1涓嶅弽鍑�鍖呮嫭锛氱┖闂瞐i,宸￠�ai,姝讳骸ai,灏镐綋娑堝けai) t_monster_model.f_beat_back
	 * @param beatBack  the value for t_monster_model.f_beat_back
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setBeatBack(Integer beatBack) {
		this.beatBack = beatBack;
	}

	/**
	 * 鎬墿鍚嶇О鍥介檯鍖�t_monster_model.f_name_i18n
	 * @return  the value of t_monster_model.f_name_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 鎬墿鍚嶇О鍥介檯鍖�t_monster_model.f_name_i18n
	 * @param nameI18n  the value for t_monster_model.f_name_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks_i18n
	 * @return  the value of t_monster_model.f_speaks_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getSpeaksI18n() {
		return speaksI18n;
	}

	/**
	 * 鎬墿璇磋瘽鍐呭 濡傛灉澶氬彞璇�涓棿浠�鍒嗗壊 t_monster_model.f_speaks_i18n
	 * @param speaksI18n  the value for t_monster_model.f_speaks_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSpeaksI18n(String speaksI18n) {
		this.speaksI18n = speaksI18n;
	}

	/**
	 * 鎬墿鎻忚堪鍥介檯鍖�t_monster_model.f_desc_i18n
	 * @return  the value of t_monster_model.f_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 鎬墿鎻忚堪鍥介檯鍖�t_monster_model.f_desc_i18n
	 * @param descI18n  the value for t_monster_model.f_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 姝﹀姛鎻忚堪鍥介檯鍖�t_monster_model.f_skill_desc_i18n
	 * @return  the value of t_monster_model.f_skill_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public String getSkillDescI18n() {
		return skillDescI18n;
	}

	/**
	 * 姝﹀姛鎻忚堪鍥介檯鍖�t_monster_model.f_skill_desc_i18n
	 * @param skillDescI18n  the value for t_monster_model.f_skill_desc_i18n
	 * @ibatorgenerated  Thu May 05 17:50:50 CST 2011
	 */
	public void setSkillDescI18n(String skillDescI18n) {
		this.skillDescI18n = skillDescI18n;
	}

	public Integer getSubtype() {
		return subtype;
	}

	public void setSubtype(Integer subtype) {
		this.subtype = subtype;
	}
}
