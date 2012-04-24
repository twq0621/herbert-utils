package net.snake.gamemodel.guide.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterIdea  implements IbatisEntity{

	/**
	 * t_character_idea.f_id
	 *
	 */
	private Integer id;
	/**
	 * 提意见角色id t_character_idea.f_character_id
	 *
	 */
	private Integer characterId;
	/**
	 * 意见类型 0bug/1好注意/2投诉/3其他 t_character_idea.t_type
	 *
	 */
	private Integer tType;
	/**
	 * 意见标题 t_character_idea.f_title
	 *
	 */
	private String title;
	/**
	 * 意见内容 t_character_idea.f_content
	 *
	 */
	private String content;

	/**
	 * t_character_idea.f_id
	 * @return  the value of t_character_idea.f_id
	 *
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_idea.f_id
	 * @param id  the value for t_character_idea.f_id
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 提意见角色id t_character_idea.f_character_id
	 * @return  the value of t_character_idea.f_character_id
	 *
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 提意见角色id t_character_idea.f_character_id
	 * @param characterId  the value for t_character_idea.f_character_id
	 *
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 意见类型 0bug/1好注意/2投诉/3其他 t_character_idea.t_type
	 * @return  the value of t_character_idea.t_type
	 *
	 */
	public Integer gettType() {
		return tType;
	}

	/**
	 * 意见类型 0bug/1好注意/2投诉/3其他 t_character_idea.t_type
	 * @param tType  the value for t_character_idea.t_type
	 *
	 */
	public void settType(Integer tType) {
		this.tType = tType;
	}

	/**
	 * 意见标题 t_character_idea.f_title
	 * @return  the value of t_character_idea.f_title
	 *
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 意见标题 t_character_idea.f_title
	 * @param title  the value for t_character_idea.f_title
	 *
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 意见内容 t_character_idea.f_content
	 * @return  the value of t_character_idea.f_content
	 *
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 意见内容 t_character_idea.f_content
	 * @param content  the value for t_character_idea.f_content
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
