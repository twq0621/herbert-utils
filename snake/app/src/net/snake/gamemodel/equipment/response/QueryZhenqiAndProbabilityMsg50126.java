package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

/**
 * 查询装备玩法所需的真气值和概率值
 * 
 * @author serv_dev
 * 
 *         类别（byte）1-7，是否需要有条件(1需要0不需要)(byte),需要多少真气（int）, 合成概率（int）,材料模型id1(int),材料模型id1个数(byte),材料模型id2(int)， 材料模型id2个数(byte),材料模型id3(int),材料模型id3个数(byte),下一级物品id（int）
 * 
 */
public class QueryZhenqiAndProbabilityMsg50126 extends ServerResponse {

	/**
	 * 失败
	 * 
	 * @param type
	 */
	public QueryZhenqiAndProbabilityMsg50126(int type) {
		setMsgCode(50126);
		writeByte(type);
		writeByte(0);
	}

	/**
	 * 重置附加属性,天生属性强化,宝石镶嵌
	 * 
	 * @param type
	 * @param zhenqi
	 * @param prob
	 * @param copper
	 * @param goodsNum
	 */
	public QueryZhenqiAndProbabilityMsg50126(int type, int zhenqi, int prob, int copper, int goodsNum) {
		setMsgCode(50126);
		writeByte(type);
		writeByte(1);
		writeInt(zhenqi);
		writeInt(prob);
		writeInt(copper);
		writeInt(goodsNum);

	}

	/**
	 * 装备升星强化
	 * 
	 * @param type
	 * @param zhenqi
	 * @param prob
	 * @param copper
	 * @param goodsNum
	 */
	public QueryZhenqiAndProbabilityMsg50126(int type, int zhenqi, int prob, int copper, int xingfu, int maxcount, int goodsNum) {
		setMsgCode(50126);
		writeByte(type);
		writeByte(1);
		writeInt(zhenqi);
		writeInt(prob);
		writeInt(copper);
		writeInt(xingfu);
		writeInt(maxcount);
		writeInt(goodsNum);

	}

	/**
	 * 宝石合成返回消息
	 * 
	 * @param type
	 * @param zhenqi
	 * @param prob
	 * @param nextGood
	 * @param copper
	 * @param maxcount
	 * @param baoshi无用
	 */
	public QueryZhenqiAndProbabilityMsg50126(int type, int zhenqi, int prob, int copper, int nextGood, int maxcount) {
		// int xingfu, int max,
		setMsgCode(50126);
		writeByte(type);
		writeByte(1);
		writeInt(zhenqi);
		writeInt(prob);
		writeInt(copper);
		// writeInt(xingfu);
		// writeInt(max);
		writeInt(nextGood);
		writeInt(maxcount);
	}

	/**
	 * 宝石拨除
	 */
	public QueryZhenqiAndProbabilityMsg50126(int type, int zhenqi, int prob, int copper, String[] gems) {
		setMsgCode(50126);
		writeByte(type);
		writeByte(1);
		writeInt(zhenqi);
		writeInt(prob);
		writeInt(copper);
		if (gems != null && gems.length > 0) {
			writeByte(gems.length);
			for (int i = 0; i < gems.length; i++) {
				writeInt(Integer.parseInt(gems[i].split(",")[0]));// 模型ID
				writeByte(i);// 位置序号
			}
		} else {
			writeByte(0);
		}
	}
}
