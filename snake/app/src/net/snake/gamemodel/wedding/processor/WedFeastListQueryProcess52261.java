package net.snake.gamemodel.wedding.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52261, accessLimit = 100)
public class WedFeastListQueryProcess52261 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte page = request.getByte();
		// page-=1;
		List<WedFeast> feastList = WedFeastManager.getInstance().getFeastList();
		character.sendMsg(new WedFeastListResult52262(feastList, page));
	}

}

class WedFeastListResult52262 extends ServerResponse {
	private static int PAGE_COUNT = 10;

	/**
	 * 
	 * @param id
	 *            序号
	 * @param type
	 *            类型
	 * @param time
	 *            时间
	 * @param state
	 *            状态
	 * @param xlName
	 *            新郎名
	 * @param xnName
	 *            新娘名
	 * @throws IOException
	 */
	public WedFeastListResult52262(List<WedFeast> list, final int page) throws Exception {
		setMsgCode(52262);
		ServerResponse out = this;
		if (list == null) {
			list = new ArrayList<WedFeast>();
		}
		List<WedFeast> feastlist = new ArrayList<WedFeast>();
		for (WedFeast wedFeast : list) {
			if (!wedFeast.isEnd()) {
				feastlist.add(wedFeast);
			}
		}

		if (page == 0) {

			return;
		}

		// for (WedFeast wedFeast : list) {
		// if(wedFeast.getState().equals(WedFeastManager.FEAST_STATE_END)){
		// list.remove(wedFeast);
		// }
		// }

		int pagesum = feastlist.size() == 0 ? 1 : feastlist.size() / PAGE_COUNT;
		if (feastlist.size() % PAGE_COUNT != 0) {
			pagesum += 1;
		}
		int start = (page - 1) * PAGE_COUNT;
		int end = (page * PAGE_COUNT);
		if (start > feastlist.size()) {
			start = (pagesum - 1) * PAGE_COUNT;
		}
		if (end > feastlist.size()) {
			end = feastlist.size();
		}
		// int count=end-start;
		List<WedFeast> subList = feastlist.subList(start, end);
		out.writeByte(pagesum);
		out.writeByte(page);
		out.writeByte(subList.size());
		for (int i = 0; i < subList.size(); i++) {
			WedFeast feast = subList.get(i);
			if (!feast.isEnd()) {
				out.writeByte(feast.getTempID());
				out.writeByte(feast.getFasttype());
				out.writeDouble(WedFeastManager.getNextTime(feast));
				out.writeByte(feast.getState());
				CharacterCacheEntry a = CharacterCacheManager.getInstance().getCharacterCacheEntryById(feast.getApplyerId());
				CharacterCacheEntry b = CharacterCacheManager.getInstance().getCharacterCacheEntryById(feast.getMateId());
				if (a != null && b != null) {
					if (a.isMale()) {
						out.writeUTF(a.getViewName());
						out.writeUTF(b.getViewName());
					} else {
						out.writeUTF(b.getViewName());
						out.writeUTF(a.getViewName());
					}
				} else {
					out.writeUTF("");
					out.writeUTF("");
				}
				out.writeByte(feast.getLine());
			}
		}
	}

}
