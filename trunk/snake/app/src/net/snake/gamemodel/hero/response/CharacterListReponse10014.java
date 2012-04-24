package net.snake.gamemodel.hero.response;

import java.util.Collection;
import java.util.List;

import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.bean.GoodsDataEntry;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.hero.bean.CharacterForList;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.bow.persistence.BowEntryManager;
import net.snake.netio.ServerResponse;

public class CharacterListReponse10014 extends ServerResponse {
	public CharacterListReponse10014(Collection<CharacterForList> characterlist) {
		setMsgCode(10014);
		try {
			writeByte(characterlist.size());
			for (CharacterForList c : characterlist) {
				addCharacter(c);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addCharacter(CharacterForList character) throws Exception {
		writeInt(character.getId());
		writeByte(character.getHeadimg());
		writeUTF(character.getViewName());
		writeShort(character.getGrade());
		writeByte(character.getPopsinger());

		List<GoodsDataEntry> avatarlist = GoodsDataEntryManager.getInstance().getCharacterAvatarGoodsList(character.getId());
		Bow bow = BowEntryManager.getInstance().getBow(character.getId());
		if (bow != null) {
			avatarlist.add(bow.getGoods());
		}

		writeByte(avatarlist.size());
		for (GoodsDataEntry goods : avatarlist) {
			Goodmodel gm = GoodmodelManager.getInstance().get(goods.getGoodmodelId());
			writeInt(gm.getAvatarId());
			writeByte(goods.getPosition());
		}

		CharacterHorse ridehorse = CharacterHorseDataProvider.getInstance().getRideHorseByCharacterID(character.getId());
		if (ridehorse == null) {
			writeByte(0);
		} else {
			HorseModel horsemodel = HorseModelDataProvider.getInstance().getHorseModelByID(ridehorse.getHorseModelId());
			writeByte(1);
			writeInt(horsemodel.getAvatarId());
		}
		writeUTF(character.getFactionName());
	}
}
