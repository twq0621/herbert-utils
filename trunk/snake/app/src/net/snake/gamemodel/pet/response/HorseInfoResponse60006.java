package net.snake.gamemodel.pet.response;

import java.util.Collection;
import java.util.List;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseGrade;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseGradeDataProvider;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;

/**
 * 返回请求马的属性的马玩家目前没有拥有 消息号50012
 * 
 * @author serv_dev
 * 
 */
public class HorseInfoResponse60006 extends ServerResponse {

	/**
	 * "玩家ID（int）,灵宠id(int),灵宠name(str),品质(byte),身价(int),等级(short),经验(int),下一级经验(int),活力(int),最大活力(int
	 * ) ,
	 * 基础HP(int),强化HP(int),基础MP(int),强化MP(int),基础攻击(int),强化攻击(int),基础防御(int),
	 * 强化防御(int),基础暴击(int),强化暴击(int),基础闪避(int),强化闪避(int),基础命中(int),强化命中(int),
	 * 技能数量(byte),{技能id(int),剩余冷却时间（int, 毫秒，无则写0）},默认技能ID(int)"
	 * 
	 * @param character
	 * @param horse
	 */
	public HorseInfoResponse60006(Hero character, Horse horse) {
		setMsgCode(60006);
		try {
			ServerResponse out = this;
			CharacterHorse characterhorse = horse.getCharacterHorse();
			out.writeInt(character.getId());
			out.writeInt(horse.getId());
			out.writeUTF(characterhorse.getName());
			out.writeByte(characterhorse.getPin());
			out.writeInt(horse.getSelfPrice());
			out.writeShort(characterhorse.getGrade());
			out.writeInt(characterhorse.getExperience());
			HorseGrade grade = HorseGradeDataProvider.getInstance().getHorseGradeById(characterhorse.getGrade());
			out.writeInt(grade.getLevelExperience());
			out.writeInt(characterhorse.getLivingness());
			out.writeInt(characterhorse.getLivingnessMax());
			// 装备数量 byte
			PropertyEntity pe = new PropertyEntity();
			Collection<CharacterGoods> goodslist = horse.getGoodsContainer().getGoodsList();
			if (goodslist == null || goodslist.size() == 0) {
				out.writeByte(0);
			} else {
				out.writeByte(goodslist.size());
				for (CharacterGoods charactergoods : goodslist) {
					out.writeInt(charactergoods.getGoodmodelId());
					out.writeShort(charactergoods.getPosition());
					out.writeByte(charactergoods.getJinjie());
					pe.addPropertyEntity(charactergoods.getPropertyEntity());
				}
			}
			// 技能
			List<CharacterSkill> skills = horse.getSkillManager().getAllHorseSkillOrderByPos();
			if (skills.size() == 0) {
				out.writeByte(0);
			} else {
				out.writeByte(skills.size());
				for (int i = 0; i < skills.size(); i++) {
					CharacterSkill characterSkill = skills.get(i);
					out.writeInt(characterSkill.getSkillId());
					out.writeByte(i);
					out.writeInt(0);
					pe.addPropertyEntity(characterSkill.getPropertyEntity());
				}
			}
			out.writeInt(characterhorse.getHp()+pe.getMaxHp());
			out.writeInt(characterhorse.getExtraHp());
			out.writeInt(characterhorse.getMp()+pe.getMaxMp());
			out.writeInt(characterhorse.getExtraMp());
			out.writeInt(characterhorse.getAttack()+pe.getAttack());
			out.writeInt(characterhorse.getExtraAttack());
			out.writeInt(characterhorse.getDefence()+pe.getDefend());
			out.writeInt(characterhorse.getExtraDefence());
			out.writeInt(characterhorse.getCrt()+pe.getCrt());
			out.writeInt(characterhorse.getExtraCrt());
			out.writeInt(characterhorse.getDodge()+pe.getDodge());
			out.writeInt(characterhorse.getExtraDodge());
			out.writeInt(characterhorse.getHit()+pe.getHit());
			out.writeInt(characterhorse.getExtraHit());
		
			out.writeInt(horse.getCharacterHorse().getDefaultSkillId());// 默认技能
			out.writeByte(horse.getCharacterHorse().getStatus());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
