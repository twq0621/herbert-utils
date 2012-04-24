package net.snake.gamemodel.activities.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.ai.util.ArithmeticUtils;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.ibatis.IbatisEntity;

public class ActivationCard implements IbatisEntity {
	/**
	 * 卡号 t_activation_card.f_card_no
	 * 
	 * 
	 */
	private String cardNo;

	/**
	 * 卡类型（待添加详细分类） t_activation_card.f_type
	 * 
	 * 
	 */
	private Byte type;

	/**
	 * 奖励的礼金数 t_activation_card.f_lijin
	 * 
	 * 
	 */
	private Integer lijin;

	/**
	 * 奖励的铜币数 t_activation_card.f_copper
	 * 
	 * 
	 */
	private Integer copper;

	/**
	 * 奖励物品列表（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_activation_card.f_goods
	 * 
	 * 
	 */
	private String goods;

	/**
	 * 卡号状态 1正常，0过期 t_activation_card.f_state
	 * 
	 * 
	 */
	private Byte state;

	/**
	 * 物品信息集合
	 */
	private Map<Integer, List<CharacterGoods>> goodMap = new HashMap<Integer, List<CharacterGoods>>();

	/**
	 * 卡号 t_activation_card.f_card_no
	 * 
	 * @return the value of t_activation_card.f_card_no
	 * 
	 * 
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 卡号 t_activation_card.f_card_no
	 * 
	 * @param cardNo
	 *            the value for t_activation_card.f_card_no
	 * 
	 * 
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 卡类型（待添加详细分类） t_activation_card.f_type
	 * 
	 * @return the value of t_activation_card.f_type
	 * 
	 * 
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 卡类型（待添加详细分类） t_activation_card.f_type
	 * 
	 * @param type
	 *            the value for t_activation_card.f_type
	 * 
	 * 
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 奖励的礼金数 t_activation_card.f_lijin
	 * 
	 * @return the value of t_activation_card.f_lijin
	 * 
	 * 
	 */
	public Integer getLijin() {
		return lijin;
	}

	/**
	 * 奖励的礼金数 t_activation_card.f_lijin
	 * 
	 * @param lijin
	 *            the value for t_activation_card.f_lijin
	 * 
	 * 
	 */
	public void setLijin(Integer lijin) {
		this.lijin = lijin;
	}

	/**
	 * 奖励的铜币数 t_activation_card.f_copper
	 * 
	 * @return the value of t_activation_card.f_copper
	 * 
	 * 
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 奖励的铜币数 t_activation_card.f_copper
	 * 
	 * @param copper
	 *            the value for t_activation_card.f_copper
	 * 
	 * 
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 奖励物品列表（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_activation_card.f_goods
	 * 
	 * @return the value of t_activation_card.f_goods
	 * 
	 * 
	 */
	public String getGoods() {
		return goods;
	}

	/**
	 * 奖励物品列表（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_activation_card.f_goods
	 * 
	 * @param goods
	 *            the value for t_activation_card.f_goods
	 * 
	 * 
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 卡号状态 1正常，0过期 t_activation_card.f_state
	 * 
	 * @return the value of t_activation_card.f_state
	 * 
	 * 
	 */
	public Byte getState() {
		return state;
	}

	/**
	 * 卡号状态 1正常，0过期 t_activation_card.f_state
	 * 
	 * @param state
	 *            the value for t_activation_card.f_state
	 * 
	 * 
	 */
	public void setState(Byte state) {
		this.state = state;
	}

	public void initGoodsInfo() {
		if (this.goods == null || this.goods.equals("")) {
			return;
		}
		String[] goodStr = this.goods.split(";");
		Map<Integer, List<CharacterGoods>> map = initMenpaiGoodsMap();
		for (int i = 0; i < goodStr.length; i++) {
			putmenpaiGoodsToMap(map, goodStr[i]);
		}
		this.goodMap = map;
	}

	private Map<Integer, List<CharacterGoods>> initMenpaiGoodsMap() {
		Map<Integer, List<CharacterGoods>> map = new HashMap<Integer, List<CharacterGoods>>();
		for (int i = 1; i < 5; i++) {
			map.put(i, new ArrayList<CharacterGoods>());
		}
		return map;
	}

	private void putmenpaiGoodsToMap(Map<Integer, List<CharacterGoods>> map, String goodsStr) {
		String[] str = goodsStr.split("[*]");
		String goodstr = str[0];
		boolean isIgnoreBind = false;
		if (str[0].contains("!")) {
			goodstr = str[0].replace("!", "");
			isIgnoreBind = true;
		}
		boolean isNoticGood = false;
		if (str[0].contains("@")) {
			goodstr = goodstr.replace("@", "");
			isNoticGood = true;
		}
		String goodIdStr = goodstr;
		Date lostDate = null;
		int toBadLostTime = 0;
		if (goodstr.contains("#")) {
			String[] goods = goodstr.split("#");
			goodIdStr = goods[1];
			if (goods[0].length() > 8) {
				lostDate = ArithmeticUtils.stringToDate(goods[0]);
			} else {
				toBadLostTime = Integer.parseInt(goods[0]);
			}
		}
		int goodId = Integer.parseInt(goodIdStr);
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm == null) {
			return;
		}
		if (str[1].contains("_")) {
			String[] limitStr = str[1].split("_");
			int count = Integer.parseInt(limitStr[0]);
			int menpai = Integer.parseInt(limitStr[1]);
			List<CharacterGoods> list = map.get(menpai);
			if (list != null) {
				CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
				cg.setGoodmodelId(goodId);
				cg.setCount(count);
				if (isIgnoreBind) {
					cg.setBind(CommonUseNumber.byte0);

				} else {
					cg.setBind(CommonUseNumber.byte1);

				}
				cg.setIgnoreBind(isIgnoreBind);
				cg.setNoticeGood(isNoticGood);
				cg.setLastDate(lostDate);
				cg.setToBadLostTime(toBadLostTime);
				list.add(cg);
			}
		} else {
			int count = Integer.parseInt(str[1]);
			CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
			cg.setGoodmodelId(goodId);
			cg.setCount(count);
			if (isIgnoreBind) {
				cg.setBind(CommonUseNumber.byte0);

			} else {
				cg.setBind(CommonUseNumber.byte1);

			}
			cg.setIgnoreBind(isIgnoreBind);
			cg.setNoticeGood(isNoticGood);
			cg.setLastDate(lostDate);
			cg.setToBadLostTime(toBadLostTime);
			Collection<List<CharacterGoods>> collection = map.values();
			for (List<CharacterGoods> list : collection) {
				list.add(cg);
			}
		}
	}

	public List<CharacterGoods> getGoodlist(int menpai) {

		return this.goodMap.get(menpai);
	}
}
