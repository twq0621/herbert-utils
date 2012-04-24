package net.snake.gamemodel.goods.response;

import java.util.Iterator;
import java.util.Map;

import net.snake.gamemodel.goods.bean.DCGoodsInfo;
import net.snake.netio.ServerResponse;

/**
 * 已完成数量(short){收集物品表中索引id(int)}
 * 
 * @author serv_dev
 * 
 */
public class InitCharacterGoodsDCMsg50902 extends ServerResponse {

	public InitCharacterGoodsDCMsg50902(Map<Integer, DCGoodsInfo> dcInfoMap) {
		setMsgCode(50902);
		// 收集项数量
		writeByte(dcInfoMap.size());
		Iterator<Integer> iterGoodsDc = dcInfoMap.keySet().iterator();
		while (iterGoodsDc.hasNext()) {
			DCGoodsInfo dcGoodsInfo = dcInfoMap.get(iterGoodsDc.next());
			// 索引id
			writeInt(dcGoodsInfo.id);
			// 状态
			writeByte(dcGoodsInfo.state);
			// 需要收集多少物品
			writeByte(dcGoodsInfo.map.size());
			Map<Integer, Short> map = dcGoodsInfo.map;
			Iterator<Integer> gooidsids = map.keySet().iterator();
			while (gooidsids.hasNext()) {
				int goodsid = gooidsids.next();
				short num = map.get(goodsid);
				// 物品id
				writeInt(goodsid);
				// 物品数量
				writeInt(num);
			}
		}
		// for (Iterator<Integer> iterator = goodDCs.iterator(); iterator
		// .hasNext();) {
		// Integer id = iterator.next();
		// writeInt(id);
		// }
	}
}
