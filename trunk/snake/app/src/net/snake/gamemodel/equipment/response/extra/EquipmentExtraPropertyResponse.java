package net.snake.gamemodel.equipment.response.extra;

import net.snake.consts.Symbol;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

/**
 * 取当前装备的附加属性
 * 
 * @author jack
 * 
 */
public class EquipmentExtraPropertyResponse extends ServerResponse {

	// 数量(byte){编号(byte),类型(byte),值(int)}*N
	public void setMsg(CharacterGoods characterGoods) {
		setMsgCode(51302);
		String attStr = characterGoods.getAdditionAttributeStr();
		if (attStr == null || "".equals(attStr)) {
			this.writeByte(0);
			return;
		}
		String[] atts = attStr.split(Symbol.FENHAO);
		int size = atts.length;
		this.writeByte(size);
		for (int i = 0; i < size; i++) {
			String att = atts[i];
			String pros[] = att.split(Symbol.DOUHAO);
			this.writeByte(i);
			this.writeByte(Integer.parseInt(pros[0]));
			this.writeInt(Integer.parseInt(pros[1]));
		}
	}
}
