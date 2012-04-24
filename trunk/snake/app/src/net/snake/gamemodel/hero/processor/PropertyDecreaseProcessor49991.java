package net.snake.gamemodel.hero.processor;

import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 洗点
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 49991, accessLimit = 100)
public class PropertyDecreaseProcessor49991 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 进攻洗点 int,防御洗点 int,轻身洗点 int,健体点数 int
		int attack = request.getInt();
		int defence = request.getInt();
		int qingshen = request.getInt();
		int jianti = request.getInt();

		if (attack > 0 || defence > 0 || qingshen > 0 || jianti > 0) {
			return;
		}
		int sum = (attack + defence + qingshen + jianti) * -1;
		if (attack + defence + qingshen + jianti >= 0) {
			sendMsg(character, 1072);
			return;
		}
		if (character.getGrade() > 30) {
			int bagGoodsCountByModelID = character.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.XISUIDAN_ID);
			if (bagGoodsCountByModelID >= sum) {
				if (xidian(character, attack, defence, qingshen, jianti)) {
					character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.XISUIDAN_ID, sum);
				}
			} else {
				sendMsg(character, 1073);
			}
		} else {
			xidian(character, attack, defence, qingshen, jianti);
		}
	}

	public boolean xidian(Hero character, int attack, int defence, int qingshen, int jianti) {
		if (xiaoyu0(character.getAttackAddpoint() + attack)) {
			sendMsg(character, 1074);
			return false;
		}
		if (xiaoyu0(character.getDefenceAddpoint() + defence)) {
			sendMsg(character, 1075);
			return false;
		}
		if (xiaoyu0(character.getLightAddpoint() + qingshen)) {
			sendMsg(character, 1076);
			return false;
		}
		if (xiaoyu0(character.getStrongAddpoint() + jianti)) {
			sendMsg(character, 1077);
			return false;
		}
		if (CharacterFormula.extraPropertyAdd(attack, defence, qingshen, jianti, character)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1079, ((attack + defence + qingshen + jianti) * -1) + ""));
			return true;
		} else {
			sendMsg(character, 1078);
			return false;
		}
	}

	boolean xiaoyu0(int value) {
		return value < 0;
	}

	void sendMsg(Hero character, int key) {
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, key));
	}
}
