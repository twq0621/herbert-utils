package net.snake.gamemodel.goods.logic;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodsContainerType;
import net.snake.consts.Position;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.GoodsDataEntry;
import net.snake.gamemodel.goods.logic.container.AbstractGirdContainer;
import net.snake.gamemodel.goods.logic.container.AcrossGoodsContainer;
import net.snake.gamemodel.goods.logic.container.BagGoodsContainer;
import net.snake.gamemodel.goods.logic.container.BodyGoodsContiner;
import net.snake.gamemodel.goods.logic.container.HorseBodyGoodsContiner;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.logic.container.IBody;
import net.snake.gamemodel.goods.logic.container.IContainer;
import net.snake.gamemodel.goods.logic.container.IGirdContainer;
import net.snake.gamemodel.goods.logic.container.IStall;
import net.snake.gamemodel.goods.logic.container.IStorage;
import net.snake.gamemodel.goods.logic.container.StallGoodsContainer;
import net.snake.gamemodel.goods.logic.container.StorageGoodsContainer;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.CharacterLoseGoods11214;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import org.apache.log4j.Logger;


/**
 * 人物物品管理者
 * 
 * @author serv_dev
 * 
 */
public class CharacterGoodControllerImp implements CharacterGoodController {
	private static Logger logger = Logger.getLogger(CharacterGoodControllerImp.class);
	private final Hero character;
	// 各种缓存=================================================================
	private BodyGoodsContiner bodyGoodsContainer = new BodyGoodsContiner();
	private BagGoodsContainer bagGoodsContainer = new BagGoodsContainer();
	private StorageGoodsContainer storageGoodsContainer = new StorageGoodsContainer();
	private StallGoodsContainer stallGoodsContainer = new StallGoodsContainer();
	private AcrossGoodsContainer acrossGoodsContainer = new AcrossGoodsContainer();
	// 为了避免为每一个马初始化一次,在此做一个临时对像缓存
	private Map<Integer, HorseBodyGoodsContiner> horseGoodsMap = new ConcurrentHashMap<Integer, HorseBodyGoodsContiner>();
	private CharacterGoodsCoolingTimeManager coolingTimeManager = new CharacterGoodsCoolingTimeManager(this);

	@Override
	public void destory() {
		bodyGoodsContainer.destory();
		bagGoodsContainer.destory();
		storageGoodsContainer.destory();
		stallGoodsContainer.destory();
		acrossGoodsContainer.destory();
		horseGoodsMap.clear();
		coolingTimeManager.destory();

	}

	public IBody getBodyGoodsContiner() {
		return bodyGoodsContainer;
	}

	public IBag getBagGoodsContiner() {
		return bagGoodsContainer;
	}

	public IStorage getStorageGoodsContainer() {
		return storageGoodsContainer;
	}

	public IStall getStallGoodsContainer() {
		return stallGoodsContainer;
	}

	// 为了避免为每一个马初始化一次,在此做一个临时对像缓存,在马初始化时调用,并从此对像中移除
	public HorseBodyGoodsContiner getAndRemoveHorseBodyGoodsContainer(int horseid) {
		getOrCreateHorseBodyGoodsContainer(horseid);
		return horseGoodsMap.remove(horseid);
	}

	private IContainer getGoodsContainerTypeByPosition(int position, int horseid) {
		if ((position >= Position.BodyGoodsBeginPostion && position <= Position.BodyGoodsEndPostion)) {
			return bodyGoodsContainer;
		} else if ((position >= Position.HorseGoodsBeginPostion && position <= Position.HorseGoodsEndPostion)) {
			Horse horse = character.getCharacterHorseController().getHorseById(horseid);
			if (horse != null) {
				return horse.getGoodsContainer();
			}
			return null;
		} else if (position >= Position.BagGoodsBeginPostion && position <= Position.BagGoodsEndPostion) {
			return bagGoodsContainer;
		} else if (position >= Position.StorageGoodsBeginPostion && position <= Position.StorageGoodsEndPostion) {
			return storageGoodsContainer;
		} else if (position >= Position.StallGoodsBeginPostion && position <= Position.StallGoodsEndPostion) {
			return stallGoodsContainer;
		} else if (position >= Position.AcrossBagBeginPostion && position <= Position.AcrossBagEndPostion) {
			return acrossGoodsContainer;
		}

		return null;
	}

	public CharacterGoodControllerImp(Hero character) {
		this.character = character;
	}

