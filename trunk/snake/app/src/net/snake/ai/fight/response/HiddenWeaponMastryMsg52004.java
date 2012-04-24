package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 暗器熟练度
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class HiddenWeaponMastryMsg52004 extends ServerResponse {
	public HiddenWeaponMastryMsg52004(int hwid,int mastry) {
		setMsgCode(52004);
		writeInt(hwid);
		writeInt(mastry);
	}
}
