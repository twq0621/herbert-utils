package net.snake.gameserver.event.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

/**
 * 夫子阵 副本
 * 
 */
public class FuZiZhen_HpChange extends SuperInstance implements IEventListener {
	private int groupNum = 10;// 一共10组怪物 打完后 + 刷一个BOSS

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SMonster monster, SVO whoAttackMe
		SMonster monster = (SMonster) args[0];
		// SVO whoAttackMe = (SVO)args[1];

		if (monster.getSceneRef().getId() != 20175) {
			return;
		}
		SInstance instance = monster.getSceneRef().getInstance();
		Collection<SRole> roles = instance.getInstanceAllCharacters();
		if (monster.getAttribute("changeAttribute") != null) {
			return;
		}
		int currGroupCount = (Integer) instance.getAttribute("currGroupCount");

		// 最后一个boss
		if (currGroupCount > groupNum) {
			if (monster.getHpPercent() < 0.5) {
				monster.changeAttack(monster.getAttack() * 2);
				monster.setAttribute("changeAttribute", 1);
			} else if (monster.getHpPercent() <= 0.55) {
				if (monster.getAttribute("crtTip") == null) {
					monster.setAttribute("crtTip", 1);
					sendMsg(api, GlobalLanguage.FuZiZhenBoosCrt, roles);
				}
			}
		} else {
			int maxGrate = getRolesMaxGrate(roles);
			// double rate=(maxGrate-20)*17;
			double value = (maxGrate - 20) * 17;
			// 怪物攻击值 * (1+当前怪物波次*10/100)
			double attack = maxGrate * value;
			// 怪物防御值 * (1+当前怪物波次*10/100)
			double defence = maxGrate * value;
			// 怪物闪避值 * (1+当前怪物波次*10/100)
			double dodge = maxGrate * value;
			// 怪物爆击值 * (1+当前怪物波次*10/100)
			double crt = maxGrate * value;
			char idioms[] = (char[]) instance.getAttribute("currGroupIdioms");
			String monsterName = monster.geReplacetName();
			List<SMonster> monsters = (ArrayList<SMonster>) instance.getAttribute("monsters");
			int index = idioms.length - monsters.size();
			if (monsterName.trim().indexOf(idioms[index]) == -1) {
				for (SMonster sMonster : monsters) {
					sMonster.changeAttack((int) attack);
					sMonster.changeDefence((int) defence);
					sMonster.changeDodge((int) dodge);
					sMonster.changeCrt((int) crt);
					// sMonster.changeAttackSpeed(200);
					// sMonster.changeMoveSpeed(200);
					// 主动攻击1/被动攻击2
					sMonster.changeAttackModel((short) 1);
					sMonster.setAttribute("changeAttribute", 1);
				}
				sendMsg(api, GlobalLanguage.FuZiZhenTip, roles);
				// sendMsg(api,"攻防闪暴:"+(int)attack+","+(int)defence+","+(int)dodge+","+(int)crt,roles);
			}
		}

	}

}
