package net.snake.gamemodel.onhoor.logic;

import net.snake.ai.formula.DistanceFormula;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.response.goods.GoodsAutoPickUpMsg11166;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;

import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 拾取管理器
 * 
 * @author serv_dev
 * 
 */
public class AutoPickUpController implements Updatable {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AutoPickUpController.class);

	private CharacterOnHoorController characterOnHoorController;
	private Hero character;
	private boolean start = false; // 开关
	private long pickUpBeginTime = 0l;// 自动拾取

	public void destroy() {
		character = null;
		characterOnHoorController = null;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	@Override
	public void update(long now) {

		// TODO 定时地打开这里的开关，做地图扫描物品显示,掉落的物品是一直会刷新的

		if (isStart()) {
			CharacterOnHoorConfig characterOnHoorConfig = characterOnHoorController.getCharacterOnHoorConfig();
			if (characterOnHoorConfig.getAutoPickup()) {// 自动拾取物品处理机制
				autoPickUp();
			}
		}
	}

	public AutoPickUpController(CharacterOnHoorController characterOnHoorController) {
		this.characterOnHoorController = characterOnHoorController;
		character = characterOnHoorController.getCharacter();
	}

	/**
	 * 
	 * 如果是挂机状态下：当人物切换到空闲状态时将调用此方法
	 * 
	 * @return
	 */
	public boolean autoPickUp() {

		if (characterOnHoorController.getAfkAttack() == OnHoorState.pickup && !isStart()) {// 还是有东西可捡
			if (System.currentTimeMillis() - pickUpBeginTime > 3000) {
				characterOnHoorController.setAfkAttack(OnHoorState.attack);// 让挂机正常地打怪练级
				return false;
			}
			return true;// 当客户端发送捡东西的消息后，这个地方的执行会被跳过，执行下面的代码
						// 客户端发送拣去消息后，但是发生服务器与客户端不同步等情况导致不能拾取，在等待5秒后，
		}

		if (character.getObjectState() == VisibleObjectState.Attack) {
			return false;
		}

		SceneDropGood cdg = getEyeLatelyDropGood();
		if (cdg != null) {
			// 如果有东西可捡，这个地方会一直地执行下去

			character.sendMsg(new GoodsAutoPickUpMsg11166(cdg));
			setStart(false);
			pickUpBeginTime = System.currentTimeMillis();
			characterOnHoorController.setAfkAttack(OnHoorState.pickup);
			return true;
		} else {
			setStart(false);
			characterOnHoorController.setAfkAttack(OnHoorState.attack);
			return false;
		}
	}

	/**
	 * 视野内获取距离玩家最近的可以拾取的包裹
	 * 
	 * @param character
	 * @return
	 */
	private SceneDropGood getEyeLatelyDropGood() {
		Collection<SceneDropGood> collection = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_DropedGood);
		int juli = 1000000;
		SceneDropGood goods = null;
		for (SceneDropGood sdg : collection) {
			if (!isAutoPickUp(character, sdg)) {
				continue;
			}
			int temp = DistanceFormula.getDistanceRound(character.getX(), character.getY(), sdg.getX(), sdg.getY());
			CharacterOnHoorConfig characterOnHoorConfig = characterOnHoorController.getCharacterOnHoorConfig();
			if (temp > characterOnHoorConfig.getAttackScope()) {
				logger.debug("此道具超过了挂机的攻击范围，不可拾取");
				continue;
			}

			short[] _ppoint = character.findWay(sdg.getX(), sdg.getY());
			if (_ppoint != null && _ppoint.length > characterOnHoorConfig.getAttackScope() * 2) {
				logger.debug("至道具的路径长度过长，不可拾取");
				continue;
			}

			if (temp >= juli) {
				logger.debug("至道具的距离超过1000000，不可拾取");
				continue;
			}
			if (!sdg.isPickUp(character)) {
				continue;
			}
			if (!sdg.isMoney()) {
				// 检查包裹是否有空余的位置
				if (character.getCharacterGoodController().getBagGoodsContiner().findIdleGirdCount() == 0) {
					continue;
				}
			}
			juli = temp;
			goods = sdg;
		}

		return goods;
	}

	/**
	 * 设置过滤拾取条件
	 * 
	 * @param picker
	 * @param sceneDropGood
	 * @return
	 */
	private boolean isAutoPickUp(Hero picker, SceneDropGood sceneDropGood) {
		if (!sceneDropGood.isPickUp(picker)) {
			return false;
		} else {
			return verifyPickUpGood(sceneDropGood);
		}
	}

	public boolean verifyPickUpGood(SceneDropGood sceneDropGood) {

		CharacterOnHoorConfig characterOnHoorConfig = characterOnHoorController.getCharacterOnHoorConfig();
		CharacterGoods characterGoods = sceneDropGood.getCg();
		Goodmodel goodmodel = characterGoods.getGoodModel();
		if (goodmodel != null) {
			if (characterOnHoorConfig.getAutoPickupEquip() && goodmodel.isEquipment()) {// 装备的判定

				int pinzhi = characterGoods.getPingzhiColor();
				if (pinzhi < characterOnHoorConfig.getQualityEquip()) {// 品阶不通过
					return false;
				}

				if (goodmodel.getLimitGrade() < characterOnHoorConfig.getGradeEquip()) {// 品级不通过
					return false;
				}

				int confLimitPosinger = characterOnHoorConfig.getLimitPopsinger().intValue();
				if (goodmodel.getPopsinger() > 0 && confLimitPosinger > 0) {// 门派不通过
					if (goodmodel.getPopsinger() != confLimitPosinger) {
						return false;
					}
				}

				return true;
			}

			if (characterOnHoorConfig.getIsTaskGoods()) {
				if (goodmodel.getKind() == 13) {// 任务物品
					return true;
				}
			}

			if (characterOnHoorConfig.getIsYaopin()) {
				if (goodmodel.getKind() == 3) {// 药品
					return true;
				}
			}

			if (characterOnHoorConfig.getIsCailiao()) {
				if (goodmodel.getKind() == 9) {// 材料
					return true;
				}
			}

			return characterOnHoorConfig.getOtherGoods();
		}

		if (characterOnHoorConfig.getIsMoney()) {
			if (sceneDropGood.isMoney())
				return true;
		}

		return false;
	}

}
