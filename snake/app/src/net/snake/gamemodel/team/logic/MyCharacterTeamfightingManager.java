package net.snake.gamemodel.team.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.bean.CharacterTeamfighting;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.persistence.CharacterTeamfightingManager;
import net.snake.gamemodel.team.persistence.TeamFightingManager;
import net.snake.gamemodel.team.response.TeamFightingListMsg10252;
import net.snake.gamemodel.team.response.TeamFightingMsg10254;
import net.snake.netio.ServerResponse;


/**
 * 玩家组队阵法管理器
 * 
 * @author serv_dev
 * 
 */

public class MyCharacterTeamfightingManager {
	private static final Logger logger = Logger.getLogger(MyCharacterTeamfightingManager.class);
	private Hero character;
	private Map<Integer, TeamFightingController> tfcMap = new HashMap<Integer, TeamFightingController>();

	public void destory() {
		if (tfcMap != null) {
			tfcMap.clear();
			tfcMap = null;
		}
	}

	public MyCharacterTeamfightingManager(Hero character) {
		this.character = character;
	}

	public TeamFightingController getOwnerTeamFightingController(int id) {
		return tfcMap.get(id);
	}

	/**
	 * 开启阵法(只有队长可以调用)
	 * 
	 * @param zhenfaId
	 * @return
	 */
	public boolean openTeamfighting(int zhenfaId) {
		if (!character.getMyTeamManager().isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 678));
			return false;
		}
		TeamFightingController tfc = tfcMap.get(zhenfaId);
		if (tfc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 679));
			return false;
		}
		TeamFightingController nowtfc = character.getMyTeamManager().getMyTeam().getNowTfc();
		if (nowtfc != null && zhenfaId == nowtfc.getTf().getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 680));
			return false;
		}
		ServerResponse msg = tfc.checkOpenCondition(character.getMyTeamManager().getMyTeam());
		if (msg != null) {
			character.sendMsg(msg);
			return false;
		}
		if (nowtfc != null) {
			nowtfc.closeTeamFighting(character.getMyTeamManager().getMyTeam());
		}
		tfc.openTeamFighting(character.getMyTeamManager().getMyTeam());
		// character.sendMsg(new
		// TipMsg(TipMsg.MSGPOSITION_ERRORTIP,"恭喜您，"+tfc.getTf().getName()+"阵法开启成功，当前地图的全体队员均获得阵法加成"));
		return true;
	}

	/**
	 * 更新队伍阵法
	 */
	public void updateTeamFighting() {
		TeamFightingController tfc = character.getMyTeamManager().getNowTfc();
		if (tfc == null) {
			useTeamfighting();
			return;
		}
		if (tfc.teamNumEfectProperty()) {
			// tfc.cancelUseTeamFighting(character);
			tfc.useTeamFighting(character);
		}

	}

	/**
	 * 关闭阵法 （只有队长可以调用）
	 * 
	 * @return
	 */
	public boolean closeTeamfighting() {
		if (!character.getMyTeamManager().isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 681));
			return false;
		}
		TeamFightingController tfc = character.getMyTeamManager().getNowTfc();
		if (tfc == null) {
			// character.sendMsg(new
			// TipMsg(TipMsg.MSGPOSITION_ERRORTIP,"目前没有使用阵法"));
			return false;
		}
		tfc.closeTeamFighting(character.getMyTeamManager().getMyTeam());
		// character.sendMsg(new
		// TipMsg(TipMsg.MSGPOSITION_ERRORTIP,tfc.getTf().getName()+"阵法的组阵条件不再相符，阵法自动失效了"));
		return true;
	}

	/**
	 * 阵法生效
	 * 
	 * @return
	 */
	public boolean useTeamfighting() {
		if (!character.getMyTeamManager().isTeam()) {
			return false;
		}
		TeamFightingController tfc = character.getMyTeamManager().getMyTeam().getNowTfc();
		if (tfc == null) {
			return false;
		}
		if (character.getMyTeamManager().getNowTfc() != null) {
			cancelUseTeamfighting();
		}
		boolean b = tfc.useTeamFighting(character);
		if (b) {
			character.getMyTeamManager().setNowTfc(tfc);
			character.sendMsg(new TeamFightingMsg10254(tfc.getTf().getId()));
		}
		return b;
	}

	/**
	 * 阵法失效
	 * 
	 * @return
	 */
	public boolean cancelUseTeamfighting() {
		TeamFightingController tfc = character.getMyTeamManager().getNowTfc();
		if (tfc == null) {
			return false;
		}
		boolean b = tfc.cancelUseTeamFighting(character);
		if (b) {
			character.getMyTeamManager().setNowTfc(null);
			character.sendMsg(new TeamFightingMsg10254(0));
		}
		return b;
	}

	/**
	 * 学习阵法
	 * 
	 * @param mijiId
	 * @return
	 */
	public boolean learnZhenfa(TeamFightingController tfc, int mijiId, int position) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) position);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		if (getOwnerTeamFightingController(tfc.getTf().getId()) != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 683));
			return false;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 684));
			return false;
		}
		CharacterTeamfighting ctf = new CharacterTeamfighting();
		ctf.setCharacterId(character.getId());
		ctf.setLearnTime(new Date());
		ctf.setTeamfightingId(tfc.getTf().getId());
		this.tfcMap.put(ctf.getTeamfightingId(), tfc);
		CharacterTeamfightingManager.getInstance().asynchronousInsert(ctf, character);
		//
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 989, tfc.getTf().getNameI18n()));
		character.getMyCharacterAchieveCountManger().getTeamCount().learnZhenfaCount(this.tfcMap.size());
		return true;
	}

	/**
	 * 发送阵法列表给客户端 学过的与未学过的
	 */
	public void sendTeamFightingList() {
		Collection<TeamFighting> collection = TeamFightingManager.getInstance().getAllTeamFightingCollection();
		List<TeamFighting> owenerlist = new ArrayList<TeamFighting>();
		List<TeamFighting> learnlist = new ArrayList<TeamFighting>();
		for (TeamFighting tf : collection) {
			TeamFightingController tfc = this.tfcMap.get(tf.getId());
			if (tfc != null) {
				owenerlist.add(tf);
			} else {
				learnlist.add(tf);
			}
		}
		Collections.sort(owenerlist);
		Collections.sort(learnlist);
		character.sendMsg(new TeamFightingListMsg10252(owenerlist, learnlist));
	}

	/**
	 * 角色阵法信息初始化
	 */
	public void init() {
		try {
			CharacterTeamfightingManager ctfm = CharacterTeamfightingManager.getInstance();
			List<CharacterTeamfighting> list = ctfm.selectCharacterFightingByCharacterId(character.getId());
			if (list == null || list.size() == 0) {
				return;
			}
			TeamFightingManager tfm = TeamFightingManager.getInstance();
			for (CharacterTeamfighting ctf : list) {
				TeamFightingController tfc = tfm.getFightingControllerById(ctf.getTeamfightingId());
				tfcMap.put(ctf.getTeamfightingId(), tfc);
			}
			character.getMyCharacterAchieveCountManger().getTeamCount().learnZhenfaCount(tfcMap.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
