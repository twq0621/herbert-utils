package net.snake.gamemodel.pet.catchpet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.snake.GameServer;
import net.snake.commons.program.Updatable;
import net.snake.consts.HorseContainerType;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.config.bean.ConfigParam;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;

import org.apache.log4j.Logger;
/**
 * 用于管理玩家当前的坐骑使用状态
 * 
 * @author serv_dev
 * 
 */
public class CharacterHorseController implements Updatable {
	private static Logger logger = Logger.getLogger(CharacterHorseController.class);

	private final Hero character;

	// 当前显示的马
	private Horse currentShowHorse;

	// 马的容器
	private HorseContainer bagHorseContainer;
	private HorseContainer storageHorseContainer;
	private HorseContainer stallHorseContainer;

	/**
	 * 获得所有的坐骑
	 * 
	 * @return
	 */
	public Collection<Horse> getAllHorse() {
		List<Horse> list = new ArrayList<Horse>();
		if (currentShowHorse != null) {
			list.add(currentShowHorse);
		}
		for (Iterator<Horse> iterator = bagHorseContainer.getHorseCollection().iterator(); iterator.hasNext();) {
			Horse horse = iterator.next();
			list.add(horse);
		}

		for (Iterator<Horse> iterator = storageHorseContainer.getHorseCollection().iterator(); iterator.hasNext();) {
			Horse horse = iterator.next();
			list.add(horse);
		}

		for (Iterator<Horse> iterator = stallHorseContainer.getHorseCollection().iterator(); iterator.hasNext();) {
			Horse horse = iterator.next();
			list.add(horse);
		}

		return list;
	}

	public void destory() {
		bagHorseContainer.destory();
		storageHorseContainer.destory();
		stallHorseContainer.destory();
		currentShowHorse = null;
	}

	public CharacterHorseController(Hero character) {
		this.character = character;
	}

	public HorseContainer getBagHorseContainer() {
		return bagHorseContainer;
	}

	public HorseContainer getStallHorseContainer() {
		return stallHorseContainer;
	}

	public HorseContainer getStorageHorseContainer() {
		return storageHorseContainer;
	}

	public void setCurrentShowHorse(Horse currentShowHorse) {
		this.currentShowHorse = currentShowHorse;
	}

	public void setCurrentRideHorse(Horse currentShowHorse) {
		this.currentShowHorse = currentShowHorse;
	}

	public HorseContainer getHorseContainer(HorseContainerType horseContainerType) {
		switch (horseContainerType) {
		case onBag:
			return bagHorseContainer;
		case onStall:
			return stallHorseContainer;
		case onStorage:
			return storageHorseContainer;
		}
		return null;
	}

	public HorseContainer getHorseContainer(int position) {
		if (position >= bagHorseContainer.getPostionBegin() && position <= bagHorseContainer.getPostionEnd()) {
			return bagHorseContainer;
		} else if (position >= storageHorseContainer.getPostionBegin() && position <= storageHorseContainer.getPostionEnd()) {
			return storageHorseContainer;
		} else if (position >= stallHorseContainer.getPostionBegin() && position <= stallHorseContainer.getPostionEnd()) {
			return stallHorseContainer;
		}
		return null;
	}

	public void gainedMonsterExp(SceneMonster monster) {
		if (currentShowHorse != null) {
			currentShowHorse.gainedMonsterExp(monster);
		}
	}

	// 当主人攻击目标时
	public void onOwnerAttack(VisibleObject obj) {
		if (currentShowHorse != null) {
			currentShowHorse.onOwnerAttack(obj);
		}
	}

	public Horse getShowHorse() {
		return currentShowHorse;
	}

	public Horse getCurrentRideHorse() {
		return null;
	}

	public Collection<Horse> getHorseCollection() {
		return bagHorseContainer.getHorseCollection();
	}

