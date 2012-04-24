package net.snake.gamemodel.goods.logic;

import org.apache.log4j.Logger;

import net.snake.ai.fight.response.CharacterFuHuo20076;
import net.snake.consts.BuffId;
import net.snake.consts.ClientConfig;
import net.snake.consts.GoodItemId;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterDieData;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.SceneManager;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.commons.script.SPropertyAdditionController;



public class CharacterResurrect {
	private static final Logger logger = Logger.getLogger(CharacterResurrect.class);
	public static int[] meiguihuaid = new int[] { GoodItemId.HongMeiGui, GoodItemId.HuangMeiGui, GoodItemId.LanMeiGui, GoodItemId.LvMeiGui, GoodItemId.BaiMeiGui,
			GoodItemId.HeiMeiGui };

	/**
	 * 查询包裹中是否存在玫瑰 0表示meiyou >0表示存在玫瑰id
	 * 
	 * @param character
	 * @return
	 */
	public static int getHaveMeiguiIdInBad(Hero character) {
		int idmeiguihu = 0;
		CharacterGoodController goodscontroller = character.getCharacterGoodController();
		for (int id : meiguihuaid) {
			if (goodscontroller.getBagGoodsCountByModelID(id) >= 1) {
				idmeiguihu = id;
				break;
			}
		}
		return idmeiguihu;
	}

	public static void yuandiResurrectProcess(Hero character) {
		try {
			yuandiResurrectProcess(character, 0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 原地复活
	 */
	public static void yuandiResurrectProcess(Hero character, int maxHp) throws Exception {
		if (character.getGrade() < 15) {
			fuhuo(character, maxHp);
			character.getDiedata().clear();
			return;
		}
		int idmeiguihu = getHaveMeiguiIdInBad(character);
		if (idmeiguihu == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 545));
			huichengFuhuo(character);
			return;
		}
		boolean b = character.getCharacterGoodController().deleteGoodsFromBag(idmeiguihu, 1);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 546));
			huichengFuhuo(character);
			return;
		}
		fuhuo(character, maxHp);
		// 玫瑰的力量为您抵消了死亡后的坐骑活力惩罚"
		CharacterDieData diedata = character.getDiedata();
		if (diedata.ridehorse != null || diedata.showhorse != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 547));
		}
		if (diedata.ridehorse != null) {
			diedata.ridehorse.addLivingness(diedata.rideHorseLostLiving);
		}
		if (diedata.showhorse != null) {
			diedata.showhorse.addLivingness(diedata.showHorseLostLiving);
		}
		diedata.clear();
	}

	/**
	 * 免费原地复活。没有死亡惩罚
	 * 
	 * @param character
	 * @param maxHp
	 * @throws Exception
	 */
	public static void yuandiFreeResurrectProcess(Hero character, int maxHp){
		fuhuo(character, maxHp);
		character.getDiedata().clear();
	}

	public static void setHuiChengFuhuoPostionInfo(Hero character) {

		SceneManager mapSceneManager = character.getVlineserver().getSceneManager();
		if (character.getSceneRef() == null) {
			int mapId = character.getScene();
			if (logger.isDebugEnabled()) {
				logger.debug("nowmapid:"+mapId);
			}

			Scene scene = mapSceneManager.getScene(mapId);
			if (scene == null) {
				scene = mapSceneManager.getScene(ClientConfig.Scene_Xianjing);
			}
			character.setSceneRef(scene);
		}
		int relivetoSceneid = character.getSceneRef().getReliveSceneId();
//		if (logger.isDebugEnabled()) {
//			logger.debug("relivetosceneid:{}", relivetoSceneid);
//		}

		Scene reliveScene = mapSceneManager.getScene(relivetoSceneid);
		if (reliveScene != null) {
			short[] points = reliveScene.getRandomPoint(reliveScene.getReliveX(), reliveScene.getReliveY(), 9);
			short x = points[0];
			short y = points[1];
			character.setX(x);
			character.setY(y);
			character.setScene(reliveScene.getId());
		} else {
			logger.error("use default relive point");
			character.setX(ClientConfig.default_fuhuo_x);
			character.setY(ClientConfig.default_fuhuo_y);
			character.setScene(ClientConfig.default_fuhuo_mapid);
		}
	}

	/**
	 * 回城复活,但实现上,要先复活再回城
	 */
	public static void huichengFuhuo(Hero character)  {
		fuhuo(character);
		setHuiChengFuhuoPostionInfo(character);
		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
				character.getCharacterHorseController().unRide();
			}
			character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
		}
		ExchangeMapTrans.trans(character.getScene(), character.getX(), character.getY(), character);
		character.getDiedata().clear();

	}

	public static void fuhuo(Hero character) {
		fuhuo(character, 0);
	}

	public static void fuhuo(Hero character, int maxHp)  {
		character.getFuhuotimer().shutdown();
		character.setStandState();
//		character.setObjectState(VisibleObjectState.Idle);
		if (character.getDiedata().showhorse != null && character.getDiedata().showhorse.isAbleRide()) {
			character.getDiedata().showhorse.changeStatus(HorseStateConsts.SHOW);
			character.getDiedata().showhorse.addSHJM();
		}
		SPropertyAdditionController pc = character.getPropertyAdditionController();
		CharacterPropertyManager.changeNowHp(character, maxHp > 0 ? maxHp : pc.getExtraMaxHp());
		CharacterPropertyManager.changeNowMp(character, pc.getExtraMaxMp());
		//CharacterPropertyManager.changeNowSp(character, pc.getExtraMaxSp());

		// 复活
		character.getEyeShotManager().sendMsg(new CharacterFuHuo20076(character));
		// 打开自动回血
		character.getCharacterOnHoorController().startAutoRevertHpMp();
		if ((character.isDieByRole()) && character.getSceneRef().isPkProtect()) {
			if (character.getPkModel() == 0) {
				if (!character.getMyCharacterVipManger().isFeibaohuState()) {
					SkillEffect effect = SkillEffectManager.getInstance().getCacheSkillEffect().get(BuffId.PkProtectBuff);
					EffectInfo effectInfo = new EffectInfo(effect);
					effectInfo.setAffecter(character);
					effectInfo.setAttacker(character);
					character.getEffectController().addEffect(effectInfo);
				}
			}
		}
		character.setTarget(null);
		// character.setWhoKillMe(null);
		character.setDieByRole(false);
	}
}
