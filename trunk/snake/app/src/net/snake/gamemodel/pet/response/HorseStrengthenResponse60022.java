package net.snake.gamemodel.pet.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;

/**
 * 灵宠强化后的属性变化
 * 
 * @author serv_dev
 * 
 */
public class HorseStrengthenResponse60022 extends ServerResponse {

	public HorseStrengthenResponse60022() {
		setMsgCode(60022);
		writeByte(0);
	}

	public HorseStrengthenResponse60022(Hero character, Horse horse, CharacterHorse addHorse) {
		setMsgCode(60022);
		writeByte(1);
		CharacterHorse characterhorse = horse.getCharacterHorse();
		writeInt(horse.getId());
		try {
			writeUTF(characterhorse.getName());
		} catch (Exception e) {
		}
		writeByte(characterhorse.getPin());
		writeInt(horse.getSelfPrice());
		writeInt(characterhorse.getExtraHp());
		writeInt(addHorse.getHp());
		writeInt(characterhorse.getExtraMp());
		writeInt(addHorse.getMp());
		writeInt(characterhorse.getExtraAttack());
		writeInt(addHorse.getAttack());
		writeInt(characterhorse.getExtraDefence());
		writeInt(addHorse.getDefence());
		writeInt(characterhorse.getExtraCrt());
		writeInt(addHorse.getCrt());
		writeInt(characterhorse.getExtraDodge());
		writeInt(addHorse.getDodge());
		writeInt(characterhorse.getExtraHit());
		writeInt(addHorse.getHit());
	}
}
