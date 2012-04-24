package net.snake.serverenv.cacheupdate;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.activities.persistence.ActivationCardManager;
import net.snake.gamemodel.activities.persistence.ActivityDataManager;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.bulletin.persistence.ScrollBulletinManager;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.persistence.EquipmentStrengthenManager;
import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.fight.persistence.RichPlayRuleDataManager;
import net.snake.gamemodel.fight.persistence.TotemManager;
import net.snake.gamemodel.gift.persistence.GrandPrixDataManager;
import net.snake.gamemodel.gm.persistence.GmInfoManager;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.persistence.BreakthroughManager;
import net.snake.gamemodel.hero.persistence.LiantiDataProvider;
import net.snake.gamemodel.hero.persistence.RoleRandomNameManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.gamemodel.heroext.dantian.persistence.DantianModelCacheManager;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.recharge.persistence.RechargeIpListManager;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.persistence.SwShopManager;
import net.snake.gamemodel.skill.bow.persistence.BowModelCacheManager;
import net.snake.gamemodel.skill.persistence.HiddenWeaponsManager;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.skill.persistence.SkillUpgradeExpManage;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.gamemodel.team.persistence.TeamFightingManager;

/**
 * 更新物品静态相关资源（存储存在依附关系的）
 * 
 * @author serv_dev
 * 
 */
public class GameServerCacheUpdateListener implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(GameServerCacheUpdateListener.class);

	private static GameServerCacheUpdateListener instance;

	private GameServerCacheUpdateListener() {

	}

	public static GameServerCacheUpdateListener getInstance() {
		if (instance == null) {
			instance = new GameServerCacheUpdateListener();
		}
		return instance;
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "ALL";
	}

	@Override
	public void reload() {
		logger.info("start reload -- authcoinfig,badwords,horsemodel,monster...");//
		AuthConfigManager.getInstance().reload();
		ChannelManager.getInstance().reload();
		ChannelRealdragonManager.getInstance().reload();
		BreakthroughManager.getInstance().reload();
		ActivityDataManager.getInstance().reload();
		BadWordsFilter.getInstance().reload();

		LinshiActivityManager.getInstance().reload();

		LiantiDataProvider.getInstance().reload();
		ScrollBulletinManager.getInstance().reload();
		RoleRandomNameManager.getInstance().reload();
		//RoleUpgradeDescManager.getInstance().reload();
		BowModelCacheManager.getInstance().reload();
		DantianModelCacheManager.getInstance().reload();
		SwShopManager.getInstance().reload();
		AcrossServerDateManager.getInstance().reload();
		DGWDManager.getInstance().reload();
		RechargeIpListManager.getInstance().reload();

		SkillUpgradeExpManage.getInstance().reload();
		SkillEffectManager.getInstance().reload();
		SkillManager.getInstance().reload();

		HorseModelDataProvider.getInstance().reload();
		TeamFightingManager.getInstance().reload();
		NpcManager.getInstance().reload();
		TaskConditionManager.getInstance().reload();
		TaskRewardManager.getInstance().reload();
//		TaskManager.getInstance().reload();

		EquipmentStrengthenManager.getInstance().reload();
		EquipmentPlayconfigManager.getInstance().reload();
		GoodmodelManager.getInstance().reload();
		// 以下数据由goodmodelmanager来加载
		// GoodspackgeDateManager.getInstance();
		// GiftPacksManager.getInstance().reload();
		// WeddingRingManager.getInstance().reload();
		// UserGoodActionManager.getInstance().reload();
		MonsterModelManager.getInstance().reload();

		ShopManager.getInstance().reload();
		GrandPrixDataManager.getInstance().reload();

		RichPlayRuleDataManager.getInstance().reload();
		HiddenWeaponsManager.getInstance().reload();
		TotemManager.getInstance().reload();
		ActivationCardManager.getInstance().reload();
		GmInfoManager.getInstance().reload();
		logger.info("reload finish");
	}

}