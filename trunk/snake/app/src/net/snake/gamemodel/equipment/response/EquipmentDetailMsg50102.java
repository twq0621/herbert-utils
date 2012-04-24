package net.snake.gamemodel.equipment.response;

import java.util.Date;

import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev 当前耐久度(int)，最大耐久度(int), 强化程度(Str(格式说明：0,1,0,1,1,1,0,0,0)1表示实星，0表示空星)， 基础属性(Str(格式说明：属性类型，基础属性值，天生属性值，强化属性值；))，
 *         附加属性(Str(格式说明：附加属性类型，属性值；)),融合属性(Str(格式说明：宝石id,融合属性类型，属性值；))
 */
public class EquipmentDetailMsg50102 extends ServerResponse {
	public EquipmentDetailMsg50102(CharacterGoods characterGoods) {
		setMsgCode(50102);
		try {
			writeInt(characterGoods.getCharacterId());
			writeShort(characterGoods.getPosition());
			writeInt(characterGoods.getGoodmodelId());
			writeInt(characterGoods.getCurrDurability());
			writeInt(characterGoods.getMaxDurability());
			writeUTF("");
			writeUTF(characterGoods.getBaseAttributeStr());
			writeUTF(characterGoods.getAdditionAttributeStr());
			writeBoolean(characterGoods.isManxingGems());
			writeUTF(characterGoods.getRongheAttributeStr());
			EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
			int maxStoneNum = 0;
			if (equipmentPlayconfig != null) {
				maxStoneNum = equipmentPlayconfig.getMaxStoneNum();
			}
			writeByte(maxStoneNum);
			writeUTF(characterGoods.getChongqiOwnerName() == null ? "" : characterGoods.getChongqiOwnerName());
			if (characterGoods.getLastDate() == null) {
				writeDouble(0);
			} else {
				writeDouble(characterGoods.getLastDate().getTime());
			}
			writeUTF("");
			writeInt(0);
			writeUTF("");
			writeInt(0);
			writeUTF("");
			writeUTF("");
			Goodmodel gm = characterGoods.getGoodModel();
			if (gm.isEquipment()) {
				WeddingRing wr = gm.getWeddingRing();
				if (wr == null) {
					this.writeByte(0);
					// return;
				} else {
					this.writeByte(1);
					this.writeInt(wr.getSkillA());
					this.writeInt(wr.getSkillB());
					String maleName = characterGoods.getMaleName();
					String femaleName = characterGoods.getFemaleName();
					Date date = characterGoods.getCoupleDate();
					if (maleName == null) {
						maleName = "";
					}
					if (femaleName == null) {
						femaleName = "";
					}
					long dateTime = 0;
					if (date != null) {
						dateTime = date.getTime();
					}
					this.writeUTF(maleName);
					this.writeUTF(femaleName);
					this.writeDouble(dateTime);
				}
			} else {
				this.writeByte(0);
			}

			if (gm.isGemStone()) {
				writeUTF(characterGoods.getTmpStroneAttributeStr());
			} else {
				writeUTF("");
			}
			// 装备当前评分(int),装备最大评分(int),装备品级(byte),装备阶数(byte)
			writeInt(characterGoods.getGS());
			// writeInt(1000);
			writeByte(characterGoods.getPin());
			writeByte(characterGoods.getJie());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
