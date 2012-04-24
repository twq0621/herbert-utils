package net.snake.gamemodel.cache.logic;

import java.util.Map;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.activities.persistence.ActivationCardManager;
import net.snake.gamemodel.activities.persistence.CharacterActivationCardManager;
import net.snake.gamemodel.activities.persistence.DoubActivityManager;
import net.snake.gamemodel.activities.persistence.XianshiActivityManager;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.config.persistence.ConfigParamManager;
import net.snake.gamemodel.dujie.persistence.DujieAddDataTbl;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.persistence.HufazhenDataTbl;
import net.snake.gamemodel.dujie.persistence.TianshenDataTbl;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.gamemodel.goods.persistence.GoodsDCManager;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.line.persistence.LineServerDataManager;
import net.snake.gamemodel.line.persistence.ServerListManager;
import net.snake.gamemodel.map.logic.DbMapLoader;
import net.snake.gamemodel.panel.persistence.PanelDateManager;
import net.snake.gamemodel.skill.persistence.SkillUpgradeExpManage;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.serverenv.cacheupdate.GameServerCacheUpdateListener;
import net.snake.serverenv.languageserver.InitLanguageServer;
import net.snake.serverenv.security.SecurityManage;
import net.snake.serverenv.stall.OnlineStallManagerImp;
import net.snake.serverenv.vline.imp.VLineServerManagerImp;
import net.snake.shell.Options;

public class CacheDataInit {

	public static void init() throws Exception {

		// 服务器国际化数据库读取
		Map<Integer, String> lang = InitLanguageServer.init();
		Language.init(lang);
		AchieveManager.getInstance().reload();
		GameServer.serverlistmanager = ServerListManager.getInstance();
		GameServer.serverlistmanager.reload();
		// 初始化配置参数
		ConfigParamManager configParamManagerImp = new ConfigParamManager();
		configParamManagerImp.reload();
		GameServer.configParamManger = configParamManagerImp;
		GameServer.activationCardManager = ActivationCardManager.getInstance();
		GameServer.lineServerDataManager = new LineServerDataManager();
		GameServer.vlineServerManager = new VLineServerManagerImp();
		GameServer.characterActivationCardManager = CharacterActivationCardManager.getInstance();
		GameServer.securityManage = new SecurityManage();
		GameServer.onlineStallManager = OnlineStallManagerImp.getInstance();
		GameServer.doubActivityManager = DoubActivityManager.getInstance();
		
		TaskManager.getInstance().reload();
		TaskConditionManager.getInstance().reload();
		TaskRewardManager.getInstance().reload();
		// 加载副本数据
		InstanceDataProvider.getInstance().reload();


		GameServer.securityManage.reload();

		GoodsDCManager gdcManager = GoodsDCManager.getInstance();
		gdcManager.initGoodsDCMap();
		// 加载所有角色缓存
		CharacterCacheManager.getInstance().init();

		FactionManager.getInstance().initData();

		PanelDateManager.getInstance().init();

		XianshiActivityManager.getInstance().reload();

		// 初始化物品相关数据 --使用物品还有怪物
		GameServerCacheUpdateListener.getInstance().reload();
		// 场景初始化
		GameServer.shareSceneManager = DbMapLoader.loadSceneManager();
		GameServer.vlineServerManager.reload();

		GameServer.doubActivityManager.reload();

		// 初始化婚宴数据
		WedFeastManager.getInstance().init();

		// 初始化人物排行榜
		RankingManager.getInstance().init();

		// 初始化宝箱数据
		ChestGroupManager.getInstance().initdate();

		if (!Options.IsCrossServ) {
			SceneBangqiManager.getInstance().initDate();
			FactionCityManager.getInstance().init();
		}
		// 初始化GM验证
		// GmInfoManager.getInstance().init();

		// 初始化渡劫数据
		DujieAddDataTbl.getInstance().loadData();
		DujieDataTbl.getInstance().loadData();
		HufazhenDataTbl.getInstance().loadData();
		TianshenDataTbl.getInstance().loadData();
		// 兑换码初始化

		SkillUpgradeExpManage.getInstance().reload();
		GameServer.activationCardManager.reload();
		// 角色激活码初始化

		GameServer.characterActivationCardManager.init();
	}
}
