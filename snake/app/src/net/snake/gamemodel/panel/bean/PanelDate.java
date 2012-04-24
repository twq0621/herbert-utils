package net.snake.gamemodel.panel.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class PanelDate  implements IbatisEntity{

	/**
	 * t_panel.f_id
	 * 
	 */
	private Integer fId;
	/**
	 * 面板ID t_panel.f_panel_id
	 * 
	 */
	private Integer fPanelId;
	/**
	 * 是否打开 t_panel.f_is_open
	 * 
	 */
	private Boolean fIsOpen;
	/**
	 * t_panel.f_start_time
	 * 
	 */
	private Date fStartTime;
	/**
	 * t_panel.f_stop_time
	 * 
	 */
	private Date fStopTime;
	/**
	 * 对应面板名字 t_panel.f_name
	 * 
	 */
	private String fName;

	/**
	 * t_panel.f_id
	 * @return  the value of t_panel.f_id
	 * 
	 */
	public Integer getfId() {
		return fId;
	}

	/**
	 * t_panel.f_id
	 * @param fId  the value for t_panel.f_id
	 * 
	 */
	public void setfId(Integer fId) {
		this.fId = fId;
	}

	/**
	 * 面板ID t_panel.f_panel_id
	 * @return  the value of t_panel.f_panel_id
	 * 
	 */
	public Integer getfPanelId() {
		return fPanelId;
	}

	/**
	 * 面板ID t_panel.f_panel_id
	 * @param fPanelId  the value for t_panel.f_panel_id
	 * 
	 */
	public void setfPanelId(Integer fPanelId) {
		this.fPanelId = fPanelId;
	}

	/**
	 * 是否打开 t_panel.f_is_open
	 * @return  the value of t_panel.f_is_open
	 * 
	 */
	public Boolean getfIsOpen() {
		return fIsOpen;
	}

	/**
	 * 是否打开 t_panel.f_is_open
	 * @param fIsOpen  the value for t_panel.f_is_open
	 * 
	 */
	public void setfIsOpen(Boolean fIsOpen) {
		this.fIsOpen = fIsOpen;
	}

	/**
	 * t_panel.f_start_time
	 * @return  the value of t_panel.f_start_time
	 * 
	 */
	public Date getfStartTime() {
		return fStartTime;
	}

	/**
	 * t_panel.f_start_time
	 * @param fStartTime  the value for t_panel.f_start_time
	 * 
	 */
	public void setfStartTime(Date fStartTime) {
		this.fStartTime = fStartTime;
	}

	/**
	 * t_panel.f_stop_time
	 * @return  the value of t_panel.f_stop_time
	 * 
	 */
	public Date getfStopTime() {
		return fStopTime;
	}

	/**
	 * t_panel.f_stop_time
	 * @param fStopTime  the value for t_panel.f_stop_time
	 * 
	 */
	public void setfStopTime(Date fStopTime) {
		this.fStopTime = fStopTime;
	}

	/**
	 * 对应面板名字 t_panel.f_name
	 * @return  the value of t_panel.f_name
	 * 
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 对应面板名字 t_panel.f_name
	 * @param fName  the value for t_panel.f_name
	 * 
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
}
