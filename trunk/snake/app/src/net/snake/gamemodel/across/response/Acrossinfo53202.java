package net.snake.gamemodel.across.response;

import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class Acrossinfo53202 extends ServerResponse {

	public Acrossinfo53202(int line, long exp, int shenwang, int copper, Collection<CharacterGoods> acrossbag) {
		setMsgCode(53202);
		// 所在战场线(byte),所获经验(int),论剑声望(int),所获铜币(int),
		// 物品数量(short)
		// {物品ID(int),索引位置(short),快捷栏索引位置(short),数量(byte),品质(byte),绑定(byte 1绑定
		// 0不绑定),耐久(int)}
		writeByte(line);
		writeDouble(exp);
		writeInt(shenwang);
		writeInt(copper);
		writeShort(acrossbag.size());
		if (acrossbag.size() > 0) {
			for (CharacterGoods goods : acrossbag) {
				writeInt(goods.getGoodmodelId());
				writeShort(goods.getPosition());
				writeShort(goods.getQuickbarindex());
				writeShort(goods.getCount());
				writeByte(goods.getPingzhiColor());
				writeByte(goods.getBind());
				writeInt(goods.getCurrDurability());
			}
		}
	}
}
