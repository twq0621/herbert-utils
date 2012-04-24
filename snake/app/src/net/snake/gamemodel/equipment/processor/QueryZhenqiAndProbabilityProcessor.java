package net.snake.gamemodel.equipment.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GameConstant;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.bean.EquipmentStrengthen;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.persistence.EquipmentStrengthenManager;
import net.snake.gamemodel.equipment.response.QueryZhenqiAndProbabilityMsg50126;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 
 * 查询装备玩法所需的真气值和概率值 50125
 * 
 */

@MsgCodeAnn(msgcode = 50125, accessLimit = 20)
public class QueryZhenqiAndProbabilityProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(QueryZhenqiAndProbabilityProcessor.class);

	@Override
	public void process(final Hero hero, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short position = request.getShort();
		int goodModelId = request.getInt();
		int type = request.getByte();
		CharacterGoods characterGoods = hero.getCharacterGoodController().getGoodsByPositon(position);
		if (characterGoods == null || goodModelId != characterGoods.getGoodmodelId()) {
			logger.debug("数据错误goodmodelid:{} position:{}");
			return;
		}
		EquipmentPlayconfig equipmentPlayconfig = null;
		// 1.装备升星强化,2重置附加属性,3天生属性强化,4宝石合成,5装备宝石镶嵌,6装备里宝石拔除
		// 1,4,5
		// 类型，真气，概率，铜币，
		QueryZhenqiAndProbabilityMsg50126 msg = null;
		switch (type) {
		case 1:// 装备升星强化
			int nextGrade = characterGoods.getStrengthenCount() + 1;
			if (nextGrade > GameConstant.StrengthenCountLimit) {
				// 装备已最高等级不能继续强化
				return;
			}
			EquipmentStrengthen euipmentStrengthen = EquipmentStrengthenManager.getInstance().getEuipmentStrengthen(characterGoods.getGoodModel().getId(),
					characterGoods.getStrengthenCount());
			if (euipmentStrengthen == null) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			// HeroXingfu heroxingfu = hero.getMyHeroXingfuController().getHeroXingfu(type, goodModelId);
			msg = new QueryZhenqiAndProbabilityMsg50126(type, euipmentStrengthen.getZhenqi(), euipmentStrengthen.getProbability(), euipmentStrengthen.getCopper(),
					characterGoods.getBornStrengthenCnt(), euipmentStrengthen.getMaxCount(), euipmentStrengthen.getJingangshiNum());
			break;
		case 2:// 重置附加属性
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
			if (equipmentPlayconfig == null) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			msg = new QueryZhenqiAndProbabilityMsg50126(type, equipmentPlayconfig.getResetAddZhenqi(), 0, equipmentPlayconfig.getResetAddCopper(),
					equipmentPlayconfig.getJinglianNum());
			break;

		case 3:// 天生属性强化
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
			if (equipmentPlayconfig == null) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			msg = new QueryZhenqiAndProbabilityMsg50126(type, equipmentPlayconfig.getBornStrengthenZhenqi(), 0, equipmentPlayconfig.getBornStrengthenCopper(),
					equipmentPlayconfig.getBornStrengthenCailiaoNum());
			break;
		case 4: // 宝石合成
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
			if (equipmentPlayconfig == null) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			if (equipmentPlayconfig.getNextGoodmodelId() == 0) {
				hero.sendMsg(new PrompMsg(848));
				break;
			}
			Integer count = hero.getCharacterGoodController().getBagGoodsCountByModelID(characterGoods.getGoodmodelId());// characterGoods.getCount();
			int item = 0;
			if (equipmentPlayconfig.getMergeSum() != 0) {
				item = count / equipmentPlayconfig.getMergeSum();
			}

			int zhenqi = equipmentPlayconfig.getCailiaoCombineZhenqi();

			int zhenqicount = hero.getZhenqi() / zhenqi;
			if (zhenqicount < item) {
				item = zhenqicount;
			}
			int copper = equipmentPlayconfig.getCailiaoCombineCopper();
			int coppercount = hero.getCopper() / copper;
			if (coppercount < item) {
				item = coppercount;
			}
			int maxcount = item;
			int hechengcishu = request.getInt();
			if (item > hechengcishu && hechengcishu > 0) {
				item = hechengcishu;
			}
			// HeroXingfu heroxingfu2 = hero.getMyHeroXingfuController().getHeroXingfu(type, goodModelId);
			msg = new QueryZhenqiAndProbabilityMsg50126(type, zhenqi * item, equipmentPlayconfig.getCailiaoCombineProbability(), copper * item,
					equipmentPlayconfig.getNextGoodmodelId(), maxcount);
			break;
		case 5: // 宝石镶嵌
			int gemsId = request.getInt();
			if (characterGoods.getInEquipId() != null && characterGoods.getInEquipId().contains("")) {
				String[] split = characterGoods.getInEquipId().split(Symbol.FENHAO);
				if (split.length == 5) {
					hero.sendMsg(new PrompMsg(849));
					break;
				}
			}
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gemsId);
			if (equipmentPlayconfig == null) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			// HeroXingfu heroxingfu3 = hero.getMyHeroXingfuController().getHeroXingfu(type, goodModelId);
			msg = new QueryZhenqiAndProbabilityMsg50126(type, equipmentPlayconfig.getRongheZhenqi(), equipmentPlayconfig.getRongheProbability(),
					equipmentPlayconfig.getRongheCopper(), 0);
			break;
		case 6:// 装备里宝石拔除
			byte bachuNum = request.getByte();
			int bachuZhenqi = 0;
			int bachuCcopper = 0;
			String[] gems = null;
			if (characterGoods.getInEquipId().contains(";")) {
				gems = characterGoods.getInEquipId().split(Symbol.FENHAO);
			} else if (characterGoods.getInEquipId() != null && !characterGoods.getInEquipId().equals("")) {
				gems = new String[] { characterGoods.getInEquipId() };
			}
			if (gems == null || bachuNum > gems.length) {
				msg = new QueryZhenqiAndProbabilityMsg50126(type);
				break;
			}
			for (int i = 0; i < bachuNum; i++) {
				byte weizhi = request.getByte();
				int bachuGemsId = Integer.parseInt(gems[weizhi]);
				equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(bachuGemsId);
				if (equipmentPlayconfig == null) {
					msg = new QueryZhenqiAndProbabilityMsg50126(type);
					break;
				}
				bachuZhenqi += equipmentPlayconfig.getBachuZhenqi();
				bachuCcopper += equipmentPlayconfig.getBachuCopper();
			}
			if (bachuNum != 0) {
				gems = null;
			}
			msg = new QueryZhenqiAndProbabilityMsg50126(type, bachuZhenqi, 0, bachuCcopper, gems);
			break;
		default:
			break;
		}
		if (msg != null) {
			hero.sendMsg(msg);
		}
	}
}
