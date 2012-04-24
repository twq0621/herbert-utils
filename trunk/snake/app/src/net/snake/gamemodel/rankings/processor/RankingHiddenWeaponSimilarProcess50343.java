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
import net.snake.gamemodel.rankings.response.RankingResponse50342;
import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 暗器排行
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50343, accessLimit = 100)
public class RankingHiddenWeaponSimilarProcess50343 extends CharacterMsgProcessor implements IThreadProcessor {
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
		List<HiddenWeaponDataEntry> list = RankingManager.getInstance().getMapCharacterRankingAnQiList().get(character.getGrade());
		RankingGetTopMeTools.setTopHeHiddenWeaponDataEntry(list);
		String keyWord = request.getString();
		if (!"".equals(keyWord) && null != list) {
			List<HiddenWeaponDataEntry> list2 = new ArrayList<HiddenWeaponDataEntry>(list.size());
			for (HiddenWeaponDataEntry hiddenWeaponDataEntry : list) {
				if (hiddenWeaponDataEntry.getCharactername().indexOf(keyWord) != -1) {
					list2.add(hiddenWeaponDataEntry);
				}
			}
			list = list2;
		}
		if (null != list) {
			mingci = RankingGetTopMeTools.getTopMeHiddenWeaponDataEntry(list, character);

			PagedListHolder<HiddenWeaponDataEntry> phHolder = new PagedListHolder<HiddenWeaponDataEntry>(list);
			phHolder.setPageSize(10);
			phHolder.setPage(yema - 1);
			list = phHolder.getPageList();
			pagecount = phHolder.getPageCount();
		}
		RankingResponse50342 response50342 = new RankingResponse50342(list, mingci, pagecount);
		character.sendMsg(response50342);

	}

}
