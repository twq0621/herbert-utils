package net.snake.ai;

import java.util.HashMap;
import java.util.Map;

import net.snake.ai.util.BrokenLine;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.logic.Scene;

/**
 * 该类为追击我的对像计算和保存追击点,以避免追击者集中到一个点上下其手
 * 
 * @author serv_dev
 * 
 */
public class PursuitPointManager {

	VisibleObject me;

	public PursuitPointManager(VisibleObject vo) {
		this.me = vo;
	}

	public void destory() {
		arroundWithMeInFightMonsterPositions.clear();
	}

	private final Map<VisibleObject, short[]> arroundWithMeInFightMonsterPositions = new HashMap<VisibleObject, short[]>();// <mosterId,position>

	public Map<VisibleObject, short[]> getArroundWithMeInFightMonsterPositions() {
		return arroundWithMeInFightMonsterPositions;
	}

	/**
	 * 获得怪追我的目标点,因为当怪多时,需要把怪的目标点进行排列
	 * 
	 * @param monster
	 * @return 附近的一个点
	 */
	public short[] getPursuitPoint(VisibleObject vo, int as) {
		if (vo == null) {
			return null;
		}
		Scene scene = me.getSceneRef();
		if (scene == null) {
			//logger.error("getPursuitPoint(VisibleObject, int) - 该对象{}已经不在场景里了", me.getId()); //$NON-NLS-1$
			return null;
		}
		short[] _ppoint = null;
		short[][] blockTiles = scene.getBlockTiles();
		for (int i = as; i >= 1; i--) {// 先从外向内围
			_ppoint = BrokenLine.getPursuePoint(vo.getX(), vo.getY(), me.getX(), me.getY(), i, blockTiles, arroundWithMeInFightMonsterPositions.values());
			if (_ppoint != null) {
				break;
			}
		}
		if (_ppoint == null) {// 先从内向外围
			for (int i = as + 1; i < as + 10; i++) {
				_ppoint = BrokenLine.getPursuePoint(vo.getX(), vo.getY(), me.getX(), me.getY(), i, blockTiles, arroundWithMeInFightMonsterPositions.values());
				if (_ppoint != null) {
					break;
				}
			}
		}

		if (_ppoint != null) {
			addArroundWithMeInFightMonsterPositions(vo, _ppoint);
		}
		return _ppoint;
	}

	public void clearArroundWithMeInFightMonsterPositions() {
		arroundWithMeInFightMonsterPositions.clear();
	}

	private void addArroundWithMeInFightMonsterPositions(VisibleObject vo, short[] point) {
		arroundWithMeInFightMonsterPositions.put(vo, point);
	}

	public short[] removeArroundWithMeInFightMonsterPositions(VisibleObject vo) {
		return arroundWithMeInFightMonsterPositions.remove(vo);
	}

}
