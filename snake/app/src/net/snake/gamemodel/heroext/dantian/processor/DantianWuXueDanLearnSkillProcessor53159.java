package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.ai.fight.response.SkillAddiGradeMsg10296;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.RequestMsg;

/**
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 53159, accessLimit = 500)
public class DantianWuXueDanLearnSkillProcessor53159 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int skillId = request.getInt();

		DanTian danTian = character.getDanTianController().getDanTian();
		if (danTian != null) {
			DantianModel model = danTian.getModel();
			String canleanSkillitem = model.getCanleanSkillitem();
			String[] split = new String[] {};
			if (canleanSkillitem != null && !canleanSkillitem.trim().equals("")) {
				split = canleanSkillitem.split(Symbol.FENHAO);
			}

			boolean find = false;
			for (int i = 0; i < split.length; i++) {
				if (split != null && split.length > 0) {
					if (Integer.parseInt(split[i]) == skillId) {
						find = true;
					}
				}
			}

			if (!find) {// 如果没有这个技能
				return;
			}

			Skill _skill = SkillManager.getInstance().getSkillById(skillId);

			if (_skill == null)
				return;

			if (character.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.dantianwuxuedan) > 0) {
				if (learnSkill(character, _skill)) {
					character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.dantianwuxuedan, 1);
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20165, _skill.getNameI18n()));
					character.sendMsg(new DanTianWuXueSkillInfoMsg53162(danTian.getDantianid(), character.getSkillManager(), split));
				}
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1034, GoodmodelManager.getInstance().get(GoodItemId.dantianwuxuedan).getNameI18n()));
			}
		}
	}

	/**
	 * 学习技能 (任务脚本，技能书学习，帮派学习)
	 * 
	 * @param skillId
	 * @return
	 */
	public boolean learnSkill(Hero character, Skill skill) {
		if (skill == null) {
			return false;
		}
		int msg = isLearnSkill(character, skill);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, msg));
			return false;
		}

		character.getCharacterSkillManager().create(skill);// 创建技能
		int addgrade = character.getCharacterSkillManager().getskilladdGrade(skill.getId());
		character.sendMsg(new SkillAddiGradeMsg10296(skill.getId(), addgrade));
		return true;
	}

	/**
	 * 是不是可以学习的技能 返回null 可以学习
	 * 
	 * @param skill
	 * @return
	 */
	public int isLearnSkill(Hero character, Skill skill) {

		CharacterSkill characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skill.getId());
		if (characterSkill != null) {
			return 581;
		}
		if (skill.getPopsinger() != 0 && skill.getPopsinger() != character.getPopsinger()) {
			return 582;
		}
		if (skill.getCharLevel() > character.getGrade()) {
			return 583;
		}

		return 0;
	}
}
