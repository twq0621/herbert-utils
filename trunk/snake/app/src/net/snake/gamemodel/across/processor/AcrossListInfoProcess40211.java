/**
 * 
 */
package net.snake.gamemodel.across.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.response.AcrossListMsg40212;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 跨服服务器列表 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40211)
public class AcrossListInfoProcess40211 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (!AcrossServerDateManager.isOpenAcross) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1327));
			return;
		}
		AcrossServerDateManager.getInstance().checkAndUpdateBalance();
		Collection<AcrossServerDate> collection = AcrossServerDateManager.getInstance().getList();
		List<AcrossServerDate> list = new ArrayList<AcrossServerDate>();
		for (AcrossServerDate as : collection) {
			if (as.getEnable() == 1) {
				list.add(as);
			}
		}
		Collections.sort(list, new Comparator<AcrossServerDate>() {
			@Override
			public int compare(AcrossServerDate o1, AcrossServerDate o2) {
				if (o1.getServerId() > o2.getServerId()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		character.sendMsg(new AcrossListMsg40212(list));
	}

}
