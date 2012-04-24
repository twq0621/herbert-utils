package net.snake.gamemodel.goods.response;

import java.util.Iterator;
import java.util.Map;

import net.snake.gamemodel.goods.bean.DCGoodsInfo;
import net.snake.netio.ServerResponse;

/**
 * 完成收集成功(0失败1成功(byte)){如果是失败+失败原因(str)}
 * 
 * @author serv_dev
 * @version 1.2  修改返回 当前已提交的物品信息
 * 
 */
public class CompleteGoodsDCMsg50904 extends ServerResponse {

	public CompleteGoodsDCMsg50904(int flag, int msgkey, DCGoodsInfo dcGoodsInfo, String... vars) {
		setMsgCode(50904);
		try {
			writeByte(flag);
			if (flag == 0) {
				writeInterUTF(msgkey, vars);
			} else {
				writeInt(dcGoodsInfo.id);
				writeByte(dcGoodsInfo.state);
				Map<Integer, Short> collectGoodsMap = dcGoodsInfo.map;
				writeByte(collectGoodsMap.size());
				Iterator<Integer> gooidsids = collectGoodsMap.keySet().iterator();
				while (gooidsids.hasNext()) {
					int goodsid = gooidsids.next();
					short num = collectGoodsMap.get(goodsid);
					// 物品id
					writeInt(goodsid);
					// 物品数量
					writeInt(num);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
