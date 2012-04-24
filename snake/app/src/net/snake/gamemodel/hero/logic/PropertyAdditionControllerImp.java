package net.snake.gamemodel.hero.logic;

import net.snake.ai.formula.Formula;
import net.snake.consts.EffectType;
import net.snake.consts.PropertyAdditionType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.map.bean.SceneObj;

/**
 * 属性管理器
 * 
 * @author serv_dev
 * 
 */
public class PropertyAdditionControllerImp implements PropertyAdditionController {

	protected PropertyEntity totalValue = new PropertyEntity();

	protected VisibleObject vo;
	private final PropertyEntityManager xinfa = new PropertyEntityManager();// 心法
	// 放入到这里的攻击力\防御力\攻击速度\移动速度为百分比
	private final PropertyEntityManager buff = new PropertyEntityManager();// buff

	public PropertyAdditionControllerImp(VisibleObject vo) {
		this.vo = vo;
	}

	protected PropertyEntityManager getPropertyEntityManager(PropertyAdditionType type) {
		switch (type) {
		case buff:
			return buff;
		case xinfa:
			return xinfa;
		default:
			return null;
		}
	}

	/**
	 * 替换监听器
	 * 
	 * @param oldlistener
	 *            在列表里原有的监听器
	 * @param newlistener
	 *            添加新的监听器
	 */
	public void replaceListener(PropertyChangeListener oldlistener, PropertyChangeListener newlistener) {
		_removeChangeListener(oldlistener);
		_addChangeListener(newlistener);
		update();
	}

	private void _removeChangeListener(PropertyChangeListener oldlistener) {
		if (oldlistener == null || oldlistener.getPropertyControllerType() == null) {
			return;
		}
		PropertyEntityManager pem = getPropertyEntityManager(oldlistener.getPropertyControllerType());
		if (pem != null) {
			pem.removePropertiesChangeListener(oldlistener);
			pem.recompute();
		}
	}

	private void _addChangeListener(PropertyChangeListener newlistener) {
		if (newlistener == null || newlistener.getPropertyControllerType() == null) {
			return;
		}
		PropertyEntityManager pem = getPropertyEntityManager(newlistener.getPropertyControllerType());
		if (pem != null) {
			pem.addPropertiesChangeListener(newlistener);
			pem.recompute();
		}
	}

	public void addChangeListener(PropertyChangeListener... listener) {
		for (int i = 0; i < listener.length; i++) {
			if (listener[i] == null)
				continue;
			addChangeListener(listener[i]);
		}
		update();
	}

	public void addChangeListener(PropertyChangeListener listener) {
		_addChangeListener(listener);
		update();
	}

	public void removeChangeListener(PropertyChangeListener... listener) {
		for (int i = 0; i < listener.length; i++) {
			if (listener[i] == null)
				continue;
			_removeChangeListener(listener[i]);
		}
		update();
	}

	public void removeChangeListener(PropertyChangeListener listener) {
		_removeChangeListener(listener);
		update();
	}

	private void update() {
		recompute();
	}

	@Override
	public PropertyEntity getTotalValue() {
		return totalValue;
	}

	public int getExtraMoveSpeed() {
		return totalValue.getMoveSpeed();
	}

	public int getExtraAttackSpeed() {
		return totalValue.getAttackSpeed();
	}

	public int getExtraMaxHp() {
		return totalValue.getMaxHp();
	}

	public int getExtraMaxMp() {
		return totalValue.getMaxMp();
	}

	public int getExtraMaxSp() {
		return totalValue.getMaxSp();
	}

	public int getExtraAttack() {
		return totalValue.getAttack();
	}

	public int getExtraDefend() {
		return totalValue.getDefend();
	}

	public int getExtraCrt() {
		return totalValue.getCrt();
	}

	public int getExtraDodge() {
		return totalValue.getDodge();
	}
	
	public int getExtraHit() {
		return totalValue.getHit();
	}
	
	public int getExtraGjl() {
		return totalValue.getGjl();
	}

	public int getExtraFtsh() {
		return totalValue.getFtsh();
	}

	public int getExtraHsfy() {
		return totalValue.getHsfy();
	}

	public int getExtraShjm() {
		return totalValue.getShjm();
	}

	public PropertyEntity getBuff() {
		return buff;
	}

	public PropertyEntity getXinfa() {
		return xinfa;
	}

