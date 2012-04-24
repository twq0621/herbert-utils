package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.rankings.response.RankingResponse40024;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 
 * 请求天下第一显示
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 40023)
public class RankingTianXiaDiYiListProcess40023 extends CharacterMsgProcessor implements IThreadProcessor {

	/**
	 * @param character
	 * @param request
	 * @throws Exception
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		int mingci = 0;
		int pagecount = 0;
		// character 人物角色

		List<CharacterTianXiaDiYi> listTopOne = CharacterTianXiaDiYiManager.getInstance().getLisTianXiaDiYi();

		byte yema = request.getByte();
		String keyWord = request.getString();
		if (!"".equals(keyWord) && null != listTopOne) {
			List<CharacterTianXiaDiYi> list2 = new ArrayList<CharacterTianXiaDiYi>(listTopOne.size());
			for (CharacterTianXiaDiYi CharacterTianXiaDiYi : listTopOne) {
				if (CharacterTianXiaDiYi.getCharacterName().indexOf(keyWord) != -1) {
					list2.add(CharacterTianXiaDiYi);
				}
			}
			listTopOne = list2;
		}
		if (null != listTopOne) {

			PagedListHolder<CharacterTianXiaDiYi> phHolder = new PagedListHolder<CharacterTianXiaDiYi>(listTopOne);
			phHolder.setPageSize(10);
			phHolder.setPage(yema - 1);
			listTopOne = phHolder.getPageList();
			pagecount = phHolder.getPageCount();

		}

		RankingResponse40024 response40024 = new RankingResponse40024(listTopOne, mingci, pagecount);
		character.sendMsg(response40024);

	}

}
