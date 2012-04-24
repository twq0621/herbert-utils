package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.gamemodel.heroext.dantian.persistence.DantianModelCacheManager;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 53151)
public class InfoQueryProcess53151 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		DanTian cha = character.getDanTianController().getDanTian();
		// 没有开启丹田则不允许查询
		if (cha != null) {
			byte dantianid = request.getByte();
			DantianModel req = null;
			if (dantianid == -1) {
				req = cha.getModel();
			} else {
				req = DantianModelCacheManager.getInstance().getModelById(dantianid);
			}
			if (req != null) {
				// String canleanSkillitem = req.getCanleanSkillitem();
				// String[] split = new String[] {};
				// if (canleanSkillitem != null && !canleanSkillitem.trim().equals("")) {
				// split = canleanSkillitem.split(Symbol.FENHAO);
				// }
				// // CharacterCacheEntry wedderCache =
				// // character.getMyFriendManager().getRoleWedingManager().getWedderCache();
				// RoleWedingManager weddermanager = character.getMyFriendManager().getRoleWedingManager();
				//
				// int mymp = character.getPopsinger();
				// int istong = 0;
				// int podtgrade = weddermanager.getWedderDanTian() != -1 ? weddermanager.getWedderDanTian() : 0;
				// int pomp = weddermanager.getWedderMenPai() != -1 ? weddermanager.getWedderMenPai() : 0;
				// // int
				// // podtgrade=wedderCache!=null?wedderCache.getDantianggrade():0;
				// // int pomp=wedderCache!=null?wedderCache.getPopsinger():0;
				// int poistong = 0;
				// character.sendMsg(new InfoMsg53152(dantianid, cha.getDantianid(), mymp, istong, podtgrade, pomp, poistong, req.getAddAttack(), req.getAddDefence(),
				// req.getAddCrt(), req.getAddDodge(), req.getAddMaxhp(), req.getAddMengpaiWuxue(), req.getMengpaiSkillitem(), req.getAddJianghuWuxue(), req
				// .getJianhuSkillitem(), req.getAddQitaJuexue(), req.getQitaSkillitem(), character.getSkillManager(), split));
			}
		}
	}

}
