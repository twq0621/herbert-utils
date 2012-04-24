package net.snake.gamemodel.equipment.response.strengthen;

import net.snake.netio.ServerResponse;

/**
 * 模型ID(int),索引位置(short,固定800)  51312
 * @author jack
 *
 */
public class EquipmentStrengThenPreviewResponse extends ServerResponse {

	public EquipmentStrengThenPreviewResponse(int modelid) {
		setMsgCode(51312);
		writeInt(modelid);
		writeShort(800);
	}

}
