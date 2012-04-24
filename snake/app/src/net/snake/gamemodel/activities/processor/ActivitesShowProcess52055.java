package net.snake.gamemodel.activities.processor;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.activities.bean.Activities;
import net.snake.gamemodel.activities.persistence.ActivitiesManager;
import net.snake.gamemodel.activities.persistence.XianshiActivityController;
import net.snake.gamemodel.activities.persistence.XianshiActivityManager;
import net.snake.gamemodel.activities.response.ActivitesResponse52056;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 52055, accessLimit = 100)
public class ActivitesShowProcess52055 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int activityId = request.getInt();
		// 是否已经送过送
		XianshiActivityController ctc = XianshiActivityManager.getInstance().getActivtyListByType(activityId);
		if (ctc == null || ctc.getNowActivityList().size() < 1) {
			return;
		}
		List<Activities> listActivities = ActivitiesManager.getInstance().getActivities(character.getAccountId());
		character.sendMsg(new ActivitesResponse52056(listActivities, ctc, character));

	}

	public int getZiseEquipment(Hero character) {
		Collection<CharacterGoods> collection = character.getCharacterGoodController().getBodyGoodsList();
		int count = 0;
		for (CharacterGoods cg : collection) {
			if (cg.getPingzhiColor() >= 4 && cg.getPosition() != Position.POSTION_TESHU) {
				count++;
			}
		}
		return count;
	}

	public List<Activities> getActivitiesListByType(List<Activities> list, int type) {
		List<Activities> typeList = new ArrayList<Activities>();
		for (Activities activitie : list) {
			if (activitie.getType() == type) {
				typeList.add(activitie);
			}
		}
		return typeList;
	}
}
