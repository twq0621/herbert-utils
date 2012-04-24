package net.snake.gamemodel.friend.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.StringUtil;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.friend.response.PersonalsResponse52204;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.netio.message.RequestMsg;

/**
 * 交友保存
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 52203)
public class PersonalsProcessor52203 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 昵称(str)、生日（String），性别(byte),血型(byte),省份(str),城市(str),职业(str),
		// 院校(str),mail(str),http(str),qq(str),电话(str),资料开放度(byte 1-完全保密 2-向好友开放
		// 3-完全开放)
		// 昵称：默认显示为空，最大可输入六个汉字
		// 职业：默认留空，最多可输入十个汉字
		// MAIL：默认留空，最多可输入25个字符
		// HTTP：默认留空，最多可输入25个字符
		// 腾讯QQ：默认留空，最多可输入15个字符
		// 联系号码：默认留空，最多可输入15个字符

		Personals personals = character.getMyPersonalsManager().getPersonals();

		// personals.setCharacterId(character.getId());
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setNicheng(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setShengri(request.getString());
		}
		personals.setSex((int) request.getByte());
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setXiexing(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setShengfen(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setChengshi(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setZhiye(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setYuanxiao(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setMail(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setHttp(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setQq(request.getString());
		}
		if (StringUtil.isNotEmpty(request.getString())) {
			personals.setLianxihaoma(request.getString());
		}
		personals.setBaomichengdu((int) request.getByte());

		// 昵称：默认显示为空，最大可输入六个汉字
		// 职业：默认留空，最多可输入十个汉字
		// MAIL：默认留空，最多可输入25个字符
		// HTTP：默认留空，最多可输入25个字符
		// 腾讯QQ：默认留空，最多可输入15个字符
		// 联系号码：默认留空，最多可输入15个字符
		if (personals.getNicheng().length() > 13) {
			PersonalsResponse52204 response52204 = new PersonalsResponse52204(CommonUseNumber.byte0, 1124);
			character.sendMsg(response52204);
		} else if (personals.getZhiye().length() > 21) {
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte0, 1125);
			character.sendMsg(response50404);
		} else if (personals.getMail().length() > 26) {
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte0, 1126);
			character.sendMsg(response50404);
		} else if (personals.getHttp().length() > 26) {
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte0, 1127);
			character.sendMsg(response50404);
		} else if (personals.getQq().toString().length() > 15) {
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte0, 1128);
			character.sendMsg(response50404);
		} else if (personals.getLianxihaoma().length() > 15) {
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte0, 1129);
			character.sendMsg(response50404);
		} else {
			character.getMyPersonalsManager().updatePersonals(personals);
			PersonalsResponse52204 response50404 = new PersonalsResponse52204(CommonUseNumber.byte1, 0);
			character.sendMsg(response50404);
		}

	}

}
