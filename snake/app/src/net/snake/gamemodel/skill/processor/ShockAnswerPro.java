package net.snake.gamemodel.skill.processor;

import net.snake.ai.fight.response.ShockAnsResp;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.ShockImg;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 20061)
public class ShockAnswerPro extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte ans = request.getByte();
		character.getEyeShotManager().sendMsg(new ShockAnsResp(character, ans));

		ShockImg img = character.getShockMeImg();
		if (!img.killerType.equals(SceneMonster.class)) {
			return;
		}
		int monsterId = img.killerId;
		SceneMonster sm = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene, monsterId);
		if (sm == null) {
			return;
		}
		if (sm.isZeroHp()) {
			return;
		}

		if (ans == 1) {// 不服

			sm.setTarget(character);
			sm.setObjectState(VisibleObjectState.Attack);
			return;
		}

		boolean attack = GenerateProbability.isGenerate(Options.Shock_AttackProb, Options.Shock_AttackBase);
		if (attack) {
			sm.setTarget(character);
			sm.setObjectState(VisibleObjectState.Attack);
		} else {
			sm.setTarget(null);
			sm.setObjectState(VisibleObjectState.Idle);
		}

	}

}
