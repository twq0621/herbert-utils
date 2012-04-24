package net.snake.gamemodel.hero.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterPhoto  implements IbatisEntity{

	/**
	 * 角色id t_character_photo.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 上传时间 t_character_photo.f_character_time
	 * 
	 */
	private Date characterTime;
	/**
	 * 审核标识0是未审核1审核通过 t_character_photo.f_character_type
	 * 
	 */
	private Byte characterType;
	/**
	 * 未通过原因 t_character_photo.f_character_context
	 * 
	 */
	private String characterContext;
	/**
	 * 地址 t_character_photo.f_url
	 * 
	 */
	private String url;
	/**
	 * 验证地址 t_character_photo.f_url_verify
	 * 
	 */
	private String urlVerify;

	/**
	 * 角色id t_character_photo.f_character_id
	 * @return  the value of t_character_photo.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_photo.f_character_id
	 * @param characterId  the value for t_character_photo.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 上传时间 t_character_photo.f_character_time
	 * @return  the value of t_character_photo.f_character_time
	 * 
	 */
	public Date getCharacterTime() {
		return characterTime;
	}

	/**
	 * 上传时间 t_character_photo.f_character_time
	 * @param characterTime  the value for t_character_photo.f_character_time
	 * 
	 */
	public void setCharacterTime(Date characterTime) {
		this.characterTime = characterTime;
	}

	/**
	 * 审核标识0是未审核1审核通过 t_character_photo.f_character_type
	 * @return  the value of t_character_photo.f_character_type
	 * 
	 */
	public Byte getCharacterType() {
		return characterType;
	}

	/**
	 * 审核标识0是未审核1审核通过 t_character_photo.f_character_type
	 * @param characterType  the value for t_character_photo.f_character_type
	 * 
	 */
	public void setCharacterType(Byte characterType) {
		this.characterType = characterType;
	}

	/**
	 * 未通过原因 t_character_photo.f_character_context
	 * @return  the value of t_character_photo.f_character_context
	 * 
	 */
	public String getCharacterContext() {
		return characterContext;
	}

	/**
	 * 未通过原因 t_character_photo.f_character_context
	 * @param characterContext  the value for t_character_photo.f_character_context
	 * 
	 */
	public void setCharacterContext(String characterContext) {
		this.characterContext = characterContext;
	}

	/**
	 * 地址 t_character_photo.f_url
	 * @return  the value of t_character_photo.f_url
	 * 
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 地址 t_character_photo.f_url
	 * @param url  the value for t_character_photo.f_url
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 验证地址 t_character_photo.f_url_verify
	 * @return  the value of t_character_photo.f_url_verify
	 * 
	 */
	public String getUrlVerify() {
		return urlVerify;
	}

	/**
	 * 验证地址 t_character_photo.f_url_verify
	 * @param urlVerify  the value for t_character_photo.f_url_verify
	 * 
	 */
	public void setUrlVerify(String urlVerify) {
		this.urlVerify = urlVerify;
	}
}
