package net.snake.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.commons.script.SEnmity;
import net.snake.commons.script.SRole;
import net.snake.gamemodel.hero.bean.Enmity;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;

import org.apache.mina.util.ConcurrentHashSet;

/**
 * 仇恨管理器
 * 
 * @author serv_dev
 * 
 */

public class EnmityManagerImp implements EnmityManager {
	private static final int KEEP_HURT_TIME = 15 * 1000;// 必须在KEEP_HURT_TIME时间内保持对我的持续伤害,否则从我的仇恨中移除
	private VisibleObject me;
	// 我自己的仇恨列表
	private Map<VisibleObject, Enmity> myEnmityMap = new ConcurrentHashMap<VisibleObject, Enmity>();
	// 我在谁的仇恨列表中
	private Set<VisibleObject> whoAddMeToEnmitys = new ConcurrentHashSet<VisibleObject>();
	private Enmity hatesetTarget = null;// 周围攻击者中我最仇恨谁,缓存最高仇恨
	// 在这个时间之前，增加仇恨值，但是不改变攻击目标,除非该目标因其他原因不在仇恨列表里了
	private long notChangeTargetBeforeThisTime = 0;
	private VisibleObject notChangetarget;

	public EnmityManagerImp(VisibleObject me) {
		this.me = me;
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		int size = 0;
		size = size + myEnmityMap.size();
		size = size + whoAddMeToEnmitys.size();
		size = size + (notChangetarget == null ? 0 : 1);
		size = size + (hatesetTarget == null ? 0 : hatesetTarget.getTarget() == null ? 0 : 1);
		return size;
	}

	@Override
	public void destory() {
		clearEnmityList();
		clearWhosEnmityisMe();
		hatesetTarget = null;
		notChangetarget = null;
	}

	public void setTargetIgnoreEnmity(VisibleObject vo, int duration) {

		if (!myEnmityMap.containsKey(vo)) {
			// 如果不在我的仇恨列表中，先添加到仇恨列表中
			addEnmityValue(vo, 0);
		}
		if (me.getSceneObjType() == SceneObj.SceneObjType_MonsterScene && me.getSceneRef() == vo.getSceneRef()) {
			notChangeTargetBeforeThisTime = System.currentTimeMillis() + duration;
			notChangetarget = vo;
			me.setTarget(vo);
		}
	}

	public Enmity getHatesetTarget() {
		return hatesetTarget;
	}

	private void addToMyEnmityList(VisibleObject vo, int enmityvalue, int hurtvalue) {
		// 根据需要修改 坐骑不能被加到仇恨列表中
		if (me.getSceneObjType() == SceneObj.SceneObjType_Horse || vo.getSceneObjType() == SceneObj.SceneObjType_Horse) {
			// 坐骑没有自己的仇恨列表,但是可以被加到别人的仇恨列表中
			return;
		}
		if (vo == me) {
			return;
		}

		if (vo.isZeroHp()) {
			return;
		}

		Enmity enmity = myEnmityMap.get(vo);
		if (enmity == null) {
			enmity = new Enmity(vo);
			myEnmityMap.put(vo, enmity);
		}
		enmity.addEnmityValue(enmityvalue);
		enmity.addHurt(hurtvalue);

		checkNewEnmityTarget(enmity);
		((EnmityManagerImp) vo.getEnmityManager()).whoAddMeToEnmitys.add(me);
	}

	private void checkNewEnmityTarget(Enmity enmity) {
		if (notChangetarget != null && myEnmityMap.containsKey(notChangetarget) && System.currentTimeMillis() < notChangeTargetBeforeThisTime) {
			// 在此条件内不改变目标
			return;
		}

		if (hatesetTarget == null) {
			hatesetTarget = enmity;
			if (me.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
				me.setTarget(hatesetTarget.getTarget());
			}
		} else {
			if (enmity != hatesetTarget && enmity.getEnmityValue() > hatesetTarget.getEnmityValue()) {
				hatesetTarget = enmity;
				// 设置新的仇恨对像
				if (me.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
					me.setTarget(hatesetTarget.getTarget());
				}

			}
		}
	}

	@Override
	public void removeFromMyEnmityList(VisibleObject vo) {
		if (vo == me || me.getSceneObjType() == SceneObj.SceneObjType_Horse) {
			// 坐骑没有自己的仇恨列表,但是可以被加到别人的仇恨列表中
			return;
		}
		myEnmityMap.remove(vo);
		if (hatesetTarget != null && vo == hatesetTarget.getTarget()) {// 最高仇恨离开了，再找一个
			hatesetTarget = getEnmityBySortPosition(0);
			if (me.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
				me.setTarget(hatesetTarget != null ? hatesetTarget.getTarget() : null);
			}
		}
		((EnmityManagerImp) vo.getEnmityManager()).onOtherNotEnmityMe(me);

	}

	@Override
	public void clearEnmityList() {
		for (VisibleObject other : myEnmityMap.keySet()) {
			// 在我的仇恨列表清除前,通知那些知道我把它加为仇恨列表的人,我已经不把你当仇恨目标了
			((EnmityManagerImp) other.getEnmityManager()).onOtherNotEnmityMe(me);
		}
		myEnmityMap.clear();
		hatesetTarget = null;
		me.setTarget(null);
		notChangetarget = null;
		notChangeTargetBeforeThisTime = 0;
	}

