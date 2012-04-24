package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class CombineFailMsg50150 extends ServerResponse {

	/**
	 * 1对不起该物品不能强化, 2装备已最高等级不能继续强化, 3真元不足, 4幸运晶不足,5金刚石不足, 
	 * 6对不起该物品不能强化重置, 7洗练石不足, 8对不起该物品不能附加属性重置, 9精练石不足, 
	 * 10该类型宝石不能融合, 11升阶石不足, 12合成宝石材料数量不足, 13包裹空间不足, 14铜钱不足, 15无法镶嵌宝石
	 * @param flag
	 */
	public CombineFailMsg50150(int flag) {
		setMsgCode(50150);
		writeByte(flag);
	}

	/**
	 * 1对不起该物品不能强化
	 */
	public CombineFailMsg50150() {
		setMsgCode(50150);
		writeByte(1);
	}
}
