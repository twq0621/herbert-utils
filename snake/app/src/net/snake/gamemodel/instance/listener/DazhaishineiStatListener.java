package net.snake.gamemodel.instance.listener;

import net.snake.api.IStateListener;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

public class DazhaishineiStatListener implements IStateListener {
	@Override
	public boolean beforeNewState(VisibleObject object, int state) {
		int instanceId=object.getSceneRef().getInstanceModelId();
		if (instanceId!=14&&instanceId!=20&&instanceId!=11&&instanceId!=18) {
			return true;
		}
		if (object.getSceneObjType() == VisibleObject.SceneObjType_Hero) {
			return true;
		}
		
		SceneMonster prot=(SceneMonster)object.getSceneRef().getInstanceController().getAttribute("protected");
		if (prot==null) {
			return true;
		}
		if (prot.getId().intValue() == object.getId().intValue()) {
			if (state == VisibleObjectState.IsReset) {
				object.setObjectState(VisibleObjectState.Idle);
				return false;
			}
			return true;
		}
		if (state == VisibleObjectState.IsReset) {
			object.setTarget(prot);
			object.setObjectState(VisibleObjectState.Attack);
			return false;
		}
		return true;
	}

	public DazhaishineiStatListener() {
	}
}
