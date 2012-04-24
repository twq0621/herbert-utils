package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.MapTeleport;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.WorldSceneUtil;
import org.apache.log4j.Logger;


/**
 * 回城卷轴
 * 
 * @author serv_dev
 * 
 */
public class CharacterTpHuiChengGood implements UseGoodAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CharacterTpHuiChengGood.class);

	public CharacterTpHuiChengGood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}

		if (character.getScene() == ClientConfig.Scene_Xianjing) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 792));
			return false;
		}

		if (character.getGrade() < ClientConfig.huichengLimitGrade) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 793));
			return false;
		}

		if (character.getFightController().inFight(System.currentTimeMillis())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 794));
			return false;
		}

		List<MapTeleport> list = WorldSceneUtil.searchTargetScenePath(character.getSceneRef(), ClientConfig.Scene_Xianjing, character.getVlineserver().getSceneManager());
		if (list == null || list.size() == 0) {
//			logger.warn("无法取得去往襄阳城传送点列表 当前地图:{}", character.getScene());
			return false;
		}
		MapTeleport teleport = list.get(list.size() - 1);

		if (teleport == null) {
			logger.warn("can not find the transport to main city");
			return false;
		}

		Scene enterScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		short[] point = enterScene.getRandomPoint(teleport.getTargetX(), teleport.getTargetY(), 7);
		try {
			ExchangeMapTrans.trans(enterScene, point[0], point[1], character);
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e); //$NON-NLS-1$
			return false;
		}
	}
}
