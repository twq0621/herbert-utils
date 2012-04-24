package net.snake.gamemodel.fight.response;

import java.util.List;

import net.snake.gamemodel.fight.bean.GrandPrix;
import net.snake.gamemodel.fight.bean.RichPlayRule;
import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class RichPlayRulesResponse40012 extends ServerResponse {
	public RichPlayRulesResponse40012(List<GrandPrix> grandPrixs,List<RichPlayRule> richPlayRules) throws Exception{
		setMsgCode(40012);
//		丰富玩法总数byte，{名称string，简介string，是否完成byte（0否/1是）,等级short}，重要奖品总数byte，{内容strin，是否领取byte（0否/1是），图标id（int）}
		if (richPlayRules != null && richPlayRules.size() > 0) {
			writeByte(richPlayRules.size());
			for (RichPlayRule playRule : richPlayRules) {
				writeUTF(playRule.getName());
				writeUTF(playRule.getBrief());
				writeByte(playRule.getComplt());
				writeShort(playRule.getLvl());
			}
		} else {
			writeByte(0);
		}
		if (grandPrixs != null && grandPrixs.size() > 0) {
			writeByte(grandPrixs.size());
			for (GrandPrix gradPrix : grandPrixs) {
				writeUTF(gradPrix.getReward());
				writeByte(gradPrix.getCanTake());
				writeInt(gradPrix.getIconId());
			}
		} else {
			writeByte(0);
		}

		
	}

}
