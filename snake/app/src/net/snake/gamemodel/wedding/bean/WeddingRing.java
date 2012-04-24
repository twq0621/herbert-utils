package net.snake.gamemodel.wedding.bean;

import net.snake.ibatis.IbatisEntity;

public class WeddingRing implements IbatisEntity {

	/**
	 * t_wedding_ring.f_id
	 */
	private Integer id;
	/**
	 * 婚戒物品id t_wedding_ring.f_ring_id
	 */
	private Integer ringId;
	/**
	 * 婚戒等级 t_wedding_ring.f_grade
	 */
	private Integer grade;
	/**
	 * 玉佩能力技能1 t_wedding_ring.f_skill_a
	 */
	private Integer skillA;
	/**
	 * 玉佩能力技能2 t_wedding_ring.f_skill_b
	 */
	private Integer skillB;
	/**
	 * 南方对应1/2玉佩 t_wedding_ring.f_male_good
	 */
	private Integer maleGood;
	/**
	 * 女方1/2玉佩 t_wedding_ring.f_female_good
	 */
	private Integer femaleGood;
	/**
	 * 出售价格 t_wedding_ring.f_sale_copper
	 */
	private Integer saleCopper;

	/**
	 * t_wedding_ring.f_id
	 * 
	 * @return the value of t_wedding_ring.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_wedding_ring.f_id
	 * 
	 * @param id
	 *            the value for t_wedding_ring.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 婚戒物品id t_wedding_ring.f_ring_id
	 * 
	 * @return the value of t_wedding_ring.f_ring_id
	 */
	public Integer getRingId() {
		return ringId;
	}

	/**
	 * 婚戒物品id t_wedding_ring.f_ring_id
	 * 
	 * @param ringId
	 *            the value for t_wedding_ring.f_ring_id
	 */
	public void setRingId(Integer ringId) {
		this.ringId = ringId;
	}

	/**
	 * 婚戒等级 t_wedding_ring.f_grade
	 * 
	 * @return the value of t_wedding_ring.f_grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 婚戒等级 t_wedding_ring.f_grade
	 * 
	 * @param grade
	 *            the value for t_wedding_ring.f_grade
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 玉佩能力技能1 t_wedding_ring.f_skill_a
	 * 
	 * @return the value of t_wedding_ring.f_skill_a
	 */
	public Integer getSkillA() {
		return skillA;
	}

	/**
	 * 玉佩能力技能1 t_wedding_ring.f_skill_a
	 * 
	 * @param skillA
	 *            the value for t_wedding_ring.f_skill_a
	 */
	public void setSkillA(Integer skillA) {
		this.skillA = skillA;
	}

	/**
	 * 玉佩能力技能2 t_wedding_ring.f_skill_b
	 * 
	 * @return the value of t_wedding_ring.f_skill_b
	 */
	public Integer getSkillB() {
		return skillB;
	}

	/**
	 * 玉佩能力技能2 t_wedding_ring.f_skill_b
	 * 
	 * @param skillB
	 *            the value for t_wedding_ring.f_skill_b
	 */
	public void setSkillB(Integer skillB) {
		this.skillB = skillB;
	}

	/**
	 * 南方对应1/2玉佩 t_wedding_ring.f_male_good
	 * 
	 * @return the value of t_wedding_ring.f_male_good
	 */
	public Integer getMaleGood() {
		return maleGood;
	}

	/**
	 * 南方对应1/2玉佩 t_wedding_ring.f_male_good
	 * 
	 * @param maleGood
	 *            the value for t_wedding_ring.f_male_good
	 */
	public void setMaleGood(Integer maleGood) {
		this.maleGood = maleGood;
	}

	/**
	 * 女方1/2玉佩 t_wedding_ring.f_female_good
	 * 
	 * @return the value of t_wedding_ring.f_female_good
	 */
	public Integer getFemaleGood() {
		return femaleGood;
	}

	/**
	 * 女方1/2玉佩 t_wedding_ring.f_female_good
	 * 
	 * @param femaleGood
	 *            the value for t_wedding_ring.f_female_good
	 */
	public void setFemaleGood(Integer femaleGood) {
		this.femaleGood = femaleGood;
	}

	/**
	 * 出售价格 t_wedding_ring.f_sale_copper
	 * 
	 * @return the value of t_wedding_ring.f_sale_copper
	 */
	public Integer getSaleCopper() {
		return saleCopper;
	}

	/**
	 * 出售价格 t_wedding_ring.f_sale_copper
	 * 
	 * @param saleCopper
	 *            the value for t_wedding_ring.f_sale_copper
	 */
	public void setSaleCopper(Integer saleCopper) {
		this.saleCopper = saleCopper;
	}
}
