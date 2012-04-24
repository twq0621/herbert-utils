package net.snake.gamemodel.equipment.response.extra;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

/**
 * 
 * 弹出替换添加一条附加属性面板 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class AddOneExtraChoiceMsg50168 extends ServerResponse {
	public AddOneExtraChoiceMsg50168(CharacterGoods characterGoods) {
		setMsgCode(50168);
		try {
			writeShort(characterGoods.getPosition());
			writeUTF(characterGoods.getAdditionAttributeStr());
			writeUTF(characterGoods.getTmpAddOneAttributeStr());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
