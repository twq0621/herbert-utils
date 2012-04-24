package net.snake.gamemodel.goods.response;

import java.util.Collection;
import java.util.Iterator;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.ServerResponse;

/**
 * 掉落物品进入包裹效果
 * 
 */

public class GoodToBadEffectMsg11170 extends ServerResponse {

	/**
	 * 物品掉落
	 * 
	 * @param cdg
	 */
	public GoodToBadEffectMsg11170(SceneDropGood cdg) {
		this.setMsgCode(11170);
		this.writeByte(1);
		this.writeShort(1);
		this.writeInt(cdg.getCg().getGoodmodelId());
		this.writeShort(cdg.getCg().getCount());
		this.writeShort(cdg.getX());
		this.writeShort(cdg.getY());

	}

	/**
	 * 物品面板类获得
	 * 
	 * @param cdg
	 */
	public GoodToBadEffectMsg11170(byte type, Collection<CharacterGoods> collection) {
		this.setMsgCode(11170);
		this.writeByte(type);
		this.writeShort(collection.size());
		for (CharacterGoods cg : collection) {
			this.writeInt(cg.getGoodmodelId());
			this.writeShort(cg.getCount());
		}
	}

	/**
	 * 物品面板类获得
	 * 
	 * @param cdg
	 */
	public GoodToBadEffectMsg11170(byte type, CharacterGoods cg) {
		this.setMsgCode(11170);
		this.writeByte(type);
		this.writeShort(1);
		this.writeInt(cg.getGoodmodelId());
		this.writeShort(cg.getCount());
	}

	/**
	 * 角色与角色交易
	 * 
	 * @param collection
	 * @param character
	 */
	public GoodToBadEffectMsg11170(Collection<CharacterGoods> collection, Hero trader) {
		this.setMsgCode(11170);
		this.writeByte(5);
		this.writeShort(collection.size());
		for (CharacterGoods cg : collection) {
			this.writeInt(cg.getGoodmodelId());
			this.writeShort(cg.getCount());
		}
		this.writeInt(trader.getId());
		this.writeShort(trader.getX());
		this.writeShort(trader.getY());
	}

	public GoodToBadEffectMsg11170(CharacterGoods cg, int npcId) {
		this.setMsgCode(11170);
		this.writeByte(4);
		this.writeShort(1);
		this.writeInt(cg.getGoodmodelId());
		this.writeShort(cg.getCount());
		this.writeInt(npcId);
	}

	public GoodToBadEffectMsg11170(Collection<CharacterGoods> col, int npcId) {
		this.setMsgCode(11170);
		this.writeByte(4);
		int size = col.size();
		this.writeShort(size);
		for (Iterator<CharacterGoods> iterator = col.iterator(); iterator.hasNext();) {
			CharacterGoods characterGoods = iterator.next();
			this.writeInt(characterGoods.getGoodmodelId());
			this.writeShort(characterGoods.getCount());
		}
		this.writeInt(npcId);
	}
}
