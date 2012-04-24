package net.snake.gamemodel.friend.response;

import net.snake.commons.StringUtil;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.netio.ServerResponse;

/**
 * 交友显示
 * 
 */
public class PersonalsResponse52206 extends ServerResponse {

	public PersonalsResponse52206(Personals personals, int characterId, byte type) {
		setMsgCode(52206);
		try {
			// 昵称(str)、生日（String），性别(byte),血型(byte),省份(str),城市(str),职业(str),
			// 院校(str),mail(str),http(str),qq(str),电话(str),资料开放度(byte 1-完全保密
			// 2-向好友开放 3-完全开放)
			if (type == 0) {
				writeByte(type);
				return;
			}
			writeByte(type);
			if (StringUtil.isNotEmpty(personals.getNicheng())) {
				writeUTF(personals.getNicheng());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getShengri())) {
				writeUTF(personals.getShengri());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getSex())) {
				writeByte((byte) personals.getSex().intValue());
			} else {
				writeByte(0);
			}
			if (StringUtil.isNotEmpty(personals.getXiexing())) {
				writeUTF(personals.getXiexing());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getShengfen())) {
				writeUTF(personals.getShengfen());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getChengshi())) {
				writeUTF(personals.getChengshi());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getZhiye())) {
				writeUTF(personals.getZhiye());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getYuanxiao())) {
				writeUTF(personals.getYuanxiao());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getMail())) {
				writeUTF(personals.getMail());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getHttp())) {
				writeUTF(personals.getHttp());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getQq())) {
				writeUTF(personals.getQq());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getLianxihaoma())) {
				writeUTF(personals.getLianxihaoma());
			} else {
				writeUTF("");
			}
			if (StringUtil.isNotEmpty(personals.getBaomichengdu())) {
				writeByte((byte) personals.getBaomichengdu().intValue());
			} else {
				writeByte(3);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