	/** 当其他人不仇恨我的时候 **/
	private void onOtherNotEnmityMe(VisibleObject other) {
		whoAddMeToEnmitys.remove(other);
	}

	/**
	 * 清除所有人对我的仇恨
	 */
	public void clearWhosEnmityisMe() {
		for (VisibleObject other : whoAddMeToEnmitys) {
			other.getEnmityManager().removeFromMyEnmityList(me);
		}
		whoAddMeToEnmitys.clear();
	}

	@Override
	public Collection<Enmity> getEnmityList() {
		return myEnmityMap.values();
	}

	@Override
	public int getEnmityListSize() {
		return myEnmityMap.size();
	}

	private ArrayList<Enmity> getEnmitySortList() {
		ArrayList<Enmity> t = new ArrayList<Enmity>(myEnmityMap.values());
		Collections.sort(t);
		return t;
	}

	@Override
	public Enmity getEnmityBySortPosition(int position) {
		if (position >= 0 && position < myEnmityMap.size()) {
			return getEnmitySortList().get(position);
		}
		return null;
	}

	@Override
	public Enmity getEnmityOfVo(VisibleObject vo) {
		return myEnmityMap.get(vo);
	}

	public void addEnmityValue(VisibleObject vo, int addvalue) {
		if (vo == null || vo.getId()==this.me.getId()){
			return;
		}
		Enmity t = getEnmityOfVo(vo);
		if (t == null) {// 不在我的仇恨列表中
			addToMyEnmityList(vo, addvalue, 0);
		} else {// 在仇恨列表中，更新一下值
			t.addEnmityValue(addvalue);
			checkNewEnmityTarget(t);
		}
	}

	@Override
	public void addHurtValue(VisibleObject vo, int hurtvalue) {
		if (vo == null)
			return;
		Enmity t = getEnmityOfVo(vo);
		if (t != null) {// 不在我的仇恨列表中
			t.addHurt(hurtvalue);
		}
	}

	@Override
	public boolean isContainMaleRole() {
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				// ,1少林,2武当,3古墓,4峨眉',
				if (((Hero) vo).getPopsinger() == 1 || ((Hero) vo).getPopsinger() == 2) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isContainFemaleRole() {
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				// ,1少林,2武当,3古墓,4峨眉',
				if (((Hero) vo).getPopsinger() == 3 || ((Hero) vo).getPopsinger() == 4) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isContainPopsinger(int t) {
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				// ,1少林,2武当,3古墓,4峨眉',
				if (((Hero) vo).getPopsinger() == t) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Hero getAtkMaxRole() {
		Hero role = null;
		int atk = 0;
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero temp = (Hero) vo;
				if (temp.getPropertyAdditionController().getExtraAttack() > atk) {
					atk = temp.getPropertyAdditionController().getExtraAttack();
					role = temp;
				}

			}
		}
		return role;
	}

	@Override
	public Hero getHpMaxRole() {
		Hero role = null;
		int atk = 0;
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero temp = (Hero) vo;
				if (temp.getNowHp() > atk) {
					atk = temp.getNowHp();
					role = temp;
				}

			}
		}
		return role;
	}

	@Override
	public Hero getDefMaxRole() {
		Hero role = null;
		int atk = 0;
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero temp = (Hero) vo;
				if (temp.getPropertyAdditionController().getExtraDefend() > atk) {
					atk = temp.getPropertyAdditionController().getExtraDefend();
					role = temp;
				}

			}
		}
		return role;
	}

	@Override
	public void update(long now) {
		if (me.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			if (notChangetarget != null) {
				if (System.currentTimeMillis() < notChangeTargetBeforeThisTime) {// 还在时间内
					// 在不改变目标的要求下，目标却消失了,因为离开场景或死亡
					if (!myEnmityMap.containsKey(notChangetarget)) {// 不包含了
						resetMaxEnmity();
					}
				} else {// 已经超出不改变目标的时间要求
					resetMaxEnmity();
				}
			}
			for (Enmity enmity : getEnmityList()) {
				if (enmity.getTarget() == notChangetarget) {
					// 对于在一定时间内强制攻击的对像，不做保持伤害的超时处理
					continue;
				}

				if (now - enmity.getLastHurtTime() > KEEP_HURT_TIME && getEnmityListSize() > 1) {
					removeFromMyEnmityList(enmity.getTarget());
				}
			}
		}
	}

	private void resetMaxEnmity() {
		notChangetarget = null;
		notChangeTargetBeforeThisTime = 0;
		ArrayList<Enmity> list = getEnmitySortList();
		if (list.size() > 0) {
			checkNewEnmityTarget(list.get(0));
		}
	}

	@Override
	public boolean isContainRole() {
		for (Enmity enmity : getEnmityList()) {
			VisibleObject vo = enmity.getTarget();
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SRole getHatesetRole() {
		if (hatesetTarget == null) {
			return null;
		}
		if (hatesetTarget.getTarget() instanceof SRole) {
			return (SRole) hatesetTarget.getTarget();
		}
		return null;
	}

	@Override
	public void addEnmityValueToRole(SRole role, int enmityValue) {
		addEnmityValue((Hero) role, enmityValue);
	}

	@Override
	public SEnmity getSEnmity(SRole role) {
		return getEnmityOfVo((Hero) role);
	}

}
