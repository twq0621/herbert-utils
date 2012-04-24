package net.snake.gamemodel.onhoor.logic;

import java.util.List;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.action.UseGoodAction;
import net.snake.gamemodel.goods.logic.action.UserGoodActionManager;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.SkillId;

/**
 * 挂机状态与非挂机状态下自动回血
 * 
 * @author serv_dev
 * 
 */
public class AutoRevertController implements Updatable {

	private CharacterOnHoorController characterOnHoorController;
	private Hero character;
	private boolean start = false;
	SafeTimer revertHpTimer = new SafeTimer(1000);
	SafeTimer revertMpTimer = new SafeTimer(1000);
	SafeTimer revertSpTimer = new SafeTimer(1000);

	public void destroy() {
		characterOnHoorController = null;
		character = null;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public AutoRevertController(CharacterOnHoorController characterOnHoorController) {
		this.characterOnHoorController = characterOnHoorController;
		character = characterOnHoorController.getCharacter();
		setStart(true);
		character.getUpdateObjManager().addFrameUpdateObject(this);
	}

	@Override
	public void update(long now) {
		if (character.getObjectState() == VisibleObjectState.Shock || character.getObjectState() == VisibleObjectState.Die) {
			return;
		}

		if (isStart() && characterOnHoorController!=null && characterOnHoorController.isAutoOnHoor()) {
			CharacterOnHoorConfig characterOnHoorConfig = characterOnHoorController.getCharacterOnHoorConfig();
			aotoRevert(characterOnHoorConfig, now);
		}
	}

	private void aotoRevert(CharacterOnHoorConfig characterOnHoorConfig, long now) {
		int nowHp = character.getNowHp();
		if (nowHp <= 0 || character.getObjectState() == VisibleObjectState.Die) {
			setStart(false);
			return;
		}

		if (characterOnHoorConfig.getAutoRevertHp() && characterOnHoorConfig.getRevertHp() > 0) {
			int maxHp = character.getPropertyAdditionController().getExtraMaxHp();
			if (nowHp < maxHp) {
				if (revertHpTimer.isIntervalOK(now) && (nowHp < maxHp * characterOnHoorConfig.getRevertHp() * .01)) {
					_hpUse(characterOnHoorConfig.getRevertHpMethod() == 1 ? true : false);
				}
			}
		}

		if (characterOnHoorConfig.getAutoRevertMp() && characterOnHoorConfig.getRevertMp() > 0) {
			if (!character.getEffectController().isMpOver()) {// 中了化功大法
				int nowMp = character.getNowMp();
				int maxMp = character.getPropertyAdditionController().getExtraMaxMp();
				if (nowMp < maxMp) {
					if (revertMpTimer.isIntervalOK(now) && (nowMp < maxMp * characterOnHoorConfig.getRevertMp() * .01)) {
						_mpUse(characterOnHoorConfig.getRevertMpMethod() == 1 ? true : false);
					}
				}
			}
		}
		if (characterOnHoorConfig.getAutoHpBuff() && !character.getEffectController().isContains(SkillId.AUTO_HP_SKILL_BUFF_ID)) {
			List<Goodmodel> goodModelList = GoodmodelManager.getInstance().getHpDrugBuffList();
			int size = goodModelList.size();
			for (int i = 0; i < size; i++) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(goodmodel.getId());
				if (uga != null && cg != null) {
					boolean hasUsedHp = uga.useGoodDoSomething(character, goodmodel.getId(), cg.getPosition(), character.getSceneRef().getUseItemListeners());
					if (hasUsedHp) {
						break;
					}
				}
			}
		}
		if (characterOnHoorConfig.getAutoHpBuff() && !character.getEffectController().isContains(SkillId.AUTO_MP_SKILL_BUFF_ID)) {
			List<Goodmodel> goodModelList = GoodmodelManager.getInstance().getMpDrugBuffList();
			int size = goodModelList.size();
			for (int i = 0; i < size; i++) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(goodmodel.getId());
				if (uga != null && cg != null) {
					boolean hasUsedHp = uga.useGoodDoSomething(character, goodmodel.getId(), cg.getPosition(), character.getSceneRef().getUseItemListeners());
					if (hasUsedHp) {
						break;
					}
				}
			}
		}
		// TODO 自动增加sp的先去掉
		// if (characterOnHoorConfig.getAutoRevertSp() && characterOnHoorConfig.getRevertSp() > 0) {
		// if (!character.getEffectController().isSpOver()) {//中了化功大法
		// int nowSp = character.getNowSp();
		// int maxSp = character.getPropertyAdditionController().getExtraMaxSp();
		//
		// if (nowSp < maxSp) {
		// if (revertSpTimer.isIntervalOK(now) && (nowSp < maxSp * characterOnHoorConfig.getRevertSp() * .01)) {
		// _spUse(characterOnHoorConfig.getRevertSpMethod() == 1 ? true : false);
		// }
		// }
		// }
		// }
	}

	private boolean _hpUse(boolean isLittleToGreat) {
		List<Goodmodel> goodModelList = GoodmodelManager.getInstance().getHpDrugList();
		int size = goodModelList.size();
		boolean hasUsedHp = false;
		if (isLittleToGreat) {
			for (int i = size - 1; i >= 0; i--) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				if (uga != null) {
					hasUsedHp = uga.useGoodDoSomething(character, goodmodel.getId(), 0, character.getSceneRef().getUseItemListeners());
					if (hasUsedHp)
						break;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				if (uga != null) {
					hasUsedHp = uga.useGoodDoSomething(character, goodmodel.getId(), 0, character.getSceneRef().getUseItemListeners());
					if (hasUsedHp)
						break;
				}
			}
		}

		return hasUsedHp;
	}

	private boolean _mpUse(boolean isLittleToGreat) {
		List<Goodmodel> goodModelList = GoodmodelManager.getInstance().getMpDrugList();
		int size = goodModelList.size();
		boolean hasUsedMp = false;

		if (isLittleToGreat) {
			for (int i = size - 1; i >= 0; i--) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				if (uga != null) {
					hasUsedMp = uga.useGoodDoSomething(character, goodmodel.getId(), 0, character.getSceneRef().getUseItemListeners());
					if (hasUsedMp)
						break;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				Goodmodel goodmodel = goodModelList.get(i);
				UseGoodAction uga = UserGoodActionManager.getInstance().getUserGoodAction(goodmodel.getId());
				if (uga != null) {
					hasUsedMp = uga.useGoodDoSomething(character, goodmodel.getId(), 0, character.getSceneRef().getUseItemListeners());
					if (hasUsedMp)
						break;
				}
			}
		}

		return hasUsedMp;
	}

}
