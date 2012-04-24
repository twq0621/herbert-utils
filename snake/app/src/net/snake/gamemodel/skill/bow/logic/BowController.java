package net.snake.gamemodel.skill.bow.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.snake.across.util.AcrossUtil;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.CharacterEffectHandler;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.GenerateProbability;
import net.snake.consts.BuffId;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.consts.SkillStatus;
import net.snake.consts.Symbol;
import net.snake.consts.WugongType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.logic.container.IStorage;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.response.hero.AvatarChange60000;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.bow.bean.BowModel;
import net.snake.gamemodel.skill.bow.persistence.BowEntryManager;
import net.snake.gamemodel.skill.bow.persistence.BowModelCacheManager;
import net.snake.gamemodel.skill.bow.response.ArrorTakeOutResult53040;
import net.snake.gamemodel.skill.bow.response.ArrowBagStateResponse53048;
import net.snake.gamemodel.skill.bow.response.ArrowExhaust53042;
import net.snake.gamemodel.skill.bow.response.ArrowPutResult53038;
import net.snake.gamemodel.skill.bow.response.BowUpGradeResult53036;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 弓箭控制器
 * 
 * @author serv_dev
 */
public class BowController extends CharacterController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BowController.class);

	private Bow bow;
	private static int BuffBlessValue = 1;

	public BowController(Hero character) {
		super(character);
	}

	/**
	 * 获取弓箭
	 * 
	 * @return
	 */
	public Bow getBow() {
		return bow;
	}

	/**
	 * 获取阶数
	 * 
	 * @return
	 */
	public int getLevel() {
		if (bow != null) {
			return bow.getModel().getLevel();
		}
		return 0;
	}

	/**
	 * 获取弓箭上己激活的技能 ---goodmodel
	 * 
	 * @return
	 */
	public List<Integer> getSkills() {
		List<Integer> skill = new ArrayList<Integer>();
		if (bow != null && bow.getSkillitems() != null && !bow.getSkillitems().equals("")) {
			String[] split = bow.getSkillitems().split(Symbol.FENHAO);
			for (String string : split) {
				skill.add(Integer.parseInt(string));
			}
		}
		return skill;
	}

	/**
	 * 在未激活技能列表里是否包含
	 * 
	 * @param skillId
	 * @return
	 */
	public boolean isInUnActivate(int skillId) {
		for (Iterator<Integer> iterator = getCanSkills().iterator(); iterator.hasNext();) {
			if (iterator.next() == skillId)
				return true;
		}
		return false;
	}

	/**
	 * 是否已经激活该技能
	 * 
	 * @param skillId
	 * @return
	 */
	public boolean hasActivate(int skillId) {
		for (Iterator<Integer> iterator = getSkills().iterator(); iterator.hasNext();) {
			if (iterator.next() == skillId)
				return true;
		}
		return false;
	}

	/**
	 * 可以激活的技能
	 * 
	 * @return
	 */
	public List<Integer> getCanSkills() {
		List<Integer> skill = new ArrayList<Integer>();
		if (bow != null && bow.getModel().getBindskill() != null && !bow.getModel().getBindskill().equals("")) {
			String[] split = bow.getModel().getBindskill().split(Symbol.FENHAO);
			for (String string : split) {
				skill.add(Integer.parseInt(string));
			}
		}
		return skill;
	}

	/**
	 * 指定的技能是否有箭支
	 * 
	 * @param skillid
	 * @return
	 */
	public boolean hasArrow(int skillid) {
		if (Options.IsCrossServ) {
			return acrossHasArrow(skillid);
		}
		if (getBow().getBag1Model() != null && getBow().getBag1Model().getSkill() == skillid && getBow().getBag1count() > 0) {
			return true;
		}
		if (getBow().getBag2Model() != null && getBow().getBag2Model().getSkill() == skillid && getBow().getBag2count() > 0) {
			return true;
		}
		return false;
	}

	public boolean acrossHasArrow(int skillid) {
		CharacterGoods goods = getSkillArrow(skillid);
		if (goods == null) {
			return false;
		} else {
			return goods.getCount() > 0;
		}
	}

	private CharacterGoods getSkillArrow(int skillid) {
		Collection<CharacterGoods> goodsList = null;
		if (Options.IsCrossServ) {
			IBag bag = character.getCharacterGoodController().getBagGoodsContiner();
			goodsList = bag.getGoodsList();
		} else {
			IStorage bag = character.getCharacterGoodController().getAcrossGoodsContainer();
			goodsList = bag.getGoodsList();
		}
		for (CharacterGoods characterGoods : goodsList) {
			if (characterGoods.getGoodModel().isArrow()) {
				if (characterGoods.getGoodModel().getSkill() == skillid) {
					return characterGoods;
				}
			}
		}
		return null;

	}

	/**
	 * 激活技能
	 * 
	 * @param skillid
	 * @return
	 */
	public void addSkill(int skillid) {
		String value = "";
		if (bow.getSkillitems() != null && !bow.getSkillitems().equals("")) {
			value = bow.getSkillitems() + Symbol.FENHAO + skillid;
		} else {
			value = skillid + "";
		}
		bow.setSkillitems(value);
		BowEntryManager.getInstance().syncUpdateBow(bow);
	}

	/**
	 * 是否最高阶且技能全满
	 * 
	 * @return
	 */
	public boolean isMaxBowAndFullSkill() {
		return getBow().getNextModel() == null && getCanSkills().size() == getSkills().size();
	}

	/**
	 * 是否最高等阶
	 * 
	 * @return
	 */
	public boolean isMaxBow() {
		return getBow() != null && getBow().getNextModel() == null;
	}

	/**
	 * 升阶
	 */
	public void upgrade() {
		AcrossUtil.checkNoAcross();
		if (bow == null) {
			// 原来身上没有箭不能做升阶
			character.sendMsg(new PrompMsg(50050));
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
			return;
		}
		if (bow.getBag1count() > 0 || bow.getBag2count() > 0) {
			// 请先将箭支取下
			character.sendMsg(new PrompMsg(50063));
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
			return;
		}

		final BowModel model = bow.getModel();
		int needCopper = model.getUpconsumeCopper();
		if (character.getCopper() < needCopper) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50051, needCopper + "", character.getCopper() + ""));
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
			return;
		}
		IBag bag = character.getCharacterGoodController().getBagGoodsContiner();
		if (model.getUpconsumeGoodcount() > 0 && model.getUpconsumeGoodid() != 0) {
			int needgood = model.getUpconsumeGoodid();
			int goodcount = bag.getGoodsCountByModelID(needgood);
			if (needgood == GoodItemId.LSJ) {
				// 等价代替物品
				int paritygood = bag.getGoodsCountByModelID(GoodItemId.ACTIVITY_LSJ);
				if (goodcount + paritygood < model.getUpconsumeGoodcount()) {
					Goodmodel goodmodel = GoodmodelManager.getInstance().get(needgood);
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50052, goodmodel.getNameI18n(), model.getUpconsumeGoodcount(), bag.getGoodsCountByModelID(needgood)));
					character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
					return;
				}
			} else {
				if (goodcount < model.getUpconsumeGoodcount()) {
					Goodmodel goodmodel = GoodmodelManager.getInstance().get(needgood);
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50052, goodmodel.getNameI18n(), model.getUpconsumeGoodcount(), bag.getGoodsCountByModelID(needgood)));
					character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
					return;
				}
			}

		}
		Integer level = model.getLevel();
		BowModel nextModel = BowModelCacheManager.getInstance().getBowModelByLevel(level + 1);
		if (nextModel == null) {
			character.sendMsg(new PrompMsg(50053));
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), 0, bow.getNowLuck(), 0));
			return;
		}
		CharacterPropertyManager.changeCopper(character, -needCopper, CopperAction.CONSUME);
		int needgood = model.getUpconsumeGoodid();

		if (needgood == GoodItemId.LSJ) {
			// 等价代替物品
			int paritygood = bag.getGoodsCountByModelID(GoodItemId.ACTIVITY_LSJ);
			// 先扣替代物品 如果现有数量大于需 要数量则只扣替代物品
			if (paritygood >= model.getUpconsumeGoodcount()) {
				bag.deleteGoodsByModel(GoodItemId.ACTIVITY_LSJ, model.getUpconsumeGoodcount());
			} else {
				// 替代物品不够需要补充的数量
				int needgoodcount = model.getUpconsumeGoodcount() - paritygood;
				bag.deleteGoodsByModel(GoodItemId.ACTIVITY_LSJ, paritygood);
				bag.deleteGoodsByModel(model.getUpconsumeGoodid(), needgoodcount);
			}
		} else {
			bag.deleteGoodsByModel(model.getUpconsumeGoodid(), model.getUpconsumeGoodcount());
		}

		Goodmodel goodmodel = GoodmodelManager.getInstance().get(needgood);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50056, needCopper + "", goodmodel.getNameI18n(), model.getUpconsumeGoodcount()));
		Integer faildcount = bow.getFaildcount();
		int luck = GenerateProbability.randomIntValue(1, 10);
		int buffluck = 0;
		if (isBowBless()) {
			buffluck = BuffBlessValue;
		}
		if (faildcount < model.getUpNeedmincount()) {
			bow.setNowLuck(bow.getNowLuck() + luck + buffluck);
			bow.setFaildcount(faildcount + 1);
			BowEntryManager.getInstance().syncUpdateBow(bow);
			if (isBowBless()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50064, luck + "", buffluck, bow.getNowLuck() + ""));
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50054, luck + "", bow.getNowLuck() + ""));
			}
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), luck, bow.getNowLuck(), buffluck));
			return;
		}

		if (faildcount < model.getUpNeedmaxcount() && !GenerateProbability.defaultIsGenerate(model.getUpProbability())) {
			bow.setNowLuck(bow.getNowLuck() + luck + buffluck);
			bow.setFaildcount(faildcount + 1);
			BowEntryManager.getInstance().syncUpdateBow(bow);
			if (isBowBless()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50064, luck + "", buffluck + "", bow.getNowLuck() + ""));
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50054, luck + "", bow.getNowLuck() + ""));
			}
			character.sendMsg(new BowUpGradeResult53036(0, bow.getModel().getId(), luck, bow.getNowLuck(), buffluck));

			return;
		}
		character.getPropertyAdditionController().removeChangeListener(bow);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, bow.getGoodModel(), false));
		// 判断
		// BowModel bowmodel=
		// BowModelCacheManager.getInstance().getBowModelById(bow.getBowmodelid());
		// Integer bowlevel = model.getLevel();
		BowModel bownextModel = BowModelCacheManager.getInstance().getBowModelByLevel(level + 1);
		if (!character.getEffectController().isContains(BuffId.bowskill_retain)) {
			clearSkill();
			bow.setSkillitems("");
		}
		bow.setBag1count(0);
		bow.setBag1type(-1);
		bow.setBag1bind(0);
		bow.setBag2count(0);
		bow.setBag2type(-1);
		bow.setBag2bind(0);
		bow.setBowmodelid(bownextModel.getId());
		bow.setFaildcount(0);

		bow.initGoods();
		bow.setLuck(0);
		bow.setNowLuck(0);
		// bow.setEnable(true);
		BowEntryManager.getInstance().syncUpdateBow(bow);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, bow.getGoodModel(), true));
		character.getPropertyAdditionController().addChangeListener(bow);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50055, bownextModel.getNameI18n()));
		character.sendMsg(new BowUpGradeResult53036(1, getBow().getModel().getId(), 0, bow.getNowLuck(), 0));

	}

	/**
	 * 首次获取弓箭
	 */
	public boolean acquisitionBow() {
		AcrossUtil.checkNoAcross();
		if (bow != null) {
			// character.sendMsg(new TipMsg("您己有弓箭，不可重复获取"));
			return false;
		}
		bow = Bow.newInstance(getCharacter());
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50057, bow.getModel().getNameI18n()));
		character.getPropertyAdditionController().addChangeListener(bow);
		BowEntryManager.getInstance().syncInsertBow(bow);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, bow.getGoodModel(), true));
		return true;
	}

	/**
	 * 添加到箭囊
	 * 
	 * @param goods
	 * @param position
	 *            1，2 箭囊序号
	 */
	public void addToArrow(CharacterGoods goods, int position) {
		if (bow == null) {
			return;
		}
		if (goods == null || goods.getGoodModel() == null) {
			return;
		}
		if (!goods.getGoodModel().isArrow()) {
			// 该物品不是箭支
			character.sendMsg(new PrompMsg(50058));
			character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
					.getBag2count(), bow.getBag2bind().byteValue()));
			return;
		}
		if (!getSkills().contains(goods.getGoodModel().getSkill())) {
			// 很抱歉，该箭支对应的箭术尚未激活，无法放入箭壶
			character.sendMsg(new PrompMsg(50059));
			character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
					.getBag2count(), bow.getBag2bind().byteValue()));
			return;
		}

		if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(goods.getPosition(), goods.getCount())) {

			Integer goodmodelId = goods.getGoodmodelId();
			Integer count = goods.getCount();
			String nameI18n = goods.getGoodModel().getNameI18n();
			Skill skill = SkillManager.getInstance().getSkillById(goods.getGoodModel().getSkill());
			int prob = skill.getTriggerProbability() + goods.getGoodModel().getArrowskillProb();
			if (bow.getBag1Model() != null && bow.getBag1Model().getSkill().intValue() == goods.getGoodModel().getSkill().intValue()) {
				String sourcename1 = getBow().getBag1Model().getNameI18n();
				Integer sourcebag1count = getBow().getBag1count();
				// String sourcename2=getBow().getBag2Model().getNameI18n();
				// Integer sourcebag2count = getBow().getBag2count();
				// 大类一样 替换
				// 类型或绑定类型不同的情况
				removeArrowToBag(1, false);
				bow.setBag1type(goodmodelId);
				bow.setBag1count(count);
				bow.setBag1bind(goods.getBind().intValue());

				character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
						.getBag2count(), bow.getBag2bind().byteValue()));
				// 成功放入$1箭支$2支，与其同类的$3箭支$4支已被替下
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50061, nameI18n, count + "", sourcename1, sourcebag1count + ""));
				BowEntryManager.getInstance().syncUpdateBow(bow);
				checkBuff();
				return;
			}
			if (bow.getBag2Model() != null && bow.getBag2Model().getSkill().intValue() == goods.getGoodModel().getSkill().intValue()) {
				// String sourcename1=getBow().getBag1Model().getNameI18n();
				// Integer sourcebag1count = getBow().getBag1count();
				String sourcename2 = getBow().getBag2Model().getNameI18n();
				Integer sourcebag2count = getBow().getBag2count();
				// 大类一样 替换
				removeArrowToBag(2, false);
				bow.setBag2type(goodmodelId);
				bow.setBag2count(count);
				bow.setBag2bind(goods.getBind().intValue());
				// 成功放入$1箭支$2支，与其同类的$3箭支$4支已被替下
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50061, nameI18n, count + "", sourcename2, sourcebag2count + ""));
				character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
						.getBag2count(), bow.getBag2bind().byteValue()));
				BowEntryManager.getInstance().syncUpdateBow(bow);
				checkBuff();
				return;
			}

			if (position == 1) {
				// 空的情况
				if (bow.getBag1count() == 0 || bow.getBag1type() == -1) {
					bow.setBag1type(goodmodelId);
					bow.setBag1count(count);
					bow.setBag1bind(goods.getBind().intValue());
					character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
							.getBag2count(), bow.getBag2bind().byteValue()));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50060, nameI18n, count + "", nameI18n, prob / 100 + ""));
					// 成功放入XXX箭支XX支，XXX箭术触发几率提升至XX%”
					BowEntryManager.getInstance().syncUpdateBow(bow);
					checkBuff();
					return;
				} else {
					String sourcename1 = getBow().getBag1Model().getNameI18n();
					Integer sourcebag1count = getBow().getBag1count();
					// String sourcename2=getBow().getBag2Model().getNameI18n();
					// Integer sourcebag2count = getBow().getBag2count();
					// 不为空的情况替换
					// 类型或绑定类型不同的情况
					removeArrowToBag(1, false);
					bow.setBag1type(goodmodelId);
					bow.setBag1count(count);
					bow.setBag1bind(goods.getBind().intValue());
					character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
							.getBag2count(), bow.getBag2bind().byteValue()));
					// 成功放入$1箭支$2支，$3箭支$4支已被替下
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50062, nameI18n, count + "", sourcename1, sourcebag1count + ""));
					BowEntryManager.getInstance().syncUpdateBow(bow);
					checkBuff();
					return;
				}
			}
			if (position == 2) {

				// 空的情况
				if (bow.getBag2count() == 0 || bow.getBag2type() == -1) {
					bow.setBag2type(goodmodelId);
					bow.setBag2count(count);
					bow.setBag2bind(goods.getBind().intValue());
					character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
							.getBag2count(), bow.getBag2bind().byteValue()));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50060, nameI18n, count + "", nameI18n, prob / 100 + ""));
					// 成功放入XXX箭支XX支，XXX箭术触发几率提升至XX%”
					BowEntryManager.getInstance().syncUpdateBow(bow);
					checkBuff();
					return;
				} else {
					String sourcename2 = getBow().getBag2Model().getNameI18n();
					Integer sourcebag2count = getBow().getBag2count();
					// 不为空的情况 替换
					removeArrowToBag(2, false);
					bow.setBag2type(goodmodelId);
					bow.setBag2count(count);
					bow.setBag2bind(goods.getBind().intValue());
					character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
							.getBag2count(), bow.getBag2bind().byteValue()));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50062, nameI18n, count + "", sourcename2, sourcebag2count + ""));
					// 成功放入$1箭支$2支，$3箭支$4支已被替下
					BowEntryManager.getInstance().syncUpdateBow(bow);
				}
			}
			character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
					.getBag2count(), bow.getBag2bind().byteValue()));
		} else {
			// 删除物品失败
			character.sendMsg(new ArrowPutResult53038(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
					.getBag2count(), bow.getBag2bind().byteValue()));
		}
		checkBuff();

	}

	public void checkBuff() {
		if (bow.arrowBag1IsEmpty() && bow.arrowBag2IsEmpty() && getSkills().size() > 0) {
			character.getEffectController().addEffect(BuffId.arrow_tip);
		} else {
			character.getEffectController().removeBuffById(BuffId.arrow_tip);
		}
	}

	/**
	 * 将箭囊中的箭支摘下
	 * 
	 * @param position
	 */
	public boolean removeArrowToBag(int position, boolean isPersistence) {
		AcrossUtil.checkNoAcross();
		if (bow == null) {
			return false;
		}
		if (position == 1) {
			if (bow.getBag1type() == -1 || bow.getBag1count() <= 0) {
				return false;
			}
			CharacterGoods goods = CharacterGoods.createCharacterGood(bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue());
			if (character.getCharacterGoodController().addGoodsToBag(goods)) {
				bow.setBag1count(0);
				bow.setBag1type(-1);
				bow.setBag1bind(0);
				if (isPersistence) {
					character.sendMsg(new ArrorTakeOutResult53040(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(),
							bow.getBag2count(), bow.getBag2bind().byteValue()));
					BowEntryManager.getInstance().syncUpdateBow(bow);
				}
				checkBuff();
				return true;
			}
			character.sendMsg(new PrompMsg(50006));
			return false;
		}
		if (position == 2) {
			if (bow.getBag2type() == -1 || bow.getBag2count() <= 0) {
				return false;
			}
			CharacterGoods goods = CharacterGoods.createCharacterGood(bow.getBag2type(), bow.getBag2count(), bow.getBag2bind().byteValue());
			if (character.getCharacterGoodController().addGoodsToBag(goods)) {
				bow.setBag2count(0);
				bow.setBag2type(-1);
				bow.setBag2bind(0);
				if (isPersistence) {
					character.sendMsg(new ArrorTakeOutResult53040(CommonUseNumber.byte1, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(),
							bow.getBag2count(), bow.getBag2bind().byteValue()));
					BowEntryManager.getInstance().syncUpdateBow(bow);
				}
				checkBuff();
				return true;
			}
			character.sendMsg(new PrompMsg(50006));
			character.sendMsg(new ArrorTakeOutResult53040(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
					.getBag2count(), bow.getBag2bind().byteValue()));
			return false;
		}
		character.sendMsg(new ArrorTakeOutResult53040(CommonUseNumber.byte0, bow.getBag1type(), bow.getBag1count(), bow.getBag1bind().byteValue(), bow.getBag2type(), bow
				.getBag2count(), bow.getBag2bind().byteValue()));
		return false;
	}

	/**
	 * 消耗一支箭
	 * 
	 * @param skill
	 */
	public void consumeArrow(int skillid) {
		if (Options.IsCrossServ) {
			acrossConsumeArrow(skillid);
			return;
		}
		if (getBow().getBag1Model() != null && getBow().getBag1Model().getSkill() == skillid) {
			String name = getBow().getBag1Model().getNameI18n();
			if (getBow().getBag1count() > 0) {
				getBow().setBag1count(getBow().getBag1count() - 1);
				if (getBow().getBag1count() <= 0) {
					getBow().setBag1count(0);
					getBow().setBag1type(-1);
					getBow().setBag1bind(0);
					character.sendMsg(new ArrowExhaust53042(name));
				}
				BowEntryManager.getInstance().syncUpdateBow(bow);
			}
		}
		if (getBow().getBag2Model() != null && getBow().getBag2Model().getSkill() == skillid) {
			String name = getBow().getBag2Model().getNameI18n();
			if (getBow().getBag2count() > 0) {
				getBow().setBag2count(getBow().getBag2count() - 1);
				if (getBow().getBag2count() <= 0) {
					getBow().setBag2count(0);
					getBow().setBag2type(-1);
					getBow().setBag2bind(0);
					character.sendMsg(new ArrowExhaust53042(name));
				}
				BowEntryManager.getInstance().syncUpdateBow(bow);
			}
		}
		checkBuff();
		character.sendMsg(new ArrowBagStateResponse53048(bow.getBag1type().intValue(), bow.getBag1count().intValue(), bow.getBag1bind().byteValue(), bow.getBag2type().intValue(),
				bow.getBag2count().intValue(), bow.getBag2bind().byteValue()));
	}

	private void acrossConsumeArrow(int skillid) {
		CharacterGoods skillArrow = getSkillArrow(skillid);
		if (skillArrow != null && character.getCharacterGoodController().deleteGoodsFromBag(skillArrow.getGoodmodelId(), 1)) {
			// 扣除一支箭
		} else {
			logger.error("arr is in bag ,but delete fail");
		}
	}

	public void init() {
		bow = BowEntryManager.getInstance().getBow(character);
		if (bow != null) {
			character.getPropertyAdditionController().addChangeListener(bow);
		}
	}

	private void clearSkill() {
		List<Integer> skills = getSkills();
		for (Integer integer : skills) {
			character.getCharacterSkillManager().delCharacterSkill(integer);
		}
	}

	public void clearBowZhufu() {
		if (bow != null) {
			bow.setLuck(bow.getNowLuck());
			bow.setNowLuck(0);
			BowEntryManager.getInstance().updataBow(bow);
		}
	}

	public boolean isBowBless() {
		return character.getEffectController().isContains(BuffId.bow_upbless);
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

	/**
	 * 使用箭术技能
	 * 
	 * @param fighterVo
	 */
	public void fightArrow(FighterVO fighterVo) {
		// 从弓上取得所有已经学习的技能
		BowController bowController = character.getBowController();
		if (bowController.getBow() == null) {
			return;
		}

		List<Integer> arrowSkillList = bowController.getSkills();
		if (arrowSkillList.isEmpty()) {
			return;
		}

		if (!bowController.getBow().getEnable()) {
			return;
		}

		for (Iterator<Integer> iterator = arrowSkillList.iterator(); iterator.hasNext();) {
			Integer skillId = iterator.next();
			// if (skillId != 57005) {
			// continue;
			// }
			Goodmodel jianzhi_goodmodel = bowController.getBow().getSkillGoodmodelMap(skillId, character);
			CharacterSkill characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skillId);
			if (characterSkill != null) {
				if (GenerateProbability.isGenerate(
						characterSkill.getTriggerProbabilityByTarget(fighterVo.getRecipient()) + (jianzhi_goodmodel == null ? 0 : jianzhi_goodmodel.getArrowskillProb()),
						GenerateProbability.gailv)) {
					if (fighterVo.getRecipient().getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
						SceneMonster sceneMonster = (SceneMonster) fighterVo.getRecipient();
						if (sceneMonster.getMonsterModel().getType() == 9 && sceneMonster.getMonsterModel().getInstanceId() == 4) {
							return;
						}
					}
					tribArrowSkill(fighterVo, characterSkill);
				}
			}
		}
	}

	/**
	 * 触发箭术技能
	 * 
	 * @param fighterVo
	 * @param skillId
	 */
	private void tribArrowSkill(FighterVO fighterVo, CharacterSkill characterSkill) {
		if (characterSkill != null) {

			if (characterSkill.ableUse() != SkillStatus.ok) {
				return;
			}
			Skill skill = characterSkill.getSkill();
			if (skill.getWugongTypeConsts() == WugongType.GONG) {

				CharacterEffectHandler effectHandler = (CharacterEffectHandler) character.getFightController().getEffectHandler();
				switch (skill.getArrowWay()) {
				case face:// 连珠箭
					List<VisibleObject> inAngleTargets = new ArrayList<VisibleObject>();
					effectHandler.findObjectInAngle(character, fighterVo.getRecipient(), skill, inAngleTargets);
					for (Iterator<VisibleObject> iterator = inAngleTargets.iterator(); iterator.hasNext();) {
						VisibleObject visibleObject = iterator.next();
						FighterVO _fighterVO = new FighterVO(character, character, character.getTarget(), characterSkill);
						effectHandler.judgeHurtWay(_fighterVO, visibleObject);
					}
					short[] targetArr = AttackFormula.getStraightLinePoint(character.getX(), character.getY(), fighterVo.getRecipient().getX(), fighterVo.getRecipient().getY(),
							skill.getDistance());
					if (targetArr != null) {
						FightMsgSender.sendSkill10400Msg(character, character.getBowController().getBow().getModel().getEffectId(), skill.getId(), targetArr[0], targetArr[1],
								skill.getAngle(), skill.getArrow_num());
					}
					break;
				case line:// 穿刺箭
					inAngleTargets = new ArrayList<VisibleObject>();
					effectHandler.findObjectInAngle(character, fighterVo.getRecipient(), skill, inAngleTargets);
					for (Iterator<VisibleObject> iterator = inAngleTargets.iterator(); iterator.hasNext();) {
						VisibleObject visibleObject = iterator.next();
						FighterVO _fighterVO = new FighterVO(character, character, character.getTarget(), characterSkill);
						effectHandler.judgeHurtWay(_fighterVO, visibleObject);
					}
					targetArr = AttackFormula.getStraightLinePoint(character.getX(), character.getY(), fighterVo.getRecipient().getX(), fighterVo.getRecipient().getY(),
							skill.getDistance());
					if (targetArr != null) {
						FightMsgSender.sendSkill10402Msg(character, character.getBowController().getBow().getModel().getEffectId(), skill.getId(), targetArr[0], targetArr[1]);
					}
					break;
				case point_one_nojump:
				case point_one:
					inAngleTargets = new ArrayList<VisibleObject>();
					FighterVO _fighterVO = new FighterVO(character, character, character.getTarget(), characterSkill);
					effectHandler.judgeHurtWay(_fighterVO, fighterVo.getRecipient());
					FightMsgSender.sendSkill10404Msg(character, character.getBowController().getBow().getModel().getEffectId(), skill.getId(), fighterVo.getRecipient());
					break;
				case point_mul:
					inAngleTargets = new ArrayList<VisibleObject>();
					effectHandler.findObjectInAngle(character, fighterVo.getRecipient(), skill, inAngleTargets);
					_fighterVO = new FighterVO(character, character, character.getTarget(), characterSkill);
					_fighterVO.setVisibleObjects(inAngleTargets);
					effectHandler.judgeHurtWay(_fighterVO, fighterVo.getRecipient());
					FightMsgSender.sendSkill10404Msg(character, character.getBowController().getBow().getModel().getEffectId(), skill.getId(), fighterVo.getRecipient());
					break;
				default:
					break;
				}
				characterSkill.xiaohaoValue(character);
				character.getFightController().triggerCommonCoolTime(characterSkill);
			}
		}
	}

}
