package net.snake.ai.eyeshot;

import java.util.Collection;

import net.snake.ai.IEyeShotManager;
import net.snake.ai.move.IMoveController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;

public class CharacterEyeShotManager extends BaseEyeShotManager implements IEyeShotManager {
	private Hero characterm;

	public CharacterEyeShotManager(Hero visibleObject) {
		super(visibleObject);
		this.characterm = visibleObject;
	}

	public int getAllEyeObjInHeap() throws Exception {
		int size = 0;
		int[] c = characterm.getHeedSceneObject();
		for (int i = 0; i < c.length; i++) {
			Collection<SceneObj> col = getEyeShortObjs(c[i]);
			if (col != null) {
				size = size + col.size();
			}
		}

		return size;
	}

	@Override
	public void addInMyEyeShot(SceneObj obj, boolean msg) {
		if (obj == null) {
			return;
		}
		int objType = obj.getSceneObjType();
		switch (objType) {
		case SceneObj.SceneObjType_Hero:
			addInMyEyeShot((Hero) obj, msg);
			break;
		case SceneObj.SceneObjType_MonsterScene:
			addInMyEyeShot((SceneMonster) obj, msg);
			break;
		case SceneObj.SceneObjType_DropedGood:
			addInMyEyeShot((SceneDropGood) obj, msg);
			break;
		default:
			break;
		}
	}

	@Override
	public void removeFromMyEyeShot(SceneObj other) {
		if (eyeShortObjManager.removeVisibleObj(other) != null) {
			characterm.sendMsg(other.getLeaveEyeshotMsg());
		}
	}

	void addInMyEyeShot(Hero other, boolean sendmsg) {
		// 　下面的代码不开启
		// int characterEyeShotLimit = scene.getCharacterEyeShotLimit();
		// if (characterEyeShotLimit != 0) {
		// if (!isAcceptCharacterWhenGreatLimit(other)) {
		// Collection<Character> characters = getEyeShortObjs(Character.class);
		// Collection<Character> otherCharacters = other
		// .getEyeShotManager().getEyeShortObjs(Character.class);
		// if (characters.size() > characterEyeShotLimit
		// || otherCharacters.size() > characterEyeShotLimit) {
		// return;
		// }
		// }
		// }
		eyeShortObjManager.addVisibleObj(other);
		if (characterm != other && sendmsg) {// 自已加入自己的视野不发消息处理
			characterm.sendMsg(other.getEnterEyeshotMsg());
			if (other.getObjectState() == VisibleObjectState.Shock) {
				characterm.sendMsg(other.getShockMsg());
			}
			IMoveController movecontroller = other.getMoveController();
			ServerResponse moveMsg = movecontroller.getMoveMsg();
			if (moveMsg != null) {
				characterm.sendMsg(moveMsg);
			}
		}
	}

	void addInMyEyeShot(SceneMonster other, boolean sendmsg) {
		eyeShortObjManager.addVisibleObj(other);
		if (sendmsg) {// 自已加入自己的视野不发消息处理
			characterm.sendMsg(other.getEnterEyeshotMsg());
			if (other.getObjectState() == VisibleObjectState.Shock) {
				characterm.sendMsg(other.getShockMsg());
			}
			IMoveController movecontroller = other.getMoveController();
			ServerResponse moveMsg = movecontroller.getMoveMsg();
			if (moveMsg != null) {
				characterm.sendMsg(moveMsg);
			}
		}
	}

	void addInMyEyeShot(Horse other, boolean sendmsg) {
		eyeShortObjManager.addVisibleObj(other);
		// horseMap.put(other.getId(), other);
		if (sendmsg) {// 自已加入自己的视野不发消息处理
			characterm.sendMsg(other.getEnterEyeshotMsg());
			IMoveController movecontroller = other.getMoveController();
			ServerResponse moveMsg = movecontroller.getMoveMsg();
			if (moveMsg != null) {
				characterm.sendMsg(moveMsg);
			}
		}
	}

	void addInMyEyeShot(SceneDropGood other, boolean sendmsg) {
		eyeShortObjManager.addVisibleObj(other);
		if (sendmsg) {// 自已加入自己的视野不发消息处理
			characterm.sendMsg(other.getEnterEyeshotMsg());
		}
	}
}
