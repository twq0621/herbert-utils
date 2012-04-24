package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.rankings.response.RankingResponse50346;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 副本排行
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50345, accessLimit = 100)
public class RankingFuBenProcess50345 extends CharacterMsgProcessor implements IThreadProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		int mingci = 0;
		int pagecount = 0;
		byte yema = request.getByte();
		byte fubenid = request.getByte();
		byte isTongJiShow = request.getByte();// byte(0江湖/1同级)
		String keyWord = request.getString();
		// character 人物角色
		List<Fubenranking> list = null;
		if (isTongJiShow == 0) {
			list = RankingManager.getInstance().getMapfubenRanking().get((int) fubenid);
			RankingGetTopMeTools.setTopHeFubenranking(list);
			if (!"".equals(keyWord) && null != list) {
				list = getKeyWord(list, keyWord);
			}
		} else {
			list = RankingGetTopMeTools.reSetTongJiList(fubenid, character.getGrade());
			RankingGetTopMeTools.setTopHeFubenranking(list);
			if (!"".equals(keyWord) && null != list) {

				list = getKeyWord(list, keyWord);
			}
		}

		if (null != list) {
			mingci = RankingGetTopMeTools.getTopMeFubenranking(list, character);
			PagedListHolder<Fubenranking> phHolder = new PagedListHolder<Fubenranking>(list);
			phHolder.setPageSize(10);
			phHolder.setPage(yema - 1);
			list = phHolder.getPageList();
			pagecount = phHolder.getPageCount();
		}
		RankingResponse50346 response50346 = new RankingResponse50346(list, mingci, pagecount);
		character.sendMsg(response50346);

	}

	public List<Fubenranking> getKeyWord(List<Fubenranking> list, String keyWord) {
		List<Fubenranking> list2 = new ArrayList<Fubenranking>(list.size());
		for (Fubenranking fubenranking : list) {
			if (fubenranking.getCce().getViewName().indexOf(keyWord) != -1) {
				list2.add(fubenranking);
			}
		}
		return list2;
	}

}
