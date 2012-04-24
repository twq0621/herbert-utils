package net.snake.gamemodel.fight.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.GrandPrix;
import net.snake.gamemodel.fight.bean.RichPlayRule;
import net.snake.gamemodel.fight.bean.RichPlayRuleData;
import net.snake.gamemodel.fight.persistence.RichPlayRuleDataManager;
import net.snake.gamemodel.fight.response.RichPlayRulesResponse40012;
import net.snake.gamemodel.gift.bean.GrandPrixData;
import net.snake.gamemodel.gift.persistence.GrandPrixDataManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40011, accessLimit = 100)
public class RichPlayRulesProccess40011 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 丰富玩法总数byte，{名称string，简介string，是否完成byte（0否/1是）,等级short}，重要奖品总数byte，{内容strin，是否领取byte（0否/1是），图标id（int）}
		List<GrandPrixData> allGrandPrix = GrandPrixDataManager.getInstance().getAllGrandPrix();
		List<GrandPrix> grandPrixs = new ArrayList<GrandPrix>();
		for (GrandPrixData grandPrixData : allGrandPrix) {
			grandPrixData.getfGiftpackid();// 用此ID检查是否领取
			GrandPrix grandPrix = new GrandPrix(grandPrixData.getfNameI18n() + "\n" + grandPrixData.getfDescI18n(), false, grandPrixData.getfIcoid());
			grandPrixs.add(grandPrix);
		}

		List<RichPlayRuleData> allRichPlayRuleData = RichPlayRuleDataManager.getInstance().getAllRichPlayRuleData();
		List<RichPlayRule> richPlayRules = new ArrayList<RichPlayRule>();
		for (RichPlayRuleData richPlayRuleData : allRichPlayRuleData) {
			// TODO 是否完成不需要
			RichPlayRule rule = new RichPlayRule(richPlayRuleData.getfNameI18n(), richPlayRuleData.getfRuledescI18n(), false, richPlayRuleData.getfGrade());
			richPlayRules.add(rule);
		}
		character.sendMsg(new RichPlayRulesResponse40012(grandPrixs, richPlayRules));
	}

}
