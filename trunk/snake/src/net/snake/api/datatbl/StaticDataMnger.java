package net.snake.api.datatbl;

import java.util.HashMap;
import java.util.Map;

import net.snake.api.context.IServerContext;

public abstract class StaticDataMnger {
	
	public static final String TblName_I18N="i18nLang";
	public static final String TblName_GoodModel="goodmodel";
	public static final String TblName_GoodsPkgDataSet="goodsPkgDataSet";
	public static final String TblName_Achieve="achieve";
	public static final String TblName_WordsFilter="wordsFilter";
	public static final String TblName_ConfigParam="configParam";
	public static final String TblName_DoubleActivity="doubleActivity";
	public static final String TblName_MapData="mapData";
	public static final String TblName_TaskData="taskData";
	public static final String TblName_InstanceData="instanceData";
	public static final String TblName_TransPortData="transportData";
	public static final String TblName_BossDropData="bossDropData";
	public static final String TblName_MonsterDropData="monsterDropData";
	public static final String TblName_MonsterModelData="monsterModelData";
	public static final String TblName_SceneMonsterData="sceneMonsterData";
	public static final String TblName_GameScene="gameScene";
	public static final String TblName_WedFeastDataSet="wedFeastDataSet";
	public static final String TblName_FeastPlayConfigDataSet="feastPlayConfigDataSet";
	public static final String TblName_SkillUpgradeExpDataSet="skillUpgradeExpDataSet";
	public static final String TblName_SkillDataSet="skillDataSet";
	public static final String TblName_SkillEffectDataSet="skillEffectDataSet";
	public static final String TblName_TeamFightingDataSet="teamFightingDataSet";
	public static final String TblName_NpcDataSet="npcDataSet";
	public static final String TblName_TaskConditionDataSet="taskConditionDataSet";
	public static final String TblName_TaskRewardDataSet="taskRewardDataSet";
	public static final String TblName_EquipmentStrengthenDataSet="equipmentStrengthenDataSet";
	public static final String TblName_EquipmentPlayconfigDataSet="equipmentPlayconfigDataSet";
	public static final String TblName_ShopDataSet="shopDataSet";
	public static final String TblName_GrandPrixDataSet="grandPrixData";
	public static final String TblName_RichPlayRuleDataSet="richPlayRuleDataSet";
	public static final String TblName_HiddenWeaponsDataSet="hiddenWeaponsDataSet";
	public static final String TblName_TotemDataSet="totemDataSet";
	public static final String TblName_VirtualSubline="virtualSubline";
	public static final String TblName_OnlineStallDataSet="OnlineStallDataSet";
	public static final String TblName_GoodDCDataSet="goodDCDataSet";
	public static final String TblName_HeroDataSet="heroDataSet";
	public static final String TblName_HerosCacheDataSet="herosCacheDataSet";
	public static final String TblName_FactionHeroDataSet="factionHeroDataSet";
	public static final String TblName_FactionDataSet="factionDataSet";
	public static final String TblName_PanelDriverDataSet="panelDriverDataSet";
	public static final String TblName_XianshiActivityRewardDataSet="xianshiActivityRewardDataSet";
	public static final String TblName_XianshiActivityDataSet="xianshiActivityDataSet";
	public static final String TblName_SecurityIpDataSet="securityIpDataSet";
	public static final String TblName_HeroHiddenWeaponDataSet="heroHiddenWeaponDataSet";
	public static final String TblName_HeroRankingDataSet="heroRankingDataSet";
	public static final String TblName_InstanceRankingDataSet="instanceRankingDataSet";
	public static final String TblName_HorseHeroViewDataSet="horseHeroViewDataSet";
	public static final String TblName_HorseModelDataSet="horseModelDataSet";
	public static final String TblName_ChestGroupDataSet="chestGroupDataSet";
	public static final String TblName_SceneBangqiDataSet="sceneBangqiDataSet";
	public static final String TblName_GmInfoDataSet="gmInfoDataSet";
	public static final String TblName_ActivationCardDataSet="activationCardDataSet";
	public static final String TblName_HeroActivationCardDataSet="heroActivationCardDataSet";
	
	
	
	@SuppressWarnings("rawtypes")
	protected Map<String, IStaticDataTbl> map = new HashMap<String, IStaticDataTbl>();

	public abstract void setupDataTable(IServerContext context)throws Exception;
 
	@SuppressWarnings("unchecked")
	public <T> IStaticDataTbl<T> getStaticDataTbl(String name) {
		IStaticDataTbl<T> tbl = map.get(name);
		return tbl;
	}
	
	public abstract void reload(String name,IServerContext context)throws Exception;
}
