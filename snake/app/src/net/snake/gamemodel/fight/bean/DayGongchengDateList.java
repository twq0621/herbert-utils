package net.snake.gamemodel.fight.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DayGongchengDateList {
	private Date gongchengDate;
	private List<GongchengDate> list = new ArrayList<GongchengDate>();

	public Date getGongchengDate() {
		return gongchengDate;
	}

	public void setGongchengDate(Date gongchengDate) {
		this.gongchengDate = gongchengDate;
	}

	public List<GongchengDate> getList() {
		return list;
	}

	public void setList(List<GongchengDate> list) {
		this.list = list;
	}

	public DayGongchengDateList(Date gongchengDate) {
		this.gongchengDate = gongchengDate;
	}

	public void addGongchengDate(GongchengDate gongcheng) {
		list.add(gongcheng);
	}

	public void removeGongchengDate(GongchengDate gongcheng) {
		int deletef = gongcheng.getFactionId();
		for (int i = 0; i < list.size(); i++) {
			GongchengDate temp = list.get(i);
			if (temp.getFactionId() == deletef) {
				list.remove(i);
				return;
			}
		}
	}

	/**
	 * 是否在内factionId 的帮会
	 * 
	 * @param factionId
	 * @return
	 */
	public boolean isHaveFaction(int factionId) {
		for (int i = 0; i < list.size(); i++) {
			GongchengDate temp = list.get(i);
			if (temp.getFactionId() == factionId) {
				return true;
			}
		}
		return false;
	}
}
