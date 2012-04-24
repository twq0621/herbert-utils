package net.snake.gamemodel.hero.bean;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.ibatis.IbatisEntity;

/**
 * 人物角色 <br>
 * 不允许再往下继承了
 * 
 * @author serv_dev
 */

final public class CharacterForList implements IbatisEntity{

	private int id;
	private byte headimg;// 人物头像
	private String name;// 姓名
	private short grade;// 等级
	private int popsinger;// 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉
	private int deleteMark;// 删除标记 1表示被删除了;
	private int originalSid;// 原始分区id 合服用
	private String factionName;//帮派名字
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getHeadimg() {
		return headimg;
	}

	public void setHeadimg(byte headimg) {
		this.headimg = headimg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public int getPopsinger() {
		return popsinger;
	}

	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	public int getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(int deleteMark) {
		this.deleteMark = deleteMark;
	}

	public int getOriginalSid() {
		return originalSid;
	}

	public void setOriginalSid(int originalSid) {
		this.originalSid = originalSid;
	}

	public String getFactionName() {
		return factionName==null?"":factionName;
	}

	public void setFactionName(String factionName) {
		this.factionName = factionName;
	}

	public String getViewName() {
		if (GameServer.configParamManger.getConfigParam().isShowMyServerId()) {
			return "[" + this.getOriginalSid() + Language.QU + "]" + this.name;
		}
		return this.name;
	}
}
