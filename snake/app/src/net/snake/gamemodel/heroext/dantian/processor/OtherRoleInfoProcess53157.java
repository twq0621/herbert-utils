package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 53157)
public class OtherRoleInfoProcess53157 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int roleid = request.getInt();
		Hero role = GameServer.vlineServerManager.getCharacterById(roleid);
		DanTian danTian = role != null ? role.getDanTianController().getDanTian() : null;
		if (role != null && danTian != null) {

			DantianModel model = danTian.getModel();
			String canleanSkillitem = model.getCanleanSkillitem();
			String[] split = new String[] {};
			if (canleanSkillitem != null && !canleanSkillitem.trim().equals("")) {
				split = canleanSkillitem.split(Symbol.FENHAO);
			}
			// CharacterCacheEntry wedderCache =
			// role.getMyFriendManager().getRoleWedingManager().getWedderCache();
			RoleWedingManager weddermanager = character.getMyFriendManager().getRoleWedingManager();

			int rolemp = role.getPopsinger();
			int istong = 0;
			int podtgrade = weddermanager.getWedderDanTian() != -1 ? weddermanager.getWedderDanTian() : 0;
			int pomp = weddermanager.getWedderMenPai() != -1 ? weddermanager.getWedderMenPai() : 0;
			// int pomp=wedderCache!=null?wedderCache.getPopsinger():0;
			// int pograde=wedderCache!=null?wedderCache.getDantianggrade():0;
			int poistong = 0;
			OtherRoleInfoResult53158 result = new OtherRoleInfoResult53158(model.getId(), rolemp, istong, podtgrade, pomp, poistong, model.getAddAttack(), model.getAddDefence(),
					model.getAddCrt(), model.getAddDodge(), model.getAddMaxhp(), model.getAddMengpaiWuxue(), model.getMengpaiSkillitem(), model.getAddJianghuWuxue(),
					model.getJianhuSkillitem(), model.getAddQitaJuexue(), model.getQitaSkillitem(), role.getCharacterSkillManager(), split);
			character.sendMsg(result);
		} else {
			character.sendMsg(new OtherRoleInfoResult53158());
		}
	}

}

class OtherRoleInfoResult53158 extends ServerResponse {
	public OtherRoleInfoResult53158() {
		setMsgCode(53158);
		writeInt(-1);
	}

	/**
	 * 角色丹田ID(byte),我的门派ID(byte),配偶的门派ID(byte),配偶的丹田ID(byte),增加攻击(int),增加防御(int)
	 * , 增加暴击(int)，增加闪避(int)，增加生命上限(int),所有门派绝学加成(int),门派武学包含技能(UTF),所有江湖绝学加成(
	 * int), 江湖武学包含技能,所有其它绝学加成(int),其它武学包含技能,技能数(byte){技能ID(int),是否己学(byte 1是
	 * 0不是)}
	 * 
	 * 
	 */
	public OtherRoleInfoResult53158(int rdtid, int mymp, int isperforation, int podtgrade, int pomp, int poisperforation, int addattack, int adddefence, int addcrt, int adddodge,
			int addmaxhp, int mengpaijuexue, String mpskillitem, int janghujuexue, String jhskillitem, int qitajuexue, String qtskillitem,
			BaseSkillManager<Hero> skillmanager, String[] skillitem) {
		setMsgCode(53158);
		// 角色丹田ID(byte),增加攻击(int),增加防御(int),增加暴击(int)，增加闪避(int)，增加生命上限(int),所有门派绝学加成(int),所有江湖绝学加成(int),
		// 所有其它绝学加成(int),技能数(byte){技能ID(int),是否己学(byte 1是 0不是)}

		try {
			writeByte(rdtid);
			writeByte(mymp);
			writeByte(isperforation);
			writeByte(podtgrade);
			writeByte(pomp);
			writeByte(poisperforation);
			writeInt(addattack);
			writeInt(adddefence);
			writeInt(addcrt);
			writeInt(adddodge);
			writeInt(addmaxhp);
			writeInt(mengpaijuexue);
			writeUTF(mpskillitem);
			writeInt(janghujuexue);
			writeUTF(jhskillitem);
			writeInt(qitajuexue);
			writeUTF(qtskillitem);
			writeByte(skillitem == null ? 0 : skillitem.length);
			for (String string : skillitem) {
				int parseInt = Integer.parseInt(string);
				writeInt(parseInt);
				CharacterSkill charskill = skillmanager.getCharacterSkillById(parseInt);
				writeByte(charskill == null ? 0 : 1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
