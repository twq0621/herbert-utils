package net.snake.gamemodel.team.logic;

import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.netio.ServerResponse;

/**
 * 同意处理属性加成 无条件阵法
 * @author serv_dev
 *
 */
public class NormalFightingControl extends TeamFightingController {

	public NormalFightingControl(TeamFighting tf) {
		super(tf);
	}

	@Override
	public ServerResponse checkOpenCondition(Team t) {
		return null;
	}

	@Override
	public boolean teamNumEfectProperty() {
		return false;
	}
}
