package net.snake.gamemodel.pet.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.snake.GameServer;
import net.snake.consts.HorseContainerType;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;
import net.snake.gamemodel.pet.response.HorseChangeStatusResponse;
import net.snake.gamemodel.pet.response.HorseListResponse60018;
import net.snake.serverenv.stall.OnlineStallManagerImp;
import org.apache.log4j.Logger;

public class HorseContainer {
	private static Logger logger = Logger.getLogger(HorseContainer.class);
	private Hero character;
	private HorseContainerType containerType;
	private int postionBegin = 1;
	private int postionEnd = 20;
	private Map<Integer, Horse> horseMap = new HashMap<Integer, Horse>();

	public void destory() {
		horseMap.clear();
		// horseMap = null;
	}

	public int getPostionBegin() {
		return postionBegin;
	}

	public Hero getCharacter() {
		return character;
	}

	public HorseContainerType getContainerType() {
		return containerType;
	}

	public int getPostionEnd() {
		return postionEnd;
	}

	public int getMaxSpace() {
		return postionEnd - postionBegin;
	}

	public HorseContainer(Hero character, HorseContainerType containerType, int postionbegin, int postionend) {
		this.character = character;
		this.containerType = containerType;
		this.postionBegin = postionbegin;
		this.postionEnd = postionend;
	}

	public void addMemHorse(Horse horse) {
		if (horseMap.get(horse.getId()) == null) {
			horseMap.put(horse.getId(), horse);
			if (isneedchangeloction(horse)) {
				horse.getCharacterHorse().setLocation(getNextPostion());
				CharacterHorseDataProvider.getInstance().asynchronousUpdateCharacterHorse(horse.getCharacterHorse(), character);
			}
		}
	}

	public int getHorseCountByModelId(int modelid) {
		int count = 0;
		for (Horse horse : horseMap.values()) {
			if (horse.getHorseModel().getId() == modelid) {
				count++;
			}
		}
		return count;
	}

	//
	public void addHorse(CharacterHorse characterHorse) {
		if (getLeaveSpace() <= 0) {
			return;
		}
		characterHorse.setCharacterId(character.getId());
		characterHorse.setStatus(HorseStateConsts.REST);
		Horse horse = new Horse(character, characterHorse);
		horse.initHorse();
		addMemHorse(horse);
		horse.getCharacterHorse().setHorsePrice(horse.getSelfPrice());
		// 更新马配置
		CharacterHorseDataProvider.getInstance().asynchronousUpdateCharacterHorse(horse.getCharacterHorse(), character);
		character.sendMsg(new HorseListResponse60018(this));
	}

	private boolean isneedchangeloction(Horse newhorse) {
		HashSet<Integer> postions = new HashSet<Integer>();
		for (Horse horse : horseMap.values()) {
			postions.add(horse.getCharacterHorse().getLocation());
		}
		if (newhorse.getCharacterHorse().getLocation() >= postionBegin && newhorse.getCharacterHorse().getLocation() < postionEnd
				&& !postions.contains(newhorse.getCharacterHorse().getLocation())) {
			return false;
		} else {
			return true;
		}
	}

	// 获得剩余空间
	public int getLeaveSpace() {
		switch (containerType) {
		case onBag:
			return character.getMaxHorseAmount() - horseMap.size();
		case onStorage:
			return character.getMaxStorageHorseAmount() - horseMap.size();
		case onStall:
			return 5 - horseMap.size();
		}
		return 0;
	}

	public int getLeaveSpaceExcludeTradeHorse() {
		for (Horse horse : horseMap.values()) {
			if (horse.isInTrade()) {
				// 同时只能交易一个马
				return getLeaveSpace() - 1;
			}
		}
		return getLeaveSpace();
	}

	public Horse getHorseByID(int id) {
		return horseMap.get(id);
	}

	public Collection<Horse> getHorseCollection() {
		return horseMap.values();
	}

	// 接受其他人的坐骑,前提必须将他人的坐骑变为骑乘状态
	public void receiveOthersHorse(final Horse horse) {
		horse.setCharacter(character);
		horse.getCharacterHorse().setCharacterId(character.getId());
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterHorseDataProvider.getInstance().updateCharacterHorse(horse.getCharacterHorse());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

		});
		horse.getGoodsContainer().changeOwner(character);
		addMemHorse(horse);
		character.sendMsg(new HorseListResponse60018(this));
		if (containerType == HorseContainerType.onStall || containerType == HorseContainerType.onStall) {
			OnlineStallManagerImp.getInstance().checkCharacterStall(character);
		}
	}

	// 释放马,目的是从当前主人的内存中移除,并在数据库中标识为没有主人, 为了让其他人拥有
	public void releaseHorse(final Horse horse) {
		// 删除马
		horseMap.remove(horse.getId());
		character.sendMsg(new HorseListResponse60018(this));
		if (containerType == HorseContainerType.onStall || containerType == HorseContainerType.onStall) {
			OnlineStallManagerImp.getInstance().checkCharacterStall(character);
		}
	}

	// 销毁马
	public Horse dropHorse(Horse horse) {
		horseMap.remove(horse.getId());
		// 删除马
		CharacterHorseDataProvider.getInstance().asynDeleteCharacterHorse(character, horse.getCharacterHorse());
		// 删除马对应的物品
		horse.getGoodsContainer().removeAllGoods();
		character.sendMsg(new HorseChangeStatusResponse(character.getId(), horse.getHorseModel().getId(), horse.getId(), HorseStateConsts.DROP));

		return horse;
	}

	public void moveToContainer(final HorseContainer targetContainer, final Horse horse) {
		if (targetContainer == this) {
			return;
		}
		if (horse.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 625));
			return;
		}
		if (targetContainer.getLeaveSpace() > 0) {
			horseMap.remove(horse.getId());
			targetContainer.addMemHorse(horse);
			CharacterHorseDataProvider.getInstance().asynchronousUpdateCharacterHorse(horse.getCharacterHorse(), character);
			// if (containerType.equals(HorseContainerType.onStall)) {
			character.sendMsg(new HorseListResponse60018(this));
			character.sendMsg(new HorseListResponse60018(targetContainer));

		}
		if (targetContainer.getContainerType() == HorseContainerType.onStall || containerType == HorseContainerType.onStall) {
			OnlineStallManagerImp.getInstance().checkCharacterStall(character);
		}
	}

	private int getNextPostion() {
		HashSet<Integer> postions = new HashSet<Integer>();
		for (Horse horse : horseMap.values()) {
			postions.add(horse.getCharacterHorse().getLocation());
		}

		for (int i = postionBegin; i < postionEnd; i++) {
			if (!postions.contains(i)) {
				return i;
			}
		}
		return 0;
	}

	public int getHorseCount() {
		return horseMap.size();
	}

	public void setCapacity(int maxHorseAmount) {
		postionEnd = postionBegin + maxHorseAmount;
	}

	public Horse getMaxPingJieHorse() {
		Collection<Horse> collection = this.getHorseCollection();
		int type = 0;
		Horse re = null;
		for (Horse horse : collection) {
			if (type < horse.getHorseModel().getKind()) {
				type = horse.getHorseModel().getKind();
				re = horse;
			}
		}
		return re;
	}
}
