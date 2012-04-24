package net.snake.gamemodel.goods.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterPropcd;
import net.snake.netio.ServerResponse;

public class EnterSceneCoolingTimeMsg50140 extends ServerResponse {
	public EnterSceneCoolingTimeMsg50140(List<CharacterPropcd> commonCdList, List<CharacterPropcd> goodCdList) {
		this.setMsgCode(50140);
		long now = System.currentTimeMillis();
		this.writeShort(commonCdList.size());
		for (CharacterPropcd cp : commonCdList) {
			this.writeShort(cp.getCdId());
			int time = (int) (cp.getUseTime() - now);
			this.writeInt(cp.getTotalTime());
			this.writeInt(time);
		}
		this.writeShort(goodCdList.size());
		for (CharacterPropcd cp : goodCdList) {
			this.writeInt(cp.getCdId());
			int time = (int) (cp.getUseTime() - now);
			this.writeInt(time);
		}
	}
}
