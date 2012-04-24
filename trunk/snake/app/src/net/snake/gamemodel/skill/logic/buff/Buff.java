package net.snake.gamemodel.skill.logic.buff;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;


public abstract class Buff implements PropertyChangeListener{
	
	
	protected final EffectInfo effect;
	public Buff(EffectInfo effectInfo){
		this.effect = effectInfo;
	}
	
	protected boolean save = true;
	
	public boolean isSave() {
		return save;
	}

	public boolean init(EffectController controller){return true;}
	
	
	public abstract boolean enter(EffectController controller);

	/**
	 * 
	 * @param controller
	 * @return
	 */
	public abstract boolean leave(EffectController controller);
	
	
	public boolean leaveCondition(long now){
		if ((now - effect.getBufBeginTime()) >= effect.getDuration()) {// 时间已到
			return true;
		}
		return false;
	}
	
	public PropertyEntity getPropertyEntity(){
		return null;
		
	}
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.buff;
	}
	
	public EffectInfo getEffectInfo(){
		return effect;
	}
	
}
