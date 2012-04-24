package net.snake.gamemodel.skill.logic.buff.drug;

import java.util.Collection;
import java.util.Iterator;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 骑战坐骑恢复活力
 * @author serv_dev
 *
 */
public class HorseHuoLiDan extends Buff {

	public HorseHuoLiDan(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		Hero character = (Hero)controller.getVo();
		
		Collection<Horse> horcol = character.getCharacterHorseController().getHorseCollection();
		if (horcol.size() == 0) {
			character.sendMsg(new PrompMsg(762));
			return false;
		}
		
		boolean haslivingness = false;
		for (Iterator<Horse> iterator = horcol.iterator(); iterator.hasNext();) {
			Horse horse2 =  iterator.next();
			CharacterHorse characterHorse = horse2.getCharacterHorse();
			int _livingness = characterHorse.getLivingnessMax() - characterHorse.getLivingness();
			if (_livingness > 0) {//可用坐骑活力丹
				haslivingness = true;
				break;
			}
		}
		
		if (!haslivingness) {
			//不可以使用坐骑活力丹，活力都是满的
			character.sendMsg(new PrompMsg(763));
			return false;
		} else {
			for (Iterator<Horse> iterator = horcol.iterator(); iterator.hasNext();) {
				Horse horse2 =  iterator.next();
				CharacterHorse characterHorse = horse2.getCharacterHorse();
				int _livingness = characterHorse.getLivingnessMax() - characterHorse.getLivingness();
				if (_livingness > 0) {//可用坐骑活力丹
					horse2.addLivingness(_livingness);
				}
			}
			return true;
		}
			
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

}
