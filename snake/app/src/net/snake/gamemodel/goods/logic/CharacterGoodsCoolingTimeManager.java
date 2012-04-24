package net.snake.gamemodel.goods.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.EnterSceneCoolingTimeMsg50140;
import net.snake.gamemodel.hero.bean.CharacterPropcd;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterPropcdManager;

/**
 * 
 * 物品使用冷却时间控制 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class CharacterGoodsCoolingTimeManager {
	public final static int CD_TYPE = 1; // 某药品冷却时间存储类型
	public final static int COMMON_CD_TYPE = 2; // 药品公共冷却时间存储类型
	public final static int TIME_MAX = 30 * 1000; // 冷却可以使用时间在这个范围内的 不提交数据库
	// private Character character;
	private CharacterGoodController cgc;
	public List<CharacterPropcd> commonCdList = new ArrayList<CharacterPropcd>();
	public List<CharacterPropcd> goodCdList = new ArrayList<CharacterPropcd>();
	private Map<Integer, RoleGoodsTypeCoolingTime> typeMap = new HashMap<Integer, RoleGoodsTypeCoolingTime>(); // 物品使用冷却时间
	private Map<Integer, Long> coolingTimeMap = new HashMap<Integer, Long>(); // 物品使用冷却时间key物品idvalue使用时间

	public void destory() {
		commonCdList.clear();
		goodCdList.clear();
		typeMap.clear();
		coolingTimeMap.clear();
	}

	public CharacterGoodsCoolingTimeManager(CharacterGoodController cgc) {
		this.cgc = cgc;
	}

	/**
	 * 使用之前 判断是否已经满足冷却时间,满足返回true 并更新
	 * 
	 * @param gm
	 * @return
	 */
	public boolean isUseGoodsAndUpdate(Goodmodel gm) {
		if (gm.getDrugType() == null) {
			return isUseNoCommonCoolingGood(gm);

		} else {
			return isUseCommonCoolingGood(gm);
		}
	}

	/**
	 * 使用有公共冷却时间的物品
	 * 
	 * @param gm
	 * @return
	 */
	private boolean isUseCommonCoolingGood(Goodmodel gm) {
		RoleGoodsTypeCoolingTime roleGoodsTypeCoolingTime = typeMap.get(gm.getCommonCoolType());
		if (roleGoodsTypeCoolingTime == null) {
			roleGoodsTypeCoolingTime = new RoleGoodsTypeCoolingTime(gm);
			typeMap.put(gm.getCommonCoolType(), roleGoodsTypeCoolingTime);
			return true;
		}
		return roleGoodsTypeCoolingTime.isUseGoods(gm);
	}

	/**
	 * 使用没有公共冷却时间的物品
	 * 
	 * @param gm
	 * @return
	 */
	private boolean isUseNoCommonCoolingGood(Goodmodel gm) {
		Long time = coolingTimeMap.get(gm.getId());
		if (time == null) {
			coolingTimeMap.put(gm.getId(), System.currentTimeMillis());
			return true;
		}
		if (System.currentTimeMillis() - time > gm.getCoolingtime()) {
			coolingTimeMap.put(gm.getId(), System.currentTimeMillis());
			return true;
		}
		return false;
	}

	/**
	 * 角色下线保存处于冷却状态的物品冷却时间
	 */
	public void downLine() {
		Hero character = cgc.getCharacter();
		for (RoleGoodsTypeCoolingTime rgtct : typeMap.values()) {
			rgtct.downLine(character);
		}
		Set<Entry<Integer, Long>> set = coolingTimeMap.entrySet();
		long nowTime = System.currentTimeMillis() + TIME_MAX;
		for (Entry<Integer, Long> ent : set) {
			if (ent.getValue() > nowTime) {
				CharacterPropcd cp = new CharacterPropcd();
				cp.setCharacterId(character.getId());
				cp.setCdId(ent.getKey());
				cp.setCdType(CD_TYPE);
				cp.setUseTime(ent.getValue());
				CharacterPropcdManager.getInstance().asynInsertCharacterGiftpacks(character, cp);
			}
		}
	}

	/**
	 * 初始化物品处于冷却状态的冷却时间信息
	 */
	public void initDate() {
		Hero character = cgc.getCharacter();
		List<CharacterPropcd> list = CharacterPropcdManager.getInstance().selecteListByCharacterId(character.getId());
		long now = System.currentTimeMillis() + TIME_MAX / 2;
		for (CharacterPropcd cp : list) {
			if (cp.getUseTime() > now) {
				addCoolingTime(cp);
			}
		}
		CharacterPropcdManager.getInstance().deleteByCharacterId(character.getId());
	}

	/**
	 * 添加冷却时间
	 * 
	 * @param cp
	 */
	private void addCoolingTime(CharacterPropcd cp) {
		int goodId = cp.getCdId();
		if (cp.getCdType() == COMMON_CD_TYPE) {
			RoleGoodsTypeCoolingTime roleGoodsTypeCoolingTime = typeMap.get(goodId);
			if (roleGoodsTypeCoolingTime == null) {
				roleGoodsTypeCoolingTime = new RoleGoodsTypeCoolingTime(goodId);
				typeMap.put(goodId, roleGoodsTypeCoolingTime);
			}
			roleGoodsTypeCoolingTime.addCharacterPropcd(cp);
			commonCdList.add(cp);
			return;
		}
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm.getCommonCoolType() == null) {
			coolingTimeMap.put(goodId, cp.getUseTime());
		} else {
			RoleGoodsTypeCoolingTime roleGoodsTypeCoolingTime = typeMap.get(gm.getCommonCoolType());
			if (roleGoodsTypeCoolingTime == null) {
				roleGoodsTypeCoolingTime = new RoleGoodsTypeCoolingTime(gm.getCommonCoolType());
				typeMap.put(gm.getCommonCoolType(), roleGoodsTypeCoolingTime);
			}
			roleGoodsTypeCoolingTime.addCharacterPropcd(cp);
		}
		goodCdList.add(cp);
	}

	/**
	 * 进入场景发送冷却信息
	 */
	public void sendCoolingMsgToClienWhenOnline() {
		if (commonCdList.size() == 0 && goodCdList.size() == 0) {
			return;
		}
		cgc.getCharacter().sendMsg(new EnterSceneCoolingTimeMsg50140(commonCdList, goodCdList));
	}
}
