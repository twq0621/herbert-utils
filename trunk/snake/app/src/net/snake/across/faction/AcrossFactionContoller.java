package net.snake.across.faction;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.across.character.XuanyuanjianBufferProperty;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.program.IntId;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.gamemodel.fight.bean.XuanyuanBufferJiacheng;
import net.snake.gamemodel.fight.bean.XuanyuanjianConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.netio.ServerResponse;

/**
 * 跨服帮会管理
 */

public class AcrossFactionContoller implements PropertyChangeListener {
	private static IntId intId = new IntId();
	private Integer factionId = intId.getNextId();
	private String factionKey;
	public String factionName;
	public int factionGrade;
	private PropertyEntity pe = null;//
	private EffectInfo buffer;
	// private int bangzhuExpJiacheng = 1;
	private int chengyuanExpJiacheng = 1;
	public Map<Integer, Hero> map = new ConcurrentHashMap<Integer, Hero>();

	public int getFactionId() {
		return factionId;
	}

	/**
	 * @param key
	 * @param ae
	 * @param ae
	 */
	public AcrossFactionContoller(String key, AcrossEtc ae) {
		this.factionKey = key;
		this.factionName = ae.getViewFactionName();
		this.factionGrade = ae.getGangGrade();
	}

	/**
	 * @param character
	 */
	public synchronized void addAcrossFaction(Hero character) {
		map.put(character.getId(), character);
		character.getMyFactionManager().initInAcross(this);
	}

	public synchronized void removeAcrossFaction(int character) {
		map.remove(character);
	}

	/**
	 * 发送帮会聊天
	 * 
	 * @param msg
	 */
	public void sendFactionMsg(ServerResponse msg) {
		for (Hero role : map.values()) {
			role.sendMsg(msg);
		}
	}

	public String getFactionKey() {
		return factionKey;
	}

	public void setFactionKey(String factionKey) {
		this.factionKey = factionKey;
	}

	public String getFactionName() {
		return factionName;
	}

	public void setFactionName(String factionName) {
		this.factionName = factionName;
	}

	public Collection<Hero> getAllFactionCharacter() {
		return map.values();
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.bangpai;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		return this.pe;
	}

	private FactionFlag flag;

	public FactionFlag getFactionFlag() {
		if (flag == null) {
			flag = FactionFlagManager.getInstance().getFactionFlagById(this.factionGrade);
		}
		return flag;
	}

	public EffectInfo getEffectInfo() {
		if (buffer == null) {
			SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(getFactionFlag().getBufferId());
			if (se == null) {
				return null;
			}
			buffer = new EffectInfo(se);
		}
		return buffer;
	}

	public void updateFactionJiacheng(Hero character) {
		createTeamPropertyChangerListener();
		character.getPropertyAdditionController().addChangeListener(this);
		EffectInfo buffer = getEffectInfo();
		if (buffer != null) {
			FightMsgSender.broastSustainEffect(buffer, character);
		}
	}

	private void createTeamPropertyChangerListener() {
		if (this.pe != null) {
			return;
		}
		PropertyEntity pe = new PropertyEntity();
		FactionFlag factionFlag = getFactionFlag();
		if (factionFlag == null) {
			return;
		}
		// int attack = factionFlag.getAttack() * onlineCount;
		// if (attack > factionFlag.getMaxAttackLimite()) {
		int attack = factionFlag.getMaxAttackLimite();
		// }
		pe.addExtraProperty(Property.attack, attack);
		// int defence = factionFlag.getDefence() * onlineCount;
		// if (defence > factionFlag.getMaxDefenceLimite()) {
		int defence = factionFlag.getMaxDefenceLimite();
		// }
		pe.addExtraProperty(Property.defence, defence);
		// int hp = factionFlag.getHp() * onlineCount;
		// if (hp > factionFlag.getMaxHpLimite()) {
		int hp = factionFlag.getMaxHpLimite();
		// }
		pe.addExtraProperty(Property.maxHp, hp);

		// pe.addExtraProperty(Property.crt, factionFlag.getCrt() *
		// onlineCount);
		// pe.addExtraProperty(Property.attackspeed,
		// factionFlag.getAttackspeed()
		// * onlineCount);
		// pe.addExtraProperty(Property.dodge, factionFlag.getDodge()
		// * onlineCount);
		// pe.addExtraProperty(Property.maxMp, factionFlag.getMp()
		// * onlineCount);
		// pe.addExtraProperty(Property.maxSp, factionFlag.getSp()
		// * onlineCount);
		// pe.addExtraProperty(Property.movespeed, factionFlag.getMovespeed()
		// * onlineCount);
		this.pe = pe;
	}

