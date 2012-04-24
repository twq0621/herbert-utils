package net.snake.gamemodel.onhoor.response;

import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.netio.ServerResponse;

public class AfkConfigMsg50592 extends ServerResponse {

	/**
	 * 
	 * @param characterOnHoorConfig
	 */
	public AfkConfigMsg50592(CharacterOnHoorConfig characterOnHoorConfig, CharacterOnHoorController characterOnHoorController) {

		setMsgCode(50592);
		this.validateConfig(characterOnHoorController.getCharacter(), characterOnHoorConfig);
		// // (自动补充(byte 0不选1选择),当生命低于1(byte 0-100),自动补充(byte 0不选1选择),当法力低于1(byte 0-100)，使用人物经验丹（byte 0不选1选择）,使用九转还魂丹（byte 0不选1选择），使用琼浆玉液露（byte 0不选1选择））
		writeBoolean(characterOnHoorConfig.getAutoRevertHp());
		writeByte(characterOnHoorConfig.getRevertHp());
		writeBoolean(characterOnHoorConfig.getAutoRevertMp());
		writeByte(characterOnHoorConfig.getRevertMp());
		writeBoolean(characterOnHoorConfig.getExpdan());
		writeBoolean(characterOnHoorConfig.getAutoHpBuff());
		writeBoolean(characterOnHoorConfig.getAutoMpBuff());
		// // （是否拾取任务品（byte 0不选1选择），是否拾取药品（byte 0不选1选择），是否拾取铜币（byte 0不选1选择），是否拾取其他（byte 0不选1选择））
		writeBoolean(characterOnHoorConfig.getIsTaskGoods());
		writeBoolean(characterOnHoorConfig.getIsYaopin());
		writeBoolean(characterOnHoorConfig.getIsMoney());
		writeBoolean(characterOnHoorConfig.getOtherGoods());

		// // （人物技能一id(int),人物技能二id(int),人物技能三id(int),是否躲避变异怪和BOSS(byte 0不选1选择),是否自动使用玫瑰花复活(byte 0不选1选择),
		writeInt(characterOnHoorConfig.getSkillOne());
		writeInt(characterOnHoorConfig.getSkillTwo());
		writeInt(characterOnHoorConfig.getSkillThree());
		writeBoolean(characterOnHoorConfig.getAvoidMonster());
		writeBoolean(characterOnHoorConfig.getBackUseRose());
		// 灵宠活力用真元恢复（byte 0不选1选择），灵宠活力为（int）时使用真元恢复）
		writeBoolean(characterOnHoorConfig.getHorseAutoLivingness());
		writeInt(characterOnHoorConfig.getHorseLivingness());
		writeByte(characterOnHoorConfig.getType());

	}

	private void validateConfig(Hero character, CharacterOnHoorConfig config) {
		int revertHp = config.getRevertHp();
		if (revertHp < 0) {
			revertHp = 0;
		} else if (revertHp > 100) {
			revertHp = 100;
		}
		config.setRevertHp(revertHp);

		int revertMp = config.getRevertMp();
		if (revertMp < 0) {
			revertMp = 0;
		} else if (revertMp > 100) {
			revertMp = 100;
		}
		config.setRevertMp(revertMp);

	}
}
