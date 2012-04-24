package net.snake.gamemodel.heroext.channel.logic;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.GenerateProbability;
import net.snake.commons.StringUtil;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Breakthrough;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.gamemodel.heroext.channel.bean.ChannelRealdragon;
import net.snake.gamemodel.heroext.channel.bean.ChannelZhenlong;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelZhenlongManager;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50204;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50206;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50206Ok;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50208;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50210;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;



public class MyChannelManager {
	private static Logger logger = Logger.getLogger(MyChannelManager.class);

	private Hero character;
	private ChannelJiSuanTools jiSuanTools;
	private Boolean notStartChongxue = true;
	private ChannelJiSuanTools jiSuanZhenLongTools;
	private int jingmaitongrenjiacheng = 0;
	/** 当前人打通多少筋脉； */
	private int datongjinmai;//
	/** 当前人打通真龙多少筋脉； */
	private int datongjinmaiZhenLong;//
	private ChannelZhenlong channelZhenlong;
	/** 失效id */
	private String failChannelId = ""; //
	/** 未失效id */
	private String noFailChannelId = ""; //
	private int failChannelIdCount = 0;

	private boolean gm = false;

	public ChannelZhenlong getChannelZhenlong() {
		return channelZhenlong;
	}

	public void setChannelZhenlong(ChannelZhenlong channelZhenlong) {
		this.channelZhenlong = channelZhenlong;
	}

	// attack(1), // 攻击力
	// defence(2), // 防御
	// crt(3), // 暴击
	// dodge(4), // 闪避
	// Hp(5), // 生命值上限
	// Sp(6),// 体力值上限
	// Mp(7),//内力上限
	public String getFailChannelId() {
		return failChannelId;
	}

	public String getNoFailChannelId() {
		noFailChannelId = "";
		if (datongjinmai <= 8 && StringUtil.isEmpty(character.getChannelRealdragon())) {
			noFailChannelId = character.getChannelJingmai();
		} else if (datongjinmaiZhenLong > 0) {
			noFailChannelId = character.getChannelRealdragon();
		}
		if (StringUtil.isNotEmpty(failChannelId)) {
			for (String str : failChannelId.split(",")) {
				noFailChannelId = noFailChannelId.replace(str + ",", "");
			}
		}

		return noFailChannelId.replace("000,", "");
	}

	/**
	 * 发送客户端失效经脉
	 * 
	 * @param id
	 *            经脉
	 * @param type
	 *            1 2 失效 3 4 回复
	 * @return
	 */
	public boolean sendMyChnanel(String id, int type) {

		if (type == 0) {
			removeListChannelProperty(Integer.valueOf(id.substring(0, 1)));
			setFailChannelId(id);
		} else if (type == 1) {
			removeListChannelRealdragonProperty(Integer.valueOf(id.substring(0, 1)));
			setFailChannelId(id);
		} else if (type == 2) {
			getListChannelProperty(Integer.valueOf(id.substring(0, 1)));
			failChannelId = failChannelId.replace(id + ",", "");
			failChannelIdCount = failChannelIdCount - 1;
		} else if (type == 3) {
			getListChannelRealdragonProperty(Integer.valueOf(id.substring(0, 1)));
			failChannelId = failChannelId.replace(id + ",", "");
			failChannelIdCount = failChannelIdCount - 1;
		}
		// try {
		// character.sendMsg(new ChannelResponse50218(character));
		// } catch (Exception e) {
		// logger.debug("失效经脉发送错误！！！！！！！");
		// }
		return true;
	}

	public boolean failMyChnanel(String id) {
		boolean type = false;
		if (datongjinmai <= 8 && StringUtil.isEmpty(character.getChannelRealdragon())) {
			type = sendMyChnanel(id, 0);
		} else if (datongjinmaiZhenLong > 0) {
			type = sendMyChnanel(id, 1);
		}

		return type;
	}

	public boolean noFailMyChnanel(String id) {
		boolean type = false;
		if (datongjinmai <= 8 && StringUtil.isEmpty(character.getChannelRealdragon())) {
			type = sendMyChnanel(id, 2);
		} else if (datongjinmaiZhenLong > 0) {
			type = sendMyChnanel(id, 3);
		}

		return type;
	}

	public void setFailChannelId(String failChannelId) {
		if (failChannelIdCount < 4) {
			if (StringUtil.isNotEmpty(failChannelId)) {
				this.failChannelId = this.failChannelId + failChannelId + ",";
				failChannelIdCount = failChannelIdCount + 1;
			}
		}

	}

	public int getFailChannelIdCount() {
		return failChannelIdCount;
	}

