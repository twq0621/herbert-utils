package net.snake.gamemodel.rankings.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.rankings.response.RankingResponse40026;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 
 * 查看自己在不在榜上和发送 松果领取数量
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 40025)
public class RankingTianXiaDiYiMeProcess40025 extends CharacterMsgProcessor implements IThreadProcessor {

	/**
	 * @param character
	 * @param request
	 * @throws Exception
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		CharacterTianXiaDiYi characterTianXiaDiYi = CharacterTianXiaDiYiManager.getInstance().getHaracterTianXiaDiYiMap()
				.get(character.getCharacterInitiallyId().toString() + character.getOriginalSid());
		int mingci = 0;
		int chestcount = 0;// 松果领取数量
		if (null != characterTianXiaDiYi) {
			mingci = 1;
			chestcount = 2 - characterTianXiaDiYi.getChestCount().intValue();
		}
		RankingResponse40026 response40026 = new RankingResponse40026((byte) mingci, (byte) chestcount);
		character.sendMsg(response40026);
	}

}
