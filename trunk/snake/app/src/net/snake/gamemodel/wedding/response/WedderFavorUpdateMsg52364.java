package net.snake.gamemodel.wedding.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.netio.ServerResponse;

public class WedderFavorUpdateMsg52364 extends ServerResponse {
	public WedderFavorUpdateMsg52364(Hero character, Hero peiOu, Couples couples) {
		this.setMsgCode(52364);
		if (character.isMale()) {
			this.writeInt(couples.getFemaleId());
			this.writeBoolean(peiOu != null);
			this.writeInt(couples.getMaleFavor());
		} else {
			this.writeInt(couples.getMaleId());
			this.writeBoolean(peiOu != null);
			this.writeInt(couples.getFemaleFavor());
		}
		if (peiOu == null) {
			this.writeByte(0);
			this.writeByte(0);
		} else {
			this.writeByte(peiOu.getVlineserver().getLineid());
			this.writeByte(peiOu.getCharacterStatus());
		}
	}

	public WedderFavorUpdateMsg52364(int wederId) {
		this.setMsgCode(52364);
		this.writeInt(wederId);
		this.writeByte(0);
		this.writeInt(0);
		this.writeByte(0);
		this.writeByte(0);
	}
}
