package net.snake.gamemodel.goods.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 古董某一收集项
 * 
 * @author dev
 * 
 */
public class DCGoodsInfo {
	/**
	 * 未完成
	 */
	public static final int STATE_NOTFINISH = 1;// 1
	/**
	 * 完成且领取奖励
	 */
	public static final int STATE_FINISH = 2;//
	/**
	 * 完成但未领取奖励
	 */
	public static final int STATE_FINISH_NO_GETAWARD = 3;//
	public int id;
	public byte state;// 状态(1 未完成,2完成且领取奖励,3完成但为领取奖励)
	// 物品id 物品数量
	public Map<Integer, Short> map = new HashMap<Integer, Short>();

	/**
	 * 索引id,状态,物品id:数量#物品id:数量
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id + "," + state);
		if (state == 1) {
			builder.append(",");
			Iterator<Integer> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				int goodsid = keys.next();
				short value = map.get(goodsid);
				builder.append(goodsid + ":");
				builder.append(value);
				if (keys.hasNext()) {
					builder.append("#");
				}
			}
		}

		return builder.toString();
	}
}
