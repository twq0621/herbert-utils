package net.snake.gamemodel.equipment.processor.gem;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;


/**
 * 宝石拔除 位置(short),模型id(int),拔除宝石数量（byte）,（宝石在装备上的顺序（byte））n*
 */
@MsgCodeAnn(msgcode = 50197, accessLimit = 100)
public class GemTakeoutProccess50197 extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(GemTakeoutProccess50197.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			return;
		}
		short equiposition = request.getShort();
		int modelid = request.getInt();

		CharacterGoods equipment = character.getCombineController().equipmentCondition(modelid, equiposition);
		if (equipment == null) {
			logger.warn("data err position:no this equipment "+ equiposition);
			return;
		}
		byte num = request.getByte();
		byte bs[] = new byte[num];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = request.getByte();
		}
		character.getCombineController().gemTakeout(equipment, bs);
	}
}
