package net.snake.gamemodel.goods.logic.container;

import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;

public interface IBag extends IGirdContainer {
	/**
	 * 是否有空间添加物品 物品必须指定 是否绑定，物品模型ID,物品数量
	 * 
	 * @param goods
	 * @return
	 */
	public boolean isHasSpaceForAddGoods(CharacterGoods goods);

	/**
	 * 是否有空间添加物品 物品必须指定 是否绑定，物品模型ID,物品数量
	 * 
	 * @param goods
	 * @return
	 */
	public boolean isHasSpaceForAddGoods(Collection<CharacterGoods> goodslist);

	/**
	 * 添加物品 物品必须指定 是否绑定，物品模型ID,物品数量 返回添加成功或失败
	 * 
	 * @param goods
	 * @return
	 */
	public boolean addGoods(CharacterGoods goods);

	/**
	 * 添加物品,添加时可设置其位置,如果位置不为空,则优先放到该位置上 如果物品无法放到此位置,则返回最后一个位置
	 * 
	 * @param goods
	 * @return
	 */
	public CharacterGoods addAndReturnLast(CharacterGoods goods);

	/**
	 * 添加一批物品，返回成功或失败
	 * 
	 * @param goodslist
	 * @return
	 */
	public boolean addGoods(Collection<CharacterGoods> goodslist);

	/**
	 * 删除指定物品id，指定数量的物品，计算物品数据量时，会排除正在交易中的以及过期的 删除时从前往向后，不管是否绑定
	 * 对于过期物品的销毁，只能deleteGoodsByPostion
	 * 
	 * @param modelid
	 * @param deletecount
	 * @return
	 */
	public boolean deleteGoodsByModel(int modelid, int deletecount);

	/**
	 * 删除指定物品id，指定数量的物品，计算物品数据量时，会排除正在交易中的以及过期的 删除时从前往向后，只删除指定绑定类型的
	 * 对于过期物品的销毁，只能deleteGoodsByPostion
	 * 
	 * @param modelid
	 * @param deletecount
	 * @param bind
	 * @return
	 */
	public boolean deleteGoodsByModel(int modelid, int deletecount, boolean bind);

	/**
	 * 找物品模型id 在包裹中的第一个位置
	 * 
	 * @param modelid
	 * @return
	 */
	public CharacterGoods getFirstGoodsByModelid(int modelid);

	/**
	 * 如果先删掉一部分物品(过期和交易的是不参加的)，是否足够添加另一部分物品 删除时的判定方式　 如果指定了位置，但数量为0，则那个位置全删
	 * 如果指定了位置，但数量不为0，则删除指定位置指定数量的物品 不指定位置的话，绑定类型为null　则不限定绑定类型删除
	 * 不指定位置的话，绑定类型非null　限定绑定类型删除 添加的物品必须物品必须指定 是否绑定，物品模型ID,物品数量
	 * 
	 * 需要指定物品的模型ID,数量
	 * 
	 * @param removepostions
	 * @param addlist
	 * @return
	 */
	public boolean isHasSpaceForRemoveAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist);

	/**
	 * 删除一部分物品为，添加另外的物品
	 * 
	 * @param removepostions
	 * @param addlist
	 * @return
	 */
	public boolean removeAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist);

	public boolean isHasSpaceForBindFirstRemoveAndAddGoods(Collection<CharacterGoods> removepostions, Collection<CharacterGoods> addlist);

	/**
	 * 在指定位置删除物品 返回true表示删除成功 false表示删除失败， 无法删除已经过期和正在交易中的物品，并返回false
	 * 
	 * @param oldpostion
	 * @param deletecount
	 */
	public boolean deleteSplitGoods(short deletepostion, int deletecount);

	/**
	 * 交易中验证是否有足够空间
	 * 
	 * @param values
	 * @param goodsCollection
	 * @return
	 */
	public boolean isHasSpaceForRemoveAndAddGoodsInTrading(Collection<CharacterGoods> values, Collection<CharacterGoods> goodsCollection);

}
