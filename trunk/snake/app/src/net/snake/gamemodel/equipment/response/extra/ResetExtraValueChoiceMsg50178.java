package net.snake.gamemodel.equipment.response.extra;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


/**
 * 
 * 弹出替换重置附加属性数值面板
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class ResetExtraValueChoiceMsg50178 extends ServerResponse {
	public ResetExtraValueChoiceMsg50178(CharacterGoods characterGoods) {
		setMsgCode(50178);
		try {
			writeShort(characterGoods.getPosition());
			writeUTF(characterGoods.getAdditionAttributeStr());
			writeUTF(characterGoods.getTmpResetExtraValueAttributeStr());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
