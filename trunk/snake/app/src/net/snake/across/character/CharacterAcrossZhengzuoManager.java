/**
 * 
 */
package net.snake.across.character;

import net.snake.across.character.msg.KuafuzhanTishiMsg51150;
import net.snake.across.character.msg.XuanyuanjianBelongToMsg51152;
import net.snake.across.faction.AcrossFactionContoller;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.NetTool;
import net.snake.consts.BuffId;
import net.snake.consts.GoodItemId;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.XuanyuanBufferJiacheng;
import net.snake.gamemodel.fight.bean.XuanyuanjianConfig;
import net.snake.gamemodel.fight.persistence.XuanyuanjianConfigManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.hero.AvatarChange60000;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * @author serv_dev
 */

public class CharacterAcrossZhengzuoManager {
	private Hero character;
	private long startTime = 0;
	private SceneXuanyuanMonster monster;
	private XuanyuanjianBufferProperty xuanyauanBangzhuBuffer;
	private XuanyuanjianBufferProperty xuanyauanKangzhuBuffer;
	private int catchXuanYuanJiacheng = 1;
	private EffectInfo kuafuExpBuff;// 跨服战区域获得经验的buff
	private EffectInfo kuafuFuHuoBuff;// 跨服战区域复活的buff
	private int regionContinuumExp = 1;
	private static final Logger logger = Logger.getLogger(CharacterAcrossZhengzuoManager.class);

	public int getRegionContinuumExp() {
		return regionContinuumExp;
	}

	public void setRegionContinuumExp(int regionContinuumExp) {
		this.regionContinuumExp = regionContinuumExp;
	}

	public EffectInfo getKuafuFuHuoBuff() {
		return kuafuFuHuoBuff;
	}

	public void setKuafuFuHuoBuff(EffectInfo kuafuFuHuoBuff) {
		this.kuafuFuHuoBuff = kuafuFuHuoBuff;
	}

	public EffectInfo getKuafuExpBuff() {
		return kuafuExpBuff;
	}

	public void setKuafuExpBuff(EffectInfo kuafuExpBuff) {
		this.kuafuExpBuff = kuafuExpBuff;
	}

	public void onLeaveKuafuTsMap(Hero killer) {
		dropXuanyuanToScene(killer);
		character.getCatchXuanyuanJianActionController().breakCatch();
	}

	public void init() {
		startTime = 0;
	}

	// 轩辕剑
	private CharacterGoods xuanyuanJian;

	/**
	 * 验证是否持有轩辕剑 只在原服务器使用
	 * 
	 * @param ai
	 * @return
	 */
	private boolean isXuanyuan(AcrossIncome ai) {
		if (ai == null || ai.getXuanyuanjian() < 1) {
			return false;
		} else {
			if (ai.getXuanyuanjianTime() == null) {
				return false;
			} else if (ai.getXuanyuanjianTime().getTime() < System.currentTimeMillis()) {
				return false;
			} else {
				return true;
			}
		}
	}

	public CharacterGoods getXuanyuanjian() {
		return xuanyuanJian;
	}

	/**
	 * 获得论剑台复活保护时间buff
	 */
	public void addFuHuoProtectBuffWhenDie() {
		// if (kuafuFuHuoBuff == null) {
		SkillEffect skillEffect = SkillEffectManager.getInstance().getSkillEffectById(BuffId.lunjiantaifuhuobaohu_Buff);
		EffectInfo effectInfo = new EffectInfo(skillEffect);
		effectInfo.setAffecter(character);
		effectInfo.setAttacker(character);
		if (character.getEffectController().addEffect(effectInfo)) {
			kuafuFuHuoBuff = effectInfo;
		}
		// }
	}

