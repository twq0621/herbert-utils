package net.snake.gamemodel.pet.response;

import java.util.List;

import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;

public class ResetSkillInfoResponse60020 extends ServerResponse {
	
	/**
	 * 灵宠ID(int)，技能数量(byte),{技能id(int),位置（byte），剩余冷却时间（int, 毫秒，无则写0）}*n,默认技能ID(int)
	 * @param horse
	 */
	public ResetSkillInfoResponse60020(Horse horse) {
		this.setMsgCode(60020);
		writeInt(horse.getCharacterHorse().getId());
		// 技能
		List<CharacterSkill> skills = horse.getSkillManager().getAllHorseSkillOrderByPos();
		if (skills.size() == 0) {
			writeByte(0);
		} else {
			writeByte(skills.size());
			for (int i = 0; i < skills.size(); i++) {
				CharacterSkill characterSkill = skills.get(i);
				writeInt(characterSkill.getSkillId());
				writeByte(i);
				writeInt(0);
			}
		}
		writeInt(horse.getCharacterHorse().getDefaultSkillId());// 默认技能
	}
}