	public int getChengyuanExpJiacheng() {
		return chengyuanExpJiacheng;
	}

	private XuanyuanjianBufferProperty xuanyuanFactionBuffer; // 帮主持有轩辕剑时帮会成员添加此buffer
	private Hero catchXuanYuan;

	public void updateFactionXuanyuanJianBuffer(KuafuZhanTsMap kuafuZhanTsMap, Hero character) {
		XuanyuanjianConfig xuanyuanConfig = null;
		Hero role = null;
		for (int i = 0; i < kuafuZhanTsMap.xuanyuanjian.length; i++) {
			Hero xuanyuanRole = kuafuZhanTsMap.xuanyuanjian[i].getXuanyuanjianCharacter();
			if (xuanyuanRole != null) {
				int factionId = xuanyuanRole.getMyFactionManager().getFactionId();
				if (factionId == this.factionId) {
					XuanyuanjianConfig temp = kuafuZhanTsMap.xuanyuanjian[i].getXuanjianConfig();
					if (xuanyuanConfig == null) {
						xuanyuanConfig = temp;
						role = xuanyuanRole;
					} else {
						if (xuanyuanConfig.getBangzhuExp() < temp.getBangzhuExp()) {
							xuanyuanConfig = temp;
							role = xuanyuanRole;
						}
					}
				}
			}
		}
		if (xuanyuanConfig == null) {
			if (this.xuanyuanFactionBuffer == null) {
				return;
			} else {
				bangzhuDropXuanyuanjian();
			}
		} else {
			if (this.xuanyuanFactionBuffer == null) {
				bangzhuCatchXuanyuanjian(xuanyuanConfig, role);
			} else {
				bangzhuDropXuanyuanjian();
				bangzhuCatchXuanyuanjian(xuanyuanConfig, role);
			}
		}

	}

	/**
	 * 帮主持有轩辕剑时调用此方法
	 * 
	 * @param xuanyuanConfig
	 */
	private void bangzhuCatchXuanyuanjian(XuanyuanjianConfig xuanyuanConfig, Hero xuanyuaner) {
		this.chengyuanExpJiacheng = xuanyuanConfig.getChengyuanExp();
		this.catchXuanYuan = xuanyuaner;
		XuanyuanBufferJiacheng factionBuffer = xuanyuanConfig.getXuanyuanJianFactionBuffer();
		xuanyuanFactionBuffer = new XuanyuanjianBufferProperty(factionBuffer, PropertyAdditionType.xuanyuanbangzhong);
		for (Hero character : map.values()) {
			character.getMycharacterAcrossZhengzuoManager().addXuanYuanFactionBuffer(xuanyuanFactionBuffer);
		}
	}

	/**
	 * 帮主持轩辕剑不持有时此方法
	 */
	private void bangzhuDropXuanyuanjian() {
		this.catchXuanYuan = null;
		this.chengyuanExpJiacheng = 1;
		this.xuanyuanFactionBuffer = null;
		for (Hero character : map.values()) {
			character.getMycharacterAcrossZhengzuoManager().takeOffXuanYuanFactionBuffer();
		}
	}

	public XuanyuanjianBufferProperty getXuanyuanFactionBuffer() {
		return xuanyuanFactionBuffer;
	}

	public void downLine(Hero character) {
		removeAcrossFaction(character.getId());
	}

	public int getFactionCount() {
		return map.size();
	}

	/**
	 * 
	 */
	public void clear() {
		map.clear();
	}

	public Hero getCatchXuanYuan() {
		return catchXuanYuan;
	}

}
