package net.snake.gamemodel.faction.processor.factionflag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.BangqiAnchangFactionInfo;
import net.snake.gamemodel.faction.bean.BangqiPosition;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.BangqiPositionManager;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.gamemodel.faction.response.FactionFlagPositionListMsg51084;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51083)
public class FactionFlagPositionProcessor51083 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		short pageNum = request.getShort();
		int nowPage = request.getShort();
		FactionController factionC = character.getMyFactionManager().getFactionController();
		List<BangqiPosition> list = BangqiPositionManager.getInstance().getBangqiListByType(type, factionC);
		int allPage = (list.size() - 1) / pageNum + 1;
		if (nowPage < 1) {
			nowPage = 1;
		}
		if (nowPage > allPage) {
			nowPage = allPage;
		}
		int start = (nowPage - 1) * pageNum;
		List<BangqiPosition> positionList = new ArrayList<BangqiPosition>();
		for (int i = start; i < list.size(); i++) {
			BangqiPosition position = list.get(i);
			positionList.add(position);
			if (positionList.size() >= pageNum) {
				break;
			}
		}
		List<BangqiAnchangFactionInfo> bangqiAnchaList = bangqiAnchaRanking(list);
		character.sendMsg(new FactionFlagPositionListMsg51084(type, pageNum, (short) nowPage, (short) allPage, positionList, character, bangqiAnchaList));
	}

	private List<BangqiAnchangFactionInfo> bangqiAnchaRanking(List<BangqiPosition> list) {
		SceneBangqiManager sbm = SceneBangqiManager.getInstance();
		List<BangqiAnchangFactionInfo> anchaList = new ArrayList<BangqiAnchangFactionInfo>();
		for (BangqiPosition position : list) {
			SceneBangqi bangqi = sbm.getSceneBangqiByPositionId(position.getId());
			if (bangqi != null) {
				Faction faction = bangqi.getFactionController().getFaction();
				andOrUpdateAnchaInfo(anchaList, faction);
			}
		}
		Collections.sort(anchaList, new Comparator<BangqiAnchangFactionInfo>() {
			@Override
			public int compare(BangqiAnchangFactionInfo o1, BangqiAnchangFactionInfo o2) {
				if (o1.getAnchaCount() > o2.getAnchaCount()) {
					return -1;
				} else if (o1.getAnchaCount() < o2.getAnchaCount()) {
					return 1;

				} else {
					if (o1.getFactionId() < o2.getFactionId()) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		return anchaList;
	}

	private void andOrUpdateAnchaInfo(List<BangqiAnchangFactionInfo> anchaList, Faction faction) {
		int factionId = faction.getId();
		for (BangqiAnchangFactionInfo bangqiAncha : anchaList) {
			if (bangqiAncha.getFactionId() == factionId) {
				bangqiAncha.setAnchaCount(bangqiAncha.getAnchaCount() + 1);
				return;
			}
		}
		anchaList.add(new BangqiAnchangFactionInfo(faction.getId(), faction.getViewName(), 1));
	}

}
