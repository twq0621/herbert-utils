package net.snake.gamemodel.heroext.dantian.response;

import net.snake.netio.ServerResponse;

public class DanTianQiXuanMsg10508 extends ServerResponse {
	public DanTianQiXuanMsg10508(int roleid, int res1id, int res2id) {
		setMsgCode(10508);
		writeInt(roleid);
		writeInt(res1id);
		writeInt(res2id);
	}

}
