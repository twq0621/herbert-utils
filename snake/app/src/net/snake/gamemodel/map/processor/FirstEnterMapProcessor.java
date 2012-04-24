package net.snake.gamemodel.map.processor;

import java.util.List;

import net.snake.ai.fight.response.ChangeSkillMsg10280;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.response.ChestResponse60122;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.response.DujieAllStateResp;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.response.GiftPacksEachLevelResponse;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.goods.response.GoodsOnBag10024;
import net.snake.gamemodel.goods.response.GoodsOnBody10040;
import net.snake.gamemodel.goods.response.GoodsOnStall10042;
import net.snake.gamemodel.goods.response.GoodsOnStorage10026;
import net.snake.gamemodel.guide.bean.CharacterNewguide;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterAttributesMsg49992;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.hero.EnterSceneRoleSelfInfo10022;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.response.HorseListResponse60018;
import net.snake.gamemodel.skill.response.CharacterSkillList10276;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastMessageResponse52248;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

import org.apache.log4j.Logger;

/**
 * 进入场景处理
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10019, accessLimit = 10000)
public class FirstEnterMapProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	private static Logger logger = Logger.getLogger(FirstEnterMapProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (character.isZeroHp()) {
			CharacterResurrect.setHuiChengFuhuoPostionInfo(character);
			character.setNowHp(character.getPropertyAdditionController().getExtraMaxHp());
			character.setNowMp(character.getPropertyAdditionController().getExtraMaxMp());
		}
		CharacterNewguide guide = character.getMyNewcomeManager().getCharacterNewguide((short) 1);
		if (guide == null) {
			startFresherGuid(character);
			return;
		}
		// 查找可用场景
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				try {
					ExchangeMapTrans.trans(character.getScene(), character.getX(), character.getY(), character);
					character.getMyFriendManager().getRoleWedingManager().onlineInit();
					character.sendMsg(new EnterSceneRoleSelfInfo10022(character, character.getX(), character.getY()));
					CharacterFormula.sendCharacterProperties(character);
					character.sendMsg(new CharacterAttributesMsg49992(character));
					// 发送物品信息
					CharacterGoodController goodscontroller = character.getCharacterGoodController();
					character.sendMsg(new GoodsOnBody10040(goodscontroller.getBodyGoodsContiner().getGoodsList()));
					character.sendMsg(new GoodsOnBag10024(goodscontroller.getBagGoodsContiner().getGoodsList()));
					character.sendMsg(new GoodsOnStorage10026(goodscontroller.getStorageGoodsContainer().getGoodsList()));
					character.sendMsg(new GoodsOnStall10042(goodscontroller.getStallGoodsContainer().getGoodsList()));
					// 发送道具冷去时间
					character.getCharacterGoodController().getCoolingTimeManager().sendCoolingMsgToClienWhenOnline();
					// 发送马的消息
					CharacterHorseController horsecontroller = character.getCharacterHorseController();
					character.sendMsg(new HorseListResponse60018(horsecontroller.getBagHorseContainer()));
					/*
					 * character.sendMsg(new HorseListResponse50030( horsecontroller.getStorageHorseContainer())); character.sendMsg(new HorseListResponse50030(
					 * horsecontroller.getStallHorseContainer()));
					 */
					// 发送设置左键为平砍技能
					// 夫妻技能\玉佩技能重加载(进入场景之后)
					character.getCharacterSkillManager().fuqiSkillReload();
					character.sendMsg(new CharacterSkillList10276(character));
					character.sendMsg(new ChangeSkillMsg10280(character.getSkillid()));
					character.getTaskController().sendInitTaskResponse();
					character.getCharacterHiddenWeaponController().sendInitMsg();
					character.getCharacterHiddenWeaponController().sendXinshoutishiMsg();
					// 经脉初始化
					// ChannelResponse50202 response = new ChannelResponse50202(character);
					// character.sendMsg(response);

					DujieCtrlor ctrlor = character.getDujieCtrlor();
					int currentLvl = ctrlor.currentTianjieNum();
					int currentStat = ctrlor.currentTianjieStat();
					int currentProc = ctrlor.currentTianjieProc();

					DujieAllStateResp resp = new DujieAllStateResp();
					int roushen = ctrlor.getHeroDujieData().getRoushen();
					resp.writeAllState(currentLvl, currentStat, roushen, currentProc, ctrlor.getHeroDujieData().getIsguard());
					character.sendMsg(resp);

					// 处理婚宴领取红包消息
					WedFeast feast = WedFeastManager.getInstance().getFeastByRoleid(character.getId());
					if (feast != null
							&& feast.isEnd()
							&& ((feast.getApplyerId().equals(character.getId()) && !feast.getIsreceive1()) || (feast.getMateId().equals(character.getId()) && !feast
									.getIsreceive2())) && feast.getGiftAmount() > 0) {
						character.sendMsg(new WedFeastMessageResponse52248(1, WedFeastManager.getNextDay(feast), feast.getFasttype(), feast.getLine()));
					}
					// 配偶婚宴提醒
					if (feast != null && !feast.isEnd() && feast.getMateId().equals(character.getId())) {
						character.sendMsg(new WedFeastMessageResponse52248(0, WedFeastManager.getNextDay(feast), feast.getFasttype(), feast.getLine()));
					}

					// 查看人物是否有宝箱的东西未领取
					int a = 0;
					for (Chest chest : character.getMyChestManager().getChestlist()) {
						if (chest.getType() == 0 && chest.getGoodmodelType() != 7004) {
							character.sendMsg(new ChestResponse60122(chest.getGoodmodelType()));
							a++;
							break;
						}
					}
					if (a == 0) {
						character.sendMsg(new ChestResponse60122(0));
					}
					List<CharacterGiftpacks> giftpacks = character.getMyCharacterGiftpacksManger().getRoleEachLevelGiftPacksManager().getEachLevelGiftPacks();
					for (int i = 0; i < giftpacks.size(); i++) {
						character.sendMsg(new GiftPacksEachLevelResponse(giftpacks.get(i),character.getPopsinger()));
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}

	private void startFresherGuid(final Hero hero) {

		hero.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				try {
					Scene scene = hero.getVlineserver().getSceneManager().getInstanceScene(30044);
					if (scene == null) {
						return;
					}
					hero.getMyCharacterInstanceManager().openInstance(scene, true);

					hero.getMyFriendManager().getRoleWedingManager().onlineInit();
					hero.sendMsg(new EnterSceneRoleSelfInfo10022(hero, hero.getX(), hero.getY()));
					CharacterFormula.sendCharacterProperties(hero);
					hero.sendMsg(new CharacterAttributesMsg49992(hero));
					// 发送物品信息
					CharacterGoodController goodscontroller = hero.getCharacterGoodController();
					hero.sendMsg(new GoodsOnBody10040(goodscontroller.getBodyGoodsContiner().getGoodsList()));
					hero.sendMsg(new GoodsOnBag10024(goodscontroller.getBagGoodsContiner().getGoodsList()));
					hero.sendMsg(new GoodsOnStorage10026(goodscontroller.getStorageGoodsContainer().getGoodsList()));
					hero.sendMsg(new GoodsOnStall10042(goodscontroller.getStallGoodsContainer().getGoodsList()));
					// 发送道具冷去时间
					hero.getCharacterGoodController().getCoolingTimeManager().sendCoolingMsgToClienWhenOnline();
					// 发送马的消息
					CharacterHorseController horsecontroller = hero.getCharacterHorseController();
					hero.sendMsg(new HorseListResponse60018(horsecontroller.getBagHorseContainer()));
					/*
					 * character.sendMsg(new HorseListResponse50030( horsecontroller.getStorageHorseContainer())); character.sendMsg(new HorseListResponse50030(
					 * horsecontroller.getStallHorseContainer()));
					 */
					// 发送设置左键为平砍技能
					// 夫妻技能\玉佩技能重加载(进入场景之后)
					hero.getCharacterSkillManager().fuqiSkillReload();
					hero.sendMsg(new CharacterSkillList10276(hero));
					hero.sendMsg(new ChangeSkillMsg10280(hero.getSkillid()));
					hero.getTaskController().sendInitTaskResponse();
					hero.getCharacterHiddenWeaponController().sendInitMsg();
					hero.getCharacterHiddenWeaponController().sendXinshoutishiMsg();
					// 经脉初始化
					// ChannelResponse50202 response = new ChannelResponse50202(character);
					// character.sendMsg(response);

					DujieCtrlor ctrlor = hero.getDujieCtrlor();
					int currentLvl = ctrlor.currentTianjieNum();
					int currentStat = ctrlor.currentTianjieStat();
					int currentProc = ctrlor.currentTianjieProc();

					DujieAllStateResp resp = new DujieAllStateResp();
					resp.writeAllState(currentLvl, currentStat, ctrlor.getHeroDujieData().getRoushen(), currentProc, ctrlor.getHeroDujieData().getIsguard());
					hero.sendMsg(resp);
					// 处理婚宴领取红包消息
					WedFeast feast = WedFeastManager.getInstance().getFeastByRoleid(hero.getId());
					if (feast != null && feast.isEnd()
							&& ((feast.getApplyerId().equals(hero.getId()) && !feast.getIsreceive1()) || (feast.getMateId().equals(hero.getId()) && !feast.getIsreceive2()))
							&& feast.getGiftAmount() > 0) {
						hero.sendMsg(new WedFeastMessageResponse52248(1, WedFeastManager.getNextDay(feast), feast.getFasttype(), feast.getLine()));
					}
					// 配偶婚宴提醒
					if (feast != null && !feast.isEnd() && feast.getMateId().equals(hero.getId())) {
						hero.sendMsg(new WedFeastMessageResponse52248(0, WedFeastManager.getNextDay(feast), feast.getFasttype(), feast.getLine()));
					}

					// 查看人物是否有宝箱的东西未领取
					int a = 0;
					for (Chest chest : hero.getMyChestManager().getChestlist()) {
						if (chest.getType() == 0 && chest.getGoodmodelType() != 7004) {
							hero.sendMsg(new ChestResponse60122(chest.getGoodmodelType()));
							a++;
							break;
						}
					}
					if (a == 0) {
						hero.sendMsg(new ChestResponse60122(0));
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

}