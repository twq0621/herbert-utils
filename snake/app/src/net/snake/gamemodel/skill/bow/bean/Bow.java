package net.snake.gamemodel.skill.bow.bean;

import java.util.Collection;

import net.snake.consts.CommonUseNumber;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.logic.container.IStorage;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bow.persistence.BowModelCacheManager;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
public class Bow extends BowDataEntry implements PropertyChangeListener {
	private CharacterGoods goods;
	private int nowLuck = 0;

	public static Bow newInstance(Hero character) {
		Bow bow = new Bow();
		bow.setBag1count(0);
		bow.setBag1type(-1);
		bow.setBag2count(0);
		bow.setBag2type(-1);
		bow.setBowmodelid(1);
		bow.setCharacterid(character.getId());
		bow.setFaildcount(0);
		bow.setSkillitems("");
		bow.setBag1bind(0);
		bow.setBag2bind(0);
		bow.setEnable(true);
		bow.setNowLuck(0);
		bow.setLuck(0);
		bow.initGoods();
		return bow;
	}

	public CharacterGoods getGoods() {
		return goods;
	}

	public void initGoods() {
		goods = CharacterGoods.createCharacterGood(getGoodModelId(), 1, CommonUseNumber.byte1);
		goods.setPosition((short) getGoodModel().getPosition());
	}

	public BowModel getModel() {
		return BowModelCacheManager.getInstance().getBowModelById(getBowmodelid());
	}

	public BowModel getNextModel() {
		return BowModelCacheManager.getInstance().getBowModelByLevel(getModel().getLevel() + 1);
	}

	public int getGoodModelId() {
		return BowModelCacheManager.getInstance().getBowModelById(getBowmodelid()).getGoodmodelId();
	}

	public Goodmodel getGoodModel() {
		return GoodmodelManager.getInstance().get(getGoodModelId());
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.bow;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		BowModel model = BowModelCacheManager.getInstance().getBowModelById(getBowmodelid());
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.attack, model.getAddattack());
		pe.addExtraProperty(Property.defence, model.getAdddefence());
		pe.addExtraProperty(Property.crt, model.getAddcrt());
		pe.addExtraProperty(Property.dodge, model.getAdddodge());
		pe.addExtraProperty(Property.maxHp, model.getAddmaxhp());
		pe.addExtraProperty(Property.maxMp, model.getAddmaxmp());
		pe.addExtraProperty(Property.maxSp, model.getAddmaxsp());
		pe.addExtraProperty(Property.attackspeed, model.getAddattackspeed());
		pe.addExtraProperty(Property.movespeed, model.getAddmovespeed());

		pe.addExtraProperty(Property.GJL, model.getRatioAddattack());
		pe.addExtraProperty(Property.HSFY, model.getRatioIgnoredefence());
		return pe;
	}

	public Goodmodel getBag1Model() {
		return GoodmodelManager.getInstance().get(getBag1type());
	}

	public Goodmodel getBag2Model() {
		return GoodmodelManager.getInstance().get(getBag2type());
	}

	public boolean arrowBag1IsEmpty() {
		if (getBag1type().intValue() == -1 || getBag1count().intValue() == 0) {
			return true;
		}
		return false;
	}

	public boolean arrowBag2IsEmpty() {
		if (getBag2type().intValue() == -1 || getBag2count().intValue() == 0) {
			return true;
		}
		return false;
	}

	public Goodmodel getSkillGoodmodelMap(int skillid, Hero attakcer) {
		if (Options.IsCrossServ) {
			return getSkillGoodmodel(skillid, attakcer);
		}
		if (getBag1Model() != null && getBag1Model().getSkill() == skillid) {
			return getBag1Model();
		}
		if (getBag2Model() != null && getBag2Model().getSkill() == skillid) {
			return getBag2Model();
		}
		return null;
	}

	private Goodmodel getSkillGoodmodel(int skillid, Hero c) {
		CharacterGoods skillArrow = Options.IsCrossServ ? getSkillArrow(skillid, c) : acrossGetSkillArrow(skillid, c);
		if (skillArrow != null) {
			return skillArrow.getGoodModel();
		}
		return null;
	}

	private CharacterGoods acrossGetSkillArrow(int skillid, Hero c) {
		IStorage bag = c.getCharacterGoodController().getAcrossGoodsContainer();
		Collection<CharacterGoods> goodsList = bag.getGoodsList();
		for (CharacterGoods characterGoods : goodsList) {
			if (characterGoods.getGoodModel().isArrow()) {
				if (characterGoods.getGoodModel().getSkill() == skillid) {
					return characterGoods;
				}
			}
		}
		return null;
	}

	private CharacterGoods getSkillArrow(int skillid, Hero c) {
		IBag bag = c.getCharacterGoodController().getBagGoodsContiner();
		Collection<CharacterGoods> goodsList = bag.getGoodsList();
		for (CharacterGoods characterGoods : goodsList) {
			if (characterGoods.getGoodModel().isArrow()) {
				if (characterGoods.getGoodModel().getSkill() == skillid) {
					return characterGoods;
				}
			}
		}
		return null;
	}

	public int getNowLuck() {
		return nowLuck;
	}

	public void setNowLuck(int nowLuck) {
		this.nowLuck = nowLuck;
	}

}