	@Override
	public void recompute() {
		// clearData();
		// TODO 属性加成
		/*
		 * (人物自身成长带来的生命值+人物加点带来的生命值
		 * +（当前佩戴的装备带来的基础生命值+强化装备带来的生命值+装备附加属性带来的生命值+宝石带来的生命固定值）*（1+套装加成百分比）
		 * +BUFF类药品带来的生命值
		 * +心法带来的生命值+人物冲穴经脉带来的生命值)*(1+阵法带来的百分比+称号带来的百分比)+坐骑本身成长的生命值
		 * +坐骑本身成长的生命值*坐骑技能带来的生命成长的百分比+技能带来的基础值
		 * ----------------------------------
		 * ------------------------------------
		 * ----------------------------------
		 * -----------------------------------------------------------
		 * (人物自身成长带来的生命值+人物加点带来的生命值
		 * +（当前佩戴的装备带来的基础生命值+强化装备带来的生命值+装备附加属性带来的生命值+宝石带来的生命固定值）*（1+套装加成百分比）
		 * +BUFF类药品带来的生命值
		 * +心法带来的生命值+人物冲穴经脉带来的生命值)*(1+阵法带来的百分比+称号带来的百分比)+坐骑本身成长的生命值
		 * +坐骑本身成长的生命值*坐骑技能带来的生命成长的百分比+坐骑技能带来的基础生命值+成就带来的生命值(new)
		 */

		int maxhp = (int) (vo.getMaxHp() + buff.getMaxHp() + xinfa.getMaxHp());
		/*
		 * 当前内力值上限=
		 * (人物自身成长带来的内力值+人物加点带来的内力值+（当前佩戴的装备带来的基础内力值+强化装备带来的内力值+装备附加属性带来的内力值
		 * +宝石带来的内力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的内力值+心法带来的内力值+人物冲穴经脉带来的内力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的内力值*(1+坐骑技能带来的内力成长的百分比)
		 * ----------------------------
		 * ------------------------------------------
		 * ----------------------------------------------------
		 * (人物自身成长带来的内力值+人物加点带来的内力值+（当前佩戴的装备带来的基础内力值+强化装备带来的内力值+装备附加属性带来的内力值
		 * +宝石带来的内力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的内力值+心法带来的内力值+人物冲穴经脉带来的内力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的内力值+坐骑本身成长的内力值*坐骑技能带来的内力成长的百分比+坐骑技能带来的内力值基础值(new)
		 */
		int maxmp = (int) (vo.getMaxMp() + buff.getMaxMp() + xinfa.getMaxMp());

		/*
		 * 当前体力值上限=
		 * (人物自身成长带来的体力值+人物加点带来的体力值+（当前佩戴的装备带来的基础体力值+强化装备带来的体力值+装备附加属性带来的体力值
		 * +宝石带来的体力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的体力值+心法带来的体力值+人物冲穴经脉带来的体力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的体力值*(1+坐骑技能带来的体力成长的百分比)
		 * ----------------------------
		 * ------------------------------------------
		 * --------------------------------------------------
		 * (人物自身成长带来的体力值+人物加点带来的体力值+（当前佩戴的装备带来的基础体力值+强化装备带来的体力值+装备附加属性带来的体力值
		 * +宝石带来的体力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的体力值+心法带来的体力值+人物冲穴经脉带来的体力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的体力值+坐骑本身成长的体力值*坐骑技能带来的体力成长的百分比+坐骑技能带来的体力基础值(new)
		 */
		int maxsp = (int) (vo.getMaxSp() + buff.getMaxSp() + xinfa.getMaxSp());

		/*
		 * 人物属性面板显示的攻击值=
		 * (人物自身成长带来的攻击值+人物加点带来的攻击值+（当前佩戴的装备带来的基础攻击值+强化装备带来的攻击值+装备附加属性带来的攻击值
		 * +宝石带来的攻击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的攻击值+心法带来的攻击值+人物冲穴经脉带来的攻击值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的攻击值*(1+坐骑技能带来的攻击成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * -------------------------------------------------------------
		 * (人物自身成长带来的攻击值+人物加点带来的攻击值+（当前佩戴的装备带来的基础攻击值+强化装备带来的攻击值+装备附加属性带来的攻击值
		 * +宝石带来的攻击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的攻击值+心法带来的攻击值+人物冲穴经脉带来的攻击值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的攻击值+坐骑本身成长的攻击值*坐骑技能带来的攻击成长的百分比+坐骑技能带来的基础攻击值
		 * (new)
		 */
		int atk = (int) (vo.getAttack() + buff.getAttack() + xinfa.getAttack());
		/*
		 * 人物属性面板显示的防御值=
		 * (人物自身成长带来的防御值+人物加点带来的防御值+（当前佩戴的装备带来的基础防御值+强化装备带来的防御值+装备附加属性带来的防御值
		 * +宝石带来的防御固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的防御值+心法带来的防御值+人物冲穴经脉带来的防御值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的防御值*(1+坐骑技能带来的防御成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * ---------------------------------------------------------------
		 * (人物自身成长带来的防御值+人物加点带来的防御值+（当前佩戴的装备带来的基础防御值+强化装备带来的防御值+装备附加属性带来的防御值
		 * +宝石带来的防御固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的防御值+心法带来的防御值+人物冲穴经脉带来的防御值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的防御值+坐骑本身成长的防御值*坐骑技能带来的防御成长的百分比+坐骑技能带来的防御基础值
		 * (new)
		 */
		int def = (int) (vo.getDefence() + buff.getDefend() + xinfa.getDefend());

		if (vo.getEffectController().isNodefence()) {
			def = 0;
		}

		/*
		 * ID人物属性面板显示的暴击值=
		 * (人物自身成长带来的暴击值+人物加点带来的暴击值+（当前佩戴的装备带来的基础暴击值+强化装备带来的暴击值+装备附加属性带来的暴击值
		 * +宝石带来的暴击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的暴击值+心法带来的暴击值+人物冲穴经脉带来的暴击值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的暴击值*(1+坐骑技能带来的暴击成长的百分比)
		 * 
		 * ----------------------------------------------------------------------
		 * --
		 * --------------------------------------------------------------------
		 * -- (人物自身成长带来的暴击值+人物加点带来的暴击值+（当前佩戴的装备带来的基础暴击值+强化装备带来的暴击值+装备附加属性带来的暴击值
		 * +宝石带来的暴击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的暴击值+心法带来的暴击值+人物冲穴经脉带来的暴击值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的暴击值+坐骑本身成长的暴击值*坐骑技能带来的暴击成长的百分比+坐骑技能带来的基础暴击值
		 * (new)
		 */
		int crt = (int) (vo.getCrt() + buff.getCrt() + xinfa.getCrt());

		if (vo.getEffectController().isReduceCrtBuff()) {
			crt = (int) (crt * (1 - AppEventCtlor.getInstance().getEvtSkillFormula().getReduceCrtPrecent_QiShaZhang() / Formula.Wan));
		}

		/*
		 * 人物属性面板显示的闪避值=
		 * (人物自身成长带来的闪避值+人物加点带来的闪避值+（当前佩戴的装备带来的基础闪避值+强化装备带来的闪避值+装备附加属性带来的闪避值
		 * +宝石带来的闪避固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的闪避值+心法带来的闪避值+人物冲穴经脉带来的闪避值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的闪避值*(1+坐骑技能带来的闪避成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * -------------------------------------------------------------
		 * (人物自身成长带来的闪避值+人物加点带来的闪避值+（当前佩戴的装备带来的基础闪避值+强化装备带来的闪避值+装备附加属性带来的闪避值
		 * +宝石带来的闪避固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的闪避值+心法带来的闪避值+人物冲穴经脉带来的闪避值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的闪避值+坐骑本身成长的闪避值*坐骑技能带来的闪避成长的百分比+坐骑技能带来的闪避基础值
		 * (new)
		 */
		int dodge = (int) (vo.getDodge() + buff.getDodge() + xinfa.getDodge());

		if (vo.getEffectController().isReduceDodgeBuff()) {
			dodge = (int) (dodge * (1 - AppEventCtlor.getInstance().getEvtSkillFormula().getReduceDodgePrecent_QianHuanZhang() / Formula.Wan));
		}

		/*
		 * 攻击速度值=(人物初始攻击速度+人物升级成长带来的攻击速度+人物冲穴带来的攻击速度+装备带来的攻击速度+BUFF类药品带来的攻击速度+
		 * 技能BUFF带来的攻击速度
		 * +人物坐骑带来的攻击速度+宝石带来的攻击速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * 
		 * ----------------------------------------------------------------------
		 * -----------------------------------------------------
		 * (人物初始攻击速度+人物升级成长带来的攻击速度+人物冲穴带来的攻击速度+装备带来的攻击速度+BUFF类药品带来的攻击速度+
		 * 技能BUFF带来的攻击速度
		 * +人物坐骑带来的攻击速度+宝石带来的攻击速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF) +
		 * 坐骑本身成长的攻击速度值*坐骑技能带来的攻击速度成长的百分比+坐骑技能带来的攻击速度基础值(new)
		 */
		int atkspeed = (int) (vo.getAtkColdtime() + buff.getAttackSpeed() + xinfa.getAttackSpeed());

		/*
		 * ID人物属性面板显示的移动速度值=
		 * (人物初始移动速度+人物升级成长带来的移动速度+人物冲穴带来的移动速度+装备带来的移动速度+BUFF类药品带来的移动速度
		 * +技能BUFF带来的移动速度
		 * +人物坐骑带来的移动速度+宝石带来的移动速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * ----------
		 * ------------------------------------------------------------
		 * ------------------------------------------------------
		 * (人物初始移动速度+人物升级成长带来的移动速度+人物冲穴带来的移动速度+装备带来的移动速度+BUFF类药品带来的移动速度
		 * +技能BUFF带来的移动速度
		 * +人物坐骑带来的移动速度+宝石带来的移动速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * +坐骑本身成长的移动速度值*坐骑技能带来的移动速度成长的百分比+坐骑技能带来的移动速度基础值(new)
		 */
		int movespeed = (int) (vo.getMoveSpeed() + buff.getMoveSpeed() + xinfa.getMoveSpeed());
		
		int hit = (int) (vo.getHit()+ buff.getHit() + xinfa.getHit());
		
		// 开始设置新的值====================================================

		
		if (atk != totalValue.getAttack()) {
			totalValue.setAttack(atk);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.attack, getExtraAttack()));
			}
		}

		if (atkspeed < 0)
			atkspeed = 0;
		if (atkspeed != totalValue.getAttackSpeed()) {
			if (!vo.getEffectController().isReduceAttackSpeedBuff()) {
				totalValue.setAttackSpeed(atkspeed);
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.attackspeed, atkspeed));
				}
			}
		}
		if (crt != totalValue.getCrt()) {
			totalValue.setCrt(crt);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.crt, crt));
			}
		}

		if (vo.getEffectController().isNodefence()) {
			totalValue.setDefend(0);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.defence, def));
			}
		} else {
			if (def != totalValue.getDefend()) {
				totalValue.setDefend(def);
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.defence, def));
				}
			}
		}

		if (dodge != totalValue.getDodge()) {
			totalValue.setDodge(dodge);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.dodge, dodge));
			}
		}

		if (hit != totalValue.getHit()) {
			totalValue.setHit(hit);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.hit, hit));
			}
		}
		// 下面几个值的改变需要进行通知的
		if (maxhp != totalValue.getMaxHp()) {
			totalValue.setMaxHp(maxhp);
			// if (vo instanceof Character) {
			vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxHp, maxhp));
			// }
		}
		if (maxmp != totalValue.getMaxMp()) {
			totalValue.setMaxMp(maxmp);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxMp, maxmp));
			}
		}
		if (maxsp != totalValue.getMaxSp()) {
			totalValue.setMaxSp(maxsp);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxSp, maxsp));
			}
		}

		if (movespeed < 0)
			movespeed = 0;
		if (movespeed != totalValue.getMoveSpeed()) {
			if (!vo.getEffectController().isReduceMoveSpeedBuff()) {
				totalValue.setMoveSpeed(movespeed);
				// if (vo instanceof Character) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.movespeed, movespeed));
				// }
			}
		}

		if (vo.getNowHp() > totalValue.getMaxHp()) {
			vo.setNowHp(totalValue.getMaxHp());
			// if (vo instanceof Character) {
			vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.hp, vo.getNowHp()));
			// }
		}
		if (vo.getNowMp() > totalValue.getMaxMp()) {
			vo.setNowMp(totalValue.getMaxMp());
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.mp, vo.getNowMp()));
			}
		}
		if (vo.getNowSp() > totalValue.getMaxSp()) {
			vo.setNowSp(totalValue.getMaxSp());
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.sp, vo.getNowSp()));
			}
		}

		int gjl = getBuff().getGjl();
		totalValue.setGjl(gjl);
		/**
		 * 反弹伤害
		 */
		int ftsh = getBuff().getFtsh();
		totalValue.setFtsh(ftsh);
		/**
		 * 忽视防御
		 */
		int hsfy = getBuff().getHsfy();
		totalValue.setHsfy(hsfy);
		/**
		 * 伤害减免
		 */
		int shjm = getBuff().getShjm();
		totalValue.setShjm(shjm);

		// if (vo instanceof Character) {
		// vo.sendMsg(new CharacterAttributesMsg49992((Character)vo));
		// }
		// if(vo instanceof Character){
		// CharacterFormula.sendCharacterProperties((Character)vo);
		// vo.sendMsg(msg)
		// }

	}

	@Override
	public float getFightAttack() {
		float attAttack = vo.getAttack() + getXinfa().getAttack() + getBuff().getAttack();
		return attAttack;
	}

	@Override
	public float getFightDefence() {
		float affDefence = vo.getDefence() + getXinfa().getDefend() + getBuff().getDefend();
		return affDefence;
	}

	@Override
	public void destory() {
		xinfa.destroy();
		buff.destroy();
	}

}
