package net.snake.gamemodel.goods.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.CharacterGoodsDC;
import net.snake.gamemodel.goods.bean.DCGoodsInfo;
import net.snake.gamemodel.goods.bean.GoodsDC;
import net.snake.gamemodel.goods.persistence.CharacterGoodsDCManager;
import net.snake.gamemodel.goods.persistence.GoodsDCManager;
import net.snake.gamemodel.goods.response.CompleteGoodsDCMsg50904;
import net.snake.gamemodel.goods.response.GetAwardGoodsDCMsg50906;
import net.snake.gamemodel.goods.response.InitCharacterGoodsDCMsg50902;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;

import org.apache.log4j.Logger;

public class CharacterGoodsDCController extends CharacterController {
	private static Logger logger = Logger.getLogger(CharacterGoodsDCController.class);

	/** 物品收集列表 */
	private Map<Integer, DCGoodsInfo> dcInfoMap = new HashMap<Integer, DCGoodsInfo>();
	private CharacterGoodsDC characterGoodsDC;

	public CharacterGoodsDCController(Hero character) {
		super(character);
	}

	public void destroy() {
		if (dcInfoMap != null) {
			dcInfoMap.clear();
			dcInfoMap = null;
		}
	}

	public void initData() {
		try {
			// 角色物品收集 t_character_goods_dc 只是通过列表的方式获取 其实 只有一个 实例
			List<CharacterGoodsDC> characterGoodsDCs = CharacterGoodsDCManager.getInstance().selecteListByCharacterId(character.getId());
			if (characterGoodsDCs == null || characterGoodsDCs.isEmpty()) {
				CharacterGoodsDC characterGoodsDC = new CharacterGoodsDC();
				characterGoodsDC.setCharacterId(character.getId());
				characterGoodsDC.setGoodsDc("");
				// characterGoodsDC.setVersion((byte) 2);
				CharacterGoodsDCManager.getInstance().asynInsertCharacterGoodsDC(character, characterGoodsDC);
				this.characterGoodsDC = characterGoodsDC;
			} else {
				this.characterGoodsDC = characterGoodsDCs.get(0);
			}

			String goodsDcStr = characterGoodsDC.getGoodsDc();
			// int version = characterGoodsDC.getVersion();
			if (goodsDcStr != null && goodsDcStr.length() > 0) {
				String[] goodsDcIds = goodsDcStr.split(";");
				for (int i = 0; i < goodsDcIds.length; i++) {
					DCGoodsInfo dcInfo = new DCGoodsInfo();
					String goodsdc = goodsDcIds[i];
					String strDcInfo[] = goodsdc.split(",");
					dcInfo.id = Integer.parseInt(strDcInfo[0]);
					dcInfo.state = Byte.parseByte(strDcInfo[1]);
					if (dcInfo.state == DCGoodsInfo.STATE_NOTFINISH) {
						String[] goodsInfo = strDcInfo[2].split("#");
						for (int k = 0; k < goodsInfo.length; k++) {
							String[] info = goodsInfo[k].split(":");
							int goodsId = Integer.parseInt(info[0]);
							short num = Short.parseShort(info[1]);
							dcInfo.map.put(goodsId, num);
						}
					}
					dcInfoMap.put(dcInfo.id, dcInfo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询
	 */
	public void queryGoodsDC() {
		character.sendMsg(new InitCharacterGoodsDCMsg50902(dcInfoMap));
	}

	/**
	 * 物品收集列表中的索引id
	 * 
	 * @param goodsDCId
	 */
	public void gcGoodsDC(int goodsDCId) {
		GoodsDC goodsDC = GoodsDCManager.getInstance().getGoodsDCById(goodsDCId);
		DCGoodsInfo dcGoodsInfo = dcInfoMap.get(goodsDCId);
		if (dcGoodsInfo != null && goodsDC != null) {
			// 这个收集项已经完成
			if (dcGoodsInfo.state == DCGoodsInfo.STATE_FINISH || dcGoodsInfo.state == DCGoodsInfo.STATE_FINISH_NO_GETAWARD) {
				character.sendMsg(new CompleteGoodsDCMsg50904(0, 20053, null));
				return;
			}
			// 接着 提交上次未收集完的
			if (dcGoodsInfo.state == DCGoodsInfo.STATE_NOTFINISH) {
				List<CharacterGoods> _cgList = validateAndGCGoodsDC(goodsDC, dcGoodsInfo);
				validateAfterAction(_cgList, dcGoodsInfo, goodsDC);
			}
		} else {
			// 不存在的收集任务
			if (goodsDC == null) {
				character.sendMsg(new CompleteGoodsDCMsg50904(0, 20054, null));
				return;
			}
			// 等级不符合
			if (goodsDC.getGrade() > character.getGrade()) {
				character.sendMsg(new CompleteGoodsDCMsg50904(0, 20055, null));
				return;
			}
			List<CharacterGoods> _cgList = validateAndGCGoodsDC(goodsDC);
			validateAfterAction(_cgList, null, goodsDC);
		}

	}

	/**
	 * 将{pointCnt}个潜能点 随机分配 到(攻击,防御,轻身,健体)属性上
	 * 
	 * @param pointCnt
	 */
	public int[] randomPoint(int pointCnt) {
		if (pointCnt == 0)
			return null;
		character.setPotential(character.getPotential() + pointCnt);

		int attackAdd = 0, defenceAdd = 0, qingshenAdd = 0, jiantiAdd = 0;

		for (int i = 0; i < pointCnt; i++) {
			int rv = GenerateProbability.randomIntValue(1, 4);
			switch (rv) {
			case 1:
				attackAdd++;
				break;
			case 2:
				defenceAdd++;
				break;
			case 3:
				qingshenAdd++;
				break;
			case 4:
				jiantiAdd++;
				break;
			default:
				attackAdd++;
				break;
			}
		}
		CharacterFormula.extraPropertyAdd(attackAdd, defenceAdd, qingshenAdd, jiantiAdd, character);
		return new int[] { attackAdd, defenceAdd, qingshenAdd, jiantiAdd };
	}

	/**
	 * 领取奖励
	 * 
	 * @param goodsDCId
	 */
	public void getAward(int goodsDCId) {
		GoodsDC goodsDC = GoodsDCManager.getInstance().getGoodsDCById(goodsDCId);
		DCGoodsInfo dcGoodsInfo = dcInfoMap.get(goodsDCId);
		// 不存在
		if (goodsDC == null || dcGoodsInfo == null) {
			character.sendMsg(new GetAwardGoodsDCMsg50906(0, 0, null, 20054));
			return;
		}
		// 未完成
		if (dcGoodsInfo.state == DCGoodsInfo.STATE_NOTFINISH) {
			character.sendMsg(new GetAwardGoodsDCMsg50906(0, 0, null, 20057));
			return;
		}
		// 等级不符合
		if (goodsDC.getGrade() > character.getGrade()) {
			character.sendMsg(new CompleteGoodsDCMsg50904(0, 20057, null));
			return;
		}
		// 不能重复领取
		if (dcGoodsInfo.state == DCGoodsInfo.STATE_FINISH) {
			character.sendMsg(new GetAwardGoodsDCMsg50906(0, 0, null, 20058));
			return;
		}
		int upAttribute[] = randomPoint(goodsDC.getPointcnt());
		dcGoodsInfo.state = DCGoodsInfo.STATE_FINISH;
		characterGoodsDC.setGoodsDc(toDBString());
		CharacterGoodsDCManager.getInstance().asynUpdateCharacterGoodsDC(character, characterGoodsDC);
		character.sendMsg(new GetAwardGoodsDCMsg50906(1, goodsDCId, upAttribute, 0));
	}

	/**
	 * 提交收集的物品 验证完后的动作
	 */
	private void validateAfterAction(List<CharacterGoods> _cgList, DCGoodsInfo dcInfo, GoodsDC goodsDC) {
		if (_cgList != null && _cgList.size() != 0) {

			if (dcInfo == null) {
				dcInfo = new DCGoodsInfo();
				dcInfo.id = goodsDC.getId();
				dcInfo.state = DCGoodsInfo.STATE_NOTFINISH;
			}
			int size = _cgList.size();
			for (int i = 0; i < size; i++) {
				CharacterGoods characterGoods = _cgList.get(i);
				int modelid = characterGoods.getGoodmodelId();
				int count = characterGoods.getCount();
				// 从包裹中清除物品
				boolean rst = character.getCharacterGoodController().deleteGoodsFromBag(modelid, count);
				if (rst) {
					Short hasNum = dcInfo.map.get(modelid);
					if (hasNum == null) {
						dcInfo.map.put(modelid, (short) count);
					} else {
						dcInfo.map.put(modelid, (short) (count + hasNum));
					}
				} else {
					logger.error("gcGoodsDC(int) - bag has err"); //$NON-NLS-1$
				}
			}
			if (validateIsFinish(dcInfo)) {
				dcInfo.state = DCGoodsInfo.STATE_FINISH_NO_GETAWARD;
			}
			dcInfoMap.put(dcInfo.id, dcInfo);
			// characterGoodsDC.setVersion((byte) 2);
			characterGoodsDC.setGoodsDc(toDBString());
			CharacterGoodsDCManager.getInstance().asynUpdateCharacterGoodsDC(character, characterGoodsDC);
			character.sendMsg(new CompleteGoodsDCMsg50904(1, 0, dcInfo));
		} else {
			// TODO 未满足收集条件
			character.sendMsg(new CompleteGoodsDCMsg50904(0, 20056, null));
		}
	}

	private boolean validateIsFinish(DCGoodsInfo dcInfo) {
		GoodsDC goodsDC = GoodsDCManager.getInstance().getGoodsDCById(dcInfo.id);
		if (goodsDC != null) {
			String targetGoodStr = goodsDC.getTargetgoods();
			// (物品id#物品名称#物品数量#地图ID_怪物ID_坐标X_坐标Y),*
			if (targetGoodStr != null && !"".equals(targetGoodStr)) {
				String[] targetGoods = targetGoodStr.split(",");
				for (int i = 0; i < targetGoods.length; i++) {
					String[] _targetGoodStr = targetGoods[i].split("#");
					if (_targetGoodStr.length < 3) {
						continue;
					}
					int goodId = Integer.parseInt(_targetGoodStr[0]);// 物品id
					int goodNum = Integer.parseInt(_targetGoodStr[1]);// 物品数量
					Short hasNum = dcInfo.map.get(goodId);
					if (hasNum == null || hasNum != goodNum) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 返回 (应该收集的物品-已经收集的物品) 结合包裹中现有的符合条件的物品
	 * 
	 * @param dcGoodsInfo
	 */
	private List<CharacterGoods> validateAndGCGoodsDC(GoodsDC goodsDC, DCGoodsInfo dcGoodsInfo) {
		// GoodsDC goodsDC = GoodsDCManager.getInstance().getGoodsDCById(
		// dcGoodsInfo.id);
		List<CharacterGoods> _cgList = new ArrayList<CharacterGoods>();
		if (goodsDC != null) {
			String targetGoodStr = goodsDC.getTargetgoods();
			// (物品id#物品名称#物品数量#地图ID_怪物ID_坐标X_坐标Y),*
			if (targetGoodStr != null && !"".equals(targetGoodStr)) {
				String[] targetGoods = targetGoodStr.split(",");
				for (int i = 0; i < targetGoods.length; i++) {
					String[] _targetGoodStr = targetGoods[i].split("#");
					if (_targetGoodStr.length < 3) {
						continue;
					}
					int goodId = Integer.parseInt(_targetGoodStr[0]);// 物品id
					int goodNum = Integer.parseInt(_targetGoodStr[1]);// 需要收集的总数量
					Short hasNum = dcGoodsInfo.map.get(goodId);// 已经收集的数量
					if (hasNum == null) {
						hasNum = 0;
					}
					int lastNum = goodNum - hasNum;// 还需要收集的数量
					if (lastNum == 0) {
						continue;
					}
					if (!character.getCharacterGoodController().isEnoughGoods(goodId, lastNum)) {
						lastNum = character.getCharacterGoodController().getBagGoodsCountByModelID(goodId);
						if (lastNum == 0) {
							continue;
						}
					}
					CharacterGoods cGoods = new CharacterGoods();
					cGoods.setGoodmodelId(goodId);
					cGoods.setCount(lastNum);
					_cgList.add(cGoods);
				}
			} else {
				logger.warn("validateAndGCGoodsDC  {goodsDCId}- goods from Collecting is null"); //$NON-NLS-1$
			}
		}
		return _cgList;
	}

	/**
	 * 返回 (应该收集的物品) 结合包裹中现有的符合条件的物品
	 * 
	 * @param goodsDCId
	 * @return
	 */
	private List<CharacterGoods> validateAndGCGoodsDC(GoodsDC goodsDC) {
		// GoodsDC goodsDC = GoodsDCManager.getInstance()
		// .getGoodsDCById(goodsDCId);
		List<CharacterGoods> _cgList = new ArrayList<CharacterGoods>();
		if (goodsDC != null) {
			String targetGoodStr = goodsDC.getTargetgoods();
			// (物品id#物品名称#物品数量#地图ID_怪物ID_坐标X_坐标Y),*
			if (targetGoodStr != null && !"".equals(targetGoodStr)) {
				String[] targetGoods = targetGoodStr.split(",");
				for (int i = 0; i < targetGoods.length; i++) {
					String[] _targetGoodStr = targetGoods[i].split("#");
					if (_targetGoodStr.length < 3) {
						continue;
					}
					int goodId = Integer.parseInt(_targetGoodStr[0]);// 物品id
					int goodNum = Integer.parseInt(_targetGoodStr[1]);// 物品名称
					if (!character.getCharacterGoodController().isEnoughGoods(goodId, goodNum)) {
						goodNum = character.getCharacterGoodController().getBagGoodsCountByModelID(goodId);
						if (goodNum == 0) {
							continue;
						}
					}
					CharacterGoods cGoods = new CharacterGoods();
					cGoods.setGoodmodelId(goodId);
					cGoods.setCount(goodNum);
					_cgList.add(cGoods);
				}
			} else {
				logger.warn("validateAndGCGoodsDC  {goodsDCId}- goods from collecting is null"); //$NON-NLS-1$
			}
		}
		return _cgList;
	}

	private String toDBString() {
		Iterator<DCGoodsInfo> infos = dcInfoMap.values().iterator();
		StringBuilder builder = new StringBuilder();
		while (infos.hasNext()) {
			DCGoodsInfo dcGoodsInfo = infos.next();
			builder.append(dcGoodsInfo.toString());
			if (infos.hasNext()) {
				builder.append(";");
			}
		}
		return builder.toString();
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return dcInfoMap.size();
	}
}
