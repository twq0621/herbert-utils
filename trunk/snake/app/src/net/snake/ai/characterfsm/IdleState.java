package net.snake.ai.characterfsm;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;

/**
 * 空闲状态
 * 
 * @author serv_dev
 * 
 */
public class IdleState extends AfkState {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IdleState.class);

	private long beginTime = 0l;

	public IdleState(CharacterFSM fsm, Hero character) {
		super(fsm, character);
	}

	@Override
	public void onBegin() {
		beginTime = System.currentTimeMillis();
	}

	@Override
	public void onUpdate(long now) {

		CharacterOnHoorController characterOnHoorController = character.getCharacterOnHoorController();

		if (character.getObjectState() == VisibleObjectState.Walk)
			return;

		if (character.isZeroHp()) {
			characterOnHoorController.setAfkState(OnHoorState.off);
			return;
		}

		if (characterOnHoorController.autoTOPickUp()) {
			return;
		}

		if (character.getTarget() == null || character.getTarget().isZeroHp() || character.getTarget().getScene() != character.getScene()) {
			long curTime = now;
			if (curTime - beginTime > 500) {
				// 重新重找目标
				if (findOtherMonsterAndAttack()) {
					fsm.switchState(VisibleObjectState.Attack);
				}
				beginTime = curTime;
			}
		} else {
			// 如果是从人物行走状态下切换而来，则切换为攻击状态
			int configX = characterOnHoorController.getX();
			int configY = characterOnHoorController.getY();
			short[] _ppoint = character.getSceneRef().findWay((short) configX, (short) configY, character.getTarget().getX(), character.getTarget().getY());
			if (_ppoint != null && _ppoint.length > characterOnHoorController.getCharacterOnHoorConfig().getAttackScope() * 2) {
				character.setTarget(null);
			}
			fsm.switchState(VisibleObjectState.Attack);
		}
	}

	private boolean findOtherMonsterAndAttack() {

		CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
		if (characterOnHoorConfig == null) {
			logger.warn("findOtherMonsterAndAttack() - no this config about onhook");
			return false;
		}
		Collection<SceneMonster> sceneMonsters = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);

		SceneMonster targetMonster = findSuitableTarget(characterOnHoorConfig, sceneMonsters);

		if (targetMonster == null) {
			targetMonster = findSuitableTarget(characterOnHoorConfig, character.getSceneRef().getAllSceneMonster());
		}

		if (targetMonster != null) {
			character.setTarget(targetMonster);
			// character.sendMsg(new ChangeTargetMsg50594(targetMonster.getId()));
			return true;
		}

		return false;
	}

	private SceneMonster findSuitableTarget(CharacterOnHoorConfig characterOnHoorConfig, Collection<SceneMonster> sceneMonsters) {
		SceneMonster targetMonster = null;
		int _tmpDis = 0;
		if (!sceneMonsters.isEmpty()) {
			for (Iterator<SceneMonster> iterator = sceneMonsters.iterator(); iterator.hasNext();) {
				SceneMonster sceneMonster = iterator.next();
				if (sceneMonster.isZeroHp()) {
					continue;
				}

				if (sceneMonster.isHorse()) {
					continue;
				}
				if (sceneMonster instanceof SceneBangqiMonster) {
					continue;
				}
				if (characterOnHoorConfig.getAvoidMonster() && (sceneMonster.variation() || sceneMonster.getMonsterModel().isBoss() || sceneMonster.getMonsterModel().isJY())) {// 避开变异的怪物
					continue;
				}
				if (!sceneMonster.getEnmityManager().getEnmityList().isEmpty()) {
					if (sceneMonster.getEnmityManager().getEnmityOfVo(character) == null) {
						continue;
					}
				}
				if (sceneMonster.getType() == 11) {
					continue;
				}
				int _disX = Math.abs(character.getX() - sceneMonster.getX());
				int _disY = Math.abs(character.getY() - sceneMonster.getY());
				int _tmpDisX_Y = _disX + _disY;
				if (_tmpDis == 0 || _tmpDisX_Y < _tmpDis) {
					_tmpDis = _tmpDisX_Y;
					targetMonster = sceneMonster;
				}
			}
		}
		return targetMonster;
	}
}
