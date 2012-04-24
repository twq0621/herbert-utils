package net.snake.gameserver.event.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.commons.script.SApi;
import net.snake.commons.script.SRole;

/**
 * 副本父类,抽一些公用方法
 * 
 */
public class SuperInstance {

	public SuperInstance() {
		super();
	}

	/**
	 * 活得队伍中所有成员
	 * 
	 * @param roles
	 * @return
	 */
	protected List<SRole> getTeamAllRole(Collection<SRole> roles) {
		if (roles == null || roles.size() == 0) {
			return new ArrayList<SRole>();
		}
		SRole role = roles.iterator().next();
		List<SRole> teamRoles = role.getTeamCharacters();
		if (teamRoles != null) {
			return teamRoles;
		}
		List<SRole> list = new ArrayList<SRole>();
		list.add(role);
		return list;
	}

	/**
	 * 得到副本中的 最高角色等级
	 * 
	 * @return
	 */
	protected int getRolesMaxGrate(SRole[] roleCollection) {
		int grate = 0;
		for (SRole role : roleCollection) {

			if (grate < role.getGrade()) {
				grate = role.getGrade();
			}
		}
		return grate;
	}

	protected int getRolesMaxGrate(Collection<SRole> roles) {
		int grate = 0;
		for (SRole role : roles) {
			if (grate < role.getGrade()) {
				grate = role.getGrade();
			}
		}
		return grate;
	}

	/**
	 * 发送信息.以 # 号分割
	 * 
	 * @param api
	 * @param msg
	 * @param roleCollection
	 */
	protected void sendMsgs(SApi api, String msg, Collection<SRole> roleCollection) {
		String msgs[] = msg.split("#");
		for (int i = 0; i < msgs.length; i++) {
			// 提示玩家
			sendMsg(api, msgs[i], roleCollection);
		}
	}

	/**
	 * 发送单条信息
	 * 
	 * @param api
	 * @param msg
	 * @param roleCollection
	 */
	protected void sendMsg(SApi api, String msg, Collection<SRole> roleCollection) {
		for (SRole roles : roleCollection) {
			api.sendMsg(roles, (byte) 7, msg);
		}
	}

}