	// 重新初始化跨服包裹
	public void reInitAcrossBag() {
		acrossGoodsContainer.clearMemery();
		try {
			List<GoodsDataEntry> list = GoodsDataEntryManager.getInstance().getCharacterGoodsList(character.getId(), Position.AcrossBagBeginPostion, Position.AcrossBagEndPostion);
			for (GoodsDataEntry goodsdataentry : list) {
				CharacterGoods charactergoods = new CharacterGoods(goodsdataentry);
				acrossGoodsContainer.initGoods(charactergoods);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 初始化数据,人物登录初始化人物数据的时候初始化
	 */
	public void initData() {
		try {
			bodyGoodsContainer.setOwner(character);
			storageGoodsContainer.setOwner(character);
			storageGoodsContainer.setTotalCapacity(character.getMaxStorageAmount());
			bagGoodsContainer.setOwner(character);
			bagGoodsContainer.setTotalCapacity(character.getMaxBagAmount());
			acrossGoodsContainer.setOwner(character);
			stallGoodsContainer.setOwner(character);
			List<GoodsDataEntry> list = GoodsDataEntryManager.getInstance().getCharacterGoodsList(character.getId());
			for (GoodsDataEntry goodsdataentry : list) {
				CharacterGoods charactergoods = new CharacterGoods(goodsdataentry);
				if (charactergoods.getQuickbarindex() != 0) {
					character.getQuickbarController().initQuickBarGoods(charactergoods);
				}
				if (charactergoods.getGoodModel().isEquipment()) {
					charactergoods.equipmentUpdate();
				}
				int position = charactergoods.getPosition();
				if ((position >= Position.BodyGoodsBeginPostion && position <= Position.BodyGoodsEndPostion)) {
					bodyGoodsContainer.initGoods(charactergoods);
				} else if ((position >= Position.HorseGoodsBeginPostion && position <= Position.HorseGoodsEndPostion)) {
					if (charactergoods.getInHorseId() != 0) {// 在马身上
						HorseBodyGoodsContiner temphorsegoodsmap = getOrCreateHorseBodyGoodsContainer(charactergoods.getInHorseId());
						temphorsegoodsmap.initGoods(charactergoods);
					}
				} else if (position >= Position.BagGoodsBeginPostion && position <= Position.BagGoodsEndPostion) {
					bagGoodsContainer.initGoods(charactergoods);
				} else if (position >= Position.AcrossBagBeginPostion && position <= Position.AcrossBagEndPostion) {
					acrossGoodsContainer.initGoods(charactergoods);
				} else if (position >= Position.StorageGoodsBeginPostion && position <= Position.StorageGoodsEndPostion) {
					storageGoodsContainer.initGoods(charactergoods);
				} else if (position >= Position.StallGoodsBeginPostion && position <= Position.StallGoodsEndPostion) {
					stallGoodsContainer.initGoods(charactergoods);
				}
			}
			coolingTimeManager.initDate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public boolean addGoodsToBag(Collection<CharacterGoods> goodslist) {
		return bagGoodsContainer.addGoods(goodslist);
	}

	public boolean addGoodsToBag(CharacterGoods goods) {
		return bagGoodsContainer.addGoods(goods);
	}

	@Override
	public void deleteCharacterGoods(CharacterGoods characterGoods) {
		IContainer container = getGoodsContainerTypeByPosition(characterGoods.getPosition(), characterGoods.getInHorseId());
		if (container != null) {
			((IGirdContainer) container).deleteGoodsByPostion(characterGoods.getPosition());
		}
	}

	// 从包裹中删除一定数量某模型的物品
	public boolean deleteGoodsFromBag(int modelid, int deletecount) {
		return bagGoodsContainer.deleteGoodsByModel(modelid, deletecount);
	}

	@Override
	public boolean deleteGoodsFromBag(int modelid, int deletecount, boolean bind) {
		return bagGoodsContainer.deleteGoodsByModel(modelid, deletecount, bind);
	}

	@Override
	public void deleteCharacterGoods(short postion, int horseid) {
		CharacterGoods goods = null;
		if (horseid == 0) {
			goods = getGoodsByPositon(postion);
		} else {
			Horse horse = character.getCharacterHorseController().getHorseById(horseid);
			if (horse != null) {
				goods = horse.getGoodsContainer().getGoodsByPostion(postion);
			}
		}
		// ========================
		if (goods == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 558));
			return;
		}
		if (goods.getGoodModel().isGiftBag()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 559));
			return;
		}
		deleteCharacterGoods(goods);
	}


	public boolean combineDeleteCailiao(CharacterGoods equipment, boolean bind, int cailiaoNum, int... cailiao) {
		/**
		 *通过调用deleteCailiao(int cailiaoModel, int totalNum)进行删除
		 *     ↓
		 * 通过调用deleteGoodsFromBag(int modelid, int deletecount, boolean bind) 
		 *   ↓
		 * bagGoodsContainer.deleteGoodsByModel(modelid, deletecount, bind);
		 */
		boolean delete = true;
		if (equipment.isBinding()) {
			int k = 0;
			for (int i = 0; i < cailiaoNum; i++) {
				if (cailiao[k + 1] == 0) {
					k = k + 2;
					continue;
				}
				delete = deleteCailiao(cailiao[k], cailiao[k + 1]);
				if (!delete) {
					return false;
				}
				k = k + 2;
			}
			return true;
		} else {
			boolean hasBind = false;
			int k = 0;
			for (int i = 0; i < cailiaoNum; i++) {// 包裹里是否有绑定的材料
				if (cailiao[k + 1] == 0) {
					k = k + 2;
					continue;
				}
				int count = this.getBagGoodsCountByModelID(cailiao[k], true);
				delete = deleteCailiao(cailiao[k], cailiao[k + 1]);
				if (!delete) {
					return false;
				}
				k = k + 2;
				if (count > 0 && !hasBind) {
					hasBind = true;
				}
			}
			if (hasBind && bind) {
				equipment.setBind(CommonUseNumber.byte1);
			}
			return true;
		}
	}

	public void deleteCailiaoByPos(CharacterGoods characterGoods, int totalNum) {
		if (characterGoods.getCount() - totalNum > 0) {
			characterGoods.setCount(characterGoods.getCount() - totalNum);
			GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(character, characterGoods);
			character.sendMsg(new GoodsUpdata10176(characterGoods));
			character.sendMsg(new CharacterLoseGoods11214(characterGoods.getGoodmodelId(), totalNum));
		} else {
			this.getBagGoodsContiner().deleteGoodsByPostion(characterGoods.getPosition());
		}
		if (characterGoods.getBuyType() == 1) {
			// 发送日志
			long time = 0;
			if (characterGoods.getLastDate() != null) {
				time = characterGoods.getLastDate().getTime() / 1000;
			}
			GameServer.dataLogManager.logRMBUseItem("1", characterGoods.getGoodmodelId(), characterGoods.getBuyPrice(), time, totalNum, character.getLogUid(), character.getHeroInfo());
		}
	}

	/**
	 * 先扣除绑定的材料，再扣除不绑定的材料
	 * 
	 * @param characterGoodController
	 * @param cailiaoModel
	 * @param cailiaoNum
	 */
	public boolean deleteCailiao(int cailiaoModel, int totalNum) {
		/**
		 * 通过调用deleteGoodsFromBag(int modelid, int deletecount, boolean bind) 
		 *  ↓
		 * bagGoodsContainer.deleteGoodsByModel(modelid, deletecount, bind);
		 */
		if (totalNum == 0)
			return true;
		int bindCount = this.getBagGoodsCountByModelID(cailiaoModel, true);
		int count = this.getBagGoodsCountByModelID(cailiaoModel, false);
		if (bindCount + count < totalNum) {
			logger.warn("bag calc with err");
			return false;
		}
		boolean delete = true;
		if (totalNum > bindCount) {
			if (bindCount > 0) {
				delete = this.deleteGoodsFromBag(cailiaoModel, bindCount, true);
				if (!delete) {
					logger.warn("bag calc with err");
					return false;
				}
			}

			totalNum = totalNum - bindCount;
			if (totalNum > 0) {
				delete = this.deleteGoodsFromBag(cailiaoModel, totalNum, false);
				if (!delete) {
					CharacterGoods characterGoods = CharacterGoods.createCharacterGoods(bindCount, cailiaoModel, 0);
					this.addGoodsToBag(characterGoods);
					logger.warn("bag calc with err");
					return false;
				}
			}
			return true;
		} else {
			delete = this.deleteGoodsFromBag(cailiaoModel, totalNum, true);
			if (!delete) {
				logger.warn("bag calc with err");
				return false;
			}
			return true;
		}
	}

	@Override
	public void updateCharacterGoods(CharacterGoods characterGoods) {
		IContainer container = getGoodsContainerTypeByPosition(characterGoods.getPosition(), characterGoods.getInHorseId());
		if (container != null) {
			container.updateGoods(characterGoods);
		}
		characterGoods.equipmentUpdate();
	}

	/**
	 * 根据物品模型ID查看来能容纳多少个同类物品
	 */
	public int getBagGoodsCountByModelID(int goodModelid) {
		return bagGoodsContainer.getGoodsCountByModelID(goodModelid);
	}

	@Override
	public int getBagGoodsCountByModelID(int goodModelid, boolean bind) {
		return bagGoodsContainer.getGoodsCountByModelID(goodModelid, bind);
	}

	@Override
	public CharacterGoods getGoodsByPositon(short postionid) {
		IContainer postion = getGoodsContainerTypeByPosition(postionid, 0);
		if (postion == null) {
			return null;
		}
		return postion.getGoodsByPostion(postionid);
	}

	@Override
	public Collection<CharacterGoods> getBodyGoodsList() {
		return bodyGoodsContainer.getGoodsList();
	}

	private HorseBodyGoodsContiner getOrCreateHorseBodyGoodsContainer(int horseid) {
		HorseBodyGoodsContiner temphorsemap = horseGoodsMap.get(horseid);
		if (temphorsemap == null) {
			temphorsemap = new HorseBodyGoodsContiner();
			temphorsemap.setOwner(character);
			temphorsemap.setHorseid(horseid);
			horseGoodsMap.put(horseid, temphorsemap);
		}
		return temphorsemap;
	}

	/**
	 * 移动,交换或合并位置 ,不包启添加新的物品,只是各种容器之间物品的相互移动
	 * 
	 */
	public void movePosition(short fromposition, int fromhorseid, GoodsContainerType toPostionConst, short topostion, int tohorseid) {
		// 查询操作需要的基本数据
		IContainer fromPostionConst = getGoodsContainerTypeByPosition(fromposition, fromhorseid);
		CharacterGoods fromGoods = fromPostionConst.getGoodsByPostion(fromposition);
		if (fromGoods == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 40000));
			return;
		}
		if (fromGoods.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 560));
			return;
		}
		IContainer toContainer2 = null;
		switch (toPostionConst) {
		case onBag:
			toContainer2 = bagGoodsContainer;
			break;
		case onBody:
			toContainer2 = bodyGoodsContainer;
			break;
		case onStorage:
			toContainer2 = storageGoodsContainer;
			break;
		case onStall:
			toContainer2 = stallGoodsContainer;
			break;
		case onAcrossBag:
			toContainer2 = acrossGoodsContainer;
			break;
		case onHorse:
			Horse horse = character.getCharacterHorseController().getHorseById(tohorseid);
			if (horse != null) {
				toContainer2 = horse.getGoodsContainer();
			}
			break;
		}
		if (toContainer2 == null) {
			// 找不到目标容器
			return;
		}
		if (fromPostionConst == toContainer2) {
			if (fromPostionConst instanceof AbstractGirdContainer) {
				AbstractGirdContainer fromcontainer = (AbstractGirdContainer) fromPostionConst;
				fromcontainer.movePostion(fromposition, topostion);
			}
		} else {
			fromPostionConst.moveTo(fromposition, toContainer2, topostion);
		}
	}

	@Override
	public void splitGoods(short oldpostion, int splitcount) {
		if (oldpostion >= Position.BagGoodsBeginPostion && oldpostion <= Position.BagGoodsEndPostion) {
			getBagGoodsContiner().splitGoods(oldpostion, splitcount);
		}
		if (oldpostion >= Position.StorageGoodsBeginPostion && oldpostion <= Position.StorageGoodsEndPostion) {
			getStallGoodsContainer().splitGoods(oldpostion, splitcount);
		}
	}

	public CharacterGoodsCoolingTimeManager getCoolingTimeManager() {
		return coolingTimeManager;
	}

	@Override
	public boolean isEnoughGoods(int goodModelId, int num) {
		int hasNum = getBagGoodsCountByModelID(goodModelId);
		if (hasNum >= num)
			return true;
		return false;
	}

	@Override
	public Hero getCharacter() {
		return character;
	}

	@Override
	public IStorage getAcrossGoodsContainer() {
		return acrossGoodsContainer;
	}

}
