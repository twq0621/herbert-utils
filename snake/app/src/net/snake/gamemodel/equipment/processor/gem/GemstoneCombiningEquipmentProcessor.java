package net.snake.gamemodel.equipment.processor.gem;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;

/**
 * 
 * 宝石融合装备 50111 装备位置(short),装备模型id(int)，宝石位置(short),宝石模型id(int)，幸运晶个数(byte)
 * 
 */
@MsgCodeAnn(msgcode = 50111, accessLimit = 100)
public class GemstoneCombiningEquipmentProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(GemstoneCombiningEquipmentProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short equipPosition = request.getShort();
		int equipModel = request.getInt();
		short gemsPosition = request.getShort();
		int gemsModel = request.getInt();
		final byte xinyunNum = request.getByte();
		
		CharacterGoods equipment = character.getCombineController().equipmentCondition(equipModel, equipPosition);
		if (equipment == null) {
			logger.warn("data err position:no equipment "+ equipPosition);
			return;
		}
		
		 CharacterGoods gemstone = character.getCombineController().gemCondition(gemsModel, gemsPosition);
		if (gemstone == null) {
			return;
		}
		
		character.getCombineController().gemstoneCombiningEquipment(equipment, gemstone, xinyunNum);

	}

}
