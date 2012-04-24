package net.snake.gamemodel.equipment.response;


import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


/**
 * 
 * 战斗中的装备磨损
 * @author serv_dev
 *
 */
public class EquipFrayMsg10170 extends ServerResponse {
	
	
	/**
	 * 
	 * @param index 索引位置
	 * @param modelId 模型ID
	 * @param naijiu 当前耐久度
	 */
	public EquipFrayMsg10170(CharacterGoods characterGoods){
		setMsgCode(10170);
//		try {
			writeShort(characterGoods.getPosition());
			writeInt(characterGoods.getGoodmodelId());
			writeInt(characterGoods.getCurrDurability());
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
		
	}
}
