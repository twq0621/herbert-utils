package net.snake.gamemodel.skill.bow.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.bow.bean.BowModel;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 53045, accessLimit = 100)
public class OtherRoleBowInfoProcess53045 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int int1 = request.getInt();
		Hero characterById = GameServer.vlineServerManager.getCharacterById(int1);
		character.sendMsg(new OtherRoleBowInfomation53046(characterById));
	}
}

class OtherRoleBowInfomation53046 extends ServerResponse {

	public OtherRoleBowInfomation53046(Hero character) {
		try {
			setMsgCode(53046);
			if (character == null || character.getBowController().getBow() == null) {
				writeInt(-1);
			} else {
				Bow bow = character.getBowController().getBow();
				writeInt(bow.getBowmodelid());
				if (bow != null) {
					BowModel model = bow.getModel();
					// writeInt(model.getId());
					writeUTF(model.getNameI18n());
					writeByte(model.getLevel().byteValue());
					writeUTF(model.getDescI18n());
					writeInt(model.getAddattack());
					writeInt(model.getAdddefence());
					writeInt(model.getAddcrt());
					writeInt(model.getAdddodge());
					writeInt(model.getAddmaxhp());
					writeInt(model.getAddmaxmp());
					writeInt(model.getAddmaxsp());
					writeInt(model.getAddattackspeed());
					writeInt(model.getAddmovespeed());
					writeInt(model.getRatioAddattack());
					writeInt(model.getRatioIgnoredefence());
					String bindskill = model.getBindskill();
					if (bindskill != null && !bindskill.equals("")) {
						String[] split = bindskill.split(Symbol.FENHAO);
						writeInt(split.length);
						for (String string : split) {
							writeInt(Integer.parseInt(string));
							if (bow != null && bow.getSkillitems().contains(string)) {
								writeByte(1);
							} else {
								writeByte(0);
							}
						}
					} else {
						writeInt(0);
					}
					if (bow != null && bow.getBowmodelid().intValue() == model.getId().intValue()) {
						writeInt(bow.getBag1type());
						writeInt(bow.getBag1count());
						writeByte(bow.getBag1bind());
						writeInt(bow.getBag2type());
						writeInt(bow.getBag2count());
						writeByte(bow.getBag2bind());
					} else {
						writeInt(-1);
						writeInt(0);
						writeByte(0);
						writeInt(-1);
						writeInt(0);
						writeByte(0);
					}
					writeBoolean(bow != null ? bow.getEnable() : false);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
