package net.snake.gamemodel.goods.processor;

import java.util.Collection;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.gamemodel.across.response.Acrossinfo53202;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 增加包裹或仓库的空间
 * 
 * 
 */
@MsgCodeAnn(msgcode = 53201)
public class GetAcrossServerBagProcessor53201 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		// 所在战场线(byte),所获经验(int),论剑声望(int),所获铜币(int),
		// 物品数量(short)
		// {物品ID(int),索引位置(short),快捷栏索引位置(short),数量(byte),品质(byte),绑定(byte 1绑定
		// 0不绑定),耐久(int)}
		if (!character.getMyCharacterAcrossIncomeManager().isInit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1301));
			character.getMyCharacterAcrossIncomeManager().init(false);
			return;
		}
		int line = character.getVlineserver().getLineid();
		AcrossIncome aincome = character.getMyCharacterAcrossIncomeManager().getAi();
		long exp = aincome.getExp();
		int shenwang = aincome.getShengwang();
		int copper = aincome.getCopper();
		Collection<CharacterGoods> acrossbag = character.getCharacterGoodController().getAcrossGoodsContainer().getGoodsList();
		character.sendMsg(new Acrossinfo53202(line, exp, shenwang, copper, acrossbag));
	}
}
