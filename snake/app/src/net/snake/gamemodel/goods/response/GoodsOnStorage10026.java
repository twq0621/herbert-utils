package net.snake.gamemodel.goods.response;

import java.io.IOException;
import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


/**
 * @author serv_dev
 */
public class GoodsOnStorage10026 extends ServerResponse {

	public GoodsOnStorage10026(Collection<CharacterGoods> goodslist) {
		setMsgCode(10026);
		ServerResponse out = this;
		try {
			out.writeShort(goodslist.size());
			if(goodslist.size()>0){
				for(CharacterGoods goods:goodslist){
					sendCharacterGoods(goods, out);
				}				
			}
		} catch (Exception e) {
		}
	}
	//索引位置(short),模型ID(string),数量(byte),快捷栏下标(short),附加绑定(byte),镶嵌孔数量（byte）,当前宝石数量(byte){宝石模型ID(str)}*,当前耐久度(int),剩余储备数（int）,剩余冷却时间(int),传送点（string） benchild
	private void sendCharacterGoods(CharacterGoods characterGoods,ServerResponse out) throws IOException {
		//物品ID(int),索引位置(short),数量(byte)
		out.writeInt(characterGoods.getGoodmodelId());
		out.writeShort(characterGoods.getPosition());
		out.writeInt(characterGoods.getCount());
		out.writeByte(characterGoods.getPingzhiColor());
		out.writeByte(characterGoods.getBind());
		out.writeInt(characterGoods.getCurrDurability());
		
	}
	
}
