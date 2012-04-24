package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.HorseInfoResponse60006;
import net.snake.gamemodel.pet.response.HorseSkillUpgradeResponse;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.message.RequestMsg;

/**
 * 获取灵宠技能信息及升级 c-->s 60035 请求 灵宠ID(int),位置（byte）,请求类型(byte)(1 查询 2升级)
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60035)
public class HorseSkillUpgradeProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int horseId = request.getInt();
		byte skillPos = request.getByte();
		byte type = request.getByte();
		CharacterHorseController controller = character.getCharacterHorseController();
		Horse horse = controller.getHorseById(horseId);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		if (skillPos < 0 || skillPos > horse.getSkillManager().getAllHorseSkillOrderByPos().size()) {
			return;
		}
		CharacterSkill characterSkill = horse.getSkillManager().getAllHorseSkillOrderByPos().get(skillPos);

		if (type == 2) {
			boolean upgradeRet = horse.getSkillManager().horseSkillUpgrade(characterSkill, characterSkill.getGrade() + 1);
			if (upgradeRet) {
				character.sendMsg(new HorseInfoResponse60006(character, horse));
			}
		}
		if (characterSkill.getGrade() >= characterSkill.getSkill().getGradeLimit()) {
			character.sendMsg(new HorseSkillUpgradeResponse(horseId, skillPos, characterSkill.getGrade()));
		} else {
			SkillUpgradeExp exp = characterSkill.upgradeNeedZhenqi();// 当前的
			if (exp == null) {
				return;
			}
			character.sendMsg(new HorseSkillUpgradeResponse(horseId, skillPos, characterSkill.getGrade(), characterSkill.getMastery(), characterSkill.getMaxMastery(), exp
					.getLimitGrade(), exp.getExpZhengqi(), exp.getExpCash()));
		}
	}
}