	private int attack, defence, crt, dodge, hp, mp, sp;
	private int attackZhenLong, defenceZhenLong, crtZhenLong, dodgeZhenLong, hpZhenLong, mpZhenLong, spZhenLong;

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getCrt() {
		return crt;
	}

	public void setCrt(int crt) {
		this.crt = crt;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getAttackZhenLong() {
		return attackZhenLong;
	}

	public void setAttackZhenLong(int attackZhenLong) {
		this.attackZhenLong = attackZhenLong;
	}

	public int getDefenceZhenLong() {
		return defenceZhenLong;
	}

	public void setDefenceZhenLong(int defenceZhenLong) {
		this.defenceZhenLong = defenceZhenLong;
	}

	public int getCrtZhenLong() {
		return crtZhenLong;
	}

	public void setCrtZhenLong(int crtZhenLong) {
		this.crtZhenLong = crtZhenLong;
	}

	public int getDodgeZhenLong() {
		return dodgeZhenLong;
	}

	public void setDodgeZhenLong(int dodgeZhenLong) {
		this.dodgeZhenLong = dodgeZhenLong;
	}

	public int getHpZhenlong() {
		return hpZhenLong;
	}

	public void setHpZhenlong(int hpZhenlong) {
		this.hpZhenLong = hpZhenlong;
	}

	public int getMpZhenlong() {
		return mpZhenLong;
	}

	public void setMpZhenlong(int mpZhenlong) {
		this.mpZhenLong = mpZhenlong;
	}

	public int getSpZhenLong() {
		return spZhenLong;
	}

	public void setSpZhenLong(int spZhenLong) {
		this.spZhenLong = spZhenLong;
	}

	public void initaddJiaCheng() {
		attack = 0;
		defence = 0;
		crt = 0;
		dodge = 0;
		hp = 0;
		mp = 0;
		sp = 0;
	}

	public void initaddJiaChengZhenLong() {
		attackZhenLong = 0;
		defenceZhenLong = 0;
		crtZhenLong = 0;
		dodgeZhenLong = 0;
		hpZhenLong = 0;
		mpZhenLong = 0;
		spZhenLong = 0;
	}

	/**
	 * @param 突破瓶颈校验
	 */
	public void addPingJing(Breakthrough breakthrough) {
		// 判断请求连续请就不处理

		if (StringUtil.isNotEmpty(failChannelId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60036));
			return;
		}

		if (notStartChongxue) {
			if (!jiSuanZhenLongTools.yanZhengBreakthrough(breakthrough, character)) {
				return;
			}
			CharacterPropertyManager.changeZhenqi(character, -breakthrough.getNeedZhenqi().intValue());
			if (!jiSuanZhenLongTools.deleteBreakthrough(breakthrough, character)) {
				return;
			}
			notStartChongxue = false;
			int addOdds = 0;
			if (gm) {
				addOdds = 10000;
			}
			if (!GenerateProbability.isGenerateToBoolean(breakthrough.getOdds().intValue() + addOdds, 10000)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60000));
				character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
				notStartChongxue = true;
				return;
			}

