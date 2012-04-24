package net.snake.gamemodel.wedding.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import net.snake.consts.WugongType;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.ServerResponse;


/**
 * 技能列表数据
 * @author serv_dev
 *
 */
public class WeddingMsg52362 extends ServerResponse {
	class Skillcomparator implements Comparator<Skill> {
		@Override
		public int compare(Skill o1, Skill o2) {
			return o1.getShowOrder() < o2.getShowOrder() ? -1 : (o1
					.getShowOrder() == o2.getShowOrder() ? 0 : 1);
		}
}

	/**
	 * 
	 * 
	 * 当前武功层数(short),当前真气储量(int),{武功ID(int)，
	 * 是否学过(byte 0-没有，1-有), 1-有(当前等级(int), 快捷栏索引(int),
	 * 消耗真气(int),消耗铜钱(int),熟练度(int),
	 * 剩余冷却时间(int,毫秒，无则写0),学习或升级等级(int),
	 * 是否为瓶颈技能(byte)1是0不是)}*n, {心法ID(int)，
	 * 是否学过(byte 0-没有，1-有),  1-有(当前等级(int),消耗真气(int),
	 * 消耗铜钱(int),熟练度(int)),学习或升级等级(int),是否为瓶颈技能(byte)1是0不是}*n,
	 * 数量(byte),其他操作{id(int),快捷栏索引(int)}*n,修炼心法ID(int)
	 * 
	 * 
	 * 当前武功层数(short),当前真气储量(int),{武功ID(int)，
	 * 是否学过(byte 0-没有，1-有), 1-有(当前等级(int), 快捷栏索引(int),
	 * 消耗真气(int),消耗铜钱(int),熟练度(int),
	 * 剩余冷却时间(int,毫秒，无则写0)),学习或升级等级(int)}*n, {心法ID(int)，
	 * 是否学过(byte 0-没有，1-有),  1-有(当前等级(int),消耗真气(int),
	 * 消耗铜钱(int),熟练度(int)),学习或升级等级(int)}*n,数量(byte),
	 * 其他操作{id(int),快捷栏索引(int)}*n
	 * @param character
	 */
	public WeddingMsg52362(Hero character) {
		setMsgCode(52362);
		ServerResponse out = this;
			// 我可以学的武功
			ArrayList<Skill> wugongICanLearn = new ArrayList<Skill>();
			// 我可以学的心法
			ArrayList<Skill> xinfaICanLearn = new ArrayList<Skill>();
			//我可以学的其他操作
			ArrayList<CharacterSkill> otherOperateSkill = new ArrayList<CharacterSkill>();
			
			Map<Integer, CharacterSkill> characterskillmap = character
			.getCharacterSkillManager().getCharacterSkillMap();
			
			for (Skill skill : SkillManager.getInstance().getCacheSkillMap()
					.values()) {
				if ((skill.getPopsinger() == 0
						|| skill.getPopsinger() == character.getPopsinger()) && skill.getSkilltype() == 2) {//属于人物的技能
					
					if (skill.getWugongTypeConsts() == WugongType.OTHER) {//其他的技能
						CharacterSkill characterSkill = characterskillmap.get(skill.getId());
						if (characterSkill != null) {
							otherOperateSkill.add(characterSkill);
						}
						continue;
					}
					
//					if (skill.getWugongTypeConsts() == WugongType.YU_PEI) {//过滤掉玉佩技能
//						continue;
//					}
					
					if (skill.isZhudong()) {
						wugongICanLearn.add(skill);
					} else {
						xinfaICanLearn.add(skill);
					}
				}
			}
			Collections.sort(wugongICanLearn,new Skillcomparator());
			Collections.sort(xinfaICanLearn,new Skillcomparator());

			

			out.writeShort(character.getWuxueJingjie());
			out.writeInt(character.getZhenqi());
			out.writeByte(wugongICanLearn.size());
			long now = System.currentTimeMillis();
			for (Skill skill : wugongICanLearn) {
				out.writeInt(skill.getId());
				CharacterSkill characterskill = characterskillmap.get(skill
						.getId());
				if (characterskill == null) {// 没学过
					out.writeByte(0);
					int skillBook = skill.getLearningBook();
					if (skillBook > 0) {
						Goodmodel goodmodel = GoodmodelManager.getInstance().get(skillBook);
					    out.writeInt(goodmodel.getLimitGrade());
					} else {
						 out.writeInt(1);
					}
					out.writeBoolean(false);
				} else {
					out.writeByte(1);
					out.writeInt(characterskill.getGrade());
					out.writeInt(characterskill.getQuickbarindex());
					SkillUpgradeExp skillUpgradeExp = characterskill.upgradeNeedZhenqi();
					int zhenqi = 0;
					int cash = 0;
					boolean pinjin = false;
					if (skillUpgradeExp != null) {
						zhenqi = skillUpgradeExp.getExpZhengqi();
						cash = skillUpgradeExp.getExpCash();
						pinjin = skillUpgradeExp.getPinjin() == 1? true : false;
					}
					out.writeInt(zhenqi);
					out.writeInt(cash);
					
					out.writeInt(characterskill.getMastery());
					long time  = characterskill.getCoolingtime() - (now - characterskill.getStartcd());
					if (time < 0) {//还有剩余的时间
						time = 0;
					}
					out.writeInt((int)time);
					out.writeInt(characterskill.getGrade() + 1);
					out.writeBoolean(characterskill.getPo() ? false : pinjin);
//					if (skillUpgradeExp.getPinjin()) {
//						output.writeBoolean(characterskill.getPo());
//						output.writeUTF(skillUpgradeExp.getTargetGoods());
//						output.writeInt(skillUpgradeExp.getPoNeedZhenqi());
//						output.writeInt(skillUpgradeExp.getPoNeedCopper());
//						output.writeInt(skillUpgradeExp.getPoCooltime());
//						output.writeInt(skillUpgradeExp.getPoNeedLv());
//					}
				}
				
				

			}
			out.writeByte(xinfaICanLearn.size());
			for (Skill skill : xinfaICanLearn) {
				out.writeInt(skill.getId());
				CharacterSkill characterskill = characterskillmap.get(skill
						.getId());
				if (characterskill == null) {// 没学过
					out.writeByte(0);
					int skillBook = skill.getLearningBook();
					if (skillBook > 0) {
						Goodmodel goodmodel = GoodmodelManager.getInstance().get(skillBook);
					    out.writeInt(goodmodel.getLimitGrade());
					} else {
						 out.writeInt(1);
					}
					out.writeBoolean(false);
				} else {
					out.writeByte(1);
					out.writeInt(characterskill.getGrade());
					SkillUpgradeExp skillUpgradeExp = characterskill.upgradeNeedZhenqi();
					int zhenqi = 0;
					int cash = 0;
					boolean pinjin = false;
					if (skillUpgradeExp != null) {
						zhenqi = skillUpgradeExp.getExpZhengqi();
						cash = skillUpgradeExp.getExpCash();
						pinjin = skillUpgradeExp.getPinjin() == 1? true : false;
					}
					out.writeInt(zhenqi);
					out.writeInt(cash);
					out.writeInt(characterskill.getMastery());
					out.writeInt(characterskill.getGrade() + 1);
					out.writeBoolean(characterskill.getPo() ? false : pinjin);
//					if (skillUpgradeExp.getPinjin()) {
//						output.writeBoolean(characterskill.getPo());
//						output.writeUTF(skillUpgradeExp.getTargetGoods());
//						output.writeInt(skillUpgradeExp.getPoNeedZhenqi());
//						output.writeInt(skillUpgradeExp.getPoNeedCopper());
//						output.writeInt(skillUpgradeExp.getPoCooltime());
//						output.writeInt(skillUpgradeExp.getPoNeedLv());
//					}
				}
			}
			
			out.writeByte(otherOperateSkill.size());
			for (CharacterSkill characterSkill : otherOperateSkill) {
				out.writeInt(characterSkill.getSkillId());
				out.writeInt(characterSkill.getQuickbarindex());
			}
			out.writeInt(character.getDazuoSkill());

	}
}
