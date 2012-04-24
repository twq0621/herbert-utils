package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

/**
 * 梦幻装备处理返回消息
 * 
 * @author serv_dev
 * 
 */
public class MengHuanEquipmentMsg50176 extends ServerResponse {
	public MengHuanEquipmentMsg50176(int flag, short pos, String additionAttributeStr) {
		setMsgCode(50176);
		try {
			writeByte(flag);
			writeShort(pos);
			writeUTF(additionAttributeStr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
