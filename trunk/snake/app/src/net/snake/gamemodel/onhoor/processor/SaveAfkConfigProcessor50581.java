package net.snake.gamemodel.onhoor.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.onhoor.response.AfkConfigMsg50592;
import net.snake.gamemodel.onhoor.response.SaveAfkConfigResponse50582;
import net.snake.netio.message.RequestMsg;

/**
 * 
 * @author jack
 *
 */
@MsgCodeAnn(msgcode = 50581, accessLimit = 200)
public class SaveAfkConfigProcessor50581 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
		// (自动补充(byte 0不选1选择),当生命低于1(byte 0-100),自动补充(byte 0不选1选择),当法力低于1(byte 0-100)，使用人物经验丹（byte 0不选1选择）,使用九转还魂丹（byte 0不选1选择），使用琼浆玉液露（byte 0不选1选择））
		boolean autoRevertHp = request.getByte() == 0 ? false : true;
		int revertHp = request.getByte();
		if (revertHp < 0) {
			revertHp = 0;
		} else if (revertHp > 100) {
			revertHp = 100;
		}
		int revertHpMethod = 2;
		boolean autoRevertMp = request.getByte() == 0 ? false : true;
		int revertMp = request.getByte();
		if (revertMp < 0) {
			revertMp = 0;
		} else if (revertMp > 100) {
			revertMp = 100;
		}
		int revertMpMethod = 2;
		// boolean autoRevertSp = false;
		boolean isDoubExp = request.getByte() == 0 ? false : true;
		boolean autoHpBuff = request.getByte() == 0 ? false : true; // 九转还魂丹--自动回血buff
		boolean autoMpBuff = request.getByte() == 0 ? false : true;// 琼浆玉液露--自动回蓝buff
		boolean isDoubZhenqi = false;
		boolean isDoubHorseExp = false;
		// （是否拾取任务品（byte 0不选1选择），是否拾取药品（byte 0不选1选择），是否拾取铜币（byte 0不选1选择），是否拾取其他（byte 0不选1选择））
		boolean isTaskGoods = request.getByte() == 0 ? false : true;
		boolean isYaopin = request.getByte() == 0 ? false : true;
		boolean ismoney = request.getByte() == 0 ? false : true;
		boolean otherGoods = request.getByte() == 0 ? false : true;

		// （人物技能一id(int),人物技能二id(int),人物技能三id(int),是否躲避变异怪和BOSS(byte 0不选1选择),是否自动使用玫瑰花复活(byte 0不选1选择),
		int skillOne = request.getInt();
		int skillTwo = request.getInt();
		int skillThree = request.getInt();
		boolean avoidMonster = request.getByte() == 0 ? false : true;
		boolean backUseRose = request.getByte() == 0 ? false : true;
		// 灵宠活力用真元恢复（byte 0不选1选择），灵宠活力为（int）时使用真元恢复）
		boolean horseAutoLivingness = request.getByte() == 0 ? false : true;
		int horseLivingness = request.getInt();
		byte type = request.getByte();
		characterOnHoorConfig.setType(type);
		characterOnHoorConfig.setAutoMpBuff(autoMpBuff);
		characterOnHoorConfig.setAutoHpBuff(autoHpBuff);
		characterOnHoorConfig.setHorseAutoLivingness(horseAutoLivingness);
		characterOnHoorConfig.setHorseLivingness(horseLivingness);
		characterOnHoorConfig.setSkillOne(skillOne);
		characterOnHoorConfig.setSkillTwo(skillTwo);
		characterOnHoorConfig.setSkillThree(skillThree);

		characterOnHoorConfig.setAutoRevertHp(autoRevertHp);
		characterOnHoorConfig.setRevertHp(revertHp);
		characterOnHoorConfig.setRevertHpMethod(revertHpMethod);
		characterOnHoorConfig.setAutoRevertMp(autoRevertMp);
		characterOnHoorConfig.setRevertMp(revertMp);
		characterOnHoorConfig.setRevertMpMethod(revertMpMethod);
		// characterOnHoorConfig.setAutoRevertSp(autoRevertSp);
		characterOnHoorConfig.setRevertSp(0);
		characterOnHoorConfig.setRevertSpMethod(1);

		characterOnHoorConfig.setExpdan(isDoubExp);
		characterOnHoorConfig.setZhenqidan(isDoubZhenqi);
		characterOnHoorConfig.setHorseExpdan(isDoubHorseExp);

		characterOnHoorConfig.setIsTaskGoods(isTaskGoods);
		characterOnHoorConfig.setIsYaopin(isYaopin);
		characterOnHoorConfig.setOtherGoods(otherGoods);
		characterOnHoorConfig.setIsMoney(ismoney);

		characterOnHoorConfig.setAvoidMonster(avoidMonster);
		characterOnHoorConfig.setBackUseRose(backUseRose);
		
		characterOnHoorConfig.setMoshixiuli(false);
		
		character.getCharacterOnHoorController().saveOnHoorConfig();
		
		
		character.sendMsg(new AfkConfigMsg50592(characterOnHoorConfig, character.getCharacterOnHoorController()));
		character.sendMsg(new SaveAfkConfigResponse50582(0));
	}
}
