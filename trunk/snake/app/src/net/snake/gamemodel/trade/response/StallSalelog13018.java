package net.snake.gamemodel.trade.response;

import java.util.Calendar;
import java.util.Date;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;

public class StallSalelog13018 extends ServerResponse {

	public StallSalelog13018(Hero character, Hero stallowner, CharacterGoods goods) {

		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);

		setMsgCode(13018);
		ServerResponse out = this;
		try {
			out.writeByte(ca.get(Calendar.HOUR_OF_DAY));
			out.writeByte(ca.get(Calendar.MINUTE));
			out.writeByte(ca.get(Calendar.SECOND));
			out.writeUTF(character.getViewName());
			out.writeUTF(goods.getGoodModel().getNameI18n());
			out.writeInt(goods.getStallCopper());
			out.writeInt(goods.getStallIngot());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// 时(byte)、分(byte)、秒(byte)、购买者名(str)、购买物品名(str)、获得铜钱数(int)、获得元宝数(int)

	}

	public StallSalelog13018(Hero character, Hero stallowner, Horse horse) {

		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);

		setMsgCode(13018);
		ServerResponse out = this;
		try {
			out.writeByte(ca.get(Calendar.HOUR_OF_DAY));
			out.writeByte(ca.get(Calendar.MINUTE));
			out.writeByte(ca.get(Calendar.SECOND));
			out.writeUTF(character.getViewName());
			out.writeUTF(horse.getHorseModel().getNameI18n());
			out.writeInt(horse.getCharacterHorse().getStallCopper());
			out.writeInt(horse.getCharacterHorse().getStallIngot());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
