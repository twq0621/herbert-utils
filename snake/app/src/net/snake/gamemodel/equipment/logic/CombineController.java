package net.snake.gamemodel.equipment.logic;

import java.io.IOException;

import net.snake.consts.GameConstant;
import net.snake.consts.GoodItemId;
import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.born.BornExtraStrengthenMsg50172;
import net.snake.gamemodel.equipment.response.extra.ResetExtraPropertyMsg50110;
import net.snake.gamemodel.equipment.response.gem.GemCombiningMsg50194;
import net.snake.gamemodel.equipment.response.gem.GemTakeoutMsg50198;
import net.snake.gamemodel.equipment.response.strengthen.StrengthenUpgradeMsg50104;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import org.apache.log4j.Logger;

/**
 * 
 * 合成玩法
 * 
 * @author serv_dev
 * 
 */
public class CombineController extends CharacterController {

	private EquipmentStrengthenPlay strengthPlay;
	private EquipmentBornStrengthenPlay bornStrengthPlay;
	private EquipmentResetExtraProPlay resetExtraProPlay;
	private EquipmentGemStonePlay gemStoneProPlay;
	private GemcombiningPlay gemcombiningPlay;
	private EquipmentGemTakeoutPlay gemTakeoutPlay;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CombineController.class);
	private CharacterGoods previewCharacterGoods;// 预览强化效果用

	public CombineController(Hero character) {
		super(character);
		strengthPlay = new EquipmentStrengthenPlay();
		bornStrengthPlay = new EquipmentBornStrengthenPlay();
		resetExtraProPlay = new EquipmentResetExtraProPlay();
		gemStoneProPlay = new EquipmentGemStonePlay();
		gemcombiningPlay = new GemcombiningPlay();
		gemTakeoutPlay = new EquipmentGemTakeoutPlay();
	}

	public CharacterGoods gemCondition(int goodModelId, short pos) {
		CharacterGoods gemstone = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (gemstone == null) {
			character.sendMsg(new CombineFailMsg50150(0));
			return null;
		}
		if (goodModelId != gemstone.getGoodmodelId()) {
			logger.warn("data err goodmodelid:"+goodModelId+" position:"+pos );
			return null;
		}
		if (!gemstone.getGoodModel().isGemStone()) {
			logger.warn("param err,the second param is not gem");
			character.sendMsg(new CombineFailMsg50150(0));
			return null;
		}
		if (gemstone.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 613));
			return null;
		}
		if (gemstone.getCount() < 1) {
			logger.warn("the count of gem is 0");
			character.sendMsg(new CombineFailMsg50150(0));
			return null;
		}
		return gemstone;
	}

	/**
	 * 根据位置和goodmodel判断该道具是否为装备
	 * 
	 * @param goodModelId
	 * @param pos
	 * @return 如果是装备，则返回该装备，否则返回null
	 */
	public CharacterGoods equipmentCondition(int goodModelId, short pos) {
		CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(pos);
		if (equipment == null) {
			logger.warn("no equipment goodmodelid:"+goodModelId+" position:"+pos );
			character.sendMsg(new CombineFailMsg50150(0));
			return null;
		}

		if (goodModelId != equipment.getGoodmodelId()) {
			logger.warn("data err goodmodelid:"+goodModelId+"position:"+pos );
			return null;
		}

		if (!equipment.getGoodModel().isEquipment()) {
			logger.warn("param err ,the first param is not equipment goodmodelid:"+goodModelId+" position:"+pos );
			return null;
		}
		if (equipment.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 599));
			return null;
		}
		if (pos < Position.BagGoodsBeginPostion || pos > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			return null;
		}
		return equipment;
	}

	public boolean xingyunjingCondition(int xinyunNum, int totalXingyunNum) {
		if (xinyunNum > GameConstant.xingyunMaxNum) {
			return false;
		}
		final CharacterGoodController characterGoodController = character.getCharacterGoodController();
		final int hasXinyunNum = characterGoodController.getBagGoodsCountByModelID(GoodItemId.XINGYUNJING_ID);
		if (totalXingyunNum > hasXinyunNum) {// 检查幸运晶数量个数
			character.sendMsg(new CombineFailMsg50150(4));
			return false;
		}
		return true;
	}

	/**
	 * 天生属性加强
	 * 
	 * @param characterGoods
	 * @return
	 * @throws Exception
	 */
	public boolean bornExtraStrengthen(final CharacterGoods characterGoods) throws Exception {
		boolean tf = bornStrengthPlay.condition(character, characterGoods);
		if (tf) {
			bornStrengthPlay.play(character, characterGoods);
			return true;
		} else {
			character.sendMsg(new BornExtraStrengthenMsg50172());
			return false;
		}
	}

	/**
	 * 装备强化升星
	 * 
	 * @param characterGoods
	 *            装备id
	 * @param xinyunNum
	 *            幸运晶数量
	 */
	public boolean strengthenUpgrade(CharacterGoods characterGoods, int xinyunNum) {
		boolean tf = strengthPlay.condition(character, characterGoods) && xingyunjingCondition(xinyunNum, xinyunNum);
		if (tf) {
			strengthPlay.play(character, characterGoods, xinyunNum);
			return true;
		} else {
			character.sendMsg(new StrengthenUpgradeMsg50104(0));
			return false;
		}
	}

	/**
	 * 装备附加属性重置
	 * 
	 * @param characterGoods
	 *            装备id
	 * @param xinyunNum
	 *            幸运晶数量
	 */
	public boolean resetExtraProperty(CharacterGoods characterGoods, byte attrs[]) {
		boolean tf = resetExtraProPlay.condition(character, characterGoods, attrs);
		if (tf) {
			resetExtraProPlay.play(character, characterGoods, attrs);
			return true;
		} else {
			character.sendMsg(new ResetExtraPropertyMsg50110(0));
			return false;
		}
	}

	/**
	 * 宝石熔合装备
	 * 
	 * @param characterGoods
	 *            装备id
	 * @param xinyunNum
	 *            幸运晶数量
	 */
	public boolean gemstoneCombiningEquipment(final CharacterGoods equipment, final CharacterGoods gemstone, final int xyjNum) {
		boolean tf = gemStoneProPlay.condition(character, equipment, gemstone);
		if (tf) {
			gemStoneProPlay.play(character, equipment, gemstone, xyjNum);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 装备升阶
	 * 
	 * @param equipment
	 * @return
	 */
	public boolean createHushenfuNextCharacterGoods(final CharacterGoods equipment) {
		logger.info("装备护身符升阶...已经屏蔽");
		return false;
	}

	/**
	 * 装备升阶
	 * 
	 * @param equipment
	 * @return
	 */
	public boolean createHushenfuNextCharacterGoods2(final CharacterGoods equipment) {
		logger.info("装备护身符升阶2...已经屏蔽");
		return false;
	}

	/**
	 * 宝石材料升阶
	 * 
	 * @param equipment
	 *            原材料
	 * @param cailiao1
	 *            辅材料1
	 * @param cailiao2
	 *            辅材料2
	 * @param cailiao3
	 *            辅材料3
	 * @param xinyunNum
	 *            幸运晶
	 * @return
	 */
	public boolean cailiaoCombining(final CharacterGoods equipment, final int cailiao1, final int cailiao2, final int cailiao3, final int xinyunNum) {
		logger.info("宝石材料升阶...已经屏蔽");
		return true;
	}

	/**
	 * 宝石合成
	 * 
	 * @param gems
	 *            要合成的宝石
	 * @param containOtherBind
	 *            是否加入包裹内同类绑定的宝石
	 * @return
	 * @throws IOException
	 */
	public void gemCombining(final CharacterGoods gems, final boolean otherTag, final int xingyunNum, final int gemcount) throws IOException {
		boolean tf = gemcombiningPlay.condition(character, gems, gemcount) && xingyunjingCondition(xingyunNum, xingyunNum * gemcount);
		if (tf) {
			gemcombiningPlay.play(character, gems, gemcount, xingyunNum);
		} else {
			character.sendMsg(new GemCombiningMsg50194(null, null, 0, 0));
		}
	}

	public void gemTakeout(final CharacterGoods equipment, final byte[] baoshiPos) throws IOException {
		boolean tf = gemTakeoutPlay.condition(character, equipment, baoshiPos);
		if (tf) {
			gemTakeoutPlay.play(character, equipment, baoshiPos);
		} else {
			character.sendMsg(new GemTakeoutMsg50198(0));
		}
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

	public CharacterGoods getPreviewCharacterGoods() {
		return previewCharacterGoods;
	}

	public void setPreviewCharacterGoods(CharacterGoods previewCharacterGoods) {
		this.previewCharacterGoods = previewCharacterGoods;
	}

}
