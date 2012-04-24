package net.snake.gamemodel.pet.bean;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;

public class HorseCharacterView  implements IbatisEntity{
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

	/**
	 * 马实例的ID t_horse_character_view.f_id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 马的拥有者 t_horse_character_view.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 角色名 t_horse_character_view.f_name
	 * 
	 * 
	 */
	private String name;
	/**
	 * 账户id t_horse_character_view.f_account_id
	 * 
	 * 
	 */
	private Integer accountId;
	/**
	 * 马的价值 t_horse_character_view.f_horse_price
	 * 
	 * 
	 */
	private Integer horsePrice;
	/**
	 * 马的模型ID t_horse_character_view.f_horse_model_id
	 * 
	 * 
	 */
	private Integer horseModelId;
	/**
	 * 当前级别 t_horse_character_view.f_grade
	 * 
	 * 
	 */
	private Integer grade;

	/**
	 * 马实例的ID t_horse_character_view.f_id
	 * 
	 * @return the value of t_horse_character_view.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 马实例的ID t_horse_character_view.f_id
	 * 
	 * @param id
	 *            the value for t_horse_character_view.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 马的拥有者 t_horse_character_view.f_character_id
	 * 
	 * @return the value of t_horse_character_view.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 马的拥有者 t_horse_character_view.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_horse_character_view.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 角色名 t_horse_character_view.f_name
	 * 
	 * @return the value of t_horse_character_view.f_name
	 * 
	 */
	public String getName() {
		return getCce() == null ? name : getCce().getViewName();
	}

	/**
	 * 角色名 t_horse_character_view.f_name
	 * 
	 * @param name
	 *            the value for t_horse_character_view.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 账户id t_horse_character_view.f_account_id
	 * 
	 * @return the value of t_horse_character_view.f_account_id
	 * 
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 账户id t_horse_character_view.f_account_id
	 * 
	 * @param accountId
	 *            the value for t_horse_character_view.f_account_id
	 * 
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 马的价值 t_horse_character_view.f_horse_price
	 * 
	 * @return the value of t_horse_character_view.f_horse_price
	 * 
	 */
	public Integer getHorsePrice() {
		return horsePrice;
	}

	/**
	 * 马的价值 t_horse_character_view.f_horse_price
	 * 
	 * @param horsePrice
	 *            the value for t_horse_character_view.f_horse_price
	 * 
	 */
	public void setHorsePrice(Integer horsePrice) {
		this.horsePrice = horsePrice;
	}

	/**
	 * 马的模型ID t_horse_character_view.f_horse_model_id
	 * 
	 * @return the value of t_horse_character_view.f_horse_model_id
	 * 
	 */
	public Integer getHorseModelId() {
		return horseModelId;
	}

	/**
	 * 马的模型ID t_horse_character_view.f_horse_model_id
	 * 
	 * @param horseModelId
	 *            the value for t_horse_character_view.f_horse_model_id
	 * 
	 */
	public void setHorseModelId(Integer horseModelId) {
		this.horseModelId = horseModelId;
	}

	/**
	 * 当前级别 t_horse_character_view.f_grade
	 * 
	 * @return the value of t_horse_character_view.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 当前级别 t_horse_character_view.f_grade
	 * 
	 * @param grade
	 *            the value for t_horse_character_view.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
