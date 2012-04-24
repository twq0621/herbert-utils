package net.snake.gamemodel.goods.processor;



import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.Symbol;
import net.snake.gamemodel.equipment.response.EquipmentDetailMsg50102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.response.GoodDetailFailMsg50132;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import org.apache.log4j.Logger;
/**
 * 50101
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50101)
public class GoodsDetailProcessor extends MsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(GoodsDetailProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		// 位置(short),模型id(int)
		Hero character = session.getCurrentCharacter(Hero.class);
		int characterid = request.getInt();
		short position = request.getShort();
		int horseid = request.getInt();
		Hero othercharacter = GameServer.vlineServerManager.getCharacterById(characterid);
		if (othercharacter == null) {
			character.sendMsg(new GoodDetailFailMsg50132(characterid, position, 0, horseid));
			return;
		}
		CharacterGoods characterGoods = null;
		if (horseid == 0) {
			if (position == 15) {
				characterGoods = othercharacter.getMyFriendManager().getRoleWedingManager().getQuanpeiGood();
			} else if (position == 16) {
				characterGoods = othercharacter.getMyFriendManager().getRoleWedingManager().getAddPropertyGood();
			} else if (position == 800) {
				characterGoods = othercharacter.getCombineController().getPreviewCharacterGoods();
			} else if (position > 9999) {
				characterGoods = getBaoshiByIndexInEquipment(position, othercharacter);
			} else {
				characterGoods = othercharacter.getCharacterGoodController().getGoodsByPositon(position);
			}
		} else {
			Horse horse = othercharacter.getCharacterHorseController().getHorseById(horseid);
			if (horse != null) {
				characterGoods = horse.getGoodsContainer().getGoodsByPostion(position);
			}
		}
		if (characterGoods != null) {
			if (characterGoods.isDynamic() || characterGoods.getLastDate() != null) {
				characterGoods.equipmentUpdate();
				character.sendMsg(new EquipmentDetailMsg50102(characterGoods));
			} else {
				character.sendMsg(new GoodDetailFailMsg50132(characterid, position, characterGoods.getGoodmodelId(), horseid));
			}
		} else {
			character.sendMsg(new GoodDetailFailMsg50132(characterid, position, 0, horseid));
		}
	}

	/**
	 * 获取镶嵌宝石装备上的 宝石属性
	 * 
	 * @param position
	 * @param character
	 * @return
	 */
	public CharacterGoods getBaoshiByIndexInEquipment(short position, Hero character) {
		short index = (short) (position % 100);
		short zhubeiPosition = (short) (position / 100);
		CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(zhubeiPosition);
		if (equipment == null) {
			return null;
		}
		if (equipment.getInEquipId() == null || equipment.getInEquipId().length() < 1) {
			return null;
		}
		String[] indexBaoshi = takeoutGembyEquipment(equipment, index);
		if (indexBaoshi == null) {
			logger.warn("invalidate request");
			return null;
		}
		CharacterGoods baoshi = CharacterGoods.createCharacterGoods(1, Integer.parseInt(indexBaoshi[0]), 0);

		if (indexBaoshi.length > 1) {
			for (int i = 1; i < indexBaoshi.length; i++) {
				if (i > 1) {
					baoshi.setStroneAddproperty(baoshi.getStroneAddproperty() == null ? "" : baoshi.getStroneAddproperty() + ",");
				}
				baoshi.setStroneAddproperty(baoshi.getStroneAddproperty() == null ? "" + indexBaoshi[i] : baoshi.getStroneAddproperty() + indexBaoshi[i]);
			}
		}
		baoshi.setBind(CommonUseNumber.byte1);// 只要是拨出的就是绑定的
		baoshi.equipmentUpdate();
		baoshi.setPosition(position);
		baoshi.setCharacterId(character.getId());
		return baoshi;
	}

	private String[] takeoutGembyEquipment(CharacterGoods equipment, int index) {
		String[] inequipids = equipment.getInEquipId().split(Symbol.FENHAO);
		for (int i = 0; i < inequipids.length; i++) {
			String[] stroneDetail = inequipids[i].split(",");
			if (i == index) {
				return stroneDetail;
			}
		}
		return null;
	}
}
