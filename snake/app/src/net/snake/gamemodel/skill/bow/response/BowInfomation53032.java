package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * 弓箭信息
 * 
 * @author serv_dev
 */
public class BowInfomation53032 extends ServerResponse {
	//
	// public BowInfomation53032(BowModel model, Bow bow) {
	// try {
	// setMsgCode(53032);
	// writeInt(bow == null ? -1 : bow.getBowmodelid());
	// writeInt(model.getId());
	// writeUTF(model.getNameI18n());
	// writeByte(model.getLevel().byteValue());
	// writeUTF(model.getDescI18n());
	// writeInt(model.getAddattack());
	// writeInt(model.getAdddefence());
	// writeInt(model.getAddcrt());
	// writeInt(model.getAdddodge());
	// writeInt(model.getAddmaxhp());
	// writeInt(model.getAddmaxmp());
	// writeInt(model.getAddmaxsp());
	// writeInt(model.getAddattackspeed());
	// writeInt(model.getAddmovespeed());
	// writeInt(model.getRatioAddattack());
	// writeInt(model.getRatioIgnoredefence());
	// String bindskill = model.getBindskill();
	// if (bindskill != null && !bindskill.equals("")) {
	// String[] split = bindskill.split(Symbol.FENHAO);
	// writeInt(split.length);
	// for (String string : split) {
	// writeInt(Integer.parseInt(string));
	// if (bow != null && bow.getBowmodelid().intValue() >= model.getId().intValue() && bow.getSkillitems().contains(string)) {
	// writeByte(1);
	// } else {
	// writeByte(0);
	// }
	// }
	//
	// } else {
	// writeInt(0);
	// }
	// if (bow != null && bow.getBowmodelid().intValue() == model.getId().intValue()) {
	// writeInt(bow.getBag1type());
	// writeInt(bow.getBag1count());
	// writeByte(bow.getBag1bind());
	// writeInt(bow.getBag2type());
	// writeInt(bow.getBag2count());
	// writeByte(bow.getBag2bind());
	// } else {
	// writeInt(-1);
	// writeInt(0);
	// writeByte(0);
	// writeInt(-1);
	// writeInt(0);
	// writeByte(0);
	// }
	// writeBoolean(bow != null ? bow.getEnable() : false);
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// }
	// }
}
