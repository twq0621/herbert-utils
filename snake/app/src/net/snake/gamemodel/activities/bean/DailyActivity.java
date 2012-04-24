package net.snake.gamemodel.activities.bean;

/**
 * @author serv_dev
 */
public class DailyActivity {
	// 数量byte，{时间string，活动名称strin，等级short,发布人名称string，发布人id int（无发布人为0），
	// 已完成次数short，总次数short，奖励信息byte（0表示没星，1半星，2一星，3,一星半......），
	// 详细信息string xml格式,类型:byte(类型1进行中,2全天,3即将举行,4已过期)，完成状态（0未完成/1完成）}
	String time;
	String name;
	short grade;
	String npcname;
	int npcid;
	short finshcount;
	String maxcount;
	byte leave;
	String desc;
	byte type;
	byte state;
	String tongguanjl;// 通关奖历
	/**
	 * 0不需要统计 1需要统计
	 */
	short iscount;

	/**
	 * 
	 * @param time
	 *            时间
	 * @param name
	 *            活动名称
	 * @param grade
	 *            等级
	 * @param npcname
	 *            发布人名称
	 * @param npcid
	 *            发布人ID 无发布人为0
	 * @param finshcount
	 *            己完成次数
	 * @param maxcount
	 *            总次数
	 * @param leave
	 *            奖励等级 0表示没星，1半星，2一星，3,一星半......
	 * @param desc
	 *            祥细信息
	 * @param type
	 *            类型 1进行中,2全天,3即将举行,4已过期
	 * @param state
	 *            完成状态 0未完成/1完成
	 */
	public DailyActivity(String time, String name, int grade, String npcname, int npcid, int finshcount, String maxcount, int leave, String desc, int type, int state,
			String tongguanjl, short isCount) {
		this.time = time;
		this.name = name;
		this.grade = (short) grade;
		this.npcname = npcname;
		this.npcid = npcid;
		this.finshcount = (short) finshcount;
		this.maxcount = maxcount;
		this.desc = desc;
		this.type = (byte) type;
		this.state = (byte) state;
		this.tongguanjl = tongguanjl;
		this.iscount = isCount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getNpcname() {
		return npcname;
	}

	public void setNpcname(String npcname) {
		this.npcname = npcname;
	}

	public int getNpcid() {
		return npcid;
	}

	public void setNpcid(int npcid) {
		this.npcid = npcid;
	}

	public short getFinshcount() {
		return finshcount;
	}

	public void setFinshcount(short finshcount) {
		this.finshcount = finshcount;
	}

	public String getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}

	public byte getLeave() {
		return leave;
	}

	public void setLeave(byte leave) {
		this.leave = leave;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getTongguanjl() {
		return tongguanjl;
	}

	public void setTongguanjl(String tongguanjl) {
		this.tongguanjl = tongguanjl;
	}

	public short getIscount() {
		return iscount;
	}

	public void setIscount(short iscount) {
		this.iscount = iscount;
	}

}
