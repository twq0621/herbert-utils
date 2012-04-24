package net.snake.gamemodel.skill.bean;

import net.snake.ai.fight.response.HiddenWeaponMastryMsg52004;
import net.snake.commons.BeanUtils;
import net.snake.commons.GenerateProbability;
import net.snake.commons.UUIDGenerater;
import net.snake.commons.script.SCharacterHiddenWeapon;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.persistence.CharacterHiddenWeaponManager;
import net.snake.gamemodel.skill.persistence.HiddenWeaponsManager;
import net.snake.gamemodel.skill.response.hiddenweapon.FirstCharacterHiddenWeapons52016;
import net.snake.shell.Options;

/**
 * 角色暗器
 * 
 * @author serv_dev
 * 
 */
public class CharacterHiddenWeapon extends HiddenWeaponDataEntry implements SCharacterHiddenWeapon, PropertyChangeListener {

	private int sendMsgTime = 0;
	private HiddenWeapons hiddenWeapons;

	public CharacterHiddenWeapon(HiddenWeapons hiddenWeapons) {
		this.hiddenWeapons = hiddenWeapons;
	}

	public HiddenWeapons getHiddenWeapons() {
		return hiddenWeapons;
	}

	public void setHiddenWeapons(HiddenWeapons hiddenWeapons) {
		this.hiddenWeapons = hiddenWeapons;
	}

	public CharacterHiddenWeapon(HiddenWeaponDataEntry goodsDataEntry) {
		BeanUtils.copyProperties(goodsDataEntry, this);
		HiddenWeapons hiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(getGrade(), getXiuGrade());
		setHiddenWeapons(hiddenWeapons);
	}

	public String[] getSpcialType() {
		if (hiddenWeapons != null) {
			if (hiddenWeapons.getSpcialType() != null && !"".equals(hiddenWeapons.getSpcialType())) {
				String[] _spType = hiddenWeapons.getSpcialType().split(",");
				return _spType;
			}
		} else {
			return null;
		}

		return null;
	}

	public static CharacterHiddenWeapon create(int grade, int xiuGrade, Hero character) {
		HiddenWeapons hiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(grade, xiuGrade);
		if (hiddenWeapons != null && character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon() == null) {
			CharacterHiddenWeapon characterHiddenWeapon = new CharacterHiddenWeapon(hiddenWeapons);
			characterHiddenWeapon.setGrade(grade);
			characterHiddenWeapon.setXiuGrade(xiuGrade);
			characterHiddenWeapon.setHiddenWeapons(hiddenWeapons);
			characterHiddenWeapon.setId(UUIDGenerater.generate());
			characterHiddenWeapon.setNowMastery(0);
			characterHiddenWeapon.setIsUse(true);
			characterHiddenWeapon.setCharacterId(character.getId());
			characterHiddenWeapon.setLuckValue(0);
			characterHiddenWeapon.setTupoCnt(0);
			characterHiddenWeapon.setFreeCnt(0);
			characterHiddenWeapon.setIsOpenHiddenProps(false);
			CharacterHiddenWeaponManager.getInstance().asynInsertCharacterHiddenWeapon(character, characterHiddenWeapon);
			character.getCharacterHiddenWeaponController().setCharacterHiddenWeapon(characterHiddenWeapon);
//			character.sendMsg(new CharacterHiddenWeaponInitMsg52008(true, characterHiddenWeapon));
			return characterHiddenWeapon;
		} else {
			return null;
		}
	}

