package net.snake.gamemodel.goods.logic.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.gamemodel.team.persistence.TeamFightingManager;

/**
 * 
 * 物品使用行为管理器
 * 
 */
public class UserGoodActionManager implements CacheUpdateListener {
	private static UserGoodActionManager instance;
	public Map<Integer, UseGoodAction> map = new HashMap<Integer, UseGoodAction>();

	public static UserGoodActionManager getInstance() {
		if (instance == null) {
			instance = new UserGoodActionManager();
		}
		return instance;
	}

	private UserGoodActionManager() {
		reload();
	}

	public void putUserGoodAction(int goodId, UseGoodAction uga) {
		map.put(goodId, uga);
	}

	public UseGoodAction getUserGoodAction(int goodId) {
		return map.get(goodId);
	}

	/**
	 * 初始化使用物品
	 * 
	 * @param goodmodellist
	 */
	public void initAllUserGoodAction(Collection<Goodmodel> goodmodellist) {
		Collection<TeamFightingController> tfCollection = TeamFightingManager.getInstance().getAllTeamFightingControllerCollection();
		for (TeamFightingController tfc : tfCollection) {
			UseGoodAction uga = new CharacterUseZhenfaGood(tfc);
			putUserGoodAction(tfc.getTf().getLearnId(), uga);
			Goodmodel gm = GoodmodelManager.getInstance().get(tfc.getTf().getLearnId());
			if (gm != null) {
				gm.setUseGoodAction(uga);
			}
		}
		SkillManager sm = SkillManager.getInstance();

		TaskManager tm = TaskManager.getInstance();

		SkillEffectManager sem = SkillEffectManager.getInstance();
		HorseModelDataProvider horseManager = HorseModelDataProvider.getInstance();
		GiftPacksManager gpm = GiftPacksManager.getInstance();

		// 1普通物品 2装备 3药品 4任务卷轴 5秘籍书 6宝石 7喇叭 8烟花 9可用于合成的材料 10时间过期类道具 11礼包道具
		// 12坐骑精魂
		
		//'1普通物品 2装备 3药品 4任务卷轴(可以给玩家一个任务) 5秘籍书 6宝石 7喇叭 8烟花 9可用于合成的材料 10时间过期类道具 
		//11礼包道具 12坐骑精魂 15宝箱和秘籍摇奖 17宝藏 18打磨石 19回城卷轴 21洗点 22脚本物品 23宝石 24技能丹 25商城购物卡 26金松果相关 27炼体的食物 28箭支 29黑玉断续膏',
		for (Goodmodel gm : goodmodellist) {
			int kind = gm.getKind();
			if (kind == 3 || kind==34) {
				if (gm.getDrugBuffId() != null && gm.getDrugBuffId() > 0) {
					SkillEffect se = sem.getSkillEffectById(gm.getDrugBuffId());
					if (se != null) {
						UseGoodAction uga = new CharacterUseBufferGood(se);
						putUserGoodAction(gm.getId(), uga);
						gm.setUseGoodAction(uga);
					}
					continue;
				}
				if (gm.getDrugType() != null && gm.getDrugType() > 0 && gm.getDrugValue() != null) {
					UseGoodAction uga = new CharacterUseJiachengGood(gm);
					putUserGoodAction(gm.getId(), uga);
					gm.setUseGoodAction(uga);
				}
				continue;
			} else if (kind == 4) {
				if (gm.getTask() != null && gm.getTask() > 0) {
					Task t = tm.get(gm.getTask());
					if (t != null) {
						UseGoodAction uga = new CharacterUseTaskGood(t);
						putUserGoodAction(gm.getId(), uga);
						gm.setUseGoodAction(uga);
					}
					continue;
				}
			} else if (kind == 5) {
				if (gm.getSkill() != null && gm.getSkill() > 0) {
					Skill skill = sm.getSkillById(gm.getSkill());
					if (skill != null) {
						UseGoodAction uga = new CharacterUseSkillGood(skill);
						putUserGoodAction(gm.getId(), uga);
						gm.setUseGoodAction(uga);
					}
					continue;
				}
				// 阵法在阵法管理器当中
			} else if (kind == 11) {
				// 礼包包装
				if (gm.getGiftPacksId() == null) {
					continue;
				}
				int giftPkg = gm.getGiftPacksId();
				GiftPacks gp = gpm.getGiftPacksById(giftPkg);
				
				if (gp != null) {
					UseGoodAction uga = new CharacterUseGiftPacksGood(gp);
					putUserGoodAction(gm.getId(), uga);
					gm.setUseGoodAction(uga);
				}
				continue;
			} else if (kind == 12) {
				if (gm.getHorseSpirit() != null && gm.getHorseSpirit() > 0) {
					HorseModel hm = horseManager.getHorseModelByID(gm.getHorseSpirit());
					if (hm != null) {
						UseGoodAction uga = new CharacterUseHorseGood(hm);
						putUserGoodAction(gm.getId(), uga);
						gm.setUseGoodAction(uga);
					}
				}
				// 宝箱 和秘籍摇奖
			} else if (kind == 15) {
				UseGoodAction uga = new CharacterUseChestGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
				// 宝藏
			} else if (kind == 17) {
				UseGoodAction uga = new CharacterUseTreasureMapGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);

			} else if (kind == 18) {// 打磨石
				UseGoodAction uga = new CharacterRepairEquipmentGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 19) {// 回城卷轴
				UseGoodAction uga = new CharacterTpHuiChengGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);

			} else if (kind == 21) {// 洗点
				UseGoodAction uga = new CharacterXiDianGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 22) { // 脚本物品
				UseGoodAction uga = new CharacterUseScripGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 24) {
				UseGoodAction uga = new CharacterUseGoodOpenUI(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 25) {
				UseGoodAction uga = new CharacterUseShopDazheGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 26) {// 金松果相关
				UseGoodAction uga = new CharacterUseChestExchangeGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 27) {// 炼体的食物
				UseGoodAction uga = new CharacterUseLiantiFood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 29) {// 黑玉断续膏
				UseGoodAction uga = new XiuFuJingMaiGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			} else if (kind == 30) {
				UseGoodAction uga = new CharacterUseJinjieChuanshuoHorseGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			}else if (kind == 33) {
				UseGoodAction uga = new CharacterUseRoushenGood(gm);
				putUserGoodAction(gm.getId(), uga);
				gm.setUseGoodAction(uga);
			}
		}
	}

	@Override
	public void reload() {
		initAllUserGoodAction(GoodmodelManager.getInstance().getAlLGoodCollection());
	}

	@Override
	public String getAppname() {
		return null;
	}

	@Override
	public String getCachename() {
		return null;
	}
}
