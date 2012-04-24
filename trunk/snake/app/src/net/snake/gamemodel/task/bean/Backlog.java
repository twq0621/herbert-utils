package net.snake.gamemodel.task.bean;

public class Backlog {
	int targetid;
	String title = "";
	short finshnum; // 完成数
	short sumnum; // 总数

	/**
	 * 
	 * @param taskid
	 *            任务ID
	 * @param title
	 *            任务名
	 * @param finshnum
	 *            完成数
	 * @param sumnum
	 *            总数
	 */
	public Backlog(int taskid, String title, int finshnum, int sumnum) {
		this.targetid = taskid;
		this.title = title;
		this.finshnum = (short) finshnum;
		this.sumnum = (short) sumnum;
	}

	/**
	 * 
	 * @param instanceNpcid
	 *            副本npcID
	 * @param title
	 *            副本名
	 */
	public Backlog(int instanceNpcid, String title) {
		this.targetid = instanceNpcid;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public short getFinshnum() {
		return finshnum;
	}

	public void setFinshnum(short finshnum) {
		this.finshnum = finshnum;
	}

	public short getSumnum() {
		return sumnum;
	}

	public void setSumnum(short sumnum) {
		this.sumnum = sumnum;
	}

	public int getTargetid() {
		return targetid;
	}

	public void setTargetid(int targetid) {
		this.targetid = targetid;
	}

}
