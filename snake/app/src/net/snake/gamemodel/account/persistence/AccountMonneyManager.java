package net.snake.gamemodel.account.persistence;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.consts.EffectType;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;


/**
 * 玩家帐号元宝操作管理
 * 
 */
public class AccountMonneyManager {
	private static Logger logger = Logger.getLogger(AccountMonneyManager.class);

	private Object lock = new Object();
	private Account account;

	public AccountMonneyManager(Account account) {
		this.account = account;
	}

	/**
	 * 更新数据库角色交易的元宝（减少）
	 * 
	 * @param reduceValue
	 */
	public void updateReduceTradeDbAccountYuanbao(final int reduceValue) {
		if (reduceValue < 1) {
			return;
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AccountManager.getInstance().updateTradeReduceYuanbaoById(account, reduceValue);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * 消费元宝
	 * 
	 * @param character
	 * @param reduceValue
	 * @return
	 */
	public boolean consumYuanbao(Hero character, final int reduceValue) {
		synchronized (lock) {
			if (account.getYuanbao() - reduceValue < 0) {
				return false;
			}
			account.setYuanbao(account.getYuanbao() - reduceValue);
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AccountManager.getInstance().updateConsumYuanbaoById(account, reduceValue);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
		return true;
	}

	/**
	 * 用于帐号交易类元宝变化更新
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public boolean changeTradeAddYuanbao(Hero character, final int value) {
		synchronized (lock) {
			if (value <= 0) {
				return false;
			}
			if (account.getYuanbao() + value < 0) {
				return false;
			}
			account.setYuanbao(account.getYuanbao() + value);
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AccountManager.getInstance().updateTradeAddYuanbaoById(account, value);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
		return true;
	}

	/**
	 * 用于帐号交易类元宝减少变化更新（摊位列表）
	 * 
	 * @param character
	 * @param value
	 * @return
	 */
	public boolean changeTradeReduceYuanbao(Hero character, final int value) {
		synchronized (lock) {
			if (value <= 0) {
				return false;
			}
			if (account.getYuanbao() - value < 0) {
				return false;
			}
			account.setYuanbao(account.getYuanbao() - value);
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AccountManager.getInstance().updateTradeReduceYuanbaoById(account, value);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
		return true;
	}

	/**
	 * 设置角色交易的元宝数
	 * 
	 * @param character
	 * @param tradeCount
	 * @return
	 */
	public boolean changeRoleTradeYuanbao(Hero character, int tradeCount) {
		if (tradeCount < 0) {
			return false;
		}
		synchronized (lock) {
			if (character.getMyTradeController().isTrading()) {
				int tradeYuanbao = character.getMyTradeController().getYuanbao();
				int nowyuanbao = account.getYuanbao() + tradeYuanbao;
				if (nowyuanbao < tradeCount) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 507));
					return false;
				}
				account.setYuanbao(nowyuanbao - tradeCount);
				character.getMyTradeController().setYuanbao(tradeCount);
				character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
				return true;
			}
			return false;
		}
	}

	/**
	 * 角色取消交易
	 * 
	 * @param character
	 * @param value
	 */
	public void changeCancelRoleTradeYuanbao(Hero character, int value) {
		synchronized (lock) {
			if (value <= 0) {
				return;
			}
			account.setYuanbao(account.getYuanbao() + value);
		}
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
	}

	/**
	 * 通过充值方式增加元宝
	 * 
	 * @param character
	 * @param value
	 * @param dbAccount
	 */
	public synchronized void updateAccountChongZhiYuanbao(Hero character, int value, Account dbAccount) {
		if (value < 1) {
			return;
		}
		synchronized (lock) {
			if (dbAccount == null) {
				return;
			}
			// int yuanbao = dbAccount.getYuanbao();
			// if (yuanbao < account.getYuanbao() + value) {
			// logger.info("充值处理正在进行非法操作，money异常=======================================");
			// return;
			// }
			account.setYuanbao(account.getYuanbao() + value);
			account.setRechargeYuanbao(account.getRechargeYuanbao() + value);
		}
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.ingot, account.getYuanbao()));
	}
}
