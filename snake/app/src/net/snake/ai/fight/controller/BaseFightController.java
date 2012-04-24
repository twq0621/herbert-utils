package net.snake.ai.fight.controller;

import java.util.Iterator;
import java.util.List;

import net.snake.ai.astar.Point;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.BaseEffectHandler;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.SynCharacterPosition30002;
import net.snake.consts.EffectType;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 
 * 战斗基础控制器
 */
public abstract class BaseFightController {
	protected VisibleObject vo;
	protected boolean autoAttack;

	private BaseEffectHandler effectHandler;

	public void destroy() {
	}

	public BaseFightController(VisibleObject vo) {
		this.vo = vo;
		autoAttack = false;
	}

	public BaseEffectHandler getEffectHandler() {
		return effectHandler;
	}

	public void setEffectHandler(BaseEffectHandler effectHandler) {
		this.effectHandler = effectHandler;
	}

	public VisibleObject getVo() {
		return vo;
	}

	public abstract void autofight();

	/**
	 * 属于基础的战斗处理 寻找作用效果对象们
	 * 
	 * @param fighterVo
	 * @return
	 */
	protected boolean basefight(FighterVO fighterVo) {
		List<VisibleObject> visibleObjects = getEffectHandler().getFighterVo(fighterVo);
		if (visibleObjects == null){
			return false;
		}
		int size = visibleObjects.size();
		for (int i = 0; i < size; i++) {
			int type =visibleObjects.get(i).getSceneObjType();
			if (type!=SceneObj.SceneObjType_MonsterScene) {
				continue;
			}
			SceneMonster monster = (SceneMonster)visibleObjects.get(i);
			int modelType = monster.getMonsterModel().getType();
			if (modelType!=11) {
				continue;
			}
		}
		if (size == 0) {
			if (fighterVo.getSkill().isPointEffect()) {
				// 功方技能ID(int),功方roleType（byte），攻方ID(int),X(short),Y(short),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*}
				FightMsgSender.sendEffectResponse_only2(fighterVo);
			}
			return false;
		}
		for (Iterator<VisibleObject> iterator = visibleObjects.iterator(); iterator.hasNext();) {
			VisibleObject visibleObject = (VisibleObject) iterator.next();
			getEffectHandler().judgeHurtWay(fighterVo, visibleObject);
		}
		fighterVo.setVisibleObjects(visibleObjects);
		return true;
	}

	/**
	 * 属于基础的战斗处理 对作用效果对象发送消息
	 * 
	 * @param fighterVo
	 * @return
	 */
	public boolean fight(FighterVO fighterVo) {
		if (!basefight(fighterVo)) {
			return false;
		}
		if (fighterVo.getSkill().getEffect().getType() == EffectType.hongyan) {// 红颜
			Hero character = (Hero) getVo();
			RoleWedingManager roleWedingManager = character.getMyFriendManager().getRoleWedingManager();
			if (!roleWedingManager.isWedding()) {
				return false;
			}
			Scene scene = character.getSceneRef();
			Hero wedder = scene.getSceneObj(SceneObj.SceneObjType_Hero, roleWedingManager.getWedderId());
			if (wedder == null) {
				return false;
			}
			Point p = character.getSceneRef().getRandomPoint2(wedder.getX(), wedder.getY(), 2);
			if (p != null) {
				character.getEyeShotManager().sendMsg(new SynCharacterPosition30002(character.getId(), p.getX(), p.getY()));
			}
			FightMsgSender.sendEffectResponse_only(fighterVo);
			return true;
		}
		List<VisibleObject> visibleObjects = fighterVo.getVisibleObjects();
		int size = visibleObjects == null ? 0 : visibleObjects.size();
		if (size > 1) {
			if (fighterVo.getSkill().isPointEffect()) {
				// 功方技能ID(int),功方roleType（byte），攻方ID(int),X(short),Y(short),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*}
				FightMsgSender.sendEffectResponse_Mul2(size, fighterVo, visibleObjects.iterator());
			} else {
				FightMsgSender.sendEffectResponse_Mul(size, fighterVo, visibleObjects.iterator());
			}
		} else {
			if (fighterVo.getSkill().isPointEffect()) {
				// 功方技能ID(int),功方roleType（byte），攻方ID(int),X(short),Y(short),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*}
				FightMsgSender.sendEffectResponse_only2(fighterVo);
			} else {
				FightMsgSender.sendEffectResponse_only(fighterVo);
			}
		}
		return true;
	}

	public void autoAttack(boolean enable) {
		autoAttack = enable;
	}

	/**
	 * 判断能否自动攻击，如果玩家处于移动等状态，则无法自动攻击
	 * @return
	 */
	public boolean couldAutoAttack() {
		return autoAttack;
	}
}
