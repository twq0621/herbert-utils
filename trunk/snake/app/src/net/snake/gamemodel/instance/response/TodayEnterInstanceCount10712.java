package net.snake.gamemodel.instance.response;

import java.util.Collection;
import java.util.List;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.MyInstanceDayStatManager;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.ServerResponse;

public class TodayEnterInstanceCount10712 extends ServerResponse {
	public TodayEnterInstanceCount10712(Hero character, Collection<Scene> collection) {
		this.setMsgCode(10712);
		if (collection == null || collection.size() == 0) {
			this.writeShort(0);
		} else {
			MyInstanceDayStatManager dayManager = character.getMyInstanceDayStatManager();
			this.writeShort(collection.size());
			for (Scene scene : collection) {
				InstanceController instance = scene.getInstanceController();
				if (instance != null) {
					int freeEnterCount = dayManager.getTodayResetCount(scene.getInstanceModelId());
					int fubenlingCount = character.getMyInstanceDayStatManager().getTodayFubenLingCount(scene.getInstanceModelId());
					this.writeInt(scene.getInstanceModelId());
					this.writeInt(scene.getId());
					this.writeShort(freeEnterCount + fubenlingCount);
					int freeCount = instance.getEnterFreeMaxCount(character);
					this.writeShort(freeCount);
					if (freeEnterCount < freeCount) {
						this.writeShort(0);
					} else {
						this.writeShort(instance.getuseGoodCount(fubenlingCount));
					}

					this.writeShort(fubenlingCount + 1);
					if (instance.getInstanceData().getFubenlingLimite() <= 0) {
						this.writeByte(0);
					} else if (freeEnterCount < freeCount) {
						this.writeByte(2);
					} else if (fubenlingCount < instance.getInstanceData().getFubenlingLimite()) {
						this.writeByte(3);
					} else {
						this.writeByte(1);
					}

					List<CharacterGoods> reward = instance.getInstanceData().getRewardGoodList();
					this.writeShort(reward.size());
					for (CharacterGoods cg : reward) {
						this.writeInt(cg.getGoodmodelId());
						this.writeInt(cg.getCount());
					}
				}
			}
		}
	}
}
