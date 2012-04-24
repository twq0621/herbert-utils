package net.snake.gamemodel.instance.response;

import java.util.List;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class InstanceRewardMsg10726 extends ServerResponse {
	public InstanceRewardMsg10726(List<CharacterGoods> reward, int miao) {
		this.setMsgCode(10726);
		this.writeShort(reward.size());
		for (CharacterGoods cg : reward) {
			this.writeInt(cg.getGoodmodelId());
			this.writeInt(cg.getCount());
		}
		this.writeInt(miao);
	}
}
