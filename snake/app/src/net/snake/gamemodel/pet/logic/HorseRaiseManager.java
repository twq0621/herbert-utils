package net.snake.gamemodel.pet.logic;

import java.util.Date;

import net.snake.ai.util.ArithmeticUtils;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.response.HorseLivingnessRestorationResponse;

public class HorseRaiseManager {
	public static int XINGYUNJIN_MAX_COUNT = 9;
	public static int XINYUNJING_Probability = 300;
	private Horse horse;
	public static int reward_type = 38;
	public static int zhufu_count = 300;
	public static int zhufu_reward_good = 1220;
	public static int zhufu_reward_count = 3;
	public static int[] replaceGoods = { 30101, 30102, 30103, 30201, 30202, 30203, 30301, 30302, 30303 };
	public static Date lostDate = ArithmeticUtils.stringToDate("2011-07-16 23:59:59");

	// 马的主人
	// private Character character;

	public HorseRaiseManager(Horse horse) {
		this.horse = horse;
		// this.character = character;
	}

	public int changeLivingness(int value) {
		if (horse.getCharacterHorse().getLivingness() >= horse.getCharacterHorse().getLivingnessMax()) {
			return 0;
		}
		if (horse.getCharacterHorse().getLivingness() + value > horse.getCharacterHorse().getLivingnessMax()) {
			value = horse.getCharacterHorse().getLivingnessMax() - horse.getCharacterHorse().getLivingness();
		}
		horse.addLivingness(value);
		if (value != 0) {
			//
			horse.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 552, value + ""));
		}
		return value;

	}

	// ==========================================================马属性升级操作===================
	public void upLivingness() {
		CharacterHorse characterHorse = horse.getCharacterHorse();
		int max = characterHorse.getLivingnessMax();
		int now = characterHorse.getLivingness();
		int zhenqi = max - now;
		if(zhenqi<=0){
			return;
		}
		Hero character = horse.getCharacter();
		if(character.getZhenqi()<zhenqi){
			zhenqi = character.getZhenqi();
		}
		CharacterPropertyManager.changeZhenqi(character, -zhenqi);
		horse.addLivingness(zhenqi);
		character.sendMsg(new HorseLivingnessRestorationResponse(horse.getId(), characterHorse.getLivingness(), characterHorse.getLivingnessMax()));
	}
}
