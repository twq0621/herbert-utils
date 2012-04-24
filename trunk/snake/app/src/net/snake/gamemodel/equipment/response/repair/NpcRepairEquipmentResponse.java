package net.snake.gamemodel.equipment.response.repair;

import java.io.IOException;

import net.snake.netio.ServerResponse;

/**
 * 修理装备 响应
 * 
 * @author dev
 */
public class NpcRepairEquipmentResponse extends ServerResponse {
	private static final int MSGCODE = 10802;

	public NpcRepairEquipmentResponse(int msgKey) {
		try {
			this.setMsgCode(MSGCODE);
			writeByte(0);
			writeInterUTF(msgKey);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public NpcRepairEquipmentResponse(short positions[], int copperMoney) {
		this.setMsgCode(MSGCODE);
		writeByte(1);
		int num = positions.length;
		writeByte(num);
		for (int i = 0; i < num; i++) {
			writeShort(positions[i]);
		}
		writeInt(copperMoney);
	}

}
