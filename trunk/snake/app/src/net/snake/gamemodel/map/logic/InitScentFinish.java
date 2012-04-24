package net.snake.gamemodel.map.logic;

import java.util.List;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterGradeComparaer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.onhoor.response.AfkConfigMsg50592;
import net.snake.gamemodel.panel.bean.PanelDate;
import net.snake.gamemodel.panel.persistence.PanelDateManager;
import net.snake.gamemodel.panel.response.PanelListMsg52058;

/**
 * 客户端初始化玩场景 集中发送客户端初始化相关其他信息
 * 
 * @author serv_dev
 * 
 */
public class InitScentFinish {
//	private static final Logger logger = Logger.getLogger(InitScentFinish.class);

	/**
	 * 客户端初始化玩场景 集中发送客户端初始化相关其他信息
	 * 
	 * @param character
	 */
	public void sendInitScentChangeFinishOtherInfo(Hero character) {
		// 向聊天服务器发送玩家切换场景消息
		// character.getVlineserver().getChatSessionManager().sendGameToChatServerMsg(new
		// ChatToGsMsg606(character.getId(), character.getScene()));
		// character.sendMsg(new
		// SkipNoviceNavigationMsg50176(character.getSkipNoviceNavigation() ==
		// null ?"" : character.getSkipNoviceNavigation()));
		ChangeScentTeamMsgProcessor(character);
		notifyCharacterGradeRankingProcessor(character);
		character.getEffectController().getSpouseEffectManager().teamReload(character.getMyTeamManager().getMyTeam());// 添加风雨同舟buff
		// checkTitleInfo( character);
		// checkCharacterCampInfo(character);
		// 角色第一次进入场景时进行下面流程
		if (!character.isFristEnterMap()) {
			return;
		}
		// try {
		character.getEffectController().initStatus();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		checkNewcomeLeader(character);

		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		checkMsgInfo(character);

		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		sendContinuteKillTime(character);
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }

		// try {
		character.getAntiAddictedSystem().online();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getMyCharacterGiftpacksManger().sendGiftMsgToClientWhenFirstEnterScene();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getMyCharacterVipManger().sendVipInfo();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getMyFactionManager().online();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getMyCharacterAcrossIncomeManager().online();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getMycharacterAcrossZhengzuoManager().online();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		// 发送面板数据。
		List<PanelDate> list = PanelDateManager.getInstance().getDataByTime();
		for (PanelDate panelDate : list) {
			character.sendMsg(new PanelListMsg52058(panelDate));
		}
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.getCharacterSkillManager().sendUpdateSkillShowMsg();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// try {
		character.sendMsg(new AfkConfigMsg50592(character.getCharacterOnHoorController().getCharacterOnHoorConfig(), character.getCharacterOnHoorController()));// 这个消息放到坐骑信息之后的消息
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		character.setFristEnterMap(false);
		character.getMyFriendManager().onLine();
		character.getMyCharacterWeddingringManager().online();
		// 有婚宴且是配偶方则发送消息
		character.checkLeaveMsg();
		// 新手引导
		character.getMyNewcomeManager().online();
	}

	/**
	 * 组队消息以及组队阵法更新变化
	 * 
	 * @param character
	 */
	private void ChangeScentTeamMsgProcessor(Hero character) {
		character.getMyTeamManager().sendInitScentTeamMsg();
	}

	private void notifyCharacterGradeRankingProcessor(Hero character) {
		int graderanking = CharacterGradeComparaer.comparacterCharacterGrade(character.getSceneRef().getAllCharacters(), character);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 20089, graderanking));
	}

	/**
	 * 连斩系统时间
	 * 
	 * @param character
	 */
	private void sendContinuteKillTime(Hero character) {
		character.getContinuumKillSys().sendInitScentcurrentkillTime();
	}

	// private void checkCharacterCampInfo(Character character){
	// character.getMyCharacterCampManager().sendLoginCampInfo();
	// }
	/**
	 * 玩家上线提示消息发送
	 * 
	 * @param character
	 */
	private void checkMsgInfo(Hero character) {
		character.getMyCharacterMsgManager().sendCharacterMsg();
	}

	/**
	 * 玩家上线检查是否新手引导
	 * 
	 * @param character
	 */
	private void checkNewcomeLeader(Hero character) {
		character.getMyNewcomeManager().sendNewcomeLeader();
	}
	/**
	 * 玩家上线称号信息初始化
	 * 
	 * @param character
	 */
	// private void checkTitleInfo(Character character){
	// character.getMyTitleManager().sendLoginTitleInfo();
	// }

}
