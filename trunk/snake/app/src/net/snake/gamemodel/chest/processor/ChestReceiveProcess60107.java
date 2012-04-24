package net.snake.gamemodel.chest.processor;

/**
 * 

 * 宝箱领取
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Timer;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.gamemodel.chest.bean.ChestGoods;
import net.snake.gamemodel.chest.logic.ChestTools;
import net.snake.gamemodel.chest.persistence.ChestGoodsManager;
import net.snake.gamemodel.chest.response.ChestResponse60104;
import net.snake.gamemodel.chest.response.ChestResponse60108;
import net.snake.gamemodel.chest.response.ChestTreasureMap_SquirrelResponse60112;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;
/**
 * 
 */

@MsgCodeAnn(msgcode = 60107)
public class ChestReceiveProcess60107 extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(ChestReceiveProcess60107.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int typeid = request.getInt();// 宝箱类别
		byte liJinType = request.getByte();// 兑换礼金标识

		if (typeid == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 835));
			return;
		}
		if (typeid != GoodItemId.zisongguo) {
			liJinType = 0;// 不能兑换
		}
		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(23)) {
			liJinType = 0;
		}
		CharacterGoods baoxiangCg = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(typeid);

		// 不是宝箱类别 return
		if (null != baoxiangCg && baoxiangCg.getGoodModel().getKind() != 15 && baoxiangCg.getGoodModel().getKind() != 17 && baoxiangCg.getGoodModel().getKind() != 20
				&& baoxiangCg.getGoodModel().getKind() != 26) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 834));
			return;
		}
		// 验证有效期
		if (null != baoxiangCg) {
			if (baoxiangCg.isExpired()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				return;
			}

		}

		// 取得人物背包地方
		Short beibaoindex = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		// 人物物品管理者
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		if (beibaoindex == 0) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, typeid));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}

		List<Chest> chestList = character.getMyChestManager().getChestlist();
		List<Chest> chestList_New = new ArrayList<Chest>(chestList.size());
		Chest chest2 = null;
		for (Chest chest : chestList) {
			if (chest.getGoodmodelType() == typeid && chest.getType() == 0) {
				chest.setType(CommonUseNumber.byte1);
				chest.setTime(Timer.getNowTime("yyyy-MM-dd HH:mm:ss"));
				chest.setTurn(CommonUseNumber.byte1);
				if (liJinType == 1) {// 兑换礼金标识
					chest.setLijinType(liJinType);
				}
				if (chest.getId() != null) {
					character.getMyChestManager().updateChest(chest);
				}
				chest2 = chest;
			}
			chestList_New.add(chest);
		}
		character.getMyChestManager().setChestlist(chestList_New);
		if (chest2 == null) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, typeid));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 836));
			return;
		}

		ChestGoods chestGoods2 = null;

		// 查看上次离线是不是有没有领的装备
		for (ChestGoods ChestGoods : character.getMyChestManager().getChestGoodslist()) {
			if (ChestGoods.getGoodmodelType().intValue() == typeid) {
				chestGoods2 = ChestGoods;
				character.getMyChestManager().getChestGoodslist().remove(ChestGoods);
				break;
			}
		}
		CharacterGoods characterGoods3 = null;
		chestGoods2 = character.getMyChestManager().getMapChestGoods().get(chest2.getChestResourcesId());
		int goodid = Integer.valueOf(chest2.getChestResourcesId().split("_")[2]);
		if (null == chestGoods2) {
			characterGoods3 = CharacterGoods.createCharacterGood(goodid, chest2.getQuantity(), chest2.getBinding());
		} else {
			characterGoods3 = ChestGoodsManager.getInstance().chestGoodsTOCharacterGoods(chestGoods2);
			final ChestGoods chestGoods3 = chestGoods2;
			if (null != chestGoods3.getChestGoodsId()) {
				character.addToBatchUpdateTask(new Runnable() {
					@Override
					public void run() {
						ChestGoodsManager.getInstance().delChsetGoods(chestGoods3);
					}
				});
			}
		}

		Date date = new Date();
		// 添加过期时间
		if (chest2.getFailTime() != 0) {
			long time = chest2.getFailTime() * 1000;
			characterGoods3.setLastDate(new Date(date.getTime() + time));
		}

		// 添加到人背包
		if (liJinType != 1) {
			characterGoodController.addGoodsToBag(characterGoods3);
		} else {
			CharacterPropertyManager.changeLijin(character, 30);
		}
		if (liJinType == 1) {
			character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "兑换礼金成功！！！"));
			return;
		}
		// 宝箱统计
		if (typeid == GoodItemId.putongbaozang || typeid == GoodItemId.zhenqibaozhang) {
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().wabaoCount(1);
		}

		// 松鼠统计
		if (typeid == GoodItemId.lansongguo || typeid == GoodItemId.lvsongguo || typeid == GoodItemId.zisongguo) {
			ChestCount chestCount = character.getMyChestManager().getChestCount();
			if (chestCount.getChesttypeExchangeCount() == -1) {// 金松果重置验证
				if (!character.getMyChestManager().getYanZhengJinSongGuo()) {
					return;
				}
			}
			// 普通松果统计
			addChestCount(character, typeid);
			// 金松果的计算
			int countExchange = 0;

			countExchange = chestCount.getChesttypeExchangeCount();
			int exchangeCiShu = countExchange / 1500;
			if (logger.isDebugEnabled()) {
				logger.debug("当前第几轮===" + exchangeCiShu);
			}

			ChestTools.addChestExchange(countExchange, chestCount, exchangeCiShu);

		}

		// 广播
		if (baoxiangCg != null && chest2.getFullServiceAnnouncement() == 1) {

			character.getMyChestManager().gongGao(goodid, baoxiangCg, characterGoods3, chest2);
		}
		// 记录装备等级
		if (baoxiangCg != null) {
			character.getMyChestManager().setChestLog(goodid, baoxiangCg, characterGoods3, chest2);
		}
		// 宝图
		if (baoxiangCg != null && baoxiangCg.getGoodModel().getKind() == 17) {
			character.sendMsg(new ChestTreasureMap_SquirrelResponse60112(character, baoxiangCg.getGoodModel()));
			return;
		}
		// 松鼠
		if (baoxiangCg != null && baoxiangCg.getGoodModel().getKind() == 20) {
			character.sendMsg(new ChestTreasureMap_SquirrelResponse60112(character, baoxiangCg.getGoodModel()));
			return;
		}

		CharacterGoods baoxiangCg2 = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(typeid);

		if (baoxiangCg2 != null) {
			baoxiangCg2.getUseGoodAction().useGoodDoSomething(character, baoxiangCg2.getGoodmodelId(), baoxiangCg2.getPosition(),character.getSceneRef().getUseItemListeners());
			return;
		}

		character.sendMsg(new ChestResponse60108(CommonUseNumber.byte0, typeid));
	}

	public void addChestCount(Hero character, int typeid) {
		int count = 0;
		int countExchange = 0;
		if (typeid == GoodItemId.lansongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3100();
			character.getMyChestManager().getChestCount().setChesttype3100(count + 1);
			countExchange = character.getMyChestManager().getChestCount().getChesttypeExchangeCount();
			character.getMyChestManager().getChestCount().setChesttypeExchangeCount(countExchange + 1);
		} else if (typeid == GoodItemId.lvsongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3200();
			character.getMyChestManager().getChestCount().setChesttype3200(count + 1);
			countExchange = character.getMyChestManager().getChestCount().getChesttypeExchangeCount();
			character.getMyChestManager().getChestCount().setChesttypeExchangeCount(countExchange + 2);
		} else if (typeid == GoodItemId.zisongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3300();
			character.getMyChestManager().getChestCount().setChesttype3300(count + 1);
			countExchange = character.getMyChestManager().getChestCount().getChesttypeExchangeCount();
			character.getMyChestManager().getChestCount().setChesttypeExchangeCount(countExchange + 3);
		}
	}

}
