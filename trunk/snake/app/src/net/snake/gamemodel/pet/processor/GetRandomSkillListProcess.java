package net.snake.gamemodel.pet.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.response.SkillListInfoResponse60040;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.RequestMsg;

/**
 * 获得灵宠技能重置列表 60039
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60039)
public class GetRandomSkillListProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 灵宠ID(int)，请求类型(1展示,2收起,3放生)（byte）

		int id = request.getInt();// 获得灵宠的ID
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(id);
		SkillManager sm = SkillManager.getInstance();
		List<Skill> list = sm.getHorseSkills();
		int size = list.size();
		size = size - 1;
		List<Skill> newlist = new ArrayList<Skill>();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		while (newlist.size() < 16) {
			int tmp = GenerateProbability.randomIntValue(0, size);
			if(map.containsKey(tmp)){
				if(map.get(tmp)>3){
					continue;
				}
				map.put(tmp, map.get(tmp)+1);
			}else{
				map.put(tmp,1);
			}
			newlist.add(list.get(tmp));
		}
		horse.getSkillManager().setRandomSkillList(newlist);
		character.sendMsg(new SkillListInfoResponse60040(newlist,horse.getHorseModel().getResetSkillCopper(),horse.getHorseModel().getResetSkillZhenqi()));
	}
}