	public void initData() {
		try {
			bagHorseContainer = new HorseContainer(character, HorseContainerType.onBag, 1, 1 + character.getMaxHorseAmount());
			storageHorseContainer = new HorseContainer(character, HorseContainerType.onStorage, 101, 101 + character.getMaxStorageHorseAmount());
			stallHorseContainer = new HorseContainer(character, HorseContainerType.onStall, 201, 201 + 5);

			List<CharacterHorse> characterHorsesList = CharacterHorseDataProvider.getInstance().getCharacterHorseByCharacterID(character.getId());
			// 马的活力计算
			long oldtime = character.getLastdate().getTime();
			long newtime = System.currentTimeMillis();
			newtime = newtime - oldtime;
			int huoli = (int) newtime / (GameServer.configParamManger.getConfigParam().getHorseRestResume() * 1000);

			if (characterHorsesList.size() > 0) {
				for (CharacterHorse characterHorse : characterHorsesList) {
					Horse horse = new Horse(character, characterHorse);
					if (characterHorse.getStatus() == HorseStateConsts.FUHUA) {
						character.setFuhuaCharacterHorse(characterHorse);
						continue;
					}
					// 马的活力增加
					if (characterHorse.getLivingness().intValue() != characterHorse.getLivingnessMax().intValue()) {
						if (characterHorse.getLivingness() + huoli < characterHorse.getLivingnessMax()) {
							characterHorse.setLivingness(characterHorse.getLivingness() + huoli);
						} else {
							characterHorse.setLivingness(characterHorse.getLivingnessMax());
						}
					}

					horse.initHorse();

					switch (characterHorse.getStatus()) {
					case HorseStateConsts.SHOW:
						currentShowHorse = horse;
						break;

					}
					HorseContainer horsecontainer = getHorseContainer(characterHorse.getLocation());
					if (horsecontainer == null) {
						bagHorseContainer.addMemHorse(horse);
					} else {
						horsecontainer.addMemHorse(horse);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public Horse getHorseById(int horseid) {
		Horse horse = bagHorseContainer.getHorseByID(horseid);
		if (horse != null) {
			return horse;
		}
		horse = storageHorseContainer.getHorseByID(horseid);
		if (horse != null) {
			return horse;
		}
		return stallHorseContainer.getHorseByID(horseid);
	}

	// 休息
	public void unShow() {
		if (currentShowHorse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 575));
			return;
		}
		currentShowHorse.changeStatus(HorseStateConsts.REST);

	}

	// 放生(丢弃马)
	public void drop(Horse horse) {
		if (horse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		Collection<CharacterGoods> collection = horse.getGoodsContainer().getGoodsList();
		if (collection.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17003));
			return;
		}
		if (horse == currentShowHorse) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 577));
			return;
		}
		if (horse.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 578));
			return;
		}
		bagHorseContainer.dropHorse(horse);

	}

	@Override
	public void update(long now) {
		if (bagHorseContainer.getHorseCount() > 0) {
			for (Horse horse : bagHorseContainer.getHorseCollection()) {
				horse.update(now);
			}
		}
		if (storageHorseContainer.getHorseCount() > 0) {
			for (Horse horse : storageHorseContainer.getHorseCollection()) {
				horse.update(now);
			}
		}
		if (stallHorseContainer.getHorseCount() > 0) {
			for (Horse horse : stallHorseContainer.getHorseCollection()) {
				horse.update(now);
			}
		}
		// character.getUpdateObjManager().removeFrameUpdateObject(this);
	}

	public void updateCharacterHorse() {
		for (Horse horse : bagHorseContainer.getHorseCollection()) {
			try {
				horse.getHorseskillManager().save();
				CharacterHorseDataProvider.getInstance().updateCharacterHorse(horse.getCharacterHorse());
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (Horse horse : storageHorseContainer.getHorseCollection()) {
			try {
				CharacterHorseDataProvider.getInstance().updateCharacterHorse(horse.getCharacterHorse());
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		for (Horse horse : stallHorseContainer.getHorseCollection()) {
			try {
				CharacterHorseDataProvider.getInstance().updateCharacterHorse(horse.getCharacterHorse());
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	public void onOwnerLeaveScene() {
		// 这个方法会将坐骑重新变为展示非攻击展示模式
		if (currentShowHorse != null) {
			Horse temp = currentShowHorse;
			currentShowHorse.changeStatus(HorseStateConsts.REST);
			temp.changeStatus(HorseStateConsts.SHOW);
		}
	}

	public void show(Horse horse) {
		if (horse.getCharacterHorse().getLivingness() < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 647));
			return;
		}
		int usegradelimit = horse.getUseGradeLimit();
		if (usegradelimit > character.getGrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1031, String.valueOf(usegradelimit)));
			return;
		}
		if (horse == currentShowHorse) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 572));
			return;
		}
		if (horse.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 573));
			return;
		}
		if (currentShowHorse != null) {
			currentShowHorse.changeStatus(HorseStateConsts.REST);
		}
		currentShowHorse = horse;
		currentShowHorse.changeStatus(HorseStateConsts.SHOW);
		// character.getUpdateObjManager().addFrameUpdateObject(this);
	}

	public void onOwnerDie() {
		ConfigParam param = GameServer.configParamManger.getConfigParam();
		if (currentShowHorse != null) {
			character.getDiedata().showhorse = currentShowHorse;
			if (character.getEnmityManager().isContainRole()) {
				currentShowHorse.addLivingness(-param.getHorseLivingnessPunish1());
				character.getDiedata().showHorseLostLiving = param.getHorseLivingnessPunish1();
			} else {
				currentShowHorse.addLivingness(-param.getHorseLivingnessPunish2());
				character.getDiedata().showHorseLostLiving = param.getHorseLivingnessPunish2();
			}
		}
	}

	public void unRide() {

	}
}
