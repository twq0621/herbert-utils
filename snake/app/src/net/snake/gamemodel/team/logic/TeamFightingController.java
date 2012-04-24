package net.snake.gamemodel.team.logic;

import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.netio.ServerResponse;


/**
 * 阵法行为类 封装阵法数据 实现阵法生效失效操作行为以及加成
 * 
 * @author serv_dev
 * 
 */
public abstract class TeamFightingController {
	private static final Logger logger = Logger.getLogger(TeamFightingController.class);
	private TeamFighting tf;
	private EffectInfo effectInfo;

	public TeamFightingController(TeamFighting tf) {
		this.tf = tf;
	}

	public TeamFighting getTf() {
		return tf;
	}

	/**
	 * 属性加成是否与队伍数量有关
	 * 
	 * @return
	 */
	public abstract boolean teamNumEfectProperty();

	public EffectInfo getEffectInfo() {
		if (effectInfo == null) {
			SkillEffect se = SkillEffectManager.getInstance()
					.getSkillEffectById(tf.getBufferId());
			if (se == null) {
				return null;
			}
			this.effectInfo = new EffectInfo(se);
		}
		return effectInfo;
	}

	public void setEffectInfo(EffectInfo effectInfo) {
		this.effectInfo = effectInfo;
	}

	/**
	 * 阵法开启
	 * 
	 * @param t
	 * @return
	 */
	public boolean openTeamFighting(Team t) {
		if (t == null) {
			return false;
		}
		t.setNowTfc(this);
		this.createTeamPropertyChangerListener(t);
		List<Hero> cs = t.getCharacterPlayers();
		for (int i = 0; i < cs.size(); i++) {
			Hero c = cs.get(i);
			c.getMyZhenfaManager().useTeamfighting();
		}
		return true;
	}

	/**
	 * 阵法关闭
	 * 
	 * @param t
	 * @return
	 */
	public boolean closeTeamFighting(Team t) {
		if (t == null) {
			return false;
		}
		loseTeamFighting(t);
		t.setNowTfc(null);
		return true;
	}

	/**
	 * 阵法失效
	 * 
	 * @param t
	 * @return
	 */
	public boolean loseTeamFighting(Team t) {
		List<Hero> cs = t.getCharacterPlayers();
		for (int i = 0; i < cs.size(); i++) {
			Hero c = cs.get(i);
			c.getMyZhenfaManager().cancelUseTeamfighting();
		}
		return true;
	}

	/**
	 * 返回阵法开启条件是否满足
	 * 
	 * @return
	 */
	public abstract ServerResponse checkOpenCondition(Team t);

	/**
	 * 阵法生效
	 * 
	 * @param character
	 */
	public boolean useTeamFighting(Hero character) {
		character.getPropertyAdditionController().addChangeListener(
				character.getMyTeamManager().getMyTeam());
		EffectInfo buffer = getEffectInfo();
			if (buffer != null) {
				FightMsgSender.broastSustainEffect(buffer, character);
			}
		return true;
	}
	/**
	 * 阵法取消
	 * 
	 * @param character
	 */
	public boolean cancelUseTeamFighting(Hero character) {
		character.getPropertyAdditionController().removeChangeListener(
				character.getMyTeamManager().getMyTeam());
		EffectInfo buffer = getEffectInfo();
		try {
			if (buffer != null) {
				FightMsgSender.sendCancelSustainEffectMsg(character, buffer);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return true;
	}

	public void createTeamPropertyChangerListener(Team t) {
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.attack, this.getTf().getAttack());
		pe.addExtraProperty(Property.attackspeed, this.getTf().getAttackspeed());
		pe.addExtraProperty(Property.crt, this.getTf().getCrt());
		pe.addExtraProperty(Property.defence, this.getTf().getDefence());
		pe.addExtraProperty(Property.dodge, this.getTf().getDodge());
		pe.addExtraProperty(Property.maxHp, this.getTf().getMaxHp());
		pe.addExtraProperty(Property.maxMp, this.getTf().getMaxMp());
		pe.addExtraProperty(Property.maxSp, this.getTf().getMaxSp());
		pe.addExtraProperty(Property.movespeed, this.getTf().getMovespeed());
		t.setPropertyEntity(pe);
	}
}
