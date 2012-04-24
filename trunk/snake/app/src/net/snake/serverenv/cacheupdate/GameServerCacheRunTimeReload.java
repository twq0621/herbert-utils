package net.snake.serverenv.cacheupdate;

import net.snake.GameServer;
import net.snake.commons.dbversioncache.Log4jUpdateListener;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.activities.persistence.ActivationCardManager;
import net.snake.gamemodel.activities.persistence.ActivityDataManager;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.activities.persistence.XianshiActivityManager;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.bulletin.persistence.ScrollBulletinManager;
import net.snake.gamemodel.cache.logic.DBCacheChecker;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.persistence.EquipmentStrengthenManager;
import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gm.persistence.GmInfoManager;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.persistence.GoodsDCManager;
import net.snake.gamemodel.hero.persistence.BreakthroughManager;
import net.snake.gamemodel.hero.persistence.LiantiDataProvider;
import net.snake.gamemodel.hero.persistence.RoleRandomNameManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.gamemodel.heroext.dantian.persistence.DantianModelCacheManager;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.map.persistence.MapDateManager;
import net.snake.gamemodel.monster.persistence.MonsterLostgoodsDateManager;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.panel.persistence.PanelDateManager;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.recharge.persistence.RechargeIpListManager;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.persistence.SwShopManager;
import net.snake.gamemodel.skill.bow.persistence.BowModelCacheManager;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.skill.persistence.SkillUpgradeExpManage;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;

public class GameServerCacheRunTimeReload {

	DBCacheChecker dbCacheChecker;

	public GameServerCacheRunTimeReload() {
		dbCacheChecker = new DBCacheChecker();
	}

	public void addCacheUpdateListener() throws Exception {

		dbCacheChecker.addCacheUpdateListener(GameServerCacheUpdateListener.getInstance());
		dbCacheChecker.addCacheUpdateListener(AuthConfigManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ChannelManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ChestGroupManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ChannelRealdragonManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(BreakthroughManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(MapDateManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(InstanceDataProvider.getInstance());
		dbCacheChecker.addCacheUpdateListener(ActivityDataManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(GameServer.securityManage);
		dbCacheChecker.addCacheUpdateListener(GameServer.scriptManager);
		dbCacheChecker.addCacheUpdateListener(GameServer.vlineServerManager);
		dbCacheChecker.addCacheUpdateListener(GameServer.serverlistmanager);
		dbCacheChecker.addCacheUpdateListener(BadWordsFilter.getInstance());
		dbCacheChecker.addCacheUpdateListener(GameServer.configParamManger);
		dbCacheChecker.addCacheUpdateListener(GameServer.doubActivityManager);
		dbCacheChecker.addCacheUpdateListener(GoodmodelManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(MonsterModelManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ShopManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(LinshiActivityManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(EquipmentStrengthenManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(EquipmentPlayconfigManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(GiftPacksManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(PanelDateManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(SkillEffectManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(SkillManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(LiantiDataProvider.getInstance());
		dbCacheChecker.addCacheUpdateListener(XianshiActivityManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ScrollBulletinManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(RoleRandomNameManager.getInstance());
		// dbCacheChecker.addCacheUpdateListener(RoleUpgradeDescManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(GmInfoManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(BowModelCacheManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(ActivationCardManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(DantianModelCacheManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(SwShopManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(AcrossServerDateManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(DGWDManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(TaskManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(TaskConditionManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(TaskRewardManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(RechargeIpListManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(MonsterLostgoodsDateManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(HorseModelDataProvider.getInstance());
		dbCacheChecker.addCacheUpdateListener(NpcManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(Log4jUpdateListener.getInstance());
		dbCacheChecker.addCacheUpdateListener(GoodsDCManager.getInstance());
		dbCacheChecker.addCacheUpdateListener(SkillUpgradeExpManage.getInstance());
		dbCacheChecker.init();
	}

	public void destory() {
		dbCacheChecker.destory();
	}
}
