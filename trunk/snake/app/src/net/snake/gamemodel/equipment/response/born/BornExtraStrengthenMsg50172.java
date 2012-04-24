package net.snake.gamemodel.equipment.response.born;

import net.snake.consts.Symbol;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

/**
 * 
 * 天生属性强化 开始强化返回
 * 
 * @author serv_dev
 */
public class BornExtraStrengthenMsg50172 extends ServerResponse {

	/**
	 * 成功失败标示(0失败)
	 * 
	 * @param result
	 */
	public BornExtraStrengthenMsg50172() {
		setMsgCode(50172);
		writeByte(0);
	}

	/**
	 * 天生属性强化 成功返回的消息 (1成功,{编号(byte),类型(byte),值(int),增量值(int)}*N)
	 * 
	 * @param characterGoods
	 */
	public BornExtraStrengthenMsg50172(CharacterGoods characterGoods) {
		setMsgCode(50172);
		String baseDesc = characterGoods.getBaseDesc();
		String afterBaseDesc = characterGoods.getStrengthenAfterBaseDesc();
		if (baseDesc == null || baseDesc.length() <= 0 || afterBaseDesc == null || afterBaseDesc.length() <= 0) {
			writeByte(0);
			return;
		}

		writeByte(1);
		String[] baseData = baseDesc.split(Symbol.FENHAO);
		String[] afterBaseData = afterBaseDesc.split(Symbol.FENHAO);
		writeByte(baseData.length);
		for (int i = 0; i < baseData.length; i++) {
			String[] baseValueStr = baseData[i].split(Symbol.DOUHAO);
			int propType = Integer.parseInt(baseValueStr[0]);
			int bornLv = Integer.parseInt(baseValueStr[1]);
			String[] afterBaseValueStr = afterBaseData[i].split(Symbol.DOUHAO);
			int newBornLv = Integer.parseInt(afterBaseValueStr[1]);
			writeByte(i);
			writeByte(propType);
			writeInt(newBornLv);
			writeInt(newBornLv - bornLv);
		}
	}
}
