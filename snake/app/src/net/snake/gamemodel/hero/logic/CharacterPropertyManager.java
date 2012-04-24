package net.snake.gamemodel.hero.logic;

import net.snake.consts.CopperAction;
import net.snake.consts.EffectType;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;

/**
 * 角色某一属性变化
 * 
 * type(byte)【1,增加攻击力2,增加防御力3,增加暴击4,增加闪避5,增加攻击速度6,增加移动速度7,增加生命上限值
 * 8,增加内力上限值9,增加体力上限值10,增加潜能点个数11,增加等级12,增加经验
 * 14,增加真气储量15,增加战场声望16,恢复生命值17,恢复内力值18,恢复体力值 20,解除全部负面状态,42铜币，43元宝】属性值(int)
 */
public class CharacterPropertyManager {
	/**
	 * 真气变化
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeZhenqi(Hero character, int value) {
		if (value == 0)
			return 0;
		if (character.getZhenqi() + value > MaxLimit.ZHENQI_MAX) {
			value = MaxLimit.ZHENQI_MAX - character.getZhenqi();
		}

		if (value > 0) {
			character.getDayInCome().dealZhenqi(value);
		}
		character.setZhenqi(character.getZhenqi() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.zhenqi, character.getZhenqi()));
		}
		return value;
	}

	/**
	 * lIJIN变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeLijin(Hero character, int value) {
		if (character.getJiaozi() + value > MaxLimit.LIJIN_MAX) {
			value = MaxLimit.LIJIN_MAX - character.getJiaozi();
		}
		character.setJiaozi(character.getJiaozi() + value);
		if (value > 0) {
			character.setLeijiGainLijin(character.getLeijiGainLijin() + value);
		}
		//if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.lijin, character.getJiaozi()));
		//}
		return value;
	}

	/**
	 * 铜币变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeCopper(Hero character, int value, CopperAction action) {
		if (value == 0)
			return 0;
		if (character.getCopper() + value > MaxLimit.BAG_COPPER_MAX) {
			value = MaxLimit.BAG_COPPER_MAX - character.getCopper();
		}
		character.setCopper(character.getCopper() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.copper, character.getCopper()));
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().copperCount(character.getCopper());
		}
		return value;
	}

	/**
	 * 玩家帮贡改变 变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeContribution(Hero character, int value) {
		if (value == 0)
			return 0;
		if (character.getContribution() + value > MaxLimit.Contribution_Max) {
			value = MaxLimit.Contribution_Max - character.getCopper();
		}
		character.setContribution(character.getContribution() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.contribution, character.getContribution()));
		}
		return value;
	}

	public static int changeStorageCopper(Hero character, int value) {
		if (character.getStorageCopper() + value > MaxLimit.STORAGE_COPPER_MAX) {
			value = MaxLimit.STORAGE_COPPER_MAX - character.getStorageCopper();
		}
		character.setStorageCopper(character.getStorageCopper() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.storageCopper, character.getStorageCopper()));
		}
		return value;
	}

	/**
	 * nowhp变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeNowHp(Hero character, int value) {
		value = character.changeNowHp(value);
		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.hp, character.getNowHp()));
		}
		return value;
	}

	/**
	 * nowMp变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeNowMp(Hero character, int value) {
		if (value > 0 && character.getEffectController().isMpOver()) {
			return 0;
		}

		value = character.changeNowMp(value);
		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.mp, character.getNowMp()));
		}
		return value;
	}

	/**
	 * nowSp变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeNowSp(Hero character, int value) {
		if (value > 0 && character.getEffectController().isSpOver()) {
			return 0;
		}

		value = character.changeNowSp(value);

		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.sp, character.getNowSp()));
		}
		return value;
	}

	/**
	 * 永久性地改变攻击力 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeAttack(Hero character, int value) {
		int maxAttack = character.getPropertyAdditionController().getExtraAttack();
		if (maxAttack + value > MaxLimit.ATTACT_MAX) {
			value = MaxLimit.ATTACT_MAX - maxAttack;
		}
		character.setAttack(character.getAttack() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.attack, character.getPropertyAdditionController().getExtraAttack()));
		}
		return value;
	}

	/**
	 * 永久性地改变防御力 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeDefence(Hero character, int value) {
		int maxDefend = character.getPropertyAdditionController().getExtraDefend();
		if (maxDefend + value > MaxLimit.DEFENCE_MAX) {
			value = MaxLimit.DEFENCE_MAX - maxDefend;
		}
		character.setDefence(character.getDefence() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.defence, character.getPropertyAdditionController().getExtraDefend()));
		}
		return value;
	}

	/**
	 * 永久性地改变暴击力 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeCrt(Hero character, int value) {
		int maxCrt = character.getPropertyAdditionController().getExtraCrt();
		if (maxCrt + value > MaxLimit.CRT_MAX) {
			value = MaxLimit.CRT_MAX - maxCrt;
		}
		character.setCrt(character.getCrt() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.crt, character.getPropertyAdditionController().getExtraCrt()));
		}
		return value;
	}
	
	/**
	 * 永久性地改变命中 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeHit(Hero character, int value) {
		int maxHit = character.getPropertyAdditionController().getExtraHit();
		if (maxHit + value > MaxLimit.HIT_MAX) {
			value = MaxLimit.HIT_MAX - maxHit;
		}
		character.setHit(character.getHit() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.hit, character.getPropertyAdditionController().getExtraHit()));
		}
		return value;
	}

	/**
	 * 永久性地改变闪避力 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeDodge(Hero character, int value) {
		int maxDodge = character.getPropertyAdditionController().getExtraDodge();
		if (maxDodge + value > MaxLimit.DODGE_MAX) {
			value = MaxLimit.DODGE_MAX - maxDodge;
		}
		character.setDodge(character.getDodge() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.dodge, character.getPropertyAdditionController().getExtraDodge()));
		}
		return value;
	}

	/**
	 * 永久性地改变移动速度 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeMoveSpeed(Hero character, int value) {
		int maxMoveSpeed = character.getPropertyAdditionController().getExtraMoveSpeed();
		if (maxMoveSpeed + value > MaxLimit.MOVESPEED_MAX) {
			value = MaxLimit.MOVESPEED_MAX - maxMoveSpeed;
		}
		character.setMoveSpeed(character.getMoveSpeed() + value);
		if (value != 0) {
			character.getEyeShotManager()
					.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.movespeed, character.getPropertyAdditionController().getExtraMoveSpeed()));
		}
		return value;
	}

	/**
	 * 永久性地改变生命上限 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeMaxHp(Hero character, int value) {
		character.setMaxHp(character.getMaxHp() + value);
		character.getPropertyAdditionController().recompute();
		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.maxHp, character.getPropertyAdditionController().getExtraMaxHp()));
		}
		return value;
	}

	/**
	 * 永久性地改变内力上限变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeMaxMp(Hero character, int value) {
		character.setMaxMp(character.getMaxMp() + value);
		character.getPropertyAdditionController().recompute();
		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.maxMp, character.getPropertyAdditionController().getExtraMaxMp()));
		}
		return value;
	}

	/**
	 * 永久性地改变体力上限变化 返回0 失败
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeMaxSp(Hero character, int value) {
		character.setMaxSp(character.getMaxSp() + value);
		character.getPropertyAdditionController().recompute();
		if (value != 0) {
			character.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.maxSp, character.getPropertyAdditionController().getExtraMaxSp()));
		}
		return value;
	}

	/**
	 * 永久性地改变增加攻击速度
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeAtkColdtime(Hero character, int value) {
		character.setAtkColdtime(character.getAtkColdtime() + value);
		if (value != 0) {
			character.getPropertyAdditionController().recompute();
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.attackspeed, character.getPropertyAdditionController().getExtraAttackSpeed()));
		}
		return value;
	}

	/**
	 * 潜能点变化
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changePotential(Hero character, int value) {
		if (character.getPotential() + value > MaxLimit.POTENTIAL_MAX) {
			value = MaxLimit.POTENTIAL_MAX - character.getPotential();
		}
		character.setPotential(character.getPotential() + value);
		if (value != 0) {
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.potential, character.getPotential()));
		}
		return value;
	}

	/**
	 * 当前战场声望变化
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changePrestige(Hero character, int value) {
		return 0;
	}

	/**
	 * 当前攻城声望变化
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeGongChengShengWang(Hero character, int value, boolean updateToadyRecord) {
		if (value == 0)
			return 0;
		if (value + character.getChengzhanShengWang() > MaxLimit.GongChengShengWang_MAX) {
			value = MaxLimit.GongChengShengWang_MAX - character.getChengzhanShengWang();
		}

		if (updateToadyRecord) {
			character.setTodayChengzhanShengwang(character.getTodayChengzhanShengwang() + value);
		}

		int chValue = character.getChengzhanShengWang() + value;
		chValue = chValue < 0 ? 0 : chValue;
		character.setChengzhanShengWang(chValue);
		if (value != 0) {
			character.getMyCharacterAchieveCountManger().getFactionCount().chengzhangShengwangCount(character.getChengzhanShengWang());
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.prestige, character.getChengzhanShengWang()));
		}
		return value;
	}

	/**
	 * 跨服服务器使用 不记录t_chararacter表 等同步回来 当前论剑声望变化
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeLunJianShengWang_InKuaFu(Hero character, int value) {
		if (value == 0)
			return 0;
		character.getMyCharacterAcrossIncomeManager().addShengWang(value);
		return value;
	}

	/**
	 * 原服务器使用
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public static int changeLunJianShengWang(Hero character, int value) {
		if (value == 0)
			return 0;
		if (value + character.getLunjianShengWang() > MaxLimit.LunJianShengWang_MAX) {
			value = MaxLimit.LunJianShengWang_MAX - character.getLunjianShengWang();
		}

		int chValue = character.getLunjianShengWang() + value;
		chValue = chValue < 0 ? 0 : chValue;
		character.setLunjianShengWang(chValue);
		if (value != 0) {
			character.getMyCharacterAchieveCountManger().getFactionCount().lunjianShengwangCount(character.getLunjianShengWang());
			character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.lunjianshengwang, character.getLunjianShengWang()));
		}
		return value;
	}
}
