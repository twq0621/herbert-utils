package net.snake.gamemodel.hero.logic;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.snake.GameServer;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeMsg10840;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.CharacterTaskExcuter;
import net.snake.serverenv.stall.OnlineStallManagerImp;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

public class CharacterDownLineManager {
	private static Logger logger = Logger.getLogger(CharacterDownLineManager.class);

	private static final Object DOWNLINELOCK = new Object();
	private Hero character;
	private volatile boolean startdownline = false;
	private ConcurrentLinkedQueue<Runnable> afterdownlinelist = new ConcurrentLinkedQueue<Runnable>();

	public CharacterDownLineManager(Hero character) {
		this.character = character;
	}

	/** 在场景中超时两分钟了，强制下线，忽略其他 startdownline状态，再调用一次 */
	public void sceneTimeoutDownLine() {
		// 无论如何都设成false
		startdownline = false;
		character.getVlineserver().addTaskInFrameUpdateThread(new FrameUpdateRunable());
	}

	/**
	 * 在游戏循环中安排一次任务。<br>
	 * 移除角色,网络连接和应用会话脱钩,网络连接关闭，各种数据的保存。
	 * 
	 * @param afterDownLine
	 */
	public void downLine(Runnable afterDownLine) {
		synchronized (DOWNLINELOCK) {
			if (afterDownLine != null) {
				afterdownlinelist.offer(afterDownLine);
			}
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new FrameUpdateRunable());
	}

