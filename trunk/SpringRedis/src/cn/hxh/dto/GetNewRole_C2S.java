package cn.hxh.dto;

import java.io.Serializable;

public class GetNewRole_C2S extends Amf3BaseDto implements Serializable {

	private static final long serialVersionUID = -6474250436607778655L;

	private String queryDay;

	public String getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(String queryDay) {
		this.queryDay = queryDay;
	}

}
