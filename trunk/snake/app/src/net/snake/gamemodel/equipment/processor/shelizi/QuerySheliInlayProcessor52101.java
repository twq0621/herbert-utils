package net.snake.gamemodel.equipment.processor.shelizi;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.shelizi.QuerySheliInlayMsg52102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 */
@MsgCodeAnn(msgcode = 52101,accessLimit=200)
public class QuerySheliInlayProcessor52101 extends CharacterMsgProcessor  implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		//护身符id
		int amuletid = request.getInt();
		short location =request.getShort();
		CharacterGoods amulet = character.getCharacterGoodController().getGoodsByPositon(location);
		if(amulet.getGoodModel().getPosition()!=Position.POSTION_TESHU){
			//  提示该物品不是护身符
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,50000));
			return ;
		}
		if(amulet.getGoodmodelId()!=amuletid){
			//TODO 非法请求
			return;
		}
		String inEquip = amulet.getShelizhiInEquipId();
		String[] split =new String[]{};
		if(inEquip!=null&&!inEquip.equals("")&&inEquip.split(Symbol.FENHAO).length>0){
			split=inEquip.trim().split(Symbol.FENHAO);
		}
		EquipmentPlayconfig playConfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(amulet.getGoodModel().getId());
		int maxStoneNum = playConfig.getMaxStoneNum();
		character.sendMsg(new QuerySheliInlayMsg52102(split.length,split,maxStoneNum));
//		己嵌数量(short){序号ID(byte),模型id(int),武功ID(int)},最大可嵌数量(short)
	}
}