	public void saveToDb() {
		// ---保存当日收益
		try {
			character.getDayInCome().toPersistence();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// -------人物下线保存人物获奖记录
		// logger.info("退出游戏时，游戏数据的保存" + character.getName());
		// 保存人物宝箱数据
		try {
			character.getMyChestManager().save();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 保存人物图片信息
		// try {
		// character.getMyCharacterPhotoManager().save();
		// } catch (Exception e) {
		// logger.error(e, e);
		// }
		// 马的保存
		try {
			character.getCharacterHorseController().updateCharacterHorse();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getEffectController().save();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getFightController().whenDownLine();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getSkillManager().save();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getDanTianController().toPersistence();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getDgwdController().toPersistence();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getDujieCtrlor().save2DB();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			// 增加在线时间
			character.setLastdate(new Date());
			character.setOnlinedate(character.getOnlinedate() + (character.getLastdate().getTime() - character.getLastLogindate().getTime()));
			character.setIsonline(CommonUseNumber.byte0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			CharacterTaskExcuter.getInstance().delAndExcuterTask(character);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			if (!Options.IsCrossServ) {
				CharacterManager.getInstance().cacheToDB(character);
				logger.info(character.getViewName() + ",hero id is "+character.getId()+": exits game server save data to db suncess");
			} else {
				character.getMyCharacterAcrossIncomeManager().cacheInComeToDb();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			character.getAccount().setOnline(0);
			AccountManager.getInstance().markOnline(character.getAccount());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void LeaveSceneProcess() {
		try {
			Scene scene = character.getSceneRef();
			if (scene != null) {
				scene.leaveScene(character, null);
			}
			InstanceController instanceC = character.getMyCharacterInstanceManager().getInstanceController();
			if (instanceC != null) {
				instanceC.onleaveInstanceWhenDownLine(character);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 交易状态中玩家下线处理
	 */
	private void downLineWhenTradeing() {
		try {
			// 处于交易中的双方玩家在开启交易界面窗口之后某一方离线或掉线，交易窗口自动关闭，交易失败；
			IMyTradeController mtc = character.getMyTradeController();
			if (!mtc.isTrading()) {
				return;
			}
			Hero trader = mtc.getTradeCharacter();
			mtc.cancelTrade();
			trader.sendMsg(new TradeMsg10840(13501));
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}
	}

	/**
	 * 玩家下线组队处理
	 */
	private void playerDownLineWhenTeam() {
		try {
			character.getMyTeamManager().playerDownLineWhenTeam();
		} catch (Exception e) {
			logger.error("broadcast hero offline with err", e);
		}
	}

	/**
	 * 此类在线程池中执行
	 */
	private class ClearCharacterInfoExcuterRunable implements Runnable {
		@Override
		public void run() {
			try {
				saveToDb();
				synchronized (DOWNLINELOCK) {
					startdownline = false;
					for (Runnable run = afterdownlinelist.poll(); run != null; run = afterdownlinelist.poll()) {
						try {
							run.run();
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
					// 发送日志
					StringBuffer itemInfo = new StringBuffer("{");
					Collection<CharacterGoods> cgs = character.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
					for (CharacterGoods cg : cgs) {
						itemInfo.append("\"" + cg.getGoodmodelId() + "\":" + cg.getCount() + ",");
					}
					cgs = character.getCharacterGoodController().getBodyGoodsList();
					for (CharacterGoods cg : cgs) {
						itemInfo.append("\"" + cg.getGoodmodelId() + "\":" + cg.getCount() + ",");
					}
					cgs = character.getCharacterGoodController().getStallGoodsContainer().getGoodsList();
					for (CharacterGoods cg : cgs) {
						itemInfo.append("\"" + cg.getGoodmodelId() + "\":" + cg.getCount() + ",");
					}
					cgs = character.getCharacterGoodController().getStorageGoodsContainer().getGoodsList();
					for (CharacterGoods cg : cgs) {
						itemInfo.append("\"" + cg.getGoodmodelId() + "\":" + cg.getCount() + ",");
					}
					String temp = null;
					if (itemInfo.lastIndexOf(",") != -1) {
						temp = itemInfo.substring(0, itemInfo.length() - 1) + "}";
					} else {
						temp = "{}";
					}
					GameServer.dataLogManager.logInventory(temp, character.getLogUid());
					GameServer.characterGCManager.addCharacterToGC(character);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	// ---------------------------------------内部类FrameUpdateRunable
	/** 此类在游戏循环中执行 **/
	private class FrameUpdateRunable implements Runnable {
		@Override
		public void run() {
			synchronized (DOWNLINELOCK) {
				// 防止多种事件同时调用下线操作
				if (startdownline) {
					return;
				} else {
					startdownline = true;
				}
			}

			try {
				GameServer.vlineServerManager.removeCharacterById(character.getId());
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}

			// 防止多次调用
			GamePlayer gp = character.getPlayer();
			if (gp != null) {
				gp.removeIOSessionMap();
				gp.close();
			}
			// GameServer.changeLineManager.removeChangeLineCharacter(character
			// .getId());
			try {
				character.getMyFriendManager().downLine();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				character.getMyFactionManager().downline();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			// try {
			// character.getMyCharacterGiftpacksManger().updateWhenDownLine(); //TODO 礼包，暂时注释
			// } catch (Exception e1) {
			// logger.error(e1.getMessage(), e1);
			// }
			try {
				character.getAntiAddictedSystem().downLine();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				character.getCharacterGoodController().getCoolingTimeManager().downLine();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				character.getMyFactionCityZhengDuoManager().dropYoulongToScene(null);
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				character.getMycharacterAcrossZhengzuoManager().dropXuanyuanToScene(null);
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				character.getMyDazuoManager().endShuangXiuWhenDownLine();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			try {
				playerDownLineWhenTeam();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			downLineWhenTradeing();
			try {
				OnlineStallManagerImp.getInstance().removeFromStallList(character);
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			LeaveSceneProcess();
			// 通知聊天服务器角色下线
			try {
				character.getChatManager().downLine();
				// logger.error("角色下线,聊天会话关闭");
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}

			try {
				character.getBowController().clearBowZhufu();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			try {
				GameServer.executorServiceForDB.execute(new ClearCharacterInfoExcuterRunable());
			} catch (Exception e) {
				logger.error("hero offline database with err", e);
			}
		}
	}

}