	public static CharacterHiddenWeapon create(int hwId, Hero character) {
		HiddenWeapons hiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsById(hwId);
		if (hiddenWeapons != null && character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon() == null) {
			CharacterHiddenWeapon characterHiddenWeapon = new CharacterHiddenWeapon(hiddenWeapons);
			characterHiddenWeapon.setGrade(hiddenWeapons.getGrade());
			characterHiddenWeapon.setXiuGrade(hiddenWeapons.getXiuGrade());
			characterHiddenWeapon.setHiddenWeapons(hiddenWeapons);
			characterHiddenWeapon.setId(UUIDGenerater.generate());
			characterHiddenWeapon.setNowMastery(0);
			characterHiddenWeapon.setIsUse(true);
			characterHiddenWeapon.setLuckValue(0);
			characterHiddenWeapon.setTupoCnt(0);
			characterHiddenWeapon.setCharacterId(character.getId());
			characterHiddenWeapon.setIsOpenHiddenProps(false);
			CharacterHiddenWeaponManager.getInstance().asynInsertCharacterHiddenWeapon(character, characterHiddenWeapon);
			character.getCharacterHiddenWeaponController().setCharacterHiddenWeapon(characterHiddenWeapon);
//			character.sendMsg(new CharacterHiddenWeaponInitMsg52008(true, characterHiddenWeapon));
			character.sendMsg(new FirstCharacterHiddenWeapons52016());
			return characterHiddenWeapon;
		} else {
			return null;
		}
	}

	public boolean triblv(Hero character, VisibleObject vo) {
		if (hiddenWeapons == null)
			return false;
		PropertyEntity lianti = ((CharacterPropertyAdditionControllerImp) character.getPropertyAdditionController()).getLianTi();
		int triblv = hiddenWeapons.getTribRealValue() + lianti.getAqJv();
		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero affector = (Hero) vo;
			int reduce_aqJv = ((CharacterPropertyAdditionControllerImp) affector.getPropertyAdditionController()).getDugujiujian().getReduce_aqJv();
			triblv = (int) (triblv * ((1 - reduce_aqJv / 10000f)));
		}
		return GenerateProbability.defaultIsGenerate(triblv);
	}

	@Override
	public int getAttackpercent() {
		if (hiddenWeapons == null)
			return 0;
		return hiddenWeapons.getHiddenWeaponscol();
	}

	public int getAttackTargets() {
		if (hiddenWeapons == null)
			return 0;
		return hiddenWeapons.getAttackTargets();
	}

	public int getAttackDis() {
		if (hiddenWeapons == null)
			return 0;
		return hiddenWeapons.getAttackDis();
	}

	/**
	 * 加熟练度
	 * 
	 * @param character
	 */
	public void addMastery(Hero character) {
		if (Options.IsCrossServ)
			return;
		if (hiddenWeapons != null) {
			if (getNowMastery() < hiddenWeapons.getMastery() || getXiuGrade() < HiddenWeaponsManager.getInstance().getMaxGrade(hiddenWeapons.getGrade())) {
				addMastery(character, 1);
				CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, this);
			} else {
				if (sendMsgTime % 10 == 0) {
					if (getGrade() != HiddenWeaponsManager.getInstance().getMaxGrade()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 669));
					}
					sendMsgTime = 0;
				}
				sendMsgTime++;
			}
		}
	}

	public void addMastery(Hero character, int addValue) {

		int nowMastery = getNowMastery() + addValue;
		if (nowMastery < hiddenWeapons.getMastery()) {
			setNowMastery(nowMastery);
			character.sendMsg(new HiddenWeaponMastryMsg52004(hiddenWeapons.getId(), nowMastery));
		} else {
			if (getXiuGrade() >= HiddenWeaponsManager.getInstance().getMaxGrade(hiddenWeapons.getGrade())) {
				setNowMastery(hiddenWeapons.getMastery());
				character.sendMsg(new HiddenWeaponMastryMsg52004(hiddenWeapons.getId(), getNowMastery()));
				if (getGrade() != HiddenWeaponsManager.getInstance().getMaxGrade()) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 669));
				}
			} else {
				setXiuGrade(getXiuGrade() + 1);
				setNowMastery(nowMastery - hiddenWeapons.getMastery());
				// 自动升级
				HiddenWeapons hideHiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(getGrade(), getXiuGrade());// 下一个等级
				setHiddenWeapons(hideHiddenWeapons);
				character.getCharacterHiddenWeaponController().sendInitMsg();
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 670));
			}
		}
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.anqi;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity ep = new PropertyEntity();
		ep.addExtraProperty(Property.attack, getHiddenWeapons().getAttackAdd());
		return ep;
	}
}
