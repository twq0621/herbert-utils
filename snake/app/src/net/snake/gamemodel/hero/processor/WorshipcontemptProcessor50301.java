package net.snake.gamemodel.hero.processor;

import java.util.Arrays;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Timer;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Worshipcontempt;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.response.WorshipcontemptResponse50302;
import net.snake.gamemodel.hero.response.WorshipcontemptResponse50302ok;
import net.snake.netio.message.RequestMsg;

/**
 * @author serv_dev 人物崇拜鄙视请求返回50302
 */
@MsgCodeAnn(msgcode = 50301)
public class WorshipcontemptProcessor50301 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		int chongbai = -1, bishi = -1;
		// character 人物角色
		Worshipcontempt worshipcontempt = character.getMyWorshipcontemptManager().getWorshipcontempt();
		if (null == worshipcontempt) {
			worshipcontempt = new Worshipcontempt();
		}
		worshipcontempt.setCharacterId(character.getId());
		String newtimeString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");

		// byte（0鄙视1崇拜）
		byte type = request.getByte();
		// 要鄙视或者崇拜人的id
		int characterid = request.getInt();
		// 那个板块
		byte bankuai_type = request.getByte();
		if (character.getId() == characterid) {
			WorshipcontemptResponse50302 response = new WorshipcontemptResponse50302(CommonUseNumber.byte0, type, 1107);
			character.sendMsg(response);
			return;
			// 等级验证
		}
		if (character.getGrade() < 20) {
			WorshipcontemptResponse50302 response = new WorshipcontemptResponse50302(CommonUseNumber.byte0, type, 1108);
			character.sendMsg(response);
			return;
		}

		// 崇拜逻辑
		if (type == 1) {
			String worshipidString = worshipcontempt.getWorshipId();
			if (null == worshipidString || worshipidString.length() < 2) {
				worshipcontempt.setWorshipId(characterid + "#" + bankuai_type + ",");
				worshipcontempt.setTime(newtimeString);
				character.getMyWorshipcontemptManager().setWorshipcontempt(worshipcontempt);
				if (null != worshipcontempt.getContemptId()) {
					character.getMyWorshipcontemptManager().updateWorshipcontempt(worshipcontempt);
				} else {
					character.getMyWorshipcontemptManager().addWorshipcontempt(worshipcontempt);
				}
				Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
				if (character2 != null) {

					int count = character2.getWorshipcount();
					character2.setWorshipcount(count + 1);
					chongbai = character2.getWorshipcount();
					bishi = character2.getContemptcount();
				} else {
					CharacterManager.getInstance().updateCharacterchongbai(characterid);
				}
				WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
				character.sendMsg(response);
			} else {
				// 验证是不是崇拜过了
				List<String> list = Arrays.asList(worshipidString.split(","));
				if (!list.contains(String.valueOf(characterid) + "#" + bankuai_type)) {
					worshipidString = worshipidString + String.valueOf(characterid) + "#" + bankuai_type + ",";
					worshipcontempt.setWorshipId(worshipidString);
					worshipcontempt.setTime(newtimeString);
					character.getMyWorshipcontemptManager().setWorshipcontempt(worshipcontempt);
					character.getMyWorshipcontemptManager().updateWorshipcontempt(worshipcontempt);

					Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
					if (character2 != null) {

						int count = character2.getWorshipcount();
						character2.setWorshipcount(count + 1);
						chongbai = character2.getWorshipcount();
						bishi = character2.getContemptcount();
					} else {
						CharacterManager.getInstance().updateCharacterchongbai(characterid);
					}
					WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
					character.sendMsg(response);

				} else {
					String oldtimeString = worshipcontempt.getTime();
					int count = Integer.valueOf(Timer.getTwoDay(newtimeString, oldtimeString));
					if (count == 0) {

						WorshipcontemptResponse50302 response = new WorshipcontemptResponse50302(CommonUseNumber.byte0, type, 1109);
						character.sendMsg(response);
					} else {

						Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
						if (character2 != null) {

							int count2 = character2.getWorshipcount();
							character2.setWorshipcount(count2 + 1);
							chongbai = character2.getWorshipcount();
							bishi = character2.getContemptcount();
						} else {
							CharacterManager.getInstance().updateCharacterchongbai(characterid);
						}
						WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
						character.sendMsg(response);
					}
				}
			}
			// 鄙视逻辑
		} else {
			String contemptidString = worshipcontempt.getContemptId();
			if (null == contemptidString || contemptidString.length() < 2) {
				worshipcontempt.setContemptId(characterid + "#" + bankuai_type + ",");
				worshipcontempt.setTime(newtimeString);
				character.getMyWorshipcontemptManager().setWorshipcontempt(worshipcontempt);
				if (null != worshipcontempt.getWorshipId()) {

					character.getMyWorshipcontemptManager().updateWorshipcontempt(worshipcontempt);
				} else {
					character.getMyWorshipcontemptManager().addWorshipcontempt(worshipcontempt);
				}

				Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
				if (character2 != null) {

					int count2 = character2.getContemptcount();
					character2.setContemptcount(count2 + 1);
					chongbai = character2.getWorshipcount();
					bishi = character2.getContemptcount();
				} else {
					CharacterManager.getInstance().updateCharacterbishi(characterid);
				}

				WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
				character.sendMsg(response);
			} else {
				// 验证是不是鄙视过
				List<String> list = Arrays.asList(contemptidString.split(","));
				if (!list.contains(String.valueOf(characterid) + "#" + bankuai_type)) {
					contemptidString = contemptidString + String.valueOf(characterid) + "#" + bankuai_type + ",";
					worshipcontempt.setContemptId(contemptidString);
					worshipcontempt.setTime(newtimeString);
					character.getMyWorshipcontemptManager().setWorshipcontempt(worshipcontempt);
					character.getMyWorshipcontemptManager().updateWorshipcontempt(worshipcontempt);

					Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
					if (character2 != null) {

						int count2 = character2.getContemptcount();
						character2.setContemptcount(count2 + 1);
						chongbai = character2.getWorshipcount();
						bishi = character2.getContemptcount();
					} else {
						CharacterManager.getInstance().updateCharacterbishi(characterid);
					}
					WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
					character.sendMsg(response);
				} else {
					String oldtimeString = worshipcontempt.getTime();
					int count = Integer.valueOf(Timer.getTwoDay(newtimeString, oldtimeString));
					if (count == 0) {

						WorshipcontemptResponse50302 response = new WorshipcontemptResponse50302(CommonUseNumber.byte0, type, 1109);
						character.sendMsg(response);
					} else {

						Hero character2 = GameServer.vlineServerManager.getCharacterById(characterid);
						if (character2 != null) {

							int count2 = character2.getContemptcount();
							character2.setContemptcount(count2 + 1);
							chongbai = character2.getWorshipcount();
							bishi = character2.getContemptcount();
						} else {
							CharacterManager.getInstance().updateCharacterbishi(characterid);
						}
						WorshipcontemptResponse50302ok response = new WorshipcontemptResponse50302ok(CommonUseNumber.byte1, type, chongbai, bishi);
						character.sendMsg(response);
					}
				}
			}
		}
	}

}
