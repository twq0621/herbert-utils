package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.rankings.response.RankingResponse50314;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 战场声望同级排行
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50315, accessLimit = 100)
public class RankingPrestigeSimilarProcess50315 extends CharacterMsgProcessor implements IThreadProcessor {
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
		// character 人物角色
		List<CharacterRanking> list = RankingManager.getInstance().getMapcharacterRankingZhanChangList().get((int) character.getGrade());
		String keyWord = request.getString();
		RankingGetTopMeTools.setTopHeCharacterRanking(list);
		if (!"".equals(keyWord) && null != list) {
			List<CharacterRanking> list2 = new ArrayList<CharacterRanking>(list.size());
			for (CharacterRanking characterRanking : list) {
				if (characterRanking.getName().indexOf(keyWord) != -1) {
					list2.add(characterRanking);
				}
			}
			list = list2;
		}
		if (null != list) {
			mingci = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			PagedListHolder<CharacterRanking> phHolder = new PagedListHolder<CharacterRanking>(list);
			phHolder.setPageSize(10);
			phHolder.setPage(yema - 1);
			list = phHolder.getPageList();
			pagecount = phHolder.getPageCount();

		}
		RankingResponse50314 response50314 = new RankingResponse50314(list, mingci, pagecount);

		character.sendMsg(response50314);
	}

}