	/**
	 * 移除论剑台复活保护时间buff
	 */
	public void removeFuHuoProtectBuff() {
		try {
			if (kuafuFuHuoBuff != null) {
				character.getEffectController().removeEffect(kuafuFuHuoBuff);
				kuafuFuHuoBuff = null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 跨服战复活保护时间buff变更
	 */
	private void updateKuaFuZhanChangFuHuoBuff() {
		if (kuafuFuHuoBuff != null) {
			Scene map = character.getSceneRef();
			int[][] fuhuoTiles = map.getFuhuoTiles();
			int isfuhuo = fuhuoTiles[character.getX()][character.getY()];
			if (isfuhuo == 0) {// 不是复活区
				removeFuHuoProtectBuff();
			}
		}
	}

	/**
	 * 跨服战经验buff变更
	 */
	private void updateKuaFuZhanChangExpBuff() {
		EffectInfo kuafuExpBuff = getKuafuExpBuff();
		if (kuafuExpBuff == null) {
			Scene map = character.getSceneRef();
			int[][] expTiles = map.getExpTiles();
			int exp = expTiles[character.getX()][character.getY()];
			if (exp == 10) {
				kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp10RegionBuff));
				kuafuExpBuff.setAffecter(character);
				kuafuExpBuff.setAttacker(character);
				setKuafuExpBuff(kuafuExpBuff);
				regionContinuumExp = 10;
				FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
			} else if (exp == 20) {
				kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp20RegionBuff));
				kuafuExpBuff.setAffecter(character);
				kuafuExpBuff.setAttacker(character);
				setKuafuExpBuff(kuafuExpBuff);
				FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
				regionContinuumExp = 20;
			} else if (exp == 30) {
				kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp30RegionBuff));
				kuafuExpBuff.setAffecter(character);
				kuafuExpBuff.setAttacker(character);
				setKuafuExpBuff(kuafuExpBuff);
				FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
				regionContinuumExp = 30;
			}
		} else {
			int buffId = kuafuExpBuff.getId();
			int curExp = 0;
			switch (buffId) {
			case BuffId.exp10RegionBuff:
				curExp = 10;
				break;
			case BuffId.exp20RegionBuff:
				curExp = 20;
				break;
			case BuffId.exp30RegionBuff:
				curExp = 30;
				break;
			default:
				break;
			}

			if (curExp > 0) {
				Scene map = character.getSceneRef();
				int[][] expTiles = map.getExpTiles();
				int exp = expTiles[character.getX()][character.getY()];
				if (exp != curExp) {// 更换buff
					try {
						regionContinuumExp = 1;
						FightMsgSender.sendCancelSustainEffectMsg(character, kuafuExpBuff);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					if (exp == 10) {
						kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp10RegionBuff));
						kuafuExpBuff.setAffecter(character);
						kuafuExpBuff.setAttacker(character);
						setKuafuExpBuff(kuafuExpBuff);
						FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
						regionContinuumExp = 10;
					} else if (exp == 20) {
						kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp20RegionBuff));
						kuafuExpBuff.setAffecter(character);
						kuafuExpBuff.setAttacker(character);
						setKuafuExpBuff(kuafuExpBuff);
						FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
						regionContinuumExp = 20;
					} else if (exp == 30) {
						kuafuExpBuff = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.exp30RegionBuff));
						kuafuExpBuff.setAffecter(character);
						kuafuExpBuff.setAttacker(character);
						setKuafuExpBuff(kuafuExpBuff);
						FightMsgSender.broastSustainEffect(kuafuExpBuff, character);
						regionContinuumExp = 30;
					}
				}
			}

		}
	}

	public CharacterAcrossZhengzuoManager(Hero character) {
		this.character = character;
	}

	public void online() {
		if (!Options.IsCrossServ) {
			AcrossIncome ai = character.getMyCharacterAcrossIncomeManager().getAi();
			if (ai == null) {
				return;
			}
			if (isXuanyuan(ai)) {
				XuanyuanjianConfig xuanyuanConfig = XuanyuanjianConfigManager.getInstance().getXuanjianList().get(0);
				addBangzhuXuanyuanBuffer(xuanyuanConfig);
				character.getMyCharacterAchieveCountManger().getFactionCount().tianxiaDiyiCount();
			}
			return;
		}
		XuanyuanjianBufferProperty factionBuffer = character.getMyFactionManager().getAcrossFactionController().getXuanyuanFactionBuffer();
		addXuanYuanFactionBuffer(factionBuffer);
		if (startTime < 1000) {
			startTime = System.currentTimeMillis();
		}
	}

	private int count;

	public void update(KuafuZhanTsMap kuafuZhanTsMap) {
		count++;
		if (count % 20 != 0) {
			return;
		}
		updateKuaFuZhanChangFuHuoBuff();
		updateKuaFuZhanChangExpBuff();
		if (count % 40 != 0) {
			return;
		}
		character.sendMsg(new KuafuzhanTishiMsg51150(character, kuafuZhanTsMap.getXuanyuanjian()));
	}

	public void onMeCatchXuanyuanjian(SceneXuanyuanMonster sceneXuanyuanMonster, KuafuZhanTsMap kuafuZhanTsMap) {
		dropXuanyuanToScene(null);
		try {
			character.getMycharacterAcrossZhengzuoManager().removeFuHuoProtectBuff();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		this.monster = sceneXuanyuanMonster;
		XuanyuanjianConfig xuanyuanConfig = sceneXuanyuanMonster.getXuanjianConfig();
		addBangzhuXuanyuanBuffer(xuanyuanConfig);
		// addBangzhuXuanyuanKangzhuBuffer(xuanyuanConfig);
		character.getMyFactionManager().getAcrossFactionController().updateFactionXuanyuanJianBuffer(kuafuZhanTsMap, character);
		NetTool.sendToCharacters(character.getVlineserver().getOnlineManager().getCharacterList(), new XuanyuanjianBelongToMsg51152(kuafuZhanTsMap.getXuanyuanjian()));
		NetTool.sendToCharacters(character.getVlineserver().getOnlineManager().getCharacterList(), new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14702, character.getViewName(),
				monster.getXuanjianConfig().getXuanyuanjianName()));
	}

	/**
	 * 帮主添加轩辕剑buffer
	 * 
	 * @param xuanyuanConfig
	 */
	private void addBangzhuXuanyuanBuffer(XuanyuanjianConfig xuanyuanConfig) {
		if (xuanyauanBangzhuBuffer != null) {
			takeOffBangzhuXuanyuanjianBuffer();
		}
		takeOffXuanYuanFactionBuffer();
		xuanyuanJian = CharacterGoods.createCharacterGoods(1, xuanyuanConfig.getGoodId(), 0);
		XuanyuanBufferJiacheng bangzhuBuffer = xuanyuanConfig.getXuanyuanJianBangzhuBuffer();
		xuanyauanBangzhuBuffer = new XuanyuanjianBufferProperty(bangzhuBuffer, PropertyAdditionType.xuanyuanbangzhu);
		catchXuanYuanJiacheng = xuanyuanConfig.getBangzhuExp();
		try {
			character.getPropertyAdditionController().addChangeListener(xuanyauanBangzhuBuffer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		character.sendMsg(new AvatarChange60000(character, xuanyuanConfig.getGoodId(), true));
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, xuanyuanConfig.getGoodId(), true), character);
		FightMsgSender.broastSustainEffect(xuanyauanBangzhuBuffer.getBuffer(), character);
	}

	/**
	 * 帮主添加帮主扛主bufffer
	 * 
	 * @param xuanyuanConfig
	 */
	protected void addBangzhuXuanyuanKangzhuBuffer(XuanyuanjianConfig xuanyuanConfig) {
		if (xuanyauanKangzhuBuffer != null) {
			takeOffBangzhuXuanyuanjianKangzhuBuffer();
			return;
		}
		XuanyuanBufferJiacheng bangzhuBuffer = xuanyuanConfig.getXuanyuanJianKangzhuBuffer();
		xuanyauanKangzhuBuffer = new XuanyuanjianBufferProperty(bangzhuBuffer, PropertyAdditionType.xuanyuanbangzhu_kangzhu);
		character.getPropertyAdditionController().addChangeListener(xuanyauanKangzhuBuffer);
		FightMsgSender.broastSustainEffect(xuanyauanKangzhuBuffer.getBuffer(), character);
	}

	public boolean isXuanyuanShenjian() {
		if (xuanyuanJian == null) {
			return false;
		}
		if (xuanyuanJian.getGoodmodelId() == GoodItemId.xuanyuanShenjianId) {
			return true;
		}
		return false;
	}

	/**
	 * 移除帮主扛主buffer
	 */
	private void takeOffBangzhuXuanyuanjianKangzhuBuffer() {
		if (xuanyauanKangzhuBuffer == null) {
			return;
		}
		try {
			character.getPropertyAdditionController().removeChangeListener(xuanyauanKangzhuBuffer);
			FightMsgSender.sendCancelSustainEffectMsg(character, xuanyauanKangzhuBuffer.getBuffer());
			this.xuanyauanKangzhuBuffer = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void takeOffBangzhuXuanyuanjianBuffer() {
		if (xuanyauanBangzhuBuffer == null) {
			return;
		}
		try {
			character.getPropertyAdditionController().removeChangeListener(xuanyauanBangzhuBuffer);
			if (xuanyuanJian != null) {
				character.getEyeShotManager().sendMsg(new AvatarChange60000(character, xuanyuanJian.getGoodmodelId(), false));
			}
			FightMsgSender.sendCancelSustainEffectMsg(character, xuanyauanBangzhuBuffer.getBuffer());
			this.xuanyauanBangzhuBuffer = null;
			this.xuanyuanJian = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void onMeDropXuanyuanjian(Hero other, KuafuZhanTsMap kuafuZhanTsMap) {
		SceneXuanyuanMonster temp = monster;
		this.monster.setXuanyuanjianCharacter(null);
		this.monster = null;
		takeOffBangzhuXuanyuanjianBuffer();
		// takeOffBangzhuXuanyuanjianKangzhuBuffer();
		character.getMyFactionManager().getAcrossFactionController().updateFactionXuanyuanJianBuffer(kuafuZhanTsMap, character);
		NetTool.sendToCharacters(character.getVlineserver().getOnlineManager().getCharacterList(), new XuanyuanjianBelongToMsg51152(kuafuZhanTsMap.getXuanyuanjian()));
		if (other != null) {
			String name = other.getViewName();
			NetTool.sendToCharacters(character.getVlineserver().getOnlineManager().getCharacterList(), new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14700,
					character.getViewName(), name, temp.getXuanjianConfig().getXuanyuanjianName()));
		} else {
			NetTool.sendToCharacters(character.getVlineserver().getOnlineManager().getCharacterList(), new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14701, temp
					.getXuanjianConfig().getXuanyuanjianName()));
		}

	}

	/**
	 * 剑掉落场景
	 */
	public void dropXuanyuanToScene(Hero other) {
		if (monster == null) {
			return;
		}
		Hero xuanyuaner = monster.getXuanyuanjianCharacter();
		if (xuanyuaner == null) {
			return;
		}
		monster.dropXuanyuanjian(other);
	}

	public SceneXuanyuanMonster getMonster() {
		return monster;
	}

	public void setMonster(SceneXuanyuanMonster monster) {
		this.monster = monster;
	}

	/**
	 * @return
	 */
	public int getOnlinTime() {
		long time = System.currentTimeMillis() - startTime;
		return (int) (time / 1000);
	}

	private XuanyuanjianBufferProperty factionXuanyuanBuffer;

	/**
	 * 添加帮会成员轩辕剑buffer
	 * 
	 * @param xuanyuanFactionBuffer
	 */
	public void addXuanYuanFactionBuffer(XuanyuanjianBufferProperty xuanyuanFactionBuffer) {
		if (xuanyuanFactionBuffer == null) {
			return;
		}
		if (factionXuanyuanBuffer != null) {
			takeOffXuanYuanFactionBuffer();
		}
		if (this.xuanyauanBangzhuBuffer != null) {
			return;
		}
		factionXuanyuanBuffer = xuanyuanFactionBuffer;
		character.getPropertyAdditionController().addChangeListener(factionXuanyuanBuffer);
		FightMsgSender.broastSustainEffect(factionXuanyuanBuffer.getBuffer(), character);

	}

	/**
	 * 添加帮会成员轩辕剑buffer
	 * 
	 * @param xuanyuanFactionBuffer
	 */
	public void takeOffXuanYuanFactionBuffer() {
		if (factionXuanyuanBuffer == null) {
			return;
		}
		try {
			character.getPropertyAdditionController().removeChangeListener(factionXuanyuanBuffer);
			FightMsgSender.sendCancelSustainEffectMsg(character, factionXuanyuanBuffer.getBuffer());
			this.factionXuanyuanBuffer = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public int getXuanyuanjianJiacheng(AcrossFactionContoller acrossFactionController) {
		if (xuanyauanBangzhuBuffer != null) {
			return catchXuanYuanJiacheng;
		}
		return acrossFactionController.getChengyuanExpJiacheng();
	}
}
