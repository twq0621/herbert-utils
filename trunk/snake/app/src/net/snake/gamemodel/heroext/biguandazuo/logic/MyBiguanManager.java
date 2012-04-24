package net.snake.gamemodel.heroext.biguandazuo.logic;

import java.util.Date;

import org.apache.log4j.Logger;

import net.snake.ai.formula.CharacterFormula;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.guide.response.NewGuideDropGoodMsg50674;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.heroext.biguandazuo.response.BiguanShouyiMsg50554;
import net.snake.gamemodel.heroext.biguandazuo.response.BiguanTimeMsg50552;



/**
 * 闭关管理器
 * 
 * @author serv_dev
 * 
 */
public class MyBiguanManager {
	private static Logger logger = Logger.getLogger(MyBiguanManager.class);

	private final static int biguanMaxTime = 12 * 60 * 60;
	private Hero character;
	private boolean isSendMsg = false;

	public MyBiguanManager(Hero character) {
		this.character = character;
		isSendMsg = false;
	}

	public void destroy() {

	}

	/**
	 * 发送闭关时间
	 */
	public void sendBiguanTime() {
		int biguantime = getBiguanTime();
		character.sendMsg(new BiguanTimeMsg50552(biguantime));
	}

	/**
	 * 玩家获取闭关收益
	 */
	public void lingquBiguanShouyi(byte type) {
		int beishu = 1;
		if (type == 1) {
			beishu = 2;
			if (!character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.BIGUANLING_ID, 1)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 508));
				return;
			}
		}
		int biguantime = getBiguanTime();
		try {
			int grade = character.getGrade();
			int zhenqi = CharacterPropertyManager.changeZhenqi(character, addbiguanzhenqi(biguantime, grade) * beishu);
			long exp = addbiguanCharacterExp(biguantime, grade) * beishu;
			CharacterFormula.experienceProcess(character, exp);
			character.setBiguanDate(new Date());
			character.sendMsg(new BiguanShouyiMsg50554((int) exp, zhenqi));
			isSendMsg = false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 计算真气收益值
	 * 
	 * @param biguantime
	 * @return
	 */
	public int addbiguanzhenqi(int biguantime, int grade) {
		return biguantime / 300 + grade * biguantime / 3000;
	}

	public long addbiguanCharacterExp(int biguantime, int grade) {
		if (grade < 30) {
			return grade * 100l * biguantime / 3600;
		} else if (grade < 40) {
			return grade * 200l * (biguantime / 3600);
		} else if (grade < 50) {
			return grade * 400l * (biguantime / 3600);
		} else {
			return grade * 600l * biguantime / 3600;
		}
	}

	public int addrideHorseExp(int biguantime, int grade) {
		return grade * 100 * biguantime / 3600;
	}

	/**
	 * 获取闭关时间
	 * 
	 * @return
	 */
	public int getBiguanTime() {
		long biguanStart = character.getBiguanDate().getTime();
		long nowTime = System.currentTimeMillis();
		long time = nowTime - biguanStart;
		long biguantime = time / 1000;
		if (biguantime > biguanMaxTime) {
			biguantime = biguanMaxTime;
		}
		if (biguantime < 0) {
			biguantime = 0;
		}
		return (int) biguantime;
	}

	public void update(long nowTime) {
		if (isSendMsg) {
			return;
		}
		long biguanStart = character.getBiguanDate().getTime();
		if (nowTime - biguanStart > biguanMaxTime * 1000) {
			character.sendMsg(new NewGuideDropGoodMsg50674());
			isSendMsg = true;
		}
	}
}
