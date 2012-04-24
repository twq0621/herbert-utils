package net.snake.gamemodel.pet.logic;

import java.util.Date;

import net.snake.GameServer;
import net.snake.ai.formula.HorseFormula;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import org.apache.log4j.Logger;

public class HorseCreator {
	private static Logger logger = Logger.getLogger(HorseCreator.class);

	public static boolean createCharacterHorse(final Hero character, int horsemodelid) {
		return createCharacterHorse(character, horsemodelid, HorseStateConsts.REST) == null;
	}

	public static CharacterHorse createCharacterHorse(final Hero character, int horsemodelid, final int status) {
		HorseContainer horsecontainer = character.getCharacterHorseController().getBagHorseContainer();
		if (horsecontainer.getLeaveSpace() <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 551));
			return null;
		}
		// 通过id取的马的模型
		HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(horsemodelid);
		if (horseModel == null) {
			return null;
		}
		HorseModel baseHorseModel = HorseModelDataProvider.getInstance().getHorseModelByID(horseModel.getBaseId());
		if (baseHorseModel == null) {
			return null;
		}
		final CharacterHorse characterHorse = new CharacterHorse();
		characterHorse.setCharacterId(character.getId());
		characterHorse.setHorseModelId(horsemodelid);
		characterHorse.setName(horseModel.getNameI18n());
		characterHorse.setGrade(horseModel.getInitLevel());
		characterHorse.setDefaultSkillId(0);

		characterHorse.setExperience(0);
		characterHorse.setStatus(status);
		characterHorse.setLocation(1);
		// 活力：
		characterHorse.setLivingness(horseModel.getLivingnessMax());
		characterHorse.setLivingnessMax(characterHorse.getLivingness());

		//
		characterHorse.setAttack((int)baseHorseModel.getAddOwnerAttack());
		characterHorse.setDefence((int)baseHorseModel.getAddOwnerDefence());
		characterHorse.setCrt((int)baseHorseModel.getAddOwnerCrt());
		characterHorse.setDodge((int)baseHorseModel.getAddOwnerDodge());
		characterHorse.setHit((int)baseHorseModel.getAddOwnerHit());
		characterHorse.setHp((int)baseHorseModel.getAddOwnerHp());
		characterHorse.setMp((int)baseHorseModel.getAddOwnerMp());
		if (baseHorseModel.getId() == horseModel.getId()) {
			characterHorse.setExtraAttack(0);
			characterHorse.setExtraDefence(0);
			characterHorse.setExtraCrt(0);
			characterHorse.setExtraHit(0);
			characterHorse.setExtraDodge(0);
			characterHorse.setExtraHp(0);
			characterHorse.setExtraMp(0);
		} else {
			characterHorse.setExtraAttack((int)horseModel.getAddOwnerAttack()+1);
			characterHorse.setExtraDefence((int)horseModel.getAddOwnerDefence()+1);
			characterHorse.setExtraCrt((int)horseModel.getAddOwnerCrt()+1);
			characterHorse.setExtraDodge((int)horseModel.getAddOwnerDodge()+1);
			characterHorse.setExtraHit((int)horseModel.getAddOwnerHit()+1);
			characterHorse.setExtraHp((int)horseModel.getAddOwnerHp()+1);
			characterHorse.setExtraMp((int)horseModel.getAddOwnerMp()+1);
		}
		characterHorse.setStallCopper(0);
		characterHorse.setStallIngot(0);
		characterHorse.setCreateDate(new Date());
		characterHorse.setPin(horseModel.getQuality());
		characterHorse.setJinjieCount(0);
		int skillcount = horseModel.getInitLevel();
		int horsePrice = HorseFormula.getHorsePrice(characterHorse, skillcount, 0);
		characterHorse.setHorsePrice(horsePrice);
		// 异步更新数据库，之后调用一个重新加载人数获得马的方法
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterHorseDataProvider.getInstance().insertCharacterHorse(characterHorse);
					if (status == HorseStateConsts.FUHUA) {
						return;
					}
					character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
						@Override
						public void run() {
							HorseContainer horsecontainer = character.getCharacterHorseController().getBagHorseContainer();
							horsecontainer.addHorse(characterHorse);
						}
					});
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		return characterHorse;
	}
}
