package net.snake.gamemodel.hero.response;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.netio.ServerResponse;

public class CharacterOneAttributeMsg49990 extends ServerResponse {
	/**
	 * 【1,增加攻击力2,增加防御力3,增加暴击4,增加闪避5,增加攻击速度6,增加移动速度7,增加生命上限值8,增加内力上限值
	 * 9,增加体力上限值10,增加潜能点个数11,增加等级12,增加经验13,增加坐骑经验 14,增加真气储量 15,增加战场声望 16,恢复生命值
	 * 17,恢复内力值 18,恢复体力值 19,恢复坐骑活力 20,解除全部负面状态21,双倍经验获得22,双倍真气储量获得真气当前值(int)
	 * 72,主角的月步移动距离 73改变玩家的当前的最大真元值】
	 * 
	 * @param type
	 * @param value
	 */
	public CharacterOneAttributeMsg49990(VisibleObject vo, int type, int value) {
		setMsgCode(49990);
		writeByte(vo.getSceneObjType());
		writeInt(vo.getId());
		writeByte(type);
		writeInt(value);
	}

	public CharacterOneAttributeMsg49990(VisibleObject vo, int type, long value) {
		setMsgCode(49990);
		writeByte(vo.getSceneObjType());
		writeInt(vo.getId());
		writeByte(type);
		writeDouble(value);
	}
}
