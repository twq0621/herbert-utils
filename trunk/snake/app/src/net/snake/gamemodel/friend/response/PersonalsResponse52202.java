package net.snake.gamemodel.friend.response;

import net.snake.commons.StringUtil;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.netio.ServerResponse;

/**
 * 自己详细资料显示
 * 
 */
public class PersonalsResponse52202 extends ServerResponse {
	// 昵称(str)、生日（String），性别(byte),血型(byte),省份(str),城市(str),职业(str),
	// 院校(str),mail(str),http(str),qq(str),电话(str),资料开放度(byte 1-完全保密 2-向好友开放
	// 3-完全开放)
	public PersonalsResponse52202(Personals personals) {
		setMsgCode(52202);
		try {
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
			// 相册 地址是空返回0 地址不是空并且审核为1发送地址，否者返回1表示在审核中
			// if (StringUtil.isNotEmpty(personals.getUrl())) {
			// if (personals.getShenhe()==1) {
			// writeUTF(personals.getUrl());
			// } else {
			// writeUTF("1");
			// }
			//
			// } else {
			// writeUTF("0");
			// }

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
