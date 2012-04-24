package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class Charactergrade implements IbatisEntity {

	/**
	 * t_character_grade.f_grade
	 * 
	 */
	private Short grade;
	/**
	 * t_character_grade.f_hp_add
	 * 
	 */
	private Integer hpAdd;

	private Integer popsinger;
	/**
	 * t_character_grade.f_mp_add
	 * 
	 */
	private Integer mpAdd;
	/**
	 * t_character_grade.f_sp_add
	 * 
	 */
	private Integer spAdd;

	private Integer hitAdd;
	/**
	 * t_character_grade.f_potential_add
	 * 
	 */
	private Integer potentialAdd;
	/**
	 * t_character_grade.f_attack_add
	 * 
	 */
	private Integer attackAdd;
	/**
	 * t_character_grade.f_def_add
	 * 
	 */
	private Integer defAdd;
	/**
	 * t_character_grade.f_expose_add
	 * 
	 */
	private Integer exposeAdd;
	/**
	 * t_character_grade.f_dodge_add
	 * 
	 */
	private Integer dodgeAdd;
	/**
	 * t_character_grade.f_exp_add
	 * 
	 */
	private Long expAdd;
	/**
	 * t_character_grade.f_atk_speed
	 * 
	 */
	private Integer atkSpeed;
	/**
	 * t_character_grade.f_move_speed
	 * 
	 */
	private Integer moveSpeed;

	private String key;

	/**
	 * t_character_grade.f_grade
	 * 
	 * @return the value of t_character_grade.f_grade
	 * 
	 */

	public Short getGrade() {
		return grade;
	}

	/**
	 * t_character_grade.f_grade
	 * 
	 * @param grade
	 *            the value for t_character_grade.f_grade
	 * 
	 */
	public void setGrade(Short grade) {
		this.grade = grade;
	}

	/**
	 * t_character_grade.f_hp_add
	 * 
	 * @return the value of t_character_grade.f_hp_add
	 * 
	 */
	public Integer getHpAdd() {
		return hpAdd;
	}

	/**
	 * t_character_grade.f_hp_add
	 * 
	 * @param hpAdd
	 *            the value for t_character_grade.f_hp_add
	 * 
	 */
	public void setHpAdd(Integer hpAdd) {
		this.hpAdd = hpAdd;
	}

	/**
	 * t_character_grade.f_mp_add
	 * 
	 * @return the value of t_character_grade.f_mp_add
	 * 
	 */
	public Integer getMpAdd() {
		return mpAdd;
	}

	/**
	 * t_character_grade.f_mp_add
	 * 
	 * @param mpAdd
	 *            the value for t_character_grade.f_mp_add
	 * 
	 */
	public void setMpAdd(Integer mpAdd) {
		this.mpAdd = mpAdd;
	}

	/**
	 * t_character_grade.f_sp_add
	 * 
	 * @return the value of t_character_grade.f_sp_add
	 * 
	 */
	public Integer getSpAdd() {
		return spAdd;
	}

	/**
	 * t_character_grade.f_sp_add
	 * 
	 * @param spAdd
	 *            the value for t_character_grade.f_sp_add
	 * 
	 */
	public void setSpAdd(Integer spAdd) {
		this.spAdd = spAdd;
	}

	/**
	 * t_character_grade.f_potential_add
	 * 
	 * @return the value of t_character_grade.f_potential_add
	 * 
	 */
	public Integer getPotentialAdd() {
		return potentialAdd;
	}

	/**
	 * t_character_grade.f_potential_add
	 * 
	 * @param potentialAdd
	 *            the value for t_character_grade.f_potential_add
	 * 
	 */
	public void setPotentialAdd(Integer potentialAdd) {
		this.potentialAdd = potentialAdd;
	}

	/**
	 * t_character_grade.f_attack_add
	 * 
	 * @return the value of t_character_grade.f_attack_add
	 * 
	 */
	public Integer getAttackAdd() {
		return attackAdd;
	}

	/**
	 * t_character_grade.f_attack_add
	 * 
	 * @param attackAdd
	 *            the value for t_character_grade.f_attack_add
	 * 
	 */
	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	/**
	 * t_character_grade.f_def_add
	 * 
	 * @return the value of t_character_grade.f_def_add
	 * 
	 */
	public Integer getDefAdd() {
		return defAdd;
	}

	/**
	 * t_character_grade.f_def_add
	 * 
	 * @param defAdd
	 *            the value for t_character_grade.f_def_add
	 * 
	 */
	public void setDefAdd(Integer defAdd) {
		this.defAdd = defAdd;
	}

	/**
	 * t_character_grade.f_expose_add
	 * 
	 * @return the value of t_character_grade.f_expose_add
	 * 
	 */
	public Integer getExposeAdd() {
		return exposeAdd;
	}

	/**
	 * t_character_grade.f_expose_add
	 * 
	 * @param exposeAdd
	 *            the value for t_character_grade.f_expose_add
	 * 
	 */
	public void setExposeAdd(Integer exposeAdd) {
		this.exposeAdd = exposeAdd;
	}

	/**
	 * t_character_grade.f_dodge_add
	 * 
	 * @return the value of t_character_grade.f_dodge_add
	 * 
	 */
	public Integer getDodgeAdd() {
		return dodgeAdd;
	}

	/**
	 * t_character_grade.f_dodge_add
	 * 
	 * @param dodgeAdd
	 *            the value for t_character_grade.f_dodge_add
	 * 
	 */
	public void setDodgeAdd(Integer dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
	}

	/**
	 * 升到下一级需要的经验
	 * 
	 * @return the value of t_character_grade.f_exp_add
	 * 
	 */
	public Long getExpAdd() {
		return expAdd;
	}

	/**
	 * t_character_grade.f_exp_add
	 * 
	 * @param expAdd
	 *            the value for t_character_grade.f_exp_add
	 * 
	 */
	public void setExpAdd(Long expAdd) {
		this.expAdd = expAdd;
	}

	/**
	 * t_character_grade.f_atk_speed
	 * 
	 * @return the value of t_character_grade.f_atk_speed
	 * 
	 */
	public Integer getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * t_character_grade.f_atk_speed
	 * 
	 * @param atkSpeed
	 *            the value for t_character_grade.f_atk_speed
	 * 
	 */
	public void setAtkSpeed(Integer atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * t_character_grade.f_move_speed
	 * 
	 * @return the value of t_character_grade.f_move_speed
	 * 
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * t_character_grade.f_move_speed
	 * 
	 * @param moveSpeed
	 *            the value for t_character_grade.f_move_speed
	 * 
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * 人男（道士1),妖男(战士2),人女（乐师3),妖女（法师4)
	 * 
	 * @param popsinger
	 */
	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	/**
	 * 人男（道士1),妖男(战士2),人女（乐师3),妖女（法师4)
	 */
	public int getPopsinger() {
		return popsinger;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setPopsinger(Integer popsinger) {
		this.popsinger = popsinger;
	}

	public Integer getHitAdd() {
		return hitAdd;
	}

	public void setHitAdd(Integer hitAdd) {
		this.hitAdd = hitAdd;
	}

}
