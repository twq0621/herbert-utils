package net.snake.gamemodel.goods.response;

import java.io.IOException;
import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


/**
 * @author serv_dev
 */
public class GoodsOnBag10024 extends ServerResponse {

	public GoodsOnBag10024(Collection<CharacterGoods> goodslist) {
		setMsgCode(10024);
		try {
			this.writeShort(goodslist.size());
			if(goodslist.size()>0){
				for(CharacterGoods goods:goodslist){
					sendCharacterGoods(goods);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//索引位置(short),模型ID(string),数量(byte),快捷栏下标(short),附加绑定(byte),镶嵌孔数量（byte）,当前宝石数量(byte){宝石模型ID(str)}*,当前耐久度(int),剩余储备数（int）,剩余冷却时间(int),传送点（string） benchild
	private void sendCharacterGoods(CharacterGoods characterGoods) throws IOException {
		//物品ID(int),索引位置(short),数量(byte)
		writeInt(characterGoods.getGoodmodelId());
		writeShort(characterGoods.getPosition());
		writeShort(characterGoods.getQuickbarindex());
		writeInt(characterGoods.getCount());
		writeByte(characterGoods.getPingzhiColor());
		writeByte(characterGoods.getBind());
		writeInt(characterGoods.getCurrDurability());
	}
	
}