			// 成功
			String channel = "000,"; // 突破瓶颈
			setChannelJingmaiZhenLong(channel);
			// 恭喜您，突破经脉瓶颈成功！！！
			character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte1));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60005));
			String msg[] = { character.getViewName() };
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 60006, msg));

			// 人物更新
			character.addToBatchUpdateTask(new Runnable() {
				@Override
				public void run() {
					try {
						CharacterManager.getInstance().update(character);
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
			notStartChongxue = true;
		}
	}

	/**
	 * 普通充穴校验
	 * 
	 * @param channelid
	 * @param jingmaitongren
	 * @param c
	 */
	public void addChongXue(final Short channelid, final byte jingmaitongren, final Channel c) {

		// 判断请求连续请就不处理
		if (notStartChongxue) {
			int firstChongxue = 0;
			// 验证是不是第一次充穴
			if (jiSuanTools.yanzhengxueweione(String.valueOf(channelid))) {
				firstChongxue = 1;
			} else {
				if (!jiSuanTools.yanzhengxueweiguanxi(channelid)) {
					// 跳级学习穴位
					ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid, 1083);
					character.sendMsg(response50206);
					return;
				}
				firstChongxue = 2;

			}
			final int type2 = firstChongxue;

			jingmaitongrenjiacheng = jingmaitongren * 500;
			if (jingmaitongren > 0) {
				boolean type = character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.JinMaiTongRen, jingmaitongren);
				if (!type) {// 物品不够
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 528));
					return;
				}
			}

			if (!GenerateProbability.isGenerateToBoolean(c.getOdds().intValue() + jingmaitongrenjiacheng, 10000)) {
				deleteChnanelZhenQiWuPin(1, c.getNeedZhenqi().intValue(), null, c);
				// 失败
				ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid, 1084);
				character.sendMsg(response50206);
				return;
			}
			notStartChongxue = false;
			character.getEyeShotManager().sendMsg(new ChannelResponse50204(character, channelid));
			character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					try {
						chongxun(c, channelid, type2);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}, 10 * 1000);

			// 不是第一次充穴

		}

	}

	/**
	 * 真龙充穴校验
	 * 
	 * @param channelid
	 * @param jingmaitongren
	 * @param c
	 */
	public void addChongXue(final Short channelid, final byte jingmaitongren, final ChannelRealdragon c) {

		// 判断请求连续请就不处理
		if (notStartChongxue) {
			int nedzhenqi = c.getNeedZhenqi();
			int firstChongxue = 0;
			// 验证是不是第一次充穴
			if (jiSuanZhenLongTools.yanzhengxueweione(String.valueOf(channelid))) {
				firstChongxue = 1;
			} else {
				if (!jiSuanZhenLongTools.yanzhengxueweiguanxi(channelid)) {
					// 跳级学习穴位
					ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid, 1083);
					character.sendMsg(response50206);
					return;
				}
				firstChongxue = 2;

			}
			final int type2 = firstChongxue;

			boolean jilvjisuanqi = false;
			if (gm) {
				channelZhenlong.setCharacterChannelCount(100);
			}
			// 一定成功
			if (channelZhenlong.getCharacterChannelCount() > c.getZhenlongMax()) {
				jilvjisuanqi = true;
			}
			if (!jilvjisuanqi) {

				// 一定失败
				if (channelZhenlong.getCharacterChannelCount() < c.getZhenlongMin()) {
					deleteChnanelZhenQiWuPin(2, nedzhenqi, c, null);
					ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid, 1084);
					character.sendMsg(response50206);
					channelZhenlong.setCharacterChannelCount(channelZhenlong.getCharacterChannelCount() + 1);
					ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);
					return;
				}
				// 计算几率
				if (!GenerateProbability.isGenerateToBoolean(c.getOdds().intValue(), 10000)) {
					deleteChnanelZhenQiWuPin(2, nedzhenqi, c, null);
					ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid, 1084);
					character.sendMsg(response50206);
					channelZhenlong.setCharacterChannelCount(channelZhenlong.getCharacterChannelCount() + 1);
					ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);
					return;
				}
			}
			notStartChongxue = false;
			character.getEyeShotManager().sendMsg(new ChannelResponse50204(character, channelid));
			character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					try {
						chongXun(c, channelid, type2);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}, 10 * 1000);

			// 不是第一次充穴

		}
	}

	public boolean deleteChnanelZhenQiWuPin(int typeChnanel, int nedzhenqi, ChannelRealdragon c, Channel c2) {

		if (character.getZhenqi() < nedzhenqi) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60035));
			return false;
		}
		if (typeChnanel == 1) {
			CharacterPropertyManager.changeZhenqi(character, -nedzhenqi);
		}

		if (typeChnanel == 2) {
			CharacterPropertyManager.changeZhenqi(character, -nedzhenqi);
			if (c.getGoodmodelId() != 0) {
				boolean type = character.getCharacterGoodController().deleteGoodsFromBag(c.getGoodmodelId(), c.getGoodmodelCount());
				if (!type) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 528));
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 任务调用
	 * 
	 * @param 穴位id
	 * @return 是否打通穴位
	 */
	public boolean getJinMaiTack(String id) {
		boolean type = false;
		if (StringUtil.isNotEmpty(character.getChannelJingmai())) {
			List<String> list = Arrays.asList(character.getChannelJingmai().split(","));
			if (list.contains(id)) {
				type = true;
			}
		}
		return type;
	}

	/**
	 * @return 返回真龙经脉贯通加成的武功数量
	 */
	public int getJinMaiZhongLongAddWuGong() {
		int count = 0;
		int jinmaicount = jiSuanZhenLongTools.getCountDaTongJinmai();
		if (jinmaicount == 8) {
			count = 3;
		}
		return count;
	}

	/**
	 * gm命令打通经脉
	 * 
	 * @param channelid
	 * @param jingmaitongren
	 * @param c
	 */
	public void addChongXueGm(final Short channelid, final byte jingmaitongren, final Channel c) {

		// 判断请求连续请就不处理
		if (notStartChongxue) {
			// int nedzhenqi = c.getNeedZhenqi();
			int firstChongxue = 0;
			// 验证是不是第一次充穴
			if (jiSuanTools.yanzhengxueweione(String.valueOf(channelid))) {
				firstChongxue = 1;
			} else {
				if (!jiSuanTools.yanzhengxueweiguanxi(channelid)) {

				}
				firstChongxue = 2;

			}
			final int type2 = firstChongxue;

			jingmaitongrenjiacheng = 1000000000;

			try {
				chongxun(c, channelid, type2);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			notStartChongxue = true;

		}
	}

	public void addChongXue_Gm_ZhenLong() {

		gm = true;
	}

	/**
	 * 给人加经验
	 * 
	 * @param list
	 * @param guanTongType
	 *            0 和1 表示0充穴1贯通
	 * @param guanTongZhenLongType1
	 *            和2 表示1充穴2贯通
	 * @throws Exception
	 */
	public void addCharacterExp(Collection<Hero> list, int guanTongType, int guanTongZhenLongType) throws Exception {
		int juLiX = 10, juLiY = 10;
		if (guanTongType == 1) {
			juLiX = 20;
			juLiY = 20;
		}

		for (Hero character2 : list) {
			if (Math.abs(character2.getX() - character.getX()) < juLiX && Math.abs(character2.getY() - character.getY()) < juLiY) {
				// 自己加经验
				if (character2.getId().intValue() == character.getId().intValue()) {
					int dengji = character2.getGrade();
					dengji = dengji * dengji * dengji;
					if (guanTongType == 1) {
						dengji = 0;
					}
					if (guanTongZhenLongType == 2) {
						dengji = 0;
					}
					CharacterFormula.experienceProcess(character2, dengji);
					channelZhenlong.setChannelExp(channelZhenlong.getChannelExp() + dengji);
					ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);

					continue;
				}
				// 不是自己按照规定减去被动加经验次数
				int expcount = character2.getChannelBeidongExp().intValue();
				if (expcount - 1 >= 0) {
					int dengji = character2.getGrade();
					if (guanTongType == 1) {
						dengji = dengji * dengji * dengji;// 普通贯通波及
					} else {
						dengji = dengji * dengji * 20; // 普通波及
					}
					if (guanTongZhenLongType == 1) {
						dengji = character2.getGrade();
						dengji = dengji * dengji * 40;
					}
					if (guanTongZhenLongType == 2) {
						dengji = character2.getGrade();
						dengji = dengji * dengji * dengji * 2;
					}

					CharacterFormula.experienceProcess(character2, dengji);
					ChannelZhenlong channelZhenlong = character2.getMyChannelManager().getChannelZhenlong();
					channelZhenlong.setChannelExp(channelZhenlong.getChannelExp() + dengji);
					ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);
					character2.setChannelBeidongExp(expcount - 1);
				} else {
					// 累计每日24次的经脉冲击波共享经验加成次数已达上限
					character2.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 529));
				}
			}
		}
	}

	/**
	 * 删除场景的怪物
	 * 
	 * @param monsters
	 * @param guanTongType
	 *            0 和1 表示0充穴1贯通
	 * @param guanTongZhenLongType
	 *            1 和2 表示1充穴2贯通
	 * @throws Exception
	 */
	public void deleteSceneMonster(Collection<SceneMonster> monsters, int guanTongType, int guanTongZhenLongType) throws Exception {
		int juLiX = 10, juLiY = 10;
		if (guanTongType == 1) {
			juLiX = 20;
			juLiY = 20;
		}

		for (SceneMonster sceneMonster : monsters) {
			if (Math.abs(sceneMonster.getX() - character.getX()) < juLiX && Math.abs(sceneMonster.getY() - character.getY()) < juLiY) {
				if (sceneMonster.isDie()) {
					continue;
				}
				if (sceneMonster.isBangqiMonster()) {
					continue;
				}
				// 8为特殊怪物 宝藏使者 老鼠等
				if (sceneMonster.getMonsterModel().getType() == 8 || sceneMonster.getMonsterModel().getType() == 9) {
					continue;
				}
				if (sceneMonster.getGrade() > character.getGrade()) {
					continue;
				}
				if (guanTongType == 1) {
					sceneMonster.getEnmityManager().addEnmityValue(character, 10000);
					sceneMonster.getEnmityManager().addHurtValue(character, sceneMonster.getNowHp());
					sceneMonster.setNowHp(0);
					// 广播怪物血量
					FightMsgSender.directHurtBroadCase(character, sceneMonster, 0, CommonUseNumber.byte0);
					sceneMonster.setTarget(character);
					sceneMonster.die(character);
					continue;
				}
				// boss 的减血判断
				if (sceneMonster.getMonsterModel().isBoss() || sceneMonster.getMonsterModel().isJY()) {
					int xie = sceneMonster.getNowHp();
					xie = xie / 2;
					sceneMonster.setNowHp(xie);
					sceneMonster.getEnmityManager().addEnmityValue(character, 5);
					sceneMonster.getEnmityManager().addHurtValue(character, sceneMonster.getNowHp());
					FightMsgSender.directHurtBroadCase(character, sceneMonster, 0, CommonUseNumber.byte0);
					if (xie == 0) {
						sceneMonster.die(character);
					}
				} else {
					sceneMonster.getEnmityManager().addEnmityValue(character, 10000);
					sceneMonster.getEnmityManager().addHurtValue(character, sceneMonster.getNowHp());

					sceneMonster.setNowHp(0);
					// 广播怪物血量
					FightMsgSender.directHurtBroadCase(character, sceneMonster, 0, CommonUseNumber.byte0);
					sceneMonster.die(character);

				}

			}
		}
	}

	/**
	 * 
	 * 普通充穴
	 * 
	 * @param c
	 *            斤脉
	 * @param channelid
	 *            斤脉id
	 * @param type
	 *            区别 2的话表示不是第一次充穴
	 * @return
	 * @throws Exception
	 */
	private void chongxun(Channel c, Short channelid, int type) throws Exception {
		notStartChongxue = true;
		if (!deleteChnanelZhenQiWuPin(1, c.getNeedZhenqi().intValue(), null, c)) {
			return;
		}
		countJiaCheng(c, character);
		// 成功
		String channel = character.getChannelJingmai();
		channelZhenlong.setChannelCongxueTime((int) new Date().getTime());
		ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);
		// 区别一次充穴和第二次充穴
		if (type == 1) {
			channel = String.valueOf(channelid) + ",";
			if (character.getChannelJingmai().length() < 3) {
			} else {
				channel = character.getChannelJingmai() + channel;
			}

		} else {
			int id = channelid - 1;
			channel = channel.replace(String.valueOf(id), String.valueOf(channelid));

		}
		setChannelJingmai(channel);
		int count = character.getChannelXuewei();
		count = count + 1;
		character.setChannelXuewei(count);
		// 添加属性
		propertyController(c, character);
		// 取的当前在我附近的玩家,给玩家加经验
		Collection<Hero> list = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		addCharacterExp(list, 0, 0);

		// 取的场景中的怪物
		Collection<SceneMonster> monsters = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		deleteSceneMonster(monsters, 0, 0);

		// 恭喜您，冲穴成功，您获得了属性加成；您周围的玩家获得经验加成；您周围低于您等级的怪物被秒
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 530));
		ChannelResponse50206Ok response50206Ok = new ChannelResponse50206Ok(character.getId(), channelid, 1103);
		character.getEyeShotManager().sendMsg(response50206Ok);

		// 判断是不是整个斤脉贯通了
		if (jiSuanTools.yanZhengJinMaiGuanTong(channelid)) {

			// 添加打通经脉数量
			character.getMyChannelManager().setDatongjinmai(character.getMyChannelManager().getDatongjinmai() + 1);
			String id = jiSuanTools.getGuanTong_Id(channelid);
			String chString = jiSuanTools.getDaTongJingMai_Id(channelid);
			character.getMyChannelManager().setChannelJingmai(chString);
			Channel c2 = ChannelManager.getInstance().getCharactergradeMap().get(Integer.valueOf(id));
			// 添加属性
			propertyController(c2, character);

			// 取的当前在我附近的玩家,给玩家加经验
			Collection<Hero> list2 = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
			addCharacterExp(list2, 1, 0);

			// 取的场景中的怪物
			Collection<SceneMonster> monsters2 = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
			deleteSceneMonster(monsters2, 1, 0);
			// "恭喜您，贯通了整条经脉；您获得了属性加成；您周围的玩家获得大幅经验加成；您周围低于您等级的怪物被秒杀";
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 531));
			// 全服广播
			String msg[] = { character.getViewName(), character.getVlineserver().getLineid() + "", character.getSceneRef().getShowName(),
					+character.getX() + "，" + character.getY(), c2.getName() };
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 532, msg));
			// response.sendMsg(msg);
			// GatewayService.sendToLoginServerMsg(response);
			// 通知客户端整个经脉贯通
			ChannelResponse50208 response50208 = new ChannelResponse50208(character, id);
			character.getEyeShotManager().sendMsg(response50208);
			try {
				character.getMyCharacterAchieveCountManger().getCharacterOtherCount().jingmaiCount(datongjinmai);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			character.getDanTianController().updateFloatIco();
			// 人物更新
			character.addToBatchUpdateTask(new Runnable() {

				@Override
				public void run() {
					try {
						CharacterManager.getInstance().update(character);

					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
		}

	}

	/**
	 * 真龙充穴
	 * 
	 * @param c
	 *            真龙斤脉
	 * @param channelid
	 *            斤脉id
	 * @param type
	 *            区别 2的话表示不是第一次充穴
	 * @return
	 * @throws Exception
	 */
	private void chongXun(ChannelRealdragon c, Short channelid, int type) throws Exception {
		notStartChongxue = true;
		if (!deleteChnanelZhenQiWuPin(2, c.getNeedZhenqi().intValue(), c, null)) {
			return;
		}
		countJiaCheng(c, character);

		// 成功
		channelZhenlong.setCharacterChannelCount(0);// 充值充穴累计数量
		channelZhenlong.setChannelCongxueTime((int) new Date().getTime());
		ChannelZhenlongManager.getInstance().updateChannelZhenlong(channelZhenlong);
		String channel = character.getChannelRealdragon();
		// 区别一次充穴和第二次充穴
		if (type == 1) {
			channel = String.valueOf(channelid) + ",";
			if (character.getChannelRealdragon().length() < 3) {
			} else {
				channel = character.getChannelRealdragon() + channel;
			}

		} else {
			int id = channelid - 1;
			channel = channel.replace(String.valueOf(id), String.valueOf(channelid));

		}
		setChannelJingmaiZhenLong(channel);
		int count = character.getChannelXuewei();
		count = count + 1;
		character.setChannelXuewei(count);
		// 添加属性
		propertyController(c, character);
		// 取的当前在我附近的玩家,给玩家加经验
		Collection<Hero> list = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		addCharacterExp(list, 0, 1);

		// 取的场景中的怪物
		Collection<SceneMonster> monsters = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		deleteSceneMonster(monsters, 0, 1);

		// 恭喜您，冲穴成功，您获得了属性加成；您周围的玩家获得经验加成；您周围低于您等级的怪物被秒
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 530));
		ChannelResponse50206Ok response50206Ok = new ChannelResponse50206Ok(character.getId(), channelid, 1103);
		character.getEyeShotManager().sendMsg(response50206Ok);
		// 判断是不是整个斤脉贯通了
		if (jiSuanZhenLongTools.yanZhengJinMaiGuanTong(channelid)) {

			// 添加打通经脉数量
			character.getMyChannelManager().setDatongjinmaiZhenLong(character.getMyChannelManager().getDatongjinmaiZhenLong() + 1);

			String id = jiSuanZhenLongTools.getGuanTong_Id(channelid);
			String chString = jiSuanZhenLongTools.getDaTongJingMai_Id(channelid);
			character.getMyChannelManager().setChannelJingmaiZhenLong(chString);
			ChannelRealdragon c2 = ChannelRealdragonManager.getInstance().getCharactergradeMap().get(Integer.valueOf(id));
			// 添加属性
			propertyController(c2, character);

			// 取的当前在我附近的玩家,给玩家加经验
			Collection<Hero> list2 = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
			addCharacterExp(list2, 1, 2);
			// 取的场景中的怪物
			Collection<SceneMonster> monsters2 = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
			deleteSceneMonster(monsters2, 1, 2);
			// "恭喜您，贯通了整条经脉；您获得了属性加成；您周围的玩家获得大幅经验加成；您周围低于您等级的怪物被秒杀";
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 531));
			// 全服广播
			String msg[] = { character.getViewName(), character.getVlineserver().getLineid() + "", character.getSceneRef().getShowName(),
					+character.getX() + "，" + character.getY(), c2.getName() };
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 532, msg));
			// response.sendMsg(msg);
			// GatewayService.sendToLoginServerMsg(response);
			// 通知客户端整个经脉贯通
			ChannelResponse50208 response50208 = new ChannelResponse50208(character, id);
			character.getEyeShotManager().sendMsg(response50208);
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().zhenlongJingmaiCount(datongjinmaiZhenLong);
			// 人物更新
			if (datongjinmaiZhenLong == 8) {
				character.getCharacterSkillManager().sendUpdateSkillShowMsg();
			}
			character.getDanTianController().updateFloatIco();
			character.addToBatchUpdateTask(new Runnable() {

				@Override
				public void run() {
					try {
						CharacterManager.getInstance().update(character);
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
		}

	}

	public ChannelJiSuanTools getJiSuanTools() {
		return jiSuanTools;
	}

	public ChannelJiSuanTools getJiSuanZhenLongTools() {
		return jiSuanZhenLongTools;
	}

	public MyChannelManager(Hero character) {
		this.character = character;
	}

	public void setChannelJingmai(String channelJingmai) {
		character.setChannelJingmai(channelJingmai);
		initData();

	}

	public void setChannelJingmaiZhenLong(String channelJingmai) {
		character.setChannelRealdragon(channelJingmai);
		initData();

	}

	public void initDataZhenLong() {
		try {
			channelZhenlong = ChannelZhenlongManager.getInstance().selecteChannelZhenlongByCharacterId(character.getId());
			if (null == channelZhenlong) {
				channelZhenlong = new ChannelZhenlong();
				channelZhenlong.setCharacterChannelCount(0);
				channelZhenlong.setCharacterId(character.getId());
				channelZhenlong.setChannelCongxueTime(0);
				channelZhenlong.setChannelExp(0);
				ChannelZhenlongManager.getInstance().addChannelZhenlong(channelZhenlong);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void initData() {
		try {
			jiSuanTools = new ChannelJiSuanTools(character.getChannelJingmai());
			jiSuanZhenLongTools = new ChannelJiSuanTools(character.getChannelRealdragon());
			datongjinmai = jiSuanTools.getCountDaTongJinmai();
			datongjinmaiZhenLong = jiSuanZhenLongTools.getCountDaTongJinmai();
			upDateCharacterXunWeiJingYan();
			upDateCharacterXunWeiJingYanZhenLong();
			countCharacterXunWeiJingYan();
			countCharacterXunWeiJingYanZhenLong();
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().jingmaiCount(datongjinmai);
			character.getMyCharacterAchieveCountManger().getCharacterOtherCount().zhenlongJingmaiCount(datongjinmaiZhenLong);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public int getDatongjinmaiZhenLong() {
		return datongjinmaiZhenLong;
	}

	public void setDatongjinmaiZhenLong(int datongjinmaiZhenLong) {
		this.datongjinmaiZhenLong = datongjinmaiZhenLong;
	}

	public void setDatongjinmai(int datongjinmai) {
		this.datongjinmai = datongjinmai;
	}

	public int getDatongjinmai() {
		return datongjinmai;
	}

	public String getJinMaiDaTongWeiZhi() {
		return jiSuanTools.getJinmaiLieBiao();
	}

	// 真龙经脉
	public void getListChannelRealdragonProperty(int id) {
		List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(id);
		for (ChannelRealdragon channel : listChannel) {
			propertyController(channel, character);
		}
	}

	// 移除真龙经脉
	public void removeListChannelRealdragonProperty(int id) {
		List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(id);
		for (ChannelRealdragon channel : listChannel) {
			removePropertyController(channel, character);
		}
	}

	// 普通经脉
	public void getListChannelProperty(int id) {
		List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(id);
		for (Channel channel : listChannel) {
			propertyController(channel, character);
		}
	}

	// 移除普通经脉
	public void removeListChannelProperty(int id) {
		List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(id);
		for (Channel channel : listChannel) {
			removePropertyController(channel, character);
		}
	}

	public void upDateCharacterXunWeiJingYanZhenLong() {
		String channelidString = "";

		channelidString = character.getChannelRealdragon();
		if ("".equals(channelidString) || channelidString.length() < 3) {

		} else {
			String chString[] = channelidString.split(",");
			for (String string : chString) {
				if ("000".equals(string)) {
					continue;
				}
				if ("101".equals(string)) {
					getListChannelRealdragonProperty(1);
				} else if ("201".equals(string)) {
					getListChannelRealdragonProperty(2);
				} else if ("301".equals(string)) {
					getListChannelRealdragonProperty(3);
				} else if ("401".equals(string)) {
					getListChannelRealdragonProperty(4);
				} else if ("501".equals(string)) {
					getListChannelRealdragonProperty(5);
				} else if ("601".equals(string)) {
					getListChannelRealdragonProperty(6);
				} else if ("701".equals(string)) {
					getListChannelRealdragonProperty(7);
				} else if ("801".equals(string)) {
					getListChannelRealdragonProperty(8);
				} else {
					String id = string.substring(0, 1);
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
					int size = Integer.valueOf(string.substring(1, 3));

					for (int i = 1; i < size; i++) {
						ChannelRealdragon channel = listChannel.get(i);
						propertyController(channel, character);
					}

				}

			}

		}

	}

	// 普通经脉
	public void upDateCharacterXunWeiJingYan() {
		String channelidString = "";

		channelidString = character.getChannelJingmai();
		if ("".equals(channelidString) || channelidString.length() < 3) {

		} else {
			String chString[] = channelidString.split(",");
			for (String string : chString) {

				if ("101".equals(string)) {
					getListChannelProperty(1);
				} else if ("201".equals(string)) {
					getListChannelProperty(2);
				} else if ("301".equals(string)) {
					getListChannelProperty(3);
				} else if ("401".equals(string)) {
					getListChannelProperty(4);
				} else if ("501".equals(string)) {
					getListChannelProperty(5);
				} else if ("601".equals(string)) {
					getListChannelProperty(6);
				} else if ("701".equals(string)) {
					getListChannelProperty(7);
				} else if ("801".equals(string)) {
					getListChannelProperty(8);
				} else {
					String id = string.substring(0, 1);
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
					int size = Integer.valueOf(string.substring(1, 3));

					for (int i = 1; i < size; i++) {
						Channel channel = listChannel.get(i);
						propertyController(channel, character);
					}

				}

			}

		}

	}

	// 真龙经脉
	public void countCharacterXunWeiJingYanZhenLong() {
		String channelidString = "";
		initaddJiaChengZhenLong();
		channelidString = character.getChannelRealdragon();
		if ("".equals(channelidString) || channelidString.length() < 3) {

		} else {
			String chString[] = channelidString.split(",");
			for (String string : chString) {
				if ("000".equals(string)) {
					continue;
				}
				if ("101".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(1);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}
				} else if ("201".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(2);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("301".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(3);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("401".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(4);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("501".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(5);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("601".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(6);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("701".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(7);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("801".equals(string)) {
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(8);
					for (ChannelRealdragon channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else {
					String id = string.substring(0, 1);
					List<ChannelRealdragon> listChannel = ChannelRealdragonManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
					int size = Integer.valueOf(string.substring(1, 3));

					for (int i = 1; i < size; i++) {
						ChannelRealdragon channel = listChannel.get(i);
						countJiaCheng(channel, character);
					}

				}

			}

		}

	}

	// 普通经脉
	public void countCharacterXunWeiJingYan() {
		String channelidString = "";
		initaddJiaCheng();
		channelidString = character.getChannelJingmai();
		if ("".equals(channelidString) || channelidString.length() < 3) {

		} else {
			String chString[] = channelidString.split(",");
			for (String string : chString) {

				if ("101".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(1);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}
				} else if ("201".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(2);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("301".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(3);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("401".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(4);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("501".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(5);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("601".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(6);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("701".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(7);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else if ("801".equals(string)) {
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(8);
					for (Channel channel : listChannel) {
						countJiaCheng(channel, character);
					}

				} else {
					String id = string.substring(0, 1);
					List<Channel> listChannel = ChannelManager.getInstance().getChannelIdQuFenMap().get(Integer.valueOf(id));
					int size = Integer.valueOf(string.substring(1, 3));

					for (int i = 1; i < size; i++) {
						Channel channel = listChannel.get(i);
						countJiaCheng(channel, character);
					}

				}

			}

		}

	}

	public void countJiaCheng(ChannelRealdragon c, Hero character) {
		attackZhenLong = attackZhenLong + c.getAttackAdd();
		defenceZhenLong = defenceZhenLong + c.getDefenceAdd();
		crtZhenLong = crtZhenLong + c.getCrtAdd();
		dodgeZhenLong = dodgeZhenLong + c.getDodgeAdd();
		hpZhenLong = hpZhenLong + c.getHpAdd();
		spZhenLong = spZhenLong + c.getSpAdd();
		mpZhenLong = mpZhenLong + c.getMpAdd();
	}

	public void countJiaCheng(Channel c, Hero character) {
		attack = attack + c.getAttackAdd();
		defence = defence + c.getDefenceAdd();
		crt = crt + c.getCrtAdd();
		dodge = dodge + c.getDodgeAdd();
		hp = hp + c.getHpAdd();
		sp = sp + c.getSpAdd();
		mp = mp + c.getMpAdd();
	}

	public void propertyController(ChannelRealdragon c, Hero character) {
		// 添加属性效果
		character.getPropertyAdditionController().addChangeListener(c);
	}

	public void propertyController(Channel c, Hero character) {
		// 添加属性效果
		character.getPropertyAdditionController().addChangeListener(c);
	}

	public void removePropertyController(ChannelRealdragon c, Hero character) {
		// 取消属性效果
		character.getPropertyAdditionController().removeChangeListener(c);
	}

	public void removePropertyController(Channel c, Hero character) {
		// 取消属性效果
		character.getPropertyAdditionController().removeChangeListener(c);
	}

	public int getChannelBebuffId(String id) {
		int a = 0;
		if (datongjinmai <= 8 && StringUtil.isEmpty(character.getChannelRealdragon())) {
			Channel c = ChannelManager.getInstance().getCharactergradeMap().get(Integer.valueOf(id));
			if (null != c) {
				a = c.getDebuffId();
			}
		} else if (datongjinmaiZhenLong > 0) {
			ChannelRealdragon c = ChannelRealdragonManager.getInstance().getCharactergradeMap().get(Integer.valueOf(id));
			if (null != c) {
				a = c.getDebuffId();
			}
		}
		return a;
	}

}
