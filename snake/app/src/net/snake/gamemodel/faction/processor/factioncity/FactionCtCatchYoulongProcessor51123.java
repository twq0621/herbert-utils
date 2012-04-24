package net.snake.gamemodel.faction.processor.factioncity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 开始拔出游龙之刃
 * 
 * 
 */
@MsgCodeAnn(msgcode = 51123)
public class FactionCtCatchYoulongProcessor51123 extends CharacterMsgProcessor {
	public int CatchYoulingLang = 4;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int scenemonsterid = request.getInt();
		if (Options.IsCrossServ) {
			catchXuanyuanjian(character, scenemonsterid);
			return;
		}
		short[] point = GongchengTsMap.monsterPoint;
		Scene scene = character.getSceneRef();
		GongchengTsMap sceneImp = null;
		if (scene instanceof GongchengTsMap) {
			sceneImp = (GongchengTsMap) scene;
		}
		if (sceneImp == null) {
			// 离铸剑台3格范围内可以拔剑
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14543));
			return;
		}
		if (sceneImp.getLineId() != 3) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14542));
			return;
		}
		if (DistanceFormula.getDistanceRound(point[0], point[1], character.getX(), character.getY()) > CatchYoulingLang) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14504));
			return;
		}
		if (MyFactionCityZhengDuoManager.monster != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14506));
			return;
		}
		if (!GongchengTsMap.isGongchenging) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14507));
			return;
		}
		SceneFactionCtMonster monster = sceneImp.getSceneObj(SceneObj.SceneObjType_MonsterFactionCt, scenemonsterid);
		if (monster == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14506));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14508));
			return;
		}
		if (character.getGrade() <= 25) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14571, 25 + ""));
			return;
		}
		if (!character.getMyFactionManager().getFactionController().isBangzhu(character.getId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14508));
			return;
		}
		if (!character.getMyFactionCityZhengDuoManager().isHaveTodayGongchengInfo()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14509));
			return;
		}
		character.getCatchYoulongActionController().startTime(monster);
	}

	private void catchXuanyuanjian(Hero character, int scenemonsterid) {
		Scene scene = character.getSceneRef();
		KuafuZhanTsMap sceneImp = null;
		if (scene instanceof KuafuZhanTsMap) {
			sceneImp = (KuafuZhanTsMap) scene;
		}
		if (sceneImp == null) {
			// 离铸剑台3格范围内可以拔剑
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14544));
			return;
		}
		SceneXuanyuanMonster[] xuanyuan = sceneImp.getXuanyuanjian();
		SceneXuanyuanMonster monster = sceneImp.getSceneObj(SceneObj.SceneObjType_MonsterXuanyuan, scenemonsterid);
		for (int i = 0; i < xuanyuan.length; i++) {
			if (xuanyuan[i] != null) {
				if (scenemonsterid == xuanyuan[i].getId()) {
					monster = xuanyuan[i];
					break;
				}
			}
		}
		if (monster == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14545));
			return;
		}
		if (monster.getXuanyuanjianCharacter() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14546, monster.getXuanjianConfig().getXuanyuanjianName()));
			return;
		}
		if (DistanceFormula.getDistanceRound(monster.getX(), monster.getY(), character.getX(), character.getY()) > CatchYoulingLang) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14547, monster.getXuanjianConfig().getXuanyuanjianName()));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14549, monster.getXuanjianConfig().getXuanyuanjianName()));
			return;
		}
		character.getCatchXuanyuanJianActionController().startTime(monster);
	}

	public int getTodayHourse() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		return hours;
	}

}
