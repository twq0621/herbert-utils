package net.snake.gamemodel.skill.bean;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;

public class HiddenWeaponDataEntry implements IbatisEntity {
	/**
	 * t_character_hidden_weapon.f_id
	 * 
	 * 
	 */
	private String id;
	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * 
	 * 
	 */
	private Integer grade;
	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * 
	 * 
	 */
	private Integer nowMastery;
	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * 
	 * 
	 */
	private Boolean isUse;
	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * 
	 * 
	 */
	private Integer xiuGrade;
	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * 
	 * 
	 */
	private Integer luckValue;
	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * 
	 * 
	 */
	private Integer tupoCnt;
	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * 
	 * 
	 */
	private Integer freeCnt;
	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * 
	 * 
	 */
	private Integer randomFreeGrade;
	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * 
	 * 
	 */
	private Boolean isOpenHiddenProps;

	/**
	 * t_character_hidden_weapon.f_id
	 * 
	 * @return the value of t_character_hidden_weapon.f_id
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * t_character_hidden_weapon.f_id
	 * 
	 * @param id
	 *            the value for t_character_hidden_weapon.f_id
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * 
	 * @return the value of t_character_hidden_weapon.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_hidden_weapon.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_hidden_weapon.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * 
	 * @return the value of t_character_hidden_weapon.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 暗器id 暗器等级 t_character_hidden_weapon.f_grade
	 * 
	 * @param grade
	 *            the value for t_character_hidden_weapon.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * 
	 * @return the value of t_character_hidden_weapon.f_now_mastery
	 * 
	 */
	public Integer getNowMastery() {
		return nowMastery;
	}

	/**
	 * 当前的熟练度 t_character_hidden_weapon.f_now_mastery
	 * 
	 * @param nowMastery
	 *            the value for t_character_hidden_weapon.f_now_mastery
	 * 
	 */
	public void setNowMastery(Integer nowMastery) {
		this.nowMastery = nowMastery;
	}

	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * 
	 * @return the value of t_character_hidden_weapon.f_is_use
	 * 
	 */
	public Boolean getIsUse() {
		return isUse;
	}

	/**
	 * 0默认不启动1启动 t_character_hidden_weapon.f_is_use
	 * 
	 * @param isUse
	 *            the value for t_character_hidden_weapon.f_is_use
	 * 
	 */
	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * 
	 * @return the value of t_character_hidden_weapon.f_xiu_grade
	 * 
	 */
	public Integer getXiuGrade() {
		return xiuGrade;
	}

	/**
	 * 暗器修炼等级 t_character_hidden_weapon.f_xiu_grade
	 * 
	 * @param xiuGrade
	 *            the value for t_character_hidden_weapon.f_xiu_grade
	 * 
	 */
	public void setXiuGrade(Integer xiuGrade) {
		this.xiuGrade = xiuGrade;
	}

	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * 
	 * @return the value of t_character_hidden_weapon.f_luck_value
	 * 
	 */
	public Integer getLuckValue() {
		return luckValue;
	}

	/**
	 * 幸运值 t_character_hidden_weapon.f_luck_value
	 * 
	 * @param luckValue
	 *            the value for t_character_hidden_weapon.f_luck_value
	 * 
	 */
	public void setLuckValue(Integer luckValue) {
		this.luckValue = luckValue;
	}

	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * 
	 * @return the value of t_character_hidden_weapon.f_tupo_cnt
	 * 
	 */
	public Integer getTupoCnt() {
		return tupoCnt;
	}

	/**
	 * 突破次数 t_character_hidden_weapon.f_tupo_cnt
	 * 
	 * @param tupoCnt
	 *            the value for t_character_hidden_weapon.f_tupo_cnt
	 * 
	 */
	public void setTupoCnt(Integer tupoCnt) {
		this.tupoCnt = tupoCnt;
	}

	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * 
	 * @return the value of t_character_hidden_weapon.f_free_cnt
	 * 
	 */
	public Integer getFreeCnt() {
		return freeCnt;
	}

	/**
	 * 免费突破次数累计（活动使用，最多为1） t_character_hidden_weapon.f_free_cnt
	 * 
	 * @param freeCnt
	 *            the value for t_character_hidden_weapon.f_free_cnt
	 * 
	 */
	public void setFreeCnt(Integer freeCnt) {
		this.freeCnt = freeCnt;
	}

	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * 
	 * @return the value of t_character_hidden_weapon.f_random_free_grade
	 * 
	 */
	public Integer getRandomFreeGrade() {
		return randomFreeGrade;
	}

	/**
	 * 随机一个免费突破等级（活动使用，这个是三阶以上的等级，而且必定突破成功） t_character_hidden_weapon.f_random_free_grade
	 * 
	 * @param randomFreeGrade
	 *            the value for t_character_hidden_weapon.f_random_free_grade
	 * 
	 */
	public void setRandomFreeGrade(Integer randomFreeGrade) {
		this.randomFreeGrade = randomFreeGrade;
	}

	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * 
	 * @return the value of t_character_hidden_weapon.f_is_open_hidden_props
	 * 
	 */
	public Boolean getIsOpenHiddenProps() {
		return isOpenHiddenProps;
	}

	/**
	 * 暗器是否已开启隐藏属性 t_character_hidden_weapon.f_is_open_hidden_props
	 * 
	 * @param isOpenHiddenProps
	 *            the value for t_character_hidden_weapon.f_is_open_hidden_props
	 * 
	 */
	public void setIsOpenHiddenProps(Boolean isOpenHiddenProps) {
		this.isOpenHiddenProps = isOpenHiddenProps;
	}

	private int metop = 0;// 临时统计出我排名第几

	public int getMetop() {
		return metop;
	}

	public void setMetop(int metop) {
		this.metop = metop;
	}

	private CharacterCacheEntry cce;

	public CharacterCacheEntry getCce() {
		if (this.cce == null) {
			this.cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(characterId);
			if (this.cce == null) {
				// return new CharacterCacheEntry();
			}
		}
		return cce;
	}

	public void setCce(CharacterCacheEntry cce) {
		this.cce = cce;
	}

	private String charactername;
	private int charactergrade;

	public int getCharactergrade() {
		return charactergrade;
	}

	public void setCharactergrade(int charactergrade) {
		this.charactergrade = charactergrade;
	}

	public String getCharactername() {
		return getCce() == null ? charactername : getCce().getViewName();
	}

	public void setCharactername(String charactername) {
		this.charactername = charactername;
	}

}
