package net.snake.gamemodel.equipment.response.gem;

import java.io.IOException;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class GemCombiningMsg50194 extends ServerResponse {

	public GemCombiningMsg50194(CharacterGoods succgood, CharacterGoods befogood, int shenyumodel, int shenyusum) throws IOException {
		byte flag = 0;
		int nextmodelid = 0;
		short nextsum = 0;
		int befomodelid = 0;
		short befosum = 0;
		short nextindex = 0;
		if (succgood != null && succgood.getCount() > 0) {
			flag = 1;
			nextmodelid = succgood.getGoodmodelId();
			nextsum = succgood.getCount().shortValue();
			nextindex = succgood.getPosition();
		}
		if (befogood != null && befogood.getCount() > 0) {
			befomodelid = befogood.getGoodmodelId();
			befosum = befogood.getCount().shortValue();
		}
		setResult(flag, nextmodelid, nextsum, befomodelid, befosum, shenyumodel, (short) shenyusum, nextindex);
	}

	public GemCombiningMsg50194(int nextmodelid, int nextsum, int befomodelid, int befosum, int surplusmodelid, int surpsum, int nextindex) {
		int flag = nextsum > 0 ? 1 : 0;
		try {
			setResult((byte) flag, nextmodelid, (short) nextsum, befomodelid, (short) befosum, surplusmodelid, (short) surpsum, (short) nextindex);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void setResult(final byte flag, final int nextmodelid, final short nextsum, final int befomodelid, final short befosum, final int surplusmodelid, final short surpsum,
			final short nextindex) throws IOException {
		setMsgCode(50194);
		// 成功失败标示，
		writeByte(flag);
		// 成功升级的宝石模型id（int），
		writeInt(nextmodelid);
		// 数量（short），
		writeShort(nextsum);
		// 降级的宝石模型id（int），
		writeInt(befomodelid);
		// 数量（short），
		writeShort(befosum);
		// 剩余的宝石模型id（int），
		writeInt(surplusmodelid);
		// 数量（short），
		writeShort(surpsum);
		// 成功宝石放入包裹的索引位置（short）
		writeShort(nextindex);
	}

}
