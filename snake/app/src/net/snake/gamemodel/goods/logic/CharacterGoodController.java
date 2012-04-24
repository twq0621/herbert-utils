package net.snake.gamemodel.goods.logic;

import java.util.Collection;

import net.snake.consts.GoodsContainerType;
import net.snake.commons.script.SCharacterGoodController;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.HorseBodyGoodsContiner;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.logic.container.IBody;
import net.snake.gamemodel.goods.logic.container.IStall;
import net.snake.gamemodel.goods.logic.container.IStorage;
import net.snake.gamemodel.hero.bean.Hero;

public interface CharacterGoodController extends SCharacterGoodController {

	public Hero getCharacter();

	public IBody getBodyGoodsContiner();

	public IBag getBagGoodsContiner();

	public IStorage getStorageGoodsContainer();

	public IStall getStallGoodsContainer();

	public IStorage getAcrossGoodsContainer();

	/**
	 * 初始化人物物品,生成各种缓存数据
	 */
	public abstract void initData();

	public HorseBodyGoodsContiner getAndRemoveHorseBodyGoodsContainer(int horseid);

	/**
	 * 根据物品位置索引找到物品ID 但该方法支持 "角色身上的,包裹里的,仓库的,摊位上的"
	 * 
	 * @param postionid
	 * @return
	 */
	public abstract CharacterGoods getGoodsByPositon(short postionid);

	/**
	 * 物品移动
	 * 
	 * @param fromposition
	 * @param fromhorseid
	 * @param topostion
	 * @param tohorseid
	 */
	public void movePosition(short fromposition, int fromhorseid, GoodsContainerType toContainer, short topostion, int tohorseid);

	public void reInitAcrossBag();

	/**
	 * 获得装备在身上的物品列表
	 * 
	 * @return
	 */
	public abstract Collection<CharacterGoods> getBodyGoodsList();

	public void deleteCharacterGoods(short postion, int horseid);

	/**
	 * 删除人物物品
	 * 
	 * @param characterGoods
	 */
	public abstract void deleteCharacterGoods(CharacterGoods characterGoods);

	/**
	 * 更新人物物品
	 * 
	 * @param characterGoods
	 */
	public abstract void updateCharacterGoods(CharacterGoods characterGoods);

	/**
	 * 返回包裹中指定物品的数量
	 * 
	 * @param goodModelid
	 * @return
	 */
	public abstract int getBagGoodsCountByModelID(int goodModelid);

	/**
	 * 返回包裹中指定物品的数量
	 * 
	 * @param goodModelid
	 * @return
	 */
	public abstract int getBagGoodsCountByModelID(int goodModelid, boolean bind);

	/**
	 * 添加到包裹中
	 * 
	 * @param goodslist
	 * @return
	 */

	public boolean addGoodsToBag(Collection<CharacterGoods> goodslist);

	/**
	 * 添加到包裹时，不修改goods对像本身，内部实现使用goods的克隆对像，返回成功或失败 方法内部会进行消息通知
	 * 
	 * @param goods
	 */
	public boolean addGoodsToBag(CharacterGoods goods);

	/**
	 * 
	 * @param modelid
	 * @param deletecount
	 */
	public boolean deleteGoodsFromBag(int modelid, int deletecount);

	/**
	 * 
	 * @param modelid
	 * @param deletecount
	 */
	public boolean deleteGoodsFromBag(int modelid, int deletecount, boolean bind);

	/**
	 * 拆分物品
	 * 
	 * @param oldpostion
	 * @param splitcount
	 */
	public void splitGoods(short oldpostion, int splitcount);

	/**
	 * 获取物品冷却时间管理器
	 * 
	 * @return
	 */
	public CharacterGoodsCoolingTimeManager getCoolingTimeManager();

	/**
	 * 包裹内是否有足够num数量的物品(包含可叠加不可叠加的计算)
	 * 
	 * @param goodModelId
	 *            物品
	 * @param num
	 *            该物品数量
	 * @return
	 */
	public boolean isEnoughGoods(int goodModelId, int num);

	public void destory();

	/**
	 * 如果删除的材料中有绑定的，则装备也要被绑定
	 * @param equipment
	 * @param bind
	 * @param cailiaoNum
	 * @param cailiao
	 * @return
	 */
	public boolean combineDeleteCailiao(CharacterGoods equipment, boolean bind, int cailiaoNum, int... cailiao);

	/**
	 * 先扣除绑定的材料，再扣除不绑定的材料
	 * 
	 * @param characterGoodController
	 * @param cailiaoModel
	 * @param cailiaoNum
	 */
	public boolean deleteCailiao(int cailiaoModel, int totalNum);

	/**
	 * 根据位置删除道具
	 * @param characterGoods
	 * @param totalNum
	 */
	public void deleteCailiaoByPos(CharacterGoods characterGoods, int totalNum);
}
