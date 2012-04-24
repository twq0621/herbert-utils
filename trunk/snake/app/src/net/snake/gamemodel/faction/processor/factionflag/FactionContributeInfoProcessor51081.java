package net.snake.gamemodel.faction.processor.factionflag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FcByContributionComparator;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.response.FactionContributionInfoMsg51082;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51081)
public class FactionContributeInfoProcessor51081 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 859));
			return;
		}
		short pageNum = request.getShort();
		int nowPage = request.getShort();
		FactionController factionC = character.getMyFactionManager().getFactionController();
		List<Integer> maxList = getContributeMaxList(factionC);
		List<FactionCharacter> list = factionC.getFactionCharacterListByType((byte) 2);
		Collections.sort(list, new FcByContributionComparator());
		int allPage = (list.size() - 1) / pageNum + 1;
		if (nowPage < 1) {
			nowPage = 1;
		}
		if (nowPage > allPage) {
			nowPage = allPage;
		}
		int start = (nowPage - 1) * pageNum;
		List<FactionCharacter> fcList = new ArrayList<FactionCharacter>();
		for (int i = start; i < list.size(); i++) {
			FactionCharacter fc = list.get(i);
			fcList.add(fc);
			if (fcList.size() >= pageNum) {
				break;
			}
		}
		character.sendMsg(new FactionContributionInfoMsg51082(pageNum, nowPage, allPage, maxList, fcList, factionC.getFaction(), character));
	}

	public List<Integer> getContributeMaxList(FactionController factionC) {
		List<Integer> maxList = new ArrayList<Integer>();
		int copperMax = 0;
		int bangzhulingMax = 0;
		int qinglongMax = 0;
		int baihuMax = 0;
		int zhuquMax = 0;
		int xuanwuMax = 0;
		Collection<FactionCharacter> collection = factionC.getAllFactionCharacter();
		for (FactionCharacter fc : collection) {
			if (copperMax < fc.getCopper()) {
				copperMax = fc.getCopper();
			}
			if (bangzhulingMax < fc.getBangzhulingCount()) {
				bangzhulingMax = fc.getBangzhulingCount();
			}
			if (qinglongMax < fc.getQinglongCount()) {
				qinglongMax = fc.getQinglongCount();
			}
			if (baihuMax < fc.getBaihuCount()) {
				baihuMax = fc.getBaihuCount();
			}
			if (zhuquMax < fc.getZhuquCount()) {
				zhuquMax = fc.getZhuquCount();
			}
			if (xuanwuMax < fc.getXuanwuCount()) {
				xuanwuMax = fc.getXuanwuCount();
			}
		}
		maxList.add(copperMax);
		maxList.add(bangzhulingMax);
		maxList.add(qinglongMax);
		maxList.add(baihuMax);
		maxList.add(zhuquMax);
		maxList.add(xuanwuMax);
		return maxList;
	}

}
