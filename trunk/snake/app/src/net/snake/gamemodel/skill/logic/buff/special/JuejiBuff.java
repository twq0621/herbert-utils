package net.snake.gamemodel.skill.logic.buff.special;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyAdditionController;
import net.snake.gamemodel.skill.bean.EffectInfo;

public class JuejiBuff extends UnHpBuff {
	public JuejiBuff(EffectInfo effect) {
		super(effect);
	}

	private List<VisibleObject> chihuan_vos = new ArrayList<VisibleObject>();

	@Override
	public boolean enter(EffectController controller) {
		controller.isImmb();
		// controller.setUnWithstand(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		try {
			for (VisibleObject vo : chihuan_vos) {// 循环上一次中buff的敌方,清除迟缓buff
				vo.getPropertyAdditionController().removeChangeListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void chihuanBuff(Map<Integer, VisibleObject> vosmap) {
		List<VisibleObject> vos = new ArrayList<VisibleObject>();
		for (VisibleObject vo : chihuan_vos) {// 循环上一次中buff的敌方
			if (vosmap.containsKey(vo.getId())) { // 判断这一次是否还会中buff
				vos.add(vo);
			} else {
				PropertyAdditionController propertyAdditionController = vo.getPropertyAdditionController();
				propertyAdditionController.removeChangeListener(this);
			}
		}
		Iterator<Integer> itkey = vosmap.keySet().iterator();
		while (itkey.hasNext()) {
			VisibleObject vo = vosmap.get(itkey.next());
			vos.add(vo);
			PropertyAdditionController propertyAdditionController = vo.getPropertyAdditionController();
			propertyAdditionController.removeChangeListener(this);
			propertyAdditionController.addChangeListener(this);
		}
		chihuan_vos = vos;
	}
}
