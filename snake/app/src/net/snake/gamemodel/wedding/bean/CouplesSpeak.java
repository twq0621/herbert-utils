package net.snake.gamemodel.wedding.bean;

import java.util.Date;

import net.snake.commons.program.IntId;
import net.snake.ibatis.IbatisEntity;


public class CouplesSpeak  implements IbatisEntity{

	/**
	 * t_couples_speak.f_id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 夫妻某方说话者id t_couples_speak.f_male_id
	 * 
	 * 
	 */
	private Integer maleId;
	/**
	 * 说话者id t_couples_speak.f_speak_id
	 * 
	 * 
	 */
	private Integer speakId;
	/**
	 * 说话时间 t_couples_speak.f_speak_date
	 * 
	 * 
	 */
	private Date speakDate;
	/**
	 * 聊天内容 t_couples_speak.f_content
	 * 
	 * 
	 */
	private String content;
	/**
	 * 女方id t_couples_speak.f_female_id
	 * 
	 * 
	 */
	private Integer femaleId;
	/**
	 * 0表示不用通知/1表示需要通知 t_couples_speak.f_is_notice
	 * 
	 * 
	 */
	private Integer isNotice;

	/**
	 * t_couples_speak.f_id
	 * 
	 * @return the value of t_couples_speak.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_couples_speak.f_id
	 * 
	 * @param id
	 *            the value for t_couples_speak.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 夫妻某方说话者id t_couples_speak.f_male_id
	 * 
	 * @return the value of t_couples_speak.f_male_id
	 * 
	 */
	public Integer getMaleId() {
		return maleId;
	}

	/**
	 * 夫妻某方说话者id t_couples_speak.f_male_id
	 * 
	 * @param maleId
	 *            the value for t_couples_speak.f_male_id
	 * 
	 */
	public void setMaleId(Integer maleId) {
		this.maleId = maleId;
	}

	/**
	 * 说话者id t_couples_speak.f_speak_id
	 * 
	 * @return the value of t_couples_speak.f_speak_id
	 * 
	 */
	public Integer getSpeakId() {
		return speakId;
	}

	/**
	 * 说话者id t_couples_speak.f_speak_id
	 * 
	 * @param speakId
	 *            the value for t_couples_speak.f_speak_id
	 * 
	 */
	public void setSpeakId(Integer speakId) {
		this.speakId = speakId;
	}

	/**
	 * 说话时间 t_couples_speak.f_speak_date
	 * 
	 * @return the value of t_couples_speak.f_speak_date
	 * 
	 */
	public Date getSpeakDate() {
		return speakDate;
	}

	/**
	 * 说话时间 t_couples_speak.f_speak_date
	 * 
	 * @param speakDate
	 *            the value for t_couples_speak.f_speak_date
	 * 
	 */
	public void setSpeakDate(Date speakDate) {
		this.speakDate = speakDate;
	}

	/**
	 * 聊天内容 t_couples_speak.f_content
	 * 
	 * @return the value of t_couples_speak.f_content
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 聊天内容 t_couples_speak.f_content
	 * 
	 * @param content
	 *            the value for t_couples_speak.f_content
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 女方id t_couples_speak.f_female_id
	 * 
	 * @return the value of t_couples_speak.f_female_id
	 * 
	 */
	public Integer getFemaleId() {
		return femaleId;
	}

	/**
	 * 女方id t_couples_speak.f_female_id
	 * 
	 * @param femaleId
	 *            the value for t_couples_speak.f_female_id
	 * 
	 */
	public void setFemaleId(Integer femaleId) {
		this.femaleId = femaleId;
	}

	/**
	 * 0表示不用通知/1表示需要通知 t_couples_speak.f_is_notice
	 * 
	 * @return the value of t_couples_speak.f_is_notice
	 * 
	 */
	public Integer getIsNotice() {
		return isNotice;
	}

	/**
	 * 0表示不用通知/1表示需要通知 t_couples_speak.f_is_notice
	 * @param isNotice
	 *            the value for t_couples_speak.f_is_notice
	 * 
	 */
	public void setIsNotice(Integer isNotice) {
		this.isNotice = isNotice;
	}
	private static IntId intId = new IntId();
	private Integer tempId = intId.getNextId();

	public int getTempId() {
		return tempId;
	}

}
