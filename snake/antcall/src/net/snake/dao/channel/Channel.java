package net.snake.dao.channel;

public class Channel {

	/**
	 * 缁忚剦缂栧彿 t_channel.f_id
	 */
	private Integer id;
	/**
	 * 缁忚剦鍚嶇О t_channel.f_name
	 */
	private String name;
	/**
	 * 琛�t_channel.f_hp_add
	 */
	private Integer hpAdd;
	/**
	 * 鍐呭姏 t_channel.f_mp_add
	 */
	private Integer mpAdd;
	/**
	 * 浣撳姏 t_channel.f_sp_add
	 */
	private Integer spAdd;
	/**
	 * 鏀�t_channel.f_attack_add
	 */
	private Integer attackAdd;
	/**
	 * 闃�t_channel.f_defence_add
	 */
	private Integer defenceAdd;
	/**
	 * 闂伩 t_channel.f_dodge_add
	 */
	private Integer dodgeAdd;
	/**
	 * 鏆村嚮 t_channel.f_crt_add
	 */
	private Integer crtAdd;
	/**
	 * 闇�鐪熸皵閲�t_channel.f_need_zhenqi
	 */
	private Integer needZhenqi;
	/**
	 * 鍐插嚮鍑犵巼涓囧垎涔嬪嚑 t_channel.f_odds
	 */
	private Integer odds;
	/**
	 * 缁忚剦鍚嶇О鍥介檯鍖�t_channel.f_name_i18n
	 */
	private String nameI18n;
	/**
	 * 浠ょ粡鑴夊け鏁堢殑debuffId t_channel.f_debuff_id
	 */
	private Integer debuffId;

	/**
	 * 缁忚剦缂栧彿 t_channel.f_id
	 * @return  the value of t_channel.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 缁忚剦缂栧彿 t_channel.f_id
	 * @param id  the value for t_channel.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 缁忚剦鍚嶇О t_channel.f_name
	 * @return  the value of t_channel.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 缁忚剦鍚嶇О t_channel.f_name
	 * @param name  the value for t_channel.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 琛�t_channel.f_hp_add
	 * @return  the value of t_channel.f_hp_add
	 */
	public Integer getHpAdd() {
		return hpAdd;
	}

	/**
	 * 琛�t_channel.f_hp_add
	 * @param hpAdd  the value for t_channel.f_hp_add
	 */
	public void setHpAdd(Integer hpAdd) {
		this.hpAdd = hpAdd;
	}

	/**
	 * 鍐呭姏 t_channel.f_mp_add
	 * @return  the value of t_channel.f_mp_add
	 */
	public Integer getMpAdd() {
		return mpAdd;
	}

	/**
	 * 鍐呭姏 t_channel.f_mp_add
	 * @param mpAdd  the value for t_channel.f_mp_add
	 */
	public void setMpAdd(Integer mpAdd) {
		this.mpAdd = mpAdd;
	}

	/**
	 * 浣撳姏 t_channel.f_sp_add
	 * @return  the value of t_channel.f_sp_add
	 */
	public Integer getSpAdd() {
		return spAdd;
	}

	/**
	 * 浣撳姏 t_channel.f_sp_add
	 * @param spAdd  the value for t_channel.f_sp_add
	 */
	public void setSpAdd(Integer spAdd) {
		this.spAdd = spAdd;
	}

	/**
	 * 鏀�t_channel.f_attack_add
	 * @return  the value of t_channel.f_attack_add
	 */
	public Integer getAttackAdd() {
		return attackAdd;
	}

	/**
	 * 鏀�t_channel.f_attack_add
	 * @param attackAdd  the value for t_channel.f_attack_add
	 */
	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	/**
	 * 闃�t_channel.f_defence_add
	 * @return  the value of t_channel.f_defence_add
	 */
	public Integer getDefenceAdd() {
		return defenceAdd;
	}

	/**
	 * 闃�t_channel.f_defence_add
	 * @param defenceAdd  the value for t_channel.f_defence_add
	 */
	public void setDefenceAdd(Integer defenceAdd) {
		this.defenceAdd = defenceAdd;
	}

	/**
	 * 闂伩 t_channel.f_dodge_add
	 * @return  the value of t_channel.f_dodge_add
	 */
	public Integer getDodgeAdd() {
		return dodgeAdd;
	}

	/**
	 * 闂伩 t_channel.f_dodge_add
	 * @param dodgeAdd  the value for t_channel.f_dodge_add
	 */
	public void setDodgeAdd(Integer dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
	}

	/**
	 * 鏆村嚮 t_channel.f_crt_add
	 * @return  the value of t_channel.f_crt_add
	 */
	public Integer getCrtAdd() {
		return crtAdd;
	}

	/**
	 * 鏆村嚮 t_channel.f_crt_add
	 * @param crtAdd  the value for t_channel.f_crt_add
	 */
	public void setCrtAdd(Integer crtAdd) {
		this.crtAdd = crtAdd;
	}

	/**
	 * 闇�鐪熸皵閲�t_channel.f_need_zhenqi
	 * @return  the value of t_channel.f_need_zhenqi
	 */
	public Integer getNeedZhenqi() {
		return needZhenqi;
	}

	/**
	 * 闇�鐪熸皵閲�t_channel.f_need_zhenqi
	 * @param needZhenqi  the value for t_channel.f_need_zhenqi
	 */
	public void setNeedZhenqi(Integer needZhenqi) {
		this.needZhenqi = needZhenqi;
	}

	/**
	 * 鍐插嚮鍑犵巼涓囧垎涔嬪嚑 t_channel.f_odds
	 * @return  the value of t_channel.f_odds
	 */
	public Integer getOdds() {
		return odds;
	}

	/**
	 * 鍐插嚮鍑犵巼涓囧垎涔嬪嚑 t_channel.f_odds
	 * @param odds  the value for t_channel.f_odds
	 */
	public void setOdds(Integer odds) {
		this.odds = odds;
	}

	/**
	 * 缁忚剦鍚嶇О鍥介檯鍖�t_channel.f_name_i18n
	 * @return  the value of t_channel.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 缁忚剦鍚嶇О鍥介檯鍖�t_channel.f_name_i18n
	 * @param nameI18n  the value for t_channel.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 浠ょ粡鑴夊け鏁堢殑debuffId t_channel.f_debuff_id
	 * @return  the value of t_channel.f_debuff_id
	 */
	public Integer getDebuffId() {
		return debuffId;
	}

	/**
	 * 浠ょ粡鑴夊け鏁堢殑debuffId t_channel.f_debuff_id
	 * @param debuffId  the value for t_channel.f_debuff_id
	 */
	public void setDebuffId(Integer debuffId) {
		this.debuffId = debuffId;
	}
}
