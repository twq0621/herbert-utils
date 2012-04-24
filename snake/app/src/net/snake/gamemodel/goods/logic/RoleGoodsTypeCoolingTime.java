package net.snake.gamemodel.goods.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.CharacterPropcd;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterPropcdManager;

public class RoleGoodsTypeCoolingTime {
	private int commonCoolType;
	private long commonCoolTime; // 公共冷去时间可以使用时间
	private int totalTime; // 当前公共冷却时间总的播放时间
	private Map<Integer, Long> coolingTimeMap = new HashMap<Integer, Long>(); // 物品使用冷却时间 key物品id value使用时间

	public RoleGoodsTypeCoolingTime(Goodmodel gm) {
		long cooling = gm.getCoolingtime() + System.currentTimeMillis();
		coolingTimeMap.put(gm.getId(), cooling);
		updateCommonCoolTime(gm);
		this.commonCoolType = gm.getCommonCoolType();
	}

	public RoleGoodsTypeCoolingTime(int commonCoolType) {
		this.commonCoolType = commonCoolType;
		this.commonCoolTime = System.currentTimeMillis();
		this.totalTime = 0;
	}

	/**
	 * 使用之前 判断是否已经满足冷却时间
	 * 
	 * @param gm
	 * @return
	 */
	public boolean isUseGoods(Goodmodel gm) {
		Long useTime = coolingTimeMap.get(gm.getId());
		long nowTime = System.currentTimeMillis();
		if (nowTime < commonCoolTime) {
			return false;
		}
		long cooling = gm.getCoolingtime() + nowTime;
		if (useTime == null) {
			coolingTimeMap.put(gm.getId(), cooling);
			updateCommonCoolTime(gm);
			return true;
		}
		if (useTime <= nowTime) {
			coolingTimeMap.put(gm.getId(), cooling);
			updateCommonCoolTime(gm);
			return true;
		}
		return false;
	}

	public void updateCommonCoolTime(Goodmodel gm) {
		if (gm.getCommonCoolTime() == null) {
			return;
		}
		this.totalTime = gm.getCommonCoolTime();
		this.commonCoolTime = gm.getCommonCoolTime() + System.currentTimeMillis();
	}

	public void downLine(Hero character) {
		Set<Entry<Integer, Long>> set = coolingTimeMap.entrySet();
		long nowTime = System.currentTimeMillis() + CharacterGoodsCoolingTimeManager.TIME_MAX;
		if (this.commonCoolTime > nowTime) {
			CharacterPropcd cp = new CharacterPropcd();
			cp.setCharacterId(character.getId());
			cp.setCdId(commonCoolType);
			cp.setCdType(CharacterGoodsCoolingTimeManager.COMMON_CD_TYPE);
			cp.setUseTime(this.commonCoolTime);
			cp.setTotalTime(totalTime);
			nowTime = this.commonCoolTime;
			CharacterPropcdManager.getInstance().asynInsertCharacterGiftpacks(character, cp);
		}
		for (Entry<Integer, Long> ent : set) {
			if (ent.getValue() > nowTime) {
				CharacterPropcd cp = new CharacterPropcd();
				cp.setCharacterId(character.getId());
				cp.setCdId(ent.getKey());
				cp.setCdType(CharacterGoodsCoolingTimeManager.CD_TYPE);
				cp.setUseTime(ent.getValue());
				CharacterPropcdManager.getInstance().asynInsertCharacterGiftpacks(character, cp);
			}
		}
	}

	public void addCharacterPropcd(CharacterPropcd cp) {
		if (cp.getCdType() != CharacterGoodsCoolingTimeManager.COMMON_CD_TYPE) {
			this.coolingTimeMap.put(cp.getCdId(), cp.getUseTime());
		} else {
			this.commonCoolTime = cp.getUseTime();
			this.totalTime = cp.getTotalTime();
		}
	}
}
