package net.snake.gamemodel.trade.logic;

import java.util.Collection;

import net.snake.commons.program.Updatable;
import net.snake.consts.TradeStatus;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;


public interface IMyTradeController extends Updatable{

	// =================================================
	/**
	 * 清空重置交易中信息 恢复为无交易状态
	 */
	public abstract void resetItem();

	/**
	 * 收到其他玩家请求交易的信息
	 */
	public abstract void onRequestTrade(Hero other);

	/**
	 * 请求与其他角色交易
	 */
	public abstract void requestTradeWith(Hero other);

	/**
	 * 锁定物品
	 */
	public abstract void lockTrade();

	/** 解锁物品*/
	public abstract void unLockTrade();

	/**
	 * 同意交易
	 */
	public abstract void trade();

/**
 * 取消交易,当一些事情发生时
 */
	public abstract void cancelTrade();

   /**
    * 添加用于交易的坐骑
    * @param horse
    */
	public abstract void setHorse(Horse horse);
  /**
   * 添加用于交易的铜币
   * @param copper
   */
	public abstract void setCopper(int copper);
/**
 * 添加用于交易的元宝
 * @param ingot
 */
	public abstract void setYuanbao(int ingot);


	/**
	 * 是不是处在交易中 返回true表示正在交易中
	 * 
	 * @return
	 */
	public boolean isTrading();

	/**
	 * 建立交易通道
	 * 
	 * @param other
	 * @return
	 */
	public boolean createTrading(Hero other);

	/**
	 * 建立交易行为初始化管理器信息
	 * 
	 * @param other
	 */
	public void createTradeInit(Hero other);
  /**
   * 获取玩家交易状态
   * @return
   */
	public TradeStatus getTradeStatus();
   /**
    * 获取用于交易的坐骑
    * @return
    */
	public Horse getHorse();
    /**
     * 获取用于交易的铜币
     * @return
     */
	public int getCopper();
   /**
    * 获取用于交易的元宝
    * @return
    */
	public int getYuanbao();

	/**
	 * 获取用于交易的物品集合
	 * 
	 * @return
	 */
	public Collection<CharacterGoods> getGoodsCollection();

	/**
	 * 包裹中删除用于交易的物品（交易成功调用此方法）
	 */
	public void deleteTradeGoods();

	/**
	 * 将自己交易的物品（包含交易物品 交易坐骑 交易金钱）给对方交易者，交易成功调用此方法
	 */
	public void changeGoodsToTradeCharacter();

	/**
	 * 交易物品（包含交易物品 交易坐骑 交易金钱）返还给角色包裹中（交易失败掉用此方法）
	 */
	public void tradeGoodToBagPack();

	/**
	 * 获取交易玩家
	 * 
	 * @return
	 */
	public Hero getTradeCharacter();

	/**
	 * 随机选择位置存放
	 * 
	 * @return
	 */
	public int findTradIndex();

	/**
	 * 添加交易物品
	 * 
	 * @param tradeIndex
	 * @param cg
	 */
	public void putGoodsToTradeMap(int tradeIndex, CharacterGoods cg);

	/**
	 * 获取某位置交易物品
	 * 
	 * @param tradeIndex
	 * @return
	 */
	public CharacterGoods getCharacterGoodsByTradeIndex(int tradeIndex);

	/**
	 * 移除交易物品
	 * 
	 * @param tradeIndex
	 */
	public void removeGoodsInTradeMap(int tradeIndex);

	/**
	 * 判断是否可以对交易物品金钱坐骑进行操作 true可以
	 * 
	 * @return
	 */
	public boolean isGoodOperate();
	/**
	 * 获取交易中的自己
	 * @return
	 */
	public Hero getCharacter();
	/**
	 * 清除向自家发送邀请的玩家的邀请记录
	 * @param otherId
	 */
	 public void removeInviteTime(int otherId);

	 
	 public int getAllObjInHeap() throws Exception;
}
