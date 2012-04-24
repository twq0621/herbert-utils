package net.snake.ai.formula;

import net.snake.consts.Property;
import net.snake.gamemodel.hero.bean.Enmity;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;

public class HorseFormula {

	/**
	 * ( 怪物经验 * （1+ (同地图内的队伍人数 – 1) * 5%）) * 玩家杀怪血量百分比 * 坐骑等级衰减系数 * （坐骑多倍经验系数 + 连斩BUFF系数）* 25% 经验获得等级差取绝对值 等级差衰减系数 | 怪物等级 - 人物等级 |>10 0.01 5<| 怪物等级 - 人物等级 |<=10 0.8 | 怪物等级 - 人物等级
	 * |<=5 1
	 * 
	 * 
	 * 当前骑乘的坐骑获得经验 = ( 怪物经验 * （1+ (同地图内的队伍人数 – 1) * 5%）) * 玩家杀怪血量百分比 * 坐骑等级衰减系数 * （坐骑多倍经验系数 + 连斩BUFF系数）* 60% zhengyuyang
	 * 
	 * 
	 * 2010.12.1 zhengyuyang 当前骑乘的坐骑获得经验 = ( 怪物经验 * （1+ (同地图内的队伍人数 – 1) * 5%）) * 玩家杀怪血量百分比 * 坐骑等级衰减系数 * （坐骑多倍经验系数 + 连斩BUFF系数） 经验获得等级差取绝对值 等级差衰减系数 | 怪物等级 - 坐骑等级 |>30 0.01 15<| 怪物等级
	 * - 坐骑等级 |<=30 0.8 | 怪物等级 - 坐骑等级 |<=15 1 现在是等于人物经验，之前是60%，惩罚等级区间有调整
	 * 
	 * 
	 * @return
	 */

	public static int getExp(Hero character, SceneMonster monster, Horse horse) {
		MonsterModel monsterModel = monster.getMonsterModel();
		int cg = horse.getCharacterHorse().getGrade();
		int mg = monsterModel.getGrade();
		int monserExp = monsterModel.getExper();

		int _grade = Math.abs(mg - cg);
		float gradeE = 1f; // 等级衰减系数
		if (_grade > 30) {
			gradeE = 0.01f;
		} else if (_grade <= 15) {
			gradeE = 1f;
		} else {
			gradeE = 0.8f;
		}

		int doubExp = 1;// 多倍经验系数
		if (character.getCharacterOnHoorController().isDoubleZuoqiExp()) {
			doubExp++;
		}

		int teamNum = 1;
		if (character.getMyTeamManager().isTeam()) {
			teamNum = character.getMyTeamManager().getMyTeam().getTeamPopulationInScene(monster.getSceneRef());
		}

		// 杀怪血量百分比
		Enmity enmity = monster.getEnmityManager().getEnmityOfVo(character);
		if (enmity == null) {
			return 0;
		}

		float enmityValue = enmity.getHurt();
		float percentMonsterHp = enmityValue / monster.getPropertyAdditionController().getExtraMaxHp();
		double returnExp = monserExp * (1 + (teamNum - 1) * .05) * percentMonsterHp * gradeE * (doubExp + (character.getBanghuiJiacheng() / 10000f));
		returnExp = returnExp * character.getAntiAddictedSystemPlusScale();
		return Math.round((float) returnExp);
	}

	public static int getHorsePrice(CharacterHorse characterHorse, int skillCount, int shenjiCount) {
		// 灵宠身价计算：20×品阶^2+技能个数×60+神技个数×100+等级×3
		// 身价 = 悟性+攻击潜力+防御潜力+轻身潜力+健体潜力 + 技能个数*50---原公式
		// int horsePrice = characterHorse.getAttack() +
		// characterHorse.getDefence() + characterHorse.getHit() +
		// characterHorse.getCrt() + characterHorse.getDodge() + skillcount *
		// 50;
		if (shenjiCount < 0) {
			shenjiCount = 0;
		}
		int horsePrice = characterHorse.getPin() * 20 * characterHorse.getPin() * 20 + skillCount * 60 + shenjiCount * 100 + characterHorse.getGrade() * 3;
		return horsePrice;
	}

