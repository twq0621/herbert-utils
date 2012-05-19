package game.entity;

import lion.core.GamePlayer;

public class Hero {

	/** 角色的网络连接 */
	private volatile GamePlayer player = null;

	private Integer id;

	private Integer accountId;

	private String viewName;

	public GamePlayer getPlayer() {
		return player;
	}

	public void setPlayer(GamePlayer player) {
		this.player = player;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
