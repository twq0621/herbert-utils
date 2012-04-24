package net.snake.gamemodel.equipment.processor.gem;



import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;
/**
 * 50119 宝石材料合成
 * 
 */
@MsgCodeAnn(msgcode = 50119, accessLimit = 100)
public class MaterialCombiningProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(MaterialCombiningProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short equipPosition = request.getShort();
		int equipModel = request.getInt();
		final int cailiao1 = request.getInt();
		final int cailiao2 = request.getInt();
		final int cailiao3 = request.getInt();
		final byte xinyunNum = request.getByte();
		final CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(equipPosition);
		if (equipModel != equipment.getGoodmodelId()) {
			if (logger.isDebugEnabled()) {
				logger.debug("数据错误goodmodelid:{} position:{}");
			}

			return;
		}

		if (equipment.getGoodModel().isFuShenfu() || equipment.getGoodModel().isShelizi()) {
			return;
		}
		character.getCombineController().cailiaoCombining(equipment, cailiao1, cailiao2, cailiao3, xinyunNum);
	}
}