	public static void horseUpgratePro(CharacterHorse characterHorse) {
		characterHorse.setAttack(characterHorse.getAttack() + getHorseProVal(Property.attack, characterHorse));
		characterHorse.setDefence(characterHorse.getDefence() + getHorseProVal(Property.defence, characterHorse));
		characterHorse.setCrt(characterHorse.getCrt() + getHorseProVal(Property.crt, characterHorse));
		characterHorse.setDodge(characterHorse.getDodge() + getHorseProVal(Property.dodge, characterHorse));
		characterHorse.setHit(characterHorse.getHit() + getHorseProVal(Property.hit, characterHorse));
		characterHorse.setHp(characterHorse.getHp() + getHorseProVal(Property.maxHp, characterHorse));
		characterHorse.setMp(characterHorse.getMp() + getHorseProVal(Property.maxMp, characterHorse));
		HorseModel newhm = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
		if (newhm.getBaseId() != characterHorse.getHorseModelId()) {
			int attack = (int) (newhm.getAddOwnerAttack() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraAttack(attack);
			int defence = (int) (newhm.getAddOwnerDefence() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraDefence(defence);
			int crt = (int) (newhm.getAddOwnerCrt() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraCrt(crt);
			int dodge = (int) (newhm.getAddOwnerDodge() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraDodge(dodge);
			int hit = (int) (newhm.getAddOwnerHit() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraHit(hit);
			int hp = (int) (newhm.getAddOwnerHp() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraHp(hp);
			int mp = (int) (newhm.getAddOwnerMp() * characterHorse.getGrade()) + 1;
			characterHorse.setExtraMp(mp);
		}
	}

	/**
	 * 全部使用int去余取整格式 宠物品阶系数 宠物攻击增量公式：宠物品阶系数*宠物攻击潜质系数*{5*（等级-1）+5｝ 宠物防御增量公式：宠物品阶系数*宠物防御潜质系数*{2*（等级-1）+5｝ 宠物HP增量公式：宠物品阶系数*宠物HP潜质系数*{30*（等级-1）+35｝
	 * 宠物MP增量公式：宠物品阶系数*宠物MP潜质系数*{10*（等级-1）+30｝ 宠物命中增量公式：宠物品阶系数*宠物命中潜质系数*{1.5*（等级-1）+5｝ 宠物闪避增量公式：宠物品阶系数*宠物闪避潜质系数*{0.75*（等级-1）+5｝ 宠物暴击增量公式：宠物品阶系数*宠物暴击潜质系数*{0.4*（等级-1）+5｝
	 * 
	 * 
	 * @param pro
	 * @param characterHorse
	 * @return
	 */
	public static int getHorseProVal(Property pro, CharacterHorse characterHorse) {
		int pin = characterHorse.getPin();
		int grade = characterHorse.getGrade();
		HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
		float qzxs = getQianzhiXishu(pro, horseModel);
		int val = (int) (getPinjieXishu(pin) * qzxs * (getProxishu(pro) * (grade - 1) + getProchlia(pro)));
		return val;
	}

	/**
	 * 一品：0.06 二品：0.08 三品：0.10 四品：0.12 五品：0.15
	 * 
	 * @param pin
	 * @return
	 */
	private static float getPinjieXishu(int pin) {
		float xishu = 0.06f;
		// switch (pin) {
		// case 1:
		// xishu = 0.06f;
		// break;
		// case 2:
		// xishu = 0.08f;
		// break;
		// case 3:
		// xishu = 0.1f;
		// break;
		// case 4:
		// xishu = 0.12f;
		// break;
		// case 5:
		// xishu = 0.15f;
		// break;
		// }
		return xishu;
	}

	private static float getQianzhiXishu(Property pro, HorseModel horseModel) {
		float xishu = 0f;
		switch (pro) {
		case attack:
			xishu = horseModel.getAttackQzxs();
			break;
		case defence:
			xishu = horseModel.getDefenceQzxs();
			break;
		case maxHp:
			xishu = horseModel.getHpQzxs();
			break;
		case maxMp:
			xishu = horseModel.getMpQzxs();
			break;
		case hit:
			xishu = horseModel.getHitQzxs();
			break;
		case dodge:
			xishu = horseModel.getDodgeQzxs();
		case crt:
			xishu = horseModel.getCrtQzxs();
			break;
		}
		return xishu;
	}

	private static float getProxishu(Property pro) {
		float xishu = 0f;
		switch (pro) {
		case attack:
			xishu = 5f;
			break;
		case defence:
			xishu = 2f;
			break;
		case maxHp:
			xishu = 30f;
			break;
		case maxMp:
			xishu = 10f;
			break;
		case hit:
			xishu = 1.5f;
			break;
		case dodge:
			xishu = 0.75f;
		case crt:
			xishu = 0.4f;
			break;
		}
		return xishu;
	}

	private static int getProchlia(Property pro) {
		int chlia = 0;
		switch (pro) {
		case attack:
			chlia = 5;
			break;
		case defence:
			chlia = 5;
			break;
		case maxHp:
			chlia = 35;
			break;
		case maxMp:
			chlia = 30;
			break;
		case hit:
			chlia = 5;
			break;
		case dodge:
			chlia = 5;
		case crt:
			chlia = 5;
			break;
		}
		return chlia;
	}
}
