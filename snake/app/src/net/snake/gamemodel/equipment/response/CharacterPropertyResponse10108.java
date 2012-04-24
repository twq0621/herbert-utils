package net.snake.gamemodel.equipment.response;

import java.io.IOException;

import net.snake.commons.Language;
import net.snake.commons.StringUtil;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.netio.ServerResponse;

/**
 * 等级(int),生命值(当前(int)/上限(int))、内力值(当前(int)/上限(int))、怒气值(当前(int)/上限(int))、 精力值(当前(int)/上限(int))、体力(int)、强壮(int)、内功(int)、灵敏(int)、定力(int)、潜力(int)、
 * 命中（int）,闪避(int),会心(int),外功攻击(int)、内功攻击(int)、外功防御(int)、内功防御(int)
 * 
 * 
 * 元宝(int),饺子(int),铜(int),等级(short),生命值(当前(int)/上限(int))、内力值(当前(int)/上限(int))、 怒气值(当前(int)/上限(int))、 经验值(当前(int)/上限(int))、潜力(int)、命中（int）,闪避(int),会心(int),攻击(
 * int)防御(int),积分(int)、//发送帮会id (int 没加入帮会 发送-1) TODO 少了攻击速度跟移动速度
 * 
 * @author serv_dev
 * 
 */
public class CharacterPropertyResponse10108 extends ServerResponse {

	private static final int MSGCODE = 10108;

	public CharacterPropertyResponse10108(Hero character) {
		try {
			setMsgCode(MSGCODE);
			writeInt(character.getCopper());
			/* 铜 * */
			writeInt(character.getAccount().getYuanbao());
			/* 金--》 元宝 * */
			writeInt(character.getJiaozi());
			/* 银 --》交子 * */
			writeShort(character.getGrade());
			/* 等级 * */
			writeInt(character.getNowHp());
			/* 生命值(当前) * */
			writeInt(character.getPropertyAdditionController().getExtraMaxHp());
			/* 生命值(上限) * */
			writeInt(character.getNowMp());
			/* 内力值(当前) * */
			writeInt(character.getPropertyAdditionController().getExtraMaxMp());
			/* 内力值(上限) * */
			writeInt(character.getNowSp());
			/* 怒气值(当前) * */
			writeInt(character.getPropertyAdditionController().getExtraMaxSp());
			/* 怒气值(上限) * */
			writeDouble(character.getNowExperience());
			/* 经验值(当前) * */
			writeDouble(character.getNextExperience());
			/* 经验值(上限) * */
			writeInt(character.getZhenqi());
			writeInt(character.getPotential());
			/* 潜力 * */
			writeInt(character.getPropertyAdditionController().getExtraHit());
			/* 命中 * */
			
			writeInt(character.getPropertyAdditionController().getExtraDodge());
			/* 闪避 * */
			writeInt(character.getPropertyAdditionController().getExtraCrt());
			/* 会心 * */
			writeInt(character.getPropertyAdditionController().getExtraAttack());
			/* 攻击 * */
			writeInt(character.getPropertyAdditionController().getExtraDefend());
			/* 防御 * */
			writeInt(character.getPropertyAdditionController().getExtraAttackSpeed());
			/* 攻击速度 * */
			writeInt(character.getPropertyAdditionController().getExtraMoveSpeed());
			/* 移动速度 * */
			writeInt(character.getKillNum());// 杀戮数量
			writeInt(character.getAttackAddpoint());// 攻击型加点
			writeInt(character.getDefenceAddpoint());// 防御型加点
			writeInt(character.getLightAddpoint());// 轻身型加点
			writeInt(character.getStrongAddpoint());// 健体型加点
			// 积分已去掉不用 协议中暂时置为零
			writeInt(0);// character.getIntegral());
			/* --积分 * */
			writeInt(character.getMyFactionManager().getFactionId()); // 发送帮会id
																		// 没加入帮会
																		// 发送-1
			writeByte(character.getMaxBagAmount() / MaxLimit.BagCapacity);
			writeByte(character.getMaxStorageAmount() / MaxLimit.StorageCapacity);
			writeInt(character.getStorageCopper());
			writeByte(character.getMaxStorageHorseAmount());

			String stallname = character.getStallName();
			if (stallname == null || stallname.length() == 0) {
				stallname = character.getViewName() + Language.TANWEI;
			}
			writeUTF(stallname);
			writeInt(character.getChengzhanShengWang());
			writeInt(character.getLunjianShengWang());
			writeInt(character.getContribution());
			DGWDController ct = character.getDgwdController();
			writeInt(ct.getProb("crt"));
			writeInt(ct.getProb("aqjv"));
			writeInt(ct.getProb("hwhurt"));
			writeInt(ct.getProb("bow"));
			writeInt(ct.getProb("dantian"));
			writeInt(character.getChengjiuPoint());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void writeRankTitle(ServerResponse output, Hero character) throws IOException {
		int[] ranktitle = character.getRankingTitle();

		CharacterTianXiaDiYi characterTianXiaDiYi = CharacterTianXiaDiYiManager.getInstance().getHaracterTianXiaDiYiMap()
				.get(character.getCharacterInitiallyId().toString() + character.getOriginalSid());

		if (null != characterTianXiaDiYi) {
			StringBuilder sb = new StringBuilder();
			if (ranktitle == null) {
				ranktitle = new int[1];
				ranktitle[0] = RankingManager.rentianxiadiyi;
			} else {
				// TODO 数组拷贝
				for (int i : ranktitle) {
					sb.append(i);
					sb.append(",");
				}
				sb.append(RankingManager.rentianxiadiyi);
				sb.append(",");
				ranktitle = StringUtil.string_To_int(sb.toString().split(","));
			}

		}

		if (ranktitle != null && ranktitle.length > 0) {
			output.writeByte(ranktitle.length);
			for (int title : ranktitle) {
				output.writeByte(title);
			}
		} else {
			output.writeByte(0);
		}
	}
}
