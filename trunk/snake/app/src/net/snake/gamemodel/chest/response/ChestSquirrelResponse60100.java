package net.snake.gamemodel.chest.response;

import java.io.IOException;
import java.util.List;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import org.apache.log4j.Logger;

/**
 * 
 * @author serv_dev
 *
 */
public class ChestSquirrelResponse60100 extends ServerResponse {
private static Logger logger = Logger
		.getLogger(ChestSquirrelResponse60100.class);
private ChestToolsResponse chestToolsResponse = new ChestToolsResponse();
	public ChestSquirrelResponse60100(Hero character, Goodmodel goodmodel) {
		setMsgCode(60100);

		CharacterGoods cGoods = character.getCharacterGoodController().getBagGoodsContiner()
				.getFirstGoodsByModelid(goodmodel.getId().intValue());

		// 松鼠逻辑
		sendSquirrel(character, goodmodel, cGoods);
			writeInt(goodmodel.getId());

	}

	/**
	 * @param character
	 *            当前人
	 * @param goodmodel
	 *            类别
	 * @param cGoods
	 *            物品对象
	 */
	public void sendSquirrel(Hero character, Goodmodel goodmodel, CharacterGoods cGoods) {
		int bangting = -1;
		boolean type = false;
		Chest chestNew = null;
		List<Chest> chestList = character.getMyChestManager().getChestlist();
		for (int a = chestList.size(); a > 0; a--) {
			Chest chest = chestList.get(a - 1);
			if (chest.getGoodmodelType().equals(goodmodel.getId())) {
				if (chest.getType() == 1) {
					// 扣松子
					if (cGoods.isInTrade()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoods
								.getGoodModel().getNameI18n()));
						return;
					}
					if(!character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoods.getPosition(), 1)){
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
						return;
					}
					
					bangting = cGoods.getBind();
					chestNew = character.getMyChestManager().getRepeatChestList(goodmodel.getId(),
							character.getGrade(), character.getPopsinger(), goodmodel.getId());
					break;
				} else {
					chestNew = chest;
					type = true;
					break;

				}
			}
		}

		if (character.getMyChestManager().getChestlist().size() == 0 || null == chestNew) {
			// 扣松子
			if (cGoods.isInTrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoods.getGoodModel()
						.getNameI18n()));
				return;
			}
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(
					cGoods.getPosition(), 1);
			bangting = cGoods.getBind();
			chestNew = character.getMyChestManager().getRepeatChestList(goodmodel.getId(),
					character.getGrade(), character.getPopsinger(), goodmodel.getId());
			type = false;
		}

		String chestResource[] = chestNew.getChestList().split(",");
		try {
			if (type) {
				chestToolsResponse.sendChestlist(chestResource, (byte) 2, chestNew, goodmodel.getId(),
						character, bangting, this);
			} else {
				chestToolsResponse.sendChestlist(chestResource, CommonUseNumber.byte1, chestNew, goodmodel.getId(),
						character, bangting, this);
			}
		} catch (IOException e) {
			logger.error("ChestSquirrelResponse60100(Chest, chestToolsResponse)", e); //$NON-NLS-1$
		}
	}

}
