package net.snake.gameserver.event.copy;

import java.util.ArrayList;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SVO;

/**
 * 两仪阵副本
 * 
 */
public class LiangYiZhen_HpChange extends SuperInstance implements IEventListener {
	public int sceneId = 20176;
	private int groupNum = 5;// 一共5组怪物

	// 30个坐标点
	private int bossId = 12001;// 两仪阵主

	/**
	 * 返回怪物性别,是否是男性
	 * 
	 * @param id
	 * @param currGroupCount
	 * @return
	 */
	private boolean isMale(int id, int currGroupCount) {
		return id - (12000 + currGroupCount * 10) == 1 ? true : false;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SMonster monster, SVO whoAttackMe
		SMonster monster = (SMonster) args[0];
		SVO whoAttackMe = (SVO) args[1];

		if (monster.getSceneRef().getId() != sceneId) {
			return;
		}
		SInstance instance = monster.getSceneRef().getInstance();
		int currGroupCount = (Integer) instance.getAttribute("currGroupCount");
		// 小怪全部杀死
		if (monster.getModel() == bossId && currGroupCount <= groupNum && ((ArrayList<SMonster>) instance.getAttribute("monsters")).size() != 0) {
			monster.changeHp(monster.getMaxHp());
		}

		if (monster.getModel() != bossId) {
			if (whoAttackMe instanceof SRole) {
				if (isMale(monster.getModel(), currGroupCount) != ((SRole) whoAttackMe).isMale()) { // 男性打女性怪物，会造成女性怪物狂暴
					monster.changeAttack(whoAttackMe.getGrade() * (whoAttackMe.getGrade() - 20) * 15); // 攻击力=人物等级*(人物等级-20)*15，
					monster.changeDefence(whoAttackMe.getGrade() * (whoAttackMe.getGrade() - 20) * 7);// 防御力=人物等级*(人物等级-20)*7；
				} else { // 狂暴后的女性怪物如果被女性玩家攻击，则会立即恢复正常状态
					monster.changeAttack((Integer) monster.getAttribute("originalAttack"));
					monster.changeDefence((Integer) monster.getAttribute("originalDefence"));
				}
			}
		}
	}
}
