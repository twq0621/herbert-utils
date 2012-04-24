package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.GenerateProbability;
import net.snake.consts.CopperAction;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.CharacterHorseDataProvider;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.pet.response.HorseStrengthenResponse60022;
import net.snake.netio.message.RequestMsg;

/**
 * 灵宠品质强化
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60021)
public class StrengthenProProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 灵宠ID(int)，请求类型(1展示,2收起,3放生)（byte）

		int id = request.getInt();// 获得要展示的马的ID
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(id);
		CharacterHorse characterHorse = horse.getCharacterHorse();
		HorseModel horseModel = horse.getHorseModel();
		if (characterHorse.getPin() > 4) {
			return;
		}
		if (horseModel.getJinjie() == 0) {
			return;
		}
		if (character.getZhenqi() < horseModel.getJinjieZhenqi()) {
			// 真气不足
			character.sendMsg(new CombineFailMsg50150(3));
			return;
		}
		if (character.getCopper() < horseModel.getJinjieCopper()) {
			// 铜币不足
			character.sendMsg(new CombineFailMsg50150(14));
			return;
		}
		final CharacterGoodController characterGoodController = character.getCharacterGoodController();
		final int hasNum = characterGoodController.getBagGoodsCountByModelID(horseModel.getJinjieGoodsId());
		if (horseModel.getJinjieGoodsCount() > hasNum) {// 检查强化道具是否充足
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60053));
			return;
		}

		// 消耗真气以及物品
		CharacterPropertyManager.changeCopper(character, -horseModel.getJinjieCopper(), CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(character, -horseModel.getJinjieZhenqi());
		if (!characterGoodController.deleteCailiao(horseModel.getJinjieGoodsId(), horseModel.getJinjieGoodsCount())) {
			return;
		}
		int probability = 0;
		if (characterHorse.getJinjieCount() >= horseModel.getJinjieMinCount()) {
			probability = horseModel.getJinjieSuccessProbability();
		}
		if (characterHorse.getJinjieCount() >= horseModel.getJinjieMaxCount()) {
			probability = GenerateProbability.gailv;
		}
		// probability = probability + xinyunNum * GameConstant.XingYunJinglv;
		// int probability = horseModel.getJinjieSuccessProbability();
		characterHorse.setJinjieCount(characterHorse.getJinjieCount() + 1);
		if (probability > GenerateProbability.gailv) {
			probability = GenerateProbability.gailv;
		}
		// probability = GenerateProbability.gailv;
		if (!GenerateProbability.defaultIsGenerate(probability)) {// 强化失败
			character.sendMsg(new HorseStrengthenResponse60022());
			return;
		}
		characterHorse.setJinjieCount(0);
		characterHorse.setHorseModelId(horseModel.getJinjie());
		characterHorse.setPin(characterHorse.getPin() + 1);
		HorseModel newhm = HorseModelDataProvider.getInstance().getHorseModelByID(horseModel.getJinjie());
		if (newhm == null) {
			character.sendMsg(new HorseStrengthenResponse60022());
			return;
		}
		if (characterHorse.getName().equals(horse.getHorseModel().getName())) {
			characterHorse.setName(newhm.getName());
		}
		horse.setHorseModel(newhm);
		CharacterHorse addHorse = new CharacterHorse();
		int attack = (int) (newhm.getAddOwnerAttack() * characterHorse.getGrade()) + 1;
		addHorse.setAttack(attack - characterHorse.getExtraAttack());
		characterHorse.setExtraAttack(attack);
		int crt = (int) (newhm.getAddOwnerCrt() * characterHorse.getGrade()) + 1;
		addHorse.setCrt(crt - characterHorse.getExtraCrt());
		characterHorse.setExtraCrt(crt);
		int defence = (int) (newhm.getAddOwnerDefence() * characterHorse.getGrade()) + 1;
		addHorse.setDefence(defence - characterHorse.getExtraDefence());
		characterHorse.setExtraDefence(defence);
		int dodge = (int) (newhm.getAddOwnerDodge() * characterHorse.getGrade()) + 1;
		addHorse.setDodge(dodge - characterHorse.getExtraDodge());
		characterHorse.setExtraDodge(dodge);
		int hit = (int) (newhm.getAddOwnerHit() * characterHorse.getGrade()) + 1;
		addHorse.setHit(hit - characterHorse.getExtraHit());
		characterHorse.setExtraHit(hit);
		int hp = (int) (newhm.getAddOwnerHp() * characterHorse.getGrade()) + 1;
		addHorse.setHp(hp - characterHorse.getExtraHp());
		characterHorse.setExtraHp(hp);
		int mp = (int) (newhm.getAddOwnerMp() * characterHorse.getGrade()) + 1;
		addHorse.setMp(mp - characterHorse.getExtraMp());
		characterHorse.setExtraMp(mp);

		CharacterHorseDataProvider.getInstance().asynchronousUpdateCharacterHorse(characterHorse, character);

		character.sendMsg(new HorseStrengthenResponse60022(character, horse, addHorse));
		if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
			character.getPropertyAdditionController().addChangeListener(horse);
			horse.addEquipPropertis();// 装备属性重检查一下,可能有的装备以前不能穿,现在能穿了
		}
	}

}
