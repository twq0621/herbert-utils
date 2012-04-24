package net.snake.gamemodel.equipment.processor.shelizi;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.QueryHushenfuJinJieMsg52122;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;




/**
 * 护身符进阶查询
 *
 */
@MsgCodeAnn(msgcode = 52121,accessLimit=200)
public class QueryShenLong_HushenfuJinjieProcessor extends CharacterMsgProcessor  implements IThreadProcessor{
private static Logger logger = Logger
		.getLogger(QueryShenLong_HushenfuJinjieProcessor.class);
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		int goodModelId = request.getInt();
		short position = request.getShort();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);
		if (goodModelId != characterGoods.getGoodmodelId()) {
			if (logger.isDebugEnabled()) {
				logger.debug("数据错误goodmodelid:{} position:{}");
			}
			
			return;
		}
		EquipmentPlayconfig equipmentPlayconfig = null;
		equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
		if (equipmentPlayconfig == null) {
			character.sendMsg(new QueryHushenfuJinJieMsg52122(0,0,0,0,0,0,0));
			return;
		}
		
		if (!vailidateHushenfu2(characterGoods,character)) {
			character.sendMsg(new QueryHushenfuJinJieMsg52122(0,0,0,0,0,0,0));
			return;
		}
		
//		int shenjieshiNum = 0 ;
//		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(14)) {
//			shenjieshiNum = equipmentPlayconfig.getShenjieshiNum();
//		}
//		
//		
//		character.sendMsg(new QueryHushenfuJinJieMsg52122(1,goodModelId,equipmentPlayconfig.getSheliziCopper(),shenjieshiNum,equipmentPlayconfig.getSheliziShowLv(),equipmentPlayconfig.getNextGoodmodelId(),Position.HUSHENFU_NEXT_TIPS));
	}
	
	private boolean vailidateHushenfu2(final CharacterGoods equipment,Hero character) {
		if (!equipment.getGoodModel().isEquipment()) {
			return false;
		}
		
		if (!equipment.getGoodModel().isFuShenfu()) {
			return false;
		}

		if (equipment.isInTrade()) {
			return false;
		}
		
		

		final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager
				.getInstance().getEPlayconfigByGoodsId(
						equipment.getGoodModel().getId());
		// 是null就是不能合成此物品
		if (null == equipmentPlayconfig) {
			return false;
		}

		final int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();
		
		if (!(nextGoodModelId == GoodItemId.SL_FUSHENFU)){
			return false;
		}
		
		// 验证是不是到最高级了不能再合成了
		if (nextGoodModelId == 1) {
			return false;
		}
		
		if (nextGoodModelId == GoodItemId.SL_FUSHENFU) {//要合成神龙护身符
				String shelizhi_inequipId_str = equipment.getShelizhiInEquipId();
				if (shelizhi_inequipId_str != null && !"".equals(shelizhi_inequipId_str)) {
				String[] arr = shelizhi_inequipId_str.split(";");
				List<Integer> shelizhiList = new ArrayList<Integer>();
				for (int i = 0; i < arr.length; i++) {
					if (!"".equals(arr[i])) {
						String[] _tmparr = arr[i].split(",");
						if (!"".equals(_tmparr[0])) {
							int shelizhi_goodmodel = Integer.parseInt(_tmparr[0]);
							if (shelizhi_goodmodel != GoodItemId.TEN_SHELIZI) {
								return false;
							}
							shelizhiList.add(shelizhi_goodmodel);
						}
					}
				}
				if (shelizhiList.size() < 5) {
					return false;
				}
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20079));
				return false;
			}
		}
		
		return true;
	}
}
